package com.samsung.android.gallery.module.mde;

import A4.C0375j;
import S3.d;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeUploadingChecker {
    private static int getStringRes(List<FileItemInterface> list) {
        if (list.size() > 1) {
            return R$string.wait_for_the_selected_items_to_finish_uploading;
        }
        if (list.get(0).isMotionPhoto()) {
            return R$string.wait_for_the_motion_photo_to_finish_uploading;
        }
        if (list.get(0).isImage()) {
            return R$string.wait_for_the_image_to_finish_uploading;
        }
        return R$string.wait_for_the_video_to_finish_uploading;
    }

    public static boolean showToastIfUploading(Context context, FileItemInterface fileItemInterface, String str) {
        return showToastIfUploading(context, (List<FileItemInterface>) List.of(fileItemInterface), str);
    }

    public static boolean showToastIfUploading(Context context, List<FileItemInterface> list, String str) {
        if (context == null) {
            return false;
        }
        List list2 = (List) list.stream().filter(new d(5)).filter(new C0375j(19)).collect(Collectors.toList());
        if (list2.isEmpty()) {
            return false;
        }
        Log.she(str, "items are not all uploaded yet. #" + list2.size());
        Utils.showToast(context, context.getResources().getString(getStringRes(list)));
        return true;
    }
}
