package com.samsung.android.gallery.module.utils;

import android.os.Bundle;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KnoxUtil {
    private static volatile KnoxUtil sInstance;
    private final KnoxCompat mCompat;

    /* renamed from: com.samsung.android.gallery.module.utils.KnoxUtil$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType[] r0 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType = r0
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.MOVE_TO_KNOX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.REMOVE_FROM_KNOX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.utils.KnoxUtil.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class KnoxCompat {
        private static final KnoxContainer EMPTY = new KnoxContainer(-100, -100, (String) null);
        private static final Object LOCK = new Object();
        /* access modifiers changed from: private */
        public final HashMap<Integer, KnoxContainer> mContainerMap;
        private final ArrayList<KnoxContainer> mLegacyList;
        private Locale mLocale;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class KnoxContainer {
            int id;
            String name;
            int type;

            public KnoxContainer(int i2, int i7, String str) {
                this.id = i2;
                this.type = i7;
                this.name = str;
            }

            public String toString() {
                StringBuilder sb2 = new StringBuilder("[");
                sb2.append(this.type);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(this.id);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                return C0212a.p(sb2, this.name, "]");
            }
        }

        public /* synthetic */ KnoxCompat(int i2) {
            this();
        }

        public KnoxContainer getContainerMoveOutKnox(int i2) {
            KnoxContainer knoxContainer;
            if (i2 == 1) {
                knoxContainer = this.mContainerMap.get(1004);
            } else if (i2 == 2) {
                knoxContainer = this.mContainerMap.get(1003);
            } else {
                knoxContainer = null;
            }
            if (knoxContainer != null) {
                return knoxContainer;
            }
            return EMPTY;
        }

        public KnoxContainer getContainerMoveToKnox(int i2) {
            if (i2 == 1) {
                if (!this.mLegacyList.isEmpty()) {
                    return this.mLegacyList.get(0);
                }
                return EMPTY;
            } else if (i2 != 2) {
                return EMPTY;
            } else {
                KnoxContainer knoxContainer = this.mContainerMap.get(1002);
                if (knoxContainer == null && this.mLegacyList.size() > 1) {
                    knoxContainer = this.mLegacyList.get(1);
                }
                if (knoxContainer != null) {
                    return knoxContainer;
                }
                return EMPTY;
            }
        }

        public void init(Locale locale) {
            if (!locale.equals(this.mLocale)) {
                init();
            }
        }

        private KnoxCompat() {
            this.mContainerMap = new HashMap<>();
            this.mLegacyList = new ArrayList<>();
        }

        public void init() {
            long currentTimeMillis = System.currentTimeMillis();
            this.mLocale = Locale.getDefault();
            if (Features.isEnabled(Features.SUPPORT_KNOX)) {
                ArrayList<Bundle> moveToKnoxMenuList = SeApiCompat.getMoveToKnoxMenuList(AppResources.getAppContext());
                if (moveToKnoxMenuList == null || moveToKnoxMenuList.isEmpty()) {
                    synchronized (LOCK) {
                        this.mContainerMap.clear();
                        this.mLegacyList.clear();
                    }
                    Log.w("KnoxCompat", "init empty +" + (System.currentTimeMillis() - currentTimeMillis));
                    return;
                }
                synchronized (LOCK) {
                    try {
                        this.mContainerMap.clear();
                        this.mLegacyList.clear();
                        Iterator<Bundle> it = moveToKnoxMenuList.iterator();
                        while (it.hasNext()) {
                            Bundle next = it.next();
                            if (next != null) {
                                int i2 = next.getInt("com.sec.knox.moveto.containerId");
                                int i7 = next.getInt("com.sec.knox.moveto.containerType");
                                KnoxContainer knoxContainer = new KnoxContainer(i2, i7, next.getString("com.sec.knox.moveto.name", (String) null));
                                switch (i7) {
                                    case 1002:
                                    case 1003:
                                    case 1004:
                                        this.mContainerMap.put(Integer.valueOf(i7), knoxContainer);
                                        break;
                                    default:
                                        this.mLegacyList.add(knoxContainer);
                                        break;
                                }
                            }
                        }
                        StringBuilder sb2 = new StringBuilder();
                        this.mContainerMap.forEach(new a(sb2));
                        this.mLegacyList.forEach(new b(0, sb2));
                        Log.d("KnoxCompat", "init {" + this.mLocale + ',' + moveToKnoxMenuList.size() + ',' + this.mContainerMap.size() + ',' + this.mLegacyList.size() + ',' + sb2.toString() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                    } finally {
                    }
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MoveType {
        MOVE_TO_KNOX,
        REMOVE_FROM_KNOX,
        MOVE_TO_SECURE_FOLDER,
        REMOVE_FROM_SECURE_FOLDER
    }

    public KnoxUtil() {
        KnoxCompat knoxCompat = new KnoxCompat(0);
        this.mCompat = knoxCompat;
        knoxCompat.init();
    }

    public static KnoxUtil getInstance() {
        KnoxUtil knoxUtil;
        KnoxUtil knoxUtil2 = sInstance;
        if (knoxUtil2 != null) {
            return knoxUtil2;
        }
        synchronized (KnoxUtil.class) {
            try {
                if (sInstance == null) {
                    knoxUtil = new KnoxUtil();
                    sInstance = knoxUtil;
                } else {
                    knoxUtil = sInstance;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return knoxUtil;
    }

    public static void releaseInstance() {
        synchronized (KnoxUtil.class) {
            sInstance = null;
        }
    }

    public String getCurrentContainerName() {
        return SeApiCompat.getKnoxContainerLabel(AppResources.getAppContext());
    }

    public int getKnoxContainerId(MoveType moveType) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType[moveType.ordinal()];
        if (i2 == 1) {
            return this.mCompat.getContainerMoveToKnox(1).id;
        }
        if (i2 == 2) {
            return this.mCompat.getContainerMoveOutKnox(1).id;
        }
        if (i2 == 3) {
            return this.mCompat.getContainerMoveToKnox(2).id;
        }
        if (i2 == 4) {
            return this.mCompat.getContainerMoveOutKnox(2).id;
        }
        Log.e("KnoxUtil", "Invalid MoveType " + moveType);
        return -100;
    }

    public String getKnoxContainerName(MoveType moveType) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType[moveType.ordinal()];
        if (i2 == 1) {
            return this.mCompat.getContainerMoveToKnox(1).name;
        }
        if (i2 == 2) {
            return this.mCompat.getContainerMoveOutKnox(1).name;
        }
        if (i2 == 3) {
            return this.mCompat.getContainerMoveToKnox(2).name;
        }
        if (i2 != 4) {
            return "";
        }
        return this.mCompat.getContainerMoveOutKnox(2).name;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isKnoxMoveOn(com.samsung.android.gallery.module.utils.KnoxUtil.MoveType r5) {
        /*
            r4 = this;
            int[] r0 = com.samsung.android.gallery.module.utils.KnoxUtil.AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType
            int r5 = r5.ordinal()
            r5 = r0[r5]
            r0 = -100
            r1 = 1
            if (r5 == r1) goto L_0x003e
            r2 = 2
            if (r5 == r2) goto L_0x0033
            r3 = 3
            if (r5 == r3) goto L_0x0028
            r0 = 4
            if (r5 == r0) goto L_0x0017
            goto L_0x0049
        L_0x0017:
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat r4 = r4.mCompat
            java.util.HashMap r4 = r4.mContainerMap
            r5 = 1003(0x3eb, float:1.406E-42)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.containsKey(r5)
            return r4
        L_0x0028:
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat r4 = r4.mCompat
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat$KnoxContainer r4 = r4.getContainerMoveToKnox(r2)
            int r4 = r4.id
            if (r4 == r0) goto L_0x0049
            goto L_0x0048
        L_0x0033:
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat r4 = r4.mCompat
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat$KnoxContainer r4 = r4.getContainerMoveOutKnox(r1)
            int r4 = r4.id
            if (r4 == r0) goto L_0x0049
            goto L_0x0048
        L_0x003e:
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat r4 = r4.mCompat
            com.samsung.android.gallery.module.utils.KnoxUtil$KnoxCompat$KnoxContainer r4 = r4.getContainerMoveToKnox(r1)
            int r4 = r4.id
            if (r4 == r0) goto L_0x0049
        L_0x0048:
            return r1
        L_0x0049:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.utils.KnoxUtil.isKnoxMoveOn(com.samsung.android.gallery.module.utils.KnoxUtil$MoveType):boolean");
    }

    public void reload(Locale locale) {
        this.mCompat.init(locale);
    }

    public void reload() {
        this.mCompat.init();
    }
}
