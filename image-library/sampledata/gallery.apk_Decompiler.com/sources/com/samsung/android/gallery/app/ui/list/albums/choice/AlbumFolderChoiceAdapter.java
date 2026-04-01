package com.samsung.android.gallery.app.ui.list.albums.choice;

import A8.C0545b;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.IFolderChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderChoiceAdapter<V extends IFolderChoiceView> extends AlbumChoiceBaseAdapter<V> {
    private final MediaItem mCurrentFolder = ((MediaItem) this.mBlackBoard.read("data://current_folder"));
    private final boolean mIsPickerMode = PickerUtil.isPickerMode(this.mBlackBoard);

    public AlbumFolderChoiceAdapter(V v, String str) {
        super(v, str);
    }

    private boolean isTargetFolder(MediaItem mediaItem) {
        if (mediaItem == null || this.mCurrentFolder == null || !mediaItem.isFolder() || mediaItem.getFolderID() != this.mCurrentFolder.getFolderID()) {
            return false;
        }
        return true;
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumFolderChoiceViewHolderFactory(context);
    }

    public int getSelectableMaxCount() {
        return 0;
    }

    public int[] getSelectedAlbumIds() {
        return this.mClipBoard.cloneSet().stream().mapToInt(new C0545b(4)).toArray();
    }

    public int getSelectedItemCount() {
        return this.mClipBoard.getTotalCount();
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        MediaItem mediaItem2 = listViewHolder.getMediaItem();
        if (mediaItem2.isFolder()) {
            if (!isTargetFolder(mediaItem2)) {
                onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
            }
        } else if (!PickerUtil.isAlbumMultiPickLaunchMode(this.mBlackBoard) || !mediaItem2.isEmptyAlbum()) {
            GalleryListAdapter.SelectableType isItemSelectable = isItemSelectable(i2);
            if (GalleryListAdapter.SelectableType.SUPPORT.equals(isItemSelectable)) {
                super.handleItemClick(listViewHolder, i2, mediaItem, i7);
            } else {
                showToastForSelectionError(isItemSelectable);
                listViewHolder.setChecked(false);
            }
        } else {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        }
        ((IFolderChoiceView) this.mView).updateSelectionToolBar();
    }

    public boolean isEnableAlbum(MediaItem mediaItem) {
        if (mediaItem == null || isDisabledAlbum(mediaItem)) {
            return false;
        }
        return true;
    }

    public GalleryListAdapter.SelectableType isItemSelectable(int i2) {
        MediaItem mediaItem;
        ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i2);
        if (viewHolderForAdapterPosition != null) {
            mediaItem = viewHolderForAdapterPosition.getMediaItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null || mediaItem.isFolder()) {
            return GalleryListAdapter.SelectableType.UNSUPPORTED;
        }
        return super.isItemSelectable(i2);
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        ((IFolderChoiceView) this.mView).updateSelectionToolBar();
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        onCreateViewHolder.getRootView().setBackground((Drawable) null);
        return onCreateViewHolder;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null && !isDisabledAlbum(mediaItem)) {
            if (!this.mIsPickerMode) {
                listViewHolder.setCheckboxEnabled(95, true);
            }
            if (mediaItem.isFolder()) {
                ViewUtils.setViewEnabledWithoutAlphaChange(listViewHolder.getCheckbox(), false);
            } else {
                listViewHolder.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
            }
            listViewHolder.getRootView().setAlpha(isTargetFolder(mediaItem) ? 0.5f : 1.0f);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r1 = (com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceViewHolder) r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r1, int r2, java.util.List<java.lang.Object> r3) {
        /*
            r0 = this;
            super.onBindViewHolder((com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r1, (int) r2, (java.util.List<java.lang.Object>) r3)
            boolean r2 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.MX_ALBUMS
            if (r2 == 0) goto L_0x001a
            boolean r2 = r1 instanceof com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceViewHolder
            if (r2 == 0) goto L_0x001a
            com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceViewHolder r1 = (com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceViewHolder) r1
            com.samsung.android.gallery.module.data.MediaItem r2 = r1.getMediaItem()
            if (r2 == 0) goto L_0x001a
            boolean r0 = r0.isEnableAlbum(r2)
            r1.setEnable(r0)
        L_0x001a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceAdapter.onBindViewHolder(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, int, java.util.List):void");
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
    }
}
