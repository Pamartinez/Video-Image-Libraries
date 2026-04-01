package B2;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final TextInputLayout f40a;

    public B(TextInputLayout textInputLayout) {
        this.f40a = textInputLayout;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Editable editable;
        boolean z;
        String str;
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        TextInputLayout textInputLayout = this.f40a;
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            editable = editText.getText();
        } else {
            editable = null;
        }
        CharSequence hint = textInputLayout.getHint();
        CharSequence error = textInputLayout.getError();
        CharSequence placeholderText = textInputLayout.getPlaceholderText();
        int counterMaxLength = textInputLayout.getCounterMaxLength();
        CharSequence counterOverflowDescription = textInputLayout.getCounterOverflowDescription();
        boolean isEmpty = TextUtils.isEmpty(editable);
        boolean isEmpty2 = TextUtils.isEmpty(hint);
        boolean z3 = textInputLayout.f1546x0;
        boolean isEmpty3 = TextUtils.isEmpty(error);
        if (!isEmpty3 || !TextUtils.isEmpty(counterOverflowDescription)) {
            z = true;
        } else {
            z = false;
        }
        if (!isEmpty2) {
            str = hint.toString();
        } else {
            str = "";
        }
        y yVar = textInputLayout.e;
        AppCompatTextView appCompatTextView = yVar.e;
        if (appCompatTextView.getVisibility() == 0) {
            accessibilityNodeInfoCompat.setLabelFor(appCompatTextView);
            accessibilityNodeInfoCompat.setTraversalAfter(appCompatTextView);
        } else {
            accessibilityNodeInfoCompat.setTraversalAfter(yVar.g);
        }
        if (!isEmpty) {
            accessibilityNodeInfoCompat.setText(editable);
        } else if (!TextUtils.isEmpty(str)) {
            accessibilityNodeInfoCompat.setText(str);
            if (!z3 && placeholderText != null) {
                accessibilityNodeInfoCompat.setText(str + ArcCommonLog.TAG_COMMA + placeholderText);
            }
        } else if (placeholderText != null) {
            accessibilityNodeInfoCompat.setText(placeholderText);
        }
        if (!TextUtils.isEmpty(str)) {
            accessibilityNodeInfoCompat.setHintText(str);
            accessibilityNodeInfoCompat.setShowingHintText(isEmpty);
        }
        if (editable == null || editable.length() != counterMaxLength) {
            counterMaxLength = -1;
        }
        accessibilityNodeInfoCompat.setMaxTextLength(counterMaxLength);
        if (z) {
            if (isEmpty3) {
                error = counterOverflowDescription;
            }
            accessibilityNodeInfoCompat.setError(error);
        }
        AppCompatTextView appCompatTextView2 = textInputLayout.m.y;
        if (appCompatTextView2 != null) {
            accessibilityNodeInfoCompat.setLabelFor(appCompatTextView2);
        }
        textInputLayout.f.b().m(accessibilityNodeInfoCompat);
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        this.f40a.f.b().n(accessibilityEvent);
    }
}
