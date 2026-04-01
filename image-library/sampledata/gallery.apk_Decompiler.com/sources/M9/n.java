package M9;

import android.database.Cursor;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisherV2;
import com.samsung.android.gallery.module.search.engine.SearchEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;

    public /* synthetic */ n(Cursor[] cursorArr, int i2) {
        this.d = i2;
        this.e = cursorArr;
    }

    public final void run() {
        int i2 = this.d;
        Cursor[] cursorArr = this.e;
        switch (i2) {
            case 0:
                StoriesDataPublisher.lambda$publishStoryHideRuleData$4(cursorArr);
                return;
            case 1:
                StoriesDataPublisher.lambda$publishStoryHideRuleData$5(cursorArr);
                return;
            case 2:
                StoriesDataPublisher.lambda$publishStoryHideRuleData$6(cursorArr);
                return;
            case 3:
                StoriesDataPublisher.lambda$publishHideSceneSelectionData$8(cursorArr);
                return;
            case 4:
                StoriesDataPublisher.lambda$publishHideSceneSelectionData$9(cursorArr);
                return;
            case 5:
                StoriesDataPublisher.lambda$publishHideSceneSelectionData$10(cursorArr);
                return;
            case 6:
                StoriesDataPublisherV2.lambda$publishHideSceneSelectionData$3(cursorArr);
                return;
            case 7:
                StoriesDataPublisherV2.lambda$publishHideSceneSelectionData$4(cursorArr);
                return;
            case 8:
                StoriesDataPublisherV2.lambda$publishStoryHideRuleData$0(cursorArr);
                return;
            case 9:
                StoriesDataPublisherV2.lambda$publishStoryHideRuleData$1(cursorArr);
                return;
            case 10:
                SearchEngine.lambda$searchForTimeline$3(cursorArr);
                return;
            default:
                SearchEngine.lambda$searchForTimeline$6(cursorArr);
                return;
        }
    }
}
