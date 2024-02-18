package com.stepaniuk.zrobleno.service.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long>,
    JpaSpecificationExecutor<ServiceCategory> {

}