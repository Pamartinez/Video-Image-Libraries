package M3;

import android.content.Intent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateFamilySpace;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Intent e;

    public /* synthetic */ d(Intent intent, int i2) {
        this.d = i2;
        this.e = intent;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Intent intent = this.e;
        switch (i2) {
            case 0:
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList) obj);
                return;
            case 1:
                intent.putExtra("sub_state", (String) obj);
                return;
            case 2:
                intent.putExtra("sub_service", (String) obj);
                return;
            default:
                RequestCreateFamilySpace.lambda$composeUrisToAdd$1(intent, (ArrayList) obj);
                return;
        }
    }
}
