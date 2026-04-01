package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResult2Presenter<V extends ISearchClusterResultView> extends SearchClusterResultPresenter<V> {
    public SearchClusterResult2Presenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private int findItemViewPositionWithSubListItem(long j2) {
        PicturesViewAdapter adapter = ((ISearchClusterResultView) this.mView).getAdapter();
        if (adapter == null) {
            return -1;
        }
        int itemCount = adapter.getItemCount();
        for (int i2 = 0; i2 < itemCount; i2++) {
            MediaItem mediaItemSync = adapter.getMediaItemSync(i2);
            if (mediaItemSync != null && mediaItemSync.getFileId() == j2) {
                return i2;
            }
        }
        return -1;
    }

    private Bundle getScsCursorExtra() {
        if (getMediaData() != null) {
            return getMediaData().getExtras().getBundle("scsCursorExtra");
        }
        return null;
    }

    private void selectItemSync(long j2, boolean z) {
        PicturesViewAdapter adapter = ((ISearchClusterResultView) this.mView).getAdapter();
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            int i2 = 0;
            while (i2 < itemCount) {
                MediaItem mediaItemSync = adapter.getMediaItemSync(i2);
                if (mediaItemSync == null || mediaItemSync.getFileId() != j2) {
                    i2++;
                } else {
                    adapter.selectItemWithSync(i2, z, true);
                    return;
                }
            }
        }
    }

    private void updateSelectedItemFromSubFragment(MediaItem mediaItem, boolean z) {
        selectItemSync(mediaItem.getFileId(), z);
        ((ISearchClusterResultView) this.mView).invalidateToolbar();
    }

    public Object getEventContextData(String str) {
        Bundle scsCursorExtra;
        if ("ocr_ids".equals(str)) {
            Bundle scsCursorExtra2 = getScsCursorExtra();
            if (scsCursorExtra2 != null) {
                return scsCursorExtra2.getString("ocr_ids");
            }
            return null;
        } else if ("ocr_keyword".equals(str)) {
            return getOcrKeyword();
        } else {
            if (!"frame_id_map".equals(str) || (scsCursorExtra = getScsCursorExtra()) == null) {
                return null;
            }
            return scsCursorExtra.getSerializable("frame_id_map");
        }
    }

    public boolean handleEvent(EventMessage eventMessage) {
        switch (eventMessage.what) {
            case ErrorCodeConvertor.TEMP_ERROR_SOCIAL_SERVICE_TERMINATED /*1038*/:
                updateSelectedItemFromSubFragment((MediaItem) eventMessage.obj, true);
                return true;
            case ErrorCodeConvertor.TEMP_ERROR_SOCIAL_DISCLAIMER_AGREEMENT_NEEDED /*1039*/:
                updateSelectedItemFromSubFragment((MediaItem) eventMessage.obj, false);
                return true;
            case 1040:
                if (!isSelectionMode()) {
                    ((ISearchClusterResultView) this.mView).enterSelectionMode(0);
                }
                return true;
            case 1043:
                Object[] objArr = (Object[]) eventMessage.obj;
                ((ISearchClusterResultView) this.mView).onStartDragViaItemLongClickedEvent((ListViewHolder) objArr[0], (MediaItem) objArr[1]);
                return true;
            case 1045:
                ListViewHolder listViewHolder = (ListViewHolder) eventMessage.obj;
                ((ISearchClusterResultView) this.mView).startDragAndDropOnAdvancedMouseAction(findItemViewPositionWithSubListItem(listViewHolder.getMediaItem().getFileId()), listViewHolder);
                return true;
            case 1046:
                ((ISearchClusterResultView) this.mView).getListView().enterSelectionMode(((Integer) eventMessage.obj).intValue());
                return true;
            default:
                return super.handleEvent(eventMessage);
        }
    }

    public void updateSelectedItem(EventMessage eventMessage) {
        Object obj = eventMessage.obj;
        if (obj != null) {
            MediaItem mediaItem = (MediaItem) obj;
            boolean z = true;
            if (eventMessage.arg1 != 1) {
                z = false;
            }
            selectItemSync(mediaItem.getFileId(), z);
            return;
        }
        super.updateSelectedItem(eventMessage);
    }
}
