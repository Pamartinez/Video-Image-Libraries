package androidx.activity;

import androidx.activity.ComponentActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((ComponentActivity.ReportFullyDrawnExecutorApi16Impl) obj).lambda$execute$0();
                return;
            case 1:
                ((ComponentActivity) obj).invalidateMenu();
                return;
            case 2:
                ComponentDialog.onBackPressedDispatcher$lambda$1((ComponentDialog) obj);
                return;
            default:
                FullyDrawnReporter.reportRunnable$lambda$2((FullyDrawnReporter) obj);
                return;
        }
    }
}
