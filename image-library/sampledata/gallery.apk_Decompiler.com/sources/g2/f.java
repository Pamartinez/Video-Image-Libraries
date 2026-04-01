package G2;

import com.samsung.scsp.framework.core.util.HashUtil;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C0246a implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public final MessageDigest f292h;

    /* renamed from: i  reason: collision with root package name */
    public final int f293i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f294j;
    public final String k;

    public f() {
        boolean z;
        try {
            MessageDigest instance = MessageDigest.getInstance(HashUtil.SHA256);
            this.f292h = instance;
            this.f293i = instance.getDigestLength();
            this.k = "Hashing.sha256()";
            try {
                instance.clone();
                z = true;
            } catch (CloneNotSupportedException unused) {
                z = false;
            }
            this.f294j = z;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final String toString() {
        return this.k;
    }
}
