package com.samsung.android.gallery.app.ui.list.albums.choice.abstraction;

import A4.C0375j;
import D3.i;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceBaseAdapter<V extends IAlbumChoiceBaseView> extends AlbumsBaseViewAdapter<V> {
    private final HashSet<AlbumType> mDisabledAlbumType = new HashSet<>();
    private final HashSet<Integer> mDisabledList = new HashSet<>();
    private boolean mSupportDisabledAlbumType;

    public AlbumChoiceBaseAdapter(V v, String str) {
        super(v, str);
        initDisabledList(str);
        initDisabledAlbumType(str);
    }

    private void initDisabledAlbumType(String str) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            String argValue = ArgumentsUtil.getArgValue(str, "disabledAlbumType", (String) null);
            if (argValue != null) {
                this.mDisabledAlbumType.addAll((Collection) Arrays.stream(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).map(new i(15)).filter(new C0375j(20)).collect(Collectors.toList()));
                Log.d(this.TAG, "AlbumChoiceBaseAdapter(AlbumType)", argValue);
            }
            this.mSupportDisabledAlbumType = !this.mDisabledAlbumType.isEmpty();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initDisabledAlbumType$0(AlbumType albumType) {
        if (albumType != AlbumType.None) {
            return true;
        }
        return false;
    }

    public int getGridSize() {
        if (isGridLayout()) {
            return super.getGridSize();
        }
        return 1;
    }

    public int getItemCount() {
        if (supportHeader()) {
            return this.mDataCount + 1;
        }
        return super.getItemCount();
    }

    public int getItemHeight(int i2) {
        return this.mItemHeight;
    }

    public int getMaxScroll() {
        int i2;
        int gridSize = getGridSize();
        int itemCount = getItemCount();
        int i7 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return getItemHeight(0) * (i7 + i2);
    }

    public int getMediaItemPosition(int i2) {
        if (supportHeader()) {
            return i2 - 1;
        }
        return super.getMediaItemPosition(i2);
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (mediaItem == null || !isDisabledAlbum(mediaItem)) {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        } else {
            Log.w((CharSequence) this.TAG, "handleItemClick skip", Integer.valueOf(mediaItem.getAlbumID()));
        }
    }

    public final boolean isDisabledAlbum(MediaItem mediaItem) {
        if (!this.mSupportDisabledAlbumType || !this.mDisabledAlbumType.contains(mediaItem.getAlbumType())) {
            return false;
        }
        return true;
    }

    public boolean isGridLayout() {
        if (((IAlbumChoiceBaseView) this.mView).getLayoutManager().getSpanCount() > 1) {
            return true;
        }
        return false;
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        listViewHolder.setOnItemLongClickListener((ListViewHolder.OnItemLongClickListener) null);
        listViewHolder.setOnSecondaryClickListener((ListViewHolder.OnItemSecondaryClickListener) null);
    }

    public boolean supportHover() {
        return false;
    }

    private void initDisabledList(String str) {
    }
}
