package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import Ae.a;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import md.b;
import me.x;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ3\u0010\u000f\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/LockScreenHelper;", "", "<init>", "()V", "Landroid/app/KeyguardManager;", "keyguardManager", "", "isScreenLocked", "(Landroid/app/KeyguardManager;)Z", "Landroid/content/Context;", "context", "Lkotlin/Function0;", "Lme/x;", "succeeded", "cancelled", "requestDismissKeyguard", "(Landroid/content/Context;LAe/a;LAe/a;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LockScreenHelper {
    public static final LockScreenHelper INSTANCE = new LockScreenHelper();

    private LockScreenHelper() {
    }

    private final boolean isScreenLocked(KeyguardManager keyguardManager) {
        return keyguardManager.isKeyguardLocked();
    }

    public static /* synthetic */ void requestDismissKeyguard$default(LockScreenHelper lockScreenHelper, Context context, a aVar, a aVar2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            aVar2 = new b(6);
        }
        lockScreenHelper.requestDismissKeyguard(context, aVar, aVar2);
    }

    /* access modifiers changed from: private */
    public static final x requestDismissKeyguard$lambda$0() {
        return x.f4917a;
    }

    public final void requestDismissKeyguard(Context context, a aVar, a aVar2) {
        j.e(context, "context");
        j.e(aVar, "succeeded");
        j.e(aVar2, "cancelled");
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager == null) {
            return;
        }
        if (INSTANCE.isScreenLocked(keyguardManager)) {
            keyguardManager.requestDismissKeyguard((Activity) context, new LockScreenHelper$requestDismissKeyguard$2$1(aVar2, aVar));
        } else {
            aVar.invoke();
        }
    }
}
