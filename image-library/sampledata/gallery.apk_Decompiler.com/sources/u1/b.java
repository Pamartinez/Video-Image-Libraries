package U1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a(15);

    /* renamed from: A  reason: collision with root package name */
    public Integer f845A;
    public Integer B;

    /* renamed from: C  reason: collision with root package name */
    public Integer f846C;
    public Integer D;
    public Integer E;

    /* renamed from: F  reason: collision with root package name */
    public Integer f847F;

    /* renamed from: G  reason: collision with root package name */
    public Boolean f848G;
    public int d;
    public Integer e;
    public Integer f;
    public Integer g;

    /* renamed from: h  reason: collision with root package name */
    public Integer f849h;

    /* renamed from: i  reason: collision with root package name */
    public Integer f850i;

    /* renamed from: j  reason: collision with root package name */
    public Integer f851j;
    public Integer k;
    public int l = ScoverState.TYPE_NFC_SMART_COVER;
    public String m;
    public int n = -2;

    /* renamed from: o  reason: collision with root package name */
    public int f852o = -2;

    /* renamed from: p  reason: collision with root package name */
    public int f853p = -2;
    public Locale q;
    public CharSequence r;
    public CharSequence s;
    public int t;
    public int u;
    public Integer v;
    public Boolean w = Boolean.TRUE;

    /* renamed from: x  reason: collision with root package name */
    public Integer f854x;
    public Integer y;
    public Integer z;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        String str;
        parcel.writeInt(this.d);
        parcel.writeSerializable(this.e);
        parcel.writeSerializable(this.f);
        parcel.writeSerializable(this.g);
        parcel.writeSerializable(this.f849h);
        parcel.writeSerializable(this.f850i);
        parcel.writeSerializable(this.f851j);
        parcel.writeSerializable(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.f852o);
        parcel.writeInt(this.f853p);
        CharSequence charSequence = this.r;
        String str2 = null;
        if (charSequence != null) {
            str = charSequence.toString();
        } else {
            str = null;
        }
        parcel.writeString(str);
        CharSequence charSequence2 = this.s;
        if (charSequence2 != null) {
            str2 = charSequence2.toString();
        }
        parcel.writeString(str2);
        parcel.writeInt(this.t);
        parcel.writeSerializable(this.v);
        parcel.writeSerializable(this.f854x);
        parcel.writeSerializable(this.y);
        parcel.writeSerializable(this.z);
        parcel.writeSerializable(this.f845A);
        parcel.writeSerializable(this.B);
        parcel.writeSerializable(this.f846C);
        parcel.writeSerializable(this.f847F);
        parcel.writeSerializable(this.D);
        parcel.writeSerializable(this.E);
        parcel.writeSerializable(this.w);
        parcel.writeSerializable(this.q);
        parcel.writeSerializable(this.f848G);
    }
}
