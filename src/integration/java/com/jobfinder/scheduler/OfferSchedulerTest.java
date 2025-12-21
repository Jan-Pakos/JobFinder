package com.jobfinder.scheduler;

import com.jobfinder.BaseIntegrationTest;
import com.jobfinder.JobFinderApplication;
import com.jobfinder.domain.offer.OfferFetchable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;

import static org.mockito.Mockito.verify;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = JobFinderApplication.class, properties = "scheduling.enabled=true")
public class OfferSchedulerTest extends BaseIntegrationTest {

    @MockitoSpyBean
    OfferFetchable remoteOfferClient;

    @Test
    public void should_run_run_offer_scheduler_exactly_given_times_within_given_time() {
        await().atMost(Duration.ofSeconds(2)).untilAsserted(() -> verify(remoteOfferClient, times(2)).getNewOffers());
    }
}
