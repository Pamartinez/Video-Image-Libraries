package androidx.appfunctions.metadata;

import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001f\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionIntTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "isNullable", "", "description", "", "", "enumValues", "<init>", "(ZLjava/lang/String;Ljava/util/Set;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/util/Set;", "getEnumValues", "()Ljava/util/Set;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionIntTypeMetadata extends AppFunctionDataTypeMetadata {
    private final Set<Integer> enumValues;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionIntTypeMetadata(boolean z, String str, Set<Integer> set) {
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
        if ((obj instanceof AppFunctionIntTypeMetadata) && super.equals(obj) && j.a(this.enumValues, ((AppFunctionIntTypeMetadata) obj).enumValues)) {
            return true;
        }
        return false;
    }

    public final Set<Integer> getEnumValues() {
        return this.enumValues;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(isNullable()), getDescription(), this.enumValues});
    }

    public String toString() {
        return "AppFunctionIntTypeMetadata(isNullable=" + isNullable() + ", description=" + getDescription() + ", enumValues=" + this.enumValues + ')';
    }
}
