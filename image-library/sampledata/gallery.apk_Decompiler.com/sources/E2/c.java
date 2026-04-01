package E2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends b {
    public final char d;

    public c(char c5) {
        this.d = c5;
    }

    public final boolean a(char c5) {
        if (c5 == this.d) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("CharMatcher.is('");
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        char c5 = this.d;
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = "0123456789ABCDEF".charAt(c5 & 15);
            c5 = (char) (c5 >> 4);
        }
        sb2.append(String.copyValueOf(cArr));
        sb2.append("')");
        return sb2.toString();
    }
}
