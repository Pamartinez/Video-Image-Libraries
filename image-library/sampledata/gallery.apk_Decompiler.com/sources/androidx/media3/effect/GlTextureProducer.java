package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GlTextureProducer {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onTextureRendered(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j2, long j3);
    }

    void releaseOutputTexture(long j2);
}
