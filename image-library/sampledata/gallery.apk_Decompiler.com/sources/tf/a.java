package Tf;

import java.nio.charset.Charset;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f3815a;

    static {
        Charset forName = Charset.forName("UTF-8");
        j.d(forName, "forName(...)");
        f3815a = forName;
        j.d(Charset.forName("UTF-16"), "forName(...)");
        j.d(Charset.forName("UTF-16BE"), "forName(...)");
        j.d(Charset.forName("UTF-16LE"), "forName(...)");
        j.d(Charset.forName("US-ASCII"), "forName(...)");
        j.d(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}
