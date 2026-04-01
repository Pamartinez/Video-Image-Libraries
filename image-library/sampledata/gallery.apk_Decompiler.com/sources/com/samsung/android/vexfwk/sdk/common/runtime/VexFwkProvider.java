package com.samsung.android.vexfwk.sdk.common.runtime;

import Ad.C0721b;
import L1.d;
import L2.a;
import android.util.Log;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;
import me.k;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0004H ¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R!\u0010\u0017\u001a\u00020\u00048FX\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0015\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkProvider;", "", "<init>", "()V", "", "usecaseId", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "fetchPropertiesNative", "(I)Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "getNdkVersionNative", "()I", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "fetchProperties", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;)Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "ndkVersion$delegate", "Lme/f;", "getNdkVersion", "getNdkVersion$annotations", "ndkVersion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkProvider {
    public static final VexFwkProvider INSTANCE = new VexFwkProvider();
    private static final String TAG = "VexFwkProvider";
    private static final f ndkVersion$delegate = d.q(new C0721b(10));

    private VexFwkProvider() {
    }

    public static final VexFwkMetadataNative fetchProperties(VexFwkUsecase vexFwkUsecase) {
        VexFwkMetadataNative vexFwkMetadataNative;
        j.e(vexFwkUsecase, "usecase");
        try {
            vexFwkMetadataNative = fetchPropertiesNative(vexFwkUsecase.getId());
        } catch (Throwable th) {
            vexFwkMetadataNative = a.l(th);
        }
        Throwable a7 = k.a(vexFwkMetadataNative);
        VexFwkMetadataNative vexFwkMetadataNative2 = vexFwkMetadataNative;
        if (a7 != null) {
            VexFwkMetadataNative vexFwkMetadataNative3 = new VexFwkMetadataNative();
            vexFwkMetadataNative3.set(VexFwkMetadataKey.PROPERTY_IS_AVAILABLE.INSTANCE, Boolean.FALSE);
            vexFwkMetadataNative2 = vexFwkMetadataNative3;
        }
        return (VexFwkMetadataNative) vexFwkMetadataNative2;
    }

    private static final native VexFwkMetadataNative fetchPropertiesNative(int i2);

    public static final int getNdkVersion() {
        return ((Number) ndkVersion$delegate.getValue()).intValue();
    }

    private static final native int getNdkVersionNative();

    /* access modifiers changed from: private */
    public static final int ndkVersion_delegate$lambda$6() {
        int i2;
        try {
            int ndkVersionNative = getNdkVersionNative();
            String str = TAG;
            Log.i(str, "NDK version is " + ndkVersionNative);
            i2 = Integer.valueOf(ndkVersionNative);
        } catch (Throwable th) {
            i2 = a.l(th);
        }
        Throwable a7 = k.a(i2);
        if (a7 != null) {
            Log.w(TAG, "Failed to get NDK version. Returning 0.", a7);
            i2 = 0;
        }
        return ((Number) i2).intValue();
    }

    public static /* synthetic */ void getNdkVersion$annotations() {
    }
}
