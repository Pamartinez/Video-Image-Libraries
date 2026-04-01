package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathEdge;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*Be\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\n\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b&\u0010%R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathNodeWrapperV1;", "Landroid/os/Parcelable;", "", "iri", "literal", "name", "type", "typeDescription", "", "startTimestamp", "endTimestamp", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathEdge;", "outboundEdges", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getIri", "()Ljava/lang/String;", "getLiteral", "getName", "getType", "getTypeDescription", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/util/List;", "getOutboundEdges", "()Ljava/util/List;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GraphPathNodeWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<GraphPathNodeWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Long endTimestamp;
    private final String iri;
    private final String literal;
    private final String name;
    private final List<GraphPathEdge> outboundEdges;
    private final Long startTimestamp;
    private final String type;
    private final String typeDescription;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathNodeWrapperV1$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/GraphPathNodeWrapperV1;", "graphPathNode", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ GraphPathNodeWrapperV1 fromContent(GraphPathNode graphPathNode) {
            j.e(graphPathNode, "graphPathNode");
            return new GraphPathNodeWrapperV1(graphPathNode.getIri(), graphPathNode.getLiteral(), graphPathNode.getName(), graphPathNode.getType(), graphPathNode.getTypeDescription(), graphPathNode.getStartTimestamp(), graphPathNode.getEndTimestamp(), graphPathNode.getOutboundEdges());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<GraphPathNodeWrapperV1> {
        public final GraphPathNodeWrapperV1 createFromParcel(Parcel parcel) {
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
            return new GraphPathNodeWrapperV1(readString, readString2, readString3, readString4, readString5, valueOf, l8, arrayList);
        }

        public final GraphPathNodeWrapperV1[] newArray(int i2) {
            return new GraphPathNodeWrapperV1[i2];
        }
    }

    public GraphPathNodeWrapperV1(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List<GraphPathEdge> list) {
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

    public final /* synthetic */ GraphPathNode toContent() {
        return new GraphPathNode(this.iri, this.literal, this.name, this.type, this.typeDescription, this.startTimestamp, this.endTimestamp, this.outboundEdges);
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
    public /* synthetic */ GraphPathNodeWrapperV1(String str, String str2, String str3, String str4, String str5, Long l, Long l8, List list, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : l, (i2 & 64) != 0 ? null : l8, list);
    }
}
