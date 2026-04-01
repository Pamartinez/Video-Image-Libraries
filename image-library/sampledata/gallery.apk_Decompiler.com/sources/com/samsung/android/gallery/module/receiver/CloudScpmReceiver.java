package com.samsung.android.gallery.module.receiver;

import F3.b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudScpmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String str;
        if (intent == null) {
            str = null;
        } else {
            str = intent.getAction();
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("com.samsung.android.scpm.policy.")) {
            Log.w((CharSequence) "CloudScpmReceiver", "onReceive skip", str);
        } else if (str.startsWith("com.samsung.android.scpm.policy.UPDATE.")) {
            String replaceFirst = str.replaceFirst("com.samsung.android.scpm.policy.UPDATE.", "");
            Log.d("CloudScpmReceiver", "onReceive#update", Logger.getEncodedString(replaceFirst));
            ThreadUtil.postOnBgThreadDelayed(new b(context, replaceFirst, 2), 200);
        } else {
            Log.d("CloudScpmReceiver", "onReceive", str.replaceFirst("com.samsung.android.scpm.policy.", ""));
        }
    }
}
