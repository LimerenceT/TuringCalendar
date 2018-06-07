document.write("<script src='/static/js/jquery.min.js'><\/script>");
window.onload=function(){
	var list=document.getElementById('list');
	var lis=list.children;

	function currentTime(){
		var date = new Date();
		var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        m=m>9?m:'0'+m;
        d=d>9?d:'0'+d;
        h=h>9?h:'0'+h;
        mi=mi>9?mi:'0'+mi;
        return y+'-'+m+'-'+d+' '+h+':'+mi;
	}
	// 代理功能
	for(var i=0;i<lis.length;i++){
		var textArea = lis[i].getElementsByClassName('comment')[0];
		lis[i].onclick=function(event){
			event=event||window.event;
			var el=event.srcElement;
			if(el.className === 'btn') {
                reply(el);
			}

		}
		//评论按键事件
        textArea.onkeyup = function () {
            var val = this.value;
            var len = val.length;
            var els = this.parentNode.children;
            var btn = els[1];
            var word = els[2];
            if (len <=0 || len > 140) {
                btn.className = 'btn btn-off';
            }
            else {
                btn.className = 'btn';
            }
            word.innerHTML = len + '/140';
        }
        //评论获取焦点
        textArea.onfocus = function () {
            this.parentNode.className = 'text-box text-box-on';
            this.value = this.value == '评论......' ? '' : this.value;
            this.onkeyup();
        }
        //评论失去焦点
        textArea.onblur = function () {
            var me = this;
            var val = me.value;
            if (val == '') {
                me.value='评论......';
                me.parentNode.className='text-box';
            }
        }
	}

	//回复评论功能
	function reply(node){
		var name = document.getElementById("button-1");
		if (name != null) {
            var username = name.value.slice(5);
			var week = document.getElementById('week').value;
            var box = node.parentNode.parentNode.parentNode;
            var commentList = box.getElementsByClassName('comment-list')[0];
            var textarea = box.getElementsByClassName('comment')[0];
            var commentBox = document.createElement('div');
            var content = textarea.value;

            commentBox.className = 'comment-box clearfix';
            commentBox.innerHTML =
                '<div class="comment-content" style="margin-top: 20px; margin-left: 30px">' +
                '<p class="comment-text"><span class="user">'+ username +'：</span>' + content + '</p>' +
                '<p class="comment-time">' + currentTime() + '<a href="javascript:;" class="comment-praise";' +

                commentList.appendChild(commentBox);
            textarea.value = '';
            textarea.parentNode.className = 'text-box';

            $.ajax({
                type: 'POST',
                url: 'comment.do',
                data: {'username': username, 'content': content, 'time': currentTime(), 'week': week},
                success: null,
                dataType: 'json'
            });

        }else {
            alert("请先登录");
		}
	}
}


function isLogin(login_info, upOrDown,  week, username,num) {
    var upState = (document.getElementById('up').value === 'true');
    var downState = (document.getElementById('down').value === 'true');

    if (login_info==="登录"){
        alert("请先登录")
    }
    else if (upState){
        alert("你已经点过赞，暂时不能改变")
    }
    else if (downState){
        alert("你已经踩过了，暂时不能改变")
    }
    else {
        $.post(upOrDown+".do", { week: week, name: username});
        if (upOrDown==='up'){
            document.getElementById('ups').className= 'fas fa-thumbs-up';
            document.getElementById('ups').innerText="("+num+")";
            document.getElementById('up').value='true';
        } else {
            document.getElementById('downs').className= 'fas fa-thumbs-down';
            document.getElementById('downs').innerText="("+num+")";
            document.getElementById('down').value='true';
        }

    }
}