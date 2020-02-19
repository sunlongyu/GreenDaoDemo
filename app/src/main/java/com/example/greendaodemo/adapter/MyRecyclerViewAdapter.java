package com.example.greendaodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greendaodemo.DetailActivity;
import com.example.greendaodemo.Model.GoodsModel;
import com.example.greendaodemo.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    public static final String GOOD_MODEL_Key = "good_model";
    private Context context;
    private List<GoodsModel> dataSource;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public List<GoodsModel> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<GoodsModel> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_item_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GoodsModel model = dataSource.get(position);
        holder.imageView.setImageResource(context.getResources().getIdentifier(model.getIcon(), "mipmap", context.getPackageName()));
        holder.textViewTitle.setText(model.getName());
        holder.textViewInfo.setText(model.getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(GOOD_MODEL_Key, model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataSource == null) return 0;
        return dataSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textViewTitle;
        private final TextView textViewInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);

        }
    }
}
