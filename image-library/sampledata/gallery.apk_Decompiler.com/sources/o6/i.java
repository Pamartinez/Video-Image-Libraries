package o6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomDecoViewDelegate e;

    public /* synthetic */ i(BottomDecoViewDelegate bottomDecoViewDelegate, int i2) {
        this.d = i2;
        this.e = bottomDecoViewDelegate;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        BottomDecoViewDelegate bottomDecoViewDelegate = this.e;
        switch (i2) {
            case 0:
                bottomDecoViewDelegate.onPlayButtonClicked(view);
                return;
            case 1:
                bottomDecoViewDelegate.onFilterButtonClicked(view);
                return;
            case 2:
                bottomDecoViewDelegate.onBgmButtonClicked(view);
                return;
            default:
                bottomDecoViewDelegate.onAudioMuteClicked(view);
                return;
        }
    }
}
