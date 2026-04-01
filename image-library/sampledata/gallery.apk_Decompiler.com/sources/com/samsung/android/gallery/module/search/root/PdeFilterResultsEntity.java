package com.samsung.android.gallery.module.search.root;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdeFilterResultsEntity extends FilterResultsEntity {
    public PdeFilterResultsEntity(String str, String str2, String str3) {
        super(str, str3);
        addRawLabel(str2);
    }

    public String getSelection() {
        String[] split = getRawLabels().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String str = "facet_event = ?";
        for (int i2 = 1; i2 < split.length; i2++) {
            str = C0212a.A(C0212a.A(str, " OR "), "facet_event = ?");
        }
        if (split.length > 1) {
            return C0212a.m("(", str, ")");
        }
        return str;
    }

    public JSONArray getSelectionArgs() {
        String[] split = getRawLabels().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList arrayList = new ArrayList();
        for (String add : split) {
            arrayList.add(add);
        }
        return new JSONArray(arrayList);
    }

    public boolean isPde() {
        return true;
    }
}
