package com.samsung.android.gallery.app.ui.menu.list;

import M3.c;
import T6.a;
import T8.C0578a;
import a8.d;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.app.controller.creature.RemoveBackgroundEffectInfoCmd;
import com.samsung.android.gallery.app.controller.externals.CopyEffectCmd;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd;
import com.samsung.android.gallery.app.controller.externals.PasteClipboardCmd;
import com.samsung.android.gallery.app.controller.externals.PasteEffectCmd;
import com.samsung.android.gallery.app.controller.externals.SetAsWallpaperChooserDialogCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.externals.StartGalleryAssistantCmd;
import com.samsung.android.gallery.app.controller.externals.StartMemorySaverCmd;
import com.samsung.android.gallery.app.controller.externals.StartPrintCmd;
import com.samsung.android.gallery.app.controller.externals.StartRemoteServerCmd;
import com.samsung.android.gallery.app.controller.externals.StartTianYiCloudCmd;
import com.samsung.android.gallery.app.controller.externals.StartVzwCloudCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagToListCmd;
import com.samsung.android.gallery.app.controller.internals.AlbumPicturesSearchCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.app.controller.internals.DeleteCmd;
import com.samsung.android.gallery.app.controller.internals.DownloadCmd;
import com.samsung.android.gallery.app.controller.internals.EachExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd2;
import com.samsung.android.gallery.app.controller.internals.ExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.internals.SetAsChooserDialogCmd;
import com.samsung.android.gallery.app.controller.internals.StartCloudCmd;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import com.samsung.android.gallery.app.controller.internals.UploadToRemoteCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.controller.remote.StartSlideshowV2Cmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.RecapCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.controller.viewer.CopyToClipboardCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListMenuHandler extends MenuHandler {
    public ListMenuHandler() {
        setMenuHandler(new AlbumsMenuHandler()).setMenuHandler(new AlbumPicturesMenuHandler()).setMenuHandler(new StoriesMenuHandler()).setMenuHandler(new SharingsMenuHandler()).setMenuHandler(new SearchMenuHandler()).setMenuHandler(new SearchPicturesMenuHandler()).setMenuHandler(new CategoryMenuHandler()).setMenuHandler(new FolderMenuHandler());
    }

    private void handleCancel(EventContext eventContext) {
        ThreadUtil.postOnUiThreadDelayed(new a(eventContext, 2), 100);
    }

    private boolean handleCloudAction(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_cloud_sync_timeline:
                new StartCloudCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_download:
                new DownloadCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_reload:
                if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
                    return true;
                }
                Optional.ofNullable(eventContext.getMediaData()).ifPresent(new c(eventContext, 4));
                return true;
            case R.id.action_tianyi_cloud:
                new StartTianYiCloudCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_upload:
                if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
                    return true;
                }
                new UploadToRemoteCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_verizon_cloud:
                new StartVzwCloudCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }

    private boolean handleCommonAction(EventContext eventContext, MenuItem menuItem) {
        StorageVolumeCompat usbStorageVolume;
        switch (menuItem.getItemId()) {
            case R.id.action_add_favorite_in_list:
                new UpdateFavoriteInListCmd().execute(eventContext, Boolean.TRUE);
                return true;
            case R.id.action_add_tag:
                new AddTagToListCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_album_search:
                new AlbumPicturesSearchCmd().execute(eventContext, eventContext.getCurrentItem());
                return true;
            case R.id.action_cancel:
                handleCancel(eventContext);
                return true;
            case R.id.action_compare_images:
                if (PocFeatures.TBD.COMPARE_IMAGES) {
                    Log.d(this.TAG, "handleActionCompareImages");
                    Intent intent = new Intent();
                    intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.compare.CompareActivity");
                    String str = "data://intent/compare-images/" + eventContext.getActivity().hashCode();
                    MediaItem[] mediaItemArr = (MediaItem[]) Arrays.stream(eventContext.getSelectedItems()).filter(new com.samsung.android.gallery.module.dynamicview.a(21)).limit(20).toArray(new C0578a(13));
                    if (mediaItemArr.length == 1) {
                        MediaItem mediaItem = mediaItemArr[0];
                        if (mediaItem.isBurstShot() || mediaItem.isSimilarShot()) {
                            ThreadUtil.postOnBgThread(new d(mediaItem, str, intent, eventContext));
                        } else {
                            Utils.showToast((Context) eventContext.getActivity(), "please select two more images");
                        }
                    } else {
                        Blackboard.getApplicationInstance().publish(str, mediaItemArr);
                        intent.putExtra("data-key", str);
                        eventContext.getActivity().startActivity(intent);
                    }
                    eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
                    break;
                }
                break;
            case R.id.action_delete_duplicate_pictures:
                eventContext.getBlackboard().post("command://MoveURL", "location://cleanOut/duplicated/fileList");
                return false;
            case R.id.action_memory_saver:
                new StartMemorySaverCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_printer_multi:
                new StartPrintCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_remove_background_effect_info:
                new RemoveBackgroundEffectInfoCmd().execute(eventContext, eventContext.getSelectedItems(), null);
                return true;
            case R.id.action_remove_favorite_in_list:
                new UpdateFavoriteInListCmd().execute(eventContext, Boolean.FALSE);
                return true;
            case R.id.action_search:
                handleSearchAction(eventContext);
                return true;
            case R.id.action_select:
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_set_as_container:
                if (Features.isEnabled(Features.SET_AS_CHOOSER_BY_INTENT_FILTER)) {
                    new SetAsChooserDialogCmd().execute(eventContext, new Object[0]);
                    return true;
                }
                new SetAsWallpaperChooserDialogCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_settings:
                new StartSettingsCmd().execute(eventContext, PopoverUtils.Anchor.TOP_END);
                return true;
            case R.id.action_slideshow:
                new StartSlideshowV2Cmd().execute(eventContext, null);
                return true;
            case R.id.action_slideshow_with_selection:
                if (eventContext.getSelectedItemCount() <= 5000) {
                    new StartSlideshowV2Cmd().execute(eventContext, eventContext.getSelectedItems());
                    return true;
                }
                Utils.showToast(eventContext.getContext(), (int) R.string.cant_select_more_than_5000_items);
                return true;
            case R.id.action_start_server:
                new StartRemoteServerCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_story_recap:
                new RecapCmd().addParameter("slide_show_recap", "1").execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_trash:
                new LaunchTrashBinCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_usb_otg:
                if (Features.isEnabled(Features.SUPPORT_USB_STORAGE) && (usbStorageVolume = FileUtils.getUsbStorageVolume(AppResources.getAppContext())) != null) {
                    new GotoMyFilesCmd().execute(eventContext, C0212a.p(new StringBuilder(), usbStorageVolume.directory, "/DCIM/Camera/"));
                    return true;
                }
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
        return true;
    }

    private boolean handleCreateAction(EventContext eventContext, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_add_to_shared_album) {
            new ChooseSharedAlbumCmd().execute(eventContext, new Object[0]);
            return true;
        } else if (itemId == R.id.action_create) {
            new CreateNewDialogCmd().execute(eventContext, new CreateMenuVisibility().getVisibilities(eventContext.getLocationKey()));
            publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
            return true;
        } else if (itemId != R.id.action_share) {
            return false;
        } else {
            eventContext.prepareExtendedShare();
            new ShareViaCmd().execute(eventContext, eventContext.getSelectedItems(), null);
            return true;
        }
    }

    private boolean handleFileAction(EventContext eventContext, MenuItem menuItem) {
        BaseCommand baseCommand;
        BaseCommand baseCommand2;
        switch (menuItem.getItemId()) {
            case R.id.action_copy_effects:
                new CopyEffectCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_copy_to_album:
                new FileOpCmd().execute(eventContext, FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM, "copy");
                return true;
            case R.id.action_copy_to_clipboard:
                new CopyToClipboardCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_delete:
                new DeleteCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_delete_motion_photo_clip:
                new MotionPhotoDeleteVideoCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_edit_date_and_time:
                if (PocFeatures.isEnabled(PocFeatures.C2paFileEdit)) {
                    baseCommand = new EditDateAndTimeCmd2();
                } else {
                    baseCommand = new EditDateAndTimeCmd();
                }
                baseCommand.execute(eventContext, new Object[0]);
                return true;
            case R.id.action_edit_location:
                if (PocFeatures.isEnabled(PocFeatures.C2paFileEdit)) {
                    baseCommand2 = new ChangeLocationCmd2();
                } else {
                    baseCommand2 = new ChangeLocationCmd();
                }
                baseCommand2.execute(eventContext, new Object[0]);
                return true;
            case R.id.action_export_motion_photo_clip:
                new EachExportVideoClipsCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_gallery_assistant:
                new StartGalleryAssistantCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_merge_motion_photo_clip:
                new ExportVideoClipsCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_move_to_album:
                new FileOpCmd().execute(eventContext, FileOpCmdHelper.CmdType.TYPE_CHOICE_ALBUM, "move");
                return true;
            case R.id.action_move_to_knox:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.MOVE_TO_KNOX);
                return true;
            case R.id.action_move_to_private_album:
                if (!PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                    return true;
                }
                new MoveFilesOnPrivateCmd().execute(eventContext, "move_to_secured", eventContext.getSelectedItems());
                return true;
            case R.id.action_move_to_secure_folder:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
                return true;
            case R.id.action_paste_clipboard:
                new PasteClipboardCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_paste_effects:
                new PasteEffectCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_remove_from_knox:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.REMOVE_FROM_KNOX);
                return true;
            case R.id.action_remove_from_secure_folder:
                new FetchContentsForKnoxCmd().execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
                return true;
            default:
                return false;
        }
    }

    private void handleSearchAction(EventContext eventContext) {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            postEvent(eventContext, EventMessage.obtain(8020, new Object[]{Boolean.FALSE, null}));
        } else {
            moveTo(eventContext, "location://search");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$handleCommonAction$1(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleCommonAction$2(MediaItem mediaItem, String str, Intent intent, EventContext eventContext) {
        Blackboard.getApplicationInstance().publish(str, GroupItemLoader.getGroupShotList(mediaItem).subList(0, 20).toArray(new MediaItem[0]));
        intent.putExtra("data-key", str);
        eventContext.getActivity().startActivity(intent);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        if (handleCommonAction(eventContext, menuItem) || handleFileAction(eventContext, menuItem) || handleCreateAction(eventContext, menuItem) || handleCloudAction(eventContext, menuItem)) {
            return true;
        }
        return false;
    }
}
