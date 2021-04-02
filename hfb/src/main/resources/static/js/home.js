// JavaScript Document

$(function(){
	
	//lazyload
	$("img.lazy").show().lazyload({effect : "fadeIn"});
	
	
	$("img.lazy_focus").show().lazyload({event:"lazy_focus", effect : "fadeIn"});
	$('.focus_list li:eq(0) img').trigger("lazy_focus").addClass("complete");
	 
		   
	//通栏伸缩广告
	var _tslide;
	function timesl(){
		var _img = $('.w_ads_slide_bg').find('img').attr('src');
		if($.trim(_img)){$('.w_ads_bg').hide()}
		_tslide = setTimeout(function(){
			$('.w_ads_slide').slideUp(1500,'easeOutQuad',function(){
				$('.w_ads_slide_bg').hide();
				$('.w_ads_bg').slideDown(1000);										
			});
		},5000);
	}
	timesl();
	$('.w_ads_slide_bg').hover(function(){
		if(_tslide){ clearTimeout(_tslide); }									
	},function(){
		timesl();	
	});
		   
	//轮播动画
	var _AdList = $('.focus_box .focus_list');
	var _listWidth = $('.focus_box .focus_list li:eq(0)').width();
	var _listCount = $('.focus_box .focus_list li').length;
	var _nav = $('.focus_box .focus_nav a');
	var _AdWarp = $('.focus_box');
	var _speed = 500;
	var _wait = 4500;
	var _sue = 0;
	_AdList.css({'width':_listCount*_listWidth});
	var scrollAd = function(sue, speed){
		_AdList.stop().animate({left:-_listWidth*sue}, speed, 'easeInOutQuad');
		_nav.removeClass('oncur').eq(sue).addClass('oncur');
		lazy_focus(sue);
	}
	//scrollAd();
	var scrollAdRun = setInterval(function(){
			_sue++;
			if(_sue == _listCount)_sue = 0;
			scrollAd(_sue, _speed);
		},
	_wait+_speed);
	
	var lazy_focus = function (s) {
		if(!$('.focus_list li:eq('+s+') img').hasClass("complete")){
			$('.focus_list li:eq('+s+') img').trigger("lazy_focus").addClass("complete");
		}
	}

	_AdWarp.live('mouseleave mouseenter', function(e){
		var type = e.type;
		if(type == 'mouseenter'){
			clearInterval(scrollAdRun);
		}else if(type == 'mouseleave'){
			scrollAdRun = setInterval(function(){
				_sue++;
				if(_sue == _listCount)_sue = 0;
				scrollAd(_sue, _speed);
			}, _wait+_speed);
		}
	})
	.delegate('p.focus_nav a', 'mouseenter', function(e){
		_sue = $(e.target).index();
		var type = e.type;
		_AdList.stop().animate({left:-_listWidth*_sue}, _speed, 'easeInOutQuad');
		_nav.removeClass('oncur').eq(_sue).addClass('oncur');
		lazy_focus(_sue);
		
	});
	
	
	
	
	
	
	//滚动公告
	var i = 0;
	var tlen = $('.notice_box ul li').length;
	var noticeShow;
	if(tlen > 1){
		noticeShow = setInterval(function(){
			i++; if(i == tlen){ i = 0; }
			$('.notice_box ul').animate({ top:-30*i },500);
		},5000);
		$('.notice_box ul li').hover(function(){
			if(noticeShow){ clearInterval(noticeShow); }									  
		},function(){
			noticeShow = setInterval(function(){
				i++; if(i == tlen){ i = 0; }
				$('.notice_box ul').animate({ top:-30*i },500);
			},5000);	
		});
	}
	
	//mini三点惠切换效果
	var j = 0;
	var _arr = $('.mini_qiang .q_list a.arr');
	var _len = $('.mini_qiang .q_list ul:visible li').length;
	_arr.hover(function(){
		$(this).stop().animate({opacity:1},500);					
	},function(){
		$(this).stop().animate({opacity:0.5},500);		
	});
	function goodSlide(j){
		$('.mini_qiang .q_list ul:visible').stop().animate({left:-200*j},300);
	}
	_arr.click(function(){
		var id = this.id;
		if(id == 'arr_l'){ j--; if(j < 0) j = _len-1;
		}else{ j++; if(j > _len-1) j = 0; }
		goodSlide(j);
	});
	
	//三点惠时间段切换效果
	var _timecur = $('.mini_qiang:visible .q_time a');
	var _glist = $('.mini_qiang:visible .q_list ul');
	_timecur.hover(function(){
		var _n = _timecur.index(this);
		$(this).addClass('oncur').siblings().removeClass('oncur');
		_glist.eq(_n).show().siblings('ul').hide();
	});
	
	//周末清仓倒计时
	var _time;
	function _tC(_time){
		__d = parseInt( _time/3600/24 );
		__h = parseInt( (_time/3600)%24 );
		__m = parseInt( (_time/60)%60 );
		__s = parseInt( _time%60 );
		_timebox.find('.day').html(__d);
		_timebox.find('.hour').html(__h);
		_timebox.find('.min').html(__m);
		_timebox.find('.sec').html(__s);
		if( _time == '0' || _time == '5' ){
			return false;
		}
	}
	var _stime = $('.index_surplus').val();
	var _timebox = $('.funny_day .q_time');
	if(_stime == 'undefined' || _stime == ''){
		return;	
	}else{
		_time = _stime;	
		var _timeCount = setInterval(function(){_tC(_time); _time--},1000 );
	}
	
	//升降价提醒效果
	var _parr = $('.goods_list .okhqb_goods li .pro_pr a.pr_status');
	_parr.hover(function(){
		var _cpr = Number($('.pr_count',this).val());	
		var _cprs = Math.abs(_cpr);
		$('.pr_info',this).stop().fadeIn(200);
		if(_cpr > 0){
			$('.pr_info',this).find('i.sta').html('上涨');
			$('.pr_info',this).find('i.price').html(_cprs);
		}
		if(_cpr < 0){
			$('.pr_info',this).find('i.sta').html('下跌').show();
			$('.pr_info',this).find('i.price').html(_cprs);
		}
	},function(){
		$('.pr_info',this).stop().fadeOut(200);	
	});
	
	//特价配件切换效果
	var k = 0;
	var _arr = $('.fitting_hot_list a.arr');
	var _fitlen = $('.fitting_hot_list ul li').length;
	$('.fitting_hot_list ul').css("width",_fitlen*200);
	_arr.hover(function(){
		$(this).stop().animate({opacity:1},500);					
	},function(){
		$(this).stop().animate({opacity:0.5},500);		
	});
	function fittingSlide(k){
		$('.fitting_hot_list ul').stop().animate({left:-200*k},300);
	}
	_arr.click(function(){
		var id = this.id;
		if(id == 'arr_l'){ k--; if(k < 0) k = _fitlen-1;
		}else{ k++; if(k > _fitlen-1) k = 0; }
		fittingSlide(k);
	});
	
	//phone recharge
	function phone_recharge(){
		
		var _am = $('select[name="phone_recharge_amount"]').val() || 100;
		var _phoneRechargeSellingPrice = $('.phoneRechargeSellingPrice');
		var tips_nav = $('#homeRecharge .tips_nav');
		$('select[name="phone_recharge_amount"]').after('<span style="padding-left:8px; color:#ff0000; font-size:12px; display:none;" class="s_tips_nav"></span>');
				
		$.get(
			'http://www.okhqb.com/detail/findGoodsVirtualMaxAndMinPrice.json?amount=' + _am + '.00',
			function( result ){
				if( result.code == 200 && result.data != null ){
					_phoneRechargeSellingPrice.html( result.data.retailMinPrice + '-' + result.data.retailMaxPrice + '元' );
				}
			}
		);
		
		$('input[name="phone_recharge_mobile"]').blur(function(){
			var _mobile = $('input[name="phone_recharge_mobile"]').val(),
				_amount = $('select[name="phone_recharge_amount"]').val();
				_mobile = _mobile.replace(/(^\s*)|(\s*$)/g, '');
			
			if( _mobile.length < 1 ){
				tips_nav.html('请填写充值号码').show();
				$(this).attr('is_mobile_licit', 0);
				$(this).attr('s', 0);
				$('#homeRecharge .s_tips_nav').html('').hide();
				return;
			}else if( _mobile.length != 11 ){
				tips_nav.html('号码必须是11位').show();
				$(this).attr('is_mobile_licit', 0);
				$(this).attr('s', 0);
				$('#homeRecharge .s_tips_nav').html('').hide();
				return;
			}else if( ! (/^1[3|5|8][0-9]\d{8}$/.test( _mobile )) ){
				tips_nav.html('格式不正确').show();
				$(this).attr('is_mobile_licit', 0);
				$(this).attr('s', 0);
				$('#homeRecharge .s_tips_nav').html('').hide();
				return;
			}else{
				tips_nav.css('color', '#999999').html('').hide();
				$.ajax({
					url: 'http://my.okhqb.com/my/mobileItemInfo.json?mobile=' + _mobile + '&amount=' + _amount + '&callback=?',
					success: function( result ){
						if( result.code == 200 && result.data != null ){
							tips_nav.html( result.data.provider + '(' + result.data.area + ')' ).show();
							_phoneRechargeSellingPrice.html( result.data.retailPrice ).show();
							$('input[name="phone_recharge_mobile"]').attr('is_mobile_licit', 1);
							$('input[name="phone_recharge_mobile"]').attr('s', 1);
							$('#homeRecharge .s_tips_nav').html('').hide();
						}else{
							//tips_nav.css('color', '#ff0000').html( '暂不支持该号码' ).show();
							$('#homeRecharge .s_tips_nav').html('暂不支持该面值').show();
							$('input[name="phone_recharge_mobile"]').attr('is_mobile_licit', 0);
							$('input[name="phone_recharge_mobile"]').attr('s', 0);
						}					
					},
					dataType: 'jsonp',
					jsonp: 'callback'
				});
			}		
		});
		
		$('select[name="phone_recharge_amount"]').live('change', function(){
			
			var _mobile = $('input[name="phone_recharge_mobile"]').val(),
			    _amount = $(this).val();
			var is_mobile_licit =  $('input[name="phone_recharge_mobile"]').attr('is_mobile_licit');
			
			if( is_mobile_licit == 1 ){

				$.ajax({
					url: 'http://my.okhqb.com/my/mobileItemInfo.json?mobile=' + _mobile + '&amount=' + _amount + '&callback=?',
					jsonp: true,
					success: function( result ){
						if( result.code == 200 && result.data != null ){
							var _s = Number( $('input[name="phone_recharge_mobile"]').attr('s') );
							if( !_s ){
								tips_nav.css('color', '#999999').html('').hide();
							}
							_phoneRechargeSellingPrice.html( result.data.retailPrice ).show();
							$('input[name="phone_recharge_mobile"]').attr('s', 1);
							$('#homeRecharge .s_tips_nav').html('').hide();
							$('input[name="phone_recharge_mobile"]').blur();
						}else{
							$('#homeRecharge .s_tips_nav').html( '暂不支持该面值' ).show();
						}					
					},
					dataType: 'jsonp',
					jsonp: 'callback'
				});
			}else{
				var _s = Number( $('input[name="phone_recharge_mobile"]').attr('s') );
				if( !_s ){
					tips_nav.css('color', '#999999').html('').hide();
				}
				$.get(
					'http://www.okhqb.com/detail/findGoodsVirtualMaxAndMinPrice.json?amount=' + _amount + '.00',
					function( result ){
						if( result.code == 200 && result.data != null ){
							_phoneRechargeSellingPrice.html( result.data.retailMinPrice + '-' + result.data.retailMaxPrice + '元' );
							$('input[name="phone_recharge_mobile"]').attr('s', 1);
							$('#homeRecharge .s_tips_nav').html('').hide();
							$('input[name="phone_recharge_mobile"]').blur();
						}
					}
				);			
			}
			
		});
		
		$('#home_rechar_submit').live('click', function(){
		
			var _mobile = $('input[name="phone_recharge_mobile"]').val(),
			    _amount = $('select[name="phone_recharge_amount"]').val();
				_mobile = _mobile.replace(/(^\s*)|(\s*$)/g, '');
			
			if( _mobile.length < 1 ){
				tips_nav.html('请填写充值号码').show();
				return;
			}else if( _mobile.length != 11 ){
				tips_nav.html('号码必须是11位').show();
				return;
			}else if( ! (/^1[3|5|8][0-9]\d{8}$/.test( _mobile )) ){
				tips_nav.html('格式不正确').show();
				return;
			}else{
				window.location.href = 'http://my.okhqb.com/my/recharge.html?mobile=' + _mobile + '&amount=' + _amount;
			}
				
		});	
	}
	
	/* phone_recharge(); 去掉手机充值 */
	
	
	//今天特卖加购物车
	$('.okhqb_hot li a.to_cart').live('click', function(){
		var $this = $(this);
		var quantity = 1;
		var skuId = $this.attr('data-sku-id');
		var cartStr = '<form id="formToCart" method="post" name="formToCart" action="http://buy.okhqb.com/buy/cart.html" style="display:none"><input type="hidden" name="quantity" value="'+quantity+'"><input type="hidden" name="skuId" value="'+skuId+'"></form>';
		$('body').append(cartStr);
		$('#formToCart').submit();
	})
	
	
	
	//friend link
	$('.f_link').hover(function(){
		$('.flink_hover').show();
	},function(){ 
		$('.flink_hover').hide(); 
	});
	/*$('.f_link').click(function(){
		  if($('.flink_hover').is(':visible')){
			    $('.flink_hover').hide();
			  }else{
				$('.flink_hover').show();  
			  }
	});*/
	$('.flink_hover').hover(function(){
		$(this).show();	
	},function(){ 
		$(this).hide(); 
	});
	
});