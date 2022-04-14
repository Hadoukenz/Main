enum Roman2Arabic {
    I(1, "I"), II(2, "II"), III(3, "III"), IV(4, "IV"), V(5, "V"),
    VI(6, "VI"), VII(7, "VII"), VIII(8, "VIII"), IX(9, "IX"),
    X(10, "X");

    int convd;
    String str;

    Roman2Arabic(int convd, String str) {
        this.convd = convd;
        this.str = str;
    }

    int getArabic() {
        return convd;
    }

    String getString() {
        return str;
    }
}