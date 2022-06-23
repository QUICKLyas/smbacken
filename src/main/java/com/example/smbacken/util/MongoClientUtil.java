package com.example.smbacken.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MongoClientUtil {
    public static String dateToObjectId() throws ParseException {

        StringBuffer objectId = new StringBuffer("");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //转换为16进制的字符串
        objectId.append(Long.toHexString(date.getTime() / 1000));
        //bson-3.6.4.jar 版本校验ObjectId的长度为24位，不足24位补0
        while(objectId.length() < 24) {
            objectId.append("0");
        }
        return objectId.toString();
    }
}
