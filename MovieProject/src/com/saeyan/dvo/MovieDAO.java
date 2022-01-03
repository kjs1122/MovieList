package com.saeyan.dvo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.MovieVO;

import util.DBmanager;

public class MovieDAO {

	private MovieDAO() {
		
	}
	
	private static MovieDAO instance = new MovieDAO();
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public List<MovieVO> selectAllMovies() {
		String sql = "select * from movie order by code desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO mVo = new MovieVO();
				mVo.setCode(rs.getInt("code"));
				mVo.setTitle(rs.getString("title"));
				mVo.setPrice(rs.getInt("price"));
				mVo.setActor(rs.getString("actor"));
				mVo.setDirector(rs.getString("director"));
				mVo.setPoster(rs.getString("poster"));
				mVo.setSynopsis(rs.getString("synopsis"));
				list.add(mVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public void insertMovie(MovieVO mVo) {
		String sql = "insert into movie values (movie_seq.nextval,"
				+ "?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getTitle());
			pstmt.setInt(2, mVo.getPrice());
			pstmt.setString(3, mVo.getDirector());
			pstmt.setString(4, mVo.getActor());
			pstmt.setString(5, mVo.getPoster());
			pstmt.setString(6, mVo.getSynopsis());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}
	}
	
	public MovieVO codeSelectMovie(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieVO mVo = null;
		String sql = "select * from movie where code = ?";
		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, code);
		    rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	mVo = new MovieVO();
		    	mVo.setCode(rs.getInt("code"));
				mVo.setTitle(rs.getString("title"));
				mVo.setPrice(rs.getInt("price"));
				mVo.setActor(rs.getString("actor"));
				mVo.setDirector(rs.getString("director"));
				mVo.setPoster(rs.getString("poster"));
				mVo.setSynopsis(rs.getString("synopsis"));
		    }
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBmanager.close(conn, pstmt, rs);
		}
		
		return mVo;
	}
	
	public void updateMovie(MovieVO mVo) {
		String sql = "update movie set title=?,price=?,director=?,actor=?,poster=?,synopsis=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getTitle());
			pstmt.setInt(2, mVo.getPrice());
			pstmt.setString(3, mVo.getDirector());
			pstmt.setString(4, mVo.getActor());
			pstmt.setString(5, mVo.getPoster());
			pstmt.setString(6, mVo.getSynopsis());
			pstmt.setInt(7, mVo.getCode());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}
		
	}
	
	public void deleteMovie(String code) {
		String sql = "delete movie where code = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBmanager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(conn, pstmt);
		}
	}
}
