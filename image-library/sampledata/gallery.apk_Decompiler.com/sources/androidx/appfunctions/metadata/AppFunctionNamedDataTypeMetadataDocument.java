package androidx.appfunctions.metadata;

import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0015\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0016\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionNamedDataTypeMetadataDocument;", "", "", "namespace", "id", "name", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "dataTypeMetadata", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "getName", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getDataTypeMetadata", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionNamedDataTypeMetadataDocument {
    private final AppFunctionDataTypeMetadataDocument dataTypeMetadata;
    private final String id;
    private final String name;
    private final String namespace;

    public AppFunctionNamedDataTypeMetadataDocument(String str, String str2, String str3, AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(str3, "name");
        j.e(appFunctionDataTypeMetadataDocument, "dataTypeMetadata");
        this.namespace = str;
        this.id = str2;
        this.name = str3;
        this.dataTypeMetadata = appFunctionDataTypeMetadataDocument;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionNamedDataTypeMetadataDocument)) {
            return false;
        }
        AppFunctionNamedDataTypeMetadataDocument appFunctionNamedDataTypeMetadataDocument = (AppFunctionNamedDataTypeMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionNamedDataTypeMetadataDocument.namespace) && j.a(this.id, appFunctionNamedDataTypeMetadataDocument.id) && j.a(this.name, appFunctionNamedDataTypeMetadataDocument.name) && j.a(this.dataTypeMetadata, appFunctionNamedDataTypeMetadataDocument.dataTypeMetadata)) {
            return true;
        }
        return false;
    }

    public final AppFunctionDataTypeMetadataDocument getDataTypeMetadata() {
        return this.dataTypeMetadata;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.dataTypeMetadata.hashCode() + C0212a.d(C0212a.d(this.namespace.hashCode() * 31, 31, this.id), 31, this.name);
    }

    public String toString() {
        return "AppFunctionNamedDataTypeMetadataDocument(namespace=" + this.namespace + ", id=" + this.id + ", name=" + this.name + ", dataTypeMetadata=" + this.dataTypeMetadata + ')';
    }
}
