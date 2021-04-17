package com.example.rsv_hackathon;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder> {

public interface  OnboardingListener{
    void onboardingClick();
}

    private List<OnboardingItem> onboardingItems;
    private OnboardingListener onboardingListener;


    public OnBoardingAdapter(List<OnboardingItem> onboardingItems, OnboardingListener onboardingListener) {
        this.onboardingItems = onboardingItems;
        this.onboardingListener = onboardingListener;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,
                        parent, false), onboardingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
            holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView onboardingTitleTV;
        private TextView onboardingDescriptionTV;
        private ImageView onboardingImageView;
        private Button onboardingButton;
        OnboardingListener onboardingListener;

        OnboardingViewHolder(@NonNull View itemView, OnboardingListener onboardingListener) {
            super(itemView);
            onboardingTitleTV = itemView.findViewById(R.id.onboardingTitleTV);
            onboardingDescriptionTV = itemView.findViewById(R.id.onboardingDescriptionTV);
            onboardingImageView = itemView.findViewById(R.id.onboardingImageView);
            onboardingButton = itemView.findViewById(R.id.onboardingButton);
            this.onboardingListener = onboardingListener;

            onboardingButton.setOnClickListener(this);

        }

        void setOnboardingData(OnboardingItem onboardingItem) {
            onboardingTitleTV.setText(onboardingItem.getTitle());
            onboardingDescriptionTV.setText(onboardingItem.getDescription());
            onboardingImageView.setImageResource(onboardingItem.getImage());
            onboardingButton.setText(onboardingItem.getButtonText());
        }

        @Override
        public void onClick(View view) {
            onboardingListener.onboardingClick();
        }
    }

}
