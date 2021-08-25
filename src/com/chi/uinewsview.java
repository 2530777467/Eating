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
 * Servlet implementation class uinewsview
 */
@WebServlet("/uinewsview")
public class uinewsview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uinewsview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//���ղ���id
		String id=request.getParameter("id");
		String strSql="select * from tbnews where id=?";
		
		//������������Ұѽ��յ���id���ӵ������ϡ�
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		//����һ�����ݿ���������
		DBHelper dal=new DBHelper();
		//ʹ�����ݿ���������ѯ���ݿ⣬���ҰѲ�ѯ�Ľ�����浽objbyid�����С�
		Map<String, Object> objbyid =dal.getSingleObject(strSql, params);
		//��objbyid������󸽼ӵ�request������
		request.setAttribute("objbyid", objbyid);
		//�ض���ǰ�˵�jspҳ�棬���ҰѲ�ѯ���Ҳ���ݹ�ȥ��
		request.getRequestDispatcher("newsview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

}
