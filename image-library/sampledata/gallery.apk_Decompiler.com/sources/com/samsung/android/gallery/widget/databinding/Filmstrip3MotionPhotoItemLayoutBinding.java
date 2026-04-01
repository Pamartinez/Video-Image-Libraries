package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Filmstrip3MotionPhotoItemLayoutBinding implements ViewBinding {
    public final LinearLayout filmStripFrame;
    public final RelativeLayout filmStripViewContainer;
    public final SquareImageView filmStripViewImage;
    private final RelativeLayout rootView;
    public final TextView test;
    public final ImageView videoPlayIcon;

    private Filmstrip3MotionPhotoItemLayoutBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, SquareImageView squareImageView, TextView textView, ImageView imageView) {
        this.rootView = relativeLayout;
        this.filmStripFrame = linearLayout;
        this.filmStripViewContainer = relativeLayout2;
        this.filmStripViewImage = squareImageView;
        this.test = textView;
        this.videoPlayIcon = imageView;
    }

    public static Filmstrip3MotionPhotoItemLayoutBinding bind(View view) {
        int i2 = R$id.film_strip_frame;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i2 = R$id.film_strip_view_image;
            SquareImageView squareImageView = (SquareImageView) ViewBindings.findChildViewById(view, i2);
            if (squareImageView != null) {
                i2 = R$id.test;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    i2 = R$id.video_play_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView != null) {
                        return new Filmstrip3MotionPhotoItemLayoutBinding(relativeLayout, linearLayout, relativeLayout, squareImageView, textView, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static Filmstrip3MotionPhotoItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.filmstrip3_motion_photo_item_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
