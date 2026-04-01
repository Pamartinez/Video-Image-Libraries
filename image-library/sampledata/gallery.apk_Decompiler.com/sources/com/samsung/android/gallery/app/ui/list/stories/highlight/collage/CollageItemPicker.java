package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import Ad.C0720a;
import E5.b;
import F8.a;
import Z8.c;
import a8.d;
import android.database.Cursor;
import c4.C0431a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.creature.FacePosRatioHelper;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageItemPicker {
    private final IStoryHighlightView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Content {
        long dateTaken;
        int faceCount;
        /* access modifiers changed from: package-private */
        public long id;
        boolean isLand;
        MediaItem item;
        float mfcScore;
        int sceneCount;

        public /* synthetic */ Content(MediaItem mediaItem, int i2) {
            this(mediaItem);
        }

        public boolean isLand(MediaItem mediaItem) {
            boolean z;
            boolean z3;
            float width = ((float) mediaItem.getWidth()) / ((float) mediaItem.getHeight());
            if (width != 0.0f) {
                if (width > 1.0f) {
                    z = true;
                } else {
                    z = false;
                }
                if (mediaItem.getOrientation() % MOCRLang.KHMER == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z == z3) {
                    return true;
                }
                return false;
            }
            return true;
        }

        public String toString() {
            return "ImageInfo@" + Integer.toHexString(hashCode()) + Logger.vt(Long.valueOf(this.id), Long.valueOf(this.dateTaken), Boolean.valueOf(this.isLand), Float.valueOf(this.mfcScore), Integer.valueOf(this.faceCount), Integer.valueOf(this.sceneCount));
        }

        private Content(MediaItem mediaItem) {
            this.item = mediaItem;
            this.id = mediaItem.getFileId();
            this.dateTaken = mediaItem.getDateTaken();
            this.isLand = isLand(mediaItem);
            this.mfcScore = MediaItemStory.getMfcScore(mediaItem);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContentInfo {
        ArrayList<Content> allContents;
        ArrayList<Long> allFileIds;
        ArrayList<Long> imageFileIds;
        ArrayList<Content> images;
        ItemsInfo imagesInfo;
        ArrayList<Long> videoFileIds;
        ArrayList<Content> videos;
        ItemsInfo videosInfo;

        public /* synthetic */ ContentInfo(int i2) {
            this();
        }

        public int getImageSize() {
            return this.images.size();
        }

        public int getVideoSize() {
            return this.videos.size();
        }

        public boolean isImageOnly() {
            if (this.videos.size() == 0) {
                return true;
            }
            return false;
        }

        private ContentInfo() {
            this.images = new ArrayList<>();
            this.videos = new ArrayList<>();
            this.imageFileIds = new ArrayList<>();
            this.videoFileIds = new ArrayList<>();
            this.imagesInfo = new ItemsInfo(0);
            this.videosInfo = new ItemsInfo(0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemsInfo {
        int landCount;
        int lastMfcScoreIndex;
        int portCount;

        public /* synthetic */ ItemsInfo(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void increase(boolean z) {
            if (z) {
                this.landCount++;
            } else {
                this.portCount++;
            }
        }

        /* access modifiers changed from: private */
        public boolean isLandType() {
            if (this.landCount >= this.portCount) {
                return true;
            }
            return false;
        }

        public boolean hasMcfScore() {
            if (this.lastMfcScoreIndex != -1) {
                return true;
            }
            return false;
        }

        private ItemsInfo() {
            this.lastMfcScoreIndex = -1;
        }
    }

    public CollageItemPicker(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    private int compareCount(int i2, int i7) {
        if (i2 <= 0 || i7 <= 0 || (i2 <= 3 && i7 <= 3)) {
            return Integer.compare(i7, i2);
        }
        return Integer.compare(i2, i7);
    }

    private void fillContentInfo(ContentInfo contentInfo) {
        ArrayList<MediaItem> allData = this.mView.getMediaData() != null ? this.mView.getMediaData().getAllData() : null;
        if (allData != null) {
            fillContentInfo(contentInfo, allData);
            sortContentInfo(contentInfo.images);
            sortContentInfo(contentInfo.videos);
            fillContentProp(contentInfo.images, contentInfo.imageFileIds, contentInfo.imagesInfo);
            fillContentProp(contentInfo.videos, contentInfo.videoFileIds, contentInfo.videosInfo);
        }
    }

    private void fillContentProp(ArrayList<Content> arrayList, ArrayList<Long> arrayList2, ItemsInfo itemsInfo) {
        Iterator<Content> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Content next = it.next();
            arrayList2.add(Long.valueOf(next.id));
            itemsInfo.increase(next.isLand);
            if (next.mfcScore > 0.0f) {
                itemsInfo.lastMfcScoreIndex = i2;
            }
            i2++;
        }
    }

    private void fillMediaItem(CollageInfo collageInfo, ArrayList<Content> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<Long> it = collageInfo.getFileIds().iterator();
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            Iterator<Content> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Content next = it2.next();
                if (next.id == longValue) {
                    arrayList2.add(next.item);
                }
            }
        }
        collageInfo.updateInfo(arrayList2);
    }

    private Content findBestImage(ArrayList<Content> arrayList) {
        ArrayList arrayList2 = new ArrayList(arrayList);
        arrayList2.sort(new c(2, this));
        return (Content) arrayList2.get(0);
    }

    private CollageType[] getCollageCandidate(ContentInfo contentInfo) {
        if (!contentInfo.isImageOnly()) {
            Log.d("CollageItemPicker", "pickType content count", Integer.valueOf(contentInfo.getImageSize()), Integer.valueOf(contentInfo.getVideoSize()));
            if (contentInfo.imagesInfo.isLandType()) {
                return new CollageType[]{CollageType.COLLAGE3};
            }
            return new CollageType[]{CollageType.COLLAGE4, CollageType.COLLAGE5};
        } else if (contentInfo.imagesInfo.isLandType()) {
            return new CollageType[]{CollageType.COLLAGE1, CollageType.COLLAGE3};
        } else {
            return new CollageType[]{CollageType.COLLAGE2, CollageType.COLLAGE4, CollageType.COLLAGE5};
        }
    }

    private CollageType[] getIrregularCollageCandidate() {
        return new CollageType[]{CollageType.COLLAGE6, CollageType.COLLAGE7, CollageType.COLLAGE8};
    }

    private CollageType getTypeForTest(int i2) {
        return CollageType.getType(i2 - 1);
    }

    private int getVideoPosition(ContentInfo contentInfo, CollageType collageType) {
        if (contentInfo.isImageOnly()) {
            return -1;
        }
        int size = collageType.getLargeCells().size();
        if (size > 0) {
            return collageType.getLargeCells().get(RandomNumber.nextInt(size)).intValue();
        }
        return 0;
    }

    private boolean hasValidContentSize(ContentInfo contentInfo, CollageInfo collageInfo) {
        if (contentInfo.getImageSize() >= collageInfo.getCollageSize() || (contentInfo.getImageSize() == collageInfo.getCollageSize() - 1 && contentInfo.getVideoSize() >= 1)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$findBestImage$3(Content content, Content content2) {
        int compare = Boolean.compare(content.isLand, content2.isLand);
        if (compare != 0) {
            return compare;
        }
        int compareCount = compareCount(content.faceCount, content2.faceCount);
        if (compareCount != 0) {
            return compareCount;
        }
        int compareCount2 = compareCount(content.sceneCount, content2.sceneCount);
        if (compareCount2 != 0) {
            return compareCount2;
        }
        int compare2 = Float.compare(content2.mfcScore, content.mfcScore);
        if (compare2 != 0) {
            return compare2;
        }
        return Long.compare(content.dateTaken, content2.dateTaken);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$pick$0(Consumer consumer, boolean z, CollageInfo collageInfo, CollageInfo collageInfo2) {
        if (!z) {
            collageInfo = collageInfo2;
        }
        consumer.accept(collageInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$pick$1(MediaItem mediaItem, CollageInfo collageInfo, Consumer consumer) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (collageInfo == null) {
            z = true;
        } else {
            z = false;
        }
        CollageInfo loadCollageInfo = loadCollageInfo(mediaItem, z);
        boolean compareAndSet = compareAndSet(mediaItem, collageInfo, loadCollageInfo);
        ThreadUtil.runOnUiThread(new a((Object) consumer, compareAndSet, (Object) loadCollageInfo, (Object) collageInfo, 7));
        Boolean valueOf = Boolean.valueOf(compareAndSet);
        Log.d("CollageItemPicker", "collage pick done", valueOf, "+" + (System.currentTimeMillis() - currentTimeMillis));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortContentInfo$5(Content content, Content content2) {
        int compare = Float.compare(content2.mfcScore, content.mfcScore);
        if (compare != 0) {
            return compare;
        }
        return Long.compare(content.dateTaken, content2.dateTaken);
    }

    private static CollageInfo loadCollageInfo(MediaItem mediaItem, boolean z) {
        Cursor collageCursor;
        CollageInfo collageInfo = new CollageInfo();
        try {
            collageCursor = DbCompat.storyApi().getCollageCursor(mediaItem.getAlbumID());
            if (collageCursor != null) {
                if (collageCursor.moveToNext()) {
                    collageInfo.load(collageCursor);
                    if (z) {
                        Log.d("CollageItemPicker", "loadCollage initial", collageInfo);
                    }
                }
            }
            if (collageCursor != null) {
                collageCursor.close();
            }
            return collageInfo;
        } catch (Exception e) {
            Log.e((CharSequence) "CollageItemPicker", "loadCollage failed", e.getMessage());
            e.printStackTrace();
            return collageInfo;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadFaceAndSceneInfo(ArrayList<Content> arrayList) {
        List list = (List) arrayList.stream().mapToLong(new b(11)).boxed().collect(Collectors.toList());
        TimeTickLog timeTickLog = new TimeTickLog();
        Cursor faceCountCursor = getFaceCountCursor(list);
        if (faceCountCursor != null) {
            try {
                if (faceCountCursor.moveToFirst()) {
                    do {
                        arrayList.get(list.indexOf(Long.valueOf(faceCountCursor.getLong(0)))).faceCount = faceCountCursor.getInt(1);
                    } while (faceCountCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (faceCountCursor != null) {
            faceCountCursor.close();
        }
        timeTickLog.tick();
        Cursor sceneCountCursor = getSceneCountCursor(list);
        if (sceneCountCursor != null) {
            try {
                if (sceneCountCursor.moveToFirst()) {
                    do {
                        arrayList.get(list.indexOf(Long.valueOf(sceneCountCursor.getLong(0)))).sceneCount = sceneCountCursor.getInt(1);
                    } while (sceneCountCursor.moveToNext());
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (sceneCountCursor != null) {
            sceneCountCursor.close();
        }
        timeTickLog.tick();
        Log.d("CollageItemPicker", "loadFaceAndScene elapsed" + Logger.vt(timeTickLog));
        return;
        throw th;
        throw th;
    }

    private void pickContents(CollageInfo collageInfo, ContentInfo contentInfo) {
        int i2;
        if (!contentInfo.isImageOnly()) {
            i2 = collageInfo.getCollageSize() - 1;
        } else {
            i2 = collageInfo.getCollageSize();
        }
        ArrayList arrayList = new ArrayList(contentInfo.images.subList(0, Math.min(contentInfo.imagesInfo.lastMfcScoreIndex + 1, i2)));
        ArrayList arrayList2 = new ArrayList(contentInfo.images);
        arrayList2.removeAll(arrayList);
        int size = arrayList.size();
        if (size < i2) {
            while (size < i2) {
                arrayList.add((Content) arrayList2.remove(RandomNumber.nextInt(arrayList2.size())));
                size++;
            }
        }
        CollageType type = collageInfo.getType();
        int videoPosition = getVideoPosition(contentInfo, type);
        if (videoPosition >= 0) {
            arrayList.add(videoPosition, contentInfo.videos.get(pickedVideoIndex(contentInfo)));
        }
        setContentsPosition(arrayList, type, videoPosition);
        ArrayList arrayList3 = new ArrayList();
        arrayList.forEach(new U5.b(25, arrayList3, collageInfo));
        collageInfo.updateInfo(arrayList3);
        if (!collageInfo.isGridType()) {
            FacePosRatioHelper.preload(arrayList3);
        }
    }

    private void pickForTest(CollageInfo collageInfo, ClusterItem clusterItem) {
        CollageType collageType;
        String title = clusterItem.getTitle();
        try {
            collageType = getTypeForTest(Integer.parseInt(title.substring(title.length() - 1)));
        } catch (Exception unused) {
            collageType = getTypeForTest(RandomNumber.nextInt(8) + 1);
        }
        collageInfo.updateType(collageType);
    }

    private void pickType(CollageInfo collageInfo, ContentInfo contentInfo) {
        CollageType[] collageTypeArr;
        if (!PreferenceFeatures.OneUi8x.STORY_IRREGULAR_COLLAGE || RandomNumber.nextInt(2) != 0) {
            collageTypeArr = getCollageCandidate(contentInfo);
        } else {
            collageTypeArr = getIrregularCollageCandidate();
        }
        collageInfo.updateType(collageTypeArr[RandomNumber.nextInt(collageTypeArr.length)]);
    }

    private int pickedVideoIndex(ContentInfo contentInfo) {
        if (contentInfo.videosInfo.hasMcfScore()) {
            return 0;
        }
        return Math.abs(MediaItemStory.getStoryId(this.mView.getHeaderItem())) % contentInfo.videos.size();
    }

    private void setContentToLandLargeCells(ArrayList<Content> arrayList, CollageType collageType, int i2) {
        Content content;
        int i7;
        int size = arrayList.size();
        if (i2 >= 0) {
            content = arrayList.remove(i2);
        } else {
            content = null;
        }
        ArrayList arrayList2 = new ArrayList();
        List<Integer> largeCells = collageType.getLargeCells();
        if (largeCells.contains(Integer.valueOf(i2))) {
            i7 = largeCells.size() - 1;
        } else {
            i7 = largeCells.size();
        }
        Iterator<Content> it = arrayList.iterator();
        while (it.hasNext()) {
            Content next = it.next();
            if (next.isLand && arrayList2.size() < i7) {
                arrayList2.add(next);
            }
        }
        arrayList.removeAll(arrayList2);
        for (int i8 = 0; i8 < size; i8++) {
            if (i8 == i2) {
                arrayList.add(i8, content);
            } else if (largeCells.contains(Integer.valueOf(i8)) && !arrayList2.isEmpty()) {
                arrayList.add(i8, (Content) arrayList2.remove(0));
            }
        }
        Log.d("CollageItemPicker", "arrangeForLandscapeLargeCell", Integer.valueOf(size), Integer.valueOf(arrayList.size()), Integer.valueOf(i2));
    }

    private void setContentToLargeCell(ArrayList<Content> arrayList, int i2) {
        loadFaceAndSceneInfo(arrayList);
        Content findBestImage = findBestImage(arrayList);
        arrayList.remove(findBestImage);
        arrayList.add(i2, findBestImage);
    }

    private void setContentsPosition(ArrayList<Content> arrayList, CollageType collageType, int i2) {
        if (!collageType.getLargeCells().isEmpty()) {
            if (collageType.hasLandscapeLargeCell()) {
                setContentToLandLargeCells(arrayList, collageType, i2);
                return;
            }
            List<Integer> largeCells = collageType.getLargeCells();
            if (largeCells.size() == 1 && i2 < 0) {
                setContentToLargeCell(arrayList, largeCells.get(0).intValue());
            }
        }
    }

    private void sortContentInfo(ArrayList<Content> arrayList) {
        arrayList.sort(new D6.a(22));
    }

    private boolean supportCollageImage(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.is3dCapture()) {
            return false;
        }
        return true;
    }

    private boolean supportCollageVideo(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken() || MediaItemStory.isLiveEffectItem(mediaItem) || mediaItem.isLogVideo() || mediaItem.is3dCapture()) {
            return false;
        }
        return true;
    }

    public boolean compareAndSet(MediaItem mediaItem, CollageInfo collageInfo, CollageInfo collageInfo2) {
        if (!this.mView.isDestroyed()) {
            ContentInfo contentInfo = new ContentInfo(0);
            fillContentInfo(contentInfo);
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryCollageForceCreation)) {
                pickForTest(collageInfo2, mediaItem);
            } else {
                try {
                    if (collageInfo2.valid(contentInfo.allFileIds)) {
                        if (!collageInfo2.equals(collageInfo)) {
                            fillMediaItem(collageInfo2, contentInfo.allContents);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    Log.d("CollageItemPicker", "compareAndSet failed, pick newData", e.getMessage());
                }
                pickType(collageInfo2, contentInfo);
            }
            if (!hasValidContentSize(contentInfo, collageInfo2)) {
                collageInfo2.reset();
                return true;
            }
            pickContents(collageInfo2, contentInfo);
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryCollageForceCreation)) {
                DbCompat.storyApi().updateCollageInfo(mediaItem.getAlbumID(), collageInfo2.getTypeValue(), collageInfo2.getFileIds());
            }
            return true;
        }
        return false;
    }

    public Cursor getFaceCountCursor(List<Long> list) {
        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
        return secMpQueryExecutor.rawQuery("select sec_media_id, count(*) from faces where sec_media_id in (" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ") and group_id > 0 GROUP BY sec_media_id", "getFaceCountCursor");
    }

    public Cursor getSceneCountCursor(List<Long> list) {
        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
        return secMpQueryExecutor.rawQuery("select sec_media_id, count(*) from scene where sec_media_id in (" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ") and (scene_name='animals' or parent_name='dogs' or parent_name='cats') GROUP BY sec_media_id", "getSceneCountCursor");
    }

    public boolean pick(CollageInfo collageInfo, MediaItem mediaItem, Consumer<CollageInfo> consumer) {
        SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) mediaItem, (Object) collageInfo, (Object) consumer, 4));
        return true;
    }

    public boolean supportVideoContents() {
        if (!PreferenceFeatures.OneUi7x.STORY_LAST_PAGE || !Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_EDITOR)) {
            return false;
        }
        return true;
    }

    private void fillContentInfo(ContentInfo contentInfo, ArrayList<MediaItem> arrayList) {
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isImage()) {
                if (supportCollageImage(next)) {
                    contentInfo.images.add(new Content(next, 0));
                }
            } else if (supportVideoContents() && supportCollageVideo(next)) {
                contentInfo.videos.add(new Content(next, 0));
            }
        }
        ArrayList<Content> arrayList2 = new ArrayList<>(contentInfo.images);
        contentInfo.allContents = arrayList2;
        arrayList2.addAll(contentInfo.videos);
        contentInfo.allFileIds = (ArrayList) contentInfo.allContents.stream().map(new C0431a(8)).collect(Collectors.toCollection(new C0720a(1)));
    }
}
