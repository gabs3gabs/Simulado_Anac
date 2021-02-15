package com.example.simuladoanac.info;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.simuladoanac.R;
import com.example.simuladoanac.questoes.QuestoesActivity;

public class GridAdapter extends BaseAdapter {

    private int sets = 0;
    private String categoria;
    public GridAdapter(int sets, String categoria){
        this.sets = sets;
        this.categoria = categoria;
    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        }else{
            view = convertView;
        }

        view.setOnClickListener(v -> {
                Intent questaoIntent = new Intent(parent.getContext(), QuestoesActivity.class);
                questaoIntent.putExtra("categoria", categoria);
                questaoIntent.putExtra("setNumero", position+1);
                parent.getContext().startActivity(questaoIntent);

        });

        ((TextView) view.findViewById(R.id.nuQues)).setText(String.valueOf(position + 1));

        return view;
    }
}
