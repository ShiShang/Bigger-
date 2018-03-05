package top.cicle.bigger.util.tag;

import top.cicle.bigger.common.*;

public class PageModel {

	private int recordCount;//总记录数
	private int pageIndex;//当前页数
	private int pageSize=biggerConstants.PAGE_DEFAULT_SIZE;//每页多少条数据
	private int totalSize;//总页数
	
	public int getRecordCount()
	{
		this.recordCount=this.recordCount<=0 ?0:this.recordCount;
		return this.recordCount;
	}
	public void setRecordCount(int recordCount)
	{
		this.recordCount=recordCount;
	}
	public int getTotalSize()
	{
		if(this.recordCount<=0)
		{
			this.totalSize=0;
		}
		else
		{
			this.totalSize=(this.getRecordCount()-1)/this.pageSize+1;
		}
		return this.totalSize;
	}
    public int getPageIndex()
    {
    	this.pageIndex=this.pageIndex<=0 ?1:this.pageIndex;
    	this.pageIndex=this.pageIndex>this.getTotalSize() ?this.getTotalSize():this.pageIndex;
    	return this.pageIndex;
    }
    public void setPageIndex(int pageIndex)
    {
    	this.pageIndex=pageIndex;
    }
    public int getFirstLimitPage()
    {
    	return(this.pageIndex-1)*this.pageSize;
    }
    
	
	
	
}
