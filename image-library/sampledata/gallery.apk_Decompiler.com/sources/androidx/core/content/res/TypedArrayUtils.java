package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TypedArrayUtils {
    public static int getAttr(Context context, int i2, int i7) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        if (typedValue.resourceId != 0) {
            return i2;
        }
        return i7;
    }

    public static boolean getBoolean(TypedArray typedArray, int i2, int i7, boolean z) {
        return typedArray.getBoolean(i2, typedArray.getBoolean(i7, z));
    }

    public static Drawable getDrawable(TypedArray typedArray, int i2, int i7) {
        Drawable drawable = typedArray.getDrawable(i2);
        if (drawable == null) {
            return typedArray.getDrawable(i7);
        }
        return drawable;
    }

    public static int getInt(TypedArray typedArray, int i2, int i7, int i8) {
        return typedArray.getInt(i2, typedArray.getInt(i7, i8));
    }

    public static boolean getNamedBoolean(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, boolean z) {
        if (!hasAttribute(xmlPullParser, str)) {
            return z;
        }
        return typedArray.getBoolean(i2, z);
    }

    public static int getNamedColor(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i7) {
        if (!hasAttribute(xmlPullParser, str)) {
            return i7;
        }
        return typedArray.getColor(i2, i7);
    }

    public static ColorStateList getNamedColorStateList(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        if (!hasAttribute(xmlPullParser, str)) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i2, typedValue);
        int i7 = typedValue.type;
        if (i7 == 2) {
            throw new UnsupportedOperationException("Failed to resolve attribute at index " + i2 + ": " + typedValue);
        } else if (i7 < 28 || i7 > 31) {
            return ColorStateListInflaterCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
        } else {
            return getNamedColorStateListFromInt(typedValue);
        }
    }

    private static ColorStateList getNamedColorStateListFromInt(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static ComplexColorCompat getNamedComplexColor(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2, int i7) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i8 = typedValue.type;
            if (i8 >= 28 && i8 <= 31) {
                return ComplexColorCompat.from(typedValue.data);
            }
            ComplexColorCompat inflate = ComplexColorCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            if (inflate != null) {
                return inflate;
            }
        }
        return ComplexColorCompat.from(i7);
    }

    public static float getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f) {
        if (!hasAttribute(xmlPullParser, str)) {
            return f;
        }
        return typedArray.getFloat(i2, f);
    }

    public static int getNamedInt(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i7) {
        if (!hasAttribute(xmlPullParser, str)) {
            return i7;
        }
        return typedArray.getInt(i2, i7);
    }

    public static int getResourceId(TypedArray typedArray, int i2, int i7, int i8) {
        return typedArray.getResourceId(i2, typedArray.getResourceId(i7, i8));
    }

    public static String getString(TypedArray typedArray, int i2, int i7) {
        String string = typedArray.getString(i2);
        if (string == null) {
            return typedArray.getString(i7);
        }
        return string;
    }

    public static CharSequence getText(TypedArray typedArray, int i2, int i7) {
        CharSequence text = typedArray.getText(i2);
        if (text == null) {
            return typedArray.getText(i7);
        }
        return text;
    }

    public static CharSequence[] getTextArray(TypedArray typedArray, int i2, int i7) {
        CharSequence[] textArray = typedArray.getTextArray(i2);
        if (textArray == null) {
            return typedArray.getTextArray(i7);
        }
        return textArray;
    }

    public static boolean hasAttribute(XmlPullParser xmlPullParser, String str) {
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null) {
            return true;
        }
        return false;
    }

    public static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
