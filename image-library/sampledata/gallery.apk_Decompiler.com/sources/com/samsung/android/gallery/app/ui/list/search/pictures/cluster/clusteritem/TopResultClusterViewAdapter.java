package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.IClusterResult;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TopResultClusterViewAdapter<V extends IClusterResult> extends ClusterResultViewAdapter<V> {
    public TopResultClusterViewAdapter(V v, EventContext eventContext) {
        super(v, eventContext);
    }

    public List<Long> getDataIds() {
        int count = this.mMediaData.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < count; i2++) {
            MediaItem read = this.mMediaData.read(i2);
            if (read != null) {
                arrayList.add(Long.valueOf(read.getFileId()));
            }
        }
        return arrayList;
    }

    public MediaItem getMediaItemSync(int i2) {
        return loadMediaItemSync(i2);
    }

    public boolean onCheckBoxClicked(ListViewHolder listViewHolder, int i2) {
        GalleryListAdapter.SelectableType isItemSelectable = isItemSelectable(i2);
        if (isItemSelectable == GalleryListAdapter.SelectableType.SUPPORT) {
            super.onCheckBoxClicked(listViewHolder, i2);
            return true;
        }
        showToastForSelectionError(isItemSelectable);
        listViewHolder.setChecked(false);
        return false;
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mView.onListItemLongClick(listViewHolder, i2, mediaItem) || super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7)) {
            return true;
        }
        return false;
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        this.mBlackBoard.postEvent(EventMessage.obtain(1044, new Object[]{pointF, mediaItem}));
    }

    public boolean selectAll() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null) {
            return false;
        }
        selectionManager.selectAll(supportHeader(), hasFooter());
        notifySelectedItemChanged();
        return true;
    }

    public void sendItemSelectedSyncEvent(boolean z, int i2) {
        Blackboard blackboard;
        int i7;
        if (this.mView.isViewActive() && (blackboard = this.mBlackBoard) != null) {
            if (z) {
                i7 = ErrorCodeConvertor.TEMP_ERROR_SOCIAL_SERVICE_TERMINATED;
            } else {
                i7 = ErrorCodeConvertor.TEMP_ERROR_SOCIAL_DISCLAIMER_AGREEMENT_NEEDED;
            }
            blackboard.postEvent(EventMessage.obtain(i7, getMediaItemFromCache(i2)));
        }
    }

    public void setDragSelection(boolean z) {
        super.setDragSelection(z);
        this.mBlackBoard.publish("data://dragging_selection_on_sub_list", Boolean.valueOf(z));
    }

    public void setSelectionModeByLongPress(boolean z) {
        super.setSelectionModeByLongPress(z);
        if (z) {
            this.mBlackBoard.postEvent(EventMessage.obtain(1040));
        }
    }

    public void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder) {
        AdvancedMouseFocusManager advancedMouseFocusManager;
        this.mBlackBoard.postEvent(EventMessage.obtain(1045, listViewHolder));
        if (!((Boolean) this.mBlackBoard.read("data://dragging_selection", Boolean.FALSE)).booleanValue() && (advancedMouseFocusManager = getAdvancedMouseFocusManager()) != null && !advancedMouseFocusManager.containsViewPosition(i2)) {
            advancedMouseFocusManager.clearViewPosition();
            advancedMouseFocusManager.addViewPosition(i2);
            notifyAdvancedMouseFocusedItemChanged();
        }
    }

    public boolean startSelect(int i2) {
        boolean startSelect = super.startSelect(i2);
        if (startSelect) {
            this.mBlackBoard.postEvent(EventMessage.obtain(1046, Integer.valueOf(i2)));
        }
        return startSelect;
    }

    public boolean supportLongPressed() {
        return true;
    }

    public void removeRemainedDataFromClipboard(LinkedHashSet<Long> linkedHashSet) {
    }
}
