package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import L1.d;
import Tf.n;
import Tf.v;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.CalendarEventBundleWrapper;
import com.samsung.android.sum.core.types.NumericEnum;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;
import k3.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import ne.C1195m;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001b\b\u0000\u0018\u0000 42\u00020\u0001:\u00014B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0018\u0010\u0012J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJG\u0010\"\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\nH\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0015H\u0016¢\u0006\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u00048\u0016X\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\n8\u0016X\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010\fR\u001b\u00100\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\fR\u001b\u00103\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b1\u0010.\u001a\u0004\b2\u0010\f¨\u00065"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/ScheduleAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "info", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "initNotes", "()Ljava/lang/String;", "initLocation", "Landroid/content/Intent;", "calendarIntent", "Lme/x;", "putStartTime", "(Landroid/content/Intent;)V", "time", "Lme/i;", "", "checkValidTime", "(Ljava/lang/String;)Lme/i;", "putEndTime", "key", "", "getStringTimeToInt", "(Ljava/lang/String;)I", "year", "month", "day", "hour", "min", "putTimeToIntent", "(IIIIILandroid/content/Intent;Ljava/lang/String;)V", "runAction", "()Z", "iconUri", "Landroid/net/Uri;", "getIconUri", "()Landroid/net/Uri;", "title", "Ljava/lang/String;", "getTitle", "notes$delegate", "Lme/f;", "getNotes", "notes", "location$delegate", "getLocation", "location", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduleAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Uri iconUri;
    private final f location$delegate = d.q(new a(this, 1));
    private final f notes$delegate = d.q(new a(this, 0));
    private final String title;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/ScheduleAction$Companion;", "", "<init>", "()V", "TAG", "", "CALENDAR_PACKAGE_URI", "JSON_KEY_TITLE", "JSON_KEY_RESERVATION_NUMBER", "JSON_KEY_START_PLACE", "JSON_KEY_END_PLACE", "JSON_KEY_START_YEAR", "JSON_KEY_START_MONTH", "JSON_KEY_START_DAY", "JSON_KEY_START_TIME", "JSON_KEY_END_YEAR", "JSON_KEY_END_MONTH", "JSON_KEY_END_DAY", "JSON_KEY_END_TIME", "NA", "AM", "PM", "TIME_DELIMITERS", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScheduleAction(Context context, Uri uri, JsonObject jsonObject) {
        super(context, uri, jsonObject);
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        this.iconUri = getResourceUri(R$drawable.capsule_calendar, context);
        String string = context.getString(R$string.add_event);
        j.d(string, "getString(...)");
        this.title = string;
    }

    private final i checkValidTime(String str) {
        if (str == null || str.length() == 0) {
            return new i("00:00", Boolean.FALSE);
        }
        if (n.u0(str, "N/A")) {
            return new i("00:00", Boolean.FALSE);
        }
        Pattern compile = Pattern.compile("[am|pm]", 2);
        Pattern compile2 = Pattern.compile("pm", 2);
        String replaceAll = compile.matcher(str).replaceAll("");
        j.d(replaceAll, "replaceAll(...)");
        String obj = n.R0(replaceAll).toString();
        j.b(compile2);
        return new i(obj, Boolean.valueOf(compile2.matcher(str).find()));
    }

    private final String getLocation() {
        return (String) this.location$delegate.getValue();
    }

    private final String getNotes() {
        return (String) this.notes$delegate.getValue();
    }

    private final int getStringTimeToInt(String str) {
        String str2;
        JsonElement jsonElement = getInfo().get(str);
        if (jsonElement != null) {
            str2 = jsonElement.getAsString();
        } else {
            str2 = null;
        }
        if (str2 == null || str2.equals("N/A") || !TextUtils.isDigitsOnly(str2)) {
            return -1;
        }
        return Integer.parseInt(str2);
    }

    private final String initLocation() {
        String str;
        String str2;
        JsonElement jsonElement = getInfo().get("StartPlace");
        if (jsonElement == null || (str = jsonElement.getAsString()) == null) {
            str = "N/A";
        }
        JsonElement jsonElement2 = getInfo().get("EndPlace");
        if (jsonElement2 == null || (str2 = jsonElement2.getAsString()) == null) {
            str2 = "N/A";
        }
        return n.R0(v.s0(str + " " + str2, "N/A", "")).toString();
    }

    private final String initNotes() {
        String str;
        JsonElement jsonElement = getInfo().get("ReservationNumber");
        if (jsonElement == null || (str = jsonElement.getAsString()) == null) {
            str = "N/A";
        }
        if (str.equals("N/A")) {
            return "";
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static final String location_delegate$lambda$1(ScheduleAction scheduleAction) {
        return scheduleAction.initLocation();
    }

    /* access modifiers changed from: private */
    public static final String notes_delegate$lambda$0(ScheduleAction scheduleAction) {
        return scheduleAction.initNotes();
    }

    private final void putEndTime(Intent intent) {
        String str;
        int i2;
        int i7;
        int stringTimeToInt = getStringTimeToInt("EndYear");
        int stringTimeToInt2 = getStringTimeToInt("EndMonth");
        int stringTimeToInt3 = getStringTimeToInt("EndDay");
        JsonElement jsonElement = getInfo().get("EndTime");
        if (jsonElement != null) {
            str = jsonElement.getAsString();
        } else {
            str = null;
        }
        i checkValidTime = checkValidTime(str);
        String str2 = (String) checkValidTime.d;
        boolean booleanValue = ((Boolean) checkValidTime.e).booleanValue();
        int i8 = 0;
        if (!j.a(str2, "N/A")) {
            List K02 = n.K0(str2, new String[]{NumericEnum.SEP});
            if (K02.size() == 2) {
                int parseInt = Integer.parseInt((String) K02.get(0));
                if (parseInt < 12 && booleanValue) {
                    parseInt += 12;
                }
                i8 = parseInt;
                i7 = Integer.parseInt((String) K02.get(1));
            } else {
                i7 = 0;
            }
            i2 = i8;
            i8 = i7;
        } else {
            i2 = 0;
        }
        putTimeToIntent(stringTimeToInt, stringTimeToInt2, stringTimeToInt3, i2, i8, intent, "endTime");
    }

    private final void putStartTime(Intent intent) {
        String str;
        int i2;
        int i7;
        int stringTimeToInt = getStringTimeToInt("StartYear");
        int stringTimeToInt2 = getStringTimeToInt("StartMonth");
        int stringTimeToInt3 = getStringTimeToInt("StartDay");
        JsonElement jsonElement = getInfo().get("StartTime");
        if (jsonElement != null) {
            str = jsonElement.getAsString();
        } else {
            str = null;
        }
        i checkValidTime = checkValidTime(str);
        String str2 = (String) checkValidTime.d;
        boolean booleanValue = ((Boolean) checkValidTime.e).booleanValue();
        int i8 = 0;
        if (!j.a(str2, "N/A")) {
            List K02 = n.K0(str2, new String[]{NumericEnum.SEP});
            if (K02.size() == 2) {
                i8 = Integer.parseInt((String) K02.get(0));
                if (i8 < 12 && booleanValue) {
                    i8 += 12;
                }
                i7 = Integer.parseInt((String) K02.get(1));
            } else {
                i7 = 0;
            }
            i2 = i7;
        } else {
            i2 = 0;
        }
        Log.d("ScheduleAction", N2.j.d("valid Time = ", str2, " (original data was ", str, ")"));
        putTimeToIntent(stringTimeToInt, stringTimeToInt2, stringTimeToInt3, i8, i2, intent, "beginTime");
    }

    private final void putTimeToIntent(int i2, int i7, int i8, int i10, int i11, Intent intent, String str) {
        if (!C1195m.q0(Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10), Integer.valueOf(i11)).contains(-1)) {
            try {
                j.b(intent.putExtra(str, LocalDateTime.of(i2, i7, i8, i10, i11).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
            } catch (DateTimeException e) {
                Log.e("ScheduleAction", "Exception during convert date/time : " + e);
            }
        }
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean runAction() {
        LibLogger.i("ScheduleAction", "runAction");
        Intent intent = new Intent("android.intent.action.INSERT", Uri.parse("content://com.android.calendar/events"));
        JsonElement jsonElement = getInfo().get("Title");
        if (jsonElement != null) {
            intent.putExtra("title", jsonElement.getAsString());
        }
        intent.putExtra("eventLocation", getLocation());
        putStartTime(intent);
        putEndTime(intent);
        intent.putExtra("description", getNotes());
        intent.putExtra(CalendarEventBundleWrapper.BUNDLE_KEY_ALL_DAY, false);
        intent.setFlags(268435456);
        getContext().startActivity(intent);
        return true;
    }
}
