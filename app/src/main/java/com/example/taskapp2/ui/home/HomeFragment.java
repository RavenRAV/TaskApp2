package com.example.taskapp2.ui.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp2.App;
import com.example.taskapp2.FromActivity;
import com.example.taskapp2.Interfaces.OnItemClickListener;
import com.example.taskapp2.MainActivity;
import com.example.taskapp2.R;
import com.example.taskapp2.Task;
import com.example.taskapp2.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerview);
        initList();
        return root;
    }

    private void initList() {
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), FromActivity.class);
                intent.putExtra("task", list.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(getContext(), "context"+getContext(),Toast.LENGTH_SHORT ).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Важно!")
                        .setMessage("Вы точно хотите удалить?")
                        .setCancelable(false)
                        .setNegativeButton("нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"отмена", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"удалено", Toast.LENGTH_SHORT).show();
//                                ?


                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
            App.getDatabase().taskDao().getAll().observe(this, new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    list.clear();
                    list.addAll(tasks);
                    adapter.notifyDataSetChanged();
                }
            });
//

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == Activity.RESULT_OK && requestCode == 100){
//            Task task = (Task)data.getSerializableExtra("task");
//            Log.e("TAG", "title: " + task.getTitle());
//            list.add(0, task);
//            adapter.notifyDataSetChanged();
//        }
//    }
}