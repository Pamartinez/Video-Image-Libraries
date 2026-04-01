package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import com.google.android.material.chip.Chip;
import h2.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends o {
    public final /* synthetic */ ChipTextInputComboView d;

    public a(ChipTextInputComboView chipTextInputComboView) {
        this.d = chipTextInputComboView;
    }

    public final void afterTextChanged(Editable editable) {
        boolean isEmpty = TextUtils.isEmpty(editable);
        ChipTextInputComboView chipTextInputComboView = this.d;
        if (isEmpty) {
            chipTextInputComboView.d.setText(ChipTextInputComboView.a(chipTextInputComboView, "00"));
            return;
        }
        String a7 = ChipTextInputComboView.a(chipTextInputComboView, editable);
        Chip chip = chipTextInputComboView.d;
        if (TextUtils.isEmpty(a7)) {
            a7 = ChipTextInputComboView.a(chipTextInputComboView, "00");
        }
        chip.setText(a7);
    }
}
