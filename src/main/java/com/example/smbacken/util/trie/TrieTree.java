package com.example.smbacken.util.trie;

import java.util.HashSet;
import java.util.Map;

/**
 *
 */
public class TrieTree {
    private static final int maxStr = 200;
    private Node root;

    public TrieTree(){
        root = new Node();
    }
    public void insert(String words){
        insert(this.root,words);
    }

    private void insert (Node root,String words){
        if(words == null || "".equals(words)) return;
        char[] chars = words.toLowerCase().toCharArray();
        for (int i = 0, length = chars.length; i < length;i++){
            if(!root.getChildMap().containsKey(chars[i])) {
                root.getChildMap().put(chars[i], new Node());
            } if(i==length -1) {
                root.getChildMap().get(chars[i]).setLeaf(true);
            }
            root = root.getChildMap().get(chars[i]);
        }
    }
    private HashSet<String> preTraversal(Node root,String preStr){
        HashSet<String> set = new HashSet<>();
        if(root != null) {
            if(root.isLeaf()){
                set.add(preStr);
            }
//            System.out.println(root.getChildMap().entrySet());
            for (Map.Entry<Character,Node> chr : root.getChildMap().entrySet()){
//                System.out.println(chr.getKey());
                String tempStr = preStr + chr.getKey();
//                System.out.println(tempStr);
                if(set.size() > maxStr) return set;
                // chr.getValue获取子结点位置
                set.addAll(preTraversal(chr.getValue(), tempStr));
//                System.out.println(set);
            }
        }
        return set;
    }

    public HashSet<String> getWordsForPrefix(String...word){
        return getWordsForTrie(this.root,word);
    }

    private HashSet<String> getWordsForTrie(Node root,String...word) {
//        System.out.println("words:"+word[0]+","+word[1]);
        char[] chars = word[0].toLowerCase().toCharArray();
        HashSet<String> set = new HashSet<>();
        String preStr = word[1];
        // 遍历第一层所有的结点
        for (Map.Entry<Character,Node> chr : root.getChildMap().entrySet()){
            for (int i = 0,length = chars.length;i < length;i++){
                String tmpStr = "";
                tmpStr = preStr + chr.getKey();
                if(chr.getValue().getChildMap().containsKey(chars[i])){
                    // 因为在查询过程中已经是使用整个字段的模糊而不是某个字的模糊，所以有匹配到第一个字符，那么后续也会被匹配到
//                    System.out.println("当前tmpStr:"+tmpStr+","+"当前chr内容:"+chr.getKey());
                    set.addAll(preTraversal(chr.getValue().getChildMap().get(chars[i]), tmpStr+chars[i]));
//                    System.out.println("当前集合内容："+set);
                } if (!chr.getValue().getChildMap().containsKey(chars[i]) ){
                    if(chr.getValue().isLeaf()){
//                        System.out.print("结点内容:"+chr.getKey()+","+"结点状态:"+chr.getValue().isLeaf());
                        break;
                    }
//                    System.out.println("结点内容:"+chr.getKey() +","+ "临时字符内容:"+tmpStr  +"," + "当前检测字符:"+chars[i]);
                    set.addAll(getWordsForTrie(root.getChildMap().get(chr.getKey()),word[0],tmpStr));
                }
            }
        }return set;
    }

}