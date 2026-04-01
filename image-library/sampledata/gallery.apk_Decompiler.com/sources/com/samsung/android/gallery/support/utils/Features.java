package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import Sd.x;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.config.Component$PackageName;
import com.samsung.android.gallery.support.config.Component$SamsungSearch;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.result.CommonStatusCodes;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sum.core.message.Message;
import com.samsung.scsp.common.Status;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import t1.C0279d;
import t1.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Features {
    ;
    
    public static final String COUNTRY_CODE = null;
    public static final String COUNTRY_CODE_LOCAL = null;
    public static final String SALES_CODE = null;
    private static final String TAG = "Features";
    private static volatile String sVendorDevice;
    private Boolean mIsEnabled;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends Features {
        public /* synthetic */ AnonymousClass1() {
            this("IS_GED", 0);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$10  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass10 extends Features {
        public /* synthetic */ AnonymousClass10() {
            this("USE_SCREEN_SHARING", 9);
        }

        public boolean getEnabling() {
            DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(Features.getAppContext());
            if (displayManagerCompat == null || !displayManagerCompat.isScreenSharingSupported()) {
                return false;
            }
            return true;
        }

        private AnonymousClass10(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$100  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass100 extends Features {
        public /* synthetic */ AnonymousClass100() {
            this("IS_THEME_INSTALLED", 99);
        }

        public boolean getEnabling() {
            if (AppResources.getSecTheme() != null) {
                return true;
            }
            return false;
        }

        private AnonymousClass100(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$101  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass101 extends Features {
        public /* synthetic */ AnonymousClass101() {
            this("IS_COLOR_THEME_ENABLED", 100);
        }

        public boolean getEnabling() {
            if (SeApiCompat.getSettingsSystemInt(AppResources.getAppContext(), "wallpapertheme_state", 0) == 1) {
                return true;
            }
            return false;
        }

        private AnonymousClass101(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$102  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass102 extends Features {
        public /* synthetic */ AnonymousClass102() {
            this("IS_AFW_MODE", 101);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_KNOX_MODE) || !SeApiCompat.isAfw(Features.getAppContext())) {
                return false;
            }
            return true;
        }

        private AnonymousClass102(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$103  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass103 extends Features {
        public /* synthetic */ AnonymousClass103() {
            this("IS_KNOX_MODE", 102);
        }

        public boolean getEnabling() {
            return SeApiCompat.isKnoxMode(Features.getAppContext());
        }

        private AnonymousClass103(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$104  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass104 extends Features {
        public /* synthetic */ AnonymousClass104() {
            this("IS_ON_SECURE_FOLDER", 103);
        }

        public boolean getEnabling() {
            return SeApiCompat.isOnSecureFolder(Features.getAppContext());
        }

        private AnonymousClass104(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$105  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass105 extends Features {
        public /* synthetic */ AnonymousClass105() {
            this("IS_SEP_LITE", 104);
        }

        public boolean getEnabling() {
            String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_COMMON_CONFIG_SEP_CATEGORY");
            if ("sep_lite".equals(string) || "sep_lite_new".equals(string) || Features.hasSystemFeature("com.samsung.feature.samsung_experience_mobile_lite")) {
                return true;
            }
            return false;
        }

        private AnonymousClass105(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$106  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass106 extends Features {
        public /* synthetic */ AnonymousClass106() {
            this("IS_SEP_LOW_SEGMENT", 105);
        }

        private boolean isSepLowSegment() {
            return GalleryPreference.getInstanceCache().computeBooleanIfAbsent("SepLowSegment", new C0671i(0));
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_SEP_LITE) || isSepLowSegment()) {
                return true;
            }
            return false;
        }

        private AnonymousClass106(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$107  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass107 extends Features {
        public /* synthetic */ AnonymousClass107() {
            this("IS_TABLET_BY_SYSTEM_PROPERTIES", 106);
        }

        public boolean getEnabling() {
            String systemPropertiesString = SeApiCompat.getSystemPropertiesString("ro.build.characteristics", "");
            if (systemPropertiesString == null || !systemPropertiesString.contains("tablet")) {
                return false;
            }
            return true;
        }

        private AnonymousClass107(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$108  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass108 extends Features {
        public /* synthetic */ AnonymousClass108() {
            this("IS_WATCH_FACE_LARGE_ENABLED", 107);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !LockScreenConfigSubDisplay.contains("WATCHFACE") || !LockScreenConfigSubDisplay.contains("LARGESCREEN")) {
                return false;
            }
            return true;
        }

        private AnonymousClass108(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$109  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass109 extends Features {
        public /* synthetic */ AnonymousClass109() {
            this("IS_COVER_SCREEN_ENABLED", 108);
        }

        public boolean getEnabling() {
            return LockScreenConfigSubDisplay.contains("WATCHFACE");
        }

        private AnonymousClass109(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$11  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass11 extends Features {
        public /* synthetic */ AnonymousClass11() {
            this("USE_WFD", 10);
        }

        public boolean getEnabling() {
            DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(Features.getAppContext());
            if (displayManagerCompat == null || !displayManagerCompat.isWfdSupported()) {
                return false;
            }
            return true;
        }

        private AnonymousClass11(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$110  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass110 extends Features {
        public /* synthetic */ AnonymousClass110() {
            this("SUPPORT_FLIP_COVER_GALLERY", 109);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !Features.isEnabled(Features.IS_COVER_SCREEN_ENABLED) || !LockScreenConfigSubDisplay.contains("LARGESCREEN")) {
                return false;
            }
            return true;
        }

        private AnonymousClass110(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$111  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass111 extends Features {
        public /* synthetic */ AnonymousClass111() {
            this("IS_CLEAR_COVER_SCREEN_ENABLED", 110);
        }

        public boolean getEnabling() {
            return LockScreenConfigSubDisplay.contains("VIRTUAL_DISPLAY");
        }

        private AnonymousClass111(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$112  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass112 extends Features {
        public /* synthetic */ AnonymousClass112() {
            this("IS_AOD_ENABLED_SIMPLE", 111);
        }

        public boolean getEnabling() {
            if (!FloatingFeatures.contains("SEC_FLOATING_FEATURE_FRAMEWORK_CONFIG_AOD_ITEM", "aodversion") || FloatingFeatures.getInt("SEC_FLOATING_FEATURE_LCD_CONFIG_AOD_FULLSCREEN") != 0) {
                return false;
            }
            return true;
        }

        private AnonymousClass112(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$113  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass113 extends Features {
        private static final String ACTION_SET_AS_AOD = "com.samsung.android.app.aodservice.intent.action.SET_AS_AOD";
        private static final int AOD_CONTENT_TO_SHOW_HOME_ONLY = 2;
        private static final String AOD_CONTENT_URI = "content://com.samsung.android.app.aodservice.provider/settings/aod_content_to_show";
        private static final String AOD_PACKAGE_NAME = "com.samsung.android.app.aodservice";

        public /* synthetic */ AnonymousClass113() {
            this("IS_AOD_ENABLED", 112);
        }

        private boolean isAodPackageAvailable() {
            List<ResolveInfo> list;
            Intent intent = new Intent(ACTION_SET_AS_AOD).setPackage(AOD_PACKAGE_NAME);
            if (Features.isPackageCompatible(AOD_PACKAGE_NAME, 804400000)) {
                intent.setType("image/*");
            }
            PackageManager packageManager = PackageMonitorCompat.getInstance().getPackageManager();
            if (packageManager != null) {
                list = packageManager.queryIntentActivities(intent, 0);
            } else {
                list = null;
            }
            if (list == null || list.isEmpty()) {
                return false;
            }
            return true;
        }

        private boolean isShowHomeOnly() {
            Cursor query;
            Throwable th;
            boolean z;
            if (SdkConfig.lessThan(SdkConfig.GED.R)) {
                try {
                    query = Features.getAppContext().getContentResolver().query(Uri.parse(AOD_CONTENT_URI), (String[]) null, (String) null, (String[]) null, (String) null);
                    if (query != null) {
                        if (query.moveToFirst()) {
                            if (query.getInt(0) == 2) {
                                z = true;
                            } else {
                                z = false;
                            }
                            query.close();
                            return z;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e) {
                    a.s(e, new StringBuilder("isShowHomeOnly failed. e="), Features.TAG);
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            return false;
            throw th;
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_AOD_ENABLED_SIMPLE) || Features.isEnabled(Features.IS_KNOX_MODE) || !isAodPackageAvailable() || isShowHomeOnly()) {
                return false;
            }
            return true;
        }

        private AnonymousClass113(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$114  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass114 extends Features {
        public /* synthetic */ AnonymousClass114() {
            this("SUPPORT_UNLIMITED_MOVE_TO_SECURE_FOLDER", 113);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.GED.Q);
        }

        private AnonymousClass114(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$115  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass115 extends Features {
        public /* synthetic */ AnonymousClass115() {
            this("IS_USA_DEVICE", 114);
        }

        public boolean getEnabling() {
            return Features.isCountryCode("USA");
        }

        private AnonymousClass115(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$116  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass116 extends Features {
        public /* synthetic */ AnonymousClass116() {
            this("IS_CHINESE_DEVICE", 115);
        }

        public boolean getEnabling() {
            if (Features.isSalesCode("CHN", "CHM", "CHC", "CHU", "CTC", "LHS", "CBK", "OZL")) {
                return true;
            }
            if (!Features.isSalesCode("PAP") || !Features.isCountryCode("CHINA")) {
                return false;
            }
            return true;
        }

        private AnonymousClass116(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$117  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass117 extends Features {
        public /* synthetic */ AnonymousClass117() {
            this("IS_KOREAN_DEVICE", 116);
        }

        public boolean getEnabling() {
            if (Features.isCountryCode("KOREA") || Features.isSalesCode("SKT", "KTT", "LGT")) {
                return true;
            }
            return false;
        }

        private AnonymousClass117(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$118  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass118 extends Features {
        public /* synthetic */ AnonymousClass118() {
            this("IS_JAPAN_DEVICE", 117);
        }

        public boolean getEnabling() {
            return Features.isCountryCode("JP");
        }

        private AnonymousClass118(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$119  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass119 extends Features {
        public /* synthetic */ AnonymousClass119() {
            this("IS_JAPAN_CLOUD_BRAND", 118);
        }

        public boolean getEnabling() {
            String str = CscFeatures.get("CscFeature_Common_ConfigSamsungCloudVariation");
            if (TextUtils.isEmpty(str) || !str.contains("SamsungCloudBrandType:String:JAPAN")) {
                return false;
            }
            return true;
        }

        private AnonymousClass119(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$12  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass12 extends Features {
        public /* synthetic */ AnonymousClass12() {
            this("USE_CHOSEN_COMPONENT_RECEIVER", 11);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.O_MR5);
        }

        private AnonymousClass12(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$120  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass120 extends Features {
        public /* synthetic */ AnonymousClass120() {
            this("IS_VERIZON_DEVICE", 119);
        }

        public boolean getEnabling() {
            return Features.isSalesCode("VZW", "VPP");
        }

        private AnonymousClass120(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$121  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass121 extends Features {
        public /* synthetic */ AnonymousClass121() {
            this("IS_ATT_DEVICE", 120);
        }

        public boolean getEnabling() {
            return Features.isSalesCode("ATT", "AIO", "APP");
        }

        private AnonymousClass121(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$122  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass122 extends Features {
        public /* synthetic */ AnonymousClass122() {
            this("IS_UPSM", 121);
        }

        public boolean getEnabling() {
            return SeApiCompat.isUpsm(Features.getAppContext());
        }

        private AnonymousClass122(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$123  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass123 extends Features {
        public /* synthetic */ AnonymousClass123() {
            this("IS_REPAIR_MODE", 122);
        }

        public boolean getEnabling() {
            return SeApiCompat.isRepairMode();
        }

        private AnonymousClass123(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$124  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass124 extends Features {
        public /* synthetic */ AnonymousClass124() {
            this("IS_RTL", 123);
        }

        public boolean getEnabling() {
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                return true;
            }
            return false;
        }

        private AnonymousClass124(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$125  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass125 extends Features {
        public /* synthetic */ AnonymousClass125() {
            this("IS_PHOTO_EDITOR_NEED_BITMAP_THROUGH_INTENT", 124);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.Q_MR5) || Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 282108000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass125(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$126  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass126 extends Features {
        public /* synthetic */ AnonymousClass126() {
            this("SUPPORT_GDPR", 125);
        }

        public boolean getEnabling() {
            return !Features.isUnpackDevice();
        }

        private AnonymousClass126(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$127  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass127 extends Features {
        public /* synthetic */ AnonymousClass127() {
            this("SUPPORT_PLACE_GDPR", 126);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass127(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$128  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass128 extends Features {
        public /* synthetic */ AnonymousClass128() {
            this("SUPPORT_SAMSUNG_PLACE", 127);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_SPC_LOCATION_POI");
        }

        private AnonymousClass128(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$129  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass129 extends Features {
        public /* synthetic */ AnonymousClass129() {
            this("SHOW_BAIDU_LOCATION_AUTH_POPUP", 128);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B) || !Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass129(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$13  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass13 extends Features {
        public /* synthetic */ AnonymousClass13() {
            this("USE_SET_AS_VIDEO_WALLPAPER", 12);
        }

        public boolean getEnabling() {
            if (!FloatingFeatures.contains("SEC_FLOATING_FEATURE_LOCKSCREEN_CONFIG_WALLPAPER_STYLE", "VIDEO") || Features.isEnabled(Features.IS_KNOX_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass13(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$130  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass130 extends Features {
        public /* synthetic */ AnonymousClass130() {
            this("SUPPORT_VISION_INTELLIGENCE", 129);
        }

        private boolean supportAiVision() {
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.samsung.android.visionintelligence");
            if (applicationMetaData == null || !applicationMetaData.getBoolean("com.samsung.android.visionintelligence.supports_ai_vision", false)) {
                return false;
            }
            return true;
        }

        public boolean getEnabling() {
            if (!Features.isPackageSettingEnabled("com.samsung.android.visionintelligence") || Features.isEnabled(Features.IS_KNOX_MODE) || supportAiVision()) {
                return false;
            }
            return true;
        }

        private AnonymousClass130(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$131  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass131 extends Features {
        public /* synthetic */ AnonymousClass131() {
            this("SUPPORT_VISION_CLOUD", 130);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable("com.samsung.android.visioncloudagent");
        }

        private AnonymousClass131(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$132  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass132 extends Features {
        public /* synthetic */ AnonymousClass132() {
            this("SUPPORT_VERIZON_CLOUD", 131);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_VERIZON_DEVICE) || Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES) || !Features.isPackageAvailable("com.vcast.mediamanager")) {
                return false;
            }
            return true;
        }

        private AnonymousClass132(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$133  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass133 extends Features {
        public /* synthetic */ AnonymousClass133() {
            this("SUPPORT_ATT_CLOUD", 132);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_ATT_DEVICE) || Features.isEnabled(Features.IS_BYOD_CARRIER) || !Features.isPackageAvailable("com.att.personalcloud")) {
                return false;
            }
            return true;
        }

        private AnonymousClass133(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$134  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass134 extends Features {
        public /* synthetic */ AnonymousClass134() {
            this("SUPPORT_MEMORY_SAVER", 133);
        }

        public boolean getEnabling() {
            if ((SdkConfig.atLeast(SdkConfig.SEM.V) || CscFeatures.get("CscFeature_Common_ConfigYuva").contains("MemorySaver")) && Features.isPackageAvailable("com.samsung.memorysaver")) {
                return true;
            }
            return false;
        }

        private AnonymousClass134(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$135  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass135 extends Features {
        public /* synthetic */ AnonymousClass135() {
            this("SUPPORT_KNOX", 134);
        }

        public boolean getEnabling() {
            return !Features.isEnabled(Features.IS_GED);
        }

        private AnonymousClass135(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$136  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass136 extends Features {
        public /* synthetic */ AnonymousClass136() {
            this("SUPPORT_GO_TO_URL", 135);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                if (!Features.isPackageAvailable("com.samsung.android.app.smartcapture") || !CmhVersionHolder.support(300700000)) {
                    return false;
                }
                return true;
            } else if (!FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_GO_TO_URL") || !CmhVersionHolder.support(300700000)) {
                return false;
            } else {
                return true;
            }
        }

        private AnonymousClass136(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$137  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass137 extends Features {
        public /* synthetic */ AnonymousClass137() {
            this("SUPPORT_GOOGLE_PLAY_SERVICE", 136);
        }

        public boolean getEnabling() {
            try {
                if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || C0279d.d.b(Features.getAppContext(), f.f1923a) != 0) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                return false;
            }
        }

        private AnonymousClass137(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$138  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass138 extends Features {
        public /* synthetic */ AnonymousClass138() {
            this("IS_ENABLED_SHOW_BUTTON_SHAPES", 137);
        }

        public boolean getEnabling() {
            if (SeApiCompat.getSettingsSystemInt(Features.getAppContext(), "show_button_background", 0) > 0) {
                return true;
            }
            return false;
        }

        private AnonymousClass138(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$139  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass139 extends Features {
        public /* synthetic */ AnonymousClass139() {
            this("SUPPORT_SEC_MP_ORIENTATION_TAG", 138);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1303);
        }

        private AnonymousClass139(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$14  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass14 extends Features {
        public /* synthetic */ AnonymousClass14() {
            this("USE_SET_AS_COVER_VIDEO_WALLPAPER", 13);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_COVER_SCREEN_ENABLED) || !Features.isEnabled(Features.SUPPORT_WALLPAPER_VIDEO_COVER) || Features.isEnabled(Features.IS_KNOX_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass14(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$140  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass140 extends Features {
        public /* synthetic */ AnonymousClass140() {
            this("SUPPORT_CMH_ORIENTATION_TAG", 139);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(811800000);
        }

        private AnonymousClass140(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$141  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass141 extends Features {
        public /* synthetic */ AnonymousClass141() {
            this("SUPPORT_MY_FILES_API", 140);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.GED.S);
        }

        private AnonymousClass141(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$142  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass142 extends Features {
        public /* synthetic */ AnonymousClass142() {
            this("SUPPORT_VR_GALLERY", 141);
        }

        public boolean getEnabling() {
            if (Features.isPackageAvailable("com.samsung.android.app.vr.gallery2") || Features.isPackageAvailable("com.samsung.android.app.vr.gallery")) {
                return true;
            }
            return false;
        }

        private AnonymousClass142(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$143  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass143 extends Features {
        public /* synthetic */ AnonymousClass143() {
            this("SUPPORT_AGIF_CROPPER", 142);
        }

        public boolean getEnabling() {
            try {
                String absolutePath = Environment.getRootDirectory().getAbsolutePath();
                if (SdkConfig.atLeast(SdkConfig.GED.P)) {
                    if (!FileUtils.exists(absolutePath + "/lib/libagifencoder.quram.so")) {
                        if (!FileUtils.exists(absolutePath + "/lib64/libagifencoder.quram.so")) {
                            return false;
                        }
                    }
                    return true;
                }
                if (!FileUtils.exists(absolutePath + "/vendor/lib/libquramagifencoder.so")) {
                    if (FileUtils.exists(absolutePath + "/vendor/lib64/libquramagifencoder.so")) {
                        return true;
                    }
                    return false;
                }
                return true;
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                return false;
            }
        }

        private AnonymousClass143(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$144  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass144 extends Features {
        public /* synthetic */ AnonymousClass144() {
            this("SUPPORT_LIVE_DRAWING", 143);
        }

        public boolean getEnabling() {
            if (!SdkConfig.match(SdkConfig.SEM.O_MR5) || !Features.isPackageInstalled("com.samsung.android.service.livedrawing")) {
                return false;
            }
            return true;
        }

        private AnonymousClass144(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$145  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass145 extends Features {
        public /* synthetic */ AnonymousClass145() {
            this("SUPPORT_LIVE_DRAWING_V2", 144);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.P) || !Features.isPackageInstalled("com.samsung.android.service.livedrawing")) {
                return false;
            }
            return true;
        }

        private AnonymousClass145(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$146  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass146 extends Features {
        public /* synthetic */ AnonymousClass146() {
            this("SUPPORT_TRASH", 145);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.P) || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_COMMON_DISABLE_RECYCLE_BIN")) {
                return false;
            }
            return true;
        }

        private AnonymousClass146(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$147  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass147 extends Features {
        public /* synthetic */ AnonymousClass147() {
            this("SUPPORT_ONE_TRASH", 146);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.U);
        }

        private AnonymousClass147(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$148  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass148 extends Features {
        public /* synthetic */ AnonymousClass148() {
            this("SUPPORT_ONE_TRASH_DURATION", 147);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_ONE_TRASH) || !SecTrashVersionHolder.support(1400400000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass148(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$149  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass149 extends Features {
        public /* synthetic */ AnonymousClass149() {
            this("SUPPORT_ONE_TRASH_NEW_CLOUD", 148);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_ONE_TRASH) || !SecTrashVersionHolder.support(1600800000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass149(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$15  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass15 extends Features {
        public /* synthetic */ AnonymousClass15() {
            this("SUPPORT_WALLPAPER_VIDEO_COVER", 14);
        }

        public boolean getEnabling() {
            return FloatingFeatures.contains("SEC_FLOATING_FEATURE_LOCKSCREEN_CONFIG_WALLPAPER_STYLE", "COVER_MP4");
        }

        private AnonymousClass15(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$150  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass150 extends Features {
        public /* synthetic */ AnonymousClass150() {
            this("SUPPORT_ONE_TRASH_CLOUD_THUMBNAIL_NOT_REMOVE", 149);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1610600000);
        }

        private AnonymousClass150(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$151  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass151 extends Features {
        public /* synthetic */ AnonymousClass151() {
            this("SUPPORT_QUERY_PARSER", 150);
        }

        public boolean getEnabling() {
            int i2 = FloatingFeatures.getInt("SEC_FLOATING_FEATURE_SFINDER_CONFIG_QUERY_PARSER_VERSION");
            if (i2 == 1 || i2 == -1) {
                return true;
            }
            return false;
        }

        private AnonymousClass151(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$152  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass152 extends Features {
        public /* synthetic */ AnonymousClass152() {
            this("SUPPORT_INTELLIGENT_SEARCH", 151);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass152(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$153  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass153 extends Features {
        public /* synthetic */ AnonymousClass153() {
            this("SUPPORT_SCS_SEARCH", 152);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass153(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$154  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass154 extends Features {
        public /* synthetic */ AnonymousClass154() {
            this("SUPPORT_HASHTAG_SEARCH", 153);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass154(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$155  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass155 extends Features {
        public /* synthetic */ AnonymousClass155() {
            this("SUPPORT_HIERARCHICAL_SUGGESTION", 154);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass155(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$156  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass156 extends Features {
        public /* synthetic */ AnonymousClass156() {
            this("SUPPORT_HIERARCHICAL_SUGGESTION_V2", 155);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION)) {
                return false;
            }
            return true;
        }

        private AnonymousClass156(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$157  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass157 extends Features {
        public /* synthetic */ AnonymousClass157() {
            this("SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST", 156);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400030200);
        }

        private AnonymousClass157(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$158  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass158 extends Features {
        public /* synthetic */ AnonymousClass158() {
            this("SUPPORT_RELATIONSHIP_SEARCH", 157);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_SEP_LITE) || !ScsVersionHolder.isEnabled()) {
                return false;
            }
            return true;
        }

        private AnonymousClass158(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$159  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass159 extends Features {
        public /* synthetic */ AnonymousClass159() {
            this("SUPPORT_ACTION_SUGGESTER", 158);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !ScsVersionHolder.isEnabled()) {
                return false;
            }
            return true;
        }

        private AnonymousClass159(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$16  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass16 extends Features {
        public /* synthetic */ AnonymousClass16() {
            this("USE_FL_DRM_ONLY", 15);
        }

        public boolean getEnabling() {
            try {
                return FileUtils.exists(Environment.getRootDirectory().getAbsolutePath() + "/lib/libomafldrm.so");
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                return false;
            }
        }

        private AnonymousClass16(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$160  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass160 extends Features {
        public /* synthetic */ AnonymousClass160() {
            this("SUPPORT_DYNAMIC_SEARCH_SUGGESTION", 159);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass160(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$161  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass161 extends Features {
        public /* synthetic */ AnonymousClass161() {
            this("SUPPORT_DYNAMIC_SEARCH_SUGGESTION_WITH_DIRECT", MOCRLang.GURMUKHI);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.supportHint();
        }

        private AnonymousClass161(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$162  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass162 extends Features {
        public /* synthetic */ AnonymousClass162() {
            this("SUPPORT_RECOMMEND_SIMILAR_CONTACT", MOCRLang.PUNJABI);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.Q_MR5) || !SecMpVersionHolder.support(ErrorCodeConvertor.AGENT_NOT_ACTIVATED)) {
                return false;
            }
            return true;
        }

        private AnonymousClass162(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$163  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass163 extends Features {
        public /* synthetic */ AnonymousClass163() {
            this("SUPPORT_SCS_SEARCH_AUTOCOMPLETE", 162);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.isEnabled();
        }

        private AnonymousClass163(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$164  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass164 extends Features {
        public /* synthetic */ AnonymousClass164() {
            this("SUPPORT_SCS_SEARCH_AUTOCOMPLETE_TAG_SEARCH", 163);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.R_MR1) || !ScsVersionHolder.support(110032200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass164(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$165  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass165 extends Features {
        public /* synthetic */ AnonymousClass165() {
            this("SUPPORT_SCS_SEARCH_SUB_LOCATION_FACET", 164);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.R_MR1) || !ScsVersionHolder.isEnabled()) {
                return false;
            }
            return true;
        }

        private AnonymousClass165(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$166  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass166 extends Features {
        public /* synthetic */ AnonymousClass166() {
            this("SUPPORT_SCS_SEARCH_CONTENT_FILTERING_ON_AUTOCOMPLETE", 165);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.S) || !ScsVersionHolder.support(130011200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass166(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$167  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass167 extends Features {
        public /* synthetic */ AnonymousClass167() {
            this("SUPPORT_SPECIAL_DAY_SUGGESTION", 166);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !ScsVersionHolder.support(300013200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass167(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$168  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass168 extends Features {
        public /* synthetic */ AnonymousClass168() {
            this("SUPPORT_DATE_NLU_AUTO_COMPLETE", 167);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !ScsVersionHolder.support(300033200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass168(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$169  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass169 extends Features {
        public /* synthetic */ AnonymousClass169() {
            this("SUPPORT_SCS_TRANSLATED_KEYWORD", 168);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.S_MR5) || !ScsVersionHolder.support(150002200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass169(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$17  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass17 extends Features {
        public /* synthetic */ AnonymousClass17() {
            this("SUPPORT_REAL_RATIO", 16);
        }

        public boolean getEnabling() {
            if (SdkConfig.FirstApiLevel.LESS_THAN_P || !Features.isEnabled(Features.IS_SEP_LITE)) {
                return true;
            }
            return false;
        }

        private AnonymousClass17(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$170  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass170 extends Features {
        public /* synthetic */ AnonymousClass170() {
            this("SUPPORT_SCS_SEARCH_FEEDBACK", 169);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !ScsVersionHolder.isEnabled()) {
                return false;
            }
            return true;
        }

        private AnonymousClass170(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$171  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass171 extends Features {
        public /* synthetic */ AnonymousClass171() {
            this("SUPPORT_SCS_SEARCH_NEW_CONTENT_OBSERVING_URI", MOCRLang.MALAYALAM);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !ScsVersionHolder.support(210014200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass171(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$172  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass172 extends Features {
        public /* synthetic */ AnonymousClass172() {
            this("SUPPORT_SCS_SEARCH_CHECK_PERMISSION", 171);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !ScsVersionHolder.support(210035200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass172(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$173  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass173 extends Features {
        public /* synthetic */ AnonymousClass173() {
            this("SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE", 172);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !ScsVersionHolder.support(220000200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass173(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$174  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass174 extends Features {
        public /* synthetic */ AnonymousClass174() {
            this("SUPPORT_SCS_SEARCH_AUTOCOMPLETE_UNIFIED", 173);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400068200);
        }

        private AnonymousClass174(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$175  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass175 extends Features {
        public /* synthetic */ AnonymousClass175() {
            this("SUPPORT_PDC_CLUSTER", 174);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_SEARCH_CLUSTER) || !ScsVersionHolder.support(400103200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass175(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$176  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass176 extends Features {
        public /* synthetic */ AnonymousClass176() {
            this("SUPPORT_SCS_FORCE_SYNC_ALL", 175);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400069200);
        }

        private AnonymousClass176(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$177  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass177 extends Features {
        public /* synthetic */ AnonymousClass177() {
            this("SUPPORT_SCS_ALBUM_PICTURES_SEARCH", 176);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400083200);
        }

        private AnonymousClass177(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$178  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass178 extends Features {
        public /* synthetic */ AnonymousClass178() {
            this("SUPPORT_SIMILAR_PHOTO", 177);
        }

        public boolean getEnabling() {
            if ((!SdkConfig.atLeast(SdkConfig.SEM.Q_MR1) || !Features.isPackageInstalled("com.samsung.ipservice")) && !Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return false;
            }
            return true;
        }

        private AnonymousClass178(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$179  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass179 extends Features {
        public /* synthetic */ AnonymousClass179() {
            this("SUPPORT_SINGLE_TAKEN", 178);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.Q_MR1) || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_SUPPORT_SINGLETAKE") || Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return true;
            }
            return false;
        }

        private AnonymousClass179(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$18  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass18 extends Features {
        public /* synthetic */ AnonymousClass18() {
            this("SUPPORT_ORIGINAL_BITMAP_SHRINK", 17);
        }

        public boolean getEnabling() {
            return !Features.isEnabled(Features.IS_SEP_LITE);
        }

        private AnonymousClass18(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$180  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass180 extends Features {
        public /* synthetic */ AnonymousClass180() {
            this("SUPPORT_RUBIN_COLLECT_SEARCH", 179);
        }

        public boolean getEnabling() {
            if (!Features.isPackageAvailable("com.samsung.android.rubin.app") || !Features.isPackageCompatible("com.samsung.android.rubin.app", 260000000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass180(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$181  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass181 extends Features {
        public /* synthetic */ AnonymousClass181() {
            this("IS_BYOD_CARRIER", MOCRLang.KHMER);
        }

        public boolean getEnabling() {
            return SeApiCompat.getSystemPropertiesBoolean("persist.sys.omc_byod", false);
        }

        private AnonymousClass181(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$182  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass182 extends Features {
        public /* synthetic */ AnonymousClass182() {
            this("IS_RUBIN_ENABLED", 181);
        }

        public boolean getEnabling() {
            try {
                PackageManager packageManager = PackageMonitorCompat.getInstance().getPackageManager();
                packageManager.getPackageInfo("com.samsung.android.rubin.app", 0);
                int applicationEnabledSetting = packageManager.getApplicationEnabledSetting("com.samsung.android.rubin.app");
                if (applicationEnabledSetting == 1 || applicationEnabledSetting == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                return false;
            }
        }

        private AnonymousClass182(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$183  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass183 extends Features {
        public /* synthetic */ AnonymousClass183() {
            this("SUPPORT_SD_CARD", 182);
        }

        public boolean getEnabling() {
            if (SeApiCompat.getSystemPropertiesInt("storage.support.sdcard", 0) == 1) {
                return true;
            }
            return false;
        }

        private AnonymousClass183(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$184  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass184 extends Features {
        public /* synthetic */ AnonymousClass184() {
            this("SUPPORT_TAG_EDITOR", 183);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable("com.samsung.android.service.tagservice");
        }

        private AnonymousClass184(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$185  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass185 extends Features {
        public /* synthetic */ AnonymousClass185() {
            this("SUPPORT_VIDEO_PLAYER_FOR_MOTION_VIDEO", 184);
        }

        private boolean supportMotionEditorSep115() {
            if (!Features.isPackageAvailable("com.samsung.app.slowmotion") || !Features.isPackageCompatible("com.samsung.app.slowmotion", 350000600)) {
                return false;
            }
            return true;
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                return true;
            }
            if ((!Features.isEnabled(Features.SUPPORT_DOWNLOAD_SLOW_FAST_MOTION) || Features.isEnabled(Features.IS_UPSM) || Features.isPackageInstalled("com.samsung.app.slowmotion")) && !supportMotionEditorSep115()) {
                return false;
            }
            return true;
        }

        private AnonymousClass185(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$186  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass186 extends Features {
        public /* synthetic */ AnonymousClass186() {
            this("SUPPORT_REALTIME_BLUR", 185);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GRAPHICS_SUPPORT_3D_SURFACE_TRANSITION_FLAG");
        }

        private AnonymousClass186(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$187  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass187 extends Features {
        public /* synthetic */ AnonymousClass187() {
            this("SUPPORT_DEX_ON_PC", 186);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_COMMON_SUPPORT_DEX_ON_PC");
        }

        private AnonymousClass187(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$188  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass188 extends Features {
        public /* synthetic */ AnonymousClass188() {
            this("SUPPORT_CHANGE_BEST_ITEM", 187);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.GED.Q);
        }

        private AnonymousClass188(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$189  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass189 extends Features {
        public /* synthetic */ AnonymousClass189() {
            this("SUPPORT_POI", 188);
        }

        public boolean getEnabling() {
            return !Features.isEnabled(Features.IS_CHINESE_DEVICE);
        }

        private AnonymousClass189(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$19  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass19 extends Features {
        public /* synthetic */ AnonymousClass19() {
            this("SUPPORT_DUAL_SCREEN", 18);
        }

        public boolean getEnabling() {
            return Features.hasSystemFeature("com.sec.feature.dual_lcd");
        }

        private AnonymousClass19(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$190  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass190 extends Features {
        public /* synthetic */ AnonymousClass190() {
            this("SUPPORT_STORIES_DATA_SEP11", 189);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.Q_MR1);
        }

        private AnonymousClass190(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$191  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass191 extends Features {
        public /* synthetic */ AnonymousClass191() {
            this("SUPPORT_MEMORY_DATA", MOCRLang.GREEK);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.R);
        }

        private AnonymousClass191(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$192  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass192 extends Features {
        public /* synthetic */ AnonymousClass192() {
            this("SUPPORT_TABLE_MODE", 191);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                if (FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_FOLDABLE_TYPE_FOLD") || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_FOLDABLE_TYPE_FLIP")) {
                    return true;
                }
                return false;
            } else if (FloatingFeatures.getInt("SEC_FLOATING_FEATURE_SIP_CONFIG_FOLD_UX_VERSION") > 0) {
                return true;
            } else {
                return false;
            }
        }

        private AnonymousClass192(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$193  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass193 extends Features {
        public /* synthetic */ AnonymousClass193() {
            this("SUPPORT_SD_CARD_ERRORS_TIP_CARD", 192);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.Q_MR5);
        }

        private AnonymousClass193(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$194  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass194 extends Features {
        public /* synthetic */ AnonymousClass194() {
            this("SUPPORT_APP_TRANSITION", 193);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.Q_MR5);
        }

        private AnonymousClass194(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$195  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass195 extends Features {
        public /* synthetic */ AnonymousClass195() {
            this("SUPPORT_CLOUD_SYNC_STATUS", 194);
        }

        public boolean getEnabling() {
            if (!Features.isPackageCompatible("com.samsung.android.scloud", 420008100) || !Features.isEnabled(Features.SUPPORT_CLOUD)) {
                return false;
            }
            return true;
        }

        private AnonymousClass195(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$196  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass196 extends Features {
        public /* synthetic */ AnonymousClass196() {
            this("SUPPORT_SMARTSWITCH_RESTORE_STATE", 195);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible("com.sec.android.easyMover", 370508100);
        }

        private AnonymousClass196(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$197  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass197 extends Features {
        public /* synthetic */ AnonymousClass197() {
            this("SUPPORT_ALIVE_ZOOM", 196);
        }

        private boolean isGlobalFeatureEnabled() {
            if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
                String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_ZOOM_TYPE");
                if (string.isEmpty() || "none".equalsIgnoreCase(string)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        private boolean isLegacyFeatureEnabled() {
            if (!SdkConfig.lessThan(SdkConfig.SEM.U) || !CscFeatures.getBoolean("CscFeature_Gallery_SupportAliveZoom", false)) {
                return false;
            }
            return true;
        }

        public boolean getEnabling() {
            if ((isGlobalFeatureEnabled() || isLegacyFeatureEnabled()) && !Features.isEnabled(Features.IS_UPSM) && PocFeatures.isEnabled(PocFeatures.SupportAliveZoom)) {
                return true;
            }
            return false;
        }

        private AnonymousClass197(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$198  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass198 extends Features {
        public /* synthetic */ AnonymousClass198() {
            this("SUPPORT_DC_HAPTIC", 197);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_AUDIO_SUPPORT_DC_MOTOR_HAPTIC_FEEDBACK");
        }

        private AnonymousClass198(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$199  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass199 extends Features {
        public /* synthetic */ AnonymousClass199() {
            this("SUPPORT_AUDIO_FADE_OUT", 198);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T) || "".equals(FloatingFeatures.getString("SEC_FLOATING_FEATURE_AUDIO_CONFIG_SOUNDALIVE_VERSION"))) {
                return false;
            }
            return true;
        }

        private AnonymousClass199(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends Features {
        public /* synthetic */ AnonymousClass2() {
            this("SUPPORT_ANDROID_EMULATOR", 1);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$20  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass20 extends Features {
        public /* synthetic */ AnonymousClass20() {
            this("SUPPORT_REORDER", 19);
        }

        public boolean getEnabling() {
            return !Features.isEnabled(Features.IS_UPSM);
        }

        private AnonymousClass20(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$200  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass200 extends Features {
        public /* synthetic */ AnonymousClass200() {
            this("SUPPORT_FILTER", 199);
        }

        public boolean getEnabling() {
            PackageManager packageManager = Features.getAppContext().getPackageManager();
            if (packageManager == null || !packageManager.hasSystemFeature("com.sec.android.secimaging")) {
                return false;
            }
            return true;
        }

        private AnonymousClass200(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$201  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass201 extends Features {
        public /* synthetic */ AnonymousClass201() {
            this("SUPPORT_MY_FILTER", 200);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getString("SEC_FLOATING_FEATURE_CAMERA_CONFIG_MYFILTER").startsWith("1,");
        }

        private AnonymousClass201(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$202  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass202 extends Features {
        public /* synthetic */ AnonymousClass202() {
            this("SUPPORT_CLOUD_SUGGESTIONS", 201);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(602200000);
        }

        private AnonymousClass202(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$203  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass203 extends Features {
        public /* synthetic */ AnonymousClass203() {
            this("IS_MUM_MODE", 202);
        }

        public boolean getEnabling() {
            return !SeApiCompat.isMyUserIdAsUserOwner();
        }

        private AnonymousClass203(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$204  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass204 extends Features {
        public /* synthetic */ AnonymousClass204() {
            this("SUPPORT_POP_OVER_UI", 203);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.Q_MR5);
        }

        private AnonymousClass204(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$205  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass205 extends Features {
        public /* synthetic */ AnonymousClass205() {
            this("SUPPORT_DYNAMIC_VIEW", 204);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
                return FloatingFeatures.getString("SEC_FLOATING_FEATURE_CAMERA_CONFIG_ACTION_CLASSIFIER").startsWith("1,");
            }
            return false;
        }

        private AnonymousClass205(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$206  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass206 extends Features {
        public /* synthetic */ AnonymousClass206() {
            this("SUPPORT_MEMORY_COVER_SAVE", Message.CODEC_NUM_EXTRA_FRAMES);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.R_MR1);
        }

        private AnonymousClass206(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$207  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass207 extends Features {
        public /* synthetic */ AnonymousClass207() {
            this("SUPPORT_BGM_SERVICE", Message.END_OF_STREAM);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable("com.sec.android.app.ve.vebgm");
        }

        private AnonymousClass207(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$208  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass208 extends Features {
        public /* synthetic */ AnonymousClass208() {
            this("SUPPORT_CALL_BG_PROVIDER_PACKAGE", 207);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || !Features.isPackageAvailable("com.samsung.android.callbgprovider")) {
                return false;
            }
            return true;
        }

        private AnonymousClass208(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$209  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass209 extends Features {
        public /* synthetic */ AnonymousClass209() {
            this("SUPPORT_SHARED_MOTION_PHOTO_PLAY", 208);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
                return true;
            }
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || !Features.isPackageCompatible("com.samsung.android.motionphoto.viewer", 103500000) || !Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER)) {
                return false;
            }
            return true;
        }

        private AnonymousClass209(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$21  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass21 extends Features {
        public /* synthetic */ AnonymousClass21() {
            this("SUPPORT_CLOUD", 20);
        }

        private int getContentSyncVersion() {
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.samsung.android.scloud");
            if (applicationMetaData != null) {
                return applicationMetaData.getInt("content_sync_version", -1);
            }
            return -1;
        }

        private boolean isLegacyService() {
            int ordinal = x.LegacyServiceStatusRequired.ordinal();
            if (GalleryPreference.getInstanceCache().loadInt("cloud_service_available", ordinal) == ordinal) {
                return true;
            }
            return false;
        }

        private boolean isOneDriveMigrationSupported() {
            return GalleryPreference.getInstance().loadBoolean(PreferenceName.ONE_DRIVE_MIGRATION_SUPPORTED, false);
        }

        private boolean isOneDriveUnlinked() {
            return "Unlinked".equals(GalleryPreference.getInstance().loadString(PreferenceName.ONE_DRIVE_LINK_STATE, ""));
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_SEP_LITE) && SdkConfig.lessThan(SdkConfig.SEM.S)) {
                return false;
            }
            if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD) || isLegacyService()) {
                if (Features.isEnabled(Features.IS_VERIZON_DEVICE)) {
                    return isOneDriveMigrationSupported();
                }
                if (!isOneDriveMigrationSupported() && isOneDriveUnlinked()) {
                    return false;
                }
            }
            if (getContentSyncVersion() >= 10000000) {
                return true;
            }
            return false;
        }

        private AnonymousClass21(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$210  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass210 extends Features {
        public /* synthetic */ AnonymousClass210() {
            this("SUPPORT_SHARED_GIF_PLAY", 209);
        }

        public boolean getEnabling() {
            return Features.isEnabled(Features.SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB);
        }

        private AnonymousClass210(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$211  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass211 extends Features {
        public /* synthetic */ AnonymousClass211() {
            this("SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB", MOCRLang.ARMENIAN);
        }

        public boolean getEnabling() {
            return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT);
        }

        private AnonymousClass211(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$212  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass212 extends Features {
        public /* synthetic */ AnonymousClass212() {
            this("SUPPORT_SHARED_SORT", 211);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1220000000);
        }

        private AnonymousClass212(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$213  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass213 extends Features {
        public /* synthetic */ AnonymousClass213() {
            this("SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER", 212);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1210000000);
        }

        private AnonymousClass213(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$214  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass214 extends Features {
        public /* synthetic */ AnonymousClass214() {
            this("SUPPORT_SHARED_RAW_QUERY", 213);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300100023);
        }

        private AnonymousClass214(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$215  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass215 extends Features {
        public /* synthetic */ AnonymousClass215() {
            this("SUPPORT_SHARED_GROUP_RAW_QUERY", 214);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300500015);
        }

        private AnonymousClass215(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$216  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass216 extends Features {
        public /* synthetic */ AnonymousClass216() {
            this("SUPPORT_SEARCH_IN_PICKER", 215);
        }

        public boolean getEnabling() {
            return Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CONTENT_FILTERING_ON_AUTOCOMPLETE);
        }

        private AnonymousClass216(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$217  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass217 extends Features {
        public /* synthetic */ AnonymousClass217() {
            this("SUPPORT_ON_DEMAND_REMASTER", 216);
        }

        public boolean getEnabling() {
            String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_SAIV_CONFIG_AI_REVITAL_VERSION");
            try {
                String[] split = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (split.length <= 1 || Integer.parseInt(split[1]) <= 0) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("checking ");
                sb2.append(name());
                sb2.append(" {");
                sb2.append(string);
                sb2.append("} failed. e=");
                a.s(e, sb2, Features.TAG);
                return false;
            }
        }

        private AnonymousClass217(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$218  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass218 extends Features {
        public /* synthetic */ AnonymousClass218() {
            this("SUPPORT_ON_DEMAND_REMASTER_GIF", 217);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER)) {
                return false;
            }
            return true;
        }

        private AnonymousClass218(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$219  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass219 extends Features {
        public /* synthetic */ AnonymousClass219() {
            this("SUPPORT_ALL_NEW_REMASTER", 218);
        }

        public boolean getEnabling() {
            String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_SAIV_CONFIG_AI_REVITAL_VERSION");
            try {
                String[] split = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (!SdkConfig.atLeast(SdkConfig.SEM.V) || split.length <= 1 || Double.parseDouble(split[0]) < 1.9d || Integer.parseInt(split[1]) < 3) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("checking ");
                sb2.append(name());
                sb2.append(" {");
                sb2.append(string);
                sb2.append("} failed. e=");
                a.s(e, sb2, Features.TAG);
                return false;
            }
        }

        private AnonymousClass219(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$22  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass22 extends Features {
        public /* synthetic */ AnonymousClass22() {
            this("SUPPORT_BACK_UP_SD_CARD_TO_ONE_DRIVE", 21);
        }

        public boolean getEnabling() {
            if (!Features.isPackageAvailable("com.microsoft.skydrive") || !Features.isPackageCompatible("com.microsoft.skydrive", 2026570000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass22(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$220  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass220 extends Features {
        public /* synthetic */ AnonymousClass220() {
            this("SUPPORT_BEST_FACE", 219);
        }

        public boolean getEnabling() {
            String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_BEST_FACE_MODE");
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || TextUtils.isEmpty(string)) {
                return false;
            }
            return true;
        }

        private AnonymousClass220(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$221  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass221 extends Features {
        public /* synthetic */ AnonymousClass221() {
            this("SUPPORT_REMASTER_FOCUS_ROI", MOCRLang.LAO);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER)) {
                return false;
            }
            return true;
        }

        private AnonymousClass221(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$222  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass222 extends Features {
        public /* synthetic */ AnonymousClass222() {
            this("IS_REQUEST_RAW_EXTERNAL_STORAGE_ENABLED", 221);
        }

        public boolean getEnabling() {
            return true;
        }

        private AnonymousClass222(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$223  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass223 extends Features {
        public /* synthetic */ AnonymousClass223() {
            this("SUPPORT_SHARED_STORAGE_USAGE", 222);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300100007);
        }

        private AnonymousClass223(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$224  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass224 extends Features {
        public /* synthetic */ AnonymousClass224() {
            this("SUPPORT_SHARED_WEB_LINK", 223);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300100007);
        }

        private AnonymousClass224(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$225  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass225 extends Features {
        public /* synthetic */ AnonymousClass225() {
            this("SUPPORT_LEADER_AUTHORITY_DELEGATION", 224);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300200000);
        }

        private AnonymousClass225(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$226  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass226 extends Features {
        public /* synthetic */ AnonymousClass226() {
            this("SUPPORT_SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE", 225);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300400000);
        }

        private AnonymousClass226(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$227  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass227 extends Features {
        public /* synthetic */ AnonymousClass227() {
            this("SUPPORT_TEXT_EXTRACTION_S_OS", 226);
        }

        public boolean getEnabling() {
            if (!SdkConfig.range(SdkConfig.GED.S, SdkConfig.GED.T) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_EAGLE_EYE")) {
                return false;
            }
            return true;
        }

        private AnonymousClass227(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$228  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass228 extends Features {
        public /* synthetic */ AnonymousClass228() {
            this("SUPPORT_TEXT_EXTRACTION_CMH_DETECTION", 227);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(705300000);
        }

        private AnonymousClass228(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$229  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass229 extends Features {
        public /* synthetic */ AnonymousClass229() {
            this("SUPPORT_SHARED_PERMISSION_POPUP", 228);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1300100023);
        }

        private AnonymousClass229(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$23  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass23 extends Features {
        public /* synthetic */ AnonymousClass23() {
            this("SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR", 22);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || Features.isEnabled(Features.IS_VERIZON_DEVICE) || !Features.isEnabled(Features.SUPPORT_CLOUD) || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE) || Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_MUM_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass23(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$230  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass230 extends Features {
        public /* synthetic */ AnonymousClass230() {
            this("SUPPORT_LOCATION", 229);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || Features.isEnabled(Features.SUPPORT_GOOGLE_PLAY_SERVICE)) {
                return true;
            }
            return false;
        }

        private AnonymousClass230(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$231  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass231 extends Features {
        public /* synthetic */ AnonymousClass231() {
            this("SUPPORT_BAIDU_CLOUD_NETDISK_DEEP_LINK", 230);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible(UrlLookup.getData("baidu"), 1623);
        }

        private AnonymousClass231(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$232  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass232 extends Features {
        public /* synthetic */ AnonymousClass232() {
            this("SUPPORT_HIDE_RULE", 231);
        }

        public boolean getEnabling() {
            return !Features.isEnabled(Features.IS_JDM);
        }

        private AnonymousClass232(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$233  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass233 extends Features {
        public /* synthetic */ AnonymousClass233() {
            this("SUPPORT_HIDE_RULE_PETS", 232);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_HIDE_RULE) || !ImageTaggerVersionHolder.isEnabled()) {
                return false;
            }
            return true;
        }

        private AnonymousClass233(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$234  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass234 extends Features {
        public /* synthetic */ AnonymousClass234() {
            this("IS_JDM", 233);
        }

        public boolean getEnabling() {
            return "jdm".equals(FloatingFeatures.getString("SEC_FLOATING_FEATURE_COMMON_CONFIG_DEVICE_MANUFACTURING_TYPE"));
        }

        private AnonymousClass234(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$235  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass235 extends Features {
        public /* synthetic */ AnonymousClass235() {
            this("SUPPORT_UNIFIED_PEOPLE_KEY", 234);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.GED.T);
        }

        private AnonymousClass235(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$236  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass236 extends Features {
        public /* synthetic */ AnonymousClass236() {
            this("SUPPORT_PEOPLE_FACE_SCORE", 235);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !SecMpVersionHolder.support(1313)) {
                return false;
            }
            return true;
        }

        private AnonymousClass236(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$237  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass237 extends Features {
        public /* synthetic */ AnonymousClass237() {
            this("SUPPORT_VIDEO_SEARCH", 236);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_MMFW_SUPPORT_VIDEOSERACH", "TRUE")) {
                return false;
            }
            return true;
        }

        private AnonymousClass237(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$238  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass238 extends Features {
        public /* synthetic */ AnonymousClass238() {
            this("SUPPORT_STORY_HIGHLIGHT_SAVE_VIA_VE", 237);
        }

        public boolean getEnabling() {
            if (!SdkConfig.lessThan(SdkConfig.SEM.U) || !Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2)) {
                return false;
            }
            return true;
        }

        private AnonymousClass238(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$239  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass239 extends Features {
        public /* synthetic */ AnonymousClass239() {
            this("SUPPORT_STORY_BADGE_ON_TAB", 238);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_STORY) || SdkConfig.atLeast(SdkConfig.GED.T)) {
                return false;
            }
            return true;
        }

        private AnonymousClass239(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$24  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass24 extends Features {
        public /* synthetic */ AnonymousClass24() {
            this("SUPPORT_ONE_DRIVE_PROMOTION", 23);
        }

        public boolean getEnabling() {
            if (!Features.isPackageAvailable("com.microsoft.skydrive") || !Features.isPackageCompatible("com.microsoft.skydrive", 2026920300)) {
                return false;
            }
            return true;
        }

        private AnonymousClass24(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$240  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass240 extends Features {
        public /* synthetic */ AnonymousClass240() {
            this("USE_SHARED_DOWNLOAD_FILE_VERIFY", 239);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1310000000);
        }

        private AnonymousClass240(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$241  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass241 extends Features {
        public /* synthetic */ AnonymousClass241() {
            this("SUPPORT_CMH_STORY_SA_TYPE", 240);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(802400000);
        }

        private AnonymousClass241(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$242  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass242 extends Features {
        public /* synthetic */ AnonymousClass242() {
            this("SUPPORT_CMH_STORY_DEFAULT_THEME", 241);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(813000000);
        }

        private AnonymousClass242(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$243  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass243 extends Features {
        public /* synthetic */ AnonymousClass243() {
            this("SUPPORT_FAVORITE_INDEX", 242);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1308);
        }

        private AnonymousClass243(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$244  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass244 extends Features {
        public /* synthetic */ AnonymousClass244() {
            this("SUPPORT_RECENT_BACKUP", 243);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1311);
        }

        private AnonymousClass244(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$245  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass245 extends Features {
        public /* synthetic */ AnonymousClass245() {
            this("SUPPORT_SEM_IMAGE_FILTER", 244);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.isEnabled(Features.IS_JDM) || Features.isEnabled(Features.IS_GED) || !Features.isEnabled(Features.SUPPORT_FILTER) || FloatingFeatures.getInt("SEC_FLOATING_FEATURE_CAMERA_CONFIG_VERSION_FILTER_PROVIDER") < 5 || !Features.isEnabled(Features.SUPPORT_HDR2SDR)) {
                return false;
            }
            return true;
        }

        private AnonymousClass245(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$246  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass246 extends Features {
        public /* synthetic */ AnonymousClass246() {
            this("SUPPORT_SEM_IMAGE_FILTER_V", 245);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_SEM_IMAGE_FILTER) || FloatingFeatures.getInt("SEC_FLOATING_FEATURE_CAMERA_CONFIG_VERSION_FILTER_PROVIDER") < 7) {
                return false;
            }
            return true;
        }

        private AnonymousClass246(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$247  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass247 extends Features {
        public /* synthetic */ AnonymousClass247() {
            this("SUPPORT_BGM_PICKER_UI", 246);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !Features.isEnabled(Features.SUPPORT_BGM_SERVICE) || Features.getPackageVersion("com.sec.android.app.ve.vebgm") < 241000000) {
                return false;
            }
            return true;
        }

        private AnonymousClass247(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$248  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass248 extends Features {
        public /* synthetic */ AnonymousClass248() {
            this("SUPPORT_HIGHLIGHT_STORY_SAVE", 247);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_EDITOR) || Features.isEnabled(Features.IS_JDM) || !Features.isEnabled(Features.SUPPORT_BGM_SERVICE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass248(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$249  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass249 extends Features {
        public /* synthetic */ AnonymousClass249() {
            this("SUPPORT_BGM_PICKER_WITH_CONTENT_PROVIDER", 248);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_BGM_PICKER_UI) || Features.getPackageVersion("com.sec.android.app.ve.vebgm") < 250200000) {
                return false;
            }
            return true;
        }

        private AnonymousClass249(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$25  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass25 extends Features {
        public /* synthetic */ AnonymousClass25() {
            this("SUPPORT_CREATE_MOVIE", 24);
        }

        public boolean getEnabling() {
            if (FloatingFeatures.getInt("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_STORYEDITOR") == 3) {
                return true;
            }
            return false;
        }

        private AnonymousClass25(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$250  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass250 extends Features {
        public /* synthetic */ AnonymousClass250() {
            this("USE_LOLLI_FILTER", 249);
        }

        public boolean getEnabling() {
            if (FloatingFeatures.getInt("SEC_FLOATING_FEATURE_CAMERA_CONFIG_VERSION_FILTER_PROVIDER") == 5) {
                return true;
            }
            return false;
        }

        private AnonymousClass250(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$251  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass251 extends Features {
        public /* synthetic */ AnonymousClass251() {
            this("SUPPORT_GO_TO_CAPTURED_PATH", 250);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !SecMpVersionHolder.support(1309)) {
                return false;
            }
            return true;
        }

        private AnonymousClass251(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$252  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass252 extends Features {
        public /* synthetic */ AnonymousClass252() {
            this("SUPPORT_MEITU", 251);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || !SdkConfig.atLeast(SdkConfig.GED.T)) {
                return false;
            }
            return true;
        }

        private AnonymousClass252(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$253  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass253 extends Features {
        public /* synthetic */ AnonymousClass253() {
            this("SUPPORT_MEITU_IMAGE_TO_IMAGE", 252);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_MEITU) || !SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
                return false;
            }
            return true;
        }

        private AnonymousClass253(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$254  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass254 extends Features {
        public /* synthetic */ AnonymousClass254() {
            this("SUPPORT_SOUND_PICKER", 253);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable("com.samsung.android.app.soundpicker");
        }

        private AnonymousClass254(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$255  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass255 extends Features {
        public /* synthetic */ AnonymousClass255() {
            this("SUPPORT_PPP_MENU", 254);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                return true;
            }
            if (!SecMpVersionHolder.support(1312)) {
                return false;
            }
            if (CameraVersionHolder.support(1310104000) || CameraVersionHolder.support("ppp_complete_notification")) {
                return true;
            }
            return false;
        }

        private AnonymousClass255(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$256  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass256 extends Features {
        public /* synthetic */ AnonymousClass256() {
            this("SUPPORT_PPP_V2", ScoverState.TYPE_NFC_SMART_COVER);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.SUPPORT_PPP_V3) || SdkConfig.atLeast(SdkConfig.SEM.U)) {
                return false;
            }
            return true;
        }

        private AnonymousClass256(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$257  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass257 extends Features {
        public /* synthetic */ AnonymousClass257() {
            this("SUPPORT_PPP_V3", 256);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                return true;
            }
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
                return false;
            }
            return true;
        }

        private AnonymousClass257(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$258  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass258 extends Features {
        public /* synthetic */ AnonymousClass258() {
            this("SUPPORT_COPY_PASTE_EFFECTS", 257);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 322412000);
        }

        private AnonymousClass258(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$259  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass259 extends Features {
        public /* synthetic */ AnonymousClass259() {
            this("SUPPORT_PPP_DRAFT", 258);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !SecMpVersionHolder.support(1402)) {
                return false;
            }
            return true;
        }

        private AnonymousClass259(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$26  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass26 extends Features {
        public /* synthetic */ AnonymousClass26() {
            this("SUPPORT_CREATE_MOVIE_V2", 25);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                return EditorPluginHolder.contains("storyvideoeditor");
            }
            if (FloatingFeatures.getInt("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_STORYEDITOR") == 4) {
                return true;
            }
            return false;
        }

        private AnonymousClass26(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$260  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass260 extends Features {
        public /* synthetic */ AnonymousClass260() {
            this("SUPPORT_SAVE_AS_STICKER", 259);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 322324000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass260(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$261  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass261 extends Features {
        public /* synthetic */ AnonymousClass261() {
            this("SUPPORT_CLIPPED_IMAGE_EDIT", 260);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 330614000);
        }

        private AnonymousClass261(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$262  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass262 extends Features {
        public /* synthetic */ AnonymousClass262() {
            this("SUPP0RT_DOCUMENT_SCAN", ASVLOFFSCREEN.ASVL_PAF_RGB16_R5G6B5);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_CAMERA_CONFIG_VENDOR_LIB_INFO", "smart_scan.samsung.v2")) {
                return false;
            }
            return true;
        }

        private AnonymousClass262(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$263  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass263 extends Features {
        public /* synthetic */ AnonymousClass263() {
            this("SUPPORT_LIGHTROOM", 262);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_CAMERA_SUPPORT_GALLERY_LR")) {
                return false;
            }
            return true;
        }

        private AnonymousClass263(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$264  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass264 extends Features {
        public /* synthetic */ AnonymousClass264() {
            this("SUPPORT_C2PA", 263);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GENAI_SUPPORT_C2PA");
        }

        private AnonymousClass264(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$265  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass265 extends Features {
        public /* synthetic */ AnonymousClass265() {
            this("SUPPORT_C2PA_SPEC_2_2", 264);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20261);
        }

        private AnonymousClass265(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$266  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass266 extends Features {
        public /* synthetic */ AnonymousClass266() {
            this("SUPPORT_C2PA_DB", 265);
        }

        public boolean getEnabling() {
            if (!SecMpVersionHolder.support(1510) || !Features.isEnabled(Features.SUPPORT_C2PA)) {
                return false;
            }
            return true;
        }

        private AnonymousClass266(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$267  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass267 extends Features {
        public /* synthetic */ AnonymousClass267() {
            this("SUPPORT_INSTANT_SEARCH", 266);
        }

        public boolean getEnabling() {
            if (!ConfigAiVersion.support(20261) || FloatingFeatures.getInt("SEC_FLOATING_FEATURE_SAMSUNG_SEARCH_SEMANTIC_SEARCH_VERSION") < 510) {
                return false;
            }
            return true;
        }

        private AnonymousClass267(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$268  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass268 extends Features {
        public /* synthetic */ AnonymousClass268() {
            this("SUPPORT_SCREEN_SHOT_CATEGORY", 267);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20261);
        }

        private AnonymousClass268(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$269  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass269 extends Features {
        public /* synthetic */ AnonymousClass269() {
            this("SUPPORT_OBJECT_ERASER", 268);
        }

        public boolean getEnabling() {
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME);
            if (applicationMetaData == null || !applicationMetaData.getBoolean("support_object_eraser", false)) {
                return false;
            }
            if (applicationMetaData.getBoolean("com.samsung.aimodelprovider.objectremovalmodels", false) || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GENAI_SUPPORT_OBJECT_ERASER")) {
                return true;
            }
            return false;
        }

        private AnonymousClass269(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$27  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass27 extends Features {
        public /* synthetic */ AnonymousClass27() {
            this("SUPPORT_CREATE_HIGHLIGHT_REEL", 26);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("highlight");
        }

        private AnonymousClass27(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$270  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass270 extends Features {
        public /* synthetic */ AnonymousClass270() {
            this("SUPP0RT_PASTE_CLIPBOARD_IMAGE", 269);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 330614000);
        }

        private AnonymousClass270(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$271  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass271 extends Features {
        public /* synthetic */ AnonymousClass271() {
            this("SUPPORT_PPP_BLENDING", 270);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.T_MR5);
        }

        private AnonymousClass271(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$272  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass272 extends Features {
        public /* synthetic */ AnonymousClass272() {
            this("SUPPORT_SEARCH_CLUSTER", 271);
        }

        public boolean getEnabling() {
            if (!ScsVersionHolder.isEnabled() || !SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
                return false;
            }
            return true;
        }

        private AnonymousClass272(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$273  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass273 extends Features {
        public /* synthetic */ AnonymousClass273() {
            this("IS_BRAND_NAME_ONLY_ASCII", 272);
        }

        public boolean getEnabling() {
            return Pattern.matches("[\\u0000-\\u007F]+", FloatingFeatures.getString("SEC_FLOATING_FEATURE_SETTINGS_CONFIG_BRAND_NAME"));
        }

        private AnonymousClass273(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$274  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass274 extends Features {
        public /* synthetic */ AnonymousClass274() {
            this("SUPPORT_PHOTO_HDR", 273);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_PHOTOHDR")) {
                return false;
            }
            return true;
        }

        private AnonymousClass274(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$275  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass275 extends Features {
        public /* synthetic */ AnonymousClass275() {
            this("SUPPORT_LONG_EXPOSURE_EFFECT", 274);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_MMFW_SUPPORT_LONGEXPOSURE_EFFECT", "TRUE")) {
                return false;
            }
            return true;
        }

        private AnonymousClass275(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$276  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass276 extends Features {
        public /* synthetic */ AnonymousClass276() {
            this("SUPPORT_INSTANT_SLOW_MO", 275);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_CAMERA_SUPPORT_AIFRC");
        }

        private AnonymousClass276(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$277  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass277 extends Features {
        public /* synthetic */ AnonymousClass277() {
            this("SUPPORT_LOG_VIDEO_TIPS", 276);
        }

        public boolean getEnabling() {
            if (!SdkConfig.lessThan(SdkConfig.SEM.B_MR5) || !Features.isEnabled(Features.SUPPORT_COLOR_CORRECT) || Features.isUnpackDevice()) {
                return false;
            }
            return true;
        }

        private AnonymousClass277(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$278  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass278 extends Features {
        public /* synthetic */ AnonymousClass278() {
            this("SUPPORT_INSTANT_SLOW_MO_TIPS", 277);
        }

        public boolean getEnabling() {
            if (!Features.SUPPORT_INSTANT_SLOW_MO.isEnabled() || Features.isUnpackDevice()) {
                return false;
            }
            return true;
        }

        private AnonymousClass278(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$279  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass279 extends Features {
        public /* synthetic */ AnonymousClass279() {
            this("SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET", 278);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO)) {
                try {
                    Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(Component$PackageName.SHARE_SHEET_PACKAGE);
                    if (applicationMetaData == null || !applicationMetaData.getBoolean("com.samsung.android.intentresolver.support_slow_mo", false)) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                }
            }
            return false;
        }

        private AnonymousClass279(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$28  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass28 extends Features {
        public /* synthetic */ AnonymousClass28() {
            this("SUPPORT_VIDEO_STUDIO", 27);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("projectpicker");
        }

        private AnonymousClass28(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$280  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass280 extends Features {
        public /* synthetic */ AnonymousClass280() {
            this("IS_ENABLED_REDUCE_TRANSPARENCY", 279);
        }

        public boolean getEnabling() {
            return SeApiCompat.isReducedTransparency(Features.getAppContext());
        }

        private AnonymousClass280(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$281  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass281 extends Features {
        public /* synthetic */ AnonymousClass281() {
            this("SUPPORT_PE_GEN_EDIT", Encode.BitRate.VIDEO_QCIF_BITRATE);
        }

        public boolean getEnabling() {
            Bundle applicationMetaData;
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !Features.isEnabled(Features.SUPPORT_NATIVE_AI) || !PackageMonitorCompat.getInstance().isPackageInstalled(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME) || (applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) == null || !applicationMetaData.getBoolean("support_generative_edit", false)) {
                return false;
            }
            return true;
        }

        private AnonymousClass281(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$282  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass282 extends Features {
        public /* synthetic */ AnonymousClass282() {
            this("SUPPORT_AI_SETTINGS", 281);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_PE_GEN_EDIT) || !ConfigAiVersion.support(20242)) {
                return false;
            }
            return true;
        }

        private AnonymousClass282(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$283  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass283 extends Features {
        public /* synthetic */ AnonymousClass283() {
            this("SUPPORT_AI_PROCESSING_EFFECT", 282);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !ConfigAiVersion.support(20242)) {
                return false;
            }
            return true;
        }

        private AnonymousClass283(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$284  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass284 extends Features {
        public /* synthetic */ AnonymousClass284() {
            this("SUPPORT_AWESOME_INTELLIGENT_FEATURES", 283);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || FloatingFeatures.getInt("SEC_FLOATING_FEATURE_COMMON_CONFIG_AWESOME_INTELLIGENCE") < 202501) {
                return false;
            }
            return true;
        }

        private AnonymousClass284(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$285  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass285 extends Features {
        public /* synthetic */ AnonymousClass285() {
            this("SUPPORT_IMAGE_TAGGER", 284);
        }

        public boolean getEnabling() {
            return ImageTaggerVersionHolder.isEnabled();
        }

        private AnonymousClass285(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$286  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass286 extends Features {
        public /* synthetic */ AnonymousClass286() {
            this("SUPPORT_OCR_ENGINE", 285);
        }

        public boolean getEnabling() {
            return OcrEngineVersionHolder.isEnabled();
        }

        private AnonymousClass286(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$287  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass287 extends Features {
        public /* synthetic */ AnonymousClass287() {
            this("SUPPORT_SEMANTIC_SEARCH", 286);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MSCH_SUPPORT_NLSEARCH")) {
                return false;
            }
            return true;
        }

        private AnonymousClass287(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$288  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass288 extends Features {
        public /* synthetic */ AnonymousClass288() {
            this("SUPPORT_STORY_NOTIFICATION", 287);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.USE_CMH) || !SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !CmhVersionHolder.support(910200000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass288(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$289  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass289 extends Features {
        public /* synthetic */ AnonymousClass289() {
            this("SUPPORT_CMH_PROVIDER_PERMISSION", 288);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || !Features.isEnabled(Features.USE_CMH) || !CmhVersionHolder.support(913400000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass289(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$29  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass29 extends Features {
        public /* synthetic */ AnonymousClass29() {
            this("SUPPORT_MULTI_VIDEO_EDIT", 28);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("multiedit");
        }

        private AnonymousClass29(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$290  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass290 extends Features {
        public /* synthetic */ AnonymousClass290() {
            this("SUPPORT_DUAL_REC", 289);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.U);
        }

        private AnonymousClass290(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$291  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass291 extends Features {
        public /* synthetic */ AnonymousClass291() {
            this("SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS", 290);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !ImageTaggerVersionHolder.support(700)) {
                return false;
            }
            return true;
        }

        private AnonymousClass291(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$292  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass292 extends Features {
        public /* synthetic */ AnonymousClass292() {
            this("SUPPORT_DOWNLOAD_LANGUAGE_PACK", 291);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass292(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$293  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass293 extends Features {
        public /* synthetic */ AnonymousClass293() {
            this("SUPPORT_PET_CLUSTER", 292);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1) && SecMpVersionHolder.support(1408) && ScsVersionHolder.support(310020200)) {
                String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_PET_CLUSTER_VERSION");
                if (TextUtils.isEmpty(string) || "none".equalsIgnoreCase(string)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        private AnonymousClass293(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$294  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass294 extends Features {
        public /* synthetic */ AnonymousClass294() {
            this("SUPPORT_SEARCH_KEYWORD_FROM_BIXBY_OD", 293);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !Features.isPackageInstalled("com.samsung.android.bixby.odner")) {
                return false;
            }
            return true;
        }

        private AnonymousClass294(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$295  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass295 extends Features {
        public /* synthetic */ AnonymousClass295() {
            this("SUPPORT_LLM", 294);
        }

        public boolean getEnabling() {
            String str;
            String string;
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                str = "com.samsung.android.offline.languagemodel.chn";
            } else {
                str = "com.samsung.android.offline.languagemodel";
            }
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(str);
            if ((applicationMetaData == null || (string = applicationMetaData.getString(str.concat(".FUNCTION_INFO"))) == null || !string.contains("QueryProcessing")) && FloatingFeatures.getInt("SEC_FLOATING_FEATURE_SAMSUNG_SEARCH_SEMANTIC_SEARCH_VERSION") < 510) {
                return false;
            }
            return true;
        }

        private AnonymousClass295(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$296  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass296 extends Features {
        public /* synthetic */ AnonymousClass296() {
            this("SUPPORT_BIXBY_UTT_KEYWORD_SEARCH", 295);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B) || !Features.isEnabled(Features.SUPPORT_LLM)) {
                return false;
            }
            return true;
        }

        private AnonymousClass296(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$297  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass297 extends Features {
        public /* synthetic */ AnonymousClass297() {
            this("SUPPORT_BIXBY_LOCAL_TIME_SEARCH", 296);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400102200);
        }

        private AnonymousClass297(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$298  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass298 extends Features {
        public /* synthetic */ AnonymousClass298() {
            this("SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON", 297);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR1) || !SmartSuggestionsVersionHolder.support(610000128)) {
                return false;
            }
            return true;
        }

        private AnonymousClass298(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$299  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass299 extends Features {
        public /* synthetic */ AnonymousClass299() {
            this("IS_RDU_MODE", 298);
        }

        public boolean getEnabling() {
            return SeApiCompat.isShopDemoMode(Features.getAppContext());
        }

        private AnonymousClass299(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends Features {
        public /* synthetic */ AnonymousClass3() {
            this("USE_SEC_MP", 2);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.GED.Q) || Features.isEnabled(Features.IS_GED)) {
                return false;
            }
            return true;
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$30  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass30 extends Features {
        public /* synthetic */ AnonymousClass30() {
            this("SUPPORT_CREATE_COLLAGE", 29);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.N_MR1);
        }

        private AnonymousClass30(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$300  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass300 extends Features {
        public /* synthetic */ AnonymousClass300() {
            this("SUPPORT_AUTO_BLOCKER", 299);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !Features.isPackageInstalled("com.samsung.android.rampart")) {
                return false;
            }
            return true;
        }

        private AnonymousClass300(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$301  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass301 extends Features {
        public /* synthetic */ AnonymousClass301() {
            this("IS_SHARED_ALBUM_BLOCKED", StatusCodes.INPUT_MISSING);
        }

        public boolean getEnabling() {
            return SeApiCompat.isSharedAlbumBlocked(Features.getAppContext());
        }

        private AnonymousClass301(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$302  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass302 extends Features {
        public /* synthetic */ AnonymousClass302() {
            this("SUPPORT_SCS_SEARCH_CHECK_EXTENDED_SUGGESTED_KEYWORD_FEATURE", 301);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !ScsVersionHolder.support(320000000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass302(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$303  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass303 extends Features {
        public /* synthetic */ AnonymousClass303() {
            this("SUPPORT_NEW_HIDE_SCENE_SELECTION", CommonStatusCodes.DEVICE_OUT_OF_STORAGE);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !CmhVersionHolder.support(950200000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass303(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$304  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass304 extends Features {
        public /* synthetic */ AnonymousClass304() {
            this("SUPPORT_STORY_REORDER", 303);
        }

        public boolean getEnabling() {
            if (!CmhVersionHolder.support(950300000) || !PocFeatures.isEnabled(PocFeatures.StoryContentsReorder)) {
                return false;
            }
            return true;
        }

        private AnonymousClass304(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$305  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass305 extends Features {
        public /* synthetic */ AnonymousClass305() {
            this("SET_AS_CHOOSER_BY_INTENT_FILTER", Status.NOT_MODIFIED);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.U_MR5);
        }

        private AnonymousClass305(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$306  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass306 extends Features {
        public /* synthetic */ AnonymousClass306() {
            this("SUPPORT_MULTI_KEYWORD_SEARCH", 305);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !ScsVersionHolder.support(320036200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass306(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$307  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass307 extends Features {
        public /* synthetic */ AnonymousClass307() {
            this("SUPPORT_SUGGEST_RELATIONSHIP", 306);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !ScsVersionHolder.support(320038200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass307(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$308  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass308 extends Features {
        public /* synthetic */ AnonymousClass308() {
            this("SUPPORT_SUGGEST_OCR", 307);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !ScsVersionHolder.support(320039200)) {
                return false;
            }
            return true;
        }

        private AnonymousClass308(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$309  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass309 extends Features {
        public /* synthetic */ AnonymousClass309() {
            this("SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP", 308);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !ImageTaggerVersionHolder.support(801)) {
                return false;
            }
            return true;
        }

        private AnonymousClass309(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$31  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass31 extends Features {
        public /* synthetic */ AnonymousClass31() {
            this("SUPPORT_PHOTO_EDIT", 30);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME);
        }

        private AnonymousClass31(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$310  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass310 extends Features {
        public /* synthetic */ AnonymousClass310() {
            this("SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2", 309);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !ImageTaggerVersionHolder.support(901)) {
                return false;
            }
            return true;
        }

        private AnonymousClass310(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$311  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass311 extends Features {
        public /* synthetic */ AnonymousClass311() {
            this("SUPPORT_CAMERA_USB_STORAGE", 310);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.V);
        }

        private AnonymousClass311(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$312  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass312 extends Features {
        public /* synthetic */ AnonymousClass312() {
            this("SUPPORT_RECOVER_COLLECT", 311);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass312(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$313  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass313 extends Features {
        public /* synthetic */ AnonymousClass313() {
            this("SUPPORT_PDC_SEARCH", 312);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
                return FloatingFeatures.getBoolean(Constants.SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_PERSONALIZED_DATA_CORE);
            }
            return false;
        }

        private AnonymousClass313(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$314  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass314 extends Features {
        public /* synthetic */ AnonymousClass314() {
            this("SUPPORT_PDC_RELATIONSHIP", 313);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_PDC_SEARCH) || !Features.isEnabled(Features.SUPPORT_SCS_SEARCH)) {
                return false;
            }
            return true;
        }

        private AnonymousClass314(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$315  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass315 extends Features {
        public /* synthetic */ AnonymousClass315() {
            this("SUPPORT_PDC_CONTACT_LINK", 314);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || !Features.isEnabled(Features.SUPPORT_PDC_SEARCH) || !ConfigAiVersion.support(20261) || !SecMpVersionHolder.support(1610000000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass315(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$316  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass316 extends Features {
        public /* synthetic */ AnonymousClass316() {
            this("SUPPORT_COLOR_CORRECT", 315);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_LOG_CORRECT_COLOR");
        }

        private AnonymousClass316(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$317  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass317 extends Features {
        public /* synthetic */ AnonymousClass317() {
            this("SUPPORT_COLOR_CORRECT_8K", 316);
        }

        public boolean getEnabling() {
            return !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_UNSUPPORT_TRANSCODE_8K10BIT");
        }

        private AnonymousClass317(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$318  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass318 extends Features {
        public /* synthetic */ AnonymousClass318() {
            this("SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA", 317);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.V)) {
                String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_SAIV_CONFIG_AI_REVITAL_VERSION");
                try {
                    String[] split = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    if (split.length <= 1 || Integer.parseInt(split[1]) <= 2) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    StringBuilder sb2 = new StringBuilder("checking ");
                    sb2.append(name());
                    sb2.append(" {");
                    sb2.append(string);
                    sb2.append("} failed. e=");
                    a.s(e, sb2, Features.TAG);
                }
            }
            return false;
        }

        private AnonymousClass318(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$319  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass319 extends Features {
        public /* synthetic */ AnonymousClass319() {
            this("SUPPORT_SMART_LASSO_AND_ERASER", 318);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass319(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$32  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass32 extends Features {
        public /* synthetic */ AnonymousClass32() {
            this("SUPPORT_CONTACT_US", 31);
        }

        public boolean getEnabling() {
            return Features.isPackageCompatible("com.samsung.android.voc", 170001000);
        }

        private AnonymousClass32(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$320  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass320 extends Features {
        public /* synthetic */ AnonymousClass320() {
            this("SUPPORT_ON_DEMAND_STORY", 319);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_NATIVE_AI_V2) || !Features.isEnabled(Features.SUPPORT_SEMANTIC_SEARCH) || !Features.isEnabled(Features.SUPPORT_LLM) || Features.isEnabled(Features.IS_MUM_MODE) || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass320(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$321  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass321 extends Features {
        public /* synthetic */ AnonymousClass321() {
            this("SUPPORT_NATIVE_AI", ThumbKind.MEDIUM_KIND_SIZE);
        }

        public boolean getEnabling() {
            return !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_COMMON_DISABLE_NATIVE_AI");
        }

        private AnonymousClass321(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$322  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass322 extends Features {
        public /* synthetic */ AnonymousClass322() {
            this("SUPPORT_NATIVE_AI_V2", 321);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20251);
        }

        private AnonymousClass322(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$323  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass323 extends Features {
        public /* synthetic */ AnonymousClass323() {
            this("SUPPORT_FACE_ENGINE_SCORE_MAX_15", 322);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_PEOPLE_FACE_SCORE) || !FaceEngineVersionHolder.support(6)) {
                return false;
            }
            return true;
        }

        private AnonymousClass323(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$324  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass324 extends Features {
        public /* synthetic */ AnonymousClass324() {
            this("SUPPORT_FACE_ENGINE", 323);
        }

        public boolean getEnabling() {
            return FaceEngineVersionHolder.isEnabled();
        }

        private AnonymousClass324(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$325  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass325 extends Features {
        public /* synthetic */ AnonymousClass325() {
            this("SUPPORT_LOCAL_TIME", 324);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1514);
        }

        private AnonymousClass325(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$326  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass326 extends Features {
        public /* synthetic */ AnonymousClass326() {
            this("SUPPORT_USB_STORAGE", 325);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1610200000);
        }

        private AnonymousClass326(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$327  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass327 extends Features {
        public /* synthetic */ AnonymousClass327() {
            this("SUPPORT_FACES_GROUP", 326);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1610400000);
        }

        private AnonymousClass327(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$328  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass328 extends Features {
        public /* synthetic */ AnonymousClass328() {
            this("SUPPORT_AUDIO_ERASER", 327);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_AUDIO_CONFIG_MULTISOURCE_SEPARATOR", "SourceSeparator") || !ConfigAiVersion.support(20251)) {
                return false;
            }
            return true;
        }

        private AnonymousClass328(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$329  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass329 extends Features {
        public /* synthetic */ AnonymousClass329() {
            this("SUPPORT_AUDIO_ERASER_IN_GALLERY", 328);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_AUDIO_CONFIG_MULTISOURCE_SEPARATOR", "SourceSeparator") || !ConfigAiVersion.support(20253)) {
                return false;
            }
            return true;
        }

        private AnonymousClass329(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$33  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass33 extends Features {
        public /* synthetic */ AnonymousClass33() {
            this("SUPPORT_SHARE_VIA", 32);
        }

        public boolean getEnabling() {
            return true;
        }

        private AnonymousClass33(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$330  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass330 extends Features {
        public /* synthetic */ AnonymousClass330() {
            this("SUPPORT_MOTION_CLIPPER", 329);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_NATIVE_AI) || TextUtils.isEmpty(FloatingFeatures.getString("SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE"))) {
                return false;
            }
            return true;
        }

        private AnonymousClass330(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$331  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass331 extends Features {
        public /* synthetic */ AnonymousClass331() {
            this("SUPPORT_SMART_SUGGESTION_APP_SUB_MENU", 330);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || !SmartSuggestionsVersionHolder.support(700128000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass331(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$332  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass332 extends Features {
        public /* synthetic */ AnonymousClass332() {
            this("SUPPORT_TOP_RESULT_SEARCH", 331);
        }

        public boolean getEnabling() {
            return ScsVersionHolder.support(400103200);
        }

        private AnonymousClass332(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$333  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass333 extends Features {
        public /* synthetic */ AnonymousClass333() {
            this("SUPPORT_NEW_SAMSUNG_CLOUD", 332);
        }

        private int getContentSyncVersion() {
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.samsung.android.scloud");
            if (applicationMetaData != null) {
                return applicationMetaData.getInt("content_sync_version", -1);
            }
            return -1;
        }

        public boolean getEnabling() {
            if (getContentSyncVersion() >= 20000000) {
                return true;
            }
            return false;
        }

        private AnonymousClass333(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$334  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass334 extends Features {
        public /* synthetic */ AnonymousClass334() {
            this("SUPPORT_AI_EDIT_SUGGESTIONS", 333);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20242);
        }

        private AnonymousClass334(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$335  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass335 extends Features {
        public /* synthetic */ AnonymousClass335() {
            this("PRIVACY_DISPLAY", 334);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass335(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$336  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass336 extends Features {
        public /* synthetic */ AnonymousClass336() {
            this("PRIVATE_ALBUM", 335);
        }

        public boolean getEnabling() {
            if (!SeApiCompat.isMyUserIdAsUserOwner() || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_AFW_MODE)) {
                return false;
            }
            return true;
        }

        private AnonymousClass336(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$337  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass337 extends Features {
        public /* synthetic */ AnonymousClass337() {
            this("SUPPORT_VEX_DOCUMENT_SCAN", 336);
        }

        public boolean getEnabling() {
            String str;
            if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5) && ConfigAiVersion.support(20261) && FloatingFeatures.contains("SEC_FLOATING_FEATURE_CAMERA_DOCUMENTSCAN_SOLUTIONS", "SCANNER_FILTER")) {
                Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.samsung.android.app.vex.scanner");
                if (applicationMetaData != null) {
                    str = applicationMetaData.getString("com.samsung.android.app.vex.scanner.features");
                } else {
                    str = null;
                }
                if (TextUtils.isEmpty(str) || !str.contains("SingleEdit")) {
                    return false;
                }
                return true;
            }
            return false;
        }

        private AnonymousClass337(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$338  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass338 extends Features {
        public /* synthetic */ AnonymousClass338() {
            this("SUPPORT_EVENT_FACET", 337);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || !Features.isEnabled(Features.SUPPORT_PDC_SEARCH)) {
                return false;
            }
            return true;
        }

        private AnonymousClass338(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$339  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass339 extends Features {
        public /* synthetic */ AnonymousClass339() {
            this("END_OF_FEATURES", 338);
        }

        public boolean getEnabling() {
            return false;
        }

        private AnonymousClass339(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$34  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass34 extends Features {
        public /* synthetic */ AnonymousClass34() {
            this("SUPPORT_SHARING", 33);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.P);
        }

        private AnonymousClass34(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$35  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass35 extends Features {
        public /* synthetic */ AnonymousClass35() {
            this("SUPPORT_SHARING_SERVICE", 34);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_SHARING) || !MdeVersionHolder.support(420000020)) {
                return false;
            }
            return true;
        }

        private AnonymousClass35(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$36  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass36 extends Features {
        public /* synthetic */ AnonymousClass36() {
            this("SUPPORT_FAMILY_ACCOUNT_PROVIDER", 35);
        }

        public boolean getEnabling() {
            try {
                Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME);
                if (applicationMetaData == null || applicationMetaData.getFloat("FamilyGroupProviderVersion", 0.0f) < 1.0f) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
                return false;
            }
        }

        private AnonymousClass36(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$37  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass37 extends Features {
        public /* synthetic */ AnonymousClass37() {
            this("SUPPORT_FAMILY_SHARED_ALBUM", 36);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1311000017);
        }

        private AnonymousClass37(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$38  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass38 extends Features {
        public /* synthetic */ AnonymousClass38() {
            this("SUPPORT_FAMILY_SHARED_SUGGEST", 37);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !CmhVersionHolder.support(810500000) || !Features.isPackageAvailable("com.samsung.faceservice")) {
                return false;
            }
            return true;
        }

        private AnonymousClass38(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$39  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass39 extends Features {
        public /* synthetic */ AnonymousClass39() {
            this("SUPPORT_FAMILY_SHARED_ITEM_STATUS", 38);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1311100010);
        }

        private AnonymousClass39(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends Features {
        public /* synthetic */ AnonymousClass4() {
            this("USE_NEWMP", 3);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.GED.P) || Features.isEnabled(Features.IS_GED)) {
                return false;
            }
            return true;
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$40  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass40 extends Features {
        public /* synthetic */ AnonymousClass40() {
            this("SUPPORT_CREATOR", 39);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1311100022);
        }

        private AnonymousClass40(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$41  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass41 extends Features {
        public /* synthetic */ AnonymousClass41() {
            this("SUPPORT_FAMILY_SHARED_TRASH", 40);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1311000021);
        }

        private AnonymousClass41(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$42  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass42 extends Features {
        public /* synthetic */ AnonymousClass42() {
            this("SUPPORT_FAMILY_SHARED_EDIT", 41);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.S) || !MdeVersionHolder.support(1311000017)) {
                return false;
            }
            return true;
        }

        private AnonymousClass42(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$43  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass43 extends Features {
        public /* synthetic */ AnonymousClass43() {
            this("SUPPORT_FAMILY_SHARED_EDIT_PHOTO", 42);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
                return false;
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.supportSharedAlbumEdit(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) {
                return true;
            }
            return false;
        }

        private AnonymousClass43(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$44  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass44 extends Features {
        public /* synthetic */ AnonymousClass44() {
            this("SUPPORT_FAMILY_SHARED_EDIT_GIF", 43);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
                return false;
            }
            if ((!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !Features.isPackageCompatible(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, 321208000)) && !Features.supportSharedAlbumEdit(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) {
                return false;
            }
            return true;
        }

        private AnonymousClass44(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$45  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass45 extends Features {
        public /* synthetic */ AnonymousClass45() {
            this("SUPPORT_FAMILY_SHARED_EDIT_VIDEO", 44);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
                return false;
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.supportSharedAlbumEdit("com.sec.android.app.vepreload")) {
                return true;
            }
            return false;
        }

        private AnonymousClass45(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$46  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass46 extends Features {
        public /* synthetic */ AnonymousClass46() {
            this("SUPPORT_FAMILY_SHARED_EDIT_LITE_VIDEO", 45);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
                return false;
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.supportSharedAlbumEdit("com.samsung.app.newtrim")) {
                return true;
            }
            return false;
        }

        private AnonymousClass46(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$47  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass47 extends Features {
        public /* synthetic */ AnonymousClass47() {
            this("SUPPORT_SHARED_ALBUM_IN_AFW_GALAXY_TO_GO_MODE", 46);
        }

        public boolean getEnabling() {
            DevicePolicyManager devicePolicyManager;
            if (!MdeVersionHolder.support(1311000019) || !Features.isPackageInstalled("com.samsung.android.galaxytogo") || (devicePolicyManager = (DevicePolicyManager) Features.getAppContext().getSystemService("device_policy")) == null || !devicePolicyManager.isDeviceOwnerApp("com.samsung.android.galaxytogo")) {
                return false;
            }
            return true;
        }

        private AnonymousClass47(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$48  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass48 extends Features {
        public /* synthetic */ AnonymousClass48() {
            this("SUPPORT_INVITE_FROM_SHARE_LINK", 47);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !MdeVersionHolder.support(1311000017)) {
                return false;
            }
            return true;
        }

        private AnonymousClass48(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$49  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass49 extends Features {
        public /* synthetic */ AnonymousClass49() {
            this("SUPPORT_PRE_THUMB_BEFORE_UPLOADING", 48);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(1365000003);
        }

        private AnonymousClass49(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends Features {
        public /* synthetic */ AnonymousClass5() {
            this("USE_CMH", 4);
        }

        public boolean getEnabling() {
            if ((!Features.isPackageAvailable("com.samsung.cmh") || Features.isEnabled(Features.IS_GED)) && !Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return false;
            }
            return true;
        }

        private AnonymousClass5(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$50  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass50 extends Features {
        public /* synthetic */ AnonymousClass50() {
            this("SUPPORT_SORT_SHARINGS", 49);
        }

        public boolean getEnabling() {
            return MdeVersionHolder.support(MdeVersionHolder.SORT_SHARINGS);
        }

        private AnonymousClass50(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$51  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass51 extends Features {
        public /* synthetic */ AnonymousClass51() {
            this("SUPPORT_SUGGESTIONS", 50);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.USE_CMH)) {
                return false;
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5)) {
                if (Features.isPackageInstalled("com.samsung.storyservice")) {
                    return true;
                }
                return false;
            } else if (Features.isPackageInstalled("com.samsung.ipservice")) {
                return true;
            } else {
                return false;
            }
        }

        private AnonymousClass51(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$52  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass52 extends Features {
        public /* synthetic */ AnonymousClass52() {
            this("SUPPORT_STORY", 51);
        }

        public boolean getEnabling() {
            return Features.isEnabled(Features.USE_CMH);
        }

        private AnonymousClass52(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$53  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass53 extends Features {
        public /* synthetic */ AnonymousClass53() {
            this("SUPPORT_STORY_COVER", 52);
        }

        public boolean getEnabling() {
            return CmhVersionHolder.support(313200000);
        }

        private AnonymousClass53(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$54  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass54 extends Features {
        public /* synthetic */ AnonymousClass54() {
            this("SUPPORT_AUTO_UPDATING_ALBUM", 53);
        }

        public boolean getEnabling() {
            if (!CmhVersionHolder.support(803000000) || !Features.isPackageAvailable("com.samsung.faceservice")) {
                return false;
            }
            return true;
        }

        private AnonymousClass54(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$55  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass55 extends Features {
        public /* synthetic */ AnonymousClass55() {
            this("SUPPORT_AUTO_UPDATING_ALBUM_SUGGESTION_RULE", 54);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !CmhVersionHolder.support(810500000) || !Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
                return false;
            }
            return true;
        }

        private AnonymousClass55(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$56  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass56 extends Features {
        public /* synthetic */ AnonymousClass56() {
            this("SUPPORT_PET_ON_AUTO_ALBUM", 55);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM) || !Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
                return false;
            }
            return true;
        }

        private AnonymousClass56(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$57  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass57 extends Features {
        public /* synthetic */ AnonymousClass57() {
            this("SUPPORT_AUTO_CREATE_STORY", 56);
        }

        public boolean getEnabling() {
            if (SdkConfig.lessThan(SdkConfig.GED.T) || CmhVersionHolder.support(1001000000)) {
                return true;
            }
            return false;
        }

        private AnonymousClass57(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$58  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass58 extends Features {
        public /* synthetic */ AnonymousClass58() {
            this("SUPPORT_CLUSTER_TABLE", 57);
        }

        public boolean getEnabling() {
            if (SecMpVersionHolder.support(ErrorCodeConvertor.AGENT_DATABASE_ERROR) || Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return true;
            }
            return false;
        }

        private AnonymousClass58(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$59  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass59 extends Features {
        public /* synthetic */ AnonymousClass59() {
            this("SUPPORT_MP_MEDIA_CACHE", 58);
        }

        public boolean getEnabling() {
            if (Features.isEnabled(Features.SUPPORT_MP_MEDIA_CACHE_TABLE) || !SecMpVersionHolder.support(1403)) {
                return false;
            }
            return true;
        }

        private AnonymousClass59(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends Features {
        public /* synthetic */ AnonymousClass6() {
            this("CMH_TO_MP_MIGRATION", 5);
        }

        public boolean getEnabling() {
            if (!SecMpVersionHolder.support(1610400000) || !CmhVersionHolder.support(1150100000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass6(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$60  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass60 extends Features {
        public /* synthetic */ AnonymousClass60() {
            this("SUPPORT_MP_MEDIA_CACHE_TABLE", 59);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1501);
        }

        private AnonymousClass60(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$61  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass61 extends Features {
        public /* synthetic */ AnonymousClass61() {
            this("SUPPORT_MP_CAPTURE_FRAMERATE", 60);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1404);
        }

        private AnonymousClass61(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$62  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass62 extends Features {
        public /* synthetic */ AnonymousClass62() {
            this("SUPPORT_MP_CAM_MODEL", 61);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1407);
        }

        private AnonymousClass62(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$63  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass63 extends Features {
        public /* synthetic */ AnonymousClass63() {
            this("SUPPORT_MP_MOTION_PHOTO_VIEW_MODE", 62);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(1504);
        }

        private AnonymousClass63(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$64  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass64 extends Features {
        public /* synthetic */ AnonymousClass64() {
            this("SUPPORT_MP_ADDRESS_UPDATE_API", 63);
        }

        public boolean getEnabling() {
            return SecMpVersionHolder.support(9999);
        }

        private AnonymousClass64(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$65  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass65 extends Features {
        public /* synthetic */ AnonymousClass65() {
            this("SUPPORT_DOWNLOAD_VIDEO_EDITOR", 64);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("videoeditor");
        }

        private AnonymousClass65(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$66  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass66 extends Features {
        public /* synthetic */ AnonymousClass66() {
            this("SUPPORT_DOWNLOAD_VIDEO_TRIMMER", 65);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("videotrimmer");
        }

        private AnonymousClass66(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$67  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass67 extends Features {
        public /* synthetic */ AnonymousClass67() {
            this("SUPPORT_DOWNLOAD_SLOW_FAST_MOTION", 66);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("slowmotion");
        }

        private AnonymousClass67(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$68  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass68 extends Features {
        public /* synthetic */ AnonymousClass68() {
            this("SUPPORT_SUPER_SLOW_MOTION", 67);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R) || EditorPluginHolder.contains("superslowmotion")) {
                return true;
            }
            return false;
        }

        private AnonymousClass68(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$69  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass69 extends Features {
        public /* synthetic */ AnonymousClass69() {
            this("SUPPORT_DOWNLOAD_STORY_VIDEO_EDITOR", 68);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("storyvideoeditor");
        }

        private AnonymousClass69(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends Features {
        public /* synthetic */ AnonymousClass7() {
            this("USE_NAVIGATION_BAR", 6);
        }

        public boolean getEnabling() {
            int identifier;
            try {
                Resources resources = Features.getAppContext().getResources();
                if (resources == null || (identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android")) <= 0 || !resources.getBoolean(identifier)) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                Log.e(Features.TAG, "checking " + name() + " failed. e=" + e.getMessage());
            }
            return false;
        }

        private AnonymousClass7(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$70  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass70 extends Features {
        public /* synthetic */ AnonymousClass70() {
            this("SUPPORT_VIDEO_COLLAGE", 69);
        }

        public boolean getEnabling() {
            return EditorPluginHolder.contains("videocollage");
        }

        private AnonymousClass70(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$71  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass71 extends Features {
        public /* synthetic */ AnonymousClass71() {
            this("SUPPORT_TIANYI_CLOUD", 70);
        }

        public boolean getEnabling() {
            return CscFeatures.getBoolean("CscFeature_Common_SupportTianYiCloud", false);
        }

        private AnonymousClass71(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$72  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass72 extends Features {
        public /* synthetic */ AnonymousClass72() {
            this("SUPPORT_BAIDU_CLOUD", 71);
        }

        public boolean getEnabling() {
            return Features.isEnabled(Features.IS_CHINESE_DEVICE);
        }

        private AnonymousClass72(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$73  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass73 extends Features {
        public /* synthetic */ AnonymousClass73() {
            this("SUPPORT_TENCENT_CLOUD", 72);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || !SdkConfig.atLeast(SdkConfig.GED.Q)) {
                return false;
            }
            return true;
        }

        private AnonymousClass73(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$74  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass74 extends Features {
        public /* synthetic */ AnonymousClass74() {
            this("SUPPORT_BURSTSHOT_FILMSTRIP", 73);
        }

        public boolean getEnabling() {
            return !Features.isVendorDevice("winner", "zodiac");
        }

        private AnonymousClass74(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$75  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass75 extends Features {
        public /* synthetic */ AnonymousClass75() {
            this("IS_FOLDABLE_TYPE_FOLD", 74);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_FOLDABLE_TYPE_FOLD");
            }
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_FRAMEWORK_SUPPORT_WM_CONTROLS_DISPLAY_SWITCH");
        }

        private AnonymousClass75(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$76  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass76 extends Features {
        public /* synthetic */ AnonymousClass76() {
            this("SUPPORT_HDR10PLUS_CONVERSION", 75);
        }

        private boolean support() {
            String str;
            if (SdkConfig.atLeast(SdkConfig.GED.S)) {
                str = "SEC_FLOATING_FEATURE_MMFW_SUPPORT_HDR2SDR";
            } else {
                str = "SEC_FLOATING_FEATURE_MMFW_SUPPORT_HDR10PLUS_PLAYBACK";
            }
            return FloatingFeatures.getBoolean(str);
        }

        public boolean getEnabling() {
            if (!SdkConfig.range(SdkConfig.SEM.P, SdkConfig.SEM.S) || !support()) {
                return false;
            }
            return true;
        }

        private AnonymousClass76(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$77  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass77 extends Features {
        public /* synthetic */ AnonymousClass77() {
            this("SUPPORT_HDR2SDR", 76);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_HDR2SDR");
        }

        private AnonymousClass77(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$78  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass78 extends Features {
        public /* synthetic */ AnonymousClass78() {
            this("SUPPORT_HDR2SDR_MAX_8K", 77);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_HDR2SDR_MAX_8K");
        }

        private AnonymousClass78(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$79  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass79 extends Features {
        public /* synthetic */ AnonymousClass79() {
            this("SUPPORT_HEIF_CONVERSION", 78);
        }

        public boolean getEnabling() {
            return SdkConfig.range(SdkConfig.GED.P, SdkConfig.GED.S);
        }

        private AnonymousClass79(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends Features {
        public /* synthetic */ AnonymousClass8() {
            this("USE_SMART_MIRRORING", 7);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.N_MR5) || !Features.isPackageInstalled("com.samsung.android.smartmirroring")) {
                return false;
            }
            return true;
        }

        private AnonymousClass8(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$80  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass80 extends Features {
        public /* synthetic */ AnonymousClass80() {
            this("SUPPORT_SLOWMOTION_V2", 79);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.Q) || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_SLOWMOTION_V2")) {
                return true;
            }
            return false;
        }

        private AnonymousClass80(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$81  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass81 extends Features {
        public /* synthetic */ AnonymousClass81() {
            this("SUPPORT_SLOWMOTION_V2_120FS", 80);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.Q) || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_SLOWMOTION_V2_120FPS")) {
                return true;
            }
            return false;
        }

        private AnonymousClass81(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$82  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass82 extends Features {
        public /* synthetic */ AnonymousClass82() {
            this("SUPPORT_SLOWMOTION_V2_NO_SVC", 81);
        }

        public boolean getEnabling() {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                return true;
            }
            if (!SdkConfig.atLeast(SdkConfig.SEM.Q_MR1) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_SLOWMOTION_V2_NO_SVC")) {
                return false;
            }
            return true;
        }

        private AnonymousClass82(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$83  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass83 extends Features {
        public /* synthetic */ AnonymousClass83() {
            this("SUPPORT_SLOWMOTION_V2_WITHOUT_SVC_240", 82);
        }

        public boolean getEnabling() {
            SdkConfig.SEM sem = SdkConfig.SEM.S_MR1;
            if (SdkConfig.moreThan(sem)) {
                return true;
            }
            if (!SdkConfig.match(sem) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_MTK_SSM_SM_VIDEO")) {
                return false;
            }
            return true;
        }

        private AnonymousClass83(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$84  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass84 extends Features {
        public /* synthetic */ AnonymousClass84() {
            this("SUPPORT_SAMSUNG_CLIPBOARD", 83);
        }

        public boolean getEnabling() {
            return SeApiCompat.isClipboardEnabled(Features.getAppContext());
        }

        private AnonymousClass84(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$85  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass85 extends Features {
        public /* synthetic */ AnonymousClass85() {
            this("SUPPORT_COPY_TO_CLIPBOARD", 84);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.GED.P) || !Features.isEnabled(Features.SUPPORT_SAMSUNG_CLIPBOARD)) {
                return false;
            }
            return true;
        }

        private AnonymousClass85(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$86  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass86 extends Features {
        public /* synthetic */ AnonymousClass86() {
            this("SUPPORT_GALAXY_APPS", 85);
        }

        public boolean getEnabling() {
            return Features.isPackageAvailable("com.sec.android.app.samsungapps");
        }

        private AnonymousClass86(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$87  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass87 extends Features {
        public /* synthetic */ AnonymousClass87() {
            this("SUPPORT_NATURAL_SORT", 86);
        }

        public boolean getEnabling() {
            return SdkConfig.atLeast(SdkConfig.SEM.O_MR5);
        }

        private AnonymousClass87(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$88  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass88 extends Features {
        public /* synthetic */ AnonymousClass88() {
            this("SUPPORT_SINGLE_SHOT_ONLY", 87);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GALLERY_SUPPORT_SINGLE_SHOT_ONLY");
        }

        private AnonymousClass88(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$89  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass89 extends Features {
        public /* synthetic */ AnonymousClass89() {
            this("SUPPORT_CAMERA_AI", 88);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.S_MR1) || !CmhVersionHolder.support(700900000)) {
                return false;
            }
            return true;
        }

        private AnonymousClass89(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends Features {
        public /* synthetic */ AnonymousClass9() {
            this("USE_SAMSUNG_FLOW", 8);
        }

        public boolean getEnabling() {
            return Features.isPackageInstalled("com.samsung.android.galaxycontinuity");
        }

        private AnonymousClass9(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$90  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass90 extends Features {
        public /* synthetic */ AnonymousClass90() {
            this("SUPPORT_PORTRAIT", 89);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.S_MR1) || !FloatingFeatures.contains("SEC_FLOATING_FEATURE_GALLERY_CONFIG_LIVEFOCUS_EFFECT_DUAL_BOKEH", "BLUR")) {
                return false;
            }
            return true;
        }

        private AnonymousClass90(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$91  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass91 extends Features {
        public /* synthetic */ AnonymousClass91() {
            this("SUPPORT_LIVE_EFFECT", 90);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.U_MR5) || !Features.isEnabled(Features.SUPPORT_NATIVE_AI) || !FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_SAIV_SUPPORT_3DPHOTO")) {
                return false;
            }
            return true;
        }

        private AnonymousClass91(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$92  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass92 extends Features {
        public /* synthetic */ AnonymousClass92() {
            this("SUPPORT_LIVE_EFFECT_VIEWER", 91);
        }

        public boolean getEnabling() {
            if (FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_SAIV_SUPPORT_3DPHOTO") || FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_SAIV_SUPPORT_3DPHOTO_VIEW")) {
                return true;
            }
            return false;
        }

        private AnonymousClass92(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$93  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass93 extends Features {
        public /* synthetic */ AnonymousClass93() {
            this("SUPPORT_LIVE_EFFECT_DYNAMIC", 92);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20253);
        }

        private AnonymousClass93(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$94  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass94 extends Features {
        public /* synthetic */ AnonymousClass94() {
            this("SUPPORT_LIVE_EFFECT_GYRO", 93);
        }

        public boolean getEnabling() {
            return ConfigAiVersion.support(20261);
        }

        private AnonymousClass94(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$95  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass95 extends Features {
        public /* synthetic */ AnonymousClass95() {
            this("SUPPORT_PHOTO_EDITOR_PORTRAIT_BACKGROUND_EFFECT", 94);
        }

        public boolean getEnabling() {
            return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_GALLERY_CONFIG_PBE");
        }

        private AnonymousClass95(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$96  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass96 extends Features {
        public /* synthetic */ AnonymousClass96() {
            this("SUPPORT_PHOTO_EDITOR_PORTRAIT_EFFECT", 95);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.B) || Features.isEnabled(Features.IS_JDM)) {
                return false;
            }
            return true;
        }

        private AnonymousClass96(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$97  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass97 extends Features {
        public /* synthetic */ AnonymousClass97() {
            this("SUPPORT_LIVE_EFFECT_WALLPAPER", 96);
        }

        public boolean getEnabling() {
            if (!SdkConfig.atLeast(SdkConfig.SEM.V) || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        private AnonymousClass97(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$98  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass98 extends Features {
        public /* synthetic */ AnonymousClass98() {
            this("SUPPORT_GALLERY_ASSISTANT", 97);
        }

        public boolean getEnabling() {
            return PackageMonitorCompat.getInstance().isPackageEnabled("com.samsung.android.gallery.assistant.app");
        }

        private AnonymousClass98(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.Features$99  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass99 extends Features {
        public /* synthetic */ AnonymousClass99() {
            this("SUPPORT_GALLERY_ASSISTANT_SPLIT_MODE", 98);
        }

        public boolean getEnabling() {
            if (!Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT) || !GalleryPreference.getInstanceCache().loadBoolean("album_split_view_enabled", true)) {
                return false;
            }
            return true;
        }

        private AnonymousClass99(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CmhVersionHolder {
        static final long sVersion = 0;

        static {
            sVersion = Features.getPackageVersion("com.samsung.cmh");
        }

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ConfigAiVersion {
        static final int sVersion = 0;

        static {
            sVersion = FloatingFeatures.getInt("SEC_FLOATING_FEATURE_COMMON_CONFIG_AI_VERSION");
        }

        public static boolean support(int i2) {
            if (sVersion >= i2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EditorPluginHolder {
        static final HashSet<String> set = null;

        static {
            set = new HashSet<String>() {
                {
                    String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_COMMON_CONFIG_MULTIMEDIA_EDITOR_PLUGIN_PACKAGES");
                    if (!string.isEmpty()) {
                        addAll(List.of(string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
                    }
                }
            };
        }

        public static boolean contains(String str) {
            return set.contains(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FaceEngineVersionHolder {
        static final FaceEngineVersionHolder sInstance = null;
        final boolean enabled;
        final int version;

        static {
            sInstance = new FaceEngineVersionHolder();
        }

        public FaceEngineVersionHolder() {
            boolean z;
            boolean z3;
            int i2 = 0;
            String str = null;
            try {
                str = FloatingFeatures.getString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_FACE_CLUSTER_VERSION");
                if (str.isEmpty() || "none".equalsIgnoreCase(str)) {
                    z = false;
                } else {
                    z = true;
                }
                try {
                    i2 = Integer.parseInt(str.substring(4));
                } catch (Exception e) {
                    Exception exc = e;
                    z3 = z;
                    e = exc;
                    a.s(e, j.k("FaceEngineVersionHolder#construct failed. feature=", str, ", e="), Features.TAG);
                    z = z3;
                    this.enabled = z;
                    this.version = i2;
                }
            } catch (Exception e7) {
                e = e7;
                z3 = false;
                a.s(e, j.k("FaceEngineVersionHolder#construct failed. feature=", str, ", e="), Features.TAG);
                z = z3;
                this.enabled = z;
                this.version = i2;
            }
            this.enabled = z;
            this.version = i2;
        }

        public static boolean isEnabled() {
            return sInstance.enabled;
        }

        public static boolean support(int i2) {
            FaceEngineVersionHolder faceEngineVersionHolder = sInstance;
            if (!faceEngineVersionHolder.enabled || faceEngineVersionHolder.version < i2) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageTaggerVersionHolder {
        static final ImageTaggerVersionHolder sInstance = null;
        final boolean enabled;
        final int version;

        static {
            sInstance = new ImageTaggerVersionHolder();
        }

        public ImageTaggerVersionHolder() {
            boolean z;
            boolean z3;
            int i2 = 0;
            String str = null;
            try {
                str = FloatingFeatures.getString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_IMAGE_TAGGER_VERSION");
                if (str.isEmpty() || "none".equalsIgnoreCase(str)) {
                    z = false;
                } else {
                    z = true;
                }
                try {
                    i2 = Integer.parseInt(str.substring(1));
                } catch (Exception e) {
                    Exception exc = e;
                    z3 = z;
                    e = exc;
                    a.s(e, j.k("ImageTaggerVersionHolder#construct failed. feature=", str, ", e="), Features.TAG);
                    z = z3;
                    this.enabled = z;
                    this.version = i2;
                }
            } catch (Exception e7) {
                e = e7;
                z3 = false;
                a.s(e, j.k("ImageTaggerVersionHolder#construct failed. feature=", str, ", e="), Features.TAG);
                z = z3;
                this.enabled = z;
                this.version = i2;
            }
            this.enabled = z;
            this.version = i2;
        }

        public static boolean isEnabled() {
            return sInstance.enabled;
        }

        public static boolean support(int i2) {
            ImageTaggerVersionHolder imageTaggerVersionHolder = sInstance;
            if (!imageTaggerVersionHolder.enabled || imageTaggerVersionHolder.version < i2) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LockScreenConfigSubDisplay {
        static final String policy = null;

        static {
            policy = FloatingFeatures.getString("SEC_FLOATING_FEATURE_LOCKSCREEN_CONFIG_SUBDISPLAY_POLICY");
        }

        public static boolean contains(String str) {
            return policy.contains(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MdeVersionHolder {
        static final long SORT_SHARINGS = 0;
        static final long sVersion = 0;

        static {
            long j2;
            if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
                j2 = 1365300001;
            } else {
                j2 = 1365000031;
            }
            SORT_SHARINGS = j2;
            sVersion = Features.getPackageVersion(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME);
        }

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OcrEngineVersionHolder {
        static final OcrEngineVersionHolder sInstance = null;
        final boolean enabled;

        static {
            sInstance = new OcrEngineVersionHolder();
        }

        public OcrEngineVersionHolder() {
            boolean z = false;
            try {
                String string = FloatingFeatures.getString("SEC_FLOATING_FEATURE_CAMERA_CONFIG_STRIDE_OCR_VERSION");
                if (!string.isEmpty() && !"None".equalsIgnoreCase(string)) {
                    z = true;
                }
            } catch (Exception e) {
                a.s(e, j.k("OcrEngineVersionHolder#construct failed. feature=", (String) null, ", e="), Features.TAG);
            }
            this.enabled = z;
        }

        public static boolean isEnabled() {
            return sInstance.enabled;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScsVersionHolder {
        static final ScsVersionHolder sInstance = null;
        final boolean enabled;
        final long version;

        static {
            sInstance = new ScsVersionHolder();
        }

        public ScsVersionHolder() {
            boolean z;
            String str = Component$SamsungSearch.PACKAGE;
            long packageVersion = Features.getPackageVersion(str);
            this.version = packageVersion;
            if (!SdkConfig.atLeast(SdkConfig.GED.R) || !ImageTaggerVersionHolder.isEnabled() || !Features.isPackageAvailable(str)) {
                z = false;
            } else {
                z = true;
            }
            this.enabled = z;
            Log.d(Features.TAG, "ScsVersionHolder", Boolean.valueOf(z), Long.valueOf(packageVersion));
        }

        public static boolean isEnabled() {
            return sInstance.enabled;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$supportHint$0() {
            Bundle callSafe = AndroidCompat.callSafe(Features.getAppContext(), Uri.parse("content://" + Component$SamsungSearch.AUTHORITY + "/v2"), "is_hint_supported", "media", (Bundle) null);
            StringBuilder sb2 = new StringBuilder("supportHint ");
            sb2.append(Logger.toString(callSafe));
            Log.d(Features.TAG, sb2.toString());
            if (callSafe == null || !callSafe.getBoolean("media", false)) {
                return false;
            }
            return true;
        }

        public static boolean support(long j2) {
            ScsVersionHolder scsVersionHolder = sInstance;
            if (!scsVersionHolder.enabled || scsVersionHolder.version < j2) {
                return false;
            }
            return true;
        }

        public static boolean supportHint() {
            if (!sInstance.enabled) {
                return false;
            }
            try {
                GalleryPreference instance = GalleryPreference.getInstance();
                return instance.computeBooleanIfAbsent("ScsHint#" + SdkConfig.SEM_VER, new C0671i(1));
            } catch (Exception e) {
                j.s(e, new StringBuilder("supportHint failed e="), Features.TAG);
                return false;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecMpVersionHolder {
        static final long sVersion = 0;

        static {
            sVersion = Features.getPackageVersion("com.samsung.android.providers.media");
        }

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecTrashVersionHolder {
        static final long sVersion = 0;

        static {
            sVersion = Features.getPackageVersion("com.samsung.android.providers.trash");
        }

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SmartSuggestionsVersionHolder {
        static final long sVersion = 0;

        static {
            sVersion = Features.getPackageVersion(Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        }

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    static {
        SALES_CODE = SeApiCompat.getSystemPropertiesString("ro.csc.sales_code", "");
        COUNTRY_CODE = SeApiCompat.getSystemPropertiesString("ro.csc.country_code", "");
        COUNTRY_CODE_LOCAL = null;
    }

    public static Context getAppContext() {
        return AppResources.getAppContext();
    }

    public static long getPackageVersion(String str) {
        return PackageMonitorCompat.getInstance().getPackageVersion(str);
    }

    public static String getSalesCode() {
        return SALES_CODE;
    }

    private static String getVendorDevice() {
        if (sVendorDevice == null) {
            sVendorDevice = SeApiCompat.getSystemProperties("ro.product.vendor.device");
        }
        return sVendorDevice;
    }

    /* access modifiers changed from: private */
    public static boolean hasSystemFeature(String str) {
        return PackageMonitorCompat.getInstance().hasSystemFeature(str);
    }

    /* access modifiers changed from: private */
    public static boolean isCountryCode(String str) {
        return str.equalsIgnoreCase(COUNTRY_CODE);
    }

    public static boolean isEnabled(Features features) {
        return features.isEnabled();
    }

    /* access modifiers changed from: private */
    public static boolean isPackageAvailable(String str) {
        return PackageMonitorCompat.getInstance().isPackageEnabled(str);
    }

    /* access modifiers changed from: private */
    public static boolean isPackageCompatible(String str, int i2) {
        if (getPackageVersion(str) >= ((long) i2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean isPackageInstalled(String str) {
        return PackageMonitorCompat.getInstance().isPackageInstalled(str);
    }

    /* access modifiers changed from: private */
    public static boolean isPackageSettingEnabled(String str) {
        return PackageMonitorCompat.getInstance().isPackageSettingEnabled(str);
    }

    /* access modifiers changed from: private */
    public static boolean isSalesCode(String str) {
        return str.equalsIgnoreCase(SALES_CODE);
    }

    /* access modifiers changed from: private */
    public static boolean isUnpackDevice() {
        return FloatingFeatures.getBoolean("SEC_FLOATING_FEATURE_COMMON_SUPPORT_UNPACK");
    }

    /* access modifiers changed from: private */
    public static boolean isVendorDevice(String... strArr) {
        String vendorDevice = getVendorDevice();
        for (String contains : strArr) {
            if (vendorDevice.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public static void printDebug() {
        Log.d(TAG, toDebugString());
    }

    public static void recycle(Features features) {
        features.recycle((Boolean) null);
    }

    /* access modifiers changed from: private */
    public static boolean supportSharedAlbumEdit(String str) {
        try {
            Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData(str);
            if (applicationMetaData == null || !applicationMetaData.getBoolean("support_shared_album_edit", false)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("supportSharedAlbumEdit failed. e="), TAG);
            return false;
        }
    }

    public static String toDebugString() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add("COUNTRY_CODE=" + COUNTRY_CODE);
            arrayList.add("SALES_CODE=" + SALES_CODE);
            arrayList.add("CARRIER_ID=" + SeApiCompat.getSystemProperties("ro.boot.carrierid"));
            arrayList.add("VENDOR_DEVICE=" + getVendorDevice());
            arrayList.add("SDK_VERSION=" + Build.VERSION.SDK_INT);
            arrayList.add("SEM_VERSION=" + Build.VERSION.SEM_INT);
            arrayList.add("SEM_PLATFORM_VERSION=" + Build.VERSION.SEM_PLATFORM_INT);
        } catch (Error | Exception unused) {
            arrayList.add("SEM_VERSION=N/A");
        }
        arrayList.add("SEP_VERSION=" + SeApiCompat.getSystemPropertiesInt("ro.build.version.sep", 0));
        arrayList.add("SDK_FIRST_VERSION=" + SeApiCompat.getSystemPropertiesInt("ro.product.first_api_level", 0));
        arrayList.add("MDE_VERSION=" + MdeVersionHolder.sVersion);
        return C0212a.B("DeviceInfo=" + ((String) arrayList.stream().collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"))), "\n", "Features=" + ((String) Arrays.stream(values()).filter(new C0680s(1)).map(new C0670h(12)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"))));
    }

    public abstract boolean getEnabling();

    public String toString() {
        return name() + "=" + this.mIsEnabled;
    }

    /* access modifiers changed from: private */
    public static boolean isSalesCode(String... strArr) {
        for (String equalsIgnoreCase : strArr) {
            if (equalsIgnoreCase.equalsIgnoreCase(SALES_CODE)) {
                return true;
            }
        }
        return false;
    }

    public static void recycle(Features features, boolean z) {
        features.recycle(Boolean.valueOf(z));
    }

    public boolean isEnabled() {
        if (this.mIsEnabled == null) {
            this.mIsEnabled = Boolean.valueOf(getEnabling());
        }
        return this.mIsEnabled.booleanValue();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CameraVersionHolder {
        static final long sVersion = 0;

        static {
            sVersion = Features.getPackageVersion("com.sec.android.app.camera");
        }

        public static boolean support(String str) {
            try {
                Bundle applicationMetaData = PackageMonitorCompat.getInstance().getApplicationMetaData("com.sec.android.app.camera");
                String string = applicationMetaData != null ? applicationMetaData.getString("com.sec.android.app.camera.core2.features") : null;
                if (string == null || !string.contains(str)) {
                    return false;
                }
                return true;
            } catch (Exception unused) {
            }
        }

        public static boolean support(long j2) {
            return sVersion >= j2;
        }
    }

    public void recycle(Boolean bool) {
        this.mIsEnabled = bool;
    }

    public static void recycle() {
        recycle(IS_UPSM);
        recycle(IS_ENABLED_SHOW_BUTTON_SHAPES);
        recycle(IS_RTL);
        recycle(SUPPORT_REORDER);
        recycle(SUPPORT_INTELLIGENT_SEARCH);
        recycle(IS_COLOR_THEME_ENABLED);
        recycle(IS_THEME_INSTALLED);
        if (isEnabled(IS_VERIZON_DEVICE)) {
            recycle(SUPPORT_VERIZON_CLOUD);
        }
    }
}
