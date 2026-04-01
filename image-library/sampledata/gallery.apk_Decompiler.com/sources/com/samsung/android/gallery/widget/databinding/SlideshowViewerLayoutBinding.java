package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SlideshowViewerLayoutBinding implements ViewBinding {
    public final RelativeLayout contentContainer;
    public final View foregroundImageView;
    public final PhotoViewCompat photoView;
    private final CoordinatorLayout rootView;
    public final ViewStub videoViewStub;
    public final ViewStub viewerDebug;
    public final CoordinatorLayout viewerLayout;

    private SlideshowViewerLayoutBinding(CoordinatorLayout coordinatorLayout, RelativeLayout relativeLayout, View view, PhotoViewCompat photoViewCompat, ViewStub viewStub, ViewStub viewStub2, CoordinatorLayout coordinatorLayout2) {
        this.rootView = coordinatorLayout;
        this.contentContainer = relativeLayout;
        this.foregroundImageView = view;
        this.photoView = photoViewCompat;
        this.videoViewStub = viewStub;
        this.viewerDebug = viewStub2;
        this.viewerLayout = coordinatorLayout2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r0 = com.samsung.android.gallery.widget.R$id.foreground_image_view;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding bind(android.view.View r10) {
        /*
            int r0 = com.samsung.android.gallery.widget.R$id.content_container
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
            r4 = r1
            android.widget.RelativeLayout r4 = (android.widget.RelativeLayout) r4
            if (r4 == 0) goto L_0x003e
            int r0 = com.samsung.android.gallery.widget.R$id.foreground_image_view
            android.view.View r5 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
            if (r5 == 0) goto L_0x003e
            int r0 = com.samsung.android.gallery.widget.R$id.photo_view
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
            r6 = r1
            com.samsung.android.gallery.widget.photoview.PhotoViewCompat r6 = (com.samsung.android.gallery.widget.photoview.PhotoViewCompat) r6
            if (r6 == 0) goto L_0x003e
            int r0 = com.samsung.android.gallery.widget.R$id.video_view_stub
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
            r7 = r1
            android.view.ViewStub r7 = (android.view.ViewStub) r7
            if (r7 == 0) goto L_0x003e
            int r0 = com.samsung.android.gallery.widget.R$id.viewer_debug
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
            r8 = r1
            android.view.ViewStub r8 = (android.view.ViewStub) r8
            if (r8 == 0) goto L_0x003e
            r3 = r10
            androidx.coordinatorlayout.widget.CoordinatorLayout r3 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r3
            com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding r2 = new com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding
            r9 = r3
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r2
        L_0x003e:
            android.content.res.Resources r10 = r10.getResources()
            java.lang.String r10 = r10.getResourceName(r0)
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Missing required view with ID: "
            java.lang.String r10 = r1.concat(r10)
            r0.<init>(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding.bind(android.view.View):com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding");
    }

    public static SlideshowViewerLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.slideshow_viewer_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
