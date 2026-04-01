package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J8\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b \u0010!R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u0014R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0018¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/Document;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "contentUri", "title", "", "rawFileId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/samsung/android/sdk/moneta/memory/entity/content/Document;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getContentUri", "getTitle", "J", "getRawFileId", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Document extends Content {
    public static final Parcelable.Creator<Document> CREATOR = new Creator();
    private final String contentUri;
    private final String id;
    private final long rawFileId;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Document> {
        public final Document createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Document(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
        }

        public final Document[] newArray(int i2) {
            return new Document[i2];
        }
    }

    public Document(String str, String str2, String str3, long j2) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        j.e(str3, "title");
        this.id = str;
        this.contentUri = str2;
        this.title = str3;
        this.rawFileId = j2;
    }

    public static /* synthetic */ Document copy$default(Document document, String str, String str2, String str3, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = document.id;
        }
        if ((i2 & 2) != 0) {
            str2 = document.contentUri;
        }
        if ((i2 & 4) != 0) {
            str3 = document.title;
        }
        if ((i2 & 8) != 0) {
            j2 = document.rawFileId;
        }
        long j3 = j2;
        return document.copy(str, str2, str3, j3);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.contentUri;
    }

    public final String component3() {
        return this.title;
    }

    public final long component4() {
        return this.rawFileId;
    }

    public final Document copy(String str, String str2, String str3, long j2) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        j.e(str3, "title");
        return new Document(str, str2, str3, j2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Document)) {
            return false;
        }
        Document document = (Document) obj;
        if (j.a(this.id, document.id) && j.a(this.contentUri, document.contentUri) && j.a(this.title, document.title) && this.rawFileId == document.rawFileId) {
            return true;
        }
        return false;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public String getId() {
        return this.id;
    }

    public final long getRawFileId() {
        return this.rawFileId;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return Long.hashCode(this.rawFileId) + C0212a.d(C0212a.d(this.id.hashCode() * 31, 31, this.contentUri), 31, this.title);
    }

    public String toString() {
        return "Document(id=" + this.id + ", contentUri=" + this.contentUri + ", title=" + this.title + ", rawFileId=" + this.rawFileId + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        parcel.writeString(this.title);
        parcel.writeLong(this.rawFileId);
    }
}
