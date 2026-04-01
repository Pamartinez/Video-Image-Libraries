package com.samsung.android.gallery.app.ui.list.pictures;

import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.share.QuickSharePrivacy;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MenuFactory {
    private static MenuDataBinding addCommonBinding(final Blackboard blackboard, MenuDataBinding menuDataBinding) {
        if (menuDataBinding == null) {
            return null;
        }
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_mode) {
            private boolean isSplitEnabled() {
                if (!Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT) || Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT_SPLIT_MODE)) {
                    return true;
                }
                return false;
            }

            public boolean getDefaultVisibility() {
                if (!isDexMode(blackboard) || !isSplitEnabled()) {
                    return false;
                }
                return true;
            }
        });
        MenuDataBinder.bindActionDownload(menuDataBinding);
        MenuDataBinder.bindActionCopyToAlbum(menuDataBinding);
        MenuDataBinder.bindActionMoveToAlbum(menuDataBinding);
        MenuDataBinder.bindActionCreate(menuDataBinding);
        MenuDataBinder.bindActionViewAs(menuDataBinding, blackboard);
        MenuDataBinder.bindActionSearch(menuDataBinding);
        MenuDataBinder.bindActionAddToSharedAlbum(menuDataBinding);
        MenuDataBinder.bindKnoxDataBinding(menuDataBinding);
        MenuDataBinder.bindActionAddTag(menuDataBinding);
        MenuDataBinder.bindSetAsWallpaper(menuDataBinding);
        MenuDataBinder.bindCopyPasteEffectBinding(menuDataBinding);
        MenuDataBinder.bindCopyPasteClipboardBinding(menuDataBinding);
        MenuDataBinder.bindSlideshowWithSelection(menuDataBinding);
        MenuDataBinder.bindCompareImages(menuDataBinding);
        MenuDataBinder.bindRemoveBackgroundEffectInfo(menuDataBinding);
        MenuDataBinder.bindActionEditLocation(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_gallery_assistant, Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT)));
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_printer_multi, true, !PocFeatures.isEnabled(PocFeatures.PrintMultiple)));
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_start_server, PocFeatures.isEnabled(PocFeatures.WifiGallery)));
        PocFeatures pocFeatures = PocFeatures.WifiGalleryClient;
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_upload, true, !PocFeatures.isEnabled(pocFeatures)));
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_reload, true, !PocFeatures.isEnabled(pocFeatures)));
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_story_recap, PocFeatures.isEnabled(PocFeatures.Recap)));
        return menuDataBinding;
    }

    public static MenuDataBinding create(Blackboard blackboard, String str) {
        if (str == null) {
            return null;
        }
        if (LocationKey.isTimelinePictures(str)) {
            return addCommonBinding(blackboard, createTimelineMenu());
        }
        if (LocationKey.isAlbumPictures(str)) {
            return addCommonBinding(blackboard, createAlbumPicturesMenu(blackboard, str));
        }
        if (LocationKey.isStoryPictures(str)) {
            return addCommonBinding(blackboard, createStoryPicturesMenu());
        }
        if (LocationKey.isSearchPictures(str)) {
            return addCommonBinding(blackboard, createSearchPicturesMenu(str));
        }
        if (LocationKey.isMtpPictures(str)) {
            return addCommonBinding(blackboard, createMtpPicturesMenu());
        }
        if (LocationKey.isSharingPictures(str)) {
            return createSharingPicturesMenu(blackboard, str);
        }
        return null;
    }

    private static MenuDataBinding createAlbumPicturesMenu(Blackboard blackboard, String str) {
        if (ArgumentsUtil.getArgValue(str, "shortcut_album", false) || LaunchIntent.isFlipCoverWidgetTheme(blackboard)) {
            return createShortcutAlbumPicturesMenu();
        }
        return createNormalAlbumPicturesMenu(str);
    }

    private static MenuDataBinding createMtpPicturesMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_mtp_pictures);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_import) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_import_selection) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    private static MenuDataBinding createNormalAlbumPicturesMenu(String str) {
        final boolean isAutoAlbum = AlbumType.isAutoAlbum(ArgumentsUtil.getArgValue(str, "type", 0));
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_album_pictures);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename_album) {
            public boolean getDefaultExcluding() {
                return isAutoAlbum;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_cover_image) {
            public boolean getDefaultExcluding() {
                return isAutoAlbum;
            }

            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_album_settings) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi5x.MX_ALBUMS;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_shortcut) {
            public boolean getDefaultVisibility() {
                if (Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_AFW_MODE)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_sortby) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_auto_album) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_cloud_sync_album) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR);
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
                return new int[]{R.drawable.gallery_ic_cloud_sync_off, R.drawable.gallery_ic_cloud_sync_on, R.drawable.gallery_ic_cloud_sync_off};
            }

            public int[] getTitleArray() {
                if (CloudStateCompat.isNewGalleryAvailable()) {
                    return new int[]{R.string.not_synced_with_samsung_cloud_menu_description, R.string.cloud_sync_settings, R.string.sync_album_with_samsung_cloud};
                }
                return new int[]{R.string.sync_with_one_drive_menu_description, R.string.synced_with_one_drive_menu_description, R.string.not_synced_with_one_drive_menu_description};
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_unlock_album, false, !PocFeatures.SUPPORT_LOCKED_ALBUM));
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_quick_share_privacy) {
            public boolean getDefaultExcluding() {
                return isUpsm();
            }

            public boolean getDefaultVisibility() {
                return QuickSharePrivacy.getInstance().isSupported();
            }
        });
        MenuDataBinder.bindRemoveFavoriteInList(menuDataBinding);
        MenuDataBinder.bindAddFavoriteInList(menuDataBinding);
        MenuDataBinder.bindMotionPhotoOperation(menuDataBinding);
        return menuDataBinding;
    }

    private static MenuDataBinding createSearchPicturesMenu(final String str) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_search_pictures);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_result) {
            public boolean getDefaultExcluding() {
                return !PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD;
            }

            public String getDefaultTitle() {
                int i2;
                if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    return super.getDefaultTitle();
                }
                if (LocationKey.isSearchCategoryMyTag(str)) {
                    i2 = R.string.remove_items_from_tag_group;
                } else {
                    i2 = R.string.remove_items_from_collection;
                }
                return AppResources.getString(i2);
            }

            public boolean getDefaultVisibility() {
                if ((str.startsWith("location://search/fileList/Category") || str.startsWith("location://search/fileList/PeopleAllPictures")) && !str.startsWith("location://search/fileList/Category/Location") && !str.startsWith("location://search/fileList/Category/ShotMode")) {
                    return true;
                }
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_this_person) {
            public String getDefaultTitle() {
                if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                    return AppResources.getString(R.string.hide_face_from_searches);
                }
                if (!IdentityCreatureUtil.isPetRecognized()) {
                    return AppResources.getString(R.string.remove_from_people);
                }
                return super.getDefaultTitle();
            }

            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || !LocationKey.isSearchCategoryPeople(str)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_merge_people_name) {
            public boolean getDefaultVisibility() {
                return LocationKey.isSearchCategoryPeople(str);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_hide_this_pet) {
            public String getDefaultTitle() {
                if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                    return AppResources.getString(R.string.hide_face_from_searches);
                }
                return super.getDefaultTitle();
            }

            public boolean getDefaultVisibility() {
                if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER) || !LocationKey.isSearchCategoryPet(str)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_merge_pets_name) {
            public boolean getDefaultVisibility() {
                if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER) || !LocationKey.isSearchCategoryPet(str)) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_to_quick_search) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_on_map) {
            public boolean getDefaultVisibility() {
                return false;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_view_by_category) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.isEnabled(PreferenceFeatures.ViewClusterResultMenuOption);
            }
        });
        if (LocationKey.isSearchPicturesLocation(str)) {
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_select, true));
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_slideshow, true));
        }
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_view_all, false));
        MenuDataBinder.bindAddFavoriteInList(menuDataBinding);
        MenuDataBinder.bindMotionPhotoOperation(menuDataBinding);
        return menuDataBinding;
    }

    private static MenuDataBinding createSharingPicturesMenu(Blackboard blackboard, String str) {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_sharing_pictures);
        final boolean argValue = ArgumentsUtil.getArgValue(str, "owner", false);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_show_group_detail) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_sharing_cover_image) {
            public boolean getDefaultVisibility() {
                return argValue;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_delete_shared_album) {
            public boolean getDefaultVisibility() {
                return argValue;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename_shared_album) {
            public boolean getDefaultVisibility() {
                return argValue;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove) {
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_download_in_sharing_album) {
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_leave_shared_album) {
            public boolean getDefaultVisibility() {
                return !argValue;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_preview_suggestion_to_add) {
            public boolean getDefaultExcluding() {
                return !Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_SUGGEST);
            }

            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_sortby) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_SHARED_SORT);
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_share_link_to_album) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_album_settings) {
            public boolean getDefaultVisibility() {
                return PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK;
            }
        });
        MenuDataBinder.bindActionViewAs(menuDataBinding, blackboard);
        return menuDataBinding;
    }

    private static MenuDataBinding createShortcutAlbumPicturesMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_album_pictures_shortcut);
        MenuDataBinder.bindRemoveFavoriteInList(menuDataBinding);
        return menuDataBinding;
    }

    private static MenuDataBinding createStoryPicturesMenu() {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return createStoryPicturesMenuR();
        }
        return createStoryPicturesMenuLegacy();
    }

    private static MenuDataBinding createStoryPicturesMenuLegacy() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_story_pictures_legacy);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_remove_from_story) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_change_story_cover_image) {
            public boolean getDefaultVisibility() {
                return Features.isEnabled(Features.SUPPORT_STORY_COVER);
            }
        });
        return menuDataBinding;
    }

    private static MenuDataBinding createStoryPicturesMenuR() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_story_pictures);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_create_highlight_reel_story) {
            public boolean getDefaultVisibility() {
                if (!Features.isEnabled(Features.SUPPORT_DOWNLOAD_STORY_VIDEO_EDITOR) || PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_pintotop) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN || PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
                    return false;
                }
                return true;
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_unpin) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi40.SUPPORT_STORIES_PIN || PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
                    return false;
                }
                return true;
            }
        });
        return menuDataBinding;
    }

    private static MenuDataBinding createTimelineMenu() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_timeline_pictures);
        MenuDataBinder.bindActionUsbOtg(menuDataBinding);
        MenuDataBinder.bindActionVerizonCloud(menuDataBinding);
        MenuDataBinder.bindActionMemorySaver(menuDataBinding);
        MenuDataBinder.bindActionDeleteDuplicatePictures(menuDataBinding);
        MenuDataBinder.bindActionCloud(menuDataBinding);
        MenuDataBinder.bindActionSimilar(menuDataBinding);
        MenuDataBinder.bindActionQuickSearch(menuDataBinding);
        MenuDataBinder.bindAddFavoriteInList(menuDataBinding);
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_select, true));
        MenuDataBinder.bindActionTrash(menuDataBinding);
        MenuDataBinder.bindActionSettings(menuDataBinding);
        MenuDataBinder.bindMotionPhotoOperation(menuDataBinding);
        return menuDataBinding;
    }
}
