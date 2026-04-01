package com.samsung.android.sum.core.cache;

import java.io.File;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DiskCache {
    void clear();

    void close();

    File get(String str);

    void put(String str, Function<File, Boolean> function);
}
