package com.samsung.android.gallery.widget.photoview;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3216a;

    public /* synthetic */ j(int i2) {
        this.f3216a = i2;
    }

    public final boolean test(Object obj) {
        Tile tile = (Tile) obj;
        switch (this.f3216a) {
            case 0:
                return DebugDelegate.lambda$drawDebugInfo$1(tile);
            case 1:
                return tile.visible;
            case 2:
                return ImageProcessor.lambda$hasMissingTile$4(tile);
            default:
                return ImageProcessor.lambda$hasUnloadedTile$8(tile);
        }
    }
}
