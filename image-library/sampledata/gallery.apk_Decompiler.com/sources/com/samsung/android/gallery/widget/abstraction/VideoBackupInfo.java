package com.samsung.android.gallery.widget.abstraction;

import android.graphics.Bitmap;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoBackupInfo {
    public int hash;
    public boolean mute;
    private HashMap<KEY, Object> params;
    public int position;
    public Bitmap preview;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum KEY {
        HIGHLIGHT_FRC
    }

    public VideoBackupInfo(int i2, int i7, Bitmap bitmap, boolean z) {
        this.hash = i2;
        this.position = i7;
        this.preview = bitmap;
        this.mute = z;
    }

    public VideoBackupInfo addParameter(KEY key, Object obj) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(key, obj);
        return this;
    }

    public Object getParameter(KEY key) {
        HashMap<KEY, Object> hashMap = this.params;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(key);
    }
}
