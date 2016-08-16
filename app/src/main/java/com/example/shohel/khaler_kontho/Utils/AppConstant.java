package com.example.shohel.khaler_kontho.Utils;


import java.util.ArrayList;

public class AppConstant {
    public static final String[] FACEBOOK_PERMISSION = {"email", "user_about_me", "read_stream", "user_photos", "public_profile" };
    public static String COLOR_MAIN = "#A551D0";

    public static final String COLOR_DEFAULT_MAIN = "#A551D0";
    public static final String COLOR_DEFAULT_SECONDARY = "#FFFFFF";

    public static final String COLOR_SETTINGS = "#4C436E";
    public static final String COLOR_SHARE = "#0054A5";
    public static final String BTN_COLOR_ORIGIN = "#B166D6";

    // news item data
    // lv position item save
/*
    public static int topnews_size=0;
    public static int seperator_latestnews_size=0;
    public static int latestnews_size=0;
    public static int seperator_bibid_size=0;
    public static int seperator_specialnews_size=0;
    public static int specialnews_size=0;
    public static int seperator_oternews_size=0;
*/

    public static final String[] allMonth = {"জানুয়ারী","ফেব্রুয়ারি","মার্চ","এপ্রিল","মে","জুন","জুলাই","অগাস্ট","সেপ্টেম্বর","অক্টবর","নভেম্বর","ডিসেম্বর"};

    private static final char[] banglaDigits = {'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
    private static final char[] englishDigits = {'0','1','2','3','4','5','6','7','8','9'};


    public  static final String  getDigitBanglaFromEnglish(String number){
        if(number==null)
            return new String("");
        StringBuilder builder = new StringBuilder();
        try{
            for(int i =0;i<number.length();i++){
                if(Character.isDigit(number.charAt(i))){
                    if(((int)(number.charAt(i))-48)<=9){
                        builder.append(banglaDigits[(int)(number.charAt(i))-48]);
                    }else{
                        builder.append(number.charAt(i));
                    }
                }else{
                    builder.append(number.charAt(i));
                }
            }
        }catch(Exception e){

            return new String("");
        }
        return builder.toString();
    }



}
