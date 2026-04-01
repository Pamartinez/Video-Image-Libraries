package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Filmstrip3SeekerViewLayoutBinding implements ViewBinding {
    public final RelativeLayout borderView;
    public final View centerIndexer;
    public final View indexer;
    public final LinearLayout progressView;
    private final RelativeLayout rootView;

    private Filmstrip3SeekerViewLayoutBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, View view, View view2, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.borderView = relativeLayout2;
        this.centerIndexer = view;
        this.indexer = view2;
        this.progressView = linearLayout;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r0 = com.samsung.android.gallery.widget.R$id.center_indexer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        r0 = com.samsung.android.gallery.widget.R$id.indexer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding bind(android.view.View r8) {
        /*
            int r0 = com.samsung.android.gallery.widget.R$id.border_view
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r8, r0)
            r4 = r1
            android.widget.RelativeLayout r4 = (android.widget.RelativeLayout) r4
            if (r4 == 0) goto L_0x002f
            int r0 = com.samsung.android.gallery.widget.R$id.center_indexer
            android.view.View r5 = androidx.viewbinding.ViewBindings.findChildViewById(r8, r0)
            if (r5 == 0) goto L_0x002f
            int r0 = com.samsung.android.gallery.widget.R$id.indexer
            android.view.View r6 = androidx.viewbinding.ViewBindings.findChildViewById(r8, r0)
            if (r6 == 0) goto L_0x002f
            int r0 = com.samsung.android.gallery.widget.R$id.progress_view
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r8, r0)
            r7 = r1
            android.widget.LinearLayout r7 = (android.widget.LinearLayout) r7
            if (r7 == 0) goto L_0x002f
            com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding r2 = new com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding
            r3 = r8
            android.widget.RelativeLayout r3 = (android.widget.RelativeLayout) r3
            r2.<init>(r3, r4, r5, r6, r7)
            return r2
        L_0x002f:
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r8 = r8.getResourceName(r0)
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Missing required view with ID: "
            java.lang.String r8 = r1.concat(r8)
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding.bind(android.view.View):com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding");
    }

    public static Filmstrip3SeekerViewLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.filmstrip3_seeker_view_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
