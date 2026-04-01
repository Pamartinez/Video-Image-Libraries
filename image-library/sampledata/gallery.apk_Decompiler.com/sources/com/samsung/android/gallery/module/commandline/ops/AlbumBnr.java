package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.adobe.internal.xmp.options.PropertyOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumBnr implements CommandOperator {
    public Bundle operate(Command command, Context context, String[] strArr) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.SettingActivity");
            intent.addFlags(PropertyOptions.DELETE_EXISTING);
            intent.putExtra("gallery.setting.location", "labs.album.bnr");
            context.startActivity(intent);
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
