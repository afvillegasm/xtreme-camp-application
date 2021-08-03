package com.upgradeinc.extremecamp.campsite.dao;

import java.util.List;

import com.upgradeinc.extremecamp.campsite.entity.CampSite;

public interface CampSiteDao {
	
	public void create(CampSite entity) throws Exception;
	public void update(CampSite entity) throws Exception;
	public void delete(CampSite entity) throws Exception;
	public List<CampSite> findAll() throws Exception;
	public CampSite findById(Long id) throws Exception;
	public List<CampSite> findByName(String name) throws Exception;

}
