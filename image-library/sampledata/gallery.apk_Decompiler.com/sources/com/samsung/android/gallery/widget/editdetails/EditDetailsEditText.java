package com.samsung.android.gallery.widget.editdetails;

import E6.a;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.EmojiList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.LengthFilter;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.UtlEditTextView;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import db.b;
import o5.C0496a;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsEditText extends UtlEditTextView {
    private InputFilter[] mInputFilter;
    private InputMethodManager mInputMethodManager;
    private boolean mIsResumed = false;
    private boolean mKeyboardShown;
    private Runnable mShowSipRunnable;

    public EditDetailsEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private InputFilter[] getInputFilter() {
        if (this.mInputFilter == null) {
            this.mInputFilter = new InputFilter[]{new LengthFilter(getContext().getApplicationContext()) {
                public boolean publishError(CharSequence charSequence) {
                    if (!EditDetailsEditText.this.isResumed() || !super.publishError(charSequence)) {
                        return false;
                    }
                    return true;
                }
            }, new b(2, this)};
        }
        return this.mInputFilter;
    }

    private InputMethodManager getInputMethodManager() {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager) AppResources.getAppContext().getSystemService("input_method");
        }
        return this.mInputMethodManager;
    }

    private void hideSip() {
        InputMethodManager inputMethodManager = getInputMethodManager();
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    private void init() {
        setOnClickListener(new C0496a(29, this));
        setOnLongClickListener(new a(3, this));
    }

    /* access modifiers changed from: private */
    public boolean isResumed() {
        return this.mIsResumed;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ CharSequence lambda$getInputFilter$2(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        if (!EmojiList.hasEmojiString(charSequence) && !StringCompat.hasSpecialCharacter(charSequence) && !StringCompat.isStartWithDot(charSequence, i8)) {
            return null;
        }
        Utils.showToast(getContext(), R$string.invalid_character, 0);
        return spanned.subSequence(i8, i10);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(View view) {
        postDelayed(new l(1, this), 300);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$init$1(View view) {
        postDelayed(new l(1, this), 300);
        return false;
    }

    /* access modifiers changed from: private */
    public void showSip() {
        if (isResumed()) {
            this.mShowSipRunnable = null;
            InputMethodManager inputMethodManager = getInputMethodManager();
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(this, 1);
                return;
            }
            return;
        }
        Log.d("TitleEditText", "pause before showSoftInput");
    }

    public void onPause() {
        boolean z = false;
        this.mIsResumed = false;
        if (this.mShowSipRunnable != null || WindowUtils.isIMEVisible(getRootWindowInsets())) {
            z = true;
        }
        this.mKeyboardShown = z;
        if (z) {
            hideSip();
        }
    }

    public void onResume() {
        this.mIsResumed = true;
        if (!hasFocus() || !this.mKeyboardShown) {
            this.mShowSipRunnable = null;
            return;
        }
        l lVar = new l(1, this);
        this.mShowSipRunnable = lVar;
        postDelayed(lVar, 300);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            this.mKeyboardShown = false;
        }
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        setFilters(new InputFilter[0]);
        super.setText(charSequence, bufferType);
        setFilters(getInputFilter());
    }
}
