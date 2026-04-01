package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0635o implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BadgeDataPublisher e;

    public /* synthetic */ C0635o(BadgeDataPublisher badgeDataPublisher, int i2) {
        this.d = i2;
        this.e = badgeDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        BadgeDataPublisher badgeDataPublisher = this.e;
        switch (i2) {
            case 0:
                badgeDataPublisher.publishAllBadges(obj, bundle);
                return;
            case 1:
                badgeDataPublisher.publishNotificationsBadge(obj, bundle);
                return;
            case 2:
                badgeDataPublisher.publishStoriesBadge(obj, bundle);
                return;
            case 3:
                badgeDataPublisher.publishSharingsBadge(obj, bundle);
                return;
            default:
                badgeDataPublisher.publishBottomMenuBadge(obj, bundle);
                return;
        }
    }
}
