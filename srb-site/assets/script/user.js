// JavaScript Document
$(function(){
	//首页顶部
	$("#top-tip i.top-tipclose").click(function(){
		$("#top-tip").addClass("hide");
		})
	 
	//首页底部
	$("#gzwmwx").hover(function(){
		$(".weixin-bg").css("display","block");},
		function(){
		$(".weixin-bg").css("display","none");}
	)
		//顶部微信
	$("#top-wx").hover(
		function(){
		$(".top-wx").css("display","block");},
		function(){
		$(".top-wx").css("display","none");}
	)
	
	//顶部手机亿人宝 
	fnPhtg();
	function fnPhtg(){
		var timer = null;
		$("#top-tgph").mouseenter(function(){
			clearTimeout(timer);
			$('.ph-tg').show();
		});
		$("#top-tgph").mouseleave(function(){
			timer = setTimeout(function(){
				$('.ph-tg').hide();
			},100)
		});
	}
	
	//首页-社会公益-轮播效果
	fnBenefit();
	function fnBenefit(){
		var i = 0;
		var iTimer = null;
		var oUl = $('.benefit-main ul');
		var aLi = $('.benefit-main ul').find('li');
		var aSpan = $('.benefit-title p').find('em');	
		var oTitle = $('.benefit-title span');	

		aSpan.click(function(){

			i = $(this).index();
			//console.log(i);
			fadeFn();				
		});		
		
		function fadeFn(){	
			oUl.animate({left : -i*224},500);	
			oTitle.text(aLi.eq(i).find('img').attr('title'));
			aSpan.removeClass();
			aSpan.eq(i).addClass('benefit-active');				
		}
		
		function autoPlay(){
			i++;
			i%=aLi.size();
			fadeFn();				
		}

		iTimer = setInterval(autoPlay,3000);
		$('.benefit-main').mouseover(function() {
			clearInterval(iTimer);
		});

		$('.benefit-main').mouseout(function() {
			clearInterval(iTimer); //先停止，再开启；
			iTimer = setInterval(autoPlay,3000);
		});		

	}


	
	//abouticon
		$("#abouticon li.icon1").hover(
			function () { $(this).removeClass("icon1").addClass("on-icon1");}, 
			function () { $(this).removeClass("on-icon1").addClass("icon1");}
			)
		$("#abouticon li.icon2").hover(
			function () { $(this).removeClass("icon2").addClass("on-icon2");}, 
			function () { $(this).removeClass("on-icon2").addClass("icon2");}
			)

		$("#abouticon li.icon3").hover(
			function () { $(this).removeClass("icon3").addClass("on-icon3");}, function () { $(this).removeClass("on-icon3").addClass("icon3");})
		$("#abouticon li.icon4").hover(
			function () { $(this).removeClass("icon4").addClass("on-icon4");}, function () { $(this).removeClass("on-icon4").addClass("icon4");})
		
	/*creditor-list*/
	$(".creditor-list li").hover(function(){
			$(this).children('.blue-border').removeClass("blue-border").addClass("pink-border");
			$(this).children('.pink-border1').css("display","block");
	},function() {
			$(this).children('.pink-border').removeClass("pink-border").addClass("blue-border");
			$(this).children('.pink-border1').css("display","none");
	});
	
	/*登录文本框交互框关键词*//*注册文本框交互框关键词*/
$('.log-txt,.reg-txt').focus(function(){
	var txt_value = $(this).val();
	$(this).css("border","1px solid #0caffe");
	if(txt_value == this.defaultValue){
		$(this).val('');
		}
	})
$('.log-txt,.reg-txt').keydown(function(){
	$(this).css("color","#4B4948");
	})
	
$('.log-txt,.reg-txt').blur(function(){
	var txt_value = $(this).val();
	$(this).css({"border":"1px solid #d5d5d5","box-shadow":"none"});
	if(txt_value == ''){
		$(this).val(this.defaultValue);
		$(this).css("color","#d5d5d5");
		}
	})

//登录页复选框效果//注册页复选框//个人中心充值复选框//确认投资复选框
$("#autolog>b").click(function(){
	var $checkname=$(this).hasClass("selected");
	if($checkname){
		$(this).removeClass("selected");
	}else{
		$(this).addClass("selected");
	}
})

/*注册页协议*/
$("#regchk").click(function(){
	var $checkname=$(this).children("b").hasClass("selected");
	if($checkname){
		$(this).children("b").removeClass("selected");
		$(this).parent().children(".errorxy").css("display","block");
	}else{
		$(this).children("b").addClass("selected");
		$(this).parent().children(".errorxy").css("display","none");
	}
})
/*投资协议*/
$("#invest-chk").click(function(){
	var $checkname=$(this).children("b").hasClass("selected");
	if($checkname){
		$(this).children("b").removeClass("selected");
		$(this).parent().children(".errorxy").css("display","block");
	}else{
		$(this).children("b").addClass("selected");
		$(this).parent().children(".errorxy").css("display","none");
	}
})

/*充值协议*/
$("#bank-check>b").click(function(){
	var $checkname=$(this).hasClass("selected");
	if($checkname){
		$(this).removeClass("selected");
		$(this).parent().children(".error").css("display","block");
	}else{
		$(this).addClass("selected");
		$(this).parent().children(".error").css("display","none");
	}
})
$("#bank-check2>b").click(function(){
	var $checkname=$(this).hasClass("selected");
	if($checkname){
		$(this).removeClass("selected");
		showSpan('alert-tyht');
	}else{
		$(this).addClass("selected");
	}
})

	/*个人中心充值文本框交互框关键词*/
$('.pay-txt,.pay-money-txt,.deposite-txt').focus(function(){
	var txt_value = $(this).val();
	$(this).css({"border":"1px solid #0caffe","box-shadow":"none"});
	if(txt_value == this.defaultValue){
		$(this).val('');
		}
	})	
$('.pay-txt,.pay-money-txt,.deposite-txt').keydown(function(){
	$(this).css("color","#4B4948");
	})
$('.pay-txt,.pay-money-txt,.deposite-txt').blur(function(){
	var txt_value = $(this).val();
	$(this).css({"border":"1px solid #d5d5d5","box-shadow":"none"});
	if(txt_value == ''){
		$(this).val(this.defaultValue);
		}
	})
	
	
/*债权详细页*/
$('.invest-txt').focus(function(){
	var txt_value = $(this).val();
	if(txt_value == this.defaultValue){
		$(this).val('');
		}
	})	
$('.invest-txt').keydown(function(){
	$(this).css("color","#4B4948");
	})
$('.invest-txt').blur(function(){
	var txt_value = $(this).val();
	if(txt_value == ''){
		$(this).val(this.defaultValue);
		$(this).css("color","#d5d5d5");
		}
	})
	
/*债权列表 导航交互*/
$("#credit-nav li").click(function(){
	var arr=$('<i class="arrow"></i>');
	$(this).siblings('li').removeClass("on").children().remove();
	$(this).addClass('on').append(arr);
  })
		
/*债权详情页 tab切换 */
$("#credit-dleft2tab>ul>li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var index=$(this).index();
	    $("#credit-dleft2conlist>div").eq(index).show().siblings().hide();
	})
	
	
	
	

/*个人中心-左侧菜单*/
$("#personal-left li").mouseover(function () {
	if ($(this).attr('class') != "pleft-cur") {
		if (!$(this).children("dl").children("dt").hasClass("on")) //优化左侧效果
		{
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
			var dot = $(this).children("span").children("a").children("i").attr('class');
			if (dot) {
				if ($(this).children("span").children("a").children("i").attr('class').indexOf('dot0') >= 0) {
					$(this).children("span").children("a").children("i").removeClass("dot0" + dot.substring(8));
					$(this).children("span").children("a").children("i").addClass("dot" + dot.substring(8));
				} else {
					$(this).children("span").children("a").children("i").addClass("dot0" + dot.substring(8));
					$(this).children("span").children("a").children("i").removeClass("dot" + dot.substring(8));
				}
			}

		}
	}

});
$("#personal-left li").mouseout(function () {
	if ($(this).attr('class') != "pleft-cur") {
		$(this).siblings("li").removeClass("on");
		$(this).removeClass("on");
		var dot = $(this).children("span").children("a").children("i").attr('class');
		if (dot) {
			if ($(this).children("span").children("a").children("i").attr('class').indexOf('dot0') < 0) {
				$(this).children("span").children("a").children("i").removeClass("dot" + dot.substring(7));
				$(this).children("span").children("a").children("i").addClass("dot0" + dot.substring(7));
			} else {
				$(this).children("span").children("a").children("i").addClass("dot" + dot.substring(7));
				$(this).children("span").children("a").children("i").removeClass("dot0" + dot.substring(7));
			}
		}
	}
});


	
//关于我们左侧导航
$("#about-nav dd").mouseover(function(){
		if($(this).attr('class')=="aboutcur"){
			$(this).siblings().children("a").removeClass("abouton");
		}
		else{
			$(this).children("a").addClass("abouton");
			var index=$(this).index();
			$(this).children("a").children("i").removeClass("icon"+index).addClass("icon0"+index);
			}
})	
		
$("#about-nav dd").mouseout(function(){
	if($(this).attr('class')!="aboutcur"){
			$(this).children("a").removeClass("abouton");
			var index=$(this).index();
			$(this).children("a").children("i").removeClass("icon0"+index).addClass("icon"+index);
	}		
})
	
	
//个人中心--充值银行 选择其他银行
$("#pay-bank .pay-other").click(function(){
	if($(this).children(".paytxt").text()=="选择其他银行卡"){
	  $(this).children(".paytxt").text("隐藏其他银行卡");
	  $(this).children("i").removeClass("paydown");
	  $(this).children("i").addClass("payup");
	  $("#pay-bank").children("ul").css("height","auto");
	  return false;
	  }
	if($(this).children(".paytxt").text()=="隐藏其他银行卡"){
	  $(this).children(".paytxt").text("选择其他银行卡");
	  $(this).children("i").removeClass("payup");
	  $(this).children("i").addClass("paydown");
	  $("#pay-bank").children("ul").css("height","150px");
	  return false;
	}
})
	

/*个人中心-usesr的icon*/
$("#kyye").hover(function(){
	$(".arrow-show").css("display","block");
	$(".icon-show").css("display","block");
	},function(){
	$(".arrow-show").css("display","none");
	$(".icon-show").css("display","none");
	})
$("#zhze").hover(function(){
	$(".arrow-show1").css("display","block");
	$(".icon-show1").css("display","block");
	},function(){
	$(".arrow-show1").css("display","none");
	$(".icon-show1").css("display","none");
	})
$("#dsbx").hover(function(){
	$(".arrow-show2").css("display","block");
	$(".icon-show2").css("display","block");
	},function(){
	$(".arrow-show2").css("display","none");
	$(".icon-show2").css("display","none");
	})
$("#ljsy").hover(function(){
	$(".arrow-show3").css("display","block");
	$(".icon-show3").css("display","block");
	},function(){
	$(".arrow-show3").css("display","none");
	$(".icon-show3").css("display","none");
	})
	
/*账户设置中提醒icon*/
$("#zhwzd").hover(function(){
	$(".arrow-personal").css("display","block");
	$(".icon-personal").css("display","block");
	},function(){
	$(".arrow-personal").css("display","none");
	$(".icon-personal").css("display","none");
	})
	
/*个人中心-提现提现费用*/
$("#txfy").hover(function(){
	$(".txarrow-show").css("display","block");
	$(".txicon-show").css("display","block");
	},function(){
	$(".txarrow-show").css("display","none");
	$(".txicon-show").css("display","none");
	})
	
/*个人中心-提现 到账金额*/
$("#dzje").hover(function(){
	$(".dzarrow-show").css("display","block");
	$(".dzicon-show").css("display","block");
	},function(){
	$(".dzarrow-show").css("display","none");
	$(".dzicon-show").css("display","none");
	})
	
	
/*个人中心-回款计划悬浮*/
$(".pmain-arrow").hover(function(){
	$(this).addClass("pr");
	$(".arrow-show-li").css("display","block");
	$(".icon-show-li").css("display","block");
	},function(){
	$(this).removeClass("pr");
	$(".arrow-show-li").css("display","none");
	$(".icon-show-li").css("display","none");
	})
	
/*个人中心-tab切换*/
$("#pmain-contab li").click(function(){
	var index=$(this).index();
	if(index!=3){
	 	$(this).addClass("on").siblings().removeClass("on");
		$("#pmain-conmain>div").eq(index).show().siblings().hide();
	}
})


/*个人中心--主页头像*/
$("#head-img").hover(function(){
			$(this).children(".head-txt").css("display","block");
		},function(){
		$(this).children(".head-txt").css("display","none");
		})
		
/*个人中心--回款计划--筛选时间*/		
$("#datebox").click(function(e){
	var $span=$("#select-date span").text();
	$(this).children("ul").toggle();
	if($(this).children('ul').css('display') == 'block'){
		$(this).find('span').addClass('select-title-bcolor');
	}else{
		$(this).find('span').removeClass('select-title-bcolor');
	}
	e.stopPropagation(); 
	$("#datebox1").find('span').removeClass('select-title-bcolor');
	$("#datebox1").children('ul').css("display","none");
	});	
$("#datebox li").click(function(){
		var $liname=$(this).text();
		$("#datebox span").text($liname);
		var $livalue=$(this).attr("value");
		$("#form\\:filtersDate").val($livalue);
	});
$(document).click(function(e) { 
	$("#datebox ul").hide();
	$("#datebox1 ul").hide();
	$("#typeboxstyle ul").hide();
	$("#select-date ul").hide();
	// e.startPropagation(); 
}); 

/* 自动投标*/ 
$("#datebox1").click(function(e){
	var $span=$("#select-date span").text();
	$(this).children("ul").toggle();
	if($(this).children('ul').css('display') == 'block'){
		$(this).find('span').addClass('select-title-bcolor');
	}else{
		$(this).find('span').removeClass('select-title-bcolor');
	}
	e.stopPropagation(); 
	$("#datebox").find('span').removeClass('select-title-bcolor');
	$("#datebox").children('ul').css("display","none");
	});
$("#datebox1 li").click(function(){
		var $liname=$(this).text();
		$("#datebox1 span").text($liname);
	});
	
		
/*个人中心--资金记录下拉列表--操作类型*/
$("#typeboxstyle").click(function(e){
	var $span=$("#select-date span").text();
	$(this).children("ul").toggle();
	if($(this).children('ul').css('display') == 'block'){
		$(this).find('span').addClass('select-title-bcolor');
	}else{
		$(this).find('span').removeClass('select-title-bcolor');
	}
	e.stopPropagation(); 
	$("#select-date").find('span').removeClass('select-title-bcolor');
	$("#select-date").children('ul').css("display","none");
		
	})	
$("#typeboxstyle li").click(function(){
		var $liname=$(this).text();
		$("#typeboxstyle span").text($liname);
		var $livalue=$(this).attr("value");
		$("#form\\:filtersType").val($livalue);
	})
	
/*个人中心--资金记录下拉列表--查询时间*/
$("#select-date").click(function(e){
	var $span=$("#select-date span").text();
	$(this).children("ul").toggle();	
	if($(this).children('ul').css('display') == 'block'){
		$(this).find('span').addClass('select-title-bcolor');
	}else{
		$(this).find('span').removeClass('select-title-bcolor');
	}
	e.stopPropagation(); 
	$("#typeboxstyle").find('span').removeClass('select-title-bcolor');
	$("#typeboxstyle").children('ul').css("display","none");
	
		
	})	
$("#select-date li").click(function(){
		var $liname=$(this).text();
		$("#select-date span").text($liname);
		var $livalue=$(this).attr("value");
		$("#form\\:filtersDate").val($livalue);
	})

	
/*确认投资下拉框*/
$("#activebox").click(function(){
	var $span=$("#activebox span").text();
	$(this).children("ul").toggle();
	if($(this).children('ul').css('display') == 'block'){
		$(this).find('span').addClass('active-bColor');
	}else{
		$(this).find('span').removeClass('active-bColor');
	}
	})	
$("#activebox li").click(function(){
		var $liname=$(this).text();
		$("#activebox span").text($liname);
})	

	
/*关于我们-招贤纳士  关于我们-理财帮助*/
$(".about-zxns>ul>li>span,.about-help>ul>li span").click(function(){
	var zxnscon=$(this).next(".zxns-con");
	if(zxnscon.css("display")=="none"){
		$(this).removeClass("zxns-tit").addClass("zxns-titdown");
			zxnscon.slideDown(300);
			$(this).parent().siblings("li").children(".zxns-con").slideUp(300);
			$(this).parent("li").siblings().children(".zxns-titdown").removeClass("zxns-titdown").addClass("zxns-tit");
		}else{
			$(this).removeClass("zxns-titdown").addClass("zxns-tit");
			zxnscon.slideUp(300);
			
		}
})



/*理财帮助*/
$("#about-tab a").click(function(){
 	$(this).addClass("on").siblings().removeClass("on");
	var index=$(this).index();
	$("#about-help>div").eq(index).show().siblings().hide();
 })
		 
		 
//密码找回页 ---  单选按钮点击事件 面板切换
$("#type-radio a").click(function(){
	var index=$(this).index();
	$(this).siblings().removeClass("selected");
	$(this).addClass("selected");
	if(index==0){
		$("#pwd-mail").css("display","none");
		$("#pwd-phone").css("display","block");
		$("#form\\:mobile").val("请输入手机号码");
		$("#form\\:mobile").css("color","#999");
		}
		if(index==1){
		$("#pwd-phone").css("display","none");
		$("#pwd-mail").css("display","block");
		$("#form\\:email").val("请输入邮箱号码");
		$("#form\\:email").css("color","#999");
		}
		$("#inputIdCardShow").css('display','none');
		$("#form\\:idCard").val("身份证后四位");
		$("#form\\:idCard").css("color","#999");
	})

})

