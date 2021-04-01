var ED = ED || {};
ED.loadjs = function(file){
    var head = $('head').remove('#loadscript');
    $("<scri"+"pt>"+"</scr"+"ipt>").attr({src:file,type:'text/javascript',id:'load'}).appendTo(head);
};
ED.slide = function(options) {
	var defaults = {
		elem: null,
		time: 6000,
		autoPlay: false,
		start: 0,
		curClass: "current",
		delay: 500
	};
	var opts = $.extend({},defaults, options);
	$(opts.elem).each(function(i) {
		var $this = $(this),
		    $container = $(this).find(".slide-container"),
		    $item = $container.find("ul").children(),
		    timer,total = $item.size(),
			index = opts.start,
		    lis = "",
		    html = '<div class="wrap"><div class="pagination"><ul></ul></div></div>';
		$item.eq(opts.start).addClass(opts.curClass);
		function createElement() {
			$(html).appendTo($this);
			$item.each(function(i) {
				lis += '<li><a href="javascript:;">' + (i + 1) + '</a></li>'
			});
			$(".pagination ul").append(lis)
		}
		total > 1 ? createElement() : !0;
		$pagination = $this.find(".pagination");
		$pagination.find("li").eq(opts.start).addClass(opts.curClass);
		function play() {
			$item.eq(index).fadeOut(opts.delay).next().fadeIn(opts.delay);
			index++;
			if (index == total) {
				index = 0;
				go(index);
			}
			$pagination.find("li").eq(index).addClass("current").siblings().removeClass("current")
		}
		function go(i) {
			$item.eq(i).fadeIn(opts.delay).siblings().fadeOut(opts.delay)
		}
		$pagination.find("li").on("click",
		function() {
			$(this).addClass("current").siblings().removeClass("current");
			go($(this).index());
			index = $(this).index()
		});
		if (opts.autoPlay && total > 1) {
			timer = setInterval(play, opts.time);
			$this.hover(function() {
				clearInterval(timer)
			},
			function() {
				timer = setInterval(play, opts.time)
			})
		}
	})
};
ED.placeholder = function(options) {
	var defaults = {
		elem: null,
		color: '#ababab'
	};
	var opts = $.extend({},defaults, options);
	if ('placeholder' in document.createElement('input')) {
		return false;
	}
	$(opts.elem).each(function(i, obj) {
		var $this = $(this),
		    pid = ($this.attr("name") || $this.attr("id")) + "_pid_" + i,
		    form = $(this).closest("form"),
		    className = $this.attr("class"),
		    width = $this.css("width"),
		    placeholderText = $this.attr("placeholder");
		$this.after("<input type='text' id=" + pid + " class='" + className + "' value='" + placeholderText + "' style='width:" + width + "'>");
		$("#" + pid).hide().css("color", opts.color);
		if (this.value == "") {
			$this.hide();
			$("#" + pid).show()
		}
		$("#" + pid).focus(function(e) {
			$(this).off("blur");
			$(this).unbind("blur");
			$this.show();
			$(this).hide();
			$this.click();
			$this.focus()
		});
		$this.blur(function() {
			if (this.value == "") {
				$this.hide();
				$("#" + pid).show()
			}
		})
	})
};
ED.imgScroll = function(options) {
	var defaults = {
		elem: null,
		autoPlay: false,
		container: ".container",
		nextPrev: false,
		time: 3000
	}
	var opts = $.extend({},
	defaults, options);
	$(opts.elem).each(function(i) {
		var $this = $(this);
		var index = 1;
		var ul = $this.find("ul");
		var li = ul.children();
		var w = ul.find("li").outerWidth(true);
		var crrent = index = 0;
		li.clone().appendTo(ul);
		var lisize = ul.find("li").size();
		ul.width(w * lisize);
		if (opts.nextPrev) {
			$this.append('<a href="javascript:;" class="prev"></a><a href="javascript:;" class="next"></a>')
		}
		var $next = $this.find(".next");
		var $prev = $this.find(".prev");
		function next() {
			if (index >= lisize / 2) {
				ul.css("left", "0px");
				index = 0
			}
			index++;
			ul.animate({
				left: -w * index
			},
			500)
		}
		function prev() {
			if (index <= 0) {
				ul.css("left", -ul.width() / 2);
				index = lisize / 2
			}
			index--;
			ul.animate({
				left: -index * w
			},
			500)
		}
		$next.on("click",
		function() {
			next()
		});
		$prev.on("click",
		function() {
			prev()
		});
		if (opts.autoPlay) {
			var timer = setInterval(function() {
				next()
			},
			opts.time);
			$this.hover(function() {
				clearInterval(timer)
			},
			function() {
				timer = setInterval(next, opts.time)
			})
		}
	})
};
ED.tabSwith = function(options) {
	var defaults = {
		elem: ".ui-tab",
		menu: ".ui-tab-nav",
		item: ".ui-tab-item",
		event: "mouseover",
		active: "active"
	};
	var opts = $.extend({},
	defaults, options);
	$(document).delegate(opts.menu + " li", opts.event,
	function() {
		var $icon = $(this).closest(opts.menu).find(".icon-cur");
		if ($icon.length) {
			var left = $(this).position().left + $(this).outerWidth() / 2 - 17;
			$icon.stop(true, false).animate({
				left: left
			})
		};
		$(this).addClass(opts.active).siblings('li').removeClass(opts.active);
		$(this).closest(opts.elem).find(opts.item).eq($(this).closest(opts.menu).find('li').index(this)).show().siblings(opts.item).hide();
	})
};
ED.simSelect = function(options) {
	var defaults = {
		elem: ".ui-select"
	};
	var opts = $.extend({},defaults, options);
	$(document).delegate(opts.elem, "mouseenter",
	function() {
		var input = $("input[type=hidden]", this),
		sleHd = $(".select-value", this),
		list = $(".select-list", this);
		$(this).delegate(".select-value", "click",
		function() {
			$(this).parent().parent().css("zIndex", "9");
			$(this).parent().addClass("active");
			list.is(":hidden") ? list.show() : !0
		}),
		$(this).delegate(".select-list li", "click",function() {
			sleHd.find("span").text($(this).text()).css("color", "#333");
			$(this).css("background-color", "#ebf5ff").siblings().css("background-color", "");
			input.val($(this).attr("data-val"));
			list.hide();
		})
	});
	$(document).delegate(opts.elem, "mouseleave",function() {
		$(this).parent().css("zIndex", "");
		$(this).removeClass("active");
		$(".select-list", this).hide()
	})
};
ED.showQrcode = function(ti, img) {
	var d = dialog({
		cancelValue: "关闭",
		quickClose: true,
		fixed: true,
		title: ti,
		width: 260,
		content: '<div style="text-align:center"><img src="' + img + '"></div>'
	});
	d.show();
};
ED.loadImage = function(url, callback) {
	var img = new Image();
	img.src = url;
	if (img.complete) {
		callback.call(img);
		return
	}
	img.onload = function() {
		callback.call(img)
	}
};
ED.drawCircle = function(options) {
	var defaults = {
		elem: null,
		size: 0,
		width: 60,
		border: 5,
		delay: 500,
		color: ["#e9e9e9", "#87c50f"],
		animate: true
	};
	var opts = $.extend({},
	defaults, options);
	$(opts.elem).each(function(i) {
		if (typeof Raphael == "undefined") return false
		var rad = opts.width / 2 - opts.border,
		border = opts.border,
		width = opts.width,
		paper = Raphael($(this)[0], width, width),
		size = opts.animate ? 0.01 : opts.size,
		color = opts.color,
		self = $(this),
		flag = true,
		maskCircle,
		drawCircle;
		paper.customAttributes.arc = function(value, total, R) {
			var alpha = 360 / total * value,
			a = (90 - alpha) * Math.PI / 180,
			x = width / 2 + R * Math.cos(a),
			y = width / 2 - R * Math.sin(a),
			path;
			if (total == value) {
				path = [["M", width / 2, width / 2 - R], ["A", R, R, 0, 1, 1, width / 2 - 0.02, width / 2 - R]]
			} else {
				path = [["M", width / 2, width / 2 - R], ["A", R, R, 0, +(alpha > 180), 1, x, y]]
			}
			return {
				path: path
			}
		};
		maskCircle = paper.path().attr({
			arc: [100, 100, rad],
			'stroke-width': border,
			'stroke': color[0]
		});
		if (opts.size > 0.01) {
			drawCircle = paper.path().attr({
				arc: [size, 100, rad],
				'stroke-width': border,
				'stroke': color[1],
				'cursor': "pointer"
			});
			if (opts.animate) {
				if (self.offset().top < $(window).height()) {
					drawCircle.animate({
						arc: [opts.size, 100, rad]
					},
					opts.delay)
				}
				$(window).scroll(function() {
					var scrollTop = $(document).scrollTop();
					var height = $(window).height();
					if (scrollTop + height > self.offset().top && flag) {
						drawCircle.animate({
							arc: [opts.size, 100, rad]
						},
						opts.delay);
						flag = false
					}
				})
			}
		}
	})
};
ED.calculator = function(obj, default_apr, tender_id, account) {
	var content = '<div class="inner"><div class="text">根据<a href="http://bbs.edai.com/forum.php?mod=viewthread&tid=3235&extra=page%3D1" class="c-blue" target="_blank">《易贷网管理费优惠方案》</a>，纯收益率因管理费率不同而不同，不同管理费率对应的纯收益率可通过</div><div class="tit"><span class="fl">下面选择框计算：</span><a href="/?user" class="c-blue fr"  target="_blank">查询您的管理费率>></a></div><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><th>管理费率</th><th>纯收益率</th></tr><tr><td>';
	content += '<select onchange="ED.changeServerApr(this.value,' + tender_id + ',' + account + ');"><option value=0>免管理费</option>';
	for (var i = 1; i < 11; i++) {
		content += '<option value=' + i + '>' + i + '%</option>'
	}
	content += '</select></td><td id="show_apr_td">' + default_apr + '%</td></tr></table></div>';
	var d = dialog({
		title: "承接人纯收益率计算器",
		content: content,
		width: 380,
		skin: "dialog-calculator",
		quickClose: true
	});
	d.show(obj);
	if($(".invest-table").length > 0){
		$(obj).closest(".item").css("background","#f5f9fd").siblings().removeAttr("style");
        //$('div:empty').on('click',function(){
        //    $(".item").removeAttr("style");
        //});
		}
};
ED.sideMenu = function(){
	var e = {
		elem: {
			window: $(window),
			document: $(document),
			menu: ".side-menu",
			contact: ".contact",
			returnTop: ".return",
			share: ".share",
			target: "[data-target='sideMenu']",
			foot: ".footer"
		},
		init: function() {
			this.scrollEvent();
			this.clickEvent();
			this.hoverEvent();
		},
		hoverEvent: function() {
			$(this.elem.menu).find("li").hover(function() {
				$(".icon-w", this).hide();
				$(".text", this).stop(true, false).fadeIn()
			},
			function() {
				$(".icon-w", this).stop(true, false).fadeIn();
				$(".text", this).hide()
			})
		},
		scrollEvent: function() {
			if (!$(this.elem.target).length > 0 || !$(this.elem.foot).length) return false;
			var $w = this.elem.window,
			$m = $(this.elem.menu),
			self = this,
			dom = this.elem.document,
			mtop,
			scrollTop,
			ftop = $(this.elem.foot).offset().top - $m.outerHeight() - 20,
			mtop = $(this.elem.target).offset().top,
			timer,
			handler,
			iheight,
			delay = 5;
			$(".uc-menu").length > 0 || $(".uc-statement").length > 0 ? $(this.elem.contact,$m).hide() : !0;
			iheight = $(".uc-menu").length > 0 || $(".uc-statement").length > 0 ? 138 : 194;
			function fix() {
				if ($w.width() < 1312) {
					$m.show()
				} else {
					$m.css({
						left: ($w.width() - 1200) / 2 + 1210,
						display: "block",
						right: 'auto'
					});
					if (dom.scrollTop() >= ($(".footer").offset().top - $w.height() - 30)) {
						$m.css({
							position: "absolute",
							top: ($(".footer").offset().top - iheight)
						})
					} else {
						$m.css({
							position: "fixed",
							top: "auto"
						})
					}
				}
			}
			fix();
			$w.resize(function() {
				if ($w.width() < 1312) {
					$m.css({
						right: 0,
						left: 'auto'
					})
				} else {
					$m.css({
						left: ($w.width() - 1200) / 2 + 1210,
						right: 'auto'
					})
				}
			});
			handler = function() {
				timer = 0;
				scrollTop = dom.scrollTop();
				if (scrollTop >= ($(".footer").offset().top - $w.height() - 30) && $w.width() > 1312) {
					$m.css({
						position: "absolute",
						top: ($(".footer").offset().top - iheight)
					})
				}
				else {
					$m.css({
						position: "fixed",
						top: "auto"
					})
				}
			};
			$w.scroll(function() {
				if (timer) {
					clearTimeout(timer);
					timer = 0;
				}
				timer = setTimeout(handler, delay);
			});
		},
		clickEvent: function() {
			var $m = this.elem.menu;
			$(this.elem.returnTop, $m).on("click",function() {
				$('body,html').animate({
					scrollTop: 0
				});
				return false
			});
			$(this.elem.contact, $m).hover(function(event) {
				$(".contact-box", $m).show()
			},function() {
				$(".contact-box", $m).hide()
			});
			$(".contact-box", $m).hover(function() {
				$(this).show()
			},function() {
				$(this).hide()
			})
		}
	}
	return e.init()
};
ED.jumpBBS = function() {
	var bbs_url = $("#bbs_href").attr('href');
	var bbs_key = $("#bbs_href").attr('rel');
	var url = '';
	if (bbs_key) {
		url = bbs_url + '?key=' + bbs_key
	} else {
		url = bbs_url
	}
	$("#bbs_href").attr('href', url)
};
ED.changeServerApr = function(changelv, change_id, account) {
	$.ajax({
		type: 'POST',
		url: '/transfer/frontchange/',
		dataType: "html",
		data: 'id=' + change_id + '&change_price=' + account + '&changelv=' + changelv,
		async: false,
		success: function(msg) {
			if (msg == 0) {
				$("#show_apr_td").html("债权已还完！<br>请 <a href='/transfer/index.html' class='c-blue'>刷新</a>  页面")
			} else {
				$("#show_apr_td").html(msg + '%')
			}
		}
	})
}
ED.init = function() {
	this.placeholder({
		elem: '[placeholder]'
	});
	this.sideMenu();
	this.simSelect();
}
$(function() {
	var $menu = $(".ui-menu");
	$menu.hover(function() {
		var $icon = $(this).find(".icon-down");
		$(this).css({
			zIndex: "99"
		}).find(".ui-menu-list").show();
		$icon.length && !!$.fn.rotate && $icon.rotate({
			animateTo: 180
		})
	},function() {
		$(this).css({
			zIndex: ""
		}).find(".ui-menu-list").hide();
		var $icon = $(this).find(".icon-down");
		$icon.length && !!$.fn.rotate && $(this).find(".icon-down").rotate({
			animateTo: 0
		})
	});
	ED.init();
	$(".circle").each(function(i) {
		var size = $(".total", this).text().replace(/\%/, '');
		var $this = $(this);
		ED.drawCircle({
			elem: $this,
			size: size
		})
	});
	$(".nav>ul>li").hover(function() {
		!! $.fn.rotate && $(this).find(".icon-down").rotate({
			animateTo: 180
		});
		$("ul", this).show();
		$(this).css({
			zIndex: "9"
		})
	},function() { 
	!! $.fn.rotate && $(this).find(".icon-down").rotate({
			animateTo: 0
		});
		$("ul", this).hide();
		$(this).css({
			zIndex: ""
		})
	});
	$(".invest-table .item").hover(function() {
		$(this).addClass("odd")},function() {
		$(this).removeClass("odd")
	});
	$(".section-receivables tr:gt(0)").hover(function() {
		$(this).addClass("odd")},function() {
		$(this).removeClass("odd")
	});
	$(".invest-list .invest-table .item").hover(function() {
		$(this).addClass("hover")
	},function() {
		$(this).removeClass("hover")
	});
	$(".page-select").on("click",function(event) {
		event.stopPropagation();
		event.preventDefault();
		$(this).addClass("active");
		$("dd", this).show()
	});
	$(".page-select dd a").on("click",function(event) {
		event.stopPropagation();
		event.preventDefault();
		$(".page-select dt span").text($(this).text());
		$(".page-select dd").hide();
		location.href = $(this).attr('href')
	});
	$(document).on("click",function() {
		$(".page-select").removeClass("active");
		$(".page-select dd").hide()
	});
	$(".item-detail-body .hd .icon").each(function(i) {
		$(this).css({
			"margin-left": -$(this).outerWidth() / 2
		})
	});
	$(".site-nav #app").hover(function() {
		$(this).addClass("hover");
		$(".app-con", this).show();
	},function() {
		$(".app-con", this).hide();
		$(this).removeClass("hover");
	})
});