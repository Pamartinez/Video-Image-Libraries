package com.samsung.android.gallery.module.publisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataPublisher e;
    public final /* synthetic */ String f;

    public /* synthetic */ T(SearchDataPublisher searchDataPublisher, String str, int i2) {
        this.d = i2;
        this.e = searchDataPublisher;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishCategoryCache$11(this.f);
                return;
            default:
                this.e.lambda$publishCategoriesCache$7(this.f);
                return;
        }
    }
}
