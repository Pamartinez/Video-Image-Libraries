package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EditDetailsUpdater2 f3007a;
    public final /* synthetic */ MediaItem b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f3008c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ h(EditDetailsUpdater2 editDetailsUpdater2, MediaItem mediaItem, String str, boolean z, boolean z3, boolean z7, boolean z9) {
        this.f3007a = editDetailsUpdater2;
        this.b = mediaItem;
        this.f3008c = str;
        this.d = z;
        this.e = z3;
        this.f = z7;
        this.g = z9;
    }

    public final Object apply(Object obj) {
        return this.f3007a.lambda$onSave$0(this.b, this.f3008c, this.d, this.e, this.f, this.g, (FileItemInterface) obj);
    }
}
