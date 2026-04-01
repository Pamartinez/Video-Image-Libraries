package A4;

import android.view.MenuItem;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropMenuDelegate;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.lottie.recap.data.parser.RecapDataParserImplV0;
import com.samsung.android.gallery.module.media.VideoConversionHelper;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ B(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 0:
                ((Blackboard) obj).erase(str);
                return;
            case 1:
                ((QueryParams) obj).setFileIds(str);
                return;
            case 2:
                ((CollapsingToolbarLayout) obj).setTitle(str);
                return;
            case 3:
                ((Preference) obj).setSummary((CharSequence) str);
                return;
            case 4:
                LabsDevManageFragment.lambda$addCategoryStatus$41(str, (GalleryPreference) obj);
                return;
            case 5:
                ((Preference) obj).setSummary((CharSequence) str);
                return;
            case 6:
                ((Preference) obj).setSummary((CharSequence) str);
                return;
            case 7:
                ((Preference) obj).setSummary((CharSequence) str);
                return;
            case 8:
                ((Preference) obj).setSummary((CharSequence) "operation : " + str);
                return;
            case 9:
                CropMenuDelegate.lambda$setNavigationMenuTitle$1(str, (MenuItem) obj);
                return;
            case 10:
                ((AlbumPicturesPresenter) obj).setLocationKeyOnly(str);
                return;
            case 11:
                ((ScreenShotFilterManager) obj).setFilteredScene(str);
                return;
            case 12:
                ((TextView) obj).setText(str);
                return;
            case 13:
                ((TextView) obj).setText(str);
                return;
            case 14:
                ((QueryParams) obj).setDataLike(str);
                return;
            case 15:
                ((QueryParams) obj).setSubCategory(str);
                return;
            case 16:
                ((QueryParams) obj).mRecommendedIds = str;
                return;
            case 17:
                ((QueryParams) obj).setFilePath(str);
                return;
            case 18:
                ((QueryParams) obj).setSubCategory(str);
                return;
            case 19:
                ((QueryParams) obj).mRecommendedIds = str;
                return;
            case 20:
                ((QueryParams) obj).mRecommendedIds = str;
                return;
            case 21:
                AlbumHelper.getInstance().addNewEmptyAlbum(str, FileUtils.getNameFromPath(str), ((Object[]) obj)[0].intValue(), ((Object[]) obj)[1]);
                return;
            case 22:
                ((QueryParams) obj).setFilePath(str);
                return;
            case 23:
                ((QueryParams) obj).setFilePath(str);
                return;
            case 24:
                ((VideoConversionHelper) obj).savePath(str);
                return;
            case 25:
                ((QueryParams) obj).setFilePath(str);
                return;
            case 26:
                ((QueryParams) obj).mRecommendedIds = str;
                return;
            case 27:
                RecapDataParserImplV0.lambda$loadImageCandidates$1(str, (QueryParams) obj);
                return;
            default:
                ((QueryParams) obj).setSubCategory(str);
                return;
        }
    }
}
