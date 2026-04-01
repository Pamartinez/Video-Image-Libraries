package com.samsung.android.gallery.app.service;

import N2.j;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpFileImporter;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.notification.ImportNotificationHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpImportService extends AbsProgressService {
    private int mCurrentObjectHandle = 0;
    private String mDeviceName = null;
    private DialogState mDialogState = DialogState.PROGRESS;
    private boolean mForceInterrupted = false;
    private int mImportCount = 0;
    private MtpFileImporter mImporter = null;
    private boolean mIsChecked = false;
    private int mTotalCount = 0;
    private int mWhichChecked = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DialogState {
        PROGRESS,
        DUPLICATE,
        RENAME
    }

    public MtpImportService() {
        super("MtpImportService", "com.samsung.android.gallery.app.service.MtpImportService");
    }

    private String getNotificationMessage() {
        if (this.mTotalCount == 1) {
            return getString(R.string.importing_one_item);
        }
        return getString(R.string.importing_n_items, new Object[]{Integer.valueOf(this.mImportCount), Integer.valueOf(this.mTotalCount)});
    }

    private boolean isAllSkip(boolean z) {
        if (z || this.mTotalCount != this.mImportCount) {
            return false;
        }
        return true;
    }

    private boolean operate(int i2, String str) {
        if (i2 != -2) {
            if (i2 == -1) {
                String buildUnique = new FileNameBuilder(str).buildUnique();
                if (this.mIsChecked) {
                    this.mImporter.performImport(this.mCurrentObjectHandle, buildUnique);
                    return true;
                }
                this.mDialogState = DialogState.RENAME;
                this.mDialogHelper.showRenameDialog(buildUnique);
                return false;
            } else if (i2 != 0) {
                return true;
            }
        }
        this.mImporter.performImport(this.mCurrentObjectHandle, str);
        return true;
    }

    private void showResult(boolean z) {
        String str;
        int i2;
        if (!isAllSkip(z)) {
            if (this.mForceInterrupted || !z) {
                if (this.mTotalCount > 1) {
                    i2 = R.string.import_items_failed;
                } else {
                    i2 = R.string.import_item_failed;
                }
                str = getString(i2);
            } else if (isInterrupted()) {
                str = getString(R.string.import_cancelled);
            } else {
                str = getString(R.string.import_complete);
            }
            Utils.showToast((Context) this, str);
        }
    }

    private boolean startImport(String str) {
        String importPath = this.mImporter.getImportPath(str);
        if (!FileUtils.exists(importPath)) {
            this.mImporter.performImport(this.mCurrentObjectHandle, importPath);
            return true;
        } else if (this.mIsChecked) {
            operate(this.mWhichChecked, importPath);
            return true;
        } else {
            this.mDialogState = DialogState.DUPLICATE;
            this.mDialogHelper.showFileOperationDialog(str, importPath, AnalyticsScreenId.SCREEN_MTP_PICTURES_EDIT.toString());
            return false;
        }
    }

    public boolean addJobs(Intent intent) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.mtw("MtpImportService", "items are empty. adding failed.");
            return false;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                addToQueue(new ProgressJob(mediaItem));
            }
        }
        if (isQueueEmpty()) {
            Log.mtw("MtpImportService", "queue is empty. adding failed.");
            return false;
        }
        this.mTotalCount = getQueueSize();
        return true;
    }

    public void doJob(ProgressJob progressJob) {
        try {
            this.mImportCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            this.mCurrentObjectHandle = (int) mediaItem.getFileId();
            this.mNotificationTitle = mediaItem.getTitle();
            this.mNotificationMessage = getNotificationMessage();
            Log.mt("MtpImportService", "do job [" + this.mImportCount + "][" + this.mTotalCount + "]");
            this.mDialogHelper.updateDialog(this.mImportCount, this.mTotalCount, getPercentage());
            updateNotification();
            if (!startImport(this.mNotificationTitle)) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ((a) this.mJobCallback).a();
            throw th;
        }
        ((a) this.mJobCallback).a();
    }

    public String getChannelName() {
        return getString(R.string.import_file);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 == 0) {
            return 0;
        }
        return (this.mImportCount * 100) / i2;
    }

    public void onCleanInternal() {
        this.mDialogHelper.dismissDialog();
        MtpFileImporter mtpFileImporter = this.mImporter;
        if (mtpFileImporter != null) {
            showResult(mtpFileImporter.isImported());
        }
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        Log.mt("MtpImportService", "continue requested [" + this.mDialogState + "]");
        this.mDialogHelper.showDialog(getString(R.string.importing), this.mImportCount, this.mTotalCount, getPercentage());
        if (this.mDialogState.ordinal() >= DialogState.DUPLICATE.ordinal()) {
            String importPath = this.mImporter.getImportPath(this.mNotificationTitle);
            this.mDialogHelper.showFileOperationDialog(this.mNotificationTitle, importPath, AnalyticsScreenId.SCREEN_MTP_PICTURES_EDIT.toString());
            if (this.mDialogState == DialogState.RENAME) {
                this.mDialogHelper.showRenameDialog(new FileNameBuilder(importPath).buildUnique());
            }
        }
    }

    public void onEndInternal() {
        MtpFileImporter mtpFileImporter = this.mImporter;
        if (mtpFileImporter != null) {
            mtpFileImporter.finishImport();
        }
        super.onEndInternal();
    }

    public boolean onInterruptInternal(Intent intent) {
        MtpFileImporter mtpFileImporter = this.mImporter;
        if (mtpFileImporter != null) {
            String deviceName = mtpFileImporter.getDeviceName();
            String stringExtra = intent.getStringExtra("mtp_device_name");
            if (stringExtra != null && !Objects.equals(deviceName, stringExtra)) {
                Log.mtw("MtpImportService", j.d("interrupt requested but not matched [", deviceName, "][", stringExtra, "]"));
                return false;
            }
        }
        this.mForceInterrupted = intent.getBooleanExtra("mtp_import_interruption", false);
        Log.mt("MtpImportService", "interrupt requested [" + this.mForceInterrupted + "][" + this.mDialogState + "]");
        if (this.mDialogState.ordinal() <= DialogState.PROGRESS.ordinal()) {
            return true;
        }
        ((a) this.mJobCallback).a();
        return true;
    }

    public void onOptionInternal(Object obj, Bundle bundle) {
        boolean z;
        if (obj == null || bundle == null) {
            z = true;
        } else {
            this.mWhichChecked = BundleWrapper.getInt(bundle, "target", -1);
            this.mIsChecked = BundleWrapper.getBoolean(bundle, "checked", false);
            z = operate(this.mWhichChecked, (String) obj);
        }
        if (z) {
            this.mDialogState = DialogState.PROGRESS;
            ((a) this.mJobCallback).a();
        }
    }

    public boolean onPrepareInternal(Intent intent) {
        String stringExtra = intent.getStringExtra("mtp_device_name");
        this.mDeviceName = stringExtra;
        if (stringExtra == null) {
            Log.mtw("MtpImportService", "device name is null, prepare failed.");
            return false;
        }
        MtpFileImporter mtpFileImporter = new MtpFileImporter(getApplicationContext(), this.mDeviceName);
        this.mImporter = mtpFileImporter;
        if (mtpFileImporter.initDirectory()) {
            return super.onPrepareInternal(intent);
        }
        Log.mtw("MtpImportService", "directory creation failed, prepare failed.");
        return false;
    }

    public void onRenameInternal(Object obj, Bundle bundle) {
        if (obj != null) {
            operate(0, (String) obj);
        }
        this.mDialogState = DialogState.PROGRESS;
        ((a) this.mJobCallback).a();
    }

    public void onStartInternal() {
        super.onStartInternal();
        DialogHelper dialogHelper = this.mDialogHelper;
        String string = getString(R.string.importing);
        int i2 = this.mTotalCount;
        dialogHelper.showDialog(string, 1, i2, 100 / i2);
    }

    public void onTerminateInternal(boolean z) {
        Blackboard blackboard;
        super.onTerminateInternal(z);
        if (this.mForceInterrupted && (blackboard = this.mBlackboard) != null) {
            blackboard.postBroadcastEvent(EventMessage.obtain(1063, 1, 0, (Object) null));
        }
    }

    public ImportNotificationHelper getNotificationHelper() {
        return new ImportNotificationHelper(this, -1388790201, "MtpImportService", "com.samsung.android.gallery.app.service.MtpImportService", this.mDeviceName);
    }
}
