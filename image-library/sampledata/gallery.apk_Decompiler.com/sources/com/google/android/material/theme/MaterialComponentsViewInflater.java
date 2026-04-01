package com.google.android.material.theme;

import B2.w;
import D2.a;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.CompoundButtonCompat;
import b2.C0082c;
import com.google.android.material.button.MaterialButton;
import com.samsung.android.photoremaster.engine.R;
import h2.p;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    public final AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new w(context, attributeSet);
    }

    public final AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    public final AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new C0082c(context, attributeSet);
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [androidx.appcompat.widget.AppCompatRadioButton, t2.a, android.widget.CompoundButton, android.view.View] */
    public final AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        int i2 = R$attr.radioButtonStyle;
        ? appCompatRadioButton = new AppCompatRadioButton(a.a(context, attributeSet, i2, R.style.Widget_MaterialComponents_CompoundButton_RadioButton), attributeSet, i2);
        Context context2 = appCompatRadioButton.getContext();
        AttributeSet attributeSet2 = attributeSet;
        TypedArray d = p.d(context2, attributeSet2, Q1.a.f614A, i2, R.style.Widget_MaterialComponents_CompoundButton_RadioButton, new int[0]);
        if (d.hasValue(0)) {
            CompoundButtonCompat.setButtonTintList(appCompatRadioButton, B1.a.u(context2, d, 0));
        }
        appCompatRadioButton.e = d.getBoolean(1, false);
        d.recycle();
        return appCompatRadioButton;
    }

    public final AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(a.a(context, attributeSet, 16842884, 0), attributeSet, 16842884);
        Context context2 = appCompatTextView.getContext();
        if (k.K(context2, com.sec.android.gallery3d.R.attr.textAppearanceLineHeightEnabled, true)) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = Q1.a.D;
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
            int a7 = C2.a.a(context2, obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (a7 == -1) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, Q1.a.f615C);
                    int a10 = C2.a.a(appCompatTextView.getContext(), obtainStyledAttributes3, 1, 2);
                    obtainStyledAttributes3.recycle();
                    if (a10 >= 0) {
                        appCompatTextView.setLineHeight(a10);
                    }
                }
            }
        }
        return appCompatTextView;
    }
}
