package J5;

import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchToolbar e;

    public /* synthetic */ a(SearchToolbar searchToolbar, int i2) {
        this.d = i2;
        this.e = searchToolbar;
    }

    public final void run() {
        int i2 = this.d;
        SearchToolbar searchToolbar = this.e;
        switch (i2) {
            case 0:
                searchToolbar.getView().setVisibility(8);
                return;
            case 1:
                searchToolbar.getView().setVisibility(0);
                return;
            case 2:
                searchToolbar.setVisibility(8);
                return;
            case 3:
                searchToolbar.clearFocus();
                return;
            case 4:
                searchToolbar.startVoiceRecognitionActivity();
                return;
            case 5:
                searchToolbar.lambda$new$11();
                return;
            case 6:
                searchToolbar.lambda$new$12();
                return;
            case 7:
                searchToolbar.lambda$setImeVisibility$10();
                return;
            default:
                searchToolbar.clickVoiceButton();
                return;
        }
    }
}
