/**
 * 
 */


//获取url相关参数的函数
var getParam = function(name){  
    var search = document.location.search;  
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");  
    var matcher = pattern.exec(search);  
    var items = null;  
    if(null != matcher){  
            try{  
                    items = decodeURIComponent(decodeURIComponent(matcher[1]));  
            }catch(e){  
                    try{  
                            items = decodeURIComponent(matcher[1]);  
                    }catch(e){  
                            items = matcher[1];  
                    }  
            }  
    }  
    return items;  
};  


function logout()
{
	 $.ajax({
		  type : "post",
		  async : true,           
		  url : "/bigger/user/logout",
		  data : {},
		  dataType : "json",  
		  success : function(result) {
			  alert("退出成功！");
			  reload();
		     return;
		  }
		  })
};

