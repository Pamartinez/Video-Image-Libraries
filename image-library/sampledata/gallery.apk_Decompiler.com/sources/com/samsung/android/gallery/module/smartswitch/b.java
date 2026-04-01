package com.samsung.android.gallery.module.smartswitch;

import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ JSONObject e;

    public /* synthetic */ b(JSONObject jSONObject, int i2) {
        this.d = i2;
        this.e = jSONObject;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        JSONObject jSONObject = this.e;
        switch (i2) {
            case 0:
                ((GalleryPreferenceEntryV2) obj).backup(jSONObject);
                return;
            case 1:
                ((SettingPreferenceEntryV2) obj).backup(jSONObject);
                return;
            default:
                ((SettingPreferenceEntryV2) obj).restore(jSONObject);
                return;
        }
    }
}
