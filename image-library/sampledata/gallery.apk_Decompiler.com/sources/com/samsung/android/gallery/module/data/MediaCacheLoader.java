package com.samsung.android.gallery.module.data;

import A.a;
import Ad.C0720a;
import android.os.Handler;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCacheLoader {
    public static final boolean SUPPORT;
    public static final boolean SUPPORT_REMASTER;
    public static final boolean SUPPORT_VIDEO_LONG_EXPOSURE;
    public static final boolean SUPPORT_XMP;
    private final Handler mHandler = ThreadUtil.createLazyHandler();
    private final ConcurrentHashMap<Integer, Runnable> mUpdatePool = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AiRemasterHolder {
        static final ArrayList<Pair<Integer, String>> values = new ArrayList<Pair<Integer, String>>() {
            {
                add(new Pair(4, "COLORIZER"));
                add(new Pair(5, "MOIRE"));
                add(new Pair(6, "REFLECTION"));
                add(new Pair(7, "SHADOW"));
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final MediaCacheLoader instance = new MediaCacheLoader();
    }

    static {
        boolean z;
        boolean z3 = MpAnalyzeInfoTable.SUPPORT_CACHE;
        SUPPORT = z3;
        SUPPORT_XMP = z3;
        if (!z3 || PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_FLARE_AND_DISTORTION) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_REMASTER = z;
        SUPPORT_VIDEO_LONG_EXPOSURE = z3;
    }

    public static MediaCacheLoader getInstance() {
        return LazyHolder.instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$buildRemasterTags$2(Pair pair) {
        return (String) pair.second;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$computeRemasterTags$0(MediaCache mediaCache, ArrayList arrayList, Pair pair) {
        boolean z;
        int intValue = ((Integer) pair.first).intValue();
        boolean contains = arrayList.contains(pair.second);
        if (((Integer) pair.first).intValue() == 4) {
            z = true;
        } else {
            z = false;
        }
        mediaCache.update(intValue, contains, z);
    }

    /* access modifiers changed from: private */
    /* renamed from: update */
    public void lambda$apply$3(MediaCache mediaCache) {
        long currentTimeMillis = System.currentTimeMillis();
        Runnable remove = this.mUpdatePool.remove(Integer.valueOf(mediaCache.hash));
        if (remove != null) {
            this.mHandler.removeCallbacks(remove);
        }
        int update = FilesApi.Cache.update(AppResources.getAppContext(), mediaCache.id, mediaCache.pack().stream);
        Log.d("MediaCacheLoader", "update {" + this.mUpdatePool.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + update + "} " + mediaCache + " +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void apply(MediaCache mediaCache) {
        if (mediaCache != null) {
            Runnable runnable = this.mUpdatePool.get(Integer.valueOf(mediaCache.hash));
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable);
            } else {
                runnable = new h(0, this, mediaCache);
                this.mUpdatePool.put(Integer.valueOf(mediaCache.hash), runnable);
            }
            this.mHandler.postDelayed(runnable, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    public ArrayList<String> buildRemasterTags(MediaCache mediaCache) {
        return (ArrayList) AiRemasterHolder.values.stream().filter(new g(0, mediaCache)).map(new j(6)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public ArrayList<XmpType> buildXmpTags(MediaCache mediaCache) {
        ArrayList<XmpType> arrayList = new ArrayList<>();
        if (mediaCache.isEnabled(0)) {
            arrayList.add(XmpType.XmpImage360);
        }
        if (mediaCache.isEnabled(1)) {
            arrayList.add(XmpType.XmpMotionPhoto);
        }
        if (mediaCache.isEnabled(2)) {
            arrayList.add(XmpType.XmpHdrGM);
        }
        return arrayList;
    }

    public void clearTag(FileItemInterface fileItemInterface, int i2) {
        MediaCache mediaCache = get(fileItemInterface);
        if (mediaCache.isEnabled(i2)) {
            Log.d("MediaCacheLoader", "clearTag", Long.valueOf(fileItemInterface.getFileId()), Integer.valueOf(i2));
            mediaCache.update(i2, false, false);
            apply(mediaCache);
        }
    }

    public boolean computeGainMap(MediaItem mediaItem) {
        if (hasGainMap(mediaItem)) {
            return true;
        }
        if (!isPhotoHdrCandidateFromXmp(mediaItem) || !computeXmpTags(mediaItem, new b(mediaItem, 2)).contains(XmpType.XmpHdrGM)) {
            return false;
        }
        return true;
    }

    public ArrayList<String> computeRemasterTags(MediaItem mediaItem, Supplier<ArrayList<String>> supplier) {
        long currentTimeMillis = System.currentTimeMillis();
        MediaCache mediaCache = get(mediaItem);
        if (mediaCache.contains(4)) {
            return buildRemasterTags(mediaCache);
        }
        ArrayList<String> arrayList = supplier.get();
        if (arrayList != null) {
            AiRemasterHolder.values.forEach(new o(2, mediaCache, arrayList));
            apply(mediaCache);
        }
        a.A(new Object[]{arrayList, mediaCache.toString(), Long.valueOf(currentTimeMillis)}, new StringBuilder("computeRemasterTags"), "MediaCacheLoader");
        return arrayList;
    }

    public ArrayList<XmpType> computeXmpTags(MediaItem mediaItem, Supplier<ArrayList<XmpType>> supplier) {
        long currentTimeMillis = System.currentTimeMillis();
        MediaCache mediaCache = get(mediaItem);
        if (mediaCache.contains(0)) {
            return buildXmpTags(mediaCache);
        }
        ArrayList<XmpType> arrayList = supplier.get();
        mediaCache.update(0, arrayList.contains(XmpType.XmpImage360));
        mediaCache.update(1, arrayList.contains(XmpType.XmpMotionPhoto), false);
        mediaCache.update(2, arrayList.contains(XmpType.XmpHdrGM), false);
        apply(mediaCache);
        a.A(new Object[]{arrayList, mediaCache.toString(), Long.valueOf(currentTimeMillis)}, new StringBuilder("computeXmpTags"), "MediaCacheLoader");
        return arrayList;
    }

    public synchronized MediaCache get(FileItemInterface fileItemInterface) {
        return DetailsData.getMediaCache(fileItemInterface);
    }

    public boolean hasGainMap(MediaItem mediaItem) {
        if (!SUPPORT_XMP || !get(mediaItem).isEnabled(2)) {
            return false;
        }
        return true;
    }

    public boolean isLongExposureCandidate(MediaItem mediaItem) {
        if (!SUPPORT_VIDEO_LONG_EXPOSURE || !mediaItem.isVideo()) {
            return false;
        }
        return get(mediaItem).isEnabled(0);
    }

    public boolean isPhotoHdrCandidateFromXmp(MediaItem mediaItem) {
        String str;
        if ((!mediaItem.isJpeg() && !mediaItem.isHeif()) || (str = mediaItem.camModel) == null) {
            return false;
        }
        if (str.startsWith("Google") || mediaItem.camModel.startsWith("Apple")) {
            return true;
        }
        return false;
    }

    public String toReadableString(MediaItem mediaItem) {
        MediaCache mediaCache = get(mediaItem);
        ArrayList arrayList = new ArrayList();
        if (SUPPORT_XMP && mediaCache.contains(0)) {
            arrayList.add(buildXmpTags(mediaCache).toString());
        }
        if (SUPPORT_REMASTER && mediaCache.contains(4)) {
            arrayList.add(buildRemasterTags(mediaCache).toString());
        }
        return String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList);
    }
}
