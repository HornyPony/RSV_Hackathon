package com.example.rsv_hackathon.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rsv_hackathon.R;

import java.util.ArrayList;

public class ProfilePagesAdapter extends RecyclerView.Adapter<ProfilePagesAdapter.ViewHolder> {
    private OnPageListener onPageListener;
    ArrayList<String > pagesName;
    Context context;
    private int lastItemIndex = 0;
    private ArrayList<TextView> textViewArrayList = new ArrayList<>() ;

    public interface OnPageListener{
        void onPageClick(int position);
    }

    public ProfilePagesAdapter(Context context, ArrayList<String> pagesName, OnPageListener onPageListener){
        this.context = context;
        this.pagesName = pagesName;
        this.onPageListener = onPageListener;
    }

    @NonNull
    @Override
    public ProfilePagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false);
        return new ViewHolder(view, onPageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePagesAdapter.ViewHolder holder, int position) {
            holder.pageNameTV.setText(pagesName.get(position));
            textViewArrayList.add(holder.pageNameTV);
            if(position==0){
                textViewArrayList.get(0).setTextColor(context.getColor(R.color.dark_blue));
            }
    }

    @Override
    public int getItemCount() {
        return pagesName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pageNameTV;
        OnPageListener onPageListener;


        public ViewHolder(@NonNull View itemView, OnPageListener onPageListener) {
            super(itemView);
            pageNameTV = itemView.findViewById(R.id.pageName);
            this.onPageListener = onPageListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPageListener.onPageClick(getAdapterPosition());
            pageNameTV.setTextColor(context.getColor(R.color.dark_blue));
            textViewArrayList.get(lastItemIndex).setTextColor(context.getColor(R.color.gray));
            lastItemIndex = textViewArrayList.indexOf(pageNameTV);
        }
    }
}
