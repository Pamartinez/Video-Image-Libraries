package C3;

import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.profileinstaller.ProfileInstaller;
import androidx.profileinstaller.ProfileInstallerInitializer;
import com.samsung.android.gallery.app.activity.GalleryApplication;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyAbnormalTask;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyExpiredTask;
import com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask;
import com.samsung.android.gallery.module.trash.helper.TrashRollbackTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ l(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final void run() {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                GalleryApplication.lambda$initHeavyApiOnBg$2(context);
                return;
            case 1:
                Toast.makeText(context, "Reset changed settings to default in Labs", 0).show();
                return;
            case 2:
                AppCompatDelegate.lambda$syncRequestedAndStoredLocales$1(context);
                return;
            case 3:
                ProfileInstallerInitializer.writeInBackground(context);
                return;
            case 4:
                ProfileInstaller.writeProfile(context);
                return;
            case 5:
                new TrashEmptyAbnormalTask().emptyAbnormalInIdleWorker(context);
                return;
            case 6:
                new TrashEmptyExpiredTask(context, true).doInBackground(new Void[0]);
                return;
            case 7:
                new TrashMpRecoverTask().recoverTrashFiles(context);
                return;
            default:
                TrashRollbackTask.TrashRollbackJob.lambda$execute$0(context);
                return;
        }
    }
}
