package com.samsung.android.gallery.app.controller.viewer;

import android.app.KeyguardManager;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDismissKeyGuardCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void onDismissKeyguardSucceeded() {
        getBlackboard().postEvent(EventMessage.obtain(3026));
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        final Runnable runnable;
        final Runnable runnable2 = null;
        if (objArr == null || objArr.length <= 0) {
            runnable = null;
        } else {
            runnable = objArr[0];
        }
        if (objArr != null && objArr.length > 1) {
            runnable2 = objArr[1];
        }
        SeApiCompat.requestDismissKeyguard(getActivity(), new KeyguardManager.KeyguardDismissCallback() {
            public void onDismissCancelled() {
                super.onDismissCancelled();
                Runnable runnable = runnable2;
                if (runnable != null) {
                    runnable.run();
                }
            }

            public void onDismissError() {
                super.onDismissError();
                Runnable runnable = runnable2;
                if (runnable != null) {
                    runnable.run();
                }
            }

            public void onDismissSucceeded() {
                super.onDismissSucceeded();
                RequestDismissKeyGuardCmd.this.onDismissKeyguardSucceeded();
                if (runnable != null) {
                    String access$000 = RequestDismissKeyGuardCmd.this.TAG;
                    Log.d(access$000, "onDismissSucceeded so run " + runnable.toString());
                    runnable.run();
                }
            }
        });
        Log.d(this.TAG, "request DismissKeyguard");
    }
}
