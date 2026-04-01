package androidx.media3.common;

import android.util.Pair;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface OverlaySettings {
    public static final Pair<Float, Float> DEFAULT_BACKGROUND_FRAME_ANCHOR;
    public static final Pair<Float, Float> DEFAULT_OVERLAY_FRAME_ANCHOR;
    public static final Pair<Float, Float> DEFAULT_SCALE;

    static {
        Float valueOf = Float.valueOf(0.0f);
        DEFAULT_BACKGROUND_FRAME_ANCHOR = Pair.create(valueOf, valueOf);
        DEFAULT_OVERLAY_FRAME_ANCHOR = Pair.create(valueOf, valueOf);
        Float valueOf2 = Float.valueOf(1.0f);
        DEFAULT_SCALE = Pair.create(valueOf2, valueOf2);
    }

    float getAlphaScale() {
        return 1.0f;
    }

    Pair<Float, Float> getBackgroundFrameAnchor() {
        return DEFAULT_BACKGROUND_FRAME_ANCHOR;
    }

    Pair<Float, Float> getOverlayFrameAnchor() {
        return DEFAULT_OVERLAY_FRAME_ANCHOR;
    }

    float getRotationDegrees() {
        return 0.0f;
    }

    Pair<Float, Float> getScale() {
        return DEFAULT_SCALE;
    }
}
