package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Organization;
import java.util.List;

public interface OrganizationDao {
    
    /**
     * Adds organization to Database
     * @param organization {Organization object}
     * @return {Organization object}
     */
    Organization createOrganization(Organization organization);
    
    /**
     * Get single organization by its id from database
     * @param id{integer organaztionId}
     * @return {Organization object}
     */
    Organization readOrganizationById(int id);
    
    /**
     * Gets all organizations from database
     * @return List of organization
     */
    List<Organization> readAllOrganization();
    
    /**
     * Updates organization in DB
     * @param organization{organization object}
     * @return {updated organization object}
     */
    Organization updateOrganization(Organization organization);
    
    /**
     * Deletes organization from DB
     * @param id{organization id}
     * @return {true if organization deleted otherwise false}
     */
    boolean deleteOrganization(int id);
    
    /**
     * Get all heroes for given organization
     * @param organization{organization object}
     * @return {list of heroes}
     */
    List<Hero> readHeroesForOrganization(Organization organization);
    
    /**
     * Get all organizations for a given hero
     * @param hero{hero object}
     * @return {List of organizations}
     */
    List<Organization> displayOrganizationForHero(Hero hero);
}
