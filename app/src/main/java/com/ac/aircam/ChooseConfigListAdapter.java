package com.ac.aircam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac.aircam.model.CamInfoDataBean;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

/**
 * Created by chengzhirui on 2018/9/8.
 */
public class ChooseConfigListAdapter extends RecyclerView.Adapter<CamManagerListAdapter.CamManagerViewHolder> {

    private Activity context;
    private List<CamInfoDataBean> list;

    public ChooseConfigListAdapter(Activity context) {
        this.context = context;
    }

    public void setData(@NonNull List<CamInfoDataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(CamInfoDataBean dataBean) {
        this.list.add(dataBean);
        notifyItemInserted(list.size() - 1);
    }

    public void removeData(CamInfoDataBean dataBean) {
        this.list.remove(dataBean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CamManagerListAdapter.CamManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_cam_manager, viewGroup, false);
        CamManagerListAdapter.CamManagerViewHolder holder = new CamManagerListAdapter.CamManagerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CamManagerListAdapter.CamManagerViewHolder holder, int position) {
        holder.nameTv.setText(list.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConnectCamActivity.class);
                context.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CamManagerViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout layout;
        public TextView nameTv;


        public CamManagerViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            nameTv = itemView.findViewById(R.id.cam_name_tv);
        }
    }
}
