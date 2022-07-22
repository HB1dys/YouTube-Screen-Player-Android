package com.example.youtube;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

;

public class Common {
    private static final String PACKAGE = "com.example.youtube";

    // ===========================================
    // Browser
    // ===========================================

    public static void InitCustomScreenSettings(Window window, ActionBar supportActionBar) {
        supportActionBar.hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.getInsetsController().hide(WindowInsets.Type.statusBars());
        }
        else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static void InitBrowserSettings(Bundle savedInstanceState, WebView webView) {
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            webView.setWebChromeClient(new WebChromeClient());
        }
    }
    public static ArrayList<String> DeserializeAL(String json)
    {
        Gson gson = new Gson();
        Type type=new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(json,type);
    }

    // ===========================================
    // Utils
    // ===========================================
    public static boolean isUrlValid(String str) {
        return android.util.Patterns.WEB_URL.matcher(str).matches();
    }

    public static String SerializeClass(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Object DeserializeClass(String json, Object obj) {
        Gson gson = new Gson();
        return gson.fromJson(json,obj.getClass());
    }

    public static void EditPreferenceString(Activity activity, String key, String string) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putString(key, string).apply();
    }

    public static void EditPreferenceStringSet(Activity activity, String key, Set<String> set) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putStringSet(key, set).apply();
    }

    public static void EditPreferenceInt(Activity activity, String key, int integer) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putInt(key, integer).apply();
    }

    public static void EditPreferenceFloat(Activity activity, String key, float fl) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putFloat(key, fl).apply();
    }

    public static void EditPreferenceLong(Activity activity, String key, long lng) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putLong(key, lng).apply();
    }

    public static void EditPreferenceBoolean(Activity activity, String key, boolean bool) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().putBoolean(key, bool).apply();
    }

    public static String getPreferenceString(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getString(key, null);
    }

    public static Set<String> getPreferenceStringSet(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getStringSet(key, null);
    }

    public static int getPreferenceInt(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getInt(key,0);
    }

    public static float getPreferenceFloat(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getFloat(key,0);
    }

    public static long getPreferenceLong(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getLong(key,0);
    }

    public static boolean getPreferenceBoolean(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        return prefs.getBoolean(key,false);
    }

    public static void removeSharedPreference(Activity activity, String key){
        SharedPreferences prefs = activity.getSharedPreferences(
                PACKAGE, Context.MODE_PRIVATE);
        key = PACKAGE+"."+key;
        prefs.edit().clear().commit();
    }
}
