package androidx.appfunctions;

import android.app.PendingIntent;
import androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBooleanTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBytesTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDoubleTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionFloatTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionIntTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionLongTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionOneOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionPendingIntentTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import androidx.appfunctions.metadata.AppFunctionStringTypeMetadata;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u0000 ?2\u00020\u0001:\u0003@A?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\u00020\f*\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0010\u001a\u00020\f*\u00020\u000f2\u0006\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u000f2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0019J\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u001a2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u001bJ\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u001c2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u001dJ\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u001e2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u001fJ\u0019\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020\u0006H ¢\u0006\u0004\b!\u0010\"J\u0017\u0010&\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0006H ¢\u0006\u0004\b$\u0010%J\u0015\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060'H ¢\u0006\u0004\b(\u0010)J\u0015\u0010+\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0006¢\u0006\u0004\b+\u0010%J\u001d\u0010\b\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010,J\u0015\u0010/\u001a\u00020\f2\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100J1\u00103\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00062\n\u00101\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u00102\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0004\b3\u00104J5\u00105\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00062\n\u00101\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u00102\u001a\u00020\u00142\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b5\u00104J%\u0010\u0015\u001a\u00020\u0014*\u00020\u00042\n\u00106\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u00102\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u00107R\u0014\u0010:\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010>\u001a\u00020;8&X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=¨\u0006B"}, d2 = {"Landroidx/appfunctions/AppFunctionDataSpec;", "", "<init>", "()V", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "type", "", "qualifiedName", "getPropertyObjectSpec", "(Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;Ljava/lang/String;)Landroidx/appfunctions/AppFunctionDataSpec;", "targetKey", "targetValue", "Lme/x;", "requireConstraintsConformance", "(Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;Ljava/lang/String;Ljava/lang/Object;)V", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "requireItemTypeConstraintsConformance", "(Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/Class;", "itemTypeClass", "", "conform", "(Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;Ljava/lang/Class;)Z", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "typeClass", "(Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;Ljava/lang/Class;)Z", "Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;", "(Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;Ljava/lang/Class;)Z", "Landroidx/appfunctions/metadata/AppFunctionOneOfTypeMetadata;", "(Landroidx/appfunctions/metadata/AppFunctionOneOfTypeMetadata;Ljava/lang/Class;)Z", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "(Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;Ljava/lang/Class;)Z", "key", "getDataType$appfunctions", "(Ljava/lang/String;)Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType", "isRequired$appfunctions", "(Ljava/lang/String;)Z", "isRequired", "", "getAllPropertyKeys$appfunctions", "()Ljava/util/Set;", "getAllPropertyKeys", "containsMetadata", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/appfunctions/AppFunctionDataSpec;", "Landroidx/appfunctions/AppFunctionData;", "data", "validateDataSpecMatches", "(Landroidx/appfunctions/AppFunctionData;)V", "targetClass", "isCollection", "validateWriteRequest", "(Ljava/lang/String;Ljava/lang/Class;ZLjava/lang/Object;)V", "validateReadRequest", "typeClazz", "(Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;Ljava/lang/Class;Z)Z", "getObjectQualifiedName", "()Ljava/lang/String;", "objectQualifiedName", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentMetadata", "Companion", "ObjectSpec", "ParametersSpec", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionDataSpec {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\u0004\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t¨\u0006\u000f"}, d2 = {"Landroidx/appfunctions/AppFunctionDataSpec$Companion;", "", "<init>", "()V", "create", "Landroidx/appfunctions/AppFunctionDataSpec;", "objectType", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "componentMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "parameterMetadataList", "", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "responseMetadata", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AppFunctionDataSpec create(AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            j.e(appFunctionObjectTypeMetadata, "objectType");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
            return new ObjectSpec(appFunctionObjectTypeMetadata, appFunctionComponentsMetadata);
        }

        private Companion() {
        }

        public final AppFunctionDataSpec create(List<AppFunctionParameterMetadata> list, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            j.e(list, "parameterMetadataList");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
            return new ParametersSpec(list, appFunctionComponentsMetadata);
        }

        public final AppFunctionDataSpec create(AppFunctionResponseMetadata appFunctionResponseMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            j.e(appFunctionResponseMetadata, "responseMetadata");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
            return new ObjectSpec(new AppFunctionObjectTypeMetadata(z.a0(new i("androidAppfunctionsReturnValue", appFunctionResponseMetadata.getValueType())), C0246a.e0("androidAppfunctionsReturnValue"), (String) null, false, (String) null, 16, (e) null), appFunctionComponentsMetadata);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010H\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019H\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001e¨\u0006%"}, d2 = {"Landroidx/appfunctions/AppFunctionDataSpec$ObjectSpec;", "Landroidx/appfunctions/AppFunctionDataSpec;", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "objectTypeMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentMetadata", "<init>", "(Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "key", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType$appfunctions", "(Ljava/lang/String;)Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType", "isRequired$appfunctions", "(Ljava/lang/String;)Z", "isRequired", "", "getAllPropertyKeys$appfunctions", "()Ljava/util/Set;", "getAllPropertyKeys", "toString", "()Ljava/lang/String;", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getObjectQualifiedName", "objectQualifiedName", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObjectSpec extends AppFunctionDataSpec {
        private final AppFunctionComponentsMetadata componentMetadata;
        private final AppFunctionObjectTypeMetadata objectTypeMetadata;

        public ObjectSpec(AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            j.e(appFunctionObjectTypeMetadata, "objectTypeMetadata");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
            this.objectTypeMetadata = appFunctionObjectTypeMetadata;
            this.componentMetadata = appFunctionComponentsMetadata;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ObjectSpec)) {
                return false;
            }
            return j.a(this.objectTypeMetadata, ((ObjectSpec) obj).objectTypeMetadata);
        }

        public Set<String> getAllPropertyKeys$appfunctions() {
            return this.objectTypeMetadata.getProperties().keySet();
        }

        public AppFunctionComponentsMetadata getComponentMetadata() {
            return this.componentMetadata;
        }

        public AppFunctionDataTypeMetadata getDataType$appfunctions(String str) {
            j.e(str, "key");
            return this.objectTypeMetadata.getProperties().get(str);
        }

        public String getObjectQualifiedName() {
            String qualifiedName = this.objectTypeMetadata.getQualifiedName();
            if (qualifiedName == null) {
                return "";
            }
            return qualifiedName;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.objectTypeMetadata});
        }

        public boolean isRequired$appfunctions(String str) {
            boolean z;
            j.e(str, "key");
            boolean contains = this.objectTypeMetadata.getRequired().contains(str);
            AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = this.objectTypeMetadata.getProperties().get(str);
            if (appFunctionDataTypeMetadata != null) {
                z = appFunctionDataTypeMetadata.isNullable();
            } else {
                z = true;
            }
            if (!contains || z) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "ObjectSpec(objectTypeMetadata=" + this.objectTypeMetadata + ", componentMetadata=" + this.componentMetadata + ')';
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0010¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010 R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001f¨\u0006&"}, d2 = {"Landroidx/appfunctions/AppFunctionDataSpec$ParametersSpec;", "Landroidx/appfunctions/AppFunctionDataSpec;", "", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "parameterMetadataList", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentMetadata", "<init>", "(Ljava/util/List;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "key", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType$appfunctions", "(Ljava/lang/String;)Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "getDataType", "isRequired$appfunctions", "(Ljava/lang/String;)Z", "isRequired", "", "getAllPropertyKeys$appfunctions", "()Ljava/util/Set;", "getAllPropertyKeys", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getObjectQualifiedName", "objectQualifiedName", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ParametersSpec extends AppFunctionDataSpec {
        private final AppFunctionComponentsMetadata componentMetadata;
        private final List<AppFunctionParameterMetadata> parameterMetadataList;

        public ParametersSpec(List<AppFunctionParameterMetadata> list, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            j.e(list, "parameterMetadataList");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
            this.parameterMetadataList = list;
            this.componentMetadata = appFunctionComponentsMetadata;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ParametersSpec)) {
                return false;
            }
            return j.a(this.parameterMetadataList, ((ParametersSpec) obj).parameterMetadataList);
        }

        public Set<String> getAllPropertyKeys$appfunctions() {
            Iterable<AppFunctionParameterMetadata> iterable = this.parameterMetadataList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionParameterMetadata name : iterable) {
                arrayList.add(name.getName());
            }
            return C1194l.p1(arrayList);
        }

        public AppFunctionComponentsMetadata getComponentMetadata() {
            return this.componentMetadata;
        }

        public AppFunctionDataTypeMetadata getDataType$appfunctions(String str) {
            Object obj;
            j.e(str, "key");
            Iterator it = this.parameterMetadataList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (j.a(((AppFunctionParameterMetadata) obj).getName(), str)) {
                    break;
                }
            }
            AppFunctionParameterMetadata appFunctionParameterMetadata = (AppFunctionParameterMetadata) obj;
            if (appFunctionParameterMetadata != null) {
                return appFunctionParameterMetadata.getDataType();
            }
            return null;
        }

        public String getObjectQualifiedName() {
            return "";
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.parameterMetadataList});
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: androidx.appfunctions.metadata.AppFunctionParameterMetadata} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: androidx.appfunctions.metadata.AppFunctionParameterMetadata} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: androidx.appfunctions.metadata.AppFunctionParameterMetadata} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.appfunctions.metadata.AppFunctionParameterMetadata} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isRequired$appfunctions(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.String r0 = "key"
                kotlin.jvm.internal.j.e(r6, r0)
                java.util.List<androidx.appfunctions.metadata.AppFunctionParameterMetadata> r0 = r5.parameterMetadataList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.Iterator r0 = r0.iterator()
            L_0x000d:
                boolean r1 = r0.hasNext()
                r2 = 0
                if (r1 == 0) goto L_0x0026
                java.lang.Object r1 = r0.next()
                r3 = r1
                androidx.appfunctions.metadata.AppFunctionParameterMetadata r3 = (androidx.appfunctions.metadata.AppFunctionParameterMetadata) r3
                java.lang.String r3 = r3.getName()
                boolean r3 = kotlin.jvm.internal.j.a(r3, r6)
                if (r3 == 0) goto L_0x000d
                goto L_0x0027
            L_0x0026:
                r1 = r2
            L_0x0027:
                androidx.appfunctions.metadata.AppFunctionParameterMetadata r1 = (androidx.appfunctions.metadata.AppFunctionParameterMetadata) r1
                r0 = 0
                if (r1 == 0) goto L_0x0031
                boolean r1 = r1.isRequired()
                goto L_0x0032
            L_0x0031:
                r1 = r0
            L_0x0032:
                java.util.List<androidx.appfunctions.metadata.AppFunctionParameterMetadata> r5 = r5.parameterMetadataList
                java.lang.Iterable r5 = (java.lang.Iterable) r5
                java.util.Iterator r5 = r5.iterator()
            L_0x003a:
                boolean r3 = r5.hasNext()
                if (r3 == 0) goto L_0x0052
                java.lang.Object r3 = r5.next()
                r4 = r3
                androidx.appfunctions.metadata.AppFunctionParameterMetadata r4 = (androidx.appfunctions.metadata.AppFunctionParameterMetadata) r4
                java.lang.String r4 = r4.getName()
                boolean r4 = kotlin.jvm.internal.j.a(r4, r6)
                if (r4 == 0) goto L_0x003a
                r2 = r3
            L_0x0052:
                androidx.appfunctions.metadata.AppFunctionParameterMetadata r2 = (androidx.appfunctions.metadata.AppFunctionParameterMetadata) r2
                r5 = 1
                if (r2 == 0) goto L_0x0062
                androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r6 = r2.getDataType()
                if (r6 == 0) goto L_0x0062
                boolean r6 = r6.isNullable()
                goto L_0x0063
            L_0x0062:
                r6 = r5
            L_0x0063:
                if (r1 == 0) goto L_0x0068
                if (r6 != 0) goto L_0x0068
                return r5
            L_0x0068:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.AppFunctionDataSpec.ParametersSpec.isRequired$appfunctions(java.lang.String):boolean");
        }

        public String toString() {
            return "ParametersSpec(parameterMetadataList=" + this.parameterMetadataList + ", componentMetadata=" + this.componentMetadata + ')';
        }
    }

    private final void requireConstraintsConformance(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, String str, Object obj) {
        if (obj == null && isRequired$appfunctions(str)) {
            throw new IllegalArgumentException(C0212a.m("\"", str, "\" cannot be set to a null value.").toString());
        } else if (obj != null) {
            if (appFunctionDataTypeMetadata instanceof AppFunctionIntTypeMetadata) {
                AppFunctionIntTypeMetadata appFunctionIntTypeMetadata = (AppFunctionIntTypeMetadata) appFunctionDataTypeMetadata;
                if (appFunctionIntTypeMetadata.getEnumValues() != null && !C1194l.G0(appFunctionIntTypeMetadata.getEnumValues(), obj)) {
                    throw new IllegalArgumentException(("Invalid value for \"" + str + "\" got \"" + obj + "\", expecting one of " + appFunctionIntTypeMetadata.getEnumValues()).toString());
                }
            } else if (appFunctionDataTypeMetadata instanceof AppFunctionStringTypeMetadata) {
                AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = (AppFunctionStringTypeMetadata) appFunctionDataTypeMetadata;
                if (appFunctionStringTypeMetadata.getEnumValues() != null && !C1194l.G0(appFunctionStringTypeMetadata.getEnumValues(), obj)) {
                    throw new IllegalArgumentException(("Invalid value for \"" + str + "\" got \"" + obj + "\", expecting one of " + appFunctionStringTypeMetadata.getEnumValues()).toString());
                }
            } else if (appFunctionDataTypeMetadata instanceof AppFunctionArrayTypeMetadata) {
                requireItemTypeConstraintsConformance((AppFunctionArrayTypeMetadata) appFunctionDataTypeMetadata, str, obj);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: ne.t} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void requireItemTypeConstraintsConformance(androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r0 = r5.getItemType()
            boolean r1 = r0 instanceof androidx.appfunctions.metadata.AppFunctionIntTypeMetadata
            r2 = 0
            if (r1 == 0) goto L_0x0028
            boolean r0 = r7 instanceof int[]
            if (r0 == 0) goto L_0x0010
            r2 = r7
            int[] r2 = (int[]) r2
        L_0x0010:
            r7 = 0
            if (r2 != 0) goto L_0x0015
            int[] r2 = new int[r7]
        L_0x0015:
            int r0 = r2.length
        L_0x0016:
            if (r7 >= r0) goto L_0x004f
            r1 = r2[r7]
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r3 = r5.getItemType()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.requireConstraintsConformance(r3, r6, r1)
            int r7 = r7 + 1
            goto L_0x0016
        L_0x0028:
            boolean r0 = r0 instanceof androidx.appfunctions.metadata.AppFunctionStringTypeMetadata
            if (r0 == 0) goto L_0x004f
            boolean r0 = r7 instanceof java.util.List
            if (r0 == 0) goto L_0x0033
            r2 = r7
            java.util.List r2 = (java.util.List) r2
        L_0x0033:
            if (r2 != 0) goto L_0x0037
            ne.t r2 = ne.C1202t.d
        L_0x0037:
            java.util.Iterator r7 = r2.iterator()
        L_0x003b:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x004f
            java.lang.Object r0 = r7.next()
            java.lang.String r0 = (java.lang.String) r0
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r1 = r5.getItemType()
            r4.requireConstraintsConformance(r1, r6, r0)
            goto L_0x003b
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.AppFunctionDataSpec.requireItemTypeConstraintsConformance(androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata, java.lang.String, java.lang.Object):void");
    }

    public final boolean conform(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, Class<?> cls, boolean z) {
        j.e(appFunctionDataTypeMetadata, "<this>");
        j.e(cls, "typeClazz");
        if (appFunctionDataTypeMetadata instanceof AppFunctionIntTypeMetadata) {
            return !z && cls.equals(Integer.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionLongTypeMetadata) {
            return !z && cls.equals(Long.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionFloatTypeMetadata) {
            return !z && cls.equals(Float.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionDoubleTypeMetadata) {
            return !z && cls.equals(Double.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionBooleanTypeMetadata) {
            return !z && cls.equals(Boolean.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionStringTypeMetadata) {
            return !z && cls.equals(String.class);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionBytesTypeMetadata) {
            return z && cls.equals(Byte.TYPE);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionPendingIntentTypeMetadata) {
            return !z && cls.equals(PendingIntent.class);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionArrayTypeMetadata) {
            return z && conform((AppFunctionArrayTypeMetadata) appFunctionDataTypeMetadata, cls);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
            return !z && conform((AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata, cls);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionAllOfTypeMetadata) {
            return !z && conform((AppFunctionAllOfTypeMetadata) appFunctionDataTypeMetadata, cls);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionReferenceTypeMetadata) {
            return !z && conform((AppFunctionReferenceTypeMetadata) appFunctionDataTypeMetadata, cls);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionOneOfTypeMetadata) {
            return !z && conform((AppFunctionOneOfTypeMetadata) appFunctionDataTypeMetadata, cls);
        }
        throw new IllegalStateException("Unexpected data type " + appFunctionDataTypeMetadata.getClass());
    }

    public final boolean containsMetadata(String str) {
        j.e(str, "key");
        if (getDataType$appfunctions(str) != null) {
            return true;
        }
        return false;
    }

    public abstract Set<String> getAllPropertyKeys$appfunctions();

    public abstract AppFunctionComponentsMetadata getComponentMetadata();

    public abstract AppFunctionDataTypeMetadata getDataType$appfunctions(String str);

    public abstract String getObjectQualifiedName();

    public final AppFunctionDataSpec getPropertyObjectSpec(String str, String str2) {
        j.e(str, "key");
        j.e(str2, "qualifiedName");
        AppFunctionDataTypeMetadata dataType$appfunctions = getDataType$appfunctions(str);
        if (dataType$appfunctions != null) {
            return getPropertyObjectSpec(dataType$appfunctions, str2);
        }
        throw new IllegalArgumentException(C0212a.m("Value associated with ", str, " is not an object"));
    }

    public abstract boolean isRequired$appfunctions(String str);

    public final void validateDataSpecMatches(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "data");
        appFunctionData.getSpec$appfunctions();
    }

    public final void validateReadRequest(String str, Class<?> cls, boolean z, Object obj) {
        String str2;
        j.e(str, "targetKey");
        j.e(cls, "targetClass");
        AppFunctionDataTypeMetadata dataType$appfunctions = getDataType$appfunctions(str);
        if (dataType$appfunctions == null) {
            throw new IllegalArgumentException("No value should be set at ".concat(str));
        } else if (!conform(dataType$appfunctions, cls, z)) {
            if (z) {
                str2 = "Unexpected read for " + str + ": expecting collection of " + cls + ", the actual value should be " + dataType$appfunctions;
            } else {
                str2 = "Unexpected read for " + str + ": expecting " + cls + ", the actual value should be " + dataType$appfunctions;
            }
            throw new IllegalArgumentException(str2.toString());
        } else {
            requireConstraintsConformance(dataType$appfunctions, str, obj);
        }
    }

    public final void validateWriteRequest(String str, Class<?> cls, boolean z, Object obj) {
        String str2;
        j.e(str, "targetKey");
        j.e(cls, "targetClass");
        j.e(obj, "targetValue");
        AppFunctionDataTypeMetadata dataType$appfunctions = getDataType$appfunctions(str);
        if (dataType$appfunctions == null) {
            throw new IllegalArgumentException("No value should be set at ".concat(str));
        } else if (!conform(dataType$appfunctions, cls, z)) {
            if (z) {
                str2 = "Invalid value for " + str + ": got collection of " + cls + ", expecting a value matching " + dataType$appfunctions;
            } else {
                str2 = "Invalid value for " + str + ": got " + cls + ", expecting a value matching " + dataType$appfunctions;
            }
            throw new IllegalArgumentException(str2.toString());
        } else {
            requireConstraintsConformance(dataType$appfunctions, str, obj);
        }
    }

    private final AppFunctionDataSpec getPropertyObjectSpec(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, String str) {
        if (appFunctionDataTypeMetadata instanceof AppFunctionArrayTypeMetadata) {
            return getPropertyObjectSpec(((AppFunctionArrayTypeMetadata) appFunctionDataTypeMetadata).getItemType(), str);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
            return new ObjectSpec((AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata, getComponentMetadata());
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionReferenceTypeMetadata) {
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = (AppFunctionReferenceTypeMetadata) appFunctionDataTypeMetadata;
            AppFunctionDataTypeMetadata appFunctionDataTypeMetadata2 = getComponentMetadata().getDataTypes().get(appFunctionReferenceTypeMetadata.getReferenceDataType());
            if (appFunctionDataTypeMetadata2 != null) {
                return getPropertyObjectSpec(appFunctionDataTypeMetadata2, str);
            }
            throw new IllegalStateException("Unable to resolve data type for " + appFunctionReferenceTypeMetadata.getReferenceDataType());
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionAllOfTypeMetadata) {
            return new ObjectSpec(((AppFunctionAllOfTypeMetadata) appFunctionDataTypeMetadata).getPseudoObjectTypeMetadata$appfunctions(getComponentMetadata()), getComponentMetadata());
        } else {
            if (appFunctionDataTypeMetadata instanceof AppFunctionOneOfTypeMetadata) {
                return getPropertyObjectSpec(((AppFunctionOneOfTypeMetadata) appFunctionDataTypeMetadata).getObjectMetadataForOneOfType$appfunctions(str), str);
            }
            throw new IllegalStateException("Unexpected data type " + appFunctionDataTypeMetadata);
        }
    }

    private final boolean conform(AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata, Class<?> cls) {
        return conform(appFunctionArrayTypeMetadata.getItemType(), cls, false);
    }

    private final boolean conform(AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata, Class<?> cls) {
        return j.a(cls, AppFunctionData.class);
    }

    private final boolean conform(AppFunctionAllOfTypeMetadata appFunctionAllOfTypeMetadata, Class<?> cls) {
        return j.a(cls, AppFunctionData.class);
    }

    private final boolean conform(AppFunctionOneOfTypeMetadata appFunctionOneOfTypeMetadata, Class<?> cls) {
        return j.a(cls, AppFunctionData.class);
    }

    private final boolean conform(AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata, Class<?> cls) {
        return j.a(cls, AppFunctionData.class);
    }
}
