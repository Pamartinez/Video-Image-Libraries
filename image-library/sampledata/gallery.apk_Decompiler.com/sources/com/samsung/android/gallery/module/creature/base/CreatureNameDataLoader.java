package com.samsung.android.gallery.module.creature.base;

import E5.a;
import android.text.TextUtils;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreatureNameDataLoader {
    protected final String TAG = getClass().getSimpleName();

    /* renamed from: com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType[] r0 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType = r0
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.MY_PROFILE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.TAGGED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.CONTACT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.FREQUENTLY_CONTACT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.RECOMMEND_CONTACT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$load$0(CreatureNameData.ContactType[] contactTypeArr, LoadFinishedListener loadFinishedListener, ThreadPool.JobContext jobContext) {
        String str = this.TAG;
        Log.d(str, "load {" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, Arrays.stream(contactTypeArr).iterator()) + "}");
        loadInternal(loadFinishedListener, contactTypeArr);
        return null;
    }

    private void loadInternal(LoadFinishedListener loadFinishedListener, CreatureNameData.ContactType... contactTypeArr) {
        for (CreatureNameData.ContactType ordinal : contactTypeArr) {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$creature$base$CreatureNameData$ContactType[ordinal.ordinal()];
            if (i2 == 1) {
                loadContactDataFromMyProfile(loadFinishedListener);
            } else if (i2 == 2) {
                loadTaggedData(loadFinishedListener);
            } else if (i2 == 3) {
                loadContactDataFromContact(loadFinishedListener);
            } else if (i2 == 4) {
                loadContactDataFromFreqContact(loadFinishedListener);
            } else if (i2 == 5) {
                loadRecommendedContact(loadFinishedListener);
            }
        }
    }

    public String getInitialLetter(String str) {
        if (TextUtils.isEmpty(str) || !Character.isLetter(str.charAt(0))) {
            return null;
        }
        return String.valueOf(str.charAt(0));
    }

    public void load(LoadFinishedListener loadFinishedListener, CreatureNameData.ContactType... contactTypeArr) {
        ThreadPool.getInstance().submit(new a(this, contactTypeArr, loadFinishedListener, 4));
    }

    public abstract void loadContactDataFromContact(LoadFinishedListener loadFinishedListener);

    public abstract void loadContactDataFromFreqContact(LoadFinishedListener loadFinishedListener);

    public abstract void loadContactDataFromMyProfile(LoadFinishedListener loadFinishedListener);

    public abstract void loadRecommendedContact(LoadFinishedListener loadFinishedListener);

    public abstract void loadTaggedData(LoadFinishedListener loadFinishedListener);
}
