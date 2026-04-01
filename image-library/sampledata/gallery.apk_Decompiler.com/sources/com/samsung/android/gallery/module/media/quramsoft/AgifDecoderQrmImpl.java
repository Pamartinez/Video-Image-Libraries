package com.samsung.android.gallery.module.media.quramsoft;

import android.content.Context;
import android.net.Uri;
import com.quramsoft.agifDecoder.QuramAGIFDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AgifDecoderQrmImpl extends QuramAGIFDecoder implements AgifDecoder {
    public AgifDecoderQrmImpl(Context context, Uri uri) {
        super(context, uri);
    }

    public void close() {
        try {
            finish();
        } catch (Error | Exception unused) {
        }
    }
}
