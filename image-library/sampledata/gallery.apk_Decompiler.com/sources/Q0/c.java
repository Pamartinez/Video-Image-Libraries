package Q0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final b f611a;
    public static final b b;

    static {
        b bVar = new b("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        f611a = bVar;
        b = new b(bVar);
        byte[] bArr = bVar.f;
        System.arraycopy(bArr, 0, new byte[64], 0, bArr.length);
        char[] cArr = bVar.e;
        System.arraycopy(cArr, 0, new char[64], 0, cArr.length);
        int[] iArr = bVar.d;
        System.arraycopy(iArr, 0, new int[128], 0, iArr.length);
        StringBuilder sb2 = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        sb2.setCharAt(sb2.indexOf("+"), '-');
        sb2.setCharAt(sb2.indexOf("/"), '_');
        new b("MODIFIED-FOR-URL", sb2.toString(), false, 0, Integer.MAX_VALUE);
    }
}
