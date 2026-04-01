package a2;

import com.google.android.material.carousel.CarouselLayoutManager;

/* renamed from: a2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0071c extends C0073e {
    public final /* synthetic */ CarouselLayoutManager b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0071c(CarouselLayoutManager carouselLayoutManager) {
        super(1);
        this.b = carouselLayoutManager;
    }

    public final int a() {
        return this.b.getHeight();
    }

    public final int b() {
        return this.b.getPaddingLeft();
    }

    public final int c() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        return carouselLayoutManager.getWidth() - carouselLayoutManager.getPaddingRight();
    }

    public final int d() {
        return 0;
    }

    public final int e() {
        return 0;
    }
}
