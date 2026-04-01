package com.samsung.android.sdk.moneta.basicdomain;

import android.content.Context;
import com.samsung.android.sdk.moneta.basicdomain.service.FeedbackService;
import com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl;
import com.samsung.android.sdk.moneta.basicdomain.service.MyProfileService;
import com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonLinkService;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonLinkServiceImpl;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendService;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/BasicDomainProvider;", "", "<init>", "()V", "getFeedbackService", "Lcom/samsung/android/sdk/moneta/basicdomain/service/FeedbackService;", "context", "Landroid/content/Context;", "getMyProfileService", "Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileService;", "getPersonRecommendService", "Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendService;", "getPersonLinkService", "Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonLinkService;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BasicDomainProvider {
    public static final BasicDomainProvider INSTANCE = new BasicDomainProvider();

    private BasicDomainProvider() {
    }

    public static final FeedbackService getFeedbackService(Context context) {
        j.e(context, "context");
        return new FeedbackServiceImpl(context);
    }

    public static final MyProfileService getMyProfileService(Context context) {
        j.e(context, "context");
        return new MyProfileServiceImpl(context);
    }

    public static final PersonLinkService getPersonLinkService(Context context) {
        j.e(context, "context");
        return new PersonLinkServiceImpl(context);
    }

    public static final PersonRecommendService getPersonRecommendService(Context context) {
        j.e(context, "context");
        return new PersonRecommendServiceImpl(context);
    }
}
