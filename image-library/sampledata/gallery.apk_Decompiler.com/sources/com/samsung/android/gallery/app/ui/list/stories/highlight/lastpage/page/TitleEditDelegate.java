package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.LastPageDataHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import h3.b;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TitleEditDelegate implements TextView.OnEditorActionListener {
    private static final Character[] INVALID_CHARS = {'\\', '/', ':', '*', '?', '\"', '<', '>', '|'};
    private final LastPageDataHolder mDataHolder;
    EditText mEditText;
    private boolean mInvalidCharError;
    String mRegexForInvalidChars;
    Predicate<CharSequence> mTextPredicate;
    final IStoryHighlightView mView;

    public TitleEditDelegate(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mDataHolder = (LastPageDataHolder) iStoryHighlightView.getEventHandler().requestData(DataRequest.REQ_LAST_PAGE_DATA_HOLDER);
    }

    private void backupState() {
        boolean z;
        if (this.mEditText != null) {
            LastPageDataHolder.ImeState imeState = new LastPageDataHolder.ImeState();
            imeState.editedTitle = this.mEditText.getText();
            if (WindowUtils.getIMEInsetsBottom(this.mEditText.getRootWindowInsets()) > 0) {
                z = true;
            } else {
                z = false;
            }
            imeState.imeShown = z;
            imeState.cursorPosition = this.mEditText.getSelectionStart();
            imeState.hasFocus = this.mEditText.hasFocus();
            this.mDataHolder.setImeState(imeState);
        }
    }

    private Context getAppContext() {
        return AppResources.getAppContext();
    }

    private CharSequence getInvalidFiltered(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        String str;
        if (i7 - i2 > 100) {
            i7 = i2 + 100;
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

    private String getRegexForInvalidChars() {
        StringBuilder sb2 = new StringBuilder();
        for (Character ch : INVALID_CHARS) {
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

    private void hideSoftInput() {
        Context context;
        InputMethodManager inputMethodManager;
        EditText editText = this.mEditText;
        if (editText != null) {
            context = editText.getContext();
        } else {
            context = null;
        }
        if (context != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
        }
    }

    private void restoreState(LastPageDataHolder.ImeState imeState, MediaItem mediaItem) {
        CharSequence charSequence;
        if (this.mEditText != null && imeState != null) {
            if (!TextUtils.isEmpty(imeState.editedTitle)) {
                charSequence = imeState.editedTitle;
            } else if (!TextUtils.isEmpty(this.mEditText.getText())) {
                charSequence = this.mEditText.getText();
            } else {
                charSequence = mediaItem.getTitle();
            }
            this.mEditText.setText(charSequence);
            if (imeState.hasFocus) {
                this.mEditText.setSelection(imeState.cursorPosition);
                this.mEditText.requestFocus();
                if (imeState.imeShown) {
                    showSoftInput();
                }
            }
        }
    }

    private void showSoftInput() {
        Context context;
        InputMethodManager inputMethodManager;
        EditText editText = this.mEditText;
        if (editText != null) {
            context = editText.getContext();
        } else {
            context = null;
        }
        if (context != null && (inputMethodManager = (InputMethodManager) this.mEditText.getContext().getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(this.mEditText, 1);
        }
    }

    public void bindItem(MediaItem mediaItem) {
        if (this.mEditText != null && mediaItem != null) {
            LastPageDataHolder.ImeState imeState = this.mDataHolder.getImeState();
            if (imeState != null) {
                restoreState(imeState, mediaItem);
            } else if (TextUtils.isEmpty(this.mEditText.getText())) {
                this.mEditText.setText(mediaItem.getTitle());
            }
            this.mDataHolder.setImeState((LastPageDataHolder.ImeState) null);
        }
    }

    public void bindView(View view) {
        EditText editText = (EditText) view.findViewById(R.id.title_edit);
        this.mEditText = editText;
        if (editText != null) {
            editText.setPrivateImeOptions("disableGifKeyboard=true;disableSticker=true");
            this.mEditText.setOnEditorActionListener(this);
            setTextPredicate(this.mEditText, new b(6, this));
        }
    }

    public CharSequence filterChar(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        this.mInvalidCharError = false;
        CharSequence predicateFiltered = getPredicateFiltered(charSequence, i2, i7, spanned, i8, i10);
        if (predicateFiltered == null) {
            predicateFiltered = getInvalidFiltered(charSequence, i2, i7, spanned, i8, i10);
        }
        if (predicateFiltered == null) {
            return null;
        }
        this.mInvalidCharError = true;
        return predicateFiltered;
    }

    public void filterLength(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        if (spanned.length() + ((i7 - i2) - (i10 - i8)) > 50) {
            Utils.showToast(getAppContext(), AppResources.getQuantityString(R.plurals.more_than_limit, 50, 50));
        } else if (this.mInvalidCharError) {
            Utils.showToast(getAppContext(), (int) R.string.invalid_character);
        }
        this.mInvalidCharError = false;
    }

    public EditText getEditTextView() {
        return this.mEditText;
    }

    public String getEditedTitle() {
        EditText editText = this.mEditText;
        if (editText == null || TextUtils.isEmpty(editText.getText())) {
            return "";
        }
        return this.mEditText.getText().toString();
    }

    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        if ((i2 != 6 && (keyEvent == null || keyEvent.getKeyCode() != 66)) || this.mEditText == null) {
            return true;
        }
        hideSoftInput();
        this.mEditText.clearFocus();
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_ONDEMAND_STORY_HIGHLIGHT_LAST_PAGE_EDIT_STORY_TITLE);
        return true;
    }

    public void onLastPageShow(boolean z) {
        if (!z) {
            hideSoftInput();
        }
    }

    public boolean predicateText(CharSequence charSequence) {
        if (!charSequence.equals("%") || Features.isEnabled(Features.USE_CMH)) {
            return false;
        }
        return true;
    }

    public void prepareResolution() {
        backupState();
    }

    public void setTextPredicate(EditText editText, Predicate<CharSequence> predicate) {
        this.mTextPredicate = predicate;
        this.mRegexForInvalidChars = getRegexForInvalidChars();
        AnonymousClass1 r62 = new InputFilter.LengthFilter(50) {
            public CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
                TitleEditDelegate.this.filterLength(charSequence, i2, i7, spanned, i8, i10);
                return super.filter(charSequence, i2, i7, spanned, i8, i10);
            }
        };
        editText.getInputExtras(true).putInt("maxLength", 50);
        editText.setFilters(new InputFilter[]{new db.b(1, this), r62});
    }
}
