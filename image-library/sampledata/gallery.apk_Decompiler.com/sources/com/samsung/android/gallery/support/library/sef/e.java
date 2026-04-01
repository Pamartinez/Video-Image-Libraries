package com.samsung.android.gallery.support.library.sef;

import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SefParser f3145a;
    public final /* synthetic */ Function b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ StringBuilder f3146c;

    public /* synthetic */ e(SefParser sefParser, Function function, StringBuilder sb2) {
        this.f3145a = sefParser;
        this.b = function;
        this.f3146c = sb2;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3145a.lambda$toReadableString$7(this.b, this.f3146c, (String) obj, (SefData) obj2);
    }
}
