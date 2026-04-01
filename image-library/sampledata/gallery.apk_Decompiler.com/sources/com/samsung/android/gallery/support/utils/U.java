package com.samsung.android.gallery.support.utils;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class U implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Thread f3173a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ StringJoiner f3174c;
    public final /* synthetic */ Consumer d;
    public final /* synthetic */ AtomicReference e;
    public final /* synthetic */ StringJoiner f;

    public /* synthetic */ U(Thread thread, String str, StringJoiner stringJoiner, Consumer consumer, AtomicReference atomicReference, StringJoiner stringJoiner2) {
        this.f3173a = thread;
        this.b = str;
        this.f3174c = stringJoiner;
        this.d = consumer;
        this.e = atomicReference;
        this.f = stringJoiner2;
    }

    public final void accept(Object obj, Object obj2) {
        ThreadUtil.lambda$dumpThreads$2(this.f3173a, this.b, this.f3174c, this.d, this.e, this.f, (Thread) obj, (StackTraceElement[]) obj2);
    }
}
