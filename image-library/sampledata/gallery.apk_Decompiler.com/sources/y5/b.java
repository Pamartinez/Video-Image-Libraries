package y5;

import android.view.Menu;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ CreaturePicturesDelegate d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Menu f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2753h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2754i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ boolean f2755j;

    public /* synthetic */ b(CreaturePicturesDelegate creaturePicturesDelegate, boolean z, Menu menu, boolean z3, boolean z7, int i2, boolean z9) {
        this.d = creaturePicturesDelegate;
        this.e = z;
        this.f = menu;
        this.g = z3;
        this.f2753h = z7;
        this.f2754i = i2;
        this.f2755j = z9;
    }

    public final void run() {
        this.d.lambda$updateCreatureMenu$3(this.e, this.f, this.g, this.f2753h, this.f2754i, this.f2755j);
    }
}
