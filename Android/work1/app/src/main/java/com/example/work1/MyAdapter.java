package com.example.work1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Essay> essayList;

    public MyAdapter(List<Essay> essayList) {
        this.essayList = essayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Essay essay = essayList.get(position);
        holder.title.setText(essay.getTitle());
        holder.content.setText(essay.getContent());
        holder.time.setText(essay.getTime());
    }

    @Override
    public int getItemCount() {
        return essayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }
    }
    public void save() {
        if (!essayList.isEmpty()) {
            Essay firstEssay = essayList.get(0);
            Essay newEssay = new Essay();
            essayList.add(newEssay);
            notifyDataSetChanged();
        }
    }

}
