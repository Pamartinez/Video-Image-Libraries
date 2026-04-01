package com.samsung.android.sdk.moneta.common;

import Bd.C0725a;
import Gd.a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import lg.C1174b;
import lg.g;
import me.k;
import me.x;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\u0005\u001a\u00028\u0000H\b¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\r\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u0004\u0018\u00012\u0006\u0010\n\u001a\u00020\u0006H\b¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\u00068\u0006XT¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/common/SafeJson;", "", "<init>", "()V", "T", "t", "", "encodeToString$pde_sdk_1_0_40_release", "(Ljava/lang/Object;)Ljava/lang/String;", "encodeToString", "jsonString", "decodeFromString$pde_sdk_1_0_40_release", "(Ljava/lang/String;)Ljava/lang/Object;", "decodeFromString", "TAG", "Ljava/lang/String;", "Llg/b;", "json", "Llg/b;", "getJson", "()Llg/b;", "getJson$annotations", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeJson {
    public static final SafeJson INSTANCE = new SafeJson();
    public static final String TAG = "SafeJson";
    private static final C1174b json = a.a(new C0725a(2));

    private SafeJson() {
    }

    /* access modifiers changed from: private */
    public static final x json$lambda$0(g gVar) {
        j.e(gVar, "$this$Json");
        gVar.f4899a = true;
        gVar.b = false;
        gVar.f4900c = true;
        return x.f4917a;
    }

    public final <T> T decodeFromString$pde_sdk_1_0_40_release(String str) {
        j.e(str, "jsonString");
        try {
            B0.a aVar = INSTANCE.getJson().b;
            j.h();
            throw null;
        } catch (Throwable th) {
            Throwable a7 = k.a(L2.a.l(th));
            if (a7 != null) {
                Logger logger = Logger.INSTANCE;
                logger.e$pde_sdk_1_0_40_release(TAG, "[decodeFromString] failed. " + a7 + '.');
            }
            return null;
        }
    }

    public final <T> String encodeToString$pde_sdk_1_0_40_release(T t) {
        try {
            B0.a aVar = INSTANCE.getJson().b;
            j.h();
            throw null;
        } catch (Throwable th) {
            Throwable a7 = k.a(L2.a.l(th));
            if (a7 == null) {
                return "";
            }
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(TAG, "[encodeToString] failed. " + a7 + '.');
            return "";
        }
    }

    public final C1174b getJson() {
        return json;
    }

    public static /* synthetic */ void getJson$annotations() {
    }
}
