package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.blackboard.Subscriber;

/* renamed from: com.samsung.android.gallery.module.publisher.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0636p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Subscriber e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0636p(Subscriber subscriber, Object obj, Object obj2, int i2) {
        this.d = i2;
        this.e = subscriber;
        this.f = obj;
        this.g = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BadgeDataPublisher) this.e).lambda$publishAllBadges$0(this.f, (Bundle) this.g);
                return;
            case 1:
                ((ListDataPublisher) this.e).lambda$publishTimelineData$7((Cursor[]) this.f, (QueryParams) this.g);
                return;
            case 2:
                ((ListDataPublisher) this.e).lambda$publishTimelineFakeData$1((Cursor[]) this.f, (Bundle) this.g);
                return;
            case 3:
                ((SearchDataPublisher) this.e).lambda$publishAllScreenShotFiles$23((Cursor[]) this.f, (QueryParams) this.g);
                return;
            default:
                ((SearchDataPublisherV2) this.e).lambda$publishCarouselClusterData$8((Cursor[]) this.f, (String) this.g);
                return;
        }
    }
}
