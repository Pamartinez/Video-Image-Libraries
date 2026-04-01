package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewerPool;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowContentViewerPool extends ContentViewerPool {
    private final LayoutInflater mLayoutInflater;
    private final AbsViewerHolder.StateListener mStateListener;

    public SlideshowContentViewerPool(Context context, ContainerModel containerModel, ContentViewCompositeFactory contentViewCompositeFactory, AbsViewerHolder.StateListener stateListener) {
        super(context, containerModel, contentViewCompositeFactory, stateListener);
        this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mStateListener = stateListener;
    }

    public AbsViewerHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return new SlideshowViewerHolder(SlideshowViewerLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false), this.mStateListener);
    }
}
