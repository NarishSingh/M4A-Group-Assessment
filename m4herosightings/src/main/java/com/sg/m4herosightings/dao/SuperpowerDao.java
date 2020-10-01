package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
import java.util.List;

public interface SuperpowerDao {

    /**
     * Create a Superpower in db
     *
     * @param superpower {Superpower} a well formed obj
     * @return {Superpower} the successfully created obj from db
     */
    Superpower createSuperpower(Superpower superpower);

    /**
     * Read a Superpower from db
     *
     * @param id {int} a valid id for an existing Superpower
     * @return {Superpower} the obj from db
     */
    Superpower readSuperpowerById(int id);

    /**
     * Read all Superpowers from db
     *
     * @return {List} all obj in db
     */
    List<Superpower> readAllSuperpowers();

    /**
     * Update a Superpower row in db
     *
     * @param superpower {Superpower} the update obj with corresponding id
     * @return {Superpower} the successfully updated obj from db, null otherwise
     */
    Superpower updateSuperpower(Superpower superpower);

    /**
     * Delete a Superpower from db
     *
     * @param id {int} the id for an existing Superpower
     * @return {boolean} true if successfully deleted, false otherwise
     */
    boolean deleteSuperpowerById(int id);

}
