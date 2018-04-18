package com.example.t100.nota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NotaAdapater extends RecyclerView.Adapter<NotaAdapater.NotaViewHlder> {

    private List<Nota> listaNota;

    private Context context;

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
    public void onBindViewHolder(NotaViewHlder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return listaNota !=null ? listaNota.size() : 0;
    }

    public class NotaViewHlder extends RecyclerView.ViewHolder{

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
}
