package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.CmdUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.o3dp.lib3dphotography.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OnThreadCmd extends BaseCmd {
    protected final String TAG = getClass().getSimpleName();
    private boolean mOnTest = false;
    CmdUtil mUtil = new CmdUtil();

    public void execute(Context context) {
        if (this.mOnTest) {
            lambda$execute$0(context);
        } else {
            SimpleThreadPool.getInstance().execute(new i(15, this, context));
        }
    }

    /* renamed from: executeInternal */
    public abstract void lambda$execute$0(Context context);
}
