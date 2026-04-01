package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GotoMyFilesCmd extends BaseCommand {
    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            String[] splitPathAndName = FileUtils.splitPathAndName(objArr[0]);
            Intent intent = new Intent("samsung.myfiles.intent.action.LAUNCH_MY_FILES");
            intent.setPackage("com.sec.android.app.myfiles");
            intent.putExtra("samsung.myfiles.intent.extra.START_PATH", splitPathAndName[0]);
            intent.putExtra("focus_fileName", splitPathAndName[1]);
            getActivity().startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("start failed. e="), this.TAG);
        }
    }
}
