package w1;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y {
    public static final Uri d = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();

    /* renamed from: a  reason: collision with root package name */
    public final String f2020a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f2021c;

    public y(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.f2020a = str;
            if (!TextUtils.isEmpty("com.google.android.gms")) {
                this.b = "com.google.android.gms";
                this.f2021c = z;
                return;
            }
            throw new IllegalArgumentException("Given String is empty or null");
        }
        throw new IllegalArgumentException("Given String is empty or null");
    }

    /* JADX WARNING: type inference failed for: r7v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent a(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ConnectionStatusConfig"
            r1 = 0
            java.lang.String r2 = r6.f2020a
            if (r2 == 0) goto L_0x005b
            boolean r3 = r6.f2021c
            if (r3 == 0) goto L_0x004c
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            java.lang.String r4 = "serviceActionBundleKey"
            r3.putString(r4, r2)
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x0022 }
            android.net.Uri r4 = d     // Catch:{ IllegalArgumentException -> 0x0022 }
            java.lang.String r5 = "serviceIntentCall"
            android.os.Bundle r7 = r7.call(r4, r5, r1, r3)     // Catch:{ IllegalArgumentException -> 0x0022 }
            goto L_0x0031
        L_0x0022:
            r7 = move-exception
            java.lang.String r3 = "Dynamic intent resolution failed: "
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r3.concat(r7)
            android.util.Log.w(r0, r7)
            r7 = r1
        L_0x0031:
            if (r7 != 0) goto L_0x0034
            goto L_0x003d
        L_0x0034:
            java.lang.String r1 = "serviceResponseIntentKey"
            android.os.Parcelable r7 = r7.getParcelable(r1)
            r1 = r7
            android.content.Intent r1 = (android.content.Intent) r1
        L_0x003d:
            if (r1 != 0) goto L_0x004c
            java.lang.String r7 = "Dynamic lookup for intent failed for action: "
            java.lang.String r3 = java.lang.String.valueOf(r2)
            java.lang.String r7 = r7.concat(r3)
            android.util.Log.w(r0, r7)
        L_0x004c:
            if (r1 != 0) goto L_0x005a
            android.content.Intent r7 = new android.content.Intent
            r7.<init>(r2)
            java.lang.String r6 = r6.b
            android.content.Intent r6 = r7.setPackage(r6)
            return r6
        L_0x005a:
            return r1
        L_0x005b:
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.content.Intent r6 = r6.setComponent(r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: w1.y.a(android.content.Context):android.content.Intent");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        if (!r.d(this.f2020a, yVar.f2020a) || !r.d(this.b, yVar.b) || !r.d((Object) null, (Object) null) || this.f2021c != yVar.f2021c) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2020a, this.b, null, 4225, Boolean.valueOf(this.f2021c)});
    }

    public final String toString() {
        String str = this.f2020a;
        if (str != null) {
            return str;
        }
        r.b((Object) null);
        throw null;
    }
}
