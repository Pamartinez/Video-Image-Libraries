package com.samsung.android.gallery.app.ui.list.mtp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpPresenter extends BaseListPresenter<IBaseListView> {
    public MtpPresenter(Blackboard blackboard, IBaseListView iBaseListView) {
        super(blackboard, iBaseListView);
    }

    private void checkMtpAvailable() {
        try {
            if (!MtpClient.getInstance(getApplicationContext()).isAvailable()) {
                Log.mtw(this.TAG, "mtp is not available.");
                this.mBlackboard.post("command://bottomtab/select", "location://timeline");
                this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.set_label_mtp_devices);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs("location://mtp/fileList", "__absPath", mediaItem.getPath()), "title", mediaItem.getTitle());
        StringCompat stringCompat = this.TAG;
        Log.mt(stringCompat, "mtp device is clicked : " + appendArgs);
        this.mBlackboard.post("command://MoveURL", appendArgs);
        postAnalyticsLog(AnalyticsEventId.EVENT_TOUCH_MTP_DEVICE, MtpClient.getInstance(getApplicationContext()).getDeviceNameFromPath(mediaItem.getPath()));
    }

    public void onViewResume() {
        super.onViewResume();
        checkMtpAvailable();
    }

    public void updateToolbar(Toolbar toolbar) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            toolbar.setTitle((int) R.string.set_label_mtp_devices);
            setNavigationUpButton(toolbar);
            return;
        }
        ((IBaseListView) this.mView).getAppbarLayout().setTitle((CharSequence) context.getString(R.string.set_label_mtp_devices));
        toolbar.setTitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
    }
}
