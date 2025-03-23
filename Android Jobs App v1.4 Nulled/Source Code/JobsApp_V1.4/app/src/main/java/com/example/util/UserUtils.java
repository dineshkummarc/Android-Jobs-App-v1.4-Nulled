package com.example.util;

import com.app.jobs.MyApplication;

public class UserUtils {
    public static String getUserId() {
        return MyApplication.getInstance().getIsLogin() ? MyApplication.getInstance().getUserId() : "";
    }
}
