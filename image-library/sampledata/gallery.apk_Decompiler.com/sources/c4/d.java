package C4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ AlbumsBaseBlurPinchAnimationManager d;
    public final /* synthetic */ ListViewHolder e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ View f2281h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2282i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f2283j;
    public final /* synthetic */ View k;

    public /* synthetic */ d(AlbumsBaseBlurPinchAnimationManager albumsBaseBlurPinchAnimationManager, ListViewHolder listViewHolder, boolean z, boolean z3, View view, int i2, int i7, View view2) {
        this.d = albumsBaseBlurPinchAnimationManager;
        this.e = listViewHolder;
        this.f = z;
        this.g = z3;
        this.f2281h = view;
        this.f2282i = i2;
        this.f2283j = i7;
        this.k = view2;
    }

    public final void onAnimationEnd(View view) {
        this.d.lambda$prepareFolderChildAnimation$0(this.e, this.f, this.g, this.f2281h, this.f2282i, this.f2283j, this.k, view);
    }
}
