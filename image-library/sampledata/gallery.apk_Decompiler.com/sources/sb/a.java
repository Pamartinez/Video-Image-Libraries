package sb;

import com.samsung.android.gallery.widget.details.DetailsListAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsListAdapter e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(DetailsListAdapter detailsListAdapter, int i2, int i7) {
        this.d = i7;
        this.e = detailsListAdapter;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$notifyItemDeleted$1(this.f);
                return;
            default:
                this.e.lambda$notifyItemAdded$0(this.f);
                return;
        }
    }
}
