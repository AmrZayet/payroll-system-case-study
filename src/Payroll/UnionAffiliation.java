package Payroll;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation extends Affiliation {
    double dues;
    Map<Long, ServiceCharge> serviceCharges = new HashMap<Long, ServiceCharge>();

    public UnionAffiliation(double dues) {
        this.dues = dues;
    }

    public double getDues() {
        return dues;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        serviceCharges.put(serviceCharge.getDate(), serviceCharge);
    }

    public ServiceCharge getServiceCharge(long date) {
        return serviceCharges.get(date);
    }
}
