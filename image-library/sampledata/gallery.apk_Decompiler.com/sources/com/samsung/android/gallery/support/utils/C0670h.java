package com.samsung.android.gallery.support.utils;

import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.ExifMakerNoteCompat;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.UrlLookup;
import i.C0212a;
import java.util.Map;
import java.util.function.Function;

/* renamed from: com.samsung.android.gallery.support.utils.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0670h implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3177a;

    public /* synthetic */ C0670h(int i2) {
        this.f3177a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3177a) {
            case 0:
                return ((ExifMakerNoteCompat.NoteEntity) obj).toString();
            case 1:
                return new GalleryPreference((GalleryPreference.PreferenceType) obj);
            case 2:
                return GalleryPreference.getInstance((GalleryPreference.PreferenceType) obj);
            case 3:
                return GalleryPreference.PreferenceTypedValue.lambda$toList$1((Map.Entry) obj);
            case 4:
                return Logger.getSimpleName(((ServiceManager.ServiceInfoCompat) obj).serviceName);
            case 5:
                return ((ServiceManager.ServiceInfoCompat) obj).serviceName;
            case 6:
                return ((ServiceManager.ServiceInfoCompat) obj).toString();
            case 7:
                return UrlLookup.DataHolder.lambda$get$0((String) obj);
            case 8:
                return ((Uri) obj).getAuthority();
            case 9:
                return Uri.parse((String) obj);
            case 10:
                return Integer.valueOf(FileUtils.getBucketId((String) obj));
            case 11:
                return StorageInfo.getInstance((String) obj).buckets();
            case 12:
                return Features.lambda$toDebugString$1((Features) obj);
            case 13:
                return FileUtils.getVolumeName((String) obj);
            case 14:
                return ((StorageVolumeCompat) obj).name;
            case 15:
                return C0212a.m("'", (String) obj, "'");
            case 16:
                return C0212a.m("'", (String) obj, "'");
            case 17:
                return C0212a.m("'", (String) obj, "'");
            case 18:
                return Logger.lambda$toString$1((Bundle) obj);
            case 19:
                return MemoryUtils.lambda$dumpProcessMemoryStats$0((Map.Entry) obj);
            case 20:
                return NamedThreadHandler.lambda$new$1((String) obj);
            case 21:
                return PackageMonitorCompat.lambda$getPackageHolder$1((String) obj);
            case 22:
                return ((PocFeatures) obj).name();
            case 23:
                return PreferenceCache.lambda$toDebugString$0((PreferenceCache) obj);
            case 24:
                return ((PreferenceFeatures) obj).name();
            case 25:
                return ServiceManager.lambda$timeout$0((Map.Entry) obj);
            case 26:
                return SqliteCaseBuilder.lambda$build$0((String[]) obj);
            case 27:
                return new StorageInfo((String) obj);
            default:
                return StorageInfo.lambda$new$0((Integer) obj);
        }
    }
}
