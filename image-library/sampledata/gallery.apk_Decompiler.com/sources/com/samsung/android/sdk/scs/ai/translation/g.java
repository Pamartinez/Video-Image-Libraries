package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1664a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g(int i2, Object obj) {
        this.f1664a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f1664a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((LanguageDirection) obj).getSourceLanguage().equals((String) obj2);
            default:
                return NeuralTranslator.lambda$getAvailableLanguageDirectionStringList$9((LanguageDirectionState) obj2, (Map.Entry) obj);
        }
    }
}
