package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.function.BooleanSupplier;

/* renamed from: com.samsung.android.gallery.module.publisher.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0641v implements BooleanSupplier {
    public final boolean getAsBoolean() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.SharingServiceEnabled);
    }
}
