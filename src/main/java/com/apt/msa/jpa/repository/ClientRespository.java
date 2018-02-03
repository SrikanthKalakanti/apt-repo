package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.Client;

@Repository
@Transactional
public interface ClientRespository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c where c.clientId=?1")
	List<Client> fetchByUserName(@Param("clientId") Long clientId);
	
	@Query("SELECT c FROM Client c WHERE c.customerId=?1")
	List<Client> fetchByCustomerId(@Param("customerId") Long customerId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Client c SET c.dateoffirstditributionoftermloan = :dateoffirstditributionoftermloan, c.doorNo = :door_no, c.email=:email, c.lineofactivity=:lineofactivity, c.landmark=:landmark, c.landphone=:landphone, c.locality=:locality, c.mobile=:mobile, c.name=:name, c.namePrefix=:name_prefix, c.pincode=:pincode, c.road=:road, c.state=:state, c.status=:status, c.town=:town WHERE c.clientId = :clientId")
	int updateClient(@Param("clientId") Long clientId, @Param("dateoffirstditributionoftermloan") String dateoffirstditributionoftermloan,
			@Param("door_no") String door_no, @Param("email") String email, @Param("lineofactivity") String lineofactivity,
			@Param("landmark") String landmark, @Param("landphone") String landphone,@Param("locality") String locality,
			@Param("mobile") Long mobile, @Param("name") String name,
			@Param("name_prefix") String name_prefix, @Param("pincode") Integer pincode, @Param("road") String road,
			@Param("state") String state, @Param("status") String status, @Param("town") String town);
	
}
