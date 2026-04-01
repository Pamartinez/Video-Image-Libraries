package a2;

import com.google.android.material.carousel.CarouselLayoutManager;

/* renamed from: a2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0072d extends C0073e {
    public final /* synthetic */ CarouselLayoutManager b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0072d(CarouselLayoutManager carouselLayoutManager) {
        super(0);
        this.b = carouselLayoutManager;
    }

    public final int a() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        return carouselLayoutManager.getHeight() - carouselLayoutManager.getPaddingBottom();
    }

    public final int b() {
        return 0;
    }

    public final int c() {
        return this.b.getWidth();
    }

    public final int d() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        if (carouselLayoutManager.isLayoutRtl()) {
            return carouselLayoutManager.getWidth();
        }
        return 0;
    }

    public final int e() {
        return this.b.getPaddingTop();
    }
}
