package com.jobfinder.domain.offer;

import lombok.Builder;

@Builder
record Offer(
        Long id,
        String title,
        String description,
        String companyName,
        String location,
        String salaryRange,
        String url
) {
}
