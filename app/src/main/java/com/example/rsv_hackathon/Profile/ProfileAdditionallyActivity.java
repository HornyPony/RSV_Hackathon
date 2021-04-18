package com.example.rsv_hackathon.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.rsv_hackathon.R;

import java.util.ArrayList;

public class ProfileAdditionallyActivity extends AppCompatActivity implements ProfilePagesAdapter.OnPageListener {
    private RecyclerView recyclerView;
    private ProfilePagesAdapter adapter;
    private LinearLayout linearLayout;
    private DevelopmentTrackFragment developmentTrackFragment;
    private ProjectsFragment projectsFragment;
    private FragmentTransaction fTrans;
    private ArrayList<String> pagesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_additionally);

        recyclerView = findViewById(R.id.pagesRV);
        linearLayout = findViewById(R.id.pagesContainer);
        developmentTrackFragment = new DevelopmentTrackFragment();
        projectsFragment = new ProjectsFragment();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.pagesContainer, developmentTrackFragment);
        fTrans.commit();

        pagesName = new ArrayList<>();
        pagesName.add("Трек развития");
        pagesName.add("Проекты");
        pagesName.add("Компетенции");
        pagesName.add("Обучение");
        pagesName.add("Тесты");
        pagesName.add("Чаты");
        pagesName.add("Заявки на проекты");
        pagesName.add("Очные мероприятия");

                LinearLayoutManager layoutManager = new LinearLayoutManager(
                ProfileAdditionallyActivity.this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ProfilePagesAdapter(ProfileAdditionallyActivity.this, pagesName, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onPageClick(int position) {

        if (pagesName.get(position).equals("Трек развития")){
            fTrans = getFragmentManager().beginTransaction();
            fTrans.replace(R.id.pagesContainer, developmentTrackFragment);

        } else if (pagesName.get(position).equals("Проекты")){
            fTrans = getFragmentManager().beginTransaction();
            projectsFragment = new ProjectsFragment();
            fTrans.replace(R.id.pagesContainer, projectsFragment);
        }
        fTrans.commit();

    }
}