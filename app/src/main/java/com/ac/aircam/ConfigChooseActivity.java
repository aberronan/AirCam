package com.ac.aircam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac.aircam.base.BaseActivity;
import com.ac.aircam.model.CamInfoDataBean;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhirui on 2018/9/8.
 * 选择要配置的Air相机
 * 相机数据列表是假数据
 */
@Route(path = "/bundle/config_choose_activity")
public class ConfigChooseActivity extends BaseActivity {

    private ImageView backImg;
    private ImageView closeImg;
    private ImageView refreshImg;
    private LinearLayout searchingLayout;
    private TextView emptyTipsTv;
    private RecyclerView recyclerView;
    private ChooseConfigListAdapter adapter;

    private LinearLayoutManager layoutManager;

    private List<CamInfoDataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_choose);
        initView();
        mockGetAirCams();
    }

    /**
     * mock数据
     */
    private void mockGetAirCams() {
        list.clear();
        for (int i = 0; i < 15; i++) {
            CamInfoDataBean bean = new CamInfoDataBean();
            bean.setName(i + "");
            list.add(bean);
        }
        adapter.setData(list);
        if (list.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            refreshImg.setVisibility(View.GONE);
            searchingLayout.setVisibility(View.VISIBLE);
            emptyTipsTv.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            refreshImg.setVisibility(View.VISIBLE);
            searchingLayout.setVisibility(View.GONE);
            emptyTipsTv.setVisibility(View.GONE);
        }
    }

    private void initView() {
        backImg = findViewById(R.id.back_img);
        closeImg = findViewById(R.id.cancel_img);
        refreshImg = findViewById(R.id.refresh_img);
        searchingLayout = findViewById(R.id.searching_layout);
        emptyTipsTv = findViewById(R.id.empty_tips_tv);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(ConfigChooseActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChooseConfigListAdapter(ConfigChooseActivity.this);
        recyclerView.setAdapter(adapter);

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
