package com.samsung.android.sdk.sgpl.media;

import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.sgpl.media.iso.ISOEditor;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaMetadataInterface {
    private static final String TAG = "MediaMetadataInterface";
    private final ISOEditor mISOEditor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Attribute {
        ATTR_CREATION_TIME(1),
        ATTR_LATITUDE(2),
        ATTR_LONGITUDE(3);
        
        /* access modifiers changed from: private */
        public final int mIsoKeyCode;

        private Attribute(int i2) {
            this.mIsoKeyCode = i2;
        }
    }

    public MediaMetadataInterface(String str) {
        this.mISOEditor = new ISOEditor(str);
    }

    public boolean isEditable() {
        return this.mISOEditor.isEditableFile();
    }

    public boolean saveAttributes() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.mISOEditor.saveAttributes();
            Log.d(TAG, "saveAttributes +" + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public void setAttribute(Attribute attribute, String str) {
        Log.d(TAG, "setAttribute(" + attribute.name() + ArcCommonLog.TAG_COMMA + str + ")");
        this.mISOEditor.setAttribute(attribute.mIsoKeyCode, str);
    }
}
