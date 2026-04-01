package androidx.appfunctions.metadata;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\tR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0013\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0014\u0010\t¨\u0006\u0015"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadataDocument;", "", "", "namespace", "id", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "getMessage", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionDeprecationMetadataDocument {
    private final String id;
    private final String message;
    private final String namespace;

    public AppFunctionDeprecationMetadataDocument(String str, String str2, String str3) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(str3, "message");
        this.namespace = str;
        this.id = str2;
        this.message = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionDeprecationMetadataDocument)) {
            return false;
        }
        AppFunctionDeprecationMetadataDocument appFunctionDeprecationMetadataDocument = (AppFunctionDeprecationMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionDeprecationMetadataDocument.namespace) && j.a(this.id, appFunctionDeprecationMetadataDocument.id) && j.a(this.message, appFunctionDeprecationMetadataDocument.message)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.message.hashCode() + C0212a.d(this.namespace.hashCode() * 31, 31, this.id);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionDeprecationMetadataDocument(namespace=");
        sb2.append(this.namespace);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", message=");
        return C0086a.m(sb2, this.message, ')');
    }
}
