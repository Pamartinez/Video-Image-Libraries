package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R$styleable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET = {16843518};
    static final int[] CHECKED_STATE_SET = {16842912};
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] FOCUSED_STATE_SET = {16842908};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] PRESSED_STATE_SET = {16842919};
    static final int[] SELECTED_STATE_SET = {16842913};
    private static final int[] TEMP_ARRAY = new int[1];
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();

    public static void checkAppCompatTheme(View view, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$styleable.AppCompatTheme);
        try {
            if (!obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
