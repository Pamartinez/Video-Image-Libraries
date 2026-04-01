package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.profileinstaller.ProfileInstaller;
import j.C0217b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileInstallReceiver extends BroadcastReceiver {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ResultDiagnostics implements ProfileInstaller.DiagnosticsCallback {
        public ResultDiagnostics() {
        }

        public void onDiagnosticReceived(int i2, Object obj) {
            ProfileInstaller.LOG_DIAGNOSTICS.onDiagnosticReceived(i2, obj);
        }

        public void onResultReceived(int i2, Object obj) {
            ProfileInstaller.LOG_DIAGNOSTICS.onResultReceived(i2, obj);
            ProfileInstallReceiver.this.setResultCode(i2);
        }
    }

    public static void saveProfile(ProfileInstaller.DiagnosticsCallback diagnosticsCallback) {
        Process.sendSignal(Process.myPid(), 10);
        diagnosticsCallback.onResultReceived(12, (Object) null);
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent != null) {
            String action = intent.getAction();
            if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
                ProfileInstaller.writeProfile(context, new C0217b(0), new ResultDiagnostics(), true);
            } else if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
                Bundle extras2 = intent.getExtras();
                if (extras2 != null) {
                    String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                    if ("WRITE_SKIP_FILE".equals(string)) {
                        ProfileInstaller.writeSkipFile(context, new C0217b(0), new ResultDiagnostics());
                    } else if ("DELETE_SKIP_FILE".equals(string)) {
                        ProfileInstaller.deleteSkipFile(context, new C0217b(0), new ResultDiagnostics());
                    }
                }
            } else if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
                saveProfile(new ResultDiagnostics());
            } else if ("androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) && (extras = intent.getExtras()) != null) {
                String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
                ResultDiagnostics resultDiagnostics = new ResultDiagnostics();
                if ("DROP_SHADER_CACHE".equals(string2)) {
                    BenchmarkOperation.dropShaderCache(context, resultDiagnostics);
                } else {
                    resultDiagnostics.onResultReceived(16, (Object) null);
                }
            }
        }
    }
}
