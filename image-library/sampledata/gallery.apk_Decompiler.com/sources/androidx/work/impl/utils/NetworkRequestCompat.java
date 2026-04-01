package androidx.work.impl.utils;

import android.net.NetworkRequest;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00128G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/utils/NetworkRequestCompat;", "", "wrapped", "<init>", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getWrapped", "()Ljava/lang/Object;", "Landroid/net/NetworkRequest;", "getNetworkRequest", "()Landroid/net/NetworkRequest;", "networkRequest", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkRequestCompat {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG;
    private final Object wrapped;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/utils/NetworkRequestCompat$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final String getTAG() {
            return NetworkRequestCompat.TAG;
        }

        private Companion() {
        }
    }

    static {
        String tagWithPrefix = Logger.tagWithPrefix("NetworkRequestCompat");
        j.d(tagWithPrefix, "tagWithPrefix(\"NetworkRequestCompat\")");
        TAG = tagWithPrefix;
    }

    public NetworkRequestCompat(Object obj) {
        this.wrapped = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof NetworkRequestCompat) && j.a(this.wrapped, ((NetworkRequestCompat) obj).wrapped)) {
            return true;
        }
        return false;
    }

    public final NetworkRequest getNetworkRequest() {
        return (NetworkRequest) this.wrapped;
    }

    public int hashCode() {
        Object obj = this.wrapped;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "NetworkRequestCompat(wrapped=" + this.wrapped + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NetworkRequestCompat(Object obj, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : obj);
    }
}
