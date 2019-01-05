var DatatableRemoteAjaxDemo = {
    init: function() {
        var t;
        t = $(".m_datatable").mDatatable({
            data: {
                type: "remote",
                source: {
                    read: {
                        url: "/user/list",
                        map: function(t) {
                            var e = t;
                            return void 0 !== t.data && (e = t.data),e
                        }
                    }
                },
                pageSize: 10,
                serverPaging: !0,
                serverFiltering: !0,
                serverSorting: !0
            },
            layout: {
                scroll: !1,
                footer: !1
            },
            sortable: !0,
            pagination: !0,
            toolbar: {
                items: {
                    pagination: {
                        pageSizeSelect: [10, 20, 30, 50, 100]
                    }
                }
            },
            search: {
                input: $("#generalSearch")
            },
            columns: [{
                field: "ID",
                title: "#",
                width: 20,
                sortable: !1,
                textAlign: "center",
                selector: {
                    class: "m-checkbox--solid m-checkbox--brand"
                }
            },
            {
                field: "id",
                title: "ID",
                width: 20,
                textAlign: "center"
            },
            {
                field: "username",
                title: "用户名",
                width: 50,
                textAlign: "center"
            },
            {
                field: "city",
                title: "登陆城市",
                width: 60
            },
            {
                field: "mobile",
                title: "电话"
            },
            {
                field: "role",
                title: "角色",
                width: 60
            },
            {
                field: "email",
                title: "邮箱"
            },
            {
                field: "ctime",
                title: "创建时间",
                width: 100,
                template: function(row) {
                	return row.ctime.time;
                } 
            },
            {
                field: "wname",
                title: "创建人",
                width: 60
            },
            {
                field: "status",
                title: "状态" ,
                width: 50,
                template: function(e) {
                    var a = {
                        0 : {
                            title: "激活",
                            class: "m-badge--success"
                        },
                        1 : {
                            title: "禁用",
                            class: " m-badge--danger"
                        }
                    };
                    return '<span class="m-badge ' + a[e.status].class + ' m-badge--wide">' + a[e.status].title + "</span>"
                } 
            },
            {
                field: "Actions",
                width: 110,
                title: "操作",
                sortable: !1,
                overflow: "visible",
                template: function(e, a, i) {
                    var rt='<a href="#" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">							<i class="la la-edit"></i>						</a><a href="#" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">							<i class="la la-trash"></i>						</a>';
                	if(e.status==0){
                		return '<a href="#" class="m-portlet__nav-link btn m-btn m-btn--hover-warning m-btn--icon m-btn--icon-only m-btn--pill" title="禁用">							<i class="la la-remove"></i>						</a>'+rt;
                	}else{
                		return '<a href="#" class="m-portlet__nav-link btn m-btn m-btn--hover-warning m-btn--icon m-btn--icon-only m-btn--pill" title="激活">							<i class="la la-check"></i>						</a>'+rt;
                	}
                }
            }]
        }),
        $("#m_form_status").on("change",
        function() {
            t.search($(this).val(), "Status")
        }),
        $("#m_form_type").on("change",
        function() {
            t.search($(this).val(), "Type")
        }),
        $("#m_form_status, #m_form_type").selectpicker()
    }
};
jQuery(document).ready(function() {
    DatatableRemoteAjaxDemo.init()
});