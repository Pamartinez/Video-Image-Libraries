package androidx.appfunctions.metadata;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0005\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u001a\u0010\u0012¨\u0006\u001b"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "", "", "name", "", "isRequired", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "dataType", "description", "<init>", "(Ljava/lang/String;ZLandroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;Ljava/lang/String;)V", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getName", "Z", "()Z", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDescription", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionParameterMetadata {
    private final AppFunctionDataTypeMetadata dataType;
    private final String description;
    private final boolean isRequired;
    private final String name;

    public AppFunctionParameterMetadata(String str, boolean z, AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, String str2) {
        j.e(str, "name");
        j.e(appFunctionDataTypeMetadata, "dataType");
        j.e(str2, "description");
        this.name = str;
        this.isRequired = z;
        this.dataType = appFunctionDataTypeMetadata;
        this.description = str2;
    }

    public boolean equals(Object obj) {
        Class<?> cls;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = null;
        }
        if (!AppFunctionParameterMetadata.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type androidx.appfunctions.metadata.AppFunctionParameterMetadata");
        AppFunctionParameterMetadata appFunctionParameterMetadata = (AppFunctionParameterMetadata) obj;
        if (j.a(this.name, appFunctionParameterMetadata.name) && this.isRequired == appFunctionParameterMetadata.isRequired && j.a(this.dataType, appFunctionParameterMetadata.dataType) && j.a(this.description, appFunctionParameterMetadata.description)) {
            return true;
        }
        return false;
    }

    public final AppFunctionDataTypeMetadata getDataType() {
        return this.dataType;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int e = C0212a.e(this.name.hashCode() * 31, 31, this.isRequired);
        return this.description.hashCode() + ((this.dataType.hashCode() + e) * 31);
    }

    public final boolean isRequired() {
        return this.isRequired;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionParameterMetadata(name=");
        sb2.append(this.name);
        sb2.append(", isRequired=");
        sb2.append(this.isRequired);
        sb2.append(", dataType=");
        sb2.append(this.dataType);
        sb2.append(",description=");
        return C0086a.m(sb2, this.description, ')');
    }
}
