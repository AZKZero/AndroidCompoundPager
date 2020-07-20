package com.azkzer0.compoundpager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.azkzer0.compoundpager.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CompoundPagerFragment extends Fragment {

    private Context context;
    private String adURL = "";
    private String imageURL = "";
    public static final String TAG = "CompoundPagerFragment";
    private ImageView pageImage;

    public CompoundPagerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            for (String string :
                    getArguments().keySet()) {
                Log.i("CompoundPagerFragment", "onCreateView: " + string);
            }
            adURL = getArguments().getString("adURL");
            imageURL = getArguments().getString("imageURL");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.image_pager, container, false);
        pageImage = view.findViewById(R.id.pageImage);

        if (getArguments() != null) {
            for (String string :
                    getArguments().keySet()) {
                Log.i(TAG, "onCreateView: " + string);
            }
            imageURL = getArguments().getString("imageURL");
        }

        if (imageURL != null && !imageURL.isEmpty()) {
            Picasso.get().load(imageURL).into(pageImage, new Callback() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "onSuccess: ");
                }

                @Override
                public void onError(Exception e) {
                    Log.w(TAG, "onError: " + imageURL);
                    e.printStackTrace();
                }
            });
        }


        return view;
    }

}
