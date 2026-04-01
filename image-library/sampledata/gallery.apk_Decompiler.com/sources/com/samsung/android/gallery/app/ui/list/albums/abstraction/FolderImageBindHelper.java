package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderImageBindHelper {
    public static boolean[] getCornerEnabled(int i2, int i7) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean z22;
        boolean z23;
        boolean z24;
        boolean z25;
        boolean z26;
        boolean z27;
        boolean z28;
        boolean z29;
        boolean isEnabled = Features.isEnabled(Features.IS_RTL);
        if (i7 != 2) {
            if (i7 != 3) {
                if (i7 != 4) {
                    return new boolean[]{true, true, true, true};
                }
                if (isEnabled) {
                    if (i2 == 1) {
                        z26 = true;
                    } else {
                        z26 = false;
                    }
                    if (i2 == 0) {
                        z27 = true;
                    } else {
                        z27 = false;
                    }
                    if (i2 == 3) {
                        z28 = true;
                    } else {
                        z28 = false;
                    }
                    if (i2 == 2) {
                        z29 = true;
                    } else {
                        z29 = false;
                    }
                    return new boolean[]{z26, z27, z28, z29};
                }
                if (i2 == 0) {
                    z22 = true;
                } else {
                    z22 = false;
                }
                if (i2 == 1) {
                    z23 = true;
                } else {
                    z23 = false;
                }
                if (i2 == 2) {
                    z24 = true;
                } else {
                    z24 = false;
                }
                if (i2 == 3) {
                    z25 = true;
                } else {
                    z25 = false;
                }
                return new boolean[]{z22, z23, z24, z25};
            } else if (isEnabled) {
                if (i2 == 1) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                if (i2 == 0) {
                    z19 = true;
                } else {
                    z19 = false;
                }
                if (i2 == 1) {
                    z20 = true;
                } else {
                    z20 = false;
                }
                if (i2 == 2) {
                    z21 = true;
                } else {
                    z21 = false;
                }
                return new boolean[]{z18, z19, z20, z21};
            } else {
                if (i2 == 0) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                if (i2 == 1) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                if (i2 == 2) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                if (i2 == 1) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                return new boolean[]{z14, z15, z16, z17};
            }
        } else if (isEnabled) {
            if (i2 == 1) {
                z10 = true;
            } else {
                z10 = false;
            }
            if (i2 == 0) {
                z11 = true;
            } else {
                z11 = false;
            }
            if (i2 == 1) {
                z12 = true;
            } else {
                z12 = false;
            }
            if (i2 == 0) {
                z13 = true;
            } else {
                z13 = false;
            }
            return new boolean[]{z10, z11, z12, z13};
        } else {
            if (i2 == 0) {
                z = true;
            } else {
                z = false;
            }
            if (i2 == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (i2 == 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (i2 == 1) {
                z9 = true;
            } else {
                z9 = false;
            }
            return new boolean[]{z, z3, z7, z9};
        }
    }

    private void setViewMargin(ImageView imageView, int i2, boolean[] zArr) {
        int i7;
        int i8;
        int i10;
        if (zArr[0]) {
            i7 = i2;
        } else {
            i7 = 0;
        }
        Integer valueOf = Integer.valueOf(i7);
        if (zArr[1]) {
            i8 = i2;
        } else {
            i8 = 0;
        }
        Integer valueOf2 = Integer.valueOf(i8);
        if (zArr[2]) {
            i10 = i2;
        } else {
            i10 = 0;
        }
        Integer valueOf3 = Integer.valueOf(i10);
        if (!zArr[3]) {
            i2 = 0;
        }
        ViewMarginUtils.setMarginRelative(imageView, valueOf, valueOf2, valueOf3, Integer.valueOf(i2));
    }

    private void setViewShape(ImageView imageView, final float f, final boolean[] zArr) {
        if (imageView != null) {
            imageView.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    float f;
                    float f5;
                    float f8;
                    if (view.getWidth() > 0) {
                        boolean[] zArr = zArr;
                        boolean z = zArr[0];
                        float f10 = 0.0f;
                        if (z || zArr[2]) {
                            f = 0.0f;
                        } else {
                            f = f;
                        }
                        int i2 = -((int) f);
                        if (z || zArr[1]) {
                            f5 = 0.0f;
                        } else {
                            f5 = f;
                        }
                        int i7 = -((int) f5);
                        int width = view.getWidth();
                        boolean[] zArr2 = zArr;
                        if (zArr2[1] || zArr2[3]) {
                            f8 = 0.0f;
                        } else {
                            f8 = f;
                        }
                        int i8 = width + ((int) f8);
                        int height = view.getHeight();
                        boolean[] zArr3 = zArr;
                        if (!zArr3[2] && !zArr3[3]) {
                            f10 = f;
                        }
                        outline.setRoundRect(i2, i7, i8, height + ((int) f10), f);
                    }
                }
            });
            imageView.setClipToOutline(true);
        }
    }

    public void updateMargin(ImageView imageView, int i2, int i7, int i8) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        if (i7 == 2) {
            if (i2 == 1) {
                z = true;
            } else {
                z = false;
            }
            if (i2 == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            setViewMargin(imageView, i8, new boolean[]{z, false, z3, false});
        } else if (i7 == 3) {
            if (i2 == 1) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (i2 == 2) {
                z9 = true;
            } else {
                z9 = false;
            }
            if (i2 != 1) {
                z10 = true;
            } else {
                z10 = false;
            }
            if (i2 == 0) {
                z11 = true;
            } else {
                z11 = false;
            }
            setViewMargin(imageView, i8, new boolean[]{z7, z9, z10, z11});
        } else if (i7 != 4) {
            ViewMarginUtils.setMargin(imageView, 0);
        } else {
            int i10 = i2 % 2;
            if (i10 == 1) {
                z12 = true;
            } else {
                z12 = false;
            }
            if (i2 > 1) {
                z13 = true;
            } else {
                z13 = false;
            }
            if (i10 == 0) {
                z14 = true;
            } else {
                z14 = false;
            }
            if (i2 < 2) {
                z15 = true;
            } else {
                z15 = false;
            }
            setViewMargin(imageView, i8, new boolean[]{z12, z13, z14, z15});
        }
    }

    public void updateRoundedCorner(ImageView imageView, int i2, int i7, float f) {
        setViewShape(imageView, f, getCornerEnabled(i2, i7));
    }
}
