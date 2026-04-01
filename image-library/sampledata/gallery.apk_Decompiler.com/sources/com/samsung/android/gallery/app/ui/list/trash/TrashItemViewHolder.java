package com.samsung.android.gallery.app.ui.list.trash;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashItemViewHolder extends PreviewViewHolder {
    TextView mNDaysTextView;

    public TrashItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private String getNDayString(int i2) {
        if (i2 > 0) {
            return getContext().getResources().getQuantityString(R.plurals.n_days, i2, new Object[]{Integer.valueOf(i2)});
        }
        return getContext().getString(R.string.today);
    }

    private int getRemainDays(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !mediaItem.isSharing()) {
            try {
                TrashData of2 = TrashData.of(mediaItem);
                return (int) ((((((long) of2.expiredPeriod) * MediaApiContract.DAY_IN_MILLI) + of2.deleteTime) - System.currentTimeMillis()) / MediaApiContract.DAY_IN_MILLI);
            } catch (Exception unused) {
                return 0;
            }
        } else {
            long j2 = TrashData.of(mediaItem).expiryDate;
            if (j2 > 0) {
                return (int) ((j2 - TimeUtil.getTodayInMillis()) / MediaApiContract.DAY_IN_MILLI);
            }
            return 0;
        }
    }

    private void updateDayText(MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.e(this.TAG, "TrashItem null");
        } else {
            this.mNDaysTextView.setText(getNDayString(getRemainDays(mediaItem)));
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        updateDayText(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mNDaysTextView = (TextView) view.findViewById(R.id.n_days);
    }

    public View getDecoView(int i2) {
        if (i2 == 18) {
            return this.mNDaysTextView;
        }
        return super.getDecoView(i2);
    }

    public void recycle() {
        super.recycle();
        this.mNDaysTextView.setText((CharSequence) null);
    }

    public void setContentDescription() {
        int i2;
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null) {
            Log.d(this.TAG, "trashItem null");
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int remainDays = getRemainDays(mediaItem);
        CheckBox checkbox = getCheckbox();
        if (ViewUtils.isVisible(checkbox)) {
            Context context = this.itemView.getContext();
            if (checkbox.isChecked()) {
                i2 = R.string.speak_checked;
            } else {
                i2 = R.string.speak_not_checked;
            }
            sb2.append(context.getString(i2));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContentDescription());
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getRemainDays(remainDays));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(this.itemView.getContext().getString(R.string.speak_checkbox));
        } else {
            sb2.append(getContentDescription());
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getRemainDays(remainDays));
        }
        this.itemView.setContentDescription(sb2.toString());
    }

    private String getRemainDays(int i2) {
        return getContext().getResources().getQuantityString(R.plurals.will_be_deleted_in_n_day, i2, new Object[]{Integer.valueOf(i2)});
    }
}
