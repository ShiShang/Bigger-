package top.cicle.bigger.domain;

import java.io.Serializable;

public class Performance_detail implements Serializable {
	
	private Integer id;
	private String year;
	private String month;
	private String price;
	private String clean_fee;
	private String source;
	private String day_in;
	private String day_out;
	private String day_live;
	private House house;
	
	
	
	public Performance_detail()
	{
		super();
	}
	
    public Integer getId()
    {
         return id;
    }
   public void setId(Integer  id)
    {
          this.id=id;
    }

   public String getYear()
    {
         return year;
    }
   public void setYear(String  year)
    {
          this.year=year;
    }

   public String getMonth()
    {
         return month;
    }
   public void setMonth(String  month)
    {
          this.month=month;
    }

   public String getPrice()
    {
         return price;
    }
   public void setPrice(String  price)
    {
          this.price=price;
    }

   public String getClean_fee()
    {
         return clean_fee;
    }
   public void setClean_fee(String  clean_fee)
    {
          this.clean_fee=clean_fee;
    }

   public String getSource()
    {
         return source;
    }
   public void setSource(String  source)
    {
          this.source=source;
    }

   public String getDay_in()
   {
        return day_in;
   }
  public void setDay_in(String  day_in)
   {
         this.day_in=day_in;
   }

  public String getDay_out()
   {
        return day_out;
   }
  public void setDay_out(String  day_out)
   {
         this.day_out=day_out;
   }

  public String getDay_live()
   {
        return day_live;
   }
  public void setDay_live(String  day_live)
   {
         this.day_live=day_live;
   }


   public House getHouse()
    {
         return house;
    }
   public void setHouse(House  house)
    {
          this.house=house;
    }



}
