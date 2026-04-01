package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import Ad.C0720a;
import C4.i;
import Fa.C0571z;
import T8.C0578a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.CollagePageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.MapPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.OnDemandPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.RelatedPageItem;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import db.a;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import n0.C0235b;
import o6.B;
import o6.p;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageDataLoader {
    private CollageItemPicker mCollagePicker;
    private final ArrayList<PageItem> mItems = new ArrayList<>();
    private RelatedDataHolder mRelatedDataHolder = new RelatedDataHolder();
    protected final IStoryHighlightView mView;

    public PageDataLoader(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    private RelatedDataHolder cloneRelatedData(RelatedDataHolder relatedDataHolder) {
        return relatedDataHolder.cloneData();
    }

    private boolean fromFavorite() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "fromStoryFavorite", false);
    }

    private CollageInfo getCollageInfo() {
        Iterator<PageItem> it = this.mItems.iterator();
        while (it.hasNext()) {
            PageItem next = it.next();
            if (next.isCollage()) {
                return ((CollagePageItem) next).getCollageInfo();
            }
        }
        return null;
    }

    private ArrayList<MediaItem> getNewTransitoryStories() {
        ArrayList<MediaItem> arrayList;
        MediaData storiesData = this.mView.getStoriesData();
        if (storiesData != null) {
            arrayList = storiesData.getAllData();
        } else {
            arrayList = new ArrayList<>();
        }
        ArrayList<MediaItem> arrayList2 = (ArrayList) arrayList.stream().filter(new C0464a(27)).limit(2).collect(Collectors.toCollection(new C0720a(1)));
        if (!arrayList2.isEmpty()) {
            return arrayList2;
        }
        return new ArrayList<>();
    }

    /* access modifiers changed from: private */
    public MediaItem getStoryAlbumById(int i2) {
        if (!this.mView.getOptions().isFromTransitoryStory()) {
            return (MediaItem) this.mView.requestData(DataRequest.FRAGMENT_REQ_STORY_ALBUM_BY_ID, Integer.valueOf(i2));
        }
        MediaData find = MediaDataFactory.getInstance(this.mView.getBlackboard()).find("location://story/albums");
        if (find != null) {
            return find.readById((long) i2);
        }
        return null;
    }

    private boolean isMapAvailable() {
        String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "categoryKey");
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.TripStoryMap) || argValue == null || !argValue.startsWith("location://stories/category/trip")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getNewTransitoryStories$8(MediaItem mediaItem) {
        if (!StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem)) || MediaItemStory.isRecap(mediaItem)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[]] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$loadCollageItem$4(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[] r1, int r2, java.util.concurrent.CountDownLatch r3, com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo r4) {
        /*
            if (r4 == 0) goto L_0x0020
            boolean r0 = r4.valid()
            if (r0 != 0) goto L_0x0009
            goto L_0x0020
        L_0x0009:
            boolean r0 = r4.isGridType()
            if (r0 == 0) goto L_0x0015
            com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.GridCollageItem r0 = new com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.GridCollageItem
            r0.<init>()
            goto L_0x001a
        L_0x0015:
            com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.IrregularCollageItem r0 = new com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.IrregularCollageItem
            r0.<init>()
        L_0x001a:
            r0.setCollageInfo(r4)
            r1[r2] = r0
            goto L_0x0023
        L_0x0020:
            r4 = 0
            r1[r2] = r4
        L_0x0023:
            r3.countDown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader.lambda$loadCollageItem$4(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[], int, java.util.concurrent.CountDownLatch, com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$1(List list, Consumer consumer) {
        swap(list);
        Optional.ofNullable(consumer).ifPresent(new B(22));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$2(Consumer consumer) {
        PageItem[] pageItemArr;
        long currentTimeMillis = System.currentTimeMillis();
        boolean isOnDemandStory = this.mView.getOptions().isOnDemandStory();
        if (isOnDemandStory) {
            pageItemArr = loadOnDemandData();
        } else {
            pageItemArr = loadData();
        }
        List list = (List) Stream.of(pageItemArr).filter(new C0464a(25)).collect(Collectors.toList());
        Log.d("PageDataLoader", "loadData" + Logger.vt(Boolean.valueOf(isOnDemandStory), Integer.valueOf(pageItemArr.length), Integer.valueOf(list.size()), Long.valueOf(currentTimeMillis)));
        ThreadUtil.runOnUiThread(new C0235b(this, list, consumer, 11));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$loadMapItem$6(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadRelatedItem$7(PageItem[] pageItemArr, int i2, CountDownLatch countDownLatch, RelatedDataHolder relatedDataHolder) {
        if (relatedDataHolder.isValid()) {
            pageItemArr[i2] = new RelatedPageItem().setRelatedData(relatedDataHolder);
        } else {
            pageItemArr[i2] = null;
        }
        swapRelatedData(relatedDataHolder);
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swapRelatedData$3(RelatedDataHolder relatedDataHolder) {
        this.mRelatedDataHolder = relatedDataHolder;
    }

    private void loadCollageItem(PageItem[] pageItemArr, int i2, CountDownLatch countDownLatch) {
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            if (this.mCollagePicker == null) {
                this.mCollagePicker = new CollageItemPicker(this.mView);
            }
            this.mCollagePicker.pick(getCollageInfo(), headerItem, new i((Object) pageItemArr, i2, (Object) countDownLatch, 7));
            return;
        }
        pageItemArr[i2] = null;
        countDownLatch.countDown();
    }

    private void loadMapItem(PageItem[] pageItemArr, int i2, CountDownLatch countDownLatch) {
        ArrayList<MediaItem> arrayList;
        if (this.mView.getMediaData() != null) {
            arrayList = this.mView.getMediaData().getAllData();
        } else {
            arrayList = null;
        }
        if (arrayList == null) {
            countDownLatch.countDown();
            return;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) arrayList.stream().filter(new C0571z(4)).filter(new C0464a(26)).toArray(new C0578a(28));
        if (mediaItemArr.length > 0) {
            pageItemArr[i2] = new MapPageItem().setItems(mediaItemArr);
            countDownLatch.countDown();
            return;
        }
        pageItemArr[i2] = null;
        countDownLatch.countDown();
    }

    private PageItem[] loadOnDemandData() {
        return new PageItem[]{new OnDemandPageItem().setMediaItem(this.mView.getHeaderItem())};
    }

    private void loadRelatedItem(PageItem[] pageItemArr, int i2, CountDownLatch countDownLatch, RelatedDataHolder relatedDataHolder) {
        if (this.mView.getOptions().isOnDemandStory()) {
            countDownLatch.countDown();
            return;
        }
        if (this.mView.getOptions().isFromTransitoryStory()) {
            ArrayList<MediaItem> newTransitoryStories = getNewTransitoryStories();
            if (!newTransitoryStories.isEmpty()) {
                RelatedDataHolder relatedDataHolder2 = new RelatedDataHolder(newTransitoryStories);
                pageItemArr[i2] = new RelatedPageItem().setRelatedData(relatedDataHolder2);
                swapRelatedData(relatedDataHolder2);
                countDownLatch.countDown();
                return;
            } else if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
                countDownLatch.countDown();
                return;
            }
        }
        new RelatedStoryLoader.Requester(this.mView.getHeaderItem(), relatedDataHolder, new p(7, this)).setFavoriteOnly(fromFavorite()).request(new a(this, pageItemArr, i2, countDownLatch));
    }

    private void loadSummaryItem(PageItem[] pageItemArr, int i2, CountDownLatch countDownLatch) {
        if (isMapAvailable()) {
            loadMapItem(pageItemArr, i2, countDownLatch);
        } else if (PreferenceFeatures.isEnabled(PreferenceFeatures.StorySummaryCollage)) {
            loadCollageItem(pageItemArr, i2, countDownLatch);
        } else {
            countDownLatch.countDown();
        }
    }

    private void swapRelatedData(RelatedDataHolder relatedDataHolder) {
        ThreadUtil.postOnUiThread(new e(1, this, relatedDataHolder));
    }

    public ArrayList<PageItem> getPageItems() {
        return this.mItems;
    }

    public void loadData(Consumer<Boolean> consumer) {
        SimpleThreadPool.getInstance().execute(new e(0, this, consumer));
    }

    public void reset() {
        this.mItems.clear();
    }

    public void swap(List<PageItem> list) {
        Log.d("PageDataLoader", "swap", Integer.valueOf(list.size()), list.stream().map(new n5.e(23)).limit(5).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]")));
        this.mItems.clear();
        this.mItems.addAll(list);
    }

    public PageItem[] loadData() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        PageItem[] pageItemArr = new PageItem[2];
        loadSummaryItem(pageItemArr, 0, countDownLatch);
        loadRelatedItem(pageItemArr, 1, countDownLatch, cloneRelatedData(this.mRelatedDataHolder));
        try {
            countDownLatch.await();
            return pageItemArr;
        } catch (InterruptedException e) {
            Log.e((CharSequence) "PageDataLoader", "loadData failed e=", e.getMessage());
            return pageItemArr;
        }
    }
}
