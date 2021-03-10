package com.app.repository.covid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CovidAreaDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidAreaDescEntity, Long>  {

}
