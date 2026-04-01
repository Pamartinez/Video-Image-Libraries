package gc;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3268h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f3269i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Object f3270j;

    public /* synthetic */ e(Context context, String str, int i2, int i7, int i8, int i10) {
        this.d = 2;
        this.e = context;
        this.f3270j = str;
        this.f = i2;
        this.g = i7;
        this.f3268h = i8;
        this.f3269i = i10;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ViewUtils.lambda$setTouchArea$2((View) this.e, this.f, this.g, this.f3268h, this.f3269i, (View) this.f3270j);
                return;
            case 1:
                ViewUtils.lambda$setTouchAreaComposite$3((View) this.e, this.f, this.g, this.f3268h, this.f3269i, (View) this.f3270j);
                return;
            default:
                Utils.createAndShowToast((Context) this.e, (String) this.f3270j, this.f, this.g, this.f3268h, this.f3269i);
                return;
        }
    }

    public /* synthetic */ e(View view, int i2, int i7, int i8, int i10, View view2, int i11) {
        this.d = i11;
        this.e = view;
        this.f = i2;
        this.g = i7;
        this.f3268h = i8;
        this.f3269i = i10;
        this.f3270j = view2;
    }
}
