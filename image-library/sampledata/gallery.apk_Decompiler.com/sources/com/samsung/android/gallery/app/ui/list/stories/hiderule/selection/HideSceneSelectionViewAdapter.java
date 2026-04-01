package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import A4.C0386v;
import B5.c;
import T8.C0578a;
import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import bd.a;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.IHideSceneSelectionView;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionViewAdapter<V extends IHideSceneSelectionView> extends PicturesViewAdapter<V> {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        private void setContentDescriptionAfterClicked(View view, int i2) {
            int selectedItemCount = ((IHideSceneSelectionView) HideSceneSelectionViewAdapter.this.mView).getSelectedItemCount();
            if (i2 == 2) {
                selectedItemCount++;
            }
            view.setContentDescription(HideSceneSelectionViewAdapter.this.getContext().getResources().getQuantityString(R.plurals.speak_item_selected_quantity_string, selectedItemCount, new Object[]{Integer.valueOf(selectedItemCount)}));
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 1 || accessibilityEvent.getEventType() == 2) {
                setContentDescriptionAfterClicked(view, accessibilityEvent.getEventType());
            }
        }
    };
    private final CopyOnWriteArrayList<Long> mDataIds = new CopyOnWriteArrayList<>();
    private final LinkedHashMap<Long, MediaItem> mUnHideItems = new LinkedHashMap<>();

    public HideSceneSelectionViewAdapter(V v, String str, boolean z) {
        super(v, str, z);
    }

    private MediaItem[] getAllItems() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return new MediaItem[0];
        }
        try {
            this.mMediaData.acquireReadLock("HideSceneSelectionViewAdapter.getAllItems");
            IntStream range = IntStream.range(0, this.mMediaData.getCount());
            MediaData mediaData2 = this.mMediaData;
            Objects.requireNonNull(mediaData2);
            return (MediaItem[]) range.mapToObj(new C0386v(4, mediaData2)).toArray(new C0578a(12));
        } finally {
            this.mMediaData.releaseReadLock("HideSceneSelectionViewAdapter.getAllItems");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreClipboard$3(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2) {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null && !((IHideSceneSelectionView) iBaseListView).isDestroyed()) {
            super.restoreClipboard(runnable, timeTickLog, linkedHashSet, linkedHashSet2);
        }
    }

    private void sendSelectLoggingEvent(boolean z) {
        String str;
        if (z) {
            str = AnalyticsDetail.EVENT_DETAIL_HIDE_SCENE_SELECT.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_HIDE_SCENE_DESELECT.toString();
        }
        ((IHideSceneSelectionView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_SCENE_SELECT, str);
    }

    private void setHideRuleIdToItem(ListViewHolder listViewHolder) {
        setHideRuleIdToItem(listViewHolder.getMediaItem());
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new ViewHolderFactory(context);
    }

    public long getDataId(MediaItem mediaItem) {
        try {
            return (long) mediaItem.getSubCategory().hashCode();
        } catch (NullPointerException e) {
            Log.e(this.TAG, e.getMessage());
            return (long) mediaItem.hashCode();
        }
    }

    public List<Long> getDataIds() {
        if (!this.mDataIds.isEmpty()) {
            return new ArrayList(this.mDataIds);
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (MediaItem dataId : getAllItems()) {
            arrayList.add(Long.valueOf(getDataId(dataId)));
        }
        this.mDataIds.addAll(arrayList);
        Log.d(this.TAG, "getDataIds", Integer.valueOf(arrayList.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    public MediaItem[] getHideItems() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> selectedList = this.mSelectionManager.getSelectedList();
        if (selectedList != null) {
            Iterator<Integer> it = selectedList.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                MediaItem mediaItemFromCache = getMediaItemFromCache(intValue);
                if (mediaItemFromCache != null && ((IHideSceneSelectionView) this.mView).getHideRuleId(mediaItemFromCache.getSubCategory()) < 0) {
                    arrayList.add(getMediaItemFromCache(intValue));
                }
            }
        }
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    public MediaItem[] getUnHideItems() {
        return (MediaItem[]) this.mUnHideItems.values().toArray(new MediaItem[0]);
    }

    public boolean isChecked(ListViewHolder listViewHolder, int i2) {
        boolean z;
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem == null) {
            return false;
        }
        if (((IHideSceneSelectionView) this.mView).getHideRuleId(mediaItem.getSubCategory()) > -1) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.mUnHideItems.containsKey(Long.valueOf(getDataId(mediaItem)))) {
            return false;
        }
        if (this.mSelectionManager.isSelected(Integer.valueOf(i2))) {
            return true;
        }
        if (z) {
            this.mSelectionManager.select(Integer.valueOf(i2), true, false);
        }
        return z;
    }

    public void onDataChanged() {
        this.mDataIds.clear();
        super.onDataChanged();
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        MediaItem mediaItem;
        super.onSelected(i2, z, z3);
        if (isData(i2)) {
            ListViewHolder viewHolderForAdapterPosition = getViewHolderForAdapterPosition(i2);
            if (viewHolderForAdapterPosition == null || viewHolderForAdapterPosition.getMediaItem() == null) {
                mediaItem = null;
            } else {
                mediaItem = viewHolderForAdapterPosition.getMediaItem();
            }
            if (mediaItem != null) {
                setHideRuleIdToItem(viewHolderForAdapterPosition);
                updateSelectionItems(viewHolderForAdapterPosition, mediaItem, i2);
                sendSelectLoggingEvent(z);
            }
        }
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        ThreadUtil.postOnBgThread(new c(this, new a(runnable, 3), timeTickLog, linkedHashSet, linkedHashSet2, 25));
    }

    public boolean selectAll() {
        boolean selectAll = super.selectAll();
        if (selectAll) {
            MediaItem[] allItems = getAllItems();
            for (int i2 = 0; i2 < allItems.length; i2++) {
                updateSelectionItems((ListViewHolder) null, allItems[i2], getViewPosition(i2));
            }
            notifySelectedItemChanged();
        }
        return selectAll;
    }

    public void unSelectAll() {
        super.unSelectAll();
        MediaItem[] allItems = getAllItems();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.mUnHideItems.clear();
        for (MediaItem mediaItem : allItems) {
            if (((IHideSceneSelectionView) this.mView).getHideRuleId(mediaItem.getSubCategory()) > -1) {
                setHideRuleIdToItem(mediaItem);
                linkedHashMap.put(Long.valueOf(getDataId(mediaItem)), mediaItem);
            }
        }
        this.mUnHideItems.putAll(linkedHashMap);
        notifySelectedItemChanged();
    }

    public void updateSelectionItems(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        if (this.mSelectionManager.isSelected(Integer.valueOf(i2))) {
            this.mUnHideItems.remove(Long.valueOf(getDataId(mediaItem)));
        } else if (HideRuleData.of(mediaItem).hideRuleId > -1 && !this.mUnHideItems.containsKey(Long.valueOf(getDataId(mediaItem)))) {
            this.mUnHideItems.put(Long.valueOf(getDataId(mediaItem)), mediaItem);
        }
    }

    private void setHideRuleIdToItem(MediaItem mediaItem) {
        if (mediaItem != null) {
            HideRuleData.of(mediaItem).hideRuleId = ((IHideSceneSelectionView) this.mView).getHideRuleId(mediaItem.getSubCategory());
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        super.onBindViewHolder(listViewHolder, i2, i7);
        listViewHolder.setChecked(isChecked(listViewHolder, i2));
        listViewHolder.itemView.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("checkBoxItem")) {
            listViewHolder.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
            updateVisualCue(listViewHolder, i2);
            return;
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }

    public void updateVisualCue(ListViewHolder listViewHolder, int i2) {
    }
}
