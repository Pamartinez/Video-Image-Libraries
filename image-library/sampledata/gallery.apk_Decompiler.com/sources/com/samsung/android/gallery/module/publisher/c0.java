package com.samsung.android.gallery.module.publisher;

import android.content.Context;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c0 implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c0(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((QueryParams) obj).mExceptRecommendedIds = (String) obj2;
                return;
            case 1:
                ((QueryParams) obj).mRecommendedIds = (String) obj2;
                return;
            case 2:
                ((QueryParams) obj).setFileIds((String) obj2);
                return;
            case 3:
                ((QueryParams) obj).setFileIds((String) obj2);
                return;
            case 4:
                ((QueryParams) obj).setFileIds((String) obj2);
                return;
            default:
                ((BadgeDataPublisher) obj2).lambda$publishSharingsBadge$1((Context) obj);
                return;
        }
    }
}
