package B2;

import android.widget.AutoCompleteTextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements AutoCompleteTextView.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ l f47a;

    public /* synthetic */ j(l lVar) {
        this.f47a = lVar;
    }

    public final void onDismiss() {
        l lVar = this.f47a;
        lVar.m = true;
        lVar.f51o = System.currentTimeMillis();
        lVar.s(false);
    }
}
