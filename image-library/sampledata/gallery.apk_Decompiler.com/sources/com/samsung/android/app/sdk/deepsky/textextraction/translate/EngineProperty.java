package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import Ae.a;
import He.t;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\n\u001a\u00028\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/EngineProperty;", "T", "", "Lkotlin/Function0;", "engine", "<init>", "(LAe/a;)V", "thisRef", "LHe/t;", "property", "getValue", "(Ljava/lang/Object;LHe/t;)Ljava/lang/Object;", "LAe/a;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngineProperty<T> {
    private final a engine;

    public EngineProperty(a aVar) {
        j.e(aVar, "engine");
        this.engine = aVar;
    }

    public final T getValue(Object obj, t tVar) {
        j.e(tVar, "property");
        return this.engine.invoke();
    }
}
