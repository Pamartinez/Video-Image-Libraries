package com.samsung.android.sdk.mobileservice.social.social.result;

import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ServiceNumber {
    private final boolean mContainsMyNumber;
    private final String mMyPhoneNumber;
    private final int mServiceNumberCnt;
    private final List<String> mServiceNumberList;

    public ServiceNumber(int i2, boolean z, List<String> list, String str) {
        this.mServiceNumberCnt = i2;
        this.mContainsMyNumber = z;
        if (list == null) {
            this.mServiceNumberList = new ArrayList(1);
        } else {
            this.mServiceNumberList = list;
        }
        this.mMyPhoneNumber = str;
    }

    public boolean containsMyNumber() {
        return this.mContainsMyNumber;
    }

    public String getMyPhoneNumber() {
        return this.mMyPhoneNumber;
    }

    public int getServiceNumberCnt() {
        return this.mServiceNumberCnt;
    }

    public List<String> getServiceNumberList() {
        return this.mServiceNumberList;
    }
}
