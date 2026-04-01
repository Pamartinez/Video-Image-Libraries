package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdState;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DelegateCmd extends BaseCmd {
    public void execute(Context context) {
        Blackboard.publishGlobal("command://bixby_action", BaseCmd.mUri);
        this.mBuilder.setState(RequestResult$CmdState.EXECUTED);
    }

    public boolean support() {
        if ("CREATE_ALBUM".equals(BaseCmd.mAction) || "CREATE_GROUP".equals(BaseCmd.mAction) || "CREATE_MOVIE".equals(BaseCmd.mAction) || "LIST_VIEW_SHARE".equals(BaseCmd.mAction) || "MOVE_TO_ALBUM".equals(BaseCmd.mAction) || "MOVE_TO_GROUP".equals(BaseCmd.mAction) || "SELECTION_MODE".equals(BaseCmd.mAction) || "TIMELINE_SIMILAR".equals(BaseCmd.mAction) || "TRASH_EMPTY_ALL".equals(BaseCmd.mAction) || "TRASH_RESTORE_ALL".equals(BaseCmd.mAction) || "DETAIL_VIEW_EDIT".equals(BaseCmd.mAction) || "DETAIL_VIEW_QUICK_CROP".equals(BaseCmd.mAction) || "DETAIL_VIEW_SAVE".equals(BaseCmd.mAction) || "DETAIL_VIEW_SET_AS".equals(BaseCmd.mAction) || "DETAIL_VIEW_SHARE".equals(BaseCmd.mAction) || "DETAIL_VIEW_TAG".equals(BaseCmd.mAction) || "DETAIL_VIEW_MOVE_TO_ALBUM".equals(BaseCmd.mAction)) {
            return true;
        }
        return false;
    }
}
