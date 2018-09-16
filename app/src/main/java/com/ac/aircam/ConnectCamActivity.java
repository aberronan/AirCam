package com.ac.aircam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac.aircam.base.BaseActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by chengzhirui on 2018/9/8.
 * 连接Air相机页面
 */
@Route(path = "/bundle/connect_cam_activity")
public class ConnectCamActivity extends BaseActivity {

    private ImageView backImg;
    private ImageView closeImg;
    private ImageView camImg;
    private TextView camIdTv;
    private TextView connectStatusTv;
    private TextView retryTv;
    private TextView camConnectTipsTv;

    private Button testAirCamWifiBtn; //测试控件，进入配置相机的wifi页面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_cam);
        initView();
    }

    private void initView() {
        backImg = findViewById(R.id.back_img);
        closeImg = findViewById(R.id.cancel_img);
        camImg = findViewById(R.id.cam_img);
        connectStatusTv = findViewById(R.id.cam_connect_status_tv);
        camIdTv = findViewById(R.id.cam_id_tv);
        retryTv = findViewById(R.id.retry_tv);
        camConnectTipsTv = findViewById(R.id.cam_connect_tips_tv);

        testAirCamWifiBtn = findViewById(R.id.air_cam_wifi_btn);

        testAirCamWifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnectCamActivity.this, ConfigWifiActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        retryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            setResult(1);
            finish();
        }
    }
}
