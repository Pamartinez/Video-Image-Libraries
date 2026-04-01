package com.samsung.android.gallery.module.dataset;

import N2.j;
import android.database.Cursor;
import android.database.StaleDataException;
import android.graphics.Bitmap;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataRemasterV2 extends MediaDataCursor {
    private final Map<Integer, MediaDataChild> mChildMediaData = new ConcurrentHashMap();
    private Vector<MediaItem> mData = new Vector<>();

    public MediaDataRemasterV2(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void compareChildData(DataInfo dataInfo) {
        Arrays.stream(RemasterSuggestGroup.values()).mapToInt(new T(1)).forEach(new C0597d0(this, dataInfo, 1));
    }

    private int getRemasterGroupType(long j2) {
        return RemasterSuggestGroup.get(VslMesDetectorCompat.decodeEnhances(j2, true)).ordinal();
    }

    private boolean hasResolution(MediaItem mediaItem) {
        return RemasterSuggestGroup.isResolution(VslMesDetectorCompat.decodeEnhances(MediaItemSuggest.getRevitalizedType(mediaItem), true));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$compareChildData$10(DataInfo dataInfo, int i2) {
        MediaDataChild mediaDataChild = this.mChildMediaData.get(Integer.valueOf(i2));
        if (mediaDataChild != null && !DataInfo.match((List<MediaItem>) mediaDataChild.mData, (List<MediaItem>) dataInfo.mChildData.get(Integer.valueOf(i2)))) {
            mediaDataChild.mIsChanged = true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFirst$6(DataInfo dataInfo, Integer num) {
        Optional.ofNullable(this.mChildMediaData.get(num)).ifPresent(new I(3, dataInfo, num));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFirst$7(DataInfo dataInfo) {
        this.mDataCount = dataInfo.mDataCount;
        dataInfo.mIndexMap.keySet().forEach(new I(2, this, dataInfo));
        if (dataInfo.mDoneSwap) {
            notifyChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFirst$8(DataInfo dataInfo, int i2, Set set) {
        loadIndexMap(dataInfo, i2, -1);
        loadItems(dataInfo, set);
        dataInfo.mDataCount = dataInfo.mIndexMap.keySet().size();
        runOnUiThread(new B(1, this, dataInfo));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaDataChild lambda$loadItems$3(Integer num) {
        return new MediaDataChild(getBlackboard(), getLocationKey(), num.intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadItems$4(Set set, DataInfo dataInfo, Integer num, Vector vector) {
        MediaItem loadMediaItem;
        this.mChildMediaData.computeIfAbsent(num, new C0608m(5, this));
        for (int i2 = 0; i2 < vector.size(); i2++) {
            Integer num2 = (Integer) vector.get(i2);
            int intValue = num2.intValue();
            if (set.add(num2) && (loadMediaItem = loadMediaItem(dataInfo.mCursor, intValue)) != null) {
                if (i2 == 0) {
                    updateMainItem(dataInfo, loadMediaItem);
                }
                if (hasResolution(loadMediaItem)) {
                    BitmapOptions parse = BitmapOptionsFactory.parse(loadMediaItem.getPath());
                    MediaItemSuggest.setRemasterSize(loadMediaItem, parse.outWidth, parse.outHeight);
                }
                dataInfo.mChildData.computeIfAbsent(num, new K(14)).add(loadMediaItem);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$2(DataInfo dataInfo, boolean z, Cursor[] cursorArr, Cursor[] cursorArr2) {
        if (this.mLock.acquireWriteLock()) {
            swapChildInternal(dataInfo, z);
            if (z) {
                swapMainInternal(cursorArr, dataInfo);
            }
            dataInfo.mDoneSwap = true;
            closeCursors(cursorArr2);
            this.mLock.releaseWriteLock();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swapChildInternal$11(DataInfo dataInfo, int i2) {
        MediaDataChild mediaDataChild = this.mChildMediaData.get(Integer.valueOf(i2));
        if (mediaDataChild != null && mediaDataChild.mIsChanged) {
            mediaDataChild.swap(dataInfo.mChildData.get(Integer.valueOf(i2)));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateMainItem$9(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.getFileId() == mediaItem.getFileId()) {
            return true;
        }
        return false;
    }

    private void load(DataInfo dataInfo) {
        if (dataInfo.mCursor != null) {
            loadIndexMap(dataInfo, 0, -1);
            loadItems(dataInfo, new HashSet());
            dataInfo.mDataCount = dataInfo.mIndexMap.keySet().size();
        }
    }

    private void loadFirst(DataInfo dataInfo) {
        if (dataInfo.mCursor != null) {
            HashSet hashSet = new HashSet();
            int loadIndexMap = loadIndexMap(dataInfo, 0, 15);
            loadItems(dataInfo, hashSet);
            dataInfo.mDataCount = dataInfo.mIndexMap.keySet().size();
            SimpleThreadPool.getInstance().execute(new C0595c0(this, dataInfo, loadIndexMap, hashSet, 0));
        }
    }

    private int loadIndexMap(DataInfo dataInfo, int i2, int i7) {
        Cursor cursor = dataInfo.mCursor;
        if (cursor.moveToPosition(i2)) {
            int columnIndex = cursor.getColumnIndex("revitalized_type");
            do {
                int remasterGroupType = getRemasterGroupType((long) cursor.getInt(columnIndex));
                if (RemasterSuggestGroup.NONE.ordinal() != remasterGroupType) {
                    dataInfo.mIndexMap.computeIfAbsent(Integer.valueOf(remasterGroupType), new K(14)).add(Integer.valueOf(cursor.getPosition()));
                    if (i7 >= 0) {
                        int i8 = i2 + 1;
                        if (i2 >= i7) {
                            return i8;
                        }
                        i2 = i8;
                    }
                }
            } while (cursor.moveToNext());
        }
        return i2;
    }

    private void loadItems(DataInfo dataInfo, Set<Integer> set) {
        dataInfo.mIndexMap.forEach(new C0599e0(this, set, dataInfo));
    }

    private void preloadThumbnail(DataInfo dataInfo) {
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        int i2 = 0;
        for (Map.Entry next : dataInfo.mChildData.entrySet()) {
            int min = Math.min(((Vector) next.getValue()).size(), 15);
            for (int i7 = 0; i7 < min; i7++) {
                MediaItem mediaItem = (MediaItem) ((Vector) next.getValue()).get(i7);
                if (mediaItem != null) {
                    instance.loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new C0612q(4));
                    i2++;
                }
            }
        }
        Log.d(this.TAG, "preloadThumbnail", Integer.valueOf(i2));
    }

    private void swapChildInternal(DataInfo dataInfo, boolean z) {
        Arrays.stream(RemasterSuggestGroup.values()).mapToInt(new T(1)).forEach(new C0597d0(this, dataInfo, 0));
        if (!z) {
            notifyDataRangeChanged(0, this.mChildMediaData.size(), "payload_item_info");
        }
    }

    private void swapMainInternal(Cursor[] cursorArr, DataInfo dataInfo) {
        this.mCursors = cursorArr;
        this.mDataCount = dataInfo.mDataCount;
        this.mData = dataInfo.mData;
        notifyChanged();
    }

    private void updateMainItem(DataInfo dataInfo, MediaItem mediaItem) {
        if (dataInfo.mData.stream().noneMatch(new C0591a0(mediaItem, 0))) {
            dataInfo.mData.add(mediaItem);
        }
    }

    public /* bridge */ /* synthetic */ boolean acquireWriteLock(String str) {
        return super.acquireWriteLock(str);
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public int findPosition(long j2) {
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            MediaItem mediaItem = this.mData.get(i2);
            if (mediaItem != null && mediaItem.getMediaId() == j2) {
                return i2;
            }
        }
        return 0;
    }

    public ArrayList<MediaItem> getAllData() {
        return new ArrayList<>(this.mData);
    }

    public MediaData getChildMediaData(int i2) {
        return this.mChildMediaData.get(Integer.valueOf(i2));
    }

    public /* bridge */ /* synthetic */ ClusterTable getClusterTable(int i2) {
        return super.getClusterTable(i2);
    }

    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
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

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (super.isListeningEvent(eventMessage) || eventMessage.what == 104) {
            return true;
        }
        return false;
    }

    public void onCreate() {
        super.onCreate();
        this.mChildMediaData.forEach(new Z(1));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mChildMediaData.forEach(new Z(0));
    }

    public /* bridge */ /* synthetic */ MediaData open(String str, boolean z) {
        return super.open(str, z);
    }

    public /* bridge */ /* synthetic */ void reInit(String str) {
        super.reInit(str);
    }

    public MediaItem read(int i2) {
        if (i2 < 0 || i2 >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    public /* bridge */ /* synthetic */ MediaItem readById(long j2) {
        return super.readById(j2);
    }

    public MediaItem readCache(int i2) {
        return read(i2);
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
        Throwable th;
        MediaDataRemasterV2 mediaDataRemasterV2;
        Throwable th2;
        boolean z;
        boolean z3;
        try {
            if (this.mLock.acquireWriteLock()) {
                Cursor[] cursorArr2 = this.mCursors;
                DataInfo dataInfo = new DataInfo(cursorArr[0]);
                if (this.mCursors == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    try {
                        loadFirst(dataInfo);
                        preloadThumbnail(dataInfo);
                    } catch (StaleDataException | IllegalStateException | NullPointerException e) {
                        th2 = e;
                        mediaDataRemasterV2 = this;
                    } catch (Throwable th3) {
                        th = th3;
                        mediaDataRemasterV2 = this;
                        mediaDataRemasterV2.mLock.releaseWriteLock();
                        throw th;
                    }
                } else {
                    load(dataInfo);
                    compareChildData(dataInfo);
                }
                if (!z) {
                    if (DataInfo.match((List<MediaItem>) this.mData, (List<MediaItem>) dataInfo.mData)) {
                        z3 = false;
                        mediaDataRemasterV2 = this;
                        mediaDataRemasterV2.runOnUiThread(new C0593b0(mediaDataRemasterV2, dataInfo, z3, cursorArr, cursorArr2));
                    }
                }
                z3 = true;
                mediaDataRemasterV2 = this;
                try {
                    mediaDataRemasterV2.runOnUiThread(new C0593b0(mediaDataRemasterV2, dataInfo, z3, cursorArr, cursorArr2));
                } catch (StaleDataException | IllegalStateException | NullPointerException e7) {
                    e = e7;
                    th2 = e;
                    try {
                        Log.e(mediaDataRemasterV2.TAG, "swap > fail by destroy e=" + th2.getMessage());
                        mediaDataRemasterV2.mLock.releaseWriteLock();
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } else {
                mediaDataRemasterV2 = this;
                Log.e(mediaDataRemasterV2.TAG, "fail to get lock");
            }
        } catch (StaleDataException | IllegalStateException | NullPointerException e8) {
            e = e8;
            mediaDataRemasterV2 = this;
            th2 = e;
            Log.e(mediaDataRemasterV2.TAG, "swap > fail by destroy e=" + th2.getMessage());
            mediaDataRemasterV2.mLock.releaseWriteLock();
        } catch (Throwable th5) {
            th = th5;
            mediaDataRemasterV2 = this;
            th = th;
            mediaDataRemasterV2.mLock.releaseWriteLock();
            throw th;
        }
        mediaDataRemasterV2.mLock.releaseWriteLock();
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

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataInfo {
        Map<Integer, Vector<MediaItem>> mChildData = new ConcurrentHashMap();
        Cursor mCursor;
        Vector<MediaItem> mData = new Vector<>();
        int mDataCount = 0;
        boolean mDoneSwap = false;
        Map<Integer, Vector<Integer>> mIndexMap = new ConcurrentHashMap();

        public DataInfo(Cursor cursor) {
            this.mCursor = cursor;
        }

        public static boolean match(MediaItem mediaItem, MediaItem mediaItem2) {
            return mediaItem != null && mediaItem2 != null && MediaItemSuggest.getRevitalizedType(mediaItem) == MediaItemSuggest.getRevitalizedType(mediaItem2) && TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) && TextUtils.equals(MediaItemSuggest.getOriginPath(mediaItem), MediaItemSuggest.getOriginPath(mediaItem2));
        }

        public static boolean match(List<MediaItem> list, List<MediaItem> list2) {
            if (list == null && list2 == null) {
                return true;
            }
            if (list == null || list2 == null || list.size() != list2.size()) {
                return false;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (!match(list.get(i2), list2.get(i2))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataChild extends MediaDataRef {
        /* access modifiers changed from: private */
        public Vector<MediaItem> mData;
        /* access modifiers changed from: private */
        public boolean mIsChanged = true;
        private final int mType;

        public MediaDataChild(Blackboard blackboard, String str, int i2) {
            super(blackboard, str);
            this.mType = i2;
        }

        public void close() {
            Log.d("MediaDataChild", "close(ignore) {" + this.mLocationKey + "}, parent { MediaDataRemasterV2 }");
        }

        public int findPosition(long j2) {
            if (this.mData != null) {
                for (int i2 = 0; i2 < this.mData.size(); i2++) {
                    MediaItem mediaItem = this.mData.get(i2);
                    if (mediaItem != null && mediaItem.getMediaId() == j2) {
                        return i2;
                    }
                }
            }
            return 0;
        }

        public int getCount() {
            Vector<MediaItem> vector = this.mData;
            if (vector != null) {
                return vector.size();
            }
            return 0;
        }

        public int getRealCount() {
            return getCount();
        }

        public boolean isDataAvailable() {
            Vector<MediaItem> vector = this.mData;
            if (vector == null || vector.isEmpty()) {
                return false;
            }
            return true;
        }

        public MediaData open(String str, boolean z) {
            Log.d("MediaDataChild", "open(ignore) {" + this.mLocationKey + "}, parent { MediaDataRemasterV2 }");
            return this;
        }

        public MediaItem read(int i2) {
            return readCache(i2);
        }

        public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
            onDataReadListener.onDataReadCompleted(readCache(i2));
        }

        public MediaItem readCache(int i2) {
            try {
                Vector<MediaItem> vector = this.mData;
                if (vector != null) {
                    return vector.get(i2);
                }
                return null;
            } catch (Exception unused) {
                Log.e((CharSequence) "MediaDataChild", "readCache failed", Integer.valueOf(getCount()), Integer.valueOf(i2));
                return null;
            }
        }

        public void swap(Vector<MediaItem> vector) {
            boolean z;
            String str;
            Vector<MediaItem> vector2 = this.mData;
            this.mData = vector;
            int i2 = 0;
            if (vector == null || !this.mIsChanged) {
                z = false;
            } else {
                z = true;
            }
            this.mIsChanged = false;
            StringBuilder sb2 = new StringBuilder("swap ");
            sb2.append(this);
            sb2.append(" (");
            if (vector2 != null) {
                i2 = vector2.size();
            }
            sb2.append(i2);
            sb2.append(") ");
            if (z) {
                str = "change";
            } else {
                str = "skip";
            }
            j.y(sb2, str, "MediaDataChild");
            if (z) {
                notifyChanged();
            }
        }

        public String toString() {
            int i2;
            StringBuilder sb2 = new StringBuilder("MediaDataRemaster#");
            sb2.append(this.mType);
            sb2.append("[");
            sb2.append(RemasterSuggestGroup.get(this.mType));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            Vector<MediaItem> vector = this.mData;
            if (vector != null) {
                i2 = vector.size();
            } else {
                i2 = 0;
            }
            return C0086a.l(sb2, i2, "]");
        }

        public void requestData(String str) {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$12(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
