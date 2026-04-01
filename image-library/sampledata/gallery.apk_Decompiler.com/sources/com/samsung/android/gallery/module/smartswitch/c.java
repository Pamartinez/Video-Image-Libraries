package com.samsung.android.gallery.module.smartswitch;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ AtomicBoolean d;
    public final /* synthetic */ JSONObject e;

    public /* synthetic */ c(AtomicBoolean atomicBoolean, JSONObject jSONObject) {
        this.d = atomicBoolean;
        this.e = jSONObject;
    }

    public final void accept(Object obj) {
        GalleryPreferenceEntryV2.lambda$restore$1(this.d, this.e, (GalleryPreferenceEntryV2) obj);
    }
}
