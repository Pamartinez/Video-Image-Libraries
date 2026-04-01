package T3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadContent;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RequestDownloadContent e;

    public /* synthetic */ d(RequestDownloadContent requestDownloadContent, int i2) {
        this.d = i2;
        this.e = requestDownloadContent;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        RequestDownloadContent requestDownloadContent = this.e;
        switch (i2) {
            case 0:
                requestDownloadContent.lambda$getDownloadResultCallback$0((ContentDownloadResult) obj);
                return;
            default:
                requestDownloadContent.lambda$deleteGroupShotData$2((ContentDownloadResult.DownloadedContent) obj);
                return;
        }
    }
}
