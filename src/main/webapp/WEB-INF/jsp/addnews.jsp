<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>添加文章</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <!-- js -->
    <script type="text/javascript" src="js/jquery-1.8.0.min.js" ></script>
    <script type="text/javascript" src="js/admin.min.js" ></script>


    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
<div class="wrap admin_page">
    <div class="header fn_clear">
        <div class="fn_left"><!--
			<a href="http://ganline.com/" target="_blank" class="logo">
				<img src="img/admin-logo.gif" alt="LINECMS 橄榄传媒网站内容管理系统">
			</a> -->
            <i> 欢迎使用网站管理系统 </i> </div>
        <div class="fn_right admin_btn"> <a class="a2" href="@{/userlogout}">注销</a> </div>
    </div>
    <div class="admin_nav"> 当前位置：<a href="index.php.html">后台</a> > 添加文章 </div>
    <div class="main admin_content fn_clear">
        <div class="admin_menu" id="main_nav">
            <div class="menu_dl">
                <dl id="iteam-home" class="frap sole">
                    <dt> <a href="#" target="_blank">回到网站首页</a> <i class="ico"></i> </dt>
                </dl>
                <div>
                    <dl id="iteam-lantern">
                        <dt> <a href="javascript:;">广告设置</a> <i class="ico"></i> </dt>
                        <dd> <a href="${path}/admin/manger/gethengfulist" >广告位</a> </dd>
                        <dd> <a href="${path}/admin/manger/addhengfu">添加横幅</a> </dd>
                    </dl>
                    <dl id="iteam-article">
                        <dt > <a href="javascript:;">新闻管理</a> <i class="ico"></i> </dt>
                        <dd class='on' > <a href="${path}/admin/news/addnew">添加文章</a> </dd>
                        <dd> <a href="${path}/admin/news/newslist">内容管理</a> </dd>
                        <dd> <a href="${path}/admin/news/getcategorylist">文章分类</a> </dd>
                        <dd> <a href="${path}/admin/news/addcategory">添加分类</a> </dd>
                    </dl>
                </div>
                <dl id="iteam-comments" >
                    <dt > <a href="javascript:;">招商留言</a> <i class="ico"></i> </dt>
                    <dd> <a href="${path}/admin/liuyan/liuyanlist">留言列表</a> </dd>
                </dl>
            </div>
        </div>
        <div class="main_body">
            <form id="sform" action="addnewinfo" method="post" enctype="multipart/form-data">
                <input type="hidden" value="${news.image}" name="oldimage"/>
                <input type="hidden" value="${news.id}" name="id"/>
                <table class="inputform" cellpadding="1" cellspacing="1">
                    <tr>
                        <td class="label">文章标题</td>
                        <td class="input"><input type="text" value="${news.title}" class="txt" name="title" style="wid400px;" /></td>
                    </tr>
                    <tr>
                        <td class="label">所属分类</td>
                        <td class="input">

                            <select name="ischoose" >
                                <c:forEach items="${data}" var="tmp1">
                                    <c:if test="${tmp1.name == news.category}"    >
                                        <option selected="selected" value="${tmp1.id}">${tmp1.name}</option>
                                    </c:if>
                                    <c:if test="${tmp1.name != news.category}"    >
                                        <option value="${tmp1.id}">${tmp1.name}</option>
                                    </c:if>

                                </c:forEach>

                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">上传封面图</td>
                        <td class="input"><input type="file" class="txt" style="wid280px;" name="image" /></td>
                    </tr>

                    <tr>
                        <td class="label">频道置顶</td>
                        <td class="input">
                            <c:if test="${news.top==1}">
                                <label><input type="radio" checked="checked" name="top" value="1" />置顶</label>
                                <label><input type="radio" name="top" value="0"/>不置顶 </label>
                            </c:if>
                            <c:if test="${news.top!=1}">
                                <label><input type="radio" name="top" value="1" />置顶</label>
                                <label><input type="radio" checked="checked" name="top" value="0"/>不置顶 </label>
                            </c:if>


                        </td>
                    </tr>
                    <tr>
                        <td class="label">文章内容</td>
                        <td class="input"> <script id="editor" name="content" type="text/plain" style="width:900px;height:300px;">${news.content}</script></td>
                    </tr>
                </table>
                <div class="inputsubmit">
                    <input id="submitbtn" type="submit" class="subtn" value="提交" />
                </div>
            </form>

        </div>
    </div>
</div>


<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function isFocus(e) {
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e) {
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++]; ) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++]; ) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }

    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }
</script>

</body>
</html>

