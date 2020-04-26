package com.example.openhometest1;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterCheck extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<CheckItem> listRecyclerItem;
    private String name1;
    private String data1;
    private String price1;
    private String token;
    Dialog checkItem3;
    private TextView data2;
    private TextView nChecka2;
    private TextView price2;
    private TextView textsmma1;
    private TextView textsmma2;
    private ImageView closeImage;
    private String data4;

    public RecyclerAdapterCheck(Context context, List<CheckItem> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView usluga;
        private TextView data;
        private TextView nChecka;
        private TextView price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            checkItem3 =   new Dialog(context);
            nChecka = itemView.findViewById(R.id.nChecka);
            usluga = itemView.findViewById(R.id.usluga);
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
                        R.layout.check_ltem, viewGroup, false);
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
                CheckItem home = (CheckItem) listRecyclerItem.get(i);
                itemViewHolder.usluga.setText("Аренда жилья");
                itemViewHolder.data.setText(home.data +"    "+home.time+"  (+03:00)" );
                data4= home.data +"    "+home.time+"  (+03:00)";
                itemViewHolder.nChecka.setText(home.checkNumber);
                itemViewHolder.price.setText(home.price);
                price1= home.price;
        }
        viewHolder.itemView.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkItem3.setContentView(R.layout.chek_view_dialog);
                closeImage = checkItem3.findViewById(R.id.dialog_close_image_check);

                closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkItem3.dismiss();
                    }
                });
                checkItem3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                checkItem3.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}



