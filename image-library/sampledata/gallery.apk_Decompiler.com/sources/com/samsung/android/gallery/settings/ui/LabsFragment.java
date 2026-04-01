package com.samsung.android.gallery.settings.ui;

import A4.C0387w;
import A4.H;
import Ad.j;
import C3.l;
import D7.g;
import Da.f;
import Fa.C;
import Fa.C0558l;
import Fa.C0568w;
import Fa.C0571z;
import Fa.D;
import Fa.E;
import Fa.F;
import Fa.G;
import Fa.I;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.debugger.BugReporter;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.secured.PrivateFiles;
import com.samsung.android.gallery.module.trash.helper.TrashRollbackTask;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import com.samsung.android.gallery.settings.widget.SummaryPreference;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.scsp.common.ContentType;
import h4.C0464a;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsFragment extends LabsBaseFragment {
    static final boolean SUPPORT_PRIVATE_ALBUM_LEGACY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugDumpWorker extends LabsBaseFragment.WorkerTask {
        boolean includeDatabase;
        boolean includePrivateAlbum = false;
        private String mZipFile;
        public final SubscriberListener onMessage = new SubscriberListener() {
            public void onNotify(Object obj, Bundle bundle) {
                DebugDumpWorker debugDumpWorker = DebugDumpWorker.this;
                StringBuilder sb2 = new StringBuilder("Collecting log");
                DebugDumpWorker debugDumpWorker2 = DebugDumpWorker.this;
                String str = "";
                if (debugDumpWorker2.includeDatabase) {
                    if (debugDumpWorker2.includePrivateAlbum) {
                        str = " with private album";
                    }
                    str = " and database".concat(str);
                }
                sb2.append(str);
                sb2.append("...\n");
                sb2.append(obj);
                debugDumpWorker.updateProgress(sb2.toString());
            }
        };

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onPostExecute$2(Context context, View view) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", FileProviderUtil.getUri(context, this.mZipFile));
            intent.setType(ContentType.OCTET_STREAM);
            context.startActivity(Intent.createChooser(intent, "Share dump file"));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ TextView lambda$onPreExecute$0(ProgressDialogCompat progressDialogCompat) {
            return this.mProgressDialog.getMessageView();
        }

        public void onExecute(Object... objArr) {
            this.mZipFile = new BugReporter(objArr[0]).archiveLogs(objArr[1].booleanValue(), this.includePrivateAlbum);
        }

        public void onPostExecute(Object... objArr) {
            super.lambda$execute$1(objArr);
            Context context = objArr[0];
            Blackboard.getApplicationInstance().unsubscribe("debug://DumpDisplayMessage", this.onMessage);
            Blackboard.getApplicationInstance().erase("debug://DumpDisplayMessage");
            if (context != null && FileUtils.exists(this.mZipFile)) {
                r j2 = r.j(((Activity) context).getWindow().getDecorView(), -1, 0, false, C0212a.m("Dump completed\n\n", this.mZipFile.replace(FileUtils.EXTERNAL_STORAGE_DIR, "/Internal storage"), "\n\nDo you want to share dump file?"));
                j2.k("Share", new P(3, this, context));
                j2.l();
            }
        }

        public void onPreExecute(Object... objArr) {
            super.lambda$execute$0(objArr);
            Optional.ofNullable(this.mProgressDialog).map(new B(this)).ifPresent(new C0657l(1));
            this.includeDatabase = objArr[1].booleanValue();
            if (objArr.length > 2) {
                this.includePrivateAlbum = objArr[2].booleanValue();
            }
            StringBuilder sb2 = new StringBuilder("Collecting log");
            String str = "";
            if (this.includeDatabase) {
                if (this.includePrivateAlbum) {
                    str = " with private album";
                }
                str = " and database".concat(str);
            }
            updateProgress(C0212a.p(sb2, str, "..."));
            Blackboard.getApplicationInstance().subscribe("debug://DumpDisplayMessage", this.onMessage);
            Log.printHistoricalLogs();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PackageHolder {
        static final ArrayList<PackageData> list = new ArrayList<PackageData>() {
            {
                if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                    add(new PackageData("com.samsung.android.gallery.plugins", "Gallery Plugin"));
                }
                add(new PackageData("com.samsung.android.widget.pictureframe", "Gallery Widget"));
                add(new PackageData(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, (String) null));
                add(new PackageData("com.sec.android.app.vepreload", (String) null));
                add(new PackageData("com.samsung.app.newtrim", (String) null));
                add(new PackageData("com.sec.android.app.camera", (String) null));
                SdkConfig.GED ged = SdkConfig.GED.U;
                if (SdkConfig.atLeast(ged)) {
                    add(new PackageData("com.samsung.android.photoremasterservice", (String) null));
                    add(new PackageData("com.samsung.mediasearch", (String) null));
                }
                add(new PackageData("com.samsung.ipservice", (String) null));
                add(new PackageData("com.samsung.faceservice", (String) null));
                add(new PackageData("com.samsung.petservice", (String) null));
                add(new PackageData("com.samsung.storyservice", (String) null));
                add(new PackageData(BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES, (String) null));
                add(new PackageData(Constants.SMART_SUGGESTIONS_PACKAGE_NAME, (String) null));
                add(new PackageData("com.samsung.android.scloud", (String) null, new C0648c(1)));
                add(new PackageData("com.samsung.android.smartmirroring", (String) null));
                add(new PackageData(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, (String) null));
                add(new PackageData("com.samsung.android.providers.media", (String) null));
                if (SdkConfig.atLeast(ged)) {
                    add(new PackageData("com.samsung.android.providers.trash", (String) null));
                }
                add(new PackageData("com.samsung.cmh", (String) null));
                add(new PackageData("com.google.android.providers.media.module", (String) null));
            }
        };

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class PackageData {
            String alias;
            Function<Bundle, String> function;
            String packageName;

            public PackageData(String str, String str2) {
                this(str, str2, (Function<Bundle, String>) null);
            }

            public PackageData(String str, String str2, Function<Bundle, String> function2) {
                this.packageName = str;
                this.alias = str2;
                this.function = function2;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivateRecoveryWorker extends LabsBaseFragment.WorkerTask {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onExecute$0(Float f) {
            updateProgress(C0086a.l(new StringBuilder(), (int) (f.floatValue() * 100.0f), "%"));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onExecute$1(Float f) {
            ThreadUtil.postOnUiThread(new C0653h(1, this, f));
        }

        public void onExecute(Object... objArr) {
            Context context = objArr[0];
            PrivateFiles.getInstance().restore(objArr[1], new r(1, this));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoPlayerMode {
        static final ArrayList<String> LIST;
        static Integer MODE;

        static {
            ArrayList<String> arrayList = new ArrayList<>();
            LIST = arrayList;
            arrayList.add("Default");
            arrayList.add("None (Play video button)");
            arrayList.add("Legacy video seekbar");
        }

        public static String get() {
            return LIST.get(value());
        }

        public static void save(int i2) {
            boolean z;
            MODE = Integer.valueOf(i2);
            PreferenceFeatures.VideoPlayerFeature.setVideoPlayerMode(i2);
            PreferenceFeatures preferenceFeatures = PreferenceFeatures.PhotoStrip41;
            if (i2 != 3) {
                z = true;
            } else {
                z = false;
            }
            PreferenceFeatures.setEnabled(preferenceFeatures, z);
        }

        public static String[] toArray() {
            return (String[]) LIST.toArray(new String[0]);
        }

        public static int value() {
            if (MODE == null) {
                Integer valueOf = Integer.valueOf(GalleryPreference.getInstance().loadInt("VideoPlayerMode", 0));
                MODE = valueOf;
                if (valueOf.intValue() >= LIST.size()) {
                    save(0);
                }
            }
            return MODE.intValue();
        }
    }

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.B) || !Features.isEnabled(Features.PRIVATE_ALBUM)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_PRIVATE_ALBUM_LEGACY = z;
    }

    private void initPreferenceForDeveloper(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_developer");
        addGenericPreference(preferenceGroup, "labs_heapdump", getString(R$string.labs_title_capture_system_heap_dump), getString(R$string.labs_summary_capture_system_heap_dump), (Preference.OnPreferenceClickListener) new E(this, 1));
        String makeDeveloperOptionsSummary = makeDeveloperOptionsSummary();
        SummaryPreference summaryPreference = (SummaryPreference) addSummaryPreference(preferenceGroup, "labs_more_options_for_developer", getString(R$string.labs_title_developer), makeDeveloperOptionsSummary, new E(this, 2));
        if (SdkConfig.atLeast(SdkConfig.SEM.B) && summaryPreference != null) {
            if (PackageMonitorCompat.getInstance().isPackageInstalled("com.salab.issuetracker")) {
                WebView webView = new WebView(summaryPreference.getContext());
                summaryPreference.appendView(webView);
                SimpleThreadPool.getInstance().execute(new g(15, webView));
                return;
            }
            SimpleThreadPool.getInstance().execute(new f(21, summaryPreference, makeDeveloperOptionsSummary));
        }
    }

    private void initPreferenceForImageViewer(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_image_viewer");
        if (preferenceGroup != null) {
            addSwitchPreference(preferenceGroup, PocFeatures.AospCodecDecoding, getString(R$string.labs_title_android_image_decoder), "Use Android embedded image decoder for image decoding");
            if (Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION)) {
                addSwitchPreference(preferenceGroup, PreferenceFeatures.HeifAutoConvert, getString(R$string.heif_auto_conversion_title), (String) null);
            }
            addSwitchPreference(preferenceGroup, PocFeatures.MoreInfoExif, getString(R$string.labs_title_show_exif), "Show additional embedded information from jpeg", (Consumer<Boolean>) null);
        }
    }

    private void initPreferenceForOneUi2x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_2x");
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryOneUi31)) {
            addSwitchPreference(preferenceGroup, PreferenceFeatures.StoryOneUi21, getString(R$string.labs_title_stories_oneui_21), getString(R$string.labs_summary_stories_oneui_21));
        }
        if (BasePreferenceFragment.FEATURES_FOR_LEGACY) {
            addSwitchPreference(preferenceGroup, PreferenceFeatures.ShareAlbums, getString(R$string.labs_title_share_albums), getString(R$string.labs_summary_share_albums));
        }
        addSwitchPreference(preferenceGroup, PreferenceFeatures.ThumbnailPreview, getString(R$string.labs_title_thumbnail_preview), getString(R$string.labs_summary_thumbnail_preview));
        addSwitchPreference(preferenceGroup, PocFeatures.ShowTrashStorage, getString(R$string.labs_title_show_trash_storage), getString(R$string.labs_summary_show_trash_storage));
        addSwitchPreference(preferenceGroup, PreferenceFeatures.AlbumTimeline, getString(R$string.labs_title_timeline_in_album), getString(R$string.labs_summary_timeline_in_album));
    }

    private void initPreferenceForOneUi30(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_30");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.PhotoStrip41, getString(R$string.labs_title_filmstrip), getString(R$string.labs_summary_filmstrip), new D(this, 1));
        addGenericPreference(preferenceGroup, "labs_video_on_filmstrip", getString(R$string.labs_title_video_player_mode_filmstrip), VideoPlayerMode.get(), (Preference.OnPreferenceClickListener) new E(this, 3));
        Optional.ofNullable(findPreference("labs_video_on_filmstrip")).ifPresent(new F(0));
        addSwitchPreference(preferenceGroup, PreferenceFeatures.DayMerge, getString(R$string.labs_title_day_merge_clustering), getString(R$string.labs_summary_day_merge_clustering));
    }

    private void initPreferenceForOneUi5x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_5x");
        Preference addSwitchPreference = addSwitchPreference(preferenceGroup, PocFeatures.LockedAlbum, getString(R$string.labs_title_album_entry_locks), "Album entry-locks with lock-screen credentials. Note that it shall not provide secure service or access control of multimedia contents.");
        if (addSwitchPreference != null) {
            addSwitchPreference.setOnPreferenceChangeListener(new H(9, (Object) this, (Object) addSwitchPreference));
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            addSwitchPreference(preferenceGroup, PocFeatures.ShowVirtualAlbums, getString(R$string.labs_title_show_virtual_albums), "Show \"Recent\" and \"Favorites\" albums on album tab");
        }
        addSwitchPreference(preferenceGroup, PocFeatures.SaveAsPdf, getString(R$string.labs_title_save_as_pdf), "Making a PDF file with images: \"Create > Save as PDF\" after selecting images");
        addSwitchPreference(preferenceGroup, PocFeatures.PrintMultiple, getString(R$string.labs_title_print_multiple_pictures), "Print multiple pictures on pictures tab");
    }

    private void initPreferenceForOneUi6x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_6x");
        addSwitchPreference(preferenceGroup, PocFeatures.OpenInOtherWindow, getString(R$string.open_in_other_window), "Open new viewer in other window from image/video viewer");
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            addSwitchPreference(preferenceGroup, PreferenceFeatures.MoreOptionsInViewerBottom, getString(R$string.labs_title_hamburger_menu_in_viewer_bottom), getString(R$string.labs_description_hamburger_menu_in_viewer_bottom));
        }
        if (SdkConfig.match(SdkConfig.SEM.U_MR1)) {
            addSwitchPreference(preferenceGroup, PreferenceFeatures.SearchTagInRecommendationView, getString(R$string.labs_title_tag_in_search_v2), "Tag in search first page is moved to recommendation in ONE UI 6.x");
        }
        addSwitchPreference(preferenceGroup, PocFeatures.SlideshowWithSelectedContents, getString(R$string.labs_title_slideshow_with_selected_items), (String) null);
        if (Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO)) {
            addSwitchPreference(preferenceGroup, PocFeatures.GotoStudioMenuEnabled, SeApiCompat.naturalizeText(getString(R$string.video_studio_title, getString(R$string.video_studio_app_name))), (String) null);
        }
    }

    private void initPreferenceForOneUi7x(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_7x");
        if (preferenceGroup != null) {
            PocFeatures pocFeatures = PocFeatures.PresentationEnabled;
            new LabsBaseFragment.BooleanChoiceDialogBuilder(pocFeatures).attach(addGenericPreference(preferenceGroup, pocFeatures.getKey(), getString(R$string.mirror_screen_mode), (String) null, (Preference.OnPreferenceClickListener) null), new String[]{"Mirroring mode", "Presentation mode (default)"});
            addSwitchPreference(preferenceGroup, PocFeatures.SupportAliveZoom, getString(R$string.support_alive_zoom), "Enhance image while using pinch zoom for small images");
            String language = Locale.getDefault().getLanguage();
            if ("en".equals(language) || "ko".equals(language)) {
                addSwitchPreference(preferenceGroup, PocFeatures.FullAddressDisplay, getString(R$string.full_address_in_details), (String) null);
            }
            removePreferenceIfEmpty(preferenceGroup);
        }
    }

    private void initPreferenceForOneUi8x(PreferenceScreen preferenceScreen) {
        LabsFragment labsFragment;
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_options_8x");
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            labsFragment = this;
            new LabsBaseFragment.MultiChoiceDialogBuilder(GalleryPreference.getInstance(), "CollectionOptional").attach(labsFragment.addGenericPreference(preferenceGroup, "CollectionOptional", getString(R$string.collections_optional_categories), (String) null, (Preference.OnPreferenceClickListener) null), new String[]{"Activity", "Shot types", "My tags"});
        } else {
            labsFragment = this;
        }
        if (SUPPORT_PRIVATE_ALBUM_LEGACY && SdkConfig.match(SdkConfig.SEM.B)) {
            labsFragment.addSwitchPreference(preferenceGroup, PocFeatures.PrivateAlbum, labsFragment.getString(R$string.private_album), "Support private storage to keep image and video in hidden area");
        }
        labsFragment.removePreferenceIfEmpty(preferenceGroup);
    }

    private void initPreferenceForUtilities(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_utilities");
        if (preferenceGroup != null) {
            addGenericPreference(preferenceGroup, "labs_album_bnr", getString(R$string.album_backup_and_restore), "Backup or restore album database", (Preference.OnPreferenceClickListener) new E(this, 4));
            addGenericPreference(preferenceGroup, "labs_file_browser", getString(R$string.file_browser), "You can browse files in \"/Android/media/com.sec.android.gallery3d/\", and preview zip-files", (Preference.OnPreferenceClickListener) new j(18));
            if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
                addGenericPreference(preferenceGroup, "labs_private_file_recovery", getString(R$string.restore_files_in_private_album), (String) null, (Preference.OnPreferenceClickListener) new E(this, 5));
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
                addGenericPreference(preferenceGroup, "labs_trash_recovery", getString(R$string.recover_missing_trashed_items), getString(R$string.recover_missing_trashed_items_description), (Preference.OnPreferenceClickListener) new j(19));
            }
            addGenericPreference(preferenceGroup, "labs_cleanup_settings", getString(R$string.clear_settings_in_labs), (String) null, (Preference.OnPreferenceClickListener) new E(this, 6));
            SwitchPreferenceCompat switchPreferenceCompat = new SwitchPreferenceCompat(preferenceGroup.getContext());
            switchPreferenceCompat.setTitle((CharSequence) getString(R$string.safe_mode));
            switchPreferenceCompat.setSummary((CharSequence) "Once enabled, decoding in the picture list is disabled to find and remove contents causing system crash");
            switchPreferenceCompat.setDefaultValue(Boolean.valueOf(SafeMode.isEnabled()));
            switchPreferenceCompat.setOnPreferenceChangeListener(new j(17));
            preferenceGroup.addPreference(switchPreferenceCompat);
        }
    }

    private void initPreferenceForVideoViewer(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference("labs_video_viewer");
        if (SdkConfig.lessThan(SdkConfig.GED.S)) {
            addSwitchPreference(preferenceGroup, PreferenceFeatures.MotionPhotoZoom, "Pinch zoom with motion photo video", "Support pinch zoom gestures on motion photo video viewer");
        }
        addSwitchPreference(preferenceGroup, PocFeatures.VideoAutoPlaybackNext, getString(R$string.labs_title_play_next_video), "Next video starts automatically right after video playback ends with the OSD turned off");
        addSwitchPreference(preferenceGroup, PreferenceFeatures.VideoThumbnailWithFirstFrame, getString(R$string.labs_title_video_thumbnail_policy_v2), "Use first frame as video thumbnail. If disabled, legacy policy, extracting a frame at 15 sec from video for representative thumbnail, is applied");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceForDeveloper$4(Preference preference) {
        if (!PocFeatures.isEnabled(PocFeatures.GalleryLabsDev)) {
            return true;
        }
        commitFragment(new LabsDeveloperFragment());
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initPreferenceForOneUi30$19(Boolean bool, Preference preference) {
        preference.setEnabled(bool.booleanValue());
        VideoPlayerMode.save(bool.booleanValue() ^ true ? 1 : 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferenceForOneUi30$20(Boolean bool) {
        Optional.ofNullable(findPreference("labs_video_on_filmstrip")).ifPresent(new G(0, bool));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceForOneUi5x$18(Preference preference, Preference preference2, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            showSwitchConfirmDialog(preference, PocFeatures.LockedAlbum, "Album entry-locks with lock-screen credentials", "\nOnce an album is locked, its contents are not shown on pictures and album tab\n\nNotice: It shall not provide secure service or access control of multimedia contents. All other applications or services, such as My Files, Google photos, cloud services, etc, can access the contents. The feature is working only in Gallery. Please use 'Secure folder' in order to protect or hide your contents in the secured space.\n\nUsage\nLock: select one album > menu > 'Lock album'\nUnlock: enter locked album > menu > 'Unlock album'\n");
            return false;
        }
        PocFeatures.LockedAlbum.setEnabled(false);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceForUtilities$10(Preference preference) {
        return commitFragment(new LabsAlbumBnRFragment());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initPreferenceForUtilities$11(Preference preference) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.filebrowser.FileBrowseActivity");
            preference.getContext().startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) "LabsFragment", "launchFileManager failed", (Throwable) e);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initPreferenceForUtilities$13(Object[] objArr) {
        Context context = objArr[0];
        new TrashRollbackTask(new I(context, 0)).execute(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initPreferenceForUtilities$14(Preference preference) {
        if (ServiceManager.getInstance().hasRunningServiceForTrash(preference.getContext())) {
            Utils.showToast(preference.getContext(), R$string.unknown_error_description);
            return true;
        }
        new LabsBaseFragment.WorkerTask(new F(1)).execute(preference.getContext());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferenceForUtilities$15(Preference preference) {
        onCleanupSettings(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferenceForUtilities$16(Preference preference) {
        showConfirmDialog(getString(R$string.clear_settings_in_labs), "Continue resetting labs settings with default value?", new f(23, this, preference));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferences$0(Boolean bool) {
        if (!bool.booleanValue()) {
            Context context = getContext();
            if (context == null) {
                context = AppResources.getAppContext();
            }
            onCleanupSettings(context);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreferences$1(Preference preference) {
        return commitFragment(new TroubleshootingFragment());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferences$2(Preference preference) {
        preference.setOnPreferenceClickListener(new E(this, 0));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onHeapDumpClicked$24(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$onHeapDumpClicked$25(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHeapDumpClicked$26() {
        MemoryUtils.dumpHprof();
        Utils.showToast(getContext(), "Heap dump done. Saved in /Download");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHeapDumpClicked$27(DialogInterface dialogInterface, int i2) {
        if (i2 == 2) {
            ThreadUtil.runOnBgThread(new Fa.H(this, 0));
        } else if (i2 == 3) {
            DebugDumpWorker debugDumpWorker = new DebugDumpWorker();
            Context context = getContext();
            Boolean bool = Boolean.TRUE;
            debugDumpWorker.execute(context, bool, bool);
        } else if (i2 == 1) {
            new DebugDumpWorker().execute(getContext(), Boolean.TRUE);
        } else {
            new DebugDumpWorker().execute(getContext(), Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrivateAlbumRestoreClicked$30(ArrayList arrayList) {
        new PrivateRecoveryWorker().execute(getContext(), arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrivateAlbumRestoreClicked$31() {
        boolean z;
        if (!SUPPORT_PRIVATE_ALBUM_LEGACY || !PrivateDatabase.getInstance().fixWrongOwner()) {
            z = false;
        } else {
            z = true;
        }
        ArrayList<File> listFiles = PrivateFiles.getInstance().listFiles();
        Log.d("LabsFragment", "onPrivateAlbumRestore#click", Boolean.valueOf(z), Integer.valueOf(listFiles.size()));
        if (listFiles.isEmpty()) {
            Utils.showToast(getContext(), "No files in Private album");
            return;
        }
        showConfirmDialog("Restore " + listFiles.size() + " files in Private album?", new f(24, this, listFiles));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrivateAlbumRestoreClicked$32(Integer num) {
        if (num.intValue() == 0) {
            SimpleThreadPool.getInstance().execute(new Fa.H(this, 1));
        } else if (num.intValue() == 2) {
            Log.e("LabsFragment", "auth not available");
            BiometricPromptCompat.setupScreenLock(getActivity());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoOnFilmstripClicked$23(AtomicInteger atomicInteger, String[] strArr, Preference preference, DialogInterface dialogInterface, int i2) {
        int i7 = atomicInteger.get();
        VideoPlayerMode.save(i7);
        Log.d("LabsFragment", "change video player mode on filmstrip", atomicInteger, strArr[i7]);
        preference.setSummary((CharSequence) strArr[i7]);
    }

    /* access modifiers changed from: private */
    /* renamed from: loadPackageInfo */
    public String lambda$makeDeveloperOptionsSummary$9(PackageMonitorCompat packageMonitorCompat, PackageHolder.PackageData packageData) {
        String str;
        Bundle applicationMetaData;
        PackageInfo packageInfo = packageMonitorCompat.getPackageInfo(packageData.packageName, 0);
        if (packageInfo == null) {
            return null;
        }
        if (packageData.function == null || (applicationMetaData = packageMonitorCompat.getApplicationMetaData(packageData.packageName)) == null) {
            str = "";
        } else {
            str = " " + packageData.function.apply(applicationMetaData);
        }
        StringBuilder sb2 = new StringBuilder();
        Object obj = packageData.alias;
        if (obj == null) {
            obj = packageMonitorCompat.getApplicationLabel(packageInfo);
        }
        sb2.append(obj);
        sb2.append(": ");
        return N2.j.f(sb2, packageInfo.versionName, "", str);
    }

    private String makeDeveloperOptionsSummary() {
        String str = PackageMonitorCompat.getVersionName() + ArcCommonLog.TAG_COMMA + TimeUtil.getIsoUtcDateTime(1767458900310L) + ", c963dd8 [8]";
        String str2 = "S/N: " + DeviceInfo.getDeviceSerial();
        PackageMonitorCompat instance = PackageMonitorCompat.getInstance();
        return str + "\n" + str2 + "\n" + ((String) PackageHolder.list.stream().map(new A(this, instance)).filter(new C0464a(13)).collect(Collectors.joining("\n")));
    }

    private void onCleanupSettings(Context context) {
        List<String> loadSwitchPreferenceKeySet = loadSwitchPreferenceKeySet(findPreference("labs_preference_screen"));
        VideoPlayerMode.MODE = null;
        loadSwitchPreferenceKeySet.add("VideoPlayerMode");
        loadSwitchPreferenceKeySet.add(PreferenceFeatures.PhotoStrip41.getKey());
        List list = (List) GalleryPreference.getInstance().getAllKeySet().stream().filter(new com.samsung.android.gallery.module.data.g(1, loadSwitchPreferenceKeySet)).collect(Collectors.toList());
        GalleryPreference.getInstance().removeState((Collection<String>) list);
        Arrays.stream(PreferenceFeatures.values()).forEach(new F(2));
        Arrays.stream(PocFeatures.values()).forEach(new F(3));
        Log.d("LabsFragment", "onCleanupSettings", Integer.valueOf(loadSwitchPreferenceKeySet.size()), Integer.valueOf(list.size()));
        Blackboard.getApplicationInstance().post("global://setting/labs/enabling", (Object) null);
        loadPreferences();
        ThreadUtil.postOnUiThreadDelayed(new l(context, 1), 300);
    }

    /* access modifiers changed from: private */
    public boolean onHeapDumpClicked(Preference preference) {
        new AlertDialog.Builder(preference.getContext()).setTitle(R$string.labs_title_capture_system_heap_dump).setItems((CharSequence[]) Arrays.stream(new String[]{"Log only", "Log and database", "HeapDump (hprof)", ""}).filter(new C0571z(2)).toArray(new C0387w(6)), new C0558l(2, this)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).create().show();
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onPrivateAlbumRestoreClicked(Preference preference) {
        new BiometricPromptCompat().setTitle(R$string.unlock_private_album).setCallback(new D(this, 3)).authenticate(getActivity());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onVideoOnFilmstripClicked(Preference preference) {
        String[] strArr = (String[]) Arrays.copyOf(VideoPlayerMode.toArray(), 3);
        AtomicInteger atomicInteger = new AtomicInteger(VideoPlayerMode.value());
        if (atomicInteger.get() > 2) {
            atomicInteger.set(0);
        }
        new AlertDialog.Builder(preference.getContext()).setTitle(R$string.labs_title_video_player_mode_filmstrip).setSingleChoiceItems((CharSequence[]) strArr, atomicInteger.get(), (DialogInterface.OnClickListener) new C0568w(atomicInteger, 2)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C(atomicInteger, strArr, preference)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs;
    }

    public int getTitleId() {
        return R$string.gallery_labs_title;
    }

    public void initPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        initSwitchPreference("labs_main_switch", PocFeatures.GalleryLabs, new D(this, 0));
        if (SdkConfig.lessThan(SdkConfig.GED.Q)) {
            Optional.ofNullable(findPreference("labs_troubleshooting")).ifPresent(new D(this, 2));
        } else {
            removePreference("labs_customer_service");
        }
        initPreferenceForOneUi8x(preferenceScreen);
        initPreferenceForOneUi7x(preferenceScreen);
        initPreferenceForOneUi6x(preferenceScreen);
        initPreferenceForOneUi5x(preferenceScreen);
        initPreferenceForOneUi30(preferenceScreen);
        initPreferenceForOneUi2x(preferenceScreen);
        initPreferenceForVideoViewer(preferenceScreen);
        initPreferenceForImageViewer(preferenceScreen);
        initPreferenceForUtilities(preferenceScreen);
        initPreferenceForDeveloper(preferenceScreen);
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = preferenceScreen.getPreference(i2);
            if ((preference instanceof PreferenceCategory) && ((PreferenceCategory) preference).getPreferenceCount() == 0) {
                preferenceScreen.removePreference(preference);
            }
        }
    }

    public List<String> loadSwitchPreferenceKeySet(Preference preference) {
        if (!(preference instanceof PreferenceGroup)) {
            return List.of(preference.getKey());
        }
        PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < preferenceGroup.getPreferenceCount(); i2++) {
            Preference preference2 = preferenceGroup.getPreference(i2);
            if (preference2 instanceof PreferenceCategory) {
                arrayList.addAll(loadSwitchPreferenceKeySet(preference2));
            } else if (preference2 instanceof SwitchPreferenceCompat) {
                arrayList.add(preference2.getKey());
            }
        }
        return arrayList;
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }
}
