package q1;

import android.view.View;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public int f1853a = Integer.MAX_VALUE;
    public int b = Integer.MAX_VALUE;

    /* renamed from: c  reason: collision with root package name */
    public int f1854c = Integer.MIN_VALUE;
    public int d = Integer.MIN_VALUE;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f1855h;

    /* renamed from: i  reason: collision with root package name */
    public int f1856i;

    /* renamed from: j  reason: collision with root package name */
    public float f1857j;
    public float k;
    public int l;
    public int m;
    public final ArrayList n = new ArrayList();

    /* renamed from: o  reason: collision with root package name */
    public int f1858o;

    /* renamed from: p  reason: collision with root package name */
    public int f1859p;
    public boolean q;
    public boolean r;

    public final int a() {
        return this.f1855h - this.f1856i;
    }

    public final void b(View view, int i2, int i7, int i8, int i10) {
        b bVar = (b) view.getLayoutParams();
        this.f1853a = Math.min(this.f1853a, (view.getLeft() - bVar.r()) - i2);
        this.b = Math.min(this.b, (view.getTop() - bVar.g()) - i7);
        this.f1854c = Math.max(this.f1854c, bVar.t() + view.getRight() + i8);
        this.d = Math.max(this.d, bVar.q() + view.getBottom() + i10);
    }
}
