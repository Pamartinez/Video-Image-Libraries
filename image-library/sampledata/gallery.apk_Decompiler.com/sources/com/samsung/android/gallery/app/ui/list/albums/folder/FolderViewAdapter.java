package com.samsung.android.gallery.app.ui.list.albums.folder;

import D7.g;
import android.content.Context;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragAdapter;
import com.samsung.android.gallery.app.ui.list.albums.folder.IFolderView;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountReorderHolder;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderViewAdapter<V extends IFolderView> extends AlbumsDragAdapter<V> {
    private final HashSet<Integer> mAddAlbums = new HashSet<>();
    private boolean mAlbumAddAnimationPrepared;
    private boolean mIsCustomOrder = SortByType.isSortByCustom();
    private final boolean mSupportReorder = Features.isEnabled(Features.SUPPORT_REORDER);

    public FolderViewAdapter(V v, String str) {
        super(v, str);
    }

    private boolean handleAlbumAddAnimation() {
        Iterator<MediaItem> it = saveDataToList().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (this.mAddAlbums.contains(Integer.valueOf(it.next().getAlbumID()))) {
                notifyItemInserted(i2);
            }
            i2++;
        }
        this.mGalleryListView.setPreparingItemAnimation(false);
        this.mGalleryListView.requestLayout();
        return true;
    }

    private void resetAnimationData() {
        this.mAlbumAddAnimationPrepared = false;
        this.mAddAlbums.clear();
    }

    private ArrayList<MediaItem> saveDataToList() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
            arrayList.add(this.mMediaData.read(i2));
        }
        return arrayList;
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new FolderViewHolderFactory(context);
    }

    public boolean handleDataUpdateAnimation() {
        boolean z;
        if (!this.mAlbumAddAnimationPrepared || !handleAlbumAddAnimation()) {
            z = false;
        } else {
            z = true;
        }
        resetAnimationData();
        if (z) {
            ThreadUtil.postOnUiThreadDelayed(new g(21, this), 500);
        }
        if (z || super.handleDataUpdateAnimation()) {
            return true;
        }
        return false;
    }

    public boolean isDragSelectSupported() {
        return !this.mSupportReorder;
    }

    public boolean isReorderSupported() {
        return this.mSupportReorder;
    }

    public void onDataRangeInserted(int i2, int i7) {
        this.mGalleryListView.setPreparingItemAnimation(false);
        super.onDataRangeInserted(i2, i7);
    }

    public void onPrepareAlbumAddition(int[] iArr) {
        this.mAddAlbums.clear();
        for (int valueOf : iArr) {
            this.mAddAlbums.add(Integer.valueOf(valueOf));
        }
        this.mAlbumAddAnimationPrepared = true;
        this.mGalleryListView.setPreparingItemAnimation(true);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (listViewHolder.getViewType() == 1) {
            ((AlbumTitleCountReorderHolder) listViewHolder).setIsCustomOrder(this.mIsCustomOrder);
        }
    }
}
