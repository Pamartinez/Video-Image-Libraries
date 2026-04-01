package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultViewHolder extends PreviewViewHolder {
    protected ImageView mOcrIcon;
    protected ViewStub mOcrIconViewStub;

    public SearchClusterResultViewHolder(View view, int i2) {
        super(view, i2);
    }

    private boolean hasOcrData() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !DetailsData.of(mediaItem).hasOcr) {
            return false;
        }
        return true;
    }

    private void inflateOcrIconView() {
        ViewStub viewStub;
        if (this.mOcrIcon == null && (viewStub = this.mOcrIconViewStub) != null) {
            ImageView imageView = (ImageView) inflateStub(viewStub);
            this.mOcrIcon = imageView;
            imageView.setFocusable(false);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mOcrIconViewStub = (ViewStub) view.findViewById(R.id.ocrIconViewStub);
    }

    public void clearReplacedView(View view) {
        super.clearReplacedView(view);
        if (this.mOcrIcon == view) {
            this.mOcrIcon = null;
        }
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mOcrIcon, 8);
    }

    public void recycleToBackup() {
        super.recycleToBackup();
        replaceWithViewStubToClear(this.mOcrIcon, this.mOcrIconViewStub);
    }

    public void setDecoViewVisibility(MediaItem mediaItem) {
        boolean z;
        super.setDecoViewVisibility(mediaItem);
        inflateOcrIconView();
        ImageView imageView = this.mOcrIcon;
        if (!hasOcrData() || isSelectionMode()) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleOrGone(imageView, z);
    }

    public void setDecoViewVisibilityInSelectionMode() {
        boolean z;
        super.setDecoViewVisibilityInSelectionMode();
        ImageView imageView = this.mOcrIcon;
        if (!hasOcrData() || isSelectionMode()) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleOrGone(imageView, z);
    }
}
