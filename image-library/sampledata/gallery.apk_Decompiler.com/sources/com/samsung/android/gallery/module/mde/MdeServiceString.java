package com.samsung.android.gallery.module.mde;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeServiceString {
    private static final HashMap<MediaType, Integer> REQUEST_ADD_CONTENTS_NETWORK_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_upload_image_network_status));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_upload_video_network_status));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_upload_images_network_status));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_upload_videos_network_status));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_upload_items_network_status));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_ADD_CONTENTS_UNKNOWN_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_upload_image));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_upload_video));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_upload_images));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_upload_videos));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_upload_items));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DELETE_CONTENTS_NETWORK_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_remove_image_network_status));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_remove_video_network_status));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_remove_images_network_status));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_remove_videos_network_status));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_remove_items_network_status));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DELETE_CONTENTS_RUNNING = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.removing_image));
            put(MediaType.Video, Integer.valueOf(R$string.removing_video));
            put(MediaType.Images, Integer.valueOf(R$string.removing_images));
            put(MediaType.Videos, Integer.valueOf(R$string.removing_videos));
            put(MediaType.Media, Integer.valueOf(R$string.removing_items));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DELETE_CONTENTS_UNKNOWN_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_remove_image));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_remove_video));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_remove_images));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_remove_videos));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_remove_items));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DELETE_FAIL_TOAST_BEFORE_UPLOADING = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            MediaType mediaType = MediaType.Image;
            int i2 = R$string.delete_failure_message_about_some_images_before_uploading;
            put(mediaType, Integer.valueOf(i2));
            MediaType mediaType2 = MediaType.Video;
            int i7 = R$string.delete_failure_message_about_some_videos_before_uploading;
            put(mediaType2, Integer.valueOf(i7));
            put(MediaType.Images, Integer.valueOf(i2));
            put(MediaType.Videos, Integer.valueOf(i7));
            put(MediaType.Media, Integer.valueOf(R$string.delete_failure_message_about_some_items_before_uploading));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DOWNLOAD_CONTENTS_NETWORK_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_download_image_network_status));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_download_video_network_status));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_download_images_network_status));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_download_videos_network_status));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_download_items_network_status));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DOWNLOAD_CONTENTS_UNKNOWN_ERROR = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.could_not_download_image));
            put(MediaType.Video, Integer.valueOf(R$string.could_not_download_video));
            put(MediaType.Images, Integer.valueOf(R$string.could_not_download_images));
            put(MediaType.Videos, Integer.valueOf(R$string.could_not_download_videos));
            put(MediaType.Media, Integer.valueOf(R$string.could_not_download_items));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_DOWNLOAD_FAIL_TOAST_BEFORE_UPLOADING = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            MediaType mediaType = MediaType.Image;
            int i2 = R$string.download_failure_message_about_some_images_before_uploading;
            put(mediaType, Integer.valueOf(i2));
            MediaType mediaType2 = MediaType.Video;
            int i7 = R$string.download_failure_message_about_some_videos_before_uploading;
            put(mediaType2, Integer.valueOf(i7));
            put(MediaType.Images, Integer.valueOf(i2));
            put(MediaType.Videos, Integer.valueOf(i7));
            put(MediaType.Media, Integer.valueOf(R$string.download_failure_message_about_some_items_before_uploading));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_MOVE_TO_TRASH_CONTENTS_RUNNING = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.moving_image_to_the_trash));
            put(MediaType.Video, Integer.valueOf(R$string.moving_video_to_the_trash));
            put(MediaType.Images, Integer.valueOf(R$string.moving_images_to_the_trash));
            put(MediaType.Videos, Integer.valueOf(R$string.moving_videos_to_the_trash));
            put(MediaType.Media, Integer.valueOf(R$string.moving_items_to_the_trash));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_RESTORE_CONTENTS_RUNNING = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            MediaType mediaType = MediaType.Image;
            int i2 = R$string.restoring;
            put(mediaType, Integer.valueOf(i2));
            put(MediaType.Video, Integer.valueOf(i2));
            put(MediaType.Images, Integer.valueOf(i2));
            put(MediaType.Videos, Integer.valueOf(i2));
            put(MediaType.Media, Integer.valueOf(i2));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_SHARED_ITEMS_COMPLETE_TOAST = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.image_shared_tab_here_to_view_shared_album));
            put(MediaType.Video, Integer.valueOf(R$string.video_shared_tab_here_to_view_shared_album));
            put(MediaType.Images, Integer.valueOf(R$string.images_shared_tab_here_to_view_shared_album));
            put(MediaType.Videos, Integer.valueOf(R$string.videos_shared_tab_here_to_view_shared_album));
            put(MediaType.Media, Integer.valueOf(R$string.item_shared_tap_here_to_view_shared_album));
        }
    };
    private static final HashMap<MediaType, Integer> REQUEST_SHARED_ITEMS_PREPARING_TOAST = new HashMap<MediaType, Integer>() {
        {
            put(MediaType.Unsupported, -1);
            put(MediaType.Image, Integer.valueOf(R$string.preparing_upload_image));
            put(MediaType.Video, Integer.valueOf(R$string.preparing_upload_video));
            put(MediaType.Images, Integer.valueOf(R$string.preparing_upload_images));
            put(MediaType.Videos, Integer.valueOf(R$string.preparing_upload_videos));
            put(MediaType.Media, Integer.valueOf(R$string.preparing_upload_items));
        }
    };

    public static int getAddNetworkError(MediaType mediaType) {
        return REQUEST_ADD_CONTENTS_NETWORK_ERROR.get(mediaType).intValue();
    }

    public static int getAddUnknownError(MediaType mediaType) {
        return REQUEST_ADD_CONTENTS_UNKNOWN_ERROR.get(mediaType).intValue();
    }

    public static int getDeleteNetworkError(MediaType mediaType) {
        return REQUEST_DELETE_CONTENTS_NETWORK_ERROR.get(mediaType).intValue();
    }

    public static int getDeleteRunning(MediaType mediaType) {
        return REQUEST_DELETE_CONTENTS_RUNNING.get(mediaType).intValue();
    }

    public static String getDeleteUnknownError(Context context, int i2) {
        return context.getResources().getQuantityString(R$plurals.could_not_remove_item, i2, new Object[]{Integer.valueOf(i2)});
    }

    public static int getDownloadNetworkError(MediaType mediaType) {
        return REQUEST_DOWNLOAD_CONTENTS_NETWORK_ERROR.get(mediaType).intValue();
    }

    public static int getDownloadUnknownError(MediaType mediaType) {
        return REQUEST_DOWNLOAD_CONTENTS_UNKNOWN_ERROR.get(mediaType).intValue();
    }

    public static String getFailedDeleteMessageBeforeUploading(Context context, MediaType mediaType, int i2, int i7) {
        return context.getResources().getString(REQUEST_DELETE_FAIL_TOAST_BEFORE_UPLOADING.get(mediaType).intValue(), new Object[]{Integer.valueOf(i2 - i7), Integer.valueOf(i2), Integer.valueOf(i7)});
    }

    public static String getFailedDownloadMessageBeforeUploading(Context context, MediaType mediaType, int i2, int i7) {
        return context.getResources().getString(REQUEST_DOWNLOAD_FAIL_TOAST_BEFORE_UPLOADING.get(mediaType).intValue(), new Object[]{Integer.valueOf(i2 - i7), Integer.valueOf(i2), Integer.valueOf(i7)});
    }

    public static String getMessageForQuotaExceededError(Context context) {
        if (context != null) {
            return context.getResources().getString(R$string.not_enough_space_in_shared_album_storage);
        }
        return null;
    }

    public static int getMoveToTrashRunning(MediaType mediaType) {
        return REQUEST_MOVE_TO_TRASH_CONTENTS_RUNNING.get(mediaType).intValue();
    }

    public static int getRestoreRunning(MediaType mediaType) {
        return REQUEST_RESTORE_CONTENTS_RUNNING.get(mediaType).intValue();
    }

    public static int getSharingItemCompleted(MediaType mediaType) {
        return REQUEST_SHARED_ITEMS_COMPLETE_TOAST.get(mediaType).intValue();
    }

    public static int getSharingItemPreparing(MediaType mediaType) {
        return REQUEST_SHARED_ITEMS_PREPARING_TOAST.get(mediaType).intValue();
    }
}
