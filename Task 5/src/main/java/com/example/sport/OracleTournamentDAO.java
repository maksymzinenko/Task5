package com.example.sport;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("oracleDAO")
public class OracleTournamentDAO implements ITournamentDAO {

    private static final String URL = "jdbc:oracle:thin:@//db.freesql.com:1521/23ai_34ui2";
    private static final String USER = "MAKSYM_ZINENKO_SCHEMA_F1S67";
    private static final String PASS = "JXNVMD969MWJZSJBIACFIYGYI2#7dY";
    private static final String INSERT = "INSERT INTO tournaments (name, year, prize_pool, sport_id) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM tournaments";
    private static final String DELETE = "DELETE FROM tournaments WHERE id=?";
    private static final String SEARCH = "SELECT * FROM tournaments WHERE year = ?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void add(Tournament t) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(INSERT)) {
            st.setString(1, t.getName());
            st.setInt(2, t.getYear());
            st.setDouble(3, t.getPrizePool());
            st.setInt(4, t.getSportId());
            st.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Tournament> getAll() {
        List<Tournament> list = new ArrayList<>();
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(GET_ALL)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void delete(int id) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(DELETE)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Tournament> searchByYear(int year) {
        List<Tournament> list = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(SEARCH)) {
            st.setInt(1, year);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Tournament mapRow(ResultSet rs) throws SQLException {
        return new Tournament(rs.getInt("id"), rs.getString("name"), rs.getInt("year"), rs.getDouble("prize_pool"), rs.getInt("sport_id"));
    }
}