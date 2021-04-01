$(function(){
	$('#wx').hover(function(){
		$('#weixin_xlgz').show();
	},function(){
		$('#weixin_xlgz').hide();
	});	
});

$(function(){
		$(".czmf_mouse").mouseenter(function(){
			$(".c_3_po").show();
		});
		$(".czmf_mouse").mouseleave(function(){
			$(".c_3_po").hide();
		});
	});

// 幻灯片插件
;(function($) {
	$.fn.eSlide = function(options) {
		var defaults = {
			time: 6000,
			autoPlay: false,
			start: 0,
			curClass: "current",
			delay: 500
		}
		var opts = $.extend({}, defaults, options);
		var t = $(this);
		return this.each(function() {
			var $container = $(this).find(".slide-container");
			var $item = $container.find("ul").children();
			var timer, total = $item.size(),index = opts.start;
			var html = '<div class="wrap"><div class="pagination"><ul></ul></div></div>';
			$item.eq(opts.start).addClass(opts.curClass);
			function createElement() {
				$(html).appendTo(t);
				$item.each(function(i) {
					t.find(".pagination ul").append('<li><a href="javascript:;">'+(i+1)+'</a></li>')
				})
			};
			total > 1 ? createElement() : !0;
			$pagination = t.find(".pagination");
			$pagination.find("li").eq(opts.start).addClass(opts.curClass);
			function play() {
				$item.eq(index).fadeOut(opts.delay).next().fadeIn(opts.delay);
				index++;
				if (index == total) {
					index = 0;
					goto(index)
				}
				$pagination.find("li").eq(index).addClass("current").siblings().removeClass("current")
			}
			function goto(i) {
				$item.eq(i).fadeIn(opts.delay).siblings().fadeOut(opts.delay)
			}
			$pagination.find("li").on("click",function() {
				$(this).addClass("current").siblings().removeClass("current");
				goto($(this).index());
				index = $(this).index()
			});
			if (opts.autoPlay && total > 1) {
				timer = setInterval(play, opts.time);
				t.hover(function() {
				clearInterval(timer)
				},function() {
				  timer = setInterval(play, opts.time)
			})
			}
			
		})
	}
})(jQuery);

//输入框提示插件
; (function($) {
	$.fn.placeholder = function(color) {
		var color = color || "#ababab";
		if ('placeholder' in document.createElement('input')) {
			return false
		}
		return this.each(function(i, obj) {
			var pid = (this.name || this.id) + "_pid_" + i;
			var t = $(this);
			var form = $(this).closest("form");
			var className = t.attr("class");
			var width = t.css("width");
			var placeholderText = t.attr("placeholder");
			t.before("<input type='text' id=" + pid + " class='" + className + "' value='" + placeholderText + "' style='width:"+width+"'>");
			$("#" + pid).hide().css("color", color);
			if (this.value == "") {
				t.hide();
				$("#" + pid).show()
			}
			$("#" + pid).focus(function(e) {
				$(this).off("blur");
				$(this).unbind("blur");
				t.show();
				$(this).hide();
				t.click();
				t.focus()
			});
			t.blur(function() {
				if (this.value == "") {
					t.hide();
					$("#" + pid).show()
				}
			})
		})
	}
})(jQuery);
$(function(){
	$('[placeholder]').placeholder();  
 });
 
 
