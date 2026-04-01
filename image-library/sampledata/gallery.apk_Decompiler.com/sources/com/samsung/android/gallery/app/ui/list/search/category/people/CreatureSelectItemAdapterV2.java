package com.samsung.android.gallery.app.ui.list.search.category.people;

import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureSelectItemAdapterV2<V extends ICreatureSelectView> extends CategoryItemAdapterV2<V> {
    public CreatureSelectItemAdapterV2(V v, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, galleryListView, categoryPropertyHelper, z);
        setHasStableIds(true);
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
        } else if (((ICreatureSelectView) this.mView).getSelectedCreatures().contains(listViewHolder.getMediaItem().getSubCategory())) {
            ((ImageViewHolder) listViewHolder).drawVisualCue();
        } else {
            ((ImageViewHolder) listViewHolder).eraseVisualCue();
        }
    }

    public long getItemId(int i2) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache != null) {
            return (long) mediaItemFromCache.getSubCategory().hashCode();
        }
        return -1;
    }

    public void onUpdateViewHolder(int i2) {
        updateVisualCue((ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(getViewPosition(i2)));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateVisualCue(listViewHolder);
    }
}
