package com.sukhov.android.ainsoft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterRecyclerView mAdapterRecyclerView;
    private SQLiteConnector mSQLiteConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView();
    }

    public void loadRecyclerView(){
        mSQLiteConnector = new SQLiteConnector(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_products);
        mAdapterRecyclerView = new AdapterRecyclerView(mSQLiteConnector.getProducts());
        mRecyclerView.setAdapter(mAdapterRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
