package com.samsung.scsp.framework.core.identity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PushInfoSupplier {
    boolean compare() {
        return ScspCorePreferences.get().pushInfos.get().equals(new PushInfoList(getPushInfo()).toJsonArray().toString());
    }

    PushInfo[] getPushInfo();

    boolean has() {
        if (getPushInfo() == null || getPushInfo().length <= 0) {
            return false;
        }
        return true;
    }

    void update() {
        ScspIdentity.updatePush();
    }
}
