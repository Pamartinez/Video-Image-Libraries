package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectionFilmStripImageItemLayoutBinding implements ViewBinding {
    public final ImageView bestImageIcon;
    public final ImageView checkedIcon;
    public final RelativeLayout filmStripViewContainer;
    public final SquareImageView filmStripViewImage;
    private final RelativeLayout rootView;
    public final ImageView savedIcon;

    private SelectionFilmStripImageItemLayoutBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, SquareImageView squareImageView, ImageView imageView3) {
        this.rootView = relativeLayout;
        this.bestImageIcon = imageView;
        this.checkedIcon = imageView2;
        this.filmStripViewContainer = relativeLayout2;
        this.filmStripViewImage = squareImageView;
        this.savedIcon = imageView3;
    }

    public static SelectionFilmStripImageItemLayoutBinding bind(View view) {
        int i2 = R$id.best_image_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R$id.checked_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
            if (imageView2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i2 = R$id.film_strip_view_image;
                SquareImageView squareImageView = (SquareImageView) ViewBindings.findChildViewById(view, i2);
                if (squareImageView != null) {
                    i2 = R$id.saved_icon;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView3 != null) {
                        return new SelectionFilmStripImageItemLayoutBinding(relativeLayout, imageView, imageView2, relativeLayout, squareImageView, imageView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static SelectionFilmStripImageItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.selection_film_strip_image_item_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
