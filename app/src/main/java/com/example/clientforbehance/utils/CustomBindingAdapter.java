package com.example.clientforbehance.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {

    @BindingAdapter("bind:coverImageUrl")
    public static void loadCoverImage(ImageView imageView, String urlImage) {
        Picasso.with(imageView.getContext()).load(urlImage).fit().into(imageView);
    }

    @BindingAdapter("bind:userImageUrl")
    public static void loadUserImage(ImageView imageView, String urlImage) {
        Picasso.with(imageView.getContext()).load(urlImage).fit().into(imageView);
    }

}
