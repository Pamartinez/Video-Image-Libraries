package Sd;

import android.content.Context;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C0212a {

    /* renamed from: a  reason: collision with root package name */
    public final String f3700a;
    public final String b;

    public f(String str, String str2) {
        this.f3700a = str;
        this.b = str2;
    }

    public static f C(Context context) {
        if (o.f3713c == null) {
            String packageName = context.getPackageName();
            try {
                o.f3713c = new f(packageName, context.getPackageManager().getPackageInfo(packageName, 0).versionName);
            } catch (Throwable unused) {
                o.f3713c = new f(packageName, "NONE");
            }
        }
        return o.f3713c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!Objects.equals(this.f3700a, fVar.f3700a) || !Objects.equals(this.b, fVar.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.b) + (Objects.hashCode(this.f3700a) * 31);
    }

    public final String toString() {
        String[] strArr;
        Object[] objArr = {this.f3700a, this.b};
        if ("a;b".length() == 0) {
            strArr = new String[0];
        } else {
            strArr = "a;b".split(";");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(f.class.getSimpleName());
        sb2.append("[");
        for (int i2 = 0; i2 < strArr.length; i2++) {
            sb2.append(strArr[i2]);
            sb2.append("=");
            sb2.append(objArr[i2]);
            if (i2 != strArr.length - 1) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append("]");
        return sb2.toString();
    }
}
