package com.samsung.android.sdk.scs.ai.asr.safety;

import Sd.s;
import android.os.Build;
import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface WatchDog extends AutoCloseable {
    public static final String DEFAULT_DETAIL_FORMAT = "YYMMdd_HHmm_ss.SSS";
    public static final SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DETAIL_FORMAT);
    public static final boolean isDevelop = (!"user".equals(Build.TYPE));

    static WatchDog create() {
        return create(false);
    }

    static String createTimeStr(long j2) {
        return formatter.format(new Date(j2));
    }

    void close();

    void executeWatchDog();

    long getCreateTime();

    boolean isWatching();

    /* renamed from: unWatchBlocking */
    void lambda$wrapBlocking$0(WatchDogCallback watchDogCallback);

    void watchBlocking(WatchDogCallback watchDogCallback, long j2);

    Closeable wrapBlocking(Runnable runnable, long j2) {
        WatchDogCallback create = create(runnable);
        watchBlocking(create, j2);
        return new s(2, this, create);
    }

    static WatchDog create(boolean z) {
        return WatchDogService.create(z);
    }

    static WatchDogCallback create(Runnable runnable) {
        if (runnable == null) {
            return WatchDogCallback.doNothing;
        }
        return new a(runnable);
    }
}
