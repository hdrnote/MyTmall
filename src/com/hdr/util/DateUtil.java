package com.hdr.util;

public class DateUtil {
    public static java.sql.Timestamp d2t(java.util.Date d){
        if (d==null) return null;
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp t){
        if(t==null) return null;
        return new java.util.Date(t.getTime());
    }

    public static void main(String[] args){
        java.util.Date d = new java.util.Date();
        System.out.println(d);
        System.out.println(d2t(d));
        System.out.println(t2d(d2t(d)));
    }

}
