package com.ac.aircam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ac.aircam.base.BaseActivity;
import com.ac.aircam.model.CamInfoDataBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhirui on 2018/9/8.
 * Air相机管理
 * 目前相机列表是假数据
 */
@Route(path = "/bundle/camManagerActivity")
public class CamManagerActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CamManagerListAdapter adapter;
    private CoordinatorLayout coordinatorLayout;
    private LinearLayout emptyLayout;
    private TextView configNewTv;

    private List<CamInfoDataBean> list = new ArrayList<>();

    private boolean isEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_manager);
        initView();
        mockRequestCamData();
    }

    /**
     * mock数据
     */
    private void mockRequestCamData() {
        list.clear();
        for (int i = 0; i < 15; i++) {
            CamInfoDataBean bean = new CamInfoDataBean();
            bean.setName(i + "");
            list.add(bean);
        }
        adapter.setData(list);
        isEmpty = false;
        if (list.size() > 0) {
            isEmpty = false;
            coordinatorLayout.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);
        } else {
            isEmpty = true;
            coordinatorLayout.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        }


    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Air相机管理");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.config_new_cam:
                        ARouter.getInstance()
                                .build("/bundle/config_new_activity")
                                .navigation();
                        break;
                    case R.id.check_app_new:
                        Toast.makeText(CamManagerActivity.this, "check_new", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        emptyLayout = findViewById(R.id.empty_layout);
        configNewTv = findViewById(R.id.config_new_tv);
        configNewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/bundle/config_new_activity")
                        .navigation();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(CamManagerActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CamManagerListAdapter(CamManagerActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cam_manager, menu);
        return true;
    }
}
