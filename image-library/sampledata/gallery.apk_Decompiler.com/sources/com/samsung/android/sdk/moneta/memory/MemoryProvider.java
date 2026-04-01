package com.samsung.android.sdk.moneta.memory;

import android.content.Context;
import com.samsung.android.sdk.moneta.memory.service.MemorySearchService;
import com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl;
import com.samsung.android.sdk.moneta.memory.service.MemoryService;
import com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/MemoryProvider;", "", "<init>", "()V", "getMemoryService", "Lcom/samsung/android/sdk/moneta/memory/service/MemoryService;", "context", "Landroid/content/Context;", "getMemorySearchService", "Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchService;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemoryProvider {
    public static final MemoryProvider INSTANCE = new MemoryProvider();

    private MemoryProvider() {
    }

    public static final MemorySearchService getMemorySearchService(Context context) {
        j.e(context, "context");
        return new MemorySearchServiceImpl(context);
    }

    public static final MemoryService getMemoryService(Context context) {
        j.e(context, "context");
        return new MemoryServiceImpl(context);
    }
}
