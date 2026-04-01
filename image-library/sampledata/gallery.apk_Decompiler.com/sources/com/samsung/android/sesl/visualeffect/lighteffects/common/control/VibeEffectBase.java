package com.samsung.android.sesl.visualeffect.lighteffects.common.control;

import U9.b;
import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.VibeEffectConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffect;
import com.samsung.android.sesl.visualeffect.utils.WeakViewArrayList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u00020\u00052\u00020\u0006B\u000f\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ+\u0010\r\u001a\u0016\u0012\u0004\u0012\u00028\u0001\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\n2\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\r\u0010\u0013\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u000f¢\u0006\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR'\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u001dj\b\u0012\u0004\u0012\u00020\u0003`\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R*\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\f0\u001dj\b\u0012\u0004\u0012\u00020\f`\u001e8\u0000X\u0004¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R6\u0010%\u001a\"\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00000\u001dj\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0000`\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b%\u0010 R\u0013\u0010(\u001a\u0004\u0018\u00018\u00018F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/VibeEffectConfig;", "T", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "R", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectControl;", "", "config", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/VibeEffectConfig;)V", "Lme/i;", "", "Landroid/animation/ValueAnimator;", "build", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/VibeEffectConfig;)Lme/i;", "Lme/x;", "start", "()V", "stop", "reset", "", "pause", "(Z)V", "destroy", "Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "views", "Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "getViews", "()Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "vibeRenderEffect", "Ljava/util/ArrayList;", "getVibeRenderEffect", "()Ljava/util/ArrayList;", "animators", "getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release", "child", "getRenderEffect", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "renderEffect", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VibeEffectBase<T extends VibeEffectConfig, R extends VibeRenderEffect> implements VibeEffectControl {
    private final ArrayList<ValueAnimator> animators;
    private ArrayList<VibeEffectBase<?, ?>> child;
    private final ArrayList<VibeRenderEffect> vibeRenderEffect;
    private final WeakViewArrayList views = new WeakViewArrayList();

    public VibeEffectBase(T t) {
        j.e(t, "config");
        ArrayList<VibeRenderEffect> arrayList = new ArrayList<>();
        this.vibeRenderEffect = arrayList;
        ArrayList<ValueAnimator> arrayList2 = new ArrayList<>();
        this.animators = arrayList2;
        this.child = new ArrayList<>();
        i build = build(t);
        arrayList.add(build.d);
        List list = (List) build.e;
        if (list != null) {
            arrayList2.addAll(list);
        }
    }

    /* access modifiers changed from: private */
    public static final void destroy$lambda$15(VibeEffectBase vibeEffectBase, View view) {
        j.e(view, "it");
        VibeRenderEffect renderEffect = vibeEffectBase.getRenderEffect();
        if (renderEffect != null) {
            renderEffect.remove(view);
        }
    }

    public static /* synthetic */ void pause$default(VibeEffectBase vibeEffectBase, boolean z, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                z = false;
            }
            vibeEffectBase.pause(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: pause");
    }

    public void add(View view) {
        VibeEffectControl.DefaultImpls.add(this, view);
    }

    public abstract i build(T t);

    public final void destroy() {
        this.child.clear();
        for (ValueAnimator cancel : this.animators) {
            cancel.cancel();
        }
        this.animators.clear();
        getViews().forEachAlive(new b(20, this));
        getViews().clear();
        for (VibeRenderEffect destroy : this.vibeRenderEffect) {
            destroy.destroy();
        }
        this.vibeRenderEffect.clear();
    }

    public final ArrayList<ValueAnimator> getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release() {
        return this.animators;
    }

    public final R getRenderEffect() {
        if (this.vibeRenderEffect.size() >= 1) {
            R r = this.vibeRenderEffect.get(0);
            if (r instanceof VibeRenderEffect) {
                return (VibeRenderEffect) r;
            }
        }
        return null;
    }

    public final ArrayList<VibeRenderEffect> getVibeRenderEffect() {
        return this.vibeRenderEffect;
    }

    public WeakViewArrayList getViews() {
        return this.views;
    }

    public final void pause(boolean z) {
        if (z) {
            reset();
        }
        for (ValueAnimator pause : this.animators) {
            pause.pause();
        }
        for (VibeEffectBase pause$default : this.child) {
            pause$default(pause$default, false, 1, (Object) null);
        }
    }

    public void remove(View view) {
        VibeEffectControl.DefaultImpls.remove(this, view);
    }

    public final void reset() {
        for (ValueAnimator currentFraction : this.animators) {
            currentFraction.setCurrentFraction(0.0f);
        }
        for (VibeEffectBase reset : this.child) {
            reset.reset();
        }
    }

    public void start() {
        for (ValueAnimator start : this.animators) {
            start.start();
        }
        for (VibeRenderEffect start2 : this.vibeRenderEffect) {
            start2.start();
        }
        for (VibeEffectBase start3 : this.child) {
            start3.start();
        }
    }

    public void stop() {
        for (ValueAnimator cancel : this.animators) {
            cancel.cancel();
        }
        for (VibeRenderEffect stop : this.vibeRenderEffect) {
            stop.stop();
        }
        for (VibeEffectBase stop2 : this.child) {
            stop2.stop();
        }
    }
}