//图片滚动插件 
;(function($){
	$.fn.eScroll = function(options){
		var defaults = {
			autoPlay : false,
			container : ".container",
			nextPrev : false,
			time : 3000
			}
		var opts = $.extend({}, defaults, options);
		return this.each(function(a){
			var $this = $(this);
			var index = 1;
			var ul = $this.find("ul");
			var li = ul.children();
			var w = ul.find("li").outerWidth(true);
			var crrent = index = 0;
			li.clone().appendTo(ul);
			var lisize = ul.find("li").size();
			ul.width(w * lisize);
			if(opts.nextPrev){
				$this.append('<a href="javascript:;" class="prev"></a><a href="javascript:;" class="next"></a>');
				}
			var $next = $this.find(".next");
			var $prev = $this.find(".prev");
			function next(){
				if(index >= lisize / 2){
					ul.css("left", "0px");
					index = 0;
					}
					index++;
					ul.animate({left:-w*index},500);
					
				}
			function prev(){
				if(index <= 0){
					ul.css("left", -ul.width() / 2);
					index = lisize / 2;
					}
					index--;
					ul.animate({left:-index*w},500);
					
				}
			$next.on("click",function(){
				next()
				});
			$prev.on("click",function(){
				prev()
				});
			if(opts.autoPlay){
				var timer = setInterval(function(){
					next()
					}, opts.time);
				$this.hover(function(){
				  clearInterval(timer)
				},function(){
				  timer = setInterval(next, opts.time);
				})
				}
			})
		}
})(jQuery);

/*tab切换*/
$(document).delegate(".ui-tab", "mouseenter",function() {
    $(this).delegate(".ui-tab-nav li","mouseover",function(){
		var $icon = $(this).closest(".ui-tab-nav").find(".icon-cur");
		if($icon.length){
		    var left = $(this).position().left + $(this).outerWidth() / 2 - 17; 
	        $icon.stop(true,false).animate({left:left});
			};
	    $(this).addClass("active").siblings('li').removeClass("active");
        $(this).closest(".ui-tab").find(".ui-tab-item").eq($(this).closest('.ui-tab-nav').find('li').index(this)).show().siblings(".ui-tab-item").hide()
	})
}),

/*模拟下拉框*/
$(document).delegate(".ui-select", "mouseenter",function(){ 
	var input = $("input[type=hidden]",this),
	    sleHd = $(".select-value",this),
	    list = $(".select-list",this);
	$(this).delegate(".select-value","click",function(){
		$(this).parent().parent().css("zIndex","9");
		$(this).parent().addClass("active")
		list.is(":hidden") ? list.show() : !0;
		}),
	$(this).delegate(".select-list li","click",function(){
		sleHd.find("span").text($(this).text()).css("color","#333");
		$(this).css("background-color","#ebf5ff").siblings().css("background-color","");
		input.val($(this).attr("data-val"));
		list.hide();
	})
}),
$(document).delegate(".ui-select","mouseleave",function(){
	$(this).parent().css("zIndex","");
	$(this).removeClass("active");
	$(".select-list",this).hide();
}),
/*下拉菜单*/
$(function(){
	var $menu = $(".ui-menu");
	$menu.hover(function(){
		var $icon = $(this).find(".icon-down");
		$(this).css({zIndex:"99"}).find(".ui-menu-list").show();
		$icon.length && !!$.fn.rotate && $icon.rotate({animateTo: 180});
	},function(){
		$(this).css({zIndex:""}).find(".ui-menu-list").hide();
		var $icon = $(this).find(".icon-down");
		$icon.length && !!$.fn.rotate && $(this).find(".icon-down").rotate({animateTo:0});
	})
})

//二维码
function showQrcode(ti,img){
	var d = dialog({
		 cancelValue: "关闭",
		 quickClose: true,
		 fixed: true,
		 title: ti,
		 width: 260,
		 content: '<div style="text-align:center"><img src="'+img+'"></div>' 
});
    d.show();
}

//图片加载完成
function loadImage(url, callback) {
    var img = new Image();
    img.src = url;
    if (img.complete) {
        callback.call(img);
        return;
    }
    img.onload = function () {
        callback.call(img);
    };
};

function copyToClipboard(txt) {
	if (window.clipboardData) {
		window.clipboardData.clearData();
		clipboardData.setData("Text", txt);
		alert("复制成功！");

	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
		
	} else if (navigator.userAgent.indexOf("Chrome") != -1) {
		alert("你的浏览器不支持复制，请使用Ctrl+C或鼠标右键。")
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor("text/unicode");
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode", str, copytext.length * 2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans, null, clipid.kGlobalClipboard);
		alert("复制成功！");
	}
}

