package androidx.appfunctions.metadata;

import c0.C0086a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0010¨\u0006\u0016"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "valueType", "", "description", "<init>", "(Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;Ljava/lang/String;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getValueType", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "Ljava/lang/String;", "getDescription", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionResponseMetadata {
    private final String description;
    private final AppFunctionDataTypeMetadata valueType;

    public AppFunctionResponseMetadata(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, String str) {
        j.e(appFunctionDataTypeMetadata, "valueType");
        j.e(str, "description");
        this.valueType = appFunctionDataTypeMetadata;
        this.description = str;
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
        if (!AppFunctionResponseMetadata.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type androidx.appfunctions.metadata.AppFunctionResponseMetadata");
        AppFunctionResponseMetadata appFunctionResponseMetadata = (AppFunctionResponseMetadata) obj;
        if (j.a(this.valueType, appFunctionResponseMetadata.valueType) && j.a(this.description, appFunctionResponseMetadata.description)) {
            return true;
        }
        return false;
    }

    public final AppFunctionDataTypeMetadata getValueType() {
        return this.valueType;
    }

    public int hashCode() {
        return this.description.hashCode() + (this.valueType.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionResponseMetadata(valueType=");
        sb2.append(this.valueType);
        sb2.append(", description=");
        return C0086a.m(sb2, this.description, ')');
    }
}
