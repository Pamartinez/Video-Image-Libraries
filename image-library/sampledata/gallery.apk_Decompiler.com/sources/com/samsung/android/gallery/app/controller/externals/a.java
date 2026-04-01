package com.samsung.android.gallery.app.controller.externals;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ CreateMediaCmd d;
    public final /* synthetic */ MediaItem[] e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ EventContext f2505h;

    public /* synthetic */ a(CreateMediaCmd createMediaCmd, MediaItem[] mediaItemArr, long j2, Object[] objArr, EventContext eventContext) {
        this.d = createMediaCmd;
        this.e = mediaItemArr;
        this.f = j2;
        this.g = objArr;
        this.f2505h = eventContext;
    }

    public final void accept(Object obj) {
        this.d.lambda$onPreExecute$0(this.e, this.f, this.g, this.f2505h, (Integer) obj);
    }
}
