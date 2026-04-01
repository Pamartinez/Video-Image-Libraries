package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.C0384t;
import A4.Z;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.Toast;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.album.CreateAlbumAndMoveCmd;
import com.samsung.android.gallery.app.controller.album.CreateNamedAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateNamedFolderCmd;
import com.samsung.android.gallery.app.controller.album.MoveToGroupCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.remote.StartSlideshowV2Cmd;
import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.app.controller.trash.RestoreTrashCmd;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.bixby.bixby.search.SearchInfo;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListViewServiceBixby {
    private static final ArrayList<String> LIST_OPTIONS = new ArrayList<String>() {
        {
            add("KEY_CREATE_ALBUM");
            add("KEY_CREATE_GROUP");
            add("KEY_CREATE_MOVIE");
            add("KEY_MOVE_TO_ALBUM");
            add("KEY_MOVE_TO_GROUP");
            add("KEY_SIMILAR");
            add("KEY_SIMILAR_STATE");
            add("KEY_TRASH");
            add("KEY_TRASH_IMAGE_COUNT");
            add("KEY_TRASH_VIDEO_COUNT");
            add("KEY_SCREEN");
        }
    };
    private final Blackboard mBlackboard;
    private final BaseListPresenter<?> mPresenter;

    public ListViewServiceBixby(BaseListPresenter<?> baseListPresenter) {
        this.mPresenter = baseListPresenter;
        this.mBlackboard = baseListPresenter.getBlackboard();
    }

    private Pair<ListViewHolder, String> findDateViewHolder(int i2, long j2, long j3, BaseListViewAdapter<?> baseListViewAdapter) {
        boolean z;
        String str;
        ListViewServiceBixby listViewServiceBixby;
        if (j2 == -1 || j3 == -1 || !LocationKey.isPictures(this.mPresenter.getLocationKey())) {
            z = false;
        } else {
            z = true;
        }
        ArrayList<ListViewHolder> allVisibleViewHolders = baseListViewAdapter.getAllVisibleViewHolders();
        if (z) {
            SearchInfo searchInfo = new SearchInfo();
            searchInfo.setTimeInfo(new long[]{j2, j3});
            j2 = searchInfo.getFrom();
            j3 = searchInfo.getTo();
        }
        long j8 = j2;
        long j10 = j3;
        ArrayList arrayList = new ArrayList();
        Iterator<ListViewHolder> it = allVisibleViewHolders.iterator();
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            if (ViewHolderValue.isData(next.getViewType())) {
                if (z) {
                    listViewServiceBixby = this;
                    if (!listViewServiceBixby.isInDateRange(next.getMediaItem(), j8, j10)) {
                    }
                } else {
                    listViewServiceBixby = this;
                }
                arrayList.add(next);
            } else {
                listViewServiceBixby = this;
            }
            this = listViewServiceBixby;
        }
        if (arrayList.size() > i2) {
            return new Pair<>((ListViewHolder) arrayList.get(i2), (Object) null);
        }
        if (!z || !arrayList.isEmpty()) {
            str = "order_num_exceed";
        } else {
            str = "no_content";
        }
        return new Pair<>((Object) null, str);
    }

    private String getAlbumName() {
        MediaItem currentItem;
        if (!isAlbumPictures() || (currentItem = this.mPresenter.getCurrentItem()) == null) {
            return null;
        }
        return AlbumTitleHelper.getAlbumTitle(currentItem.getAlbumID(), (String) Optional.ofNullable(currentItem.getPath()).map(new C0384t(14)).orElse((Object) null));
    }

    private int getCreateMovieState() {
        if (!MenuSupportHelper.supportCreateHighlightReel()) {
            return 202;
        }
        if (isUnsupportedScreen()) {
            return 100;
        }
        if (!isInSelectionMode()) {
            return 101;
        }
        int selectedItemCount = getSelectedItemCount();
        if (selectedItemCount <= 0) {
            return 102;
        }
        if (selectedItemCount > 60) {
            return 200;
        }
        if (!MenuSupportHelper.supportCreateMovie()) {
            return 100;
        }
        if (involveInvalidItem()) {
            return 201;
        }
        return 0;
    }

    private HashMap<String, Object> getInformationList() {
        HashMap<String, Object> hashMap = new HashMap<>();
        for (Map.Entry next : BixbyAppStateUtil.OPTIONS.entrySet()) {
            String str = (String) next.getKey();
            if (LIST_OPTIONS.contains(str)) {
                hashMap.put(str, supportBixbyCommand(str));
            } else {
                hashMap.put(str, next.getValue());
            }
        }
        putListInformation(hashMap);
        return hashMap;
    }

    private int getMoveToAlbumState() {
        boolean isInSelectionMode = isInSelectionMode();
        int selectedItemCount = getSelectedItemCount();
        if (isUnsupportedScreen()) {
            return 100;
        }
        if (isSearchPictures()) {
            if (isInSelectionMode) {
                if (selectedItemCount <= 0) {
                    return 102;
                }
                return 0;
            } else if (this.mPresenter.getDataCount() <= 0) {
                return 100;
            } else {
                return 0;
            }
        } else if (!isInSelectionMode) {
            return 101;
        } else {
            if (selectedItemCount <= 0) {
                return 102;
            }
            if (!hasMoveToAlbumMenuItem()) {
                return 100;
            }
            return 0;
        }
    }

    private int getScreenType() {
        String locationKey = this.mPresenter.getLocationKey();
        if (locationKey == null) {
            return BixbyKey.ScreenType.UNKNOWN.toInt();
        }
        if (LocationKey.isTimelinePictures(locationKey)) {
            return BixbyKey.ScreenType.PICTURES.toInt();
        }
        if (LocationKey.isAlbumPictures(locationKey)) {
            return BixbyKey.ScreenType.ALBUM_PICTURES.toInt();
        }
        if (LocationKey.isAlbums(locationKey)) {
            return BixbyKey.ScreenType.ALBUMS.toInt();
        }
        if (LocationKey.isStoryPictures(locationKey)) {
            return BixbyKey.ScreenType.STORY_PICTURES.toInt();
        }
        if (LocationKey.isStories(locationKey)) {
            return BixbyKey.ScreenType.STORIES.toInt();
        }
        if (!LocationKey.isSearchPictures(locationKey)) {
            return BixbyKey.ScreenType.UNKNOWN.toInt();
        }
        if (this.mPresenter.getDataCount() <= 0) {
            return BixbyKey.ScreenType.SEARCH_NO_RESULT.toInt();
        }
        return BixbyKey.ScreenType.SEARCH_RESULT.toInt();
    }

    private String getSearchKeyword() {
        if (isSearchPictures()) {
            return ArgumentsUtil.getArgValue(this.mPresenter.getLocationKey(), "title");
        }
        return null;
    }

    private int getSelectedItemCount() {
        return this.mPresenter.getFocusedItemCount() + this.mPresenter.getSelectedItemCount();
    }

    private String getShareState() {
        if (isUnsupportedScreen()) {
            return "unsupported";
        }
        if (!isInSelectionMode()) {
            return "not_in_selection";
        }
        if (getSelectedItemCount() <= 0) {
            return "zero_selection";
        }
        return "no_error";
    }

    private int getSimilarState() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode) ? 1 : 0;
    }

    private int getTrashCount(boolean z) {
        BaseListPresenter<?> baseListPresenter = this.mPresenter;
        if (z) {
            return baseListPresenter.getImageCount();
        }
        return baseListPresenter.getVideoCount();
    }

    private void handleAlbumSlideshow() {
        if (!isAlbumPictures()) {
            Log.bxe("ListViewServiceBixby", "unable to handle share via tv. not in album pictures");
        } else {
            new StartSlideshowV2Cmd().execute(this.mPresenter, null);
        }
    }

    private void handleCreateAlbum(Uri uri) {
        if (!isAlbumFolders()) {
            Log.bxe("ListViewServiceBixby", "unable to handle create album. not in albums or folders");
            return;
        }
        CreateNamedAlbumCmd createNamedAlbumCmd = new CreateNamedAlbumCmd();
        BaseListPresenter<?> baseListPresenter = this.mPresenter;
        createNamedAlbumCmd.execute(baseListPresenter, baseListPresenter.getCurrentItem(), uri);
    }

    private void handleCreateGroup(Uri uri) {
        if (!isAlbumFolders()) {
            Log.bxe("ListViewServiceBixby", "unable to handle create group. not in albums or folders");
            return;
        }
        CreateNamedFolderCmd createNamedFolderCmd = new CreateNamedFolderCmd();
        BaseListPresenter<?> baseListPresenter = this.mPresenter;
        createNamedFolderCmd.execute(baseListPresenter, baseListPresenter.getCurrentItem(), uri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleCreateMovie(android.net.Uri r5) {
        /*
            r4 = this;
            boolean r0 = r4.isInSelectionMode()
            java.lang.String r1 = "ListViewServiceBixby"
            if (r0 != 0) goto L_0x000e
            java.lang.String r4 = "unable to handle create movie. not in selection mode"
            com.samsung.android.gallery.support.utils.Log.bxe(r1, r4)
            return
        L_0x000e:
            java.lang.String r0 = "KEY_DURATION"
            java.lang.String r5 = r5.getQueryParameter(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x0035
            long r2 = java.lang.Long.parseLong(r5)     // Catch:{ NumberFormatException -> 0x001f }
            goto L_0x0037
        L_0x001f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "duration converting failed ["
            r0.<init>(r2)
            r0.append(r5)
            java.lang.String r5 = "]"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.bxe(r1, r5)
        L_0x0035:
            r2 = -1
        L_0x0037:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "handle create movie ["
            r5.<init>(r0)
            r5.append(r2)
            java.lang.String r0 = "] ms"
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.samsung.android.gallery.support.utils.Log.bx(r1, r5)
            com.samsung.android.gallery.support.utils.Features r5 = com.samsung.android.gallery.support.utils.Features.SUPPORT_CREATE_MOVIE_V2
            boolean r5 = com.samsung.android.gallery.support.utils.Features.isEnabled(r5)
            if (r5 == 0) goto L_0x006e
            com.samsung.android.gallery.app.controller.externals.CreateMovieHighlightReelCmd r5 = new com.samsung.android.gallery.app.controller.externals.CreateMovieHighlightReelCmd
            r5.<init>()
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r4 = r4.mPresenter
            com.samsung.android.gallery.module.data.MediaItem[] r0 = r4.getSelectedItems()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2}
            r5.execute(r4, r0)
            goto L_0x0086
        L_0x006e:
            com.samsung.android.gallery.app.controller.externals.CreateMovieCmd r5 = new com.samsung.android.gallery.app.controller.externals.CreateMovieCmd
            r5.<init>()
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r4 = r4.mPresenter
            com.samsung.android.gallery.module.data.MediaItem[] r0 = r4.getSelectedItems()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2}
            r5.execute(r4, r0)
        L_0x0086:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.ListViewServiceBixby.handleCreateMovie(android.net.Uri):void");
    }

    private void handleGetSelectedContents(Uri uri) {
        Object obj;
        if (!uri.getBooleanQueryParameter("KEY_INTERNAL_REQUEST", false)) {
            Log.bxe("ListViewServiceBixby", "skip handle get selected contents");
            return;
        }
        boolean isSelectionMode = this.mPresenter.isSelectionMode();
        MediaItem[] selectedItems = this.mPresenter.getSelectedItems();
        StringBuilder sb2 = new StringBuilder("handle get selected contents [");
        sb2.append(isSelectionMode);
        sb2.append("][");
        if (selectedItems != null) {
            obj = Integer.valueOf(selectedItems.length);
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append("]");
        Log.bx("ListViewServiceBixby", sb2.toString());
        this.mBlackboard.post("data://bixby_command_done", new Object[]{Boolean.valueOf(isSelectionMode), selectedItems});
    }

    private void handleListViewShare(Uri uri) {
        int selectedItemCount = getSelectedItemCount();
        if (!this.mPresenter.isSelectionMode() || selectedItemCount <= 0) {
            StringBuilder o2 = C0086a.o(selectedItemCount, "unable to handle list view share. invalid. ", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(this.mPresenter.isSelectionMode());
            Log.bxe("ListViewServiceBixby", o2.toString());
            return;
        }
        this.mPresenter.prepareExtendedShare();
        String queryParameter = uri.getQueryParameter("KEY_TARGET_COMPONENT_PACKAGE");
        String queryParameter2 = uri.getQueryParameter("KEY_TARGET_COMPONENT_ACTIVITY");
        Log.bx("ListViewServiceBixby", "handle list view share [" + Logger.getEncodedString(queryParameter) + "][" + Logger.getEncodedString(queryParameter2) + "]");
        ShareViaCmd shareViaCmd = new ShareViaCmd();
        BaseListPresenter<?> baseListPresenter = this.mPresenter;
        shareViaCmd.execute(baseListPresenter, baseListPresenter.getSelectedItems(), ShareComponent.builder().setPackageName(queryParameter).setClassName(queryParameter2).setFromBixby());
    }

    private void handleMoveToAlbum(Uri uri) {
        MediaItem[] mediaItemArr;
        boolean isInSelectionMode = isInSelectionMode();
        if (!isSearchPictures()) {
            mediaItemArr = this.mPresenter.getSelectedItems();
        } else if (isInSelectionMode) {
            mediaItemArr = this.mPresenter.getSelectedItems();
        } else {
            mediaItemArr = this.mPresenter.getAllItems();
        }
        if (mediaItemArr == null || mediaItemArr.length == 0 || (mediaItemArr.length == 1 && mediaItemArr[0] == null)) {
            Log.bxe("ListViewServiceBixby", "unable to handle move to album. items are invalid");
            return;
        }
        Log.bx("ListViewServiceBixby", "handle move to album");
        new CreateAlbumAndMoveCmd().execute(this.mPresenter, mediaItemArr, uri);
    }

    private void handleMoveToGroup(Uri uri) {
        if (!isAlbumFolders()) {
            Log.bxe("ListViewServiceBixby", "unable to handle move to group. not in albums or folders");
            return;
        }
        Log.bx("ListViewServiceBixby", "handle move to group");
        new MoveToGroupCmd().execute(this.mPresenter, uri);
    }

    private void handleShareViaTV() {
        if (!isAlbumPictures()) {
            Log.bxe("ListViewServiceBixby", "unable to handle share via tv. not in album pictures");
        } else if (!isSmartViewConnected()) {
            Log.bx("ListViewServiceBixby", "handle share via tv. need to connect smart view.");
            Toast.makeText(this.mPresenter.getContext(), R.string.need_to_connect_smart_view, 0).show();
        } else {
            MediaItem[] allItems = this.mPresenter.getAllItems();
            if (allItems == null || allItems.length == 0) {
                Log.bxe("ListViewServiceBixby", "unable to handle share via tv. items are invalid");
                return;
            }
            Log.bx("ListViewServiceBixby", "handle share via tv. direct share to smart view.");
            new StartSlideshowV2Cmd().execute(this.mPresenter, allItems);
        }
    }

    private void handleShowSingleContent(Uri uri) {
        boolean z = false;
        if (!uri.getBooleanQueryParameter("KEY_INTERNAL_REQUEST", false)) {
            Log.bxe("ListViewServiceBixby", "skip handle show single content");
            return;
        }
        int i2 = UnsafeCast.toInt(uri.getQueryParameter("KEY_ORDER_NUM"), 0);
        long j2 = UnsafeCast.toLong(uri.getQueryParameter("KEY_TIME_FROM"), -1);
        long j3 = UnsafeCast.toLong(uri.getQueryParameter("KEY_TIME_TO"), -1);
        if (i2 <= 0) {
            Log.bxe("ListViewServiceBixby", "unable to show single content. invalid arguments");
            this.mBlackboard.post("data://bixby_command_done", new Object[]{Boolean.FALSE, null});
            return;
        }
        BaseListViewAdapter adapter = ((IBaseListView) this.mPresenter.getView()).getAdapter();
        if (adapter == null) {
            Log.bxe("ListViewServiceBixby", "unable to show single content. null adapter");
            this.mBlackboard.post("data://bixby_command_done", new Object[]{Boolean.FALSE, null});
            return;
        }
        Pair<ListViewHolder, String> findDateViewHolder = findDateViewHolder(i2 - 1, j2, j3, adapter);
        ListViewHolder listViewHolder = (ListViewHolder) findDateViewHolder.first;
        String str = (String) findDateViewHolder.second;
        if (listViewHolder == null || listViewHolder.getMediaItem() == null) {
            StringBuilder sb2 = new StringBuilder("unable to show single content. wrong view holder [");
            if (listViewHolder == null) {
                z = true;
            }
            sb2.append(z);
            sb2.append("][");
            sb2.append(i2);
            sb2.append("][");
            sb2.append(j2);
            sb2.append("][");
            sb2.append(j3);
            sb2.append("]");
            Log.bxe("ListViewServiceBixby", sb2.toString());
            this.mBlackboard.post("data://bixby_command_done", new Object[]{Boolean.FALSE, str});
            return;
        }
        if (((IBaseListView) this.mPresenter.getView()).useAdvancedMouseDragAndDrop() && ((Boolean) this.mBlackboard.pop("data://motion_event_tool_type_mouse", Boolean.FALSE)).booleanValue()) {
            this.mBlackboard.erase("data://motion_event_tool_type_mouse");
        }
        StringBuilder o2 = C0086a.o(i2, "handle show single content [", "][");
        o2.append(listViewHolder.getViewPosition());
        o2.append("][");
        o2.append(this.mPresenter.isSelectionMode());
        o2.append("]");
        Log.bx("ListViewServiceBixby", o2.toString());
        ((IBaseListView) this.mPresenter.getView()).showSingleContent(listViewHolder);
        this.mBlackboard.post("data://bixby_command_done", new Object[]{Boolean.TRUE, null});
    }

    private void handleSimilar(Uri uri) {
        if (SimilarPhotoHelper.supportSimilarPhoto()) {
            boolean z = false;
            if (UnsafeCast.toInt(uri.getQueryParameter("KEY_SIMILAR_ON"), 0) == 1) {
                z = true;
            }
            boolean isSimilarPhotoMode = SimilarPhotoHelper.isSimilarPhotoMode();
            Log.bx("ListViewServiceBixby", "handle similar [" + isSimilarPhotoMode + "][" + z + "]");
            if (isSimilarPhotoMode != z) {
                Context context = this.mPresenter.getContext();
                if (context == null) {
                    Log.bxe("ListViewServiceBixby", "handle similar failed, context is null");
                } else if (!isSimilarPhotoMode) {
                    SimpleThreadPool.getInstance().execute(new Z(this, context, 0));
                } else {
                    lambda$handleSimilar$1(context);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleSimilarInternal */
    public void lambda$handleSimilar$1(Context context) {
        String str;
        if (SimilarPhotoHelper.checkToShowSimilarPhotoToast()) {
            Log.bxe("ListViewServiceBixby", "handle similar executed but no similar photo");
            Utils.showToast(context, context.getString(R.string.cannot_show_similar_photo));
            return;
        }
        SimilarPhotoHelper.toggleSimilarPhotoMode();
        this.mBlackboard.postEvent(EventMessage.obtain(1072));
        BaseListPresenter<?> baseListPresenter = this.mPresenter;
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_MENU_SIMILAR_PHOTO;
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode)) {
            str = "1";
        } else {
            str = "0";
        }
        baseListPresenter.postAnalyticsLog(analyticsEventId, str);
    }

    private void handleTrash(boolean z) {
        String str;
        if (!isTrash()) {
            StringBuilder sb2 = new StringBuilder("unable to handle ");
            if (z) {
                str = "empty";
            } else {
                str = "restore";
            }
            sb2.append(str);
            sb2.append(". not in trash");
            Log.bxe("ListViewServiceBixby", sb2.toString());
        } else if (z) {
            EmptyTrashCmd emptyTrashCmd = new EmptyTrashCmd();
            BaseListPresenter<?> baseListPresenter = this.mPresenter;
            Boolean bool = Boolean.TRUE;
            emptyTrashCmd.execute(baseListPresenter, null, bool, bool);
        } else {
            new RestoreTrashCmd().execute(this.mPresenter, null, Boolean.TRUE);
        }
    }

    private boolean hasMoveToAlbumMenuItem() {
        MenuDataBinding menuDataBinding = this.mPresenter.getMenuDataBinding();
        if (menuDataBinding == null || !menuDataBinding.getVisibility(R.id.action_move_to_album)) {
            return false;
        }
        return true;
    }

    private boolean involveInvalidItem() {
        MediaItem[] selectedItems = this.mPresenter.getSelectedItems();
        if (selectedItems == null || selectedItems.length == 0) {
            Log.bxe("ListViewServiceBixby", "invalid item check failed, null or empty");
            return true;
        }
        for (MediaItem isInvalid : selectedItems) {
            if (isInvalid(isInvalid)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlbumFolders() {
        if ("location://albums".equals(this.mPresenter.getLocationKey()) || "location://albums/all".equals(this.mPresenter.getLocationKey()) || LocationKey.isFolder(this.mPresenter.getLocationKey())) {
            return true;
        }
        return false;
    }

    private boolean isAlbumPictures() {
        return LocationKey.isAlbumPictures(this.mPresenter.getLocationKey());
    }

    private boolean isCreateAlbumEnabled() {
        MenuDataBinding menuDataBinding = this.mPresenter.getMenuDataBinding();
        if (menuDataBinding == null) {
            return false;
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return menuDataBinding.getVisibility(R.id.action_create_albums);
        }
        return menuDataBinding.getVisibility(R.id.action_album_view_new_album);
    }

    private boolean isCreateGroupEnabled() {
        MenuDataBinding menuDataBinding = this.mPresenter.getMenuDataBinding();
        if (menuDataBinding == null) {
            return false;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !LocationKey.isAlbums(this.mPresenter.getLocationKey())) {
            return menuDataBinding.getVisibility(R.id.action_folder_add_folder);
        }
        return menuDataBinding.getVisibility(R.id.action_create_albums);
    }

    private boolean isInDateRange(MediaItem mediaItem, long j2, long j3) {
        if (mediaItem == null || j2 > mediaItem.getDateTaken() || mediaItem.getDateTaken() > j3) {
            return false;
        }
        return true;
    }

    private boolean isInSelectionMode() {
        if (this.mPresenter.isSelectionMode() || this.mPresenter.isOnAdvancedMouseFocused()) {
            return true;
        }
        return false;
    }

    private boolean isInvalid(MediaItem mediaItem) {
        if (mediaItem == null || CreateMediaHelper.SupportType.SUPPORT != CreateMediaHelper.supportMovieMaker(mediaItem.getMediaType(), mediaItem.getMimeType(), mediaItem.isCloudOnly())) {
            return true;
        }
        return false;
    }

    private boolean isMoveToGroupEnabled() {
        return isAlbumFolders();
    }

    private boolean isSearchPictures() {
        return LocationKey.isSearchPictures(this.mPresenter.getLocationKey());
    }

    private boolean isSimilarPhotoEnabled() {
        MenuDataBinding menuDataBinding = this.mPresenter.getMenuDataBinding();
        if (menuDataBinding != null && menuDataBinding.isItemVisible(R.id.action_similar)) {
            return true;
        }
        if (!SimilarPhotoHelper.supportSimilarPhoto() || !LocationKey.isTimelinePictures(this.mPresenter.getLocationKey())) {
            return false;
        }
        return true;
    }

    private boolean isSmartViewConnected() {
        DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        if (displayManagerCompat == null || displayManagerCompat.getDisplay(displayManagerCompat.getPrimaryPresentationId()) == null) {
            return false;
        }
        return true;
    }

    private boolean isTrash() {
        return LocationKey.isTrash(this.mPresenter.getLocationKey());
    }

    private boolean isUnsupportedScreen() {
        String locationKey = this.mPresenter.getLocationKey();
        if (locationKey == null) {
            return true;
        }
        if (LocationKey.isTimelinePictures(locationKey) || LocationKey.isAlbumPictures(locationKey) || LocationKey.isSearchPictures(locationKey) || LocationKey.isFavoritePictures(locationKey) || LocationKey.isVideoPictures(locationKey) || LocationKey.isRecentlyPictures(locationKey)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSimilar$2(Context context) {
        try {
            if (SimilarPhotoHelper.checkSimilarPhoto(context)) {
                ThreadUtil.postOnUiThread(new Z(this, context, 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putListInformation(HashMap<String, Object> hashMap) {
        int i2;
        if (isSearchPictures()) {
            i2 = this.mPresenter.getDataCount();
        } else {
            i2 = 0;
        }
        hashMap.put("KEY_SEARCH_COUNT", Integer.valueOf(i2));
        hashMap.put("KEY_SEARCH_KEYWORD", BixbyAppStateUtil.getNonNullValue(getSearchKeyword()));
        hashMap.put("KEY_SELECTED_COUNT", Integer.valueOf(getSelectedItemCount()));
        hashMap.put("KEY_SELECTION_MODE", Boolean.valueOf(isInSelectionMode()));
        hashMap.put("KEY_ALBUM_NAME", BixbyAppStateUtil.getNonNullValue(getAlbumName()));
        hashMap.put("KEY_SHARE_MULTIPLE", getShareState());
    }

    private Object supportBixbyCommand(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2041972916:
                if (str.equals("KEY_CREATE_ALBUM")) {
                    c5 = 0;
                    break;
                }
                break;
            case -2036240548:
                if (str.equals("KEY_CREATE_GROUP")) {
                    c5 = 1;
                    break;
                }
                break;
            case -2030782451:
                if (str.equals("KEY_CREATE_MOVIE")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1968357831:
                if (str.equals("KEY_MOVE_TO_ALBUM")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1962625463:
                if (str.equals("KEY_MOVE_TO_GROUP")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1849072572:
                if (str.equals("KEY_TRASH_IMAGE_COUNT")) {
                    c5 = 5;
                    break;
                }
                break;
            case -668509588:
                if (str.equals("KEY_SCREEN")) {
                    c5 = 6;
                    break;
                }
                break;
            case 918322123:
                if (str.equals("KEY_SIMILAR")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1313470308:
                if (str.equals("KEY_TRASH_VIDEO_COUNT")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1905944317:
                if (str.equals("KEY_SIMILAR_STATE")) {
                    c5 = 9;
                    break;
                }
                break;
            case 2057999640:
                if (str.equals("KEY_TRASH")) {
                    c5 = 10;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Boolean.valueOf(isCreateAlbumEnabled());
            case 1:
                return Boolean.valueOf(isCreateGroupEnabled());
            case 2:
                return Integer.valueOf(getCreateMovieState());
            case 3:
                return Integer.valueOf(getMoveToAlbumState());
            case 4:
                return Boolean.valueOf(isMoveToGroupEnabled());
            case 5:
                return Integer.valueOf(getTrashCount(true));
            case 6:
                return Integer.valueOf(getScreenType());
            case 7:
                return Boolean.valueOf(isSimilarPhotoEnabled());
            case 8:
                return Integer.valueOf(getTrashCount(false));
            case 9:
                return Integer.valueOf(getSimilarState());
            case 10:
                return Boolean.valueOf(isTrash());
            default:
                return Boolean.FALSE;
        }
    }

    public String getAppState() {
        Log.bx("ListViewServiceBixby", "getAppState " + this.mPresenter.getLocationKey());
        return BixbyAppStateUtil.generate(this.mBlackboard.getName(), getInformationList());
    }

    public void handleCommand(Uri uri) {
        Log.bx("ListViewServiceBixby", "handle command [" + Logger.getEncodedString(uri.toString()) + "]");
        String lastPathSegment = uri.getLastPathSegment();
        if (!TextUtils.isEmpty(lastPathSegment)) {
            lastPathSegment.getClass();
            char c5 = 65535;
            switch (lastPathSegment.hashCode()) {
                case -1140698360:
                    if (lastPathSegment.equals("TRASH_EMPTY_ALL")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -430296203:
                    if (lastPathSegment.equals("GET_SELECTED_CONTENTS")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 16936086:
                    if (lastPathSegment.equals("SELECTION_MODE")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 235054137:
                    if (lastPathSegment.equals("MOVE_TO_ALBUM")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 240786505:
                    if (lastPathSegment.equals("MOVE_TO_GROUP")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 461485299:
                    if (lastPathSegment.equals("SHARE_VIA_TV")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 626070471:
                    if (lastPathSegment.equals("SHOW_ALBUM_SLIDE_SHOW_VIEW")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 703880166:
                    if (lastPathSegment.equals("LIST_VIEW_SHARE")) {
                        c5 = 7;
                        break;
                    }
                    break;
                case 718584036:
                    if (lastPathSegment.equals("SHOW_SINGLE_CONTENT")) {
                        c5 = 8;
                        break;
                    }
                    break;
                case 800051532:
                    if (lastPathSegment.equals("CREATE_ALBUM")) {
                        c5 = 9;
                        break;
                    }
                    break;
                case 805783900:
                    if (lastPathSegment.equals("CREATE_GROUP")) {
                        c5 = 10;
                        break;
                    }
                    break;
                case 811241997:
                    if (lastPathSegment.equals("CREATE_MOVIE")) {
                        c5 = 11;
                        break;
                    }
                    break;
                case 861993933:
                    if (lastPathSegment.equals("TIMELINE_SIMILAR")) {
                        c5 = 12;
                        break;
                    }
                    break;
                case 1206655369:
                    if (lastPathSegment.equals("TRASH_RESTORE_ALL")) {
                        c5 = 13;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    handleTrash(true);
                    return;
                case 1:
                    handleGetSelectedContents(uri);
                    return;
                case 2:
                    handleSelectionMode(uri);
                    return;
                case 3:
                    handleMoveToAlbum(uri);
                    return;
                case 4:
                    handleMoveToGroup(uri);
                    return;
                case 5:
                    handleShareViaTV();
                    return;
                case 6:
                    handleAlbumSlideshow();
                    return;
                case 7:
                    handleListViewShare(uri);
                    return;
                case 8:
                    handleShowSingleContent(uri);
                    return;
                case 9:
                    handleCreateAlbum(uri);
                    return;
                case 10:
                    handleCreateGroup(uri);
                    return;
                case 11:
                    handleCreateMovie(uri);
                    return;
                case 12:
                    handleSimilar(uri);
                    return;
                case 13:
                    handleTrash(false);
                    return;
                default:
                    Log.bxe("ListViewServiceBixby", "unable to handle command [" + lastPathSegment + "]");
                    return;
            }
        }
    }

    private void handleSelectionMode(Uri uri) {
    }
}
