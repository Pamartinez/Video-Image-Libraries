package com.samsung.android.sdk.scs.ai.extension.lts;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1658a;
    public final /* synthetic */ int b;

    public /* synthetic */ a(int i2, int i7) {
        this.f1658a = i7;
        this.b = i2;
    }

    public final boolean test(Object obj) {
        int i2 = this.f1658a;
        int i7 = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return LineChunkStrategy.lambda$canHandle$0(i7, str);
            case 1:
                return ParagraphChunkStrategy.lambda$canHandle$0(i7, str);
            default:
                return SentenceChunkStrategy.lambda$canHandle$0(i7, str);
        }
    }
}
