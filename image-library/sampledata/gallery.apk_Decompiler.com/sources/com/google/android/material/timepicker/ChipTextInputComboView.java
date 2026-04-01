package com.google.android.material.timepicker;

import D5.a;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ChipTextInputComboView extends FrameLayout implements Checkable {
    public final Chip d;
    public final EditText e;

    public ChipTextInputComboView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        LayoutInflater from = LayoutInflater.from(context);
        Chip chip = (Chip) from.inflate(R.layout.material_time_chip, this, false);
        this.d = chip;
        chip.setAccessibilityClassName("android.view.View");
        TextInputLayout textInputLayout = (TextInputLayout) from.inflate(R.layout.material_time_input, this, false);
        EditText editText = textInputLayout.getEditText();
        this.e = editText;
        editText.setVisibility(4);
        editText.addTextChangedListener(new a(this));
        editText.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
        addView(chip);
        addView(textInputLayout);
        editText.setId(ViewCompat.generateViewId());
        ViewCompat.setLabelFor((TextView) findViewById(R.id.material_label), editText.getId());
        editText.setSaveEnabled(false);
        editText.setLongClickable(false);
    }

    public static String a(ChipTextInputComboView chipTextInputComboView, CharSequence charSequence) {
        try {
            return String.format(chipTextInputComboView.getResources().getConfiguration().locale, "%02d", new Object[]{Integer.valueOf(Integer.parseInt(String.valueOf(charSequence)))});
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public final boolean isChecked() {
        return this.d.isChecked();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.e.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
    }

    public final void setChecked(boolean z) {
        int i2;
        Chip chip = this.d;
        chip.setChecked(z);
        int i7 = 0;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        EditText editText = this.e;
        editText.setVisibility(i2);
        if (z) {
            i7 = 8;
        }
        chip.setVisibility(i7);
        if (chip.isChecked()) {
            editText.requestFocus();
            editText.post(new a(editText, 6));
        }
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public final void setTag(int i2, Object obj) {
        this.d.setTag(i2, obj);
    }

    public final void toggle() {
        this.d.toggle();
    }
}
