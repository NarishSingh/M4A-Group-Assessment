/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
import java.util.List;

/**
 *
 * @author irabob
 */
public interface SuperpowerDao {
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    Superpower getSuperpowerById(int id);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpower(int id);
}
