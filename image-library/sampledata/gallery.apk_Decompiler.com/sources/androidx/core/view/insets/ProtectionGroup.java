package androidx.core.view.insets;

import android.graphics.RectF;
import androidx.core.graphics.Insets;
import androidx.core.view.insets.SystemBarStateMonitor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ProtectionGroup implements SystemBarStateMonitor.Callback {
    private int mAnimationCount;
    private boolean mDisposed;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final SystemBarStateMonitor mMonitor;
    private final ArrayList<Protection> mProtections = new ArrayList<>();

    public ProtectionGroup(SystemBarStateMonitor systemBarStateMonitor, List<Protection> list) {
        Insets insets = Insets.NONE;
        this.mInsets = insets;
        this.mInsetsIgnoringVisibility = insets;
        addProtections(list, false);
        addProtections(list, true);
        systemBarStateMonitor.addCallback(this);
        this.mMonitor = systemBarStateMonitor;
    }

    private void addProtections(List<Protection> list, boolean z) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protection protection = list.get(i2);
            if (protection.occupiesCorners() == z) {
                Object controller = protection.getController();
                if (controller == null) {
                    protection.setController(this);
                    this.mProtections.add(protection);
                } else {
                    throw new IllegalStateException(protection + " is already controlled by " + controller);
                }
            }
        }
    }

    private void updateInsets() {
        Insets insets = Insets.NONE;
        for (int size = this.mProtections.size() - 1; size >= 0; size--) {
            insets = Insets.max(insets, this.mProtections.get(size).dispatchInsets(this.mInsets, this.mInsetsIgnoringVisibility, insets));
        }
    }

    public void dispose() {
        if (!this.mDisposed) {
            this.mDisposed = true;
            this.mMonitor.removeCallback(this);
            for (int size = this.mProtections.size() - 1; size >= 0; size--) {
                this.mProtections.get(size).setController((Object) null);
            }
            this.mProtections.clear();
        }
    }

    public Protection getProtection(int i2) {
        return this.mProtections.get(i2);
    }

    public void onAnimationEnd() {
        boolean z;
        int i2 = this.mAnimationCount;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        int i7 = i2 - 1;
        this.mAnimationCount = i7;
        if (z && i7 == 0) {
            updateInsets();
        }
    }

    public void onAnimationProgress(int i2, Insets insets, RectF rectF) {
        Insets insets2 = this.mInsetsIgnoringVisibility;
        for (int size = this.mProtections.size() - 1; size >= 0; size--) {
            Protection protection = this.mProtections.get(size);
            int side = protection.getSide();
            if ((side & i2) != 0) {
                protection.setSystemVisible(true);
                if (side == 1) {
                    int i7 = insets2.left;
                    if (i7 > 0) {
                        protection.setSystemInsetAmount(((float) insets.left) / ((float) i7));
                    }
                    protection.setSystemAlpha(rectF.left);
                } else if (side == 2) {
                    int i8 = insets2.top;
                    if (i8 > 0) {
                        protection.setSystemInsetAmount(((float) insets.top) / ((float) i8));
                    }
                    protection.setSystemAlpha(rectF.top);
                } else if (side == 4) {
                    int i10 = insets2.right;
                    if (i10 > 0) {
                        protection.setSystemInsetAmount(((float) insets.right) / ((float) i10));
                    }
                    protection.setSystemAlpha(rectF.right);
                } else if (side == 8) {
                    int i11 = insets2.bottom;
                    if (i11 > 0) {
                        protection.setSystemInsetAmount(((float) insets.bottom) / ((float) i11));
                    }
                    protection.setSystemAlpha(rectF.bottom);
                }
            }
        }
    }

    public void onAnimationStart() {
        this.mAnimationCount++;
    }

    public void onColorHintChanged(int i2) {
        for (int size = this.mProtections.size() - 1; size >= 0; size--) {
            this.mProtections.get(size).dispatchColorHint(i2);
        }
    }

    public void onInsetsChanged(Insets insets, Insets insets2) {
        this.mInsets = insets;
        this.mInsetsIgnoringVisibility = insets2;
        updateInsets();
    }

    public int size() {
        return this.mProtections.size();
    }
}
