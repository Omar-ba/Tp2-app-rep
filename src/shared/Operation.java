package shared;

import java.io.Serializable;

public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    private double op1;
    private double op2;
    private char operateur;

    public Operation(double op1, char operateur, double op2) {
        this.op1 = op1;
        this.op2 = op2;
        this.operateur = operateur;
    }

    public double getOp1() { return op1; }
    public double getOp2() { return op2; }
    public char getOperateur() { return operateur; }
}
