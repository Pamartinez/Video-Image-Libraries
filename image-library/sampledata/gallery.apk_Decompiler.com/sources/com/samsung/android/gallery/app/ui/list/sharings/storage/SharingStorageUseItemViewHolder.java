package com.samsung.android.gallery.app.ui.list.sharings.storage;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingStorageUseItemViewHolder extends ImageTitleViewHolder {
    protected View mDivider;
    protected TextView mMyUsage;
    protected float mRoundRadius;

    public SharingStorageUseItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private Bitmap getReplacedThumbnail() {
        this.mMediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setThumbnailShape(1, this.mRoundRadius);
        addThumbnailBorder(getContext().getDrawable(R.drawable.sharings_thumbnail_border));
        this.mMyUsage.setText(StringCompat.toReadableSize((double) MediaItemMde.getMyUsage(mediaItem)));
        if (getViewType() != 7) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mDivider.getLayoutParams();
            marginLayoutParams.setMarginStart((int) (getContext().getResources().getDimension(R.dimen.shared_storage_use_view_text_horizontal_margin) + getContext().getResources().getDimension(R.dimen.shared_storage_use_thumbnail_size)));
            marginLayoutParams.setMarginEnd((int) getContext().getResources().getDimension(R.dimen.shared_storage_use_view_item_divider_end_margin_diff));
            this.mDivider.setLayoutParams(marginLayoutParams);
            this.mDivider.setVisibility(0);
        }
    }

    public void bindImage(Bitmap bitmap) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (bitmap == null && TextUtils.isEmpty(mediaItem.getPath())) {
                bitmap = getReplacedThumbnail();
            }
            super.bindImage(bitmap);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMyUsage = (TextView) view.findViewById(R.id.my_usage);
        this.mDivider = view.findViewById(R.id.divider);
        this.mRoundRadius = view.getResources().getDimension(R.dimen.shared_view_round_radius);
    }

    public String getContentDescription() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        if (TextUtils.isEmpty(this.mMediaItem.getDisplayName())) {
            str = this.mMediaItem.getTitle();
        } else {
            str = this.mMediaItem.getDisplayName();
        }
        sb2.append(str);
        sb2.append(" ");
        sb2.append(getString(R.string.album));
        if (this.mMyUsage.getVisibility() == 0) {
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(this.mMyUsage.getText().toString());
        }
        return sb2.toString();
    }

    public TextView getSubTitleView() {
        return this.mMyUsage;
    }

    public void recycle() {
        this.mMyUsage.setText((CharSequence) null);
        super.recycle();
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
