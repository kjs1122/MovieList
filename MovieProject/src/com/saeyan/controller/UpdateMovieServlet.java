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
 * Servlet implementation class UpdateMovieServlet
 */
@WebServlet("/updateMovie.do")
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		MovieDAO mDao = MovieDAO.getInstance();
		MovieVO mVo = mDao.codeSelectMovie(code);
		
		request.setAttribute("mVo", mVo);
		
		request.getRequestDispatcher("movie/updateMovie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("images");
		int limitSize = 20 * 1024 * 1024;
		String encType = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				limitSize,
				encType,
				new DefaultFileRenamePolicy());

		MovieVO mVo = new MovieVO();
		mVo.setCode(Integer.parseInt(multi.getParameter("code")));
		mVo.setTitle(multi.getParameter("title"));
		mVo.setPrice(Integer.parseInt(multi.getParameter("price")));
		mVo.setDirector(multi.getParameter("director"));
		mVo.setActor(multi.getParameter("actor"));
		mVo.setPoster(multi.getFilesystemName("poster"));
		mVo.setSynopsis(multi.getParameter("synopsis"));
		
		if(multi.getFilesystemName("poster") == null) {
			mVo.setPoster(multi.getParameter("posterNull"));
		}
		MovieDAO mDao = MovieDAO.getInstance();
		mDao.updateMovie(mVo);
	
		response.sendRedirect("movieList.do");
		
	}

}
