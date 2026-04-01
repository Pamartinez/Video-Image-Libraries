package q6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LastPageView e;

    public /* synthetic */ c(LastPageView lastPageView, int i2) {
        this.d = i2;
        this.e = lastPageView;
    }

    public final void run() {
        int i2 = this.d;
        LastPageView lastPageView = this.e;
        switch (i2) {
            case 0:
                lastPageView.lambda$invalidateChild$6();
                return;
            case 1:
                lastPageView.updatePageLayout();
                return;
            case 2:
                lastPageView.onNextPageAlarm();
                return;
            case 3:
                lastPageView.handleConfigurationChange();
                return;
            case 4:
                lastPageView.lambda$show$2();
                return;
            default:
                lastPageView.lambda$restorePageScroll$7();
                return;
        }
    }
}
