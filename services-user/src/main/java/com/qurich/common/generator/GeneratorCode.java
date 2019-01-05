package com.qurich.common.generator;

import com.qurich.common.utils.FileUtil;
import com.qurich.common.utils.StringCharUtil;
import com.qurich.mapper.GeneratorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * 代码生成器
 */
@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeneratorCode {
	
	@Autowired
	private GeneratorMapper generatorMapper;
	
	private String table="t_user";
	private String modelPath="com.qurich.model";
	private String mapperPath="com.qurich.mapper";
	private String servicePath="com.qurich.service";
	private String controllerPath="com.qurich.controller";
	
	@Test
	public void generator() {
		String fileName=table.substring(table.indexOf("_")+1, table.length());
		List<Map> tableList=generatorMapper.listTableColumn(table);
		String upper=StringCharUtil.upperCaseFirstChar(fileName);
		model(tableList, fileName,upper);
		mapper(tableList, fileName,upper);
		service(tableList, fileName,upper);
		//controller(tableList, fileName,upper);
		//页面
		//page(tableList, fileName,upper);
	}
	
	private void page(List<Map> tableList,String fileName,String upper) {
		String path=GeneratorCode.class.getClassLoader().getResource(controllerPath.replaceAll("\\.", "/")).getPath();
		path=path.replaceAll("target/classes", "src/main/resources");
		path=path.substring(0,path.lastIndexOf("main/resources"))+"main/resources/templates";
		StringBuffer sb=new StringBuffer();
		sb.append("<#include \"/common/page_above.ftl\"/>\n");
		sb.append("<div class=\"m-portlet m-portlet--mobile \">\n");
		sb.append("<div class=\"m-portlet__head\">\r\n" + 
				"		<div class=\"m-portlet__head-caption\">\r\n" + 
				"			<div class=\"m-portlet__head-title\">\r\n" + 
				"				<h3 class=\"m-portlet__head-text\">\r\n" + 
				"					"+upper+"管理\r\n" + 
				"				</h3>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\n");
		sb.append("<div class=\"m-portlet__body\">\n");
		sb.append("<div class=\"m-form m-form--label-align-right m--margin-top-20 m--margin-bottom-30\">\n");
		sb.append("<div id=\"search-condition\" class=\"row align-items-center\">\r\n" + 
				"				<div class=\"col-xl-12 order-2 order-xl-1\">\r\n" + 
				"					<div class=\"form-group m-form__group row\">\r\n" + 
				"						<div class=\"col-lg-3\">\r\n" + 
				"							<label class=\"col-form-label col-lg-12\">id</label>\r\n" + 
				"							<div class=\"col-xl-12\">\r\n" + 
				"								<input name=\"id\" type=\"text\" class=\"form-control m-input\"\r\n" + 
				"										placeholder=\"id\">\r\n" + 
				"							</div>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"form-group m-form__group row\">\r\n" + 
				"						<div class=\"col-xl-12 order-1 order-xl-2 m--align-right\">\r\n" + 
				"							<a href=\"javascript:;\" id=\"b_t_search\" class=\"btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill\">\r\n" + 
				"								<span> <i class=\"la la-search\"></i> <span>搜索 </span>\r\n" + 
				"							</span>\r\n" + 
				"							</a>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</div></div>");
		sb.append("<!--begin: Datatable -->\r\n" + 
				"	<table class=\"m_datatable\" id=\""+fileName+"_datatable\" width=\"100%\">\r\n" + 
				"		\r\n" + 
				"	</table>\r\n" + 
				"	<!--end: Datatable -->");
		sb.append("</div>\r\n" + 
				"</div>");
		sb.append("<script type=\"text/javascript\">\n");

		sb.append("var "+fileName+"Data = {\r\n" +
				"	    init: function() {\r\n" +
				"	    	var t;\r\n" +
				"	        t = $(\"#"+fileName+"_datatable\").mDatatable({\r\n" +
				"	        	data: {\r\n" +
				"	                type: \"remote\",\r\n" +
				"	                source: {\r\n" +
				"	                    read: {\r\n" +
				"	                        url: \"/"+fileName+"/list/ajax\",\r\n" +
				"	                        map: function(t) {\r\n" +
				"	                            var e = t;\r\n" +
				"	                            return void 0 !== t.data && (e = t.data),e\r\n" +
				"	                        }\r\n" +
				"	                    }\r\n" +
				"	                },\r\n" +
				"	                pageSize: 10,\r\n" +
				"	                serverPaging: !0,\r\n" +
				"	                serverFiltering: !0,\r\n" +
				"	                serverSorting: !0\r\n" +
				"	            },\r\n" +
				"	            layout: {\r\n" +
				"	                scroll: !1,\r\n" +
				"	                footer: !1\r\n" +
				"	            },\r\n" +
				"	            sortable: !0,\r\n" +
				"	            pagination: !0,\r\n" +
				"	            toolbar: {\r\n" +
				"	                items: {\r\n" +
				"	                    pagination: {\r\n" +
				"	                        pageSizeSelect: [10, 20, 30, 50, 100]\r\n" +
				"	                    }\r\n" +
				"	                }\r\n" +
				"	            },");
		sb.append("columns: [{\r\n" + 
				"	                field: \"ID\",\r\n" + 
				"	                title: \"#\",\r\n" + 
				"	                width: 20,\r\n" + 
				"	                sortable: !1,\r\n" + 
				"	                textAlign: \"center\",\r\n" + 
				"	                selector: {\r\n" + 
				"	                    class: \"m-checkbox--solid m-checkbox--brand\"\r\n" + 
				"	                }\r\n" + 
				"	            }");
		sb.append(",\r\n" + 
				"	            {\r\n" + 
				"	                field: \"Actions\",\r\n" + 
				"	                width: 110,\r\n" + 
				"	                title: \"操作\",\r\n" + 
				"	                sortable: !1,\r\n" + 
				"	                overflow: \"visible\",\r\n" + 
				"	                template: function(e, a, i) {\r\n" + 
				"	                    return '<a href=\"/"+fileName+"/page?id='+e.id+'\"  class=\"m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill\" title=\"编辑\">							<i class=\"la la-edit\"></i>						</a><a href=\"#\" class=\"m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill\" uid=\"'+e.id+'\" title=\"删除\">							<i class=\"la la-trash\"></i>						</a>';\r\n" + 
				"	                }\r\n" + 
				"	            }");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			String comment=(String)map.get("COLUMN_COMMENT");
			sb.append(",\r\n" + 
					"	            {\r\n" + 
					"	                field: \""+column+"\",\r\n" + 
					"	                title: \""+comment+"\",\r\n" + 
					"	                textAlign: \"center\"\r\n" + 
					"	            }");
		}
		sb.append("]\r\n" + 
				"	        })");
		sb.append(",$(\"#"+fileName+"_datatable\").on(\"click\",\".m-btn--hover-danger\",function(e) {\r\n" + 
				"    	    	var id=$(this).attr(\"uid\");\r\n" + 
				"    	    	swal({\r\n" + 
				"    	             title: \"警告\",\r\n" + 
				"    	             text: \"是否删除!\",\r\n" + 
				"    	             icon: \"success\",\r\n" + 
				"    	             confirmButtonText: \"<span><i class='la la-check'></i><span>确定</span></span>\",\r\n" + 
				"    	             confirmButtonClass: \"btn btn-danger m-btn m-btn--pill m-btn--air m-btn--icon\",\r\n" + 
				"    	             showCancelButton: !0,\r\n" + 
				"    	             cancelButtonText: \"<span><i class='la la-remove'></i><span>取消</span></span>\",\r\n" + 
				"    	             cancelButtonClass: \"btn btn-secondary m-btn m-btn--pill m-btn--icon\"\r\n" + 
				"    	         }).then(function(e) {\r\n" + 
				"    	        	 if(e.value){\r\n" + 
				"    	        		 $.post(\"/"+fileName+"/remove\", {id:id}, function (r) {\r\n" + 
				"    	        			 	if (r.code ===\"SUCCESS\") {\r\n" + 
				"    	        			 		swal(\"成功!\", r.msg, \"success\")\r\n" + 
				"    	        			 	}else{\r\n" + 
				"    	        			 		swal(\"失败!\", r.msg, \"error\")\r\n" + 
				"    	        			 	}\r\n" + 
				"    	        			 	t.reload()\r\n" + 
				"    	        		 });  \r\n" + 
				"    	        	 }\r\n" + 
				"    	         })	\r\n" + 
				"	        })");
		sb.append(",$(\"#b_t_search\").click(function(){\r\n" + 
				"	        	t.search({\"id\":$(\"input[name='id']\").val()\r\n" + 
				"	        		})\r\n" + 
				"	        })");
		sb.append("}\r\n" + 
				"	};\r\n" + 
				"	jQuery(document).ready(function() {\r\n" + 
				"	    "+fileName+"Data.init()\r\n" + 
				"	});");
		sb.append("</script>\r\n" + 
				"<#include \"/common/page_under.ftl\"/>");
		//结束
		try {
			FileUtil.saveFile(path+"/"+fileName+"/list.ftl", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
	}
	
	private void controller(List<Map> tableList,String fileName,String upper) {
		String path=GeneratorCode.class.getClassLoader().getResource(controllerPath.replaceAll("\\.", "/")).getPath();
		path=path.replaceAll("target/classes", "src/main/java");
		StringBuffer sb=new StringBuffer();
		String beanService=fileName+"Service";
		sb.append("package "+controllerPath+";\n");
		sb.append("import javax.servlet.http.HttpServletRequest;\r\n" + 
				"import javax.servlet.http.HttpServletResponse;\r\n" + 
				"\r\n" + 
				"import org.apache.shiro.authz.annotation.RequiresPermissions;\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Controller;\r\n" + 
				"import org.springframework.ui.Model;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMethod;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestParam;\r\n" + 
				"import org.springframework.web.bind.annotation.ResponseBody;\r\n");
		sb.append("import com.qurich.common.utils.Result;\r\n\r\n");
		sb.append("@Controller\n");
		sb.append("@RequestMapping(\""+fileName+"\")\n");
		sb.append("public class "+upper+"Controller extends BaseController{\n");
		sb.append("\t@Autowired\n");
		sb.append("\tprivate "+upper+"Service "+beanService+";\n\n");
		
		//list页面
		sb.append("\t@RequestMapping(value=\"/list\", method=RequestMethod.GET,produces = \"application/json; charset=utf-8\")\n");
		sb.append("\tpublic String list( Model model) {\n");
		sb.append("\t\treturn "+beanService+".listPage(model);\n");
		sb.append("\t}\n\n");
		
		//list数据ajax请求
		sb.append("\t//ajax请求list数据\n");
		sb.append("\t@RequestMapping(value=\"/list/ajax\", method=RequestMethod.POST,produces = \"application/json; charset=utf-8\")\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic String listAjax(\n");
		sb.append("\t\t@RequestParam(value=\"pagination[page]\", required=false, defaultValue=\"1\")int page,\n");
		sb.append("\t\t@RequestParam(value=\"pagination[perpage]\", required=false, defaultValue=\"10\")int perpage,\n");
		sb.append("\t\t@RequestParam(value=\"sort[sort]\", required=false, defaultValue=\"desc\")String sort,\n");
		sb.append("\t\t@RequestParam(value=\"sort[field]\", required=false, defaultValue=\"id\")String field,\n");
		sb.append("\t\t@RequestParam(value=\"query[undefined][id]\", required=false, defaultValue=\"null\")Integer id,\n");
		sb.append("\t\tHttpServletRequest request, HttpServletResponse response, Model model) {\n");
		sb.append("\t\t"+upper+" bean = new "+upper+"();\n");
		sb.append("\t\tbean.setId(id);\n");
		sb.append("\t\treturn "+beanService+".list(page,perpage,bean,sort,field,model);\n");
		sb.append("\t}\n\n");
		
		//跳转新增or修改页面
		sb.append("\t//跳转新增or修改页面\n");
		sb.append("\t@RequestMapping(value=\"/page\", method=RequestMethod.GET,produces = \"application/json; charset=utf-8\")\n");
		sb.append("\tpublic String addOrUpdatePage(@RequestParam(value=\"id\", required=false, defaultValue=\"0\")int id,\n");
		sb.append("\t\tHttpServletRequest request, HttpServletResponse response, Model model) {\n");
		sb.append("\t\treturn "+beanService+".addOrUpdate(id,request,model);\n");
		sb.append("\t}\n\n");				
		
		//新增or修改
		sb.append("\t//添加（id==0）或者修改(id!=0)\n");
		sb.append("\t@RequestMapping(value=\"/save\", method=RequestMethod.POST,produces = \"application/json; charset=utf-8\")\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Result save(User user,\n");
		sb.append("\t\tHttpServletRequest request, HttpServletResponse response, Model model) {\n");
		sb.append("\t\treturn "+beanService+".save(user,request,model);\n");
		sb.append("\t}\n\n");
		
		//删除
		sb.append("\t//删除\n");
		sb.append("\t@RequestMapping(value=\"/remove\", method=RequestMethod.POST,produces = \"application/json; charset=utf-8\")\n");
		sb.append("\t@ResponseBody\n");
		sb.append("\tpublic Result remove(\n");
		sb.append("\t\t@RequestParam(value=\"id\", required=false, defaultValue=\"0\")int id,\n");
		sb.append("\t\tHttpServletRequest request, HttpServletResponse response, Model model) {\n");
		sb.append("\t\treturn "+beanService+".remove(id,super.getCurrentUser().getUsername(),request,model);\n");
		sb.append("\t}\n\n");	
		
		//结束
		sb.append("\t//////////////////////////////上面代码由代码生成器生成////////////////////////////////////\n\n");
		sb.append("}");
		try {
			FileUtil.saveFile(path+"/"+upper+"Controller.java", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
	}
	private void service(List<Map> tableList,String fileName,String upper) {
		String path=GeneratorCode.class.getClassLoader().getResource(servicePath.replaceAll("\\.", "/")).getPath();
		path=path.replaceAll("target/classes", "src/main/java");
		StringBuffer sb=new StringBuffer();
		String beanMapper=fileName+"Mapper";
		sb.append("package "+servicePath+";\n");
		sb.append("import java.util.Date;\n"
				+ "import java.util.List;\n");
		sb.append("import javax.servlet.http.HttpServletRequest;\n");
		sb.append("import org.apache.log4j.Logger;\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Service;\r\n" + 
				"import org.springframework.ui.Model;\r\n");
		sb.append("import net.sf.json.JSONArray;\r\n" + 
				"import net.sf.json.JSONObject;\r\n");
		sb.append("import com.qurich.common.model.PageMeta;\r\n" + 
				"import com.qurich.common.model.Result;\r\n" + 
				"import com.qurich.common.model.ResultCodeEnum;\r\n" + 
				"import com.qurich.common.utils.IpAddressUtil;\n");
		sb.append("@Service\n");
		sb.append("public class "+upper+"Service {\n");
		sb.append("\tprivate Logger log=Logger.getLogger("+upper+"Service.class);\n");
		sb.append("\t@Autowired\n");
		sb.append("\tprivate LogMapper logMapper;//操作日志，入库\n");
		sb.append("\t@Autowired\n");
		sb.append("\tprivate "+upper+"Mapper "+beanMapper+";\n\n");
		
		//list page
		sb.append("\t//跳转list页面\n");
		sb.append("\tpublic String listPage( Model model) {\n");
		sb.append("\t\treturn \""+fileName+"/list\";\n");
		sb.append("\t}\n\n");				
		
		//list ajax
		sb.append("\t//ajax请求list\n");
		sb.append("\tpublic String list(int page, int perpage,"+upper+" bean, String sort, String field, Model model) {\n");
		sb.append("\t\tJSONObject json=new JSONObject();\n");
		sb.append("\t\ttry {\n");
		sb.append("\t\t\tList<"+upper+"> lists="+beanMapper+".get"+upper+"sByCondition(bean,sort,field,(page-1)*perpage,perpage);\n");
		sb.append("\t\t\tInteger count="+beanMapper+".get"+upper+"sByConditionCount(bean);\n");
		sb.append("\t\t\tPageMeta pageBean=new PageMeta();\n");
		sb.append("\t\t\tpageBean.setPerpage(perpage);\n");
		sb.append("\t\t\tpageBean.setPage(page);\n");
		sb.append("\t\t\tpageBean.setTotal(null==count?0:count.intValue());\n");
		sb.append("\t\t\tpageBean.setSort(sort);\n");
		sb.append("\t\t\tpageBean.setField(field);\n");
		sb.append("\t\t\tjson.put(\"meta\", JSONObject.fromObject(pageBean));\n");
		sb.append("\t\t\tjson.put(\"data\", JSONArray.fromObject(lists).toString());\n");
		sb.append("\t\t}catch(Exception e) {\n");
		sb.append("\t\t\tlog.error(\""+upper+"Service.list error\",e);\n");
		sb.append("\t\t}\n");
		sb.append("\t\treturn json.toString();\n");
		sb.append("\t}\n\n");
		
		//addOrUpdate
		sb.append("\t//跳转添加或者修改页面\n");
		sb.append("\tpublic String addOrUpdate(int id, HttpServletRequest request, Model model) {\n");
		sb.append("\t\ttry {\n");
		sb.append("\t\t\t"+upper+" bean="+beanMapper+".get"+upper+"ById(id);\n");
		sb.append("\t\t\tmodel.addAttribute(\"bean\", bean);\n");
		sb.append("\t\t}catch(Exception e) {\n");
		sb.append("\t\t\tlog.error(\""+upper+"Service.addOrUpdate error\",e);\n");
		sb.append("\t\t}\n");
		sb.append("\t\treturn \""+fileName+"/addOrUpdate\";\n");
		sb.append("\t}\n\n");
		
		//remove
		sb.append("\t//删除\n");
		sb.append("\tpublic Result remove(int id,String wname, HttpServletRequest request, Model model) {\n");
		sb.append("\t\tResult result=new Result();\n");
		sb.append("\t\ttry {\n");
		sb.append("\t\t\tInteger res="+beanMapper+".delete"+upper+"(id, new Date(),wname);\n");
		sb.append("\t\t\tif(null!=res&&res>0) {\n");
		sb.append("\t\t\t\tString ip=IpAddressUtil.getIp(request);\n");
		sb.append("\t\t\t\tlogMapper.saveBean(new Log(id, \""+table+"\", \"删除"+table+"表：\"+id,ip,IpAddressUtil.ip2City(ip),wname));\n");
		sb.append("\t\t\t\tresult.setCode(ResultCodeEnum.SUCCESS);\n");
		sb.append("\t\t\t\tresult.setMsg(\"删除成功！\");\n");
		sb.append("\t\t\t\treturn result;\n");
		sb.append("\t\t\t}\n");
		sb.append("\t\t}catch(Exception e) {\n");
		sb.append("\t\t\tlog.error(\""+upper+"Service.remove error\",e);\n");
		sb.append("\t\t}\n");
		sb.append("\t\tresult.setCode(ResultCodeEnum.FAIL);\n");
		sb.append("\t\tresult.setMsg(\"操作失败！请重试\");\n");
		sb.append("\t\treturn result;\n");
		sb.append("\t}\n\n");
		
		//保存（添加保存或者修改保存）
		sb.append("\t//保存（添加保存或者修改保存）\n");
		sb.append("\t@Transactional\n");
		sb.append("\tpublic Result save("+upper+" bean, HttpServletRequest request, Model model) {\n");
		sb.append("\tResult result=new Result();\n");
		sb.append("\t\tif(bean.getId()==0) {//添加\n");
		sb.append("\t\t\tInteger ss="+beanMapper+".save"+upper+"(bean);\n");
		sb.append("\t\t\tif(ss>0) {\n");
		sb.append("\t\t\t\tString ip=IpAddressUtil.getIp(request);\n");//写日志
		sb.append("\t\t\t\tlogMapper.saveBean(new Log(bean.getId(), \""+table+"\", \"新增：\"+bean.toString(),ip,IpAddressUtil.ip2City(ip),bean.getWname()));\n");//写日志
		sb.append("\t\t\t\tresult.setCode(ResultCodeEnum.SUCCESS);\n");//写日志
		sb.append("\t\t\t\tresult.setMsg(\"添加成功！\");\n");//写日志
		sb.append("\t\t\t\treturn result;\n");//写日志
		sb.append("\t\t\t}\n");
		sb.append("\t\t}else {//修改\n");
		sb.append("\t\t\tInteger ss="+beanMapper+".update"+upper+"(bean);\n");
		sb.append("\t\t\tif(ss>0) {\n");
		sb.append("\t\t\t\tString ip=IpAddressUtil.getIp(request);\n");//写日志
		sb.append("\t\t\t\tlogMapper.saveBean(new Log(bean.getId(), \""+table+"\", \"修改：\"+bean.toString(),ip,IpAddressUtil.ip2City(ip),bean.getWname()));\n");//写日志
		sb.append("\t\t\t\tresult.setCode(ResultCodeEnum.SUCCESS);\n");//写日志
		sb.append("\t\t\t\tresult.setMsg(\"修改成功！\");\n");//写日志
		sb.append("\t\t\t\treturn result;\n");//写日志
		sb.append("\t\t\t}\n");
		sb.append("\t\t}\n");
		sb.append("\t\tresult.setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR);\n");
		sb.append("\t\tresult.setMsg(\"操作失败，请重试！\");\n");
		sb.append("\t\treturn result;\n");
		sb.append("\t}\n\n");
		//结束
		sb.append("\t//////////////////////////////上面代码由代码生成器生成////////////////////////////////////\n\n");
		sb.append("}");
		try {
			FileUtil.saveFile(path+"/"+upper+"Service.java", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void mapper(List<Map> tableList,String fileName,String upper) {
		String path=GeneratorCode.class.getClassLoader().getResource(mapperPath.replaceAll("\\.", "/")).getPath();
		path=path.replaceAll("target/classes", "src/main/java");
		StringBuffer sb=new StringBuffer();
		sb.append("package "+mapperPath+";\n");
		sb.append("import java.util.Date;\n");
		sb.append("import java.util.List;\n");
		sb.append("import org.apache.ibatis.annotations.Mapper;\n");
		sb.append("import org.apache.ibatis.annotations.Insert;\n");
		sb.append("import org.apache.ibatis.annotations.Options;\n");
		sb.append("import org.apache.ibatis.annotations.Param;\n");
		sb.append("import org.apache.ibatis.annotations.Result;\n");
		sb.append("import org.apache.ibatis.annotations.Results;\n");
		sb.append("import org.apache.ibatis.annotations.Select;\n");
		sb.append("import org.apache.ibatis.annotations.Update;\n");
		sb.append("import org.apache.ibatis.annotations.Delete;\n\n");
		sb.append("import "+modelPath+"."+upper+";\n\n");
		sb.append("@Mapper\n");
		sb.append("public interface "+upper+"Mapper {\n");
		boolean existResults=false;
		StringBuffer results=new StringBuffer();
		String beanMap=null;
		//insert
		sb.append("\t//添加\n");
		sb.append("\t@Insert(\"INSERT INTO "+table+" (");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			if(column.contains("_")) {
				if(!existResults) {
					beanMap=fileName+"Map";
					results.append("\t@Results(id=\""+beanMap+"\", value={\n");
				}
				existResults=true;
				results.append("\t\t@Result(property=\""+column+"\",column=\""+column+"\"),\n");
			}
			if(!column.equals("id")) {
				sb.append("`"+column+"`,");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")\"+ \n\t\t\"VALUES (");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			if(!column.equals("id")) {
				sb.append("#{"+column+"},");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(");\")\n");
		sb.append("\t@Options(useGeneratedKeys=true)\n");
		sb.append("\tInteger save"+upper+"("+upper+" "+fileName+");\n\n");
		
		//select by id
		sb.append("\t//通过id查询\n");
		sb.append("\t@Select(\"select * from "+table+" where id=#{id};\")\n");
		if(existResults) {//有results
			results.deleteCharAt(results.length() - 2);
			results.append("\t})\n");
		}
		sb.append(results);
		sb.append("\t"+upper+" get"+upper+"ById(@Param(\"id\")int id);\n\n");
		
		//update
		sb.append("\t//修改\n");
		sb.append("\t@Update(\"<script>update "+table+" set `utime`=#{utime}, `uname`=#{uname} \"\n");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			if(!column.equals("id")&&!column.equals("is_del")&&!column.equals("ctime")
					&&!column.equals("utime")&&!column.equals("cname")&&!column.equals("uname")) {
				sb.append("\t\t+ \"<if test=\\\""+column+"!=null\\\">,`"+column+"` = #{"+column+"}</if>\"\n");
			}
		}
		sb.append("\t\t+ \" WHERE id=#{id};</script>\")\n");
		sb.append("\tInteger update"+upper+"("+upper+" "+fileName+");\n\n");
		
		//delete
		sb.append("\t//逻辑删除\n");
		sb.append("\t@Delete(\"update "+table+" set is_del=1,utime=#{utime},uname=#{uname} where id=#{id};\")\n");
		sb.append("\tInteger delete"+upper+"(@Param(\"id\")int id,@Param(\"utime\")Date utime,@Param(\"uname\")String uname);\n\n");
		
		//list 查询
		sb.append("\t//查询条件列表\n");
		sb.append("\t@Select({\"<script>select * from "+table+" where is_del=0 \"\n");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			if(!column.equals("is_del")&&!column.equals("ctime")
					&&!column.equals("utime")) {
				sb.append("\t\t+ \"<if test=\\\"bean."+column+"!=null and bean."+column+"!=''\\\">and `"+column+"` = #{bean."+column+"} </if>\"\n");
			}
		}
		sb.append("\t\t+ \"order by `${field}` ${sort} limit #{offset},#{rows}</script>\"})\n");
		sb.append("\t@ResultMap(\""+beanMap+"\")\n");
		sb.append("\tList<"+upper+"> get"+upper+"sByCondition(@Param(\"bean\")"+upper+" bean, @Param(\"sort\")String sort,@Param(\"field\")String field,@Param(\"offset\") int offset,@Param(\"rows\") int rows);\n\n");
		
		sb.append("\t//查询条件列表总条数\n");
		sb.append("\t@Select({\"<script>select count(1) from "+table+" where is_del=0 \"\n");
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			if(!column.equals("is_del")&&!column.equals("ctime")
					&&!column.equals("utime")) {
				sb.append("\t\t+ \"<if test=\\\"bean."+column+"!=null and bean."+column+"!=''\\\">and `"+column+"` = #{bean."+column+"} </if>\"\n");
			}
		}
		sb.append("\t\t+\"</script>\"})\n");
		sb.append("\tInteger get"+upper+"sByConditionCount(@Param(\"bean\")"+upper+" bean);\n\n");
		
		//结束
		sb.append("\t//////////////////////////////上面代码由代码生成器生成////////////////////////////////////\n\n");
		sb.append("}");
		try {
			FileUtil.saveFile(path+"/"+upper+"Mapper.java", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void model(List<Map> tableList,String fileName,String upper) {
		String path=GeneratorCode.class.getClassLoader().getResource(modelPath.replaceAll("\\.", "/")).getPath();
		path=path.replaceAll("target/classes", "src/main/java");
		StringBuffer sb=new StringBuffer();
		sb.append("package "+modelPath+";\n");
		sb.append("\npublic class "+upper+" extends BaseModel{\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\n");
		LinkedHashMap<String,String> linkedMap=new LinkedHashMap<>();
		for(Map map:tableList) {
			String column=(String)map.get("COLUMN_NAME");
			String type=(String)map.get("DATA_TYPE");
			String is_nullable=(String)map.get("IS_NULLABLE");
			//String comment=(String)map.get("COLUMN_COMMENT");//注解
			generatorVariable(column, type,is_nullable, linkedMap);
		}
		if(linkedMap.size()==0)return;
		//成员变量
		for(Entry<String,String> entry:linkedMap.entrySet()) {
			sb.append("\tprivate "+entry.getValue()+" "+entry.getKey()+";\n");//结束
		}
		sb.append("\n");
		//get set方法
		for(Entry<String,String> entry:linkedMap.entrySet()) {
			//get
			sb.append("\tpublic "+entry.getValue()+" get"+ StringCharUtil.upperCaseFirstChar(entry.getKey())+"(){\n");
			sb.append("\t\treturn "+entry.getKey()+";\n\t}\n");
			//get---end
			//set
			sb.append("\tpublic void set"+StringCharUtil.upperCaseFirstChar(entry.getKey())+"("+entry.getValue()+" "+entry.getKey()+"){\n");
			sb.append("\t\tthis."+entry.getKey()+" = "+entry.getKey()+";\n\t}\n");
			//set----end
		}
		sb.append("}");//结束
		try {
			FileUtil.saveFile(path+"/"+upper+".java", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void generatorVariable(String column,String type,String is_nullable,LinkedHashMap<String,String> map) {
		if(!column.equals("ctime")&&!column.equals("utime")
				&&!column.equals("wname")&&!column.equals("is_del"))
		switch (type) {
		case "decimal":
		case "numeric":
		case "double":
			map.put(column, "Double");
			break;
		case "float":
			map.put(column, "Float");
			break;
		case "tinyint":
		case "smallint":
		case "mediumint":
		case "int":
		case "integer":
			if("NO".equals(is_nullable)){
				map.put(column, "int");
			}else{
				map.put(column, "Integer");
			}
			break;
		case "bigint":
			map.put(column, "Long");
			break;
		case "date":
		case "time":
		case "datetime":
		case "timestamp":
			map.put(column, "Date");
			break;
		default:
			map.put(column, "String");
			break;
		}
	}
	
	
}
