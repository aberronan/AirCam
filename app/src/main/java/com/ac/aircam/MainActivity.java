package com.ac.aircam;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ac.aircam.base.BaseActivity;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 启动页面
 */
public class MainActivity extends BaseActivity {

    private ImageView acImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        acImage = findViewById(R.id.ac_img);
        acImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/bundle/camManagerActivity")
                        .navigation();
            }
        });
    }
}
