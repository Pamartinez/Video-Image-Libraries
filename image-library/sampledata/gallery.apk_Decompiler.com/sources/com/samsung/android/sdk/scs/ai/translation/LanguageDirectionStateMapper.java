package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LanguageDirectionStateMapper {
    public static Map<LanguageDirection, LanguageDirectionState> from(Map<LanguageDirection, Integer> map) {
        HashMap hashMap = new HashMap();
        map.entrySet().forEach(new a(hashMap));
        return hashMap;
    }
}
