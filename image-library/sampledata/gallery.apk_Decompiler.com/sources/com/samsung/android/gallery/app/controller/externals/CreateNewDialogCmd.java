package com.samsung.android.gallery.app.controller.externals;

import A.a;
import A4.C0387w;
import L5.b;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.SaveAsPdfCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateNewDialogCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public static final int COLLAGE_DESCRIPTION_RES;
    /* access modifiers changed from: private */
    public static final int COLLAGE_ICON_RES;
    /* access modifiers changed from: private */
    public static final int GIF_ICON_RES;
    /* access modifiers changed from: private */
    public static final int MEITU_ICON_RES;
    /* access modifiers changed from: private */
    public static final int MOVIE_ICON_RES;
    private final ArrayList<CreateType> mItems = new ArrayList<>();

    /* renamed from: com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType[] r0 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType = r0
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.HIGHLIGHT_REEL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.MOVIE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.COLLAGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.GIF     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.MEITU_POSTER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.MEITU_VIDEO_CLIP     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.PDF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd$CreateType r1 = com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.CreateType.VIDEO_STUDIO     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CreateType {
        HIGHLIGHT_REEL(R.drawable.ic_suggestions_create_popup_highlightreal, R.string.highlight_reel, R.string.highlight_reel_description),
        GIF(CreateNewDialogCmd.GIF_ICON_RES, R.string.create_gif_menu, R.string.create_gif_menu_description),
        COLLAGE(CreateNewDialogCmd.COLLAGE_ICON_RES, R.string.sa_collage, CreateNewDialogCmd.COLLAGE_DESCRIPTION_RES),
        MOVIE(CreateNewDialogCmd.MOVIE_ICON_RES, R.string.create_movie_menu, R.string.create_movie_menu_description),
        MEITU_POSTER(CreateNewDialogCmd.MEITU_ICON_RES, R.string.meitu_poster, R.string.meitu_poster_description),
        MEITU_VIDEO_CLIP(CreateNewDialogCmd.MEITU_ICON_RES, R.string.meitu_video_clip, R.string.meitu_video_clip_description),
        PDF(R.drawable.ic_create_popup_album, R.string.save_as_pdf, R.string.save_as_pdf_description),
        VIDEO_STUDIO(R.drawable.ic_samsung_studio, R.string.video_studio_app_name, R.string.video_studio_app_name, 0);
        
        private final int mDescription;
        private final int mIcon;
        private final int mNeedIconTint;
        private final int mTitle;

        private CreateType(int i2, int i7, int i8) {
            this(r8, r9, i2, i7, i8, 1);
        }

        public String getValue() {
            return this.mIcon + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTitle + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mDescription + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mNeedIconTint;
        }

        private CreateType(int i2, int i7, int i8, int i10) {
            this.mIcon = i2;
            this.mTitle = i7;
            this.mDescription = i8;
            this.mNeedIconTint = i10;
        }
    }

    static {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        boolean z = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
        if (z) {
            i2 = R.drawable.ic_create_new_circle_dialog_gif;
        } else {
            i2 = R.drawable.suggestions_create_popup_gif;
        }
        GIF_ICON_RES = i2;
        if (z) {
            i7 = R.drawable.ic_create_new_circle_dialog_collage;
        } else {
            i7 = R.drawable.suggestions_create_popup_collage;
        }
        COLLAGE_ICON_RES = i7;
        if (z) {
            i8 = R.drawable.ic_create_new_circle_dialog_movie;
        } else {
            i8 = R.drawable.suggestions_create_popup_movie;
        }
        MOVIE_ICON_RES = i8;
        if (z) {
            i10 = R.drawable.ic_create_new_circle_dialog_meitu;
        } else {
            i10 = R.drawable.gallery_suggestions_create_popup_meitu_china_delta_line;
        }
        MEITU_ICON_RES = i10;
        if (!PreferenceFeatures.OneUi40.SUPPORT_COLLAGE_ON_VIDEO_TRIMMER || !Features.isEnabled(Features.SUPPORT_VIDEO_COLLAGE)) {
            i11 = R.string.sa_collage_image_description;
        } else {
            i11 = R.string.sa_collage_image_video_description;
        }
        COLLAGE_DESCRIPTION_RES = i11;
    }

    private CreateMovieCmd getCreateMovieCmd() {
        if (Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) {
            return new CreateMovieMultiEditCmd();
        }
        if (Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2)) {
            return new CreateMovieManualCmd();
        }
        return new CreateMovieCmd();
    }

    private boolean isCreateCollageVisible(boolean[] zArr) {
        if (!Features.isEnabled(Features.SUPPORT_CREATE_COLLAGE) || !zArr[CreateType.COLLAGE.ordinal()]) {
            return false;
        }
        return true;
    }

    private boolean isCreateGifVisible(boolean[] zArr) {
        if (Features.isEnabled(Features.IS_GED) || !zArr[CreateType.GIF.ordinal()]) {
            return false;
        }
        return true;
    }

    private boolean isCreateHighlightReelVisible(boolean[] zArr) {
        if (!Features.isEnabled(Features.SUPPORT_CREATE_HIGHLIGHT_REEL) || !zArr[CreateType.HIGHLIGHT_REEL.ordinal()]) {
            return false;
        }
        return true;
    }

    private boolean isCreateMeituPosterVisible(boolean[] zArr) {
        if (!Features.isEnabled(Features.SUPPORT_MEITU) || !zArr[CreateType.MEITU_POSTER.ordinal()]) {
            return false;
        }
        return true;
    }

    private boolean isCreateMeituVideoClipVisible(boolean[] zArr) {
        if (!Features.isEnabled(Features.SUPPORT_MEITU) || !zArr[CreateType.MEITU_VIDEO_CLIP.ordinal()] || !isMountedChinaSimCard()) {
            return false;
        }
        return true;
    }

    private boolean isCreateMovieVisible(boolean[] zArr) {
        if ((Features.isEnabled(Features.SUPPORT_CREATE_MOVIE) || Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2) || Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) && zArr[CreateType.MOVIE.ordinal()]) {
            return true;
        }
        return false;
    }

    private boolean isMountedChinaSimCard() {
        String str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService("phone");
            String str2 = "";
            if (telephonyManager != null) {
                str = telephonyManager.getSimOperator();
            } else {
                str = str2;
            }
            if (str != null && str.length() > 3) {
                str2 = str.substring(0, 3);
            }
            if (TextUtils.isEmpty(str2) || !str2.startsWith("460")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("isMountedChinaSimCard failed e="), this.TAG);
            return false;
        }
    }

    private boolean isVideoStudioVisible() {
        if (!Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO) || getHandler().isSelectionMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getItems$0(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(this.TAG, "CreateNewDialog invalid data is selected");
            return;
        }
        Object obj = arrayList.get(0);
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !"video_studio_button".equals(obj)) {
            if (obj instanceof Integer) {
                i2 = ((Integer) obj).intValue();
            } else {
                i2 = -1;
            }
            if (i2 < 0 || i2 >= this.mItems.size()) {
                a.B(i2, "CreateNewDialog invalid data ", this.TAG);
                return;
            }
            switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$controller$externals$CreateNewDialogCmd$CreateType[this.mItems.get(i2).ordinal()]) {
                case 1:
                    new CreateMovieHighlightReelCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 2:
                    getCreateMovieCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 3:
                    new CreateCollageCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 4:
                    new CreateGifCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 5:
                    new CreateMeituPostCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 6:
                    new CreateMeituMovieCmd().execute(eventContext, eventContext.getSelectedItems());
                    return;
                case 7:
                    new SaveAsPdfCmd().execute(eventContext, new Object[0]);
                    return;
                case 8:
                    new StartVideoStudioCmd().execute(eventContext, this);
                    return;
                default:
                    return;
            }
        } else {
            new StartVideoStudioCmd().execute(eventContext, this);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_CREATE_NEW.toString();
    }

    public String[] getItems() {
        return (String[]) this.mItems.stream().map(new b(15)).toArray(new C0387w(22));
    }

    public void loadItems(boolean[] zArr) {
        if (isCreateHighlightReelVisible(zArr)) {
            this.mItems.add(CreateType.HIGHLIGHT_REEL);
        }
        if (isCreateGifVisible(zArr)) {
            this.mItems.add(CreateType.GIF);
        }
        if (isCreateCollageVisible(zArr)) {
            this.mItems.add(CreateType.COLLAGE);
        }
        if (isCreateMovieVisible(zArr)) {
            this.mItems.add(CreateType.MOVIE);
        }
        if (isCreateMeituPosterVisible(zArr)) {
            this.mItems.add(CreateType.MEITU_POSTER);
        }
        if (isCreateMeituVideoClipVisible(zArr)) {
            this.mItems.add(CreateType.MEITU_VIDEO_CLIP);
        }
        if (PocFeatures.isEnabled(PocFeatures.SaveAsPdf)) {
            this.mItems.add(CreateType.PDF);
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 && isVideoStudioVisible()) {
            this.mItems.add(CreateType.VIDEO_STUDIO);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        loadItems(objArr[0]);
        if (this.mItems.size() < 1) {
            Log.e(this.TAG, "Failed to execute -> no create sub menu");
            return;
        }
        UriBuilder appendArg = new UriBuilder("data://user/dialog/CreateNew").appendArg("create_new_items", getItems());
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !isVideoStudioVisible()) {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        } else {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(appendArg.appendArg("video_studio_button", str).build()).setOnDataCompleteListener(new K4.a(10, this)).start();
    }
}
