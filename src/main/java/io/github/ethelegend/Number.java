package io.github.ethelegend;
// github jank
public class Number {
    double x;
    double i;
    double j;
    double k;
    public Number(double a, double b, double c, double d) {
        this.x = a;
        this.i = b;
        this.j = c;
        this.k = d;
    }
    public Number(double a, double b) {
        this.x = a;
        this.i = b;
    }
    public Number(double a) {
        this.x = a;
    }
    public String toString() {
        String number = "";
        if (this.x != 0) {
            number += this.x;
        } if (this.i != 0) {
            number += ((this.i > 0) ? " + " : " - ") + Math.abs(this.i) + "i";
        } if (this.j != 0) {
            number += ((this.j > 0) ? " + " : " - ") + Math.abs(this.j) + "j";
        } if (this.k != 0) {
            number += ((this.k > 0) ? " + " : " - ") + Math.abs(this.k) + "k";
        }
        return (number.isEmpty()) ? "0" : number;
    }
    public double modulus(boolean squared) {
        double result = this.x*this.x + this.i*this.i + this.j*this.j + this.k*this.k;
        return (squared) ? result : Math.sqrt(result);
    }

    public Number conjugate() {
        return new Number(
                this.x,
                -this.i,
                -this.j,
                -this.k
        );
    }
    public Number add(Number number) {
        return new Number(
                this.x + number.x,
                this.i + number.i,
                this.j + number.j,
                this.k + number.k
        );
    }
    public Number subtract(Number number) {
        return new Number(
                this.x - number.x,
                this.i - number.i,
                this.j - number.j,
                this.k - number.k
        );
    }
    public Number multiply(Number number) {
        return new Number(
                this.x*number.x - this.i*number.i - this.j*number.j - this.k*number.k,
                this.x*number.i + this.i*number.x + this.j*number.k - this.k*number.j,
                this.x*number.j - this.i*number.k + this.j*number.x + this.k*number.i,
                this.x*number.k + this.i*number.j - this.j*number.i + this.k*number.x

        );
    }
    public Number divide(Number number) {
        Number result = this.multiply(number.conjugate());
        result.x /= number.modulus(true);
        result.i /= number.modulus(true);
        result.j /= number.modulus(true);
        result.k /= number.modulus(true);
        return result;
    }
}

