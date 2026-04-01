package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumTitleCountReorderHolder extends AlbumTitleCountViewHolder {
    private boolean mIsCustomOrder = true;
    private View mReorderIconView;
    private final boolean mSupportReorder;

    public AlbumTitleCountReorderHolder(View view, int i2, boolean z) {
        super(view, i2);
        setUseThumbnailCheckbox(false);
        this.mSupportReorder = z;
    }

    private View getReorderIcon() {
        if (!this.mSupportReorder) {
            return null;
        }
        inflateReorderIcon();
        return this.mReorderIconView;
    }

    private void inflateReorderIcon() {
        View view = this.mReorderIconView;
        if (view instanceof ViewStub) {
            this.mReorderIconView = ((ViewStub) view).inflate();
            setReorderIconVisibleOrGone(false);
        }
    }

    private void setReorderIconVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(this.mReorderIconView, z);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (this.mSupportReorder && this.mIsCustomOrder && isCheckBoxEnabled()) {
            inflateReorderIcon();
            setReorderIconVisibleOrGone(true);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mReorderIconView = view.findViewById(R.id.reorder_icon);
    }

    public void enableBorderView(boolean z, Drawable drawable) {
        super.enableBorderView(z, drawable);
        inflateReorderIcon();
        int i2 = 0;
        if (isCheckBoxEnabled()) {
            if (this.mSupportReorder) {
                setReorderIconVisibleOrGone(!z);
            }
            CheckBox checkBox = this.mCheckboxView;
            if (z) {
                i2 = 4;
            }
            checkBox.setVisibility(i2);
        } else if (this.mSupportReorder) {
            setReorderIconVisibleOrGone(false);
        }
    }

    public View getDecoView(int i2) {
        if (i2 == 22) {
            return getReorderIcon();
        }
        return super.getDecoView(i2);
    }

    public boolean isGridView() {
        return false;
    }

    public void recycle() {
        super.recycle();
        if (this.mSupportReorder) {
            setReorderIconVisibleOrGone(false);
        }
    }

    public void setCheckboxEnabled(boolean z) {
        boolean z3;
        super.setCheckboxEnabled(z);
        if (this.mSupportReorder && this.mIsCustomOrder) {
            if (!z || this.mMediaItem == null) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                inflateReorderIcon();
            }
            setReorderIconVisibleOrGone(z3);
        }
    }

    public void setIsCustomOrder(boolean z) {
        this.mIsCustomOrder = z;
        if (this.mSupportReorder && !z) {
            setReorderIconVisibleOrGone(false);
        }
    }

    public AlbumTitleCountReorderHolder(View view, int i2, boolean z, boolean z3) {
        super(view, i2, z3);
        setUseThumbnailCheckbox(false);
        this.mSupportReorder = z;
    }
}
