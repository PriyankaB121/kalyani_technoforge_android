package com.operator.app.kalyanitechnoforge.UiViews.adapter.dashboard;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.operator.app.kalyanitechnoforge.CreateActivity;
import com.operator.app.kalyanitechnoforge.DetailsActivity;
import com.operator.app.kalyanitechnoforge.HomeActivity;
import com.operator.app.kalyanitechnoforge.Model.KaizenList;
import com.operator.app.kalyanitechnoforge.R;
import com.operator.app.kalyanitechnoforge.UpdateActivity;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    List<KaizenList> itemList1;
    private Context context;


    public MyListAdapter(List<KaizenList> initData, Context context) {
        this.itemList1=initData;
        this.context=context;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_dashboard, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        holder.tvApplicationIdTitle.setText(itemList1.get(position).getDoc_no());
        holder.tvApplicationNo.setText(itemList1.get(position).getRev_date());
        holder.tvStatusTitle.setText(itemList1.get(position).getTpm_circle_no());
        holder.tvStatusType.setText(itemList1.get(position).getTpm_circle_name());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Log.d("TAG", "onBindViewHolder: "+itemList1.get(position).getOperator_kaizen_id());
                  Intent intent=new Intent(context, DetailsActivity.class);
                  Bundle bundle=new Bundle();
                  bundle.putString("kaizen_id", String.valueOf(itemList1.get(position).getOperator_kaizen_id()));
                  intent.putExtras(bundle);
                  context.startActivity(intent);
              }
          });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvApplicationIdTitle, tvApplicationNo, tvStatusTitle,tvStatusType ;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvApplicationIdTitle = (TextView) itemView.findViewById(R.id.tvApplicationIdTitle);
            this.tvApplicationNo = (TextView) itemView.findViewById(R.id.tvApplicationNo);
            this.tvStatusTitle = (TextView) itemView.findViewById(R.id.tvStatusTitle);

            tvStatusType = (TextView) itemView.findViewById(R.id.tvStatusType);
        }
    }
}
