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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    TextView btnSalvar;
    LinearLayout linearLayout;
    Nota n;
    private static final String LOG = AdicionarNota.class.getSimpleName();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nota);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        try {
            n = (Nota) getIntent().getExtras().getSerializable("nota");
            EditText titulo = findViewById(R.id.titulo);
            EditText nota = findViewById(R.id.note);
            titulo.setText(n.getTitulo());
            nota.setText(n.getNota());
            getSupportActionBar().setTitle(R.string.title_actionbar_editar);
        } catch (NullPointerException e) {
            Log.e(LOG, "Error: " + e.getMessage());
        }


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
        final String strTitulo = titulo.getText().toString();
        notas = findViewById(R.id.note);
        final String strNota = notas.getText().toString();

        if (!strTitulo.trim().isEmpty() && !strNota.trim().isEmpty()) {

            new AlertDialog.Builder(AdicionarNota.this).setTitle(getString(R.string.dialog_atencao))
                    .setMessage(R.string.dialog_confirmacao_novanota).setPositiveButton(R.string.dialog_sim, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    notaBox = boxStore.boxFor(Nota.class);

                    if (n != null) {
                        n.setTitulo(strTitulo);
                        n.setNota(strNota);
                        notaBox.put(n);
                    } else {
                        novaNota = new Nota(strTitulo, strNota);
                        notaBox.put(novaNota);
                    }
                    finish();
                }
            }).setNegativeButton(getString(R.string.dialog_cancelar), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        } else {
            Snackbar.make(linearLayout, R.string.aviso_campo_vazio,
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                new AlertDialog.Builder(AdicionarNota.this).setTitle(R.string.dialog_atencao)
                        .setMessage(R.string.dialog_alteracao_naosalva).setPositiveButton(R.string.dialog_sair_sem_salvar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AdicionarNota.this, MainActivity.class));
                        finishAffinity();
                    }
                }).setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            default:
                break;
        }
        return true;
    }
}
