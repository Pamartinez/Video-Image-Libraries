package com.samsung.android.gallery.app.activity;

import A4.A;
import A4.C0367b;
import B5.e;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.activity.GalleryFragmentFactory;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.activity.preload.DataRequesterFactory;
import com.samsung.android.gallery.app.activity.preload.abstraction.IDataRequester;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.delegate.LocationEditDelegate;
import com.samsung.android.gallery.app.controller.delegate.PickerDelegate;
import com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate;
import com.samsung.android.gallery.app.controller.delegate.TagEditorDelegate;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.externals.SamsungAccountSignInCmd;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.dialog.DialogFactory;
import com.samsung.android.gallery.app.ui.dialog.FileOperationDialog;
import com.samsung.android.gallery.app.ui.dialog.RenameFileDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AutoAlbumSettingFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.ScreenshotFilterCustomFragment;
import com.samsung.android.gallery.app.ui.list.albums.virtual.PrivateAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;
import com.samsung.android.gallery.app.ui.list.search.keyword.KeywordResultFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.category.CategoryPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Fragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.screenshot.SearchScreenShotPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.StoriesCategoryListFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.StoriesTripListFragment;
import com.samsung.android.gallery.app.ui.list.stories.favorite.StoriesFavoriteFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.legacy.StoryPicturesLegacyFragment;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapFragment;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewFragment;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowFragment;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.RequestCode;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.account.IntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.crop.CropRequestInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewNavigatorController {
    private final Object LOCK = new Object();
    protected final String TAG = getClass().getSimpleName();
    protected final IGalleryActivityView mActivityView;
    protected final Blackboard mBlackboard;
    protected IDataRequester mDataRequester;
    private volatile LaunchIntent mLaunchIntent;
    protected final Stack<String> mLocationStack;
    private final boolean mRootClass;

    public ViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        Stack<String> stack = new Stack<>();
        this.mLocationStack = stack;
        this.mRootClass = ViewNavigatorController.class.getName().equals(getClass().getName());
        this.mBlackboard = blackboard;
        this.mActivityView = iGalleryActivityView;
        blackboard.publishIfEmpty("debug://locationKeyStack", stack);
    }

    private MvpBaseFragment createStoryPicturesCompat() {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return new StoryPicturesFragment();
        }
        return new StoryPicturesLegacyFragment();
    }

    private IDataRequester getDataRequester() {
        if (this.mDataRequester == null) {
            this.mDataRequester = new DataRequesterFactory().create(getRequesterType(), this.mBlackboard);
        }
        return this.mDataRequester;
    }

    private String getUriBuilderWithArgs(String str, Bundle bundle) {
        UriBuilder uriBuilder = new UriBuilder(str);
        bundle.keySet().forEach(new A(4, (Object) uriBuilder, (Object) bundle));
        return uriBuilder.build();
    }

    private void handleAwesomeIntelligenceServiceConsentResult(int i2) {
        boolean z;
        if (i2 == -1) {
            AwesomeIntelligenceServiceManager.getInstance().setIntelligenceServiceShown(this.mActivityView.getContext());
            boolean hasAccount = SamsungAccountManager.getInstance().reload().hasAccount();
            if (!hasAccount || !SamsungAccountManager.isSamsungAiSupportAccount(getContext())) {
                z = false;
            } else {
                z = true;
            }
            Log.d(this.TAG, "handleIntelligenceServiceConsentResult success", Integer.valueOf(i2), Boolean.valueOf(hasAccount), Boolean.valueOf(z));
            if (!hasAccount) {
                new SamsungAccountSignInCmd().execute((EventContext) this.mActivityView.getActivity(), new Object[0]);
                return;
            }
            Object pop = this.mBlackboard.pop("data://viewer_executed_ai_edit", null);
            if (z) {
                this.mBlackboard.postEvent(EventMessage.obtain(3059, pop));
            } else {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        } else {
            Log.d(this.TAG, "handleIntelligenceServiceConsentResult failed", Integer.valueOf(i2));
        }
    }

    private void handleDocumentScanActivityResult(int i2) {
        if (i2 == -1) {
            this.mBlackboard.postEvent(EventMessage.obtain(3039, Integer.valueOf(i2)));
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3055));
    }

    private void handleExitDlnaWithSwipe(int i2, Intent intent) {
        if (i2 == -1) {
            String stringExtra = intent.getStringExtra("exitSwipe");
            if ("next".equals(stringExtra)) {
                Blackboard.postEventGlobal(EventMessage.obtain(9004, Boolean.TRUE));
            } else if ("prev".equals(stringExtra)) {
                Blackboard.postEventGlobal(EventMessage.obtain(9004, Boolean.FALSE));
            }
        }
    }

    private void handleIntelligenceServiceConsentResult(int i2) {
        boolean z;
        String str = this.TAG;
        Log.i(str, "handleIntelligenceServiceConsentResult: " + i2);
        if (i2 == -1) {
            boolean hasSamsungAccountId = SamsungAccountManager.getInstance().hasSamsungAccountId(getContext());
            if (!hasSamsungAccountId || !SamsungAccountManager.isSamsungAiSupportAccount(getContext())) {
                z = false;
            } else {
                z = true;
            }
            if (!hasSamsungAccountId) {
                new SamsungAccountSignInCmd().execute((EventContext) this.mActivityView.getActivity(), Boolean.valueOf(SamsungAccountManager.getInstance().isAccountTypeB2B(getContext())));
                return;
            }
            Object pop = this.mBlackboard.pop("data://viewer_executed_intelligence_service", null);
            if (z) {
                this.mBlackboard.postEvent(EventMessage.obtain(3059, pop));
            } else {
                IntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        } else {
            Log.w(this.TAG, "handleIntelligenceServiceConsentResult: failed");
        }
    }

    private void handleLightRoomAddLibraryReady(int i2) {
        this.mBlackboard.post("command://event/AddedToLightRoomLibrary", (Object) null);
    }

    private void handleOnBackgroundBlurEditorActivityResult(int i2) {
        if (i2 == -1) {
            updatePortraitEffectBitmap();
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3055));
    }

    private void handleOnChangePortraitEffectActivityResult(int i2, Intent intent) {
        Bundle bundle;
        if (i2 == -1) {
            updatePortraitEffectBitmap();
            Blackboard blackboard = this.mBlackboard;
            if (intent != null) {
                bundle = intent.getExtras();
            } else {
                bundle = null;
            }
            blackboard.postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_ERROR_SOCIAL_SERVICE_NOT_AVAILABLE, bundle));
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3055));
    }

    private void handleOnImage360ActivityResult(Intent intent) {
        String str = this.TAG;
        Log.d(str, "handleOnImage360ActivityResult intent=" + intent);
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.getBoolean("is_direction_changed", false)) {
                    this.mBlackboard.postEvent(EventMessage.obtain(3022));
                }
                Log.d(this.TAG, "handleOnImage360ActivityResult publish USER_DATA_IMAGE_360");
                return;
            }
            Log.d(this.TAG, "handleOnImage360ActivityResult extras is null!");
        }
    }

    private void handleOnPhotoEditorActivityReenter(Intent intent) {
        Bundle extras = intent.getExtras();
        String str = this.TAG;
        Log.at(str, "handleOnPhotoEditorActivityReenter extras=" + Logger.toString(extras));
        if (extras != null) {
            this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_OWNERS_PERMISSIONS_CANNOT_BE_CHANGED, extras));
        } else {
            this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_NOT_ACCEPTED_THE_INVITATION_YET));
        }
    }

    private void handleOnPhotoEditorActivityResult(Intent intent, int i2) {
        boolean z;
        Bundle extras;
        String str = this.TAG;
        Log.at(str, "handleOnPhotoEditorActivityResult intent=" + Logger.getEncodedString((Object) intent) + " resultCode=" + i2);
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_APP_TRANSITION);
        if (isEnabled && (i2 == 0 || intent == null)) {
            Log.at(this.TAG, "PhotoEditor's change is discarded.");
            this.mBlackboard.postEvent(EventMessage.obtain(3029));
            this.mBlackboard.post("data://bixby_command_done", new Object[]{"user_cancel"});
        } else if (intent == null || (extras = intent.getExtras()) == null) {
            String str2 = this.TAG;
            if (intent == null) {
                z = true;
            } else {
                z = false;
            }
            Log.w((CharSequence) str2, "handleOnPhotoEditorActivityResult intent or extras is null!", Boolean.valueOf(z));
            this.mBlackboard.post("data://bixby_command_done", new Object[]{"user_cancel"});
        } else {
            if (!isEnabled || extras.getBoolean("no_return_transition")) {
                this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_OWNERS_PERMISSIONS_CANNOT_BE_CHANGED, extras));
            }
            if (extras.getString("sub_state", (String) null) == null && TextUtils.equals(extras.getString("sub_service", (String) null), "save_result")) {
                this.mBlackboard.post("data://bixby_command_done", new Object[]{"no_error", extras.getString("mime_type"), extras.getString("output"), extras.getParcelable("saved_uri")});
            }
        }
    }

    private void handleOnRequestSVoiceRecognitionActivityResult(int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("samsung.honeyboard.extra.RESULTS");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.mBlackboard.postEvent(EventMessage.obtain(8503, stringExtra));
            }
        }
    }

    private void handleOnRequestVoiceRecognitionActivityResult(int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        if (i2 == -1 && intent != null && (stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS")) != null && !stringArrayListExtra.isEmpty()) {
            String str = stringArrayListExtra.get(0);
            if (!TextUtils.isEmpty(str)) {
                this.mBlackboard.postEvent(EventMessage.obtain(8503, str));
            }
        }
    }

    private void handleOnSuggestionItemActivityResult(int i2) {
        if (i2 == -1) {
            this.mBlackboard.post("command:///UpdateSuggestionItem", (Object) null);
        }
    }

    private void handleOnThemeStoreActivityResult(int i2) {
        if (i2 == -1) {
            this.mBlackboard.publish("command://request_suicide", (Object) null);
        }
    }

    private void handlePickerResult(int i2, int i7, Intent intent) {
        LaunchIntent launchIntent = getLaunchIntent();
        if (launchIntent.isAlbumCoverPick()) {
            Blackboard.getInstance(launchIntent.getIntent().getStringExtra("blackboard_name")).publish("data://user/selected", this.mBlackboard.pop("data://user/selected"));
            Activity activity = (Activity) getContext();
            activity.setResult(i7, intent);
            activity.finish();
            return;
        }
        PickerDelegate.handleOnActivityResult(this.mBlackboard, i2, i7, intent);
    }

    private void handleSAFamilyGroupResult(int i2, Intent intent) {
        if (i2 == -1) {
            this.mBlackboard.postEvent(EventMessage.obtain(6012));
        }
    }

    private void handleSamsungAccountSignIn(int i2, Intent intent) {
        String str;
        String str2;
        Object pop = this.mBlackboard.pop("data://viewer_executed_ai_edit", null);
        Object pop2 = this.mBlackboard.pop("data://viewer_executed_intelligence_service", null);
        if (pop2 != null) {
            handleSamsungAccountSignInIntelligenceService(i2, intent, pop2);
        } else if (i2 != -1) {
            String str3 = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (intent != null) {
                str = intent.getStringExtra("error_message");
            } else {
                str = "";
            }
            Log.w((CharSequence) str3, "handleAccountSignIn failed", valueOf, str);
        } else if (Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES)) {
            boolean isSamsungAiSupportAccount = SamsungAccountManager.isSamsungAiSupportAccount(getContext());
            String str4 = this.TAG;
            Integer valueOf2 = Integer.valueOf(i2);
            Boolean valueOf3 = Boolean.valueOf(isSamsungAiSupportAccount);
            if (pop != null) {
                str2 = "AiEdit";
            } else {
                str2 = "Intelligence";
            }
            Log.d(str4, "handleAccountSignIn success", valueOf2, valueOf3, str2);
            if (isSamsungAiSupportAccount) {
                this.mBlackboard.postEvent(EventMessage.obtain(3059, pop));
            } else {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        }
    }

    private void handleSamsungAccountSignInIntelligenceService(int i2, Intent intent, Object obj) {
        String str;
        String str2 = this.TAG;
        Log.i(str2, "handleSamsungAccountSignInIntelligenceService: " + obj);
        if (i2 == -1) {
            boolean isSamsungAiSupportAccount = SamsungAccountManager.isSamsungAiSupportAccount(getContext());
            Log.d(this.TAG, "handleSamsungAccountSignInIntelligenceService success", Integer.valueOf(i2), Boolean.valueOf(isSamsungAiSupportAccount), obj);
            if (isSamsungAiSupportAccount) {
                this.mBlackboard.postEvent(EventMessage.obtain(3059, obj));
            } else {
                IntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        } else {
            String str3 = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (intent != null) {
                str = intent.getStringExtra("error_message");
            } else {
                str = "";
            }
            Log.w((CharSequence) str3, "handleSamsungAccountSignInIntelligenceService failed", valueOf, str);
        }
    }

    private void handleSharedAlbumSetupWizard(int i2) {
        if (i2 == -1) {
            this.mBlackboard.postEvent(EventMessage.obtain(6006));
        }
    }

    private void handleStoryBgmPickDone(int i2, Intent intent) {
        Bundle bundle;
        if (i2 == -1) {
            Blackboard blackboard = this.mBlackboard;
            if (intent != null) {
                bundle = intent.getExtras();
            } else {
                bundle = null;
            }
            blackboard.postEvent(EventMessage.obtain(1095, bundle));
        }
    }

    private void handleStoryHighlightSaveResult(int i2, int i7, Intent intent) {
        boolean z;
        String str;
        boolean z3 = false;
        if (i2 == 1796) {
            z = true;
        } else {
            z = false;
        }
        if (i7 == -1) {
            z3 = true;
        }
        if (intent == null || intent.getExtras() == null) {
            str = "";
        } else {
            str = intent.getExtras().getString("export_file_path");
        }
        Object[] objArr = {Boolean.valueOf(z3), str, Integer.valueOf(i2)};
        if (z3 && z) {
            Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.video_saved_in, str));
        }
        this.mBlackboard.postEvent(EventMessage.obtain(1092, objArr));
    }

    private void handleStoryMusicPickDone(int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            this.mBlackboard.postEvent(EventMessage.obtain(1096, intent.getData()));
        }
    }

    private boolean isNeedToChangeLocationKey(String str) {
        if (("location://story/albums".equals(str) && (Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_GED))) || "location://search".equals(str)) {
            return true;
        }
        if ((!LocationKey.isCollection(str) || PreferenceFeatures.OneUi8x.COLLECTION_TAB) && !"location://sharing/albums".equals(str) && !"location://mtp".equals(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getUriBuilderWithArgs$0(UriBuilder uriBuilder, Bundle bundle, String str) {
        if (!"_SUBSCRIBE_KEY".equals(str) && !"_PUBLISHER".equals(str) && !"#".equals(str)) {
            uriBuilder.appendArg(str, bundle.getString(str));
        }
    }

    private boolean needRefreshData(String str) {
        if (ArgumentsUtil.getArgValue(str, "ARGS_NO_REFRESH") == null) {
            return true;
        }
        return false;
    }

    private boolean recoverLastStack() {
        if (!getLaunchIntent().isFromMainLauncher()) {
            return false;
        }
        String[] split = PreferenceCache.LastLocationKeyStack.getString().split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (split.length <= 1) {
            return false;
        }
        for (int i2 = 1; i2 < split.length; i2++) {
            this.mBlackboard.publish("command://MoveURL", split[i2].trim());
        }
        Log.d(this.TAG, "recoverLastStack");
        PreferenceCache.LastLocationKeyStack.clear();
        return true;
    }

    private void saveLocationStack() {
        if (PocFeatures.TBD.RECOVER_LAST_STACK && getLaunchIntent().isFromMainLauncher()) {
            if (this.mLocationStack.size() > 1) {
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                this.mLocationStack.forEach(new e(stringJoiner, 2));
                PreferenceCache.LastLocationKeyStack.setString(stringJoiner.toString());
                return;
            }
            PreferenceCache.LastLocationKeyStack.clear();
        }
    }

    private boolean setChildMvpFragmentForContainer(String str, String str2) {
        if (skipChildMvpFragmentForContainer(str)) {
            return false;
        }
        if (isExistFragment("ListContainerFragment")) {
            if (this.mLocationStack.size() == 1) {
                updateHistoryStack("location://albums");
            }
            this.mBlackboard.post("command://AddChildFragment", str);
            return true;
        }
        Log.w(this.TAG, "Shortcut should be launched as container fragment.");
        return false;
    }

    private boolean setFragment(Fragment fragment, String str, String str2) {
        setLocationKey(fragment, str);
        return commitFragment(fragment, str2);
    }

    private Fragment setLocationKey(Fragment fragment, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("locationKey", str);
        bundle.putString("callerKey", this.mActivityView.getTopFragmentTag());
        bundle.putString("blackboardKey", this.mBlackboard.getName());
        fragment.setArguments(bundle);
        return fragment;
    }

    private boolean skipChildMvpFragmentForContainer(String str) {
        if (UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(str, "cluster_album_item", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE)) || "ScreenShot".equals(ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, ""))) {
            return true;
        }
        return false;
    }

    private boolean supportSearchCluster(String str) {
        return "key_word".equals(ArgumentsUtil.getArgValue(str, "term"));
    }

    private void syncMoveBack() {
        synchronized (this.LOCK) {
            try {
                if (this.mLocationStack.size() > 1) {
                    this.mLocationStack.pop();
                    String peek = this.mLocationStack.peek();
                    if (supportDrawerLayout()) {
                        peek = ArgumentsUtil.appendArgs(peek, "focus_only", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
                    }
                    this.mBlackboard.publish("location://variable/currentv1", peek);
                    writeNavigateLog("back#" + this.mLocationStack.size() + "> " + peek);
                } else if (this.mLocationStack.size() == 1) {
                    this.mLocationStack.pop();
                    writeNavigateLog("back#0");
                }
                saveLocationStack();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void syncRemoveUrl(String str) {
        synchronized (this.LOCK) {
            try {
                if (this.mLocationStack.size() > 1) {
                    if (str != null) {
                        this.mLocationStack.remove(str);
                    } else {
                        this.mLocationStack.pop();
                    }
                } else if (this.mLocationStack.size() == 1) {
                    this.mLocationStack.pop();
                    writeNavigateLog("back#0");
                }
                saveLocationStack();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void updateHistoryStack(String str) {
        synchronized (this.LOCK) {
            try {
                if (!this.mLocationStack.empty()) {
                    this.mLocationStack.pop();
                    this.mLocationStack.push(str);
                    saveLocationStack();
                    writeNavigateLog(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void updatePortraitEffectBitmap() {
        this.mBlackboard.postEvent(EventMessage.obtain(3043));
    }

    private void writeNavigateLog(String str) {
        try {
            Log.majorEvent(URLDecoder.decode(str, StandardCharsets.UTF_8.toString()));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "writeNavigateLog", (Throwable) e);
        }
    }

    public void clearPreloadedData(Object obj, Bundle bundle) {
        getDataRequester().clearPreload((String) obj);
    }

    public boolean commitFragment(Fragment fragment, String str) {
        return commitFragment(fragment, str, (ArrayList<View>) null);
    }

    public boolean commitFragmentByLocationKey(String str, String str2) {
        MvpBaseFragment mvpBaseFragment;
        String str3;
        str2.getClass();
        char c5 = 65535;
        switch (str2.hashCode()) {
            case -1708313737:
                if (str2.equals("location://albums/AlbumSetting/ScreenShotFilterCustom")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1497569866:
                if (str2.equals("location://albums/AutoAlbumSetting")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1327340640:
                if (str2.equals("location://albums/fileList")) {
                    c5 = 2;
                    break;
                }
                break;
            case -212479357:
                if (str2.equals("location://story/albums")) {
                    c5 = 3;
                    break;
                }
                break;
            case -125579287:
                if (str2.equals("location://albums")) {
                    c5 = 4;
                    break;
                }
                break;
            case 45543451:
                if (str2.equals("location://sharing/albums/fileList/SharingAlbumSetting")) {
                    c5 = 5;
                    break;
                }
                break;
            case 130119015:
                if (str2.equals("location://albums/AlbumSetting")) {
                    c5 = 6;
                    break;
                }
                break;
            case 263612166:
                if (str2.equals("location://timeline")) {
                    c5 = 7;
                    break;
                }
                break;
            case 983147555:
                if (str2.equals("location://collection")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1894681211:
                if (str2.equals("location://albums/all")) {
                    c5 = 9;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return setFragment(new ScreenshotFilterCustomFragment(), str, str2);
            case 1:
                return setFragment(new AutoAlbumSettingFragment(), str, str2);
            case 2:
            case 9:
                if (supportDrawerLayout() && setChildMvpFragmentForContainer(str, str2)) {
                    return true;
                }
            case 3:
                if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
                    return setFragment(new StoriesCategory2Fragment(), str, str2);
                }
                return setMvpFragmentForContainer(str2);
            case 4:
            case 7:
            case 8:
                return setMvpFragmentForContainer(str2);
            case 5:
                return setFragment(new SharingPicturesSettingFragment(), str, str2);
            case 6:
                return setFragment(new AlbumSettingFragment(), str, str2);
        }
        GalleryFragmentFactory.FragmentFactory fragmentFactory = GalleryFragmentFactory.get(str2);
        if (fragmentFactory != null) {
            return setMvpFragment(fragmentFactory.create(), str, str2);
        }
        if (str2.startsWith("location://recap")) {
            return setMvpFragment(new RecapFragment(), str, str2);
        }
        if (LocationKey.isCropView(str2)) {
            return setMvpFragment(new CropViewFragment(), str, str2);
        }
        if (LocationKey.isContentViewer(str2)) {
            return setMvpFragment(new VuContainerFragment(), str, str2);
        }
        if (LocationKey.isSlideShow(str2)) {
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.SlideshowV2)) {
                return setMvpFragment(new SlideshowV2Fragment(), str, str2);
            }
            return setMvpFragment(new SlideshowFragment(), str, str2);
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61 && LocationKey.isSearchCategoryLocation(str)) {
            return setMvpFragment(new ClusteringMapFragmentV2(), str, str2);
        } else {
            if (LocationKey.isSearchKeyword(str2)) {
                if (ArgumentsUtil.getArgValue(str, "searchResultOnMapView", false)) {
                    return setMvpFragment(new ClusteringMapFragmentV2(), str, str2);
                }
                if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster)) {
                    return setMvpFragment(Features.isEnabled(Features.SUPPORT_SCS_SEARCH) ? new KeywordResultFragment() : new SearchPicturesFragment(), str, str2);
                } else if (!supportSearchCluster(str)) {
                    return setMvpFragment(new SearchPicturesFragment(), str, str2);
                } else {
                    if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                        return setMvpFragment(new SearchClusterResultFragment(), str, str2);
                    }
                    if (PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD) {
                        str3 = ArgumentsUtil.appendArgsIfEmpty(str, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
                    } else {
                        str3 = ArgumentsUtil.removeArg(str, "disableTimeline");
                    }
                    return setMvpFragment(new SearchClusterResult2Fragment(), str3, str2);
                }
            } else if (LocationKey.isSearchRelationshipPreview(str2)) {
                return setMvpFragment(new SearchRelationshipPreviewFragment(), str, str2);
            } else {
                if (LocationKey.isSearchPictures(str2)) {
                    if (ArgumentsUtil.getArgValue(str, "searchResultOnMapView", false)) {
                        return setMvpFragment(new ClusteringMapFragmentV2(), str, str2);
                    }
                    if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && str.startsWith("location://search/fileList/Category/ScreenShot")) {
                        return setMvpFragment(new SearchScreenShotPicturesFragment(), str, str2);
                    }
                    if (LocationKey.isCategoryList(str)) {
                        return setMvpFragment(new CategoryPicturesFragment(), str, str2);
                    }
                    return setMvpFragment(new SearchPicturesFragment(), str, str2);
                } else if (LocationKey.isStoryPictures(str2)) {
                    return setMvpFragment(createStoryPicturesCompat(), str, str2);
                } else {
                    if (LocationKey.isSearchAutoComplete(str2)) {
                        return setMvpFragment(new SuggestionContainerFragment(), str, str2);
                    }
                    if (PreferenceFeatures.OneUi30.MEMORIES && LocationKey.isSpotifySlideShow(str2)) {
                        return setMvpFragment(new SpotifySlideshowFragment(), str, str2);
                    }
                    if (LocationKey.isFolder(str2)) {
                        if (!supportDrawerLayout() || !setChildMvpFragmentForContainer(str, str2)) {
                            return setMvpFragment(new FolderViewFragment(), str, str2);
                        }
                        return true;
                    } else if (LocationKey.isStoryHighlight(str)) {
                        return setMvpFragment(new StoryHighlightFragment(), str, str2);
                    } else {
                        if (LocationKey.isStoriesFavorite(str)) {
                            return setMvpFragment(new StoriesFavoriteFragment(), str, str2);
                        }
                        if (LocationKey.isStoriesCategory(str)) {
                            if (str.startsWith("location://stories/category/trip")) {
                                mvpBaseFragment = new StoriesTripListFragment();
                            } else {
                                mvpBaseFragment = new StoriesCategoryListFragment();
                            }
                            return setMvpFragment(mvpBaseFragment, str, str2);
                        } else if (LocationKey.isRemasterPictures(str)) {
                            if (PreferenceFeatures.OneUi5x.REMASTER_PICTURES_V2) {
                                return setMvpFragment(new RemasterPicturesFragment(), str, str2);
                            }
                            return setMvpFragment(new RevitalizedPicturesFragment(), str, str2);
                        } else if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateAlbum(str)) {
                            return false;
                        } else {
                            setMvpFragment(new PrivateAlbumPicturesFragment(), str, str2);
                            return true;
                        }
                    }
                }
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("== view navigator : location stacks #" + this.mLocationStack.size());
        this.mLocationStack.forEach(new C0367b(18, printWriter));
        printWriter.println("");
    }

    public Context getContext() {
        return this.mActivityView.getContext();
    }

    public LaunchIntent getLaunchIntent() {
        if (this.mLaunchIntent == null) {
            this.mLaunchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent", null);
            if (this.mLaunchIntent == null) {
                this.mLaunchIntent = this.mActivityView.getLaunchIntent();
            }
        }
        return this.mLaunchIntent;
    }

    public DataRequesterFactory.LaunchType getRequesterType() {
        return DataRequesterFactory.LaunchType.NORMAL;
    }

    public void handleFinishFragment() {
        if (this.mActivityView.finishFragment()) {
            syncMoveBack();
        } else {
            Log.e(this.TAG, "fail to finish fragment");
        }
    }

    public boolean isExistFragment(String str) {
        return this.mActivityView.isExistFragment(str);
    }

    public void onActivityCreate(Object obj, Bundle bundle) {
        onNavigatorCreated();
    }

    public void onActivityReenter(Object obj, Bundle bundle) {
        String str;
        Intent intent = (Intent) ((Object[]) obj)[1];
        if (intent != null) {
            ComponentName component = intent.getComponent();
            if (component != null) {
                str = component.getPackageName();
            } else {
                str = null;
            }
            if (str != null && ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME.contentEquals(str)) {
                handleOnPhotoEditorActivityReenter(intent);
                return;
            }
            return;
        }
        Log.at(this.TAG, "onActivityReenter - intent is null, force start transition.");
        this.mBlackboard.postEvent(EventMessage.obtain(GroupAuthority.REASON_NOT_ACCEPTED_THE_INVITATION_YET));
    }

    public void onActivityResult(Object obj, Bundle bundle) {
        Object[] objArr = (Object[]) obj;
        int intValue = ((Integer) objArr[0]).intValue();
        int intValue2 = ((Integer) objArr[1]).intValue();
        Intent intent = (Intent) objArr[2];
        if (RequestCode.isPickerRequestCode(intValue) || intValue == 769) {
            handlePickerResult(intValue, intValue2, intent);
        } else if (intValue == 778) {
            LocationEditDelegate.handleOnLocationEditActivityResult(this.mBlackboard, intValue2, intent);
        } else if (intValue == 782) {
            handleOnPhotoEditorActivityResult(intent, intValue2);
        } else if (intValue == 791) {
            handleOnImage360ActivityResult(intent);
        } else if (intValue == 788) {
            handleOnBackgroundBlurEditorActivityResult(intValue2);
        } else if (intValue == 2320) {
            handleOnChangePortraitEffectActivityResult(intValue2, intent);
        } else if (intValue == 2311) {
            handleOnRequestVoiceRecognitionActivityResult(intValue2, intent);
        } else if (intValue == 2312) {
            handleOnRequestSVoiceRecognitionActivityResult(intValue2, intent);
        } else if (intValue == 784 || intValue == 785) {
            handleOnSuggestionItemActivityResult(intValue2);
        } else if (intValue == 2309) {
            handleOnThemeStoreActivityResult(intValue2);
        } else if (intValue == 790) {
            TagEditorDelegate.handleOnTagEditorResult(this.mBlackboard, intValue2, intent);
        } else if (intValue == 300) {
            SesPickerDelegate.handleOnActivityResult((EventContext) this.mActivityView.getActivity(), intValue2, intent);
        } else if (intValue == 795) {
            handleDocumentScanActivityResult(intValue2);
        } else if (intValue == 802) {
            new DocumentScanCmd.DocumentScanSaveTask().execute(this.mActivityView.getActivity(), intValue2, intent);
        } else if (intValue == 301) {
            handleSAFamilyGroupResult(intValue2, intent);
        } else if (intValue == 8192) {
            handleSharedAlbumSetupWizard(intValue2);
        } else if (intValue == 1796 || intValue == 1797 || intValue == 1798) {
            handleStoryHighlightSaveResult(intValue, intValue2, intent);
        } else if (intValue == 1799) {
            handleStoryBgmPickDone(intValue2, intent);
        } else if (intValue == 1800) {
            handleStoryMusicPickDone(intValue2, intent);
        } else if (intValue == 796) {
            handleLightRoomAddLibraryReady(intValue2);
        } else if (intValue == 798) {
            handleAwesomeIntelligenceServiceConsentResult(intValue2);
        } else if (intValue == 799) {
            handleSamsungAccountSignIn(intValue2, intent);
        } else if (intValue == 800) {
            handleIntelligenceServiceConsentResult(intValue2);
        } else if (intValue == 801) {
            handleExitDlnaWithSwipe(intValue2, intent);
        }
    }

    public void onCropImage(Object obj, Bundle bundle) {
        Bundle bundle2;
        MediaItem mediaItem = (MediaItem) this.mBlackboard.read(DataKey.DATA("location://cropView"));
        boolean z = BundleWrapper.getBoolean(bundle, "pick-from-gallery", true);
        Blackboard blackboard = this.mBlackboard;
        if (z) {
            bundle2 = CropRequestInfo.get(bundle);
        } else {
            bundle2 = getLaunchIntent().getExtra();
        }
        blackboard.publish("data://user/Crop/ReqInfo", bundle2);
        new VuLauncher(this.mBlackboard).launch("location://cropView", 0, mediaItem);
    }

    public void onCurrent(Object obj, Bundle bundle) {
        String str = (String) obj;
        String removeArgs = ArgumentsUtil.removeArgs(str);
        boolean supportDrawerLayout = supportDrawerLayout();
        if (supportDrawerLayout) {
            str = ArgumentsUtil.removeArg(str, "focus_only");
        }
        updateHistoryStack(str);
        if (needRefreshData(str)) {
            getDataRequester().load(removeArgs, str);
        }
        if (supportDrawerLayout) {
            this.mBlackboard.publish("data://drawer_focus_changed", obj);
        }
    }

    public void onDestroy() {
        getDataRequester().destroy();
    }

    public void onDialog(Object obj, Bundle bundle) {
        String DATA_REQUEST_REVERT = CommandKey.DATA_REQUEST_REVERT(BundleWrapper.getKey(bundle));
        showDialog(DialogFactory.create(DATA_REQUEST_REVERT), bundle, DATA_REQUEST_REVERT);
    }

    public void onHandleBroadcastEvent(Object obj, Bundle bundle) {
        this.mActivityView.handleBroadcastEvent((EventMessage) obj);
    }

    public void onHandleEvent(Object obj, Bundle bundle) {
        this.mActivityView.handleEvent((EventMessage) obj);
    }

    public void onLocationEdit(Object obj, Bundle bundle) {
        Activity activity = this.mActivityView.getActivity();
        if (SeApiCompat.isActivityResumed(activity)) {
            LocationEditDelegate.startLocationEditActivity(activity, bundle);
        }
    }

    public void onMove(Object obj, Bundle bundle) {
        if (!this.mActivityView.isActivityDestroyed()) {
            String str = (String) obj;
            str.getClass();
            if (str.equals("command://MoveCMD/FinishFragment")) {
                handleFinishFragment();
            } else if (str.equals("command://MoveCMD/SyncBackKey")) {
                syncMoveBack();
            }
        }
    }

    public void onMoveUrl(Object obj, Bundle bundle) {
        String str = (String) obj;
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (!commitFragmentByLocationKey(str, removeArgs)) {
            Log.e((CharSequence) this.TAG, "onMoveUrl failed", removeArgs);
            this.mBlackboard.erase("data://on_location_moving");
            onMoveUrlFailed(removeArgs);
            return;
        }
        synchronized (this.LOCK) {
            this.mLocationStack.push(str);
        }
        this.mBlackboard.publish("location://variable/currentv1", str);
        saveLocationStack();
    }

    public void onMoveView(Object obj, Bundle bundle) {
        String DATA_REQUEST_REVERT = CommandKey.DATA_REQUEST_REVERT(BundleWrapper.getKey(bundle));
        DATA_REQUEST_REVERT.getClass();
        char c5 = 65535;
        switch (DATA_REQUEST_REVERT.hashCode()) {
            case 610100134:
                if (DATA_REQUEST_REVERT.equals("data://user/move/AlbumChoice")) {
                    c5 = 0;
                    break;
                }
                break;
            case 1049365246:
                if (DATA_REQUEST_REVERT.equals("data://user/move/SharingAlbumChoice")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1605011956:
                if (DATA_REQUEST_REVERT.equals("data://user/move/AlbumFolderChoice")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                onMoveUrl(getUriBuilderWithArgs("location://albums/choice/root", bundle), bundle);
                return;
            case 1:
                onMoveUrl(getUriBuilderWithArgs("location://sharing/choice", bundle), bundle);
                return;
            case 2:
                onMoveUrl(getUriBuilderWithArgs("location://folder/choice", bundle), bundle);
                return;
            default:
                return;
        }
    }

    public void onNavigatorCreated() {
        String currentLocation = LocationKey.getCurrentLocation();
        boolean z = PocFeatures.TBD.RECOVER_LAST_STACK;
        if (z) {
            getLaunchIntent();
        }
        if (isNeedToChangeLocationKey(currentLocation)) {
            currentLocation = "location://timeline";
        }
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            if (LocationKey.isStories(currentLocation)) {
                currentLocation = "location://collection";
            }
            if (LocationKey.isCollection(currentLocation)) {
                currentLocation = ArgumentsUtil.appendArgs(currentLocation, "vs_use_cache", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
        }
        preloadDataCursor(currentLocation);
        this.mBlackboard.post("command://MoveURL", currentLocation);
        if (z) {
            recoverLastStack();
        }
    }

    public void onPicker(Object obj, Bundle bundle) {
        PickerDelegate.startPicker(this.mActivityView.getActivity(), bundle);
    }

    public void onRemoveUrl(Object obj, Bundle bundle) {
        syncRemoveUrl((String) obj);
    }

    public void onShowDialog(Object obj, Bundle bundle) {
        DialogFragment dialogFragment;
        String string = BundleWrapper.getString(bundle, "target", "");
        string.getClass();
        char c5 = 65535;
        switch (string.hashCode()) {
            case -1334391743:
                if (string.equals("data://user/dialog/Switchable")) {
                    c5 = 0;
                    break;
                }
                break;
            case -300698654:
                if (string.equals("RenameFileDialog")) {
                    c5 = 1;
                    break;
                }
                break;
            case 549271635:
                if (string.equals("FileOperationDialog")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1188882503:
                if (string.equals("SimpleProgressDialog")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (!this.mActivityView.isDialogShowing("data://user/dialog/Switchable")) {
                    dialogFragment = new SwitchableDialog();
                    break;
                } else {
                    return;
                }
            case 1:
                dialogFragment = new RenameFileDialog();
                break;
            case 2:
                dialogFragment = new FileOperationDialog();
                break;
            case 3:
                if (!this.mActivityView.isDialogShowing("SimpleProgressDialog")) {
                    dialogFragment = new SimpleProgressDialog();
                    break;
                } else {
                    return;
                }
            default:
                dialogFragment = null;
                break;
        }
        showDialog(dialogFragment, bundle, string);
    }

    public void onTagEdit(Object obj, Bundle bundle) {
        TagEditorDelegate.startTagEditor(this.mActivityView.getActivity(), bundle, this.mBlackboard);
    }

    public void preloadDataCursor(String str) {
        String str2 = this.TAG;
        Log.p(str2, "preloadDataCursor " + str);
        Trace.beginSection("preloadDataCursor");
        getDataRequester().preload(str);
        Trace.endSection();
    }

    public final boolean setMvpFragment(MvpBaseFragment mvpBaseFragment, String str, String str2) {
        return setMvpFragment(mvpBaseFragment, str, mvpBaseFragment.getFragmentTag(str2), mvpBaseFragment.getSharedViewList(this.mBlackboard));
    }

    public boolean setMvpFragmentForContainer(String str) {
        String str2;
        MvpBaseFragment mvpBaseFragment;
        boolean supportDrawerLayout = supportDrawerLayout();
        if (supportDrawerLayout) {
            str2 = "ListContainerFragment";
        } else {
            str2 = "BottomTabFragment";
        }
        if (isExistFragment(str2)) {
            return false;
        }
        if (supportDrawerLayout) {
            mvpBaseFragment = new ListContainerFragment();
        } else {
            mvpBaseFragment = new BottomTabFragment();
        }
        return setMvpFragment(mvpBaseFragment, str, str2);
    }

    public void showDialog(DialogFragment dialogFragment, Bundle bundle, String str) {
        if (dialogFragment != null) {
            dialogFragment.setArguments(bundle);
            this.mActivityView.showDialog(dialogFragment, str);
        }
    }

    public boolean supportDrawerLayout() {
        if (!this.mRootClass || !DrawerUtil.supportDrawerLayout(getContext())) {
            return false;
        }
        return true;
    }

    private boolean setMvpFragment(MvpBaseFragment mvpBaseFragment, String str, String str2, ArrayList<View> arrayList) {
        setLocationKey(mvpBaseFragment, str);
        return commitFragment(mvpBaseFragment, str2, arrayList);
    }

    public boolean commitFragment(Fragment fragment, String str, ArrayList<View> arrayList) {
        return this.mActivityView.commitFragment(fragment, str);
    }

    public void onMoveUrlFailed(String str) {
    }

    public void onRequestCropImage(Object obj, Bundle bundle) {
    }

    public void onRequestQuickViewItem(Object obj, Bundle bundle) {
    }

    public void onSharingsDataLoaded(Object obj, Bundle bundle) {
    }

    public void onTimelineDataLoaded(Object obj, Bundle bundle) {
    }
}
