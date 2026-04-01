package com.samsung.android.gallery.app.controller.mtp;

import P3.C0408a;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DialogTask;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.mtp.MtpFileImporter;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleMtpImportCmd extends BaseCommand {
    private MtpFileImporter mImporter;
    private MediaItem mItem;
    private final SubscriberListener mOptionListener = new C0408a(this, 0);
    private final SubscriberListener mRenameListener = new C0408a(this, 1);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MtpImportTask extends DialogTask {
        private final Blackboard mBlackboard;
        private final int mId;
        private final MtpFileImporter mImporter;
        private final String mPath;
        private final WeakReference<Context> mRef;

        public MtpImportTask(Context context, Blackboard blackboard, MtpFileImporter mtpFileImporter, int i2, String str) {
            this.mBlackboard = blackboard;
            this.mImporter = mtpFileImporter;
            this.mId = i2;
            this.mPath = str;
            this.mRef = new WeakReference<>(context);
            createDialog(context);
        }

        private void handleResult() {
            int i2;
            Context context = this.mRef.get();
            if (context == null) {
                Log.mte(this.TAG, "unable to handle result, context is null");
                return;
            }
            if (this.mImporter.isImported()) {
                i2 = R.string.import_complete;
            } else {
                i2 = R.string.import_item_failed;
            }
            showToast(context, context.getString(i2));
        }

        public void onPreExecute() {
            showDialog();
        }

        public Void doInBackground(Void... voidArr) {
            if (!this.mImporter.initDirectory()) {
                Log.mtw(this.TAG, "directory creation failed");
                return null;
            }
            this.mImporter.performImport(this.mId, this.mPath);
            return null;
        }

        public void onPostExecute(Void voidR) {
            dismissDialog();
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
            this.mImporter.finishImport();
            handleResult();
        }
    }

    /* access modifiers changed from: private */
    public void onOptionSelected(Object obj, Bundle bundle) {
        if (obj == null || bundle == null) {
            String str = this.TAG;
            Log.mtw(str, "Unable to import. wrong option [" + obj + "][" + bundle + "]");
        } else {
            int i2 = BundleWrapper.getInt(bundle, "target", -1);
            if (i2 == -2) {
                operateImport((String) obj);
            } else if (i2 == -1) {
                showRenameDialog(new FileNameBuilder((String) obj).buildUnique());
                return;
            }
        }
        unsubscribeEvents();
    }

    /* access modifiers changed from: private */
    public void onRenameSelected(Object obj, Bundle bundle) {
        if (obj != null) {
            operateImport((String) obj);
        } else {
            Log.mtw(this.TAG, "unable to import. wrong rename.");
        }
        unsubscribeEvents();
    }

    private void operateImport(String str) {
        new MtpImportTask(getContext(), getBlackboard(), this.mImporter, (int) this.mItem.getFileId(), str).execute(new Void[0]);
    }

    private void showFileOperationDialog(String str, String str2) {
        subscribeEvents();
        getBlackboard().post(new UriBuilder("command://ShowDialog").appendArg("target", "FileOperationDialog").appendArg("title", str).appendArg(FileApiContract.Parameter.PATH, str2).appendArg("screenId", AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString()).appendArg("disable_check_box", true).build(), (Object) null);
    }

    private void showRenameDialog(String str) {
        getBlackboard().post(new UriBuilder("command://ShowDialog").appendArg("target", "RenameFileDialog").appendArg(FileApiContract.Parameter.PATH, new FileNameBuilder(str).buildUnique()).build(), (Object) null);
    }

    private void subscribeEvents() {
        Blackboard blackboard = getBlackboard();
        blackboard.subscribeOnUi("command://OperationSelected", this.mOptionListener);
        blackboard.subscribeOnUi("command://RenameSelected", this.mRenameListener);
    }

    private void unsubscribeEvents() {
        Blackboard blackboard = getBlackboard();
        blackboard.unsubscribe("command://OperationSelected", this.mOptionListener);
        blackboard.unsubscribe("command://RenameSelected", this.mRenameListener);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_IMPORT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mItem = mediaItem;
        if (mediaItem == null || mediaItem.getPath() == null) {
            Log.mte(this.TAG, "unable to import. item is null or path is null.");
            return;
        }
        Context applicationContext = getApplicationContext();
        MtpFileImporter mtpFileImporter = new MtpFileImporter(applicationContext, MtpClient.getInstance(applicationContext).getDeviceNameFromPath(this.mItem.getPath()));
        this.mImporter = mtpFileImporter;
        if (mtpFileImporter.getDeviceName() == null) {
            Log.mte(this.TAG, "unable to import. device name is null.");
            return;
        }
        String importPath = this.mImporter.getImportPath(this.mItem.getTitle());
        if (FileUtils.exists(importPath)) {
            showFileOperationDialog(FileUtils.getNameFromPath(importPath), importPath);
        } else {
            operateImport(importPath);
        }
    }
}
