package com.samsung.android.app.sdk.deepsky.textextraction.logger;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\tJ\u001f\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\tJ'\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\n\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/logger/LibLogger;", "", "<init>", "()V", "", "tag", "message", "", "d", "(Ljava/lang/String;Ljava/lang/String;)I", "e", "i", "w", "", "throwable", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LibLogger {
    public static final LibLogger INSTANCE = new LibLogger();

    private LibLogger() {
    }

    public static final int d(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.d("dslte[8.5.30]@".concat(str), str2);
    }

    public static final int e(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.e("dslte[8.5.30]@".concat(str), str2);
    }

    public static final int i(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.i("dslte[8.5.30]@".concat(str), str2);
    }

    public static final int w(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.w("dslte[8.5.30]@".concat(str), str2);
    }

    public static final int e(String str, String str2, Throwable th) {
        j.e(str, "tag");
        j.e(str2, "message");
        j.e(th, "throwable");
        return Log.e("dslte[8.5.30]@".concat(str), str2, th);
    }
}
