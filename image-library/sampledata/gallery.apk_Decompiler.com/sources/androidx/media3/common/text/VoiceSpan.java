package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VoiceSpan {
    private static final String FIELD_NAME = Util.intToStringMaxRadix(0);
    public final String name;

    public VoiceSpan(String str) {
        this.name = str;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_NAME, this.name);
        return bundle;
    }
}
