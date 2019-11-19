package com.example.taskapp2.onboard;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taskapp2.MainActivity;
import com.example.taskapp2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {


    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        int pos = getArguments().getInt("pos");
        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.imageView);
        Button btnStart = view.findViewById(R.id.btnStart);
        switch (pos){
            case 0:
                textView.setText("привет");
                imageView.setImageResource(R.drawable.onboard_page1);
                btnStart.setVisibility(View.GONE);

                break;
            case 1:
                textView.setText("как дела?");
                imageView.setImageResource(R.drawable.onboard_page2);
                btnStart.setVisibility(View.GONE);
                break;
            case 2:
                textView.setText("что делаешь?");
                imageView.setImageResource(R.drawable.onboard_page3);
                break;

        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
                SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );
                preferences.edit().putBoolean("isShow", true).apply();
            }
        });

        return view;
    }

}
