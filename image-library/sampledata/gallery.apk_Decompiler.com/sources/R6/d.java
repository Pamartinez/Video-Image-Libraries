package r6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RecapCoverPage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecapCoverPage e;

    public /* synthetic */ d(RecapCoverPage recapCoverPage, int i2) {
        this.d = i2;
        this.e = recapCoverPage;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        RecapCoverPage recapCoverPage = this.e;
        switch (i2) {
            case 0:
                recapCoverPage.onReplayClicked(view);
                return;
            default:
                recapCoverPage.onSaveAsClicked(view);
                return;
        }
    }
}
