package com.samsung.android.gallery.app.ui.menu;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuDataBinder {
    public static void bindActionAddShortcut(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_shortcut) {
            public boolean getDefaultExcluding() {
                if (isKnox() || isUpsm() || isAfw()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionAddTag(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_tag) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionAddToSharedAlbum(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_to_shared_album) {
            public boolean getDefaultExcluding() {
                if (!MdeSharingService.getInstance().isServiceSupported() || isUpsm()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionCloud(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_cloud_sync_timeline) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR);
            }

            public int getDefaultIndex() {
                boolean z;
                if (CloudStateCompat.isLegacyServiceStatusRequired()) {
                    z = CloudStateCompat.isEnabledInPref();
                } else {
                    z = CloudStateCompat.isEnabled();
                }
                if (!z || !CloudStateCompat.isSyncOnInPref()) {
                    return 0;
                }
                return 1;
            }

            public boolean getDefaultVisibility() {
                if (CloudStateCompat.isLegacyServiceStatusRequired()) {
                    return CloudStateCompat.isMigrationSupportedInPref();
                }
                if (CloudStateCompat.isEnabled() || CloudStateCompat.isOneDriveLinkRequired()) {
                    return true;
                }
                return false;
            }

            public int[] getIconArray() {
                return new int[]{R.drawable.gallery_ic_cloud_sync_off, R.drawable.gallery_ic_cloud_sync_on};
            }

            public int[] getTitleArray() {
                if (CloudStateCompat.isNewGalleryAvailable()) {
                    return new int[]{R.string.not_synced_with_samsung_cloud_menu_description, R.string.synced_with_samsung_cloud_menu_description};
                }
                return new int[]{R.string.sync_with_one_drive_menu_description, R.string.synced_with_one_drive_menu_description};
            }
        });
    }

    public static void bindActionCopyToAlbum(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_copy_to_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionCreate(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_create, MenuSupportHelper.supportCreate()));
    }

    public static void bindActionCreateMovie(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_movie) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT);
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionDeleteDuplicatePictures(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_duplicate_pictures) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !Features.isEnabled(Features.SUPPORT_SUGGESTIONS)) {
                    return false;
                }
                return true;
            }
        });
    }

    public static void bindActionDownload(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_download) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return false;
            }
        });
    }

    public static void bindActionEditLocation(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_edit_location) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_LOCATION);
            }
        });
    }

    public static void bindActionEditMyQuery(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_edit_quick_search) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY;
            }
        });
    }

    public static void bindActionHideAlbums(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionMemorySaver(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_memory_saver) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_MEMORY_SAVER);
            }
        });
    }

    public static void bindActionMoveToAlbum(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_move_to_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionQuickSearch(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_quick_search) {
            public boolean getDefaultExcluding() {
                if (!PocFeatures.QUICK_SEARCH || isUpsm()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionSearch(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_search) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindActionSearchSetting(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_search_setting, true, PreferenceFeatures.OneUi8x.COLLECTION_TAB));
    }

    public static void bindActionSettings(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_settings, true, PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU));
    }

    public static void bindActionShareAlbums(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_album) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.isEnabled(PreferenceFeatures.ShareAlbums);
            }
        });
    }

    public static void bindActionSimilar(MenuDataBinding menuDataBinding) {
        if (SimilarPhotoHelper.supportSimilarPhoto()) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_similar) {
                public int getDefaultIndex() {
                    return SimilarPhotoHelper.isSimilarPhotoMode() ? 1 : 0;
                }

                public boolean getDefaultVisibility() {
                    return false;
                }

                public int[] getTitleArray() {
                    return new int[]{R.string.group_similar_image, R.string.ungroup_similar_image};
                }
            });
        } else {
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_similar));
        }
    }

    public static void bindActionTrash(MenuDataBinding menuDataBinding) {
        boolean z;
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU || PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            z = true;
        } else {
            z = false;
        }
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_trash, true, z));
    }

    public static void bindActionUsbOtg(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_usb_otg, false));
    }

    public static void bindActionVerizonCloud(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_verizon_cloud) {
            public boolean getDefaultExcluding() {
                if (isKnox() || isAfw()) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_VERIZON_CLOUD);
            }
        });
    }

    public static void bindActionViewAs(MenuDataBinding menuDataBinding, final Blackboard blackboard) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_as) {
            public boolean getDefaultVisibility() {
                return isDexMode(blackboard);
            }
        });
    }

    public static void bindAddFavoriteInList(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_favorite_in_list) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindCompareImages(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_compare_images) {
            public boolean getDefaultExcluding() {
                return !PocFeatures.TBD.COMPARE_IMAGES;
            }
        });
    }

    public static void bindCopyPasteClipboardBinding(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_copy_to_clipboard) {
            public boolean getDefaultExcluding() {
                if (isKnox() || isUpsm() || isAfw() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return SdkConfig.atLeast(SdkConfig.SEM.U);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_paste_clipboard) {
            public boolean getDefaultExcluding() {
                if (isKnox() || isUpsm() || isAfw() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return SdkConfig.atLeast(SdkConfig.SEM.U);
            }
        });
    }

    public static void bindCopyPasteEffectBinding(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_copy_effects) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_paste_effects) {
            public boolean getDefaultExcluding() {
                if (isUpsm() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS);
            }
        });
    }

    public static void bindInvisible(MenuDataBinding menuDataBinding, int i2) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(i2) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
    }

    public static void bindKnoxDataBinding(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_move_to_secure_folder) {
            public String getDefaultTitle() {
                return AppResources.getString(R.string.move_to_knox_s, KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER));
            }

            public boolean getDefaultVisibility() {
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_secure_folder) {
            public String getDefaultTitle() {
                if (SdkConfig.lessThan(SdkConfig.SEM.V)) {
                    return AppResources.getString(R.string.move_out_of_secure_folder);
                }
                return AppResources.getString(R.string.move_out_of_secure_folder_s, KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER));
            }

            public boolean getDefaultVisibility() {
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_move_to_knox) {
            public String getDefaultTitle() {
                return AppResources.getString(R.string.move_to_knox_s, KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.MOVE_TO_KNOX));
            }

            public boolean getDefaultVisibility() {
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_KNOX);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_knox) {
            public String getDefaultTitle() {
                String knoxContainerName = KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.REMOVE_FROM_KNOX);
                if (TextUtils.isEmpty(knoxContainerName) || "Personal".equals(knoxContainerName)) {
                    return AppResources.getString(R.string.remove_from_knox);
                }
                return AppResources.getString(R.string.move_to_knox_s, knoxContainerName);
            }

            public boolean getDefaultVisibility() {
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_KNOX);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_move_to_private_album, true, !PocFeatures.SUPPORT_PRIVATE_ALBUM));
    }

    public static void bindMotionPhotoOperation(MenuDataBinding menuDataBinding) {
        final boolean z;
        if (!PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER || Features.isEnabled(Features.IS_GED)) {
            z = false;
        } else {
            z = true;
        }
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_merge_motion_photo_clip) {
            public boolean getDefaultExcluding() {
                if (!z || !PreferenceFeatures.OneUi7x.IS_ONE_UI_70) {
                    return true;
                }
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_motion_photo_clip) {
            public boolean getDefaultExcluding() {
                return !z;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_export_motion_photo_clip) {
            public boolean getDefaultExcluding() {
                return !z;
            }
        });
    }

    public static void bindRemoveBackgroundEffectInfo(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_background_effect_info) {
            public boolean getDefaultExcluding() {
                return !PocFeatures.isEnabled(PocFeatures.RemoveBackgroundEffectInfo);
            }
        });
    }

    public static void bindRemoveFavoriteInList(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_favorite_in_list) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindSetAsWallpaper(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_set_as_container) {
            public boolean getDefaultExcluding() {
                if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || isKnox() || isUpsm() || Features.isEnabled(Features.IS_GED)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
    }

    public static void bindSlideshowWithSelection(MenuDataBinding menuDataBinding) {
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_slideshow_with_selection) {
            public boolean getDefaultExcluding() {
                return !PocFeatures.SLIDESHOW_SELECTED_ITEMS;
            }
        });
    }
}
