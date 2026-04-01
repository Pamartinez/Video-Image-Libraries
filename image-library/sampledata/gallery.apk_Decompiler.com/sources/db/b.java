package db;

import android.text.InputFilter;
import android.text.Spanned;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.TitleEditDelegate;
import com.samsung.android.gallery.widget.TextInputLayoutCompat;
import com.samsung.android.gallery.widget.editdetails.EditDetailsEditText;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3261a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f3261a = i2;
        this.b = obj;
    }

    public final CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
        switch (this.f3261a) {
            case 0:
                return ((TextInputLayoutCompat) this.b).filterChar(charSequence, i2, i7, spanned, i8, i10);
            case 1:
                return ((TitleEditDelegate) this.b).filterChar(charSequence, i2, i7, spanned, i8, i10);
            default:
                return ((EditDetailsEditText) this.b).lambda$getInputFilter$2(charSequence, i2, i7, spanned, i8, i10);
        }
    }
}
