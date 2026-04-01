package com.samsung.android.gallery.module.publisher;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.support.blackboard.Subscriber;

/* renamed from: com.samsung.android.gallery.module.publisher.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0629i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Subscriber e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ long g;

    public /* synthetic */ C0629i(Subscriber subscriber, Object obj, long j2, int i2) {
        this.d = i2;
        this.e = subscriber;
        this.f = obj;
        this.g = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((AlbumDataPublisher) this.e).lambda$publishAlbumHideData$26((Cursor[]) this.f, this.g);
                return;
            case 1:
                ((AlbumDataPublisher) this.e).lambda$publishAlbumFileData$3((Cursor[]) this.f, this.g);
                return;
            default:
                ((DataChangeEventPublisher) this.e).lambda$init$4((Context) this.f, this.g);
                return;
        }
    }
}
