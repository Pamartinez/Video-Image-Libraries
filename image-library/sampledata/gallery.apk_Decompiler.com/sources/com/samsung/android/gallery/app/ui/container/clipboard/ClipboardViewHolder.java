package com.samsung.android.gallery.app.ui.container.clipboard;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipboardViewHolder extends RecyclerView.ViewHolder {
    private ViewGroup mContentTypeContainer;
    private FrameLayout mLayout;
    private ImageView mRemoveIcon;
    private ImageView mThumbnailView;

    public ClipboardViewHolder(View view) {
        super(view);
        bindView(view);
    }

    private void bindView(View view) {
        this.mLayout = (FrameLayout) view.findViewById(R.id.item_layout);
        this.mThumbnailView = (ImageView) view.findViewById(R.id.clipboard_list_item_thumbnail);
        this.mRemoveIcon = (ImageView) view.findViewById(R.id.clipboard_list_remove_icon);
        this.mContentTypeContainer = (ViewGroup) view.findViewById(R.id.content_type_container);
    }

    public FrameLayout getLayout() {
        return this.mLayout;
    }

    public ImageView getRemoveIconView() {
        return this.mRemoveIcon;
    }

    public ImageView getThumbnailView() {
        return this.mThumbnailView;
    }

    public void setContentType(MediaItem mediaItem) {
        if (mediaItem != null) {
            ViewUtils.setVisibleOrGone(this.mContentTypeContainer, mediaItem.isVideo());
        }
    }
}
