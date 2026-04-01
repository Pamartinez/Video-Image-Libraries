package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A4.C0366a;
import A4.C0369d;
import A4.L;
import B6.a;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesHeaderView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryHeaderViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeader {
    private MediaItem mBasisItem;
    private HeaderSimpleData mData;
    private final List<StoryHeaderBasic> mSubItems = new ArrayList();
    private final IStoryPicturesHeaderView mView;
    private final IViewCache mViewCache;
    private final StoryHeaderViewHolder mViewHolder;

    public StoryHeader(IStoryPicturesHeaderView iStoryPicturesHeaderView, IViewCache iViewCache) {
        this.mViewCache = iViewCache;
        this.mView = iStoryPicturesHeaderView;
        this.mViewHolder = new StoryHeaderViewHolder(iViewCache.loadOrCreate(R.layout.recycler_item_story_pictures_header_layout), 0);
    }

    private void cache(IViewCache iViewCache) {
        this.mViewHolder.recycleToBackup();
        this.mViewHolder.recycle();
        iViewCache.restore(R.layout.recycler_item_story_pictures_header_layout, this.mViewHolder.getRootView());
    }

    private List<StoryHeaderBasic> createSubItems(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        getStoryPicturesSubItems().collect(arrayList, mediaItem);
        return arrayList;
    }

    private StoryHeaderBasic getStoryPicturesSubItems() {
        return (StoryHeaderBasic) new ChainBuilder().append(new StoryHeaderTitle()).append(new StoryHeaderPeople()).append(new StoryHeaderSummary()).append(new StoryHeaderMapView()).build();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHeaderItems$1(StoryHeaderBasic storyHeaderBasic) {
        storyHeaderBasic.init(this.mView, getView(), this.mBasisItem, this.mData);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHeaderItems$2(StoryHeaderBasic storyHeaderBasic) {
        storyHeaderBasic.loadData(this.mBasisItem);
    }

    private void loadHeaderItems() {
        if (this.mSubItems.isEmpty()) {
            this.mData = new HeaderSimpleData(this.mView);
            this.mSubItems.addAll(createSubItems(this.mBasisItem));
            this.mSubItems.forEach(new a(this, 0));
        }
        this.mSubItems.forEach(new a(this, 1));
    }

    public void bind(MediaItem mediaItem) {
        this.mBasisItem = mediaItem;
        this.mViewHolder.bind(mediaItem);
        loadHeaderItems();
        Log.d("StoryHeader", "bind", "StoryType=" + MediaItemStory.getStoryType(mediaItem), MediaItemUtil.getSimpleLog(mediaItem));
    }

    public void destroy() {
        cache(this.mViewCache);
        this.mSubItems.forEach(new C0366a(25));
        HeaderSimpleData headerSimpleData = this.mData;
        if (headerSimpleData != null) {
            headerSimpleData.destroy();
        }
    }

    public MediaItem getHeaderItem() {
        return this.mBasisItem;
    }

    public ViewGroup getView() {
        return (ViewGroup) this.mViewHolder.getRootView();
    }

    public void handleOrientationChange(int i2) {
        this.mSubItems.forEach(new C0369d(i2, 1));
    }

    public void pause() {
        this.mSubItems.forEach(new C0366a(27));
    }

    public void resume() {
        updateLocationAuthState();
        this.mSubItems.forEach(new C0366a(26));
    }

    public void setEnableHeader(float f, boolean z) {
        this.mViewHolder.setEnabledHeader(f);
        this.mSubItems.forEach(new L(z, 2));
    }

    public void setLayoutAnimationDone() {
        this.mSubItems.forEach(new C0366a(24));
    }

    public void updateHeaderItems() {
        loadHeaderItems();
    }

    public void updateLocationAuthState() {
        this.mViewHolder.updateLocationAuthState();
    }
}
