package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
import java.util.List;

public interface SuperpowerDao {

    Superpower createSuperpower(Superpower superpower);

    Superpower readSuperpowerById(int id);

    List<Superpower> readAllSuperpowers();

    Superpower updateSuperpower(Superpower superpower);

    boolean deleteSuperpowerById(int id);

}
