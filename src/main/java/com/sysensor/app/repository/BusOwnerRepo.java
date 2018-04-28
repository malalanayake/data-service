package com.sysensor.app.repository;

import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = APIConfig.BUS_OWNER, path = APIConfig.BUS_OWNER)
public interface BusOwnerRepo extends JpaRepository<BusOwner, String> {

}
