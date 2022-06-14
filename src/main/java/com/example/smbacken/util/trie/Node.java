package com.example.smbacken.util.trie;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

public class Node implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private HashMap<Character,Node> childMap;
    private boolean isLeaf;

    public Node(){
        setLeaf(false);
        setChildMap(new HashMap<>());
    }

    public HashMap<Character, Node> getChildMap() {
        return childMap;
    }

    public void setChildMap(HashMap<Character, Node> childMap) {
        this.childMap = childMap;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
