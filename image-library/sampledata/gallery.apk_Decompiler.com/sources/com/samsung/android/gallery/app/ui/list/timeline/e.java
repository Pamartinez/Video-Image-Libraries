package com.samsung.android.gallery.app.ui.list.timeline;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TimelinePresenter e;

    public /* synthetic */ e(TimelinePresenter timelinePresenter, int i2) {
        this.d = i2;
        this.e = timelinePresenter;
    }

    public final void run() {
        int i2 = this.d;
        TimelinePresenter timelinePresenter = this.e;
        switch (i2) {
            case 0:
                timelinePresenter.toggleSimilarPhotoMode();
                return;
            default:
                timelinePresenter.lambda$checkSimilarData$2();
                return;
        }
    }
}
