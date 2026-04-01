package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import v5.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResult2Adapter<V extends ISearchPicturesView> extends SearchPicturesAdapter<V> {
    private final ArrayList<MediaItem> mSelectedItemsList = new ArrayList<>();
    private final ArrayList<MediaItem> mUnselectedItemsList = new ArrayList<>();

    public SearchClusterResult2Adapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    private long getDateTimeMillis(String[] strArr) {
        return getTimeOfDay(TimeUtil.getDateTimeMillis(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]) - 1, Integer.parseInt(strArr[2]), 0, 0, 0));
    }

    private long getTimeOfDay(long j2) {
        return (j2 / 1000) * 1000;
    }

    private boolean isKeywordSearch() {
        return "key_word".equals(ArgumentsUtil.getArgValue(getLocationKey(), "term"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$setSelectionModeByLongPress$0() {
        return Boolean.valueOf(((ISearchPicturesView) this.mView).isAutoDragPossible());
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new SearchClusterResultViewHolderFactory(context, isKeywordSearch());
    }

    public Pair<Long, Long> getDateRange(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return super.getDateRange(mediaItem);
        }
        String isoLocalDate = TimeUtil.getIsoLocalDate(mediaItem.getDateTaken());
        return new Pair<>(Long.valueOf(getDateTimeMillis(isoLocalDate.split("-"))), Long.valueOf(getDateTimeMillis(isoLocalDate.split("-")) + 86399999));
    }

    public List<GalleryListView> getSelectableHeaderRecyclerListView() {
        if (!supportHeader() || !(((ISearchPicturesView) this.mView).getHeaderView() instanceof SearchClusterHeaderView)) {
            return null;
        }
        return ((SearchClusterHeaderView) ((ISearchPicturesView) this.mView).getHeaderView()).getSelectableListView();
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        if (!isGroupCheckBoxClicked()) {
            return;
        }
        if (z) {
            this.mSelectedItemsList.add(getMediaItemFromCache(i2));
        } else {
            this.mUnselectedItemsList.add(getMediaItemFromCache(i2));
        }
    }

    public void sendItemSelectedSyncEvent(boolean z, int i2) {
        Blackboard blackboard;
        int i7;
        if (!isGroupCheckBoxClicked() && (blackboard = this.mBlackBoard) != null) {
            if (z) {
                i7 = 1020;
            } else {
                i7 = 1021;
            }
            blackboard.postEvent(EventMessage.obtain(i7, new ArrayList(Arrays.asList(new MediaItem[]{getMediaItemFromCache(i2)}))));
        }
    }

    public void setSelectionModeByLongPress(boolean z) {
        super.setSelectionModeByLongPress(z);
        if (z) {
            this.mBlackBoard.postEvent(EventMessage.obtain(1040));
            if (this.mBlackBoard.read("data://user/AutoDragPossible") == null) {
                this.mBlackBoard.publish("data://user/AutoDragPossible", new a(1, this));
            }
        }
    }

    public boolean syncClusterData(int i2) {
        boolean syncClusterData = super.syncClusterData(i2);
        if (this.mBlackBoard != null) {
            if (!this.mSelectedItemsList.isEmpty()) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1020, this.mSelectedItemsList.clone()));
                this.mSelectedItemsList.clear();
                return syncClusterData;
            } else if (!this.mUnselectedItemsList.isEmpty()) {
                this.mBlackBoard.postEvent(EventMessage.obtain(1021, this.mUnselectedItemsList.clone()));
                this.mUnselectedItemsList.clear();
            }
        }
        return syncClusterData;
    }
}
