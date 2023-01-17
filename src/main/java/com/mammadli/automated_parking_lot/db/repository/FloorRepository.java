package com.mammadli.automated_parking_lot.db.repository;

import com.mammadli.automated_parking_lot.db.entity.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends CrudRepository<Floor,String> {
    List<Floor> findByCeilingHeightGreaterThan(int height);
}
