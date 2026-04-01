package x8;

import com.samsung.android.gallery.image360.engine.texture.RenderRequestListener;
import com.samsung.android.gallery.image360.engine.view.SphericalGlView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements RenderRequestListener, SphericalGlView.GlIdleListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SphericalGlView f2752a;

    public /* synthetic */ a(SphericalGlView sphericalGlView) {
        this.f2752a = sphericalGlView;
    }

    public void a() {
        this.f2752a.requestRender();
    }
}
