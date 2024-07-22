package br.com.devtools.service.template.gateway.database.template.repository;

import br.com.devtools.service.template.gateway.database.BaseRepository;
import br.com.devtools.service.template.gateway.database.template.model.TemplateEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends BaseRepository<TemplateEntity, UUID> {}
