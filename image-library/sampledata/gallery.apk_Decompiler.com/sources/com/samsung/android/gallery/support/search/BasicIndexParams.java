package com.samsung.android.gallery.support.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BasicIndexParams implements IndexParams {
    private final ArrayList<String> mIds;
    private final Map<String, String> mValueMap;

    public BasicIndexParams(Collection<String> collection, Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.mIds = arrayList;
        HashMap hashMap = new HashMap();
        this.mValueMap = hashMap;
        arrayList.addAll(collection);
        hashMap.putAll(map);
    }

    public String getSelection() {
        return "_id";
    }

    public String[] getSelectionArgs() {
        return (String[]) this.mIds.toArray(new String[0]);
    }

    public Map<String, String> getValueMap() {
        return this.mValueMap;
    }
}
