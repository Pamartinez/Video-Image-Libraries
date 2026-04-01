package com.samsung.android.gallery.widget.animations;

import android.animation.ValueAnimator;
import android.widget.ImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SimpleDragShrinkHandler d;
    public final /* synthetic */ ImageView e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3198h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f3199i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ float f3200j;
    public final /* synthetic */ float k;
    public final /* synthetic */ float l;
    public final /* synthetic */ float m;
    public final /* synthetic */ float n;

    public /* synthetic */ j(SimpleDragShrinkHandler simpleDragShrinkHandler, ImageView imageView, int i2, int i7, int i8, int i10, float f5, float f8, float f10, float f11, float f12) {
        this.d = simpleDragShrinkHandler;
        this.e = imageView;
        this.f = i2;
        this.g = i7;
        this.f3198h = i8;
        this.f3199i = i10;
        this.f3200j = f5;
        this.k = f8;
        this.l = f10;
        this.m = f11;
        this.n = f12;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$returnShrinkView$2(this.e, this.f, this.g, this.f3198h, this.f3199i, this.f3200j, this.k, this.l, this.m, this.n, valueAnimator);
    }
}
