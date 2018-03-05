package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.cicle.bigger.dao.provider.BonusDynaSqlProvider;
import top.cicle.bigger.dao.provider.UserDynaSqlProvider;
import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.Notice;

public interface BonusDao {

	
	@SelectProvider(type=BonusDynaSqlProvider.class,method="addBonus")
	public void addBonus(Bonus bonus);
	
	//获取所有分红数据
	@Select("select * from "+BONUSTABLE+" order by create_time desc")
	public List<Bonus> getAllBonusData();
	
	//根据Id删除分红
	@Select("delete  from "+BONUSTABLE+" where id=#{id}")
	public Bonus deleteBonusById(Integer id);
}
