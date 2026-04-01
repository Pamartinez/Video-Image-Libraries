package androidx.appfunctions.metadata;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "isNullable", "", "description", "", "enumValues", "<init>", "(ZLjava/lang/String;Ljava/util/Set;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/util/Set;", "getEnumValues", "()Ljava/util/Set;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionStringTypeMetadata extends AppFunctionDataTypeMetadata {
    private final Set<String> enumValues;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionStringTypeMetadata(boolean z, String str, Set<String> set) {
        super(z, str);
        j.e(str, "description");
        this.enumValues = set;
        if (set != null && set.isEmpty()) {
            throw new IllegalArgumentException("If specified, enumValues cannot be empty.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionStringTypeMetadata)) {
            return false;
        }
        return super.equals(obj);
    }

    public final Set<String> getEnumValues() {
        return this.enumValues;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "AppFunctionStringTypeMetadata(isNullable=" + isNullable() + ", description=" + getDescription() + ')';
    }
}
