package androidx.appfunctions.metadata;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\b\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u0007\u0010\u001dR\u001a\u0010\t\u001a\u00020\b8\u0006X\u0004¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b!\u0010\u0011¨\u0006\""}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionParameterMetadataDocument;", "", "", "namespace", "id", "name", "", "isRequired", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "dataTypeMetadata", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLandroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;Ljava/lang/String;)V", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "toAppFunctionParameterMetadata", "()Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "getName", "Z", "()Z", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getDataTypeMetadata", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getDescription", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionParameterMetadataDocument {
    private final AppFunctionDataTypeMetadataDocument dataTypeMetadata;
    private final String description;
    private final String id;
    private final boolean isRequired;
    private final String name;
    private final String namespace;

    public AppFunctionParameterMetadataDocument(String str, String str2, String str3, boolean z, AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument, String str4) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(str3, "name");
        j.e(appFunctionDataTypeMetadataDocument, "dataTypeMetadata");
        this.namespace = str;
        this.id = str2;
        this.name = str3;
        this.isRequired = z;
        this.dataTypeMetadata = appFunctionDataTypeMetadataDocument;
        this.description = str4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionParameterMetadataDocument)) {
            return false;
        }
        AppFunctionParameterMetadataDocument appFunctionParameterMetadataDocument = (AppFunctionParameterMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionParameterMetadataDocument.namespace) && j.a(this.id, appFunctionParameterMetadataDocument.id) && j.a(this.name, appFunctionParameterMetadataDocument.name) && this.isRequired == appFunctionParameterMetadataDocument.isRequired && j.a(this.dataTypeMetadata, appFunctionParameterMetadataDocument.dataTypeMetadata) && j.a(this.description, appFunctionParameterMetadataDocument.description)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int hashCode = (this.dataTypeMetadata.hashCode() + C0212a.e(C0212a.d(C0212a.d(this.namespace.hashCode() * 31, 31, this.id), 31, this.name), 31, this.isRequired)) * 31;
        String str = this.description;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    public final AppFunctionParameterMetadata toAppFunctionParameterMetadata() {
        String str = this.name;
        boolean z = this.isRequired;
        AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = this.dataTypeMetadata.toAppFunctionDataTypeMetadata();
        String str2 = this.description;
        if (str2 == null) {
            str2 = "";
        }
        return new AppFunctionParameterMetadata(str, z, appFunctionDataTypeMetadata, str2);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionParameterMetadataDocument(namespace=");
        sb2.append(this.namespace);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", name=");
        sb2.append(this.name);
        sb2.append(", isRequired=");
        sb2.append(this.isRequired);
        sb2.append(", dataTypeMetadata=");
        sb2.append(this.dataTypeMetadata);
        sb2.append(", description=");
        return C0086a.m(sb2, this.description, ')');
    }
}
