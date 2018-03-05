package top.cicle.bigger.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import static top.cicle.bigger.common.biggerConstants.*;
import top.cicle.bigger.dao.provider.AttachmentDynaSqlProvider;
import top.cicle.bigger.domain.Attachment;

public interface AttachmentDao {

	//查找附件（通过标题和内容模糊查询）
	@SelectProvider(type=AttachmentDynaSqlProvider.class,method="selectAttachment")
	public List<Attachment> selectAttachment(Map<String,Object> params);
	
	//添加附件
	@SelectProvider(type=AttachmentDynaSqlProvider.class,method="addAttachment")
	public void addAttachment(Attachment attachment);
	
	//查找附件（通过id）
	@Select("select * from "+ ATTACHMENTTABLE+" where id= #{id}")
	public Attachment selectAttachmentById(Integer id);
	
	//查找所有附件
	@Select("select * from "+ ATTACHMENTTABLE+" order by create_time DESC")
	List<Attachment> selectAllAttachment();
	
	//根据ID删除附件
	@Select("delete from "+ ATTACHMENTTABLE+" where id= #{id}")
	void delectAttById(Integer id);
}
