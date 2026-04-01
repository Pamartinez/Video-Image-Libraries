package com.samsung.android.sum.core.types.nn;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFileDescriptor implements Parcelable, Cloneable {
    public static final Parcelable.Creator<NNFileDescriptor> CREATOR = new Parcelable.Creator<NNFileDescriptor>() {
        public NNFileDescriptor createFromParcel(Parcel parcel) {
            return new NNFileDescriptor(parcel);
        }

        public NNFileDescriptor[] newArray(int i2) {
            return new NNFileDescriptor[i2];
        }
    };

    /* renamed from: fd  reason: collision with root package name */
    private ParcelFileDescriptor f4146fd;
    private long length;
    private String name;
    private long offset;
    private Uri pathUri;
    private String realPath;

    public NNFileDescriptor(String str) {
        this.realPath = str;
    }

    public Object clone() {
        Object clone = super.clone();
        try {
            ParcelFileDescriptor parcelFileDescriptor = this.f4146fd;
            if (parcelFileDescriptor == null) {
                return clone;
            }
            ((NNFileDescriptor) clone).f4146fd = parcelFileDescriptor.dup();
            return clone;
        } catch (IOException e) {
            e.printStackTrace();
            ((NNFileDescriptor) clone).f4146fd = null;
            return clone;
        }
    }

    public int describeContents() {
        return 0;
    }

    public ParcelFileDescriptor getFd() {
        return this.f4146fd;
    }

    public long getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    public long getOffset() {
        return this.offset;
    }

    public Uri getPathUri() {
        return this.pathUri;
    }

    public String getRealPath() {
        return this.realPath;
    }

    public void setFd(ParcelFileDescriptor parcelFileDescriptor) {
        this.f4146fd = parcelFileDescriptor;
    }

    public void setLength(long j2) {
        this.length = j2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOffset(long j2) {
        this.offset = j2;
    }

    public void setPathUri(Uri uri) {
        this.pathUri = uri;
    }

    public void setRealPath(String str) {
        this.realPath = str;
    }

    public NNFileDescriptor toNNFileDescriptor() {
        return (NNFileDescriptor) clone();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.pathUri, i2);
        parcel.writeString(this.realPath);
        parcel.writeLong(this.offset);
        parcel.writeLong(this.length);
        parcel.writeParcelable(this.f4146fd, i2);
    }

    public NNFileDescriptor(String str, ParcelFileDescriptor parcelFileDescriptor) {
        this.realPath = str;
        this.f4146fd = parcelFileDescriptor;
    }

    public NNFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        this.f4146fd = parcelFileDescriptor;
    }

    public NNFileDescriptor(Uri uri, String str, ParcelFileDescriptor parcelFileDescriptor) {
        this.pathUri = uri;
        this.realPath = str;
        this.f4146fd = parcelFileDescriptor;
    }

    public NNFileDescriptor(Parcel parcel) {
        this.pathUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.realPath = parcel.readString();
        this.offset = parcel.readLong();
        this.length = parcel.readLong();
        this.f4146fd = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
    }
}
