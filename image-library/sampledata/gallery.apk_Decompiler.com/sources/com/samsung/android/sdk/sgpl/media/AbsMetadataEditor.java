package com.samsung.android.sdk.sgpl.media;

import android.util.Log;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsMetadataEditor {
    protected final String TAG = getClass().getSimpleName();
    protected AbsMetadataEditor mNextEditor;

    public void addEditor(AbsMetadataEditor absMetadataEditor) {
        this.mNextEditor = absMetadataEditor;
    }

    public final void edit(String str, Map<MediaMetadataInterface.Attribute, String[]> map) {
        if (isEditable(str)) {
            processEdit(str, map);
            return;
        }
        AbsMetadataEditor absMetadataEditor = this.mNextEditor;
        if (absMetadataEditor != null) {
            absMetadataEditor.edit(str, map);
        } else {
            Log.w(this.TAG, "Not support format.");
        }
    }

    public abstract boolean isEditable(String str);

    public abstract void processEdit(String str, Map<MediaMetadataInterface.Attribute, String[]> map);
}
