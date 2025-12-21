package com.jobfinder.domain.offer;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record Offer(
        String id,
        String title,
        String companyName,
        String salaryRange,
        String url
) {
}
