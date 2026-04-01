package androidx.appcompat.widget;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TooltipCompatHandler e;

    public /* synthetic */ b(TooltipCompatHandler tooltipCompatHandler, int i2) {
        this.d = i2;
        this.e = tooltipCompatHandler;
    }

    public final void run() {
        int i2 = this.d;
        TooltipCompatHandler tooltipCompatHandler = this.e;
        switch (i2) {
            case 0:
                tooltipCompatHandler.lambda$new$0();
                return;
            default:
                tooltipCompatHandler.hide();
                return;
        }
    }
}
