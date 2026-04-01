package com.samsung.android.gallery.module.album;

import B8.c;
import B8.d;
import android.util.Pair;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoAlbumSettingData {
    private static final boolean SUPPORT_PET = Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM);
    private final int mAlbumId;
    private boolean mIsAutoUpdatingEnabled;
    private ArrayList<Long> mPeopleIds;
    private ArrayList<Long> mPetIds;
    private int mSuggestedContentsRuleType;

    public AutoAlbumSettingData(int i2) {
        this.mAlbumId = i2;
    }

    /* access modifiers changed from: private */
    public void loadAutoUpdatingEnabled() {
        this.mIsAutoUpdatingEnabled = AutoAlbumHelper.getInstance().isAutoUpdatingEnabled(this.mAlbumId);
    }

    public int getCreatureCount() {
        int i2;
        int peopleCount = getPeopleCount();
        if (SUPPORT_PET) {
            i2 = getPetCount();
        } else {
            i2 = 0;
        }
        return peopleCount + i2;
    }

    public ArrayList<String> getCreatureIds() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!this.mPeopleIds.isEmpty()) {
            this.mPeopleIds.forEach(new d(arrayList, 0));
        }
        if (SUPPORT_PET && !this.mPetIds.isEmpty()) {
            this.mPetIds.forEach(new d(arrayList, 1));
        }
        return arrayList;
    }

    public int getPeopleCount() {
        ArrayList<Long> arrayList = this.mPeopleIds;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public ArrayList<Long> getPeopleIds() {
        ArrayList<Long> arrayList = this.mPeopleIds;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList<>();
    }

    public int getPetCount() {
        ArrayList<Long> arrayList = this.mPetIds;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getSuggestedContentsRuleType() {
        return this.mSuggestedContentsRuleType;
    }

    public boolean isAutoUpdatingEnabled() {
        return this.mIsAutoUpdatingEnabled;
    }

    public void load() {
        try {
            new LatchBuilder("loadAutoAlbumSettingData").addWorker(new c(this, 0)).addWorker(new c(this, 1)).addWorker(new c(this, 2)).setTimeout(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS).start();
        } catch (Exception e) {
            Log.e("AutoAlbumSettingData", e.getMessage());
        }
    }

    public void loadSubscribeCreatureList() {
        if (SUPPORT_PET) {
            Pair<ArrayList<Long>, ArrayList<Long>> subscribeCreatureList = AutoAlbumHelper.getInstance().getSubscribeCreatureList((long) this.mAlbumId);
            this.mPeopleIds = (ArrayList) subscribeCreatureList.first;
            this.mPetIds = (ArrayList) subscribeCreatureList.second;
            return;
        }
        this.mPeopleIds = AutoAlbumHelper.getInstance().getSubscribePeopleList((long) this.mAlbumId);
    }

    public void loadSuggestedContentsRuleType() {
        this.mSuggestedContentsRuleType = AutoAlbumHelper.getInstance().getSuggestedContentsRule((long) this.mAlbumId);
    }

    public void onAutoUpdateChanged(boolean z) {
        this.mIsAutoUpdatingEnabled = z;
        AutoAlbumHelper.getInstance().changeAutoUpdatingProperty(z, (long) this.mAlbumId);
    }

    public void updateSuggestedContentsRuleType(int i2) {
        this.mSuggestedContentsRuleType = i2;
        AutoAlbumHelper.getInstance().updateSuggestedContentsRule(i2, this.mAlbumId);
    }
}
