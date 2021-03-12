package com.app.repository.covid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>  {

}
