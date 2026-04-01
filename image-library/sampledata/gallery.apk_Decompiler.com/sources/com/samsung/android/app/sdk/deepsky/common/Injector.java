package com.samsung.android.app.sdk.deepsky.common;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/common/Injector;", "", "<init>", "()V", "provideServiceCaller", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "context", "Landroid/content/Context;", "provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release", "shareSystemDatasource", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "shareSystemDatasource$deepsky_sdk_smartsuggestion_6_1_0_release", "provideAppSearch", "Lcom/samsung/android/app/sdk/deepsky/common/AppSearch;", "provideAppSearch$deepsky_sdk_smartsuggestion_6_1_0_release", "provideShortcutManagerWrapper", "Lcom/samsung/android/app/sdk/deepsky/common/ShortcutManagerWrapper;", "provideShortcutManagerWrapper$deepsky_sdk_smartsuggestion_6_1_0_release", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Injector {
    public static final Injector INSTANCE = new Injector();

    private Injector() {
    }

    public final AppSearch provideAppSearch$deepsky_sdk_smartsuggestion_6_1_0_release(Context context) {
        j.e(context, "context");
        return new AppSearchImpl(context);
    }

    public final ContentProviderCaller provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(Context context) {
        j.e(context, "context");
        return new ContentProviderCallerImpl(context, (String) null, 2, (e) null);
    }

    public final ShortcutManagerWrapper provideShortcutManagerWrapper$deepsky_sdk_smartsuggestion_6_1_0_release(Context context) {
        j.e(context, "context");
        return new ShortcutManagerWrapperImpl(context);
    }

    public final SystemDataSource shareSystemDatasource$deepsky_sdk_smartsuggestion_6_1_0_release(Context context) {
        j.e(context, "context");
        return new SystemRepository(context);
    }
}
