package com.example.android.newsapp.util;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.example.android.newsapp.App;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by kevinsun on 11/27/17.
 */

public class MyUtil {

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout){
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWidth();

        if(tabWidth <= screenWidth){
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private static int calculateTabWidth(TabLayout tabLayout){
        int tabWidth = 0;
        for(int i = 0; i< tabLayout.getChildCount(); i++){
            final View view = tabLayout.getChildAt(i);
            view.measure(0,0);

            tabWidth += view.getMeasuredWidth();
        }
    return  tabWidth;
    }
    public static int getScreenWidth(){
        return App.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static String formateDate(String before){
        if(before.contains("T")) {
            String s1 = before.replace("T", " ");

            StringBuilder stringBuilder = new StringBuilder(s1);
            stringBuilder.deleteCharAt(s1.lastIndexOf("Z"));
            return stringBuilder.toString();
        }
        return before;
    }
}
