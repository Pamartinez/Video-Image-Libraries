package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushInfoManager extends InfoManager<PushInfoList> {
    private final Logger logger = Logger.get("PushInfoManager");
    private final UpdateApiImpl updateApi;

    public PushInfoManager(UpdateApiImpl updateApiImpl) {
        this.updateApi = updateApiImpl;
    }

    public boolean accept(PushInfoList pushInfoList) {
        PushInfoList make = make(pushInfoList);
        if (make == null || !this.updateApi.update(make)) {
            return false;
        }
        updateCache(make);
        return true;
    }

    public PushInfoList make(PushInfoList pushInfoList) {
        String str = ScspCorePreferences.get().pushInfos.get();
        if (StringUtil.isEmpty(str)) {
            return pushInfoList;
        }
        PushInfoList pushInfoList2 = new PushInfoList(str);
        List<PushInfo> pushInfoList3 = pushInfoList.getPushInfoList();
        List<PushInfo> pushInfoList4 = pushInfoList2.getPushInfoList();
        ArrayList arrayList = new ArrayList(pushInfoList4);
        for (PushInfo next : pushInfoList3) {
            Optional<PushInfo> findAny = pushInfoList4.stream().filter(new i(next, 0)).findAny();
            if (!findAny.isPresent()) {
                arrayList.add(next);
            } else {
                PushInfo pushInfo = findAny.get();
                if (!pushInfo.equalsValue(next)) {
                    arrayList.removeIf(new i(pushInfo, 1));
                    arrayList.add(next);
                }
            }
        }
        if (arrayList.equals(pushInfoList4)) {
            return null;
        }
        return new PushInfoList((List<PushInfo>) arrayList);
    }

    public void updateCache(PushInfoList pushInfoList) {
        this.logger.d(new a(1, pushInfoList));
        ScspCorePreferences.get().pushInfos.accept(pushInfoList.toJsonArray().toString());
    }
}
