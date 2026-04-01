package com.samsung.android.gallery.app.ui.menu.list;

import android.text.TextUtils;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.UnmergeCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.people.PersonHideCmd;
import com.samsung.android.gallery.app.controller.creature.pet.PetHideCmd;
import com.samsung.android.gallery.app.controller.internals.EachExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.ExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveFromResultCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesMenuHandler extends MenuHandler {
    private String getCategoryLocationKey() {
        try {
            return new UriBuilder("location://search/fileList/CarouselCluster").build();
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            eventContext.getBlackboard().postEvent(EventMessage.obtain(8030));
        }
        switch (menuItem.getItemId()) {
            case R.id.action_add_to_quick_search:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(8034));
                eventContext.postAnalyticsLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_SEARCH_SELECT_ADD_SHORTCUT);
                return true;
            case R.id.action_delete_motion_photo_clip:
                new MotionPhotoDeleteVideoCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_export_motion_photo_clip:
                new EachExportVideoClipsCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_hide_this_person:
                new PersonHideCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_hide_this_pet:
                new PetHideCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_merge_motion_photo_clip:
                new ExportVideoClipsCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_merge_people_name:
            case R.id.action_merge_pets_name:
                new MergeCreatureCmd().execute(eventContext, eventContext.getHeaderItem());
                return true;
            case R.id.action_remove_from_result:
                new RemoveFromResultCmd().execute(eventContext, (String) menuItem.getTitle());
                return true;
            case R.id.action_scoped_search:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(8526, Boolean.TRUE));
                menuItem.setVisible(false);
                return true;
            case R.id.action_unmerge_people_name:
            case R.id.action_unmerge_pets_name:
                new UnmergeCreatureCmd().execute(eventContext, eventContext.getHeaderItem());
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_view_by_category:
                String categoryLocationKey = getCategoryLocationKey();
                if (TextUtils.isEmpty(categoryLocationKey)) {
                    return true;
                }
                eventContext.getBlackboard().post("command://MoveURL", categoryLocationKey);
                return true;
            case R.id.action_view_on_map:
                eventContext.getBlackboard().postEvent(EventMessage.obtain(8019));
                return true;
            default:
                return false;
        }
    }
}
