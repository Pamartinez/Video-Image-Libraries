package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class E2eeInfoManager extends InfoManager<String> {
    private final Logger logger = Logger.get("E2eeInfoManager");
    private final UpdateApiImpl updateApi;

    public E2eeInfoManager(UpdateApiImpl updateApiImpl) {
        this.updateApi = updateApiImpl;
    }

    public boolean accept(String str) {
        String make = make(str);
        if (make == null || !this.updateApi.update(make)) {
            return false;
        }
        updateCache(make);
        return true;
    }

    public String make(String str) {
        if (!str.equals(ScspCorePreferences.get().e2eeType.get())) {
            return str;
        }
        return null;
    }

    public void updateCache(String str) {
        this.logger.d(new l(str, 1));
        ScspCorePreferences.get().e2eeType.accept(str);
    }
}
