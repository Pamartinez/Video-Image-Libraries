package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2550a;

    public /* synthetic */ h(int i2) {
        this.f2550a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2550a) {
            case 0:
                return Boolean.valueOf(((String) obj).startsWith("location://sharing/albums/fileList/storageUsage"));
            default:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
        }
    }
}
