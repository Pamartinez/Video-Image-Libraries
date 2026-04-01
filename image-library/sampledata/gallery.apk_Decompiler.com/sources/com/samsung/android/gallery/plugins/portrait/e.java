package com.samsung.android.gallery.plugins.portrait;

import java.io.File;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LiveEffectDelegate f3098a;
    public final /* synthetic */ String b;

    public /* synthetic */ e(LiveEffectDelegate liveEffectDelegate, String str) {
        this.f3098a = liveEffectDelegate;
        this.b = str;
    }

    public final Object apply(Object obj) {
        return this.f3098a.lambda$getCachePath$10(this.b, (File) obj);
    }
}
