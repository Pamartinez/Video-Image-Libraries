package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ HashMap d;

    public /* synthetic */ a(HashMap hashMap) {
        this.d = hashMap;
    }

    public final void accept(Object obj) {
        this.d.put((LanguageDirection) ((Map.Entry) obj).getKey(), LanguageDirectionState.from(((Integer) ((Map.Entry) obj).getValue()).intValue()));
    }
}
