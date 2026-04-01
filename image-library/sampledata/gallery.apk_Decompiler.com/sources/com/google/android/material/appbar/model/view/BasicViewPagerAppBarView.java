package com.google.android.material.appbar.model.view;

import A4.C0368c;
import T1.b;
import T1.c;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.R$interpolator;
import androidx.appcompat.widget.SeslIndicator;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001-\b'\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\fH\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0010J\u001d\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u0019\u0010\u0010R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002XD¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002XD¢\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\n \"*\u0004\u0018\u00010!0!8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001c\u0010%\u001a\n \"*\u0004\u0018\u00010!0!8\u0002X\u0004¢\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0004¢\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010+\u001a\u00020*8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010/¨\u00060"}, d2 = {"Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarView;", "Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroidx/viewpager2/widget/ViewPager2;", "viewPager", "", "index", "Lme/x;", "moveNextAndRemove", "(Landroidx/viewpager2/widget/ViewPager2;I)V", "internalRemoveItem", "(I)V", "count", "initIndicator", "addIndicator", "()V", "position", "removeIndicator", "", "animate", "removeItem", "(IZ)V", "", "deleteScaleDuration", "J", "deleteAlphaDuration", "isDeleteAnimatorRunning", "Z", "Landroid/animation/PropertyValuesHolder;", "kotlin.jvm.PlatformType", "deleteScaleX", "Landroid/animation/PropertyValuesHolder;", "deleteScaleY", "Landroid/animation/ValueAnimator;", "deleteScaleAnimator", "Landroid/animation/ValueAnimator;", "deleteAlphaAnimator", "Landroid/animation/AnimatorSet;", "deleteAnimator", "Landroid/animation/AnimatorSet;", "T1/b", "pageChangeCallback", "LT1/b;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BasicViewPagerAppBarView extends ViewPagerAppBarView {
    private final ValueAnimator deleteAlphaAnimator;
    private final long deleteAlphaDuration;
    private AnimatorSet deleteAnimator;
    private ValueAnimator deleteScaleAnimator;
    private final long deleteScaleDuration;
    private final PropertyValuesHolder deleteScaleX;
    private final PropertyValuesHolder deleteScaleY;
    /* access modifiers changed from: private */
    public boolean isDeleteAnimatorRunning;
    private final b pageChangeCallback;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicViewPagerAppBarView(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final void internalRemoveItem(int i2) {
        removeItem(i2);
        removeIndicator(i2);
    }

    /* access modifiers changed from: private */
    public final void moveNextAndRemove(ViewPager2 viewPager2, int i2) {
        int i7;
        RecyclerView.Adapter adapter = viewPager2.getAdapter();
        if (adapter != null && i2 >= 0 && i2 < adapter.getItemCount()) {
            if (i2 == viewPager2.getCurrentItem()) {
                int itemCount = adapter.getItemCount();
                if (i2 == itemCount - 1) {
                    i7 = i2 - 1;
                } else if (i2 < itemCount) {
                    i7 = i2 + 1;
                } else {
                    i7 = i2;
                }
                this.isDeleteAnimatorRunning = true;
                viewPager2.setCurrentItem(i7, true);
                viewPager2.postDelayed(new C0368c(this, i2, 15), 250);
                return;
            }
            removeItem(i2);
        }
    }

    /* access modifiers changed from: private */
    public static final void moveNextAndRemove$lambda$11$lambda$10(BasicViewPagerAppBarView basicViewPagerAppBarView, int i2) {
        j.e(basicViewPagerAppBarView, "this$0");
        basicViewPagerAppBarView.isDeleteAnimatorRunning = false;
        basicViewPagerAppBarView.removeItem(i2);
    }

    public final void addIndicator() {
        SeslIndicator indicator = getIndicator();
        if (indicator != null) {
            SeslIndicator.addIndicator$default(indicator, (Integer) null, 1, (Object) null);
        }
    }

    public final void initIndicator(int i2) {
        if (i2 > 1) {
            for (int i7 = 0; i7 < i2; i7++) {
                addIndicator();
            }
        }
        ViewPager2 viewpager = getViewpager();
        if (viewpager != null) {
            viewpager.registerOnPageChangeCallback(this.pageChangeCallback);
        }
    }

    public final void removeIndicator(int i2) {
        RecyclerView.Adapter adapter;
        SeslIndicator indicator = getIndicator();
        if (indicator != null) {
            indicator.removeIndicator(i2);
            ViewPager2 viewpager = getViewpager();
            if (viewpager != null && (adapter = viewpager.getAdapter()) != null && adapter.getItemCount() == 1) {
                indicator.removeIndicator(i2);
            }
        }
    }

    public abstract void removeItem(int i2);

    public final void removeItem(int i2, boolean z) {
        RecyclerView.Adapter adapter;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        View view;
        if (!z) {
            internalRemoveItem(i2);
            return;
        }
        ViewPager2 viewpager = getViewpager();
        if (viewpager != null && (adapter = viewpager.getAdapter()) != null && i2 >= 0 && i2 < adapter.getItemCount() && viewpager.getChildCount() >= 0) {
            View view2 = ViewGroupKt.get(viewpager, 0);
            RecyclerView recyclerView = view2 instanceof RecyclerView ? (RecyclerView) view2 : null;
            if (recyclerView == null || (findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(i2)) == null || (view = findViewHolderForAdapterPosition.itemView) == null) {
                internalRemoveItem(i2);
                return;
            }
            if (this.deleteAnimator == null) {
                if (this.deleteScaleAnimator == null) {
                    ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{this.deleteScaleX, this.deleteScaleY});
                    ofPropertyValuesHolder.setDuration(this.deleteScaleDuration);
                    ofPropertyValuesHolder.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R$interpolator.sesl_interpolator_22_25_0_1));
                    this.deleteScaleAnimator = ofPropertyValuesHolder;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                ValueAnimator valueAnimator = this.deleteScaleAnimator;
                if (valueAnimator != null) {
                    animatorSet.playTogether(C1195m.q0(valueAnimator, this.deleteAlphaAnimator));
                    this.deleteAnimator = animatorSet;
                } else {
                    j.k("deleteScaleAnimator");
                    throw null;
                }
            }
            ValueAnimator valueAnimator2 = this.deleteAlphaAnimator;
            valueAnimator2.removeAllListeners();
            valueAnimator2.addListener(new c(this, viewpager, i2));
            AnimatorSet animatorSet2 = this.deleteAnimator;
            if (animatorSet2 != null) {
                animatorSet2.setTarget(view);
                animatorSet2.start();
                return;
            }
            j.k("deleteAnimator");
            throw null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BasicViewPagerAppBarView(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicViewPagerAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        this.deleteScaleDuration = 350;
        this.deleteAlphaDuration = 150;
        this.deleteScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f, 0.9f});
        this.deleteScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f, 0.9f});
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object) null, View.ALPHA, new float[]{0.0f});
        ofFloat.setDuration(150);
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(context, R$interpolator.sesl_interpolator_0_0_1_1));
        this.deleteAlphaAnimator = ofFloat;
        this.pageChangeCallback = new b(this);
    }
}
