package com.ac.aircam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ac.aircam.model.CamInfoDataBean;

import java.util.List;

/**
 * Created by chengzhirui on 2018/9/8.
 */
public class CamManagerListAdapter extends RecyclerView.Adapter<CamManagerListAdapter.CamManagerViewHolder> {

    private Context context;
    private List<CamInfoDataBean> list;

    public CamManagerListAdapter(Context context) {
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
    public CamManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_cam_manager, viewGroup, false);
        CamManagerViewHolder holder = new CamManagerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CamManagerViewHolder holder, int position) {
        holder.nameTv.setText(list.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 进入详情页
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
