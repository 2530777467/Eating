package com.chi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chi.DBHelper;

/**
 * Servlet implementation class OrderDelete
 */
@WebServlet("/OrderDelete")
public class OrderDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("utf-8");
	  	String r_id=request.getParameter("id");
	  	List<Object> params = new ArrayList<Object>();
	  	params.add(r_id);
	  	String strSql="delete from ordered where id=?";
	  	DBHelper Dal=new DBHelper();
	  	Dal.excuteSql(strSql, params);
		response.sendRedirect("OrderList");//�ض����б�ҳ�� 
	  
	  	
	    //����ǰ�ˣ�����Ѿ������ݱ��浽�����ݿ�
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().write("<font color='green'>ɾ���ɹ�,0��֮������ת���б�</font>");
//        response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/FoodListWithOutPager");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}