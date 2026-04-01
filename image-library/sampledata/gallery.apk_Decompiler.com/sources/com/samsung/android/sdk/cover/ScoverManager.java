package com.samsung.android.sdk.cover;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.Window;
import android.view.WindowManager;
import c0.C0086a;
import com.samsung.android.cover.CoverState;
import com.samsung.android.cover.ICoverManager;
import com.samsung.android.sdk.SsdkUnsupportedException;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScoverManager {
    public static final int COVER_MODE_HIDE_SVIEW_ONCE = 2;
    public static final int COVER_MODE_NONE = 0;
    public static final int COVER_MODE_SVIEW = 1;
    private static final String FEATURE_COVER_CLEAR = "com.sec.feature.cover.clearcover";
    private static final String FEATURE_COVER_CLEAR_CAMERA_VIEW = "com.sec.feature.cover.clearcameraviewcover";
    private static final String FEATURE_COVER_CLEAR_SIDE_VIEW = "com.sec.feature.cover.clearsideviewcover";
    private static final String FEATURE_COVER_FLIP = "com.sec.feature.cover.flip";
    private static final String FEATURE_COVER_LED_BACK = "com.sec.feature.cover.ledbackcover";
    private static final String FEATURE_COVER_MINI_SVIEW_WALLET = "com.sec.feature.cover.minisviewwalletcover";
    private static final String FEATURE_COVER_NEON = "com.sec.feature.cover.neoncover";
    private static final String FEATURE_COVER_NFCLED = "com.sec.feature.cover.nfcledcover";
    private static final String FEATURE_COVER_SVIEW = "com.sec.feature.cover.sview";
    static final int SDK_VERSION = 16842752;
    private static final String TAG = "ScoverManager";
    private static boolean sIsClearCameraViewCoverSystemFeatureEnabled = false;
    private static boolean sIsClearCoverSystemFeatureEnabled = false;
    private static boolean sIsClearSideViewCoverSystemFeatureEnabled = false;
    private static boolean sIsFilpCoverSystemFeatureEnabled = false;
    private static boolean sIsLEDBackCoverSystemFeatureEnabled = false;
    private static boolean sIsMiniSviewWalletCoverSysltemFeatureEnabled = false;
    private static boolean sIsNeonCoverSystemFeatureEnabled = false;
    private static boolean sIsNfcLedCoverSystemFeatureEnabled = false;
    private static boolean sIsSViewCoverSystemFeatureEnabled = false;
    private static boolean sIsSystemFeatureQueried = false;
    private static int sServiceVersion = 16777216;
    private Context mContext;
    private final CopyOnWriteArrayList<CoverPowerKeyListenerDelegate> mCoverPowerKeyListenerDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<CoverStateListenerDelegate> mCoverStateListenerDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<CoverListenerDelegate> mLcdOffDisableDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<LedSystemEventListenerDelegate> mLedSystemEventListenerDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<LegacyLedSystemEventListenerDelegate> mLegacyLedSystemEventListenerDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<CoverListenerDelegate> mListenerDelegates = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<NfcLedCoverTouchListenerDelegate> mNfcLedCoverTouchListenerDelegates = new CopyOnWriteArrayList<>();
    private ICoverManager mService;
    private IBinder mToken = new Binder();

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ScoverStateListener {
        void onCoverStateChanged(ScoverState scoverState);
    }

    public ScoverManager(Context context) {
        this.mContext = context;
        initSystemFeature();
    }

    private synchronized ICoverManager getService() {
        try {
            if (this.mService == null) {
                ICoverManager asInterface = ICoverManager.Stub.asInterface(ServiceManager.getService("cover"));
                this.mService = asInterface;
                if (asInterface == null) {
                    Slog.w(TAG, "warning: no COVER_MANAGER_SERVICE");
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mService;
    }

    private void initSystemFeature() {
        if (!sIsSystemFeatureQueried) {
            sIsFilpCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_FLIP);
            sIsSViewCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_SVIEW);
            sIsNfcLedCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_NFCLED);
            sIsClearCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_CLEAR);
            sIsNeonCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_NEON);
            sIsClearSideViewCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_CLEAR_SIDE_VIEW);
            sIsLEDBackCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_LED_BACK);
            sIsMiniSviewWalletCoverSysltemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_MINI_SVIEW_WALLET);
            sIsClearCameraViewCoverSystemFeatureEnabled = this.mContext.getPackageManager().hasSystemFeature(FEATURE_COVER_CLEAR_CAMERA_VIEW);
            sIsSystemFeatureQueried = true;
            sServiceVersion = getCoverManagerVersion();
        }
    }

    public static boolean isSupportableVersion(int i2) {
        int i7 = (i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER;
        int i8 = (i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER;
        int i10 = i2 & 65535;
        int i11 = sServiceVersion;
        int i12 = (i11 >> 24) & ScoverState.TYPE_NFC_SMART_COVER;
        int i13 = (i11 >> 16) & ScoverState.TYPE_NFC_SMART_COVER;
        int i14 = 65535 & i11;
        if (i12 < i7 || i13 < i8 || i14 < i10) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: type inference failed for: r2v4, types: [com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate, java.lang.Object] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void registerLegacyLedSystemListener(com.samsung.android.sdk.cover.ScoverManager.LedSystemEventListener r6) {
        /*
            r5 = this;
            java.lang.String r0 = "ScoverManager"
            if (r6 != 0) goto L_0x000a
            java.lang.String r5 = "registerLegacyLedSystemListener : listener is null"
            android.util.Log.w(r0, r5)
            return
        L_0x000a:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate> r1 = r5.mLegacyLedSystemEventListenerDelegates
            java.util.Iterator r1 = r1.iterator()
        L_0x0010:
            boolean r2 = r1.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0028
            java.lang.Object r2 = r1.next()
            com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate r2 = (com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate) r2
            java.lang.Object r4 = r2.getListener()
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0010
            goto L_0x0029
        L_0x0028:
            r2 = r3
        L_0x0029:
            if (r2 != 0) goto L_0x0037
            com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate r2 = new com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate
            android.content.Context r1 = r5.mContext
            r2.<init>(r6, r3, r1)
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate> r6 = r5.mLegacyLedSystemEventListenerDelegates
            r6.add(r2)
        L_0x0037:
            com.samsung.android.cover.ICoverManager r6 = r5.getService()     // Catch:{ RemoteException -> 0x0055 }
            if (r6 == 0) goto L_0x0057
            android.content.ComponentName r1 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x0055 }
            android.content.Context r3 = r5.mContext     // Catch:{ RemoteException -> 0x0055 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ RemoteException -> 0x0055 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ RemoteException -> 0x0055 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x0055 }
            r1.<init>(r3, r5)     // Catch:{ RemoteException -> 0x0055 }
            r5 = 4
            r6.registerNfcTouchListenerCallback(r5, r2, r1)     // Catch:{ RemoteException -> 0x0055 }
            return
        L_0x0055:
            r5 = move-exception
            goto L_0x0058
        L_0x0057:
            return
        L_0x0058:
            java.lang.String r6 = "RemoteException in registerLegacyLedSystemListener: "
            android.util.Log.e(r0, r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerLegacyLedSystemListener(com.samsung.android.sdk.cover.ScoverManager$LedSystemEventListener):void");
    }

    private static boolean supportNewLedSystemEventListener() {
        return isSupportableVersion(17104896);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void unregisterLegacyLedSystemEventListener(com.samsung.android.sdk.cover.ScoverManager.LedSystemEventListener r5) {
        /*
            r4 = this;
            java.lang.String r0 = "unregisterLegacyLedSystemEventListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            if (r5 != 0) goto L_0x0011
            java.lang.String r4 = "unregisterLegacyLedSystemEventListener : listener is null"
            android.util.Log.w(r1, r4)
            return
        L_0x0011:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate> r0 = r4.mLegacyLedSystemEventListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate r2 = (com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate) r2
            java.lang.Object r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0017
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            if (r2 != 0) goto L_0x0032
            goto L_0x0046
        L_0x0032:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x0044 }
            if (r5 == 0) goto L_0x0046
            boolean r5 = r5.unregisterNfcTouchListenerCallback(r2)     // Catch:{ RemoteException -> 0x0044 }
            if (r5 == 0) goto L_0x0046
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LegacyLedSystemEventListenerDelegate> r4 = r4.mLegacyLedSystemEventListenerDelegates     // Catch:{ RemoteException -> 0x0044 }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x0044 }
            return
        L_0x0044:
            r4 = move-exception
            goto L_0x0047
        L_0x0046:
            return
        L_0x0047:
            java.lang.String r5 = "RemoteException in unregisterLegacyLedSystemEventListener: "
            android.util.Log.e(r1, r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterLegacyLedSystemEventListener(com.samsung.android.sdk.cover.ScoverManager$LedSystemEventListener):void");
    }

    public void addLedNotification(Bundle bundle) {
        if (!isSupportCover()) {
            Log.w(TAG, "addLedNotification : This device does not support cover");
        } else if (!isSupportNfcLedCover()) {
            Log.w(TAG, "addLedNotification : This device does not support NFC Led cover");
        } else if (!isSupportableVersion(17039360)) {
            throw new SsdkUnsupportedException("This device does not support this function. Device is must higher then v1.4.0", 2);
        } else if (bundle == null) {
            Log.e(TAG, "addLedNotification : Null notification data!");
        } else {
            ICoverManager service = getService();
            if (service != null) {
                try {
                    service.addLedNotification(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, "addLedNotification in sendData to NFC : ", e);
                }
            }
        }
    }

    public boolean checkValidPacakge(String str) {
        if (!isSupportCover()) {
            Log.w(TAG, "checkValidPacakge : This device is not supported cover");
            return false;
        } else if (str == null) {
            Log.w(TAG, "checkValidPacakge : pkg is null");
            return false;
        } else {
            try {
                ICoverManager service = getService();
                if (service != null) {
                    CoverState coverState = service.getCoverState();
                    if (coverState == null || !coverState.attached) {
                        Log.e(TAG, "checkValidPacakge : coverState is null or cover is detached");
                    } else {
                        String smartCoverAppUri = coverState.getSmartCoverAppUri();
                        if (TextUtils.isEmpty(smartCoverAppUri) || !str.equals(smartCoverAppUri.substring(1, smartCoverAppUri.length()))) {
                            return false;
                        }
                        return true;
                    }
                }
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException in checkCoverAppUri: ", e);
            }
            return false;
        }
    }

    public void disableCoverManager(boolean z) {
        if (!isSupportCover()) {
            Log.w(TAG, "disableCoverManager : This device is not supported cover");
            return;
        }
        try {
            ICoverManager service = getService();
            if (service != null) {
                service.disableCoverManager(z, this.mToken, this.mContext.getPackageName());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in disalbeCoverManager : ", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean disableLcdOffByCover(com.samsung.android.sdk.cover.ScoverManager.StateListener r7) {
        /*
            r6 = this;
            boolean r0 = r6.isSupportCover()
            r1 = 0
            java.lang.String r2 = "ScoverManager"
            if (r0 != 0) goto L_0x000f
            java.lang.String r6 = "disableLcdOffByCover : This device does not support cover"
            android.util.Log.w(r2, r6)
            return r1
        L_0x000f:
            r0 = 17104896(0x1050000, float:2.4428242E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x0079
            if (r7 != 0) goto L_0x001f
            java.lang.String r6 = "disableLcdOffByCover : listener cannot be null"
            android.util.Log.w(r2, r6)
            return r1
        L_0x001f:
            java.lang.String r0 = "disableLcdOffByCover"
            android.util.Log.d(r2, r0)
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r0 = r6.mLcdOffDisableDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x002a:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0042
            java.lang.Object r3 = r0.next()
            com.samsung.android.sdk.cover.CoverListenerDelegate r3 = (com.samsung.android.sdk.cover.CoverListenerDelegate) r3
            com.samsung.android.sdk.cover.ScoverManager$StateListener r5 = r3.getListener()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x002a
            goto L_0x0043
        L_0x0042:
            r3 = r4
        L_0x0043:
            if (r3 != 0) goto L_0x004c
            com.samsung.android.sdk.cover.CoverListenerDelegate r3 = new com.samsung.android.sdk.cover.CoverListenerDelegate
            android.content.Context r0 = r6.mContext
            r3.<init>(r7, r4, r0)
        L_0x004c:
            com.samsung.android.cover.ICoverManager r7 = r6.getService()     // Catch:{ RemoteException -> 0x0072 }
            if (r7 == 0) goto L_0x0078
            android.content.ComponentName r0 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x0072 }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x0072 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x0072 }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x0072 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x0072 }
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0072 }
            boolean r7 = r7.disableLcdOffByCover(r3, r0)     // Catch:{ RemoteException -> 0x0072 }
            if (r7 == 0) goto L_0x0078
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r6 = r6.mLcdOffDisableDelegates     // Catch:{ RemoteException -> 0x0072 }
            r6.add(r3)     // Catch:{ RemoteException -> 0x0072 }
            r6 = 1
            return r6
        L_0x0072:
            r6 = move-exception
            java.lang.String r7 = "RemoteException in disableLcdOffByCover: "
            android.util.Log.e(r2, r7, r6)
        L_0x0078:
            return r1
        L_0x0079:
            com.samsung.android.sdk.SsdkUnsupportedException r6 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r7 = "This device does not support this function. Device is must higher then v1.5.0"
            r0 = 2
            r6.<init>(r7, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.disableLcdOffByCover(com.samsung.android.sdk.cover.ScoverManager$StateListener):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enableLcdOffByCover(com.samsung.android.sdk.cover.ScoverManager.StateListener r7) {
        /*
            r6 = this;
            boolean r0 = r6.isSupportCover()
            r1 = 0
            java.lang.String r2 = "ScoverManager"
            if (r0 != 0) goto L_0x000f
            java.lang.String r6 = "enableLcdOffByCover : This device does not support cover"
            android.util.Log.w(r2, r6)
            return r1
        L_0x000f:
            r0 = 17104896(0x1050000, float:2.4428242E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x0077
            if (r7 != 0) goto L_0x001f
            java.lang.String r6 = "enableLcdOffByCover : listener cannot be null"
            android.util.Log.w(r2, r6)
            return r1
        L_0x001f:
            java.lang.String r0 = "enableLcdOffByCover"
            android.util.Log.d(r2, r0)
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r0 = r6.mLcdOffDisableDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x002a:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0041
            java.lang.Object r3 = r0.next()
            com.samsung.android.sdk.cover.CoverListenerDelegate r3 = (com.samsung.android.sdk.cover.CoverListenerDelegate) r3
            com.samsung.android.sdk.cover.ScoverManager$StateListener r4 = r3.getListener()
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x002a
            goto L_0x0042
        L_0x0041:
            r3 = 0
        L_0x0042:
            if (r3 != 0) goto L_0x004a
            java.lang.String r6 = "enableLcdOffByCover: Matching listener not found, cannot enable"
            android.util.Log.e(r2, r6)
            return r1
        L_0x004a:
            com.samsung.android.cover.ICoverManager r7 = r6.getService()     // Catch:{ RemoteException -> 0x0070 }
            if (r7 == 0) goto L_0x0076
            android.content.ComponentName r0 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x0070 }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x0070 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x0070 }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x0070 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x0070 }
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0070 }
            boolean r7 = r7.enableLcdOffByCover(r3, r0)     // Catch:{ RemoteException -> 0x0070 }
            if (r7 == 0) goto L_0x0076
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r6 = r6.mLcdOffDisableDelegates     // Catch:{ RemoteException -> 0x0070 }
            r6.remove(r3)     // Catch:{ RemoteException -> 0x0070 }
            r6 = 1
            return r6
        L_0x0070:
            r6 = move-exception
            java.lang.String r7 = "RemoteException in unregisterNfcTouchListener: "
            android.util.Log.e(r2, r7, r6)
        L_0x0076:
            return r1
        L_0x0077:
            com.samsung.android.sdk.SsdkUnsupportedException r6 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r7 = "This device does not support this function. Device is must higher then v1.5.0"
            r0 = 2
            r6.<init>(r7, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.enableLcdOffByCover(com.samsung.android.sdk.cover.ScoverManager$StateListener):boolean");
    }

    public int getCoverManagerVersion() {
        int i2;
        if (isSupportCover()) {
            try {
                i2 = ((Integer) ICoverManager.class.getMethod("getVersion", (Class[]) null).invoke(getService(), (Object[]) null)).intValue();
            } catch (Exception e) {
                Log.w(TAG, "getVersion failed : " + e);
            }
            C0086a.C(i2, "serviceVersion : ", TAG);
            return i2;
        }
        i2 = 16777216;
        C0086a.C(i2, "serviceVersion : ", TAG);
        return i2;
    }

    public ScoverState getCoverState() {
        ScoverState scoverState;
        if (!isSupportCover()) {
            Log.w(TAG, "getCoverState : This device is not supported cover");
            return null;
        }
        try {
            ICoverManager service = getService();
            if (service != null) {
                CoverState coverState = service.getCoverState();
                if (coverState == null) {
                    Log.e(TAG, "getCoverState : coverState is null");
                } else if (coverState.type != 255 || coverState.switchState) {
                    if (isSupportableVersion(17498112)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached, coverState.model, coverState.fakeCover, coverState.fotaMode);
                    } else if (isSupportableVersion(17235968)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached, coverState.model, coverState.fakeCover);
                    } else if (isSupportableVersion(16908288)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached, coverState.model);
                    } else if (isSupportableVersion(SDK_VERSION)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached);
                    } else {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel);
                    }
                    if (isSupportableVersion(17563648)) {
                        scoverState.setVisibleRect(coverState.getVisibleRect());
                    }
                    return scoverState;
                } else {
                    Log.e(TAG, "getCoverState : type of cover is nfc smart cover and cover is closed");
                    return null;
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in getCoverState: ", e);
        }
        return null;
    }

    public String getServiceVersionName() {
        int i2 = sServiceVersion;
        return String.format("%d.%d.%d", new Object[]{Integer.valueOf((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER), Integer.valueOf((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER), Integer.valueOf(i2 & 65535)});
    }

    public boolean isCoverManagerDisabled() {
        if (!isSupportCover()) {
            Log.w(TAG, "isCoverManagerDisabled : This device is not supported cover");
            return false;
        }
        try {
            ICoverManager service = getService();
            if (service != null) {
                return service.isCoverManagerDisabled();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in isCoverManagerDisabled : ", e);
            return false;
        }
    }

    public boolean isSmartCover() {
        ScoverState coverState = getCoverState();
        if (coverState == null || coverState.type != 255) {
            return false;
        }
        return true;
    }

    public boolean isSupportClearCameraViewCover() {
        return sIsClearCameraViewCoverSystemFeatureEnabled;
    }

    public boolean isSupportClearCover() {
        return sIsClearCoverSystemFeatureEnabled;
    }

    public boolean isSupportClearSideViewCover() {
        return sIsClearSideViewCoverSystemFeatureEnabled;
    }

    public boolean isSupportCover() {
        if (sIsFilpCoverSystemFeatureEnabled || sIsSViewCoverSystemFeatureEnabled || sIsClearCoverSystemFeatureEnabled || sIsNeonCoverSystemFeatureEnabled || sIsClearSideViewCoverSystemFeatureEnabled || sIsNfcLedCoverSystemFeatureEnabled || sIsLEDBackCoverSystemFeatureEnabled || sIsMiniSviewWalletCoverSysltemFeatureEnabled || sIsClearCameraViewCoverSystemFeatureEnabled) {
            return true;
        }
        return false;
    }

    public boolean isSupportFlipCover() {
        return sIsFilpCoverSystemFeatureEnabled;
    }

    public boolean isSupportLEDBackCover() {
        return sIsLEDBackCoverSystemFeatureEnabled;
    }

    public boolean isSupportNeonCover() {
        return sIsNeonCoverSystemFeatureEnabled;
    }

    public boolean isSupportNfcLedCover() {
        return sIsNfcLedCoverSystemFeatureEnabled;
    }

    public boolean isSupportSViewCover() {
        return sIsSViewCoverSystemFeatureEnabled;
    }

    public boolean isSupportTypeOfCover(int i2) {
        if (i2 == 0) {
            return sIsFilpCoverSystemFeatureEnabled;
        }
        if (i2 == 1 || i2 == 3) {
            return sIsSViewCoverSystemFeatureEnabled;
        }
        if (i2 == 11) {
            return sIsNeonCoverSystemFeatureEnabled;
        }
        if (i2 == 17) {
            return sIsClearCameraViewCoverSystemFeatureEnabled;
        }
        if (i2 == 7) {
            return sIsNfcLedCoverSystemFeatureEnabled;
        }
        if (i2 == 8) {
            return sIsClearCoverSystemFeatureEnabled;
        }
        if (i2 == 14) {
            return sIsLEDBackCoverSystemFeatureEnabled;
        }
        if (i2 != 15) {
            return false;
        }
        return sIsClearSideViewCoverSystemFeatureEnabled;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerCoverPowerKeyListener(com.samsung.android.sdk.cover.ScoverManager.CoverPowerKeyListener r7) {
        /*
            r6 = this;
            boolean r0 = r6.isSupportCover()
            java.lang.String r1 = "ScoverManager"
            if (r0 != 0) goto L_0x000e
            java.lang.String r6 = "registerCoverPowerKeyListener : This device does not support cover"
            android.util.Log.w(r1, r6)
            return
        L_0x000e:
            java.lang.String r0 = "registerCoverPowerKeyListener"
            android.util.Log.d(r1, r0)
            boolean r0 = r6.isSupportFlipCover()
            if (r0 != 0) goto L_0x0025
            boolean r0 = r6.isSupportNeonCover()
            if (r0 != 0) goto L_0x0025
            java.lang.String r6 = "registerLedSystemListener : This device does not support Flip cover or Neon Cover"
            android.util.Log.w(r1, r6)
            return
        L_0x0025:
            r0 = 17432576(0x10a0000, float:2.5346597E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x008e
            if (r7 != 0) goto L_0x0035
            java.lang.String r6 = "registerCoverPowerKeyListener : listener is null"
            android.util.Log.w(r1, r6)
            return
        L_0x0035:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate> r0 = r6.mCoverPowerKeyListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x003b:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0054
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate r2 = (com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate) r2
            java.lang.Object r4 = r2.getListener()
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x003b
            r0 = 1
            goto L_0x0056
        L_0x0054:
            r0 = 0
            r2 = r3
        L_0x0056:
            if (r2 != 0) goto L_0x005f
            com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate r2 = new com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate
            android.content.Context r4 = r6.mContext
            r2.<init>(r7, r3, r4)
        L_0x005f:
            com.samsung.android.cover.ICoverManager r7 = r6.getService()     // Catch:{ RemoteException -> 0x0085 }
            if (r7 == 0) goto L_0x0087
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x0085 }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x0085 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x0085 }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x0085 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x0085 }
            r3.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0085 }
            r4 = 10
            r7.registerNfcTouchListenerCallback(r4, r2, r3)     // Catch:{ RemoteException -> 0x0085 }
            if (r0 != 0) goto L_0x0087
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate> r6 = r6.mCoverPowerKeyListenerDelegates     // Catch:{ RemoteException -> 0x0085 }
            r6.add(r2)     // Catch:{ RemoteException -> 0x0085 }
            return
        L_0x0085:
            r6 = move-exception
            goto L_0x0088
        L_0x0087:
            return
        L_0x0088:
            java.lang.String r7 = "RemoteException in registerCoverPowerKeyListener: "
            android.util.Log.e(r1, r7, r6)
            return
        L_0x008e:
            com.samsung.android.sdk.SsdkUnsupportedException r6 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r7 = "This device does not support this function. Device is must higher then v1.10.0 for Flip Cover and Neon cover"
            r0 = 2
            r6.<init>(r7, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerCoverPowerKeyListener(com.samsung.android.sdk.cover.ScoverManager$CoverPowerKeyListener):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.LedSystemEventListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.samsung.android.sdk.cover.LedSystemEventListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerLedSystemListener(com.samsung.android.sdk.cover.ScoverManager.LedSystemEventListener r7) {
        /*
            r6 = this;
            boolean r0 = r6.isSupportCover()
            java.lang.String r1 = "ScoverManager"
            if (r0 != 0) goto L_0x000e
            java.lang.String r6 = "registerLedSystemListener : This device does not support cover"
            android.util.Log.w(r1, r6)
            return
        L_0x000e:
            java.lang.String r0 = "registerLedSystemListener"
            android.util.Log.d(r1, r0)
            boolean r0 = r6.isSupportNfcLedCover()
            if (r0 != 0) goto L_0x0025
            boolean r0 = r6.isSupportNeonCover()
            if (r0 != 0) goto L_0x0025
            java.lang.String r6 = "registerLedSystemListener : This device does not support NFC Led cover or Neon Cover"
            android.util.Log.w(r1, r6)
            return
        L_0x0025:
            boolean r0 = r6.isSupportNfcLedCover()
            if (r0 == 0) goto L_0x0033
            r0 = 16973824(0x1030000, float:2.40609E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 != 0) goto L_0x0041
        L_0x0033:
            boolean r0 = r6.isSupportNeonCover()
            if (r0 == 0) goto L_0x00ab
            r0 = 17301504(0x1080000, float:2.4979255E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x00ab
        L_0x0041:
            if (r7 != 0) goto L_0x0049
            java.lang.String r6 = "registerLedSystemListener : listener is null"
            android.util.Log.w(r1, r6)
            return
        L_0x0049:
            boolean r0 = supportNewLedSystemEventListener()
            if (r0 != 0) goto L_0x0053
            r6.registerLegacyLedSystemListener(r7)
            return
        L_0x0053:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LedSystemEventListenerDelegate> r0 = r6.mLedSystemEventListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0059:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0072
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.LedSystemEventListenerDelegate r2 = (com.samsung.android.sdk.cover.LedSystemEventListenerDelegate) r2
            java.lang.Object r4 = r2.getListener()
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x0059
            r0 = 1
            goto L_0x0074
        L_0x0072:
            r0 = 0
            r2 = r3
        L_0x0074:
            if (r2 != 0) goto L_0x007d
            com.samsung.android.sdk.cover.LedSystemEventListenerDelegate r2 = new com.samsung.android.sdk.cover.LedSystemEventListenerDelegate
            android.content.Context r4 = r6.mContext
            r2.<init>(r7, r3, r4)
        L_0x007d:
            com.samsung.android.cover.ICoverManager r7 = r6.getService()     // Catch:{ RemoteException -> 0x00a2 }
            if (r7 == 0) goto L_0x00a4
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x00a2 }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x00a2 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x00a2 }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x00a2 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x00a2 }
            r3.<init>(r4, r5)     // Catch:{ RemoteException -> 0x00a2 }
            r4 = 4
            r7.registerNfcTouchListenerCallback(r4, r2, r3)     // Catch:{ RemoteException -> 0x00a2 }
            if (r0 != 0) goto L_0x00a4
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LedSystemEventListenerDelegate> r6 = r6.mLedSystemEventListenerDelegates     // Catch:{ RemoteException -> 0x00a2 }
            r6.add(r2)     // Catch:{ RemoteException -> 0x00a2 }
            return
        L_0x00a2:
            r6 = move-exception
            goto L_0x00a5
        L_0x00a4:
            return
        L_0x00a5:
            java.lang.String r7 = "RemoteException in registerLedSystemListener: "
            android.util.Log.e(r1, r7, r6)
            return
        L_0x00ab:
            com.samsung.android.sdk.SsdkUnsupportedException r6 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r7 = "This device does not support this function. Device is must higher then v1.3.0 for NFC LED Cover and v1.8.0 for Neon cover"
            r0 = 2
            r6.<init>(r7, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerLedSystemListener(com.samsung.android.sdk.cover.ScoverManager$LedSystemEventListener):void");
    }

    @Deprecated
    public void registerListener(ScoverStateListener scoverStateListener) {
        Log.d(TAG, "registerListener : Use deprecated API!! Change ScoverStateListener to StateListener");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerNfcTouchListener(int r7, com.samsung.android.sdk.cover.ScoverManager.NfcLedCoverTouchListener r8) {
        /*
            r6 = this;
            boolean r0 = r6.isSupportCover()
            java.lang.String r1 = "ScoverManager"
            if (r0 != 0) goto L_0x000e
            java.lang.String r6 = "registerNfcTouchListener : This device does not support cover"
            android.util.Log.w(r1, r6)
            return
        L_0x000e:
            java.lang.String r0 = "registerNfcTouchListener"
            android.util.Log.d(r1, r0)
            boolean r0 = r6.isSupportNfcLedCover()
            if (r0 != 0) goto L_0x001f
            java.lang.String r6 = "registerNfcTouchListener : This device does not support NFC Led cover"
            android.util.Log.w(r1, r6)
            return
        L_0x001f:
            r0 = 16973824(0x1030000, float:2.40609E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x0086
            if (r8 != 0) goto L_0x002f
            java.lang.String r6 = "registerNfcTouchListener : listener is null"
            android.util.Log.w(r1, r6)
            return
        L_0x002f:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate> r0 = r6.mNfcLedCoverTouchListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0035:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x004e
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate r2 = (com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate) r2
            java.lang.Object r4 = r2.getListener()
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x0035
            r0 = 1
            goto L_0x0050
        L_0x004e:
            r0 = 0
            r2 = r3
        L_0x0050:
            if (r2 != 0) goto L_0x0059
            com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate r2 = new com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate
            android.content.Context r4 = r6.mContext
            r2.<init>(r8, r3, r4)
        L_0x0059:
            com.samsung.android.cover.ICoverManager r8 = r6.getService()     // Catch:{ RemoteException -> 0x007d }
            if (r8 == 0) goto L_0x007f
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x007d }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x007d }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x007d }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x007d }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x007d }
            r3.<init>(r4, r5)     // Catch:{ RemoteException -> 0x007d }
            r8.registerNfcTouchListenerCallback(r7, r2, r3)     // Catch:{ RemoteException -> 0x007d }
            if (r0 != 0) goto L_0x007f
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate> r6 = r6.mNfcLedCoverTouchListenerDelegates     // Catch:{ RemoteException -> 0x007d }
            r6.add(r2)     // Catch:{ RemoteException -> 0x007d }
            return
        L_0x007d:
            r6 = move-exception
            goto L_0x0080
        L_0x007f:
            return
        L_0x0080:
            java.lang.String r7 = "RemoteException in registerNfcTouchListener: "
            android.util.Log.e(r1, r7, r6)
            return
        L_0x0086:
            com.samsung.android.sdk.SsdkUnsupportedException r6 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r7 = "This device does not support this function. Device is must higher then v1.3.0"
            r8 = 2
            r6.<init>(r7, r8)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerNfcTouchListener(int, com.samsung.android.sdk.cover.ScoverManager$NfcLedCoverTouchListener):void");
    }

    public void removeLedNotification(Bundle bundle) {
        if (!isSupportCover()) {
            Log.w(TAG, "removeLedNotification : This device does not support cover");
        } else if (!isSupportNfcLedCover()) {
            Log.w(TAG, "removeLedNotification : This device does not support NFC Led cover");
        } else if (!isSupportableVersion(17039360)) {
            throw new SsdkUnsupportedException("This device does not support this function. Device is must higher then v1.4.0", 2);
        } else if (bundle == null) {
            Log.e(TAG, "removeLedNotification : Null notification data!");
        } else {
            ICoverManager service = getService();
            if (service != null) {
                try {
                    service.removeLedNotification(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, "removeLedNotification in sendData to NFC : ", e);
                }
            }
        }
    }

    public void sendDataToCover(int i2, byte[] bArr) {
        if (!isSupportCover()) {
            Log.w(TAG, "sendDataToCover : This device is not supported cover");
        } else if (isSmartCover()) {
            Log.w(TAG, "sendDataToCover : If cover is smart cover, it does not need to send the data to cover");
        } else if (isSupportableVersion(16908288)) {
            ICoverManager service = getService();
            if (service != null) {
                try {
                    service.sendDataToCover(i2, bArr);
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException in sendData : ", e);
                }
            }
        } else {
            throw new SsdkUnsupportedException("This device is not supported this function. Device is must higher then v1.2.0", 2);
        }
    }

    public void sendDataToNfcLedCover(int i2, byte[] bArr) {
        if (!isSupportCover()) {
            Log.w(TAG, "sendDataToNfcLedCover : This device does not support cover");
        } else if (!isSupportNfcLedCover()) {
            Log.w(TAG, "sendDataToNfcLedCover : This device does not support NFC Led cover");
        } else if (isSupportableVersion(16973824)) {
            ICoverManager service = getService();
            if (service != null) {
                try {
                    service.sendDataToNfcLedCover(i2, bArr);
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException in sendData to NFC : ", e);
                }
            }
        } else {
            throw new SsdkUnsupportedException("This device does not support this function. Device is must higher then v1.3.0", 2);
        }
    }

    public void sendSystemEvent(Bundle bundle) {
        if (!isSupportCover()) {
            Log.w(TAG, "sendSystemEvent : This device does not support cover");
        } else if (!isSupportNfcLedCover()) {
            Log.w(TAG, "sendSystemEvent : This device does not support NFC Led cover");
        } else if (!isSupportableVersion(17170432)) {
            throw new SsdkUnsupportedException("This device does not support this function. Device is must higher then v1.6.0", 2);
        } else if (bundle == null) {
            Log.e(TAG, "sendSystemEvent : Null system event data!");
        } else {
            ICoverManager service = getService();
            if (service != null) {
                try {
                    service.sendSystemEvent(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, "sendSystemEvent in sendData to NFC : ", e);
                }
            }
        }
    }

    public WindowManager.LayoutParams setCoverMode(WindowManager.LayoutParams layoutParams, int i2) {
        if (!isSupportSViewCover()) {
            Log.w(TAG, "setSViewCoverModeToWindow : This device is not supported s view cover");
            return layoutParams;
        }
        if (layoutParams != null) {
            C0086a.C(i2, "setCoverMode : ", TAG);
            layoutParams.coverMode = i2;
        }
        return layoutParams;
    }

    public void setCoverModeToWindow(Window window, int i2) {
        if (!isSupportSViewCover()) {
            Log.w(TAG, "setSViewCoverModeToWindow : This device is not supported s view cover");
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes != null) {
            attributes.coverMode = i2;
            window.setAttributes(attributes);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterCoverPowerKeyListener(com.samsung.android.sdk.cover.ScoverManager.CoverPowerKeyListener r5) {
        /*
            r4 = this;
            boolean r0 = r4.isSupportCover()
            java.lang.String r1 = "ScoverManager"
            if (r0 != 0) goto L_0x000f
            java.lang.String r4 = "unregisterCoverPowerKeyListener : This device does not support cover"
            android.util.Log.w(r1, r4)
            return
        L_0x000f:
            java.lang.String r0 = "unregisterCoverPowerKeyListener"
            android.util.Log.d(r1, r0)
            boolean r0 = r4.isSupportFlipCover()
            if (r0 != 0) goto L_0x0028
            boolean r0 = r4.isSupportNeonCover()
            if (r0 != 0) goto L_0x0028
            java.lang.String r4 = "unregisterCoverPowerKeyListener : This device does not support Flip cover or Neon Cover"
            android.util.Log.w(r1, r4)
            return
        L_0x0028:
            r0 = 17432576(0x10a0000, float:2.5346597E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x0075
            if (r5 != 0) goto L_0x0039
            java.lang.String r4 = "unregisterCoverPowerKeyListener : listener is null"
            android.util.Log.w(r1, r4)
            return
        L_0x0039:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate> r0 = r4.mCoverPowerKeyListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x003f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0056
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate r2 = (com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate) r2
            java.lang.Object r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x003f
            goto L_0x0057
        L_0x0056:
            r2 = 0
        L_0x0057:
            if (r2 != 0) goto L_0x005a
            goto L_0x006e
        L_0x005a:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x006c }
            if (r5 == 0) goto L_0x006e
            boolean r5 = r5.unregisterNfcTouchListenerCallback(r2)     // Catch:{ RemoteException -> 0x006c }
            if (r5 == 0) goto L_0x006e
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverPowerKeyListenerDelegate> r4 = r4.mCoverPowerKeyListenerDelegates     // Catch:{ RemoteException -> 0x006c }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x006c }
            return
        L_0x006c:
            r4 = move-exception
            goto L_0x006f
        L_0x006e:
            return
        L_0x006f:
            java.lang.String r5 = "RemoteException in unregisterCoverPowerKeyListener: "
            android.util.Log.e(r1, r5, r4)
            return
        L_0x0075:
            com.samsung.android.sdk.SsdkUnsupportedException r4 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r5 = "This device does not support this function. Device is must higher then v1.1.0 for Flip Cover Neon cover"
            r0 = 2
            r4.<init>(r5, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterCoverPowerKeyListener(com.samsung.android.sdk.cover.ScoverManager$CoverPowerKeyListener):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.samsung.android.sdk.cover.LedSystemEventListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterLedSystemEventListener(com.samsung.android.sdk.cover.ScoverManager.LedSystemEventListener r5) {
        /*
            r4 = this;
            java.lang.String r0 = "ScoverManager"
            java.lang.String r1 = "unregisterLedSystemEventListener"
            android.util.Log.d(r0, r1)
            boolean r2 = r4.isSupportCover()
            if (r2 != 0) goto L_0x0015
            java.lang.String r4 = "unregisterLedSystemEventListener : This device does not support cover"
            android.util.Log.w(r0, r4)
            return
        L_0x0015:
            android.util.Log.d(r0, r1)
            boolean r1 = r4.isSupportNfcLedCover()
            if (r1 != 0) goto L_0x002b
            boolean r1 = r4.isSupportNeonCover()
            if (r1 != 0) goto L_0x002b
            java.lang.String r4 = "unregisterLedSystemEventListener : This device does not support NFC Led cover or Neon Cover"
            android.util.Log.w(r0, r4)
            return
        L_0x002b:
            boolean r1 = r4.isSupportNfcLedCover()
            if (r1 == 0) goto L_0x0039
            r1 = 16973824(0x1030000, float:2.40609E-38)
            boolean r1 = isSupportableVersion(r1)
            if (r1 != 0) goto L_0x0047
        L_0x0039:
            boolean r1 = r4.isSupportNeonCover()
            if (r1 == 0) goto L_0x0096
            r1 = 17301504(0x1080000, float:2.4979255E-38)
            boolean r1 = isSupportableVersion(r1)
            if (r1 == 0) goto L_0x0096
        L_0x0047:
            if (r5 != 0) goto L_0x0050
            java.lang.String r4 = "unregisterLedSystemEventListener : listener is null"
            android.util.Log.w(r0, r4)
            return
        L_0x0050:
            boolean r1 = supportNewLedSystemEventListener()
            if (r1 != 0) goto L_0x005a
            r4.unregisterLegacyLedSystemEventListener(r5)
            return
        L_0x005a:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LedSystemEventListenerDelegate> r1 = r4.mLedSystemEventListenerDelegates
            java.util.Iterator r1 = r1.iterator()
        L_0x0060:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0077
            java.lang.Object r2 = r1.next()
            com.samsung.android.sdk.cover.LedSystemEventListenerDelegate r2 = (com.samsung.android.sdk.cover.LedSystemEventListenerDelegate) r2
            java.lang.Object r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0060
            goto L_0x0078
        L_0x0077:
            r2 = 0
        L_0x0078:
            if (r2 != 0) goto L_0x007b
            goto L_0x008f
        L_0x007b:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x008d }
            if (r5 == 0) goto L_0x008f
            boolean r5 = r5.unregisterNfcTouchListenerCallback(r2)     // Catch:{ RemoteException -> 0x008d }
            if (r5 == 0) goto L_0x008f
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.LedSystemEventListenerDelegate> r4 = r4.mLedSystemEventListenerDelegates     // Catch:{ RemoteException -> 0x008d }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x008d }
            return
        L_0x008d:
            r4 = move-exception
            goto L_0x0090
        L_0x008f:
            return
        L_0x0090:
            java.lang.String r5 = "RemoteException in unregisterLedSystemEventListener: "
            android.util.Log.e(r0, r5, r4)
            return
        L_0x0096:
            com.samsung.android.sdk.SsdkUnsupportedException r4 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r5 = "This device does not support this function. Device is must higher then v1.3.0 for NFC LED Cover and v1.8.0 for Neon cover"
            r0 = 2
            r4.<init>(r5, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterLedSystemEventListener(com.samsung.android.sdk.cover.ScoverManager$LedSystemEventListener):void");
    }

    @Deprecated
    public void unregisterListener(ScoverStateListener scoverStateListener) {
        Log.d(TAG, "unregisterListener : Use deprecated API!! Change ScoverStateListener to StateListener");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterNfcTouchListener(com.samsung.android.sdk.cover.ScoverManager.NfcLedCoverTouchListener r5) {
        /*
            r4 = this;
            java.lang.String r0 = "unregisterNfcTouchListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            boolean r0 = r4.isSupportCover()
            if (r0 != 0) goto L_0x0015
            java.lang.String r4 = "unregisterNfcTouchListener : This device does not support cover"
            android.util.Log.w(r1, r4)
            return
        L_0x0015:
            boolean r0 = r4.isSupportNfcLedCover()
            if (r0 != 0) goto L_0x0022
            java.lang.String r4 = "unregisterNfcTouchListener : This device does not support NFC Led cover"
            android.util.Log.w(r1, r4)
            return
        L_0x0022:
            r0 = 16973824(0x1030000, float:2.40609E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x006f
            if (r5 != 0) goto L_0x0033
            java.lang.String r4 = "unregisterNfcTouchListener : listener is null"
            android.util.Log.w(r1, r4)
            return
        L_0x0033:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate> r0 = r4.mNfcLedCoverTouchListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0039:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0050
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate r2 = (com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate) r2
            java.lang.Object r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0039
            goto L_0x0051
        L_0x0050:
            r2 = 0
        L_0x0051:
            if (r2 != 0) goto L_0x0054
            goto L_0x0068
        L_0x0054:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x0066 }
            if (r5 == 0) goto L_0x0068
            boolean r5 = r5.unregisterNfcTouchListenerCallback(r2)     // Catch:{ RemoteException -> 0x0066 }
            if (r5 == 0) goto L_0x0068
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.NfcLedCoverTouchListenerDelegate> r4 = r4.mNfcLedCoverTouchListenerDelegates     // Catch:{ RemoteException -> 0x0066 }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x0066 }
            return
        L_0x0066:
            r4 = move-exception
            goto L_0x0069
        L_0x0068:
            return
        L_0x0069:
            java.lang.String r5 = "RemoteException in unregisterNfcTouchListener: "
            android.util.Log.e(r1, r5, r4)
            return
        L_0x006f:
            com.samsung.android.sdk.SsdkUnsupportedException r4 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r5 = "This device does not support this function. Device is must higher then v1.3.0"
            r0 = 2
            r4.<init>(r5, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterNfcTouchListener(com.samsung.android.sdk.cover.ScoverManager$NfcLedCoverTouchListener):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerListener(com.samsung.android.sdk.cover.ScoverManager.StateListener r7) {
        /*
            r6 = this;
            java.lang.String r0 = "registerListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            boolean r0 = r6.isSupportCover()
            if (r0 != 0) goto L_0x0013
            java.lang.String r6 = "registerListener : This device is not supported cover"
            android.util.Log.w(r1, r6)
            return
        L_0x0013:
            boolean r0 = r6.isSmartCover()
            if (r0 == 0) goto L_0x001f
            java.lang.String r6 = "registerListener : If cover is smart cover, it does not need to register listener of intenal App"
            android.util.Log.w(r1, r6)
            return
        L_0x001f:
            if (r7 != 0) goto L_0x0027
            java.lang.String r6 = "registerListener : listener is null"
            android.util.Log.w(r1, r6)
            return
        L_0x0027:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r0 = r6.mListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x002d:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0046
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.CoverListenerDelegate r2 = (com.samsung.android.sdk.cover.CoverListenerDelegate) r2
            com.samsung.android.sdk.cover.ScoverManager$StateListener r4 = r2.getListener()
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x002d
            r0 = 1
            goto L_0x0048
        L_0x0046:
            r0 = 0
            r2 = r3
        L_0x0048:
            if (r2 != 0) goto L_0x0051
            com.samsung.android.sdk.cover.CoverListenerDelegate r2 = new com.samsung.android.sdk.cover.CoverListenerDelegate
            android.content.Context r4 = r6.mContext
            r2.<init>(r7, r3, r4)
        L_0x0051:
            com.samsung.android.cover.ICoverManager r7 = r6.getService()     // Catch:{ RemoteException -> 0x0075 }
            if (r7 == 0) goto L_0x0077
            android.content.ComponentName r3 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x0075 }
            android.content.Context r4 = r6.mContext     // Catch:{ RemoteException -> 0x0075 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ RemoteException -> 0x0075 }
            java.lang.Class r5 = r6.getClass()     // Catch:{ RemoteException -> 0x0075 }
            java.lang.String r5 = r5.getCanonicalName()     // Catch:{ RemoteException -> 0x0075 }
            r3.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0075 }
            r7.registerCallback(r2, r3)     // Catch:{ RemoteException -> 0x0075 }
            if (r0 != 0) goto L_0x0077
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r6 = r6.mListenerDelegates     // Catch:{ RemoteException -> 0x0075 }
            r6.add(r2)     // Catch:{ RemoteException -> 0x0075 }
            return
        L_0x0075:
            r6 = move-exception
            goto L_0x0078
        L_0x0077:
            return
        L_0x0078:
            java.lang.String r7 = "RemoteException in registerListener: "
            android.util.Log.e(r1, r7, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerListener(com.samsung.android.sdk.cover.ScoverManager$StateListener):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.CoverListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterListener(com.samsung.android.sdk.cover.ScoverManager.StateListener r5) {
        /*
            r4 = this;
            java.lang.String r0 = "unregisterListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            boolean r0 = r4.isSupportCover()
            if (r0 != 0) goto L_0x0015
            java.lang.String r4 = "unregisterListener : This device is not supported cover"
            android.util.Log.w(r1, r4)
            return
        L_0x0015:
            boolean r0 = r4.isSmartCover()
            if (r0 == 0) goto L_0x0022
            java.lang.String r4 = "unregisterListener : If cover is smart cover, it does not need to unregister listener of intenal App"
            android.util.Log.w(r1, r4)
            return
        L_0x0022:
            if (r5 != 0) goto L_0x002b
            java.lang.String r4 = "unregisterListener : listener is null"
            android.util.Log.w(r1, r4)
            return
        L_0x002b:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r0 = r4.mListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0031:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0048
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.CoverListenerDelegate r2 = (com.samsung.android.sdk.cover.CoverListenerDelegate) r2
            com.samsung.android.sdk.cover.ScoverManager$StateListener r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0031
            goto L_0x0049
        L_0x0048:
            r2 = 0
        L_0x0049:
            if (r2 != 0) goto L_0x004c
            goto L_0x0060
        L_0x004c:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x005e }
            if (r5 == 0) goto L_0x0060
            boolean r5 = r5.unregisterCallback(r2)     // Catch:{ RemoteException -> 0x005e }
            if (r5 == 0) goto L_0x0060
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverListenerDelegate> r4 = r4.mListenerDelegates     // Catch:{ RemoteException -> 0x005e }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x005e }
            return
        L_0x005e:
            r4 = move-exception
            goto L_0x0061
        L_0x0060:
            return
        L_0x0061:
            java.lang.String r5 = "RemoteException in unregisterListener: "
            android.util.Log.e(r1, r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterListener(com.samsung.android.sdk.cover.ScoverManager$StateListener):void");
    }

    public void setCoverModeToWindow(WindowManager.LayoutParams layoutParams, int i2) {
        if (!isSupportSViewCover()) {
            Log.w(TAG, "setSViewCoverModeToWindow : This device is not supported s view cover");
        } else if (layoutParams != null) {
            layoutParams.coverMode = i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.sdk.cover.CoverStateListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.IBinder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unregisterListener(com.samsung.android.sdk.cover.ScoverManager.CoverStateListener r5) {
        /*
            r4 = this;
            java.lang.String r0 = "unregisterListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            boolean r0 = r4.isSupportCover()
            if (r0 != 0) goto L_0x0015
            java.lang.String r4 = "unregisterListener : This device is not supported cover"
            android.util.Log.w(r1, r4)
            return
        L_0x0015:
            boolean r0 = r4.isSmartCover()
            if (r0 == 0) goto L_0x0022
            java.lang.String r4 = "unregisterListener : If cover is smart cover, it does not need to unregister listener of intenal App"
            android.util.Log.w(r1, r4)
            return
        L_0x0022:
            r0 = 16842752(0x1010000, float:2.3693558E-38)
            boolean r0 = isSupportableVersion(r0)
            if (r0 == 0) goto L_0x006f
            if (r5 != 0) goto L_0x0033
            java.lang.String r4 = "unregisterListener : listener is null"
            android.util.Log.w(r1, r4)
            return
        L_0x0033:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverStateListenerDelegate> r0 = r4.mCoverStateListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0039:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0050
            java.lang.Object r2 = r0.next()
            com.samsung.android.sdk.cover.CoverStateListenerDelegate r2 = (com.samsung.android.sdk.cover.CoverStateListenerDelegate) r2
            com.samsung.android.sdk.cover.ScoverManager$CoverStateListener r3 = r2.getListener()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0039
            goto L_0x0051
        L_0x0050:
            r2 = 0
        L_0x0051:
            if (r2 != 0) goto L_0x0054
            goto L_0x0068
        L_0x0054:
            com.samsung.android.cover.ICoverManager r5 = r4.getService()     // Catch:{ RemoteException -> 0x0066 }
            if (r5 == 0) goto L_0x0068
            boolean r5 = r5.unregisterCallback(r2)     // Catch:{ RemoteException -> 0x0066 }
            if (r5 == 0) goto L_0x0068
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverStateListenerDelegate> r4 = r4.mCoverStateListenerDelegates     // Catch:{ RemoteException -> 0x0066 }
            r4.remove(r2)     // Catch:{ RemoteException -> 0x0066 }
            return
        L_0x0066:
            r4 = move-exception
            goto L_0x0069
        L_0x0068:
            return
        L_0x0069:
            java.lang.String r5 = "RemoteException in unregisterListener: "
            android.util.Log.e(r1, r5, r4)
            return
        L_0x006f:
            com.samsung.android.sdk.SsdkUnsupportedException r4 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r5 = "This device is not supported this function. Device is must higher then v1.1.0"
            r0 = 2
            r4.<init>(r5, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.unregisterListener(com.samsung.android.sdk.cover.ScoverManager$CoverStateListener):void");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CoverPowerKeyListener {
        private static final int EVENT_TYPE_POWER_KEY = 10;

        public void onPowerKeyPress() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NfcLedCoverTouchListener {
        public static final int EVENT_TYPE_ALARM = 1;
        public static final int EVENT_TYPE_BIXBY = 9;
        public static final int EVENT_TYPE_CALL = 0;
        public static final int EVENT_TYPE_FACTORY = 5;
        public static final int EVENT_TYPE_REMINDER = 6;
        public static final int EVENT_TYPE_SCHEDULE = 3;
        public static final int EVENT_TYPE_TIMER = 2;
        public static final int EVENT_TYPE_VOICE_RECORDER = 7;
        public static final int EVENT_TYPE_VOLUME_CONTROLLER = 8;

        public void onCoverTapLeft() {
        }

        public void onCoverTapMid() {
        }

        public void onCoverTapRight() {
        }

        public void onCoverTouchAccept() {
        }

        public void onCoverTouchReject() {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.samsung.android.sdk.cover.CoverStateListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: com.samsung.android.sdk.cover.CoverStateListenerDelegate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: android.os.IBinder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: android.os.IBinder} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerListener(com.samsung.android.sdk.cover.ScoverManager.CoverStateListener r8) {
        /*
            r7 = this;
            java.lang.String r0 = "registerListener"
            java.lang.String r1 = "ScoverManager"
            android.util.Log.d(r1, r0)
            boolean r0 = r7.isSupportCover()
            if (r0 != 0) goto L_0x0013
            java.lang.String r7 = "registerListener : This device is not supported cover"
            android.util.Log.w(r1, r7)
            return
        L_0x0013:
            boolean r0 = r7.isSmartCover()
            if (r0 == 0) goto L_0x001f
            java.lang.String r7 = "registerListener : If cover is smart cover, it does not need to register listener of intenal App"
            android.util.Log.w(r1, r7)
            return
        L_0x001f:
            r0 = 16842752(0x1010000, float:2.3693558E-38)
            boolean r0 = isSupportableVersion(r0)
            r2 = 2
            if (r0 == 0) goto L_0x0087
            if (r8 != 0) goto L_0x0030
            java.lang.String r7 = "registerListener : listener is null"
            android.util.Log.w(r1, r7)
            return
        L_0x0030:
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverStateListenerDelegate> r0 = r7.mCoverStateListenerDelegates
            java.util.Iterator r0 = r0.iterator()
        L_0x0036:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x004f
            java.lang.Object r3 = r0.next()
            com.samsung.android.sdk.cover.CoverStateListenerDelegate r3 = (com.samsung.android.sdk.cover.CoverStateListenerDelegate) r3
            com.samsung.android.sdk.cover.ScoverManager$CoverStateListener r5 = r3.getListener()
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0051
        L_0x004f:
            r0 = 0
            r3 = r4
        L_0x0051:
            if (r3 != 0) goto L_0x005a
            com.samsung.android.sdk.cover.CoverStateListenerDelegate r3 = new com.samsung.android.sdk.cover.CoverStateListenerDelegate
            android.content.Context r5 = r7.mContext
            r3.<init>(r8, r4, r5)
        L_0x005a:
            com.samsung.android.cover.ICoverManager r8 = r7.getService()     // Catch:{ RemoteException -> 0x007e }
            if (r8 == 0) goto L_0x0080
            android.content.ComponentName r4 = new android.content.ComponentName     // Catch:{ RemoteException -> 0x007e }
            android.content.Context r5 = r7.mContext     // Catch:{ RemoteException -> 0x007e }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ RemoteException -> 0x007e }
            java.lang.Class r6 = r7.getClass()     // Catch:{ RemoteException -> 0x007e }
            java.lang.String r6 = r6.getCanonicalName()     // Catch:{ RemoteException -> 0x007e }
            r4.<init>(r5, r6)     // Catch:{ RemoteException -> 0x007e }
            r8.registerListenerCallback(r3, r4, r2)     // Catch:{ RemoteException -> 0x007e }
            if (r0 != 0) goto L_0x0080
            java.util.concurrent.CopyOnWriteArrayList<com.samsung.android.sdk.cover.CoverStateListenerDelegate> r7 = r7.mCoverStateListenerDelegates     // Catch:{ RemoteException -> 0x007e }
            r7.add(r3)     // Catch:{ RemoteException -> 0x007e }
            return
        L_0x007e:
            r7 = move-exception
            goto L_0x0081
        L_0x0080:
            return
        L_0x0081:
            java.lang.String r8 = "RemoteException in registerListener: "
            android.util.Log.e(r1, r8, r7)
            return
        L_0x0087:
            com.samsung.android.sdk.SsdkUnsupportedException r7 = new com.samsung.android.sdk.SsdkUnsupportedException
            java.lang.String r8 = "This device is not supported this function. Device is must higher then v1.1.0"
            r7.<init>(r8, r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.cover.ScoverManager.registerListener(com.samsung.android.sdk.cover.ScoverManager$CoverStateListener):void");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CoverStateListener {
        public void onCoverAttachStateChanged(boolean z) {
        }

        public void onCoverSwitchStateChanged(boolean z) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StateListener {
        public void onCoverStateChanged(ScoverState scoverState) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LedSystemEventListener {
        private static final int EVENT_TYPE_SYSTEM = 4;

        public void onSystemCoverEvent(int i2, Bundle bundle) {
        }
    }
}
