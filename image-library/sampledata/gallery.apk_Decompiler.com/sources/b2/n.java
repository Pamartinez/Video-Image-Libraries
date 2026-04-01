package B2;

import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ p f53a;

    public n(p pVar) {
        this.f53a = pVar;
    }

    public final void a(TextInputLayout textInputLayout) {
        p pVar = this.f53a;
        m mVar = pVar.y;
        if (pVar.v != textInputLayout.getEditText()) {
            EditText editText = pVar.v;
            if (editText != null) {
                editText.removeTextChangedListener(mVar);
                if (pVar.v.getOnFocusChangeListener() == pVar.b().e()) {
                    pVar.v.setOnFocusChangeListener((View.OnFocusChangeListener) null);
                }
            }
            EditText editText2 = textInputLayout.getEditText();
            pVar.v = editText2;
            if (editText2 != null) {
                editText2.addTextChangedListener(mVar);
            }
            pVar.b().l(pVar.v);
            pVar.j(pVar.b());
        }
    }
}
