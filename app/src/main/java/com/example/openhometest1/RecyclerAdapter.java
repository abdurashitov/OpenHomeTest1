package com.example.openhometest1;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Apartments> listRecyclerItem;
    private String name1;
    private String data1;
    private String price1;
    private String token;


    public RecyclerAdapter(Context context, List<Apartments> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView data;
        private TextView price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
            price = itemView.findViewById(R.id.price);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item, viewGroup, false);
                return new ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Apartments home = (Apartments) listRecyclerItem.get(i);
                itemViewHolder.name.setText(home.title);
                itemViewHolder.data.setText(home.date);
                itemViewHolder.price.setText(home.getPrice());
        }
        viewHolder.itemView.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView textView1 = v.findViewById(R.id.name);
                Intent intent = new Intent(context, ScrinHomeActivity.class);
                intent.putExtra(Apartments.class.getSimpleName(), listRecyclerItem.get(i));
                context.startActivity(intent);
                Log.d("msg", ""+i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

}

