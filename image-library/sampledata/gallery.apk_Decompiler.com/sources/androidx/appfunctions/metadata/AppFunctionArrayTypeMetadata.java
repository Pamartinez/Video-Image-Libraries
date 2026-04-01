package androidx.appfunctions.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "itemType", "", "isNullable", "", "description", "<init>", "(Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;ZLjava/lang/String;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getItemType", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionArrayTypeMetadata extends AppFunctionDataTypeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final AppFunctionDataTypeMetadata itemType;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata$Companion;", "", "<init>", "()V", "TYPE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionArrayTypeMetadata(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, boolean z, String str) {
        super(z, str);
        j.e(appFunctionDataTypeMetadata, "itemType");
        j.e(str, "description");
        this.itemType = appFunctionDataTypeMetadata;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof AppFunctionArrayTypeMetadata)) {
            return j.a(this.itemType, ((AppFunctionArrayTypeMetadata) obj).itemType);
        }
        return false;
    }

    public final AppFunctionDataTypeMetadata getItemType() {
        return this.itemType;
    }

    public int hashCode() {
        return this.itemType.hashCode() + (super.hashCode() * 31);
    }

    public String toString() {
        return "AppFunctionArrayTypeMetadataDocument(itemType=" + this.itemType + ", isNullable=" + isNullable() + ",description=" + getDescription() + ')';
    }
}
