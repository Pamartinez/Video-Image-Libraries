package com.samsung.android.gallery.app.ui.list.stories.category.category;

import T3.e;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.header.SelectionHelper;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCatViewHolder extends StoriesCatBaseViewHolder {
    protected StoriesCatItemAdapter mAdapter;
    protected GridLayoutManager mLayoutManager;
    protected GalleryListView mList;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                StoriesCatViewHolder.this.mView.internalEvent(InternalEvent.REQUEST_PREVIEW, new Object[0]);
            }
        }
    };

    public StoriesCatViewHolder(IStoriesCategoryView iStoriesCategoryView, View view, int i2) {
        super(iStoriesCategoryView, view, i2);
    }

    private GridLayoutManager createLayoutManager() {
        return new StoriesCatItemLayoutManager(this.mList.getContext(), getDefaultSpanCount(), this.mDataKey);
    }

    private void fillSelectionItems(ArrayList<MediaItem> arrayList) {
        SelectionHelper<Integer> headerSelectionHelper = this.mView.getHeaderSelectionHelper();
        if (headerSelectionHelper != null && this.mMediaData != null) {
            HashMap hashMap = new HashMap();
            if (this.mMediaData.getAllData() != null) {
                Iterator<MediaItem> it = this.mMediaData.getAllData().iterator();
                while (it.hasNext()) {
                    MediaItem next = it.next();
                    hashMap.put(Integer.valueOf(MediaItemStory.getStoryId(next)), next);
                }
            }
            for (Integer num : headerSelectionHelper.getSelected()) {
                MediaItem mediaItem = (MediaItem) hashMap.get(num);
                if (mediaItem != null) {
                    arrayList.add(mediaItem);
                }
            }
        }
    }

    private int getDefaultSpanCount() {
        return 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChangedOnUi$0(StoriesCatItemAdapter storiesCatItemAdapter) {
        updateSpanCount(storiesCatItemAdapter);
        storiesCatItemAdapter.notifyDataChanged((Runnable) null);
    }

    private void selectAll(boolean z) {
        ArrayList<MediaItem> allData;
        SelectionHelper<Integer> headerSelectionHelper = this.mView.getHeaderSelectionHelper();
        MediaData mediaData = this.mMediaData;
        if (mediaData != null && headerSelectionHelper != null && (allData = mediaData.getAllData()) != null) {
            Iterator<MediaItem> it = allData.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (z) {
                    headerSelectionHelper.add(Integer.valueOf(MediaItemStory.getStoryId(next)));
                } else {
                    headerSelectionHelper.remove(Integer.valueOf(MediaItemStory.getStoryId(next)));
                }
            }
        }
    }

    private void updateLayout() {
        ViewMarginUtils.setHorizontalPadding(this.mList, this.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.stories_category_content_side_margin));
    }

    private void updateSpanCount(StoriesCatItemAdapter storiesCatItemAdapter) {
        int i2;
        if (this.mLayoutManager != null && getDefaultSpanCount() > 1) {
            if (storiesCatItemAdapter.getDataCount() >= getDefaultSpanCount() || storiesCatItemAdapter.getDataCount() <= 0) {
                i2 = getDefaultSpanCount();
            } else {
                i2 = storiesCatItemAdapter.getDataCount();
            }
            this.mLayoutManager.setSpanCount(i2);
        }
    }

    public void bindData(MediaData mediaData, Bundle bundle) {
        super.bindData(mediaData, bundle);
        StoriesCatItemAdapter storiesCatItemAdapter = this.mAdapter;
        if (storiesCatItemAdapter == null) {
            StoriesCatItemAdapter storiesCatItemAdapter2 = new StoriesCatItemAdapter(this.mView, mediaData.getLocationKey(), this.mList);
            this.mAdapter = storiesCatItemAdapter2;
            this.mList.setAdapter(storiesCatItemAdapter2);
            GridLayoutManager createLayoutManager = createLayoutManager();
            this.mLayoutManager = createLayoutManager;
            this.mList.setLayoutManager(createLayoutManager);
            this.mList.addOnScrollListener(this.mScrollListener);
        } else {
            storiesCatItemAdapter.notifyDataChanged((Runnable) null);
        }
        updateSpanCount(this.mAdapter);
        updateLayout();
    }

    public void bindView(View view) {
        super.bindView(view);
        GalleryListView galleryListView = (GalleryListView) view.findViewById(R.id.my_recycler_view);
        this.mList = galleryListView;
        galleryListView.disableSelectMode(true);
    }

    public void clear() {
        super.clear();
        StoriesCatItemAdapter storiesCatItemAdapter = this.mAdapter;
        if (storiesCatItemAdapter != null) {
            storiesCatItemAdapter.destroy();
            this.mAdapter = null;
        }
    }

    public RecyclerView getListView() {
        return this.mList;
    }

    public ArrayList<PreviewViewHolder> getPlayableVH() {
        int findLastVisibleItemPositionCompat = this.mList.findLastVisibleItemPositionCompat();
        ArrayList<PreviewViewHolder> arrayList = new ArrayList<>();
        for (int findFirstVisibleItemPositionCompat = this.mList.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) this.mList.findViewHolderForAdapterPosition(findFirstVisibleItemPositionCompat);
            if ((listViewHolder instanceof PreviewViewHolder) && StoryHelper.isVideoItem(listViewHolder.getMediaItem()) && ViewUtils.isInVisibleRange(listViewHolder.itemView, this.mView.getListView(), 0.65f)) {
                arrayList.add((PreviewViewHolder) listViewHolder);
            }
        }
        return arrayList;
    }

    public void invalidateLayout() {
        Optional.ofNullable(this.mAdapter).ifPresent(new e(29));
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        Optional.ofNullable(this.mAdapter).ifPresent(new b(this, 1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onHandleInternalEvent(com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r4, java.lang.Object... r5) {
        /*
            r3 = this;
            boolean r0 = r3.isCreationCat()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0012
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r0 = com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent.ON_DEMAND_STORY_SAVED
            if (r4 != r0) goto L_0x0012
            com.samsung.android.gallery.widget.listview.GalleryListView r3 = r3.mList
            r3.scrollToPosition(r2)
            return r1
        L_0x0012:
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r0 = com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent.SELECT_MODE_CHANGE
            if (r4 != r0) goto L_0x0045
            android.view.View r4 = r3.mHeader
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView r5 = r3.mView
            boolean r5 = r5.isSelectionMode()
            r5 = r5 ^ r1
            com.samsung.android.gallery.widget.utils.ViewUtils.setViewEnabledWithoutAlphaChange(r4, r5)
            android.widget.ImageView r4 = r3.mArrow
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView r5 = r3.mView
            boolean r5 = r5.isSelectionMode()
            if (r5 == 0) goto L_0x0030
            r5 = 1055286886(0x3ee66666, float:0.45)
            goto L_0x0032
        L_0x0030:
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x0032:
            com.samsung.android.gallery.widget.utils.ViewUtils.setAlpha(r4, r5)
            com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter r3 = r3.mAdapter
            java.util.Optional r3 = java.util.Optional.ofNullable(r3)
            com.samsung.android.gallery.app.ui.list.stories.category.category.d r4 = new com.samsung.android.gallery.app.ui.list.stories.category.category.d
            r5 = 2
            r4.<init>(r5)
            r3.ifPresent(r4)
            return r1
        L_0x0045:
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r0 = com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent.SELECT_ITEM
            if (r4 != r0) goto L_0x0066
            if (r5 == 0) goto L_0x0056
            int r4 = r5.length
            if (r4 <= 0) goto L_0x0056
            r4 = r5[r2]
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r2 = r4.intValue()
        L_0x0056:
            com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter r3 = r3.mAdapter
            java.util.Optional r3 = java.util.Optional.ofNullable(r3)
            com.samsung.android.gallery.app.ui.list.stories.category.category.c r4 = new com.samsung.android.gallery.app.ui.list.stories.category.category.c
            r5 = 1
            r4.<init>(r2, r5)
            r3.ifPresent(r4)
            return r1
        L_0x0066:
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r0 = com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent.SELECTED_ITEMS
            if (r4 != r0) goto L_0x007d
            if (r5 == 0) goto L_0x0074
            int r4 = r5.length
            if (r4 <= 0) goto L_0x0074
            r4 = r5[r2]
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            goto L_0x0079
        L_0x0074:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x0079:
            r3.fillSelectionItems(r4)
            return r1
        L_0x007d:
            com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent r0 = com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent.SELECT_ALL
            if (r4 != r0) goto L_0x00a4
            if (r5 == 0) goto L_0x0091
            int r4 = r5.length
            if (r4 <= 0) goto L_0x0091
            r4 = r5[r2]
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0091
            r2 = r1
        L_0x0091:
            r3.selectAll(r2)
            com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter r3 = r3.mAdapter
            java.util.Optional r3 = java.util.Optional.ofNullable(r3)
            com.samsung.android.gallery.app.ui.list.stories.category.category.d r4 = new com.samsung.android.gallery.app.ui.list.stories.category.category.d
            r5 = 3
            r4.<init>(r5)
            r3.ifPresent(r4)
            return r1
        L_0x00a4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatViewHolder.onHandleInternalEvent(com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent, java.lang.Object[]):boolean");
    }

    public void recycle() {
        super.recycle();
        clear();
    }

    public void updateBadge() {
        Optional.ofNullable(this.mAdapter).ifPresent(new d(1));
    }
}
