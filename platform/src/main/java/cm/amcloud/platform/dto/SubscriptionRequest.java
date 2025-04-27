package cm.amcloud.platform.dto;

public class SubscriptionRequest {
    
    private String agencyName;
    private String planType;

    // Getters and Setters
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}
