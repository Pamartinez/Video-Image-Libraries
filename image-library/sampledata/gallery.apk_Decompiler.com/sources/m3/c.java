package M3;

import android.os.Bundle;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate;
import com.samsung.android.gallery.app.controller.externals.StartLightRoomCmd;
import com.samsung.android.gallery.app.controller.externals.StartMeituCmd;
import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ EventContext e;

    public /* synthetic */ c(EventContext eventContext, int i2) {
        this.d = i2;
        this.e = eventContext;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        EventContext eventContext = this.e;
        switch (i2) {
            case 0:
                SesPickerDelegate.lambda$handleOnActivityResult$0(eventContext, (Bundle) obj);
                return;
            case 1:
                new StartLightRoomCmd().execute(eventContext, eventContext.getCurrentItem(), Boolean.FALSE);
                return;
            case 2:
                new StartMeituCmd().execute(eventContext, eventContext.getCurrentItem());
                return;
            case 3:
                ((MediaData) obj).reopen(eventContext.getLocationKey());
                return;
            default:
                ((MediaData) obj).reopen(eventContext.getLocationKey());
                return;
        }
    }
}
