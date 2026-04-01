package com.samsung.android.gallery.module.commandline.ops;

import N2.j;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UxTune;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ApplyUxTune implements CommandOperator {
    private HashMap<String, BiConsumer<Context, String>> operator = new HashMap<String, BiConsumer<Context, String>>() {
        public static final /* synthetic */ int d = 0;

        {
            put("interpolator", new a(ApplyUxTune.this, 0));
            put("int", new a(ApplyUxTune.this, 1));
            put("float", new a(ApplyUxTune.this, 2));
        }
    };

    private void applyUxTune(Context context, String str) {
        Log.d("CommandOp", "applyUxTune " + str);
        String[] split = str.split("=");
        if (split.length != 2) {
            Toast.makeText(context, "Gallery UX Tune{" + str + "} not proper", 0).show();
            return;
        }
        BiConsumer biConsumer = this.operator.get(split[0]);
        if (biConsumer != null) {
            biConsumer.accept(context, split[1]);
            return;
        }
        Toast.makeText(context, "Gallery UX Tune{" + split[0] + "} not support operator", 0).show();
    }

    /* access modifiers changed from: private */
    public void uxtune_interpolator(Context context, String str) {
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (split.length != 4) {
            Toast.makeText(context, "Gallery UX Tune{" + str + "} interpolator arguments must 4", 0).show();
            return;
        }
        try {
            UxTune.setInterpolator(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
            Toast.makeText(context, "Gallery UX Tune{" + str + "} apply interpolator", 0).show();
        } catch (Exception e) {
            StringBuilder k = j.k("Gallery UX Tune{", str, "} exception\n");
            k.append(e.getMessage());
            Toast.makeText(context, k.toString(), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void uxtune_set_float(Context context, String str) {
        try {
            UxTune.setTestFloatValue(Float.parseFloat(str));
            Toast.makeText(context, "Gallery UX Tune{" + str + "} apply float value", 0).show();
        } catch (Exception e) {
            StringBuilder k = j.k("Gallery UX Tune{", str, "} exception\n");
            k.append(e.getMessage());
            Toast.makeText(context, k.toString(), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void uxtune_set_int(Context context, String str) {
        try {
            UxTune.setTestIntValue(Integer.parseInt(str));
            Toast.makeText(context, "Gallery UX Tune{" + str + "} apply int value", 0).show();
        } catch (Exception e) {
            StringBuilder k = j.k("Gallery UX Tune{", str, "} exception\n");
            k.append(e.getMessage());
            Toast.makeText(context, k.toString(), 0).show();
        }
    }

    public Bundle operate(Command command, Context context, String[] strArr) {
        try {
            applyUxTune(context, strArr[1]);
            return null;
        } catch (Exception unused) {
            Toast.makeText(context, "Gallery UX Tune{" + strArr + "} not proper", 0).show();
            return null;
        }
    }
}
