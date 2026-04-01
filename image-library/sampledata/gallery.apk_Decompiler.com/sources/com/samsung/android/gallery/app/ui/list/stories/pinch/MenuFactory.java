package com.samsung.android.gallery.app.ui.list.stories.pinch;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create(final String str) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_stories_pinch);
        MenuDataBinder.bindActionTrash(menuDataBinding);
        MenuDataBinder.bindActionSettings(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_favorite_view) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_story_album) {
            public boolean getDefaultExcluding() {
                if (!Features.isEnabled(Features.SUPPORT_STORY) || LocationKey.isStoriesFavorite(str)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_content) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi40.SUPPORT_STORIES_HIDE_RULE;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_story_in_list) {
            public boolean getDefaultExcluding() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_on_demand) {
            public boolean getDefaultExcluding() {
                if (!PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY || !OnDemandStory.getInstance().isEnabled()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_search) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }
}
