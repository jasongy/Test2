package com.accuracy.test.fragment;


import com.accuracy.test.R;

/**
 * Created by wenmingvs on 15/12/26.
 */
public class TabDB {

    public static String[] getTabText() {
        String[] tabs = {"首页", "详情",  "发现", "我"};
        return tabs;
    }

    public static int[] getTabImg() {
        int[] imags = {R.drawable.tabbar_home, R.drawable.tabbar_message_center,
               R.drawable.tabbar_discover,
                R.drawable.tabbar_profile};
        return imags;
    }


    public static Class[] getFragments() {
        Class[] classess = {MainFragment.class, DetailFragment.class,
                RouteFragment.class, PersonalFragment.class};
        return classess;
    }


}