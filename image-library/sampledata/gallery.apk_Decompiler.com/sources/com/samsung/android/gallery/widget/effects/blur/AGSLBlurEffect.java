package com.samsung.android.gallery.widget.effects.blur;

import Bb.g;
import android.graphics.RenderEffect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.samsung.android.gallery.support.utils.Log;
import q2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AGSLBlurEffect implements DefaultLifecycleObserver {
    private ViewTreeObserver.OnPreDrawListener mAreaChangeListener;
    private final View mAreaView;
    private final BlurModel mModel;
    private final View mTargetView;
    private final boolean mUsePreDrawListener;
    private ViewTreeObserver mViewTreeObserver;

    /* renamed from: com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$BlurType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$BlurType[] r0 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.BlurType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$BlurType = r0
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$BlurType r1 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.BlurType.SINGLE_PATH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$BlurType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$BlurType r1 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.BlurType.DUAL_PATH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$BlurType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect$BlurType r1 = com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.BlurType.GED_BLEND_MASKED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum BlurType {
        SINGLE_PATH,
        DUAL_PATH,
        GED_CHAIN_MASKED,
        GED_BLEND_MASKED
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        View mAreaView;
        BlurType mBlurType = BlurType.GED_CHAIN_MASKED;
        int mCornerRadius;
        RoundingMode mRoundingMode = RoundingMode.NONE;
        View mTargetView;
        boolean mUsePreDrawListener;

        public Builder(View view, View view2) {
            this.mTargetView = view;
            this.mAreaView = view2;
        }

        public AGSLBlurEffect build() {
            return new AGSLBlurEffect(this);
        }

        public Builder setRounding(int i2) {
            this.mRoundingMode = RoundingMode.ALL;
            this.mCornerRadius = i2;
            return this;
        }

        public Builder setTopRounding(int i2) {
            this.mRoundingMode = RoundingMode.TOP;
            this.mCornerRadius = i2;
            return this;
        }

        public Builder useDualPath() {
            this.mBlurType = BlurType.DUAL_PATH;
            return this;
        }

        public Builder usePreDrawListener() {
            this.mUsePreDrawListener = true;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RoundingMode {
        NONE(-1),
        ALL(0),
        TOP(1),
        BOTTOM(2);
        
        private final int mValue;

        private RoundingMode(int i2) {
            this.mValue = i2;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public AGSLBlurEffect(Builder builder) {
        this.mTargetView = builder.mTargetView;
        View view = builder.mAreaView;
        this.mAreaView = view;
        this.mUsePreDrawListener = builder.mUsePreDrawListener;
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$gallery$widget$effects$blur$AGSLBlurEffect$BlurType[builder.mBlurType.ordinal()];
        if (i2 == 1) {
            this.mModel = new SinglePathBlurModel(builder);
        } else if (i2 == 2) {
            this.mModel = new DualPathBlurModel(builder);
        } else if (i2 != 3) {
            this.mModel = new ChainMaskedBlurModel(builder);
        } else {
            this.mModel = new BlendMaksedBlurModel(builder);
        }
        this.mModel.mAreaViewVisible = view.isShown();
        addBlurAreaChangedListener();
    }

    private void addBlurAreaChangedListener() {
        if (this.mUsePreDrawListener) {
            LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(this.mAreaView);
            if (lifecycleOwner != null && this.mAreaView.isAttachedToWindow()) {
                lifecycleOwner.getLifecycle().addObserver(this);
                safeAttachPreDrawListener();
            }
            this.mAreaView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View view) {
                    AGSLBlurEffect.this.safeAttachPreDrawListener();
                }

                public void onViewDetachedFromWindow(View view) {
                    AGSLBlurEffect.this.safeReleasePreDrawListener();
                }
            });
            return;
        }
        this.mAreaView.addOnLayoutChangeListener(new g(13, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addBlurAreaChangedListener$0(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        onAreaChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$safeAttachPreDrawListener$1() {
        onAreaChanged();
        return true;
    }

    private void onAreaChanged() {
        int[] iArr = new int[2];
        this.mTargetView.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.mAreaView.getLocationOnScreen(iArr2);
        int width = this.mAreaView.getWidth();
        int height = this.mAreaView.getHeight();
        int i2 = iArr2[0] - iArr[0];
        int i7 = iArr2[1] - iArr[1];
        boolean isShown = this.mAreaView.isShown();
        if (this.mModel.isBlurAreaSizeChanged(width, height, i2, i7, isShown)) {
            BlurModel blurModel = this.mModel;
            blurModel.mWidth = width;
            blurModel.mHeight = height;
            blurModel.mStartX = i2;
            blurModel.mStartY = i7;
            blurModel.mAreaViewVisible = isShown;
            updateRenderEffect();
        }
    }

    /* access modifiers changed from: private */
    public void safeAttachPreDrawListener() {
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(this.mAreaView);
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        } else {
            Log.e("AGSLBlurEffect", "lifecycleOwner is NULL!");
        }
        this.mAreaChangeListener = new d(1, this);
        ViewTreeObserver viewTreeObserver = this.mAreaView.getViewTreeObserver();
        this.mViewTreeObserver = viewTreeObserver;
        viewTreeObserver.addOnPreDrawListener(this.mAreaChangeListener);
    }

    /* access modifiers changed from: private */
    public void safeReleasePreDrawListener() {
        ViewTreeObserver viewTreeObserver = this.mViewTreeObserver;
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            Log.e("AGSLBlurEffect", "removeOnPreDrawListener failed. try again. " + this.mViewTreeObserver);
            this.mAreaView.getViewTreeObserver().removeOnPreDrawListener(this.mAreaChangeListener);
        } else {
            this.mViewTreeObserver.removeOnPreDrawListener(this.mAreaChangeListener);
        }
        this.mViewTreeObserver = null;
        this.mAreaChangeListener = null;
    }

    private void updateRenderEffect() {
        if (Build.VERSION.SDK_INT >= 33) {
            BlurModel blurModel = this.mModel;
            if (blurModel.mAreaViewVisible) {
                this.mTargetView.setRenderEffect(blurModel.generateRenderEffect());
            } else {
                this.mTargetView.setRenderEffect((RenderEffect) null);
            }
        }
    }

    public void clearEffect() {
        safeReleasePreDrawListener();
        this.mTargetView.setRenderEffect((RenderEffect) null);
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Log.d("AGSLBlurEffect", "onDestroy " + lifecycleOwner);
        safeReleasePreDrawListener();
    }
}
