package com.wyp.innsky.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by yingping_wang on 2018/11/22.
 */
public class StringFromatUtil {

    public static String formatJson(Context context) {
        String str = "";
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("test.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();

            while((str = br.readLine())!=null){
                stringBuffer.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject rootJson = new JSONObject(str);
            formatJSONObject(sb, rootJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static StringBuilder formatJSONObject(StringBuilder sb, JSONObject rootJson) {
        sb.append("{\n\t");
        Iterator<String> keys = rootJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            sb.append("\"").append(key).append("\":");
            Object value = rootJson.opt(key);
            if (value instanceof String) {
                sb.append("\"").append(rootJson.optString(key)).append("\",\n");
            } else if (value instanceof Integer) {
                sb.append("\"").append(rootJson.optInt(key)).append("\",\n");
            } else if (value instanceof Double) {
                sb.append("\"").append(rootJson.optDouble(key)).append("\",\n");
            } else if (value instanceof Long) {
                sb.append("\"").append(rootJson.optLong(key)).append("\",\n");
            } else if (value instanceof Boolean) {
                sb.append("\"").append(rootJson.optBoolean(key)).append("\",\n");
            } else if (value instanceof JSONObject) {
                formatJSONObject(sb, rootJson.optJSONObject(key));
            } else if (value instanceof JSONObject) {
                formatJSONArray(sb, rootJson.optJSONArray(key));
            }
        }
        sb.append("}");
        return sb;
    }

    private static void formatJSONArray(StringBuilder sb, JSONArray rootJson) {
        sb.append("[");
        for (int key=0;key < rootJson.length();key++){
            Object value = rootJson.opt(key);
            if (value instanceof String) {
                sb.append("\"").append(rootJson.optString(key)).append("\",\n");
            } else if (value instanceof Integer) {
                sb.append("\"").append(rootJson.optInt(key)).append("\",\n");
            } else if (value instanceof Double) {
                sb.append("\"").append(rootJson.optDouble(key)).append("\",\n");
            } else if (value instanceof Long) {
                sb.append("\"").append(rootJson.optLong(key)).append("\",\n");
            } else if (value instanceof Boolean) {
                sb.append("\"").append(rootJson.optBoolean(key)).append("\",\n");
            } else if (value instanceof JSONObject) {
                formatJSONObject(sb, rootJson.optJSONObject(key));
            } else if (value instanceof JSONObject) {
                formatJSONArray(sb, rootJson.optJSONArray(key));
            }
        }
        sb.append("]");
    }

}
