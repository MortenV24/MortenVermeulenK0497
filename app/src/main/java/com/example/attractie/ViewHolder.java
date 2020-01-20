package com.example.attractie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mClickListener.onItemClick(v, getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx, String name, String img, String place) {

        TextView mName = mview.findViewById(R.id.post_name);
        TextView mPlace = mview.findViewById(R.id.post_place);
        ImageView mImage = mview.findViewById(R.id.post_image);

        mName.setText(name);
        mPlace.setText(place);

        Picasso.get().load(img).into(mImage);

    }
    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}
