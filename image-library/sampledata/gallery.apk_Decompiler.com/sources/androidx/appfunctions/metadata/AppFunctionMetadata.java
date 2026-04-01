package androidx.appfunctions.metadata;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001Ba\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001f\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b\u0006\u0010!R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b.\u0010\u001cR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010/\u001a\u0004\b0\u00101¨\u00062"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionMetadata;", "", "", "id", "packageName", "", "isEnabled", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "schema", "", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "parameters", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "response", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "components", "description", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "deprecation", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/appfunctions/metadata/AppFunctionSchemaMetadata;Ljava/util/List;Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;Ljava/lang/String;Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;)V", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getId", "getPackageName", "Z", "()Z", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "getSchema", "()Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "Ljava/util/List;", "getParameters", "()Ljava/util/List;", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "getResponse", "()Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponents", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getDescription", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "getDeprecation", "()Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionMetadata {
    private final AppFunctionComponentsMetadata components;
    private final AppFunctionDeprecationMetadata deprecation;
    private final String description;
    private final String id;
    private final boolean isEnabled;
    private final String packageName;
    private final List<AppFunctionParameterMetadata> parameters;
    private final AppFunctionResponseMetadata response;
    private final AppFunctionSchemaMetadata schema;

    public AppFunctionMetadata(String str, String str2, boolean z, AppFunctionSchemaMetadata appFunctionSchemaMetadata, List<AppFunctionParameterMetadata> list, AppFunctionResponseMetadata appFunctionResponseMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata, String str3, AppFunctionDeprecationMetadata appFunctionDeprecationMetadata) {
        j.e(str, "id");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        j.e(list, "parameters");
        j.e(appFunctionResponseMetadata, Contract.RESPONSE);
        j.e(appFunctionComponentsMetadata, "components");
        j.e(str3, "description");
        this.id = str;
        this.packageName = str2;
        this.isEnabled = z;
        this.schema = appFunctionSchemaMetadata;
        this.parameters = list;
        this.response = appFunctionResponseMetadata;
        this.components = appFunctionComponentsMetadata;
        this.description = str3;
        this.deprecation = appFunctionDeprecationMetadata;
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
        if (!AppFunctionMetadata.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type androidx.appfunctions.metadata.AppFunctionMetadata");
        AppFunctionMetadata appFunctionMetadata = (AppFunctionMetadata) obj;
        if (j.a(this.id, appFunctionMetadata.id) && this.isEnabled == appFunctionMetadata.isEnabled && j.a(this.packageName, appFunctionMetadata.packageName) && j.a(this.schema, appFunctionMetadata.schema) && j.a(this.parameters, appFunctionMetadata.parameters) && j.a(this.response, appFunctionMetadata.response) && j.a(this.components, appFunctionMetadata.components) && j.a(this.description, appFunctionMetadata.description) && j.a(this.deprecation, appFunctionMetadata.deprecation)) {
            return true;
        }
        return false;
    }

    public final AppFunctionComponentsMetadata getComponents() {
        return this.components;
    }

    public final List<AppFunctionParameterMetadata> getParameters() {
        return this.parameters;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.isEnabled), this.id, this.packageName, this.schema, this.parameters, this.response, this.components, this.description, this.deprecation});
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionMetadata(");
        sb2.append("id='" + this.id + "', ");
        sb2.append("packageName='" + this.packageName + "', ");
        sb2.append("isEnabled=" + this.isEnabled + ArcCommonLog.TAG_COMMA);
        sb2.append("schema=" + this.schema + ArcCommonLog.TAG_COMMA);
        sb2.append("parameters=" + this.parameters + ArcCommonLog.TAG_COMMA);
        sb2.append("response=" + this.response + ArcCommonLog.TAG_COMMA);
        StringBuilder sb3 = new StringBuilder("components=");
        sb3.append(this.components);
        sb2.append(sb3.toString());
        sb2.append("description=" + this.description);
        sb2.append("deprecation=" + this.deprecation);
        sb2.append(")");
        String sb4 = sb2.toString();
        j.d(sb4, "toString(...)");
        return sb4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ AppFunctionMetadata(java.lang.String r15, java.lang.String r16, boolean r17, androidx.appfunctions.metadata.AppFunctionSchemaMetadata r18, java.util.List r19, androidx.appfunctions.metadata.AppFunctionResponseMetadata r20, androidx.appfunctions.metadata.AppFunctionComponentsMetadata r21, java.lang.String r22, androidx.appfunctions.metadata.AppFunctionDeprecationMetadata r23, int r24, kotlin.jvm.internal.e r25) {
        /*
            r14 = this;
            r0 = r24
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x000f
            androidx.appfunctions.metadata.AppFunctionComponentsMetadata r1 = new androidx.appfunctions.metadata.AppFunctionComponentsMetadata
            r3 = 1
            r1.<init>(r2, r3, r2)
            r11 = r1
            goto L_0x0011
        L_0x000f:
            r11 = r21
        L_0x0011:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0019
            java.lang.String r1 = ""
            r12 = r1
            goto L_0x001b
        L_0x0019:
            r12 = r22
        L_0x001b:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x002d
            r13 = r2
        L_0x0020:
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            goto L_0x0030
        L_0x002d:
            r13 = r23
            goto L_0x0020
        L_0x0030:
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.metadata.AppFunctionMetadata.<init>(java.lang.String, java.lang.String, boolean, androidx.appfunctions.metadata.AppFunctionSchemaMetadata, java.util.List, androidx.appfunctions.metadata.AppFunctionResponseMetadata, androidx.appfunctions.metadata.AppFunctionComponentsMetadata, java.lang.String, androidx.appfunctions.metadata.AppFunctionDeprecationMetadata, int, kotlin.jvm.internal.e):void");
    }
}
