package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RubySpan {
    private static final String FIELD_POSITION = Util.intToStringMaxRadix(1);
    private static final String FIELD_TEXT = Util.intToStringMaxRadix(0);
    public final int position;
    public final String rubyText;

    public RubySpan(String str, int i2) {
        this.rubyText = str;
        this.position = i2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_TEXT, this.rubyText);
        bundle.putInt(FIELD_POSITION, this.position);
        return bundle;
    }
}
