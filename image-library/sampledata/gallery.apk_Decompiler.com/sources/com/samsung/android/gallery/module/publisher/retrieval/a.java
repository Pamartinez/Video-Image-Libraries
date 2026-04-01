package com.samsung.android.gallery.module.publisher.retrieval;

import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SQLiteRetrieval e;
    public final /* synthetic */ AtomicReference f;
    public final /* synthetic */ Consumer g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ SQLiteRetrieval.DbKeySet f3072h;

    public /* synthetic */ a(SQLiteRetrieval sQLiteRetrieval, AtomicReference atomicReference, Consumer consumer, SQLiteRetrieval.DbKeySet dbKeySet, int i2) {
        this.d = i2;
        this.e = sQLiteRetrieval;
        this.f = atomicReference;
        this.g = consumer;
        this.f3072h = dbKeySet;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getCursors$21(this.f, this.g, this.f3072h);
                return;
            case 1:
                this.e.lambda$getCursors$22(this.f, this.g, this.f3072h);
                return;
            case 2:
                this.e.lambda$getCursors$23(this.f, this.g, this.f3072h);
                return;
            case 3:
                this.e.lambda$getCursors$24(this.f, this.g, this.f3072h);
                return;
            default:
                this.e.lambda$getCursors$25(this.f, this.g, this.f3072h);
                return;
        }
    }
}
