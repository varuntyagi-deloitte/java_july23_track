public class Fraction {
    private double denominator = 4;
    private double numerator = 5;
    private double result;

    public Fraction(double denominator, double numerator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.result = this.numerator / this.denominator;
        System.out.println("The result of " + this.numerator + "/" + this.denominator + " is: " + this.result);
    }

    public Fraction(double denominator) {
        this.denominator = denominator;
        this.result = this.numerator / this.denominator;
        System.out.println("The result of " + this.numerator + "/" + this.denominator + " is: " + this.result);
    }

    public Fraction() {
        this.result = this.numerator / this.denominator;
        System.out.println("The result of " + this.numerator + "/" + this.denominator + " is: " + this.result);
    }

    public static void main(String g[]) {
        Fraction f = new Fraction();
        Fraction f1=new Fraction(5);
        Fraction f2 = new Fraction(5,6);
    }
}
