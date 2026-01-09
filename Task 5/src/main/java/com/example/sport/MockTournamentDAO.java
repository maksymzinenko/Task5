package com.example.sport;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository("mockDAO")
public class MockTournamentDAO implements ITournamentDAO {
    private List<Tournament> list = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public MockTournamentDAO() {
        // Початкові дані
        add(new Tournament(0, "Test Cup", 2024, 1000, 1));
        add(new Tournament(0, "Super League", 2025, 5000, 2));
    }

    @Override
    public void add(Tournament t) {
        t.setId(idCounter.getAndIncrement());
        list.add(t);
    }

    @Override
    public List<Tournament> getAll() { return list; }

    @Override
    public void delete(int id) {
        list.removeIf(t -> t.getId() == id);
    }

    @Override
    public List<Tournament> searchByYear(int year) {
        return list.stream()
                .filter(t -> t.getYear() == year)
                .collect(Collectors.toList());
    }
}