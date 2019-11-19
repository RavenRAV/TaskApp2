package com.example.taskapp2.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.taskapp2.R;

public class OnBoardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        ViewPager viewPager = findViewById(R.id.viewPager);
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//            ?
        }



        @Override
        public Fragment getItem(int position) {
            BoardFragment fragment = new BoardFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
