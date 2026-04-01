package com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader;

import A4.I;
import Ae.b;
import Bd.C0725a;
import W8.C0579a;
import ad.d;
import android.content.Context;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewTreeObserver;
import com.samsung.android.sesl.visualeffect.utils.WeakViewHashSet;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000 W2\u00020\u0001:\u0002WXB\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0011J\u0019\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0005J\r\u0010\u0017\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0011J\u001f\u0010\u001a\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010$\u001a\u00020\b2\u000e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!H\u0004¢\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0018¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0006H\u0002¢\u0006\u0004\b*\u0010\nJ\u000f\u0010+\u001a\u00020\bH\u0002¢\u0006\u0004\b+\u0010\u0011J\u000f\u0010,\u001a\u00020\bH\u0002¢\u0006\u0004\b,\u0010\u0011J\u000f\u0010-\u001a\u00020\bH\u0002¢\u0006\u0004\b-\u0010\u0011J\u000f\u0010.\u001a\u00020\u0002H\u0002¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b0\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00101R.\u00103\u001a\u0004\u0018\u00010\u00182\b\u00102\u001a\u0004\u0018\u00010\u00188\u0016@VX\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R*\u00109\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u00028\u0006@@X\u000e¢\u0006\u0012\n\u0004\b9\u00101\u001a\u0004\b:\u0010/\"\u0004\b;\u0010\u0005R\u0014\u0010=\u001a\u00020<8\u0002X\u0004¢\u0006\u0006\n\u0004\b=\u0010>R?\u0010B\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0@0?j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0@`A8\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER?\u0010F\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0@0?j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0@`A8\u0006¢\u0006\f\n\u0004\bF\u0010C\u001a\u0004\bG\u0010ER\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u00101R$\u0010M\u001a\u00020L2\u0006\u00102\u001a\u00020L8\u0006@BX\u000e¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0004¢\u0006\u0006\n\u0004\bU\u0010V¨\u0006Y"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "", "autoUpdate", "<init>", "(Z)V", "Landroid/content/Context;", "appContext", "Lme/x;", "onInitialize", "(Landroid/content/Context;)V", "Landroid/view/View;", "view", "add", "(Landroid/view/View;)V", "remove", "tryStart", "()V", "start", "stop", "destroy", "forceUpdate", "update", "invalidate", "", "alpha", "setViewAlpha", "(Landroid/view/View;F)V", "", "width", "height", "setSize", "(II)V", "Ljava/util/function/Consumer;", "Landroid/graphics/RuntimeShader;", "updater", "updateShader", "(Ljava/util/function/Consumer;)V", "frameRate", "setFrameRate", "(F)V", "context", "initializeWithContext", "attachFrameCallback", "updateOnFrameCallback", "removeFrameCallback", "hasVisibleView", "()Z", "removeViewTreeObserverListener", "Z", "value", "requestedMaxFrameRate", "Ljava/lang/Float;", "getRequestedMaxFrameRate", "()Ljava/lang/Float;", "setRequestedMaxFrameRate", "(Ljava/lang/Float;)V", "shaderUpdated", "getShaderUpdated", "setShaderUpdated$sesl_visualeffect_INTERNAL_2_1_6_release", "Lcom/samsung/android/sesl/visualeffect/utils/WeakViewHashSet;", "targetViewSet", "Lcom/samsung/android/sesl/visualeffect/utils/WeakViewHashSet;", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Lkotlin/collections/ArrayList;", "onUpdateListeners", "Ljava/util/ArrayList;", "getOnUpdateListeners", "()Ljava/util/ArrayList;", "onUpdateListenersEachView", "getOnUpdateListenersEachView", "Landroid/view/ViewTreeObserver;", "viewTreeObserverOnAdd", "Landroid/view/ViewTreeObserver;", "initializedWithContext", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase$State;", "runningState", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase$State;", "getRunningState", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase$State;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "invalidationListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroid/view/Choreographer$FrameCallback;", "frameCallback", "Landroid/view/Choreographer$FrameCallback;", "Companion", "State", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VibeRenderEffectBase implements VibeRenderEffect {
    public static final Companion Companion = new Companion((e) null);
    private final boolean autoUpdate;
    private final Choreographer.FrameCallback frameCallback;
    private boolean initializedWithContext;
    private ViewTreeObserver.OnGlobalLayoutListener invalidationListener;
    private final ArrayList<b> onUpdateListeners;
    private final ArrayList<b> onUpdateListenersEachView;
    private Float requestedMaxFrameRate;
    private State runningState;
    private boolean shaderUpdated;
    private final WeakViewHashSet targetViewSet;
    private ViewTreeObserver viewTreeObserverOnAdd;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase$Companion;", "", "<init>", "()V", "TAG", "", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase$State;", "", "<init>", "(Ljava/lang/String;I)V", "READY", "RUNNING", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        READY,
        RUNNING;

        static {
            State[] $values;
            $ENTRIES = c.t($values);
        }
    }

    public VibeRenderEffectBase(boolean z) {
        this.autoUpdate = z;
        this.shaderUpdated = true;
        this.targetViewSet = new WeakViewHashSet();
        this.onUpdateListeners = new ArrayList<>();
        this.onUpdateListenersEachView = new ArrayList<>();
        this.runningState = State.READY;
        this.invalidationListener = new ad.b(0, this);
        this.frameCallback = new ad.c(this);
    }

    private final void attachFrameCallback() {
        if (this.runningState == State.READY) {
            this.runningState = State.RUNNING;
            Log.i("VibeRenderEffectBase", "attachFrameCallback");
            Choreographer.getInstance().postFrameCallback(this.frameCallback);
            return;
        }
        Log.i("VibeRenderEffectBase", "effect is already in running state.");
    }

    /* access modifiers changed from: private */
    public static final void destroy$lambda$6(VibeRenderEffectBase vibeRenderEffectBase, View view) {
        j.e(view, "it");
        vibeRenderEffectBase.removeViewTreeObserverListener(view);
    }

    /* access modifiers changed from: private */
    public static final void frameCallback$lambda$1(VibeRenderEffectBase vibeRenderEffectBase, long j2) {
        vibeRenderEffectBase.updateOnFrameCallback();
    }

    private final boolean hasVisibleView() {
        if (this.targetViewSet.size() <= 0 || this.targetViewSet.stream().filter(new I(12, new C0725a(11))).count() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean hasVisibleView$lambda$2(WeakReference weakReference) {
        View view;
        View view2 = (View) weakReference.get();
        if (view2 == null || view2.getVisibility() != 0 || (view = (View) weakReference.get()) == null || view.getWindowVisibility() != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean hasVisibleView$lambda$3(b bVar, Object obj) {
        return ((Boolean) bVar.invoke(obj)).booleanValue();
    }

    private final void initializeWithContext(Context context) {
        if (!this.initializedWithContext) {
            Context applicationContext = context.getApplicationContext();
            j.d(applicationContext, "getApplicationContext(...)");
            onInitialize(applicationContext);
            this.initializedWithContext = true;
        }
    }

    /* access modifiers changed from: private */
    public static final void invalidationListener$lambda$0(VibeRenderEffectBase vibeRenderEffectBase) {
        if (vibeRenderEffectBase.runningState == State.RUNNING && vibeRenderEffectBase.hasVisibleView()) {
            vibeRenderEffectBase.update(true);
        }
    }

    private final void removeFrameCallback() {
        if (this.runningState == State.RUNNING) {
            this.runningState = State.READY;
            Log.i("VibeRenderEffectBase", "removeFrameCallback");
            Choreographer.getInstance().removeFrameCallback(this.frameCallback);
            return;
        }
        Log.i("VibeRenderEffectBase", "effect is already in ready state.");
    }

    private final void removeViewTreeObserverListener(View view) {
        int i2;
        Integer num;
        ViewTreeObserver viewTreeObserver;
        int hashCode = view.getViewTreeObserver().hashCode();
        ViewTreeObserver viewTreeObserver2 = this.viewTreeObserverOnAdd;
        if (viewTreeObserver2 != null) {
            i2 = viewTreeObserver2.hashCode();
        } else {
            i2 = 0;
        }
        if (hashCode != i2) {
            Log.w("VibeRenderEffectBase", "removeOnGlobalLayoutListener: viewTreeObserver " + view.getViewTreeObserver().hashCode() + " is changed, use saved one");
            ViewTreeObserver viewTreeObserver3 = this.viewTreeObserverOnAdd;
            if (!(viewTreeObserver3 == null || !viewTreeObserver3.isAlive() || (viewTreeObserver = this.viewTreeObserverOnAdd) == null)) {
                viewTreeObserver.removeOnGlobalLayoutListener(this.invalidationListener);
            }
        } else {
            ViewTreeObserver viewTreeObserver4 = this.viewTreeObserverOnAdd;
            if (viewTreeObserver4 != null) {
                num = Integer.valueOf(viewTreeObserver4.hashCode());
            } else {
                num = null;
            }
            Log.i("VibeRenderEffectBase", "removeOnGlobalLayoutListener observer: " + num + " view: " + view.hashCode());
            if (view.getViewTreeObserver().isAlive()) {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this.invalidationListener);
            }
        }
        this.viewTreeObserverOnAdd = null;
    }

    /* access modifiers changed from: private */
    public static final void setSize$lambda$10(VibeRenderEffectBase vibeRenderEffectBase, int i2, int i7, RuntimeShader runtimeShader) {
        RuntimeShader shader = vibeRenderEffectBase.getShader();
        if (shader != null) {
            shader.setFloatUniform("uSize", (float) i2, (float) i7);
        }
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$4(View view) {
        j.e(view, "it");
        view.invalidate();
    }

    /* access modifiers changed from: private */
    public static final void stop$lambda$5(View view) {
        j.e(view, "it");
        view.setRenderEffect((RenderEffect) null);
        view.invalidate();
    }

    public static /* synthetic */ void update$default(VibeRenderEffectBase vibeRenderEffectBase, boolean z, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                z = false;
            }
            vibeRenderEffectBase.update(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: update");
    }

    /* access modifiers changed from: private */
    public static final void update$lambda$9(VibeRenderEffectBase vibeRenderEffectBase, View view) {
        j.e(view, "it");
        vibeRenderEffectBase.setSize(view.getWidth(), view.getHeight());
        vibeRenderEffectBase.setViewAlpha(view, view.getAlpha());
        for (b invoke : vibeRenderEffectBase.onUpdateListenersEachView) {
            invoke.invoke(view);
        }
        view.setRenderEffect(vibeRenderEffectBase.getRenderEffect());
    }

    private final void updateOnFrameCallback() {
        if (!this.autoUpdate) {
            return;
        }
        if (hasVisibleView()) {
            update$default(this, false, 1, (Object) null);
            if (this.runningState == State.RUNNING) {
                Float requestedMaxFrameRate2 = getRequestedMaxFrameRate();
                if (requestedMaxFrameRate2 == null || requestedMaxFrameRate2.floatValue() == 120.0f) {
                    Choreographer.getInstance().postFrameCallback(this.frameCallback);
                } else {
                    Choreographer.getInstance().postFrameCallbackDelayed(this.frameCallback, (long) (((float) 1000) / requestedMaxFrameRate2.floatValue()));
                }
            }
        } else {
            State state = this.runningState;
            Log.i("VibeRenderEffectBase", "there is no visible view state: " + state);
            removeFrameCallback();
        }
    }

    public void add(View view) {
        j.e(view, "view");
        Context context = view.getContext();
        j.d(context, "getContext(...)");
        initializeWithContext(context);
        if (this.targetViewSet.add(view) && this.viewTreeObserverOnAdd == null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.viewTreeObserverOnAdd = viewTreeObserver;
            Integer num = null;
            if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                this.viewTreeObserverOnAdd = null;
                return;
            }
            ViewTreeObserver viewTreeObserver2 = this.viewTreeObserverOnAdd;
            if (viewTreeObserver2 != null) {
                viewTreeObserver2.addOnGlobalLayoutListener(this.invalidationListener);
            }
            ViewTreeObserver viewTreeObserver3 = this.viewTreeObserverOnAdd;
            if (viewTreeObserver3 != null) {
                num = Integer.valueOf(viewTreeObserver3.hashCode());
            }
            int hashCode = view.hashCode();
            Log.i("VibeRenderEffectBase", "addOnGlobalLayoutListener observer: " + num + " view: " + hashCode);
        }
    }

    public void destroy() {
        Log.i("VibeRenderEffectBase", "destroy Render Effect ");
        stop();
        this.targetViewSet.forEachAlive(new ad.e(this, 0));
        this.invalidationListener = null;
        this.targetViewSet.clear();
        Choreographer.getInstance().removeFrameCallback(this.frameCallback);
    }

    public final ArrayList<b> getOnUpdateListeners() {
        return this.onUpdateListeners;
    }

    public Float getRequestedMaxFrameRate() {
        return this.requestedMaxFrameRate;
    }

    public final boolean getShaderUpdated() {
        return this.shaderUpdated;
    }

    public final void invalidate() {
        this.shaderUpdated = true;
    }

    public abstract void onInitialize(Context context);

    public void remove(View view) {
        j.e(view, "view");
        if (this.targetViewSet.remove(view)) {
            removeViewTreeObserverListener(view);
            view.setRenderEffect((RenderEffect) null);
            if (this.targetViewSet.size() == 0) {
                removeFrameCallback();
            }
        }
    }

    public final void setFrameRate(float f) {
        setRequestedMaxFrameRate(Float.valueOf(f));
    }

    public void setRequestedMaxFrameRate(Float f) {
        this.requestedMaxFrameRate = f;
        Log.i("VibeRenderEffectBase", "Set FrameRate to " + f);
    }

    public final void setShaderUpdated$sesl_visualeffect_INTERNAL_2_1_6_release(boolean z) {
        this.shaderUpdated = z;
    }

    public void setSize(int i2, int i7) {
        updateShader(new d(this, i2, i7));
    }

    public void setViewAlpha(View view, float f) {
        j.e(view, "view");
    }

    public void start() {
        State state = this.runningState;
        Log.i("VibeRenderEffectBase", "start - runningState: " + state);
        if (this.runningState != State.RUNNING) {
            attachFrameCallback();
        }
        this.targetViewSet.forEachAlive(new C0579a(10));
    }

    public void stop() {
        State state = this.runningState;
        Log.i("VibeRenderEffectBase", "stop - runningState: " + state);
        if (this.runningState != State.READY) {
            removeFrameCallback();
        }
        this.targetViewSet.forEachAlive(new C0579a(9));
    }

    public void tryStart() {
        if (this.runningState != State.RUNNING) {
            start();
        }
    }

    public void update(boolean z) {
        boolean z3 = z | this.shaderUpdated;
        for (b invoke : this.onUpdateListeners) {
            invoke.invoke(Boolean.valueOf(z3));
        }
        if (z3) {
            this.targetViewSet.forEachAlive(new ad.e(this, 1));
            this.shaderUpdated = false;
        }
    }

    public final void updateShader(Consumer<RuntimeShader> consumer) {
        j.e(consumer, "updater");
        invalidate();
        consumer.accept(getShader());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VibeRenderEffectBase(boolean z, int i2, e eVar) {
        this((i2 & 1) != 0 ? true : z);
    }
}
