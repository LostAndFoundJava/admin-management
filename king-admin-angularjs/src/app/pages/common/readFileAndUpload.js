function readMainImg($this, id) {
	
	if($($this)[0].files.length == 0){
		return;
	}
	
	var file = $($this)[0].files[0];
	if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	var url = getObjectURL(file);
		
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		var time = new Date().getTime();
		$("#zhutuMainImg").remove();
		$("#" + id)
				.append(
						'<a id="zhutuMainImg" ><img  src="'
								+ this.result
								+ '" alt="" name="mainCourseImg" style="height: 100%;width: 100%;"/></a>');
	}

}

function readCarouselImgs($this, id) {
	var file = $($this)[0].files[0];
	if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		var time = new Date().getTime();
		$("#carouselCourseImgs").append('<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px; float:left;border:0px;" id="'
								+ $this.id + '"></div>');
		$("div#" + $this.id).append('<img src="'
								+ this.result
								+ '" alt="" style="height: 100%;width: 100%;"/> <button type="button"  class="btn red btn-xs" style="width: 100%;border-radius: 0px" onclick=removeImg(\''
								+ $this.id + '\')>删除');
		$(".carouselImgs").hide();
		$("#carouselCourseInputs").append(
				'<input type="file" name="carouselImgs" class="carouselImgs" id="img'
						+ time + '"  onchange="readCarouselImgs(this,\'img'
						+ time + '\')" file-model="carouselImgs"/>');

	}

}

function removeImg(imgId) {
	$("#" + imgId).remove();
	$("#" + imgId).remove();
}


function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}