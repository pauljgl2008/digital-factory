package com.scotiabank.domain.aggregates;

public interface StatusMapper {

    default Status toStatusEnum(String status) {
        return Status.fromValue(status);
    }

}
