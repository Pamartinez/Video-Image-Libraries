package com.samsung.android.app.sdk.deepsky;

import L1.d;
import android.content.Context;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.common.Injector;
import com.samsung.android.app.sdk.deepsky.contribution.ContributionImpl;
import com.samsung.android.app.sdk.deepsky.donation.Donation;
import com.samsung.android.app.sdk.deepsky.donation.DonationImpl;
import com.samsung.android.app.sdk.deepsky.nedgesuggestion.NudgeSuggestionImpl;
import com.samsung.android.app.sdk.deepsky.search.SearchImpl;
import com.samsung.android.app.sdk.deepsky.smartwidget.SmartWidgetImpl;
import com.samsung.android.app.sdk.deepsky.suggestion.SuggestionRequest;
import com.samsung.android.app.sdk.deepsky.suggestion.SuggestionRequestProxy;
import f3.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000  2\u00020\u0001:\u0001 B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u000f\u001a\u0004\u0018\u00010\u00068BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u0012\u001a\u0004\u0018\u00010\t8BX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000bR\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0018\u0010\u0015R\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u001b\u0010\u0015R\u001d\u0010\u001f\u001a\u0004\u0018\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\r\u001a\u0004\b\u001e\u0010\u0015¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/DeepSky;", "", "Landroid/content/Context;", "appContext", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "getSuggestionRequest", "()Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "Lcom/samsung/android/app/sdk/deepsky/donation/Donation;", "getDonation", "()Lcom/samsung/android/app/sdk/deepsky/donation/Donation;", "suggestionRequestByLazy$delegate", "Lme/f;", "getSuggestionRequestByLazy", "suggestionRequestByLazy", "donationByLazy$delegate", "getDonationByLazy", "donationByLazy", "contributionByLazy$delegate", "getContributionByLazy", "()Ljava/lang/Object;", "contributionByLazy", "nudgeSuggestionByLazy$delegate", "getNudgeSuggestionByLazy", "nudgeSuggestionByLazy", "searchByLazy$delegate", "getSearchByLazy", "searchByLazy", "smartWidgetByLazy$delegate", "getSmartWidgetByLazy", "smartWidgetByLazy", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeepSky {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static volatile DeepSky instance;
    private final f contributionByLazy$delegate;
    private final f donationByLazy$delegate;
    private final f nudgeSuggestionByLazy$delegate;
    private final f searchByLazy$delegate;
    private final f smartWidgetByLazy$delegate;
    private final f suggestionRequestByLazy$delegate;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/DeepSky$Companion;", "", "<init>", "()V", "TAG", "", "ONE_UI_4_1_1", "instance", "Lcom/samsung/android/app/sdk/deepsky/DeepSky;", "with", "context", "Landroid/content/Context;", "isSupported", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isSupported(Context context) {
            j.e(context, "context");
            Injector injector = Injector.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            j.d(applicationContext, "getApplicationContext(...)");
            return injector.provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(applicationContext).isContentProviderSupported();
        }

        public final DeepSky with(Context context) {
            DeepSky access$getInstance$cp;
            j.e(context, "context");
            DeepSky access$getInstance$cp2 = DeepSky.instance;
            if (access$getInstance$cp2 != null) {
                return access$getInstance$cp2;
            }
            synchronized (this) {
                access$getInstance$cp = DeepSky.instance;
                if (access$getInstance$cp == null) {
                    Context applicationContext = context.getApplicationContext();
                    j.d(applicationContext, "getApplicationContext(...)");
                    access$getInstance$cp = new DeepSky(applicationContext, (e) null);
                    DeepSky.instance = access$getInstance$cp;
                    Log.i("DeepSkyLibrary", "Version = 6.1.0");
                }
            }
            return access$getInstance$cp;
        }

        private Companion() {
        }
    }

    public /* synthetic */ DeepSky(Context context, e eVar) {
        this(context);
    }

    /* access modifiers changed from: private */
    public static final ContributionImpl contributionByLazy_delegate$lambda$5(Context context) {
        Injector injector = Injector.INSTANCE;
        ContributionImpl contributionImpl = new ContributionImpl(context, injector.provideAppSearch$deepsky_sdk_smartsuggestion_6_1_0_release(context), injector.provideShortcutManagerWrapper$deepsky_sdk_smartsuggestion_6_1_0_release(context));
        if (contributionImpl.checkIfAccessAllowed()) {
            return contributionImpl;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final DonationImpl donationByLazy_delegate$lambda$3(Context context) {
        DonationImpl donationImpl = new DonationImpl(Injector.INSTANCE.provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(context));
        if (donationImpl.checkIfAccessAllowed()) {
            return donationImpl;
        }
        return null;
    }

    private final Donation getDonationByLazy() {
        return (Donation) this.donationByLazy$delegate.getValue();
    }

    private final SuggestionRequest getSuggestionRequestByLazy() {
        return (SuggestionRequest) this.suggestionRequestByLazy$delegate.getValue();
    }

    public static final boolean isSupported(Context context) {
        return Companion.isSupported(context);
    }

    /* access modifiers changed from: private */
    public static final NudgeSuggestionImpl nudgeSuggestionByLazy_delegate$lambda$7(Context context) {
        NudgeSuggestionImpl nudgeSuggestionImpl = new NudgeSuggestionImpl(context, Injector.INSTANCE.provideAppSearch$deepsky_sdk_smartsuggestion_6_1_0_release(context));
        if (nudgeSuggestionImpl.checkIfAccessAllowed()) {
            return nudgeSuggestionImpl;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final SearchImpl searchByLazy_delegate$lambda$9(Context context) {
        Injector injector = Injector.INSTANCE;
        SearchImpl searchImpl = new SearchImpl(context, injector.provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(context), injector.shareSystemDatasource$deepsky_sdk_smartsuggestion_6_1_0_release(context));
        if (searchImpl.checkIfAccessAllowed()) {
            return searchImpl;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final SmartWidgetImpl smartWidgetByLazy_delegate$lambda$11(Context context) {
        Injector injector = Injector.INSTANCE;
        SmartWidgetImpl smartWidgetImpl = new SmartWidgetImpl(injector.provideServiceCaller$deepsky_sdk_smartsuggestion_6_1_0_release(context), injector.shareSystemDatasource$deepsky_sdk_smartsuggestion_6_1_0_release(context));
        if (smartWidgetImpl.checkIfAccessAllowed()) {
            return smartWidgetImpl;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final SuggestionRequestProxy suggestionRequestByLazy_delegate$lambda$1(Context context) {
        SuggestionRequestProxy suggestionRequestProxy = new SuggestionRequestProxy(context);
        if (suggestionRequestProxy.checkIfAccessAllowed()) {
            return suggestionRequestProxy;
        }
        return null;
    }

    public static final DeepSky with(Context context) {
        return Companion.with(context);
    }

    public final Donation getDonation() {
        return getDonationByLazy();
    }

    public final SuggestionRequest getSuggestionRequest() {
        return getSuggestionRequestByLazy();
    }

    private DeepSky(Context context) {
        this.suggestionRequestByLazy$delegate = d.q(new a(context, 0));
        this.donationByLazy$delegate = d.q(new a(context, 1));
        this.contributionByLazy$delegate = d.q(new a(context, 2));
        this.nudgeSuggestionByLazy$delegate = d.q(new a(context, 3));
        this.searchByLazy$delegate = d.q(new a(context, 4));
        this.smartWidgetByLazy$delegate = d.q(new a(context, 5));
    }
}
