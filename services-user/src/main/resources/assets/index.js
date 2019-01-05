var $m_ver_menu=$("#m_header_menu")
$(window).on("load", function () {
    // 加载loading
    /*setTimeout(function () {
        $(".page-loader").fadeOut();
    }, 500)*/
}), $(document).ready(function () {
    //使用递归处理菜单
	var str = '';
	var forTree = function (o,parentId) {
		//alert(parentId==0)
        for (var i = 0; i < o.length; i++) {
            try {
            	//顶级菜单
            	if(o[i]["type"]==0){
            		if(parentId==0&&o[i]["parent_id"]==0){
                		str +='<li id="id_menu_'+o[i]["id"]+'" class="m-menu__item m-menu__item--submenu m-menu__item--rel m-menu__item--open-dropdown" m-menu-submenu-toggle="click" aria-haspopup="true">';
                		str +='<a  href="javascript:;" class="m-menu__link m-menu__toggle">';
                		str +='<span class="m-menu__item-here"></span><span class="m-menu__link-text">'+o[i]["menu_name"]+'</span>'
                		str +='<i class="m-menu__hor-arrow la la-angle-down"></i><i class="m-menu__ver-arrow la la-angle-right"></i></a>';
                		str +='<div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left"><span class="m-menu__arrow m-menu__arrow--adjust"></span><ul class="m-menu__subnav">';
                		forTree(o,o[i]["id"])
                		str +='</ul></div>';
                	}
                	if(parentId!=0&&o[i]["parent_id"]==parentId){
                		str +='<li class="m-menu__item "  aria-haspopup="true">';
                		str +='<a  href="'+ctx+o[i]["url"]+'"   class="m-menu__link ">';
                		str +='<i class="m-menu__link-icon '+o[i]["icon"]+'"></i>';
                		str +='<span class="m-menu__link-text">'+o[i]["menu_name"]+'</span></a></li>';
                	}
            	}
            } catch (e) {
                console.log(e);
            }
        }
        return str;
    };
	
    $.get(ctx+"menu/authinfo", {}, function (r) {
        if (r.code ==="SUCCESS") {
        	$m_ver_menu.find(".m-menu__nav").append(forTree(r.data,0));
        	for (var i = 0; i < r.data.length; i++) {
        		if(location.href.indexOf(r.data[i]["url"])!=-1){
        			$("#id_menu_"+r.data[i]["parent_id"]).addClass("m-menu__item--active");
        			break;
        		}
        	}
        } else {
            swal("error!", r.msg, "error")
        }
    })

});

//日期格式化
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}