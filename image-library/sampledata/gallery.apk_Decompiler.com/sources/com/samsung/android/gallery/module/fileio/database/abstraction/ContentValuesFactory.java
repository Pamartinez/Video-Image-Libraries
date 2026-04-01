package com.samsung.android.gallery.module.fileio.database.abstraction;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContentValuesFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FactoryHolder {
        static final ContentValuesFactory factory;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ContentValuesFactoryCmh extends ContentValuesFactory {
            public ContentValuesBuilder createContentValuesBuilder() {
                return new ContentValuesBuilderCmh();
            }

            public Uri getUriFromItem(FileItemInterface fileItemInterface, String str) {
                return Uri.withAppendedPath(MediaUri.getInstance().getImageUri(), String.valueOf(fileItemInterface.getMediaId()));
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ContentValuesFactoryMp extends ContentValuesFactory {
            public ContentValuesBuilder createContentValuesBuilder() {
                return new ContentValuesBuilderMp();
            }

            public Uri getUriFromItem(FileItemInterface fileItemInterface, String str) {
                if (fileItemInterface.isCloudOnly()) {
                    return ContentUris.withAppendedId(MediaUri.getInstance().getSecCloudUri(), fileItemInterface.getFileId());
                }
                return ContentUris.withAppendedId(getFilesUri(str), fileItemInterface.getFileId());
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ContentValuesFactoryMpQ extends ContentValuesFactory {
            public ContentValuesBuilder createContentValuesBuilder() {
                return new ContentValuesBuilderMpQ();
            }

            public Uri getUriFromItem(FileItemInterface fileItemInterface, String str) {
                if (fileItemInterface.isCloudOnly()) {
                    return ContentUris.withAppendedId(MediaUri.getInstance().getSecCloudUri(), fileItemInterface.getFileId());
                }
                return ContentUris.withAppendedId(getFilesUri(str), fileItemInterface.getMediaId());
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ContentValuesFactoryMpR extends ContentValuesFactory {
            public ContentValuesBuilder createContentValuesBuilder() {
                return new ContentValuesBuilderMpR();
            }

            public Uri getUriFromItem(FileItemInterface fileItemInterface, String str) {
                if (fileItemInterface.isCloudOnly()) {
                    return ContentUris.withAppendedId(MediaUri.getInstance().getSecCloudUri(), fileItemInterface.getFileId());
                }
                return ContentUris.withAppendedId(getFilesUri(str), fileItemInterface.getMediaId());
            }
        }

        static {
            ContentValuesFactory contentValuesFactory;
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                contentValuesFactory = new ContentValuesFactoryMpR();
            } else if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                contentValuesFactory = new ContentValuesFactoryMpQ();
            } else if (Features.isEnabled(Features.USE_NEWMP)) {
                contentValuesFactory = new ContentValuesFactoryMp();
            } else if (Features.isEnabled(Features.USE_CMH)) {
                contentValuesFactory = new ContentValuesFactoryCmh();
            } else {
                contentValuesFactory = new ContentValuesFactoryMpQ();
            }
            factory = contentValuesFactory;
        }
    }

    public static ContentValuesFactory getFactory() {
        return FactoryHolder.factory;
    }

    private ContentValues getGroupMediaCopyValues(FileItemInterface fileItemInterface, Pair<String, String> pair, String str, int i2, int i7, int i8) {
        boolean z;
        Pair<String, String> pair2 = pair;
        int i10 = i8;
        ContentValuesBuilder groupInfo = createContentValuesBuilder().setPath((String) pair2.first).setFileSize(fileItemInterface.getFileSize()).setMediaInfo(fileItemInterface.getMimeType(), fileItemInterface.getWidth(), fileItemInterface.getHeight(), fileItemInterface.getOrientation()).setDateInfo(fileItemInterface.getDateTaken(), fileItemInterface.getDateAdded(), fileItemInterface.getDateModified()).setLocationInfo(fileItemInterface.getLatitude(), fileItemInterface.getLongitude()).setSefInfo(fileItemInterface.getSefFileType(), fileItemInterface.getSefFileSubType(), fileItemInterface.getSefFileTypes()).setGroupInfo((long) i7, fileItemInterface.getGroupType(), fileItemInterface.getBestImage());
        if (!isMoveMode(i10) || !fileItemInterface.isFavourite()) {
            z = false;
        } else {
            z = true;
        }
        ContentValuesBuilder favorite = groupInfo.setFavorite(z);
        if (SdkConfig.atLeast(SdkConfig.GED.Q) && ((isCopyMode(i10) || isDifferentStorage(i10)) && fileItemInterface.getGroupType() == GroupType.SIMILAR.value)) {
            favorite.clearGroupInfo();
        }
        if (fileItemInterface.isVideo()) {
            favorite.setVideoInfo(fileItemInterface.getFileDuration(), fileItemInterface.getResolution(), fileItemInterface.is360Video()).setVideoRecordingInfo(fileItemInterface.getRecordingMode(), fileItemInterface.getRecordingType());
        }
        if (fileItemInterface.isCloudOnly()) {
            favorite.setCloudData(fileItemInterface.getCloudData2()).setCloudMediaType(fileItemInterface.getMediaType()).setCloudThumbPath((String) pair2.second);
        }
        return favorite.build();
    }

    private ContentValues getGroupMediaMoveValues(FileItemInterface fileItemInterface, Pair<String, String> pair, String str, int i2) {
        String str2 = (String) pair.first;
        ContentValuesBuilder hide = createContentValuesBuilder().setPath(str2).setRelativePath(str2).setHide(-1);
        if (fileItemInterface.isCloudOnly()) {
            hide.setCloudData(fileItemInterface.getCloudData2()).setCloudOriginalSize(fileItemInterface.getCloudOriginalSize());
        }
        return hide.build();
    }

    private boolean isCopyMode(int i2) {
        if ((i2 & 4) != 0) {
            return true;
        }
        return false;
    }

    private boolean isDifferentStorage(int i2) {
        if ((i2 & 16) != 0) {
            return true;
        }
        return false;
    }

    private boolean isMoveMode(int i2) {
        if ((i2 & 2) != 0) {
            return true;
        }
        return false;
    }

    public abstract ContentValuesBuilder createContentValuesBuilder();

    public ContentValues getCopyValues(FileItemInterface fileItemInterface, String str, int i2) {
        boolean z;
        DetailsData of2 = DetailsData.of(fileItemInterface);
        ContentValuesBuilder captureInfo = createContentValuesBuilder().setPath(str).setFileSize(fileItemInterface.getFileSize()).setMediaInfo(fileItemInterface.getMimeType(), fileItemInterface.getWidth(), fileItemInterface.getHeight(), fileItemInterface.getOrientation()).setDateInfo(fileItemInterface.getDateTaken(), fileItemInterface.getDateAdded(), fileItemInterface.getDateModified()).setLocationInfo(fileItemInterface.getLatitude(), fileItemInterface.getLongitude()).setSefInfo(fileItemInterface.getSefFileType(), fileItemInterface.getSefFileSubType(), fileItemInterface.getSefFileTypes()).setDrm(fileItemInterface.isDrm()).setCaptureInfo(of2.capturedUrl, of2.capturedApp);
        if (!isMoveMode(i2) || !fileItemInterface.isFavourite()) {
            z = false;
        } else {
            z = true;
        }
        ContentValuesBuilder favorite = captureInfo.setFavorite(z);
        if (fileItemInterface.isVideo()) {
            favorite.setVideoInfo(fileItemInterface.getFileDuration(), fileItemInterface.getResolution(), fileItemInterface.is360Video()).setVideoRecordingInfo(fileItemInterface.getRecordingMode(), fileItemInterface.getRecordingType());
        }
        if (fileItemInterface.isCloudOnly()) {
            favorite.setCloudData(fileItemInterface.getCloudData2()).setCloudMediaType(fileItemInterface.getMediaType()).setCloudThumbPath(FileUtils.getNewFilePath(fileItemInterface.getCloudThumbPath())).setCloudInfo(fileItemInterface.getCloudHash(), fileItemInterface.getCloudOriginalSize());
        }
        return favorite.build();
    }

    public final Uri getFilesUri(String str) {
        return MediaUri.getFilesUri(str);
    }

    public ContentValues[] getGroupMediaCopyValueArray(List<FileItemInterface> list, List<Pair<String, String>> list2, String str, int i2, int i7) {
        int bucketId = FileUtils.getBucketId(str);
        ArrayList arrayList = new ArrayList();
        int i8 = 0;
        while (i8 < list.size()) {
            ContentValuesFactory contentValuesFactory = this;
            arrayList.add(contentValuesFactory.getGroupMediaCopyValues(list.get(i8), list2.get(i8), str, bucketId, i2, i7));
            i8++;
            this = contentValuesFactory;
        }
        return (ContentValues[]) arrayList.toArray(new ContentValues[0]);
    }

    public ContentProviderOperation[] getGroupMediaMoveValueArray(List<FileItemInterface> list, List<Pair<String, String>> list2, String str, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < list.size(); i7++) {
            FileItemInterface fileItemInterface = list.get(i7);
            arrayList.add(ContentProviderOperation.newUpdate(getUriFromItem(fileItemInterface, (String) list2.get(i7).first)).withValues(getGroupMediaMoveValues(fileItemInterface, list2.get(i7), str, i2)).build());
        }
        return (ContentProviderOperation[]) arrayList.toArray(new ContentProviderOperation[0]);
    }

    public ContentValues getMoveValues(FileItemInterface fileItemInterface, String str, int i2) {
        ContentValuesBuilder hide = createContentValuesBuilder().setPath(str).setRelativePath(str).setHide(-1);
        if (fileItemInterface.isCloudOnly()) {
            hide.setCloudData(fileItemInterface.getCloudData2()).setCloudOriginalSize(fileItemInterface.getCloudOriginalSize());
        }
        return hide.build();
    }

    public abstract Uri getUriFromItem(FileItemInterface fileItemInterface, String str);
}
