package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.IHideSceneSelectionView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionViewAdapterV2<V extends IHideSceneSelectionView> extends HideSceneSelectionViewAdapter<V> {
    private int mItemHeight;

    public HideSceneSelectionViewAdapterV2(V v, String str, boolean z) {
        super(v, str, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder) {
        this.mItemHeight = listViewHolder.itemView.getHeight();
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

    public boolean isCheckBoxEnabled(ListViewHolder listViewHolder) {
        return true;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        super.onBindViewHolder(listViewHolder, i2, i7);
        listViewHolder.itemView.post(new i(10, this, listViewHolder));
        updateVisualCue(listViewHolder, i2);
    }

    public void updateSelectionItems(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        super.updateSelectionItems(listViewHolder, mediaItem, i2);
        updateVisualCue(listViewHolder, i2);
    }

    public void updateVisualCue(ListViewHolder listViewHolder, int i2) {
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
        } else if (isChecked(listViewHolder, i2)) {
            ((ImageViewHolder) listViewHolder).drawVisualCue();
        } else {
            ((ImageViewHolder) listViewHolder).eraseVisualCue();
        }
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2, int i7) {
    }
}
