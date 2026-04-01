package com.samsung.android.sdk.mobileservice.social.datasync;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSyncRequest {
    private static final String BUNDLE_KEY_DATA_LIST = "data_list";
    private final List<IData> mDataList;

    public DataSyncRequest() {
        this.mDataList = new ArrayList(0);
    }

    public List<IData> getData() {
        return this.mDataList;
    }

    public String getServiceName() {
        if (this.mDataList.isEmpty()) {
            return null;
        }
        return this.mDataList.get(0).getSyncServiceName();
    }

    public Boolean isInvalid() {
        if (this.mDataList.isEmpty()) {
            return Boolean.TRUE;
        }
        for (IData isInvalid : this.mDataList) {
            if (isInvalid.isInvalid().booleanValue()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Bundle toBundle() {
        if (this.mDataList.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (IData bundle : this.mDataList) {
            arrayList.add(bundle.toBundle());
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList(BUNDLE_KEY_DATA_LIST, arrayList);
        return bundle2;
    }

    public DataSyncRequest(List<IData> list) {
        this.mDataList = new ArrayList(list);
    }
}
