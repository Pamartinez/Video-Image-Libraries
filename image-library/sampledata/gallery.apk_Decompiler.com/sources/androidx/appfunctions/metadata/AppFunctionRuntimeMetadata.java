package androidx.appfunctions.metadata;

import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\rR\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0018\u0010\rR\u001a\u0010\u0006\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0019\u0010\rR\u001a\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\b\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\t\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u001d\u0010\r¨\u0006\u001f"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionRuntimeMetadata;", "", "", "id", "namespace", "functionId", "packageName", "", "enabled", "appFunctionStaticMetadataQualifiedId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getNamespace", "getFunctionId", "getPackageName", "J", "getEnabled", "()J", "getAppFunctionStaticMetadataQualifiedId", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionRuntimeMetadata {
    public static final Companion Companion = new Companion((e) null);
    private final String appFunctionStaticMetadataQualifiedId;
    private final long enabled;
    private final String functionId;
    private final String id;
    private final String namespace;
    private final String packageName;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionRuntimeMetadata$Companion;", "", "<init>", "()V", "STATIC_METADATA_JOIN_PROPERTY", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public AppFunctionRuntimeMetadata(String str, String str2, String str3, String str4, long j2, String str5) {
        j.e(str, "id");
        j.e(str2, "namespace");
        j.e(str3, "functionId");
        j.e(str4, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        j.e(str5, "appFunctionStaticMetadataQualifiedId");
        this.id = str;
        this.namespace = str2;
        this.functionId = str3;
        this.packageName = str4;
        this.enabled = j2;
        this.appFunctionStaticMetadataQualifiedId = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionRuntimeMetadata)) {
            return false;
        }
        AppFunctionRuntimeMetadata appFunctionRuntimeMetadata = (AppFunctionRuntimeMetadata) obj;
        if (j.a(this.id, appFunctionRuntimeMetadata.id) && j.a(this.namespace, appFunctionRuntimeMetadata.namespace) && j.a(this.functionId, appFunctionRuntimeMetadata.functionId) && j.a(this.packageName, appFunctionRuntimeMetadata.packageName) && this.enabled == appFunctionRuntimeMetadata.enabled && j.a(this.appFunctionStaticMetadataQualifiedId, appFunctionRuntimeMetadata.appFunctionStaticMetadataQualifiedId)) {
            return true;
        }
        return false;
    }

    public final long getEnabled() {
        return this.enabled;
    }

    public int hashCode() {
        return this.appFunctionStaticMetadataQualifiedId.hashCode() + C0212a.c(C0212a.d(C0212a.d(C0212a.d(this.id.hashCode() * 31, 31, this.namespace), 31, this.functionId), 31, this.packageName), 31, this.enabled);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionRuntimeMetadata(id=");
        sb2.append(this.id);
        sb2.append(", namespace=");
        sb2.append(this.namespace);
        sb2.append(", functionId=");
        sb2.append(this.functionId);
        sb2.append(", packageName=");
        sb2.append(this.packageName);
        sb2.append(", enabled=");
        sb2.append(this.enabled);
        sb2.append(", appFunctionStaticMetadataQualifiedId=");
        return C0086a.m(sb2, this.appFunctionStaticMetadataQualifiedId, ')');
    }
}
