package com.samsung.android.gallery.support.cache;

import A.a;
import A4.C0375j;
import E5.b;
import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import i.C0212a;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NoIndexDiskCacheHelper implements CacheHelper {
    private final File mCacheDir;
    private final int mCacheId;
    private final long mCacheSize;
    private final String mExtCacheDir;
    private volatile boolean mIsCacheInitDone;
    private final CacheBuffer mWriteCache;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CacheBuffer {
        private static final ConcurrentHashMap<Integer, CacheBuffer> cacheBuffers = new ConcurrentHashMap<>();
        private final Object LOCK = new Object();
        private final LruCache<byte[], DiskLruCacheData> mCache;

        private CacheBuffer(int i2, int i7) {
            this.mCache = new LruCache<byte[], DiskLruCacheData>(i7) {
                public int sizeOf(byte[] bArr, DiskLruCacheData diskLruCacheData) {
                    int length = diskLruCacheData.mValue.length;
                    if (length == 0) {
                        return 1;
                    }
                    return length;
                }
            };
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.Function] */
        public static CacheBuffer getInstance(int i2) {
            int i7;
            ConcurrentHashMap<Integer, CacheBuffer> concurrentHashMap = cacheBuffers;
            if (i2 < 10) {
                i7 = i2 * 100;
            } else {
                i7 = i2 / 10;
            }
            return concurrentHashMap.computeIfAbsent(Integer.valueOf(i7), new Object());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ CacheBuffer lambda$getInstance$0(Integer num) {
            return new CacheBuffer(num.intValue(), 104857600);
        }

        public DiskLruCacheData get(byte[] bArr) {
            DiskLruCacheData diskLruCacheData;
            synchronized (this.LOCK) {
                diskLruCacheData = this.mCache.get(bArr);
            }
            return diskLruCacheData;
        }

        public void put(byte[] bArr, DiskLruCacheData diskLruCacheData) {
            synchronized (this.LOCK) {
                this.mCache.put(bArr, diskLruCacheData);
            }
        }

        public DiskLruCacheData remove(byte[] bArr) {
            DiskLruCacheData remove;
            synchronized (this.LOCK) {
                remove = this.mCache.remove(bArr);
            }
            return remove;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DiskLruCacheData {
        final byte[] mValue;

        public DiskLruCacheData(byte[] bArr) {
            this.mValue = bArr;
        }
    }

    public NoIndexDiskCacheHelper(String str, int i2, long j2) {
        long j3;
        long currentTimeMillis = System.currentTimeMillis();
        this.mExtCacheDir = str;
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(i2);
        File file = new File(s.toString());
        this.mCacheDir = file;
        this.mCacheId = i2;
        if (j2 <= 0) {
            j3 = 104857600;
        } else {
            j3 = j2;
        }
        this.mCacheSize = j3;
        this.mWriteCache = CacheBuffer.getInstance(i2);
        init();
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 6) {
            Log.w("NoIndexDiskCacheHelper", "CacheInit" + Logger.v(Integer.valueOf(i2), Long.valueOf(j2), file.getPath()) + " +" + currentTimeMillis2);
        }
    }

    private void addToWriteCache(byte[] bArr, byte[] bArr2) {
        this.mWriteCache.put(bArr, new DiskLruCacheData(bArr2));
    }

    private void checkAllDirs() {
        int i2 = this.mCacheId / 100;
        if (i2 == 1 || i2 == 6) {
            for (int i7 = 0; i7 < 7; i7++) {
                File file = new File(this.mExtCacheDir + File.separator + ((i2 * 100) + i7));
                if (!file.exists()) {
                    boolean mkdirs = file.mkdirs();
                    Log.e("NoIndexDiskCacheHelper", "try recreate cache dir : " + mkdirs);
                }
            }
        } else if (!this.mCacheDir.exists()) {
            boolean mkdirs2 = this.mCacheDir.mkdirs();
            Log.e("NoIndexDiskCacheHelper", "try recreate cache dir : " + mkdirs2);
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.w("NoIndexDiskCacheHelper", "close failed\n" + e.getMessage());
            }
        }
    }

    private File getCacheDir() {
        return this.mCacheDir;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002c A[Catch:{ IOException -> 0x001e, all -> 0x001c }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean getFromDiskLruCache(byte[] r6, com.samsung.android.gallery.support.cache.BytesBuffer r7) {
        /*
            r5 = this;
            java.lang.String r0 = "NoIndexDiskCacheHelper"
            java.lang.String r1 = "get: failed\n"
            r2 = 0
            r3 = 0
            java.io.InputStream r3 = r5.getInputStream(r6)     // Catch:{ IOException -> 0x001e }
            if (r3 != 0) goto L_0x0010
            r5.closeSilently(r3)
            return r2
        L_0x0010:
            int r6 = r3.available()     // Catch:{ IOException -> 0x001e }
            byte[] r4 = r7.data     // Catch:{ IOException -> 0x001e }
            if (r4 == 0) goto L_0x0020
            int r4 = r4.length     // Catch:{ IOException -> 0x001e }
            if (r4 >= r6) goto L_0x0024
            goto L_0x0020
        L_0x001c:
            r6 = move-exception
            goto L_0x0053
        L_0x001e:
            r6 = move-exception
            goto L_0x003e
        L_0x0020:
            byte[] r4 = new byte[r6]     // Catch:{ IOException -> 0x001e }
            r7.data = r4     // Catch:{ IOException -> 0x001e }
        L_0x0024:
            byte[] r4 = r7.data     // Catch:{ IOException -> 0x001e }
            int r4 = r3.read(r4)     // Catch:{ IOException -> 0x001e }
            if (r4 <= 0) goto L_0x0035
            r7.offset = r2     // Catch:{ IOException -> 0x001e }
            r7.length = r6     // Catch:{ IOException -> 0x001e }
            r5.closeSilently(r3)
            r5 = 1
            return r5
        L_0x0035:
            java.lang.String r6 = "get: read failed"
            com.samsung.android.gallery.support.utils.Log.e(r0, r6)     // Catch:{ IOException -> 0x001e }
        L_0x003a:
            r5.closeSilently(r3)
            goto L_0x0052
        L_0x003e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            r7.<init>(r1)     // Catch:{ all -> 0x001c }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x001c }
            r7.append(r6)     // Catch:{ all -> 0x001c }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x001c }
            com.samsung.android.gallery.support.utils.Log.e(r0, r6)     // Catch:{ all -> 0x001c }
            goto L_0x003a
        L_0x0052:
            return r2
        L_0x0053:
            r5.closeSilently(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.cache.NoIndexDiskCacheHelper.getFromDiskLruCache(byte[], com.samsung.android.gallery.support.cache.BytesBuffer):boolean");
    }

    private boolean getFromWriteCache(byte[] bArr, BytesBuffer bytesBuffer) {
        DiskLruCacheData diskLruCacheData = this.mWriteCache.get(bArr);
        if (diskLruCacheData == null) {
            return getFromDiskLruCache(bArr, bytesBuffer);
        }
        byte[] bArr2 = diskLruCacheData.mValue;
        int length = bArr2.length;
        if (length > bytesBuffer.data.length) {
            bytesBuffer.data = new byte[length];
        }
        bytesBuffer.offset = 0;
        bytesBuffer.length = length;
        System.arraycopy(bArr2, 0, bytesBuffer.data, 0, length);
        return true;
    }

    private String getPath(byte[] bArr) {
        return this.mCacheDir + "/" + Crc.getCrc64Long(bArr) + ".0";
    }

    private void init() {
        if (!this.mCacheDir.exists() && !this.mCacheDir.mkdirs()) {
            Log.e("NoIndexDiskCacheHelper", "init failed. cant make cache dir : " + this.mCacheDir.getAbsolutePath() + ",r=" + this.mCacheDir.canRead() + ",w=" + this.mCacheDir.canWrite() + ",s=" + this.mCacheDir.getUsableSpace() + ",e=" + this.mCacheDir.exists());
        }
        this.mIsCacheInitDone = true;
    }

    private void touch(File file) {
        file.setLastModified(System.currentTimeMillis());
    }

    public void add(byte[] bArr, byte[] bArr2) {
        addToWriteCache(bArr, bArr2);
    }

    public void clear() {
        FileUtils.deleteFilesInDir(this.mCacheDir);
    }

    public boolean containsKey(byte[] bArr) {
        return FileUtils.exists(getPath(bArr));
    }

    public boolean get(byte[] bArr, BytesBuffer bytesBuffer) {
        return getFromWriteCache(bArr, bytesBuffer);
    }

    public int getCacheId() {
        return this.mCacheId;
    }

    public long getCacheSize() {
        return this.mCacheSize;
    }

    public InputStream getInputStream(byte[] bArr) {
        String path = getPath(bArr);
        File file = new File(path);
        if (file.exists()) {
            try {
                touch(file);
                return new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean isReady() {
        return this.mIsCacheInitDone;
    }

    public File[] listFiles() {
        File[] fileArr;
        File file = this.mCacheDir;
        if (file != null) {
            fileArr = file.listFiles();
        } else {
            fileArr = null;
        }
        if (fileArr != null) {
            return fileArr;
        }
        return new File[0];
    }

    public void remove(byte[] bArr) {
        try {
            FileUtils.delete(getPath(bArr));
        } catch (Exception e) {
            j.v("remove: failed\n", e, "NoIndexDiskCacheHelper");
        }
    }

    public long size() {
        return FileUtils.size(this.mCacheDir);
    }

    public String toString() {
        return "DiskCache[" + this.mCacheId + "]={dir=" + getCacheDir() + ", size=" + getCacheSize();
    }

    public long[] trim() {
        TimeTickLog timeTickLog = new TimeTickLog();
        File[] listFiles = listFiles();
        timeTickLog.tick();
        if (listFiles.length == 0) {
            return new long[]{0, 0};
        }
        long sum = Arrays.stream(listFiles).filter(new C0375j(28)).mapToLong(new b(14)).sum();
        timeTickLog.tick();
        if (sum < this.mCacheSize) {
            return new long[]{sum, 0};
        }
        Arrays.sort(listFiles, Comparator.comparingLong(new b(3)));
        timeTickLog.tick();
        long j2 = (long) (((double) this.mCacheSize) * 0.800000011920929d);
        long j3 = sum;
        int i2 = 0;
        for (File file : listFiles) {
            if (file != null) {
                long length = file.length();
                if (file.delete()) {
                    i2++;
                    j3 -= length;
                    if (j3 < j2) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        timeTickLog.tick();
        StringBuilder o2 = C0086a.o(i2, "trim=", "[");
        long j8 = sum - j3;
        o2.append(Logger.toMegaBytes(j8));
        o2.append("]");
        a.A(new Object[]{"id=" + this.mCacheId, "count=" + listFiles.length, "size=" + Logger.toMegaBytes(j3), "max=" + Logger.toMegaBytes(this.mCacheSize), o2.toString(), timeTickLog}, new StringBuilder("trim"), "NoIndexDiskCacheHelper");
        return new long[]{j3, j8};
    }

    public void writeToFile(byte[] bArr) {
        FileOutputStream fileOutputStream;
        DiskLruCacheData remove = this.mWriteCache.remove(bArr);
        if (remove != null) {
            try {
                fileOutputStream = new FileOutputStream(getPath(bArr));
                fileOutputStream.write(remove.mValue);
                fileOutputStream.close();
                return;
            } catch (Exception e) {
                Log.w("NoIndexDiskCacheHelper", "write cache failed : " + e.toString());
                checkAllDirs();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    public void commit(byte[] bArr) {
    }
}
