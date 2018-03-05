package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.cicle.bigger.dao.provider.NoticeDynaSqlProvider;
import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Notice;

public interface NoticeDao {

	//添加公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="addNotice")
	public void addNotice(Notice notice);
	
	//获取所有公告数据
	@Select("select * from "+NOTICETABLE+" order by create_time desc")
	public List<Notice> getAllNoticeData();
	
	//根据Id查找公告
	@Select("select * from "+NOTICETABLE+" where id=#{id}")
	public Notice getNoticeById(Integer id);
	
	//根据Id删除公告
	@Select("delete  from "+NOTICETABLE+" where id=#{id}")
	public Notice deleteNoticeById(Integer id);
	
}
