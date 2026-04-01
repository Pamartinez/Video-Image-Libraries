package com.samsung.android.scloud.cloudagent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.scloud.cloudagent.exception.CloudException;
import com.samsung.android.scloud.cloudagent.exception.CloudLocalStorageFullException;
import com.samsung.android.scloud.cloudagent.exception.CloudNetworkException;
import com.samsung.android.scloud.cloudagent.exception.CloudRecordNotFoundException;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CloudStore {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class API {
        public static void cancelDownloadOriginalFile(Context context) {
            context.getContentResolver().call(Files.getContentUri(), "cancel_download_original", (String) null, (Bundle) null);
        }

        public static int clearFilesWithBlocking(Context context, ArrayList<String> arrayList) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "clear_with_blocking", (String) null, bundle);
            if (call == null) {
                return 200;
            }
            return call.getInt("CLEAR_RESULT");
        }

        public static int copyFileWithBlocking(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            String parseListAsString2 = CloudStore.parseListAsString(arrayList2);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            bundle.putString("destination_path", parseListAsString2);
            bundle.putBoolean("is_src_file_delete", false);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "copy_move_with_blocking", (String) null, bundle);
            if (call == null) {
                return 200;
            }
            return call.getInt("COPY_MOVE_RESULT");
        }

        public static boolean deleteFileWithBlocking(Context context, ArrayList<String> arrayList, boolean z) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            bundle.putBoolean("is_force_clear", z);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "delete_with_blocking", (String) null, bundle);
            if (call == null) {
                return false;
            }
            return call.getBoolean("DELETE_RESULT");
        }

        public static ArrayList<Uri> downloadOriginalFileWithBlocking(Context context, ArrayList<String> arrayList, String str, boolean z, boolean z3) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("filekeys", arrayList);
            bundle.putString("download_path", str);
            bundle.putBoolean("with_ui", z);
            bundle.putBoolean("do_media_scan", z3);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "download_original", (String) null, bundle);
            if (call == null) {
                return null;
            }
            int i2 = call.getInt("exception", 0);
            if (i2 == 0) {
                return call.getParcelableArrayList("DOWNLOAD_FILE_URI_LIST");
            }
            throw getCloudException(i2);
        }

        public static Bundle getCloudAgentVersion(Context context) {
            return context.getContentResolver().call(Files.getContentUri(), "cloud_store_version", (String) null, (Bundle) null);
        }

        private static CloudException getCloudException(int i2) {
            switch (i2) {
                case 102:
                    return new CloudLocalStorageFullException();
                case 103:
                case 104:
                    return new CloudNetworkException();
                case 105:
                    return new CloudRecordNotFoundException();
                default:
                    return new CloudException();
            }
        }

        public static String getDownloadURL(Context context, Uri uri) {
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "get_download_URL", uri.toString(), (Bundle) null);
            if (call == null) {
                return null;
            }
            String string = call.getString("DOWNLOAD_URL");
            if (string != null || call.getSerializable("exception") == null) {
                return string;
            }
            throw new ClassCastException();
        }

        public static String getStreamingURL(Context context, Uri uri) {
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "get_streaming_URL", uri.toString(), (Bundle) null);
            if (call == null) {
                return null;
            }
            String string = call.getString("STREAMING_URL");
            if (string != null || call.getSerializable("exception") == null) {
                return string;
            }
            throw new ClassCastException();
        }

        public static boolean isCloudAvailable(Context context) {
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "cloud_available", (String) null, (Bundle) null);
            if (call != null) {
                return call.getBoolean("cloudAvailable");
            }
            return false;
        }

        public static boolean isCloudAvailableExceptAccount(Context context) {
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "cloud_available_except_account", (String) null, (Bundle) null);
            if (call != null) {
                return call.getBoolean("cloudAvailable");
            }
            return false;
        }

        public static boolean isSyncOffBucket(Context context, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("bucket_path", str);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "is_sync_off_bucket", (String) null, bundle);
            if (call == null) {
                return false;
            }
            return call.getBoolean("IS_SYNC_OFF_BUCKET");
        }

        public static int moveFileWithBlocking(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            String parseListAsString2 = CloudStore.parseListAsString(arrayList2);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            bundle.putString("destination_path", parseListAsString2);
            bundle.putBoolean("is_src_file_delete", true);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "copy_move_with_blocking", (String) null, bundle);
            if (call == null) {
                return 200;
            }
            return call.getInt("COPY_MOVE_RESULT");
        }

        public static int moveFileWithNoneBlocking(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            String parseListAsString2 = CloudStore.parseListAsString(arrayList2);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            bundle.putString("destination_path", parseListAsString2);
            bundle.putBoolean("is_src_file_delete", true);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "copy_move_with_none_blocking", (String) null, bundle);
            if (call == null) {
                return 200;
            }
            return call.getInt("COPY_MOVE_RESULT");
        }

        public static int revertFilesWithBlocking(Context context, ArrayList<String> arrayList) {
            String parseListAsString = CloudStore.parseListAsString(arrayList);
            Bundle bundle = new Bundle();
            bundle.putString("filekeys", parseListAsString);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "revert_delete_with_blocking", (String) null, bundle);
            if (call == null) {
                return 200;
            }
            return call.getInt("REVERT_RESULT");
        }

        public static boolean setCloudSettingsValue(Context context, String str, int i2) {
            Bundle bundle = new Bundle();
            bundle.putString("key", str);
            bundle.putInt("value", i2);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "set_cloud_settings_value", (String) null, bundle);
            if (call != null) {
                return call.getBoolean("RESULT_SYNC_ENABLE");
            }
            return false;
        }

        public static boolean setFavoriteWithBlocking(Context context, String str, String str2, int i2) {
            Bundle bundle = new Bundle();
            bundle.putString("photokey", str);
            bundle.putString("localpath", str2);
            bundle.putInt(MediaApiContract.Parameter.FAVORITE, i2);
            Bundle call = context.getContentResolver().call(Files.getContentUri(), "set_favorite_with_blocking", (String) null, bundle);
            if (call == null) {
                return false;
            }
            return call.getBoolean("FAVORITE_RESULT");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Files {
        public static Uri getContentUri() {
            return Uri.parse("content://com.samsung.android.scloud.cloudagent/data/cloudfiles");
        }
    }

    public static String parseListAsString(ArrayList<String> arrayList) {
        String str = "";
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                str = C0212a.B(str, it.next(), NumericEnum.SEP);
            }
        }
        return str;
    }
}
