package com.samsung.android.gallery.module.trash.helper;

import android.content.Context;
import android.os.AsyncTask;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.support.TrashExternalLogger;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashUpdateTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<Context> mContext;
    private final TrashExternalLogger.Task mTask;

    /* renamed from: com.samsung.android.gallery.module.trash.helper.TrashUpdateTask$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$trash$support$TrashExternalLogger$Task;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task[] r0 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$trash$support$TrashExternalLogger$Task = r0
                com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task r1 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.SIGNED_OUT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$trash$support$TrashExternalLogger$Task     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task r1 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.MIGRATION_TO_ONE_DRIVE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$trash$support$TrashExternalLogger$Task     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task r1 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.TRASH_OFF     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashUpdateTask.AnonymousClass1.<clinit>():void");
        }
    }

    public TrashUpdateTask(Context context, TrashExternalLogger.Task task) {
        this.mContext = new WeakReference<>(context);
        this.mTask = task;
    }

    public void done(TrashExternalHelper trashExternalHelper) {
        trashExternalHelper.done();
        trashExternalHelper.dump(TrashLogType.TRASH_UPDATE, (String) null);
    }

    public Void doInBackground(Void... voidArr) {
        Context context = this.mContext.get();
        if (context == null) {
            Log.e("TrashUpdateTask", "unable to update trash [" + this.mTask + "]. context is null");
            return null;
        }
        Log.d("TrashUpdateTask", "trash update for [" + this.mTask + "]");
        TrashExternalHelper externalHelper = TrashHelperFactory.getExternalHelper(context);
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$trash$support$TrashExternalLogger$Task[this.mTask.ordinal()];
        if (i2 == 1 || i2 == 2) {
            externalHelper.eraseCloudRecord();
        } else if (i2 != 3) {
            Log.w("TrashUpdateTask", "Not matched.");
        } else {
            externalHelper.turnOffTrash();
        }
        done(externalHelper);
        return null;
    }

    public void onPostExecute(Void voidR) {
        try {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
        } catch (IncompatibleClassChangeError e) {
            Log.e("TrashUpdateTask", "unable to broadcast trash data change. " + e.getMessage());
        }
    }
}
