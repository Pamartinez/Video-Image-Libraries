package androidx.appfunctions.metadata;

import i.C0212a;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.z;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0018\u0010\u000eR \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionComponentsMetadataDocument;", "", "", "namespace", "id", "", "Landroidx/appfunctions/metadata/AppFunctionNamedDataTypeMetadataDocument;", "dataTypes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "toAppFunctionComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "Ljava/util/List;", "getDataTypes", "()Ljava/util/List;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionComponentsMetadataDocument {
    private final List<AppFunctionNamedDataTypeMetadataDocument> dataTypes;
    private final String id;
    private final String namespace;

    public AppFunctionComponentsMetadataDocument(String str, String str2, List<AppFunctionNamedDataTypeMetadataDocument> list) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(list, "dataTypes");
        this.namespace = str;
        this.id = str2;
        this.dataTypes = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionComponentsMetadataDocument)) {
            return false;
        }
        AppFunctionComponentsMetadataDocument appFunctionComponentsMetadataDocument = (AppFunctionComponentsMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionComponentsMetadataDocument.namespace) && j.a(this.id, appFunctionComponentsMetadataDocument.id) && j.a(this.dataTypes, appFunctionComponentsMetadataDocument.dataTypes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.dataTypes.hashCode() + C0212a.d(this.namespace.hashCode() * 31, 31, this.id);
    }

    public final AppFunctionComponentsMetadata toAppFunctionComponentsMetadata() {
        Iterable<AppFunctionNamedDataTypeMetadataDocument> iterable = this.dataTypes;
        int Z = z.Z(C1196n.w0(iterable, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (AppFunctionNamedDataTypeMetadataDocument appFunctionNamedDataTypeMetadataDocument : iterable) {
            linkedHashMap.put(appFunctionNamedDataTypeMetadataDocument.getName(), appFunctionNamedDataTypeMetadataDocument.getDataTypeMetadata().toAppFunctionDataTypeMetadata());
        }
        return new AppFunctionComponentsMetadata(linkedHashMap);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionComponentsMetadataDocument(namespace=");
        sb2.append(this.namespace);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", dataTypes=");
        return C0212a.r(sb2, this.dataTypes, ')');
    }
}
