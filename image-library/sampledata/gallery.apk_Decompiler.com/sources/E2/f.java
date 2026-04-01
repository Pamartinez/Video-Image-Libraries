package E2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends d {
    public static final int e = Integer.numberOfLeadingZeros(31);
    public static final f f = new d("CharMatcher.whitespace()");

    public final boolean a(char c5) {
        if (" 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".charAt((48906 * c5) >>> e) == c5) {
            return true;
        }
        return false;
    }
}
