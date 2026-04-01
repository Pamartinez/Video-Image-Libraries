package B2;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z implements TextWatcher {
    public int d;
    public final /* synthetic */ EditText e;
    public final /* synthetic */ TextInputLayout f;

    public z(TextInputLayout textInputLayout, EditText editText) {
        this.f = textInputLayout;
        this.e = editText;
        this.d = editText.getLineCount();
    }

    public final void afterTextChanged(Editable editable) {
        int i2;
        TextInputLayout textInputLayout = this.f;
        textInputLayout.u(!textInputLayout.f1514D0, false);
        if (textInputLayout.n) {
            textInputLayout.n(editable);
        }
        if (textInputLayout.v) {
            textInputLayout.v(editable);
        }
        EditText editText = this.e;
        int lineCount = editText.getLineCount();
        int i7 = this.d;
        if (lineCount != i7) {
            if (lineCount < i7 && ViewCompat.getMinimumHeight(editText) != (i2 = textInputLayout.f1544w0)) {
                editText.setMinimumHeight(i2);
            }
            this.d = lineCount;
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
    }

    public final void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
    }
}
