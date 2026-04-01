package com.samsung.android.gallery.app.controller.sharing;

import A.a;
import S3.e;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DialogTask;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.AbsRequest;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestFactory;
import com.samsung.android.gallery.app.controller.sharing.request.RequestRestoreFromTrash;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSocialHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestSharedAlbumCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public boolean mFromSelectionMode;
    /* access modifiers changed from: private */
    public AbsRequest mSharedAlbumRequest;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RequestOperationTask extends DialogTask {
        WeakReference<RequestSharedAlbumCmd> mCmd;

        public RequestOperationTask(RequestSharedAlbumCmd requestSharedAlbumCmd) {
            this.mCmd = new WeakReference<>(requestSharedAlbumCmd);
        }

        public void onPreExecute() {
            RequestSharedAlbumCmd requestSharedAlbumCmd = this.mCmd.get();
            if (requestSharedAlbumCmd == null) {
                Log.she(this.TAG, "cmd is null onPreExecute");
            } else {
                requestSharedAlbumCmd.mSharedAlbumRequest.showRunningToast();
            }
        }

        public Void doInBackground(Void... voidArr) {
            RequestSharedAlbumCmd requestSharedAlbumCmd = this.mCmd.get();
            if (requestSharedAlbumCmd == null) {
                Log.she(this.TAG, "cmd is null doInBackground");
                return null;
            }
            Thread currentThread = Thread.currentThread();
            currentThread.setName(RequestOperationTask.class.getSimpleName() + " : " + requestSharedAlbumCmd.mSharedAlbumRequest.getRequestCmdType().toString());
            requestSharedAlbumCmd.mSharedAlbumRequest.request();
            return null;
        }

        public void onPostExecute(Void voidR) {
            RequestSharedAlbumCmd requestSharedAlbumCmd = this.mCmd.get();
            if (requestSharedAlbumCmd == null) {
                Log.she(this.TAG, "cmd is null onPostExecute");
            } else {
                requestSharedAlbumCmd.mSharedAlbumRequest.onPostExecute(requestSharedAlbumCmd.mFromSelectionMode);
            }
        }
    }

    private boolean isSemsPermissionAllowed(Context context) {
        boolean z;
        boolean z3;
        boolean z7;
        char c5;
        char c6;
        char c8;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            if (packageManager.checkPermission("android.permission.READ_EXTERNAL_STORAGE", CommonUtils.MOBILE_SERVICE_PACKAGE_NAME) == 0) {
                z = true;
            } else {
                z = false;
            }
            if (SdkConfig.atLeast(SdkConfig.GED.S) || packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", CommonUtils.MOBILE_SERVICE_PACKAGE_NAME) == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (packageManager.checkPermission("android.permission.READ_CONTACTS", CommonUtils.MOBILE_SERVICE_PACKAGE_NAME) == 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z && z3 && z7) {
                return true;
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("SemsPermission not allowed {");
            if (z) {
                c5 = 'R';
            } else {
                c5 = 'r';
            }
            sb2.append(c5);
            if (z3) {
                c6 = 'W';
            } else {
                c6 = 'w';
            }
            sb2.append(c6);
            if (z7) {
                c8 = 'C';
            } else {
                c8 = 'c';
            }
            sb2.append(c8);
            sb2.append('}');
            Log.she(str, sb2.toString());
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(RequestCmdType requestCmdType, EventContext eventContext, Context context, Object[] objArr, long j2, long j3) {
        RequestCmdType requestCmdType2 = RequestCmdType.REQUEST_SYNC;
        if (requestCmdType != requestCmdType2 && requestCmdType != RequestCmdType.REQUEST_INVITATION_SYNC && MdeSocialHelper.isSocialServiceEnabled() && !MdeAuthHelper.isReadyToUseShareService()) {
            new StartSharingServiceSetupWizardCmd().execute(eventContext, RequestSetupWizardType.SETUP_SHARING_SERVICE);
        } else if (!NetworkUtils.isNetworkConnected(context)) {
            this.mSharedAlbumRequest.showNetworkErrorToast();
        } else {
            if ((requestCmdType == requestCmdType2 || requestCmdType == RequestCmdType.REQUEST_INVITATION_SYNC) && MdeAlbumHelper.sSemsPermissionDenied) {
                if (isSemsPermissionAllowed(context)) {
                    MdeAlbumHelper.sSemsPermissionDenied = false;
                } else {
                    String str = this.TAG;
                    Log.she(str, "SemsPermission denied. cancel {" + requestCmdType + '}');
                    return;
                }
            }
            requestOperation(objArr);
            String str2 = this.TAG;
            a.A(new Object[]{requestCmdType, Boolean.valueOf(this.mFromSelectionMode), Long.valueOf(j2), Long.valueOf(j3)}, new StringBuilder("processRequest"), str2);
        }
    }

    private void requestOperation(Object... objArr) {
        if (MdeSocialHelper.isSocialServiceEnabled()) {
            new RequestOperationTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        String str = this.TAG;
        Log.sh(str, "SocialService is not enabled. call CONNECT_SHARE_SERVICE before doing " + this.mSharedAlbumRequest.getRequestCmdType().toString() + " ReqType.");
        SharingConnectionHelper.getInstance().connectSession(getHandler(), objArr);
    }

    public String getEventId() {
        if (this.mSharedAlbumRequest.getRequestCmdType() == RequestCmdType.REQUEST_DOWNLOAD_CONTENTS) {
            return AnalyticsEventId.EVENT_MENU_DOWNLOAD.toString();
        }
        return null;
    }

    public boolean isAnalyticsEnabled() {
        if (this.mSharedAlbumRequest.getRequestCmdType() == RequestCmdType.REQUEST_DOWNLOAD_CONTENTS) {
            return true;
        }
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        long currentTimeMillis = System.currentTimeMillis();
        Context context = getContext();
        this.mFromSelectionMode = eventContext.isSelectionMode();
        RequestCmdType requestCmdType = objArr[0];
        AbsRequest createRequest = RequestFactory.createRequest(eventContext, objArr, requestCmdType);
        this.mSharedAlbumRequest = createRequest;
        if (createRequest instanceof RequestRestoreFromTrash) {
            ((RequestRestoreFromTrash) createRequest).setRequestCmd(new RequestSharedAlbumCmd());
        }
        SimpleThreadPool.getInstance().execute(new e(this, requestCmdType, eventContext, context, objArr, System.currentTimeMillis() - currentTimeMillis, currentTimeMillis));
    }
}
