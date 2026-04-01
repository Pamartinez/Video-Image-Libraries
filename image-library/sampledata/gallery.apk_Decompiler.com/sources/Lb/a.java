package Lb;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.livemotion.LiveMotionPageTransformer;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowPageTransformer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ViewPager2.PageTransformer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewPager2.PageTransformer e;

    public /* synthetic */ a(ViewPager2.PageTransformer pageTransformer, int i2) {
        this.d = i2;
        this.e = pageTransformer;
    }

    public final void transformPage(View view, float f) {
        int i2 = this.d;
        ViewPager2.PageTransformer pageTransformer = this.e;
        switch (i2) {
            case 0:
                ((LiveMotionPageTransformer) pageTransformer).lambda$new$0(view, f);
                return;
            default:
                ((SimpleSlideShowPageTransformer) pageTransformer).lambda$new$0(view, f);
                return;
        }
    }
}
