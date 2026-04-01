package com.samsung.android.app.sdk.deepsky.textextraction.action;

import android.content.Context;
import android.net.Uri;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.Action;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.BoardingPassAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.BookAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.CouponAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.FoodAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.LocationAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.MembershipAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.ScheduleAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.ShoppingAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.UnclassifiedAction;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionFactory;", "", "<init>", "()V", "getAction", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "classification", "", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "data", "Lcom/google/gson/JsonObject;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionFactory {
    public static final ActionFactory INSTANCE = new ActionFactory();

    private ActionFactory() {
    }

    public final Action getAction(String str, Context context, Uri uri, JsonObject jsonObject) {
        j.e(str, "classification");
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "data");
        switch (str.hashCode()) {
            case -633276745:
                if (str.equals("Schedule")) {
                    return new ScheduleAction(context, uri, jsonObject);
                }
                break;
            case -279816824:
                if (str.equals("Shopping")) {
                    return new ShoppingAction(context, uri, jsonObject);
                }
                break;
            case 2076425:
                if (str.equals("Book")) {
                    return new BookAction(context, uri, jsonObject);
                }
                break;
            case 2195582:
                if (str.equals("Food")) {
                    return new FoodAction(context, uri, jsonObject);
                }
                break;
            case 653222902:
                if (str.equals("Membership")) {
                    return new MembershipAction(context, uri, jsonObject);
                }
                break;
            case 691810669:
                if (str.equals("Boardingpass")) {
                    return new BoardingPassAction(context, uri, jsonObject);
                }
                break;
            case 1965687765:
                if (str.equals("Location")) {
                    return new LocationAction(context, uri, jsonObject);
                }
                break;
            case 2024260678:
                if (str.equals("Coupon")) {
                    return new CouponAction(context, uri, jsonObject);
                }
                break;
        }
        return new UnclassifiedAction(context, uri, jsonObject, str);
    }
}
