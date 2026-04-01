package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowLowStorageCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        getBlackboard().post(CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/LowStorage").build()), (Object) null);
    }
}
