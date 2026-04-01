package com.samsung.android.gallery.database.dal.mp;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.database.dal.mp.impl.ActivitiesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.AlbumFilesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.AlbumsImpl;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.FilesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.FilesImplV2;
import com.samsung.android.gallery.database.dal.mp.impl.GroupMediaImpl;
import com.samsung.android.gallery.database.dal.mp.impl.PeoplePetImpl;
import com.samsung.android.gallery.database.dal.mp.impl.PicturesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.StoriesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.VirtualAlbumsImpl;
import com.samsung.android.gallery.database.dbtype.DateType;
import java.util.HashMap;
import r8.a;
import r8.b;
import r8.c;
import r8.d;
import r8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecMpMethodMap extends HashMap<String, CursorProvider> {
    final QueryParams mParams;

    public SecMpMethodMap(QueryParams queryParams) {
        this.mParams = queryParams;
        init();
    }

    private ActivitiesImpl getActivities() {
        return new ActivitiesImpl(this.mParams);
    }

    private AlbumFilesImpl getAlbumFiles() {
        return new AlbumFilesImpl(this.mParams);
    }

    private AlbumsImpl getAlbums() {
        return new AlbumsImpl(this.mParams);
    }

    private FilesImplV2 getAllPictures() {
        return new FilesImplV2(this.mParams);
    }

    private CategoriesImpl getCategories() {
        return new CategoriesImpl(this.mParams);
    }

    @Deprecated
    private FilesImpl getFiles() {
        return new FilesImpl(this.mParams);
    }

    private GroupMediaImpl getGroupMedia() {
        return new GroupMediaImpl(this.mParams);
    }

    private PeoplePetImpl getPeoplePet() {
        return new PeoplePetImpl(this.mParams);
    }

    private PicturesImpl getPictures() {
        return new PicturesImpl(this.mParams);
    }

    private VirtualAlbumsImpl getVirtualAlbums() {
        return new VirtualAlbumsImpl(this.mParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$0(QueryParams queryParams) {
        return getPictures().getTimelineFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$1(QueryParams queryParams) {
        return getPictures().getTimelineDateCursor(DateType.DAY);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$10(QueryParams queryParams) {
        return getAlbums().getNewAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$100(QueryParams queryParams) {
        return getCategories().getScreenShotCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$101(QueryParams queryParams) {
        return getCategories().getScreenShotSubListFileIdsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$102(QueryParams queryParams) {
        return getCategories().getScreenShotSubList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$103(QueryParams queryParams) {
        return getCategories().getVideoFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$104(QueryParams queryParams) {
        return getCategories().getBlurredFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$105(QueryParams queryParams) {
        return getCategories().getSmileFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$106(QueryParams queryParams) {
        return getActivities().getActivityCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$107(QueryParams queryParams) {
        return getActivities().getGeneratedFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$108(QueryParams queryParams) {
        return getActivities().getGeneratedFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$109(QueryParams queryParams) {
        return getActivities().getGeneratedFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$11(QueryParams queryParams) {
        return getAlbums().getAlbumCountCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$110(QueryParams queryParams) {
        return getActivities().getGeneratedFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$111(QueryParams queryParams) {
        return getActivities().getRecentlyEditedFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$112(QueryParams queryParams) {
        return getActivities().getRecentlyEditedFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$113(QueryParams queryParams) {
        return getActivities().getRecentlyEditedFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$114(QueryParams queryParams) {
        return getActivities().getRecentlyEditedFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$115(QueryParams queryParams) {
        return getStories().getStoryAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$116(QueryParams queryParams) {
        return getStories().getStoryAlbumPickerCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$117(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$118(QueryParams queryParams) {
        return getStories().getStoryAlbumFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$119(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCurationCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$12(QueryParams queryParams) {
        return getAlbums().getAlbumAutoCompleteCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$120(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$121(QueryParams queryParams) {
        return getStories().getStoryNotificationFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$122(QueryParams queryParams) {
        return getStories().getSearchAutoCompleteStoryCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$123(QueryParams queryParams) {
        return getStories().getRelatedMemories();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$124(QueryParams queryParams) {
        return getStories().getStoryAppBar();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$125(QueryParams queryParams) {
        return getStories().getDateHideRuleCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$126(QueryParams queryParams) {
        return getStories().getCreatureHideRuleCursor(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$127(QueryParams queryParams) {
        return getStories().getCreatureHideRuleCursor(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$128(QueryParams queryParams) {
        return getStories().getStoryShareFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$13(QueryParams queryParams) {
        return getAlbumFiles().getAlbumFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$14(QueryParams queryParams) {
        return getAlbumFiles().getAlbumCountCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$15(QueryParams queryParams) {
        return getAlbumFiles().getAlbumFileIds();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$16(QueryParams queryParams) {
        return getAlbumFiles().getAlbumFileIdsOrdered();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$17(QueryParams queryParams) {
        return getAlbumFiles().getAlbumFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$18(QueryParams queryParams) {
        return getAlbumFiles().getAlbumFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$19(QueryParams queryParams) {
        return getFiles().getFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$2(QueryParams queryParams) {
        return getPictures().getRealRatioDataCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$20(QueryParams queryParams) {
        return getAllPictures().getDataStamp();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$21(QueryParams queryParams) {
        return getGroupMedia().getBurstShotCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$22(QueryParams queryParams) {
        return getGroupMedia().getSimilarShotCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$23(QueryParams queryParams) {
        return getGroupMedia().getSimilarShotForAllCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$24(QueryParams queryParams) {
        return getGroupMedia().getSimilarShotCountCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$25(QueryParams queryParams) {
        return getGroupMedia().getSingleTakenShotCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$26(QueryParams queryParams) {
        return getGroupMedia().getSingleTakenShotForAllCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$27(QueryParams queryParams) {
        return getAllPictures().getIdCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$28(QueryParams queryParams) {
        return getAllPictures().getCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$29(QueryParams queryParams) {
        return getAllPictures().getNoGroupCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$3(QueryParams queryParams) {
        return getPictures().getSpanDataCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$30(QueryParams queryParams) {
        return getAllPictures().getDayCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$31(QueryParams queryParams) {
        return getAllPictures().getRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$32(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFavoriteAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$33(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFavoriteCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$34(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFavoriteDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$35(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFavoriteFileIdsOrdered();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$36(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFavoriteRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$37(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualVideoCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$38(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualVideoDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$39(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualVideoFileIdsOrdered();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$4(QueryParams queryParams) {
        return getPictures().getTimelineFileIds();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$40(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualVideoRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$41(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualRecentAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$42(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualRecentCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$43(QueryParams queryParams) {
        return getVirtualAlbums().getVirtualRecentShareDataCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$44(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualRepairCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$45(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualRepairDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$46(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualRepairRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$47(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFilesCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$48(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFilesDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$49(QueryParams queryParams) {
        return getVirtualAlbums().getAlbumVirtualFilesRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$5(QueryParams queryParams) {
        return getPictures().getTimelineFileIdsOrdered();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$50(QueryParams queryParams) {
        return getCategories().getMyTagCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$51(QueryParams queryParams) {
        return getCategories().getMyTagFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$52(QueryParams queryParams) {
        return getCategories().getMyTagFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$53(QueryParams queryParams) {
        return getCategories().getMyTagFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$54(QueryParams queryParams) {
        return getCategories().getMyTagFilRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$55(QueryParams queryParams) {
        return getCategories().getShotModeCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$56(QueryParams queryParams) {
        return getCategories().getShotModeFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$57(QueryParams queryParams) {
        return getCategories().getShotModeFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$58(QueryParams queryParams) {
        return getCategories().getShotModeFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$59(QueryParams queryParams) {
        return getCategories().getShotModeFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$6(QueryParams queryParams) {
        return getPictures().getTimelineYear();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$60(QueryParams queryParams) {
        return getPeoplePet().getPeopleCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$61(QueryParams queryParams) {
        return getPeoplePet().getAllPeopleCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$62(QueryParams queryParams) {
        return getPeoplePet().getPeopleNoRelationshipCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$63(QueryParams queryParams) {
        return getPeoplePet().getPeopleFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$64(QueryParams queryParams) {
        return getPeoplePet().getPeopleFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$65(QueryParams queryParams) {
        return getPeoplePet().getPeopleFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$66(QueryParams queryParams) {
        return getPeoplePet().getPeopleFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$67(QueryParams queryParams) {
        return getPeoplePet().getPetsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$68(QueryParams queryParams) {
        return getPeoplePet().getPetsFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$69(QueryParams queryParams) {
        return getPeoplePet().getPetsFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$7(QueryParams queryParams) {
        return getPictures().findHiddenFiles();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$70(QueryParams queryParams) {
        return getPeoplePet().getPetsFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$71(QueryParams queryParams) {
        return getPeoplePet().getPetsFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$72(QueryParams queryParams) {
        return getPeoplePet().getPeopleAndPetsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$73(QueryParams queryParams) {
        return getPeoplePet().getAllPeopleAndPetsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$74(QueryParams queryParams) {
        return getPeoplePet().getHiddenPeopleCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$75(QueryParams queryParams) {
        return getPeoplePet().getHiddenPeopleAndPetsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$76(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$77(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationFileCursor(this.mParams.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$78(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationFileDateCursor(this.mParams.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$79(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationFileIdsCursor(this.mParams.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$8(QueryParams queryParams) {
        return getPictures().findWrongDateTime();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$80(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationFileRealRatioCursor(this.mParams.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$81(QueryParams queryParams) {
        return new LocationApi(this.mParams).getLocationFileGpsCursor(this.mParams.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$82(QueryParams queryParams) {
        return getCategories().getExpressionCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$83(QueryParams queryParams) {
        return getCategories().getExpressionFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$84(QueryParams queryParams) {
        return getCategories().getExpressionFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$85(QueryParams queryParams) {
        return getCategories().getExpressionFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$86(QueryParams queryParams) {
        return getCategories().getExpressionFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$87(QueryParams queryParams) {
        return getCategories().getDocumentCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$88(QueryParams queryParams) {
        return getCategories().getDocumentFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$89(QueryParams queryParams) {
        return getCategories().getDocumentFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$9(QueryParams queryParams) {
        return getAlbums().getAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$90(QueryParams queryParams) {
        return getCategories().getDocumentFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$91(QueryParams queryParams) {
        return getCategories().getDocumentFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$92(QueryParams queryParams) {
        return getCategories().getSceneCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$93(QueryParams queryParams) {
        return getCategories().getRelatedSceneCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$94(QueryParams queryParams) {
        return getCategories().getSceneFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$95(QueryParams queryParams) {
        return getCategories().getSceneFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$96(QueryParams queryParams) {
        return getCategories().getSceneFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$97(QueryParams queryParams) {
        return getCategories().getSceneFileRealRatioCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$98(QueryParams queryParams) {
        return getCategories().getThingsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$99(QueryParams queryParams) {
        return getCategories().getSceneryCursor();
    }

    public StoriesImpl getStories() {
        return new StoriesImpl(this.mParams);
    }

    public void init() {
        put(DbKey.TIMELINE, new a(this, 0));
        put(DbKey.TIMELINE_DAY, new c(this, 3));
        put(DbKey.TIMELINE_REALRATIO, new c(this, 15));
        put(DbKey.TIMELINE_SPAN, new c(this, 27));
        put(DbKey.TIMELINE_FILE_IDS, new d(this, 9));
        put(DbKey.TIMELINE_FILE_IDS_ORDERED, new d(this, 21));
        put(DbKey.TIMELINE_YEAR, new e(this, 3));
        put("mp://hiddenFiles", new a(this, 7));
        put("mp:///WrongDateTime", new a(this, 19));
        put(DbKey.ALBUMS, new b(this, 1));
        put(DbKey.ALBUMS_NEW, new a(this, 22));
        put(DbKey.ALBUMS_COUNT, new b(this, 13));
        put(DbKey.ALBUMS_AUTO_COMPLETE, new b(this, 24));
        put(DbKey.ALBUM_FILES, new b(this, 25));
        put(DbKey.ALBUM_FILE_COUNT, new b(this, 26));
        put(DbKey.ALBUM_FILE_IDS, new b(this, 27));
        put(DbKey.ALBUM_FILE_IDS_ORDERED, new b(this, 28));
        put(DbKey.ALBUM_FILE_DAY, new b(this, 29));
        put(DbKey.ALBUM_FILE_REALRATIO, new c(this, 0));
        put(DbKey.FILES, new c(this, 1));
        put(DbKey.FILES_DATA_STAMP, new c(this, 4));
        put(DbKey.FILES_BURSTSHOT, new c(this, 5));
        put(DbKey.FILES_SIMILAR, new c(this, 6));
        put(DbKey.FILES_SIMILAR_ALL, new c(this, 7));
        put(DbKey.FILES_SIMILAR_COUNT, new c(this, 8));
        put(DbKey.FILES_SINGLETAKE, new c(this, 9));
        put(DbKey.FILES_SINGLETAKE_ALL, new c(this, 10));
        put(DbKey.ALL_PICTURES_ID, new c(this, 11));
        put(DbKey.ALL_PICTURES, new c(this, 12));
        put(DbKey.ALL_PICTURES_NO_GROUP, new c(this, 14));
        put(DbKey.ALL_PICTURES_DAY, new c(this, 16));
        put(DbKey.ALL_PICTURES_REAL_RATIO, new c(this, 17));
        put(DbKey.VIRTUAL_ALBUM_FAVORITE_ALBUM, new c(this, 18));
        put(DbKey.VIRTUAL_ALBUM_FAVORITE, new c(this, 19));
        put(DbKey.VIRTUAL_ALBUM_FAVORITE_DAY, new c(this, 20));
        put(DbKey.VIRTUAL_ALBUM_FAVORITE_FILE_IDS_ORDERED, new c(this, 21));
        put(DbKey.VIRTUAL_ALBUM_FAVORITE_REAL_RATIO, new c(this, 22));
        put(DbKey.VIRTUAL_ALBUM_VIDEO, new c(this, 23));
        put(DbKey.VIRTUAL_ALBUM_VIDEO_DAY, new c(this, 25));
        put(DbKey.VIRTUAL_ALBUM_VIDEO_FILE_IDS_ORDERED, new c(this, 26));
        put(DbKey.VIRTUAL_ALBUM_VIDEO_REAL_RATIO, new c(this, 28));
        put(DbKey.VIRTUAL_ALBUM_RECENT_ALBUM, new c(this, 29));
        put(DbKey.VIRTUAL_ALBUM_RECENT, new d(this, 0));
        put(DbKey.VIRTUAL_ALBUM_RECENT_SHARE_DATA, new d(this, 1));
        put(DbKey.VIRTUAL_ALBUM_REPAIR, new d(this, 2));
        put(DbKey.VIRTUAL_ALBUM_REPAIR_DAY, new d(this, 3));
        put(DbKey.VIRTUAL_ALBUM_REPAIR_REAL_RATIO, new d(this, 4));
        put(DbKey.VIRTUAL_ALBUM_FILES, new d(this, 6));
        put(DbKey.VIRTUAL_ALBUM_FILES_DAY, new d(this, 7));
        put(DbKey.VIRTUAL_ALBUM_FILES_REAL_RATIO, new d(this, 8));
        put("mp://myTag", new d(this, 10));
        put("mp://myTag/files", new d(this, 11));
        put("mp://myTag/files/day", new d(this, 12));
        put("mp://myTag/files/fileIds", new d(this, 13));
        put("mp://myTag/files/realratio", new d(this, 14));
        put("mp://ShotMode", new d(this, 15));
        put("mp://ShotMode/files", new d(this, 17));
        put("mp://ShotMode/files/day", new d(this, 18));
        put("mp://ShotMode/files/fileIds", new d(this, 19));
        put("mp://ShotMode/files/realratio", new d(this, 20));
        put("mp://People", new d(this, 22));
        put("mp://PeopleHide", new d(this, 23));
        put("mp://PeopleNoRelationship", new d(this, 24));
        put("mp://People/files", new d(this, 25));
        put("mp://People/files/day", new d(this, 26));
        put("mp://People/files/fileIds", new d(this, 28));
        put("mp://People/files/realratio", new d(this, 29));
        put("mp://Pets", new e(this, 0));
        put("mp://Pets/files", new e(this, 1));
        put("mp://Pets/day", new e(this, 2));
        put("mp://Pets/files/fileIds", new e(this, 4));
        put("mp://Pets/files/realratio", new e(this, 5));
        put("mp://PeopleAndPets", new e(this, 6));
        put("mp://PeopleAndPetsHide", new e(this, 7));
        put("mp://PeopleHidden", new a(this, 1));
        put("mp://PeopleAndPetsHidden", new a(this, 2));
        put("mp://Location", new a(this, 3));
        put("mp://Location/files", new a(this, 4));
        put("mp://Location/day", new a(this, 5));
        put("mp://Location/fileIds", new a(this, 6));
        put("mp://Location/files/realratio", new a(this, 8));
        put("mp://Location/files/gps", new a(this, 9));
        put("mp://Expression", new a(this, 10));
        put("mp://Expression/files", new a(this, 12));
        put("mp://Expression/files/day", new a(this, 13));
        put("mp://Expression/files/fileIds", new a(this, 14));
        put("mp://Expression/files/realratio", new a(this, 15));
        put("mp://Document", new a(this, 16));
        put("mp://Document/files", new a(this, 17));
        put("mp://Document/files/day", new a(this, 18));
        put("mp://Document/files/fileIds", new a(this, 20));
        put("mp://Document/files/realratio", new a(this, 21));
        put("mp://Scene", new a(this, 23));
        put("mp://RelatedScene", new a(this, 24));
        put("mp://Scene/files", new a(this, 25));
        put("mp://Scene/files/day", new a(this, 26));
        put("mp://Scene/files/fileIds", new a(this, 27));
        put("mp://Scene/files/realratio", new a(this, 28));
        put("mp://Things", new a(this, 29));
        put("mp://Scenery", new b(this, 0));
        put("mp://ScreenShot", new b(this, 10));
        put("mp://ScreenShot/Sub/List/fileIds", new b(this, 21));
        put("mp://ScreenShot/Sub/List", new c(this, 2));
        put("mp://SearchSuggestion/Video", new c(this, 13));
        put("mp://SearchSuggestion/Blurred", new c(this, 24));
        put("mp://SearchSuggestion/Smile", new d(this, 5));
        put("mp://Activity", new d(this, 16));
        put("mp://Generated/files", new d(this, 27));
        put("mp://Generated/files/day", new e(this, 8));
        put("mp://Generated/files/fileIds", new a(this, 11));
        put("mp://Generated/files/realratio", new b(this, 2));
        put("mp://RecentlyEdited/files", new b(this, 3));
        put("mp://RecentlyEdited/files/day", new b(this, 4));
        put("mp://RecentlyEdited/fileIds", new b(this, 5));
        put("mp://RecentlyEdited/realratio", new b(this, 6));
        put(DbKey.STORIES, new b(this, 7));
        put(DbKey.STORIES_PICKER, new b(this, 8));
        put(DbKey.STORY_FILES, new b(this, 9));
        put(DbKey.STORY_FILES_DAY, new b(this, 11));
        put(DbKey.STORY_FILES_CURATION, new b(this, 12));
        put(DbKey.STORY_FILES_HIGHLIGHT_LIST, new b(this, 14));
        put(DbKey.STORY_NOTIFICATION_FILES, new b(this, 15));
        put(DbKey.STORY_AUTO_COMPLETE, new b(this, 16));
        put(DbKey.RELATED_MEMORIES, new b(this, 17));
        put(DbKey.STORY_APPBAR, new b(this, 18));
        put(DbKey.STORY_HIDE_RULE_DATE, new b(this, 19));
        put(DbKey.STORY_HIDE_RULE_SCENE, new b(this, 20));
        put(DbKey.STORY_HIDE_SCENE_SELECTION, new b(this, 22));
        put(DbKey.STORY_SHARE_FILES, new b(this, 23));
    }
}
