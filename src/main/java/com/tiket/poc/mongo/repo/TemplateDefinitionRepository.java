package com.tiket.poc.mongo.repo;

import com.tiket.poc.mongo.entity.TemplateDefinition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author zakyalvan
 */
public interface TemplateDefinitionRepository extends ReactiveMongoRepository<TemplateDefinition, String> {

}
