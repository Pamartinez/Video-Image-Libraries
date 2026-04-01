package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.C0064d;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.contact.AddToContactAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.contact.ContactCallAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.contact.ContactEmailAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.contact.ContactMessageAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0007\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0013\u001a\u00020\u0012*\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\u0012*\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0014J#\u0010\u0016\u001a\u00020\u0012*\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0002¢\u0006\u0004\b\u0016\u0010\u0014J#\u0010\u0017\u001a\u00020\u0012*\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010 \u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/AddressBookAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/d;", "addressBookParsedResult", "<init>", "(Landroid/content/Context;LX2/d;)V", "", "phoneNumber", "getContactBodyText", "(Ljava/lang/String;)Ljava/lang/String;", "", "hasPhoneNumber", "()Z", "Ljava/util/ArrayList;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Lkotlin/collections/ArrayList;", "Lme/x;", "addAddToContactActionIfPossible", "(Ljava/util/ArrayList;)V", "addContactCallActionIfPossible", "addContactMessageActionIfPossible", "addContactEmailActionIfPossible", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "getActions", "()Ljava/util/List;", "LX2/d;", "appContext", "Landroid/content/Context;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AddressBookAdapter implements BarcodeParsedResult {
    public static final Companion Companion = new Companion((e) null);
    private final C0064d addressBookParsedResult;
    private final Context appContext;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/AddressBookAdapter$Companion;", "", "<init>", "()V", "TAG", "", "BARCODE_DIALOG_BODY_CONTACT_ONE_INPUT", "BARCODE_DIALOG_BODY_CONTACT_TWO_INPUT", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public AddressBookAdapter(Context context, C0064d dVar) {
        j.e(context, "context");
        j.e(dVar, "addressBookParsedResult");
        this.addressBookParsedResult = dVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final void addAddToContactActionIfPossible(ArrayList<Action> arrayList) {
        AddToContactAction addToContactAction = new AddToContactAction(this.appContext, this.addressBookParsedResult);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, addToContactAction.getIntent())) {
            addToContactAction = null;
        }
        if (addToContactAction != null) {
            arrayList.add(addToContactAction);
        }
    }

    private final void addContactCallActionIfPossible(ArrayList<Action> arrayList) {
        ContactCallAction contactCallAction = new ContactCallAction(this.appContext, this.addressBookParsedResult, (Injector) null, 4, (e) null);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, contactCallAction.getIntent())) {
            contactCallAction = null;
        }
        if (contactCallAction != null) {
            arrayList.add(contactCallAction);
        }
    }

    private final void addContactEmailActionIfPossible(ArrayList<Action> arrayList) {
        String str;
        String[] strArr = this.addressBookParsedResult.f913i;
        if (strArr != null) {
            ContactEmailAction contactEmailAction = null;
            if (strArr.length == 0 || (str = strArr[0]) == null || str.length() <= 0) {
                strArr = null;
            }
            if (strArr != null) {
                ContactEmailAction contactEmailAction2 = new ContactEmailAction(this.appContext, this.addressBookParsedResult, (Injector) null, 4, (e) null);
                if (ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, contactEmailAction2.getIntent())) {
                    contactEmailAction = contactEmailAction2;
                }
                if (contactEmailAction != null) {
                    arrayList.add(contactEmailAction);
                }
            }
        }
    }

    private final void addContactMessageActionIfPossible(ArrayList<Action> arrayList) {
        ContactMessageAction contactMessageAction = new ContactMessageAction(this.appContext, this.addressBookParsedResult, (Injector) null, 4, (e) null);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, contactMessageAction.getIntent())) {
            contactMessageAction = null;
        }
        if (contactMessageAction != null) {
            arrayList.add(contactMessageAction);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r0 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0044, code lost:
        if (r7 != null) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getContactBodyText(java.lang.String r8) {
        /*
            r7 = this;
            X2.d r0 = r7.addressBookParsedResult
            java.lang.String[] r0 = r0.b
            r1 = 0
            java.lang.String r2 = ""
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0026
            int r5 = r0.length
            if (r5 != 0) goto L_0x0010
            r5 = r3
            goto L_0x0011
        L_0x0010:
            r5 = r4
        L_0x0011:
            if (r5 != 0) goto L_0x001e
            r5 = r0[r4]
            if (r5 == 0) goto L_0x001e
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r0 = r1
        L_0x001f:
            if (r0 == 0) goto L_0x0026
            r0 = r0[r4]
            if (r0 == 0) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            r0 = r2
        L_0x0027:
            X2.d r7 = r7.addressBookParsedResult
            java.lang.String[] r7 = r7.f913i
            if (r7 == 0) goto L_0x0047
            int r5 = r7.length
            if (r5 != 0) goto L_0x0032
            r5 = r3
            goto L_0x0033
        L_0x0032:
            r5 = r4
        L_0x0033:
            if (r5 != 0) goto L_0x0040
            r5 = r7[r4]
            if (r5 == 0) goto L_0x0040
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x0040
            r1 = r7
        L_0x0040:
            if (r1 == 0) goto L_0x0047
            r7 = r1[r4]
            if (r7 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r7 = r2
        L_0x0048:
            int r1 = r0.length()
            java.lang.String r4 = "%s"
            r5 = 2
            java.lang.String r6 = "%1$s\n%2$s"
            if (r1 <= 0) goto L_0x0086
            int r1 = r8.length()
            if (r1 <= 0) goto L_0x0066
            java.lang.Object[] r7 = new java.lang.Object[]{r0, r8}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r5)
            java.lang.String r7 = java.lang.String.format(r6, r7)
            return r7
        L_0x0066:
            int r8 = r7.length()
            if (r8 <= 0) goto L_0x0079
            java.lang.Object[] r7 = new java.lang.Object[]{r0, r7}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r5)
            java.lang.String r7 = java.lang.String.format(r6, r7)
            return r7
        L_0x0079:
            java.lang.Object[] r7 = new java.lang.Object[]{r0}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r3)
            java.lang.String r7 = java.lang.String.format(r4, r7)
            return r7
        L_0x0086:
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x009f
            int r0 = r7.length()
            if (r0 <= 0) goto L_0x009f
            java.lang.Object[] r7 = new java.lang.Object[]{r8, r7}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r5)
            java.lang.String r7 = java.lang.String.format(r6, r7)
            return r7
        L_0x009f:
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x00b2
            java.lang.Object[] r7 = new java.lang.Object[]{r8}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r3)
            java.lang.String r7 = java.lang.String.format(r4, r7)
            return r7
        L_0x00b2:
            int r8 = r7.length()
            if (r8 <= 0) goto L_0x00c5
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r3)
            java.lang.String r7 = java.lang.String.format(r4, r7)
            return r7
        L_0x00c5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.AddressBookAdapter.getContactBodyText(java.lang.String):java.lang.String");
    }

    private final boolean hasPhoneNumber() {
        boolean z;
        String str;
        String[] strArr = this.addressBookParsedResult.g;
        if (strArr != null) {
            if (strArr.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z || (str = strArr[0]) == null || str.length() <= 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    public List<Action> getActions() {
        ArrayList arrayList = new ArrayList();
        addAddToContactActionIfPossible(arrayList);
        if (hasPhoneNumber()) {
            addContactCallActionIfPossible(arrayList);
            addContactMessageActionIfPossible(arrayList);
        }
        addContactEmailActionIfPossible(arrayList);
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getBody() {
        /*
            r3 = this;
            X2.d r0 = r3.addressBookParsedResult
            java.lang.String[] r0 = r0.g
            if (r0 == 0) goto L_0x0022
            int r1 = r0.length
            r2 = 0
            if (r1 != 0) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = r2
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            r1 = r0[r2]
            if (r1 == 0) goto L_0x001a
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            if (r0 == 0) goto L_0x0022
            r0 = r0[r2]
            if (r0 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            java.lang.String r0 = ""
        L_0x0024:
            java.lang.String r3 = r3.getContactBodyText(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.AddressBookAdapter.getBody():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getBodyTts() {
        /*
            r3 = this;
            X2.d r0 = r3.addressBookParsedResult
            java.lang.String[] r0 = r0.g
            if (r0 == 0) goto L_0x0022
            int r1 = r0.length
            r2 = 0
            if (r1 != 0) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = r2
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            r1 = r0[r2]
            if (r1 == 0) goto L_0x001a
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            if (r0 == 0) goto L_0x0022
            r0 = r0[r2]
            if (r0 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            java.lang.String r0 = ""
        L_0x0024:
            com.samsung.android.app.sdk.deepsky.barcode.parser.common.ParserUtil r1 = com.samsung.android.app.sdk.deepsky.barcode.parser.common.ParserUtil.INSTANCE
            java.lang.String r0 = r1.getTtsOneDigitNumber(r0)
            java.lang.String r3 = r3.getContactBodyText(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.AddressBookAdapter.getBodyTts():java.lang.String");
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_dialog_title_contact);
        j.d(string, "getString(...)");
        return string;
    }
}
