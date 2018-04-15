package com.sanbhat.aptmgr.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanbhat.aptmgr.entities.ApartmentRolesMetaEntity;

public interface ApartmentRolesRepository extends JpaRepository<ApartmentRolesMetaEntity, Integer> {

	@Query("select a from ApartmentRolesMetaEntity a where a.core = true")
	List<ApartmentRolesMetaEntity> findAllCoreRoles();
}
