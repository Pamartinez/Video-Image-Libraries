package androidx.appfunctions.metadata;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0018\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u001c\u0010\u000e¨\u0006\u001d"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionResponseMetadataDocument;", "", "", "namespace", "id", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "valueType", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;Ljava/lang/String;)V", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "toAppFunctionResponseMetadata", "()Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getValueType", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getDescription", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionResponseMetadataDocument {
    private final String description;
    private final String id;
    private final String namespace;
    private final AppFunctionDataTypeMetadataDocument valueType;

    public AppFunctionResponseMetadataDocument(String str, String str2, AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument, String str3) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(appFunctionDataTypeMetadataDocument, "valueType");
        this.namespace = str;
        this.id = str2;
        this.valueType = appFunctionDataTypeMetadataDocument;
        this.description = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionResponseMetadataDocument)) {
            return false;
        }
        AppFunctionResponseMetadataDocument appFunctionResponseMetadataDocument = (AppFunctionResponseMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionResponseMetadataDocument.namespace) && j.a(this.id, appFunctionResponseMetadataDocument.id) && j.a(this.valueType, appFunctionResponseMetadataDocument.valueType) && j.a(this.description, appFunctionResponseMetadataDocument.description)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int hashCode = (this.valueType.hashCode() + C0212a.d(this.namespace.hashCode() * 31, 31, this.id)) * 31;
        String str = this.description;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    public final AppFunctionResponseMetadata toAppFunctionResponseMetadata() {
        AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = this.valueType.toAppFunctionDataTypeMetadata();
        String str = this.description;
        if (str == null) {
            str = "";
        }
        return new AppFunctionResponseMetadata(appFunctionDataTypeMetadata, str);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionResponseMetadataDocument(namespace=");
        sb2.append(this.namespace);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", valueType=");
        sb2.append(this.valueType);
        sb2.append(", description=");
        return C0086a.m(sb2, this.description, ')');
    }
}
