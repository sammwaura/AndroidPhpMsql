package com.meshsami27.android_phpmysql.ui.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.model.Note;
import com.meshsami27.android_phpmysql.ui.ui.Update.UpdateActivity;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHoler>{

    private Context context;
    private ArrayList<Note> noter;
    private ItemClickListener itemClickListener;
    private int INTENT_EDIT = 200;


    class CustomViewHoler extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView tv_title, tv_note, tv_date;
         CardView card_item;
         ItemClickListener itemClickListener;



        CustomViewHoler(View view, ItemClickListener itemClickListener) {
            super(view);


            tv_title =view.findViewById(R.id.title);
            tv_note = view.findViewById(R.id.note);
            tv_date =  view.findViewById(R.id.date);
            card_item = view.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
//            card_item.setOnClickListener(this);


        }
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            itemClickListener.onItemOnClick(v, getAdapterPosition());
        }
    }

    public MainAdapter(Context context, ArrayList<Note> noter) {
        this.context = context;
        this.noter = noter;
    }



    @NonNull

    @Override
    public CustomViewHoler onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new CustomViewHoler(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHoler holder, final int position) {
        final Note note = noter.get(position);
        holder.tv_title.setText( noter.get(position).getTitle());
      //  holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        //holder.tv_date.setText(note.getDate());

       holder.card_item.setCardBackgroundColor(note.getColor());

       holder.tv_date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, UpdateActivity.class);
               intent.putExtra("note_id", note.getId());
               intent.putExtra("title", noter.get(position).getTitle());
               intent.putExtra("note", noter.get(position).getNote());
               intent.putExtra("color", noter.get(position).getColor());
                context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return noter.size();
    }



    public interface ItemClickListener{
        void onItemOnClick(View view, int position);

        View.OnClickListener onItemOnClick();
    }
}
