package com.example.t100.nota;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

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
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
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
}
