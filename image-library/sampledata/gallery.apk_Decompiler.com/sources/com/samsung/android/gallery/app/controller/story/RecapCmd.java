package com.samsung.android.gallery.app.controller.story;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.data.DummyCreator;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapCmd extends BaseSelectedCommand {
    private String getTargetUri(Object[] objArr) {
        MediaItem[] mediaItemArr;
        String str;
        String str2;
        if (objArr == null || objArr.length <= 0) {
            mediaItemArr = null;
        } else {
            mediaItemArr = objArr[0];
        }
        if (objArr == null || objArr.length <= 1) {
            str = null;
        } else {
            str = objArr[1];
        }
        if (objArr == null || objArr.length <= 2) {
            str2 = null;
        } else {
            str2 = objArr[2];
        }
        String appendUriKey = ArgumentsUtil.appendUriKey("location://selectedItems", "/slideshow", true);
        getBlackboard().publish(DataKey.DATA("location://selectedItems"), mediaItemArr);
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(appendUriKey, "slide_show_recap", (String) getParameter("slide_show_recap")), Message.KEY_POSITION, "0"), "media_item", (String) null);
        if (!TextUtils.isEmpty(str)) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "filter_name", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            return ArgumentsUtil.appendArgs(appendArgs, "bgm_name", str2);
        }
        return appendArgs;
    }

    /* access modifiers changed from: private */
    public void startSlideshow(String str) {
        getBlackboard().post("command://MoveURL", str);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        eventContext.getSelectedItemCount();
        new SafeClipboard(AppResources.getAppContext()).setPrimaryClip(new ClipData(new ClipDescription("dummy", new String[]{"text/plain"}), new ClipData.Item("dummy")));
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
        final String targetUri = getTargetUri(objArr);
        if (targetUri == null) {
            Log.d(this.TAG, "startSlideshow Fail.  null target uri");
        } else if (LocationKey.isStoryHighlight(eventContext.getLocationKey())) {
            startSlideshow(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(targetUri, "type", "StoryMoments"), "media_item", BlackboardUtils.publishMediaItem(getBlackboard(), eventContext.getHeaderItem())));
        } else {
            new AlertDialog.Builder(getContext()).setItems(DummyCreator.TYPE_NAMES, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    RecapCmd.this.startSlideshow(ArgumentsUtil.appendArgs(targetUri, "type", (String) DummyCreator.TYPE_NAMES[i2]));
                }
            }).create().show();
        }
    }
}
