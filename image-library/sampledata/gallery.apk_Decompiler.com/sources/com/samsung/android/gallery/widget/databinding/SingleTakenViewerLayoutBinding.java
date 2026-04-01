package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleTakenViewerLayoutBinding implements ViewBinding {
    public final RelativeLayout contentContainer;
    private final CoordinatorLayout rootView;
    public final CoordinatorLayout viewerLayout;
    public final PhotoViewCompat viewerPreview;

    private SingleTakenViewerLayoutBinding(CoordinatorLayout coordinatorLayout, RelativeLayout relativeLayout, CoordinatorLayout coordinatorLayout2, PhotoViewCompat photoViewCompat) {
        this.rootView = coordinatorLayout;
        this.contentContainer = relativeLayout;
        this.viewerLayout = coordinatorLayout2;
        this.viewerPreview = photoViewCompat;
    }

    public static SingleTakenViewerLayoutBinding bind(View view) {
        int i2 = R$id.content_container;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i2);
        if (relativeLayout != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            int i7 = R$id.viewer_preview;
            PhotoViewCompat photoViewCompat = (PhotoViewCompat) ViewBindings.findChildViewById(view, i7);
            if (photoViewCompat != null) {
                return new SingleTakenViewerLayoutBinding(coordinatorLayout, relativeLayout, coordinatorLayout, photoViewCompat);
            }
            i2 = i7;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static SingleTakenViewerLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.single_taken_viewer_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
