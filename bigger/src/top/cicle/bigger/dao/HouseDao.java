package top.cicle.bigger.dao;


import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.cicle.bigger.dao.provider.HouseDynaSqlProvider;
import top.cicle.bigger.domain.House;

public interface HouseDao {

	@Select("select count(*) from "+ HOUSETABLE)
	public Integer house_count();
	
	@Select("select * from "+ HOUSETABLE+" where id=#{id}")
	public House selectHouseById(Integer id);
	
	@Select("select * from "+ HOUSETABLE+" where name like concat('%',#{name},'%')")
	public House selectHouseByName(String name);

	
	@SelectProvider(type=HouseDynaSqlProvider.class,method="addHouse")
	public void addHouse(House house);
	
	//查找房源列表
	@Select("select * from   "+ HOUSETABLE)
	public List<House> getAllHouse();
	
	
}
