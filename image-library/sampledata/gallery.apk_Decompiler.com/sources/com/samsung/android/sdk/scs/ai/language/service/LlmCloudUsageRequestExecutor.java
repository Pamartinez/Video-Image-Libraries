package com.samsung.android.sdk.scs.ai.language.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sivs.ai.sdkcommon.language.l0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import t1.C0280e;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u000b\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0014¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0005R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;", "Lcom/samsung/android/sdk/scs/base/connection/ServiceExecutor;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/content/ComponentName;", "name", "Landroid/os/IBinder;", "service", "Lme/x;", "onConnected", "(Landroid/content/ComponentName;Landroid/os/IBinder;)V", "onDisconnected", "(Landroid/content/ComponentName;)V", "Landroid/content/Intent;", "getServiceIntent", "()Landroid/content/Intent;", "Lcom/samsung/android/sivs/ai/sdkcommon/language/l0;", "getService", "()Lcom/samsung/android/sivs/ai/sdkcommon/language/l0;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "Lcom/samsung/android/sivs/ai/sdkcommon/language/l0;", "Landroid/os/IBinder$DeathRecipient;", "deathRecipient", "Landroid/os/IBinder$DeathRecipient;", "Companion", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageRequestExecutor extends ServiceExecutor {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "UsageExecutor";
    private Context context;
    private final IBinder.DeathRecipient deathRecipient = new LlmCloudUsageRequestExecutor$deathRecipient$1(this);
    /* access modifiers changed from: private */
    public l0 service;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor$Companion;", "", "<init>", "()V", "TAG", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LlmCloudUsageRequestExecutor(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r10, r0)
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingDeque r8 = new java.util.concurrent.LinkedBlockingDeque
            r8.<init>()
            r3 = 1
            r4 = 1
            r5 = 60
            r1 = r9
            r2 = r10
            r1.<init>((android.content.Context) r2, (int) r3, (int) r4, (long) r5, (java.util.concurrent.TimeUnit) r7, (java.util.concurrent.BlockingQueue<java.lang.Runnable>) r8)
            r1.context = r2
            android.content.Context r9 = r2.getApplicationContext()
            r1.context = r9
            com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestExecutor$deathRecipient$1 r9 = new com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestExecutor$deathRecipient$1
            r9.<init>(r1)
            r1.deathRecipient = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestExecutor.<init>(android.content.Context):void");
    }

    public final Context getContext() {
        return this.context;
    }

    public final l0 getService() {
        return this.service;
    }

    public Intent getServiceIntent() {
        return C0280e.a("android.intellivoiceservice.UsageService", BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.j0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.j0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.j0} */
    /* JADX WARNING: type inference failed for: r2v7, types: [java.lang.Object, com.samsung.android.sivs.ai.sdkcommon.language.j0] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r2, android.os.IBinder r3) {
        /*
            r1 = this;
            int r2 = com.samsung.android.sivs.ai.sdkcommon.language.k0.f1710a
            if (r3 != 0) goto L_0x0006
            r2 = 0
            goto L_0x001c
        L_0x0006:
            java.lang.String r2 = "com.samsung.android.sivs.ai.sdkcommon.language.IUsageService"
            android.os.IInterface r2 = r3.queryLocalInterface(r2)
            if (r2 == 0) goto L_0x0015
            boolean r0 = r2 instanceof com.samsung.android.sivs.ai.sdkcommon.language.l0
            if (r0 == 0) goto L_0x0015
            com.samsung.android.sivs.ai.sdkcommon.language.l0 r2 = (com.samsung.android.sivs.ai.sdkcommon.language.l0) r2
            goto L_0x001c
        L_0x0015:
            com.samsung.android.sivs.ai.sdkcommon.language.j0 r2 = new com.samsung.android.sivs.ai.sdkcommon.language.j0
            r2.<init>()
            r2.f1708a = r3
        L_0x001c:
            r1.service = r2
            kotlin.jvm.internal.j.b(r2)     // Catch:{ RemoteException -> 0x002c }
            com.samsung.android.sivs.ai.sdkcommon.language.j0 r2 = (com.samsung.android.sivs.ai.sdkcommon.language.j0) r2     // Catch:{ RemoteException -> 0x002c }
            android.os.IBinder r2 = r2.f1708a     // Catch:{ RemoteException -> 0x002c }
            android.os.IBinder$DeathRecipient r1 = r1.deathRecipient     // Catch:{ RemoteException -> 0x002c }
            r3 = 0
            r2.linkToDeath(r1, r3)     // Catch:{ RemoteException -> 0x002c }
            return
        L_0x002c:
            r1 = move-exception
            r1.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        this.service = null;
    }

    public final void setContext(Context context2) {
        j.e(context2, "<set-?>");
        this.context = context2;
    }
}
