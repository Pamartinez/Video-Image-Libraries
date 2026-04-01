package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.StringCompat;
import i.C0212a;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2581a;

    public /* synthetic */ i(int i2) {
        this.f2581a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2581a) {
            case 0:
                return DetailsItemDebugExif.lambda$showDetails$4((ExifTag) obj);
            case 1:
                return DetailsItemDebugExif.lambda$showDetails$5((MediaHelper.VideoInfo) obj);
            case 2:
                return StringCompat.valueOf((byte[]) obj, 8);
            case 3:
                return ((String) obj).replaceAll("^,|,$", "");
            default:
                return C0212a.m("[", (String) obj, "]");
        }
    }
}
