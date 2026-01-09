package com.example.sport;
import java.util.List;

public interface ITournamentDAO {
    void add(Tournament t);
    List<Tournament> getAll();
    void delete(int id);
    List<Tournament> searchByYear(int year);
}