package com.samsung.android.gallery.widget.photoview;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PhotoViewPositionControl f3217a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3218c;

    public /* synthetic */ m(PhotoViewPositionControl photoViewPositionControl, int i2, int i7) {
        this.f3217a = photoViewPositionControl;
        this.b = i2;
        this.f3218c = i7;
    }

    public final boolean test(Object obj) {
        return ImageProcessor.lambda$hasMissingTile$5(this.f3217a, this.b, this.f3218c, (Tile) obj);
    }
}
