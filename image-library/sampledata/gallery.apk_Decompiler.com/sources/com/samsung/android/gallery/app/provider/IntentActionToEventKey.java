package com.samsung.android.gallery.app.provider;

import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IntentActionToEventKey {
    private static final HashMap<String, Integer> sMap = new HashMap<String, Integer>() {
        {
            put("com.samsung.android.video.action_share_current_frame_info", 10001);
            put("com.sec.android.app.vepreload.singleedit.action.saved", 10003);
            put("com.sec.android.app.vepreload.singleedit.action.canceled", 10003);
            put("com.samsung.app.slowmotion.action.saved", 10003);
            put("com.samsung.app.slowmotion.action.canceled", 10003);
            put("com.samsung.app.newtrim.action.saved", 10003);
            put("com.samsung.app.newtrim.action.canceled", 10003);
            put("com.sec.android.mimage.photoretouching.action.saved", 10002);
            put("com.sec.android.mimage.photoretouching.action.canceled", 10002);
        }
    };

    public static Integer get(String str) {
        return sMap.get(str);
    }
}
