package com.azkzer0.androidimagepager;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;

public class testF extends Fragment {

    private Context context;
    private String text = "";
    private String imageURL = "";
    private View view;

    public testF() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            for (String string :
                    getArguments().keySet()) {
                Log.i("testF", "onCreateView: " + string);
            }
            text = getArguments().getString("adURL");
            imageURL = getArguments().getString("imageURL");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        Log.i("sampleF", "onCreateView: count");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sample_page, container, false);

        if (getArguments() != null) {
            for (String string :
                    getArguments().keySet()) {
                Log.i("testF", "onCreateView: " + string);
            }
            text = getArguments().getString("text");
            imageURL = getArguments().getString("imageURL");
        }

        Log.i("sampleF", "onCreateView: " + imageURL);

        ImageButton sampleImage = view.findViewById(R.id.sampleImage);
        if (!imageURL.isEmpty()) Picasso.get().load(imageURL).into(sampleImage);

        sampleImage.setOnClickListener(v -> {
            Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG);
        });

        TextView textView = view.findViewById(R.id.textTitle);
        textView.setText(text == null ? "" : text);

        return view;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        ImageButton adImage = view.findViewById(R.id.adImage);
        if (!imageURL.isEmpty()) Picasso.get().load(imageURL).fit().placeholder(R.drawable.ad_placeholder).into(adImage);
        else Picasso.get().load(R.drawable.ad_placeholder).fit().into(adImage);
    }*/
}
