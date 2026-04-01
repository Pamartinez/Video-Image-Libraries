package b4;

import android.view.View;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;

/* renamed from: b4.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0424d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PresentationViewPager e;

    public /* synthetic */ C0424d(PresentationViewPager presentationViewPager, int i2) {
        this.d = i2;
        this.e = presentationViewPager;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        PresentationViewPager presentationViewPager = this.e;
        switch (i2) {
            case 0:
                presentationViewPager.lambda$initView$0(view);
                return;
            case 1:
                presentationViewPager.lambda$initView$1(view);
                return;
            case 2:
                presentationViewPager.lambda$initView$2(view);
                return;
            default:
                presentationViewPager.lambda$updateViews$5(view);
                return;
        }
    }
}
