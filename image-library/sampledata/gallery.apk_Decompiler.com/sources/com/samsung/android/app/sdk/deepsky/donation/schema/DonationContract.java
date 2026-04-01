package com.samsung.android.app.sdk.deepsky.donation.schema;

import android.net.Uri;
import java.util.Locale;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001f\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001f\u0010\n\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR\u001f\u0010\r\u001a\n \u0005*\u0004\u0018\u00010\f0\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/donation/schema/DonationContract;", "", "<init>", "()V", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "CONTENT_URI", "Landroid/net/Uri;", "getCONTENT_URI", "()Landroid/net/Uri;", "READ_CONTENT_URI", "getREAD_CONTENT_URI", "Ljava/util/Locale;", "DONATION_LOCALE", "Ljava/util/Locale;", "getDONATION_LOCALE", "()Ljava/util/Locale;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DonationContract {
    private static final Uri CONTENT_URI = Uri.parse("content://com.samsung.android.app.deepsky.donation.donateaction/ActionDataTable");
    private static final Locale DONATION_LOCALE = Locale.US;
    public static final DonationContract INSTANCE = new DonationContract();
    private static final Uri READ_CONTENT_URI = Uri.parse("content://com.samsung.android.app.deepsky.donation.donateaction/ActionView");

    private DonationContract() {
    }

    public final Uri getCONTENT_URI() {
        return CONTENT_URI;
    }
}
