package S1;

import B2.k;
import Fd.C0744a;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.animation.PathInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;
import ie.c;
import k2.h;
import k2.i;
import k2.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f788a;
    public final /* synthetic */ Object b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Object obj, Looper looper, int i2) {
        super(looper);
        this.f788a = i2;
        this.b = obj;
    }

    public final void handleMessage(Message message) {
        int i2;
        int i7 = this.f788a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                if (message.what == 100) {
                    SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = (SeslImmersiveScrollBehavior) obj;
                    if (seslImmersiveScrollBehavior.B()) {
                        CoordinatorLayout coordinatorLayout = seslImmersiveScrollBehavior.f1373I;
                        AppBarLayout appBarLayout = seslImmersiveScrollBehavior.f1372H;
                        seslImmersiveScrollBehavior.f1385g0 = -seslImmersiveScrollBehavior.f1372H.getUpNestedPreScrollRange();
                        PathInterpolator pathInterpolator = new PathInterpolator(0.17f, 0.17f, 0.2f, 1.0f);
                        float seslGetCollapsedHeight = ((float) (-seslImmersiveScrollBehavior.f1372H.getHeight())) + seslImmersiveScrollBehavior.f1372H.seslGetCollapsedHeight();
                        int[] iArr = {0};
                        ValueAnimator valueAnimator = seslImmersiveScrollBehavior.h0;
                        if (valueAnimator == null) {
                            ValueAnimator valueAnimator2 = new ValueAnimator();
                            seslImmersiveScrollBehavior.h0 = valueAnimator2;
                            valueAnimator2.addUpdateListener(new s(seslImmersiveScrollBehavior, iArr, coordinatorLayout, appBarLayout));
                        } else {
                            valueAnimator.cancel();
                        }
                        seslImmersiveScrollBehavior.h0.addListener(new k(1, seslImmersiveScrollBehavior));
                        seslImmersiveScrollBehavior.h0.setDuration(150);
                        seslImmersiveScrollBehavior.h0.setInterpolator(pathInterpolator);
                        seslImmersiveScrollBehavior.h0.setStartDelay(0);
                        ValueAnimator valueAnimator3 = seslImmersiveScrollBehavior.h0;
                        if (seslImmersiveScrollBehavior.f1383e0) {
                            i2 = -seslImmersiveScrollBehavior.f1372H.getHeight();
                        } else {
                            i2 = (int) seslGetCollapsedHeight;
                        }
                        valueAnimator3.setIntValues(new int[]{i2, (int) seslGetCollapsedHeight});
                        seslImmersiveScrollBehavior.h0.start();
                        return;
                    }
                    return;
                }
                return;
            case 1:
                og.k.m("AsServiceConnection", "msg : " + message.what, new Object[0]);
                c cVar = (c) ((C0744a) obj).e;
                if (cVar != null) {
                    int i8 = message.what;
                    if (i8 == 1) {
                        cVar.getClass();
                        og.k.m("AsImageUpscale [UPSCALE]", "onConnected to bind alive ", new Object[0]);
                        return;
                    } else if (i8 == 2) {
                        cVar.getClass();
                        og.k.m("AsImageUpscale [UPSCALE]", "onDisconnected", new Object[0]);
                        return;
                    } else if (i8 == 3) {
                        cVar.getClass();
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            default:
                if (message.what == 100) {
                    m mVar = (m) obj;
                    if (mVar.e != null) {
                        PathInterpolator pathInterpolator2 = new PathInterpolator(0.33f, 0.0f, 0.1f, 1.0f);
                        h hVar = mVar.e;
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(hVar, "y", new float[]{(float) hVar.getHeight()});
                        ofFloat.setDuration(400);
                        ofFloat.setInterpolator(pathInterpolator2);
                        ofFloat.start();
                        ofFloat.addListener(new i(mVar, pathInterpolator2));
                        return;
                    }
                    return;
                }
                return;
        }
    }
}
