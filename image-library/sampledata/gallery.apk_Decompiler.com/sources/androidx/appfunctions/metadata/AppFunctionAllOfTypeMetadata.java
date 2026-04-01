package androidx.appfunctions.metadata;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB3\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u0013¨\u0006 "}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "", "matchAll", "", "qualifiedName", "", "isNullable", "description", "<init>", "(Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "getPseudoObjectTypeMetadata$appfunctions", "(Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "getPseudoObjectTypeMetadata", "Ljava/util/List;", "getMatchAll", "()Ljava/util/List;", "Ljava/lang/String;", "getQualifiedName", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionAllOfTypeMetadata extends AppFunctionDataTypeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final List<AppFunctionDataTypeMetadata> matchAll;
    private final String qualifiedName;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata$Companion;", "", "<init>", "()V", "TYPE", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppFunctionAllOfTypeMetadata(List<? extends AppFunctionDataTypeMetadata> list, String str, boolean z, String str2) {
        super(z, str2);
        j.e(list, "matchAll");
        j.e(str2, "description");
        this.matchAll = list;
        this.qualifiedName = str;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof AppFunctionAllOfTypeMetadata)) {
            return false;
        }
        AppFunctionAllOfTypeMetadata appFunctionAllOfTypeMetadata = (AppFunctionAllOfTypeMetadata) obj;
        if (!j.a(this.qualifiedName, appFunctionAllOfTypeMetadata.qualifiedName)) {
            return false;
        }
        return j.a(this.matchAll, appFunctionAllOfTypeMetadata.matchAll);
    }

    public final AppFunctionObjectTypeMetadata getPseudoObjectTypeMetadata$appfunctions(AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
        j.e(appFunctionComponentsMetadata, "componentsMetadata");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (AppFunctionDataTypeMetadata next : this.matchAll) {
            if (next instanceof AppFunctionReferenceTypeMetadata) {
                AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = (AppFunctionReferenceTypeMetadata) next;
                AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = appFunctionComponentsMetadata.getDataTypes().get(appFunctionReferenceTypeMetadata.getReferenceDataType());
                if (appFunctionDataTypeMetadata == null) {
                    throw new IllegalStateException("Unable to resolve the " + appFunctionReferenceTypeMetadata.getReferenceDataType());
                } else if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
                    AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata = (AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata;
                    linkedHashMap.putAll(appFunctionObjectTypeMetadata.getProperties());
                    linkedHashSet.addAll(appFunctionObjectTypeMetadata.getRequired());
                }
            } else if (next instanceof AppFunctionObjectTypeMetadata) {
                AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata2 = (AppFunctionObjectTypeMetadata) next;
                linkedHashMap.putAll(appFunctionObjectTypeMetadata2.getProperties());
                linkedHashSet.addAll(appFunctionObjectTypeMetadata2.getRequired());
            } else if (next instanceof AppFunctionAllOfTypeMetadata) {
                AppFunctionObjectTypeMetadata pseudoObjectTypeMetadata$appfunctions = ((AppFunctionAllOfTypeMetadata) next).getPseudoObjectTypeMetadata$appfunctions(appFunctionComponentsMetadata);
                linkedHashMap.putAll(pseudoObjectTypeMetadata$appfunctions.getProperties());
                linkedHashSet.addAll(pseudoObjectTypeMetadata$appfunctions.getRequired());
            }
        }
        return new AppFunctionObjectTypeMetadata(linkedHashMap, C1194l.k1(linkedHashSet), this.qualifiedName, false, "");
    }

    public final String getQualifiedName() {
        return this.qualifiedName;
    }

    public int hashCode() {
        int hashCode = this.matchAll.hashCode() + (super.hashCode() * 31);
        String str = this.qualifiedName;
        if (str == null) {
            return hashCode;
        }
        return str.hashCode() + (hashCode * 31);
    }

    public String toString() {
        return "AppFunctionAllOfTypeMetadata(matchAll=" + this.matchAll + ", isNullable=" + isNullable() + ", description=" + getDescription() + ')';
    }
}
