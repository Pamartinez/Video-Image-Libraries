package androidx.media3.exoplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT = new RendererConfiguration(0, false);
    public final int offloadModePreferred;
    public final boolean tunneling;

    public RendererConfiguration(int i2, boolean z) {
        this.offloadModePreferred = i2;
        this.tunneling = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RendererConfiguration.class == obj.getClass()) {
            RendererConfiguration rendererConfiguration = (RendererConfiguration) obj;
            if (this.offloadModePreferred == rendererConfiguration.offloadModePreferred && this.tunneling == rendererConfiguration.tunneling) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (this.offloadModePreferred << 1) + (this.tunneling ? 1 : 0);
    }
}
