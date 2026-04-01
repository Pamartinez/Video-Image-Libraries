package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import A.a;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.core.math.MathUtils;
import bc.d;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradControl;
import com.samsung.android.sesl.visualeffect.utils.DeviceSettingsUtil;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 P2\u00020\u0001:\u0004PQRSB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB-\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0010\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010\"\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\u0015\u0010%\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020 ¢\u0006\u0004\b%\u0010#J\u0017\u0010'\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\nH\u0007¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020 ¢\u0006\u0004\b*\u0010#J\u0015\u0010+\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00020\u000f¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u000fH\u0002¢\u0006\u0004\b/\u0010.J\u0017\u00101\u001a\u00020 2\u0006\u00100\u001a\u00020 H\u0002¢\u0006\u0004\b1\u00102R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u00104R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u0004058\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00109\u001a\u0002088\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010H\u001a\u00020G8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048BX\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\bN\u0010O¨\u0006T"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect;", "", "Landroid/content/Context;", "context", "Landroid/view/View;", "view", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "config", "<init>", "(Landroid/content/Context;Landroid/view/View;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;)V", "", "roundRect", "(Landroid/content/Context;Landroid/view/View;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;Z)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;", "animationType", "Lme/x;", "show", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;)V", "", "customDuration", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;Ljava/lang/Long;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$HideAnimationType;", "hide", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$HideAnimationType;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;", "movement", "setLightMovement", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;)V", "", "inset", "setInsetPixel", "(I)V", "", "cornerRadius", "setCornerRadiusPixel", "(F)V", "intensity", "setLightIntensity", "isEnable", "setDefaultTouchInteraction", "(Z)V", "outlineThickness", "setOutlineThickness", "remove", "(Landroid/view/View;)V", "release", "()V", "stopControls", "default", "getMaxCornerRadius", "(F)F", "Landroid/content/Context;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "Ljava/lang/ref/WeakReference;", "weakView", "Ljava/lang/ref/WeakReference;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;", "lightControl", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager;", "animationManager", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "colorControl", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor;", "colorConfig", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "colorState", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/TouchInteractionHandler;", "touchHandler", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/TouchInteractionHandler;", "isRunning", "Z", "getView", "()Landroid/view/View;", "isAnimationOff", "()Z", "Companion", "Movement", "ShowAnimationType", "HideAnimationType", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GuidingLightEffect {
    public static final Companion Companion = new Companion((e) null);
    private AnimationManager animationManager;
    private PresetEffectColor colorConfig;
    private RadialGradControl colorControl;
    private PresetEffectColor.State colorState;
    private final GuidingLightConfig config;
    private final Context context;
    private boolean isRunning;
    private GuidingLightControl lightControl;
    private TouchInteractionHandler touchHandler;
    private final WeakReference<View> weakView;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002XT¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002XT¢\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0002XT¢\u0006\u0006\n\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Companion;", "", "<init>", "()V", "", "TAG", "Ljava/lang/String;", "", "MIN_CORNER_RADIUS", "F", "MIN_OUTER_LINE_THICKNESS", "MAX_OUTER_LINE_THICKNESS", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$HideAnimationType;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "LUMINANCE", "LUMINANCE_LONG", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum HideAnimationType {
        NONE,
        LUMINANCE,
        LUMINANCE_LONG;

        static {
            HideAnimationType[] $values;
            $ENTRIES = c.t($values);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "DEFAULT", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Movement {
        NONE,
        DEFAULT;

        static {
            Movement[] $values;
            $ENTRIES = c.t($values);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "SIZE", "LUMINANCE", "LUMINANCE_LONG", "NOW_BAR", "NOW_BAR_SHORTCUT", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ShowAnimationType {
        NONE,
        SIZE,
        LUMINANCE,
        LUMINANCE_LONG,
        NOW_BAR,
        NOW_BAR_SHORTCUT;

        static {
            ShowAnimationType[] $values;
            $ENTRIES = c.t($values);
        }
    }

    public GuidingLightEffect(Context context2, View view, GuidingLightConfig guidingLightConfig) {
        j.e(context2, "context");
        j.e(view, "view");
        j.e(guidingLightConfig, "config");
        this.context = context2;
        this.config = guidingLightConfig;
        this.weakView = new WeakReference<>(view);
        this.colorState = guidingLightConfig.getColorState();
        if (Build.VERSION.SDK_INT >= 35) {
            view.setRequestedFrameRate(-3.0f);
        }
        Log.i("GuidingLightEffect", "Create effect, version: 2.1.6 config:" + guidingLightConfig);
        int width = view.getWidth();
        int height = view.getHeight();
        boolean isClickable = view.isClickable();
        boolean z = view.getVisibility() == 0;
        StringBuilder h5 = a.h(width, height, "View size: ", "x", " clickable: ");
        h5.append(isClickable);
        h5.append(" visible: ");
        h5.append(z);
        Log.i("GuidingLightEffect", h5.toString());
        GuidingLightControl guidingLightControl = new GuidingLightControl(guidingLightConfig);
        this.lightControl = guidingLightControl;
        guidingLightControl.add(view);
        PresetEffectColor presetEffectColor = new PresetEffectColor(this.colorState);
        this.colorConfig = presetEffectColor;
        RadialGradControl build = presetEffectColor.build(context2, guidingLightConfig.getFrameRate(), guidingLightConfig.getLightMovementInterval());
        this.colorControl = build;
        this.lightControl.setColorEffect(build);
        this.animationManager = new AnimationManager(guidingLightConfig, this.lightControl);
        this.touchHandler = new TouchInteractionHandler(view, (GuidingLightRenderEffect) this.lightControl.getRenderEffect(), guidingLightConfig);
        stopControls();
    }

    private final float getMaxCornerRadius(float f) {
        View view = getView();
        if (view == null) {
            return 0.0f;
        }
        if (!(view.getWidth() == 0 || view.getHeight() == 0)) {
            f = ((float) view.getWidth()) / 2.0f;
            float height = ((float) view.getHeight()) / 2.0f;
            if (f > height) {
                return height;
            }
        }
        return f;
    }

    private final View getView() {
        return this.weakView.get();
    }

    private final boolean isAnimationOff() {
        DeviceSettingsUtil.Companion companion = DeviceSettingsUtil.Companion;
        if (companion.isBlockedByReduceAnimations(this.context) || companion.isBlockedByAnimatorDurationScale(this.context)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$2(ShowAnimationType showAnimationType, GuidingLightEffect guidingLightEffect, Long l) {
        Log.i("GuidingLightEffect", "Show Guiding Light Effect: " + showAnimationType);
        guidingLightEffect.lightControl.start();
        if (guidingLightEffect.config.getLightMovement() == Movement.DEFAULT && !guidingLightEffect.isAnimationOff()) {
            guidingLightEffect.colorControl.start();
        }
        guidingLightEffect.animationManager.showWithAnimation(showAnimationType, l, new GuidingLightEffect$show$1$1(showAnimationType, guidingLightEffect));
        guidingLightEffect.isRunning = true;
    }

    /* access modifiers changed from: private */
    public final void stopControls() {
        this.lightControl.stop();
        this.colorControl.pause(false);
    }

    public final void hide(HideAnimationType hideAnimationType) {
        j.e(hideAnimationType, "animationType");
        Log.i("GuidingLightEffect", "Hide Guiding Light Effect: " + hideAnimationType);
        if (this.isRunning) {
            this.animationManager.hideWithAnimation(hideAnimationType, new GuidingLightEffect$hide$1(this));
            if (hideAnimationType == HideAnimationType.NONE) {
                stopControls();
            }
            this.isRunning = false;
        }
    }

    public final void release() {
        Log.i("GuidingLightEffect", "release");
        stopControls();
        this.animationManager.release();
        this.touchHandler.destroy();
        this.colorConfig.destroy();
        this.lightControl.destroy();
        this.colorControl.destroy();
    }

    public final void remove(View view) {
        j.e(view, "view");
        Log.i("GuidingLightEffect", "remove view: " + view);
        this.lightControl.remove(view);
        this.colorControl.remove(view);
    }

    public final void setCornerRadiusPixel(float f) {
        float clamp = MathUtils.clamp(f, 2.0f, getMaxCornerRadius(f));
        Log.i("GuidingLightEffect", "setCornerRadiusPixel: " + clamp);
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setViewCornerRadius(clamp);
        }
    }

    public final void setDefaultTouchInteraction(boolean z) {
        this.touchHandler.setDefaultTouchInteraction(z);
    }

    public final void setInsetPixel(int i2) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setBorderWidth(new Point(i2, i2));
        }
    }

    public final void setLightIntensity(float f) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setLightIntensity(f);
        }
        this.config.setLightIntensity(f);
        this.touchHandler.updateBaseIntensity(f);
    }

    public final void setLightMovement(Movement movement) {
        j.e(movement, "movement");
        boolean z = this.isRunning;
        Log.i("GuidingLightEffect", "setLightMovement: " + movement + ", isRunning: " + z);
        this.config.setLightMovement(movement);
        if (movement == Movement.NONE) {
            this.colorControl.pause(false);
            Log.i("GuidingLightEffect", "Light movement disabled - colorControl paused");
        } else if (this.isRunning && !isAnimationOff()) {
            this.colorControl.start();
            Log.i("GuidingLightEffect", "Light movement enabled - colorControl started");
        }
    }

    public final void setOutlineThickness(float f) {
        Log.w("GuidingLightEffect", "setOutlineThickness: " + f);
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setOutlineThickness(MathUtils.clamp(f, 2.0f, 80.0f));
        }
    }

    public final void show(ShowAnimationType showAnimationType) {
        j.e(showAnimationType, "animationType");
        show(showAnimationType, (Long) null);
    }

    public final void show(ShowAnimationType showAnimationType, Long l) {
        j.e(showAnimationType, "animationType");
        View view = getView();
        if (view != null) {
            view.post(new d((Object) showAnimationType, (Object) this, (Object) l, 1));
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GuidingLightEffect(android.content.Context r34, android.view.View r35, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig r36, boolean r37) {
        /*
            r33 = this;
            r0 = r34
            r1 = r35
            java.lang.String r2 = "context"
            kotlin.jvm.internal.j.e(r0, r2)
            java.lang.String r2 = "view"
            kotlin.jvm.internal.j.e(r1, r2)
            java.lang.String r2 = "config"
            r3 = r36
            kotlin.jvm.internal.j.e(r3, r2)
            r31 = 67108863(0x3ffffff, float:1.5046327E-36)
            r32 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig r2 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.copy$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r31, r32)
            if (r37 == 0) goto L_0x004c
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$Shape r3 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.Shape.RoundRect
            goto L_0x004e
        L_0x004c:
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$Shape r3 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.Shape.Circle
        L_0x004e:
            r2.setShape(r3)
            r3 = r33
            r3.<init>(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.<init>(android.content.Context, android.view.View, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig, boolean):void");
    }
}