//返回顶部 优化
function b() {
	h = $(document).height()-$(window).height(),//整个页面的高度-当前浏览器所能看到的页面
	t=$(document).scrollTop(),//滚动到顶部的距离
	t >= h/2 ? $("#backTop").fadeIn() : $("#backTop").fadeOut();

}
$(function() {
	b(),
	$("#backTop").click(function() {
		var backtimer=setInterval(function(){
        var osTop=document.documentElement.scrollTop || document.body.scrollTop;
        var ispeed=Math.floor(-osTop/10);
		document.documentElement.scrollTop=document.body.scrollTop=osTop+ispeed;
		isTop=true;
		if(osTop==0){
			clearInterval(backtimer);
		}
		},10);
		
		
	})
}),
$(window).scroll(function() {
	b();
});

$(function(){
	$('.suspend-btn-3,.suspend-btn-4').hover(function(){
		$(this).find('span').show();
	},function(){
		$(this).find('span').hide();
	})
})

//网站公告 文字向上滚动效果  20150319
function autoScroll(obj){  
	$(obj).find("ul").animate({  
		marginTop : "-51px"  
	},800,function(){  
		$(this).css({marginTop : "0px"}).find("li:first").appendTo(this);  
	})  
}  
$(function(){  
	var iTime = null;
	iTime = setInterval('autoScroll(".notice-list")',3500)

	$('.notice-list').hover(function(){
		clearInterval(iTime);
	},function(){
		iTime = setInterval('autoScroll(".notice-list")',3500)
	})
}) 
//验证输入字符串的长度是否合法（包括汉字的验证）
function validateInputLength(value,exceptValue,chineseLength,charLength,minLength,maxLength)
{
	if(value==exceptValue)
	{
		return true;
	}
	var len=value.length;
	var regex="^[\u4e00-\u9fa5]$";
	var valuePattern = new RegExp(regex);
	var valueFlag=null;
	var charNumber=0;
	var chineseNumber=0;
	for(i=0;i<len;i=i+1)
	{
		valueFlag=valuePattern.test(value[i]);
		if(valueFlag)
		{
			chineseNumber+=1;
		}
		else
		{
			charNumber+=1;
		}
	}
	var valueLength=chineseLength*chineseNumber+charLength*charNumber;
	if(valueLength<minLength||valueLength>maxLength)
	{
		return false;
	}
	else
	{
		return true;
	}
}


//倒计时函数
function countDown(showid,count,url){
	timerNoCisabled(showid, {
		"count" : count,
		"animate" : false,
		initTextBefore : "",
		initTextAfter : "",
		callback:function(){window.location.href=url;}
	}).begin();
}
