package com.samsung.android.gallery.support.cache;

import java.io.File;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CacheHelper {
    void add(byte[] bArr, byte[] bArr2);

    void clear();

    void commit(byte[] bArr);

    boolean containsKey(byte[] bArr);

    boolean get(byte[] bArr, BytesBuffer bytesBuffer);

    int getCacheId();

    long getCacheSize();

    InputStream getInputStream(byte[] bArr);

    boolean isReady();

    File[] listFiles();

    void remove(byte[] bArr);

    long size();

    long[] trim();

    void writeToFile(byte[] bArr);
}
