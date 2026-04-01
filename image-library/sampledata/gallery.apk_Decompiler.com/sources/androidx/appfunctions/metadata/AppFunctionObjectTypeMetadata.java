package androidx.appfunctions.metadata;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eBG\b\u0007\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R#\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001d\u0010\u0015¨\u0006\u001f"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "", "properties", "", "required", "qualifiedName", "", "isNullable", "description", "<init>", "(Ljava/util/Map;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getProperties", "()Ljava/util/Map;", "Ljava/util/List;", "getRequired", "()Ljava/util/List;", "Ljava/lang/String;", "getQualifiedName", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionObjectTypeMetadata extends AppFunctionDataTypeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final Map<String, AppFunctionDataTypeMetadata> properties;
    private final String qualifiedName;
    private final List<String> required;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata$Companion;", "", "<init>", "()V", "TYPE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionObjectTypeMetadata(Map<String, ? extends AppFunctionDataTypeMetadata> map, List<String> list, String str, boolean z, String str2) {
        super(z, str2);
        j.e(map, "properties");
        j.e(list, "required");
        j.e(str2, "description");
        this.properties = map;
        this.required = list;
        this.qualifiedName = str;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof AppFunctionObjectTypeMetadata)) {
            return false;
        }
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata = (AppFunctionObjectTypeMetadata) obj;
        if (j.a(this.properties, appFunctionObjectTypeMetadata.properties) && j.a(this.required, appFunctionObjectTypeMetadata.required) && j.a(this.qualifiedName, appFunctionObjectTypeMetadata.qualifiedName)) {
            return true;
        }
        return false;
    }

    public final Map<String, AppFunctionDataTypeMetadata> getProperties() {
        return this.properties;
    }

    public final String getQualifiedName() {
        return this.qualifiedName;
    }

    public final List<String> getRequired() {
        return this.required;
    }

    public int hashCode() {
        int hashCode = this.properties.hashCode();
        int hashCode2 = this.required.hashCode() + ((hashCode + (super.hashCode() * 31)) * 31);
        String str = this.qualifiedName;
        if (str == null) {
            return hashCode2;
        }
        return str.hashCode() + (hashCode2 * 31);
    }

    public String toString() {
        return "AppFunctionObjectTypeMetadata(properties=" + this.properties + ", required=" + this.required + ", qualifiedName=" + this.qualifiedName + ", isNullable=" + isNullable() + ",description=" + getDescription() + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AppFunctionObjectTypeMetadata(Map map, List list, String str, boolean z, String str2, int i2, e eVar) {
        this(map, list, str, z, (i2 & 16) != 0 ? "" : str2);
    }
}
