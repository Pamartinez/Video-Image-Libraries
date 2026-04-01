package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.LineWidthRule;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ LineWidthRule d;
    public final /* synthetic */ LineWidthRule.AnonymousClass1 e;

    public /* synthetic */ k(LineWidthRule lineWidthRule, LineWidthRule.AnonymousClass1 r22) {
        this.d = lineWidthRule;
        this.e = r22;
    }

    public final void accept(Object obj) {
        this.d.lambda$getScore$4(this.e, (LttOcrResult.BlockInfo) obj);
    }
}
