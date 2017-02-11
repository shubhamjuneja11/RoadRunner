package com.example.junejaspc.roadrunner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.junejaspc.roadrunner.MainActivity;
import com.example.junejaspc.roadrunner.R;
import com.example.junejaspc.roadrunner.controller.Recycler_View_Adapter;
import com.example.junejaspc.roadrunner.model.Data;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

public class PublicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<Data> data;
    Firebase firebase;
    Recycler_View_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        Constants.fun();
        firebase=new Firebase(Constants.publicf);
      data=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Recycler_View_Adapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);
        final long[] x = {0};
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Data d=dataSnapshot.getValue(Data.class);
                if(!Constants.convert2(d.getName()).equals(MainActivity.email)){
                    data.add(d);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

   /* private List<Data> fillWithData(){
        List<Data> data = new ArrayList<>();

        for(int i = 1; i <= 12; i++){
            data.add(new Data("one", "two", "three", "four"));
        }

        return data;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
public void openup(View view){
    Intent intent=new Intent(PublicActivity.this,PrivateMapsActivity.class);
    intent.putExtra("decide","public");
    startActivity(intent);
}
}
