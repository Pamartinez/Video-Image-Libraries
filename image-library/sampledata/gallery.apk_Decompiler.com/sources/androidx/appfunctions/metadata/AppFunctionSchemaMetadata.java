package androidx.appfunctions.metadata;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "", "", "category", "name", "", "version", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getCategory", "getName", "J", "getVersion", "()J", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionSchemaMetadata {
    private final String category;
    private final String name;
    private final long version;

    public AppFunctionSchemaMetadata(String str, String str2, long j2) {
        j.e(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(str2, "name");
        this.category = str;
        this.name = str2;
        this.version = j2;
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
        if (!AppFunctionSchemaMetadata.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type androidx.appfunctions.metadata.AppFunctionSchemaMetadata");
        AppFunctionSchemaMetadata appFunctionSchemaMetadata = (AppFunctionSchemaMetadata) obj;
        if (this.version == appFunctionSchemaMetadata.version && j.a(this.category, appFunctionSchemaMetadata.category) && j.a(this.name, appFunctionSchemaMetadata.name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode() + C0212a.d(Long.hashCode(this.version) * 31, 31, this.category);
    }

    public String toString() {
        return "AppFunctionSchemaMetadata(category='" + this.category + "', name='" + this.name + "', version=" + this.version + ')';
    }
}
