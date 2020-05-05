package com.tiket.poc.mongo.repo;

import com.tiket.poc.mongo.SampleApplication;
import com.tiket.poc.mongo.entity.EndpointType;
import com.tiket.poc.mongo.entity.FilterType;
import com.tiket.poc.mongo.entity.OriginScheduleFilter;
import com.tiket.poc.mongo.entity.RouteEndpoint;
import com.tiket.poc.mongo.entity.TemplateDefinition;
import com.tiket.poc.mongo.entity.TrainRoute;
import com.tiket.poc.mongo.entity.TrainScheduleFilter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

/**
 * @author zakyalvan
 */
@SpringBootTest(classes = SampleApplication.class,
    webEnvironment = WebEnvironment.NONE)
class TemplateDefinitionRepositoryTests {
  @Autowired
  private TemplateDefinitionRepository templateRepository;

  @Test
  void whenPersistTemplateWithTrainNameFilter_thenShouldConsistent() {
    TemplateDefinition template = TemplateDefinition.builder()
        .scheduleFilter(TrainScheduleFilter.builder()
            .value("Argo Parahyangan")
            .routes(Arrays.asList(
                TrainRoute.builder()
                    .originCode("GMR").originType(EndpointType.STATION)
                    .destinationCode("BD").destinationType(EndpointType.STATION)
                    .build(),
                TrainRoute.builder()
                    .originCode("BD").originType(EndpointType.STATION)
                    .destinationCode("GMR").destinationType(EndpointType.STATION)
                    .build()
            ))
            .build())
        .build();

    Mono<TemplateDefinition> testStream = templateRepository.save(template)
        .flatMap(persisted -> templateRepository.findById(persisted.getId()));

    StepVerifier.create(testStream)
        .expectSubscription().thenAwait()
        .assertNext(persisted -> {
          assertThat(persisted.getScheduleFilter()).isInstanceOf(TrainScheduleFilter.class);
          assertThat(persisted.getScheduleFilter().getType()).isEqualTo(FilterType.TRAIN);
          assertThat(persisted.getScheduleFilter().getValue()).isEqualTo("Argo Parahyangan");
          assertThat(((TrainScheduleFilter) persisted.getScheduleFilter()).getRoutes()).hasSize(2);
        })
        .expectComplete()
        .verify(Duration.ofSeconds(15));
  }

  @Test
  void whenPersistTemplateWithOriginStationFilter_thenShouldConsistent() {
    TemplateDefinition template = TemplateDefinition.builder()
        .scheduleFilter(OriginScheduleFilter.builder()
            .value(RouteEndpoint.builder()
                .code("GMR").type(EndpointType.STATION)
                .build())
            .destinations(Collections.singletonList(
                RouteEndpoint.builder()
                    .code("BD").type(EndpointType.STATION)
                    .build()
            ))
            .build())
        .build();

    Mono<TemplateDefinition> testStream = templateRepository.save(template)
        .flatMap(persisted -> templateRepository.findById(persisted.getId()));

    StepVerifier.create(testStream)
        .expectSubscription().thenAwait()
        .assertNext(persisted -> {
          assertThat(persisted.getScheduleFilter()).isInstanceOf(OriginScheduleFilter.class);
          assertThat(persisted.getScheduleFilter().getType()).isEqualTo(FilterType.ORIGIN);
          assertThat(persisted.getScheduleFilter().getValue()).isEqualToComparingFieldByField(template.getScheduleFilter().getValue());
          assertThat(((OriginScheduleFilter) persisted.getScheduleFilter()).getDestinations()).hasSize(1);
        })
        .expectComplete()
        .verify(Duration.ofSeconds(15));
  }
}