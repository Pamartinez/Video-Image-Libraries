package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterCustomDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataScreenShotFilter extends MediaDataCursor {
    private ArrayList<MediaItem> mFilterItems = new ArrayList<>();
    private final boolean mIsSetting;

    public MediaDataScreenShotFilter(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str);
        this.mIsSetting = z;
    }

    private boolean equalsFilters(ArrayList<MediaItem> arrayList) {
        ArrayList<MediaItem> arrayList2 = this.mFilterItems;
        if (arrayList2.size() != arrayList.size()) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!TextUtils.equals(arrayList.get(i2).getSubCategory(), arrayList2.get(i2).getSubCategory())) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        requestData(getLocationKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$1(ArrayList arrayList) {
        if (this.mLock.acquireWriteLock()) {
            this.mFilterItems = arrayList;
            this.mDataCount = arrayList.size();
            notifyChanged(true);
            this.mLock.releaseWriteLock();
        }
    }

    private ArrayList<MediaItem> load(Cursor cursor) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String string = cursor.getString(0);
                MediaItem mediaItem = new MediaItem();
                mediaItem.setSubCategory(string);
                mediaItem.setTitle(ScreenShotFilterTitle.getInstance().getTitle(string));
                arrayList.add(mediaItem);
            } while (cursor.moveToNext());
            if (!arrayList.isEmpty()) {
                if (PreferenceFeatures.OneUi8x.SCREEN_SHOT_FILTER_REORDER) {
                    ScreenShotFilterCustomDataManager.reorder(arrayList, this.mIsSetting);
                }
                if (!this.mIsSetting) {
                    MediaItem mediaItem2 = new MediaItem();
                    mediaItem2.setSubCategory(ScreenShotFilterType.All.key());
                    mediaItem2.setTitle(AppResources.getString(R$string.all));
                    arrayList.add(0, mediaItem2);
                }
            }
        }
        return arrayList;
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command:///ScreenshotFilterOrderChanged", new C0594c(this, 5)));
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mFilterItems;
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public int getCount() {
        return this.mFilterItems.size();
    }

    public /* bridge */ /* synthetic */ int getDataVersion() {
        return super.getDataVersion();
    }

    public /* bridge */ /* synthetic */ ArrayList getFileIds() {
        return super.getFileIds();
    }

    public /* bridge */ /* synthetic */ String getLocationKey() {
        return super.getLocationKey();
    }

    public /* bridge */ /* synthetic */ String getLocationReference() {
        return super.getLocationReference();
    }

    public /* bridge */ /* synthetic */ int getPosition(int i2) {
        return super.getPosition(i2);
    }

    public /* bridge */ /* synthetic */ int getRealCount() {
        return super.getRealCount();
    }

    public /* bridge */ /* synthetic */ int getRefCount() {
        return super.getRefCount();
    }

    public /* bridge */ /* synthetic */ boolean isDataAvailable() {
        return super.isDataAvailable();
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        return this.mFilterItems.get(i2);
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        this.mFilterItems.clear();
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        return this.mFilterItems.get(i2);
    }

    public /* bridge */ /* synthetic */ void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        super.readAsync(i2, onDataReadListener, booleanSupplier);
    }

    public /* bridge */ /* synthetic */ MediaItem readById(long j2) {
        return super.readById(j2);
    }

    public MediaItem readCache(int i2) {
        return this.mFilterItems.get(i2);
    }

    public /* bridge */ /* synthetic */ void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void releaseWriteLock(String str) {
        super.releaseWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void removeItemAt(int i2) {
        super.removeItemAt(i2);
    }

    public /* bridge */ /* synthetic */ void reopen(String str) {
        super.reopen(str);
    }

    public void swap(Cursor[] cursorArr) {
        try {
            ArrayList<MediaItem> load = load(cursorArr[0]);
            if (!equalsFilters(load)) {
                runOnUiThread(new B(5, this, load));
            }
        } catch (Error | Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "swap failed. e=" + e.getMessage());
        } finally {
            closeCursors(cursorArr);
        }
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        super.unregister(onDataChangeListener);
    }

    public /* bridge */ /* synthetic */ void updateDataStampByPpp(MediaItem mediaItem) {
        super.updateDataStampByPpp(mediaItem);
    }
}
