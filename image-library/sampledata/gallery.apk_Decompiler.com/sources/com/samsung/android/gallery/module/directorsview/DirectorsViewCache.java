package com.samsung.android.gallery.module.directorsview;

import A.a;
import android.database.Cursor;
import androidx.window.embedding.c;
import bc.C0584a;
import c9.C0587a;
import com.samsung.android.gallery.database.dal.mp.helper.DirectorsViewApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DirectorsViewCache {
    private static final DirectorsViewCache sInstance = new DirectorsViewCache();
    protected ConcurrentHashMap<Integer, PairInfo> mDirectorsViewMap;
    private final AtomicBoolean mIsLoading = new AtomicBoolean(false);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PairInfo {
        List<MediaItem> items = new CopyOnWriteArrayList();

        /* access modifiers changed from: private */
        public MediaItem findItem(MediaItem mediaItem) {
            for (MediaItem next : this.items) {
                if (next.getFileId() == mediaItem.getFileId()) {
                    return next;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public MediaItem findPairItem(MediaItem mediaItem) {
            for (MediaItem next : this.items) {
                if (next.getFileId() != mediaItem.getFileId() && mediaItem.getSefFileSubType() != next.getSefFileSubType()) {
                    return next;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public void removeItem(MediaItem mediaItem) {
            MediaItem findItem = findItem(mediaItem);
            if (findItem != null) {
                this.items.remove(findItem);
            }
        }

        /* access modifiers changed from: private */
        public void updateItem(MediaItem mediaItem) {
            removeItem(mediaItem);
            this.items.add(mediaItem);
        }
    }

    public static DirectorsViewCache getInstance() {
        return sInstance;
    }

    private int getKey(MediaItem mediaItem) {
        return (mediaItem.getSefFileType() + "/" + mediaItem.getBucketID() + "/" + mediaItem.getDirectorsViewGroupId()).hashCode();
    }

    private PairInfo getPairInfo(MediaItem mediaItem) {
        ConcurrentHashMap<Integer, PairInfo> concurrentHashMap = this.mDirectorsViewMap;
        if (concurrentHashMap != null) {
            return concurrentHashMap.get(Integer.valueOf(getKey(mediaItem)));
        }
        return null;
    }

    private boolean hasItem(MediaItem mediaItem) {
        PairInfo pairInfo = getPairInfo(mediaItem);
        if (pairInfo == null || pairInfo.findItem(mediaItem) == null) {
            return false;
        }
        return true;
    }

    private boolean hasPairItem(MediaItem mediaItem) {
        return isValidPairItem(findPairItem(mediaItem));
    }

    private static boolean isDirectorsItem(MediaItem mediaItem) {
        if (!isDirectorsShotMode(mediaItem) || !isDirectorsSefFileSubType(mediaItem)) {
            return false;
        }
        return true;
    }

    private static boolean isDirectorsSefFileSubType(MediaItem mediaItem) {
        int sefFileSubType = mediaItem.getSefFileSubType();
        if (sefFileSubType == 3 || sefFileSubType == 5) {
            return true;
        }
        return false;
    }

    private static boolean isDirectorsShotMode(MediaItem mediaItem) {
        return mediaItem.isShotMode("directors_view");
    }

    private static boolean isDualRecItem(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_DUAL_REC) || !isDualRecShotMode(mediaItem) || !isDualRecSSefFileSubType(mediaItem)) {
            return false;
        }
        return true;
    }

    private static boolean isDualRecSSefFileSubType(MediaItem mediaItem) {
        int sefFileSubType = mediaItem.getSefFileSubType();
        if (sefFileSubType == 3 || sefFileSubType == 257 || sefFileSubType == 513 || sefFileSubType == 1025 || sefFileSubType == 2049 || sefFileSubType == 4097) {
            return true;
        }
        return false;
    }

    private static boolean isDualRecShotMode(MediaItem mediaItem) {
        return mediaItem.isShotMode("Dual_Recording_Info");
    }

    public static boolean isDualVideo(MediaItem mediaItem) {
        if (!isValid(mediaItem)) {
            return false;
        }
        if (isDirectorsItem(mediaItem) || isDualRecItem(mediaItem)) {
            return true;
        }
        return false;
    }

    public static boolean isEditable(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_VIDEO_COLLAGE) || !isDualVideo(mediaItem) || !getInstance().hasPairItem(mediaItem)) {
            return false;
        }
        return true;
    }

    private static boolean isValid(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getShotMode() == null || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    private static boolean isValidPairItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isCloudOnly() || FileUtils.exists(mediaItem.getPath())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteFromCache$1(MediaItem mediaItem) {
        if (this.mDirectorsViewMap != null) {
            PairInfo pairInfo = getPairInfo(mediaItem);
            if (pairInfo != null) {
                pairInfo.removeItem(mediaItem);
            }
            Blackboard.publishGlobal("data://user/directorsViewUpdated", (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateData$2(ConcurrentHashMap concurrentHashMap) {
        this.mIsLoading.set(false);
        this.mDirectorsViewMap = concurrentHashMap;
        Blackboard.publishGlobal("data://user/directorsViewUpdated", (Object) null);
        Log.i("DirectorsViewCache", "updateData", Integer.valueOf(concurrentHashMap.size()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMediaItem$0(MediaItem mediaItem) {
        if (this.mDirectorsViewMap != null) {
            PairInfo pairInfo = getPairInfo(mediaItem);
            if (pairInfo != null) {
                pairInfo.updateItem(mediaItem);
            } else {
                putToPairInfo(mediaItem);
            }
        }
    }

    private void loadItems() {
        if (!this.mIsLoading.getAndSet(true)) {
            SimpleThreadPool.getInstance().execute(new C0584a(12, this));
        }
    }

    private void putToPairInfo(MediaItem mediaItem) {
        if (this.mDirectorsViewMap != null) {
            int key = getKey(mediaItem);
            PairInfo pairInfo = getPairInfo(mediaItem);
            if (pairInfo == null) {
                pairInfo = new PairInfo();
                this.mDirectorsViewMap.put(Integer.valueOf(key), pairInfo);
            }
            pairInfo.items.add(mediaItem);
        }
    }

    private void updateData(ConcurrentHashMap<Integer, PairInfo> concurrentHashMap) {
        ThreadUtil.postOnUiThread(new c(13, this, concurrentHashMap));
    }

    public boolean checkPairItem(MediaItem mediaItem, boolean z) {
        if (this.mDirectorsViewMap != null) {
            MediaItem findPairItem = findPairItem(mediaItem);
            if (findPairItem != null) {
                return isValidPairItem(findPairItem);
            }
            if (!z || hasItem(mediaItem)) {
                return false;
            }
            loadItems();
            return false;
        }
        loadItems();
        return false;
    }

    public void clear() {
        ConcurrentHashMap<Integer, PairInfo> concurrentHashMap = this.mDirectorsViewMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.mDirectorsViewMap = null;
        }
    }

    public void deleteFromCache(MediaItem mediaItem) {
        if (isDualVideo(mediaItem) && mediaItem.isCloudOnly()) {
            ThreadUtil.postOnUiThread(new C0587a(this, mediaItem, 1));
        }
    }

    public MediaItem findPairItem(MediaItem mediaItem) {
        PairInfo pairInfo = getPairInfo(mediaItem);
        if (pairInfo != null) {
            return pairInfo.findPairItem(mediaItem);
        }
        return null;
    }

    public void forceReload() {
        clear();
        this.mIsLoading.set(false);
        loadItems();
    }

    public void loadData() {
        Cursor directorsViewCursor;
        long currentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        int i2 = -1;
        try {
            directorsViewCursor = new DirectorsViewApi().getDirectorsViewCursor();
            if (directorsViewCursor != null) {
                if (directorsViewCursor.moveToFirst()) {
                    i2 = directorsViewCursor.getCount();
                    do {
                        MediaItem create = MediaItemBuilder.create(directorsViewCursor);
                        int key = getKey(create);
                        PairInfo pairInfo = (PairInfo) concurrentHashMap.get(Integer.valueOf(key));
                        if (pairInfo == null) {
                            pairInfo = new PairInfo();
                            concurrentHashMap.put(Integer.valueOf(key), pairInfo);
                        }
                        pairInfo.items.add(create);
                    } while (directorsViewCursor.moveToNext());
                    updateData(concurrentHashMap);
                }
            }
            if (directorsViewCursor != null) {
                directorsViewCursor.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadData failed. e="), "DirectorsViewCache");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("DirectorsViewCache", "loadData {M:" + concurrentHashMap.size() + " ,C:" + i2 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        return;
        throw th;
    }

    public void updateMediaItem(MediaItem mediaItem) {
        if (isDualVideo(mediaItem)) {
            ThreadUtil.postOnUiThread(new C0587a(this, mediaItem, 0));
        }
    }
}
