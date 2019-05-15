package com.meshsami27.android_phpmysql.ui.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter>{

    private Context context;
    private ArrayList<Note> noter;
    private ItemClickListener itemClickListener;

     class RecyclerViewAdapter extends RecyclerView.ViewHolder implements ItemClickListener{
             TextView tv_title, tv_note, tv_date;
             CardView card_item;
             ItemClickListener itemClickListener;

            RecyclerViewAdapter(View view, ItemClickListener itemClickListener) {
            super(view);
            this.itemClickListener = itemClickListener;

            tv_title = (TextView) view.findViewById(R.id.title);
            tv_note = (TextView) view.findViewById(R.id.note);
            tv_date = (TextView) view.findViewById(R.id.date);
            card_item = view.findViewById(R.id.card_item);

            card_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemOnClick(view, getAdapterPosition());
                }
            });

        }

        @Override
        public void onItemOnClick(View view, int position) {
            onItemOnClick(view, getAdapterPosition());
        }
    }


    public MainAdapter(Context context, ArrayList<Note> noter) {
        this.context = context;
        this.noter = noter;
    }

    @NonNull

    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note note = noter.get(position);
        holder.tv_title.setText( noter.get(position).getTitle());


        System.out.println("768786786876786876"+noter.get(position).getTitle());

      //  holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        //holder.tv_date.setText(note.getDate());

       holder.card_item.setCardBackgroundColor(note.getColor());
    }

    @Override
    public int getItemCount() {
        return noter.size();
    }



    public interface ItemClickListener{
        void onItemOnClick(View view, int position);
    }
}
