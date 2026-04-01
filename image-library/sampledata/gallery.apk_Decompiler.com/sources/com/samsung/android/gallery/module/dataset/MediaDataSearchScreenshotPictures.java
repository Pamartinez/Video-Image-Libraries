package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchScreenshotPictures extends MediaDataExpandedSearchPictures {
    final ConcurrentHashMap<String, SubSceneData> mExtraData = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SubSceneData extends MediaDataCursor {
        List<MediaItem> mItems = Collections.synchronizedList(new ArrayList());
        boolean mPrimary;

        public SubSceneData(Blackboard blackboard, String str, boolean z) {
            super(blackboard, str);
            this.mPrimary = z;
        }

        private void appendAllItem(ArrayList<MediaItem> arrayList) {
            if (!arrayList.isEmpty()) {
                MediaItem mediaItem = new MediaItem();
                mediaItem.setTitle(AppResources.getString(R$string.all));
                mediaItem.setSubCategory(ScreenShotFilterType.All.key());
                loadTitle(mediaItem);
                arrayList.add(0, mediaItem);
            }
        }

        private MediaItem getMediaItem(int i2) {
            if (i2 < 0 || i2 >= this.mItems.size()) {
                return null;
            }
            return this.mItems.get(i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$swap$0(ArrayList arrayList) {
            this.mItems.clear();
            this.mItems.addAll(arrayList);
            notifyChanged();
        }

        private void loadTitle(MediaItem mediaItem) {
            if (ScreenShotFilterType.All.key().equals(mediaItem.getSubCategory())) {
                mediaItem.setTitle(AppResources.getString(R$string.all));
                return;
            }
            String subCategory = mediaItem.getSubCategory();
            if (!StringCompat.startsWith(subCategory, "cap_")) {
                subCategory = C0212a.l("cap_", subCategory);
            }
            mediaItem.setTitle(ScreenShotFilterTitle.getInstance().getTitle(subCategory));
        }

        /* access modifiers changed from: private */
        public void swap(Cursor cursor) {
            if (cursor != null && cursor.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                do {
                    MediaItem load = MediaItemLoader.load(cursor);
                    loadTitle(load);
                    arrayList.add(load);
                } while (cursor.moveToNext());
                if (this.mPrimary) {
                    appendAllItem(arrayList);
                }
                ThreadUtil.runOnUiThread(new B(3, this, arrayList));
            }
        }

        public ArrayList<MediaItem> getAllData() {
            return new ArrayList<>(this.mItems);
        }

        public int getCount() {
            return this.mItems.size();
        }

        public MediaItem read(int i2) {
            return getMediaItem(i2);
        }

        public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
            onDataReadListener.onDataReadCompleted(getMediaItem(i2));
        }

        public MediaItem readCache(int i2) {
            return getMediaItem(i2);
        }
    }

    public MediaDataSearchScreenshotPictures(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private String getSubSceneLocationKey() {
        String argValue = ArgumentsUtil.getArgValue(getLocationReference(), "sub");
        String argValue2 = ArgumentsUtil.getArgValue(getLocationReference(), "sub_sub");
        StringBuilder sb2 = new StringBuilder("location://search/fileList/Category/ScreenShot/");
        if (!TextUtils.isEmpty(argValue2)) {
            argValue = argValue2;
        }
        sb2.append(argValue);
        return sb2.toString();
    }

    private boolean isPrimary() {
        return TextUtils.isEmpty(ArgumentsUtil.getArgValue(getLocationReference(), "sub_sub"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SubSceneData lambda$loadSubScenes$0(String str) {
        return new SubSceneData(this.mBlackboard, str, isPrimary());
    }

    private void loadSubScenes(Cursor cursor, CountDownLatch countDownLatch) {
        if (cursor != null) {
            this.mExtraData.computeIfAbsent(getSubSceneLocationKey(), new C0608m(6, this)).swap(cursor);
            Optional.ofNullable(countDownLatch).ifPresent(new C0596d(7));
        }
    }

    public MediaData getChildMediaData(String str) {
        return this.mExtraData.get(str);
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        super.initExtraTable(cursorArr, countDownLatch, candidates);
        loadSubScenes(cursorArr[cursorArr.length - 1], countDownLatch);
    }

    public void swapClusterTables(Cursor[] cursorArr, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, int i2, CountDownLatch countDownLatch) {
        super.swapClusterTables(cursorArr, clusterTableArr, defaultTableArr, i2, countDownLatch);
        loadSubScenes(cursorArr[cursorArr.length - 1], (CountDownLatch) null);
    }
}
