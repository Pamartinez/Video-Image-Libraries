package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.activity.preload.DataRequesterFactory;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.picker.MultiPickFragment;
import com.samsung.android.gallery.app.ui.container.picker.MultiPickPicturesFragment;
import com.samsung.android.gallery.app.ui.container.picker.PickerConFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumFolderPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.AllAlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.MxAlbumFolderPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.MxAlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.creature.CreaturePickFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.suggestion.SuggestionContainerPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.stories.StoriesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.stories.StoriesPickerFragmentV2;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PickerViewNavigatorController extends ViewNavigatorController {
    private final LaunchModeType mLaunchModeType = PickerUtil.getPickerLaunchMode(this.mBlackboard);

    public PickerViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    private MvpBaseFragment createPickerContainer() {
        return new PickerConFragment();
    }

    private String getAlbumPickLocationKey(String str) {
        String str2;
        String appendPickerArgs = PickerUtil.appendPickerArgs(this.mBlackboard, str);
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return appendPickerArgs;
        }
        if (isIncludeVirtualAlbum()) {
            str2 = Integer.toString(AlbumType.AutoUpdated.toInt());
        } else {
            str2 = AlbumType.Virtual.toInt() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + AlbumType.AutoUpdated.toInt();
        }
        return ArgumentsUtil.appendArgs(appendPickerArgs, "disabledAlbumType", "" + str2);
    }

    private String getAlbumPicturesPickerLocationKey() {
        String str;
        String str2;
        LaunchIntent launchIntent = getLaunchIntent();
        int albumBucketId = launchIntent.getAlbumBucketId();
        if (!BucketUtils.isVirtualAlbum(albumBucketId)) {
            str = "location://albums/fileList";
        } else if (albumBucketId == BucketUtils.VirtualBucketHolder.video) {
            str = "location://virtual/album/video/fileList";
        } else if (albumBucketId == BucketUtils.VirtualBucketHolder.favorite) {
            str = "location://virtual/album/favorite/fileList";
        } else {
            str = "location://virtual/album/recently/fileList";
        }
        UriBuilder uriBuilder = new UriBuilder(str);
        if (albumBucketId == 0) {
            str2 = "";
        } else {
            str2 = String.valueOf(albumBucketId);
        }
        return uriBuilder.appendArg("id", str2).appendArg("ids", launchIntent.getAlbumBucketIds()).appendArg("support_clipboard", PickerUtil.supportClipboard(this.mBlackboard)).appendArg("type", launchIntent.getAlbumType()).build();
    }

    private String getCoverItemPickerLocationKey() {
        LaunchIntent launchIntent = getLaunchIntent();
        return CoverPickType.getType(launchIntent.getCoverPickType()).getLocationKey(launchIntent);
    }

    private String getFragmentTag(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -125579287:
                if (str.equals("location://albums")) {
                    c5 = 0;
                    break;
                }
                break;
            case 263612166:
                if (str.equals("location://timeline")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1344752317:
                if (str.equals("location://folder/choice")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (isAlbumPick()) {
                    return "AlbumsPickerFragment";
                }
                return "BottomTabPickerFragment";
            case 1:
                return "BottomTabPickerFragment";
            case 2:
                return "AlbumFolderPickerFragment";
            default:
                return str;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getPickRequestKey() {
        /*
            r6 = this;
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r6.getLaunchIntent()
            boolean r1 = r6.isAlbumPick()
            java.lang.String r2 = "location://albums"
            if (r1 == 0) goto L_0x000e
            goto L_0x00dd
        L_0x000e:
            boolean r1 = r6.isAlbumMultiPick()
            if (r1 == 0) goto L_0x0018
            java.lang.String r2 = "location://folder/choice"
            goto L_0x00dd
        L_0x0018:
            boolean r1 = r6.isCoverItemPick()
            if (r1 == 0) goto L_0x002e
            boolean r0 = r6.isCoverHistoryItemPick()
            if (r0 == 0) goto L_0x0029
            java.lang.String r0 = "location://albums/coverhistory"
        L_0x0026:
            r2 = r0
            goto L_0x00dd
        L_0x0029:
            java.lang.String r0 = r6.getCoverItemPickerLocationKey()
            goto L_0x0026
        L_0x002e:
            boolean r1 = r6.isStoryPick()
            if (r1 != 0) goto L_0x00db
            boolean r1 = r6.isStoryMultiPick()
            if (r1 == 0) goto L_0x003c
            goto L_0x00db
        L_0x003c:
            boolean r1 = r0.isCustomAlbumPicturesPick()
            if (r1 == 0) goto L_0x0048
            java.lang.String r2 = r6.getAlbumPicturesPickerLocationKey()
            goto L_0x00dd
        L_0x0048:
            java.lang.String r1 = "reference-location"
            boolean r3 = r0.containsKey(r1)
            if (r3 == 0) goto L_0x005b
            java.lang.String r2 = ""
            java.lang.Object r0 = r0.getExtra(r1, r2)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x00dd
        L_0x005b:
            boolean r1 = r0.hasTargetCluster()
            java.lang.String r3 = "location://timeline"
            if (r1 != 0) goto L_0x007b
            boolean r1 = r0.isFromGalleryWidget()
            if (r1 != 0) goto L_0x007b
            com.samsung.android.gallery.support.utils.GalleryPreference r1 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstance()
            java.lang.String r4 = "location://variable/currentv1"
            java.lang.String r1 = r1.loadString((java.lang.String) r4, (java.lang.String) r3)
            boolean r1 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isAlbumsMatch(r1)
            if (r1 == 0) goto L_0x007b
            r1 = r2
            goto L_0x007c
        L_0x007b:
            r1 = r3
        L_0x007c:
            java.lang.String r4 = "BottomTab"
            r5 = 0
            java.lang.Object r0 = r0.getExtra(r4, r5)
            java.lang.String r0 = (java.lang.String) r0
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x00d9
            r0.getClass()
            int r4 = r0.hashCode()
            r5 = -1
            switch(r4) {
                case -1415163932: goto L_0x00b8;
                case -906336856: goto L_0x00ad;
                case -730119371: goto L_0x00a2;
                case 1853891989: goto L_0x0097;
                default: goto L_0x0096;
            }
        L_0x0096:
            goto L_0x00c2
        L_0x0097:
            java.lang.String r4 = "collections"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L_0x00a0
            goto L_0x00c2
        L_0x00a0:
            r5 = 3
            goto L_0x00c2
        L_0x00a2:
            java.lang.String r4 = "pictures"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L_0x00ab
            goto L_0x00c2
        L_0x00ab:
            r5 = 2
            goto L_0x00c2
        L_0x00ad:
            java.lang.String r4 = "search"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L_0x00b6
            goto L_0x00c2
        L_0x00b6:
            r5 = 1
            goto L_0x00c2
        L_0x00b8:
            java.lang.String r4 = "albums"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L_0x00c1
            goto L_0x00c2
        L_0x00c1:
            r5 = 0
        L_0x00c2:
            switch(r5) {
                case 0: goto L_0x00dd;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00d4;
                case 3: goto L_0x00d1;
                default: goto L_0x00c5;
            }
        L_0x00c5:
            java.lang.String r2 = r6.TAG
            java.lang.String r3 = "wrong init tab value="
            java.lang.String r0 = r3.concat(r0)
            com.samsung.android.gallery.support.utils.Log.e(r2, r0)
            goto L_0x00d9
        L_0x00d1:
            java.lang.String r2 = "location://collection"
            goto L_0x00dd
        L_0x00d4:
            r2 = r3
            goto L_0x00dd
        L_0x00d6:
            java.lang.String r2 = "location://search/fileList/KeywordTab"
            goto L_0x00dd
        L_0x00d9:
            r2 = r1
            goto L_0x00dd
        L_0x00db:
            java.lang.String r2 = "location://story/albums"
        L_0x00dd:
            com.samsung.android.gallery.support.blackboard.Blackboard r6 = r6.mBlackboard
            java.lang.String r6 = com.samsung.android.gallery.module.utils.PickerUtil.appendPickerArgs(r6, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.PickerViewNavigatorController.getPickRequestKey():java.lang.String");
    }

    private boolean isAlbumMultiPick() {
        return getLaunchIntent().isAlbumMultiPick();
    }

    private boolean isAlbumPick() {
        if (this.mLaunchModeType == LaunchModeType.ACTION_ALBUM_PICK) {
            return true;
        }
        return false;
    }

    private boolean isCoverHistoryItemPick() {
        return getLaunchIntent().isCoverHistoryItemPick();
    }

    private boolean isCoverItemPick() {
        if (this.mLaunchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return true;
        }
        return false;
    }

    private boolean isIncludeVirtualAlbum() {
        return getLaunchIntent().isIncludeVirtualAlbum();
    }

    private boolean isStoryMultiPick() {
        return getLaunchIntent().isStoryMultiPick();
    }

    private boolean isStoryPick() {
        return getLaunchIntent().isStoryPick();
    }

    private boolean setMvpFragmentForAlbum(String str) {
        String fragmentTag = getFragmentTag(str);
        if (isExistFragment(fragmentTag)) {
            return false;
        }
        if (isAlbumPick()) {
            return setMvpFragmentForAlbumsPicker(getAlbumPickLocationKey(str), fragmentTag);
        }
        if (isAlbumMultiPick()) {
            return setMvpFragmentForAlbumFolderPicker(getAlbumPickLocationKey(str), fragmentTag);
        }
        return setMvpFragment(createPickerContainer(), str, fragmentTag);
    }

    private boolean setMvpFragmentForAlbumFolderPicker(String str, String str2) {
        MvpBaseFragment mvpBaseFragment;
        if (supportMxAlbumsPicker()) {
            mvpBaseFragment = new MxAlbumFolderPickerFragment();
        } else {
            mvpBaseFragment = new AlbumFolderPickerFragment();
        }
        return setMvpFragment(mvpBaseFragment, str, str2);
    }

    private boolean setMvpFragmentForAlbumsPicker(String str, String str2) {
        MvpBaseFragment mvpBaseFragment;
        if (supportMxAlbumsPicker()) {
            mvpBaseFragment = new MxAlbumsPickerFragment();
        } else {
            mvpBaseFragment = new AlbumsPickerFragment();
        }
        return setMvpFragment(mvpBaseFragment, str, str2);
    }

    private boolean setMvpFragmentForStories(String str) {
        MvpBaseFragment mvpBaseFragment;
        String fragmentTag = getFragmentTag(str);
        if (isExistFragment(fragmentTag)) {
            return false;
        }
        if (!isStoryPick() && !isStoryMultiPick()) {
            return false;
        }
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            mvpBaseFragment = new StoriesPickerFragmentV2();
        } else {
            mvpBaseFragment = new StoriesPickerFragment();
        }
        return setMvpFragment(mvpBaseFragment, PickerUtil.appendPickerArgs(this.mBlackboard, str), fragmentTag);
    }

    private boolean supportMxAlbumsPicker() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums) || !PreferenceFeatures.EssentialAlbums.isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean commitFragmentByLocationKey(String str, String str2) {
        str2.getClass();
        char c5 = 65535;
        switch (str2.hashCode()) {
            case -2009053537:
                if (str2.equals("location://search/fileList/KeywordTab")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1327340640:
                if (str2.equals("location://albums/fileList")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1141680973:
                if (str2.equals("location://search/fileList/Category/Location")) {
                    c5 = 2;
                    break;
                }
                break;
            case -984961596:
                if (str2.equals("location://mtp")) {
                    c5 = 3;
                    break;
                }
                break;
            case -968159475:
                if (str2.equals("location://search/fileList/Category/People")) {
                    c5 = 4;
                    break;
                }
                break;
            case -440239236:
                if (str2.equals("location://sharing/albums")) {
                    c5 = 5;
                    break;
                }
                break;
            case -332973319:
                if (str2.equals("location://albums/fileList/mxVirtual/recent")) {
                    c5 = 6;
                    break;
                }
                break;
            case -300413254:
                if (str2.equals("location://albums/fileList/mxVirtual/favorite")) {
                    c5 = 7;
                    break;
                }
                break;
            case -234597023:
                if (str2.equals("location://search/fileList/Category/Pet")) {
                    c5 = 8;
                    break;
                }
                break;
            case -212479357:
                if (str2.equals("location://story/albums")) {
                    c5 = 9;
                    break;
                }
                break;
            case -125579287:
                if (str2.equals("location://albums")) {
                    c5 = 10;
                    break;
                }
                break;
            case 42699107:
                if (str2.equals("location://albums/coverhistory")) {
                    c5 = 11;
                    break;
                }
                break;
            case 263612166:
                if (str2.equals("location://timeline")) {
                    c5 = 12;
                    break;
                }
                break;
            case 505417069:
                if (str2.equals("location://sharing/albums/fileList")) {
                    c5 = 13;
                    break;
                }
                break;
            case 608528445:
                if (str2.equals("location://search/fileList/Category/CreatureMultiPick")) {
                    c5 = 14;
                    break;
                }
                break;
            case 649428972:
                if (str2.equals("location://search/fileList/Recommendation")) {
                    c5 = 15;
                    break;
                }
                break;
            case 1177645495:
                if (str2.equals("location://virtual/album/video/fileList")) {
                    c5 = 16;
                    break;
                }
                break;
            case 1194757598:
                if (str2.equals("location://search/fileList/Category/PeopleAndPets")) {
                    c5 = 17;
                    break;
                }
                break;
            case 1297591864:
                if (str2.equals("location://virtual/album/recently/fileList")) {
                    c5 = 18;
                    break;
                }
                break;
            case 1344752317:
                if (str2.equals("location://folder/choice")) {
                    c5 = 19;
                    break;
                }
                break;
            case 1662845699:
                if (str2.equals("location://stories/hideSceneSelection")) {
                    c5 = 20;
                    break;
                }
                break;
            case 1894681211:
                if (str2.equals("location://albums/all")) {
                    c5 = 21;
                    break;
                }
                break;
            case 1944014916:
                if (str2.equals("location://virtual/album/favorite/fileList")) {
                    c5 = 22;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 3:
            case 5:
            case 12:
                return setMvpFragmentForContainer(str2);
            case 1:
            case 6:
            case 7:
            case 11:
            case 13:
            case 16:
            case 18:
            case 20:
            case 22:
                if (ArgumentsUtil.getArgValue(str, "support_clipboard", false)) {
                    return setMvpFragment(new MultiPickPicturesFragment(), str, str2);
                }
                return setMvpFragment(new PicturesPickerFragment(), str, str2);
            case 2:
            case 4:
            case 8:
            case 17:
                return setMvpFragment(new MultiPickFragment(), str, str2);
            case 9:
                return setMvpFragmentForStories(str2);
            case 10:
            case 19:
                return setMvpFragmentForAlbum(str2);
            case 14:
                return setMvpFragment(new CreaturePickFragment(), str, str2);
            case 15:
                return setMvpFragment(new SuggestionContainerPickerFragment(), str, str2);
            case 21:
                return setMvpFragment(new AllAlbumsPickerFragment(), str, str2);
            default:
                if (!LocationKey.isContentViewer(str2)) {
                    if (LocationKey.isStoryPictures(str2) || LocationKey.isStoryHighlight(str2)) {
                        return setMvpFragment(new PicturesPickerFragment(), str, str2);
                    }
                    if (LocationKey.isFolder(str2)) {
                        return setMvpFragmentForAlbumsPicker(str, str2);
                    }
                    if (LocationKey.isSearchPictures(str2)) {
                        return setMvpFragment(new SearchPicturesPickerFragment(), str, str2);
                    }
                    if (LocationKey.isSearchAutoComplete(str2)) {
                        return setMvpFragment(new SuggestionContainerPickerFragment(), str, str2);
                    }
                }
                return super.commitFragmentByLocationKey(str, str2);
        }
    }

    public DataRequesterFactory.LaunchType getRequesterType() {
        return DataRequesterFactory.LaunchType.PICKER;
    }

    public void handleFinishFragment() {
        IBaseFragment topFragment = this.mActivityView.getTopFragment();
        if (topFragment == null || LocationKey.isContentViewer(((MvpBaseFragment) topFragment).getLocationKey())) {
            super.handleFinishFragment();
        } else {
            topFragment.onBackPressed();
        }
    }

    public void onNavigatorCreated() {
        String pickRequestKey = getPickRequestKey();
        preloadDataCursor(pickRequestKey);
        this.mBlackboard.post("command://MoveURL", pickRequestKey);
    }

    public void preloadDataCursor(String str) {
        if (!getLaunchIntent().hasTargetCluster()) {
            super.preloadDataCursor(str);
        }
    }

    public boolean setMvpFragmentForContainer(String str) {
        String fragmentTag = getFragmentTag(str);
        if (!isExistFragment(fragmentTag)) {
            return setMvpFragment(createPickerContainer(), str, fragmentTag);
        }
        return false;
    }
}
