package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001aJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b!\u0010 J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003¢\u0006\u0004\b\"\u0010#Jr\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b&\u0010\u001aJ\u0010\u0010'\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b'\u0010\u0018J\u001a\u0010+\u001a\u00020*2\b\u0010)\u001a\u0004\u0018\u00010(HÖ\u0003¢\u0006\u0004\b+\u0010,R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010-\u001a\u0004\b.\u0010\u001aR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010-\u001a\u0004\b/\u0010\u001aR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010-\u001a\u0004\b0\u0010\u001aR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010-\u001a\u0004\b1\u0010\u001aR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010-\u001a\u0004\b2\u0010\u001aR\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u00103\u001a\u0004\b4\u0010 R\u0019\u0010\n\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u00103\u001a\u0004\b5\u0010 R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006¢\u0006\f\n\u0004\b\r\u00106\u001a\u0004\b7\u0010#¨\u00068"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "Landroid/os/Parcelable;", "", "iri", "literal", "name", "type", "typeDescription", "", "startTimestamp", "endTimestamp", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "outboundEdges", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "()Ljava/lang/Long;", "component7", "component8", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getIri", "getLiteral", "getName", "getType", "getTypeDescription", "Ljava/lang/Long;", "getStartTimestamp", "getEndTimestamp", "Ljava/util/List;", "getOutboundEdges", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GraphPathNode implements Parcelable {
    public static final Parcelable.Creator<GraphPathNode> CREATOR = new Creator();
    private final Long endTimestamp;
    private final String iri;
    private final String literal;
    private final String name;
    private final List<GraphPathEdge> outboundEdges;
    private final Long startTimestamp;
    private final String type;
    private final String typeDescription;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GraphPathNode> {
        public final GraphPathNode createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            Long l8 = l;
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(GraphPathEdge.CREATOR, parcel, arrayList, i2, 1);
            }
            return new GraphPathNode(readString, readString2, readString3, readString4, readString5, valueOf, l8, arrayList);
        }

        public final GraphPathNode[] newArray(int i2) {
            return new GraphPathNode[i2];
        }
    }

    public GraphPathNode(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List<GraphPathEdge> list) {
        j.e(str, "iri");
        j.e(list, GraphPathNodeBundleWrapper.BUNDLE_KEY_OUTBOUND_EDGES);
        this.iri = str;
        this.literal = str2;
        this.name = str3;
        this.type = str4;
        this.typeDescription = str5;
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.outboundEdges = list;
    }

    public static /* synthetic */ GraphPathNode copy$default(GraphPathNode graphPathNode, String str, String str2, String str3, String str4, String str5, Long l, Long l8, List<GraphPathEdge> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = graphPathNode.iri;
        }
        if ((i2 & 2) != 0) {
            str2 = graphPathNode.literal;
        }
        if ((i2 & 4) != 0) {
            str3 = graphPathNode.name;
        }
        if ((i2 & 8) != 0) {
            str4 = graphPathNode.type;
        }
        if ((i2 & 16) != 0) {
            str5 = graphPathNode.typeDescription;
        }
        if ((i2 & 32) != 0) {
            l = graphPathNode.startTimestamp;
        }
        if ((i2 & 64) != 0) {
            l8 = graphPathNode.endTimestamp;
        }
        if ((i2 & 128) != 0) {
            list = graphPathNode.outboundEdges;
        }
        Long l10 = l8;
        List<GraphPathEdge> list2 = list;
        String str6 = str5;
        Long l11 = l;
        String str7 = str4;
        String str8 = str2;
        return graphPathNode.copy(str, str8, str3, str7, str6, l11, l10, list2);
    }

    public final String component1() {
        return this.iri;
    }

    public final String component2() {
        return this.literal;
    }

    public final String component3() {
        return this.name;
    }

    public final String component4() {
        return this.type;
    }

    public final String component5() {
        return this.typeDescription;
    }

    public final Long component6() {
        return this.startTimestamp;
    }

    public final Long component7() {
        return this.endTimestamp;
    }

    public final List<GraphPathEdge> component8() {
        return this.outboundEdges;
    }

    public final GraphPathNode copy(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List<GraphPathEdge> list) {
        j.e(str, "iri");
        List<GraphPathEdge> list2 = list;
        j.e(list2, GraphPathNodeBundleWrapper.BUNDLE_KEY_OUTBOUND_EDGES);
        return new GraphPathNode(str, str2, str3, str4, str5, l, l8, list2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphPathNode)) {
            return false;
        }
        GraphPathNode graphPathNode = (GraphPathNode) obj;
        if (j.a(this.iri, graphPathNode.iri) && j.a(this.literal, graphPathNode.literal) && j.a(this.name, graphPathNode.name) && j.a(this.type, graphPathNode.type) && j.a(this.typeDescription, graphPathNode.typeDescription) && j.a(this.startTimestamp, graphPathNode.startTimestamp) && j.a(this.endTimestamp, graphPathNode.endTimestamp) && j.a(this.outboundEdges, graphPathNode.outboundEdges)) {
            return true;
        }
        return false;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getIri() {
        return this.iri;
    }

    public final String getLiteral() {
        return this.literal;
    }

    public final String getName() {
        return this.name;
    }

    public final List<GraphPathEdge> getOutboundEdges() {
        return this.outboundEdges;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final String getType() {
        return this.type;
    }

    public final String getTypeDescription() {
        return this.typeDescription;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int hashCode = this.iri.hashCode() * 31;
        String str = this.literal;
        int i12 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i13 = (hashCode + i2) * 31;
        String str2 = this.name;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        String str3 = this.type;
        if (str3 == null) {
            i8 = 0;
        } else {
            i8 = str3.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        String str4 = this.typeDescription;
        if (str4 == null) {
            i10 = 0;
        } else {
            i10 = str4.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        Long l = this.startTimestamp;
        if (l == null) {
            i11 = 0;
        } else {
            i11 = l.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        Long l8 = this.endTimestamp;
        if (l8 != null) {
            i12 = l8.hashCode();
        }
        return this.outboundEdges.hashCode() + ((i17 + i12) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GraphPathNode(iri=");
        sb2.append(this.iri);
        sb2.append(", literal=");
        sb2.append(this.literal);
        sb2.append(", name=");
        sb2.append(this.name);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", typeDescription=");
        sb2.append(this.typeDescription);
        sb2.append(", startTimestamp=");
        sb2.append(this.startTimestamp);
        sb2.append(", endTimestamp=");
        sb2.append(this.endTimestamp);
        sb2.append(", outboundEdges=");
        return C0212a.r(sb2, this.outboundEdges, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.iri);
        parcel.writeString(this.literal);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.typeDescription);
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        Iterator j2 = a.j(parcel, this.outboundEdges);
        while (j2.hasNext()) {
            ((GraphPathEdge) j2.next()).writeToParcel(parcel, i2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GraphPathNode(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List list, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : l, (i2 & 64) != 0 ? null : l8, list);
    }
}
