package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateVideoGifCmd extends BaseCommand {
    private boolean isVertical(MediaItem mediaItem) {
        if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
            return true;
        }
        return false;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_GIF.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        int i2;
        int i7;
        int i8 = 0;
        MediaItem mediaItem = objArr[0];
        String path = mediaItem.getPath();
        Integer num = objArr[1];
        if (num != null) {
            i8 = num.intValue();
        }
        if (isVertical(mediaItem)) {
            i2 = mediaItem.getHeight();
        } else {
            i2 = mediaItem.getWidth();
        }
        if (isVertical(mediaItem)) {
            i7 = mediaItem.getWidth();
        } else {
            i7 = mediaItem.getHeight();
        }
        try {
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.GifPlayerActivity");
            intent.putExtra("gif_video_path", path);
            intent.putExtra("gif_current_position", i8);
            intent.putExtra("gif_duration", VideoPropData.getVideoDuration(mediaItem));
            intent.putExtra("gif_video_width", i2);
            intent.putExtra("gif_video_height", i7);
            intent.putExtra("from_application", "com.sec.android.gallery3d");
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            showToast((int) R.string.no_app_for_action);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("onExecute failed. e="), this.TAG);
        }
    }
}
