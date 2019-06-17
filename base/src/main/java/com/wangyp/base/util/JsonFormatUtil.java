package com.wangyp.base.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

/**
 * author:wangyp
 * Date:2018/11/25
 * Description:
 */
public class JsonFormatUtil {

    public static final String TAG = "jsonFormatUtil";

    public static void jsonFormatPrint(String tag,String json){
        if (TextUtils.isEmpty(json)){
            return;
        }
        try {
            jsonFormatPrint(tag, new JSONObject(json));
        } catch (JSONException e) {
            Log.i(tag,e.toString());
        }
    }

    public static void jsonFormatPrint(String tag,JSONObject rootJson){
        if (rootJson == null){
            return;
        }
        String msg = formatJSONObject(new StringBuilder(" \n"), rootJson,0).toString();
        printInfo(tag,msg);
    }


    private static void printInfo(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }

    public static StringBuilder formatJSONObject(StringBuilder sb, JSONObject rootJson,int level) {
        sb.append("{");
        Iterator<String> keys = rootJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            sb.append("\n").append(getTabForNum(level+1)).append("\"").append(key).append("\":");
            Object value = rootJson.opt(key);
            if (value instanceof String) {
                sb.append("\"").append((String)value).append("\"").append(keys.hasNext() ? "," :"");
            } else if (value instanceof JSONObject) {
                sb.append("\n").append(getTabForNum(level+1));
                formatJSONObject(sb, (JSONObject)value,level+1);
                sb.append(keys.hasNext() ? "," :"");
            } else if (value instanceof JSONArray) {
                formatJSONArray(sb, (JSONArray)value,level+1);
                sb.append(keys.hasNext() ? "," :"");
            } else {
                sb.append(value).append(keys.hasNext() ? "," :"");
            }
        }
        sb.append("\n").append(getTabForNum(level)).append("}");
        return sb;
    }

    private static String getTabForNum(int num){
        String tab = "";
        while (num >0){
            tab += "\t";
            num--;
        }
        return tab;
    }

    private static void formatJSONArray(StringBuilder sb, JSONArray rootJson,int level) {
        sb.append("[").append("\n");
        int count = rootJson.length();
        for (int key=0;key < count;key++){
            sb.append(getTabForNum(level+1));
            Object value = rootJson.opt(key);
            if (value instanceof String) {
                sb.append("\"").append((String)value).append("\"").append(key != count -1 ? "," : "").append("\n");
            } else if (value instanceof JSONObject) {
                formatJSONObject(sb, (JSONObject)value,level+1);
                sb.append(key != count -1 ? "," : "").append("\n");
            } else if (value instanceof JSONArray) {
                formatJSONArray(sb, (JSONArray)value,level+1);
            } else {
                sb.append(value).append(key != count -1 ? "," : "").append("\n");
            }
        }
        sb.append(getTabForNum(level)).append("]");
    }

}
