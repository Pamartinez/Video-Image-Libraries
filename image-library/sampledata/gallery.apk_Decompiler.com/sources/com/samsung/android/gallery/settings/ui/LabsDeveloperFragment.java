package com.samsung.android.gallery.settings.ui;

import Ad.j;
import Ba.a;
import Bb.l;
import D7.g;
import Da.f;
import Fa.A;
import Fa.B;
import Fa.C;
import Fa.C0558l;
import Fa.C0568w;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.remotegallery.RemoteClientUi;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.InputDialogBuilder;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsDeveloperFragment extends LabsBaseFragment {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class YearTimeSlot {
        /* access modifiers changed from: private */
        public static final int[] ARRAY = {1, 2, 4, 8, 10, 20, 30, 40, 50, 60};
        private Consumer<Integer> mCallback;
        private final AlertDialog mDialog;
        /* access modifiers changed from: private */
        public int mTimeSlot = getValue();

        public YearTimeSlot(Context context) {
            View inflate = LayoutInflater.from(context).inflate(R$layout.simple_seekbar_dialog, (ViewGroup) null, false);
            final TextView textView = (TextView) inflate.findViewById(R$id.time);
            textView.setText(this.mTimeSlot + " minutes");
            inflate.findViewById(R$id.startAnimation).setVisibility(8);
            inflate.findViewById(R$id.endAnimation).setVisibility(8);
            SeekBar seekBar = (SeekBar) inflate.findViewById(R$id.seekBar);
            seekBar.setMin(0);
            seekBar.setMax(9);
            seekBar.setProgress(findIndex(this.mTimeSlot));
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                    YearTimeSlot.this.mTimeSlot = YearTimeSlot.ARRAY[i2];
                    TextView textView = textView;
                    textView.setText(YearTimeSlot.this.mTimeSlot + " minutes");
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            this.mDialog = new AlertDialog.Builder(context).setTitle((CharSequence) "Year time-slot configuration").setView(inflate).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new z(this, 0)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new z(this, 1)).create();
        }

        private int findIndex(int i2) {
            int i7 = 0;
            while (true) {
                int[] iArr = ARRAY;
                if (i7 >= iArr.length) {
                    return 6;
                }
                if (iArr[i7] == i2) {
                    return i7;
                }
                i7++;
            }
        }

        public static int getValue() {
            return PreferenceCache.YearTimeSlot.getInt();
        }

        /* access modifiers changed from: private */
        public void onNegativeClicked(DialogInterface dialogInterface, int i2) {
            dismiss();
        }

        /* access modifiers changed from: private */
        public void onPositiveClicked(DialogInterface dialogInterface, int i2) {
            PreferenceCache.YearTimeSlot.setInt(this.mTimeSlot);
            Consumer<Integer> consumer = this.mCallback;
            if (consumer != null) {
                consumer.accept(Integer.valueOf(this.mTimeSlot));
            }
        }

        public void dismiss() {
            this.mDialog.dismiss();
        }

        public YearTimeSlot setCallback(Consumer<Integer> consumer) {
            this.mCallback = consumer;
            return this;
        }

        public void show() {
            this.mDialog.show();
        }
    }

    private void deleteDarkCache() {
        SimpleThreadPool.getInstance().execute(new f(20, this, new ProgressDialogCompat(getContext()).setProgressMessage(R$string.processing).setCancelable(false).setCanceledOnTouchOutside(false).build().show()));
    }

    private void initPreferenceAiFeatures(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_ai_features");
        if (DeepSkyHelper.isObjectCaptureSupported()) {
            addSwitchPreference(preferenceGroup, PocFeatures.ObjectCaptureDebug, "Object Capture Debug Mode", "Show object capture outline for debugging");
        }
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SuggestIntelligent, "Suggest intelligent stuff", "Show revitalize, highlight, and portrait");
    }

    private void initPreferenceDebugLog(PreferenceScreen preferenceScreen) {
        addSwitchPreference((PreferenceGroup) preferenceScreen.findPreference("labs_debug_log_options"), PocFeatures.PerformanceLog, getString(R$string.labs_title_performance_log), getString(R$string.labs_summary_performance_log), new l(29));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v28, types: [java.lang.String[], java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initPreferenceDevOptions(androidx.preference.PreferenceScreen r12) {
        /*
            r11 = this;
            java.lang.String r1 = "labs_developer_options"
            androidx.preference.Preference r1 = r12.findPreference(r1)
            r6 = r1
            androidx.preference.PreferenceGroup r6 = (androidx.preference.PreferenceGroup) r6
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.AMapPreferred
            java.lang.String r2 = "Use AMAP"
            r7 = 0
            r11.addSwitchPreference(r6, r1, r2, r7)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.FileOpService2
            java.lang.String r2 = "File operation service v2"
            r11.addSwitchPreference(r6, r1, r2, r7)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.TimelineGridExtension
            java.lang.String r2 = "Add 12x on pictures tab"
            java.lang.String r3 = "Support more grid configuration of real,3,4,7(month),12(month), and year"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.NewTrash
            java.lang.String r2 = "New Trash(Mp trash)"
            java.lang.String r3 = "Use Mp trash provider"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.ExposeNonDestructiveRecordingInSearch
            java.lang.String r2 = "Expose NonDestructive recording in Search"
            java.lang.String r3 = "Expose nondestructive recording in Search shot mode category (for SM/SSM)"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.UndoPeopleMerge
            java.lang.String r2 = "Support undo merge people"
            java.lang.String r3 = "Support undo after merging people"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.DebugSmartCropRectInfo
            java.lang.String r2 = "Debug SmartCropRect info in Visual Search"
            java.lang.String r3 = "Show SmartCropRect of media item in Visual search with long press"
            r11.addSwitchPreference(r6, r1, r2, r3)
            boolean r1 = com.samsung.android.gallery.support.utils.SuperHdrConfig.SUPPORT
            if (r1 == 0) goto L_0x0052
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.ThumbnailPreviewHdr
            java.lang.String r2 = "Surface preview on list"
            java.lang.String r3 = "Surface Preview video thumbnails on list for HDR"
            r11.addSwitchPreference(r6, r1, r2, r3)
        L_0x0052:
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.InsensitiveFastScroll
            java.lang.String r2 = "Insensitive fast scroll"
            java.lang.String r3 = "Try to maintain the position at the point of the release of your finger."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.AdaptiveFastScroll
            java.lang.String r2 = "Adaptive fast scroll"
            java.lang.String r3 = "Hold fast scroll and drag it to the opposite side of fast scroll, user can adjust scroll speed."
            r11.addSwitchPreference(r6, r1, r2, r3)
            java.lang.String r1 = "labs_year_view"
            androidx.preference.Preference r1 = r12.findPreference(r1)
            androidx.preference.PreferenceGroup r1 = (androidx.preference.PreferenceGroup) r1
            com.samsung.android.gallery.support.utils.PreferenceCache r2 = com.samsung.android.gallery.support.utils.PreferenceCache.YearTimeSlot
            java.lang.String r2 = r2.key
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "1 representative image every "
            r3.<init>(r4)
            int r4 = com.samsung.android.gallery.settings.ui.LabsDeveloperFragment.YearTimeSlot.getValue()
            java.lang.String r5 = " minutes"
            java.lang.String r4 = c0.C0086a.l(r3, r4, r5)
            Fa.A r5 = new Fa.A
            r3 = 0
            r5.<init>(r11, r3)
            java.lang.String r3 = "Time slot in year view"
            r0 = r11
            r0.addGenericPreference((androidx.preference.PreferenceGroup) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (androidx.preference.Preference.OnPreferenceClickListener) r5)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.RecoverLastStack
            java.lang.String r2 = "Recover last fragment"
            java.lang.String r3 = "Recover last fragment stack from process kill or activity recreate."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.DateTimeLocationRestore
            java.lang.String r2 = "Restore Time/Location"
            java.lang.String r3 = "Support dateTime and location restore for modified file"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.EnhancedVideoThumbnail
            Fa.B r5 = new Fa.B
            r1 = 1
            r5.<init>(r11, r1)
            java.lang.String r3 = "Enhanced video thumbnail"
            java.lang.String r4 = "remove black video thumbnails"
            r1 = r6
            r0.addSwitchPreference(r1, r2, r3, r4, r5)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.DebugFaceRect
            java.lang.String r2 = "Debug face rectangle"
            java.lang.String r3 = "Draw yellow rectangle on face"
            r11.addSwitchPreference(r6, r1, r2, r3)
            java.lang.String r1 = "labs_photo_strip"
            androidx.preference.Preference r1 = r12.findPreference(r1)
            androidx.preference.PreferenceGroup r1 = (androidx.preference.PreferenceGroup) r1
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.MediumCacheEnhance
            java.lang.String r3 = "[PS] Enhanced Medium Cache"
            java.lang.String r4 = "Make medium thumbnail with 640 for long side"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.PhotoStripHighQualityPreview
            java.lang.String r3 = "[PS] High quality preview"
            java.lang.String r4 = "use large size thumbnail for better quality"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r2 = com.samsung.android.gallery.support.utils.PreferenceFeatures.PhotoStrip41
            java.lang.String r3 = "[PS] for One UI 41"
            java.lang.String r4 = "photoStrip DA"
            r11.addSwitchPreference(r1, r2, r3, r4)
            java.lang.String r1 = "labs_viewer_next"
            androidx.preference.Preference r1 = r12.findPreference(r1)
            androidx.preference.PreferenceGroup r1 = (androidx.preference.PreferenceGroup) r1
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.FilmSmoothScroll
            java.lang.String r3 = "Film Smooth scroll"
            java.lang.String r4 = "Support Film Smooth scroll"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.DualPhotoPreview
            java.lang.String r3 = "Dual photo preview only"
            java.lang.String r4 = "File is not saved by changing close-up/wide of Live focus photo"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.CloudVideoStreamingPreview
            java.lang.String r3 = "Cloud video preview"
            java.lang.String r4 = "Preview cloud video and video in shared album"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.RegionDecodingInfo
            java.lang.String r3 = "Region decoding info"
            java.lang.String r4 = "Show region decoding info on canvas"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.MoreInfoDebug
            java.lang.String r3 = "Debug info in more-info"
            r11.addSwitchPreference(r1, r2, r3, r7)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.ImageFilterAlways
            java.lang.String r3 = "Image filter always"
            java.lang.String r4 = "Show suggested effects always"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.DetailsVI
            java.lang.String r3 = "Slide-up VI in viewer details"
            java.lang.String r4 = "Enable VI and change view recycle logic"
            r11.addSwitchPreference(r1, r2, r3, r4)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.Viewer2DebugTxt
            java.lang.String r3 = "Viewer2 debug text"
            r11.addSwitchPreference(r1, r2, r3, r7)
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.PreviewVideoOnPageChanged
            java.lang.String r3 = "Play video while swipe"
            r11.addSwitchPreference(r1, r2, r3, r7)
            java.lang.String r2 = "Always un-mute"
            java.lang.String r3 = "maintain mute value before app destroy"
            java.lang.String r4 = "Always mute (default)"
            java.lang.String[] r2 = new java.lang.String[]{r4, r2, r3}
            com.samsung.android.gallery.support.utils.PocFeatures r3 = com.samsung.android.gallery.support.utils.PocFeatures.SetAudioUnMuteUntilAppDestroy
            boolean r3 = r3.isEnabled()
            if (r3 == 0) goto L_0x0143
            r3 = 2
            goto L_0x014e
        L_0x0143:
            com.samsung.android.gallery.support.utils.PocFeatures r3 = com.samsung.android.gallery.support.utils.PocFeatures.SetAudioUnMuteAlways
            boolean r3 = r3.isEnabled()
            if (r3 == 0) goto L_0x014d
            r3 = 1
            goto L_0x014e
        L_0x014d:
            r3 = 0
        L_0x014e:
            r4 = r2[r3]
            A4.Y r5 = new A4.Y
            r8 = 2
            r5.<init>((int) r3, (java.io.Serializable) r2, (int) r8)
            java.lang.String r2 = "labs_video_viewer_audio_un_mute"
            java.lang.String r3 = "Set video viewer mute concept"
            r0 = r11
            r0.addGenericPreference((androidx.preference.PreferenceGroup) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (androidx.preference.Preference.OnPreferenceClickListener) r5)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.SkipAliveZoomOutput
            java.lang.String r2 = "Skip alive zoom output"
            java.lang.String r3 = "Skip alive zoom output, directly use region decoder output"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.SavePppTempImage
            java.lang.String r2 = "Save PPP temp image"
            java.lang.String r3 = "Save temp image before Camera post processing."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.CleanOutBurstSimilar
            java.lang.String r2 = "Add cleanout burst/similar pictures tab to suggestions"
            java.lang.String r3 = "By removing similar photos, you can increase available storage."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.RemoveBackgroundEffectInfo
            java.lang.String r2 = "Add Remove background effect info menu"
            java.lang.String r3 = "remove background effect info for reduce file size.\nYou can use list & viewer."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.CustomPeopleRelationshipEditAndRemove
            java.lang.String r2 = "Search People custom relationship edit and remove"
            java.lang.String r3 = "Support custom relationship name edit and remove function for testing"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r8 = com.samsung.android.gallery.support.utils.PocFeatures.QuickSearch
            java.lang.String r9 = "Quick search"
            java.lang.String r10 = "Support quick search on Pictures tab"
            r11.addSwitchPreference(r6, r8, r9, r10)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.AlbumCoverSync
            java.lang.String r2 = "Album cover sync"
            java.lang.String r3 = "Support album cover cloud sync"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.StoryOriginScaleWhenPaused
            java.lang.String r2 = "Original content scale on story"
            java.lang.String r3 = "Show original content scale on story when paused"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.StorySummaryCollage
            java.lang.String r2 = "Story summary with Collage"
            java.lang.String r3 = "Show Collage above related stories"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.StoryContentsReorder
            java.lang.String r2 = "Story contents reorder"
            java.lang.String r3 = "Support reorder in Story highlight list"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.StoryLastPage
            java.lang.String r2 = "Story last page"
            java.lang.String r3 = "show collage and related stories on page"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.StoryIrregularCollage
            java.lang.String r2 = "Story irregular collage"
            java.lang.String r3 = "show irregular collage on last page"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.StoriesIrregularCover
            java.lang.String r2 = "Stories irregular cover"
            java.lang.String r3 = "mask image with irregular shape"
            r11.addSwitchPreference(r6, r1, r2, r3)
            java.lang.String r1 = "labs_remote_gallery"
            androidx.preference.Preference r1 = r12.findPreference(r1)
            androidx.preference.PreferenceGroup r1 = (androidx.preference.PreferenceGroup) r1
            com.samsung.android.gallery.support.utils.PocFeatures r2 = com.samsung.android.gallery.support.utils.PocFeatures.WifiGallery
            java.lang.String r3 = "Support Remote Gallery"
            java.lang.String r4 = "Access any album of the remote device in the same WiFi domain"
            r11.addSwitchPreference(r1, r2, r3, r4)
            Fa.A r5 = new Fa.A
            r2 = 1
            r5.<init>(r11, r2)
            java.lang.String r2 = "labs_remote_gallery_server"
            java.lang.String r3 = "Start Remote Gallery Server"
            java.lang.String r4 = "Share my pictures with other galleries in same wifi network"
            r0.addGenericPreference((androidx.preference.PreferenceGroup) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (androidx.preference.Preference.OnPreferenceClickListener) r5)
            Fa.A r5 = new Fa.A
            r2 = 2
            r5.<init>(r11, r2)
            java.lang.String r2 = "labs_remote_gallery_client"
            java.lang.String r3 = "Connect to Remote Gallery"
            java.lang.String r4 = "Connect Remote gallery server to preview and download pictures."
            r0.addGenericPreference((androidx.preference.PreferenceGroup) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (androidx.preference.Preference.OnPreferenceClickListener) r5)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.ExpandedViewNavWidget
            java.lang.String r2 = "Show widgets for selection mode expanded viewer"
            java.lang.String r3 = "Show filmstrip and navigation button"
            r11.addSwitchPreference(r6, r1, r2, r3)
            r11.addSwitchPreference(r6, r8, r9, r10)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.AddressFromDatabase
            java.lang.String r2 = "Use address from database"
            r11.addSwitchPreference(r6, r1, r2, r7)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.AlbumAutoGroup
            java.lang.String r2 = "Enable album auto grouping"
            java.lang.String r3 = "Group albums by directory tree structure.\nAlbums->View All->Menu->Auto grouping\n"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.Features r1 = com.samsung.android.gallery.support.utils.Features.SUPP0RT_PASTE_CLIPBOARD_IMAGE
            boolean r1 = com.samsung.android.gallery.support.utils.Features.isEnabled(r1)
            if (r1 == 0) goto L_0x0231
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.PasteClipboardViewer
            java.lang.String r2 = "Paste clipboard in image viewer"
            java.lang.String r3 = "Enable paste clipboard option of image viewer"
            r11.addSwitchPreference(r6, r1, r2, r3)
        L_0x0231:
            com.samsung.android.gallery.support.utils.Features r1 = com.samsung.android.gallery.support.utils.Features.SUPPORT_CLIPPED_IMAGE_EDIT
            boolean r1 = com.samsung.android.gallery.support.utils.Features.isEnabled(r1)
            if (r1 == 0) goto L_0x0242
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.ClippedImageEdit
            java.lang.String r2 = "Clipped image edit"
            java.lang.String r3 = "Enable edit option of clipped image popup menu"
            r11.addSwitchPreference(r6, r1, r2, r3)
        L_0x0242:
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.VisualSearch61
            java.lang.String r2 = "Visual search 6.1"
            java.lang.String r3 = "New visual search look"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.SearchCluster
            java.lang.String r2 = "Support Search Cluster"
            java.lang.String r3 = "Show search result using cluster GUI but it depends on U OS SCS and CMH"
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PreferenceFeatures r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.InAppAssistLook
            java.lang.String r2 = "In app assist look"
            java.lang.String r3 = "Enables 'In app assist look' for the search bar including blur, light, gradient effect for the views."
            r11.addSwitchPreference(r6, r1, r2, r3)
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.MapViewBlur
            java.lang.String r2 = "Enable blur map view under bottom sheet"
            r11.addSwitchPreference(r6, r1, r2, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.settings.ui.LabsDeveloperFragment.initPreferenceDevOptions(androidx.preference.PreferenceScreen):void");
    }

    private void initPreferenceInternal(PreferenceScreen preferenceScreen) {
        PocFeatures.isEnabled(PocFeatures.GalleryLabsDev);
    }

    private void initPreferenceManager(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_manager");
        addGenericPreference(preferenceGroup, "labs_cat_manager", "Manager for preference & cache", (String) null, (Preference.OnPreferenceClickListener) new A(this, 3));
        addGenericPreference(preferenceGroup, "key_user_trial", getString(R$string.labs_user_trial), (String) null, (Preference.OnPreferenceClickListener) new A(this, 4));
        addGenericPreference(preferenceGroup, "key_config_poc_features", "PocFeatures", (String) null, (Preference.OnPreferenceClickListener) new A(this, 5));
        addGenericPreference(preferenceGroup, "key_config_preference_features", "PreferenceFeatures", (String) null, (Preference.OnPreferenceClickListener) new A(this, 6));
    }

    private void initPreferenceOneUi3x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_3x");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryOneUi31, getString(R$string.labs_title_oneui30_memories), getString(R$string.labs_summary_oneui30_memories));
        addSwitchPreference(preferenceGroup, PocFeatures.UndoDelete, "\"Undo delete\" in viewer", "Support \"undo delete\" function when deleting image or video in viewer");
    }

    private void initPreferenceOneUi4x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_4x");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.MotionPhotoPlayer, "MotionPhoto player", "enable video player for motion photo");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchPicker, "Search picker", "enable search in picker mode");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.PermanentAlbumCover, "Permanent album cover", "can select an album cover as an item in another album");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.GalleryMotionPhotoPlayer, "Gallery motion photo player", "Support embedded motion photo viewer");
    }

    private void initPreferenceOneUi50(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_5x");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryOneUi50, "Story UI 5.0 (slideshow)", "Story slideshow for OneUI 5.0");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryHighlightSave, "Story recording solution", "Embedded story recording solution instead of highlight-reels");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.FaceCluster, "Face cluster", "Support face cluster merge recommend");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchMultipleKeyword, "Search Multiple Keyword filter", "Enable selecting multiple keyword filter in search results");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchResultScreenV2, "Search Result Screen V2", "Show Story on result screen");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchHidePeople, "Search Hide People", "Hide unwanted people");
        addSwitchPreference(preferenceGroup, PocFeatures.DoubleTapSeek, "DoubleTapSeek", "Support DoubleTap Seek");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SharedAlbumPinch, "Shared Pinch Layout", "Support a shared pinch layout");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.MxAlbums, "Albums v2", "New album layout including physical/logical albums and shared albums");
        addSwitchPreference(preferenceGroup, PocFeatures.ShowNameMergedAlbumIcon, "Show an icon on name-merged albums", (String) null);
        addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchPeopleFaceScore, "Search people face score", "Support represent people thumbnail by face score");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryHighlightSmartCrop, "Enable new smart crop for story highlight", "Support for expanded slideshows with smart crop");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoriesFilter, "Support stories filter", "Support for filter effects");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryDefaultTheme, "Enable story default theme", "Enable story default theme");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteDarkCache$19(ProgressDialogCompat progressDialogCompat) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = DbCompat.query(DbKey.VIRTUAL_ALBUM_VIDEO);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int count = query.getCount();
                    Log.d(this.TAG, "remove dark video thumbnail", Integer.valueOf(count));
                    int i2 = 0;
                    do {
                        MediaItem load = MediaItemLoader.load(query);
                        if (BitmapUtils.isDark(ThumbnailLoader.getInstance().getBitmapFromDiskCache(load, ThumbKind.MEDIUM_KIND))) {
                            ThumbnailLoader.getInstance().removeCache(load);
                            i2++;
                        }
                    } while (query.moveToNext());
                    String str = this.TAG;
                    Log.d(str, "remove dark video thumbnail" + Logger.vt(Integer.valueOf(count), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        Objects.requireNonNull(progressDialogCompat);
        ThreadUtil.postOnUiThread(new g(14, progressDialogCompat));
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initPreferenceDevOptions$10(int i2, String[] strArr, Preference preference) {
        AtomicInteger atomicInteger = new AtomicInteger(i2);
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "Set video viewer mute concept").setSingleChoiceItems((CharSequence[]) strArr, i2, (DialogInterface.OnClickListener) new C0568w(atomicInteger, 1)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C(atomicInteger, preference, strArr)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initPreferenceDevOptions$12(RemoteServer remoteServer, DialogInterface dialogInterface, int i2) {
        remoteServer.stop();
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceDevOptions$14(final Preference preference) {
        final RemoteServer instance = RemoteServer.getInstance();
        if (instance.isRun()) {
            instance.stop();
            Utils.showToast(preference.getContext(), "Server stop");
            preference.setTitle((CharSequence) "Start Remote Gallery Server");
            return true;
        }
        String wifiAddress = Utils.getWifiAddress();
        if (wifiAddress != null) {
            instance.setThumbnailLoader(new j(15));
            String start = instance.start();
            ImageView imageView = new ImageView(preference.getContext());
            imageView.setImageBitmap(RemoteGalleryUtil.createQrCode(wifiAddress, start));
            AlertDialog.Builder title = new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "Connection Info");
            title.setMessage((CharSequence) "server created.\n\nip address : " + wifiAddress + "\npass code : " + start).setView((View) imageView).setNegativeButton((CharSequence) "Stop", (DialogInterface.OnClickListener) new C0558l(1, instance)).setPositiveButton((CharSequence) "Hide", (DialogInterface.OnClickListener) new a(3)).setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    if (instance.isRun()) {
                        preference.setTitle((CharSequence) "Stop Remote Gallery Server");
                    } else {
                        preference.setTitle((CharSequence) "Start Remote Gallery Server");
                    }
                }
            }).create().show();
            return true;
        }
        Utils.showToast(preference.getContext(), "wifi not connected");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferenceDevOptions$16(EditText editText, EditText editText2, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        PocFeatures.WifiGalleryClient.setEnabled(true);
        String obj = editText.getText().toString();
        RemoteClient.setServerInfo(obj, editText2.getText().toString());
        ThumbnailLoader.getInstance().setRemoteThumbnailLoader(new j(16));
        RemoteClientUi.startActivity(getActivity(), obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceDevOptions$18(Preference preference) {
        String wifiAddress = Utils.getWifiAddress();
        if (wifiAddress != null) {
            InputDialogBuilder inputDialogBuilder = new InputDialogBuilder(preference.getContext(), "Input ConnectionInfo");
            inputDialogBuilder.setPositiveButton("Connect", new Da.g(this, inputDialogBuilder.addInputField("Ip", wifiAddress.substring(0, wifiAddress.lastIndexOf(".") + 1)), inputDialogBuilder.addInputField("PassCode", (String) null), 3));
            inputDialogBuilder.setNegativeButton("Cancel", new a(2));
            inputDialogBuilder.buildAndShow();
            return true;
        }
        Utils.showToast(preference.getContext(), "wifi not connected");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferenceDevOptions$5(Integer num) {
        Optional.ofNullable(findPreference(PreferenceCache.YearTimeSlot.key)).ifPresent(new E9.a(9, num));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceDevOptions$6(Preference preference) {
        new YearTimeSlot(getContext()).setCallback(new B(this, 0)).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferenceDevOptions$7(Boolean bool) {
        if (bool.booleanValue()) {
            deleteDarkCache();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initPreferenceDevOptions$9(AtomicInteger atomicInteger, Preference preference, String[] strArr, DialogInterface dialogInterface, int i2) {
        boolean z;
        int i7 = atomicInteger.get();
        PocFeatures pocFeatures = PocFeatures.SetAudioUnMuteAlways;
        boolean z3 = false;
        if (i7 == 1) {
            z = true;
        } else {
            z = false;
        }
        pocFeatures.setEnabled(z);
        PocFeatures pocFeatures2 = PocFeatures.SetAudioUnMuteUntilAppDestroy;
        if (i7 == 2) {
            z3 = true;
        }
        pocFeatures2.setEnabled(z3);
        preference.setSummary((CharSequence) strArr[atomicInteger.get()]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceManager$0(Preference preference) {
        return commitFragment(new LabsDevManageFragment());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceManager$1(Preference preference) {
        return commitFragment(new LabsUserTrialFragment());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceManager$2(Preference preference) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "PocFeatures");
        commitFragment(new LabsConfigFragment(), bundle);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceManager$3(Preference preference) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "PreferenceFeatures");
        commitFragment(new LabsConfigFragment(), bundle);
        return true;
    }

    public Preference addSwitchPreference(PreferenceGroup preferenceGroup, BooleanFeatures booleanFeatures, String str, String str2) {
        StringBuilder sb2 = new StringBuilder("[");
        sb2.append(booleanFeatures.getKey());
        sb2.append("] ");
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        return super.addSwitchPreference(preferenceGroup, booleanFeatures, str, sb2.toString(), (Consumer<Boolean>) null);
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs_developer;
    }

    public int getTitleId() {
        return R$string.gallery_labs_developer_title;
    }

    public void initPreferences() {
        super.initPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        initSwitchPreference("labs_developer_master_switch", PocFeatures.GalleryLabsDev);
        initPreferenceManager(preferenceScreen);
        initPreferenceAiFeatures(preferenceScreen);
        initPreferenceOneUi3x(preferenceScreen);
        initPreferenceOneUi4x(preferenceScreen);
        initPreferenceOneUi50(preferenceScreen);
        initPreferenceDevOptions(preferenceScreen);
        initPreferenceDebugLog(preferenceScreen);
        initPreferenceInternal(preferenceScreen);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }
}
