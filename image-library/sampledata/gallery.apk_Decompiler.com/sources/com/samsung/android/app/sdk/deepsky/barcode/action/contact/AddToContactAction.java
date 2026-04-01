package com.samsung.android.app.sdk.deepsky.barcode.action.contact;

import Tf.n;
import X2.C0064d;
import X2.C0065e;
import X2.C0066f;
import X2.k;
import X2.l;
import X2.q;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\r\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u0013\u0010\u000eJ'\u0010\u0014\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u0014\u0010\u000eJ'\u0010\u0015\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u0015\u0010\u000eJ\u0019\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\u001b\u0010\u0012J\u0019\u0010\u001c\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0004\b\u001c\u0010\u001aJ'\u0010\u001d\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u001d\u0010\u000eJ'\u0010\u001e\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u001e\u0010\u000eJ'\u0010\u001f\u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u001f\u0010\u000eJ'\u0010 \u001a\u00020\f2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b \u0010\u000eJ\u0017\u0010\"\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0018H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0016H\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u000fH\u0016¢\u0006\u0004\b(\u0010)R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010*¨\u0006+"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/contact/AddToContactAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/d;", "addressBookParsedResult", "<init>", "(Landroid/content/Context;LX2/d;)V", "Ljava/util/ArrayList;", "Landroid/content/ContentValues;", "Lkotlin/collections/ArrayList;", "values", "Lme/x;", "updateContactNickName", "(Ljava/util/ArrayList;)V", "Landroid/content/Intent;", "intent", "updateContactName", "(Landroid/content/Intent;Ljava/util/ArrayList;)V", "updateContactNumber", "updateContactOrigination", "updateContactEmail", "", "type", "", "getEmailType", "(Ljava/lang/String;)I", "updateContactAddress", "getAddressType", "updateContactUrl", "updateContactNote", "updateContactBirthday", "updateContactEvent", "LX2/k;", "getEventType", "(LX2/k;)I", "getTitleId", "()I", "getActionId", "()Ljava/lang/String;", "getIntent", "()Landroid/content/Intent;", "LX2/d;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AddToContactAction extends Action {
    private final C0064d addressBookParsedResult;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                X2.k[] r0 = X2.k.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                X2.k r1 = X2.k.ANNIVERSARY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                X2.k r1 = X2.k.CUSTOM     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.barcode.action.contact.AddToContactAction.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddToContactAction(Context context, C0064d dVar) {
        super(context);
        j.e(context, "context");
        j.e(dVar, "addressBookParsedResult");
        this.addressBookParsedResult = dVar;
    }

    private final int getAddressType(String str) {
        if (str == null) {
            return 3;
        }
        if (str.equals("HOME")) {
            return 1;
        }
        if (str.equals("WORk")) {
            return 2;
        }
        return 0;
    }

    private final int getEmailType(String str) {
        if (str == null) {
            return 3;
        }
        if (str.equals("WORK") || n.u0(str, "WORK")) {
            return 2;
        }
        if (str.equals("HOME") || n.u0(str, "HOME")) {
            return 1;
        }
        if (str.equals("MOBILE") || n.u0(str, "MOBILE")) {
            return 4;
        }
        return 0;
    }

    private final int getEventType(k kVar) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[kVar.ordinal()];
        if (i2 == 1) {
            return 1;
        }
        if (i2 != 2) {
            return 2;
        }
        return 0;
    }

    private final void updateContactAddress(Intent intent, ArrayList<ContentValues> arrayList) {
        boolean z;
        x xVar;
        String str;
        C0065e[] eVarArr = this.addressBookParsedResult.u;
        String[] strArr = null;
        if (eVarArr != null) {
            for (C0065e eVar : eVarArr) {
                ContentValues c5 = C0086a.c(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/postal-address_v2");
                c5.put("data4", eVar.d);
                c5.put("data5", eVar.b);
                c5.put("data8", eVar.f);
                c5.put("data7", eVar.e);
                c5.put("data9", eVar.g);
                c5.put("data10", eVar.f919h);
                c5.put("data6", eVar.f918c);
                int addressType = getAddressType(eVar.f917a);
                c5.put("data2", Integer.valueOf(addressType));
                if (addressType == 0) {
                    c5.put("data3", eVar.f917a);
                }
                arrayList.add(c5);
            }
            String[] strArr2 = this.addressBookParsedResult.m;
            if (strArr2 != null) {
                int length = strArr2.length;
                int i2 = 0;
                int i7 = 0;
                while (i2 < length) {
                    String str2 = strArr2[i2];
                    int i8 = i7 + 1;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/postal-address_v2");
                    contentValues.put("data1", str2);
                    String[] strArr3 = this.addressBookParsedResult.n;
                    if (strArr3 != null) {
                        str = strArr3[i7];
                    } else {
                        str = null;
                    }
                    contentValues.put("data2", Integer.valueOf(getAddressType(str)));
                    arrayList.add(contentValues);
                    i2++;
                    i7 = i8;
                }
                xVar = x.f4917a;
            } else {
                xVar = null;
            }
            if (xVar != null) {
                return;
            }
        }
        String[] strArr4 = this.addressBookParsedResult.m;
        boolean z3 = true;
        if (strArr4 != null) {
            if (strArr4.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                strArr4 = null;
            }
            if (strArr4 != null) {
                intent.putExtra("postal", strArr4[0]);
            }
        }
        String[] strArr5 = this.addressBookParsedResult.n;
        if (strArr5 != null) {
            if (strArr5.length != 0) {
                z3 = false;
            }
            if (!z3) {
                strArr = strArr5;
            }
            if (strArr != null) {
                intent.putExtra("postal_type", getAddressType(strArr[0]));
            }
        }
    }

    private final void updateContactBirthday(ArrayList<ContentValues> arrayList) {
        C0066f fVar = this.addressBookParsedResult.q;
        if (fVar != null) {
            ContentValues c5 = C0086a.c(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/contact_event");
            c5.put("data1", fVar.f920c);
            c5.put("data2", 3);
            c5.put("data15", (String) fVar.f943a);
            c5.put("data14", (String) fVar.b);
            arrayList.add(c5);
        }
    }

    private final void updateContactEmail(ArrayList<ContentValues> arrayList) {
        String[] strArr = this.addressBookParsedResult.f913i;
        if (strArr != null) {
            int length = strArr.length;
            int i2 = 0;
            int i7 = 0;
            while (i2 < length) {
                String str = strArr[i2];
                int i8 = i7 + 1;
                ContentValues contentValues = new ContentValues();
                contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/email_v2");
                contentValues.put("data1", str);
                String[] strArr2 = this.addressBookParsedResult.f914j;
                if (strArr2 != null) {
                    int emailType = getEmailType(strArr2[i7]);
                    contentValues.put("data2", Integer.valueOf(emailType));
                    if (emailType == 0) {
                        contentValues.put("data3", strArr2[i7]);
                    }
                }
                arrayList.add(contentValues);
                i2++;
                i7 = i8;
            }
        }
    }

    private final void updateContactEvent(ArrayList<ContentValues> arrayList) {
        l[] lVarArr = this.addressBookParsedResult.v;
        if (lVarArr != null) {
            ArrayList l0 = C1192j.l0(lVarArr);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = l0.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (((l) next).e == k.ANNIVERSARY) {
                    arrayList2.add(next);
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                l lVar = (l) it2.next();
                ContentValues c5 = C0086a.c(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/contact_event");
                c5.put("data1", lVar.f926c);
                c5.put("data15", (String) lVar.f943a);
                c5.put("data14", (String) lVar.b);
                k kVar = lVar.e;
                j.d(kVar, "type");
                c5.put("data2", Integer.valueOf(getEventType(kVar)));
                String str = lVar.d;
                if (str != null) {
                    if (str.length() <= 0) {
                        str = null;
                    }
                    if (str != null) {
                        c5.put("data3", str);
                    }
                }
                arrayList.add(c5);
            }
        }
    }

    private final void updateContactName(Intent intent, ArrayList<ContentValues> arrayList) {
        boolean z;
        q[] qVarArr = this.addressBookParsedResult.f911c;
        if (qVarArr != null) {
            for (q qVar : qVarArr) {
                ContentValues c5 = C0086a.c(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/name");
                c5.put("data3", qVar.f934a);
                c5.put("data2", qVar.b);
                c5.put("data5", qVar.f935c);
                c5.put("data4", qVar.d);
                c5.put("data6", qVar.e);
                c5.put("data9", qVar.f);
                c5.put("data7", qVar.g);
                c5.put("data8", qVar.f936h);
                arrayList.add(c5);
            }
        }
        C0064d dVar = this.addressBookParsedResult;
        String[] strArr = dVar.b;
        String[] strArr2 = null;
        boolean z3 = true;
        if (strArr != null) {
            if (strArr.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                strArr = null;
            }
            if (strArr != null) {
                intent.putExtra("name", strArr[0]);
                return;
            }
        }
        String[] strArr3 = dVar.d;
        if (strArr3 != null) {
            if (strArr3.length != 0) {
                z3 = false;
            }
            if (!z3) {
                strArr2 = strArr3;
            }
            if (strArr2 != null) {
                intent.putExtra("name", strArr2[0]);
            }
        }
    }

    private final void updateContactNickName(ArrayList<ContentValues> arrayList) {
        String[] strArr = this.addressBookParsedResult.e;
        if (strArr != null) {
            for (String put : strArr) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/nickname");
                contentValues.put("data1", put);
                arrayList.add(contentValues);
            }
        }
    }

    private final void updateContactNote(ArrayList<ContentValues> arrayList) {
        String str = this.addressBookParsedResult.l;
        if (str != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/note");
            contentValues.put("data1", str);
            arrayList.add(contentValues);
        }
    }

    private final void updateContactNumber(ArrayList<ContentValues> arrayList) {
        int i2;
        String[] strArr = this.addressBookParsedResult.g;
        if (strArr != null) {
            int length = strArr.length;
            int i7 = 0;
            int i8 = 0;
            while (i7 < length) {
                String str = strArr[i7];
                int i10 = i8 + 1;
                ContentValues contentValues = new ContentValues();
                contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/phone_v2");
                contentValues.put("data1", str);
                String[] strArr2 = this.addressBookParsedResult.f912h;
                if (strArr2 != null) {
                    String str2 = strArr2[i8];
                    if (str2 != null) {
                        i2 = NumberType.valueOf(str2).getContactPhoneType();
                    } else {
                        i2 = 7;
                    }
                    contentValues.put("data2", Integer.valueOf(i2));
                    if (i2 == 0) {
                        contentValues.put("data3", Integer.valueOf(i2));
                    }
                }
                arrayList.add(contentValues);
                i7++;
                i8 = i10;
            }
        }
    }

    private final void updateContactOrigination(ArrayList<ContentValues> arrayList) {
        String str = this.addressBookParsedResult.f915o;
        if (str != null) {
            String str2 = (String) C1194l.O0(0, n.K0(str, new String[]{"\n"}));
            String str3 = (String) C1194l.O0(1, n.K0(str, new String[]{"\n"}));
            ContentValues c5 = C0086a.c(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/organization");
            c5.put("data4", this.addressBookParsedResult.r);
            if (str2 != null) {
                c5.put("data1", str2);
            }
            if (str3 != null) {
                c5.put("data5", str3);
            }
            arrayList.add(c5);
        }
    }

    private final void updateContactUrl(ArrayList<ContentValues> arrayList) {
        String[] strArr = this.addressBookParsedResult.s;
        if (strArr != null) {
            for (String put : strArr) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "vnd.android.cursor.item/website");
                contentValues.put("data1", put);
                arrayList.add(contentValues);
            }
        }
    }

    public String getActionId() {
        return "AddToContacts";
    }

    public Intent getIntent() {
        ArrayList arrayList;
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        ArrayList arrayList2 = new ArrayList();
        updateContactName(intent, arrayList2);
        updateContactNumber(arrayList2);
        updateContactOrigination(arrayList2);
        updateContactEmail(arrayList2);
        updateContactAddress(intent, arrayList2);
        updateContactUrl(arrayList2);
        updateContactNote(arrayList2);
        updateContactNickName(arrayList2);
        updateContactBirthday(arrayList2);
        updateContactEvent(arrayList2);
        if (!arrayList2.isEmpty()) {
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            intent.putExtra("data", arrayList2);
        }
        intent.addFlags(335544320);
        return intent;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_add_to_contact;
    }
}
