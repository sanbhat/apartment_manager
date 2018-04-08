package com.sanbhat.aptmgr.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanbhat.aptmgr.entities.ApartmentRolesMetaEntity;

public interface ApartmentRolesRepository extends JpaRepository<ApartmentRolesMetaEntity, Integer> {

}
