package com.azkzer0.compoundpager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class TabAdapterVertical /*extends RecyclerView.Adapter<TabAdapterVertical.MyViewHolder>*/ {

    /*private ArrayList<View> views;
    private GridClick gridClick;

    public GridModel getByID(@IdRes int id) {
        for (ArrayList<GridModel> subList :
                views) {
            for (GridModel sub :
                    subList) {
                if (sub.getId() == id) return sub;
            }
        }
        return null;
    }

    public GridAdapter(ArrayList<ArrayList<GridModel>> list, GridClick gridClick) {
        this.views = list;
        this.gridClick = gridClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_template_grid, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (views.get(myViewHolder.getAdapterPosition()).size() >= 1) {
            myViewHolder.setSub1(views.get(myViewHolder.getAdapterPosition()).get(0));
        } else {
            myViewHolder.sub1Card.setVisibility(View.INVISIBLE);
        }

        if (views.get(myViewHolder.getAdapterPosition()).size() >= 2) {
            myViewHolder.setSub2(views.get(myViewHolder.getAdapterPosition()).get(1));
        } else {
            myViewHolder.sub2Card.setVisibility(View.INVISIBLE);
        }

        if (views.get(myViewHolder.getAdapterPosition()).size() >= 3) {
            myViewHolder.setSub3(views.get(myViewHolder.getAdapterPosition()).get(2));
        } else {
            myViewHolder.sub3Card.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return views.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton sub1, sub2, sub3;
        MaterialCardView sub1Card, sub2Card, sub3Card;
        TextView sub1Text, sub2Text, sub3Text;
        ImageView sub1Im, sub2Im, sub3Im;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            *//*sub1 = itemView.findViewById(R.id.sub1);
            sub2 = itemView.findViewById(R.id.sub2);
            sub3 = itemView.findViewById(R.id.sub3);*//*
            sub1Card = itemView.findViewById(R.id.sub1_card);
            sub2Card = itemView.findViewById(R.id.sub2_card);
            sub3Card = itemView.findViewById(R.id.sub3_card);

            sub1Text = itemView.findViewById(R.id.sub1_text);
            sub2Text = itemView.findViewById(R.id.sub2_text);
            sub3Text = itemView.findViewById(R.id.sub3_text);

            sub1Im = itemView.findViewById(R.id.sub1_im);
            sub2Im = itemView.findViewById(R.id.sub2_im);
            sub3Im = itemView.findViewById(R.id.sub3_im);
        }

        public void setSub1(final GridModel subject) {
            sub1Text.setText(subject.getName());
            sub1Card.setOnClickListener(v -> gridClick.gridSelect(subject));
        }

        public void setSub2(final GridModel subject) {
            sub2Text.setText(subject.getName());
            sub2Card.setOnClickListener(v -> gridClick.gridSelect(subject));
        }

        public void setSub3(final GridModel subject) {
            sub3Text.setText(subject.getName());
            sub3Card.setOnClickListener(v -> gridClick.gridSelect(subject));
        }

        *//*public void setModelTest(final GridModel modelTest) {
            titleTextView.setText(modelTest.getSubject_name());

            btnTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (modelTest.isSubscribed()) {
                        subjectClickListener.redirectToExamActivity(getAdapterPosition());
                    } else {

                        payD = payB.setDescription(messageSMSActivate + modelTest.getModelTestTitle() + "\n" + ((modelTest.getPrice() == 0) ? "The subscription is free" : ("The price for this subscription is " + modelTest.getPrice() + "Taka")) + "\nWould you like to pay now?").setPositiveText("Subscribe Now").setNegativeText("No")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    }
                                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        subjectClickListener.initializePayment(getAdapterPosition());
                                        payD.dismiss();
                                    }
                                }).build();
                        payD.show();
//                        subscribeButtonClickListener.initializePayment(getAdapterPosition());
                    }
                }
            });
        }*//*
    }*/
}

