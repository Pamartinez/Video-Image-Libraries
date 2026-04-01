package com.samsung.scsp.framework.core.identity;

import c0.C0086a;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.util.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceInfoManager extends InfoManager<DeviceInfo> {
    private final Logger logger = Logger.get("DeviceInfoManager");
    private final UpdateApiImpl updateApi;

    public DeviceInfoManager(UpdateApiImpl updateApiImpl) {
        this.updateApi = updateApiImpl;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$updateCache$0(DeviceInfo deviceInfo) {
        String simMcc = deviceInfo.getSimMcc();
        String simMnc = deviceInfo.getSimMnc();
        String osVersion = deviceInfo.getOsVersion();
        String alias = deviceInfo.getAlias();
        String platformVersion = deviceInfo.getPlatformVersion();
        StringBuilder q = C0086a.q("mcc: ", simMcc, ", mnc: ", simMnc, ", osVersion: ");
        C0086a.z(q, osVersion, ", alias: ", alias, ", platformVersion: ");
        q.append(platformVersion);
        return q.toString();
    }

    public boolean accept(DeviceInfo deviceInfo) {
        DeviceInfo make = make(deviceInfo);
        if (make == null || make.isEmpty() || !this.updateApi.update(make)) {
            return false;
        }
        updateCache(make);
        return true;
    }

    public DeviceInfo make(DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2 = new DeviceInfo("", "", "", "", "");
        String simMcc = deviceInfo.getSimMcc();
        if (!StringUtil.equals(ScspCorePreferences.get().simMcc.get(), simMcc)) {
            deviceInfo2.setSimMcc(simMcc);
        }
        String simMnc = deviceInfo.getSimMnc();
        if (!StringUtil.equals(ScspCorePreferences.get().simMnc.get(), simMnc)) {
            deviceInfo2.setSimMnc(simMnc);
        }
        String osVersion = deviceInfo.getOsVersion();
        if (!StringUtil.equals(ScspCorePreferences.get().osVersion.get(), osVersion)) {
            deviceInfo2.setOsVersion(osVersion);
        }
        String alias = deviceInfo.getAlias();
        if (!StringUtil.equals(ScspCorePreferences.get().deviceAlias.get(), alias)) {
            deviceInfo2.setAlias(alias);
        }
        String platformVersion = deviceInfo.getPlatformVersion();
        if (!StringUtil.equals(ScspCorePreferences.get().platformVersion.get(), platformVersion)) {
            deviceInfo2.setPlatformVersion(platformVersion);
        }
        return deviceInfo2;
    }

    public void updateCache(DeviceInfo deviceInfo) {
        this.logger.i("update preference");
        this.logger.d(new a(0, deviceInfo));
        if (!StringUtil.isEmpty(deviceInfo.getSimMcc())) {
            ScspCorePreferences.get().simMcc.accept(deviceInfo.getSimMcc());
        }
        if (!StringUtil.isEmpty(deviceInfo.getSimMnc())) {
            ScspCorePreferences.get().simMnc.accept(deviceInfo.getSimMnc());
        }
        if (!StringUtil.isEmpty(deviceInfo.getOsVersion())) {
            ScspCorePreferences.get().osVersion.accept(deviceInfo.getOsVersion());
        }
        if (!StringUtil.isEmpty(deviceInfo.getAlias())) {
            ScspCorePreferences.get().deviceAlias.accept(deviceInfo.getAlias());
        }
        if (!StringUtil.isEmpty(deviceInfo.getPlatformVersion())) {
            ScspCorePreferences.get().platformVersion.accept(deviceInfo.getPlatformVersion());
        }
    }
}
