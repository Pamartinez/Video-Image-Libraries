package v7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DualShotOptionsViewHandler e;
    public final /* synthetic */ Consumer f;

    public /* synthetic */ l(DualShotOptionsViewHandler dualShotOptionsViewHandler, Consumer consumer, int i2) {
        this.d = i2;
        this.e = dualShotOptionsViewHandler;
        this.f = consumer;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$setOnClickListener$0(this.f, view);
                return;
            default:
                this.e.lambda$setOnClickListener$1(this.f, view);
                return;
        }
    }
}
