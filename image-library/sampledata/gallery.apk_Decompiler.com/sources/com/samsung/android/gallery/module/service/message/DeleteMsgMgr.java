package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.service.support.DeleteAlbumInfo;
import com.samsung.android.gallery.module.service.support.DeleteItemInfo;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StringResources;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeleteMsgMgr {
    private static void appendCloud(Context context, StringBuilder sb2, boolean z, boolean z3, int i2, int i7, int i8, boolean z7) {
        if (z) {
            appendNewLine(sb2);
            if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                sb2.append(getCloudInvolvedDescriptionOneUI50(context, z3, i2, i7, i8, z7));
            } else {
                sb2.append(getCloudInvolvedDescription(context, z3, i2, i7, i8));
            }
        }
    }

    private static void appendEmptyAlbum(Context context, StringBuilder sb2, int i2) {
        if (i2 > 0) {
            appendNewLine(sb2);
            sb2.append(context.getResources().getQuantityString(R$plurals.empty_albums_will_not_be_moved_to_the_trash, i2, new Object[]{Integer.valueOf(i2)}));
        }
    }

    private static void appendNewLine(StringBuilder sb2) {
        if (sb2.length() > 0) {
            sb2.append("\n\n");
        }
    }

    private static void appendSdCard(Context context, StringBuilder sb2, boolean z) {
        if (z && PreferenceCache.SdCardWarning.compareAndSet(false, true)) {
            appendNewLine(sb2);
            sb2.append(context.getString(R$string.remember_to_empty_the_trash_before_removing_your_sd_card));
        }
    }

    private static void appendTrashStorage(Context context, StringBuilder sb2) {
        if (PocFeatures.isEnabled(PocFeatures.ShowTrashStorage)) {
            appendNewLine(sb2);
            sb2.append(context.getString(R$string.trash));
            sb2.append(": ");
            sb2.append(TrashHelperFactory.getDeleteHelper(context).getReadableTrashStorage());
        }
    }

    private static void appendVirtualAlbum(Context context, StringBuilder sb2, boolean z, boolean z3, int i2) {
        int i7;
        int i8;
        if (!z) {
            return;
        }
        if (z3) {
            if (i2 == 1) {
                i8 = R$string.its_original_will_also_be_moved_to_the_trash;
            } else {
                i8 = R$string.their_originals_will_also_be_moved_to_the_trash;
            }
            sb2.append(context.getString(i8));
            return;
        }
        if (i2 == 1) {
            i7 = R$string.its_original_will_also_be_deleted;
        } else {
            i7 = R$string.their_originals_will_also_be_deleted;
        }
        sb2.append(context.getString(i7));
    }

    private static void appendWarning(Context context, StringBuilder sb2, boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        if (!z) {
            if (z3) {
                i2 = R$string.are_you_sure_you_want_to_move_to_trash_everything_q;
            } else {
                i2 = R$string.are_you_sure_you_want_to_delete_everything_q;
            }
            sb2.append(context.getString(i2));
        } else if (CloudStateCompat.isEnabled()) {
            if (z3) {
                i8 = R$string.are_you_sure_you_want_to_move_to_trash_everything_q_recycle_bin;
            } else {
                i8 = R$string.are_you_sure_you_want_to_delete_everything_q_recycle_bin;
            }
            sb2.append(context.getString(i8, new Object[]{StringResources.getCloudBrand()}));
        } else {
            if (z3) {
                i7 = R$string.are_you_sure_you_want_to_move_to_trash_everything_q_trash;
            } else {
                i7 = R$string.are_you_sure_you_want_to_delete_everything_q_trash;
            }
            sb2.append(context.getString(i7, new Object[]{StringResources.getCloudBrand()}));
        }
    }

    private static String getAlbumDescription(Context context, DeleteAlbumInfo deleteAlbumInfo, boolean z) {
        String str;
        if (deleteAlbumInfo.showDeleteAllWarning()) {
            str = getDescriptionDeleteAllWarning(context, deleteAlbumInfo.isCloudInvolved(), deleteAlbumInfo.useTrash(), deleteAlbumInfo.isSdCardInvolved());
        } else if (deleteAlbumInfo.isOnlyAutoNEmptyAlbumSelected()) {
            str = getAlbumDescriptionForAutoNEmptyAlbumOnly(context, deleteAlbumInfo);
        } else if (deleteAlbumInfo.useTrash()) {
            str = getAlbumDescriptionSupportTrash(context, deleteAlbumInfo, z);
        } else {
            str = getAlbumDescriptionNotSupportTrash(context, deleteAlbumInfo.isCloudInvolved(), z);
        }
        if (deleteAlbumInfo.isRecentAlbumInvolved() && deleteAlbumInfo.isFavoriteAlbumInvolved()) {
            return getRecentAndFavoriteAlbumDescription(context, str, deleteAlbumInfo.useTrash());
        }
        if (deleteAlbumInfo.isRecentAlbumInvolved()) {
            return getRecentAlbumDescription(context, str, deleteAlbumInfo.useTrash());
        }
        if (deleteAlbumInfo.isFavoriteAlbumInvolved()) {
            return getFavoriteAlbumDescription(context, str, deleteAlbumInfo.useTrash());
        }
        return str;
    }

    private static String getAlbumDescriptionForAutoNEmptyAlbumOnly(Context context, DeleteAlbumInfo deleteAlbumInfo) {
        if (deleteAlbumInfo.getEmptyAlbumCount() + deleteAlbumInfo.getAutoAlbumCount() == 1) {
            return context.getResources().getString(R$string.content_in_album_will_not_be_deleted_from_gallery);
        }
        return context.getResources().getString(R$string.content_in_albums_will_not_be_deleted_from_gallery);
    }

    private static String getAlbumDescriptionNotSupportTrash(Context context, boolean z, boolean z3) {
        StringBuilder sb2 = new StringBuilder();
        appendCloud(context, sb2, z, false, 2, 0, 0, z3);
        return sb2.toString();
    }

    private static String getAlbumDescriptionSupportTrash(Context context, DeleteAlbumInfo deleteAlbumInfo, boolean z) {
        StringBuilder sb2 = new StringBuilder();
        Context context2 = context;
        appendCloud(context2, sb2, deleteAlbumInfo.isCloudInvolved(), true, 2, 0, 0, z);
        appendSdCard(context2, sb2, deleteAlbumInfo.isSdCardInvolved());
        appendEmptyAlbum(context2, sb2, deleteAlbumInfo.getEmptyAlbumCount());
        appendTrashStorage(context2, sb2);
        return sb2.toString();
    }

    public static String[] getAlbumMessage(Context context, DeleteAlbumInfo deleteAlbumInfo, boolean z) {
        return new String[]{getAlbumTitle(context, deleteAlbumInfo), getAlbumDescription(context, deleteAlbumInfo, z), String.valueOf(deleteAlbumInfo.getItemCount()), String.valueOf(deleteAlbumInfo.getAutoAlbumItemCount())};
    }

    private static String getAlbumTitle(Context context, DeleteAlbumInfo deleteAlbumInfo) {
        if (deleteAlbumInfo.showDeleteAllWarning()) {
            return getTitleDeleteAllWarning(context);
        }
        if (deleteAlbumInfo.isOnlyAutoNEmptyAlbumSelected()) {
            return getAlbumTitleForAutoNEmptyAlbumOnly(context, deleteAlbumInfo);
        }
        if (!deleteAlbumInfo.useTrash() || deleteAlbumInfo.getItemCount() <= 0) {
            return getAlbumTitleNotSupportTrash(context, deleteAlbumInfo);
        }
        return getAlbumTitleSupportTrash(context, deleteAlbumInfo);
    }

    private static String getAlbumTitleForAutoNEmptyAlbumOnly(Context context, DeleteAlbumInfo deleteAlbumInfo) {
        int emptyAlbumCount = deleteAlbumInfo.getEmptyAlbumCount() + deleteAlbumInfo.getAutoAlbumCount();
        return context.getResources().getQuantityString(R$plurals.delete_auto_album_title, emptyAlbumCount, new Object[]{Integer.valueOf(emptyAlbumCount)});
    }

    private static String getAlbumTitleNotSupportTrash(Context context, DeleteAlbumInfo deleteAlbumInfo) {
        int albumCount = deleteAlbumInfo.getAlbumCount() - deleteAlbumInfo.getAutoAlbumCount();
        int groupCount = deleteAlbumInfo.getGroupCount();
        int itemCount = deleteAlbumInfo.getItemCount() - deleteAlbumInfo.getAutoAlbumItemCount();
        if (itemCount == 0) {
            if (groupCount == 0) {
                return context.getResources().getQuantityString(R$plurals.n_delete_empty_album, albumCount, new Object[]{Integer.valueOf(albumCount)});
            }
            if (albumCount == 0) {
                return context.getResources().getQuantityString(R$plurals.delete_groups, groupCount, new Object[]{Integer.valueOf(groupCount)});
            }
            if (groupCount == 1) {
                return context.getString(R$string.delete_group_and_albums_q, new Object[]{Integer.valueOf(albumCount)});
            }
            return context.getString(R$string.delete_groups_and_albums_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(albumCount)});
        } else if (groupCount == 0) {
            if (albumCount == 1) {
                if (itemCount == 1) {
                    return context.getString(R$string.delete_album_and_item_q);
                }
                return context.getString(R$string.delete_album_and_items_q, new Object[]{Integer.valueOf(itemCount)});
            } else if (itemCount == 1) {
                return context.getString(R$string.delete_albums_and_item_q, new Object[]{Integer.valueOf(albumCount)});
            } else {
                return context.getString(R$string.delete_albums_and_items_q, new Object[]{Integer.valueOf(albumCount), Integer.valueOf(itemCount)});
            }
        } else if (groupCount == 1) {
            if (albumCount == 1) {
                if (itemCount == 1) {
                    return context.getString(R$string.delete_group_and_album_and_item_q);
                }
                return context.getString(R$string.delete_group_and_album_and_items_q, new Object[]{Integer.valueOf(itemCount)});
            } else if (itemCount == 1) {
                return context.getResources().getQuantityString(R$plurals.delete_group_and_albums_and_item_q, albumCount, new Object[]{Integer.valueOf(albumCount)});
            } else {
                return context.getString(R$string.delete_group_and_albums_and_items_q, new Object[]{Integer.valueOf(albumCount), Integer.valueOf(itemCount)});
            }
        } else if (albumCount == 1) {
            if (itemCount == 1) {
                return context.getString(R$string.delete_groups_and_album_and_item_q, new Object[]{Integer.valueOf(groupCount)});
            }
            return context.getString(R$string.delete_groups_and_album_and_items_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(itemCount)});
        } else if (itemCount == 1) {
            return context.getString(R$string.delete_groups_and_albums_and_item_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(albumCount)});
        } else {
            return context.getString(R$string.delete_groups_and_albums_and_items_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(albumCount), Integer.valueOf(itemCount)});
        }
    }

    private static String getAlbumTitleSupportTrash(Context context, DeleteAlbumInfo deleteAlbumInfo) {
        int albumCount = deleteAlbumInfo.getAlbumCount() - deleteAlbumInfo.getAutoAlbumCount();
        int itemCount = deleteAlbumInfo.getItemCount() - deleteAlbumInfo.getAutoAlbumItemCount();
        int groupCount = deleteAlbumInfo.getGroupCount() - deleteAlbumInfo.getEmptyGroupCount();
        int emptyAlbumCount = albumCount - deleteAlbumInfo.getEmptyAlbumCount();
        if (groupCount == 0) {
            if (emptyAlbumCount == 1) {
                if (itemCount == 1) {
                    return context.getString(R$string.move_album_and_item_to_the_trash_q);
                }
                return context.getString(R$string.move_album_and_items_to_the_trash_q, new Object[]{Integer.valueOf(itemCount)});
            } else if (itemCount == 1) {
                return context.getString(R$string.move_albums_and_item_to_the_trash_q, new Object[]{Integer.valueOf(emptyAlbumCount)});
            } else {
                return context.getString(R$string.move_albums_and_items_to_the_trash_q, new Object[]{Integer.valueOf(emptyAlbumCount), Integer.valueOf(itemCount)});
            }
        } else if (groupCount == 1) {
            if (emptyAlbumCount == 1) {
                if (itemCount == 1) {
                    return context.getString(R$string.move_group_and_album_and_item_to_the_trash_q);
                }
                return context.getResources().getQuantityString(R$plurals.move_group_and_album_and_items_to_the_trash_q, itemCount, new Object[]{Integer.valueOf(itemCount)});
            } else if (itemCount == 1) {
                return context.getResources().getQuantityString(R$plurals.move_group_and_albums_and_item_to_the_trash_q, emptyAlbumCount, new Object[]{Integer.valueOf(emptyAlbumCount)});
            } else {
                return context.getString(R$string.move_group_and_albums_and_items_to_the_trash_q, new Object[]{Integer.valueOf(emptyAlbumCount), Integer.valueOf(itemCount)});
            }
        } else if (emptyAlbumCount == 1) {
            if (itemCount == 1) {
                return context.getResources().getQuantityString(R$plurals.move_groups_and_album_and_item_to_the_trash_q, groupCount, new Object[]{Integer.valueOf(groupCount)});
            }
            return context.getString(R$string.move_groups_and_album_and_items_to_the_trash_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(itemCount)});
        } else if (itemCount == 1) {
            return context.getString(R$string.move_groups_and_albums_and_item_to_the_trash_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(emptyAlbumCount)});
        } else {
            return context.getString(R$string.move_groups_and_albums_and_items_to_the_trash_q, new Object[]{Integer.valueOf(groupCount), Integer.valueOf(emptyAlbumCount), Integer.valueOf(itemCount)});
        }
    }

    private static String getCloudInvolvedDescription(Context context, boolean z, int i2, int i7, int i8) {
        int i10;
        if (i2 == 1) {
            if (i7 == 1) {
                if (z) {
                    i10 = R$string.this_image_will_be_delete_from_all_synced_devices_gallery;
                } else {
                    i10 = R$string.this_image_will_be_delete_from_all_synced_devices_cloud;
                }
            } else if (z) {
                i10 = R$string.this_video_will_be_delete_from_all_synced_devices_gallery;
            } else {
                i10 = R$string.this_video_will_be_delete_from_all_synced_devices_cloud;
            }
        } else if (i7 == i2) {
            if (z) {
                i10 = R$string.these_images_will_be_delete_from_all_synced_devices_gallery;
            } else {
                i10 = R$string.these_images_will_be_delete_from_all_synced_devices_cloud;
            }
        } else if (i8 == i2) {
            if (z) {
                i10 = R$string.these_videos_will_be_delete_from_all_synced_devices_gallery;
            } else {
                i10 = R$string.these_videos_will_be_delete_from_all_synced_devices_cloud;
            }
        } else if (z) {
            i10 = R$string.these_items_will_be_delete_from_all_synced_devices_gallery;
        } else {
            i10 = R$string.these_items_will_be_delete_from_all_synced_devices_cloud;
        }
        return context.getString(i10, new Object[]{StringResources.getCloudBrand()});
    }

    private static String getCloudInvolvedDescriptionOneUI50(Context context, boolean z, int i2, int i7, int i8, boolean z3) {
        int i10;
        if (i2 == 1) {
            if (i7 == 1) {
                if (!z) {
                    i10 = R$string.trash_off_onedrive_image_description;
                } else if (z3) {
                    i10 = R$string.trash_on_samsung_cloud_image_description;
                } else {
                    i10 = R$string.trash_on_onedrive_image_description;
                }
            } else if (!z) {
                i10 = R$string.trash_off_onedrive_video_description;
            } else if (z3) {
                i10 = R$string.trash_on_samsung_cloud_video_description;
            } else {
                i10 = R$string.trash_on_onedrive_video_description;
            }
        } else if (i7 == i2) {
            if (!z) {
                i10 = R$string.trash_off_onedrive_images_description;
            } else if (z3) {
                i10 = R$string.trash_on_samsung_cloud_images_description;
            } else {
                i10 = R$string.trash_on_onedrive_images_description;
            }
        } else if (i8 == i2) {
            if (!z) {
                i10 = R$string.trash_off_onedrive_videos_description;
            } else if (z3) {
                i10 = R$string.trash_on_samsung_cloud_videos_description;
            } else {
                i10 = R$string.trash_on_onedrive_videos_description;
            }
        } else if (!z) {
            i10 = R$string.trash_off_onedrive_items_description;
        } else if (z3) {
            i10 = R$string.trash_on_samsung_cloud_items_description;
        } else {
            i10 = R$string.trash_on_onedrive_items_description;
        }
        return context.getString(i10, new Object[]{StringResources.getCloudBrand()});
    }

    public static int getDeleteFailedToastMessage(int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == 0) {
            return -1;
        }
        if (i8 != 1) {
            if (i2 == i8) {
                return R$string.could_not_move_images_to_the_trash;
            }
            if (i7 == i8) {
                return R$string.could_not_move_videos_to_the_trash;
            }
            return R$string.could_not_move_items_to_the_trash;
        } else if (i2 == 1) {
            return R$string.could_not_move_image_to_the_trash;
        } else {
            return R$string.could_not_move_video_to_the_trash;
        }
    }

    private static String getDescriptionDeleteAllWarning(Context context, boolean z, boolean z3, boolean z7) {
        StringBuilder sb2 = new StringBuilder();
        appendWarning(context, sb2, z, z3);
        if (z3) {
            appendSdCard(context, sb2, z7);
            appendTrashStorage(context, sb2);
        }
        return sb2.toString();
    }

    private static String getFavoriteAlbumDescription(Context context, String str, boolean z) {
        String str2;
        int i2;
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            str2 = C0212a.A(str, "\n");
        } else {
            str2 = "";
        }
        sb2.append(str2);
        if (z) {
            i2 = R$string.cannot_move_to_trash_favorite_album;
        } else {
            i2 = R$string.cannot_delete_favorite_album;
        }
        sb2.append(context.getString(i2));
        return sb2.toString();
    }

    public static String[] getGroupMessage(Context context, DeleteItemInfo deleteItemInfo, boolean z, int i2, boolean z3) {
        return new String[]{getGroupShotTitle(context, deleteItemInfo, z), getGroupShotCheckBoxDescription(context, deleteItemInfo.useTrash(), z, i2), getGroupShotDescription(context, deleteItemInfo, z3)};
    }

    private static String getGroupShotCheckBoxDescription(Context context, boolean z, boolean z3, int i2) {
        int i7;
        int i8;
        int i10;
        if (!z3) {
            return null;
        }
        if (i2 == 0) {
            if (z) {
                i7 = StringResources.getBurstShotStringId(R$string.also_move_the_rest_of_the_burst_shot);
            } else {
                i7 = StringResources.getBurstShotStringId(R$string.also_delete_the_rest_of_the_burst_shot);
            }
            return context.getString(i7);
        } else if (i2 == 1) {
            if (z) {
                i8 = R$string.also_move_all_similar_images_in_this_group;
            } else {
                i8 = R$string.also_delete_all_similar_images_in_this_group;
            }
            return context.getString(i8);
        } else if (i2 != 2) {
            return null;
        } else {
            if (z) {
                i10 = R$string.also_move_the_rest_of_the_single_take_set;
            } else {
                i10 = R$string.also_delete_the_rest_of_the_single_take_set;
            }
            return context.getString(i10);
        }
    }

    private static String getGroupShotDescription(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        if (deleteItemInfo.isCloudInvolved() || deleteItemInfo.isSdCardInvolved()) {
            return getItemDescription(context, deleteItemInfo, z);
        }
        return null;
    }

    private static String getGroupShotTitle(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        boolean z3;
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        boolean z7 = false;
        if (deleteItemInfo.getTotalVideoCount() > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z) {
            int totalCount = deleteItemInfo.getTotalCount();
            if (deleteItemInfo.useTrash()) {
                Resources resources = context.getResources();
                if (z3) {
                    i7 = R$plurals.move_selected_item_to_the_trash_plural;
                } else {
                    i7 = R$plurals.move_selected_picture_to_the_trash_plural;
                }
                return resources.getQuantityString(i7, totalCount, new Object[]{Integer.valueOf(totalCount)});
            }
            Resources resources2 = context.getResources();
            if (z3) {
                i2 = R$plurals.delete_selected_item_plural;
            } else {
                i2 = R$plurals.delete_selected_picture_plural;
            }
            return resources2.getQuantityString(i2, totalCount, new Object[]{Integer.valueOf(totalCount)});
        } else if (!deleteItemInfo.useTrash()) {
            if (z3) {
                i8 = R$string.delete_this_video;
            } else {
                i8 = R$string.delete_this_image;
            }
            return context.getString(i8);
        } else if (deleteItemInfo.getTotalCount() == 1) {
            if (z3) {
                i11 = R$string.move_this_video_to_the_trash;
            } else {
                i11 = R$string.move_this_image_to_the_trash;
            }
            return context.getString(i11);
        } else {
            if (deleteItemInfo.getTotalCount() == deleteItemInfo.getTotalImageCount()) {
                z7 = true;
            }
            Resources resources3 = context.getResources();
            if (z7) {
                i10 = R$plurals.move_selected_picture_to_the_trash_plural;
            } else {
                i10 = R$plurals.move_selected_item_to_the_trash_plural;
            }
            return resources3.getQuantityString(i10, deleteItemInfo.getTotalCount(), new Object[]{Integer.valueOf(deleteItemInfo.getTotalCount())});
        }
    }

    private static String getItemDescription(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        if (deleteItemInfo.showDeleteAllWarning()) {
            return getDescriptionDeleteAllWarning(context, deleteItemInfo.isCloudInvolved(), deleteItemInfo.useTrash(), deleteItemInfo.isSdCardInvolved());
        }
        if (deleteItemInfo.useTrash()) {
            return getItemDescriptionSupportTrash(context, deleteItemInfo, z);
        }
        return getItemDescriptionNotSupportTrash(context, deleteItemInfo, z);
    }

    private static String getItemDescriptionNotSupportTrash(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        StringBuilder sb2 = new StringBuilder();
        appendVirtualAlbum(context, sb2, deleteItemInfo.isVirtual(), false, deleteItemInfo.getTotalCount());
        appendCloud(context, sb2, deleteItemInfo.isCloudInvolved(), false, deleteItemInfo.getTotalCount(), deleteItemInfo.getTotalImageCount(), deleteItemInfo.getTotalVideoCount(), z);
        return sb2.toString();
    }

    private static String getItemDescriptionSupportTrash(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        StringBuilder sb2 = new StringBuilder();
        appendVirtualAlbum(context, sb2, deleteItemInfo.isVirtual(), true, deleteItemInfo.getTotalCount());
        Context context2 = context;
        appendCloud(context2, sb2, deleteItemInfo.isCloudInvolved(), true, deleteItemInfo.getTotalCount(), deleteItemInfo.getTotalImageCount(), deleteItemInfo.getTotalVideoCount(), z);
        appendSdCard(context2, sb2, deleteItemInfo.isSdCardInvolved());
        appendTrashStorage(context2, sb2);
        return sb2.toString();
    }

    public static String[] getItemMessage(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        return new String[]{getItemTitle(context, deleteItemInfo), getItemDescription(context, deleteItemInfo, z)};
    }

    private static String getItemTitle(Context context, DeleteItemInfo deleteItemInfo) {
        if (deleteItemInfo.showDeleteAllWarning()) {
            return getTitleDeleteAllWarning(context);
        }
        int similarPhotoCount = deleteItemInfo.getSimilarPhotoCount();
        if (deleteItemInfo.useTrash()) {
            if (similarPhotoCount > 0) {
                return getSimilarItemTitleSupportTrash(context, deleteItemInfo.getTotalCount(), similarPhotoCount);
            }
            return getItemTitleSupportTrash(context, deleteItemInfo);
        } else if (similarPhotoCount > 0) {
            return getSimilarItemTitleNotSupportTrash(context, deleteItemInfo.getTotalCount(), similarPhotoCount);
        } else {
            return getItemTitleNotSupportTrash(context, deleteItemInfo);
        }
    }

    private static String getItemTitleNotSupportTrash(Context context, DeleteItemInfo deleteItemInfo) {
        int totalCount = deleteItemInfo.getTotalCount();
        if (totalCount == deleteItemInfo.getBurstShotCount()) {
            if (deleteItemInfo.getBurstShotCount() == 1) {
                return context.getString(StringResources.getBurstShotStringId(R$string.delete_one_burst_shot));
            }
            return context.getString(StringResources.getBurstShotStringId(R$string.delete_n_burst_shots), new Object[]{Integer.valueOf(totalCount)});
        } else if (totalCount == deleteItemInfo.getSingleTakenCount()) {
            return context.getResources().getQuantityString(R$plurals.delete_single_taken, totalCount, new Object[]{Integer.valueOf(totalCount)});
        } else {
            if (totalCount == deleteItemInfo.getImageCount()) {
                return context.getResources().getQuantityString(R$plurals.delete_images, totalCount, new Object[]{Integer.valueOf(totalCount)});
            }
            if (totalCount == deleteItemInfo.getVideoCount()) {
                return context.getResources().getQuantityString(R$plurals.delete_videos, totalCount, new Object[]{Integer.valueOf(totalCount)});
            }
            return context.getResources().getQuantityString(R$plurals.delete_items, totalCount, new Object[]{Integer.valueOf(totalCount)});
        }
    }

    private static String getItemTitleSupportTrash(Context context, DeleteItemInfo deleteItemInfo) {
        int totalCount = deleteItemInfo.getTotalCount();
        if (totalCount == deleteItemInfo.getBurstShotCount()) {
            return context.getResources().getQuantityString(StringResources.getBurstShotStringId(R$plurals.move_burst_shot_to_the_trash_plural), totalCount, new Object[]{Integer.valueOf(totalCount)});
        }
        if (totalCount == deleteItemInfo.getSingleTakenCount()) {
            return context.getResources().getQuantityString(R$plurals.move_single_taken_to_the_trash_plural, totalCount, new Object[]{Integer.valueOf(totalCount)});
        }
        if (totalCount == deleteItemInfo.getImageCount()) {
            return context.getResources().getQuantityString(R$plurals.move_images_to_the_trash_plural, totalCount, new Object[]{Integer.valueOf(totalCount)});
        }
        if (totalCount == deleteItemInfo.getVideoCount()) {
            return context.getResources().getQuantityString(R$plurals.move_videos_to_the_trash_plural, totalCount, new Object[]{Integer.valueOf(totalCount)});
        }
        return context.getResources().getQuantityString(R$plurals.move_items_to_the_trash_plural, totalCount, new Object[]{Integer.valueOf(totalCount)});
    }

    public static String getProgressTitleRes(Context context, boolean z, boolean z3, int i2, int i7, int i8) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        if (z3) {
            if (z) {
                return context.getResources().getQuantityString(R$plurals.moving_items_to_the_trash, i2, new Object[]{Integer.valueOf(i2)});
            }
            return context.getString(R$string.deleting_items);
        } else if (i2 == 1) {
            if (i7 == 1) {
                if (z) {
                    i14 = R$string.moving_image_to_the_trash;
                } else {
                    i14 = R$string.deleting_image;
                }
                return context.getString(i14);
            }
            if (z) {
                i13 = R$string.moving_video_to_the_trash;
            } else {
                i13 = R$string.deleting_video;
            }
            return context.getString(i13);
        } else if (i7 == i2) {
            if (z) {
                i12 = R$string.moving_images_to_the_trash;
            } else {
                i12 = R$string.deleting_images;
            }
            return context.getString(i12);
        } else if (i8 == i2) {
            if (z) {
                i11 = R$string.moving_videos_to_the_trash;
            } else {
                i11 = R$string.deleting_videos;
            }
            return context.getString(i11);
        } else {
            if (z) {
                i10 = R$string.moving_items_to_the_trash;
            } else {
                i10 = R$string.deleting_items;
            }
            return context.getString(i10);
        }
    }

    private static String getRecentAlbumDescription(Context context, String str, boolean z) {
        String str2;
        int i2;
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            str2 = C0212a.A(str, "\n");
        } else {
            str2 = "";
        }
        sb2.append(str2);
        if (z) {
            i2 = R$string.cannot_move_to_trash_recent_album;
        } else {
            i2 = R$string.cannot_delete_recent_album;
        }
        sb2.append(context.getString(i2));
        return sb2.toString();
    }

    private static String getRecentAndFavoriteAlbumDescription(Context context, String str, boolean z) {
        String str2;
        int i2;
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            str2 = C0212a.A(str, "\n");
        } else {
            str2 = "";
        }
        sb2.append(str2);
        if (z) {
            i2 = R$string.cannot_move_to_trash_recent_and_favorite_album;
        } else {
            i2 = R$string.cannot_delete_recent_and_favorite_album;
        }
        sb2.append(context.getString(i2));
        return sb2.toString();
    }

    private static String getSimilarItemTitleNotSupportTrash(Context context, int i2, int i7) {
        int i8 = i2 - i7;
        if (i8 == 0) {
            return context.getResources().getQuantityString(R$plurals.delete_n_similar_photo, i7, new Object[]{Integer.valueOf(i7)});
        }
        if (i7 == 1) {
            if (i8 == 1) {
                return context.getString(R$string.delete_one_similar_photo_and_one);
            }
            return context.getResources().getQuantityString(R$plurals.delete_one_similar_photo_and_n, i8, new Object[]{Integer.valueOf(i8)});
        } else if (i8 == 1) {
            return context.getResources().getQuantityString(R$plurals.delete_n_similar_photo_and_one, i7, new Object[]{Integer.valueOf(i7)});
        } else {
            return context.getString(R$string.delete_n_similar_photo_and_n, new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)});
        }
    }

    private static String getSimilarItemTitleSupportTrash(Context context, int i2, int i7) {
        int i8 = i2 - i7;
        if (i8 == 0) {
            return context.getResources().getQuantityString(R$plurals.move_n_similar_photo, i7, new Object[]{Integer.valueOf(i7)});
        }
        if (i7 == 1) {
            if (i8 == 1) {
                return context.getString(R$string.move_one_similar_photo_and_one);
            }
            return context.getResources().getQuantityString(R$plurals.move_one_similar_photo_and_n, i8, new Object[]{Integer.valueOf(i8)});
        } else if (i8 == 1) {
            return context.getResources().getQuantityString(R$plurals.move_n_similar_photo_and_one, i7, new Object[]{Integer.valueOf(i7)});
        } else {
            return context.getString(R$string.move_n_similar_photo_and_n, new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)});
        }
    }

    public static String[] getSingleItemMessage(Context context, DeleteItemInfo deleteItemInfo, boolean z) {
        return new String[]{getSingleItemTitle(context, deleteItemInfo), getItemDescription(context, deleteItemInfo, z)};
    }

    private static String getSingleItemTitle(Context context, DeleteItemInfo deleteItemInfo) {
        int i2;
        int i7;
        if (deleteItemInfo.useTrash()) {
            if (deleteItemInfo.getVideoCount() > 0) {
                i7 = R$string.move_this_video_to_the_trash;
            } else {
                i7 = R$string.move_this_image_to_the_trash;
            }
            return context.getString(i7);
        }
        if (deleteItemInfo.getVideoCount() > 0) {
            i2 = R$string.delete_this_video;
        } else {
            i2 = R$string.delete_this_image;
        }
        return context.getString(i2);
    }

    private static String getTitleDeleteAllWarning(Context context) {
        return context.getString(R$string.hold_on_a_second);
    }
}
