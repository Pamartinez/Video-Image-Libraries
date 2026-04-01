package androidx.media3.common.util;

import F.c;
import H2.f;
import I2.b;
import L2.a;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseLongArray;
import android.view.Display;
import android.view.WindowManager;
import androidx.media3.common.Player;
import androidx.media3.common.audio.AudioManagerCompat;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.sum.core.message.Message;
import i.C0212a;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import o1.C0246a;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Util {
    private static final int[] CRC16_BYTES_MSBF = {0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935};
    private static final int[] CRC32_BYTES_MSBF = {0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    private static final int[] CRC8_BYTES_MSBF = {0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, 224, 231, 238, 233, 252, 251, 242, 245, 216, 223, 214, 209, 196, 195, 202, Message.CODEC_NUM_EXTRA_FRAMES, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, MOCRLang.PUNJABI, MOCRLang.KHMER, 179, 186, 189, 199, 192, 201, Message.END_OF_STREAM, 219, MOCRLang.LAO, 213, MOCRLang.ARMENIAN, ScoverState.TYPE_NFC_SMART_COVER, 248, 241, 246, 227, 228, 237, 234, 183, 176, 185, MOCRLang.GREEK, 171, 172, 165, 162, 143, 136, 129, 134, 147, 148, 157, 154, 39, 32, 41, 46, 59, 60, 53, 50, 31, 24, 17, 22, 3, 4, 13, 10, 87, 80, 89, 94, 75, 76, 69, 66, 111, 104, 97, 102, 115, 116, 125, 122, 137, 142, 135, 128, 149, 146, 155, 156, 177, 182, 191, 184, 173, MOCRLang.MALAYALAM, 163, 164, 249, 254, 247, 240, 229, 226, 235, 236, 193, 198, 207, 200, 221, 218, 211, 212, 105, 110, 103, 96, 117, 114, 123, 124, 81, 86, 95, 88, 77, 74, 67, 68, 25, 30, 23, 16, 5, 2, 11, 12, 33, 38, 47, 40, 61, 58, 51, 52, 78, 73, 64, 71, 82, 85, 92, 91, 118, 113, 120, 127, 106, 109, 100, 99, 62, 57, 48, 55, 34, 37, 44, 43, 6, 1, 8, 15, 26, 29, 20, 19, 174, 169, MOCRLang.GURMUKHI, 167, 178, 181, 188, 187, 150, 145, 152, 159, 138, 141, 132, 131, 222, 217, 208, 215, 194, 197, 204, 203, 230, 225, 232, 239, 250, 253, 244, 243};
    @Deprecated
    public static final String DEVICE;
    public static final String DEVICE_DEBUG_INFO;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final Pattern ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
    private static final Pattern ISM_PATH_PATTERN = Pattern.compile("(?:.*\\.)?isml?(?:/(manifest(.*))?)?", 2);
    @Deprecated
    public static final String MANUFACTURER;
    @Deprecated
    public static final String MODEL;
    @Deprecated
    public static final int SDK_INT;
    private static final Pattern XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
    private static final Pattern XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
    private static final String[] additionalIsoLanguageReplacements = {"alb", "sq", "arm", "hy", "baq", "eu", "bur", "my", "tib", "bo", "chi", "zh", "cze", "cs", "dut", "nl", "ger", "de", "gre", "el", "fre", "fr", "geo", "ka", "ice", "is", "mac", "mk", "mao", "mi", "may", "ms", "per", "fa", "rum", "ro", "scc", "hbs-srp", "slo", "sk", "wel", "cy", "id", "ms-ind", "iw", "he", "heb", "he", "ji", "yi", "arb", "ar-arb", "in", "ms-ind", "ind", "ms-ind", "nb", "no-nob", "nob", "no-nob", "nn", "no-nno", "nno", "no-nno", "tw", "ak-twi", "twi", "ak-twi", "bs", "hbs-bos", "bos", "hbs-bos", "hr", "hbs-hrv", "hrv", "hbs-hrv", "sr", "hbs-srp", "srp", "hbs-srp", "cmn", "zh-cmn", "hak", "zh-hak", "nan", "zh-nan", "hsn", "zh-hsn"};
    private static final String[] isoLegacyTagReplacements = {"i-lux", "lb", "i-hak", "zh-hak", "i-navajo", "nv", "no-bok", "no-nob", "no-nyn", "no-nno", "zh-guoyu", "zh-cmn", "zh-hakka", "zh-hak", "zh-min-nan", "zh-nan", "zh-xiang", "zh-hsn"};
    private static HashMap<String, String> languageTagReplacementMap;

    static {
        int i2 = Build.VERSION.SDK_INT;
        SDK_INT = i2;
        String str = Build.DEVICE;
        DEVICE = str;
        String str2 = Build.MANUFACTURER;
        MANUFACTURER = str2;
        String str3 = Build.MODEL;
        MODEL = str3;
        DEVICE_DEBUG_INFO = str + ArcCommonLog.TAG_COMMA + str3 + ArcCommonLog.TAG_COMMA + str2 + ArcCommonLog.TAG_COMMA + i2;
    }

    public static long addWithOverflowDefault(long j2, long j3, long j8) {
        long j10 = j2 + j3;
        if (((j2 ^ j10) & (j3 ^ j10)) < 0) {
            return j8;
        }
        return j10;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchCeil(long[] r4, long r5, boolean r7, boolean r8) {
        /*
            int r0 = java.util.Arrays.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x0008
            int r5 = ~r0
            goto L_0x001a
        L_0x0008:
            int r1 = r0 + 1
            int r2 = r4.length
            if (r1 >= r2) goto L_0x0015
            r2 = r4[r1]
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0015
            r0 = r1
            goto L_0x0008
        L_0x0015:
            if (r7 == 0) goto L_0x0019
            r5 = r0
            goto L_0x001a
        L_0x0019:
            r5 = r1
        L_0x001a:
            if (r8 == 0) goto L_0x0024
            int r4 = r4.length
            int r4 = r4 + -1
            int r4 = java.lang.Math.min(r4, r5)
            return r4
        L_0x0024:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.Util.binarySearchCeil(long[], long, boolean, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0018  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchFloor(int[] r3, int r4, boolean r5, boolean r6) {
        /*
            int r0 = java.util.Arrays.binarySearch(r3, r4)
            if (r0 >= 0) goto L_0x000a
            int r0 = r0 + 2
            int r3 = -r0
            goto L_0x0019
        L_0x000a:
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x0014
            r2 = r3[r1]
            if (r2 != r4) goto L_0x0014
            r0 = r1
            goto L_0x000a
        L_0x0014:
            if (r5 == 0) goto L_0x0018
            r3 = r0
            goto L_0x0019
        L_0x0018:
            r3 = r1
        L_0x0019:
            if (r6 == 0) goto L_0x0020
            r4 = 0
            int r3 = java.lang.Math.max(r4, r3)
        L_0x0020:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.Util.binarySearchFloor(int[], int, boolean, boolean):int");
    }

    public static int ceilDivide(int i2, int i7) {
        return ((i2 + i7) - 1) / i7;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int constrainValue(int i2, int i7, int i8) {
        return Math.max(i7, Math.min(i2, i8));
    }

    public static <T> boolean contains(SparseArray<T> sparseArray, int i2) {
        if (sparseArray.indexOfKey(i2) >= 0) {
            return true;
        }
        return false;
    }

    public static int crc16(byte[] bArr, int i2, int i7, int i8) {
        while (i2 < i7) {
            byte b = bArr[i2];
            i8 = crc16UpdateFourBits(b & 15, crc16UpdateFourBits((b & 255) >> 4, i8));
            i2++;
        }
        return i8;
    }

    private static int crc16UpdateFourBits(int i2, int i7) {
        return (CRC16_BYTES_MSBF[(i2 ^ ((i7 >> 12) & ScoverState.TYPE_NFC_SMART_COVER)) & ScoverState.TYPE_NFC_SMART_COVER] ^ ((i7 << 4) & 65535)) & 65535;
    }

    public static int crc32(byte[] bArr, int i2, int i7, int i8) {
        while (i2 < i7) {
            i8 = CRC32_BYTES_MSBF[((i8 >>> 24) ^ (bArr[i2] & 255)) & 255] ^ (i8 << 8);
            i2++;
        }
        return i8;
    }

    public static int crc8(byte[] bArr, int i2, int i7, int i8) {
        while (i2 < i7) {
            i8 = CRC8_BYTES_MSBF[i8 ^ (bArr[i2] & 255)];
            i2++;
        }
        return i8;
    }

    public static Handler createHandler(Looper looper, Handler.Callback callback) {
        return new Handler(looper, callback);
    }

    public static Handler createHandlerForCurrentLooper() {
        return createHandlerForCurrentLooper((Handler.Callback) null);
    }

    public static Handler createHandlerForCurrentOrMainLooper() {
        return createHandlerForCurrentOrMainLooper((Handler.Callback) null);
    }

    private static HashMap<String, String> createIsoLanguageReplacementMap() {
        String[] iSOLanguages = Locale.getISOLanguages();
        HashMap<String, String> hashMap = new HashMap<>(iSOLanguages.length + additionalIsoLanguageReplacements.length);
        int i2 = 0;
        for (String str : iSOLanguages) {
            try {
                String iSO3Language = new Locale(str).getISO3Language();
                if (!TextUtils.isEmpty(iSO3Language)) {
                    hashMap.put(iSO3Language, str);
                }
            } catch (MissingResourceException unused) {
            }
        }
        while (true) {
            String[] strArr = additionalIsoLanguageReplacements;
            if (i2 >= strArr.length) {
                return hashMap;
            }
            hashMap.put(strArr[i2], strArr[i2 + 1]);
            i2 += 2;
        }
    }

    public static long durationUsToSampleCount(long j2, int i2) {
        return scaleLargeValue(j2, (long) i2, 1000000, RoundingMode.UP);
    }

    public static String formatInvariant(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String fromUtf8Bytes(byte[] bArr) {
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public static int generateAudioSessionIdV21(Context context) {
        return AudioManagerCompat.getAudioManager(context).generateAudioSessionId();
    }

    public static int getAudioTrackChannelConfig(int i2) {
        if (i2 != 10) {
            if (i2 == 12) {
                return 743676;
            }
            if (i2 != 24) {
                switch (i2) {
                    case 1:
                        return 4;
                    case 2:
                        return 12;
                    case 3:
                        return 28;
                    case 4:
                        return 204;
                    case 5:
                        return MOCRLang.LAO;
                    case 6:
                        return 252;
                    case 7:
                        return 1276;
                    case 8:
                        return 6396;
                    default:
                        return 0;
                }
            } else if (Build.VERSION.SDK_INT >= 32) {
                return 67108860;
            } else {
                return 0;
            }
        } else if (Build.VERSION.SDK_INT >= 32) {
            return 737532;
        } else {
            return 6396;
        }
    }

    public static String getAuxiliaryTrackTypeString(int i2) {
        if (i2 == 0) {
            return "undefined";
        }
        if (i2 == 1) {
            return ShareApi.ORIGINAL_SIZE_IMAGE;
        }
        if (i2 == 2) {
            return "depth-linear";
        }
        if (i2 == 3) {
            return "depth-inverse";
        }
        if (i2 == 4) {
            return "depth metadata";
        }
        throw new IllegalStateException("Unsupported auxiliary track type");
    }

    public static Player.Commands getAvailableCommands(Player player, Player.Commands commands) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean isPlayingAd = player.isPlayingAd();
        boolean isCurrentMediaItemSeekable = player.isCurrentMediaItemSeekable();
        boolean hasPreviousMediaItem = player.hasPreviousMediaItem();
        boolean hasNextMediaItem = player.hasNextMediaItem();
        boolean isCurrentMediaItemLive = player.isCurrentMediaItemLive();
        boolean isCurrentMediaItemDynamic = player.isCurrentMediaItemDynamic();
        boolean isEmpty = player.getCurrentTimeline().isEmpty();
        Player.Commands.Builder addIf = new Player.Commands.Builder().addAll(commands).addIf(4, !isPlayingAd);
        boolean z12 = false;
        if (!isCurrentMediaItemSeekable || isPlayingAd) {
            z = false;
        } else {
            z = true;
        }
        Player.Commands.Builder addIf2 = addIf.addIf(5, z);
        if (!hasPreviousMediaItem || isPlayingAd) {
            z3 = false;
        } else {
            z3 = true;
        }
        Player.Commands.Builder addIf3 = addIf2.addIf(6, z3);
        if (isEmpty || ((!hasPreviousMediaItem && isCurrentMediaItemLive && !isCurrentMediaItemSeekable) || isPlayingAd)) {
            z7 = false;
        } else {
            z7 = true;
        }
        Player.Commands.Builder addIf4 = addIf3.addIf(7, z7);
        if (!hasNextMediaItem || isPlayingAd) {
            z9 = false;
        } else {
            z9 = true;
        }
        Player.Commands.Builder addIf5 = addIf4.addIf(8, z9);
        if (isEmpty || ((!hasNextMediaItem && (!isCurrentMediaItemLive || !isCurrentMediaItemDynamic)) || isPlayingAd)) {
            z10 = false;
        } else {
            z10 = true;
        }
        Player.Commands.Builder addIf6 = addIf5.addIf(9, z10).addIf(10, !isPlayingAd);
        if (!isCurrentMediaItemSeekable || isPlayingAd) {
            z11 = false;
        } else {
            z11 = true;
        }
        Player.Commands.Builder addIf7 = addIf6.addIf(11, z11);
        if (isCurrentMediaItemSeekable && !isPlayingAd) {
            z12 = true;
        }
        return addIf7.addIf(12, z12).build();
    }

    public static int getByteDepth(int i2) {
        if (i2 != 2) {
            if (i2 == 3) {
                return 1;
            }
            if (i2 != 4) {
                if (i2 != 21) {
                    if (i2 != 22) {
                        if (i2 != 268435456) {
                            if (i2 != 1342177280) {
                                if (i2 != 1610612736) {
                                    throw new IllegalArgumentException();
                                }
                            }
                        }
                    }
                }
                return 3;
            }
            return 4;
        }
        return 2;
    }

    public static String getCountryCode(Context context) {
        TelephonyManager telephonyManager;
        if (!(context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null)) {
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (!TextUtils.isEmpty(networkCountryIso)) {
                return k.T(networkCountryIso);
            }
        }
        return k.T(Locale.getDefault().getCountry());
    }

    public static Point getCurrentDisplayModeSize(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        Display display = displayManager != null ? displayManager.getDisplay(0) : null;
        if (display == null) {
            display = ((WindowManager) Assertions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        }
        return getCurrentDisplayModeSize(context, display);
    }

    public static Looper getCurrentOrMainLooper() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return myLooper;
        }
        return Looper.getMainLooper();
    }

    private static void getDisplaySizeV23(Display display, Point point) {
        Display.Mode mode = display.getMode();
        point.x = mode.getPhysicalWidth();
        point.y = mode.getPhysicalHeight();
    }

    public static int getErrorCodeForMediaDrmErrorCode(int i2) {
        if (i2 == 2 || i2 == 4) {
            return 6005;
        }
        if (i2 == 10) {
            return 6004;
        }
        if (i2 == 7) {
            return 6005;
        }
        if (i2 == 8) {
            return 6003;
        }
        switch (i2) {
            case 15:
                return 6003;
            case 16:
            case 18:
                return 6005;
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
                return 6004;
            default:
                switch (i2) {
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                        return 6002;
                    default:
                        return 6006;
                }
        }
    }

    public static int getErrorCodeFromPlatformDiagnosticsInfo(String str) {
        String[] split;
        int length;
        boolean z;
        int i2 = 0;
        if (str == null || (length = split.length) < 2) {
            return 0;
        }
        String str2 = split[length - 1];
        if (length < 3 || !"neg".equals((split = split(str, "_"))[length - 2])) {
            z = false;
        } else {
            z = true;
        }
        try {
            i2 = Integer.parseInt((String) Assertions.checkNotNull(str2));
            if (z) {
                return -i2;
            }
        } catch (NumberFormatException unused) {
        }
        return i2;
    }

    public static String getFormatSupportString(int i2) {
        if (i2 == 0) {
            return "NO";
        }
        if (i2 == 1) {
            return "NO_UNSUPPORTED_TYPE";
        }
        if (i2 == 2) {
            return "NO_UNSUPPORTED_DRM";
        }
        if (i2 == 3) {
            return "NO_EXCEEDS_CAPABILITIES";
        }
        if (i2 == 4) {
            return "YES";
        }
        throw new IllegalStateException();
    }

    public static String getLocaleLanguageTag(Locale locale) {
        return locale.toLanguageTag();
    }

    public static int getMaxPendingFramesCountForMediaCodecDecoders(Context context) {
        if (isFrameDropAllowedOnSurfaceInput(context)) {
            return 1;
        }
        return 5;
    }

    public static long getMediaDurationForPlayoutDuration(long j2, float f) {
        if (f == 1.0f) {
            return j2;
        }
        return Math.round(((double) j2) * ((double) f));
    }

    public static long getNowUnixTimeMs(long j2) {
        if (j2 == -9223372036854775807L) {
            return System.currentTimeMillis();
        }
        return SystemClock.elapsedRealtime() + j2;
    }

    public static int getPcmEncoding(int i2) {
        return getPcmEncoding(i2, ByteOrder.LITTLE_ENDIAN);
    }

    public static int getPcmFrameSize(int i2, int i7) {
        return getByteDepth(i2) * i7;
    }

    public static long getPlayoutDurationForMediaDuration(long j2, float f) {
        if (f == 1.0f) {
            return j2;
        }
        return Math.round(((double) j2) / ((double) f));
    }

    public static List<String> getRoleFlagStrings(int i2) {
        ArrayList arrayList = new ArrayList();
        if ((i2 & 1) != 0) {
            arrayList.add("main");
        }
        if ((i2 & 2) != 0) {
            arrayList.add("alt");
        }
        if ((i2 & 4) != 0) {
            arrayList.add("supplementary");
        }
        if ((i2 & 8) != 0) {
            arrayList.add("commentary");
        }
        if ((i2 & 16) != 0) {
            arrayList.add("dub");
        }
        if ((i2 & 32) != 0) {
            arrayList.add("emergency");
        }
        if ((i2 & 64) != 0) {
            arrayList.add("caption");
        }
        if ((i2 & 128) != 0) {
            arrayList.add("subtitle");
        }
        if ((i2 & 256) != 0) {
            arrayList.add("sign");
        }
        if ((i2 & 512) != 0) {
            arrayList.add("describes-video");
        }
        if ((i2 & 1024) != 0) {
            arrayList.add("describes-music");
        }
        if ((i2 & 2048) != 0) {
            arrayList.add("enhanced-intelligibility");
        }
        if ((i2 & 4096) != 0) {
            arrayList.add("transcribes-dialog");
        }
        if ((i2 & SerializeOptions.SORT) != 0) {
            arrayList.add("easy-read");
        }
        if ((i2 & 16384) != 0) {
            arrayList.add("trick-play");
        }
        if ((i2 & 32768) != 0) {
            arrayList.add("auxiliary");
        }
        return arrayList;
    }

    public static List<String> getSelectionFlagStrings(int i2) {
        ArrayList arrayList = new ArrayList();
        if ((i2 & 4) != 0) {
            arrayList.add("auto");
        }
        if ((i2 & 1) != 0) {
            arrayList.add(Event.DEFAULT_EVENT_TYPE);
        }
        if ((i2 & 2) != 0) {
            arrayList.add("forced");
        }
        return arrayList;
    }

    public static String[] getSystemLanguageCodes() {
        String[] systemLocales = getSystemLocales();
        for (int i2 = 0; i2 < systemLocales.length; i2++) {
            systemLocales[i2] = normalizeLanguageCode(systemLocales[i2]);
        }
        return systemLocales;
    }

    private static String[] getSystemLocales() {
        return getSystemLocalesV24(Resources.getSystem().getConfiguration());
    }

    private static String[] getSystemLocalesV24(Configuration configuration) {
        return split(configuration.getLocales().toLanguageTags(), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
    }

    private static String getSystemProperty(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e) {
            Log.e("Util", "Failed to read system property " + str, e);
            return null;
        }
    }

    public static String getTrackTypeString(int i2) {
        switch (i2) {
            case -2:
                return "none";
            case -1:
                return "unknown";
            case 0:
                return Event.DEFAULT_EVENT_TYPE;
            case 1:
                return "audio";
            case 2:
                return "video";
            case 3:
                return "text";
            case 4:
                return "image";
            case 5:
                return GroupContract.Group.META_DATA;
            case 6:
                return "camera motion";
            default:
                if (i2 >= 10000) {
                    return C0212a.j(i2, "custom (", ")");
                }
                return "?";
        }
    }

    public static byte[] getUtf8Bytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public static int inferContentType(Uri uri) {
        int inferContentTypeForExtension;
        String scheme = uri.getScheme();
        if (scheme != null && (k.v("rtsp", scheme) || k.v("rtspt", scheme))) {
            return 3;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            int lastIndexOf = lastPathSegment.lastIndexOf(46);
            if (lastIndexOf >= 0 && (inferContentTypeForExtension = inferContentTypeForExtension(lastPathSegment.substring(lastIndexOf + 1))) != 4) {
                return inferContentTypeForExtension;
            }
            Matcher matcher = ISM_PATH_PATTERN.matcher((CharSequence) Assertions.checkNotNull(uri.getPath()));
            if (matcher.matches()) {
                String group = matcher.group(2);
                if (group != null) {
                    if (group.contains("format=mpd-time-csf")) {
                        return 0;
                    }
                    if (group.contains("format=m3u8-aapl")) {
                        return 2;
                    }
                }
                return 1;
            }
        }
        return 4;
    }

    public static int inferContentTypeForExtension(String str) {
        String S = k.S(str);
        S.getClass();
        char c5 = 65535;
        switch (S.hashCode()) {
            case 104579:
                if (S.equals("ism")) {
                    c5 = 0;
                    break;
                }
                break;
            case 108321:
                if (S.equals("mpd")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3242057:
                if (S.equals("isml")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3299913:
                if (S.equals("m3u8")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 2:
                return 1;
            case 1:
                return 0;
            case 3:
                return 2;
            default:
                return 4;
        }
    }

    public static int inferContentTypeForUriAndMimeType(Uri uri, String str) {
        if (str == null) {
            return inferContentType(uri);
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -979127466:
                if (str.equals("application/x-mpegURL")) {
                    c5 = 0;
                    break;
                }
                break;
            case -156749520:
                if (str.equals("application/vnd.ms-sstr+xml")) {
                    c5 = 1;
                    break;
                }
                break;
            case 64194685:
                if (str.equals("application/dash+xml")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1154777587:
                if (str.equals("application/x-rtsp")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 2;
            case 1:
                return 1;
            case 2:
                return 0;
            case 3:
                return 3;
            default:
                return 4;
        }
    }

    public static boolean inflate(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, Inflater inflater) {
        if (parsableByteArray.bytesLeft() == 0) {
            return false;
        }
        if (parsableByteArray2.capacity() < parsableByteArray.bytesLeft()) {
            parsableByteArray2.ensureCapacity(parsableByteArray.bytesLeft() * 2);
        }
        if (inflater == null) {
            inflater = new Inflater();
        }
        inflater.setInput(parsableByteArray.getData(), parsableByteArray.getPosition(), parsableByteArray.bytesLeft());
        int i2 = 0;
        while (true) {
            try {
                i2 += inflater.inflate(parsableByteArray2.getData(), i2, parsableByteArray2.capacity() - i2);
                if (inflater.finished()) {
                    parsableByteArray2.setLimit(i2);
                    inflater.reset();
                    return true;
                } else if (inflater.needsDictionary()) {
                    break;
                } else if (inflater.needsInput()) {
                    break;
                } else if (i2 == parsableByteArray2.capacity()) {
                    parsableByteArray2.ensureCapacity(parsableByteArray2.capacity() * 2);
                }
            } catch (DataFormatException unused) {
                return false;
            } finally {
                inflater.reset();
            }
        }
        return false;
    }

    public static String intToStringMaxRadix(int i2) {
        return Integer.toString(i2, 36);
    }

    public static boolean isBitmapFactorySupportedMimeType(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1487656890:
                if (str.equals("image/avif")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1487464693:
                if (str.equals("image/heic")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1487464690:
                if (str.equals("image/heif")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1487394660:
                if (str.equals("image/jpeg")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1487018032:
                if (str.equals("image/webp")) {
                    c5 = 4;
                    break;
                }
                break;
            case -879272239:
                if (str.equals("image/bmp")) {
                    c5 = 5;
                    break;
                }
                break;
            case -879258763:
                if (str.equals("image/png")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (Build.VERSION.SDK_INT >= 34) {
                    return true;
                }
                return false;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public static boolean isEncodingLinearPcm(int i2) {
        if (i2 == 3 || i2 == 2 || i2 == 268435456 || i2 == 21 || i2 == 1342177280 || i2 == 22 || i2 == 1610612736 || i2 == 4) {
            return true;
        }
        return false;
    }

    public static boolean isFrameDropAllowedOnSurfaceInput(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (context.getApplicationInfo().targetSdkVersion < 29) {
            return true;
        }
        if (i2 == 30) {
            String str = Build.MODEL;
            if (k.v(str, "moto g(20)") || k.v(str, "rmx3231")) {
                return true;
            }
        }
        if (i2 != 34 || !k.v(Build.MODEL, "sm-x200")) {
            return false;
        }
        return true;
    }

    public static boolean isLinebreak(int i2) {
        if (i2 == 10 || i2 == 13) {
            return true;
        }
        return false;
    }

    public static boolean isLocalFileUri(Uri uri) {
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme) || Objects.equals(scheme, "file")) {
            return true;
        }
        return false;
    }

    public static boolean isRunningOnEmulator() {
        String S = k.S(Build.DEVICE);
        if (S.contains("emulator") || S.contains("emu64a") || S.contains("emu64x") || S.contains("generic")) {
            return true;
        }
        return false;
    }

    public static boolean isTv(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService("uimode");
        if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
            return false;
        }
        return true;
    }

    public static boolean isWear(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$newSingleThreadExecutor$3(String str, Runnable runnable) {
        return new Thread(runnable, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$newSingleThreadScheduledExecutor$4(String str, Runnable runnable) {
        return new Thread(runnable, str);
    }

    public static int linearSearch(int[] iArr, int i2) {
        for (int i7 = 0; i7 < iArr.length; i7++) {
            if (iArr[i7] == i2) {
                return i7;
            }
        }
        return -1;
    }

    public static String loadAsset(Context context, String str) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            return fromUtf8Bytes(f.b(inputStream));
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static boolean maybeInflate(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, Inflater inflater) {
        if (parsableByteArray.bytesLeft() <= 0 || parsableByteArray.peekUnsignedByte() != 120 || !inflate(parsableByteArray, parsableByteArray2, inflater)) {
            return false;
        }
        return true;
    }

    private static String maybeReplaceLegacyLanguageTags(String str) {
        int i2 = 0;
        while (true) {
            String[] strArr = isoLegacyTagReplacements;
            if (i2 >= strArr.length) {
                return str;
            }
            if (str.startsWith(strArr[i2])) {
                return strArr[i2 + 1] + str.substring(strArr[i2].length());
            }
            i2 += 2;
        }
    }

    public static long minValue(SparseLongArray sparseLongArray) {
        if (sparseLongArray.size() != 0) {
            long j2 = Long.MAX_VALUE;
            for (int i2 = 0; i2 < sparseLongArray.size(); i2++) {
                j2 = Math.min(j2, sparseLongArray.valueAt(i2));
            }
            return j2;
        }
        throw new NoSuchElementException();
    }

    public static long msToUs(long j2) {
        if (j2 == -9223372036854775807L || j2 == Long.MIN_VALUE) {
            return j2;
        }
        return j2 * 1000;
    }

    public static ExecutorService newSingleThreadExecutor(String str) {
        return Executors.newSingleThreadExecutor(new c(str, 1));
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String str) {
        return Executors.newSingleThreadScheduledExecutor(new c(str, 0));
    }

    public static String normalizeLanguageCode(String str) {
        if (str == null) {
            return null;
        }
        String replace = str.replace('_', '-');
        if (!replace.isEmpty() && !replace.equals("und")) {
            str = replace;
        }
        String S = k.S(str);
        String str2 = splitAtFirst(S, "-")[0];
        if (languageTagReplacementMap == null) {
            languageTagReplacementMap = createIsoLanguageReplacementMap();
        }
        String str3 = languageTagReplacementMap.get(str2);
        if (str3 != null) {
            StringBuilder s = C0212a.s(str3);
            s.append(S.substring(str2.length()));
            S = s.toString();
            str2 = str3;
        }
        if ("no".equals(str2) || "i".equals(str2) || "zh".equals(str2)) {
            return maybeReplaceLegacyLanguageTags(S);
        }
        return S;
    }

    public static <T> T[] nullSafeArrayConcatenation(T[] tArr, T[] tArr2) {
        T[] copyOf = Arrays.copyOf(tArr, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, copyOf, tArr.length, tArr2.length);
        return copyOf;
    }

    public static <T> T[] nullSafeArrayCopy(T[] tArr, int i2) {
        boolean z;
        if (i2 <= tArr.length) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        return Arrays.copyOf(tArr, i2);
    }

    public static <T> T[] nullSafeArrayCopyOfRange(T[] tArr, int i2, int i7) {
        boolean z;
        boolean z3 = false;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (i7 <= tArr.length) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        return Arrays.copyOfRange(tArr, i2, i7);
    }

    public static int percentInt(long j2, long j3) {
        long j8;
        long w = a.w(j2, 100);
        if (w == Long.MAX_VALUE || w == Long.MIN_VALUE) {
            j8 = j2 / (j3 / 100);
        } else {
            j8 = w / j3;
        }
        return C0246a.N(j8);
    }

    public static boolean postOrRun(Handler handler, Runnable runnable) {
        Looper looper = handler.getLooper();
        if (!looper.getThread().isAlive()) {
            return false;
        }
        if (looper != Looper.myLooper()) {
            return handler.post(runnable);
        }
        runnable.run();
        return true;
    }

    public static long sampleCountToDurationUs(long j2, int i2) {
        return scaleLargeValue(j2, 1000000, (long) i2, RoundingMode.DOWN);
    }

    public static long scaleLargeTimestamp(long j2, long j3, long j8) {
        return scaleLargeValue(j2, j3, j8, RoundingMode.DOWN);
    }

    public static void scaleLargeTimestampsInPlace(long[] jArr, long j2, long j3) {
        scaleLargeValuesInPlace(jArr, j2, j3, RoundingMode.DOWN);
    }

    public static long scaleLargeValue(long j2, long j3, long j8, RoundingMode roundingMode) {
        if (j2 == 0 || j3 == 0) {
            return 0;
        }
        int i2 = (j8 > j3 ? 1 : (j8 == j3 ? 0 : -1));
        if (i2 >= 0 && j8 % j3 == 0) {
            return a.m(j2, a.m(j8, j3, RoundingMode.UNNECESSARY), roundingMode);
        }
        if (i2 < 0 && j3 % j8 == 0) {
            return a.w(j2, a.m(j3, j8, RoundingMode.UNNECESSARY));
        }
        int i7 = (j8 > j2 ? 1 : (j8 == j2 ? 0 : -1));
        if (i7 >= 0 && j8 % j2 == 0) {
            return a.m(j3, a.m(j8, j2, RoundingMode.UNNECESSARY), roundingMode);
        }
        if (i7 >= 0 || j2 % j8 != 0) {
            return scaleLargeValueFallback(j2, j3, j8, roundingMode);
        }
        return a.w(j3, a.m(j2, j8, RoundingMode.UNNECESSARY));
    }

    private static long scaleLargeValueFallback(long j2, long j3, long j8, RoundingMode roundingMode) {
        long w = a.w(j2, j3);
        if (w != Long.MAX_VALUE && w != Long.MIN_VALUE) {
            return a.m(w, j8, roundingMode);
        }
        long n = a.n(Math.abs(j3), Math.abs(j8));
        RoundingMode roundingMode2 = RoundingMode.UNNECESSARY;
        long m = a.m(j3, n, roundingMode2);
        long m4 = a.m(j8, n, roundingMode2);
        long n3 = a.n(Math.abs(j2), Math.abs(m4));
        long m8 = a.m(j2, n3, roundingMode2);
        long m9 = a.m(m4, n3, roundingMode2);
        long w6 = a.w(m8, m);
        if (w6 != Long.MAX_VALUE && w6 != Long.MIN_VALUE) {
            return a.m(w6, m9, roundingMode);
        }
        double d = ((double) m8) * (((double) m) / ((double) m9));
        if (d > 9.223372036854776E18d) {
            return Long.MAX_VALUE;
        }
        if (d < -9.223372036854776E18d) {
            return Long.MIN_VALUE;
        }
        return b.d(d, roundingMode);
    }

    public static void scaleLargeValuesInPlace(long[] jArr, long j2, long j3, RoundingMode roundingMode) {
        if (j2 == 0) {
            Arrays.fill(jArr, 0);
            return;
        }
        int i2 = (j3 > j2 ? 1 : (j3 == j2 ? 0 : -1));
        int i7 = 0;
        if (i2 >= 0 && j3 % j2 == 0) {
            long m = a.m(j3, j2, RoundingMode.UNNECESSARY);
            while (i7 < jArr.length) {
                jArr[i7] = a.m(jArr[i7], m, roundingMode);
                i7++;
            }
        } else if (i2 >= 0 || j2 % j3 != 0) {
            for (int i8 = 0; i8 < jArr.length; i8++) {
                long j8 = jArr[i8];
                if (j8 != 0) {
                    if (j3 >= j8 && j3 % j8 == 0) {
                        jArr[i8] = a.m(j2, a.m(j3, j8, RoundingMode.UNNECESSARY), roundingMode);
                    } else if (j3 >= j8 || j8 % j3 != 0) {
                        jArr[i8] = scaleLargeValueFallback(j8, j2, j3, roundingMode);
                    } else {
                        jArr[i8] = a.w(j2, a.m(j8, j3, RoundingMode.UNNECESSARY));
                    }
                }
            }
        } else {
            long m4 = a.m(j2, j3, RoundingMode.UNNECESSARY);
            while (i7 < jArr.length) {
                jArr[i7] = a.w(jArr[i7], m4);
                i7++;
            }
        }
    }

    public static String[] split(String str, String str2) {
        return str.split(str2, -1);
    }

    public static String[] splitAtFirst(String str, String str2) {
        return str.split(str2, 2);
    }

    public static String[] splitCodecs(String str) {
        if (TextUtils.isEmpty(str)) {
            return new String[0];
        }
        return split(str.trim(), "(\\s*,\\s*)");
    }

    public static long subtractWithOverflowDefault(long j2, long j3, long j8) {
        long j10 = j2 - j3;
        if (((j2 ^ j10) & (j3 ^ j2)) < 0) {
            return j8;
        }
        return j10;
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb2 = new StringBuilder(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb2.append(Character.forDigit((bArr[i2] >> 4) & 15, 16));
            sb2.append(Character.forDigit(bArr[i2] & 15, 16));
        }
        return sb2.toString();
    }

    public static long toLong(int i2, int i7) {
        return toUnsignedLong(i7) | (toUnsignedLong(i2) << 32);
    }

    public static long toUnsignedLong(int i2) {
        return ((long) i2) & 4294967295L;
    }

    public static long usToMs(long j2) {
        if (j2 == -9223372036854775807L || j2 == Long.MIN_VALUE) {
            return j2;
        }
        return j2 / 1000;
    }

    public static long constrainValue(long j2, long j3, long j8) {
        return Math.max(j3, Math.min(j2, j8));
    }

    public static Handler createHandlerForCurrentLooper(Handler.Callback callback) {
        return createHandler((Looper) Assertions.checkStateNotNull(Looper.myLooper()), callback);
    }

    public static Handler createHandlerForCurrentOrMainLooper(Handler.Callback callback) {
        return createHandler(getCurrentOrMainLooper(), callback);
    }

    public static String fromUtf8Bytes(byte[] bArr, int i2, int i7) {
        return new String(bArr, i2, i7, StandardCharsets.UTF_8);
    }

    public static int getPcmEncoding(int i2, ByteOrder byteOrder) {
        if (i2 == 8) {
            return 3;
        }
        if (i2 == 16) {
            return byteOrder.equals(ByteOrder.LITTLE_ENDIAN) ? 2 : 268435456;
        }
        if (i2 == 24) {
            return byteOrder.equals(ByteOrder.LITTLE_ENDIAN) ? 21 : 1342177280;
        }
        if (i2 != 32) {
            return 0;
        }
        return byteOrder.equals(ByteOrder.LITTLE_ENDIAN) ? 22 : 1610612736;
    }

    public static float constrainValue(float f, float f5, float f8) {
        return Math.max(f5, Math.min(f, f8));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int binarySearchFloor(long[] r4, long r5, boolean r7, boolean r8) {
        /*
            int r0 = java.util.Arrays.binarySearch(r4, r5)
            if (r0 >= 0) goto L_0x000a
            int r0 = r0 + 2
            int r4 = -r0
            goto L_0x001b
        L_0x000a:
            int r1 = r0 + -1
            if (r1 < 0) goto L_0x0016
            r2 = r4[r1]
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0016
            r0 = r1
            goto L_0x000a
        L_0x0016:
            if (r7 == 0) goto L_0x001a
            r4 = r0
            goto L_0x001b
        L_0x001a:
            r4 = r1
        L_0x001b:
            if (r8 == 0) goto L_0x0022
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
        L_0x0022:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.Util.binarySearchFloor(long[], long, boolean, boolean):int");
    }

    public static int binarySearchFloor(LongArray longArray, long j2, boolean z, boolean z3) {
        int i2;
        int size = longArray.size() - 1;
        int i7 = 0;
        while (i7 <= size) {
            int i8 = (i7 + size) >>> 1;
            if (longArray.get(i8) < j2) {
                i7 = i8 + 1;
            } else {
                size = i8 - 1;
            }
        }
        if (z && (i2 = size + 1) < longArray.size() && longArray.get(i2) == j2) {
            return i2;
        }
        if (!z3 || size != -1) {
            return size;
        }
        return 0;
    }

    public static Point getCurrentDisplayModeSize(Context context, Display display) {
        if (display.getDisplayId() == 0 && isTv(context)) {
            String systemProperty = getSystemProperty("vendor.display-size");
            if (!TextUtils.isEmpty(systemProperty)) {
                try {
                    String[] split = split(systemProperty.trim(), "x");
                    if (split.length == 2) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        if (parseInt > 0 && parseInt2 > 0) {
                            return new Point(parseInt, parseInt2);
                        }
                    }
                } catch (NumberFormatException unused) {
                }
                Log.e("Util", "Invalid display size: " + systemProperty);
            }
            if ("Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
                return new Point(3840, 2160);
            }
        }
        Point point = new Point();
        getDisplaySizeV23(display, point);
        return point;
    }

    public static <T> T castNonNull(T t) {
        return t;
    }

    public static <T> T[] castNonNullTypeArray(T[] tArr) {
        return tArr;
    }
}
