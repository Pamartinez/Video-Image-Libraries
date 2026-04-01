package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemHeaderAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.people.ISuggestedCreatureSelectView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedCreatureSelectItemAdapter<V extends ISuggestedCreatureSelectView> extends CategoryItemHeaderAdapter<V> {
    public SuggestedCreatureSelectItemAdapter(V v, String str, View view, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, view, galleryListView, categoryPropertyHelper, z);
    }

    private void updateVisualCue(ListViewHolder listViewHolder) {
        String str;
        boolean z;
        if (listViewHolder == null || listViewHolder.getMediaItem() == null) {
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("updateVisualCue failed");
            if (listViewHolder != null) {
                StringBuilder sb3 = new StringBuilder(" ");
                sb3.append(listViewHolder.getViewPosition());
                sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (listViewHolder.getMediaItem() != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb3.append(z);
                str = sb3.toString();
            } else {
                str = "";
            }
            sb2.append(str);
            Log.se(str2, sb2.toString());
        } else if (((ISuggestedCreatureSelectView) this.mView).getSelectedCreatures().contains(listViewHolder.getMediaItem().getSubCategory())) {
            ((ImageViewHolder) listViewHolder).drawVisualCue();
        } else {
            ((ImageViewHolder) listViewHolder).eraseVisualCue();
        }
    }

    public int getViewPosition(int i2) {
        return (supportHeader() ? 1 : 0) + this.mMediaData.findPositionBy((Object) Integer.valueOf(i2));
    }

    public boolean isDragSelectSupported() {
        return false;
    }

    public void onUpdateViewHolder(int i2) {
        updateVisualCue((ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(getViewPosition(i2)));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateVisualCue(listViewHolder);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List list) {
        if (list.contains("update_cue")) {
            updateVisualCue(listViewHolder);
        } else {
            super.onBindViewHolder(listViewHolder, i2, (List<Object>) list);
        }
    }

    public void restoreSelectionOnDataChanged(Runnable runnable, boolean z) {
    }
}
