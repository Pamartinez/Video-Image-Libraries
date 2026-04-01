package com.samsung.android.gallery.compat.qrencoder.common.reedsolomon;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    public GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length != 0) {
            this.field = genericGF;
            int length = iArr.length;
            int i2 = 1;
            if (length <= 1 || iArr[0] != 0) {
                this.coefficients = iArr;
                return;
            }
            while (i2 < length && iArr[i2] == 0) {
                i2++;
            }
            if (i2 == length) {
                this.coefficients = new int[]{0};
                return;
            }
            int[] iArr2 = new int[(length - i2)];
            this.coefficients = iArr2;
            System.arraycopy(iArr, i2, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    public GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (isZero()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = genericGFPoly.coefficients;
            if (iArr.length <= iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr4, 0, length);
            for (int i2 = length; i2 < iArr.length; i2++) {
                iArr4[i2] = GenericGF.addOrSubtract(iArr2[i2 - length], iArr[i2]);
            }
            return new GenericGFPoly(this.field, iArr4);
        }
    }

    public GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!genericGFPoly.isZero()) {
            GenericGFPoly zero = this.field.getZero();
            int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
            GenericGFPoly genericGFPoly2 = this;
            while (genericGFPoly2.getDegree() >= genericGFPoly.getDegree() && !genericGFPoly2.isZero()) {
                int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
                int multiply = this.field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
                GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
                zero = zero.addOrSubtract(this.field.buildMonomial(degree, multiply));
                genericGFPoly2 = genericGFPoly2.addOrSubtract(multiplyByMonomial);
            }
            return new GenericGFPoly[]{zero, genericGFPoly2};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public int getCoefficient(int i2) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - i2];
    }

    public int[] getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.coefficients.length - 1;
    }

    public boolean isZero() {
        if (this.coefficients[0] == 0) {
            return true;
        }
        return false;
    }

    public GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (isZero() || genericGFPoly.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = genericGFPoly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i2 = 0; i2 < length; i2++) {
                int i7 = iArr[i2];
                for (int i8 = 0; i8 < length2; i8++) {
                    int i10 = i2 + i8;
                    iArr3[i10] = GenericGF.addOrSubtract(iArr3[i10], this.field.multiply(i7, iArr2[i8]));
                }
            }
            return new GenericGFPoly(this.field, iArr3);
        }
    }

    public GenericGFPoly multiplyByMonomial(int i2, int i7) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (i7 == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            int[] iArr = new int[(i2 + length)];
            for (int i8 = 0; i8 < length; i8++) {
                iArr[i8] = this.field.multiply(this.coefficients[i8], i7);
            }
            return new GenericGFPoly(this.field, iArr);
        }
    }

    public String toString() {
        if (isZero()) {
            return "0";
        }
        StringBuilder sb2 = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    if (degree == getDegree()) {
                        sb2.append("-");
                    } else {
                        sb2.append(" - ");
                    }
                    coefficient = -coefficient;
                } else if (sb2.length() > 0) {
                    sb2.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int log = this.field.log(coefficient);
                    if (log == 0) {
                        sb2.append('1');
                    } else if (log == 1) {
                        sb2.append('a');
                    } else {
                        sb2.append("a^");
                        sb2.append(log);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        sb2.append('x');
                    } else {
                        sb2.append("x^");
                        sb2.append(degree);
                    }
                }
            }
        }
        return sb2.toString();
    }
}
