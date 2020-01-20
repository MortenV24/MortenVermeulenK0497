package com.example.attractie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Attr;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mAttrList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Attracties");

        mDatabase.keepSynced(true); // Keeps database synced

        mAttrList = (RecyclerView) findViewById(R.id.myrecycleview);
        mAttrList.setHasFixedSize(true);
        mAttrList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Attractions, AttractionsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attractions, AttractionsViewHolder>
                (Attractions.class, R.layout.attr_row, AttractionsViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(AttractionsViewHolder viewHolder, Attractions model, int i) {
                viewHolder.setNaam(model.getNaam());
                viewHolder.setPlaats(model.getPlaats());
                viewHolder.setImage(getApplicationContext(),model.getImg());
            }
        };

        mAttrList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class AttractionsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public AttractionsViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }

        public void setNaam(String name)
        {
            TextView post_name=(TextView)mView.findViewById(R.id.post_name);
            post_name.setText(name);
        }

        public void setPlaats(String place)
        {
            TextView post_place=(TextView)mView.findViewById(R.id.post_place);
            post_place.setText(place);
        }

        public void setImage(Context ctx, String img)
        {
            ImageView post_img =(ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(img).into(post_img);
        }
    }
}
