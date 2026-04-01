package com.samsung.android.gallery.bixby.activity;

import J5.c;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.bixby.cmd.CmdLoader;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$ActivityResultBuilder;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.sdk.bixby2.Constants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryBixbyActivity extends Activity {
    private final RequestResult$ActivityResultBuilder mBuilder = new RequestResult$ActivityResultBuilder();

    private boolean isCallerAllowed() {
        String callingPackageCompat = AppResources.getCallingPackageCompat(this, new c(11, this));
        if (!Constants.BIXBY_AGENT_PKG_NAME.equals(callingPackageCompat) && !Constants.ROUTINE_PKG_NAME.equals(callingPackageCompat)) {
            Log.bxe("GalleryBixbyActivity", "unable to handle intent. invalid caller. " + callingPackageCompat);
            return false;
        } else if (SignatureChecker.checkSignature(this, callingPackageCompat)) {
            return true;
        } else {
            Log.bxe("GalleryBixbyActivity", "unable to handle intent. invalid signature. " + callingPackageCompat);
            return false;
        }
    }

    private void runCommand(Intent intent) {
        if (isCallerAllowed()) {
            Uri data = intent.getData();
            if (data == null) {
                Log.bxe("GalleryBixbyActivity", "unable to handle intent. null uri");
                return;
            }
            Log.bx("GalleryBixbyActivity", "requested uri=" + Logger.getEncodedString(data.toString()));
            BaseCmd load = CmdLoader.load(data, getBixbyClientTaskId(intent));
            if (load == null) {
                Log.bxe("GalleryBixbyActivity", "unable to handle intent. not matched");
                return;
            }
            load.execute(this);
            this.mBuilder.setExecuted();
        }
    }

    public int getBixbyClientTaskId(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey("bixbyClient_taskId")) {
            return -1;
        }
        return extras.getInt("bixbyClient_taskId");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        runCommand(getIntent());
        finish();
    }
}
