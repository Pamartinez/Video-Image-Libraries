package com.samsung.android.gallery.support.cache;

import A4.I;
import A5.a;
import Fa.C0563q;
import L5.b;
import M4.j;
import android.graphics.RectF;
import android.util.SparseArray;
import androidx.core.util.Pair;
import c0.C0086a;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CacheManager {
    private static final ConcurrentHashMap<String, StringBuilder> mKeyBuilderPool = new ConcurrentHashMap<>();
    private String mCacheDir;
    private final SparseArray<CacheHelper> mCacheHelperList;
    private GalleryPreference mPreference;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        static final CacheManager sInstance = new CacheManager(0);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Options {
        private final String mDiskCacheDir;
        private final ArrayList<Pair<Integer, Long>> mDiskCacheInfoList;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Builder {
            /* access modifiers changed from: private */
            public String mDiskCacheDir;
            /* access modifiers changed from: private */
            public final ArrayList<Pair<Integer, Long>> mDiskCacheInfoList = new ArrayList<>();

            public Builder addDiskCacheInfo(int i2, long j2) {
                if (CacheManager.hasChild(i2)) {
                    long j3 = j2 / 7;
                    int c5 = CacheManager.getFirstChildIndex(i2);
                    int i7 = c5 + 7;
                    while (c5 < i7) {
                        this.mDiskCacheInfoList.add(new Pair(Integer.valueOf(c5), Long.valueOf(j3)));
                        c5++;
                    }
                    return this;
                }
                this.mDiskCacheInfoList.add(new Pair(Integer.valueOf(i2), Long.valueOf(j2)));
                return this;
            }

            public Options build() {
                return new Options(this, 0);
            }

            public Builder setDiskCacheDir(String str) {
                this.mDiskCacheDir = str;
                return this;
            }
        }

        public /* synthetic */ Options(Builder builder, int i2) {
            this(builder);
        }

        public String getDiskCacheDir() {
            return this.mDiskCacheDir;
        }

        public ArrayList<Pair<Integer, Long>> getDiskCacheInfoList() {
            return this.mDiskCacheInfoList;
        }

        private Options(Builder builder) {
            this.mDiskCacheInfoList = builder.mDiskCacheInfoList;
            this.mDiskCacheDir = builder.mDiskCacheDir;
        }
    }

    public /* synthetic */ CacheManager(int i2) {
        this();
    }

    public static byte[] generateKey(String str, long j2) {
        StringBuilder computeIfAbsent = mKeyBuilderPool.computeIfAbsent(Thread.currentThread().getName(), new b(13));
        computeIfAbsent.setLength(0);
        computeIfAbsent.append(str);
        computeIfAbsent.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        computeIfAbsent.append(j2);
        computeIfAbsent.append("Sam2016Sung");
        return computeIfAbsent.toString().getBytes();
    }

    public static byte[] generateSmallCropKey(String str, RectF rectF, long j2) {
        return generateKey(str + "/" + rectF, j2);
    }

    private String getCacheFilePath(int i2, byte[] bArr) {
        if (hasChild(i2)) {
            int firstChildIndex = getFirstChildIndex(i2);
            int i7 = 0;
            for (byte b : bArr) {
                i7 += b;
            }
            i2 = firstChildIndex + (Math.abs(i7) % 7);
        }
        return this.mCacheDir + "/" + i2 + "/" + Crc.getCrc64Long(bArr) + ".0";
    }

    private String getDiskCacheSizePrefName(int i2) {
        return C0086a.i(i2, "disk_cache_size");
    }

    /* access modifiers changed from: private */
    public static int getFirstChildIndex(int i2) {
        return (i2 + 1) * 100;
    }

    private InputStream getInputStreamDirectly(int i2, byte[] bArr) {
        String cacheFilePath = getCacheFilePath(i2, bArr);
        if (!FileUtils.exists(cacheFilePath)) {
            return null;
        }
        try {
            Log.d("CacheManager", "direct cache path = " + cacheFilePath);
            return new FileInputStream(cacheFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CacheManager getInstance() {
        return Holder.sInstance;
    }

    public static Options getSimpleOptionsWithDiskOnly(String str, boolean z) {
        long j2;
        Options.Builder diskCacheDir = new Options.Builder().setDiskCacheDir(str);
        if (z) {
            diskCacheDir.addDiskCacheInfo(5, 209715200).addDiskCacheInfo(0, 1048576000);
        } else {
            diskCacheDir.addDiskCacheInfo(0, 1048576000).addDiskCacheInfo(5, 209715200);
        }
        if (PocFeatures.isEnabled(PocFeatures.PhotoStripHighQualityPreview)) {
            j2 = 3145728000L;
        } else {
            j2 = 104857600;
        }
        diskCacheDir.addDiskCacheInfo(2, j2).addDiskCacheInfo(4, 104857600).addDiskCacheInfo(3, 104857600).addDiskCacheInfo(6, 104857600).addDiskCacheInfo(7, 10485760).addDiskCacheInfo(8, 104857600);
        return diskCacheDir.build();
    }

    public static boolean hasChild(int i2) {
        if (i2 == 0 || i2 == 5) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ StringBuilder lambda$generateKey$1(String str) {
        return new StringBuilder(1024);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ long lambda$max$0(Integer num) {
        return this.mCacheHelperList.get(num.intValue()).getCacheSize();
    }

    private void migrateFromVersion0(int i2) {
        if (i2 >= 100) {
            int i7 = (i2 % 100) + ((i2 / 100) * 10);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mCacheDir);
            String str = File.separator;
            sb2.append(str);
            sb2.append(i7);
            String sb3 = sb2.toString();
            if (FileUtils.move(sb3, this.mCacheDir + str + i2)) {
                Log.w("CacheManager", "CacheInit : migrate from v0 " + Logger.v(Integer.valueOf(i7), Integer.valueOf(i2)));
            }
        }
    }

    public void add(int i2, byte[] bArr, byte[] bArr2) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper != null) {
            cacheHelper.add(bArr, bArr2);
            String diskCacheSizePrefName = getDiskCacheSizePrefName(cacheHelper.getCacheId());
            GalleryPreference preferenceCache = getPreferenceCache();
            long loadLong = preferenceCache.loadLong(diskCacheSizePrefName, -1);
            if (loadLong < 0) {
                loadLong = cacheHelper.size();
            }
            preferenceCache.saveState(diskCacheSizePrefName, loadLong + ((long) bArr2.length));
        }
    }

    public void clear(int i2) {
        for (CacheHelper cacheHelper : getCacheHelperArray(i2)) {
            if (cacheHelper != null) {
                cacheHelper.clear();
            }
        }
    }

    public void commit(int i2, byte[] bArr) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper != null) {
            cacheHelper.commit(bArr);
        }
    }

    public boolean containsKey(int i2, byte[] bArr) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper == null || !cacheHelper.containsKey(bArr)) {
            return false;
        }
        return true;
    }

    public boolean get(int i2, byte[] bArr, BytesBuffer bytesBuffer) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper == null || !cacheHelper.get(bArr, bytesBuffer)) {
            return false;
        }
        return true;
    }

    public CacheHelper getCacheHelper(int i2, byte[] bArr) {
        if (!hasChild(i2) || bArr == null) {
            return this.mCacheHelperList.get(i2);
        }
        int firstChildIndex = getFirstChildIndex(i2);
        int i7 = 0;
        for (byte b : bArr) {
            i7 += b;
        }
        return this.mCacheHelperList.get((Math.abs(i7) % 7) + firstChildIndex);
    }

    public CacheHelper[] getCacheHelperArray(int i2) {
        if (hasChild(i2)) {
            ArrayList arrayList = new ArrayList();
            int firstChildIndex = getFirstChildIndex(i2);
            for (int i7 = firstChildIndex; i7 < firstChildIndex + 7; i7++) {
                arrayList.add(this.mCacheHelperList.get(i7));
            }
            return (CacheHelper[]) arrayList.toArray(new CacheHelper[0]);
        }
        return new CacheHelper[]{this.mCacheHelperList.get(i2)};
    }

    public InputStream getInputStream(int i2, byte[] bArr) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper == null || !cacheHelper.isReady()) {
            return getInputStreamDirectly(i2, bArr);
        }
        return cacheHelper.getInputStream(bArr);
    }

    public GalleryPreference getPreferenceCache() {
        if (this.mPreference == null) {
            this.mPreference = GalleryPreference.getInstanceCache();
        }
        return this.mPreference;
    }

    public void initialize(Options options) {
        F f;
        this.mCacheDir = options.getDiskCacheDir();
        ArrayList<Pair<Integer, Long>> diskCacheInfoList = options.getDiskCacheInfoList();
        Trace.beginSection("DiskCache_Init");
        int loadInt = GalleryPreference.getInstance().loadInt(PreferenceName.DISK_CACHE_VERSION, 0);
        Iterator<Pair<Integer, Long>> it = diskCacheInfoList.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!(next == null || (f = next.first) == null || next.second == null)) {
                if (loadInt == 0) {
                    migrateFromVersion0(((Integer) f).intValue());
                }
                this.mCacheHelperList.put(((Integer) next.first).intValue(), new NoIndexDiskCacheHelper(this.mCacheDir, ((Integer) next.first).intValue(), ((Long) next.second).longValue()));
            }
        }
        if (loadInt != 1) {
            GalleryPreference.getInstance().saveState(PreferenceName.DISK_CACHE_VERSION, 1);
        }
        Trace.endSection();
    }

    public List<File> listFiles(int i2) {
        Stream<Integer> stream = listOf(i2).stream();
        SparseArray<CacheHelper> sparseArray = this.mCacheHelperList;
        Objects.requireNonNull(sparseArray);
        return (List) stream.map(new a(16, sparseArray)).filter(new j(2)).map(new b(14)).flatMap(new q8.a(11)).collect(Collectors.toList());
    }

    public List<Integer> listOf(int i2) {
        if (!hasChild(i2)) {
            return (List) IntStream.of(i2).boxed().collect(Collectors.toList());
        }
        int firstChildIndex = getFirstChildIndex(i2);
        return (List) IntStream.range(firstChildIndex, firstChildIndex + 7).boxed().collect(Collectors.toList());
    }

    public long max(int i2) {
        try {
            if (!hasChild(i2)) {
                return this.mCacheHelperList.get(i2).getCacheSize();
            }
            Stream<Integer> stream = listOf(i2).stream();
            SparseArray<CacheHelper> sparseArray = this.mCacheHelperList;
            Objects.requireNonNull(sparseArray);
            return stream.filter(new I(8, sparseArray)).mapToLong(new C0563q(1, this)).sum();
        } catch (Exception e) {
            A.a.s(e, C0086a.o(i2, "max[", "] failed. e="), "CacheManager");
            return 0;
        }
    }

    public void remove(String str, long j2) {
        byte[] generateKey = generateKey(str, j2);
        remove(0, generateKey);
        remove(2, generateKey);
        remove(5, generateKey);
    }

    public void removeCrop(String str, long j2, RectF rectF) {
        remove(5, generateSmallCropKey(str, rectF, j2));
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("CacheManager={\n");
        for (int i2 = 0; i2 < this.mCacheHelperList.size(); i2++) {
            CacheHelper valueAt = this.mCacheHelperList.valueAt(i2);
            if (valueAt != null) {
                sb2.append(valueAt.toString());
                sb2.append("\n");
            }
        }
        sb2.append("}");
        return sb2.toString();
    }

    public ArrayList<Integer> trim(long j2, List<Integer> list) {
        long currentTimeMillis = System.currentTimeMillis();
        long[] jArr = {0, 0};
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Integer next : list) {
            CacheHelper cacheHelper = this.mCacheHelperList.get(next.intValue());
            if (cacheHelper == null) {
                arrayList.add(next);
            } else if (j2 <= 0 || System.currentTimeMillis() < j2) {
                long[] trim = cacheHelper.trim();
                jArr[0] = jArr[0] + trim[0];
                jArr[1] = jArr[1] + trim[1];
                arrayList.add(next);
            }
        }
        A.a.A(new Object[]{"[" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + "]", "[" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]", "size=" + Logger.toMegaBytes(jArr[0]), "trimmed=" + Logger.toMegaBytes(jArr[1]), Long.valueOf(currentTimeMillis)}, new StringBuilder("trim"), "CacheManager");
        return arrayList;
    }

    public void writeToFile(int i2, byte[] bArr) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper != null) {
            cacheHelper.writeToFile(bArr);
        }
    }

    private CacheManager() {
        this.mCacheHelperList = new SparseArray<>();
    }

    public void remove(int i2, byte[] bArr) {
        CacheHelper cacheHelper = getCacheHelper(i2, bArr);
        if (cacheHelper != null) {
            cacheHelper.remove(bArr);
        }
    }
}
