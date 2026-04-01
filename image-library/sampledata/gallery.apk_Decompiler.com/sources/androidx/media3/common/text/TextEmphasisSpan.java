package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextEmphasisSpan {
    private static final String FIELD_MARK_FILL = Util.intToStringMaxRadix(1);
    private static final String FIELD_MARK_SHAPE = Util.intToStringMaxRadix(0);
    private static final String FIELD_POSITION = Util.intToStringMaxRadix(2);
    public int markFill;
    public int markShape;
    public final int position;

    public TextEmphasisSpan(int i2, int i7, int i8) {
        this.markShape = i2;
        this.markFill = i7;
        this.position = i8;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_MARK_SHAPE, this.markShape);
        bundle.putInt(FIELD_MARK_FILL, this.markFill);
        bundle.putInt(FIELD_POSITION, this.position);
        return bundle;
    }
}
