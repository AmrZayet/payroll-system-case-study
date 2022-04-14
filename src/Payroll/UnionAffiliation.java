package Payroll;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation extends Affiliation {
    int memberId;
    double dues;
    Map<Long, ServiceCharge> serviceCharges = new HashMap<Long, ServiceCharge>();

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        serviceCharges.put(serviceCharge.getDate(), serviceCharge);
    }

    public ServiceCharge getServiceCharge(long date) {
        return serviceCharges.get(date);
    }
}
