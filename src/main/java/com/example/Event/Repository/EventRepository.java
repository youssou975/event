package com.example.Event.Repository;

import com.example.Event.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Override
    Optional<Event> findById(Integer integer);
}
