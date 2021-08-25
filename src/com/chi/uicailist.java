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

import com.chi.DBHelper;
import com.chi.Pager;

/**
 * Servlet implementation class uicailist
 */
@WebServlet("/uicailist")
public class uicailist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uicailist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 String typenname=request.getParameter("typenname"); 
		 String kind="";
			switch (typenname) {
				case "1":kind="����";break;
				case "2":kind="����";break;
				case "3":kind="�ղ�";break;
				case "4":kind="���";break;
				case "5":kind="�ղ�";break;
				case "6":kind="����";break;
				case "7":kind="³��";break;
				case "8":kind="���";break;
			}
			
			
		int currentpage=1;//��ǰҳ�룬Ĭ�ϲ�ѯ��һҳ������
		try
		{
			String p=request.getParameter("p");//����ҳ�룬����p�����ڷ�ҳ�ؼ�����ҳ�ؼ����洫�ݲ����õĹؼ��־���p,�����������Ҳ��p������
			currentpage = Integer.valueOf(p);//ǰ����յ���ҳ�����ַ����� ��ҳ��תΪ��������
		}
		catch(Exception e){
			currentpage=1;//����ܽ��յ�ҳ�룬��ô��ѯ����ʱ����ý��յ���ҳ�룬���û�н��յ�ҳ�룬��Ĭ�ϲ�ѯ��һҳ��
		}
		
		
		
		DBHelper Dal=new DBHelper();
		//��ѯȫ�����ݣ�ֻ��֪�����ܹ���������¼֮�󣬲��ܹ����ɷ�ҳ��Ϣ
		String strSql=" select id from food order by id desc "; 
		
		if(!(typenname==null||typenname==""))
		{
			strSql=" select id from food where kind='"+kind+"'order by id desc "; 
		}
		
		
		List<Map<String, Object>> listall = null;
		List<Object> params = new ArrayList<Object>();
		try {
			listall=Dal.executeQuery(strSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		//Ҫ���ɷ�ҳ��Ϣ������Ҫ��newһ����ҳ�ؼ���Ķ���
		Pager pageobj=new Pager();
		pageobj.allrecordcount=listall.size();//ͨ��listall.size()�ͻ�ȡ�����ݿ������ļ�¼������������ҳ�ؼ�ָ���ܵļ�¼����
		pageobj.pagesize=10;//����ҳ�ؼ�ָ��ÿҳ��ʾ����
		pageobj.currentpage=currentpage;//����ҳ�ؼ�ָ����ǰ��ҳ��
		pageobj.urlname="";
		pageobj.w="typenname="+typenname;
		
		//��ҳ��ѯ
		int startindex=pageobj.pagesize*(pageobj.currentpage-1);//�����ܹ�Ҫ������������¼ 
		String strSqlpager=" select * from food order by id desc limit "+startindex+","+pageobj.pagesize+""; //�����ҳ��ѯsql���
		
		if(!(typenname==null||typenname==""))
		{
			strSqlpager=" select * from food where kind='"+kind+"' order by id desc limit "+startindex+","+pageobj.pagesize+""; 
		}
		
		List<Map<String, Object>> listpage = null;
		try {
			listpage=Dal.executeQuery(strSqlpager, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		
		String pagestr=pageobj.GetPageInfo();//ͨ����ҳ�ؼ�������������GetPageInfo()��ȡ����ҳ��Ϣ��
		
		request.setAttribute("pagestr", pagestr);//�ѷ�ҳ��Ϣ���ӵ�request������
		request.setAttribute("lists", listpage);//�ѷ�ҳ��������Ϣ���ӵ�request������
		request.getRequestDispatcher("cailist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
