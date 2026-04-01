package dd;

import Ae.b;
import android.graphics.PointF;
import com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradControl;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(Object obj, int i2, int i7) {
        this.d = i7;
        this.e = obj;
        this.f = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return RadialGradControl.buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$4((RadialGradRenderEffect) this.e, this.f, ((Integer) obj).intValue());
            case 1:
                return RadialGradControl.buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$5((RadialGradRenderEffect) this.e, this.f, (PointF) obj);
            case 2:
                return RadialGradControl.buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$6((RadialGradRenderEffect) this.e, this.f, ((Float) obj).floatValue());
            default:
                return JPEGMetaWriter.reserveXMPByMove$lambda$0((JPEGMetaWriter) this.e, this.f, (FileChannel) obj);
        }
    }
}
