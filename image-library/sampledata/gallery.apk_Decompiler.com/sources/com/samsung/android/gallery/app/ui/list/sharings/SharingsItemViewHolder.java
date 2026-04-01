package com.samsung.android.gallery.app.ui.list.sharings;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingsItemViewHolder extends AlbumTitleCountViewHolder {
    protected ImageView mCreatorIcon;
    protected TextView mGroupMemberCount;
    protected LinearLayout mGroupMemberLayout;
    protected TextView mGroupName;
    protected View mHolderView;
    protected float mRoundRadius;

    public SharingsItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageOnUi */
    public void lambda$bindImage$0(Bitmap bitmap) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            if (bitmap == null && TextUtils.isEmpty(mediaItem.getPath())) {
                bitmap = getReplacedThumbnail();
            }
            super.bindImage(bitmap);
        }
    }

    public static ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingsItemViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_sharings_image_layout, viewGroup, false), i2);
    }

    private Bitmap getReplacedThumbnail() {
        this.mMediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        super.bind(mediaItem);
        setThumbnailShape(1, this.mRoundRadius);
        addThumbnailBorder(getContext().getDrawable(R.drawable.sharings_thumbnail_border));
        this.mCreatorIcon.setTooltipText(getContext().getString(R.string.group_creator));
        ImageView imageView = this.mCreatorIcon;
        int i7 = 8;
        if (MediaItemMde.isOwnedByMe(mediaItem)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        View view = this.mNewLabel;
        if (MediaItemMde.getUnreadCount(mediaItem) > 0) {
            i7 = 0;
        }
        ViewUtils.setVisibility(view, i7);
        ViewUtils.setVisibleOrGone(this.mGroupName, MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem)));
    }

    public void bindImage(Bitmap bitmap) {
        ThreadUtil.runOnUiThread(this.mImageView, new a(this, bitmap));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mGroupMemberCount = (TextView) view.findViewById(R.id.count);
        this.mGroupName = (TextView) view.findViewById(R.id.subtitle);
        this.mCreatorIcon = (ImageView) view.findViewById(R.id.icon);
        this.mGroupMemberLayout = (LinearLayout) view.findViewById(R.id.groupMemberLayout);
        this.mHolderView = view.findViewById(R.id.recycler_view_item);
        this.mAlbumExpiryNoticeView = view.findViewById(R.id.album_expiry_notice_icon);
        this.mRoundRadius = view.getResources().getDimension(R.dimen.shared_view_round_radius);
    }

    public void clearTransitionName() {
        this.mImageView.setTransitionName((String) null);
        this.mTitleText.setTransitionName((String) null);
        this.mGroupMemberLayout.setTransitionName((String) null);
        this.mCreatorIcon.setTransitionName((String) null);
    }

    public String getContentDescription() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mMediaItem.getDisplayName());
        sb2.append(" ");
        sb2.append(this.mGroupMemberCount.getText().toString());
        if (this.mGroupName.getVisibility() == 0) {
            sb2.append(" ");
            sb2.append(this.mGroupName.getText().toString());
        }
        if (this.mCreatorIcon.getVisibility() == 0) {
            sb2.append(" ");
            sb2.append(getString(R.string.leader));
        }
        View view = this.mNewLabel;
        if (view != null && view.getVisibility() == 0) {
            sb2.append(" ");
            sb2.append(getString(R.string.new_content_added));
        }
        return sb2.toString();
    }

    public TextView getCountView() {
        return this.mGroupMemberCount;
    }

    public View getDecoView(int i2) {
        if (i2 == 42) {
            return this.mCreatorIcon;
        }
        if (i2 == 43) {
            return this.mGroupMemberLayout;
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
        this.mTitleText.requestLayout();
        ViewUtils.setVisibleOrGone(this.mGroupName, false);
        this.mGroupMemberCount.setText((CharSequence) null);
        super.recycle();
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mImageView, i2, f);
        ViewUtils.setViewShape(this.mGroupMemberLayout, i2, f);
        ViewUtils.setViewShape(this.mGroupName, i2, f);
        return this;
    }

    public void setTransitionName(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            String spaceId = MediaItemMde.getSpaceId(mediaItem);
            this.mImageView.setTransitionName(SharedTransition.getTransitionName(mediaItem));
            TextView textView = this.mTitleText;
            textView.setTransitionName("sharing/title/" + spaceId);
            LinearLayout linearLayout = this.mGroupMemberLayout;
            linearLayout.setTransitionName("sharing/groupInfo/" + spaceId);
            if (MediaItemMde.isOwnedByMe(mediaItem)) {
                ImageView imageView = this.mCreatorIcon;
                imageView.setTransitionName("sharing/owner/" + spaceId);
            }
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
