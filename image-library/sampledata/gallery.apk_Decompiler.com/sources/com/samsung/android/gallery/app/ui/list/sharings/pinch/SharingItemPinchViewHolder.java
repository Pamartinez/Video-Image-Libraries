package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import Ob.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingItemPinchViewHolder extends AlbumTitleCountViewHolder {
    private ImageView mCreatorIcon;
    private TextView mGroupMemberCount;
    private TextView mGroupName;

    public SharingItemPinchViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageOnUi */
    public void lambda$bindImage$0(Bitmap bitmap) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (TextUtils.isEmpty(mediaItem.getPath())) {
                bitmap = getReplacedThumbnail();
            }
            super.bindImage(bitmap);
        }
    }

    private Bitmap getReplacedThumbnail() {
        this.mMediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true ^ PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR);
    }

    public void bind(MediaItem mediaItem) {
        boolean z;
        super.bind(mediaItem);
        int i2 = 0;
        if (MediaItemMde.getUnreadCount(mediaItem) > 0) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(this.mNewLabel, z);
        TextView textView = this.mTitleText;
        if (z) {
            i2 = textView.getResources().getDimensionPixelSize(R.dimen.new_badge_dot_size);
        }
        ViewMarginUtils.setEndMargin(textView, i2);
    }

    public void bindCountView(MediaItem mediaItem) {
        this.mCountText.setVisibility(8);
    }

    public void bindImage(Bitmap bitmap) {
        ThreadUtil.runOnUiThread(this.mImageView, new a(3, this, bitmap));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mGroupMemberCount = (TextView) view.findViewById(R.id.member_count);
        this.mAlbumExpiryNoticeView = view.findViewById(R.id.album_expiry_notice_icon);
    }

    public void clearTransitionName() {
        this.mImageView.setTransitionName((String) null);
        this.mTitleText.setTransitionName((String) null);
    }

    public String getContentDescription() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mMediaItem.getDisplayName());
        sb2.append(" ");
        sb2.append(getString(R.string.album));
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.mGroupMemberCount.getText().toString());
        if (ViewUtils.isVisible(this.mNewLabel)) {
            sb2.append(" ");
            sb2.append(getString(R.string.new_content_added));
        }
        return sb2.toString();
    }

    public View getDecoView(int i2) {
        if (i2 == 42) {
            return this.mCreatorIcon;
        }
        if (i2 == 46) {
            return this.mGroupMemberCount;
        }
        if (i2 == 47) {
            return this.mGroupName;
        }
        return super.getDecoView(i2);
    }

    public TextView getSubTitleView() {
        return this.mGroupName;
    }

    public void recycle() {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            clearTransitionName();
        }
        ViewUtils.setText(this.mGroupMemberCount, (String) null);
        super.recycle();
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mImageView, i2, f);
        return this;
    }

    public void setTransitionName(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            String spaceId = MediaItemMde.getSpaceId(mediaItem);
            this.mImageView.setTransitionName(SharedTransition.getTransitionName(mediaItem));
            TextView textView = this.mTitleText;
            textView.setTransitionName("sharing/title/" + spaceId);
        }
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

    public void resizeUpFontToLarge(View view) {
    }
}
