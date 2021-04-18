package com.example.rsv_hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity implements OnBoardingAdapter.OnboardingListener {
    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private  ViewPager2 onBoardingViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);

        setupOnboardingItems();

         onBoardingViewPager = findViewById(R.id.onboardingViewPager);
        onBoardingViewPager.setAdapter(onBoardingAdapter);

        setupOnBoardingIndicators();
        setCurrentOnboardingIndicator(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
    }

    private void setupOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemProjects = new OnboardingItem();
        itemProjects.setTitle("ПРОЕКТЫ");
        itemProjects.setDescription("Брось вызов тысячам управленцев \n" +
                "и попади в сообщество лидеров");
        itemProjects.setImage(R.drawable.icon1);
        itemProjects.setButtonText("ИНТЕРЕСНО");

        OnboardingItem itemEducation = new OnboardingItem();
        itemEducation.setTitle("ОБУЧЕНИЕ");
        itemEducation.setDescription("Изучай то, что тебя \n" +
                "действительно интересует");
        itemEducation.setImage(R.drawable.icon2);
        itemEducation.setButtonText("ЗАМАНЧИВО");

        OnboardingItem itemDevelopment = new OnboardingItem();
        itemDevelopment.setTitle("ТРЕК РАЗВИТИЯ");
        itemDevelopment.setDescription("Создай пошаговый план развития \n" +
                "знаний для успешной карьеры");
        itemDevelopment.setImage(R.drawable.icon3);
        itemDevelopment.setButtonText("С УДОВОЛЬСТВИЕМ");

        OnboardingItem itemSuccess = new OnboardingItem();
        itemSuccess.setTitle("ИСТОРИИ УСПЕХА");
        itemSuccess.setDescription("Познакомься с людьми,\n" +
                "которыми мы гордимся!");
        itemSuccess.setImage(R.drawable.icon4);
        itemSuccess.setButtonText("ЛЮБОПЫТНО");

        OnboardingItem itemHelp = new OnboardingItem();
        itemHelp.setTitle("ПОМОЩЬ ДРУГИМ");
        itemHelp.setDescription("Для тех, кто делает добрые дела \n" +
                "и всегда готов помочь");
        itemHelp.setImage(R.drawable.icon5);
        itemHelp.setButtonText("ПОЕХАЛИ!");


        onboardingItems.add(itemProjects);
        onboardingItems.add(itemEducation);
        onboardingItems.add(itemDevelopment);
        onboardingItems.add(itemSuccess);
        onboardingItems.add(itemHelp);

        onBoardingAdapter = new OnBoardingAdapter(onboardingItems, this);
    }

    private void setupOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.onboarding_indicator_active
                        ));
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.onboarding_indicator_inactive
                        ));
            }
        }
    }

    @Override
    public void onboardingClick() {
        if (onBoardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()){
            onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
        } else {
            startActivity(new Intent(IntroActivity.this, RegistrationActivity.class));
            finish();
        }
    }
}