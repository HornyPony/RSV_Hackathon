package com.example.rsv_hackathon.Profile;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rsv_hackathon.R;


public class ProjectsFragment extends Fragment {
    private TextView competitionsEndedTextView;
    private TextView competitionsNowTextView;
    private View competitionsNowUnderline;
    private View competitionsEndedUnderline;
    private ImageView competitionsImageView;


    public ProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        competitionsEndedTextView = view.findViewById(R.id.competitionsEndedTV);
        competitionsNowTextView = view.findViewById(R.id.competitionsNowTV);
        competitionsNowUnderline = view.findViewById(R.id.competitionsNowUnderline);
        competitionsEndedUnderline = view.findViewById(R.id.competitionsEndedUnderline);
        competitionsImageView = view.findViewById(R.id.competitionsImageView);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.competitionsEndedTV:
                        competitionsNowUnderline.setBackgroundColor(getResources().
                                getColor(R.color.gray));
                        competitionsNowTextView.setTextColor(getResources().
                                getColor(R.color.gray));
                        competitionsEndedTextView.setTextColor(getResources().
                                getColor(R.color.dark_blue));
                        competitionsEndedUnderline.setBackgroundColor(getResources().
                                getColor(R.color.dark_blue));
                        competitionsImageView.setImageDrawable(getResources().getDrawable(R.drawable.proriv_ended));
                        break;

                    case R.id.competitionsNowTV:
                        competitionsNowUnderline.setBackgroundColor(getResources().
                                getColor(R.color.dark_blue));
                        competitionsNowTextView.setTextColor(getResources().
                                getColor(R.color.dark_blue));
                        competitionsEndedTextView.setTextColor(getResources().
                                getColor(R.color.gray));
                        competitionsEndedUnderline.setBackgroundColor(getResources().
                                getColor(R.color.gray));
                        competitionsImageView.setImageDrawable(getResources().getDrawable(R.drawable.proriv_2021));
                        break;
                }
            }
        };

        competitionsEndedTextView.setOnClickListener(onClickListener);
        competitionsNowTextView.setOnClickListener(onClickListener);
        return  view;
    }
}