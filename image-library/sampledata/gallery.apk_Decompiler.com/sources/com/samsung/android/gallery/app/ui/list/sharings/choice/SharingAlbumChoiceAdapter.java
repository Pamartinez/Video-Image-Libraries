package com.samsung.android.gallery.app.ui.list.sharings.choice;

import H3.l;
import K5.a;
import M4.d;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingAlbumChoiceAdapter<V extends IAlbumChoiceBaseView> extends AlbumChoiceBaseAdapter<V> {
    private final Drawable mAlbumAddIcon = getContext().getDrawable(R.drawable.album_choice_add_icon);
    private MediaData mGroupMediaData;
    private final MediaData.OnDataChangeListener mGroupMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new l(29, SharingAlbumChoiceAdapter.this));
        }
    };

    public SharingAlbumChoiceAdapter(V v, String str) {
        super(v, str);
        openMediaData();
    }

    private boolean bindGroupInfo(ListViewHolder listViewHolder, MediaItem mediaItem) {
        MediaItem readByKey;
        if (!this.mGroupMediaData.isDataAvailable() || mediaItem == null || (readByKey = this.mGroupMediaData.readByKey(MediaItemMde.getGroupId(mediaItem))) == null) {
            return true;
        }
        listViewHolder.getCountView().setText(MdeGroupHelper.getCountString(getContext(), mediaItem, readByKey.getCount()));
        return true;
    }

    private void bindHeader(ListViewHolder listViewHolder) {
        setListeners(listViewHolder);
        Optional.ofNullable(listViewHolder.getImage()).ifPresent(new a(9, this));
        listViewHolder.bind(MediaItemBuilder.create("dummy"));
        Optional.ofNullable(listViewHolder.getTitleView()).ifPresent(new d(12));
        Optional.ofNullable(listViewHolder.getCountView()).ifPresent(new d(13));
    }

    private void closeMediaData() {
        MediaData mediaData = this.mGroupMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mGroupMediaDataChangeListener);
            this.mGroupMediaData.close();
            this.mGroupMediaData = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindHeader$1(ImageView imageView) {
        imageView.setImageDrawable(this.mAlbumAddIcon);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
    }

    private void openMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackBoard).open("location://sharing/groups");
        this.mGroupMediaData = open;
        open.register(this.mGroupMediaDataChangeListener);
    }

    public void bindThumbnail(ListViewHolder listViewHolder, int i2, int i7, MediaItem mediaItem) {
        super.bindThumbnail(listViewHolder, i2, i7, mediaItem);
        bindGroupInfo(listViewHolder, mediaItem);
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        if (!super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7) || !bindGroupInfo(listViewHolder, getMediaItemSync(i2))) {
            return false;
        }
        return true;
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new SharingChoiceViewHolderFactory(context);
    }

    public void destroy() {
        closeMediaData();
        super.destroy();
    }

    public boolean isGridLayout() {
        return false;
    }

    public boolean supportHeader() {
        return true;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        ViewUtils.setVisibility(listViewHolder.getCountView(), 0);
        if (isHeader(i2)) {
            bindHeader(listViewHolder);
        } else {
            super.onBindViewHolder(listViewHolder, i2);
        }
        listViewHolder.itemView.post(new M5.a(0, this, listViewHolder));
        updateBorders(listViewHolder, this.mGalleryListView.getDepth());
    }

    public void subscribeLatestAlbumIdChanged() {
    }

    public void unSubscribeLatestAlbumIdChanged() {
    }
}
