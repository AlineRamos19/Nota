package com.example.t100.nota;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class AdicionarNota extends AppCompatActivity {

    BoxStore boxStore = App.getBoxStore();
    Box<Nota> notaBox;
    Nota novaNota;
    List<Nota> listaNota = new ArrayList<>();
    EditText titulo;
    EditText notas;
    Button btnSalvar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nota);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        linearLayout = findViewById(R.id.linear);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
    }

    private void salvarDados() {

        titulo = findViewById(R.id.titulo);
        String strTitulo = titulo.getText().toString();

        notas = findViewById(R.id.note);
        String strNota = notas.getText().toString();

        if (!strTitulo.trim().isEmpty() && !strNota.trim().isEmpty()) {
            novaNota = new Nota(strTitulo, strNota);


           new AlertDialog.Builder(AdicionarNota.this).setTitle("Atenção")
                   .setMessage("Confirmar nova nota?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   notaBox = boxStore.boxFor(Nota.class);
                   notaBox.put(novaNota);
                   finish();
               }
           }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

               }
           }).show();

        } else {
            Snackbar.make(linearLayout, "Favor preencher todos os campos!",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }
}
