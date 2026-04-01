package D9;

import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupResult;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.result.DownloadImageResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements GroupApi.GroupResultCallback, ShareApi.ShareSyncResultCallback, ShareApi.ShareBaseResultCallback, ShareApi.SharedItemResultCallback, ShareApi.SharedItemSyncResultCallback, ShareApi.SpaceResultCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ BiConsumer e;

    public /* synthetic */ c(BiConsumer biConsumer, int i2) {
        this.d = i2;
        this.e = biConsumer;
    }

    public void onResult(BooleanResult booleanResult) {
        int i2 = this.d;
        BiConsumer biConsumer = this.e;
        switch (i2) {
            case 2:
                biConsumer.accept(Integer.valueOf(booleanResult.getStatus().getCode()), booleanResult.getStatus().getMessage());
                return;
            default:
                biConsumer.accept(Integer.valueOf(booleanResult.getStatus().getCode()), booleanResult.getStatus().getMessage());
                return;
        }
    }

    public void onResult(SharedItemResult sharedItemResult) {
        MdeSharingHelper.lambda$requestShareItem$1(this.e, sharedItemResult);
    }

    public void onResult(SpaceResult spaceResult) {
        MdeSharingHelper.lambda$requestShare$3(this.e, spaceResult);
    }

    public void onResult(Object obj) {
        int i2 = this.d;
        BiConsumer biConsumer = this.e;
        switch (i2) {
            case 0:
                MdeGroupHelper.lambda$requestFamilyGroupCreation$3(biConsumer, (GroupResult) obj);
                return;
            case 1:
                MdeGroupHelper.lambda$requestLocalGroupCreation$5(biConsumer, (GroupInvitationResult) obj);
                return;
            case 3:
                MdeSharingHelper.lambda$requestThumbnailDownload$11(biConsumer, (DownloadImageResult) obj);
                return;
            case 5:
                biConsumer.accept(((ItemListResult) obj).getFailureList(), Integer.valueOf(((ItemListResult) obj).getStatus().getCode()));
                return;
            case 6:
                biConsumer.accept(((ItemListResult) obj).getFailureList(), Integer.valueOf(((ItemListResult) obj).getStatus().getCode()));
                return;
            case 7:
                biConsumer.accept(((ItemListResult) obj).getFailureList(), Integer.valueOf(((ItemListResult) obj).getStatus().getCode()));
                return;
            default:
                biConsumer.accept(((ItemListResult) obj).getFailureList(), Integer.valueOf(((ItemListResult) obj).getStatus().getCode()));
                return;
        }
    }
}
