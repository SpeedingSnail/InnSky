package com.wyp.innsky.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.wyp.innsky.BaseActivity;
import com.wyp.innsky.R;
import com.wyp.innsky.utils.StringFromatUtil;

/**
 * Created by yingping_wang on 2018/11/22.
 */
public class ViewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
        TextView tvJson = findViewById(R.id.tvJson);
        tvJson.setText(StringFromatUtil.formatJson(this));
    }
}
