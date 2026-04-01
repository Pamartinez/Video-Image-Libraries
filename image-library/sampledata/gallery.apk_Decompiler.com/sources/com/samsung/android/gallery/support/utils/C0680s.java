package com.samsung.android.gallery.support.utils;

import android.graphics.RectF;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.gallery.support.utils.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0680s implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3186a;

    public /* synthetic */ C0680s(int i2) {
        this.f3186a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3186a) {
            case 0:
                return GalleryPreference.PreferenceTypedValue.lambda$toList$0((Map.Entry) obj);
            case 1:
                return Features.lambda$toDebugString$0((Features) obj);
            case 2:
                return FileUtils.lambda$getUsbStorageVolumes$10((StorageVolumeCompat) obj);
            case 3:
                return FileUtils.lambda$loadMountedVolumes$4((StorageVolumeCompat) obj);
            case 4:
                return ((StorageVolumeCompat) obj).isMounted();
            case 5:
                return MediaScanner.isValidPath((String) obj);
            case 6:
                return PreferenceCache.lambda$migrate$4((BooleanFeatures) obj);
            case 7:
                return PreferenceCache.lambda$migrate$5((String) obj);
            case 8:
                return Objects.nonNull((RectF) obj);
            default:
                return Objects.nonNull((SoftReference) obj);
        }
    }
}
