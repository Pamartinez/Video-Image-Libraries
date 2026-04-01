package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import com.samsung.android.gallery.support.R$styleable;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FontUtils {
    public static float getUpToLargeScaleSize(Context context, float f) {
        return getUpToSize(context, f, 1.3f);
    }

    private static float getUpToSize(Context context, float f, float f5) {
        float f8 = context.getResources().getConfiguration().fontScale;
        if (f8 > f5) {
            return (f / f8) * f5;
        }
        return f;
    }

    private static void resize(Context context, TextView textView, float f, float f5) {
        if (textView != null && context != null) {
            if (f <= 0.0f) {
                f = textView.getTextSize();
            }
            textView.setTextSize(0, getUpToSize(context, f, f5));
        }
    }

    public static void resizeUpToExtraLarge(Context context, TextView textView) {
        resize(context, textView, -1.0f, 1.4f);
    }

    public static void resizeUpToLarge(Context context, TextView textView, float f) {
        resize(context, textView, f, 1.3f);
    }

    public static void setUpToLargeAttr(TextView textView, AttributeSet attributeSet) {
        if (attributeSet != null) {
            Context context = textView.getContext();
            Resources resources = textView.getResources();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.UpToLargeTextView);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.UpToLargeTextView_resizableTextSize, 0);
            if (dimensionPixelSize != 0) {
                if (supportUpToLarge()) {
                    textView.setTextSize(0, getUpToLargeScaleSize(context, (float) dimensionPixelSize));
                } else {
                    textView.setTextSize(1, (((float) dimensionPixelSize) / resources.getDisplayMetrics().density) / resources.getConfiguration().fontScale);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static boolean supportUpToLarge() {
        return PreferenceFeatures.OneUi6x.IS_ONE_UI_60;
    }
}
