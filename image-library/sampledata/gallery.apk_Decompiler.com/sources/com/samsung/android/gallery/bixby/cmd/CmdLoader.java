package com.samsung.android.gallery.bixby.cmd;

import android.net.Uri;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CmdLoader {
    private static final BaseCmd mCmdChain = ((BaseCmd) new ChainBuilder().append(new AlbumSlideshowCmd()).append(new CategorySearchCmd()).append(new DelegateCmd()).append(new NameSearchCmd()).append(new ShareViaTVCmd()).append(new ShowViewCmd()).build());

    public static BaseCmd load(Uri uri, int i2) {
        try {
            BaseCmd.init(uri, i2);
            return mCmdChain.get();
        } catch (IllegalStateException e) {
            Log.e((CharSequence) "CmdLoader", "load failed", (Throwable) e);
            return null;
        }
    }
}
