package androidx.core.text;

import android.text.Html;
import android.text.Spanned;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HtmlCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static Spanned fromHtml(String str, int i2) {
            return Html.fromHtml(str, i2);
        }

        public static String toHtml(Spanned spanned, int i2) {
            return Html.toHtml(spanned, i2);
        }
    }

    public static Spanned fromHtml(String str, int i2) {
        return Api24Impl.fromHtml(str, i2);
    }

    public static String toHtml(Spanned spanned, int i2) {
        return Api24Impl.toHtml(spanned, i2);
    }
}
