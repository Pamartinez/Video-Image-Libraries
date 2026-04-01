package f4;

import android.content.res.Configuration;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ Configuration d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2634h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ ArrayList f2635i;

    public /* synthetic */ b(ArrayList arrayList, Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        this.d = configuration;
        this.e = z;
        this.f = z3;
        this.g = z7;
        this.f2634h = z9;
        this.f2635i = arrayList;
    }

    public final void accept(Object obj) {
        DelegateComposite.lambda$onConfigurationChangedInternal$5(this.d, this.e, this.f, this.g, this.f2634h, this.f2635i, (AbsDelegate) obj);
    }
}
