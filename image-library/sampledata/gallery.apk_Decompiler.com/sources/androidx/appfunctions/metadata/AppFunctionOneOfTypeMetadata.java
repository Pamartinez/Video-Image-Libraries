package androidx.appfunctions.metadata;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB1\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u0013¨\u0006\u001d"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionOneOfTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "matchOneOf", "", "qualifiedName", "", "isNullable", "description", "<init>", "(Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "getObjectMetadataForOneOfType$appfunctions", "(Ljava/lang/String;)Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getObjectMetadataForOneOfType", "Ljava/util/List;", "getMatchOneOf", "()Ljava/util/List;", "Ljava/lang/String;", "getQualifiedName", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionOneOfTypeMetadata extends AppFunctionDataTypeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final List<AppFunctionDataTypeMetadata> matchOneOf;
    private final String qualifiedName;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionOneOfTypeMetadata$Companion;", "", "<init>", "()V", "TYPE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionOneOfTypeMetadata(List<? extends AppFunctionDataTypeMetadata> list, String str, boolean z, String str2) {
        super(z, str2);
        j.e(list, "matchOneOf");
        j.e(str, "qualifiedName");
        j.e(str2, "description");
        this.matchOneOf = list;
        this.qualifiedName = str;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof AppFunctionOneOfTypeMetadata)) {
            return false;
        }
        AppFunctionOneOfTypeMetadata appFunctionOneOfTypeMetadata = (AppFunctionOneOfTypeMetadata) obj;
        if (!j.a(this.qualifiedName, appFunctionOneOfTypeMetadata.qualifiedName)) {
            return false;
        }
        return j.a(this.matchOneOf, appFunctionOneOfTypeMetadata.matchOneOf);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadata} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadata} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadata} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadata} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadata} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.appfunctions.metadata.AppFunctionDataTypeMetadata getObjectMetadataForOneOfType$appfunctions(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "qualifiedName"
            kotlin.jvm.internal.j.e(r7, r0)
            java.util.List<androidx.appfunctions.metadata.AppFunctionDataTypeMetadata> r6 = r6.matchOneOf
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
            r0 = 0
            r1 = 0
            r2 = r0
        L_0x0010:
            boolean r3 = r6.hasNext()
            if (r3 == 0) goto L_0x006a
            java.lang.Object r3 = r6.next()
            r4 = r3
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r4 = (androidx.appfunctions.metadata.AppFunctionDataTypeMetadata) r4
            boolean r5 = r4 instanceof androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata
            if (r5 == 0) goto L_0x002c
            androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata r4 = (androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata) r4
            java.lang.String r4 = r4.getQualifiedName()
            boolean r4 = kotlin.jvm.internal.j.a(r4, r7)
            goto L_0x0049
        L_0x002c:
            boolean r5 = r4 instanceof androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata
            if (r5 == 0) goto L_0x003b
            androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata r4 = (androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata) r4
            java.lang.String r4 = r4.getReferenceDataType()
            boolean r4 = kotlin.jvm.internal.j.a(r4, r7)
            goto L_0x0049
        L_0x003b:
            boolean r5 = r4 instanceof androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata
            if (r5 == 0) goto L_0x0051
            androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata r4 = (androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata) r4
            java.lang.String r4 = r4.getQualifiedName()
            boolean r4 = kotlin.jvm.internal.j.a(r4, r7)
        L_0x0049:
            if (r4 == 0) goto L_0x0010
            if (r1 == 0) goto L_0x004e
            goto L_0x006e
        L_0x004e:
            r1 = 1
            r2 = r3
            goto L_0x0010
        L_0x0051:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected data type "
            r7.<init>(r0)
            r7.append(r4)
            java.lang.String r0 = " for one of type"
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x006a:
            if (r1 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r0 = r2
        L_0x006e:
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r0 = (androidx.appfunctions.metadata.AppFunctionDataTypeMetadata) r0
            if (r0 == 0) goto L_0x0073
            return r0
        L_0x0073:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = " does not match any of the oneOf types"
            java.lang.String r7 = r7.concat(r0)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.metadata.AppFunctionOneOfTypeMetadata.getObjectMetadataForOneOfType$appfunctions(java.lang.String):androidx.appfunctions.metadata.AppFunctionDataTypeMetadata");
    }

    public int hashCode() {
        return this.qualifiedName.hashCode() + C0212a.f(this.matchOneOf, super.hashCode() * 31, 31);
    }

    public String toString() {
        return "AppFunctionOneOfTypeMetadata(matchOneOf=" + this.matchOneOf + ", isNullable=" + isNullable() + ", description=" + getDescription() + ')';
    }
}
