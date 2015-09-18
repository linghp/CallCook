package com.peoit.callcook.beans;

import java.io.Serializable;

/**
 * Created by ling on 2015/9/15.
 * description:
 */
public class HomeBean implements Serializable{
    public static  final String type1 ="header";
    public static  final String type2 ="content1";
    public static  final String type3 ="content2";
    public String type;
    public String content="123325455";

    public HomeBean(String type) {
        this.type = type;
    }
}
