package com.samsung.android.gallery.widget.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.UtlTextView;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RemasterViewerLayoutBinding implements ViewBinding {
    public final RelativeLayout contentContainer;
    public final ViewerContentLayout contentContainerForTouch;
    public final ConstraintLayout decorLayout;
    public final View dimLayout;
    public final RemasterViewerHandlerLayoutBinding effectHandlerView;
    public final PhotoViewCompat photoView;
    public final AiProcessingEffectLayout processingContainerLayout;
    public final ViewStub remasterFocusView;
    public final ImageView remasterLoadingIcon;
    public final PhotoViewCompat remasterPhotoView;
    public final UtlTextView remasterProcessingText;
    public final ViewStub remasterProgressingValueLayout;
    private final CoordinatorLayout rootView;
    public final FlexModeTouchPadBinding tableModeBg;
    public final RelativeLayout viewerContainer;
    public final CoordinatorLayout viewerLayout;
    public final ViewStub zoomInOutLayout;

    private RemasterViewerLayoutBinding(CoordinatorLayout coordinatorLayout, RelativeLayout relativeLayout, ViewerContentLayout viewerContentLayout, ConstraintLayout constraintLayout, View view, RemasterViewerHandlerLayoutBinding remasterViewerHandlerLayoutBinding, PhotoViewCompat photoViewCompat, AiProcessingEffectLayout aiProcessingEffectLayout, ViewStub viewStub, ImageView imageView, PhotoViewCompat photoViewCompat2, UtlTextView utlTextView, ViewStub viewStub2, FlexModeTouchPadBinding flexModeTouchPadBinding, RelativeLayout relativeLayout2, CoordinatorLayout coordinatorLayout2, ViewStub viewStub3) {
        this.rootView = coordinatorLayout;
        this.contentContainer = relativeLayout;
        this.contentContainerForTouch = viewerContentLayout;
        this.decorLayout = constraintLayout;
        this.dimLayout = view;
        this.effectHandlerView = remasterViewerHandlerLayoutBinding;
        this.photoView = photoViewCompat;
        this.processingContainerLayout = aiProcessingEffectLayout;
        this.remasterFocusView = viewStub;
        this.remasterLoadingIcon = imageView;
        this.remasterPhotoView = photoViewCompat2;
        this.remasterProcessingText = utlTextView;
        this.remasterProgressingValueLayout = viewStub2;
        this.tableModeBg = flexModeTouchPadBinding;
        this.viewerContainer = relativeLayout2;
        this.viewerLayout = coordinatorLayout2;
        this.zoomInOutLayout = viewStub3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0085, code lost:
        r1 = com.samsung.android.gallery.widget.R$id.table_mode_bg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        r1 = com.samsung.android.gallery.widget.R$id.dim_layout;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
        r1 = com.samsung.android.gallery.widget.R$id.effect_handler_view;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding bind(android.view.View r21) {
        /*
            r0 = r21
            int r1 = com.samsung.android.gallery.widget.R$id.content_container
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r5 = r2
            android.widget.RelativeLayout r5 = (android.widget.RelativeLayout) r5
            if (r5 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.content_container_for_touch
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r6 = r2
            com.samsung.android.gallery.widget.abstraction.ViewerContentLayout r6 = (com.samsung.android.gallery.widget.abstraction.ViewerContentLayout) r6
            if (r6 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.decor_layout
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r7 = r2
            androidx.constraintlayout.widget.ConstraintLayout r7 = (androidx.constraintlayout.widget.ConstraintLayout) r7
            if (r7 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.dim_layout
            android.view.View r8 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r8 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.effect_handler_view
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r2 == 0) goto L_0x00b4
            com.samsung.android.gallery.widget.databinding.RemasterViewerHandlerLayoutBinding r9 = com.samsung.android.gallery.widget.databinding.RemasterViewerHandlerLayoutBinding.bind(r2)
            int r1 = com.samsung.android.gallery.widget.R$id.photo_view
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r10 = r2
            com.samsung.android.gallery.widget.photoview.PhotoViewCompat r10 = (com.samsung.android.gallery.widget.photoview.PhotoViewCompat) r10
            if (r10 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.processing_container_layout
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r11 = r2
            com.samsung.android.gallery.widget.AiProcessingEffectLayout r11 = (com.samsung.android.gallery.widget.AiProcessingEffectLayout) r11
            if (r11 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.remaster_focus_view
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r12 = r2
            android.view.ViewStub r12 = (android.view.ViewStub) r12
            if (r12 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.remaster_loading_icon
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r13 = r2
            android.widget.ImageView r13 = (android.widget.ImageView) r13
            if (r13 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.remaster_photo_view
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r14 = r2
            com.samsung.android.gallery.widget.photoview.PhotoViewCompat r14 = (com.samsung.android.gallery.widget.photoview.PhotoViewCompat) r14
            if (r14 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.remaster_processing_text
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r15 = r2
            com.samsung.android.gallery.widget.UtlTextView r15 = (com.samsung.android.gallery.widget.UtlTextView) r15
            if (r15 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.remaster_progressing_value_layout
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r16 = r2
            android.view.ViewStub r16 = (android.view.ViewStub) r16
            if (r16 == 0) goto L_0x00b4
            int r1 = com.samsung.android.gallery.widget.R$id.table_mode_bg
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            if (r2 == 0) goto L_0x00b4
            com.samsung.android.gallery.widget.databinding.FlexModeTouchPadBinding r17 = com.samsung.android.gallery.widget.databinding.FlexModeTouchPadBinding.bind(r2)
            int r1 = com.samsung.android.gallery.widget.R$id.viewer_container
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r18 = r2
            android.widget.RelativeLayout r18 = (android.widget.RelativeLayout) r18
            if (r18 == 0) goto L_0x00b4
            r4 = r0
            androidx.coordinatorlayout.widget.CoordinatorLayout r4 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r4
            int r1 = com.samsung.android.gallery.widget.R$id.zoom_in_out_layout
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r20 = r2
            android.view.ViewStub r20 = (android.view.ViewStub) r20
            if (r20 == 0) goto L_0x00b4
            com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding r3 = new com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding
            r19 = r4
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r3
        L_0x00b4:
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = r0.getResourceName(r1)
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "Missing required view with ID: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding.bind(android.view.View):com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding");
    }

    public static RemasterViewerLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R$layout.remaster_viewer_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
