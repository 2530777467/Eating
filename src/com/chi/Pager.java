package com.chi;
public class Pager {
	public int allrecordcount;
	public int allpagecount;
	public int currentpage;
	public int pagesize;
	public String urlname;
	public String w;//����ĸ������
	
	public String GetPageInfo()
	{
		String s="�ܹ� "+allrecordcount+" ����¼ ÿҳ "+pagesize+" �� ";
		if(allrecordcount%pagesize==0)
		{
			allpagecount=allrecordcount/pagesize;
		}
		else
		{
			allpagecount=(allrecordcount/pagesize)+1;
		}
		int prepage=(currentpage>1)?(currentpage-1):1;
		int nextpage=(currentpage==allpagecount)?allpagecount:(currentpage+1);
		s+="�� "+allpagecount+" ҳ ��ǰ�� "+currentpage+" ҳ  ";
		if(w==null||"".equals(w))
		{
			s+="<a href=\""+urlname+"?p=1\">��ҳ</a> <a href=\""+urlname+"?p="+prepage+"\">��һҳ</a> <a href=\""+urlname+"?p="+nextpage+"\">��һҳ</a> <a href=\""+urlname+"?p="+allpagecount+"\">ĩҳ</a>";
		}
		else
		{
			s+="<a href=\""+urlname+"?p=1&"+w+"\">��ҳ</a> <a href=\""+urlname+"?p="+prepage+"&"+w+"\">��һҳ</a> <a href=\""+urlname+"?p="+nextpage+"&"+w+"\">��һҳ</a> <a href=\""+urlname+"?p="+allpagecount+"&"+w+"\">ĩҳ</a>";
		}
		
		return s;
	}
	
}
