package X2;

import D0.e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z extends u {
    public static final Pattern f = Pattern.compile("[-._~:/?#\\[\\]@!$&'()*+,;=%A-Za-z0-9]+");
    public static final Pattern g = Pattern.compile(":/*([^/@]+)@[^/]+");

    /* renamed from: h  reason: collision with root package name */
    public static final Pattern f946h = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    /* renamed from: i  reason: collision with root package name */
    public static final Pattern f947i = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    public static boolean h(String str) {
        if (str.contains(" ")) {
            return false;
        }
        Matcher matcher = f946h.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = f947i.matcher(str);
        if (!matcher2.find() || matcher2.start() != 0) {
            return false;
        }
        return true;
    }

    public final r e(e eVar) {
        String str;
        String a7 = u.a(eVar);
        if (a7.startsWith("URL:") || a7.startsWith("URI:")) {
            return new y(a7.substring(4).trim(), (String) null);
        }
        if (a7.startsWith("rcsbot:") || a7.startsWith("RCSBOT:")) {
            return new y(a7.trim(), (String) null);
        }
        if (a7.startsWith("FIDO:") || a7.startsWith("fido:")) {
            return null;
        }
        String trim = a7.trim();
        if (!h(trim) || g.matcher(trim).find()) {
            return null;
        }
        Pattern pattern = f;
        if (!pattern.matcher(trim).matches()) {
            try {
                str = URLEncoder.encode(trim, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                str = null;
            }
            if (str == null || !pattern.matcher(str).matches()) {
                return null;
            }
        }
        return new y(trim, (String) null);
    }
}
