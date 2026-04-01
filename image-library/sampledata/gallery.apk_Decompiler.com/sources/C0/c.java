package C0;

import android.graphics.PointF;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public String f87a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public float f88c;
    public b d;
    public int e;
    public float f;
    public float g;

    /* renamed from: h  reason: collision with root package name */
    public int f89h;

    /* renamed from: i  reason: collision with root package name */
    public int f90i;

    /* renamed from: j  reason: collision with root package name */
    public float f91j;
    public boolean k;
    public PointF l;
    public PointF m;

    public final int hashCode() {
        int ordinal = ((this.d.ordinal() + (((int) (((float) C0212a.d(this.f87a.hashCode() * 31, 31, this.b)) + this.f88c)) * 31)) * 31) + this.e;
        long floatToRawIntBits = (long) Float.floatToRawIntBits(this.f);
        return (((ordinal * 31) + ((int) (floatToRawIntBits ^ (floatToRawIntBits >>> 32)))) * 31) + this.f89h;
    }
}
