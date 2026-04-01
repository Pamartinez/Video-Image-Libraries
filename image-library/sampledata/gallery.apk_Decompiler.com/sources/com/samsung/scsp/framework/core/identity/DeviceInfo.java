package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.framework.core.util.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceInfo {
    private String alias;
    private String osVersion;
    private String platformVersion;
    private String simMcc;
    private String simMnc;

    public DeviceInfo(String str, String str2, String str3, String str4, String str5) {
        this.simMcc = str;
        this.simMnc = str2;
        this.osVersion = str3;
        this.alias = str4;
        this.platformVersion = str5;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getPlatformVersion() {
        return this.platformVersion;
    }

    public String getSimMcc() {
        return this.simMcc;
    }

    public String getSimMnc() {
        return this.simMnc;
    }

    public boolean isEmpty() {
        if (!StringUtil.isEmpty(this.simMcc) || !StringUtil.isEmpty(this.simMnc) || !StringUtil.isEmpty(this.osVersion) || !StringUtil.isEmpty(this.alias) || !StringUtil.isEmpty(this.platformVersion)) {
            return false;
        }
        return true;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setPlatformVersion(String str) {
        this.platformVersion = str;
    }

    public void setSimMcc(String str) {
        this.simMcc = str;
    }

    public void setSimMnc(String str) {
        this.simMnc = str;
    }
}
