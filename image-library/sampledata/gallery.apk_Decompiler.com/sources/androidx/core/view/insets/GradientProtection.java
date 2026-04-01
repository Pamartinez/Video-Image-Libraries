package androidx.core.view.insets;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GradientProtection extends Protection {
    private static final float[] ALPHAS;
    private int mColor;
    private final int[] mColors;
    private final GradientDrawable mDrawable;
    private boolean mHasColor;
    private float mScale;

    static {
        float[] fArr = new float[100];
        ALPHAS = fArr;
        PathInterpolator pathInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        int length = fArr.length - 1;
        for (int i2 = length; i2 >= 0; i2--) {
            ALPHAS[i2] = pathInterpolator.getInterpolation(((float) (length - i2)) / ((float) length));
        }
    }

    public GradientProtection(int i2) {
        super(i2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.mDrawable = gradientDrawable;
        this.mColors = new int[ALPHAS.length];
        this.mColor = 0;
        this.mScale = 1.2f;
        if (i2 == 1) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        } else if (i2 == 2) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        } else if (i2 == 4) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
        } else if (i2 == 8) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        }
    }

    private void setColorInner(int i2) {
        if (this.mColor != i2) {
            this.mColor = i2;
            toColors(i2, this.mColors);
            this.mDrawable.setColors(this.mColors);
            setDrawable(this.mDrawable);
        }
    }

    private static void toColors(int i2, int[] iArr) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr[length] = Color.argb((int) (ALPHAS[length] * ((float) Color.alpha(i2))), Color.red(i2), Color.green(i2), Color.blue(i2));
        }
    }

    public void dispatchColorHint(int i2) {
        if (!this.mHasColor) {
            setColorInner(i2);
        }
    }

    public int getThickness(int i2) {
        return (int) (this.mScale * ((float) i2));
    }

    public void setColor(int i2) {
        this.mHasColor = true;
        setColorInner(i2);
    }

    public void setScale(float f) {
        if (f >= 0.0f) {
            this.mScale = f;
            updateLayout();
            return;
        }
        throw new IllegalArgumentException("Scale must not be negative.");
    }

    public GradientProtection(int i2, int i7) {
        this(i2);
        setColor(i7);
    }
}
