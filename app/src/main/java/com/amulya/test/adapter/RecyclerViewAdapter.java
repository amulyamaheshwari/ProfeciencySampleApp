package com.amulya.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulya.test.R;
import com.amulya.test.pojo.DataModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {

    Context mCtx;
    List<DataModel> dataModelList;

    public RecyclerViewAdapter(Context mCtx, List<DataModel> dataModelList) {
        this.mCtx = mCtx;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);

        if (dataModel.getImageHref() != null) {
            Glide.with(mCtx)  //2
                    .load(dataModel.getImageHref()) //3
                    .centerCrop() //4
                    .apply(new RequestOptions().override(250, 250))
                    .placeholder(R.mipmap.place_holder) //5
                    .error(R.mipmap.place_holder) //6
                    .fallback(R.mipmap.place_holder) //7
                    .into(holder.imageView); //8

        }

        holder.textTitle.setText(dataModel.getTitle());
        holder.textDes.setText(dataModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textTitle, textDes;

        public ListViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textTitle = itemView.findViewById(R.id.txt_title);
            textDes = itemView.findViewById(R.id.txt_des);
        }
    }
}
