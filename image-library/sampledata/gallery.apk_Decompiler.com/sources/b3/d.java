package B3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SharedMemory;
import android.util.Log;
import i.C0212a;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import te.C1295a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a(1);
    public final String d;
    public final SharedMemory e;
    public final int f;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final int f82h;

    /* renamed from: i  reason: collision with root package name */
    public final int f83i;

    /* renamed from: j  reason: collision with root package name */
    public final i f84j;
    public final byte[] k;

    public d(Parcel parcel) {
        Object obj;
        String readString = parcel.readString();
        readString = readString == null ? "" : readString;
        Class<SharedMemory> cls = SharedMemory.class;
        SharedMemory sharedMemory = (SharedMemory) parcel.readParcelable(cls.getClassLoader(), SharedMemory.class);
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        int readInt3 = parcel.readInt();
        int readInt4 = parcel.readInt();
        int readInt5 = parcel.readInt();
        C1295a b = i.b();
        if (readInt5 < 0 || readInt5 >= b.size()) {
            obj = i.FP32;
        } else {
            obj = b.get(readInt5);
        }
        i iVar = (i) obj;
        int readInt6 = parcel.readInt();
        byte[] bArr = null;
        if (readInt6 != -1) {
            if (readInt6 == 0) {
                bArr = parcel.createByteArray();
            } else if (readInt6 != 1) {
                Log.w("ImageTensor", "Unknown byteData flag: " + readInt6);
            } else {
                SharedMemory sharedMemory2 = (SharedMemory) parcel.readParcelable(cls.getClassLoader(), SharedMemory.class);
                if (sharedMemory2 != null) {
                    try {
                        Log.e("ImageTensor", "Failed to read SharedMemory");
                        ByteBuffer mapReadOnly = sharedMemory2.mapReadOnly();
                        j.d(mapReadOnly, "mapReadOnly(...)");
                        byte[] bArr2 = new byte[sharedMemory2.getSize()];
                        mapReadOnly.get(bArr2);
                        SharedMemory.unmap(mapReadOnly);
                        sharedMemory2.close();
                        bArr = bArr2;
                    } catch (Exception e7) {
                        Log.e("ImageTensor", "Failed to read SharedMemory", e7);
                    }
                }
            }
        }
        j.e(iVar, "dataType");
        this.d = readString;
        this.e = sharedMemory;
        this.f = readInt;
        this.g = readInt2;
        this.f82h = readInt3;
        this.f83i = readInt4;
        this.f84j = iVar;
        this.k = bArr;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (j.a(this.d, dVar.d) && j.a(this.e, dVar.e) && this.f == dVar.f && this.g == dVar.g && this.f82h == dVar.f82h && this.f83i == dVar.f83i && this.f84j == dVar.f84j && j.a(this.k, dVar.k)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.d.hashCode() * 31;
        int i7 = 0;
        SharedMemory sharedMemory = this.e;
        if (sharedMemory == null) {
            i2 = 0;
        } else {
            i2 = sharedMemory.hashCode();
        }
        int hashCode2 = (this.f84j.hashCode() + C0212a.b(this.f83i, C0212a.b(this.f82h, C0212a.b(this.g, C0212a.b(this.f, (hashCode + i2) * 31, 31), 31), 31), 31)) * 31;
        byte[] bArr = this.k;
        if (bArr != null) {
            i7 = Arrays.hashCode(bArr);
        }
        return hashCode2 + i7;
    }

    public final String toString() {
        String arrays = Arrays.toString(this.k);
        StringBuilder sb2 = new StringBuilder("ImageTensor(tensorName=");
        sb2.append(this.d);
        sb2.append(", tensorData=");
        sb2.append(this.e);
        sb2.append(", batch=");
        N2.j.x(sb2, this.f, ", height=", this.g, ", width=");
        N2.j.x(sb2, this.f82h, ", channel=", this.f83i, ", dataType=");
        sb2.append(this.f84j);
        sb2.append(", byteData=");
        sb2.append(arrays);
        sb2.append(")");
        return sb2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i2);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.f82h);
        parcel.writeInt(this.f83i);
        parcel.writeInt(this.f84j.ordinal());
        byte[] bArr = this.k;
        if (bArr == null) {
            parcel.writeInt(-1);
        } else if (bArr.length <= 8192) {
            parcel.writeInt(0);
            parcel.writeByteArray(bArr);
        } else {
            parcel.writeInt(1);
            try {
                SharedMemory create = SharedMemory.create("ImageTensor", bArr.length);
                j.d(create, "create(...)");
                ByteBuffer mapReadWrite = create.mapReadWrite();
                j.d(mapReadWrite, "mapReadWrite(...)");
                mapReadWrite.put(bArr);
                SharedMemory.unmap(mapReadWrite);
                parcel.writeParcelable(create, i2);
                create.close();
            } catch (Exception e7) {
                Log.e("ImageTensor", "Failed to write SharedMemory", e7);
                parcel.writeParcelable((Parcelable) null, i2);
            }
        }
    }
}
