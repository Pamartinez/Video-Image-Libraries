package com.samsung.android.gallery.app.ui.list.stories.pictures.related;

import A2.d;
import A4.C0367b;
import A4.C0375j;
import D6.a;
import N2.j;
import android.database.Cursor;
import com.samsung.android.gallery.app.ui.list.stories.util.CoverHelper;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RelatedStoryLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ItemProvider {
        MediaItem getMediaItem(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Requester {
        private final RelatedDataHolder dataHolder;
        boolean enableCollageStory;
        boolean favoriteOnly;
        /* access modifiers changed from: private */
        public final MediaItem header;
        /* access modifiers changed from: private */
        public final ItemProvider itemProvider;

        public Requester(MediaItem mediaItem, RelatedDataHolder relatedDataHolder, ItemProvider itemProvider2) {
            this.header = mediaItem;
            this.dataHolder = relatedDataHolder;
            this.itemProvider = itemProvider2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$request$0(Consumer consumer) {
            RelatedStoryLoader.load(this, this.dataHolder);
            consumer.accept(this.dataHolder);
        }

        public Requester enableCollageStory(boolean z) {
            this.enableCollageStory = z;
            return this;
        }

        public void request(Consumer<RelatedDataHolder> consumer) {
            SimpleThreadPool.getInstance().execute(new d(28, this, consumer));
        }

        public Requester setFavoriteOnly(boolean z) {
            this.favoriteOnly = z;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$pickRelatedStories$1(RelatedInfo relatedInfo) {
        if (!relatedInfo.hasPerson() || !relatedInfo.hasLocation()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void load(Requester requester, RelatedDataHolder relatedDataHolder) {
        TimeTickLog timeTickLog = new TimeTickLog("RelatedStoryLoader");
        if (relatedDataHolder.isLoaded()) {
            pickRelatedStory(requester, relatedDataHolder, timeTickLog);
        } else {
            loadAndPick(requester, relatedDataHolder, timeTickLog);
        }
        timeTickLog.tock(0);
    }

    private static void loadAndPick(Requester requester, RelatedDataHolder relatedDataHolder, TimeTickLog timeTickLog) {
        loadRelatedList(requester, relatedDataHolder, timeTickLog);
        pickRelatedStory(requester, relatedDataHolder, timeTickLog);
    }

    private static ArrayList<MediaItem> loadMediaItems(ItemProvider itemProvider, ArrayList<RelatedInfo> arrayList, boolean z) {
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        Iterator<RelatedInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = itemProvider.getMediaItem(it.next().getAlbumId());
            if (mediaItem != null) {
                if (z && MediaItemStory.isCollageStory(mediaItem)) {
                    mediaItem = mediaItem.clone();
                    CoverHelper.changeAttributeOriginalCoverItem(mediaItem);
                }
                arrayList2.add(mediaItem);
            }
        }
        return arrayList2;
    }

    private static void loadRelatedList(Requester requester, RelatedDataHolder relatedDataHolder, TimeTickLog timeTickLog) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.RELATED_MEMORIES).setStoryFavoriteType(requester.favoriteOnly));
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    RelatedInfo relatedInfo = new RelatedInfo(query);
                    if (relatedInfo.getAlbumId() == MediaItemStory.getStoryId(requester.header)) {
                        relatedDataHolder.base = relatedInfo;
                    } else if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || requester.favoriteOnly || Objects.equals(MediaItemStory.getStoryCategoryKey(requester.header), StoryCategory.getKey(relatedInfo.getStoryType(), relatedInfo.getStorySaType()))) {
                        if (!StoryType.isTransitoryType(relatedInfo.getStoryType())) {
                            relatedDataHolder.addRelatedAlbumInfo(relatedInfo);
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        timeTickLog.tick("load(" + relatedDataHolder.isLoaded() + ")");
        return;
        throw th;
    }

    private static void pick(List<RelatedInfo> list, ArrayList<RelatedInfo> arrayList) {
        list.removeAll(arrayList);
        if (list.size() > 0) {
            arrayList.add(pickFromList(list));
        }
    }

    private static RelatedInfo pickFromList(List<RelatedInfo> list) {
        Iterator<RelatedInfo> it = list.iterator();
        int i2 = 0;
        while (it.hasNext() && it.next().isNewStory()) {
            i2++;
        }
        if (i2 <= 0) {
            i2 = list.size();
        }
        return list.remove(Math.abs(RandomNumber.nextInt() % i2));
    }

    private static ArrayList<RelatedInfo> pickRelatedStories(RelatedDataHolder relatedDataHolder) {
        String str;
        ArrayList<RelatedInfo> arrayList = new ArrayList<>();
        ArrayList<RelatedInfo> relatedAlbumsInfo = relatedDataHolder.getRelatedAlbumsInfo();
        RelatedInfo relatedInfo = relatedDataHolder.base;
        StringBuilder sb2 = new StringBuilder("currentStory : ");
        if (relatedInfo != null) {
            str = relatedInfo.toStringOrigin();
        } else {
            str = null;
        }
        j.y(sb2, str, "RelatedStoryLoader");
        if (!(relatedInfo == null || relatedAlbumsInfo == null || relatedAlbumsInfo.size() < 4)) {
            relatedAlbumsInfo.sort(new a(0));
            relatedAlbumsInfo.forEach(new C0367b(22, relatedInfo));
            pick((List) relatedAlbumsInfo.stream().filter(new C0375j(12)).collect(Collectors.toList()), arrayList);
            pick((List) relatedAlbumsInfo.stream().filter(new C0375j(14)).collect(Collectors.toList()), arrayList);
            pick((List) relatedAlbumsInfo.stream().filter(new C0375j(13)).collect(Collectors.toList()), arrayList);
            pick((List) relatedAlbumsInfo.stream().filter(new C0375j(15)).collect(Collectors.toList()), arrayList);
            if (arrayList.size() > 3) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
        return arrayList;
    }

    private static void pickRelatedStory(Requester requester, RelatedDataHolder relatedDataHolder, TimeTickLog timeTickLog) {
        relatedDataHolder.pickedStories = loadMediaItems(requester.itemProvider, pickRelatedStories(relatedDataHolder), requester.enableCollageStory);
        timeTickLog.tick("pick(" + relatedDataHolder.isValid() + ")");
    }
}
