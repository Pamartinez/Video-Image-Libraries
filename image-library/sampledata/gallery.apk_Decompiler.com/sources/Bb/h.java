package Bb;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2781a;
    public final /* synthetic */ FilmStripViewHolder b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ImageView f2782c;
    public final /* synthetic */ int d;

    public /* synthetic */ h(FilmStripViewHolder filmStripViewHolder, ImageView imageView, int i2, int i7) {
        this.f2781a = i7;
        this.b = filmStripViewHolder;
        this.f2782c = imageView;
        this.d = i2;
    }

    public final void accept(Object obj, Object obj2) {
        Bitmap bitmap = (Bitmap) obj;
        Filter filter = (Filter) obj2;
        switch (this.f2781a) {
            case 0:
                this.b.lambda$setDefaultImageWithFilter$6(this.f2782c, this.d, bitmap, filter);
                return;
            default:
                this.b.lambda$setDefaultImageWithFilter$5(this.f2782c, this.d, bitmap, filter);
                return;
        }
    }
}
