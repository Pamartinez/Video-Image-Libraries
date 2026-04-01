package com.samsung.android.gallery.module.abstraction;

import A.a;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.content.FileProvider;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileProviderUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataSecPathHolder {
        static final ArrayList<Pair<String, String>> list = new ArrayList<Pair<String, String>>() {
            {
                for (String str : DataSecPathHolder.paths) {
                    add(new Pair(str, Integer.toHexString(str.hashCode()) + "/"));
                }
            }
        };
        static final String[] paths = {"/data/sec/gallery/secured/", "/data/sec/gallery/", "/data/sec/photoeditor/", "/data/sec/sems/", "/data/sec/cloud/", "/data/sec/"};
    }

    private static Uri adjustLegacyFileUri(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if ("content".equals(scheme)) {
                return parse;
            }
            if ("file".equals(scheme)) {
                return getUri(context, URLDecoder.decode(str, "UTF-8").replace("file://", ""));
            }
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getUriFromPath failed e="), "FileProviderUtil");
            return null;
        }
    }

    public static Uri getFileProviderUri(Context context, String str) {
        if (isPrivateStorage(str)) {
            return getLocalUriForFile(str);
        }
        Uri adjustLegacyFileUri = adjustLegacyFileUri(context, str);
        if (adjustLegacyFileUri == null || !TextUtils.isEmpty(adjustLegacyFileUri.getUserInfo())) {
            return adjustLegacyFileUri;
        }
        Uri.Builder buildUpon = adjustLegacyFileUri.buildUpon();
        return buildUpon.encodedAuthority(SeApiCompat.getMyUserId() + Log.TAG_SEPARATOR + adjustLegacyFileUri.getEncodedAuthority()).build();
    }

    private static Uri getLocalUriForFile(String str) {
        return LocalDatabase.getLocalUriForFile(toHiddenPath(str));
    }

    public static Uri getUri(Context context, String str) {
        return getUri(context, new File(str));
    }

    private static boolean isPrivateStorage(String str) {
        if (str == null || !str.startsWith("/data/sec")) {
            return false;
        }
        return true;
    }

    public static String toHiddenPath(String str) {
        Iterator<Pair<String, String>> it = DataSecPathHolder.list.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (str.startsWith((String) next.first)) {
                return str.replaceFirst((String) next.first, (String) next.second);
            }
        }
        return str;
    }

    public static String toReadablePath(String str) {
        Iterator<Pair<String, String>> it = DataSecPathHolder.list.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (str.startsWith((String) next.second)) {
                return str.replaceFirst((String) next.second, (String) next.first);
            }
        }
        return str;
    }

    public static Uri getUri(Context context, File file) {
        if (isPrivateStorage(file.getPath())) {
            return getLocalUriForFile(file.getPath());
        }
        return FileProvider.getUriForFile(context, "com.sec.android.gallery3d.fileprovider", file);
    }
}
