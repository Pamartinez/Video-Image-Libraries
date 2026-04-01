package ad;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onGlobalLayout() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                VibeRenderEffectBase.invalidationListener$lambda$0((VibeRenderEffectBase) obj);
                return;
            default:
                ((Toolbar) obj).lambda$seslSetTouchDelegateForToolbar$1();
                return;
        }
    }
}
