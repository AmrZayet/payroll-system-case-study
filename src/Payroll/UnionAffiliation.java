package Payroll;

public class UnionAffiliation extends Affiliation {
    double dues;

    public UnionAffiliation(double dues) {
        this.dues = dues;
    }

    public double getDues() {
        return dues;
    }
}
