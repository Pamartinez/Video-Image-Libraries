package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TimedGlTextureInfo {
    public final GlTextureInfo glTextureInfo;
    public final long presentationTimeUs;

    public TimedGlTextureInfo(GlTextureInfo glTextureInfo2, long j2) {
        this.glTextureInfo = glTextureInfo2;
        this.presentationTimeUs = j2;
    }
}
