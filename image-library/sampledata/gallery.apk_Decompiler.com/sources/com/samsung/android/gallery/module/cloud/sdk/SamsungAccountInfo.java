package com.samsung.android.gallery.module.cloud.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.auth.AuthConstants;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungAccountInfo {
    public final String accessToken;
    public final String apiServerUrl;
    public final String countryCode;
    public final String userId;

    public SamsungAccountInfo(Bundle bundle) {
        this.accessToken = bundle.getString(AuthConstants.EXTRA_ACCESS_TOKEN);
        this.userId = bundle.getString(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID);
        this.countryCode = bundle.getString("cc");
        this.apiServerUrl = bundle.getString(AuthConstants.EXTRA_API_SERVER_URL);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SamsungAccountInfo) {
            SamsungAccountInfo samsungAccountInfo = (SamsungAccountInfo) obj;
            if (!TextUtils.equals(this.accessToken, samsungAccountInfo.accessToken) || !TextUtils.equals(this.userId, samsungAccountInfo.userId) || !TextUtils.equals(this.countryCode, samsungAccountInfo.countryCode) || !TextUtils.equals(this.apiServerUrl, samsungAccountInfo.apiServerUrl)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SamsungAccountInfo[");
        sb2.append(this.accessToken);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.userId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.countryCode);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.p(sb2, this.apiServerUrl, "]");
    }
}
