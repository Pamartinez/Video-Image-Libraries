package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdResultBuilder;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowViewCmd extends BaseCmd {
    private boolean needPush() {
        if ("SHOW_SEARCH_SUGGESTION_VIEW".equals(BaseCmd.mAction) || "SHOW_SLIDE_SHOW_VIEW".equals(BaseCmd.mAction) || "SHOW_TRASH_VIEW".equals(BaseCmd.mAction)) {
            return true;
        }
        return false;
    }

    public void execute(Context context) {
        RequestResult$CmdState requestResult$CmdState;
        Intent defaultIntent = getDefaultIntent();
        defaultIntent.putExtra("bixby_locationKey", BaseCmd.mAction);
        boolean needPush = needPush();
        startGalleryExternalActivity(context, defaultIntent, needPush);
        RequestResult$CmdResultBuilder requestResult$CmdResultBuilder = this.mBuilder;
        if (needPush) {
            requestResult$CmdState = RequestResult$CmdState.NEED_PUSH;
        } else {
            requestResult$CmdState = RequestResult$CmdState.NOT_NEED_PUSH;
        }
        requestResult$CmdResultBuilder.setState(requestResult$CmdState);
    }

    public boolean support() {
        if ("SHOW_ALBUM_VIEW".equals(BaseCmd.mAction) || "SHOW_TIME_VIEW".equals(BaseCmd.mAction) || "SHOW_SEARCH_SUGGESTION_VIEW".equals(BaseCmd.mAction) || "SHOW_SLIDE_SHOW_VIEW".equals(BaseCmd.mAction) || "SHOW_STORY_VIEW".equals(BaseCmd.mAction) || "SHOW_TRASH_VIEW".equals(BaseCmd.mAction)) {
            return true;
        }
        return false;
    }
}
