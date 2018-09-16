package com.ac.aircam;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac.aircam.base.BaseActivity;

/**
 * Created by chengzhirui on 2018/9/16.
 * 输入wifi密码界面
 */
public class WifiKeyActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backImg;
    private ImageView closeImg;
    private TextView wifiNameTv;
    private TextView connectTv;
    private EditText editText;
    private ImageView deleteImg;
    private ImageView eyeImg;
    private ScanResult wifiSource;

    private boolean isShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_key);
        wifiSource = getIntent().getParcelableExtra("wifi");
        initView();
    }

    private void initView() {
        backImg = findViewById(R.id.back_img);
        closeImg = findViewById(R.id.cancel_img);
        wifiNameTv = findViewById(R.id.wifi_name_tv);
        connectTv = findViewById(R.id.connect_tv);
        editText = findViewById(R.id.key_et);
        deleteImg = findViewById(R.id.delete_img);
        eyeImg = findViewById(R.id.eye_img);
        eyeImg.setBackground(getResources().getDrawable(R.drawable.eye_close));
        if (wifiSource != null) {
            if (!TextUtils.isEmpty(wifiSource.SSID)) {
                wifiNameTv.setText(wifiSource.SSID);
            }
        }
        eyeImg.setOnClickListener(this);
        connectTv.setOnClickListener(this);
        backImg.setOnClickListener(this);
        closeImg.setOnClickListener(this);
        deleteImg.setOnClickListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    deleteImg.setVisibility(View.VISIBLE);
                    connectTv.setBackgroundColor(Color.parseColor("#63b8ff"));
                    connectTv.setTextColor(Color.parseColor("#ffffff"));
                    connectTv.setClickable(true);
                } else {
                    connectTv.setBackgroundColor(Color.parseColor("#dbdbdb"));
                    connectTv.setTextColor(Color.parseColor("#8a8a8a"));
                    connectTv.setClickable(false);
                    deleteImg.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_tv:
                //TODO 相机连接wifi
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.cancel_img:
                setResult(1);
                finish();
                break;
            case R.id.delete_img:
                editText.getText().clear();
                deleteImg.setVisibility(View.GONE);
                break;
            case R.id.eye_img:
                if (isShow) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeImg.setBackground(getResources().getDrawable(R.drawable.eye_close));
                } else {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeImg.setBackground(getResources().getDrawable(R.drawable.eye_open));
                }
                isShow = !isShow;
                break;
        }
    }
}
