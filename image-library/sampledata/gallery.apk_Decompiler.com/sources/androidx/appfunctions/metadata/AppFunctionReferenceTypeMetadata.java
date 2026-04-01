package androidx.appfunctions.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B#\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "referenceDataType", "", "isNullable", "description", "<init>", "(Ljava/lang/String;ZLjava/lang/String;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getReferenceDataType", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionReferenceTypeMetadata extends AppFunctionDataTypeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final String referenceDataType;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata$Companion;", "", "<init>", "()V", "TYPE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionReferenceTypeMetadata(String str, boolean z, String str2) {
        super(z, str2);
        j.e(str, "referenceDataType");
        j.e(str2, "description");
        this.referenceDataType = str;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof AppFunctionReferenceTypeMetadata)) {
            return j.a(this.referenceDataType, ((AppFunctionReferenceTypeMetadata) obj).referenceDataType);
        }
        return false;
    }

    public final String getReferenceDataType() {
        return this.referenceDataType;
    }

    public int hashCode() {
        return this.referenceDataType.hashCode() + (super.hashCode() * 31);
    }

    public String toString() {
        return "AppFunctionReferenceTypeMetadata(referenceDataType=" + this.referenceDataType + ", isNullable=" + isNullable() + ",description=" + getDescription() + ')';
    }
}
