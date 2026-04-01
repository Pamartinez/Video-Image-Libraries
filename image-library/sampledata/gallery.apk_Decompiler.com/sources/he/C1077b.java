package he;

import He.F;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import ee.N;

/* renamed from: he.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1077b implements N {
    public static final ThreadLocal d = new ThreadLocal();

    /* renamed from: a  reason: collision with root package name */
    public final Parser f4580a;
    public final MessageLite b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4581c = -1;

    public C1077b(MessageLite messageLite) {
        F.n(messageLite, "defaultInstance cannot be null");
        this.b = messageLite;
        this.f4580a = messageLite.getParserForType();
    }
}
