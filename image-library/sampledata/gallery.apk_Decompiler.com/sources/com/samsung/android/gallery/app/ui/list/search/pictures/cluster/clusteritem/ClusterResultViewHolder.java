package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleDurationViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClusterResultViewHolder extends ImageTitleDurationViewHolder {
    protected View mDecoViewLayout;
    protected final EventContext mHandler;

    public ClusterResultViewHolder(EventContext eventContext, View view, int i2) {
        super(view, i2);
        this.mHandler = eventContext;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        TextView textView = this.mDurationText;
        if (textView != null) {
            textView.setVisibility(0);
            this.mDurationText.setText(getDurationText(mediaItem));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDecoViewLayout = view.findViewById(R.id.deco_view_layout);
        setThumbnailShape(1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_cluster_results_album_type_item_radius));
    }

    public View getDecoView(int i2) {
        if (i2 == 57) {
            return this.mDecoViewLayout;
        }
        return super.getDecoView(i2);
    }
}
