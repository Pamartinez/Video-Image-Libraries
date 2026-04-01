package com.samsung.android.sdk.moneta.common;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\tR\u0014\u0010\u000f\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/common/Logger;", "", "<init>", "()V", "", "className", "msg", "Lme/x;", "i$pde_sdk_1_0_40_release", "(Ljava/lang/String;Ljava/lang/String;)V", "i", "d$pde_sdk_1_0_40_release", "d", "e$pde_sdk_1_0_40_release", "e", "TAG", "Ljava/lang/String;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Logger {
    public static final Logger INSTANCE = new Logger();
    private static final String TAG = "Moneta:sdk:[1.0.40]@";

    private Logger() {
    }

    public final /* synthetic */ void d$pde_sdk_1_0_40_release(String str, String str2) {
        j.e(str, "className");
        j.e(str2, "msg");
        Log.d(TAG.concat(str), str2);
    }

    public final /* synthetic */ void e$pde_sdk_1_0_40_release(String str, String str2) {
        j.e(str, "className");
        j.e(str2, "msg");
        Log.e(TAG.concat(str), str2);
    }

    public final /* synthetic */ void i$pde_sdk_1_0_40_release(String str, String str2) {
        j.e(str, "className");
        j.e(str2, "msg");
        Log.i(TAG.concat(str), str2);
    }
}
