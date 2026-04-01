package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.internals.SavePortraitEffectCmd;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.suggestions.fixup.IFixUpPictures;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixUpPicturesPresenter<V extends IFixUpPictures> extends PicturesPresenter<V> {
    private MediaItem mSelectItem;

    public FixUpPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        int i2;
        if (((IFixUpPictures) this.mView).isHighLightPictures()) {
            i2 = R.menu.menu_highlight_pictures;
        } else {
            i2 = R.menu.menu_portrait_pictures;
        }
        return new MenuDataBinding(i2);
    }

    public MenuHandler createMenuHandler() {
        return new FixUpPicturesMenuHandler();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1037) {
            return super.handleEvent(eventMessage);
        }
        new SavePortraitEffectCmd().execute(this, this.mSelectItem, eventMessage.obj, Boolean.TRUE);
        return true;
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() == 0) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        this.mSelectItem = mediaItem;
        if (((IFixUpPictures) this.mView).isHighLightPictures()) {
            ((IFixUpPictures) this.mView).postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_SUGGEST_HIGHLIGHT_PICTURES_VIEW_DETAIL);
            new VuLauncher(getBlackboard()).disableTimeline().publishData().launch(getLocationKey(), i2, mediaItem);
            return;
        }
        new PlayDualShotCmd().execute(this, mediaItem, Integer.valueOf(PlayDualShotCmd.SUGGESTION));
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }
}
