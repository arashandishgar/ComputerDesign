package floatingpoint;

class Test {
    public static void main(String[] args) {
        double test = .5;
        long doubleBits = Double.doubleToLongBits(test);
        String doubleBitsStr = Long.toBinaryString(doubleBits);
        System.out.println(doubleBitsStr);
    }
}
