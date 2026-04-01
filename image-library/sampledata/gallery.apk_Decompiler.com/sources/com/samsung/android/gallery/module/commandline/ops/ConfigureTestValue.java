package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConfigureTestValue implements CommandOperator {
    public Bundle operate(Command command, Context context, String[] strArr) {
        if (strArr.length > 1) {
            int i2 = UnsafeCast.toInt(strArr[1]);
            if (i2 > 0) {
                command.set(strArr[1]);
                Log.i("CommandOp", "setValue : " + command + " = " + i2);
            }
        } else {
            Log.i("CommandOp", "setNull : " + command);
            command.set((String) null);
        }
        return null;
    }
}
