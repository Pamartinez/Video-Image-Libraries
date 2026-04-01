package com.samsung.android.app.sdk.deepsky.common;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/common/SystemRepository;", "Lcom/samsung/android/app/sdk/deepsky/common/SystemDataSource;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/os/Bundle;", "newBundle", "()Landroid/os/Bundle;", "Landroid/content/Context;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SystemRepository implements SystemDataSource {
    private final Context context;

    public SystemRepository(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    public Bundle newBundle() {
        return new Bundle();
    }
}
