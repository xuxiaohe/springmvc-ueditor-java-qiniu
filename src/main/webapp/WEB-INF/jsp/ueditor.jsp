<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>添加文章</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
        <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
        <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
        <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
        <link href="images/style.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <!-- js -->
        <script type="text/javascript" src="js/jquery-1.8.0.min.js" ></script>
        <script type="text/javascript" src="js/admin.min.js" ></script>

        <style type="text/css">
            div{
                width:100%;
            }
        </style>
    </head>
    <body>

    <div class="wrap admin_page">
        <div class="header fn_clear">
            <div class="fn_left"><!--
			<a href="http://ganline.com/" target="_blank" class="logo">
				<img src="img/admin-logo.gif" alt="LINECMS 橄榄传媒网站内容管理系统">
			</a> -->
                <i> ，欢迎使用网站管理系统 </i> </div>
            <div class="fn_right admin_btn"> <a class="a1" href="cleancache.php.html">清除缓存</a><a class="a2" href="logout.php.html">注销</a> </div>
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
                            <dt> <a href="javascript:;">焦点横幅</a> <i class="ico"></i> </dt>
                            <dd> <a href="横幅列表.html" >横幅管理</a> </dd>
                            <dd> <a href="添加横幅.html">添加横幅</a> </dd>
                        </dl>
                        <dl id="iteam-article">
                            <dt > <a href="javascript:;">文章管理</a> <i class="ico"></i> </dt>
                            <dd class='on' > <a href="添加文章.html">添加文章</a> </dd>
                            <dd> <a href="文章列表.html">文章列表</a> </dd>
                            <dd> <a href="文章分类.html">文章分类</a> </dd>
                            <dd> <a href="添加分类.html">添加分类</a> </dd>
                        </dl>
                    </div>
                    <dl id="iteam-comments" class="frap" >
                        <dt > <a href="javascript:;">留言管理</a> <i class="ico"></i> </dt>
                        <dd> <a href="留言列表.html">留言列表</a> </dd>
                    </dl>
                    <dl id="iteam-links" class="frap">
                        <dt> <a href="javascript:;">友情链接</a> <i class="ico"></i> </dt>
                        <dd> <a href="友情链接.html">友情链接管理</a> </dd>
                    </dl>
                    <dl>
                        <dt > <a href="javascript:;">SEO</a> <i class="ico"></i> </dt>
                        <dd> <a href="基本规则设置.html">基本规则设置</a> </dd>
                        <dd> <a href="URL重写.html">URL重写</a> </dd>
                        <dd> <a href="生成HTML.html">生成HTML</a> </dd>
                        <dd> <a href="友情链接.html">友链列表</a> </dd>
                    </dl>
                </div>
            </div>
            <div class="main_body">
                <form id="sform" action="article-add.php" method="post">
                    <table class="inputform" cellpadding="1" cellspacing="1">
                        <tr>
                            <td class="label">文章标题</td>
                            <td class="input"><input type="text" class="txt" name="articletitle" /></td>
                        </tr>
                        <tr>
                            <td class="label">所属分类</td>
                            <td class="input"><select name="articlecategory">
                                <option value="0">请选择</option>
                                <option value="40">作者团队</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td class="label">缩略图</td>
                            <td class="input"><input type="file" class="txt" style="width:280px;" name="productthumb" /></td>
                        </tr>
                        <tr>
                            <td class="label">文章名称</td>
                            <td class="input"><input type="text" class="txt" name="articlefilename" value="" />
                                &nbsp;&nbsp;设置为http://开头，将链接到指定的地址。</td>
                        </tr>
                        <tr>
                            <td class="label">文章内容</td>
                            <td class="input"> <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script></td>
                        </tr>
                        <tr>
                            <td class="label"><a id="extattrlink" href="javascript:void(0);">显示附加属性</a></td>
                            <td class="input"><a href="javascript:void(0);" id="addext" class="fr">增加一个附加属性</a></td>
                        </tr>
                    </table>
                    <div class="inputsubmit">
                        <input id="submitbtn" type="submit" class="subtn" value="提交" />
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>




        <%--<div id="btns">--%>
            <%--<div>--%>
                <%--<button onclick="getAllHtml()">获得整个html的内容</button>--%>
                <%--<button onclick="getContent()">获得内容</button>--%>
                <%--<button onclick="setContent()">写入内容</button>--%>
                <%--<button onclick="setContent(true)">追加内容</button>--%>
                <%--<button onclick="getContentTxt()">获得纯文本</button>--%>
                <%--<button onclick="getPlainTxt()">获得带格式的纯文本</button>--%>
                <%--<button onclick="hasContent()">判断是否有内容</button>--%>
                <%--<button onclick="setFocus()">使编辑器获得焦点</button>--%>
                <%--<button onmousedown="isFocus(event)">编辑器是否获得焦点</button>--%>
                <%--<button onmousedown="setblur(event)" >编辑器失去焦点</button>--%>

            <%--</div>--%>
            <%--<div>--%>
                <%--<button onclick="getText()">获得当前选中的文本</button>--%>
                <%--<button onclick="insertHtml()">插入给定的内容</button>--%>
                <%--<button id="enable" onclick="setEnabled()">可以编辑</button>--%>
                <%--<button onclick="setDisabled()">不可编辑</button>--%>
                <%--<button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>--%>
                <%--<button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>--%>
                <%--<button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>--%>
            <%--</div>--%>

            <%--<div>--%>
                <%--<button onclick="getLocalData()" >获取草稿箱内容</button>--%>
                <%--<button onclick="clearLocalData()" >清空草稿箱</button>--%>
            <%--</div>--%>

        <%--</div>--%>
        <%--<div>--%>
            <%--<button onclick="createEditor()">--%>
                <%--创建编辑器</button>--%>
            <%--<button onclick="deleteEditor()">--%>
                <%--删除编辑器</button>--%>
        <%--</div>--%>

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
