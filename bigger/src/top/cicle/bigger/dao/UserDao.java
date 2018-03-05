package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import top.cicle.bigger.dao.provider.UserDynaSqlProvider;
import top.cicle.bigger.domain.User;


public interface UserDao {

	@Select("select * from "+USERTABLE+" where email=#{email} and password=#{password}") 
	User selectByNameAndPassword(@Param("email") String name, @Param("password") String password);
	
	
	@Select("select * from "+USERTABLE+" where id=#{id}")
	User selectById(Integer id);
	
	@Delete("delete * from "+USERTABLE+" where id=#{id}")
	void deleteById(Integer id);
	
	@Select("select * from "+USERTABLE)
	List<User> findAllUser();
	
	//模糊查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectUserByPage(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void updateUser(User user);
	
	@Select("select count(*) from "+ USERTABLE)
	Integer countUser();
}
