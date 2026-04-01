package X2;

import D0.e;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends u {
    public static final Pattern f = Pattern.compile(GlobalPostProcInternalPPInterface.SPLIT_REGEX);

    public final r e(e eVar) {
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String str;
        String str2;
        String[] strArr4;
        String str3;
        String a7 = u.a(eVar);
        String[] strArr5 = null;
        if (a7.startsWith("mailto:") || a7.startsWith("MAILTO:")) {
            String substring = a7.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            try {
                String decode = URLDecoder.decode(substring, "UTF-8");
                boolean isEmpty = decode.isEmpty();
                Pattern pattern = f;
                if (!isEmpty) {
                    strArr = pattern.split(decode);
                } else {
                    strArr = null;
                }
                HashMap g = u.g(a7);
                if (g != null) {
                    if (strArr == null && (str3 = (String) g.get("to")) != null) {
                        strArr = pattern.split(str3);
                    }
                    String str4 = (String) g.get("cc");
                    if (str4 != null) {
                        strArr4 = pattern.split(str4);
                    } else {
                        strArr4 = null;
                    }
                    String str5 = (String) g.get("bcc");
                    if (str5 != null) {
                        strArr5 = pattern.split(str5);
                    }
                    str2 = (String) g.get("body");
                    strArr2 = strArr4;
                    strArr3 = strArr5;
                    str = (String) g.get("subject");
                } else {
                    strArr2 = null;
                    strArr3 = null;
                    str = null;
                    str2 = null;
                }
                return new h(strArr, strArr2, strArr3, str, str2);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } else if (!j.f.matcher(a7).matches() || a7.indexOf(64) < 0) {
            return null;
        } else {
            return new h(new String[]{a7}, (String[]) null, (String[]) null, (String) null, (String) null);
        }
    }
}
