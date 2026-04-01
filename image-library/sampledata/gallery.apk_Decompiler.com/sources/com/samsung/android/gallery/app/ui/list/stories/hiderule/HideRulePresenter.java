package com.samsung.android.gallery.app.ui.list.stories.hiderule;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import c4.C0438h;
import com.samsung.android.gallery.app.controller.internals.RangeDatePickerCmd;
import com.samsung.android.gallery.app.controller.story.RemoveHideDateCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.abstraction.IHideRuleView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRulePresenter<V extends IHideRuleView> extends BaseListPresenter<V> {
    public HideRulePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.hide_content);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 101) {
            forceReloadData();
            return true;
        } else if (i2 != 3017) {
            return super.handleEvent(eventMessage);
        } else {
            return true;
        }
    }

    public void launchDatePicker() {
        new RangeDatePickerCmd().execute(this, new Object[0]);
        postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_DATE_ADD);
    }

    public void launchHideSceneSelection(int i2, MediaItem mediaItem) {
        String build = new UriBuilder("location://stories/hideSceneSelection").appendArg("id", mediaItem.getAlbumID()).appendArg(Message.KEY_POSITION, i2).build();
        Log.d(this.TAG, "launchHideSceneSelection ");
        this.mBlackboard.post("command://MoveURL", build);
        if (mediaItem.getItemsInFolder().length > 0) {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_SCENE_VIEW);
        } else {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_SCENE_ADD);
        }
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
    }

    public void removeDate(int i2, MediaItem mediaItem) {
        new RemoveHideDateCmd().execute(this, mediaItem, Integer.valueOf(i2));
        postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_DATE_REMOVE);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((int) R.string.hide_content);
        toolbar.setSubtitle((CharSequence) null);
        setNavigationUpButton(toolbar);
        Optional.ofNullable(((IHideRuleView) this.mView).getAppbarLayout()).ifPresent(new C0438h(16));
    }
}
