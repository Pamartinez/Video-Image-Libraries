package com.samsung.android.gallery.app.ui.list.stories.category;

import O3.l;
import Qb.c;
import T3.e;
import U3.a;
import U5.b;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.EventListener;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCategory2HeaderAdapter extends GalleryListAdapter<StoriesCatBaseViewHolder> {
    private ArrayList<String> mDataKeyVerifier;
    private final ArrayList<EventListener> mEventListeners = new ArrayList<>();
    protected final MediaData mMediaData;
    private final Bundle mRestoreBundle = new Bundle();
    private final IStoriesCategoryView mView;

    public StoriesCategory2HeaderAdapter(IStoriesCategoryView iStoriesCategoryView) {
        super(iStoriesCategoryView.getBlackboard());
        this.mView = iStoriesCategoryView;
        this.mMediaData = iStoriesCategoryView.getMediaData("location://story/albums");
        this.mDataKeyVerifier = fillDataKeyVerifier();
        setHasStableIds(true);
    }

    private ArrayList<String> fillDataKeyVerifier() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < getMediaDataCount(); i2++) {
            MediaData childMediaData = this.mMediaData.getChildMediaData(i2);
            if (childMediaData != null) {
                arrayList.add(childMediaData.getLocationKey());
            }
        }
        return arrayList;
    }

    private int getMediaDataCount() {
        return this.mMediaData.getChildCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDensityChange$1(EventListener eventListener) {
        eventListener.saveState(this.mRestoreBundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidateLayout$2(Runnable runnable) {
        Optional.ofNullable(runnable).ifPresent(new l(0));
        this.mEventListeners.forEach(new e(18));
    }

    private void registerEventListener(StoriesCatBaseViewHolder storiesCatBaseViewHolder) {
        if (!this.mEventListeners.contains(storiesCatBaseViewHolder)) {
            this.mEventListeners.add(storiesCatBaseViewHolder);
        }
    }

    private void unregisterEventListener(StoriesCatBaseViewHolder storiesCatBaseViewHolder) {
        this.mEventListeners.remove(storiesCatBaseViewHolder);
    }

    public void destroy() {
        super.destroy();
        this.mEventListeners.clear();
    }

    public int getDataCount() {
        return this.mMediaData.getChildCount();
    }

    public int getItemCount() {
        return getDataCount();
    }

    public long getItemId(int i2) {
        MediaData childMediaData = this.mMediaData.getChildMediaData(i2);
        if (childMediaData != null) {
            return (long) childMediaData.getLocationKey().hashCode();
        }
        return -1;
    }

    public int getItemViewType(int i2) {
        String str;
        MediaData childMediaData = this.mMediaData.getChildMediaData(i2);
        if (childMediaData != null) {
            str = childMediaData.getLocationKey();
        } else {
            str = null;
        }
        return StoriesCatViewHolderFactory.getCategoryViewType(str);
    }

    public int getSelectableTotalItemCount() {
        int i2 = 0;
        for (int i7 = 0; i7 < this.mMediaData.getChildCount(); i7++) {
            MediaData childMediaData = this.mMediaData.getChildMediaData(i7);
            if (childMediaData != null && !"location://stories/category/transitory".equals(childMediaData.getLocationKey())) {
                i2 = childMediaData.getCount() + i2;
            }
        }
        return i2;
    }

    public void handleDensityChange() {
        this.mEventListeners.forEach(new c(27, this));
        this.mEventListeners.forEach(new e(17));
        this.mEventListeners.clear();
        notifyDataSetChanged();
    }

    public void invalidateLayout(Runnable runnable) {
        ThreadUtil.postOnUiThreadDelayed(new U5.c(0, this, runnable), 50);
    }

    public void onDataChangedOnUi() {
        ArrayList arrayList = new ArrayList(this.mDataKeyVerifier);
        this.mDataKeyVerifier = fillDataKeyVerifier();
        for (int i2 = 0; i2 < this.mDataKeyVerifier.size(); i2++) {
            if (i2 >= arrayList.size()) {
                notifyDataRangeInserted(i2, 1);
            } else if (!this.mDataKeyVerifier.get(i2).equals(arrayList.get(i2))) {
                notifyItemChanged(i2);
            }
        }
        if (arrayList.size() > this.mDataKeyVerifier.size()) {
            notifyItemRangeRemoved(this.mDataKeyVerifier.size(), arrayList.size() - this.mDataKeyVerifier.size());
        }
    }

    public void onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        this.mEventListeners.forEach(new b(0, internalEvent, objArr));
    }

    public StoriesCatBaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return StoriesCatViewHolderFactory.create(this.mView, viewGroup, i2);
    }

    public void onViewRecycled(StoriesCatBaseViewHolder storiesCatBaseViewHolder) {
        storiesCatBaseViewHolder.recycle();
        unregisterEventListener(storiesCatBaseViewHolder);
    }

    public void onBindViewHolder(StoriesCatBaseViewHolder storiesCatBaseViewHolder, int i2, List<Object> list) {
        if (list.contains("PAYLOAD_UPDATE_BADGE")) {
            storiesCatBaseViewHolder.handleEvent(Event.UPDATE_BADGE, new Object[0]);
            return;
        }
        MediaData childMediaData = this.mMediaData.getChildMediaData(i2);
        if (childMediaData != null) {
            storiesCatBaseViewHolder.bindData(childMediaData, this.mRestoreBundle);
            IStoriesCategoryView iStoriesCategoryView = this.mView;
            Objects.requireNonNull(iStoriesCategoryView);
            storiesCatBaseViewHolder.setOnItemClickListener(new a(6, iStoriesCategoryView));
            registerEventListener(storiesCatBaseViewHolder);
            return;
        }
        Log.w((CharSequence) this.TAG, "onBindViewHolder failed due to no data", Integer.valueOf(i2));
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
