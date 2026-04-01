package com.samsung.android.app.sdk.deepsky.barcode.logger;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\tJ\u001f\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/logger/LibLogger;", "", "<init>", "()V", "", "tag", "message", "", "d", "(Ljava/lang/String;Ljava/lang/String;)I", "e", "i", "w", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LibLogger {
    public static final LibLogger INSTANCE = new LibLogger();

    private LibLogger() {
    }

    public static final int d(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.d("dslbar[1.0.12]@".concat(str), str2);
    }

    public static final int e(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.e("dslbar[1.0.12]@".concat(str), str2);
    }

    public static final int i(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.i("dslbar[1.0.12]@".concat(str), str2);
    }

    public static final int w(String str, String str2) {
        j.e(str, "tag");
        j.e(str2, "message");
        return Log.w("dslbar[1.0.12]@".concat(str), str2);
    }
}
