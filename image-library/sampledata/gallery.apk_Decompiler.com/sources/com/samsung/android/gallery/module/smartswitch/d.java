package com.samsung.android.gallery.module.smartswitch;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3088a;
    public final /* synthetic */ Enum b;

    public /* synthetic */ d(Enum enumR, int i2) {
        this.f3088a = i2;
        this.b = enumR;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3088a;
        Enum enumR = this.b;
        switch (i2) {
            case 0:
                return ((GalleryPreferenceEntryV2) enumR).lambda$getPreferenceFeature$2((PreferenceFeatures) obj);
            default:
                return ((SettingPreferenceEntryV2) enumR).lambda$getPreferenceFeature$0((PreferenceFeatures) obj);
        }
    }
}
