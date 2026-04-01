package androidx.media3.common;

import androidx.media3.common.util.Util;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Label {
    private static final String FIELD_LANGUAGE_INDEX = Util.intToStringMaxRadix(0);
    private static final String FIELD_VALUE_INDEX = Util.intToStringMaxRadix(1);
    public final String language;
    public final String value;

    public Label(String str, String str2) {
        this.language = Util.normalizeLanguageCode(str);
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Label label = (Label) obj;
            if (!Objects.equals(this.language, label.language) || !Objects.equals(this.value, label.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.value.hashCode() * 31;
        String str = this.language;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        return hashCode + i2;
    }
}
