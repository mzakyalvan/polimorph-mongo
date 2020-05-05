package com.tiket.poc.mongo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zakyalvan
 */
@Getter
@Document(collection = "template-definition")
@SuppressWarnings("serial")
public class TemplateDefinition implements Serializable {
  @Id
  private String id;

  private final ScheduleFilter scheduleFilter;

  @Version
  private Integer recordVersion;

  @CreatedDate
  private LocalDateTime createdTime;

  @LastModifiedDate
  private LocalDateTime modifiedTime;

  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder", toBuilder = true)
  TemplateDefinition(String id, ScheduleFilter scheduleFilter, Integer recordVersion,
      LocalDateTime createdTime, LocalDateTime modifiedTime) {
    this.id = id;
    this.scheduleFilter = scheduleFilter;
    this.recordVersion = recordVersion;
    this.createdTime = createdTime;
    this.modifiedTime = modifiedTime;
  }
}
