package V9;

import android.os.CancellationSignal;
import com.samsung.android.gallery.module.search.autocomplete.ScsAutoCompleteDataLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements CancellationSignal.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ScsAutoCompleteDataLoader f2889a;
    public final /* synthetic */ String b;

    public /* synthetic */ a(ScsAutoCompleteDataLoader scsAutoCompleteDataLoader, String str) {
        this.f2889a = scsAutoCompleteDataLoader;
        this.b = str;
    }

    public final void onCancel() {
        this.f2889a.lambda$createCancelSignal$0(this.b);
    }
}
