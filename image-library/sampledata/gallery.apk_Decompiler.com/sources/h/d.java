package H;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.MultipleInputVideoGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements GlTextureProducer.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MultipleInputVideoGraph f296a;

    public /* synthetic */ d(MultipleInputVideoGraph multipleInputVideoGraph) {
        this.f296a = multipleInputVideoGraph;
    }

    public final void onTextureRendered(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j2, long j3) {
        this.f296a.processCompositorOutputTexture(glTextureProducer, glTextureInfo, j2, j3);
    }
}
