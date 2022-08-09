 class NumberFormat {

     static double getNumberFormat(double i) {
        double scale = Math.pow(10, 2);
        return Math.ceil(i * scale) / scale;
    }
}
