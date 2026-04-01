package I3;

import com.samsung.android.gallery.app.controller.creature.PersonUnlinkCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDbQueryDataLoader;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ i(ArrayList arrayList, ArrayList arrayList2, int i2) {
        this.d = i2;
        this.e = arrayList;
        this.f = arrayList2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                PersonUnlinkCmd.lambda$onExecute$0(this.e, this.f, (MediaItem) obj);
                return;
            case 1:
                RequestCreateStory.lambda$createStoryAfterScan$6(this.e, this.f, (ContentDownloadResult.DownloadedContent) obj);
                return;
            case 2:
                DetailsDbQueryDataLoader.lambda$setPeopleAndPets$1(this.e, this.f, (MediaItem) obj);
                return;
            default:
                CreatureHidePresenter.lambda$fillCreatureList$8(this.e, this.f, (Map.Entry) obj);
                return;
        }
    }
}
