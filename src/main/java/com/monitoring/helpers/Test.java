package com.monitoring.helpers;

import java.text.SimpleDateFormat;

/**
 * Created by venisac on 7/16/16.
 */
public class Test {

    public static void main(String args[]) {
        String startDate = "2016.07.10 07:06:27 PM UTC";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a zzzz");

        //String startDate = "2001-07-04T12:08:56.235-0700";
        //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        java.util.Date date = null;
        try {
            date = sdf1.parse(startDate);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(date);

        java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());

        System.out.print(sqlStartDate);

    }
}
