package com.samsung.android.gallery.database.dbtype;

import Ba.h;
import android.graphics.RectF;
import android.net.Uri;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface FileItemInterface extends Cloneable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CategoryHolder {
        static final HashSet<String> categories;
        static final HashSet<String> creatures = new HashSet<>(List.of("People", "Pet"));
        static final HashSet<String> scenes;
        static final HashSet<String> scenesOrCreature;

        static {
            HashSet<String> hashSet = new HashSet<>(List.of("Things Scenery", "Scenery", "Things", "Documents"));
            scenes = hashSet;
            scenesOrCreature = new HashSet<String>(hashSet) {
                {
                    addAll(CategoryHolder.creatures);
                }
            };
            categories = new HashSet<String>(hashSet) {
                {
                    add("Location");
                    add("My tags");
                }
            };
        }
    }

    /* access modifiers changed from: private */
    static /* synthetic */ void lambda$cloneTags$0(Map map, String str, Object obj) {
        if (obj instanceof Copyable) {
            map.put(str, ((Copyable) obj).copyOf());
        } else {
            map.put(str, obj);
        }
    }

    void cloneTags(FileItemInterface fileItemInterface) {
        Map<String, Object> tags = tags();
        tags.clear();
        if (fileItemInterface != null) {
            fileItemInterface.tags().forEach(new h(25, tags));
        }
    }

    int getAlbumID() {
        return 0;
    }

    String getAlbumPath() {
        String dataPath = getDataPath();
        if (dataPath == null || !dataPath.startsWith("/data/sec/cloud/")) {
            return dataPath;
        }
        return getCloudServerPath();
    }

    int getBestImage();

    int getBucketID();

    byte[] getByteArray() {
        return (byte[]) getTag("data-stream");
    }

    String getCamModel() {
        return null;
    }

    String getCategory() {
        return null;
    }

    String getCloudData2() {
        return null;
    }

    String getCloudHash();

    String getCloudOriginalBinaryHash();

    long getCloudOriginalBinarySize();

    long getCloudOriginalSize();

    int getCloudRevision();

    String getCloudServerId();

    String getCloudServerPath();

    String getCloudThumbPath();

    long getCloudTimestamp() {
        return 0;
    }

    int getComplexHashCode() {
        return 0;
    }

    int getCount() {
        return 0;
    }

    RectF getCropRectRatio() {
        return null;
    }

    ArrayList<RectF> getCropRectRatioList() {
        return null;
    }

    Supplier<Uri> getCustomUriProvider() {
        return null;
    }

    String getDataPath() {
        return getPath();
    }

    long getDateAdded() {
        return 0;
    }

    long getDateLocal() {
        return 0;
    }

    long getDateModified();

    long getDateTaken();

    long getDirectorsViewGroupId() {
        return 0;
    }

    String getDiskCacheKey();

    String getDisplayName() {
        return "";
    }

    ExifTag getExifTag() {
        return null;
    }

    Object getExtra(ExtrasID extrasID) {
        return getTag(extrasID.name());
    }

    int getFileDuration();

    int getFileHashCode() {
        return 0;
    }

    long getFileId();

    long getFileSize();

    long getGroupMediaId();

    String getGroupMode();

    int getGroupType();

    int getHeight();

    InputStream getInputStream() {
        return null;
    }

    double getLatitude();

    double getLongitude();

    long getMediaId();

    MediaType getMediaType();

    String getMimeType();

    MimeType getMimeTypeEnum() {
        return MimeType.ETC;
    }

    int getOrientation();

    int getOrientationTag();

    String getOriginalFileHash() {
        return null;
    }

    String getOwnerPackage() {
        return null;
    }

    String getPath();

    int getRecordingMode();

    int getRecordingType();

    String getReferencePath() {
        if (isCloudOnly()) {
            return getCloudServerPath();
        }
        return getDataPath();
    }

    String getRelativePath();

    String getResolution();

    int getSefFileSubType();

    int getSefFileType();

    String getSefFileTypes();

    String getShotMode() {
        return null;
    }

    int getSimpleHashCode() {
        return 0;
    }

    StorageType getStorageType();

    String getSubCategory() {
        return null;
    }

    Object getTag(String str) {
        return tags().get(str);
    }

    int getThumbnailOrientation() {
        if (isVideo() || isBroken()) {
            return 0;
        }
        return getOrientation();
    }

    long getTimeStamp();

    String getTitle();

    int getVideoColorFormat() {
        return 0;
    }

    String getVolumeName();

    int getWidth();

    XmpType getXmpType() {
        return XmpType.XmpNone;
    }

    boolean hasFilterAndTone() {
        return false;
    }

    boolean hasPortraitsEffect() {
        return false;
    }

    boolean hasSefFileTypes(int i2);

    boolean is360Image() {
        if ("360_photo".equals(getShotMode()) || XmpType.XmpImage360 == getXmpType()) {
            return true;
        }
        return false;
    }

    boolean is360Video();

    boolean is3dCapture() {
        String shotMode = getShotMode();
        if (shotMode == null || !ShotModeType.is3dCapture(shotMode)) {
            return false;
        }
        return true;
    }

    boolean isBmp() {
        if (getMimeTypeEnum() == MimeType.BMP) {
            return true;
        }
        return false;
    }

    boolean isBroken() {
        return false;
    }

    boolean isBurstShot() {
        return "burst_shot".equals(getGroupMode());
    }

    boolean isCamModelSamsung();

    boolean isCategory() {
        String category = getCategory();
        if (category == null || !CategoryHolder.categories.contains(category)) {
            return false;
        }
        return true;
    }

    boolean isCloud() {
        StorageType storageType = getStorageType();
        if (storageType == StorageType.Cloud || storageType == StorageType.LocalCloud) {
            return true;
        }
        return false;
    }

    boolean isCloudOnly() {
        if (getStorageType() == StorageType.Cloud) {
            return true;
        }
        return false;
    }

    boolean isCreature() {
        String category = getCategory();
        if (category == null || !CategoryHolder.creatures.contains(category)) {
            return false;
        }
        return true;
    }

    boolean isCustomCover() {
        return false;
    }

    boolean isDng() {
        if (getMimeTypeEnum() == MimeType.DNG) {
            return true;
        }
        return false;
    }

    boolean isDrm();

    boolean isFavourite();

    boolean isGif() {
        if (getMimeTypeEnum() == MimeType.GIF) {
            return true;
        }
        return false;
    }

    boolean isGroupShot() {
        String groupMode = getGroupMode();
        if (groupMode == null) {
            return false;
        }
        if ("burst_shot".equals(groupMode) || "Similar photo".equals(groupMode) || "Single Taken".equals(groupMode)) {
            return true;
        }
        return false;
    }

    boolean isHdr10Video();

    boolean isHdrVideo() {
        if (isHdr10Video() || isHlgVideo()) {
            return true;
        }
        return false;
    }

    boolean isHeif() {
        MimeType mimeTypeEnum = getMimeTypeEnum();
        if (mimeTypeEnum == MimeType.HEIF || mimeTypeEnum == MimeType.HEIC) {
            return true;
        }
        return false;
    }

    boolean isHlgVideo() {
        return false;
    }

    boolean isImage() {
        if (getMediaType() == MediaType.Image) {
            return true;
        }
        return false;
    }

    boolean isJpeg() {
        if (getMimeTypeEnum() == MimeType.JPG) {
            return true;
        }
        return false;
    }

    boolean isLocal() {
        StorageType storageType = getStorageType();
        if (storageType == StorageType.Local || storageType == StorageType.LocalCloud) {
            return true;
        }
        return false;
    }

    boolean isLocalCloud() {
        if (getStorageType() == StorageType.LocalCloud) {
            return true;
        }
        return false;
    }

    boolean isLocalOnly() {
        if (getStorageType() == StorageType.Local) {
            return true;
        }
        return false;
    }

    boolean isMotionPhoto() {
        if ("motion_photo".equals(getShotMode())) {
            return true;
        }
        if (!PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO || XmpType.XmpMotionPhoto != getXmpType()) {
            return false;
        }
        return true;
    }

    boolean isMovie() {
        MimeType mimeTypeEnum = getMimeTypeEnum();
        if (mimeTypeEnum == MimeType.GIF || mimeTypeEnum == MimeType.WEBP) {
            return true;
        }
        return false;
    }

    boolean isMtp() {
        if (getStorageType() == StorageType.Mtp) {
            return true;
        }
        return false;
    }

    boolean isMyQuery() {
        return "My query".equals(getCategory());
    }

    boolean isNonMovieClip();

    boolean isPanoramaShot() {
        return "panorama".equals(getShotMode());
    }

    boolean isPanoramic() {
        return false;
    }

    boolean isPeople() {
        return "People".equals(getCategory());
    }

    boolean isPet() {
        return "Pet".equals(getCategory());
    }

    boolean isPhotoHdrCandidate() {
        return false;
    }

    boolean isPhotoHdrSupported() {
        if (isJpeg() || isHeif()) {
            return true;
        }
        return false;
    }

    boolean isPng() {
        if (getMimeTypeEnum() == MimeType.PNG) {
            return true;
        }
        return false;
    }

    boolean isPostProcessing();

    boolean isPrivateItem() {
        if (getStorageType() == StorageType.PrivateItem) {
            return true;
        }
        return false;
    }

    boolean isQuramDecodable() {
        return false;
    }

    boolean isRawImage() {
        MimeType mimeTypeEnum = getMimeTypeEnum();
        if (mimeTypeEnum == MimeType.RAW || mimeTypeEnum == MimeType.DNG) {
            return true;
        }
        return false;
    }

    boolean isRectThumbCategory() {
        String category = getCategory();
        if ("Location".equals(category) || "Things Scenery".equals(category)) {
            return true;
        }
        return false;
    }

    boolean isRemasterSaved() {
        return false;
    }

    boolean isScenes() {
        String category = getCategory();
        if (category == null || !CategoryHolder.scenes.contains(category)) {
            return false;
        }
        return true;
    }

    boolean isScenesOrCreature(String str) {
        if (str == null || !CategoryHolder.scenesOrCreature.contains(str)) {
            return false;
        }
        return true;
    }

    boolean isSharing() {
        if (getStorageType() == StorageType.Sharing) {
            return true;
        }
        return false;
    }

    boolean isShotMode(String str) {
        return str.equals(getShotMode());
    }

    boolean isSimilarShot() {
        return "Similar photo".equals(getGroupMode());
    }

    boolean isSingleTakenShot() {
        return "Single Taken".equals(getGroupMode());
    }

    boolean isStream() {
        if (getStorageType() == StorageType.Stream) {
            return true;
        }
        return false;
    }

    boolean isTemporary() {
        if (getStorageType() == StorageType.TempItem) {
            return true;
        }
        return false;
    }

    boolean isTransparent();

    boolean isTrash() {
        return false;
    }

    boolean isUriItem() {
        if (getStorageType() == StorageType.UriItem) {
            return true;
        }
        return false;
    }

    boolean isVideo() {
        if (getMediaType() == MediaType.Video) {
            return true;
        }
        return false;
    }

    boolean isWebp() {
        if (getMimeTypeEnum() == MimeType.WEBP) {
            return true;
        }
        return false;
    }

    void setExtra(ExtrasID extrasID, Object obj) {
        if (obj != null) {
            tags().put(extrasID.name(), obj);
        }
    }

    void setTag(String str, Object obj) {
        if (obj == null) {
            tags().remove(str);
        } else {
            tags().put(str, obj);
        }
    }

    Map<String, Object> tags();

    <T> T getExtra(ExtrasID extrasID, T t) {
        return getTag(extrasID.name(), t);
    }

    <T> T getTag(String str, T t) {
        return tags().getOrDefault(str, t);
    }

    void setXmpType(XmpType xmpType) {
    }
}
