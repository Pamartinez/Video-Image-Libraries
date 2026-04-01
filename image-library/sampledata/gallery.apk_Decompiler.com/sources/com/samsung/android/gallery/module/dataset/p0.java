package com.samsung.android.gallery.module.dataset;

import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p0 implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDataSearchStories f2991a;
    public final /* synthetic */ long b;

    public /* synthetic */ p0(MediaDataSearchStories mediaDataSearchStories, long j2) {
        this.f2991a = mediaDataSearchStories;
        this.b = j2;
    }

    public final boolean test(int i2) {
        return this.f2991a.lambda$findPosition$3(this.b, i2);
    }
}
