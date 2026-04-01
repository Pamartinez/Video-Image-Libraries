package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public boolean getBoolean(int i2, boolean z) {
        return this.mWrapped.getBoolean(i2, z);
    }

    public int getColor(int i2, int i7) {
        return this.mWrapped.getColor(i2, i7);
    }

    public ColorStateList getColorStateList(int i2) {
        int resourceId;
        ColorStateList colorStateList;
        if (!this.mWrapped.hasValue(i2) || (resourceId = this.mWrapped.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.mContext, resourceId)) == null) {
            return this.mWrapped.getColorStateList(i2);
        }
        return colorStateList;
    }

    public float getDimension(int i2, float f) {
        return this.mWrapped.getDimension(i2, f);
    }

    public int getDimensionPixelOffset(int i2, int i7) {
        return this.mWrapped.getDimensionPixelOffset(i2, i7);
    }

    public int getDimensionPixelSize(int i2, int i7) {
        return this.mWrapped.getDimensionPixelSize(i2, i7);
    }

    public Drawable getDrawable(int i2) {
        int resourceId;
        if (!this.mWrapped.hasValue(i2) || (resourceId = this.mWrapped.getResourceId(i2, 0)) == 0) {
            return this.mWrapped.getDrawable(i2);
        }
        return AppCompatResources.getDrawable(this.mContext, resourceId);
    }

    public Drawable getDrawableIfKnown(int i2) {
        int resourceId;
        if (!this.mWrapped.hasValue(i2) || (resourceId = this.mWrapped.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, resourceId, true);
    }

    public float getFloat(int i2, float f) {
        return this.mWrapped.getFloat(i2, f);
    }

    public Typeface getFont(int i2, int i7, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, resourceId, this.mTypedValue, i7, fontCallback);
    }

    public int getInt(int i2, int i7) {
        return this.mWrapped.getInt(i2, i7);
    }

    public int getInteger(int i2, int i7) {
        return this.mWrapped.getInteger(i2, i7);
    }

    public int getLayoutDimension(int i2, int i7) {
        return this.mWrapped.getLayoutDimension(i2, i7);
    }

    public int getResourceId(int i2, int i7) {
        return this.mWrapped.getResourceId(i2, i7);
    }

    public String getString(int i2) {
        return this.mWrapped.getString(i2);
    }

    public CharSequence getText(int i2) {
        return this.mWrapped.getText(i2);
    }

    public CharSequence[] getTextArray(int i2) {
        return this.mWrapped.getTextArray(i2);
    }

    public TypedArray getWrappedTypeArray() {
        return this.mWrapped;
    }

    public boolean hasValue(int i2) {
        return this.mWrapped.hasValue(i2);
    }

    public TypedValue peekValue(int i2) {
        return this.mWrapped.peekValue(i2);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i7) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i7));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i2, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i2, iArr));
    }
}
