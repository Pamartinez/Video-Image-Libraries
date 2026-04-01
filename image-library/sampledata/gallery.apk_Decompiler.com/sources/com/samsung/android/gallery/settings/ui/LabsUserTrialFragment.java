package com.samsung.android.gallery.settings.ui;

import A4.B;
import A4.H;
import A4.O;
import Ad.j;
import E9.a;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.support.search.LlmQpOperation;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsUserTrialFragment extends LabsBaseFragment {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchLlmQpOperation {
        private Consumer<String> mCallback;
        private final AlertDialog mDialog;
        private int mOpValue = getValue();

        public SearchLlmQpOperation(Context context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle((CharSequence) "Select LLM QP option");
            builder.setSingleChoiceItems((CharSequence[]) (String[]) Arrays.stream(LlmQpOperation.values()).map(new C0648c(2)).toArray(new C0649d(1)), this.mOpValue, (DialogInterface.OnClickListener) new C0654i(1, this));
            AlertDialog create = builder.create();
            this.mDialog = create;
            create.show();
        }

        public static int getValue() {
            return GalleryPreference.getInstance().loadInt(PreferenceName.SEARCH_LLM_QP_OPERATION, LlmQpOperation.ENABLE.ordinal());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String[] lambda$new$1(int i2) {
            return new String[i2];
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$2(DialogInterface dialogInterface, int i2) {
            GalleryPreference.getInstance().saveState(PreferenceName.SEARCH_LLM_QP_OPERATION, i2);
            this.mOpValue = i2;
            Consumer<String> consumer = this.mCallback;
            if (consumer != null) {
                consumer.accept(LlmQpOperation.valueOf(i2).getValue());
            }
            dismiss();
        }

        public void dismiss() {
            this.mDialog.dismiss();
        }

        public SearchLlmQpOperation setCallback(Consumer<String> consumer) {
            this.mCallback = consumer;
            return this;
        }

        public void show() {
            this.mDialog.show();
        }
    }

    private void addCategoryCommon() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_category_common", CommonConstants.SERVICE_NAME_COMMON);
        addSwitchPreference(computePreferenceCategory, PocFeatures.C2paFileEdit, "C2PA File edit", "File Edit work with C2PA");
        addSwitchPreference(computePreferenceCategory, PocFeatures.C2paSecMp, "C2PA SEC MP", "C2PA check via SEC MP");
        addSwitchPreference(computePreferenceCategory, PocFeatures.AlbumMonthExtend, "Grid 12x in album pictures", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.UseConcatThumbnail, "Grid 12x custom view", "Use concatenated thumbnail on month cluster (12x)");
        addSwitchPreference(computePreferenceCategory, PocFeatures.ExportPhotoMotionClips, "Export photo motion clips", (String) null);
        addSwitchPreference(computePreferenceCategory, PocFeatures.BurstShotSeeker, "Expand seeker for Burst/Similar images", (String) null);
        Preference addSwitchPreference = addSwitchPreference(computePreferenceCategory, PocFeatures.WifiGallery, "Remote Gallery", "Access an album of the remote device in the same WiFi domain");
        if (addSwitchPreference != null) {
            addSwitchPreference.setOnPreferenceChangeListener(new H(10, (Object) this, (Object) addSwitchPreference));
        }
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.PlayVideoInPresentation, "Play video in presentation", "Play video in presentation for mirroring");
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.PlayVideoInSlideshowPresentation, "Play video in slideshow presentation", "Play video in slideshow presentation for mirroring");
        addSwitchPreference(computePreferenceCategory, PocFeatures.SlideshowV2Bgm, "Slideshow V2 BGM", "Enable BGM in slideshow V2");
        addSwitchPreference(computePreferenceCategory, PocFeatures.StoryOriginScaleWhenPaused, "Story full viewing", "Show full contents when story playing is paused");
    }

    private void addCategoryDev() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_category_ut_dev", "OneUI 8.5");
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.MxBlurAlbums, "Album blur cover", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.BottomTabMenu, "Bottom tab menu", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.Collection, "Collection tab", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.SearchCategoryScreenShot, "Screen shot filter category", (String) null);
    }

    private void addCategoryPoc() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_category_poc", "PoC");
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.RecentAlbumTimeline, "Timeline in Recent album", "Support timeline in recent album");
        addSwitchPreference(computePreferenceCategory, PocFeatures.CompareImages, getString(R$string.compare_images), (String) null);
    }

    private void addCategorySearch() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_category_search", "Search for ONE UI 8.5(V-OS)");
        addGenericPreference(computePreferenceCategory, "fix_wrong_faces_database", "Fix wrong faces table", (String) null, (Preference.OnPreferenceClickListener) new j(20));
        PocFeatures pocFeatures = PocFeatures.SearchLog;
        addSwitchPreference(computePreferenceCategory, pocFeatures, "Debug search transaction", "/Android/media/com.sec.android.gallery3d/log/search.log");
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.VisualSearch71, "Visual search view 7.x", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.SearchAllFilters, "Enable all filter dialog", (String) null);
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.BottomTabPickerSearch, "Show search tab for picker", (String) null);
        PreferenceFeatures preferenceFeatures = PreferenceFeatures.ViewClusterResultMenuOption;
        addSwitchPreference(computePreferenceCategory, preferenceFeatures, "Enable [" + getString(R$string.view_results_in_category) + "] option for Search cluster", (String) null);
        String key = PreferenceName.SEARCH_LLM_QP_OPERATION.key();
        addGenericPreference(computePreferenceCategory, key, "LLM QP operation option", "operation : " + LlmQpOperation.valueOf(SearchLlmQpOperation.getValue()).getValue(), (Preference.OnPreferenceClickListener) new O(27, this));
        addSwitchPreference(computePreferenceCategory, PocFeatures.SearchDebugInfo, "Search debug info", "Provides the search results from which DB");
        addSwitchPreference(computePreferenceCategory, pocFeatures, "Debug search transaction", "/Android/media/com.sec.android.gallery3d/log/search.log");
        addSwitchPreference(computePreferenceCategory, PreferenceFeatures.SseDebug, "SSE Debug", "Send debug info to test app");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCommon$10(Preference preference, Preference preference2, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            showSwitchConfirmDialog(preference, PocFeatures.WifiGallery, "Remote Gallery", "Access an album of the remote device in the same WiFi domain. You can see them using browser or launch Samsung Gallery from the browser.\n\nStart the server from \"Menu > Start Remote Album\" and connect it using QR code.\n");
            return false;
        }
        PocFeatures.WifiGallery.setEnabled(false);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategorySearch$8(String str) {
        Optional.ofNullable(findPreference(PreferenceName.SEARCH_LLM_QP_OPERATION.key())).ifPresent(new B(str, 8));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategorySearch$9(Preference preference) {
        new SearchLlmQpOperation(getContext()).setCallback(new a(10, this)).show();
        return true;
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs_common_search;
    }

    public int getTitleId() {
        return R$string.labs_user_trial;
    }

    public void initPreferences() {
        super.initPreferences();
        addCategoryDev();
        addCategorySearch();
        addCategoryCommon();
        addCategoryPoc();
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }
}
