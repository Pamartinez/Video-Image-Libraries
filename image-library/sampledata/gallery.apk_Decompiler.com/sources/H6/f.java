package H6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaControllerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaControllerDelegate e;

    public /* synthetic */ f(MediaControllerDelegate mediaControllerDelegate, int i2) {
        this.d = i2;
        this.e = mediaControllerDelegate;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        MediaControllerDelegate mediaControllerDelegate = this.e;
        switch (i2) {
            case 0:
                mediaControllerDelegate.onControllerClicked(view);
                return;
            default:
                mediaControllerDelegate.onAudioButtonClicked(view);
                return;
        }
    }
}
