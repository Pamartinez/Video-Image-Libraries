package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.textfield.TextInputLayout;
import db.b;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextInputLayoutCompat extends FrameLayout {
    private final AppCompatEditText mEditText;
    private boolean mInvalidCharError;
    private int mMaxLength;
    private boolean mMaxLengthError;
    private String mRegexForInvalidChars;
    private final TextInputLayout mTextInputLayout;
    private Predicate<CharSequence> mTextPredicate;

    public TextInputLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private CharSequence getInvalidFiltered(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        String str;
        int i11 = i7 - i2;
        int i12 = this.mMaxLength;
        if (i11 > i12 * 2) {
            i7 = (i12 * 2) + i2;
        }
        String charSequence2 = charSequence.subSequence(i2, i7).toString();
        String str2 = this.mRegexForInvalidChars;
        if (str2 != null) {
            str = charSequence2.replaceAll(str2, "");
        } else {
            str = charSequence2;
        }
        if (str.length() == charSequence2.length()) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return spanned.subSequence(i8, i10);
        }
        return str;
    }

    private CharSequence getPredicateFiltered(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        Predicate<CharSequence> predicate = this.mTextPredicate;
        if (predicate == null || !predicate.test(charSequence)) {
            return null;
        }
        return spanned.subSequence(i8, i10);
    }

    private String getRegexForInvalidChars(Character[] chArr) {
        StringBuilder sb2 = new StringBuilder();
        for (Character ch : chArr) {
            if ('\\' == ch.charValue()) {
                sb2.append("\\\\");
            } else {
                sb2.append(ch);
            }
        }
        if (sb2.length() <= 0) {
            return null;
        }
        sb2.insert(0, "[");
        sb2.append("]");
        return sb2.toString();
    }

    private void initView() {
        this.mEditText.setImeOptions(33554438);
        this.mEditText.setInputType(16385);
        this.mEditText.requestFocus();
        this.mEditText.selectAll();
        this.mEditText.setSelectAllOnFocus(true);
        this.mEditText.setFocusable(true);
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        AppCompatEditText appCompatEditText = this.mEditText;
        if (appCompatEditText != null) {
            appCompatEditText.addTextChangedListener(textWatcher);
        }
    }

    public void clearError() {
        this.mTextInputLayout.setErrorEnabled(false);
    }

    public CharSequence filterChar(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        this.mInvalidCharError = false;
        CharSequence predicateFiltered = getPredicateFiltered(charSequence, i2, i7, spanned, i8, i10);
        if (predicateFiltered == null) {
            predicateFiltered = getInvalidFiltered(charSequence, i2, i7, spanned, i8, i10);
        }
        if (predicateFiltered != null) {
            setError(R$string.cannot_enter_emojis_or_special_characters);
            this.mInvalidCharError = true;
            return predicateFiltered;
        } else if (this.mMaxLengthError) {
            return null;
        } else {
            clearError();
            return null;
        }
    }

    public void filterLength(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        this.mMaxLengthError = false;
        if (spanned.length() + ((i7 - i2) - (i10 - i8)) > this.mMaxLength) {
            this.mMaxLengthError = true;
            Resources resources = getResources();
            int i11 = R$plurals.more_than_limit;
            int i12 = this.mMaxLength;
            setError(resources.getQuantityString(i11, i12, new Object[]{Integer.valueOf(i12)}));
        } else if (!this.mInvalidCharError) {
            clearError();
        }
    }

    public String getText() {
        return this.mEditText.getText().toString().trim();
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        AppCompatEditText appCompatEditText = this.mEditText;
        if (appCompatEditText != null) {
            appCompatEditText.removeTextChangedListener(textWatcher);
        }
    }

    public void setEnabledInputLayoutHint(boolean z) {
        this.mTextInputLayout.setHintEnabled(z);
    }

    public void setError(int i2) {
        setError(getResources().getString(i2));
    }

    public void setHint(String str) {
        this.mEditText.setHint(str);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        this.mEditText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setPrivateImeOptions(String str) {
        this.mEditText.setPrivateImeOptions(str);
    }

    public void setText(CharSequence charSequence) {
        this.mEditText.setText(charSequence);
        this.mEditText.selectAll();
        this.mEditText.setSelectAllOnFocus(true);
    }

    public void setTextPredicate(Predicate<CharSequence> predicate, Character[] chArr, int i2) {
        this.mTextPredicate = predicate;
        this.mRegexForInvalidChars = getRegexForInvalidChars(chArr);
        this.mMaxLength = i2;
        AnonymousClass1 r32 = new InputFilter.LengthFilter(i2) {
            public CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
                TextInputLayoutCompat.this.filterLength(charSequence, i2, i7, spanned, i8, i10);
                return super.filter(charSequence, i2, i7, spanned, i8, i10);
            }
        };
        this.mEditText.getInputExtras(true).putInt("maxLength", i2);
        this.mEditText.setFilters(new InputFilter[]{new b(0, this), r32});
    }

    public TextInputLayoutCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        View.inflate(getContext(), R$layout.text_input_layout_compat, this);
        this.mTextInputLayout = (TextInputLayout) findViewById(R$id.text_input_layout);
        this.mEditText = (AppCompatEditText) findViewById(R$id.name_edit);
        initView();
    }

    public void setError(String str) {
        CharSequence error = this.mTextInputLayout.getError();
        if (!this.mTextInputLayout.m.q || TextUtils.isEmpty(error) || !error.equals(str)) {
            TextInputLayout textInputLayout = this.mTextInputLayout;
            if (!textInputLayout.m.q) {
                textInputLayout.setError(str);
            } else if (TextUtils.isEmpty(error) || error.equals(str)) {
                this.mTextInputLayout.setErrorEnabled(false);
            } else {
                this.mTextInputLayout.setErrorEnabled(false);
                this.mTextInputLayout.setError(str);
            }
        } else {
            Log.d("TextInputLayoutCompat", "setError skip same error");
        }
    }
}
