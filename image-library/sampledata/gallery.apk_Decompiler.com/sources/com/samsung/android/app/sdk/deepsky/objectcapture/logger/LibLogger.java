package com.samsung.android.app.sdk.deepsky.objectcapture.logger;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0007J \u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/logger/LibLogger;", "", "<init>", "()V", "TAG", "", "d", "", "tag", "message", "e", "i", "w", "throwable", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LibLogger {
    public static final LibLogger INSTANCE = new LibLogger();
    private static final String TAG = "dsloc[8.5.9]@";

    private LibLogger() {
    }

    public static final int d(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.d(TAG.concat(str), str2);
    }

    public static final int e(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.e(TAG.concat(str), str2);
    }

    public static final int i(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.i(TAG.concat(str), str2);
    }

    public static final int w(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.w(TAG.concat(str), str2);
    }

    public static final int e(String str, String str2, Throwable th) {
        j.e(str, "tag");
        j.e(str2, "message");
        j.e(th, "throwable");
        return Log.e(TAG.concat(str), str2, th);
    }
}
