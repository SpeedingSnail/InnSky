package com.wyp.innsky.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wangyp.base.BaseActivity;
import com.wyp.innsky.R;
import com.wyp.innsky.dialog.InnDialogFragment;

/**
 * Created by yingping_wang on 2018/11/22.
 */
public class ViewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
    }

    public void start(View view) {
        InnDialogFragment.create(this,getSupportFragmentManager())
                .setTitle("标题")
                .setMessage("这里是内容的展示地方")
                .setPositive("确认", v -> Toast.makeText(getBaseContext(),"确认",Toast.LENGTH_SHORT).show())
                .setNegative("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(),"取消",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
