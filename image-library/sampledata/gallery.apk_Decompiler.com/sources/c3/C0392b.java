package C3;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.ui.dialog.CreateSharedAlbumDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.util.ActionSuggester;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.support.type.ExpressionsType;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat;
import com.samsung.android.sum.core.DebugUtils;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: C3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0392b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2271a;
    public final /* synthetic */ String b;

    public /* synthetic */ C0392b(String str, int i2) {
        this.f2271a = i2;
        this.b = str;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2271a;
        String str = this.b;
        switch (i2) {
            case 0:
                return ((String) obj).startsWith(str);
            case 1:
                return TextUtils.equals((String) obj, str);
            case 2:
                return TextUtils.equals(str, (String) obj);
            case 3:
                return TextUtils.equals(str, (String) obj);
            case 4:
                return TextUtils.equals((String) obj, str);
            case 5:
                return TextUtils.equals((String) obj, str);
            case 6:
                return ((ActionSuggester) obj).getActionId().equals(str);
            case 7:
                return str.equals(((MediaItem) obj).getTitle().toLowerCase());
            case 8:
                return TextUtils.equals((CharSequence) ((Map.Entry) obj).getValue(), str);
            case 9:
                return FixDateTimeCmd.lambda$getGroupItems$10(str, (MediaItem) obj);
            case 10:
                return ((ViewerMenuItem) obj).isValidLocation(str);
            case 11:
                return str.equals(((MediaItem) obj).getPath());
            case 12:
                return str.equals(MediaItemMde.getItemId((MediaItem) obj));
            case 13:
                return str.contains((String) obj);
            case 14:
                return TextUtils.equals(str, (String) obj);
            case 15:
                return ((String) obj).equalsIgnoreCase(str.toLowerCase());
            case 16:
                return ((String) obj).equalsIgnoreCase(str.toLowerCase());
            case 17:
                return ((String) obj).equals(str);
            case 18:
                return Objects.equals(((MotionPhotoFormat) obj).version, str);
            case 19:
                return ((DebugUtils.SumDebugLevel) obj).getLevelName().startsWith(str.toLowerCase());
            case 20:
                return ((String) ((Map.Entry) obj).getKey()).equals(str);
            case 21:
                return ((SettingPreference) obj).key.equals(str);
            case 22:
                return SuggestedCreatureSelectPresenter.lambda$setAsRelation$2(str, (MediaItem) obj);
            case 23:
                return ((String) obj).equals(str);
            case 24:
                return CreateSharedAlbumDialog.lambda$getDefaultName$0(str, (MediaItem) obj);
            case 25:
                return ((CreatureNameData) obj).hasSameName(str);
            case 26:
                return ((CreatureNameData) obj).hasSameName(str);
            case 27:
                return YearThumbnailLoader.lambda$deleteYearData$3(str, (File) obj);
            case 28:
                return ((ExpressionsType) obj).getSubCategory().equals(str);
            default:
                return ((String) obj).equalsIgnoreCase(str);
        }
    }
}
