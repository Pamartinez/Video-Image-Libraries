package com.samsung.android.gallery.widget.dragdrop;

import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DnDAnimation {
    void clear();

    void onDrop(boolean z);

    void onMove(int i2, int i7);

    void onPrepare(ViewGroup viewGroup, GalleryListView galleryListView, ListViewHolder listViewHolder, BooleanSupplier booleanSupplier);

    void onStarted(int i2, int i7);

    void setIsDragging(boolean z);
}
