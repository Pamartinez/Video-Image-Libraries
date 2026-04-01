package androidx.versionedparcelable;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;
import i.C0212a;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VersionedParcelParcel extends VersionedParcel {
    private int mCurrentField;
    private final int mEnd;
    private int mFieldId;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;

    public VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    public void closeField() {
        int i2 = this.mCurrentField;
        if (i2 >= 0) {
            int i7 = this.mPositionLookup.get(i2);
            int dataPosition = this.mParcel.dataPosition();
            this.mParcel.setDataPosition(i7);
            this.mParcel.writeInt(dataPosition - i7);
            this.mParcel.setDataPosition(dataPosition);
        }
    }

    public VersionedParcel createSubParcel() {
        Parcel parcel = this.mParcel;
        int dataPosition = parcel.dataPosition();
        int i2 = this.mNextRead;
        if (i2 == this.mOffset) {
            i2 = this.mEnd;
        }
        return new VersionedParcelParcel(parcel, dataPosition, i2, C0212a.p(new StringBuilder(), this.mPrefix, "  "), this.mReadCache, this.mWriteCache, this.mParcelizerCache);
    }

    public byte[] readByteArray() {
        int readInt = this.mParcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.mParcel.readByteArray(bArr);
        return bArr;
    }

    public boolean readField(int i2) {
        while (this.mNextRead < this.mEnd) {
            int i7 = this.mFieldId;
            if (i7 == i2) {
                return true;
            }
            if (String.valueOf(i7).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.mParcel.setDataPosition(this.mNextRead);
            int readInt = this.mParcel.readInt();
            this.mFieldId = this.mParcel.readInt();
            this.mNextRead += readInt;
        }
        if (this.mFieldId == i2) {
            return true;
        }
        return false;
    }

    public int readInt() {
        return this.mParcel.readInt();
    }

    public long readLong() {
        return this.mParcel.readLong();
    }

    public <T extends Parcelable> T readParcelable() {
        return this.mParcel.readParcelable(getClass().getClassLoader());
    }

    public String readString() {
        return this.mParcel.readString();
    }

    public IBinder readStrongBinder() {
        return this.mParcel.readStrongBinder();
    }

    public void setOutputField(int i2) {
        closeField();
        this.mCurrentField = i2;
        this.mPositionLookup.put(i2, this.mParcel.dataPosition());
        writeInt(0);
        writeInt(i2);
    }

    public void writeByteArray(byte[] bArr) {
        if (bArr != null) {
            this.mParcel.writeInt(bArr.length);
            this.mParcel.writeByteArray(bArr);
            return;
        }
        this.mParcel.writeInt(-1);
    }

    public void writeInt(int i2) {
        this.mParcel.writeInt(i2);
    }

    public void writeLong(long j2) {
        this.mParcel.writeLong(j2);
    }

    public void writeParcelable(Parcelable parcelable) {
        this.mParcel.writeParcelable(parcelable, 0);
    }

    public void writeString(String str) {
        this.mParcel.writeString(str);
    }

    public void writeStrongBinder(IBinder iBinder) {
        this.mParcel.writeStrongBinder(iBinder);
    }

    private VersionedParcelParcel(Parcel parcel, int i2, int i7, String str, ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        super(arrayMap, arrayMap2, arrayMap3);
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mFieldId = -1;
        this.mParcel = parcel;
        this.mOffset = i2;
        this.mEnd = i7;
        this.mNextRead = i2;
        this.mPrefix = str;
    }
}
