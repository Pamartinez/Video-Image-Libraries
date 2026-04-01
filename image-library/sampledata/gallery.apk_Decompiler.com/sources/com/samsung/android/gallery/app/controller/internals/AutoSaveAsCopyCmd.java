package com.samsung.android.gallery.app.controller.internals;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoSaveAsCopyCmd extends SaveAsCopyCmd {
    public void finish(String str, Uri uri) {
        getBlackboard().post("data://bixby_command_done", new Object[]{"no_error", null, str, uri});
    }
}
