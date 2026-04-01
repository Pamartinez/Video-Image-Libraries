package com.samsung.android.gallery.app.controller.trash;

import A.a;
import U5.c;
import V3.b;
import android.app.Dialog;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.trash.helper.onetrash.OneTrashMigration;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashMigrationCmd extends BaseCommand {
    private OneTrashMigration mOneTrashMigration;
    private Dialog mProgressDialog;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneTrashMigrationTask extends AsyncTask<Void, Void, Integer> {
        private onCompleteListener mListener;
        private OneTrashMigration mOneTrashMigration;

        public OneTrashMigrationTask(onCompleteListener oncompletelistener, OneTrashMigration oneTrashMigration) {
            this.mListener = oncompletelistener;
            this.mOneTrashMigration = oneTrashMigration;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onPreExecute$0() {
            this.mListener.showDialog();
        }

        public void onPreExecute() {
            if (this.mOneTrashMigration.getMigrationCount() > 300) {
                ThreadUtil.runOnUiThread(new a(this));
            }
        }

        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(this.mOneTrashMigration.startMigration());
        }

        public void onPostExecute(Integer num) {
            onCompleteListener oncompletelistener = this.mListener;
            if (oncompletelistener != null) {
                oncompletelistener.onComplete(num.intValue());
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onCompleteListener {
        void onComplete(int i2);

        void showDialog();
    }

    /* access modifiers changed from: private */
    public void createDialog(EventContext eventContext) {
        ThreadUtil.runOnUiThread(new c(4, this, eventContext));
    }

    private void hideDialog() {
        ThreadUtil.runOnUiThread(new b(0, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(EventContext eventContext) {
        AlertDialog create = new ProgressCircleBuilder(eventContext.getContext()).create();
        this.mProgressDialog = create;
        create.setCancelable(false);
        this.mProgressDialog.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideDialog$1() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void onCompleted(int i2) {
        hideDialog();
        if (i2 > 0) {
            getBlackboard().postEvent(EventMessage.obtain(1029, 1, 0, (Object) null));
        }
        a.k(i2, "onCompleted, result = ", "OneTrashMigrationCmd");
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(final EventContext eventContext, Object... objArr) {
        OneTrashMigration oneTrashMigration = new OneTrashMigration();
        this.mOneTrashMigration = oneTrashMigration;
        if (!oneTrashMigration.checkValid()) {
            Log.d("OneTrashMigrationCmd", "not need trash migration");
            return;
        }
        Log.d("OneTrashMigrationCmd", "start migration");
        new OneTrashMigrationTask(new onCompleteListener() {
            public void onComplete(int i2) {
                OneTrashMigrationCmd.this.onCompleted(i2);
            }

            public void showDialog() {
                OneTrashMigrationCmd.this.createDialog(eventContext);
            }
        }, this.mOneTrashMigration).execute(new Void[0]);
    }
}
