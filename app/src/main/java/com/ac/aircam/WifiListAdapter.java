package com.ac.aircam;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhirui on 2018/9/16.
 */
public class WifiListAdapter extends RecyclerView.Adapter<WifiListAdapter.WifiViewHolder> {

    private List<ScanResult> list = new ArrayList<>();

    private Activity context;

    public WifiListAdapter(Activity context) {
        this.context = context;
    }

    public void setData(List<ScanResult> data) {
        if (data != null && data.size() > 0) {
            list = data;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public WifiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_wifi, viewGroup, false);
        WifiViewHolder holder = new WifiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WifiViewHolder holder, int i) {
        final ScanResult item = list.get(i);
        holder.nameTv.setText(item.SSID);
        if (item.capabilities.contains("WPA") || item.capabilities.contains("WEP")) {
            holder.wifiStatusImg.setImageDrawable(context.getResources().getDrawable(R.drawable.wifi_lock));
        } else {
            holder.wifiStatusImg.setImageDrawable(context.getResources().getDrawable(R.drawable.wifi_open));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WifiKeyActivity.class);
                intent.putExtra("wifi", item);
                context.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WifiViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;
        private TextView nameTv;
        private ImageView wifiStatusImg;
        private ImageView refreshImg;

        public WifiViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            nameTv = itemView.findViewById(R.id.wifi_name_tv);
            wifiStatusImg = itemView.findViewById(R.id.wifi_icon);
            refreshImg = itemView.findViewById(R.id.right_icon);
        }
    }
}
