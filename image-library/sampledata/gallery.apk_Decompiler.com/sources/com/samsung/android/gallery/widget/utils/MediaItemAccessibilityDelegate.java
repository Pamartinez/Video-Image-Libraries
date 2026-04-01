package com.samsung.android.gallery.widget.utils;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaItemAccessibilityDelegate extends View.AccessibilityDelegate {
    private static final CharSequence TAG = "MediaItemAccessibilityDelegate";
    CharSequence defaultDescription;
    MediaItem item;

    public MediaItemAccessibilityDelegate(MediaItem mediaItem, CharSequence charSequence) {
        this.item = mediaItem;
        this.defaultDescription = charSequence;
    }

    public String getContentDescription(Context context, MediaItem mediaItem) {
        String str;
        int i2;
        String str2;
        int[] iArr = {R$string.details_ms, R$string.details_hms};
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getLocalizedDateTime(mediaItem.getDateTaken()));
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (mediaItem.isFavourite()) {
            str = context.getString(R$string.favorite) + ArcCommonLog.TAG_COMMA;
        } else {
            str = "";
        }
        sb2.append(str);
        if (mediaItem.isImage()) {
            i2 = R$string.image;
        } else {
            i2 = R$string.video;
        }
        sb2.append(context.getString(i2));
        if (mediaItem.isImage()) {
            str2 = " ";
        } else {
            str2 = TimeUtil.formatDurationForAccessibility(context, mediaItem.getFileDuration(), iArr);
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if (accessibilityEvent.getEventType() == 32768) {
            Log.v(TAG, "initialize accessibility event");
            setContentDescription(view.getContext(), view, this.item);
        }
    }

    public void setContentDescription(Context context, View view, MediaItem mediaItem) {
        if (mediaItem != null) {
            view.setContentDescription(this.defaultDescription + " " + getContentDescription(context, mediaItem));
        }
    }
}
