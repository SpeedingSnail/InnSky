package com.wyp.innsky.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wangyp.base.BaseActivity;
import com.wangyp.base.util.JsonFormatUtil;
import com.wyp.innsky.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author:wangyp
 * Date:2018/11/25
 * Description:
 */
public class TestActivity extends BaseActivity {


    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void testJson(View view) {
        test(this);

    }

    public static void test(Context context){
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("test.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while((str = br.readLine())!=null){
                stringBuffer.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonFormatUtil.jsonFormatPrint(TAG,stringBuffer.toString());
    }
}
