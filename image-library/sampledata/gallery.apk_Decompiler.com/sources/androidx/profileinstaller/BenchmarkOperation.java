package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import androidx.profileinstaller.ProfileInstallReceiver;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BenchmarkOperation {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21ContextHelper {
        public static File getCodeCacheDir(Context context) {
            return context.getCodeCacheDir();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24ContextHelper {
        public static Context createDeviceProtectedStorageContext(Context context) {
            return context.createDeviceProtectedStorageContext();
        }
    }

    public static boolean deleteFilesRecursively(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            boolean z = true;
            for (File deleteFilesRecursively : listFiles) {
                if (!deleteFilesRecursively(deleteFilesRecursively) || !z) {
                    z = false;
                } else {
                    z = true;
                }
            }
            return z;
        }
        file.delete();
        return true;
    }

    public static void dropShaderCache(Context context, ProfileInstallReceiver.ResultDiagnostics resultDiagnostics) {
        File file;
        if (Build.VERSION.SDK_INT >= 34) {
            file = Api24ContextHelper.createDeviceProtectedStorageContext(context).getCacheDir();
        } else {
            file = Api21ContextHelper.getCodeCacheDir(Api24ContextHelper.createDeviceProtectedStorageContext(context));
        }
        if (deleteFilesRecursively(file)) {
            resultDiagnostics.onResultReceived(14, (Object) null);
        } else {
            resultDiagnostics.onResultReceived(15, (Object) null);
        }
    }
}
