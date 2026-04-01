package com.samsung.android.gallery.widget.livemotion;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.viewpager2.widget.ViewPager2;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.livemotion.abstraction.ILiveMotionAdapter;
import i.C0212a;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AccessibilityDelegate extends View.AccessibilityDelegate {
    protected static final int[] DURATION_FORMAT = {R$string.details_ms, R$string.details_hms};
    private final Supplier<ILiveMotionAdapter> mAdapter;
    private final ViewPager2 mViewPager;

    public AccessibilityDelegate(ViewPager2 viewPager2, Supplier<ILiveMotionAdapter> supplier) {
        this.mViewPager = viewPager2;
        this.mAdapter = supplier;
    }

    private String getContentDescription() {
        MediaItem mediaItem;
        long j2;
        Supplier<ILiveMotionAdapter> supplier = this.mAdapter;
        if (supplier == null || supplier.get() == null || this.mViewPager == null || (mediaItem = this.mAdapter.get().getMediaItem(this.mViewPager.getCurrentItem())) == null) {
            return "";
        }
        if (mediaItem.getDateLocal() > 0) {
            j2 = mediaItem.getDateLocal();
        } else {
            j2 = mediaItem.getDateTaken();
        }
        String str = TimeUtil.getLocalizedDateTime(j2) + ArcCommonLog.TAG_COMMA + mediaItem.getMimeType();
        if (!mediaItem.isVideo()) {
            return str;
        }
        StringBuilder t = C0212a.t(str, ArcCommonLog.TAG_COMMA);
        t.append(TimeUtil.formatDuration(this.mViewPager.getContext(), mediaItem.getFileDuration(), DURATION_FORMAT));
        return t.toString();
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 128 || !SeApiCompat.requestAccessibilityFocus(this.mViewPager)) {
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }
        return true;
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        if (accessibilityEvent.getEventType() == 32768) {
            this.mViewPager.setContentDescription(getContentDescription());
        }
    }
}
