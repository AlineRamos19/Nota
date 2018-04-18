package com.example.t100.nota;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {

    BoxStore boxStore = App.getBoxStore();
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.add_nota);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdicionarNota.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Box<Nota> notaBox = boxStore.boxFor(Nota.class);
        List<Nota> listaNota = notaBox.getAll();

        if(listaNota != null && listaNota.size() > 0){
            NotaAdapater adapater = new NotaAdapater(this, listaNota );
            recyclerView.setAdapter(adapater);
            adapater.notifyDataSetChanged();
        } else{
            TextView visuVazia = findViewById(R.id.visu_vazia);
            visuVazia.setText(R.string.visualizacao_vazia);
        }




    }
}
