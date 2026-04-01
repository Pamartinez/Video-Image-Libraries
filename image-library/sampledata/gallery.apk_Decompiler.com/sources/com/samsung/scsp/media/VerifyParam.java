package com.samsung.scsp.media;

import android.os.ParcelFileDescriptor;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.MediaConstants;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VerifyParam {
    public static void checkValidForDeleteData(List<Media> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toDeleteIdList is invalid.");
        }
    }

    public static void checkValidForDownloadBinary(String str, String str2, MediaConstants.FileType fileType) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (StringUtil.isEmpty(str2)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "absoluteFilePath is invalid.");
        } else if (fileType == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "fileType is invalid.");
        }
    }

    public static void checkValidForDownloadBinaryWithFD(String str, ParcelFileDescriptor parcelFileDescriptor, MediaConstants.FileType fileType) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (parcelFileDescriptor == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "parcelFileDescriptor is null.");
        } else if (fileType != MediaConstants.FileType.ORIGINAL) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "fileType is invalid.");
        }
    }

    public static void checkValidForDownloadHDVideo(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        }
    }

    public static void checkValidForDownloadItemOriginalBinary(Media media, String str) {
        if (media == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "media is invalid.");
        } else if (StringUtil.isEmpty(media.photoId)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (StringUtil.isEmpty(media.originalBinaryHash)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "originalBinaryHash is invalid.");
        } else if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "absoluteFilePath is invalid.");
        }
    }

    public static void checkValidForExtendedDeleteData(MediaExtended mediaExtended) {
        if (mediaExtended == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toUpdateExtend is invalid.");
        } else if (StringUtil.isEmpty(mediaExtended.extId)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "extId is invalid.");
        } else if (StringUtil.isEmpty(mediaExtended.photoId)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (mediaExtended.clientTimestamp == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "clientTimestamp is invalid.");
        }
    }

    public static void checkValidForExtendedDeleteDataSet(List<MediaExtended> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "mediaExtendedList is invalid.");
        }
    }

    public static void checkValidForExtendedGetChanges(long j2) {
        if (j2 < 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "modifiedAfter is invalid.");
        }
    }

    public static void checkValidForExtendedGetDataSet(List<MediaExtended> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "mediaExtendedList is invalid.");
        }
    }

    public static void checkValidForExtendedUpdateData(MediaExtended mediaExtended) {
        if (mediaExtended == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toUpdateExtend is invalid.");
        } else if (StringUtil.isEmpty(mediaExtended.extId)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "extId is invalid.");
        } else if (StringUtil.isEmpty(mediaExtended.photoId)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        }
    }

    public static void checkValidForExtendedUploadData(List<MediaExtended> list, String str) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toUploadMedia is invalid.");
        } else if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        }
    }

    public static void checkValidForGetChanges(MediaConstants.MediaType mediaType) {
        if (mediaType == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "mediaType is invalid.");
        }
    }

    public static void checkValidForGetData(List<String> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoIdList is invalid.");
        }
    }

    public static void checkValidForGetDownloadUrl(String str, MediaConstants.FileType fileType) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (fileType == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "fileType is invalid.");
        }
    }

    public static void checkValidForGetLatestList(MediaConstants.MediaType mediaType) {
        if (mediaType == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "mediaType is invalid.");
        }
    }

    public static void checkValidForGetStream(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        }
    }

    public static void checkValidForGetUrlHDVideo(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        }
    }

    public static void checkValidForRestorePhotos(List<Media> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "mediaList is invalid.");
        }
        for (Media next : list) {
            if (StringUtil.isEmpty(next.photoId)) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
            } else if (next.clientTimestamp == null) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "clientTimestamp is invalid.");
            }
        }
    }

    public static void checkValidForRevertData(Media media) {
        if (media == null || media.photoId == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toRevertMedia is invalid.");
        } else if (!StringUtil.isEmpty(media.originalBinaryHash)) {
            checkValidGroup(media);
        } else {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toRevertMedia has no itemOriginal hash.");
        }
    }

    public static void checkValidForUpdateData(Media media) {
        if (media == null || media.photoId == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toUpdateMedia is invalid.");
        }
        checkValidGroup(media);
    }

    public static void checkValidForUpdateLocation(String str, String str2, String str3) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (StringUtil.isEmpty(str2)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "latitude is invalid.");
        } else if (StringUtil.isEmpty(str3)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "longitude is invalid.");
        }
    }

    public static void checkValidForUpdateOrientation(String str, String str2) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "photoId is invalid.");
        } else if (StringUtil.isEmpty(str2) || Integer.parseInt(str2) < 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "orientation is invalid.");
        }
    }

    public static void checkValidForUploadData(Media media) {
        if (media != null) {
            checkValidGroup(media);
            return;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "toUploadMedia is invalid.");
    }

    public static void checkValidForUploadFile(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "filePath is invalid.");
        }
    }

    public static void checkValidForUploadSettingRecords(List<RecordItem> list) {
        if (list == null || list.size() == 0) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "recordItems is invalid.");
        } else if (list.size() > 100) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "recordItems contain up to 100 items.");
        }
    }

    private static void checkValidGroup(Media media) {
        Integer num;
        Media.Group group = media.group;
        if (group != null) {
            Integer num2 = group.type;
            if (num2 == null) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Type of group is null");
            } else if (num2.intValue() == 1 || media.group.type.intValue() == 3 || media.group.type.intValue() == 4) {
                Integer num3 = media.group.id;
                if (num3 == null || num3.intValue() == 0) {
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Id of group is invalid");
                } else if (media.group.type.intValue() == 1 && !media.group.id.equals(media.burstshotId)) {
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Type of group is 1, but id of group is not same with burstshot id");
                } else if (media.group.type.intValue() == 3 && (num = media.burstshotId) != null && num.intValue() != 0) {
                    throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Type of group is 3, but burstshotId is not 0");
                }
            } else {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Type of group is not 1 or 3");
            }
        }
    }
}
