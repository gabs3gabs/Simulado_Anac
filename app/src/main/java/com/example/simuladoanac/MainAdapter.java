package com.example.simuladoanac;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simuladoanac.info.InfoNavActivity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Viewholder> {

    private List<MainModel> mainModelList;

    public MainAdapter(List<MainModel> mainModelList) {
        this.mainModelList = mainModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_itens, parent, false);
        return  new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(mainModelList.get(position).getUrl(), mainModelList.get(position).getNome(), mainModelList.get(position).getProvas());
    }

    @Override
    public int getItemCount() {
        return mainModelList.size();
    }

    class  Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView titulo;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagemView);
            titulo = itemView.findViewById(R.id.txtTitulo);

        }

        private void setData(String url, String titulo, int sets){

            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.titulo.setText(titulo);

            itemView.setOnClickListener(v -> {
                Intent setIntent = new Intent(itemView.getContext(),InfoNavActivity.class);
                setIntent.putExtra("titulo", titulo);
                setIntent.putExtra("sets", sets);
                itemView.getContext().startActivity(setIntent);
            });

        }

    }

}
