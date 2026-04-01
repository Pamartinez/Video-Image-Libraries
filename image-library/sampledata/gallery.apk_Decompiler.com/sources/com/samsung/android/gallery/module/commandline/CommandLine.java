package com.samsung.android.gallery.module.commandline;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.commandline.ops.Command;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommandLine {
    public static final Bundle SUCCESS = new Bundle();
    private static final CharSequence TAG = "LabsCMD";
    static HashMap<String, Command> sMap = new HashMap<String, Command>() {
        {
            for (Command command : Command.values()) {
                put(command.cmd, command);
            }
        }
    };

    public Bundle onCmd(Context context, String str) {
        CharSequence charSequence = TAG;
        Log.i(charSequence, "onCmd : " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (context == null) {
            context = AppResources.getAppContext();
        }
        String[] split = str.split(" ");
        if (split.length > 0) {
            String lowerCase = split[0].toLowerCase(Locale.ROOT);
            Command command = sMap.get(lowerCase);
            if (command != null) {
                Log.i(charSequence, "exec : " + command);
                Bundle operate = command.operate(context, split);
                if (operate != null) {
                    return operate;
                }
                return SUCCESS;
            }
            Utils.showToast(context, "fail to find command : " + lowerCase);
        }
        return null;
    }
}
