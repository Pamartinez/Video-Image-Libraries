package com.samsung.android.gallery.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CharacterFilter implements InputFilter {
    private final Context mAppContext;
    private Toast mToast;

    public CharacterFilter(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    private void showErrorToast() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(this.mAppContext, R$string.cant_use_special_character, 0);
        this.mToast = makeText;
        makeText.show();
    }

    public CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        if (charSequence.length() <= 0 || Character.isIdentifierIgnorable(charSequence.charAt(0)) || !StringCompat.isSpecialCharacter(charSequence.charAt(0))) {
            return null;
        }
        showErrorToast();
        return "";
    }
}
