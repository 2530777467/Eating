package com.chi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FoodEdit
 */
@WebServlet("/FoodEdit")
public class FoodEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * �޸Ĺ��ܰ����������̣�
		 * ��һ�����̣����Ǵ����ݿ���ؾɵ����ݵ�ҳ�档�����ȴ��б�ҳ�����޸İ�ť��Ȼ��ʼ�����޸ĵ�servlet����servlet�в�ѯ���ݿ⣬�Ѿɵ����ݲ�ѯ�س�����Ȼ�󴫸��޸�ҳ��ı���
		 * �ڶ������̣����޸ĺ�ı����ݴ��ݵ�servlet,Ȼ���ٱ��浽���ݿ⡣
		 * 
		 * ע�⣺�޸����ݵ�ʱ��һ��Ҫָ���޸ĵ�id,�Ǹ���id�������޸ĵģ�ÿ��ֻ�޸�һ�����ݡ�
		 * ����ͨ��url��ַ����������get���� 
		 * request:����ǰ�˴��ݹ����Ĳ���
		 * response�������ǰ����Ӧ���ݡ�
		 * */
		//���ý��ղ����ı���
		request.setCharacterEncoding("utf-8");
		//���ղ���id
		String id=request.getParameter("id");
		//����sql��䣬��ԭ���ɵ����ݲ�ѯ����
		String strSql="select * from food where id=?";
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
		request.getRequestDispatcher("/admin/foodedit.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//���ý��ܲ����ı���
		 request.setCharacterEncoding("utf-8"); 
		 
		 String j_id="";
		 String j_kind="";
		 String j_cainame=""; 
		 String j_price=""; 
		 String j_brief="";  
		 String imgpath="";
		  
		//�ϴ��ļ� ���ļ��ϴ���ָ����Ŀ¼
		 //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
       String savePath = this.getServletContext().getRealPath("/upload");
       File file = new File(savePath);
       //�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
       if (!file.exists() && !file.isDirectory()) {
           System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
           //����Ŀ¼
           file.mkdir();
       }
       //��Ϣ��ʾ
       String message = "";
       try{
           //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
           //1������һ��DiskFileItemFactory����
           DiskFileItemFactory factory = new DiskFileItemFactory();
           //2������һ���ļ��ϴ�������
           ServletFileUpload upload = new ServletFileUpload(factory);
            //����ϴ��ļ�������������
           upload.setHeaderEncoding("UTF-8"); 
           //3���ж��ύ�����������Ƿ����ϴ���������
           if(!ServletFileUpload.isMultipartContent(request)){
               //���մ�ͳ��ʽ��ȡ����
               return;
           }
           //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
           List<FileItem> list = upload.parseRequest(request);
           for(FileItem item : list){
           	
               //���fileitem�з�װ������ͨ�����������
               if(item.isFormField()){
                   String name = item.getFieldName();
                   //�����ͨ����������ݵ�������������
                   if(name.equals("id"))
                   {
                   	j_id=item.getString("UTF-8");
                   }
                   if(name.equals("kind"))
                   {
                	j_kind=item.getString("UTF-8");
                   }
                   if(name.equals("cainame"))
                   {
                   	j_cainame=item.getString("UTF-8");
                   }
                   if(name.equals("price"))
                   {
                	j_price=item.getString("UTF-8");
                   }
                   if(name.equals("brief"))
                   {
                	j_brief=item.getString("UTF-8");
                   }
                  
               }else{//���fileitem�з�װ�����ϴ��ļ�
                   //�õ��ϴ����ļ����ƣ�
                   String filename = item.getName();
                   System.out.println(filename);
                   if(filename==null || filename.trim().equals("")){
                       continue;
                   }
                   //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\111.png������Щֻ�ǵ������ļ������磺1.txt
                   //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
                   String namestr= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                   
                   filename =namestr+filename.substring(filename.lastIndexOf("."));
                   imgpath=filename;
                   //��ȡitem�е��ϴ��ļ���������
                   InputStream in = item.getInputStream();
                   //����һ���ļ������
                   FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                   //����һ��������
                   byte buffer[] = new byte[1024];
                   //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                   int len = 0;
                   //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                   while((len=in.read(buffer))>0){
                       //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                       out.write(buffer, 0, len);
                   }
                   //�ر�������
                   in.close();
                   //�ر������
                   out.close();
                   //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
                   item.delete();
               }
           }
       }catch (Exception e) {
           message= "�ļ��ϴ�ʧ�ܣ�";
           e.printStackTrace();
           
       }
       
		 //����һ���������󣬰�Ҫ��ӵ����ݶ��ŵ���������ϡ�
		 List<Object> params = new ArrayList<Object>();
		 
		 params.add(j_kind);
		 params.add(j_cainame);
		 params.add(j_price);
		 params.add(j_brief);
		
		 if(imgpath.length()>0)
		 {
			 params.add(imgpath);
		 }
		 params.add(j_id);
		 
		 //����sql��ѯ��� 
		 String strSql=" update food set kind=?,cainame=?,price=?,brief=? where id=?";
		 if(imgpath.length()>0)
		 {
			 strSql=" update food set kind=?,cainame=?,price=?,brief=?,imgurl=? where id=?";
		 }
		 //DBHelper��һ�����ݿ�����Ĺ����࣬�������װ��ִ��sql����ķ������Ժ���Ҫ�������ݿ�ĵط�����Ҫnewһ�������Ķ���ͨ���������ȥִ��sql���
		 DBHelper Dal=new DBHelper();
		 Dal.excuteSql(strSql, params);
		 
		 //����ǰ�ˣ�����Ѿ������ݱ��浽�����ݿ�
		 response.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=utf-8");
		 response.getWriter().write("<font color='green'>����ɹ�,0��֮������ת������ҳ���������</font>");
		 //5��֮����ת�����ҳ��
        response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/FoodEdit?id="+j_id);
	}

}
