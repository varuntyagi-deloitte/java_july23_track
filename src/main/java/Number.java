public class Number {
    private double num;

    public Number(double num) {
        this.num = num;
    }

    public boolean isZero() {
        return this.num == 0;
    }

    public boolean isPositive() {
        return this.num >= 0;
    }

    public boolean isNegative() {
        return this.num < 0;
    }

    public boolean isOdd() {
        return this.num % 2 != 0;
    }

    public boolean isEven() {
        return this.num % 2 == 0;
    }

    public boolean isPrime() {
        int flag = 1;
        for (int i = 2; i < this.num; i++) {
            if (this.num % i == 0) {
                flag = 0;
                break;
            }
        }
        return flag == 1;
    }

    public boolean isArmstrong() {
        double rem = 0, new_num = 0, flag = this.num;
        while (this.num != 0) {
            rem = this.num % 10;
            new_num = (new_num + (rem * rem * rem));
            this.num = this.num / 10;
        }
        return flag == new_num;
    }

    public double getFactorial() {
        double fact = 1;
        while (this.num != 0) {
            fact = fact * this.num;
            this.num -= 1;
        }
        return fact;
    }

    public double getSqrt() {
        return Math.sqrt(this.num);
    }

    public double getSqr() {
        return this.num * this.num;
    }

    public double sumDigits() {
        double sum = 0;
        while (this.num > 0) {
            sum = sum + this.num % 10;
            this.num = this.num / 10;
        }
        return sum;
    }

    public double getReverse() {
        double new_num = 0, rem;
        while (this.num > 0) {
            rem = this.num % 10;
            new_num = (new_num * 10) + rem;
            this.num = this.num / 10;
        }
        return new_num;
    }

    public void listFactor() {
        System.out.println("The factors of " + this.num + " are: ");
        if (this.num == 1) {
            System.out.print("1");
        } else if (isPrime()) {
            System.out.print("1," + this.num);
        } else {
            for (int i = 2; i < this.num; i++) {
                if (this.num % i == 0) {
                    System.out.print(i + ",");
                }
            }
        }
    }

    public void dispBinary() {
        System.out.println("Binary representation of " + this.num + " is " + Double.doubleToLongBits(this.num));
    }

    public static void main(String[] args) {
        double num = 11.00;
        Number obj = new Number(num);
        obj.dispBinary();
        System.out.println();
        Number obj1 = new Number(num);
        obj1.listFactor();
        System.out.println();
        Number obj2 = new Number(num);
        System.out.println(num + "'s reverse is " + obj2.getReverse());
        Number obj3 = new Number(num);
        System.out.println(num + "'s square is " + obj3.getSqr());
        Number obj4 = new Number(num);
        System.out.println(num + "'s square root is " + obj4.getSqrt());
        Number obj5 = new Number(num);
        System.out.println(num + "'s factorial is " + obj5.getFactorial());
        Number obj6 = new Number(num);
        if (obj6.isArmstrong()) {
            System.out.println(num + " is a armstrong number");
        } else {
            System.out.println(num + " is not a armstrong number");
        }
        Number obj7 = new Number(num);
        if(obj7.isPositive()){
            System.out.println(num + " is a positive number");
        }
        else{
            System.out.println(num + " is not a positive number");
        }
        Number obj9 = new Number(num);
        System.out.println(num + "'s digits sum is "+obj9.sumDigits());
        if(obj7.isNegative()){
            System.out.println(num + " is a negative number");
        }
        else{
            System.out.println(num + " is not a negative number");
        }
        Number obj8 = new Number(num);
        if(obj8.isPrime()){
            System.out.println(num + " is a prime number");
        }
        else{
            System.out.println(num + " is not a prime number");
        }
        if(obj7.isEven()){
            System.out.println(num + " is a even number");
        }
        else{
            System.out.println(num + " is not a even number");
        }
        if(obj7.isOdd()){
            System.out.println(num + " is a odd number");
        }
        else{
            System.out.println(num + " is not a odd number");
        }
        if(obj7.isZero()){
            System.out.println(num + " is a zero number");
        }
        else{
            System.out.println(num + " is not a zero number");
        }
    }
}
