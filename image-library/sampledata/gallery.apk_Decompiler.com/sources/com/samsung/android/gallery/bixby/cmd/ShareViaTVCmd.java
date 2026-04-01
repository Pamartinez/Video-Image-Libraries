package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelperParams;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdResultBuilder;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdState;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareViaTVCmd extends OnThreadCmd {
    private ActionHelperParams getParams(String str) {
        return new ActionHelperParams.Builder().setName(str).addFlag(256).addFlag(1).addFlag(65536).build();
    }

    public void executeInternal(Context context) {
        RequestResult$CmdState requestResult$CmdState;
        Intent defaultIntent = getDefaultIntent();
        String queryParameter = BaseCmd.mUri.getQueryParameter("KEY_ALBUM_NAME");
        String str = this.TAG;
        Log.bx(str, "search name [" + Logger.getEncodedString(queryParameter) + "]");
        if (TextUtils.isEmpty(queryParameter)) {
            Log.bxe(this.TAG, "unable to search album, invalid parameter");
            this.mBuilder.setState(RequestResult$CmdState.NO_ALBUM_NAME);
            return;
        }
        boolean searchAlbumAndGroup = this.mUtil.searchAlbumAndGroup(context, defaultIntent, getParams(queryParameter));
        if (searchAlbumAndGroup) {
            defaultIntent.putExtra("bixby_share_via_tv", true);
        }
        startGalleryExternalActivity(context, defaultIntent, searchAlbumAndGroup);
        RequestResult$CmdResultBuilder requestResult$CmdResultBuilder = this.mBuilder;
        if (searchAlbumAndGroup) {
            requestResult$CmdState = RequestResult$CmdState.EXECUTED;
        } else {
            requestResult$CmdState = RequestResult$CmdState.EXECUTED_DEFAULT;
        }
        requestResult$CmdResultBuilder.setState(requestResult$CmdState);
    }

    public boolean support() {
        return "SHARE_VIA_TV".equals(BaseCmd.mAction);
    }
}
