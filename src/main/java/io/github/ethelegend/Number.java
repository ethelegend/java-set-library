package io.github.ethelegend;
public class Number {
    // Real constants
    final double a;
    final double b;
    final double c;
    final double d;
    final double modulus;
    final double squaredModulus;
    final double argument;

    // (probably) Non-real constants
    public Number vector() {
        return new Number(0,this.b,this.c,this.d);
    }
    public Number unitVector() {
        Number result = this.vector();
        return result.divide(result.modulus);
    }
    public Number conjugate() {
        return new Number(
                this.a,
                -this.b,
                -this.c,
                -this.d
        );
    }
    public Number reciprocal() {
        return this.conjugate().divide(this.squaredModulus);
    }

    // Constructors
    public Number(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.squaredModulus = a*a + b*b + c*c + d*d;
        this.modulus = Math.sqrt(this.squaredModulus);
        this.argument = Math.acos(a/this.modulus);
    }
    public Number(double a, double b) {
        this(a,b,0,0);
    }
    public Number(double a) {
        this(a,0,0,0);
    }

    // Overriden functions
    public String toString() {
        String number = "";
        if (this.a != 0) {
            number += this.a;
        } if (this.b != 0) {
            if (!number.isEmpty()) {
                number += ((this.b > 0) ? " + " : " - ");
            }
            number += Math.abs(this.b) + "i";
        } if (this.c != 0) {
            if (!number.isEmpty()) {
                number += ((this.c > 0) ? " + " : " - ");
            }
            number += Math.abs(this.c) + "j";
        } if (this.d != 0) {
            if (!number.isEmpty()) {
                number += ((this.d > 0) ? " + " : " - ");
            }
            number += Math.abs(this.d) + "k";
        }
        return (number.isEmpty()) ? "0" : number;
    }

    // Basic operations
    public Number add(double addend) {
        return new Number(
                this.a + addend,
                this.b,
                this.c,
                this.d
        );
    }
    public Number add(Number addend) {
        return new Number(
                this.a + addend.a,
                this.b + addend.b,
                this.c + addend.c,
                this.d + addend.d
        );
    }
    public Number subtract(double subtrahend) {
        return new Number(
                this.a - subtrahend,
                this.b,
                this.c,
                this.d
        );
    }
    public Number subtract(Number subtrahend) {
        return new Number(
                this.a - subtrahend.a,
                this.b - subtrahend.b,
                this.c - subtrahend.c,
                this.d - subtrahend.d
        );
    }
    public Number multiply(double factor) {
        return new Number(
                this.a * factor,
                this.b * factor,
                this.c * factor,
                this.d * factor
        );
    }
    public Number multiply(Number factor) {
        return new Number(
                this.a * factor.a - this.b * factor.b - this.c * factor.c - this.d * factor.d,
                this.a * factor.b + this.b * factor.a + this.c * factor.d - this.d * factor.c,
                this.a * factor.c - this.b * factor.d + this.c * factor.a + this.d * factor.b,
                this.a * factor.d + this.b * factor.c - this.c * factor.b + this.d * factor.a

        );
    }
    public Number divide(double divisor) {
        return new Number(
                this.a / divisor,
                this.b / divisor,
                this.c / divisor,
                this.d / divisor
        );
    }
    public Number divide(Number divisor) {
        return this.multiply(divisor.conjugate()).divide(divisor.squaredModulus);
    }

    // Exponential operations
    public Number exp() {
        double vectorModulus = this.vector().modulus;
        return this.unitVector().multiply(Math.sin(vectorModulus)).add(Math.cos(vectorModulus)).multiply(Math.exp(this.a));
    }
    public Number exp(double base) {
        return this.multiply(Math.log(base)).exp();
    }
    public Number exp(Number base) {
        return base.log().multiply(this).exp();
    }
    public Number log() {
        return this.unitVector().multiply(this.argument).add(Math.log(this.modulus));
    }
    public Number log(double base) {
        return this.log().divide(Math.log(base));
    }
    public Number log(Number base) {
        return this.log().divide(base.log());
    }
    public Number pow(double index) {
        double angle = index*this.argument;
        return this.unitVector().multiply(Math.sin(angle)).add(Math.cos(angle)).multiply(Math.pow(this.modulus,index));
    }
    public Number pow(Number index) {
        return index.exp(this);
    }
    public Number root(double index) {
        return this.pow(1/index);
    }
    public Number root(Number index) {
        return this.pow(index.reciprocal());
    }
}

