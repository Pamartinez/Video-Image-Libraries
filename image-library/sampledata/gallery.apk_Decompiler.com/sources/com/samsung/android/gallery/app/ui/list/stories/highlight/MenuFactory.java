package com.samsung.android.gallery.app.ui.list.stories.highlight;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuFactory {
    public static MenuDataBinding create(Blackboard blackboard, IStoryHighlightView iStoryHighlightView) {
        boolean isOnDemandStory = iStoryHighlightView.getOptions().isOnDemandStory();
        final boolean isFromTransitoryStory = iStoryHighlightView.getOptions().isFromTransitoryStory();
        MenuDataBinding menuDataBinding = new MenuDataBinding(getMenuResId(isOnDemandStory));
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_export_options) {
            public boolean getDefaultVisibility() {
                if (!MenuFactory.isExportEnabled() || !PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_save_story) {
            public boolean getDefaultVisibility() {
                return MenuFactory.isExportEnabled();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_menu) {
            public boolean getDefaultVisibility() {
                return MenuFactory.isExportEnabled();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_items) {
            public boolean getDefaultVisibility() {
                return !MenuFactory.isExportEnabled();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_download_all) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_sound_picker) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_SOUND_PICKER);
            }
        });
        if (isOnDemandStory) {
            return menuDataBinding;
        }
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_highlight_reel_story) {
            public boolean getDefaultVisibility() {
                if (!Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2) || !SdkConfig.lessThan(SdkConfig.SEM.U)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_from_story) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_story) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_cover) {
            public boolean getDefaultVisibility() {
                return !isFromTransitoryStory;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_story) {
            public boolean getDefaultVisibility() {
                if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !isFromTransitoryStory) {
                    return true;
                }
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename) {
            public boolean getDefaultVisibility() {
                return !isFromTransitoryStory;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_to_shared_album) {
            public boolean getDefaultExcluding() {
                if (!MenuFactory.isExportEnabled() || !MdeSharingService.getInstance().isServiceSupported() || isUpsm()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return MenuFactory.isExportEnabled();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_to_favorite) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_favorite) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_original_picture) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_story_recap, PocFeatures.isEnabled(PocFeatures.Recap)));
        return menuDataBinding;
    }

    private static int getMenuResId(boolean z) {
        if (z) {
            return R.menu.menu_story_highlight_on_demand;
        }
        return R.menu.menu_story_highlight_v2;
    }

    /* access modifiers changed from: private */
    public static boolean isExportEnabled() {
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave);
        }
        return Features.isEnabled(Features.SUPPORT_STORY_HIGHLIGHT_SAVE_VIA_VE);
    }
}
