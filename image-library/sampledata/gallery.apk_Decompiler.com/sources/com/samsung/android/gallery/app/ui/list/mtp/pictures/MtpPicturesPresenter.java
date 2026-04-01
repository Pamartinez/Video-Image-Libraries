package com.samsung.android.gallery.app.ui.list.mtp.pictures;

import V3.b;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpPicturesPresenter<V extends IPicturesView> extends PicturesPresenter<V> {
    public MtpPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void checkMtpAvailable() {
        if (!MtpClient.getInstance(getApplicationContext()).isAvailable()) {
            ThreadUtil.postOnBgThread(new b(16, this));
        }
    }

    /* access modifiers changed from: private */
    public void restartGallery() {
        Log.w(this.TAG, "mtp is not available. restart gallery process.");
        MtpClient.getInstance(getApplicationContext()).restartGallery(getActivity());
    }

    public MenuHandler createMenuHandler() {
        return new MtpMenuHandler();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1063) {
            return super.handleEvent(eventMessage);
        }
        ThreadUtil.postOnBgThread(new b(16, this));
        return true;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        super.onListItemClickInternal(i2, mediaItem);
        this.mBlackboard.publish("data://user/fromMtpViewer", Boolean.TRUE);
    }

    public void onViewResume() {
        super.onViewResume();
        checkMtpAvailable();
    }

    public void updateToolbar(Toolbar toolbar) {
        String locationKey = getLocationKey();
        if (locationKey != null) {
            toolbar.setTitle((CharSequence) ArgumentsUtil.getArgs(locationKey).getString("title"));
        }
        toolbar.setSubtitle((CharSequence) null);
        if (!isSelectionMode()) {
            setNavigationUpButton(toolbar);
        }
    }
}
