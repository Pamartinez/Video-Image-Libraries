package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathEdgeBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathEdge;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u001e\u0010\u0019R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u001f\u0010\u0019¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathEdgeWrapperV1;", "Landroid/os/Parcelable;", "", "iri", "nextNodeIri", "", "inverse", "label", "comment", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getIri", "()Ljava/lang/String;", "getNextNodeIri", "Z", "getInverse", "()Z", "getLabel", "getComment", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GraphPathEdgeWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<GraphPathEdgeWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String comment;
    private final boolean inverse;
    private final String iri;
    private final String label;
    private final String nextNodeIri;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathEdgeWrapperV1$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathEdgeWrapperV1;", "graphPathEdge", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ GraphPathEdgeWrapperV1 fromContent(GraphPathEdge graphPathEdge) {
            j.e(graphPathEdge, "graphPathEdge");
            return new GraphPathEdgeWrapperV1(graphPathEdge.getIri(), graphPathEdge.getNextNodeIri(), graphPathEdge.getInverse(), graphPathEdge.getLabel(), graphPathEdge.getComment());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GraphPathEdgeWrapperV1> {
        public final GraphPathEdgeWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new GraphPathEdgeWrapperV1(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        public final GraphPathEdgeWrapperV1[] newArray(int i2) {
            return new GraphPathEdgeWrapperV1[i2];
        }
    }

    public GraphPathEdgeWrapperV1(String str, String str2, boolean z, String str3, String str4) {
        j.e(str, "iri");
        j.e(str2, GraphPathEdgeBundleWrapper.BUNDLE_KEY_NEXT_NODE_IRI);
        this.iri = str;
        this.nextNodeIri = str2;
        this.inverse = z;
        this.label = str3;
        this.comment = str4;
    }

    public final int describeContents() {
        return 0;
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

    public final /* synthetic */ GraphPathEdge toContent() {
        return new GraphPathEdge(this.iri, this.nextNodeIri, this.inverse, this.label, this.comment);
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
    public /* synthetic */ GraphPathEdgeWrapperV1(String str, String str2, boolean z, String str3, String str4, int i2, e eVar) {
        this(str, str2, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4);
    }
}
