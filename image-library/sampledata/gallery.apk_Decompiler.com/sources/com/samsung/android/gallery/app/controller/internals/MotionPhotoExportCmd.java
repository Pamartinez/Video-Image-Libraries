package com.samsung.android.gallery.app.controller.internals;

import A5.a;
import Ga.d;
import H3.i;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoExportCmd extends BaseCommand {
    private final ArrayList<SubMenuType> mItems = new ArrayList<>();
    private MediaItem mMediaItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SubMenuType {
        AS_VIDEO(R.string.save_video_menu),
        AS_GIF(R.string.create_gif_menu);
        
        private final int mResId;

        private SubMenuType(int i2) {
            this.mResId = i2;
        }

        public int value() {
            return this.mResId;
        }
    }

    private String buildFilename(String str) {
        return FileUtils.getExternalFilesDir("motion") + File.separator + FileUtils.getBaseName(str) + O3DPConstant.MP4_EXTENSION;
    }

    private void create(String str, Function<String, Boolean> function, String str2, boolean z) {
        int i2;
        if (function.apply(str).booleanValue()) {
            String str3 = this.TAG;
            Log.d(str3, "success export as " + str2);
            MediaScanner.scanFile(str, new i((Object) this, (Object) str, z));
            return;
        }
        Log.e((CharSequence) this.TAG, C0212a.l("fail export as ", str2), str);
        Context context = getContext();
        if (z) {
            i2 = R.string.image_save_fail;
        } else {
            i2 = R.string.video_save_fail;
        }
        Utils.showToast(context, i2);
    }

    private void createGif(EventContext eventContext) {
        if (eventContext.getContext() != null) {
            String buildFilename = buildFilename(this.mMediaItem.getPath());
            try {
                Intent createGifIntent = MotionPhotoUtils.createGifIntent(this.mMediaItem.getPath(), buildFilename);
                getContext().startActivity(createGifIntent);
                Log.d(this.TAG, "createGif export as gif", createGifIntent);
                eventContext.subscribeInstant("lifecycle://on_activity_resume", new d(2, this, buildFilename));
            } catch (Exception e) {
                FileUtils.delete(buildFilename);
                Utils.showToast(getContext(), (int) R.string.unsupported_file_type);
                String str = this.TAG;
                Log.d(str, "createGif failed. e=" + e.getMessage());
            }
        }
    }

    private String[] getItemsArray() {
        Resources resources = getActivity().getResources();
        int size = this.mItems.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = resources.getString(this.mItems.get(i2).value());
        }
        return strArr;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$create$3(String str, boolean z) {
        int i2;
        Log.d(this.TAG, "scan done");
        String translatedDirectory = BucketUtils.getTranslatedDirectory(str);
        Context context = getContext();
        Context context2 = getContext();
        if (z) {
            i2 = R.string.toast_image_saved_in;
        } else {
            i2 = R.string.video_saved_in;
        }
        Utils.showToast(context, context2.getString(i2, new Object[]{translatedDirectory}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGif$2(String str, Object obj, Bundle bundle) {
        Log.d(this.TAG, "createGif delete temp", Boolean.valueOf(FileUtils.delete(str)), "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onItemSelected$0(String str) {
        return Boolean.valueOf(MotionPhotoUtils.exportVideo(this.mMediaItem.getPath(), str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemSelected$1(SubMenuType subMenuType, EventContext eventContext) {
        if (SubMenuType.AS_VIDEO == subMenuType) {
            create(new FileNameBuilder(this.mMediaItem.getPath()).setExtension(IFormat.FORMAT_MP4).buildUnique(), new a(18, this), "video", false);
            AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_MOTION_PHOTO_EXPORT_VIDEO.toString());
        } else if (SubMenuType.AS_GIF == subMenuType) {
            createGif(eventContext);
            AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_MOTION_PHOTO_EXPORT_GIF.toString());
        }
    }

    private void loadItems() {
        this.mItems.add(SubMenuType.AS_VIDEO);
        this.mItems.add(SubMenuType.AS_GIF);
    }

    /* access modifiers changed from: private */
    public void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            SimpleThreadPool.getInstance().execute(new g(this, this.mItems.get(((Integer) arrayList.get(0)).intValue()), eventContext));
        }
    }

    public String getEventId() {
        return null;
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getPath())) {
            String str = this.TAG;
            Log.e(str, "Failed to execute -> item is null : " + this.mMediaItem);
            return;
        }
        loadItems();
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ViewerListChooser").appendArg("list_chooser_title", (int) R.string.export_as).appendArg("list_chooser_items", getItemsArray()).build()).setOnDataCompleteListener(new K4.a(24, this)).start();
        PreferenceCache.MotionPhotoExportNewBadge.setBoolean(false);
        AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_MOTION_PHOTO_EXPORT.toString());
    }
}
