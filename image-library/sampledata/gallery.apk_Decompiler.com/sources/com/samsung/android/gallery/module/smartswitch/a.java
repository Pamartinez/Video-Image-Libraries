package com.samsung.android.gallery.module.smartswitch;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3087a;

    public /* synthetic */ a(int i2) {
        this.f3087a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3087a) {
            case 0:
                return ((GalleryPreferenceEntryV2) obj).exist();
            case 1:
                return RestoreThread.lambda$restoreGalleryPreference$0((Blackboard) obj);
            default:
                return ((SettingPreferenceEntryV2) obj).support();
        }
    }
}
