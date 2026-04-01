package com.samsung.android.gallery.app.ui.list.dragdrop;

import S3.d;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsDragViewHolderFinder {
    private ListViewHolder findValidViewHolder(ListViewHolder listViewHolder) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem == null) {
            return null;
        }
        if (mediaItem.isFolder() && listViewHolder.getDecoView(23) != null) {
            return findValidViewHolderInFolder(listViewHolder);
        }
        if (!mediaItem.isEmptyAlbum()) {
            return listViewHolder;
        }
        return null;
    }

    private ListViewHolder findValidViewHolderInFolder(ListViewHolder listViewHolder) {
        ListViewHolder[] folderImageHolders = listViewHolder.getFolderImageHolders();
        if (folderImageHolders != null) {
            return (ListViewHolder) Arrays.stream(folderImageHolders).filter(new d(10)).findFirst().orElse((Object) null);
        }
        return null;
    }

    private ListViewHolder findViewHolderFromSelectedItem(GalleryListView galleryListView) {
        ListViewHolder findValidViewHolder;
        ArrayList<Integer> selectedViewPositions = getSelectedViewPositions(galleryListView);
        if (selectedViewPositions == null) {
            return null;
        }
        Iterator<Integer> it = selectedViewPositions.iterator();
        while (it.hasNext()) {
            ListViewHolder listViewHolder = (ListViewHolder) galleryListView.findViewHolderForAdapterPosition(it.next().intValue());
            if (listViewHolder != null && (findValidViewHolder = findValidViewHolder(listViewHolder)) != null) {
                return findValidViewHolder;
            }
        }
        return null;
    }

    private ArrayList<Integer> getSelectedViewPositions(GalleryListView galleryListView) {
        if (galleryListView.getAdapter() == null || galleryListView.getAdapter().getAdvancedMouseFocusManager() == null || galleryListView.isSelectionMode()) {
            return galleryListView.getSelectedItemList();
        }
        if (galleryListView.getAdapter().getAdvancedMouseFocusManager().getTotalCount() <= 0) {
            return null;
        }
        galleryListView.getAdapter().getAdvancedMouseFocusManager().getSelectedList();
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$findValidViewHolderInFolder$0(ListViewHolder listViewHolder) {
        if (listViewHolder == null || listViewHolder.getMediaItem() == null || listViewHolder.getMediaItem().isEmptyAlbum()) {
            return false;
        }
        return true;
    }

    public ListViewHolder find(GalleryListView galleryListView, ListViewHolder listViewHolder) {
        ListViewHolder findValidViewHolder = findValidViewHolder(listViewHolder);
        if (findValidViewHolder == null) {
            return findViewHolderFromSelectedItem(galleryListView);
        }
        return findValidViewHolder;
    }
}
