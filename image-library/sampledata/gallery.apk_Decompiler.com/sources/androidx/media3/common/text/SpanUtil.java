package androidx.media3.common.text;

import android.text.Spannable;
import android.text.style.RelativeSizeSpan;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SpanUtil {
    public static void addInheritedRelativeSizeSpan(Spannable spannable, float f, int i2, int i7, int i8) {
        for (RelativeSizeSpan relativeSizeSpan : (RelativeSizeSpan[]) spannable.getSpans(i2, i7, RelativeSizeSpan.class)) {
            if (spannable.getSpanStart(relativeSizeSpan) <= i2 && spannable.getSpanEnd(relativeSizeSpan) >= i7) {
                f = relativeSizeSpan.getSizeChange() * f;
            }
            removeIfStartEndAndFlagsMatch(spannable, relativeSizeSpan, i2, i7, i8);
        }
        spannable.setSpan(new RelativeSizeSpan(f), i2, i7, i8);
    }

    public static void addOrReplaceSpan(Spannable spannable, Object obj, int i2, int i7, int i8) {
        for (Object removeIfStartEndAndFlagsMatch : spannable.getSpans(i2, i7, obj.getClass())) {
            removeIfStartEndAndFlagsMatch(spannable, removeIfStartEndAndFlagsMatch, i2, i7, i8);
        }
        spannable.setSpan(obj, i2, i7, i8);
    }

    private static void removeIfStartEndAndFlagsMatch(Spannable spannable, Object obj, int i2, int i7, int i8) {
        if (spannable.getSpanStart(obj) == i2 && spannable.getSpanEnd(obj) == i7 && spannable.getSpanFlags(obj) == i8) {
            spannable.removeSpan(obj);
        }
    }
}
