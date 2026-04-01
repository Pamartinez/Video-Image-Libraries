package com.samsung.android.sesl.visualeffect.lighteffects.processinglight;

import A.a;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.samsung.android.sesl.visualeffect.utils.DomainColorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B)\b\u0017\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\rJ\r\u0010\u0012\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\rJ\u0015\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u000b¢\u0006\u0004\b\u0015\u0010\rR\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightEffect;", "", "Landroid/view/View;", "view", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "userConfig", "<init>", "(Landroid/view/View;Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;)V", "Landroid/content/Context;", "context", "(Landroid/content/Context;Landroid/view/View;Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;)V", "Lme/x;", "stopEffect", "()V", "", "getDomainColorFromView", "(Landroid/view/View;)I", "start", "stop", "remove", "(Landroid/view/View;)V", "release", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightControl;", "control", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightControl;", "config", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "Landroid/animation/ValueAnimator;", "stopAnimator", "Landroid/animation/ValueAnimator;", "", "isRunning", "Z", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessingLightEffect {
    public static final Companion Companion = new Companion((e) null);
    private ProcessingLightConfig config;
    private ProcessingLightControl control;
    private boolean isRunning;
    private ValueAnimator stopAnimator;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightEffect$Companion;", "", "<init>", "()V", "TAG", "", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ProcessingLightEffect(View view, ProcessingLightConfig processingLightConfig) {
        j.e(view, "view");
        j.e(processingLightConfig, "userConfig");
        this.config = processingLightConfig;
        if (Build.VERSION.SDK_INT >= 35) {
            view.setRequestedFrameRate(-3.0f);
        }
        if (processingLightConfig.getUseDynamicDomainColor()) {
            processingLightConfig.setDomainColor(Integer.valueOf(getDomainColorFromView(view)));
        }
        StringBuilder h5 = a.h(view.getWidth(), view.getHeight(), "initialize version: 2.1.6 view size:", "x", " config:");
        h5.append(processingLightConfig);
        Log.i("ProcessingLightEffect", h5.toString());
        ProcessingLightConfig makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release = processingLightConfig.makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release();
        this.config = makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release;
        ProcessingLightControl processingLightControl = new ProcessingLightControl(makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release);
        this.control = processingLightControl;
        processingLightControl.add(view);
        ProcessingLightControl processingLightControl2 = this.control;
        if (processingLightControl2 != null) {
            processingLightControl2.buildAnimation(this.config);
        }
        ProcessingLightControl processingLightControl3 = this.control;
        if (processingLightControl3 != null) {
            processingLightControl3.stop();
        }
    }

    private final int getDomainColorFromView(View view) {
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            return Color.parseColor("#FFFFFF");
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        view.draw(new Canvas(createBitmap));
        DomainColorUtil domainColorUtil = DomainColorUtil.INSTANCE;
        return DomainColorUtil.calibrateByLightness$default(domainColorUtil, domainColorUtil.extract(createBitmap, DomainColorUtil.ColorPalette.Palette512), (PathInterpolator) null, 2, (Object) null);
    }

    private final void stopEffect() {
        ProcessingLightControl processingLightControl = this.control;
        if (processingLightControl != null) {
            processingLightControl.stop();
        }
        ValueAnimator valueAnimator = this.stopAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.stopAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator3 = this.stopAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.removeAllListeners();
        }
    }

    public final void release() {
        Log.i("ProcessingLightEffect", "Release Processing Light Effect");
        stopEffect();
        this.config.destroy();
        ProcessingLightControl processingLightControl = this.control;
        if (processingLightControl != null) {
            processingLightControl.destroy();
        }
        this.control = null;
    }

    public final void remove(View view) {
        j.e(view, "view");
        ProcessingLightControl processingLightControl = this.control;
        if (processingLightControl != null) {
            processingLightControl.remove(view);
        }
    }

    public final void start() {
        Boolean bool;
        boolean z = this.isRunning;
        ValueAnimator valueAnimator = this.stopAnimator;
        if (valueAnimator != null) {
            bool = Boolean.valueOf(valueAnimator.isRunning());
        } else {
            bool = null;
        }
        Log.i("ProcessingLightEffect", "Start Processing Effect isRunning:" + z + " stopAnimation:" + bool);
        stopEffect();
        this.isRunning = true;
        ProcessingLightControl processingLightControl = this.control;
        if (processingLightControl != null) {
            processingLightControl.start();
        }
    }

    public final void stop() {
        Log.i("ProcessingLightEffect", "Stop Processing Effect");
        stopEffect();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ProcessingLightEffect(Context context, View view, ProcessingLightConfig processingLightConfig) {
        this(view, processingLightConfig);
        j.e(processingLightConfig, "userConfig");
        if (view != null) {
            return;
        }
        throw new IllegalArgumentException("View is null");
    }
}
