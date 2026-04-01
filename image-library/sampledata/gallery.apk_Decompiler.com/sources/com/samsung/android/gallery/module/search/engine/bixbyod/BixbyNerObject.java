package com.samsung.android.gallery.module.search.engine.bixbyod;

import A.a;
import N2.j;
import com.samsung.android.gallery.support.utils.GsonTool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BixbyNerObject {
    public static BixbyNerObject unmarshal(String str) {
        if (str != null && str.length() > 0) {
            try {
                if (GsonTool.fromJsonString(BixbyNerObject.class, str) != null) {
                    throw new ClassCastException();
                }
            } catch (Exception e) {
                a.s(e, j.k("unmarshal failed [", str, "] e="), "BixbyNerObject");
            }
        }
        return null;
    }
}
