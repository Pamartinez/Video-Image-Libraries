package androidx.core.widget;

import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SeslScrollable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslOnGoToTopClickListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslScrollBarOffsetChangedListener {
    }

    void seslForceTopFadingEdgeClamped(int i2);

    Rect seslGetAvailableBounds();

    int seslGetGoToTopDefaultBottomPadding();

    void seslSetAvailableBounds(Rect rect);

    void seslSetBottomScrollOffset(int i2);

    void seslSetFloatingBottomLayoutHeight(int i2);

    void seslSetGoToTopBottomPadding(int i2);

    void seslSetHoverBottomPadding(int i2);

    void seslSetHoverTopPadding(int i2);

    void seslSetScrollBarBottomOffset(int i2);

    void seslSetScrollBarTopOffset(int i2);

    void seslSetScrollBarOffsetChangedListener(SeslScrollBarOffsetChangedListener seslScrollBarOffsetChangedListener) {
    }
}
