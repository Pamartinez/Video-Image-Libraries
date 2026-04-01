package androidx.appfunctions;

import android.net.Uri;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\r¨\u0006\u0017"}, d2 = {"Landroidx/appfunctions/AppFunctionUriGrant;", "", "Landroid/net/Uri;", "uri", "", "modeFlags", "<init>", "(Landroid/net/Uri;I)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "I", "getModeFlags", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionUriGrant {
    private static final Companion Companion = new Companion((e) null);
    private final int modeFlags;
    private final Uri uri;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Landroidx/appfunctions/AppFunctionUriGrant$Companion;", "", "<init>", "()V", "isAccessUriMode", "", "modeFlags", "", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final boolean isAccessUriMode(int i2) {
            if ((i2 & 3) != 0) {
                return true;
            }
            return false;
        }

        private Companion() {
        }
    }

    public AppFunctionUriGrant(Uri uri2, int i2) {
        j.e(uri2, OCRServiceConstant.KEY_PARAM_URI);
        this.uri = uri2;
        this.modeFlags = i2;
        if (!Companion.isAccessUriMode(i2)) {
            throw new IllegalArgumentException("Must set either FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION to specify the access mode");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionUriGrant)) {
            return false;
        }
        AppFunctionUriGrant appFunctionUriGrant = (AppFunctionUriGrant) obj;
        if (!j.a(this.uri, appFunctionUriGrant.uri) || this.modeFlags != appFunctionUriGrant.modeFlags) {
            return false;
        }
        return true;
    }

    public final int getModeFlags() {
        return this.modeFlags;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.uri, Integer.valueOf(this.modeFlags)});
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionUriGrant(uri=");
        sb2.append(this.uri);
        sb2.append(", modeFlags=");
        return N2.j.e(sb2, this.modeFlags, ')');
    }
}
