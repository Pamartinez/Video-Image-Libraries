package androidx.media3.common;

import androidx.media3.common.util.Size;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoCompositorSettings {
    public static final VideoCompositorSettings DEFAULT = new VideoCompositorSettings() {
        public Size getOutputSize(List<Size> list) {
            return list.get(0);
        }

        public OverlaySettings getOverlaySettings(int i2, long j2) {
            return new OverlaySettings() {
            };
        }
    };

    Size getOutputSize(List<Size> list);

    OverlaySettings getOverlaySettings(int i2, long j2);
}
