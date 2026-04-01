package com.samsung.android.gallery.support.search;

import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StringJoiner f3152a;
    public final /* synthetic */ IntelligentSearchIndex.IndexMode b;

    public /* synthetic */ a(StringJoiner stringJoiner, IntelligentSearchIndex.IndexMode indexMode) {
        this.f3152a = stringJoiner;
        this.b = indexMode;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3152a.add("SI{" + this.b + ',' + ((String) obj) + ',' + ((String) obj2) + '}');
    }
}
