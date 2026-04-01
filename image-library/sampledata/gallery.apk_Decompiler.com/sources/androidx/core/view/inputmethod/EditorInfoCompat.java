package androidx.core.view.inputmethod;

import android.view.inputmethod.EditorInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EditorInfoCompat {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static void setContentMimeTypes(EditorInfo editorInfo, String[] strArr) {
        editorInfo.contentMimeTypes = strArr;
    }
}
