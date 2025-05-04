package com.cbo.account.management.user.domain.valueobject;

public enum RiskRating {
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH,
    ULTRA_HIGH;

    public static RiskRating fromString(String riskRating) {
        return RiskRating.valueOf(riskRating.toUpperCase());
    }
}
