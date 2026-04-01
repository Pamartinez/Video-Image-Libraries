package androidx.appfunctions.metadata;

import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1203u;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "", "", "", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "dataTypes", "<init>", "(Ljava/util/Map;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getDataTypes", "()Ljava/util/Map;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionComponentsMetadata {
    private final Map<String, AppFunctionDataTypeMetadata> dataTypes;

    public AppFunctionComponentsMetadata(Map<String, ? extends AppFunctionDataTypeMetadata> map) {
        j.e(map, "dataTypes");
        this.dataTypes = map;
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
        if (!AppFunctionComponentsMetadata.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type androidx.appfunctions.metadata.AppFunctionComponentsMetadata");
        return j.a(this.dataTypes, ((AppFunctionComponentsMetadata) obj).dataTypes);
    }

    public final Map<String, AppFunctionDataTypeMetadata> getDataTypes() {
        return this.dataTypes;
    }

    public int hashCode() {
        return Objects.hashCode(this.dataTypes);
    }

    public String toString() {
        return "AppFunctionComponentsMetadata(dataTypes=" + this.dataTypes + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AppFunctionComponentsMetadata(Map map, int i2, e eVar) {
        this((i2 & 1) != 0 ? C1203u.d : map);
    }
}
