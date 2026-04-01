package D1;

import C1.b;
import E1.a;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends a {
    public final C1.a e(b bVar, String str, int i2) {
        Parcel c5 = c();
        F1.b.c(c5, bVar);
        c5.writeString(str);
        c5.writeInt(i2);
        Parcel a7 = a(c5, 2);
        C1.a d = b.d(a7.readStrongBinder());
        a7.recycle();
        return d;
    }

    public final C1.a f(b bVar, String str, int i2, b bVar2) {
        Parcel c5 = c();
        F1.b.c(c5, bVar);
        c5.writeString(str);
        c5.writeInt(i2);
        F1.b.c(c5, bVar2);
        Parcel a7 = a(c5, 8);
        C1.a d = b.d(a7.readStrongBinder());
        a7.recycle();
        return d;
    }

    public final C1.a g(b bVar, String str, int i2) {
        Parcel c5 = c();
        F1.b.c(c5, bVar);
        c5.writeString(str);
        c5.writeInt(i2);
        Parcel a7 = a(c5, 4);
        C1.a d = b.d(a7.readStrongBinder());
        a7.recycle();
        return d;
    }

    public final C1.a h(b bVar, String str, boolean z, long j2) {
        Parcel c5 = c();
        F1.b.c(c5, bVar);
        c5.writeString(str);
        c5.writeInt(z ? 1 : 0);
        c5.writeLong(j2);
        Parcel a7 = a(c5, 7);
        C1.a d = b.d(a7.readStrongBinder());
        a7.recycle();
        return d;
    }
}
