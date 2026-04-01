package com.samsung.android.sdk.bixby2.action;

import android.content.Context;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActionHandler {
    public static final String ACTION_BACKGROUND = "background";
    public static final String ACTION_PUNCH_OUT = "punchOut";
    public static final String ACTION_TYPE = "actionType";
    public static final String PARAMS = "params";

    public abstract void executeAction(Context context, String str, Bundle bundle, ResponseCallback responseCallback);
}
