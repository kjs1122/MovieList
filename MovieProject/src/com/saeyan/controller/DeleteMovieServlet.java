package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.MovieVO;
import com.saeyan.dvo.MovieDAO;

/**
 * Servlet implementation class DeleteMovieServlet
 */
@WebServlet("/deleteMovie.do")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		MovieDAO mDao = MovieDAO.getInstance();
		MovieVO mVo = mDao.codeSelectMovie(code);
		
		request.setAttribute("mVo", mVo);
		
		request.getRequestDispatcher("movie/deleteMovie.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		MovieDAO mDao = MovieDAO.getInstance();
		mDao.deleteMovie(code);
		
		response.sendRedirect("movieList.do");
	}

}
