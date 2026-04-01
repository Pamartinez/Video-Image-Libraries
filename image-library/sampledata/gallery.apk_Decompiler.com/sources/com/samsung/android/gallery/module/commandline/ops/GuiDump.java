package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.module.utils.ViewDebug;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GuiDump implements CommandOperator {
    public Bundle operate(Command command, Context context, String[] strArr) {
        return ViewDebug.dumpBundleForGui();
    }
}
