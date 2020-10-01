package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dao.HeroDaoDb.HeroMapper;
import com.sg.m4herosightings.dao.LocationDaoDb.LocationMapper;
import com.sg.m4herosightings.dao.SuperpowerDaoDb.SuperpowerMapper;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrganizationDaoDb implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        final String CREATE_ORGANIZATION = "INSERT INTO organization(name, description, phone, email, locationId) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(CREATE_ORGANIZATION, organization.getName(), organization.getDescription(), 
                organization.getPhone(), organization.getEmail(), organization.getLocation().getLocationId());
        int id= jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(id);
        insertIntoHeroOrganization(organization);
        return organization;
    }

    @Override
    public Organization readOrganizationById(int id) {
        try{
            final String GET_ORGANIZATION_BY_ID =   "SELECT * FROM organization WHERE organizationId = ?";
            Organization organization = jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            organization.setLocation(getLocationForOrganization(id));
            organization.setMembers(getMembersForOrganization(id));
            return organization;
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Organization> readAllOrganization() {
        final String GET_ORGANIZATIONS = "SELECT * FROM organization";
        List<Organization> organizations = jdbc.query(GET_ORGANIZATIONS, new OrganizationMapper());
        getLocationsAndHeroesForOrganization(organizations);
        return organizations;
    }

    @Override
    @Transactional
    public Organization updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET name=?, description=?, phone=?, email=?, locationid=? "
                + "WHERE organizationId = ?";
        int success = jdbc.update(UPDATE_ORGANIZATION, organization.getName(), organization.getDescription(), organization.getPhone(), 
                organization.getEmail(), organization.getLocation().getLocationId(), organization.getOrganizationId());
        if(success == 1){
            final String DELETE_HERO_ORGANIZATION  = "DELETE FROM heroOrganization WHERE organizationId = ?";
            jdbc.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationId());
            insertIntoHeroOrganization(organization);
            return organization;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteOrganization(int id) {
        /*
            first delete from bridge then from organization
        */
        final String DELETE_ORGANIZATION_HERO_ORGANIZATION  = "DELETE FROM heroOrganization WHERE organizationId = ?";
            jdbc.update(DELETE_ORGANIZATION_HERO_ORGANIZATION, id);
        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE organizationId = ?";
            return jdbc.update(DELETE_ORGANIZATION, id)>0;
    }

    @Override
    public List<Hero> readHeroesForOrganization(Organization organization) {
        final String GET_HEROES_ORGANIZATION = "SELECT * FROM hero h "
                + "JOIN heroOrganization ho ON h.heroId = ho.heroId "
                + "JOIN organization o ON ho.organizationId = o.organizationId "
                + "WHERE organizationId = ?";
        List<Hero> heroes =  jdbc.query(GET_HEROES_ORGANIZATION, new HeroMapper(), organization.getOrganizationId());
        for(Hero hero: heroes){
            hero.setSuperpower(getPowerForHeroes(hero.getHeroId()));
        }
        return heroes;
    }
    
    
    @Override
    public List<Organization> displayOrganizationForHero(Hero hero) {
        final String GET_ORGNIZATIONS_FOR_HERO = "SELECT * organization o "
                + "JOIN heroOrganization ho ON o.organizationId = ho.organization Id "
                + "WHERE ho.heroId = ?";
        List<Organization> organizations = jdbc.query(GET_ORGNIZATIONS_FOR_HERO, new OrganizationMapper(),  
                hero.getHeroId());
        getLocationsAndHeroesForOrganization(organizations);
        
        return organizations;
    }

       
    private void getLocationsAndHeroesForOrganization(List<Organization> organizations) {
        for(Organization org: organizations){
            org.setLocation(getLocationForOrganization(org.getOrganizationId()));
            org.setMembers(getMembersForOrganization(org.getOrganizationId()));
        }
    }

    
    /*
        This helper method for setup location for a organization by organizationId. 
        returns Location; It called inside the getLocationsAndHeroesForOrganization method
    */
    private Location getLocationForOrganization(int id) {
        final String GET_LOCATION_ORGANIZATION = "SELECT l.* FROM location l "
                + "JOIN organization o ON o.locationId = l.locationId "
                + "WHERE o.organizationId = ?";
        return jdbc.queryForObject(GET_LOCATION_ORGANIZATION, new LocationMapper(), id);
    }

    /*
    This helper method for setup heroes for a given organization by organization id
    returns list of heroes. It used inside the getLocationsAndHeroesForOrganization method
    */
    private List<Hero> getMembersForOrganization(int id) {
        final String GET_HERO_ORGANIZATION = "SELECT h.* FROM hero h "
                + "JOIN heroOrganization ho ON h.heroId=ho.heroId "
                + "WHERE organizationId = ?";
        List<Hero> heroes =  jdbc.query(GET_HERO_ORGANIZATION, new HeroMapper(),id);

        for(Hero hero: heroes){
            hero.setSuperpower(getPowerForHeroes(hero.getHeroId()));
        }
        
        return heroes;
    }

    /**
    *This method gets a superpower for hero. used inside the getMembersForOrganization
    and readHeroesForOrganization methods 
    para- {hero id}
    * return-{}
    */
    private Superpower getPowerForHeroes(int id){
        String getPowerForHero = "SELECT * FROM superpower s "
                + "JOIN hero h ON s.superpowerId = h.superpowerId "
                + "WHERE h.heroId = ?";
        return jdbc.queryForObject(getPowerForHero, new SuperpowerMapper(), id);
    }
 
    /**
     * This helper method inserts heroId and organizationId into bridge table, used inside the create method
     * @param organization - organization object
     */
    private void insertIntoHeroOrganization(Organization organization) {
        final String ADD_HERO_ORGANIZATION = "INSERT into heroOrganization(heroId, organizationId) VALUES(?,?)";
        for(Hero hero: organization.getMembers()){
            jdbc.update(ADD_HERO_ORGANIZATION, hero.getHeroId(), organization.getOrganizationId());
        }
    }


    /*Mapper*/
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getInt("organizationId"));
            o.setName(rs.getString("name"));
            o.setDescription(rs.getString("description"));
            o.setPhone(rs.getString("phone"));
            o.setEmail(rs.getString("email"));
            //Location and list of member heroes will be brought in by helper methods
            
            return o;
        }
    }
    
}
