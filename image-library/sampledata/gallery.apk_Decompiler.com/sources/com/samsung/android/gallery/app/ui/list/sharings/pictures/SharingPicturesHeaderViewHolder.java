package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPicturesHeaderViewHolder extends ImageTitleViewHolder {
    private View mOwnerIcon;
    private TextView mPeriodView;

    public SharingPicturesHeaderViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mOwnerIcon = view.findViewById(R.id.owner_icon);
        this.mPeriodView = (TextView) view.findViewById(R.id.period);
    }

    public View getOwnerView() {
        return this.mOwnerIcon;
    }

    public TextView getPeriodView() {
        return this.mPeriodView;
    }

    public void setViewMatrix() {
        int i2;
        if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = this.mMediaItem.getOrientation();
        }
        if (TextUtils.isEmpty(this.mMediaItem.getPath()) || !MediaItemMde.isUserCoverItem(this.mMediaItem)) {
            setViewMatrix((RectF) null, i2);
        } else {
            setViewMatrix(RectUtils.stringToRectF(MediaItemMde.getSpaceCoverRectRatio(this.mMediaItem)), i2);
        }
    }
}
