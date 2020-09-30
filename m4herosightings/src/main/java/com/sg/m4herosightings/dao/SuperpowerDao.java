package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
import java.util.List;

public interface SuperpowerDao {
    List<Superpower> getAllSuperpowers();
    
    Superpower addSuperpower(Superpower superpower);
    
    Superpower getSuperpowerById(int id);
    
    void updateSuperpower(Superpower superpower);
    
    void deleteSuperpower(int id);
}
