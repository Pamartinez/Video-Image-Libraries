package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpPeopleView;
import com.samsung.android.gallery.database.dal.mp.table.MpPetView;
import com.samsung.android.gallery.database.dal.mp.table.MpPlaceView;
import com.samsung.android.gallery.database.dal.mp.table.MpSceneView;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.database.dal.mp.table.MpUserTagView;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchProviderApi extends BaseImpl {
    public SearchProviderApi() {
        super(new QueryParams());
    }

    private boolean checkVideoSefType(ShotMode shotMode) {
        if (shotMode.contains(3088) || shotMode.contains(3312)) {
            return true;
        }
        return false;
    }

    private MpFilesTable createAlbumVirtualFavoriteFilesTable(String str) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGalleryMedia(str);
        mpFilesTable.filterIsFavorite();
        mpFilesTable.filterGroupMediaBest(true);
        return mpFilesTable;
    }

    private MpPlaceView createPlaceView(String str) {
        MpPlaceView mpPlaceView = new MpPlaceView(this.mParams);
        mpPlaceView.filterLocation(str);
        mpPlaceView.filterBurstShotBestImage(true);
        mpPlaceView.groupByFileId();
        mpPlaceView.replaceSubCategoryProjection(str);
        return mpPlaceView;
    }

    private MpTagView getDocumentFileView(String str) {
        MpSceneView mpSceneView = new MpSceneView(this.mParams);
        mpSceneView.havingMedia();
        mpSceneView.filterSceneOrSubScene();
        if (str != null) {
            if (str.equals("Other Documents")) {
                mpSceneView.filterOtherDocuments();
            } else if (!str.equals("Documents")) {
                mpSceneView.filterDocuments();
                mpSceneView.filterTagData(str);
            } else {
                mpSceneView.filterAllDocuments();
            }
        }
        mpSceneView.groupByFileId();
        return mpSceneView;
    }

    private MpFacesView getExpressionFileView(String str) {
        return MpFacesView.getExpressionFileView(this.mParams, str);
    }

    private MpFilesTable getShotModeFilesTable(String str, String str2) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1997563968:
                if (str.equals("log_video")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1419937693:
                if (str.equals("apv_video")) {
                    c5 = 1;
                    break;
                }
                break;
            case -422395980:
                if (str.equals("slow_motion")) {
                    c5 = 2;
                    break;
                }
                break;
            case 112680:
                if (str.equals("raw")) {
                    c5 = 3;
                    break;
                }
                break;
            case 100313435:
                if (str.equals("image")) {
                    c5 = 4;
                    break;
                }
                break;
            case 112202875:
                if (str.equals("video")) {
                    c5 = 5;
                    break;
                }
                break;
            case 119089129:
                if (str.equals("360_video")) {
                    c5 = 6;
                    break;
                }
                break;
            case 410607289:
                if (str.equals("burst_shot")) {
                    c5 = 7;
                    break;
                }
                break;
            case 481952495:
                if (str.equals("Single Taken")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1121735576:
                if (str.equals("3d_capture")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1126770652:
                if (str.equals("super_slow_mo")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1675382260:
                if (str.equals("3d_capture_image")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1687271700:
                if (str.equals("3d_capture_video")) {
                    c5 = 12;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                mpFilesTable.filterLogShotMode();
                return mpFilesTable;
            case 1:
                mpFilesTable.filterApvShotMode();
                return mpFilesTable;
            case 2:
            case 10:
                if (PreferenceFeatures.Poc.SUPPORT_EXPOSE_NONDESTRUCTIVE_RECORDING_IN_SEARCH) {
                    mpFilesTable.filterVideo();
                    mpFilesTable.filterNonDestructiveRecording(str);
                    return mpFilesTable;
                }
                break;
            case 3:
                mpFilesTable.filterRawShotMode();
                return mpFilesTable;
            case 4:
                mpFilesTable.filterImage();
                return mpFilesTable;
            case 5:
                mpFilesTable.filterVideo();
                return mpFilesTable;
            case 6:
                mpFilesTable.filter360Video();
                return mpFilesTable;
            case 7:
                mpFilesTable.filterBurstShotOnly();
                return mpFilesTable;
            case 8:
                if (UnsafeCast.toInt(str2) == MediaType.Video.toInt()) {
                    mpFilesTable.filterSingleTakeChildVideoOnly();
                    return mpFilesTable;
                }
                mpFilesTable.filterSingleTakeOnly();
                return mpFilesTable;
            case 9:
            case 11:
            case 12:
                mpFilesTable.filter3dCaptureShotMode();
                return mpFilesTable;
        }
        ArrayList<ShotMode> findByStringKeyword = ShotModeList.getInstance().findByStringKeyword(str, false);
        if (findByStringKeyword.size() >= 1) {
            ShotMode shotMode = findByStringKeyword.get(0);
            if (shotMode.isImage() || checkVideoSefType(shotMode)) {
                mpFilesTable.filterSefFileType(shotMode.getSefTypesForSearch());
                return mpFilesTable;
            }
            mpFilesTable.filterRecordingMode(shotMode.getRecordingModesForSearch());
            return mpFilesTable;
        }
        throw new AssertionError("unexpected result : " + findByStringKeyword);
    }

    public Cursor getAlbumVirtualFavoriteCursor(String str) {
        return getCursor(createAlbumVirtualFavoriteFilesTable(str).buildSelectQuery(), "getAlbumVirtualFavoriteCursor");
    }

    public Cursor getAlbumVirtualVideoCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        return getCursor(mpFilesTable.buildSelectQuery(), "getAlbumVirtualVideoCursor");
    }

    public Cursor getBlurredFileCursor(String str) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterBlurryImages();
        mpFilesTable.filterMediaType(str);
        mpFilesTable.removeBurstShotProjections();
        return getCursor(mpFilesTable.buildSelectQuery(), "blurred files");
    }

    public Cursor getDocumentFileCursor(String str, String str2) {
        MpTagView documentFileView = getDocumentFileView(str);
        documentFileView.filterMediaType(str2);
        documentFileView.addOrderByDate();
        Query buildSelectQuery = documentFileView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Document : " + str);
    }

    public Cursor getExpressionFileCursor(String str, String str2) {
        if (str == null) {
            Log.w(this.TAG, "getExpressionFileCursor - subCategory is null");
            return null;
        }
        MpFacesView expressionFileView = getExpressionFileView(str);
        expressionFileView.filterMediaType(str2);
        return getCursor(expressionFileView.buildSelectQuery(), "expression files ".concat(str));
    }

    public Cursor getFromTimedFileCursor(String str, String str2) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterByFromTime(str, true);
        mpFilesTable.filterMediaType(str2);
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "from time" + str);
    }

    public Cursor getLocationFileCursor(String str, String str2) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.filterMediaType(str2);
        createPlaceView.addOrderByDate();
        Query buildSelectQuery = createPlaceView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Location : " + str);
    }

    public Cursor getMyTagFileCursor(String str, String str2) {
        MpUserTagView mpUserTagView = new MpUserTagView(this.mParams);
        mpUserTagView.filterTagData(str);
        mpUserTagView.filterMediaType(str2);
        Query buildSelectQuery = mpUserTagView.buildSelectQuery();
        return getCursor(buildSelectQuery, "My Tag : " + str);
    }

    public Cursor getPeopleFileCursor(long j2, String str) {
        MpPeopleView mpPeopleView = new MpPeopleView(this.mParams);
        mpPeopleView.filterBurstShotBestImage(false);
        mpPeopleView.filterMediaType(str);
        mpPeopleView.filterGroupId(j2);
        mpPeopleView.modifyForPictures(true);
        Query buildSelectQuery = mpPeopleView.buildSelectQuery();
        return getCursor(buildSelectQuery, "people file : " + j2);
    }

    public Cursor getPetsFileCursor(long j2, String str) {
        MpPetView mpPetView = new MpPetView(this.mParams);
        mpPetView.filterBurstShotBestImage(false);
        mpPetView.filterMediaType(str);
        mpPetView.filterGroupId(j2);
        mpPetView.modifyForPictures(true);
        Query buildSelectQuery = mpPetView.buildSelectQuery();
        return getCursor(buildSelectQuery, "pets file : " + j2);
    }

    public Cursor getSceneFileCursor(String str, String str2) {
        MpTagView mpTagView = new MpTagView(this.mParams);
        mpTagView.filterSceneOrSubScene();
        mpTagView.filterRemainScenes();
        mpTagView.filterTagData(str);
        mpTagView.filterMediaType(str2);
        mpTagView.groupByFileId();
        mpTagView.addOrderByDate();
        Query buildSelectQuery = mpTagView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Scene : " + str);
    }

    public Cursor getShotModeFileCursor(String str, String str2) {
        Query buildSelectQuery = getShotModeFilesTable(str, str2).buildSelectQuery();
        return getCursor(buildSelectQuery, "shotMode : " + str);
    }

    public Cursor getSmileFileCursor(String str) {
        MpFacesView mpFacesView = new MpFacesView(this.mParams);
        mpFacesView.filterSmile();
        mpFacesView.modifyForPictures();
        mpFacesView.filterMediaType(str);
        return getCursor(mpFacesView.buildSelectQuery(), "smile files");
    }

    public Cursor getVideoFileCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterVideo();
        return getCursor(mpFilesTable.buildSelectQuery(), "video files");
    }

    public String tag() {
        return "SearchProviderApi";
    }

    public SearchProviderApi(QueryParams queryParams) {
        super(queryParams);
    }
}
