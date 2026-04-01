package c2;

import com.google.android.material.chip.SeslExpandableContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SeslExpandableContainer e;

    public /* synthetic */ q(SeslExpandableContainer seslExpandableContainer, int i2) {
        this.d = i2;
        this.e = seslExpandableContainer;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SeslExpandableContainer seslExpandableContainer = this.e;
                seslExpandableContainer.f.setExpanded(seslExpandableContainer.f1446h);
                return;
            default:
                SeslExpandableContainer seslExpandableContainer2 = this.e;
                seslExpandableContainer2.f.setExpanded(seslExpandableContainer2.f1446h);
                return;
        }
    }
}
