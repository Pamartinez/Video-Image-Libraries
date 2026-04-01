package com.samsung.android.gallery.module.publisher;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.R$bool;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.suggested.SuggestedManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BadgeDataPublisher extends Subscriber {
    private final SuggestedManager mSuggestedManager = SuggestedManager.getInstance();

    public BadgeDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishAllBadges$0(Object obj, Bundle bundle) {
        publishNotificationsBadge(obj, bundle);
        if (Features.isEnabled(Features.SUPPORT_STORY_BADGE_ON_TAB)) {
            publishStoriesBadge(obj, bundle);
        }
        publishSharingsBadge(obj, bundle);
        publishBottomMenuBadge(obj, bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSharingsBadge$1(Context context) {
        if (context.getResources().getBoolean(R$bool.isTabletDpi)) {
            publishBadge("data://badge/sharings", Boolean.valueOf(BadgeHelper.hasNewSharedAlbums()), System.currentTimeMillis());
        }
    }

    /* access modifiers changed from: private */
    public void publishAllBadges(Object obj, Bundle bundle) {
        SimpleThreadPool.getInstance().execute(new C0636p(this, obj, bundle, 0));
    }

    private void publishBadge(String str, Object obj, long j2) {
        this.mBlackboard.publish(str, obj);
        this.mBlackboard.erase(CommandKey.DATA_REQUEST(str));
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishBadge {" + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + obj + "} +" + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public void publishBottomMenuBadge(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean needBottomMenuTabBadge = BadgeHelper.needBottomMenuTabBadge();
        publishBadge("data://badge/bottom_menu", Boolean.valueOf(needBottomMenuTabBadge), currentTimeMillis);
        PreferenceCache.MenuBadgeOnTab.setBoolean(needBottomMenuTabBadge);
    }

    /* access modifiers changed from: private */
    public void publishNotificationsBadge(Object obj, Bundle bundle) {
        Context readAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
        if (readAppContext != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mSuggestedManager.isNewSuggestionUpdated(readAppContext)) {
                publishBadge("data://badge/notifications", 1, currentTimeMillis);
            }
        }
    }

    /* access modifiers changed from: private */
    public void publishSharingsBadge(Object obj, Bundle bundle) {
        Optional.ofNullable(BlackboardUtils.readAppContext(this.mBlackboard)).ifPresent(new c0(5, this));
    }

    /* access modifiers changed from: private */
    public void publishStoriesBadge(Object obj, Bundle bundle) {
        int i2;
        Features features = Features.SUPPORT_STORY;
        if (Features.isEnabled(features) && BlackboardUtils.readAppContext(this.mBlackboard) != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (Features.isEnabled(features)) {
                i2 = DbCompat.storyBadgeApi().getNewStoryCount();
            } else {
                i2 = 0;
            }
            String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
            if (i2 <= 0 || "location://story/albums".equals(readLocationKeyCurrent)) {
                if ("location://story/albums".equals(readLocationKeyCurrent)) {
                    DbCompat.storyBadgeApi().updateNotifyStatusChecked();
                }
                publishBadge("data://badge/stories", Boolean.FALSE, currentTimeMillis);
            } else {
                publishBadge("data://badge/stories", Boolean.TRUE, currentTimeMillis);
            }
            try {
                AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_UNKNOWN.toString(), AnalyticsEventId.EVENT_STORIES_NOTI_COUNT.toString(), String.valueOf(i2));
            } catch (Exception unused) {
            }
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://badge/all"), new C0635o(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://badge/notifications"), new C0635o(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://badge/stories"), new C0635o(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://badge/sharings"), new C0635o(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), new C0635o(this, 4)));
    }
}
