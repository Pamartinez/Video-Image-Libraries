package com.samsung.android.gallery.widget.animations.photostacking;

import android.animation.Animator;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.ViAnimator;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingValue;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ThrowingAnimator {
    protected final Interpolator DOWN_INTERPOLATOR = PathInterpolator.create(0.6f, 0.4f, 1.0f, 1.0f);
    protected final Interpolator UP_INTERPOLATOR = PathInterpolator.create(0.0f, 0.0f, 0.01f, 0.99f);
    protected final int mBounceDuration1 = 1000;
    protected final int mBounceDuration2 = 800;
    protected final float mBounceScale1 = 1.5f;
    protected final float mBounceScale2 = 1.1f;
    protected final List<Integer> mRandomIndexer;
    protected final ViewGroup mRootView;
    protected final float mStartScale = 0.3f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnimationInfo extends C0212a {
        private final int index;
        private final boolean isLast;
        private final long key;
        private final View view;

        public AnimationInfo(View view2, long j2, int i2, boolean z) {
            this.view = view2;
            this.key = j2;
            this.index = i2;
            this.isLast = z;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof AnimationInfo)) {
                return false;
            }
            AnimationInfo animationInfo = (AnimationInfo) obj;
            if (this.isLast == animationInfo.isLast && this.index == animationInfo.index && this.key == animationInfo.key && Objects.equals(this.view, animationInfo.view)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            boolean z = this.isLast;
            int i2 = this.index;
            long j2 = this.key;
            View view2 = this.view;
            return Objects.hashCode(view2) + C0212a.c(((Boolean.hashCode(z) * 31) + i2) * 31, 31, j2);
        }

        public int index() {
            return this.index;
        }

        public boolean isLast() {
            return this.isLast;
        }

        public long key() {
            return this.key;
        }

        public final String toString() {
            String[] strArr;
            Object[] objArr = {this.view, Long.valueOf(this.key), Integer.valueOf(this.index), Boolean.valueOf(this.isLast)};
            if ("view;key;index;isLast".length() == 0) {
                strArr = new String[0];
            } else {
                strArr = "view;key;index;isLast".split(";");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(AnimationInfo.class.getSimpleName());
            sb2.append("[");
            for (int i2 = 0; i2 < strArr.length; i2++) {
                sb2.append(strArr[i2]);
                sb2.append("=");
                sb2.append(objArr[i2]);
                if (i2 != strArr.length - 1) {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
            }
            sb2.append("]");
            return sb2.toString();
        }

        public View view() {
            return this.view;
        }
    }

    public ThrowingAnimator(ViewGroup viewGroup) {
        this.mRootView = viewGroup;
        List<Integer> list = (List) IntStream.range(0, 16).boxed().collect(Collectors.toList());
        this.mRandomIndexer = list;
        Collections.shuffle(list);
    }

    private Point calcStartXY() {
        float height = ((float) (this.mRootView.getHeight() + this.mRootView.getWidth())) * 0.8f;
        double random = ((double) ((int) (Math.random() * 16.0d))) * 0.39269908169872414d;
        return new Point((int) ((((float) Math.cos(random)) * height) + ((float) (this.mRootView.getWidth() / 2))), (int) ((height * ((float) Math.sin(random))) + ((float) (this.mRootView.getHeight() / 2))));
    }

    public ViAnimator.Builder getAnimatorBuilder(AnimationInfo animationInfo) {
        int i2;
        View view = animationInfo.view();
        ViewUtils.removeSelf(view);
        ViewUtils.addView(this.mRootView, view);
        AiProcessingEffectLayout aiProcessingEffectLayout = (AiProcessingEffectLayout) view.findViewById(R$id.vi_effect);
        if (aiProcessingEffectLayout != null) {
            aiProcessingEffectLayout.stopEffect();
        }
        Point calcStartXY = calcStartXY();
        view.setX((float) calcStartXY.x);
        view.setY((float) calcStartXY.y);
        view.bringToFront();
        Size imageViewSize = ThrowingValue.getImageViewSize(view.getContext());
        int width = (imageViewSize.getWidth() / 2) + calcStartXY.x;
        int height = (imageViewSize.getHeight() / 2) + calcStartXY.y;
        RectF viewRect = ViewUtils.getViewRect(this.mRootView.findViewById(R$id.thumbnail_area));
        float f = viewRect.left;
        int i7 = (int) (((viewRect.right - f) / 2.0f) + f);
        float f5 = viewRect.top;
        int i8 = (int) (((viewRect.bottom - f5) / 2.0f) + f5);
        if (animationInfo.isLast()) {
            i2 = animationInfo.index();
        } else {
            i2 = this.mRandomIndexer.get(animationInfo.index() % 16).intValue();
        }
        ThrowingValue.ViFixedValues fixedViValue = ThrowingValue.getFixedViValue(i2);
        float f8 = fixedViValue.distanceXFromCenter;
        float f10 = fixedViValue.distanceYFromCenter;
        float f11 = fixedViValue.rotate;
        int i10 = (int) (((float) i8) + f10);
        int p1DurationAdjustRatio = (int) (getP1DurationAdjustRatio() * 1000.0f);
        int p2DurationAdjustRatio = (int) (getP2DurationAdjustRatio(animationInfo.isLast()) * 800.0f);
        ViAnimator.Builder builder = new ViAnimator.Builder();
        int i11 = p1DurationAdjustRatio + p2DurationAdjustRatio;
        builder.addTranslateAnimator(view, 0, i11, width, height, (int) (((float) i7) + f8), i10, this.UP_INTERPOLATOR);
        int i12 = i11;
        int i13 = p1DurationAdjustRatio / 2;
        builder.addScaleAnimator(view, 0, i13, 0.3f, 1.5f, this.UP_INTERPOLATOR);
        int i14 = i13;
        int i15 = p1DurationAdjustRatio;
        builder.addScaleAnimator(view, i14, i15, 1.5f, 1.0f, this.DOWN_INTERPOLATOR);
        int i16 = i15;
        float f12 = f11;
        builder.addRotateAnimator(view, 0, i15, 0.0f, f12, (Interpolator) null);
        int i17 = i15;
        float f13 = f12;
        int i18 = (p2DurationAdjustRatio / 2) + i17;
        builder.addScaleAnimator(view, i17, i18, 1.0f, 1.1f, this.UP_INTERPOLATOR);
        int i19 = i12;
        builder.addScaleAnimator(view, i18, i19, 1.1f, 1.0f, this.DOWN_INTERPOLATOR);
        builder.addRotateAnimator(view, i17, i19, f13, f13 * 2.0f, (Interpolator) null);
        return builder;
    }

    public float getP1DurationAdjustRatio() {
        return 1.0f;
    }

    public float getP2DurationAdjustRatio(boolean z) {
        if (z) {
            return 1.5f;
        }
        return 1.0f;
    }

    public int getTotalPlayDuration() {
        return 1800;
    }

    public void play(final AnimationInfo animationInfo, final Consumer<AnimationInfo> consumer) {
        ViAnimator.Builder animatorBuilder = getAnimatorBuilder(animationInfo);
        animatorBuilder.setListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(animationInfo);
                }
            }

            public void onAnimationStart(Animator animator) {
                ViewUtils.setVisibility(animationInfo.view(), 0);
            }
        });
        animatorBuilder.build().animatorSet.start();
    }
}
