package B8;

import com.samsung.android.gallery.module.album.AutoAlbumSettingData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AutoAlbumSettingData e;

    public /* synthetic */ c(AutoAlbumSettingData autoAlbumSettingData, int i2) {
        this.d = i2;
        this.e = autoAlbumSettingData;
    }

    public final void run() {
        int i2 = this.d;
        AutoAlbumSettingData autoAlbumSettingData = this.e;
        switch (i2) {
            case 0:
                autoAlbumSettingData.loadAutoUpdatingEnabled();
                return;
            case 1:
                autoAlbumSettingData.loadSubscribeCreatureList();
                return;
            default:
                autoAlbumSettingData.loadSuggestedContentsRuleType();
                return;
        }
    }
}
