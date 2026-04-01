package com.samsung.android.gallery.support.utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.support.utils.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0679q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0679q(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((GalleryPreference) this.e).lambda$restore$6((SharedPreferences.Editor) this.f, (GalleryPreference.PreferenceTypedValue) obj);
                return;
            case 1:
                BucketUtils.lambda$initialize$2((HashSet) this.e, (HashSet) this.f, (StorageInfo.BucketHolder) obj);
                return;
            case 2:
                BucketUtils.lambda$getPredefined$4((ArrayList) this.e, (ArrayList) this.f, (StorageInfo) obj);
                return;
            default:
                Logger.lambda$toString$0((StringBuilder) this.e, (Bundle) this.f, (String) obj);
                return;
        }
    }
}
