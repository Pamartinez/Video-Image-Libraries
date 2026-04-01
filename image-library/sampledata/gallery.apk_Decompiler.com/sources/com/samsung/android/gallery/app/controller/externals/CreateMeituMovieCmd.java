package com.samsung.android.gallery.app.controller.externals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMeituMovieCmd extends CreateMeituMediaCmd {
    public CreateMediaHelper.SupportType checkSupportType(MediaItem[] mediaItemArr, boolean z) {
        for (MediaItem isCloudOnly : mediaItemArr) {
            if (isCloudOnly.isCloudOnly()) {
                return CreateMediaHelper.SupportType.NOT_SUPPORT_COMMON;
            }
        }
        return CreateMediaHelper.SupportType.SUPPORT;
    }

    public /* bridge */ /* synthetic */ void done(MediaItem[] mediaItemArr) {
        super.done(mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_MEITU_MOVIE.toString();
    }

    public Intent getIntent(ArrayList<Uri> arrayList) {
        Intent intent = new Intent(C0212a.p(new StringBuilder(), CreateMeituMediaCmd.MT_ACTION, ".VIDEOEDIT"));
        intent.setType("*/*");
        intent.setPackage(CreateMeituMediaCmd.MT_PACKAGE_NAME);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.putExtra("function", "quick_formula");
        intent.putExtra("source", "Samsung");
        return intent;
    }

    public int getMaxCount() {
        return -1;
    }

    public CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly()) {
            return CreateMediaHelper.SupportType.NOT_SUPPORT_COMMON;
        }
        return CreateMediaHelper.SupportType.SUPPORT;
    }

    public int getTitleRes() {
        return R.string.meitu_video_clip;
    }

    public boolean isExceedMaxCount() {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isSupported(MediaItem mediaItem) {
        return super.isSupported(mediaItem);
    }

    public /* bridge */ /* synthetic */ void showExceedMaxCountToast(Context context) {
        super.showExceedMaxCountToast(context);
    }
}
