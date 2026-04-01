package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TestUri implements UriInterface {
    private static final Uri TEST_URI = Uri.parse("content://test/");

    public Uri getCloudWatchUri() {
        return TEST_URI;
    }

    public Uri getDirectories() {
        return TEST_URI;
    }

    public Uri getFilesUri() {
        return TEST_URI;
    }

    public Uri getGroupTableUri() {
        return TEST_URI;
    }

    public Uri getImageUri() {
        return TEST_URI;
    }

    public Uri getImageWatchUri() {
        return TEST_URI;
    }

    public Uri getRawQueryUri(String str) {
        return TEST_URI;
    }

    public Uri getScloudDeleted() {
        return TEST_URI;
    }

    public Uri getScloudPolicy() {
        return TEST_URI;
    }

    public Uri getSecCloudUri() {
        return TEST_URI;
    }

    public Uri getSecMediaUri() {
        return TEST_URI;
    }

    public Uri getSecPickerUri() {
        return TEST_URI;
    }

    public String getSecPickerUriString() {
        return TEST_URI.toString();
    }

    public Uri getVideoUri() {
        return TEST_URI;
    }

    public Uri getVideoWatchUri() {
        return TEST_URI;
    }

    public boolean isSecMediaUri(String str) {
        if (str == null) {
            return false;
        }
        if (Pattern.matches("^content://(|\\d+@)secmedia/((images|video)/)?media/.*", str) || Pattern.matches("^content://(|\\d+@)secmedia/gallery/picker/(image|video)/.*", str)) {
            return true;
        }
        return false;
    }

    public boolean matches(String str) {
        if (Pattern.matches("^content://(|\\d+@)media/(external(_primary)?|[a-z0-9]{4}-[a-z0-9]{4})/(images|video|file|downloads)/.*", str) || Pattern.matches("^content://(|\\d+@)secmedia/gallery/picker/(image|video)/.*", str) || Pattern.matches("^content://(|\\d+@)secmedia/(images|video)/media/.*", str)) {
            return true;
        }
        return false;
    }
}
