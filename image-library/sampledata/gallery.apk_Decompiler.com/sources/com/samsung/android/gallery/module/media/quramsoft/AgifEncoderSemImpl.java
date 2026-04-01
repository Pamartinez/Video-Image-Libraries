package com.samsung.android.gallery.module.media.quramsoft;

import com.samsung.android.media.SemAgifEncoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AgifEncoderSemImpl extends SemAgifEncoder implements AgifEncoder {
    final String path;

    public AgifEncoderSemImpl(String str) {
        this.path = str;
    }

    public void close() {
        try {
            finish();
        } catch (Error | Exception unused) {
        }
    }

    public boolean start(int i2, int i7, int i8) {
        if (i8 > 3) {
            setMaxTaskTP(3);
        }
        setDispose(2);
        setPosition(0, 0);
        setRepeat(0);
        setGlobalSize(i2, i7);
        setSize(i2, i7);
        setTransparent(1);
        return start(this.path);
    }
}
