package com.samsung.android.sesl.visualeffect.surfaceeffects.ripple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0015¢\u0006\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R0\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u00168\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R*\u0010$\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010#8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/surfaceeffects/ripple/MultiRippleView;", "Landroid/view/View;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/graphics/Canvas;", "canvas", "Lme/x;", "onDraw", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Path;", "maskPath", "Landroid/graphics/Path;", "getMaskPath", "()Landroid/graphics/Path;", "setMaskPath", "(Landroid/graphics/Path;)V", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "ripples", "Ljava/util/ArrayList;", "getRipples", "()Ljava/util/ArrayList;", "getRipples$annotations", "()V", "Landroid/graphics/Paint;", "ripplePaint", "Landroid/graphics/Paint;", "", "runningAnimationCount", "I", "Ljava/util/function/Consumer;", "animationCountChangeListener", "Ljava/util/function/Consumer;", "getAnimationCountChangeListener", "()Ljava/util/function/Consumer;", "setAnimationCountChangeListener", "(Ljava/util/function/Consumer;)V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiRippleView extends View {
    private Consumer<Integer> animationCountChangeListener;
    private Path maskPath;
    private final Paint ripplePaint = new Paint();
    private final ArrayList<Object> ripples = new ArrayList<>();
    private int runningAnimationCount;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MultiRippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }

    public final Consumer<Integer> getAnimationCountChangeListener() {
        return this.animationCountChangeListener;
    }

    public final Path getMaskPath() {
        return this.maskPath;
    }

    public final ArrayList<Object> getRipples() {
        return this.ripples;
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        if (canvas.isHardwareAccelerated()) {
            if (this.runningAnimationCount != this.ripples.size()) {
                int size = this.ripples.size();
                this.runningAnimationCount = size;
                Consumer<Integer> consumer = this.animationCountChangeListener;
                if (consumer != null) {
                    consumer.accept(Integer.valueOf(size));
                }
            }
            Iterator<T> it = this.ripples.iterator();
            if (it.hasNext()) {
                throw C0212a.h(it);
            }
        }
    }

    public final void setAnimationCountChangeListener(Consumer<Integer> consumer) {
        this.animationCountChangeListener = consumer;
    }

    public final void setMaskPath(Path path) {
        this.maskPath = path;
    }

    public static /* synthetic */ void getRipples$annotations() {
    }
}
