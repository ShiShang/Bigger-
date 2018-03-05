package top.cicle.bigger.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import top.cicle.bigger.domain.Attachment;
import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.House;
import top.cicle.bigger.domain.Notice;
import top.cicle.bigger.domain.Performance;
import top.cicle.bigger.domain.Performance_detail;
import top.cicle.bigger.domain.User;

public interface biggerService {

	//User
	User login(String username,String password);
	
	User findUserById(Integer id);
	
	List<User> selectUserwithName(Map<String,Object> params);
	
	void updateUser(User user);
	
	Integer User_count();
	
	
	//Expense
	void addExpense(Expense expense);
	List<Expense> getAllExpenseData();
	void deleteExpenseById(Integer id);
	
	//Bonus
	void addBonus(Bonus bonus);
	List<Bonus> getAllBonusData();
	void deleteBonusById(Integer id);
	
	//House
	void addHouse(House house);
	House findHouseByName(String name);
	House findHouseById(Integer id);
	List<House> getAllHouse();
	
	//Notice 
	void addNotice(Notice notice);
	List<Notice> getAllNoticeData();
	Notice getNoticeById(Integer id);
	void deleteNoticeById(Integer id);
	
	//Performance
	void addPerformance(Performance performance);
	List<Performance> findPerformanceByIdAndDate(Map<String,Object> params);
	void deletePerformanceByIdAndDate(Map<String,Object> params);
	List<Performance> getAllPerformanceData();
	Integer getMonthProfitByDate(Map<String, Object> params);
	
	//Performance_detail
	void addPerformance_detail(Performance_detail pd);
	List<Performance_detail> findPerformance_detailByIdAndDate(Map<String,Object> params);
	void deletePerformance_detailByIdAndDate(Map<String,Object> params);
	Integer getAllOrderByDate(Map<String,Object> params);
	
	//Attachment
	List<Attachment> selectAttachent(Map<String,Object> params);
	void addAttachment(Attachment attachment);
	Attachment selectAttachmentById(Integer id);
	List<Attachment> selectAllAttachment();
	void delectAttById(Integer id);
}
