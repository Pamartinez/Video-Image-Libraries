package A4;

import android.content.Intent;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate;
import com.samsung.android.gallery.app.controller.externals.PlayGenericVideoCmd;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewPresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupShotItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.module.bgm.bgmlist.story.BgmListV70;
import com.samsung.android.gallery.module.c2pa.C2paInfo;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.AlbumDataHelper;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.module.settings.SettingPrefApi;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import com.samsung.android.gallery.settings.helper.KnoxRestrictions;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchAnimInfo;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import com.samsung.android.gallery.widget.listview.pinch.v3.RelativeRange;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.PeopleDataHelper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ A(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Blackboard) obj).publish((String) this.e, this.f);
                return;
            case 1:
                RemasterDetector.lambda$unmarshalAnalyzedTag$1((JSONObject) this.e, (ArrayList) this.f, (String) obj);
                return;
            case 2:
                ((SearchRelationshipPreviewFragment) this.e).lambda$onFaceDataLoaded$3((PdcSearchDelegate) this.f, (SearchRelationshipPreviewPresenter) obj);
                return;
            case 3:
                ((MotionPhotoViewPlayer) this.e).lambda$computeVideo$1((Consumer) this.f, (MediaHelper.VideoInfo) obj);
                return;
            case 4:
                ViewNavigatorController.lambda$getUriBuilderWithArgs$0((UriBuilder) this.e, (Bundle) this.f, (String) obj);
                return;
            case 5:
                ((AlbumsBasePinchAnimationManager) this.e).lambda$prepareChildViewAnim$2((PinchItem) this.f, (ListViewHolder) obj);
                return;
            case 6:
                ((AlbumsBasePresenter) this.e).lambda$handleEvent$2((EventMessage) this.f, (AlbumsBaseViewAdapter) obj);
                return;
            case 7:
                ((GroupShotItemLoader) this.e).lambda$getCursor$2((MediaItem) this.f, (QueryParams) obj);
                return;
            case 8:
                ((PanoramaActivity) this.e).lambda$onCreate$3((MediaItem) this.f, (Bitmap) obj);
                return;
            case 9:
                ((LocationPickerActivity) this.e).lambda$startAddressTaskForMarker$4((ISimpleMarker) this.f, (AddressCompat) obj);
                return;
            case 10:
                ((FloatingRecommendationDelegateV2) this.e).lambda$updateData$2((ArrayList) this.f, (FloatingItemDelegate) obj);
                return;
            case 11:
                ((KnoxRestrictions) this.e).lambda$new$0((Bundle) this.f, (String) obj);
                return;
            case 12:
                ((BaseSelectedCommand) this.e).lambda$onPreExecute$0((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 13:
                BasePreferenceFragment.lambda$setTwoStatePreference$12((SettingPrefApi) this.e, (Preference.OnPreferenceChangeListener) this.f, (TwoStatePreference) obj);
                return;
            case 14:
                LabsDevManageFragment.lambda$printSignature$52((StringBuilder) this.f, (String) this.e, (Signature) obj);
                return;
            case 15:
                ((AdvancedMouseSelectionHandler) this.e).lambda$handleMultipleItemSelection$3((AdvancedMouseFocusManager) this.f, (Integer) obj);
                return;
            case 16:
                ChangeAlbumCoverCmd.lambda$updateCropRectInfoFromCoverResult$1((MediaItem) this.e, (AlbumDataHelper.CropRectInfo) this.f, (String) obj);
                return;
            case 17:
                ((FileOpCmd) this.f).lambda$startAddToAlbumDialogCmd$6((String) this.e, (ArrayList) obj);
                return;
            case 18:
                ((FileOpCmd) this.e).lambda$startAddToAlbumDialogCmd$4((MediaItem) this.f, (ArrayList) obj);
                return;
            case 19:
                ((MediaViewPlayerHandler) this.e).lambda$handleVideoBackupInfo$18((MediaItem) this.f, (Bitmap) obj);
                return;
            case 20:
                ((BgmListV70) this.e).lambda$replaceHappy$0((LinkedHashMap) this.f, (String) obj);
                return;
            case 21:
                MyQueryUtil.lambda$mergeTop5List$3((ArrayList) this.e, (Integer[]) this.f, (String) obj);
                return;
            case 22:
                MyQueryUtil.lambda$mergeTop5List$4((ArrayList) this.f, (String) this.e, (Integer) obj);
                return;
            case 23:
                SearchMyQuery.lambda$delete$2((String) this.e, (String[]) this.f, (SQLiteDatabase) obj);
                return;
            case 24:
                ((PinchItemBuilder) this.e).lambda$calculateExtraSpace$1((RelativeRange) this.f, (PinchAnimInfo) obj);
                return;
            case 25:
                ((C2paWrapper) this.e).lambda$extract$1((C2paInfo) this.f, (Consumer) obj);
                return;
            case 26:
                ((PeopleDataHelper) this.e).lambda$loadPeopleData$2((MediaItem) this.f, (ArrayList) obj);
                return;
            case 27:
                SesPickerDelegate.lambda$onConfirmed$2((ArrayList) this.e, (EventContext) this.f, (Blackboard) obj);
                return;
            case 28:
                ((SharingAlbumChoicePresenter) this.e).lambda$addToSharedAlbum$1((MediaItem) this.f, obj);
                return;
            default:
                ((PlayGenericVideoCmd) this.e).lambda$addExtraForDynamicViewInfo$1((Intent) this.f, obj);
                return;
        }
    }

    public /* synthetic */ A(int i2, Object obj, String str) {
        this.d = i2;
        this.f = obj;
        this.e = str;
    }
}
