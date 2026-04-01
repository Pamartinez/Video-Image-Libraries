package F3;

import android.content.Context;
import android.widget.Toast;
import com.samsung.android.gallery.module.cloud.scpm.ScpmManager;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;
    public final /* synthetic */ String f;

    public /* synthetic */ b(Context context, String str, int i2) {
        this.d = i2;
        this.e = context;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                Toast.makeText(this.e, this.f, 0).show();
                return;
            case 1:
                Toast.makeText(this.e, this.f, 0).show();
                return;
            case 2:
                ScpmManager.getInstance().update(this.e, this.f);
                return;
            case 3:
                Utils.showToast(this.e, this.f);
                return;
            case 4:
                Toast.makeText(this.e, this.f, 1).show();
                return;
            default:
                Toast.makeText(this.e, this.f, 0).show();
                return;
        }
    }
}
