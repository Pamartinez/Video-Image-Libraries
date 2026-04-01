package com.samsung.scsp.common;

import M8.a;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushVoFactory {
    private static final PushVo DEFAULT_PUSH_VO;

    static {
        PushVo pushVo = new PushVo();
        DEFAULT_PUSH_VO = pushVo;
        pushVo.category = "none";
    }

    public static PushVo create(String str) {
        return (PushVo) FaultBarrier.get(new a(str, 8), DEFAULT_PUSH_VO).obj;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JsonObject lambda$create$0(PushVo pushVo) {
        return (JsonObject) new Gson().fromJson(pushVo.dataValue, JsonObject.class);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PushVo lambda$create$1(String str) {
        PushVo pushVo = (PushVo) new Gson().fromJson(str, PushVo.class);
        if (!StringUtil.isEmpty(pushVo.dataValue)) {
            pushVo.data = (JsonObject) FaultBarrier.get(new b(pushVo), null).obj;
        }
        return pushVo;
    }

    public static PushVo create(Map<String, String> map) {
        return create(new Gson().toJson((Object) new HashMap(map)));
    }
}
