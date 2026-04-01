package androidx.media3.common.text;

import android.os.Bundle;
import android.text.Spanned;
import androidx.media3.common.util.Util;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CustomSpanBundler {
    private static final String FIELD_END_INDEX = Util.intToStringMaxRadix(1);
    private static final String FIELD_FLAGS = Util.intToStringMaxRadix(2);
    private static final String FIELD_PARAMS = Util.intToStringMaxRadix(4);
    private static final String FIELD_START_INDEX = Util.intToStringMaxRadix(0);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(3);

    public static ArrayList<Bundle> bundleCustomSpans(Spanned spanned) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        for (RubySpan rubySpan : (RubySpan[]) spanned.getSpans(0, spanned.length(), RubySpan.class)) {
            arrayList.add(spanToBundle(spanned, rubySpan, 1, rubySpan.toBundle()));
        }
        for (TextEmphasisSpan textEmphasisSpan : (TextEmphasisSpan[]) spanned.getSpans(0, spanned.length(), TextEmphasisSpan.class)) {
            arrayList.add(spanToBundle(spanned, textEmphasisSpan, 2, textEmphasisSpan.toBundle()));
        }
        for (HorizontalTextInVerticalContextSpan spanToBundle : (HorizontalTextInVerticalContextSpan[]) spanned.getSpans(0, spanned.length(), HorizontalTextInVerticalContextSpan.class)) {
            arrayList.add(spanToBundle(spanned, spanToBundle, 3, (Bundle) null));
        }
        for (VoiceSpan voiceSpan : (VoiceSpan[]) spanned.getSpans(0, spanned.length(), VoiceSpan.class)) {
            arrayList.add(spanToBundle(spanned, voiceSpan, 4, voiceSpan.toBundle()));
        }
        return arrayList;
    }

    private static Bundle spanToBundle(Spanned spanned, Object obj, int i2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt(FIELD_START_INDEX, spanned.getSpanStart(obj));
        bundle2.putInt(FIELD_END_INDEX, spanned.getSpanEnd(obj));
        bundle2.putInt(FIELD_FLAGS, spanned.getSpanFlags(obj));
        bundle2.putInt(FIELD_TYPE, i2);
        if (bundle != null) {
            bundle2.putBundle(FIELD_PARAMS, bundle);
        }
        return bundle2;
    }
}
