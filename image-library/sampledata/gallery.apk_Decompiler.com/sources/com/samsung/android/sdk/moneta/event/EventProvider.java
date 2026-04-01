package com.samsung.android.sdk.moneta.event;

import android.content.Context;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.event.service.EventContentProviderClient;
import com.samsung.android.sdk.moneta.event.service.EventService;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/EventProvider;", "", "<init>", "()V", "TAG", "", "getEventService", "Lcom/samsung/android/sdk/moneta/event/service/EventService;", "context", "Landroid/content/Context;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventProvider {
    public static final EventProvider INSTANCE = new EventProvider();
    private static final String TAG = "EventProvider";

    private EventProvider() {
    }

    public final EventService getEventService(Context context) {
        j.e(context, "context");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getEventService] in");
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        return new EventContentProviderClient(applicationContext);
    }
}
