package com.samsung.android.gallery.module.dataset;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2956a;
    public final /* synthetic */ MediaDataMxAlbum b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2957c;

    public /* synthetic */ P(MediaDataMxAlbum mediaDataMxAlbum, int i2, int i7) {
        this.f2956a = i7;
        this.b = mediaDataMxAlbum;
        this.f2957c = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2956a) {
            case 0:
                return this.b.lambda$read$6(this.f2957c, (MediaData) obj);
            case 1:
                return this.b.lambda$read$7(this.f2957c, (MediaData) obj);
            default:
                return this.b.lambda$readCache$4(this.f2957c, (MediaData) obj);
        }
    }
}
