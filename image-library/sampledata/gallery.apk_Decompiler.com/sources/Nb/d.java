package Nb;

import com.samsung.android.gallery.widget.photoview.RemasterPhotoPreView;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RemasterPhotoPreView f2854a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f2855c;

    public /* synthetic */ d(RemasterPhotoPreView remasterPhotoPreView, int i2, float f) {
        this.f2854a = remasterPhotoPreView;
        this.b = i2;
        this.f2855c = f;
    }

    public final boolean getAsBoolean() {
        return this.f2854a.lambda$applyCenterCrop$0(this.b, this.f2855c);
    }
}
