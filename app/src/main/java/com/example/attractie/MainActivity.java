package com.example.attractie;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Attr;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Attractions, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Attractions> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mLinearLayoutManager = new LinearLayoutManager(this);
            mLinearLayoutManager.setReverseLayout(true);
            mLinearLayoutManager.setStackFromEnd(true);

            mRecyclerView = findViewById(R.id.myrecycleview);

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference().child("Attracties");

            showData();
    }

    private void showData() {

        options = new FirebaseRecyclerOptions.Builder<Attractions>().setQuery(mDatabaseReference, Attractions.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attractions, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Attractions attractions) {

                holder.setDetails(getApplicationContext(),
                        attractions.getNaam(),
                        attractions.getImg(),
                        attractions.getPlaats());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attr_row,parent,false);

                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener(){
                    @Override
                    public void onItemClick(View view, int position){

                        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onItemLongClick(View view, int position){
                        Toast.makeText(MainActivity.this, "Long click", Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void firebaseSearch(String searchText){
        String query = searchText.toLowerCase();

        Query firebaseSearchQuery = mDatabaseReference.orderByChild("naam").startAt(query).endAt(query + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<Attractions>().setQuery(firebaseSearchQuery, Attractions.class).build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attractions, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Attractions attractions) {

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attr_row, parent, false);

                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Long click", Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    protected void onStart(){

        super.onStart();

        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }


}