package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopupMenuDataFactory {
    public static PopupMenuData create(PopupMenuParams popupMenuParams) {
        String removeArgs = ArgumentsUtil.removeArgs(getLocationKey(popupMenuParams));
        if (isPickerMode(popupMenuParams)) {
            return createPickerMenuData(popupMenuParams);
        }
        if (isPicturesNotInTimeline(removeArgs)) {
            return createPicturesMenuData(removeArgs, popupMenuParams);
        }
        return createMenuData(removeArgs, popupMenuParams);
    }

    private static PopupMenuData createMenuData(String str, PopupMenuParams popupMenuParams) {
        if (LocationKey.isAlbums(str)) {
            if (isMxAlbums(str)) {
                return new PopupMenuDataView$MxAlbumMenuData(popupMenuParams);
            }
            return new PopupMenuDataView$AlbumMenuData(popupMenuParams);
        } else if (LocationKey.isFolder(str)) {
            return new PopupMenuDataView$FolderMenuData(popupMenuParams);
        } else {
            if (LocationKey.isSharings(str)) {
                return new PopupMenuDataView$SharingsMenuData(popupMenuParams);
            }
            if (LocationKey.isStories(str) || LocationKey.isStoriesCategory(str)) {
                return new PopupMenuDataView$StoriesMenuData(popupMenuParams);
            }
            if (LocationKey.isRevitalizationView(str)) {
                return new PopupMenuDataView$RevitalizationMenuData(popupMenuParams);
            }
            if (LocationKey.isTrash(str)) {
                return new PopupMenuDataView$TrashMenuData(popupMenuParams);
            }
            if (LocationKey.isTimeline(str)) {
                return new PopupMenuDataView$TimelineMenuData(popupMenuParams);
            }
            if ("location://search".equals(str)) {
                return new PopupMenuDataView$SearchMenuData(popupMenuParams);
            }
            if (LocationKey.isMapCluster(str)) {
                return new PopupMenuDataView$MapMenuData(popupMenuParams);
            }
            return null;
        }
    }

    private static PopupMenuData createPickerMenuData(PopupMenuParams popupMenuParams) {
        if (LocationKey.isTimeline(getLocationKey(popupMenuParams))) {
            return new PopupMenuDataView$TimelineMenuData(popupMenuParams);
        }
        if (LocationKey.isAlbumsMatch(getLocationKey(popupMenuParams))) {
            return new PopupMenuDataView$AlbumMenuData(popupMenuParams);
        }
        return null;
    }

    private static PopupMenuData createPicturesMenuData(String str, PopupMenuParams popupMenuParams) {
        if (LocationKey.isAlbumPictures(str)) {
            return new PopupMenuDataPictures$AlbumPicturesMenuData(popupMenuParams);
        }
        if (LocationKey.isVirtualPictures(str)) {
            return new PopupMenuDataPictures$VirtualPicturesMenuData(popupMenuParams);
        }
        if (LocationKey.isSharingPictures(str)) {
            return new PopupMenuDataPictures$SharingPicturesMenuData(popupMenuParams);
        }
        if (LocationKey.isStoryPictures(str)) {
            return new PopupMenuDataPictures$StoryPicturesMenuData(popupMenuParams);
        }
        if (LocationKey.isCleanOutPictures(str)) {
            return new PopupMenuDataPictures$CleanOutPicturesMenuData(popupMenuParams);
        }
        if (LocationKey.isSearchPictures(str)) {
            return new PopupMenuDataPictures$SearchPicturesMenuData(popupMenuParams);
        }
        return new PopupMenuDataPictures$PicturesMenuData(popupMenuParams);
    }

    private static String getLocationKey(PopupMenuParams popupMenuParams) {
        return popupMenuParams.getLocationKey();
    }

    private static boolean isMxAlbums(String str) {
        if (!LocationKey.isAlbumsMatch(str) || !PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return false;
        }
        return true;
    }

    private static boolean isPickerMode(PopupMenuParams popupMenuParams) {
        return popupMenuParams.isPickerMode();
    }

    private static boolean isPicturesNotInTimeline(String str) {
        if (!LocationKey.isPictures(str) || LocationKey.isTimeline(str)) {
            return false;
        }
        return true;
    }
}
