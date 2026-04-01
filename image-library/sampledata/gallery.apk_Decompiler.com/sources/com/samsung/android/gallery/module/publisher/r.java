package com.samsung.android.gallery.module.publisher;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.module.data.DataStamp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ r(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BitmapDataPublisher) this.e).lambda$onBitmapRequested$0((String) this.f);
                return;
            case 1:
                ((DataChangeEventPublisher) this.e).lambda$onDataStamp$0((DataStamp) this.f);
                return;
            case 2:
                ListDataPublisher.lambda$publishMtpData$8((Cursor[]) this.e, (Context) this.f);
                return;
            default:
                ((SearchDataPublisherV2) this.e).lambda$publishPeopleSelectForRelation$22((String) this.f);
                return;
        }
    }
}
