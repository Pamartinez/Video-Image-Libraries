package com.samsung.android.gallery.app.controller.story;

import T3.e;
import U3.g;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StorySaveCmd extends BaseCommand {
    private boolean checkItemUnavailable(MediaItem mediaItem) {
        if (mediaItem == null) {
            showFailToast();
            Log.w(this.TAG, "data error : mediaItem is null");
            return true;
        } else if (!isUnsupportedFormat(mediaItem)) {
            return false;
        } else {
            Utils.showToast(getApplicationContext(), (int) R.string.this_video_is_unsupported_format);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(boolean z, MediaItem mediaItem, List list, int i2, String str) {
        Object[] objArr;
        Object obj;
        System.currentTimeMillis();
        if (z) {
            objArr = StorySaveHelper.createInfoForStoryCover(getContext(), mediaItem, list);
        } else if (list == null || list.isEmpty()) {
            objArr = null;
        } else {
            objArr = StorySaveHelper.createInfoForStoryCard(mediaItem, list, i2);
        }
        if (objArr == null || objArr.length == 0 || (obj = objArr[0]) == null) {
            showFailToast();
            Log.w(this.TAG, "converting error : info is null or content bitmap is null.");
            return;
        }
        getBlackboard().publish("data://user/storyContentBitmap", (Bitmap) obj);
        getBlackboard().publish("data://user/selected", new MediaItem[]{mediaItem});
        getBlackboard().postEvent(EventMessage.obtain(1003));
        startService((String) objArr[1], (Rect) objArr[2], str, z);
    }

    private void startService(String str, Rect rect, String str2, boolean z) {
        boolean z3;
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", getServiceName());
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("video_path", str);
        intent.putExtra("video_layout_info", rect);
        if (str == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        intent.putExtra("is_image_save", z3);
        intent.putExtra("is_content_share", isWithShare());
        intent.putExtra("from_story_pictures", true ^ z);
        intent.putExtra("output_file_path", str2);
        getContext().startService(intent);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SAVE_COVER_STORY.toString();
    }

    public String getResultFilePath(MediaItem mediaItem, boolean z, boolean z3) {
        String str;
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(System.currentTimeMillis()));
        if (z) {
            str = IFormat.FORMAT_MP4;
        } else {
            str = "jpg";
        }
        String str2 = StorageInfo.getDefault().stories;
        StringBuilder s = C0212a.s(str2);
        s.append(File.separator);
        s.append(FileUtils.getUniqueFilename(str2, format + "." + str));
        return s.toString();
    }

    public String getServiceName() {
        return "com.samsung.android.gallery.app.service.StorySaveService";
    }

    public boolean isAllowingShareDirectly(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public final boolean isUnsupportedFormat(MediaItem mediaItem) {
        return FileType.getMimeType(mediaItem.getPath()).contains("wmv");
    }

    public boolean isWithShare() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            boolean z = false;
            MediaItem mediaItem = objArr[0];
            if (!checkItemUnavailable(mediaItem)) {
                List list = objArr[1];
                boolean booleanValue = objArr[2].booleanValue();
                int color = getContext().getColor(R.color.story_pictures_view_bg_color);
                if (StorySaveLayoutInfo.getVideoPath(mediaItem) != null) {
                    z = true;
                }
                String resultFilePath = getResultFilePath(mediaItem, z, booleanValue);
                if (preparedStartService(resultFilePath)) {
                    SimpleThreadPool.getInstance().execute(new g(this, booleanValue, mediaItem, list, color, resultFilePath));
                }
            }
        }
    }

    public boolean preparedStartService(String str) {
        if (str == null) {
            return false;
        }
        try {
            boolean isEmpty = Blackboard.getApplicationInstance().isEmpty("data://running_story_share");
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("isServiceRunning : ");
            sb2.append(!isEmpty);
            Log.d(str2, sb2.toString());
            if (!isEmpty) {
                return false;
            }
            SecureFile secureFile = new SecureFile(str);
            if (!isAllowingShareDirectly(secureFile)) {
                return true;
            }
            new ShareViaCmd().execute(getHandler(), new MediaItem[]{UriItemLoader.createUriItem(getContext(), secureFile)}, null);
            Optional.ofNullable(getBlackboard()).ifPresent(new e(10));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void showFailToast() {
        Utils.showToast(getApplicationContext(), (int) R.string.error);
    }
}
