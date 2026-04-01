package r6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveWithReplayPage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveWithReplayPage e;

    public /* synthetic */ i(SaveWithReplayPage saveWithReplayPage, int i2) {
        this.d = i2;
        this.e = saveWithReplayPage;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SaveWithReplayPage saveWithReplayPage = this.e;
        switch (i2) {
            case 0:
                saveWithReplayPage.onSaveAsClicked(view);
                return;
            default:
                saveWithReplayPage.onReplayClicked(view);
                return;
        }
    }
}
