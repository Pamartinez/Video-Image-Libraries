package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ApplyPreference implements CommandOperator {
    private String[] applyPreference(String str) {
        String[] split = str.split("=");
        String str2 = split[0];
        String str3 = split[1];
        Log.i("CommandOp", "applyPreference", str2, str3);
        if (str3.equals("ture") || str3.equals("treu")) {
            str3 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        } else if (str3.equals("fales") || str3.equals("flase") || str3.equals("fasle")) {
            str3 = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        }
        if (SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equalsIgnoreCase(str3) || SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE.equalsIgnoreCase(str3)) {
            GalleryPreference.getInstance().saveState(str2, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equalsIgnoreCase(str3));
            return split;
        } else if (str3.matches("\\d+")) {
            GalleryPreference.getInstance().saveState(str2, Integer.parseInt(str3));
            return split;
        } else if (str3.matches("\\d+(L|l)$")) {
            GalleryPreference.getInstance().saveState(str2, Long.parseLong(str3.substring(0, str3.length() - 1)));
            return split;
        } else {
            GalleryPreference.getInstance().saveState(str2, str3);
            return split;
        }
    }

    public Bundle operate(Command command, Context context, String[] strArr) {
        try {
            String[] applyPreference = applyPreference(strArr[1]);
            Toast.makeText(context, "[DEV] Gallery Preference{" + applyPreference[0] + ',' + applyPreference[1] + "} saved", 0).show();
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
