package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.dialog.abstraction.EditBaseDialog;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.utils.EmojiList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.TextInputLayoutCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h3.b;
import n5.f;
import o6.t;
import q4.C0506c;
import q4.C0507d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreateNameDialog extends EditBaseDialog implements TextWatcher, TextView.OnEditorActionListener, View.OnLayoutChangeListener {
    private static final Character[] INVALID_CHARS = {'\\', '/', ':', '*', '?', '\"', '<', '>', '|'};
    EditText mEditText;
    protected String mOrgName;
    protected String mPreviousName;
    TextInputLayoutCompat mTextInputCompat;
    TextView mTitleView;
    ViewStub mTitleViewStub;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearError$6() {
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.clearError();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClickPositive$2() {
        setPositiveButtonEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClickPositive$3(String str) {
        ErrorType isValid = isValid(str);
        if (isValid.isNone()) {
            publishData(str);
            ThreadUtil.postOnUiThread(new C0507d(this, 0));
            return;
        }
        publishError(isValid);
        ThreadUtil.postOnUiThread(new C0507d(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postInitDialog$0() {
        setPositiveButtonEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postInitDialog$1(String str) {
        boolean isEmpty = TextUtils.isEmpty(str);
        ErrorType isValid = isValid(str);
        if (isEmpty || !isValid.isNone()) {
            if (isEmpty) {
                clearError();
            }
            ThreadUtil.postOnUiThread(new C0507d(this, 2));
        }
        onPostInit();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setError$4(int i2) {
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.setError(i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setError$5(String str) {
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.setError(str);
        }
    }

    private void publishError(ErrorType errorType) {
        setError(errorType.getStringId());
    }

    private void updateSoftInputMode(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(16);
        }
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mEditText = (EditText) view.findViewById(R.id.name_edit);
        this.mTextInputCompat = (TextInputLayoutCompat) view.findViewById(R.id.text_input_compat);
        this.mTitleViewStub = (ViewStub) view.findViewById(R.id.dialog_title);
    }

    public final void clearError() {
        ThreadUtil.postOnUiThread(new C0507d(this, 3));
    }

    public void closeDialog() {
        super.closeDialog();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ThreadUtil.postOnUiThreadDelayed(new t(8, activity.getWindow()), 300);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Dialog createDialog = super.createDialog(bundle);
        updateSoftInputMode(createDialog);
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.addTextChangedListener(this);
        }
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.addOnLayoutChangeListener(this);
        }
        return createDialog;
    }

    public void dismiss() {
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.removeTextChangedListener(this);
        }
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.removeOnLayoutChangeListener(this);
        }
        dismissAllowingStateLoss();
    }

    public String getDefaultName() {
        return "";
    }

    public View getEditTextView() {
        return this.mEditText;
    }

    public String getHint() {
        return getTitle();
    }

    public Character[] getInvalidChars() {
        return INVALID_CHARS;
    }

    public int getLayoutId() {
        return R.layout.alert_dialog_text_entry;
    }

    public int getMaxLength() {
        return 50;
    }

    public final String getName() {
        return this.mTextInputCompat.getText();
    }

    public int getPositiveButtonResource() {
        return R.string.create_shared_album_dialog_positive_button;
    }

    public String getScreenId() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getString("screenId");
        }
        return null;
    }

    public String getTitle() {
        return getString(R.string.new_album);
    }

    public void initDialog() {
        this.mTextInputCompat.setText(getDefaultName());
        this.mTextInputCompat.setPrivateImeOptions("inputType=filename;disableEmoticonInput=true");
        this.mTextInputCompat.setOnEditorActionListener(this);
        this.mTextInputCompat.setTextPredicate(new b(3, this), getInvalidChars(), getMaxLength());
        this.mTextInputCompat.setHint(getHint());
        this.mPreviousName = getName();
        if (useCustomTitle()) {
            setCustomTitle();
        }
    }

    public void initInputMethodManager() {
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(48);
        }
        super.initInputMethodManager();
    }

    public final boolean isDottedText(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith(".")) {
            return false;
        }
        return true;
    }

    public boolean isSameOrgName(String str) {
        String str2 = this.mOrgName;
        if (str2 == null) {
            return false;
        }
        if (str2.equals(str) || this.mOrgName.equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    public abstract ErrorType isValid(String str);

    public void onCancel(DialogInterface dialogInterface) {
        publishCancel();
    }

    public void onClickNegative(View view) {
        publishCancel();
        super.onClickNegative(view);
    }

    public void onClickPositive(View view) {
        super.onClickPositive(view);
        if (this.mTextInputCompat == null) {
            Log.e(this.TAG, "already destroyed");
            return;
        }
        String name = getName();
        if (name.contains("\n")) {
            name = name.replaceAll("\n", " ");
        }
        if (name.endsWith(".")) {
            name = replaceLastDots(name);
        }
        if (TextUtils.isEmpty(name)) {
            setError((int) R.string.name_cannot_empty);
        } else {
            ThreadUtil.postOnBgThread(new C0506c(this, name, 0));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateSoftInputMode(getDialog());
    }

    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        if ((i2 != 6 && (keyEvent == null || keyEvent.getKeyCode() != 66)) || !isPositiveButtonEnabled()) {
            return true;
        }
        onClickPositive(textView);
        return true;
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (!ViewUtils.hasFocus(this.mEditText)) {
            this.mEditText.requestFocus();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        if (charSequence.length() <= 0 || charSequence.toString().trim().isEmpty() || isSameOrgName(charSequence.toString().trim())) {
            setPositiveButtonEnabled(false);
            this.mPreviousName = "";
            return;
        }
        if (!this.mPreviousName.equals(charSequence.toString())) {
            setPositiveButtonEnabled(true);
        }
        String charSequence2 = charSequence.toString();
        this.mPreviousName = charSequence2;
        if (charSequence2.contains("\n")) {
            this.mPreviousName = this.mPreviousName.replaceAll("\n", " ");
        }
    }

    public void postInitDialog() {
        ThreadUtil.postOnBgThread(new C0506c(this, getName(), 1));
    }

    public boolean predicateText(CharSequence charSequence) {
        if (EmojiList.hasEmojiString(charSequence)) {
            return true;
        }
        if (!charSequence.equals("%") || supportCmh()) {
            return false;
        }
        return true;
    }

    public abstract void publishCancel();

    public abstract void publishData(String str);

    public void setCustomTitle() {
        if (this.mTitleView == null) {
            this.mTitleView = (TextView) this.mTitleViewStub.inflate().findViewById(R.id.title);
        }
        this.mTitleView.setText(getTitle());
    }

    public final void setError(int i2) {
        ThreadUtil.postOnUiThread(new f(this, i2, 2));
    }

    public boolean supportPopover() {
        return true;
    }

    public boolean useCustomTitle() {
        return true;
    }

    public final void setError(String str) {
        ThreadUtil.postOnUiThread(new C0506c(this, str, 2));
    }

    public void onPostInit() {
    }

    public void afterTextChanged(Editable editable) {
    }

    public String replaceLastDots(String str) {
        return str;
    }

    public void setSoftInputModeWhenHide(Context context) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
    }
}
