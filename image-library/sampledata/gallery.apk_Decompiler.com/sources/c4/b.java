package C4;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.widget.PinchBlurImageView;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.LayoutRuleHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ AlbumsBaseBlurPinchAnimationManager d;
    public final /* synthetic */ ListViewHolder e;
    public final /* synthetic */ View f;
    public final /* synthetic */ Drawable g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2276h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ PinchBlurImageView f2277i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ float f2278j;
    public final /* synthetic */ int k;
    public final /* synthetic */ LayoutRuleHolder l;

    public /* synthetic */ b(AlbumsBaseBlurPinchAnimationManager albumsBaseBlurPinchAnimationManager, ListViewHolder listViewHolder, View view, Drawable drawable, boolean z, PinchBlurImageView pinchBlurImageView, float f5, int i2, LayoutRuleHolder layoutRuleHolder) {
        this.d = albumsBaseBlurPinchAnimationManager;
        this.e = listViewHolder;
        this.f = view;
        this.g = drawable;
        this.f2276h = z;
        this.f2277i = pinchBlurImageView;
        this.f2278j = f5;
        this.k = i2;
        this.l = layoutRuleHolder;
    }

    public final void onAnimationEnd(View view) {
        this.d.lambda$prepareThumbnailBlurAnimation$5(this.e, this.f, this.g, this.f2276h, this.f2277i, this.f2278j, this.k, this.l, view);
    }
}
