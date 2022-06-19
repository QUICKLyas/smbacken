package com.example.smbacken.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateF {
    public static void getTime(String msg){
        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter)+"\t\t" + msg);
    }
}
