public class OrbitEquationCalculator {

    public double aphelion;
    public double perihelion;
    public double eccentriciy;
    public double orbitedRadius;
    public double theA;
    public double theB;
    public double theOffset;
    public String theEquation;

    public OrbitEquationCalculator(double aph, double per, double ecc,
            double oRad) {
        this.aphelion = aph;
        this.perihelion = per;
        this.eccentriciy = ecc;
        this.orbitedRadius = oRad;
        this.theA = (aphelion + perihelion + (2 * orbitedRadius)) / 2;
        this.theB = findB();
        this.theOffset = findOffset();
        this.theEquation = equationBuilder();
    }

    public double getTheA() {
        return theA;
    }

    public double getTheB() {
        return theB;
    }

    public double getTheOffset() {
        return theOffset;
    }

    public double findB() {
        double b;
        double a = theA;
        b = eccentriciy * a;
        b *= b;
        b -= (a * a);
        b *= -1;
        b = Math.sqrt(b);
        return b;
    }

    public double findOffset() {
        double offset;
        double as = aphelion + orbitedRadius;
        double ac = theA;
        offset = ac - as;
        return offset;
    }

    public String equationBuilder() {
        String equation;
        double scale = ((double)(10 ^ -6)) / 50.0;
        double a = theA * scale;
        double b = theB * scale;
        double o = theOffset * scale;
        equation = "((x^2)/(" + a + "^2)) + ";
        equation += "(((y+" + (o * -1) + ")^2)/("
            + b + "^2)) = 1";
        return equation;
    }

    @Override
    public String toString() {
        return theEquation;
    }

}
