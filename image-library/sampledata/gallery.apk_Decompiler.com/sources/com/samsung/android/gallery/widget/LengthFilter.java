package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LengthFilter extends InputFilter.LengthFilter {
    private final Context mAppContext;
    private final int mMaxInputLength;
    private Toast mToast;

    public LengthFilter(Context context, int i2) {
        super(i2);
        this.mAppContext = context;
        this.mMaxInputLength = i2;
    }

    private int calculateKeepLength(Spanned spanned, int i2, int i7) {
        return this.mMaxInputLength - (spanned.length() - (i7 - i2));
    }

    private CharSequence getEditTextMaxLengthFilter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        int calculateKeepLength = calculateKeepLength(spanned, i8, i10);
        int i11 = i7 - i2;
        if (calculateKeepLength <= 0) {
            return "";
        }
        if (calculateKeepLength < i11) {
            return getSubSequence(charSequence, i2, calculateKeepLength);
        }
        return null;
    }

    private CharSequence getSubSequence(CharSequence charSequence, int i2, int i7) {
        int i8 = i7 + i2;
        try {
            if (!Character.isHighSurrogate(charSequence.charAt(i8 - 1)) || i8 - 1 != i2) {
                return charSequence.subSequence(i2, i8);
            }
            return "";
        } catch (IndexOutOfBoundsException unused) {
            return "";
        }
    }

    public CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        CharSequence editTextMaxLengthFilter = getEditTextMaxLengthFilter(charSequence, i2, i7, spanned, i8, i10);
        if (editTextMaxLengthFilter != null) {
            publishError(editTextMaxLengthFilter);
        }
        return editTextMaxLengthFilter;
    }

    public boolean publishError(CharSequence charSequence) {
        if (this.mAppContext == null) {
            return false;
        }
        showToast();
        return true;
    }

    public void showToast() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        Resources resources = this.mAppContext.getResources();
        int i2 = R$plurals.more_than_limit;
        int i7 = this.mMaxInputLength;
        Toast makeText = Toast.makeText(this.mAppContext, resources.getQuantityString(i2, i7, new Object[]{Integer.valueOf(i7)}), 0);
        this.mToast = makeText;
        makeText.show();
    }

    public LengthFilter(Context context) {
        this(context, 50);
    }
}
