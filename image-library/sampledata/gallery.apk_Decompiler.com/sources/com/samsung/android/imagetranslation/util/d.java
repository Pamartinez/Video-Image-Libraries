package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.BrokenLineDetector;
import com.samsung.android.imagetranslation.util.LineWidthRule;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                BrokenLineDetector.lambda$new$0((BrokenLineDetector.AnonymousClass1) obj2, (LttOcrResult.LineInfo) obj);
                return;
            default:
                LineWidthRule.lambda$getScore$2((LineWidthRule.AnonymousClass2) obj2, (LttOcrResult.LineInfo) obj);
                return;
        }
    }
}
