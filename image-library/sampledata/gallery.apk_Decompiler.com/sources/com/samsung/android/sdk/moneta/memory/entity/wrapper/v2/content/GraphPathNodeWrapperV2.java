package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathEdge;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*Be\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\n\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b&\u0010%R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathNodeWrapperV2;", "Landroid/os/Parcelable;", "", "iri", "literal", "name", "type", "typeDescription", "", "startTimestamp", "endTimestamp", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathEdgeWrapperV2;", "outboundEdgesWrappers", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getIri", "()Ljava/lang/String;", "getLiteral", "getName", "getType", "getTypeDescription", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/util/List;", "getOutboundEdgesWrappers", "()Ljava/util/List;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GraphPathNodeWrapperV2 implements Parcelable {
    public static final Parcelable.Creator<GraphPathNodeWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Long endTimestamp;
    private final String iri;
    private final String literal;
    private final String name;
    private final List<GraphPathEdgeWrapperV2> outboundEdgesWrappers;
    private final Long startTimestamp;
    private final String type;
    private final String typeDescription;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathNodeWrapperV2$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/GraphPathNodeWrapperV2;", "graphPathNode", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ GraphPathNodeWrapperV2 fromContent(GraphPathNode graphPathNode) {
            j.e(graphPathNode, "graphPathNode");
            String iri = graphPathNode.getIri();
            String literal = graphPathNode.getLiteral();
            String name = graphPathNode.getName();
            String type = graphPathNode.getType();
            String typeDescription = graphPathNode.getTypeDescription();
            Long startTimestamp = graphPathNode.getStartTimestamp();
            Long endTimestamp = graphPathNode.getEndTimestamp();
            Iterable<GraphPathEdge> outboundEdges = graphPathNode.getOutboundEdges();
            ArrayList arrayList = new ArrayList(C1196n.w0(outboundEdges, 10));
            for (GraphPathEdge wrapperV2 : outboundEdges) {
                arrayList.add(j.e(wrapperV2, "<this>"));
            }
            return new GraphPathNodeWrapperV2(iri, literal, name, type, typeDescription, startTimestamp, endTimestamp, arrayList);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GraphPathNodeWrapperV2> {
        public final GraphPathNodeWrapperV2 createFromParcel(Parcel parcel) {
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
                i2 = a.a(GraphPathEdgeWrapperV2.CREATOR, parcel, arrayList, i2, 1);
            }
            return new GraphPathNodeWrapperV2(readString, readString2, readString3, readString4, readString5, valueOf, l8, arrayList);
        }

        public final GraphPathNodeWrapperV2[] newArray(int i2) {
            return new GraphPathNodeWrapperV2[i2];
        }
    }

    public GraphPathNodeWrapperV2(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List<GraphPathEdgeWrapperV2> list) {
        j.e(str, "iri");
        j.e(list, "outboundEdgesWrappers");
        this.iri = str;
        this.literal = str2;
        this.name = str3;
        this.type = str4;
        this.typeDescription = str5;
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.outboundEdgesWrappers = list;
    }

    public final int describeContents() {
        return 0;
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

    public final List<GraphPathEdgeWrapperV2> getOutboundEdgesWrappers() {
        return this.outboundEdgesWrappers;
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

    public final /* synthetic */ GraphPathNode toContent() {
        String str = this.iri;
        String str2 = this.literal;
        String str3 = this.name;
        String str4 = this.type;
        String str5 = this.typeDescription;
        Long l = this.startTimestamp;
        Long l8 = this.endTimestamp;
        Iterable<GraphPathEdgeWrapperV2> iterable = this.outboundEdgesWrappers;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (GraphPathEdgeWrapperV2 content : iterable) {
            arrayList.add(content.toContent());
        }
        return new GraphPathNode(str, str2, str3, str4, str5, l, l8, arrayList);
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
        Iterator j2 = a.j(parcel, this.outboundEdgesWrappers);
        while (j2.hasNext()) {
            ((GraphPathEdgeWrapperV2) j2.next()).writeToParcel(parcel, i2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GraphPathNodeWrapperV2(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List list, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : l, (i2 & 64) != 0 ? null : l8, list);
    }
}
