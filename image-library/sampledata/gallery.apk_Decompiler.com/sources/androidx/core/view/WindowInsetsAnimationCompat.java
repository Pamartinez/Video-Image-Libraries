package androidx.core.view;

import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;
import androidx.core.graphics.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowInsetsAnimationCompat {
    private Impl mImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BoundsCompat {
        private final Insets mLowerBound;
        private final Insets mUpperBound;

        private BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            this.mLowerBound = Impl30.getLowerBounds(bounds);
            this.mUpperBound = Impl30.getHigherBounds(bounds);
        }

        public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        private final int mDispatchMode;

        public Callback(int i2) {
            this.mDispatchMode = i2;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public abstract void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat);

        public abstract void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat);

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list);

        public abstract BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl {
        private float mAlpha = 1.0f;
        private final long mDurationMillis;
        private final Interpolator mInterpolator;
        private final int mTypeMask;

        public Impl(int i2, Interpolator interpolator, long j2) {
            this.mTypeMask = i2;
            this.mInterpolator = interpolator;
            this.mDurationMillis = j2;
        }

        public abstract float getAlpha();

        public abstract float getInterpolatedFraction();

        public abstract int getTypeMask();

        public abstract void setFraction(float f);
    }

    public WindowInsetsAnimationCompat(int i2, Interpolator interpolator, long j2) {
        this.mImpl = new Impl30(i2, interpolator, j2);
    }

    public static void setCallback(View view, Callback callback) {
        Impl30.setCallback(view, callback);
    }

    public static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public void setFraction(float f) {
        this.mImpl.setFraction(f);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl30 extends Impl {
        private final WindowInsetsAnimation mWrapped;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ProxyCallback extends WindowInsetsAnimation.Callback {
            private final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> mAnimations = new HashMap<>();
            private final Callback mCompat;
            private List<WindowInsetsAnimationCompat> mRORunningAnimations;
            private ArrayList<WindowInsetsAnimationCompat> mTmpRunningAnimations;

            public ProxyCallback(Callback callback) {
                super(callback.getDispatchMode());
                this.mCompat = callback;
            }

            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = this.mAnimations.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat windowInsetsAnimationCompat2 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(windowInsetsAnimation);
                this.mAnimations.put(windowInsetsAnimation, windowInsetsAnimationCompat2);
                return windowInsetsAnimationCompat2;
            }

            public void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(windowInsetsAnimation));
                this.mAnimations.remove(windowInsetsAnimation);
            }

            public void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                this.mCompat.onPrepare(getWindowInsetsAnimationCompat(windowInsetsAnimation));
            }

            public WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.mTmpRunningAnimations;
                if (arrayList == null) {
                    ArrayList<WindowInsetsAnimationCompat> arrayList2 = new ArrayList<>(list.size());
                    this.mTmpRunningAnimations = arrayList2;
                    this.mRORunningAnimations = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = list.get(size);
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat = getWindowInsetsAnimationCompat(windowInsetsAnimation);
                    windowInsetsAnimationCompat.setFraction(windowInsetsAnimation.getFraction());
                    this.mTmpRunningAnimations.add(windowInsetsAnimationCompat);
                }
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(windowInsets), this.mRORunningAnimations).toWindowInsets();
            }

            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                return this.mCompat.onStart(getWindowInsetsAnimationCompat(windowInsetsAnimation), BoundsCompat.toBoundsCompat(bounds)).toBounds();
            }
        }

        public Impl30(WindowInsetsAnimation windowInsetsAnimation) {
            super(0, (Interpolator) null, 0);
            this.mWrapped = windowInsetsAnimation;
        }

        public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat boundsCompat) {
            return new WindowInsetsAnimation.Bounds(boundsCompat.getLowerBound().toPlatformInsets(), boundsCompat.getUpperBound().toPlatformInsets());
        }

        public static Insets getHigherBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }

        public static Insets getLowerBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static void setCallback(View view, Callback callback) {
            ProxyCallback proxyCallback;
            if (callback != null) {
                proxyCallback = new ProxyCallback(callback);
            } else {
                proxyCallback = null;
            }
            view.setWindowInsetsAnimationCallback(proxyCallback);
        }

        public float getAlpha() {
            return this.mWrapped.getAlpha();
        }

        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        public void setFraction(float f) {
            this.mWrapped.setFraction(f);
        }

        public Impl30(int i2, Interpolator interpolator, long j2) {
            this(new WindowInsetsAnimation(i2, interpolator, j2));
        }
    }

    private WindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        this(0, (Interpolator) null, 0);
        this.mImpl = new Impl30(windowInsetsAnimation);
    }
}
