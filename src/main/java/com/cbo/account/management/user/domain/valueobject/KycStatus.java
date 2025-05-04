package com.cbo.account.management.user.domain.valueobject;

public enum KycStatus { // Know Your Customer Status
    PENDING,
    APPROVED,
    REJECTED,
    EXPIRED,
    UNDER_REVIEW,
    DOCUMENTS_REQUIRED,
    DOCUMENTS_SUBMITTED,
    DOCUMENTS_VERIFIED,
    DOCUMENTS_REJECTED
}
