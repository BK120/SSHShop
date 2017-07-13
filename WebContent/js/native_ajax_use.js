	//传统的ajax访问网络,get
	function native_ajax_init(url) {
		 //创建交互对象
		var xhr=createXmlHttp();
		//设置监听
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					//获取后数据操作
					$("#span1").html(xhr.responseText);
				}
			}
		};
		//打开连接
		xhr.open("GET", url, true);
		//发送
		xhr.send(null);
	}
	//创建对象
	function createXmlHttp() {
		var xmlHttp;
		try{
			xmlHttp=new XMLHttpRequest();
		}catch (e) {
			try{
				xmlHttp=new ActiveXObject("Msxml12.XMLHTTP");
			}catch (e) {
				try{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				}catch (e) {
				}
			}
		}
		return xmlHttp;
	}