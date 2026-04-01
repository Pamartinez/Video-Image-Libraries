package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Filmstrip3ImageItemLayoutBinding implements ViewBinding {
    public final RelativeLayout filmStripViewContainer;
    public final SquareImageView filmStripViewImage;
    private final RelativeLayout rootView;
    public final TextView test;

    private Filmstrip3ImageItemLayoutBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, SquareImageView squareImageView, TextView textView) {
        this.rootView = relativeLayout;
        this.filmStripViewContainer = relativeLayout2;
        this.filmStripViewImage = squareImageView;
        this.test = textView;
    }

    public static Filmstrip3ImageItemLayoutBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i2 = R$id.film_strip_view_image;
        SquareImageView squareImageView = (SquareImageView) ViewBindings.findChildViewById(view, i2);
        if (squareImageView != null) {
            i2 = R$id.test;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                return new Filmstrip3ImageItemLayoutBinding(relativeLayout, relativeLayout, squareImageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static Filmstrip3ImageItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.filmstrip3_image_item_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
