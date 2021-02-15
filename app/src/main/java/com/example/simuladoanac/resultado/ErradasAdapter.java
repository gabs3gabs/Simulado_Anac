package com.example.simuladoanac.resultado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simuladoanac.R;
import com.example.simuladoanac.questoes.QuestoesModel;

import java.util.List;

public class ErradasAdapter extends RecyclerView.Adapter<ErradasAdapter.Viewholder> {

    private List<QuestoesModel> list;

    public ErradasAdapter(List<QuestoesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.erradas_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(list.get(position).getQuestao(), list.get(position).getRespostaCorreta());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView questoes, respostas;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            questoes = itemView.findViewById(R.id.questao);
            respostas = itemView.findViewById(R.id.resposta);
        }

        private void setData(String questoes, String respostas){
            this.questoes.setText(questoes);
            this.respostas.setText(respostas);
        }

    }

}
