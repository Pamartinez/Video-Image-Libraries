package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathEdgeBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0015J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0015JF\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u0010\u0010\u001e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0013J\u001a\u0010!\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010#\u001a\u0004\b%\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b'\u0010\u0018R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b(\u0010\u0015R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b)\u0010\u0015¨\u0006*"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "Landroid/os/Parcelable;", "", "iri", "nextNodeIri", "", "inverse", "label", "comment", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Z", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getIri", "getNextNodeIri", "Z", "getInverse", "getLabel", "getComment", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GraphPathEdge implements Parcelable {
    public static final Parcelable.Creator<GraphPathEdge> CREATOR = new Creator();
    private final String comment;
    private final boolean inverse;
    private final String iri;
    private final String label;
    private final String nextNodeIri;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GraphPathEdge> {
        public final GraphPathEdge createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new GraphPathEdge(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        public final GraphPathEdge[] newArray(int i2) {
            return new GraphPathEdge[i2];
        }
    }

    public GraphPathEdge(String str, String str2, boolean z, String str3, String str4) {
        j.e(str, "iri");
        j.e(str2, GraphPathEdgeBundleWrapper.BUNDLE_KEY_NEXT_NODE_IRI);
        this.iri = str;
        this.nextNodeIri = str2;
        this.inverse = z;
        this.label = str3;
        this.comment = str4;
    }

    public static /* synthetic */ GraphPathEdge copy$default(GraphPathEdge graphPathEdge, String str, String str2, boolean z, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = graphPathEdge.iri;
        }
        if ((i2 & 2) != 0) {
            str2 = graphPathEdge.nextNodeIri;
        }
        if ((i2 & 4) != 0) {
            z = graphPathEdge.inverse;
        }
        if ((i2 & 8) != 0) {
            str3 = graphPathEdge.label;
        }
        if ((i2 & 16) != 0) {
            str4 = graphPathEdge.comment;
        }
        String str5 = str3;
        String str6 = str4;
        return graphPathEdge.copy(str, str2, z, str5, str6);
    }

    public final String component1() {
        return this.iri;
    }

    public final String component2() {
        return this.nextNodeIri;
    }

    public final boolean component3() {
        return this.inverse;
    }

    public final String component4() {
        return this.label;
    }

    public final String component5() {
        return this.comment;
    }

    public final GraphPathEdge copy(String str, String str2, boolean z, String str3, String str4) {
        j.e(str, "iri");
        j.e(str2, GraphPathEdgeBundleWrapper.BUNDLE_KEY_NEXT_NODE_IRI);
        return new GraphPathEdge(str, str2, z, str3, str4);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphPathEdge)) {
            return false;
        }
        GraphPathEdge graphPathEdge = (GraphPathEdge) obj;
        if (j.a(this.iri, graphPathEdge.iri) && j.a(this.nextNodeIri, graphPathEdge.nextNodeIri) && this.inverse == graphPathEdge.inverse && j.a(this.label, graphPathEdge.label) && j.a(this.comment, graphPathEdge.comment)) {
            return true;
        }
        return false;
    }

    public final String getComment() {
        return this.comment;
    }

    public final boolean getInverse() {
        return this.inverse;
    }

    public final String getIri() {
        return this.iri;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getNextNodeIri() {
        return this.nextNodeIri;
    }

    public int hashCode() {
        int i2;
        int e = C0212a.e(C0212a.d(this.iri.hashCode() * 31, 31, this.nextNodeIri), 31, this.inverse);
        String str = this.label;
        int i7 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i8 = (e + i2) * 31;
        String str2 = this.comment;
        if (str2 != null) {
            i7 = str2.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GraphPathEdge(iri=");
        sb2.append(this.iri);
        sb2.append(", nextNodeIri=");
        sb2.append(this.nextNodeIri);
        sb2.append(", inverse=");
        sb2.append(this.inverse);
        sb2.append(", label=");
        sb2.append(this.label);
        sb2.append(", comment=");
        return C0086a.m(sb2, this.comment, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.iri);
        parcel.writeString(this.nextNodeIri);
        parcel.writeInt(this.inverse ? 1 : 0);
        parcel.writeString(this.label);
        parcel.writeString(this.comment);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GraphPathEdge(String str, String str2, boolean z, String str3, String str4, int i2, e eVar) {
        this(str, str2, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4);
    }
}
