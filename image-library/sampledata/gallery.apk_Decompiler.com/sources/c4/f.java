package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsBaseFragment e;

    public /* synthetic */ f(AlbumsBaseFragment albumsBaseFragment, int i2) {
        this.d = i2;
        this.e = albumsBaseFragment;
    }

    public final void run() {
        int i2 = this.d;
        AlbumsBaseFragment albumsBaseFragment = this.e;
        switch (i2) {
            case 0:
                albumsBaseFragment.lambda$onNewItemCreated$3();
                return;
            default:
                albumsBaseFragment.lambda$onPostViewCreated$4();
                return;
        }
    }
}
