package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.scsp.media.api.constant.MediaApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EnableLabsAll implements CommandOperator {
    public Bundle operate(Command command, Context context, String[] strArr) {
        String str;
        boolean z = PocFeatures.toggleEnabled(PocFeatures.GalleryLabsDev);
        if (z) {
            PocFeatures.setEnabled(PocFeatures.GalleryLabs, true);
        }
        if (z) {
            str = MediaApiContract.Parameter.ENABLED;
        } else {
            str = "disabled";
        }
        Toast.makeText(context, "[DEV] all labs for developer ".concat(str), 1).show();
        return null;
    }
}
