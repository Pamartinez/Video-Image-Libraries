package com.samsung.android.gallery.module.remotegallery;

import android.util.Pair;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ ConnectionWrap d;
    public final /* synthetic */ DataInputStream e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ AtomicInteger g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ TransferHeader f3078h;

    public /* synthetic */ b(ConnectionWrap connectionWrap, DataInputStream dataInputStream, ArrayList arrayList, AtomicInteger atomicInteger, TransferHeader transferHeader) {
        this.d = connectionWrap;
        this.e = dataInputStream;
        this.f = arrayList;
        this.g = atomicInteger;
        this.f3078h = transferHeader;
    }

    public final void accept(Object obj) {
        this.d.lambda$run$0(this.e, this.f, this.g, this.f3078h, (Pair) obj);
    }
}
