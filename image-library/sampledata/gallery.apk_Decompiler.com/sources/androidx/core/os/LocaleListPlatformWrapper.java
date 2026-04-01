package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class LocaleListPlatformWrapper implements LocaleListInterface {
    private final LocaleList mLocaleList;

    public LocaleListPlatformWrapper(Object obj) {
        this.mLocaleList = (LocaleList) obj;
    }

    public boolean equals(Object obj) {
        return this.mLocaleList.equals(((LocaleListInterface) obj).getLocaleList());
    }

    public Locale get(int i2) {
        return this.mLocaleList.get(i2);
    }

    public Object getLocaleList() {
        return this.mLocaleList;
    }

    public int hashCode() {
        return this.mLocaleList.hashCode();
    }

    public boolean isEmpty() {
        return this.mLocaleList.isEmpty();
    }

    public int size() {
        return this.mLocaleList.size();
    }

    public String toLanguageTags() {
        return this.mLocaleList.toLanguageTags();
    }

    public String toString() {
        return this.mLocaleList.toString();
    }
}
