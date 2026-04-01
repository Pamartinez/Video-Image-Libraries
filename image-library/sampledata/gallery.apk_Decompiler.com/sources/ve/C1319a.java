package ve;

/* renamed from: ve.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1319a {

    /* renamed from: a  reason: collision with root package name */
    public static final Integer f5156a;

    static {
        Integer num;
        Integer num2 = null;
        try {
            Object obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
            if (obj instanceof Integer) {
                num = (Integer) obj;
                if (num != null && num.intValue() > 0) {
                    num2 = num;
                }
                f5156a = num2;
            }
        } catch (Throwable unused) {
        }
        num = null;
        num2 = num;
        f5156a = num2;
    }
}
