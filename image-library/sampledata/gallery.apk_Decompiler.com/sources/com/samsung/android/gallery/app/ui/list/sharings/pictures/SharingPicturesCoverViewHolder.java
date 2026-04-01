package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingPicturesCoverViewHolder extends ImageTitleViewHolder {
    RelativeLayout mCoverHolder;
    ImageView mDimmingView;
    TextView mGroupCountText;
    LinearLayout mGroupInfoLayout;
    ImageView mGroupText;
    TextView mItemCountText;
    ImageView mOwnerImage;

    public SharingPicturesCoverViewHolder(View view, int i2) {
        super(view, i2);
        int statusBarHeight = DeviceInfo.getStatusBarHeight();
        this.mCoverHolder.getLayoutParams().height = getContext().getResources().getDimensionPixelSize(R.dimen.shared_photo_cover_thumb_height) + statusBarHeight;
        this.mTitleText.setPadding(0, statusBarHeight / 2, 0, 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageInternal */
    public void lambda$bindImage$1(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap = BitmapUtils.blur(getRootView().getContext(), bitmap, 11.0f);
        }
        super.bindImage(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onClickGroupInfo();
    }

    private void setTransitionNames(MediaItem mediaItem) {
        String spaceId = MediaItemMde.getSpaceId(mediaItem);
        SharedTransition.setTransitionName(getImage(), SharedTransition.getTransitionName(mediaItem));
        TextView titleView = getTitleView();
        SharedTransition.setTransitionName(titleView, "sharing/title/" + spaceId);
        if (MediaItemMde.isOwnedByMe(mediaItem)) {
            ImageView imageView = this.mOwnerImage;
            SharedTransition.setTransitionName(imageView, "sharing/owner/" + spaceId);
        }
        LinearLayout linearLayout = this.mGroupInfoLayout;
        SharedTransition.setTransitionName(linearLayout, "sharing/groupInfo/" + spaceId);
    }

    public void bind(MediaItem mediaItem) {
        float f;
        super.bind(mediaItem);
        ImageView imageView = this.mGroupText;
        if (Features.isEnabled(Features.IS_RTL)) {
            f = 180.0f;
        } else {
            f = 0.0f;
        }
        imageView.setRotation(f);
        setTransitionNames(mediaItem);
    }

    public void bindImage(Bitmap bitmap) {
        ThreadUtil.runOnUiThread(getImage(), new g(0, this, bitmap));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCoverHolder = (RelativeLayout) view.findViewById(R.id.cover_holder);
        this.mGroupCountText = (TextView) view.findViewById(R.id.group_count);
        this.mItemCountText = (TextView) view.findViewById(R.id.item_count);
        this.mOwnerImage = (ImageView) view.findViewById(R.id.icon);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.group_information_layout);
        this.mGroupInfoLayout = linearLayout;
        linearLayout.setOnClickListener(new e(2, this));
        this.mDimmingView = (ImageView) view.findViewById(R.id.dimmingView);
        this.mGroupText = (ImageView) view.findViewById(R.id.group_next);
    }

    public TextView getCountView() {
        return this.mGroupCountText;
    }

    public View getDecoView(int i2) {
        if (i2 == 42) {
            return this.mOwnerImage;
        }
        if (i2 == 43) {
            return this.mGroupInfoLayout;
        }
        if (i2 == 44) {
            return this.mDimmingView;
        }
        return super.getDecoView(i2);
    }

    public LinearLayout getGroupInfoLayout() {
        return this.mGroupInfoLayout;
    }

    public ImageView getOwnerImageView() {
        return this.mOwnerImage;
    }

    public TextView getSubTitleView() {
        return this.mItemCountText;
    }

    public void onClickGroupInfo() {
        MdeGroupHelper.requestShowGroupDetail(this.mImageView.getContext(), MediaItemMde.getGroupId(getMediaItem()), getMediaItem().getTitle());
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_NORMAL.toString(), AnalyticsEventId.EVENT_SHARED_DETAIL_GROUP_DETAIL.toString());
    }

    public void recycle() {
        this.mItemCountText.setText((CharSequence) null);
        this.mGroupCountText.setText((CharSequence) null);
        super.recycle();
    }

    public void setViewMatrix() {
        int i2;
        if (MediaItemMde.isUserCoverItem(this.mMediaItem)) {
            RectF stringToRectF = RectUtils.stringToRectF(MediaItemMde.getSpaceCoverRectRatio(this.mMediaItem));
            if (this.mMediaItem.isVideo()) {
                i2 = 0;
            } else {
                i2 = this.mMediaItem.getOrientation();
            }
            setViewMatrix(stringToRectF, i2);
            return;
        }
        setViewMatrix(RectUtils.stringToRectF(""), this.mMediaItem.getOrientation());
    }
}
