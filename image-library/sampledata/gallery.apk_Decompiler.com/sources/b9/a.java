package b9;

import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageSaveHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.utils.GalleryPreference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ a(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final void run() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 0:
                DeepSkyHelper.sTextExtractionDrawHelperMap.remove(str);
                return;
            case 1:
                CollageSaveHelper.showSuccessToast(str);
                return;
            case 2:
                GalleryPreference.getInstance().saveState("location://variable/currentv1", str);
                return;
            case 3:
                GalleryPreference.getInstance().saveState("location://variable/currentv1", str);
                return;
            default:
                PeopleDataManager.removeCustomRelationship(str);
                return;
        }
    }
}
