package M9;

import android.os.Bundle;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesDataPublisher e;

    public /* synthetic */ m(StoriesDataPublisher storiesDataPublisher, int i2) {
        this.d = i2;
        this.e = storiesDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoriesDataPublisher storiesDataPublisher = this.e;
        switch (i2) {
            case 0:
                storiesDataPublisher.publishStoriesData(obj, bundle);
                return;
            case 1:
                storiesDataPublisher.publishStoriesFavoriteData(obj, bundle);
                return;
            case 2:
                storiesDataPublisher.publishStoryAlbumFileData(obj, bundle);
                return;
            case 3:
                storiesDataPublisher.publishStoryHighlightData(obj, bundle);
                return;
            case 4:
                storiesDataPublisher.publishStoryHighlightListData(obj, bundle);
                return;
            case 5:
                storiesDataPublisher.publishStoryHideRuleData(obj, bundle);
                return;
            default:
                storiesDataPublisher.publishHideSceneSelectionData(obj, bundle);
                return;
        }
    }
}
