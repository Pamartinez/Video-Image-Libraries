package com.samsung.android.sdk.bixby2.data;

import com.samsung.android.sdk.bixby2.action.ActionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CapsuleAction {
    ActionHandler actionHandler;
    boolean isExported;

    public CapsuleAction(ActionHandler actionHandler2, boolean z) {
        this.actionHandler = actionHandler2;
        this.isExported = z;
    }

    public ActionHandler getActionHandler() {
        return this.actionHandler;
    }

    public boolean isExported() {
        return this.isExported;
    }
}
