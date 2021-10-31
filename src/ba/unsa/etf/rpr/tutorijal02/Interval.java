package ba.unsa.etf.rpr.tutorijal02;

import static java.lang.Double.compare;

public class Interval {
    private double pt;
    private double kt;
    private boolean ptp;
    private boolean ktp;

    public Interval(double x, double y, boolean b1, boolean b2) {
        if (compare(x, y) > 0) throw new IllegalArgumentException("Prva tačka je veća od druge!");
        pt = x;
        kt = y;
        ptp = b1;
        ktp = b2;
    }

    public Interval() {
        pt = 0;
        kt = 0;
        ptp = false;
        ktp = false;
    }

    public boolean isNull() {
        return compare(0, pt) == 0 && compare(0, kt) == 0;
    }

    public boolean isIn(double tacka) {
        if (ptp && !ktp) return compare(pt, tacka) <= 0 && compare(tacka, kt) < 0;
        else if (ktp && !ptp) return compare(pt, tacka) < 0 && compare(tacka, kt) <= 0;
        else if (ptp && ktp) return compare(pt, tacka) <= 0 && compare(tacka, kt) <= 0;
        return compare(pt, tacka) < 0 && compare(tacka, kt) < 0;
    }

    public Interval intersect(Interval in) {
        Interval presjek = new Interval();
        if (compare(pt, in.pt) >= 0 && compare(in.kt, pt) >= 0) {
            presjek.pt = pt;
            if (ptp) presjek.ptp = true;
        } else if (compare(in.pt, pt) >= 0 && compare(kt, in.pt) >= 0) {
            presjek.pt = in.pt;
            if (in.ptp) presjek.ptp = true;
        }
        if (compare(kt, in.kt) <= 0 && compare(in.pt, kt) <= 0) {
            presjek.kt = kt;
            if (ktp) presjek.ktp = true;
        } else if (compare(in.kt, kt) <= 0 && compare(pt, in.kt) <= 0) {
            presjek.kt = in.kt;
            if (in.ktp) presjek.ktp = true;
        }
        return presjek;
    }

    public static Interval intersect(Interval in, Interval in1) {
        Interval presjek = new Interval();
        if (compare(in1.pt, in.pt) >= 0 && compare(in.kt, in1.pt) >= 0) {
            presjek.pt = in1.pt;
            if (in1.ptp) presjek.ptp = true;
        } else if (compare(in.pt, in1.pt) >= 0 && compare(in1.kt, in.pt) >= 0) {
            presjek.pt = in.pt;
            if (in.ptp) presjek.ptp = true;
        }
        if (compare(in1.kt, in.kt) <= 0 && compare(in.pt, in1.kt) <= 0) {
            presjek.kt = in1.kt;
            if (in1.ktp) presjek.ktp = true;
        } else if (compare(in.kt, in1.kt) <= 0 && compare(in1.pt, in.kt) <= 0) {
            presjek.kt = in.kt;
            if (in.ktp) presjek.ktp = true;
        }
        return presjek;
    }

    @Override
    public String toString() {
        String str = "";
        if(ptp) str+="["; else str+="(";
        if(!isNull()) str = str+pt+","+kt;
        if(ktp) str+="]"; else str+=")";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        Interval in = (Interval) o;
        return compare(in.pt,pt)==0 && compare(in.kt,kt)==0 && in.ptp==ptp && in.ktp==ktp;
    }
}
