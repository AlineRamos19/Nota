package com.example.t100.nota;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;


public class NotaAdapater extends RecyclerView.Adapter<NotaAdapater.NotaViewHlder> {

    private List<Nota> listaNota;
    private Context context;
    BoxStore boxStore = App.getBoxStore();

    public NotaAdapater(Context context, List<Nota> notaList) {
        this.context = context;
        this.listaNota = notaList;
    }

    @Override
    public NotaViewHlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHlder(view);
    }

    @Override
    public void onBindViewHolder(final NotaViewHlder holder, int position) {

        final Nota nota = listaNota.get(position);
        holder.titulo.setText(nota.getTitulo());
        holder.nota.setText(nota.getNota());
        holder.data.setText(getData());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(holder.itemView.getContext()).setTitle(nota.getTitulo())
                        .setMessage(nota.getNota()).setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Box<Nota> notaBox = boxStore.boxFor(Nota.class);
                        notaBox.remove(nota);
                    }
                }).setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(holder.itemView.getContext(), AdicionarNota.class);
                        intent.putExtra("nota", nota);
                        holder.itemView.getContext().startActivity(intent);
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNota != null ? listaNota.size() : 0;
    }

    public class NotaViewHlder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView nota;
        private TextView data;

        public NotaViewHlder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_nota);
            nota = itemView.findViewById(R.id.nota);
            data = itemView.findViewById(R.id.data_nota);
        }
    }

    public String getData() {
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(data);
    }


}
