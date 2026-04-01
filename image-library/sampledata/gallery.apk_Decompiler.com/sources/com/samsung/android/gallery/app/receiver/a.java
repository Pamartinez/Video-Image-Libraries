package com.samsung.android.gallery.app.receiver;

import android.content.Context;
import com.samsung.android.gallery.app.receiver.SharedAlbumNotificationReceiver;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ SharedAlbumNotificationReceiver.AnonymousClass1 d;
    public final /* synthetic */ String e;
    public final /* synthetic */ Context f;

    public /* synthetic */ a(SharedAlbumNotificationReceiver.AnonymousClass1 r1, String str, Context context) {
        this.d = r1;
        this.e = str;
        this.f = context;
    }

    public final void accept(Object obj) {
        this.d.lambda$onSuccess$0(this.e, this.f, (Blackboard) obj);
    }
}
