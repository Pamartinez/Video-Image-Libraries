package com.samsung.android.app.sdk.deepsky.suggestion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionData;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionItem;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/DummySuggestionRequest;", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionRequest;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;", "capability", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "getSuggestionItem", "(Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "getMaybeEventItem", "()Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "Landroid/os/Bundle;", "extras", "Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "requestSuggestion", "(Lcom/samsung/android/app/sdk/deepsky/suggestion/CapabilityEnum;Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/suggestion/SuggestionResponse;", "Landroid/content/Context;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DummySuggestionRequest implements SuggestionRequest {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/suggestion/DummySuggestionRequest$Companion;", "", "<init>", "()V", "MAYBE_EVENT_KNOWLEDGE_ID", "", "MAYBE_EVENT_KNOWLEDGE_TITLE", "MAYBE_EVENT_KNOWLEDGE_DESCRIPTION", "MAYBE_EVENT_KNOWLEDGE_ICON_URI", "MAYBE_EVENT_KNOWLEDGE_IS_VALID", "", "MAYBE_EVENT_KNOWLEDGE_ITEM_DESCRIPTION", "MAYBE_EVENT_KNOWLEDGE_ITEM_TITLE", "MAYBE_EVENT_KNOWLEDGE_ITEM_URL", "MAYBE_EVENT_KNOWLEDGE_STRUCTURED_DATA", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CapabilityEnum.values().length];
            try {
                iArr[CapabilityEnum.MAYBE_EVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DummySuggestionRequest(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final SuggestionItem getMaybeEventItem() {
        Uri parse = Uri.parse("android.resource://com.samsung.android.smartsuggestions/drawable/calendar");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SuggestionData((String) null, "Typhoon with Radiation City", "09:30 PM - 11:30 PM", (Uri) null, (Uri) null, (Bundle) null, new JSONObject("{\n                  \"@context\": \"https://schema.org\",\n                  \"@type\": \"Event\",\n                  \"location\": {\n                    \"@type\": \"Place\",\n                    \"address\": {\n                      \"@type\": \"PostalAddress\",\n                      \"addressLocality\": \"Denver\",\n                      \"addressRegion\": \"CO\",\n                      \"postalCode\": \"80209\",\n                      \"streetAddress\": \"7 S. Broadway\"\n                    },\n                    \"name\": \"The Hi-Dive\"\n                  },\n                  \"name\": \"Typhoon with Radiation City\",\n                  \"startDate\": \"2013-09-14T21:30\",\n                  \"endDate\": \"2013-09-14T23:30\",\n                  \"attendee\": {\n                    \"@type\": \"Person\",\n                    \"name\": \"Darren R Story\"\n                  }\n                }"), "gi://applink/action/calendar/view?startDate=1627351200000", 0, 313, (e) null));
        return new SuggestionItem("_:node1fb1s26kux6", "MAYBE EVENT", "There is one may event", parse, (Uri) null, arrayList, MapUtil.INVALID_LOCATION, true, 0, (Bundle) null, (String) null, 1872, (e) null);
    }

    private final SuggestionItem getSuggestionItem(CapabilityEnum capabilityEnum) {
        if (WhenMappings.$EnumSwitchMapping$0[capabilityEnum.ordinal()] == 1) {
            return getMaybeEventItem();
        }
        return null;
    }

    public SuggestionResponse requestSuggestion(CapabilityEnum capabilityEnum, Bundle bundle) {
        j.e(capabilityEnum, "capability");
        j.e(bundle, "extras");
        SuggestionItem suggestionItem = getSuggestionItem(capabilityEnum);
        if (suggestionItem != null) {
            return new SuggestionResponseImpl(this.context, suggestionItem);
        }
        return null;
    }
}
