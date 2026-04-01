package com.samsung.android.sdk.moneta.lifestyle;

import android.content.Context;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.service.EntertainmentService;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.service.EntertainmentServiceImpl;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.service.HealthService;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.service.HealthServiceImpl;
import com.samsung.android.sdk.moneta.lifestyle.notification.service.NotificationService;
import com.samsung.android.sdk.moneta.lifestyle.notification.service.NotificationServiceImpl;
import com.samsung.android.sdk.moneta.lifestyle.social.service.SocialService;
import com.samsung.android.sdk.moneta.lifestyle.social.service.SocialServiceImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/LifeStyleServiceProvider;", "", "<init>", "()V", "getHealthService", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/service/HealthService;", "context", "Landroid/content/Context;", "getEntertainmentService", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/service/EntertainmentService;", "getSocialService", "Lcom/samsung/android/sdk/moneta/lifestyle/social/service/SocialService;", "getNotificationService", "Lcom/samsung/android/sdk/moneta/lifestyle/notification/service/NotificationService;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LifeStyleServiceProvider {
    public static final LifeStyleServiceProvider INSTANCE = new LifeStyleServiceProvider();

    private LifeStyleServiceProvider() {
    }

    public static final EntertainmentService getEntertainmentService(Context context) {
        j.e(context, "context");
        return new EntertainmentServiceImpl(context);
    }

    public static final HealthService getHealthService(Context context) {
        j.e(context, "context");
        return new HealthServiceImpl(context);
    }

    public static final NotificationService getNotificationService(Context context) {
        j.e(context, "context");
        return new NotificationServiceImpl(context);
    }

    public static final SocialService getSocialService(Context context) {
        j.e(context, "context");
        return new SocialServiceImpl(context);
    }
}
