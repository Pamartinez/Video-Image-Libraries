package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.ISharingPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingPicturesViewAdapter<V extends ISharingPicturesView> extends PicturesHeaderViewAdapter<V> {
    private final MediaData.OnDataChangeListener mGroupDataChanged = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new f(3, SharingPicturesViewAdapter.this));
        }
    };
    private MediaData mGroupMediaData;
    private final int mHeaderViewTopPadding;

    public SharingPicturesViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        openGroupMediaData();
        this.mHeaderViewTopPadding = getContext().getResources().getDimensionPixelOffset(R.dimen.album_pictures_side_spacing);
    }

    private void closeGroupMediaData() {
        MediaData mediaData = this.mGroupMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mGroupDataChanged);
            this.mGroupMediaData.close();
        }
    }

    private MediaItem getGroupItem() {
        if (this.mGroupMediaData.getCount() <= 0 || this.mGroupMediaData.getAllData() == null) {
            return null;
        }
        return this.mGroupMediaData.getAllData().stream().filter(new p(ArgumentsUtil.getArgValue(getLocationKey(), "groupId"))).findFirst().orElse((Object) null);
    }

    private void openGroupMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackBoard).open("location://sharing/groups");
        this.mGroupMediaData = open;
        open.register(this.mGroupDataChanged);
    }

    private void setBrokenBitmaps(ListViewHolder listViewHolder) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null || thumbnailRequestHolder.getPosition() == -1) {
            String str = this.TAG;
            Log.she(str, "Fatal : no media item or invalid view position to request Thumb : " + listViewHolder);
            return;
        }
        listViewHolder.setThumbKind(getThumbnailKind());
        mediaItem.setBroken(true);
        thumbnailRequestHolder.setResult(ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem)));
        this.mFgHandler.sendThumbLoadDoneMessage(thumbnailRequestHolder);
    }

    /* access modifiers changed from: private */
    public void updateGroup(MediaItem mediaItem) {
        ((ISharingPicturesView) this.mView).updateGroup(mediaItem);
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        super.attachHeaderViewToHolder(listViewHolder);
        if (ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            ViewMarginUtils.setTopPadding(listViewHolder.getRootView(), this.mHeaderViewTopPadding);
        }
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new SharingPicturesViewHolderFactory(context);
    }

    public void destroy() {
        closeGroupMediaData();
        super.destroy();
    }

    public void forceReloadData() {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null) {
            ((ISharingPicturesView) iBaseListView).getMediaData(getLocationKey()).reopen(getLocationKey());
        }
    }

    public int getHeaderViewHeight() {
        return super.getHeaderViewHeight() + this.mHeaderViewTopPadding;
    }

    public void onDataRangeInserted(int i2, int i7) {
        super.onDataRangeInserted(i2, i7);
        String str = this.TAG;
        Log.sh(str, "onDataRangeInserted : from=" + i2 + ", count=" + i7);
        if (isSelectionMode()) {
            this.mSelectionManager.updateTotalCount(getItemCount());
        }
    }

    public void onGroupDataChangedOnUi() {
        Optional.ofNullable(getGroupItem()).ifPresent(new c(3, this));
    }

    public void onGroupMemberDataChangedOnUi() {
        forceReloadData();
    }

    public boolean preBindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        if (!mediaItem.isSharing() || !TextUtils.isEmpty(mediaItem.getPath())) {
            return super.preBindImageOnScrollIdle(listViewHolder, mediaItem);
        }
        listViewHolder.bindImage((Bitmap) null);
        return true;
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem == null || mediaItem.getPath() != null) {
            super.requestThumbnail(listViewHolder, i2);
        } else {
            setBrokenBitmaps(listViewHolder);
        }
    }

    public boolean supportVideoPreview() {
        return false;
    }
}
