package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dto.MovieVO;
import com.saeyan.dvo.MovieDAO;

/**
 * Servlet implementation class CreateMovieServlet
 */
@WebServlet("/insertMovie.do")
public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("movie/insertMovie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context =  getServletContext();
		String path = context.getRealPath("images");
		String encType = "UTF-8";
		int limitSize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				limitSize,
				encType,
				new DefaultFileRenamePolicy());

		String title = multi.getParameter("title");
		String price = multi.getParameter("price");
		String director = multi.getParameter("director");
		String actor = multi.getParameter("actor");
		String synopsis = multi.getParameter("synopsis");
		String poster = multi.getFilesystemName("poster");
		
		MovieVO mVo = new MovieVO();
		
		mVo.setTitle(title);
		mVo.setPrice(Integer.parseInt(price));
		mVo.setActor(actor);
		mVo.setDirector(director);
		mVo.setPoster(poster);
		mVo.setSynopsis(synopsis);
		if(poster == null) {
			poster = "noimage.jpg";
		}
		MovieDAO mDao = MovieDAO.getInstance();
		mDao.insertMovie(mVo);
		
		response.sendRedirect("movieList.do");
	}

}
