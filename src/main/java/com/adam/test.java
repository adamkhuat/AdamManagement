//package com.adam;
//
//import com.adam.constants.Constants;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class test {
//    public static void main(String[] args) {
//        String s = "29-2-2016";
//        String a = "11.02.2020";
//        String b = "11/02/2020";
//        String c = "11 02 2020";
//        s = s.replace("-", "/");
//
//        String regex = Constants.DATE_REGEX;
//
//        if (s.matches(regex)) {
//            System.out.println(s + " true");
//        } else System.out.println(s + " false");
//        if (a.matches(regex)) {
//            System.out.println(a + " true");
//        } else System.out.println(a + " false");
//        if (b.matches(regex)) {
//            System.out.println(b + " true");
//        } else System.out.println(b + " false");
//        if (c.matches(regex)) {
//            System.out.println(c + " true");
//        } else System.out.println(c + " false");
//
//        System.out.println(s);
//        try {
////            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
////            System.out.println(date);
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            dateFormat.setLenient(false);
//            dateFormat.parse(s);
//            System.out.println(s);
//
//        } catch (ParseException e) {
//            System.out.println("Format failed");
//        }
//    }
//}
