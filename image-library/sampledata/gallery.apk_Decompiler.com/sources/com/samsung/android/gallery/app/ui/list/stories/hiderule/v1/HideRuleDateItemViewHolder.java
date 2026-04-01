package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import a6.C0419b;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleDateItemViewHolder extends ListViewHolder {
    TextView mDateInfoTextView;

    public HideRuleDateItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void bindDateInfo(MediaItem mediaItem) {
        HideRuleData of2 = HideRuleData.of(mediaItem);
        String localDateYYMD = getLocalDateYYMD(of2.hideRuleStartTime);
        String localDateYYMD2 = getLocalDateYYMD(of2.hideRuleEndTime);
        if (TextUtils.equals(localDateYYMD, localDateYYMD2)) {
            this.mDateInfoTextView.setText(localDateYYMD);
            return;
        }
        TextView textView = this.mDateInfoTextView;
        textView.setText(localDateYYMD + " - " + localDateYYMD2);
    }

    private String getLocalDateYYMD(long j2) {
        return TimeUtil.toLocalDate(j2, "YYMD");
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        bindDateInfo(mediaItem);
    }

    public void bindView(View view) {
        this.mDateInfoTextView = (TextView) view.findViewById(R.id.date_info);
        view.findViewById(R.id.remove).setOnClickListener(new C0419b(10, this));
    }

    public void onClickRemoveDate(View view) {
        onItemClickInternal(1002);
    }
}
