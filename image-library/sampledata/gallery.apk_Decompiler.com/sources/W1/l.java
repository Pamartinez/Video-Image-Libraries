package w1;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Intent e;
    public final /* synthetic */ Object f;

    public /* synthetic */ l(int i2, Intent intent, Object obj) {
        this.d = i2;
        this.e = intent;
        this.f = obj;
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [v1.e, java.lang.Object] */
    public final void a() {
        switch (this.d) {
            case 0:
                Intent intent = this.e;
                if (intent != null) {
                    ((GoogleApiActivity) this.f).startActivityForResult(intent, 2);
                    return;
                }
                return;
            default:
                Intent intent2 = this.e;
                if (intent2 != null) {
                    this.f.a(intent2, 2);
                    return;
                }
                return;
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        try {
            a();
        } catch (ActivityNotFoundException e7) {
            String str = "Failed to start resolution intent.";
            if (true == Build.FINGERPRINT.contains("generic")) {
                str = "Failed to start resolution intent. This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.";
            }
            Log.e("DialogRedirect", str, e7);
        } catch (Throwable th) {
            dialogInterface.dismiss();
            throw th;
        }
        dialogInterface.dismiss();
    }
}
