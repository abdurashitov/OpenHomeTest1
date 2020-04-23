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

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Home> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Home> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView adress;
        private TextView name;
        private TextView oplata;
        private TextView price;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            adress = itemView.findViewById(R.id.adress);
            oplata = itemView.findViewById(R.id.oplata);
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
                Home home = (Home) listRecyclerItem.get(i);
                itemViewHolder.name.setText(home.getName());
                itemViewHolder.adress.setText(home.getAddress());
                itemViewHolder.oplata.setText("demo");
                itemViewHolder.price.setText(home.getValue());

        }

        viewHolder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = v.findViewById(R.id.name);
                Intent intent = new Intent(context, ScrinHomeActivity.class);
                intent.putExtra("name", textView1.getText());
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

