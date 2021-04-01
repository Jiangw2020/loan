$(function() {
	;(function() {
		var $album = $("#album"),
		$btnPrev = $(".btn-prev", $album),
		$btnNext = $(".btn-next", $album),
		$thumb = $("#albumThumb"),
		$albumShow = $(".album-show", $album);
		if(!$album.length) return false;
		$thumb.jCarouselLite({
			btnNext: ".btn-next",
			btnPrev: ".btn-prev",
			scroll: 1,
			speed: 240,
			circular: false,
			visible: 5
		});
		ED.loadImage($("img", $albumShow).attr("src"),function() {
			$(".loading", $album).hide()
		});
		
		$(".small_img").click(function() {
				var self = $(this);
				$(".loading", $album).show();
				$(this).parent().addClass("active").siblings().removeClass("active");
				ED.loadImage(self.attr("id"),function() {
					$("img", $albumShow).attr({
						src: self.attr("id")
					});
					$(".loading", $album).hide()
				});
				return false
		})
		
	})();
	

	
	
});

