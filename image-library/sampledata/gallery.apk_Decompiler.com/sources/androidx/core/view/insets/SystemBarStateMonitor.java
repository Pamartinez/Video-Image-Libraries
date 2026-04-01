package androidx.core.view.insets;

import android.content.res.Configuration;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SystemBarStateMonitor {
    /* access modifiers changed from: private */
    public final ArrayList<Callback> mCallbacks = new ArrayList<>();
    /* access modifiers changed from: private */
    public int mColorHint;
    private final View mDetector;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        void onAnimationEnd();

        void onAnimationProgress(int i2, Insets insets, RectF rectF);

        void onAnimationStart();

        void onColorHintChanged(int i2);

        void onInsetsChanged(Insets insets, Insets insets2);
    }

    public SystemBarStateMonitor(final ViewGroup viewGroup) {
        int i2;
        Insets insets = Insets.NONE;
        this.mInsets = insets;
        this.mInsetsIgnoringVisibility = insets;
        Drawable background = viewGroup.getBackground();
        if (background instanceof ColorDrawable) {
            i2 = ((ColorDrawable) background).getColor();
        } else {
            i2 = 0;
        }
        this.mColorHint = i2;
        AnonymousClass1 r0 = new View(viewGroup.getContext()) {
            public void onConfigurationChanged(Configuration configuration) {
                int i2;
                Drawable background = viewGroup.getBackground();
                if (background instanceof ColorDrawable) {
                    i2 = ((ColorDrawable) background).getColor();
                } else {
                    i2 = 0;
                }
                if (SystemBarStateMonitor.this.mColorHint != i2) {
                    int unused = SystemBarStateMonitor.this.mColorHint = i2;
                    for (int size = SystemBarStateMonitor.this.mCallbacks.size() - 1; size >= 0; size--) {
                        ((Callback) SystemBarStateMonitor.this.mCallbacks.get(size)).onColorHintChanged(i2);
                    }
                }
            }
        };
        this.mDetector = r0;
        r0.setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(r0, new a(this));
        ViewCompat.setWindowInsetsAnimationCallback(r0, new WindowInsetsAnimationCompat.Callback(0) {
            private final HashMap<WindowInsetsAnimationCompat, Integer> mAnimationSidesMap = new HashMap<>();

            private boolean animatesSystemBars(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
                if ((windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.systemBars()) != 0) {
                    return true;
                }
                return false;
            }

            public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
                if (animatesSystemBars(windowInsetsAnimationCompat)) {
                    this.mAnimationSidesMap.remove(windowInsetsAnimationCompat);
                    for (int size = SystemBarStateMonitor.this.mCallbacks.size() - 1; size >= 0; size--) {
                        ((Callback) SystemBarStateMonitor.this.mCallbacks.get(size)).onAnimationEnd();
                    }
                }
            }

            public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
                if (animatesSystemBars(windowInsetsAnimationCompat)) {
                    for (int size = SystemBarStateMonitor.this.mCallbacks.size() - 1; size >= 0; size--) {
                        ((Callback) SystemBarStateMonitor.this.mCallbacks.get(size)).onAnimationStart();
                    }
                }
            }

            public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
                RectF rectF = new RectF(1.0f, 1.0f, 1.0f, 1.0f);
                int i2 = 0;
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat = list.get(size);
                    Integer num = this.mAnimationSidesMap.get(windowInsetsAnimationCompat);
                    if (num != null) {
                        int intValue = num.intValue();
                        float alpha = windowInsetsAnimationCompat.getAlpha();
                        if ((intValue & 1) != 0) {
                            rectF.left = alpha;
                        }
                        if ((intValue & 2) != 0) {
                            rectF.top = alpha;
                        }
                        if ((intValue & 4) != 0) {
                            rectF.right = alpha;
                        }
                        if ((intValue & 8) != 0) {
                            rectF.bottom = alpha;
                        }
                        i2 |= intValue;
                    }
                }
                Insets access$200 = SystemBarStateMonitor.this.getInsets(windowInsetsCompat);
                for (int size2 = SystemBarStateMonitor.this.mCallbacks.size() - 1; size2 >= 0; size2--) {
                    ((Callback) SystemBarStateMonitor.this.mCallbacks.get(size2)).onAnimationProgress(i2, access$200, rectF);
                }
                return windowInsetsCompat;
            }

            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
                int i2;
                if (!animatesSystemBars(windowInsetsAnimationCompat)) {
                    return boundsCompat;
                }
                Insets upperBound = boundsCompat.getUpperBound();
                Insets lowerBound = boundsCompat.getLowerBound();
                if (upperBound.left != lowerBound.left) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (upperBound.top != lowerBound.top) {
                    i2 |= 2;
                }
                if (upperBound.right != lowerBound.right) {
                    i2 |= 4;
                }
                if (upperBound.bottom != lowerBound.bottom) {
                    i2 |= 8;
                }
                this.mAnimationSidesMap.put(windowInsetsAnimationCompat, Integer.valueOf(i2));
                return boundsCompat;
            }
        });
        viewGroup.addView(r0, 0);
    }

    /* access modifiers changed from: private */
    public Insets getInsets(WindowInsetsCompat windowInsetsCompat) {
        return Insets.min(windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()), windowInsetsCompat.getInsets(WindowInsetsCompat.Type.tappableElement()));
    }

    private Insets getInsetsIgnoringVisibility(WindowInsetsCompat windowInsetsCompat) {
        return Insets.min(windowInsetsCompat.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()), windowInsetsCompat.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.tappableElement()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$detachFromWindow$1() {
        ViewParent parent = this.mDetector.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.mDetector);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$new$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = getInsets(windowInsetsCompat);
        Insets insetsIgnoringVisibility = getInsetsIgnoringVisibility(windowInsetsCompat);
        if (!insets.equals(this.mInsets) || !insetsIgnoringVisibility.equals(this.mInsetsIgnoringVisibility)) {
            this.mInsets = insets;
            this.mInsetsIgnoringVisibility = insetsIgnoringVisibility;
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                this.mCallbacks.get(size).onInsetsChanged(insets, insetsIgnoringVisibility);
            }
        }
        return windowInsetsCompat;
    }

    public void addCallback(Callback callback) {
        if (!this.mCallbacks.contains(callback)) {
            this.mCallbacks.add(callback);
            callback.onInsetsChanged(this.mInsets, this.mInsetsIgnoringVisibility);
            callback.onColorHintChanged(this.mColorHint);
        }
    }

    public void detachFromWindow() {
        this.mDetector.post(new b(this));
    }

    public boolean hasCallback() {
        return !this.mCallbacks.isEmpty();
    }

    public void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }
}
