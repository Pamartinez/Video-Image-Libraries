package androidx.core.content;

import android.content.Context;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.ObjectsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PermissionChecker {
    public static int checkPermission(Context context, String str, int i2, int i7, String str2) {
        int i8;
        if (context.checkPermission(str, i2, i7) == -1) {
            return -1;
        }
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i7);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (myUid != i7 || !ObjectsCompat.equals(packageName, str2)) {
            i8 = AppOpsManagerCompat.noteProxyOpNoThrow(context, permissionToOp, str2);
        } else {
            i8 = AppOpsManagerCompat.checkOrNoteProxyOp(context, i7, permissionToOp, str2);
        }
        if (i8 == 0) {
            return 0;
        }
        return -2;
    }

    public static int checkSelfPermission(Context context, String str) {
        return checkPermission(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
