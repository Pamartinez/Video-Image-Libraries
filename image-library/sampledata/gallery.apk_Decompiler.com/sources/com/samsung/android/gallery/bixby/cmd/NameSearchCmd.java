package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelperParams;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdState;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NameSearchCmd extends OnThreadCmd {
    private ActionHelperParams getParams(String str) {
        return new ActionHelperParams.Builder().setName(str).addFlag(65536).build();
    }

    public void executeInternal(Context context) {
        boolean z;
        Intent defaultIntent = getDefaultIntent();
        String queryParameter = BaseCmd.mUri.getQueryParameter("KEY_ALBUM_NAME");
        String queryParameter2 = BaseCmd.mUri.getQueryParameter("KEY_STORY_NAME");
        String str = this.TAG;
        Log.bx(str, "search name [" + Logger.getEncodedString(queryParameter) + "][" + Logger.getEncodedString(queryParameter2) + "]");
        if (!TextUtils.isEmpty(queryParameter)) {
            z = this.mUtil.searchAlbumAndGroup(context, defaultIntent, getParams(queryParameter));
        } else if (!TextUtils.isEmpty(queryParameter2)) {
            z = this.mUtil.searchStory(context, defaultIntent, queryParameter2);
        } else {
            Log.bxe(this.TAG, "unable to search album or story, invalid parameter");
            this.mBuilder.setState(RequestResult$CmdState.NO_PARAMETER);
            return;
        }
        startGalleryExternalActivity(context, defaultIntent, z);
        this.mBuilder.setState(RequestResult$CmdState.EXECUTED);
    }

    public boolean support() {
        return "SEARCH_BY_NAME".equals(BaseCmd.mAction);
    }
}
