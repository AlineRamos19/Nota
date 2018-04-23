package com.example.t100.nota;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {

    BoxStore boxStore = App.getBoxStore();
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    NotaAdapater adapter;
    List<Nota> listaNota = new ArrayList<>();

    @Subscribe
    public void onEvent(Event event){
        adapter = new NotaAdapater(this, event.getListaNota());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.add_nota);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdicionarNota.class));
            }
        });
        getNota();
    }

    public void getNota() {

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager =
                new GridLayoutManager(this,  2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListaNota();
    }

    public void getListaNota() {
        Box<Nota> notaBox = boxStore.boxFor(Nota.class);
        listaNota = notaBox.getAll();
        adapter = new NotaAdapater(this, listaNota);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
