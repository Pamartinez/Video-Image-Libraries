package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameCmd;
import com.samsung.android.gallery.app.controller.creature.HideCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.internals.SortByLocationDialogCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryMenuHandler extends MenuHandler {
    private String getHidePeopleAndPetsLocationKey() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                return "location://search/fileList/Category/HiddenPeopleAndPets";
            }
            return "location://search/fileList/Category/HiddenPeople";
        } else if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            return "location://search/fileList/Category/PeopleAndPetsHide";
        } else {
            return "location://search/fileList/Category/PeopleHide";
        }
    }

    private String getScreenId(EventContext eventContext) {
        if (eventContext.isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_CATEGORY_SELECT.toString();
        }
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_CATEGORY.toString();
    }

    private void showEditCreatureNameDialog(EventContext eventContext, MediaItem mediaItem) {
        eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
        new EditCreatureNameCmd().execute(eventContext, ArgumentsUtil.appendArgs(eventContext.getLocationKey(), "sub", mediaItem.getSubCategory()), mediaItem);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_name:
                eventContext.postAnalyticsLog(getScreenId(eventContext), AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_CHANGE_NAME);
                showEditCreatureNameDialog(eventContext, eventContext.getSelectedItems()[0]);
                return true;
            case R.id.action_edit_done:
                postEvent(eventContext, EventMessage.obtain(8009));
                return true;
            case R.id.action_hide_creature_bottom:
                eventContext.postAnalyticsLog(getScreenId(eventContext), AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_HIDE, (long) eventContext.getSelectedItemCount());
                new HideCreatureCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_hide_people_and_pets:
                eventContext.getBlackboard().post("command://MoveURL", getHidePeopleAndPetsLocationKey());
                eventContext.postAnalyticsLog(getScreenId(eventContext), AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_HIDDEN_MENU);
                return true;
            case R.id.action_location_sort_by:
                new SortByLocationDialogCmd().execute(eventContext, new Object[0]);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_merge_creature:
                eventContext.postAnalyticsLog(getScreenId(eventContext), AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_MERGE, (long) eventContext.getSelectedItemCount());
                new MergeCreatureMultipleCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_select_done:
                postEvent(eventContext, EventMessage.obtain(8001));
                return true;
            default:
                return false;
        }
    }
}
