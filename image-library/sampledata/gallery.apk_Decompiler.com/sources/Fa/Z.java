package Fa;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.helper.Troubleshooting;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Z implements DialogInterface.OnClickListener {
    public final /* synthetic */ TroubleshootingFragment d;
    public final /* synthetic */ Troubleshooting.TroubleResolver e;
    public final /* synthetic */ int f;

    public /* synthetic */ Z(TroubleshootingFragment troubleshootingFragment, Troubleshooting.TroubleResolver troubleResolver, int i2) {
        this.d = troubleshootingFragment;
        this.e = troubleResolver;
        this.f = i2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        this.d.lambda$onListItemClicked$3(this.e, this.f, dialogInterface, i2);
    }
}
