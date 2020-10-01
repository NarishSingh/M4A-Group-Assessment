package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Organization;
import java.util.List;

public interface OrganizationDao {

    /**
     * Adds organization to Database
     *
     * @param organization {Organization} a well formed obj
     * @return {Organization} the successfully create obj from db
     */
    Organization createOrganization(Organization organization);

    /**
     * Get single organization by its id from database
     *
     * @param id {int} id for an existing Organization
     * @return {Organization} the obj from db at that id
     */
    Organization readOrganizationById(int id);

    /**
     * Gets all organizations from database
     *
     * @return {List} all organizations
     */
    List<Organization> readAllOrganizations();

    /**
     * Updates organization in DB
     *
     * @param organization {Organization} an updated obj with a valid id
     * @return {Organization} the successfully updated obj from db
     */
    Organization updateOrganization(Organization organization);

    /**
     * Deletes organization from DB
     *
     * @param id {int} id for an existing Organization
     * @return {boolean} true if organization deleted, otherwise false
     */
    boolean deleteOrganizationById(int id);

    /**
     * Get all heroes for given organization
     *
     * @param organization {Organization} a well formed obj
     * @return {List} all Hero obj's from db
     */
    List<Hero> readHeroesForOrganization(Organization organization);

    /**
     * Get all organizations for a given hero
     *
     * @param hero {Hero} a well formed Hero obj
     * @return {List} all Organizations where the Hero is a member
     */
    List<Organization> displayOrganizationForHero(Hero hero);
  
}
