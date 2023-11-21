package com.geovis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.Date;

public class CommonTest {
    @Test
    public void common() {
//        String a = "12050009";
//        char[] code = a.toCharArray();
//        for (int i = code.length - 1; i > 0; i--) {
//            if (!StrUtil.equals(code[i] + "", "0")) {
//                a = a.substring(0, i+1);
//                System.out.println(a);
//                return;
//            }
//        }
        System.out.println(DateUtil.endOfDay(new Date()).getTime()-(new Date().getTime()));
    }
}
