package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Context mContext;
    private final Bundle mExtras = new Bundle();
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api20Impl {
        public static Notification.Builder addAction(Notification.Builder builder, Notification.Action action) {
            return builder.addAction(action);
        }

        public static Notification.Action.Builder addExtras(Notification.Action.Builder builder, Bundle bundle) {
            return builder.addExtras(bundle);
        }

        public static Notification.Action.Builder addRemoteInput(Notification.Action.Builder builder, RemoteInput remoteInput) {
            return builder.addRemoteInput(remoteInput);
        }

        public static Notification.Action build(Notification.Action.Builder builder) {
            return builder.build();
        }

        public static Notification.Builder setGroup(Notification.Builder builder, String str) {
            return builder.setGroup(str);
        }

        public static Notification.Builder setGroupSummary(Notification.Builder builder, boolean z) {
            return builder.setGroupSummary(z);
        }

        public static Notification.Builder setLocalOnly(Notification.Builder builder, boolean z) {
            return builder.setLocalOnly(z);
        }

        public static Notification.Builder setSortKey(Notification.Builder builder, String str) {
            return builder.setSortKey(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static Notification.Builder addPerson(Notification.Builder builder, String str) {
            return builder.addPerson(str);
        }

        public static Notification.Builder setCategory(Notification.Builder builder, String str) {
            return builder.setCategory(str);
        }

        public static Notification.Builder setColor(Notification.Builder builder, int i2) {
            return builder.setColor(i2);
        }

        public static Notification.Builder setPublicVersion(Notification.Builder builder, Notification notification) {
            return builder.setPublicVersion(notification);
        }

        public static Notification.Builder setSound(Notification.Builder builder, Uri uri, Object obj) {
            return builder.setSound(uri, (AudioAttributes) obj);
        }

        public static Notification.Builder setVisibility(Notification.Builder builder, int i2) {
            return builder.setVisibility(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static Notification.Action.Builder createBuilder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
            return new Notification.Action.Builder(icon, charSequence, pendingIntent);
        }

        public static Notification.Builder setLargeIcon(Notification.Builder builder, Icon icon) {
            return builder.setLargeIcon(icon);
        }

        public static Notification.Builder setSmallIcon(Notification.Builder builder, Object obj) {
            return builder.setSmallIcon((Icon) obj);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder builder, boolean z) {
            return builder.setAllowGeneratedReplies(z);
        }

        public static Notification.Builder setCustomBigContentView(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomBigContentView(remoteViews);
        }

        public static Notification.Builder setCustomContentView(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomContentView(remoteViews);
        }

        public static Notification.Builder setCustomHeadsUpContentView(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomHeadsUpContentView(remoteViews);
        }

        public static Notification.Builder setRemoteInputHistory(Notification.Builder builder, CharSequence[] charSequenceArr) {
            return builder.setRemoteInputHistory(charSequenceArr);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static Notification.Builder createBuilder(Context context, String str) {
            return new Notification.Builder(context, str);
        }

        public static Notification.Builder setBadgeIconType(Notification.Builder builder, int i2) {
            return builder.setBadgeIconType(i2);
        }

        public static Notification.Builder setColorized(Notification.Builder builder, boolean z) {
            return builder.setColorized(z);
        }

        public static Notification.Builder setGroupAlertBehavior(Notification.Builder builder, int i2) {
            return builder.setGroupAlertBehavior(i2);
        }

        public static Notification.Builder setSettingsText(Notification.Builder builder, CharSequence charSequence) {
            return builder.setSettingsText(charSequence);
        }

        public static Notification.Builder setShortcutId(Notification.Builder builder, String str) {
            return builder.setShortcutId(str);
        }

        public static Notification.Builder setTimeoutAfter(Notification.Builder builder, long j2) {
            return builder.setTimeoutAfter(j2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static Notification.Action.Builder setSemanticAction(Notification.Action.Builder builder, int i2) {
            return builder.setSemanticAction(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static Notification.Builder setAllowSystemGeneratedContextualActions(Notification.Builder builder, boolean z) {
            return builder.setAllowSystemGeneratedContextualActions(z);
        }

        public static Notification.Builder setBubbleMetadata(Notification.Builder builder, Notification.BubbleMetadata bubbleMetadata) {
            return builder.setBubbleMetadata(bubbleMetadata);
        }

        public static Notification.Action.Builder setContextual(Notification.Action.Builder builder, boolean z) {
            return builder.setContextual(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api31Impl {
        public static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder builder, boolean z) {
            return builder.setAuthenticationRequired(z);
        }

        public static Notification.Builder setForegroundServiceBehavior(Notification.Builder builder, int i2) {
            return builder.setForegroundServiceBehavior(i2);
        }
    }

    public NotificationCompatBuilder(NotificationCompat.Builder builder) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        Icon icon;
        int i2;
        this.mBuilderCompat = builder;
        Context context = builder.mContext;
        this.mContext = context;
        Notification.Builder createBuilder = Api26Impl.createBuilder(context, builder.mChannelId);
        this.mBuilder = createBuilder;
        Notification notification = builder.mNotification;
        Notification.Builder lights = createBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        if ((notification.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        Notification.Builder ongoing = lights.setOngoing(z);
        if ((notification.flags & 8) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Notification.Builder onlyAlertOnce = ongoing.setOnlyAlertOnce(z3);
        if ((notification.flags & 16) != 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        Notification.Builder deleteIntent = onlyAlertOnce.setAutoCancel(z7).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(builder.mContentInfo).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent);
        PendingIntent pendingIntent = builder.mFullScreenIntent;
        if ((notification.flags & 128) != 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        deleteIntent.setFullScreenIntent(pendingIntent, z9).setNumber(builder.mNumber).setProgress(builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        IconCompat iconCompat = builder.mLargeIcon;
        if (iconCompat == null) {
            icon = null;
        } else {
            icon = iconCompat.toIcon(context);
        }
        Api23Impl.setLargeIcon(createBuilder, icon);
        createBuilder.setSubText(builder.mSubText).setUsesChronometer(builder.mUseChronometer).setPriority(builder.mPriority);
        Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
        while (it.hasNext()) {
            addAction(it.next());
        }
        Bundle bundle = builder.mExtras;
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        this.mContentView = builder.mContentView;
        this.mBigContentView = builder.mBigContentView;
        this.mBuilder.setShowWhen(builder.mShowWhen);
        Api20Impl.setLocalOnly(this.mBuilder, builder.mLocalOnly);
        Api20Impl.setGroup(this.mBuilder, builder.mGroupKey);
        Api20Impl.setSortKey(this.mBuilder, builder.mSortKey);
        Api20Impl.setGroupSummary(this.mBuilder, builder.mGroupSummary);
        this.mGroupAlertBehavior = builder.mGroupAlertBehavior;
        Api21Impl.setCategory(this.mBuilder, builder.mCategory);
        Api21Impl.setColor(this.mBuilder, builder.mColor);
        Api21Impl.setVisibility(this.mBuilder, builder.mVisibility);
        Api21Impl.setPublicVersion(this.mBuilder, builder.mPublicVersion);
        Api21Impl.setSound(this.mBuilder, notification.sound, notification.audioAttributes);
        ArrayList<String> arrayList = builder.mPeople;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (String addPerson : arrayList) {
                Api21Impl.addPerson(this.mBuilder, addPerson);
            }
        }
        this.mHeadsUpContentView = builder.mHeadsUpContentView;
        if (builder.mInvisibleActions.size() > 0) {
            Bundle bundle2 = builder.getExtras().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i7 = 0; i7 < builder.mInvisibleActions.size(); i7++) {
                bundle4.putBundle(Integer.toString(i7), NotificationCompatJellybean.getBundleForAction(builder.mInvisibleActions.get(i7)));
            }
            bundle2.putBundle("invisible_actions", bundle4);
            bundle3.putBundle("invisible_actions", bundle4);
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle2);
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle3);
        }
        int i8 = Build.VERSION.SDK_INT;
        Object obj = builder.mSmallIcon;
        if (obj != null) {
            Api23Impl.setSmallIcon(this.mBuilder, obj);
        }
        this.mBuilder.setExtras(builder.mExtras);
        Api24Impl.setRemoteInputHistory(this.mBuilder, builder.mRemoteInputHistory);
        RemoteViews remoteViews = builder.mContentView;
        if (remoteViews != null) {
            Api24Impl.setCustomContentView(this.mBuilder, remoteViews);
        }
        RemoteViews remoteViews2 = builder.mBigContentView;
        if (remoteViews2 != null) {
            Api24Impl.setCustomBigContentView(this.mBuilder, remoteViews2);
        }
        RemoteViews remoteViews3 = builder.mHeadsUpContentView;
        if (remoteViews3 != null) {
            Api24Impl.setCustomHeadsUpContentView(this.mBuilder, remoteViews3);
        }
        Api26Impl.setBadgeIconType(this.mBuilder, builder.mBadgeIcon);
        Api26Impl.setSettingsText(this.mBuilder, builder.mSettingsText);
        Api26Impl.setShortcutId(this.mBuilder, builder.mShortcutId);
        Api26Impl.setTimeoutAfter(this.mBuilder, builder.mTimeout);
        Api26Impl.setGroupAlertBehavior(this.mBuilder, builder.mGroupAlertBehavior);
        if (builder.mColorizedSet) {
            Api26Impl.setColorized(this.mBuilder, builder.mColorized);
        }
        if (!TextUtils.isEmpty(builder.mChannelId)) {
            this.mBuilder.setSound((Uri) null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[]) null);
        }
        Iterator<Object> it2 = builder.mPersonList.iterator();
        if (!it2.hasNext()) {
            Api29Impl.setAllowSystemGeneratedContextualActions(this.mBuilder, builder.mAllowSystemGeneratedContextualActions);
            Api29Impl.setBubbleMetadata(this.mBuilder, NotificationCompat.BubbleMetadata.toPlatform((NotificationCompat.BubbleMetadata) null));
            if (i8 >= 31 && (i2 = builder.mFgsDeferBehavior) != 0) {
                Api31Impl.setForegroundServiceBehavior(this.mBuilder, i2);
            }
            if (builder.mSilent) {
                if (this.mBuilderCompat.mGroupSummary) {
                    this.mGroupAlertBehavior = 2;
                } else {
                    this.mGroupAlertBehavior = 1;
                }
                this.mBuilder.setVibrate((long[]) null);
                this.mBuilder.setSound((Uri) null);
                int i10 = notification.defaults & -4;
                notification.defaults = i10;
                this.mBuilder.setDefaults(i10);
                if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                    Api20Impl.setGroup(this.mBuilder, "silent");
                }
                Api26Impl.setGroupAlertBehavior(this.mBuilder, this.mGroupAlertBehavior);
                return;
            }
            return;
        }
        throw C0212a.h(it2);
    }

    private void addAction(NotificationCompat.Action action) {
        Icon icon;
        Bundle bundle;
        IconCompat iconCompat = action.getIconCompat();
        if (iconCompat != null) {
            icon = iconCompat.toIcon();
        } else {
            icon = null;
        }
        Notification.Action.Builder createBuilder = Api23Impl.createBuilder(icon, action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (RemoteInput addRemoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                Api20Impl.addRemoteInput(createBuilder, addRemoteInput);
            }
        }
        if (action.getExtras() != null) {
            bundle = new Bundle(action.getExtras());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        int i2 = Build.VERSION.SDK_INT;
        Api24Impl.setAllowGeneratedReplies(createBuilder, action.getAllowGeneratedReplies());
        bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
        Api28Impl.setSemanticAction(createBuilder, action.getSemanticAction());
        Api29Impl.setContextual(createBuilder, action.isContextual());
        if (i2 >= 31) {
            Api31Impl.setAuthenticationRequired(createBuilder, action.isAuthenticationRequired());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
        Api20Impl.addExtras(createBuilder, bundle);
        Api20Impl.addAction(this.mBuilder, Api20Impl.build(createBuilder));
    }

    public Notification build() {
        RemoteViews remoteViews;
        Bundle extras;
        RemoteViews makeHeadsUpContentView;
        RemoteViews makeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        if (style != null) {
            remoteViews = style.makeContentView(this);
        } else {
            remoteViews = null;
        }
        Notification buildInternal = buildInternal();
        if (remoteViews != null) {
            buildInternal.contentView = remoteViews;
        } else {
            RemoteViews remoteViews2 = this.mBuilderCompat.mContentView;
            if (remoteViews2 != null) {
                buildInternal.contentView = remoteViews2;
            }
        }
        if (!(style == null || (makeBigContentView = style.makeBigContentView(this)) == null)) {
            buildInternal.bigContentView = makeBigContentView;
        }
        if (!(style == null || (makeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) == null)) {
            buildInternal.headsUpContentView = makeHeadsUpContentView;
        }
        if (!(style == null || (extras = NotificationCompat.getExtras(buildInternal)) == null)) {
            style.addCompatExtras(extras);
        }
        return buildInternal;
    }

    public Notification buildInternal() {
        return this.mBuilder.build();
    }

    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }
}
