package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import com.samsung.android.gallery.support.utils.FileType;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface UriInterface {
    public static final Pattern PATTERN_GED_MEDIA_AUTHORITY = Pattern.compile("^content://(|\\d+@)media/.*");

    String getCameraPppUriString() {
        return "content://com.samsung.android.camera.core2/files";
    }

    Uri getCloudWatchUri();

    Uri getDirectories();

    Uri getFilesUri();

    Uri getFilesUri(String str) {
        return getFilesUri();
    }

    Uri getGroupTableUri() {
        return unsupportedUri();
    }

    Uri getImageUri();

    Uri getImageWatchUri();

    Uri getLocationEditUri() {
        return unsupportedUri();
    }

    Uri getMediaSyncUri() {
        return unsupportedUri();
    }

    Uri getProperUri(String str) {
        if (FileType.isImage(str)) {
            return getImageUri();
        }
        if (FileType.isVideo(str)) {
            return getVideoUri();
        }
        return getFilesUri();
    }

    Uri getRawQueryUri(String str);

    Uri getScloudDeleted();

    Uri getScloudPolicy();

    Uri getSecCloudUri();

    Uri getSecMediaUri();

    Uri getSecMediaUri(boolean z) {
        return getSecMediaUri();
    }

    Uri getSecPickerUri();

    String getSecPickerUriString();

    Uri getSecTrashUri() {
        return null;
    }

    Uri getVideoUri();

    Uri getVideoWatchUri();

    boolean isCameraUri(String str) {
        if (str == null || !str.startsWith(getCameraPppUriString())) {
            return false;
        }
        return true;
    }

    boolean isGedMediaUri(String str) {
        if (str == null || str.contains("com.android.providers.media.photopicker")) {
            return false;
        }
        return PATTERN_GED_MEDIA_AUTHORITY.matcher(str).matches();
    }

    boolean isSecMediaUri(String str);

    boolean matches(String str);

    Uri unsupportedUri() {
        throw new UnsupportedOperationException();
    }
}
