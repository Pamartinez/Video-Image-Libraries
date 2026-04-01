package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import A6.a;
import B8.e;
import H7.A;
import O3.y;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.sharing.StartSlideShowSharedAlbumCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingHeaderViewDelegateV2 extends SharingHeaderViewDelegate {
    private SharingPicturesHeaderViewHolder mHeaderViewHolder;
    private View mItemView;
    private View mTipCardLayout;

    public SharingHeaderViewDelegateV2(ISharingPicturesView iSharingPicturesView) {
        super(iSharingPicturesView);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageToView */
    public void lambda$bindImageToView$3(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        SharingPicturesHeaderViewHolder sharingPicturesHeaderViewHolder = this.mHeaderViewHolder;
        if (sharingPicturesHeaderViewHolder == null) {
            return;
        }
        if (ThreadUtil.isMainThread()) {
            sharingPicturesHeaderViewHolder.bindImage(bitmap);
        } else {
            ThreadUtil.postOnUiThread(new a((Object) this, (Object) bitmap, (Object) uniqueKey, (Object) thumbKind, 15));
        }
    }

    private void bindViewHolder() {
        MediaItem headerItem = this.mView.getHeaderItem();
        this.mHeaderViewHolder.bind(headerItem);
        if (headerItem == null || TextUtils.isEmpty(headerItem.getPath()) || TextUtils.isEmpty(headerItem.getThumbCacheKey())) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(new ColorDrawable(this.mHeaderViewHolder.itemView.getContext().getColor(R.color.shared_photo_cover_empty_color)), 50, 50);
            if (bitmapFromDrawable != null) {
                lambda$bindImageToView$3(bitmapFromDrawable, (UniqueKey) null, ThumbKind.LARGE_KIND);
            }
        } else {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.LARGE_KIND;
            Bitmap memCache = instance.getMemCache(headerItem, thumbKind);
            if (memCache != null) {
                lambda$bindImageToView$3(memCache, (UniqueKey) null, thumbKind);
            } else {
                instance.loadThumbnail(headerItem, thumbKind, new e(headerItem, 1), new y(3, this));
            }
        }
        setPeriodToViewAsync(MediaItemMde.getSpaceId(headerItem));
        setOwnerViewVisibility(MediaItemMde.isOwnedByMe(headerItem));
    }

    private void createViewHolder() {
        this.mHeaderViewHolder = new SharingPicturesHeaderViewHolder(this.mHeaderView, 0);
    }

    private boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!MediaItemUtil.equals(mediaItem, mediaItem2) || !RectUtils.equalsRectF(mediaItem.getCropRectRatio(), mediaItem2.getCropRectRatio()) || mediaItem.isCustomCover() != mediaItem2.isCustomCover() || MediaItemMde.isUserCoverItem(mediaItem) != MediaItemMde.isUserCoverItem(mediaItem2) || !TextUtils.equals(MediaItemMde.getSpaceCoverRectRatio(mediaItem), MediaItemMde.getSpaceCoverRectRatio(mediaItem2))) {
            return false;
        }
        if (!PreferenceFeatures.OneUi41.SHARING_LEADER_AUTHORITY_DELEGATION || MdeMediaItemHelper.isSameOwner(mediaItem, mediaItem2)) {
            return true;
        }
        return false;
    }

    private int getSize(View view, int i2) {
        if (view != null) {
            return view.getResources().getDimensionPixelOffset(i2);
        }
        return 0;
    }

    private void inflate() {
        View inflate = LayoutInflater.from(this.mView.getActivity()).inflate(R.layout.recycler_item_sharing_pictures_header_layout_v2, (ViewGroup) null, false);
        this.mHeaderView = inflate;
        View findViewById = inflate.findViewById(R.id.recycler_item_view);
        this.mItemView = findViewById;
        findViewById.setOnClickListener(new A(14, this));
        updateLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPeriodToViewAsync$1(String str) {
        this.mHeaderViewHolder.getPeriodView().setText(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPeriodToViewAsync$2(String str) {
        ThreadUtil.postponeOnUiThread(new O5.a(this, MdeAlbumHelper.getSharedAlbumPeriod(str), 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSlideShow$0(MediaItem mediaItem) {
        new StartSlideShowSharedAlbumCmd().execute(this.mView.getEventContext(), mediaItem);
    }

    private void setContentLayout() {
        View findViewById = this.mItemView.findViewById(R.id.content_view_layout);
        ViewMarginUtils.setHorizontalMargin(findViewById, getSize(findViewById, R.dimen.shared_pictures_header_item_view_horizontal_margin));
    }

    private void setDecorViewLayout() {
        setIconView();
        setContentLayout();
        setTitleView();
        setSubTitleView();
    }

    private void setIconView() {
        ImageView imageView = (ImageView) this.mItemView.findViewById(R.id.owner_icon);
        ViewMarginUtils.setMargin(imageView, getSize(imageView, R.dimen.shared_pictures_header_item_icon_margin));
    }

    private void setItemViewLayout() {
        int i2;
        View view = this.mItemView;
        if (!ResourceCompat.isPortrait(view) || this.mView.isTabletDpi()) {
            i2 = getSize(this.mItemView, R.dimen.shared_pictures_header_item_view_width);
        } else {
            i2 = -1;
        }
        ViewUtils.resize(view, i2, getSize(this.mItemView, R.dimen.shared_pictures_header_item_view_height));
        View view2 = this.mItemView;
        ViewUtils.setViewShape(view2, 1, (float) getSize(view2, R.dimen.shared_pictures_header_item_view_radius));
    }

    private void setOwnerViewVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mHeaderViewHolder.getOwnerView(), z);
    }

    private void setPeriodToViewAsync(String str) {
        SimpleThreadPool.getInstance().execute(new O5.a(this, str, 0));
    }

    private void setSubTitleView() {
        TextView textView = (TextView) this.mItemView.findViewById(R.id.period);
        ViewMarginUtils.setBottomMargin(textView, getSize(textView, R.dimen.shared_pictures_header_item_sub_title_bottom_margin));
        ViewUtils.setTextSize(textView, 0, getSize(textView, R.dimen.shared_pictures_header_item_sub_title_text_size));
    }

    private void setTitleView() {
        TextView textView = (TextView) this.mItemView.findViewById(R.id.title);
        ViewMarginUtils.setBottomMargin(textView, getSize(textView, R.dimen.shared_pictures_header_item_title_bottom_margin));
        ViewUtils.setTextSize(textView, 0, getSize(textView, R.dimen.shared_pictures_header_item_title_text_size));
    }

    /* access modifiers changed from: private */
    public void startSlideShow(View view) {
        if (!this.mView.isSelectionMode()) {
            this.mView.dismissAlbumLinkTip();
            Optional.ofNullable(this.mView.getHeaderItem()).ifPresent(new K5.a(21, this));
        }
    }

    public void changeTipCard(View view, boolean z) {
        ViewUtils.setVisibleOrGone(this.mTipCardLayout, z);
    }

    public /* bridge */ /* synthetic */ View get() {
        return super.get();
    }

    public View inflateTipCard(Context context, boolean z, Pair<Integer, Integer> pair) {
        View view;
        if (!z || context == null) {
            return this.mTipCardLayout;
        }
        if (this.mTipCardLayout == null && (view = this.mHeaderView) != null) {
            this.mTipCardLayout = view.findViewById(R.id.tip_card_layout);
        }
        composeTipCard(this.mTipCardLayout, context, pair);
        return this.mTipCardLayout;
    }

    public void initView() {
        inflate();
        createViewHolder();
        bindViewHolder();
    }

    public void recycle() {
        SharingPicturesHeaderViewHolder sharingPicturesHeaderViewHolder = this.mHeaderViewHolder;
        if (sharingPicturesHeaderViewHolder != null) {
            sharingPicturesHeaderViewHolder.recycle();
        }
        this.mItemView = null;
        this.mTipCardLayout = null;
        super.recycle();
    }

    public void updateLayout() {
        if (ViewUtils.isVisible(this.mItemView)) {
            try {
                setItemViewLayout();
                setDecorViewLayout();
            } catch (Exception e) {
                String str = this.TAG;
                Log.se(str, "updateLayout failed: " + e);
            }
        }
    }

    public /* bridge */ /* synthetic */ void updateTipCard(boolean z, Pair pair) {
        super.updateTipCard(z, pair);
    }

    public void updateView() {
        if (!equalsItem(this.mHeaderViewHolder.getMediaItem(), this.mView.getHeaderItem())) {
            bindViewHolder();
        }
    }
}
