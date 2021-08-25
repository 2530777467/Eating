package com.chi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
/**
 * Servlet implementation class AjaxForCai
 */
@WebServlet("/AjaxForCai")
public class AjaxForCai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxForCai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String typeid=request.getParameter("typeid"); 
		String kind="";
		switch (typeid) {
			case "1":kind="����";break;
			case "2":kind="����";break;
			case "3":kind="�ղ�";break;
			case "4":kind="���";break;
			case "5":kind="�ղ�";break;
			case "6":kind="����";break;
			case "7":kind="³��";break;
			case "8":kind="���";break;
		}
		DBHelper Dal=new DBHelper();
		//��ѯȫ�����ݣ�ֻ��֪�����ܹ���������¼֮�󣬲��ܹ����ɷ�ҳ��Ϣ
		String strSql="select * from food where kind=? order by id desc limit 0,6"; 
		List<Map<String, Object>> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(kind);
		try {
			list=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		String jsonstr =JSON.toJSONString(list); 
		response.getWriter().write(jsonstr);
		
	}

}
