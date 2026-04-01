package Vf;

import Ad.f;
import Ae.d;
import android.graphics.Color;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.WiggleAnimationConfig;
import fg.b;
import fg.c;
import fg.h;
import java.util.HashMap;
import me.x;
import qe.C1232h;

/* renamed from: Vf.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0874k implements d {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0874k(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i2 = this.d;
        Object obj4 = this.e;
        switch (i2) {
            case 0:
                C1232h hVar = (C1232h) obj3;
                ((f) obj4).invoke((Throwable) obj);
                return x.f4917a;
            case 1:
                return ProcessingLightConfig.makeConfigWithAnimator$lambda$1$lambda$0((ProcessingLightConfig) obj4, (VibeEffectControl) obj, ((Float) obj2).floatValue(), ((Float) obj3).floatValue());
            case 2:
                return WiggleAnimationConfig.build$lambda$15$lambda$14((HashMap) obj4, (VibeEffectControl) obj, ((Float) obj2).floatValue(), (Color) obj3);
            case 3:
                c cVar = (c) obj4;
                Throwable th = (Throwable) obj;
                x xVar = (x) obj2;
                C1232h hVar2 = (C1232h) obj3;
                c.f4373h.set(cVar, (Object) null);
                cVar.d((Object) null);
                return x.f4917a;
            default:
                Throwable th2 = (Throwable) obj;
                x xVar2 = (x) obj2;
                C1232h hVar3 = (C1232h) obj3;
                ((h) obj4).b();
                return x.f4917a;
        }
    }

    public /* synthetic */ C0874k(c cVar, b bVar) {
        this.d = 3;
        this.e = cVar;
    }
}
