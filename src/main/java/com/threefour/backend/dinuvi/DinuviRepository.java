package com.threefour.backend.dinuvi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinuviRepository extends JpaRepository<Dinuvi, Long> {

}