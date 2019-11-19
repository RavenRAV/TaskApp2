package com.example.taskapp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp2.Interfaces.OnItemClickListener;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> list;
    private OnItemClickListener onItemClickListener;







    public TaskAdapter(List<Task> list) {
        this.list = list;
    }
    public void SortArrayList(){
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(getAdapterPosition());
                    return false;
                }
            });
        }

        public void bind(Task task) {
            textTitle.setText(task.getTitle() + " " + task.getDesk());
        }
    }
}
