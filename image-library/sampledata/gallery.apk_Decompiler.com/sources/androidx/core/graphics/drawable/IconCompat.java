package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    Object mObj1;
    public Parcelable mParcelable;
    public String mString1;
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static int getResId(Object obj) {
            return Api28Impl.getResId(obj);
        }

        public static String getResPackage(Object obj) {
            return Api28Impl.getResPackage(obj);
        }

        public static int getType(Object obj) {
            return Api28Impl.getType(obj);
        }

        public static Uri getUri(Object obj) {
            return Api28Impl.getUri(obj);
        }

        public static Icon toIcon(IconCompat iconCompat, Context context) {
            Icon icon;
            switch (iconCompat.mType) {
                case -1:
                    return (Icon) iconCompat.mObj1;
                case 1:
                    icon = Icon.createWithBitmap((Bitmap) iconCompat.mObj1);
                    break;
                case 2:
                    icon = Icon.createWithResource(iconCompat.getResPackage(), iconCompat.mInt1);
                    break;
                case 3:
                    icon = Icon.createWithData((byte[]) iconCompat.mObj1, iconCompat.mInt1, iconCompat.mInt2);
                    break;
                case 4:
                    icon = Icon.createWithContentUri((String) iconCompat.mObj1);
                    break;
                case 5:
                    icon = Api26Impl.createWithAdaptiveBitmap((Bitmap) iconCompat.mObj1);
                    break;
                case 6:
                    icon = Api30Impl.createWithAdaptiveBitmapContentUri(iconCompat.getUri());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type");
            }
            ColorStateList colorStateList = iconCompat.mTintList;
            if (colorStateList != null) {
                icon.setTintList(colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.mTintMode;
            if (mode != IconCompat.DEFAULT_TINT_MODE) {
                icon.setTintMode(mode);
            }
            return icon;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static Icon createWithAdaptiveBitmap(Bitmap bitmap) {
            return Icon.createWithAdaptiveBitmap(bitmap);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static int getResId(Object obj) {
            return ((Icon) obj).getResId();
        }

        public static String getResPackage(Object obj) {
            return ((Icon) obj).getResPackage();
        }

        public static int getType(Object obj) {
            return ((Icon) obj).getType();
        }

        public static Uri getUri(Object obj) {
            return ((Icon) obj).getUri();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static Icon createWithAdaptiveBitmapContentUri(Uri uri) {
            return Icon.createWithAdaptiveBitmapContentUri(uri);
        }
    }

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        ObjectsCompat.requireNonNull(bitmap);
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.mObj1 = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithResource(Resources resources, String str, int i2) {
        ObjectsCompat.requireNonNull(str);
        if (i2 != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.mInt1 = i2;
            if (resources != null) {
                try {
                    iconCompat.mObj1 = resources.getResourceName(i2);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.mObj1 = str;
            }
            iconCompat.mString1 = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    private static String typeToString(int i2) {
        switch (i2) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public int getResId() {
        int i2 = this.mType;
        if (i2 == -1) {
            return Api23Impl.getResId(this.mObj1);
        }
        if (i2 == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String getResPackage() {
        int i2 = this.mType;
        if (i2 == -1) {
            return Api23Impl.getResPackage(this.mObj1);
        }
        if (i2 == 2) {
            String str = this.mString1;
            if (str == null || TextUtils.isEmpty(str)) {
                return ((String) this.mObj1).split(NumericEnum.SEP, -1)[0];
            }
            return this.mString1;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int getType() {
        int i2 = this.mType;
        if (i2 == -1) {
            return Api23Impl.getType(this.mObj1);
        }
        return i2;
    }

    public Uri getUri() {
        int i2 = this.mType;
        if (i2 == -1) {
            return Api23Impl.getUri(this.mObj1);
        }
        if (i2 == 4 || i2 == 6) {
            return Uri.parse((String) this.mObj1);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    @Deprecated
    public Icon toIcon() {
        return toIcon((Context) null);
    }

    public String toString() {
        if (this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder sb2 = new StringBuilder("Icon(typ=");
        sb2.append(typeToString(this.mType));
        switch (this.mType) {
            case 1:
            case 5:
                sb2.append(" size=");
                sb2.append(((Bitmap) this.mObj1).getWidth());
                sb2.append("x");
                sb2.append(((Bitmap) this.mObj1).getHeight());
                break;
            case 2:
                sb2.append(" pkg=");
                sb2.append(this.mString1);
                sb2.append(" id=");
                sb2.append(String.format("0x%08x", new Object[]{Integer.valueOf(getResId())}));
                break;
            case 3:
                sb2.append(" len=");
                sb2.append(this.mInt1);
                if (this.mInt2 != 0) {
                    sb2.append(" off=");
                    sb2.append(this.mInt2);
                    break;
                }
                break;
            case 4:
            case 6:
                sb2.append(" uri=");
                sb2.append(this.mObj1);
                break;
        }
        if (this.mTintList != null) {
            sb2.append(" tint=");
            sb2.append(this.mTintList);
        }
        if (this.mTintMode != DEFAULT_TINT_MODE) {
            sb2.append(" mode=");
            sb2.append(this.mTintMode);
        }
        sb2.append(")");
        return sb2.toString();
    }

    public Icon toIcon(Context context) {
        return Api23Impl.toIcon(this, context);
    }

    public IconCompat(int i2) {
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = i2;
    }
}
