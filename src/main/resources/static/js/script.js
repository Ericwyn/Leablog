function loadNavigation() {
    document.getElementsByTagName("body")[0].innerHTML +=
        "<div class=\"mdui-container mdui-appbar-with-toolbar\">\n" +
        "    <div class=\"mdui-drawer \" id=\"drawer\" >\n" +
        "        <ul class=\"mdui-list\">\n" +
        "            <li class=\"mdui-subheader\">博客</li>\n" +
        "            <li class=\"mdui-list-item mdui-ripple\" onclick=\"gotoPath('/');document.body.scrollTop=0;\">\n" +
        "                <i class=\"mdui-list-item-icon mdui-icon material-icons mdui-text-color-blue\">&#xe88a;</i>\n" +
        "                <div class=\"mdui-list-item-content\">主页</div>\n" +
        "            </li>\n" +

        "            <li class=\"mdui-list-item mdui-ripple\" onclick=\'goto_gallery()\'>\n" +
        "" +
        "                <i class=\"mdui-list-item-icon mdui-icon material-icons mdui-text-color-deep-orange\">&#xe865;</i>\n                <div class=\"mdui-list-item-content\">标签</div>\n" +
        "            </li>\n            <li class=\"mdui-list-item mdui-ripple\" onclick=\'goto_gallery()\'>\n                <i class=\"mdui-list-item-icon mdui-icon material-icons mdui-text-color-green\">&#xe2c7;</i>\n                <div class=\"mdui-list-item-content\">归档</div>\n            </li>\n            \n" +

        "            <li class=\"mdui-subheader\">朋友</li>\n" +

        "            <li class=\"mdui-list-item mdui-ripple\">\n" +
        "                <i class=\"mdui-list-item-icon mdui-icon material-icons \">&#xe80b;</i>\n" +
        "                <a href=\"https://xiphoray.github.io/\" target=\"_Blank\" class=\"mdui-list-item-content\">五寸畦田</a>\n" +
        "            </li>\n            <li class=\"mdui-list-item mdui-ripple\">\n                <i class=\"mdui-list-item-icon mdui-icon material-icons \">&#xe80b;</i>\n                <a href=\"https://zilchfp.github.io/\" target=\"_Blank\" class=\"mdui-list-item-content\">你的益达</a>\n            </li>\n" +
        "            </li>\n" +
        "        </ul>\n" +
        "    </div>\n" +
        "</div>";
}

//存储博客数据
var blogdata =getDataFromCookie();

function getData() {
    if(blogdata==null){
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'allblog', true);
        // 注意下行注释
        // xhr.setRequestHeader('Content-Type', 'anything')
        xhr.send();

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    if(isFromPath){
                        dataTemp=xhr.responseText;
                        saveDataToCookie(dataTemp);
                        blogdata = JSON.parse(dataTemp);
                        loadPathCard();
                        //直接载入卡片
                    }else {
                        dataTemp=xhr.responseText;
                        saveDataToCookie(dataTemp);
                        blogdata = JSON.parse(dataTemp);
                        loadIndexCard();
                        //刷新卡片
                    }

                } else {
                    console.log(xhr.status);
                    console.log("笔记获取失败");
                }
            }
        }
    }
    return blogdata;
}

//应该就只在一开始的时候调用吧
function getDataFromCookie() {
    if(window.sessionStorage.blog != null){
        console.log("sessionStorage如下");
        console.log(window.sessionStorage.blog);
        console.log("从sessionStorage当中找到数据");
        return JSON.parse(window.sessionStorage.blog);
    }else {
        console.log("无法在sessionStorage当中找到博客数据");
        return null;
    }
}

function saveDataToCookie(blogData) {
    window.sessionStorage.blog=blogData;
    console.log("数据已经保存到sessionStorage了");
}
function loadIndexCard() {
    if(blogdata==null){
        getData()
    }else {
        var dataList = getData().dataList;
        document.getElementById("card_container").innerHTML = "";
        for(i=0;i<dataList.length;i++){
            if(dataList[i].isMarkdown){
                document.getElementById("card_container").innerHTML +=
                    "<div class=\"mdui-card mdui-m-t-3 mdui-hoverable\">\n" +

                    "    <div class=\"mdui-card-primary\">\n" +
                    "        <div class=\"mdui-card-primary-title\">"+dataList[i].title+"</div>\n" +
                    "        <div class=\"mdui-card-primary-subtitle \">"+dataList[i].createdTime+"</div>\n" +
                    "    </div>\n" +

                    "    <div class=\"mdui-card-content mdui-typo mdui-p-l-4 mdui-p-r-4 mdui-p-t-0 mdui-p-b-0 \">"+marked(dataList[i].content.substring(0,300))+"</div>\n" +

                    "    <div class=\"mdui-card-actions\">\n" +
                    "        <i class=\"mdui-float-right mdui-icon material-icons mdui-ripple mdui-m-r-1\"  onclick=\"gotoPath('"+dataList[i].noteId+"')\">&#xe5d3;</i>\n" +
                    "    </div>\n" +
                    "</div>\n";
            }else {
                document.getElementById("card_container").innerHTML +=
                    "<div class=\"mdui-card mdui-m-t-3 mdui-hoverable\">\n" +

                    "    <div class=\"mdui-card-primary\">\n" +
                    "        <div class=\"mdui-card-primary-title\">"+dataList[i].title+"</div>\n" +
                    "        <div class=\"mdui-card-primary-subtitle \">"+dataList[i].createdTime+"</div>\n" +
                    "    </div>\n" +

                    "    <div class=\"mdui-card-content mdui-typo mdui-p-l-4 mdui-p-r-4 mdui-p-t-0 mdui-p-b-0\">"+dataList[i].content.substring(0,300)+"</div>\n" +

                    "    <div class=\"mdui-card-actions\">\n" +
                    "        <i class=\"mdui-float-right mdui-icon material-icons mdui-ripple mdui-m-r-1\" onclick=\"gotoPath('"+dataList[i].noteId+"')\">&#xe5d3;</i>\n" +
                    "    </div>\n" +
                    "</div>\n";
            }
            console.log("载入一篇笔记");
        }
    }

}

var isFromPath=false;
//从路径访问本网页
function loadPathCard(path) {
    isFromPath=true;
    if(blogdata==null){
        //先去获取数据然后再回来
        getData();
    }else {
        var noteid;
        if(path==null){
            noteid = document.location.pathname.replace("/","");
        }else {
            noteid = path;
        }
        var dataList = blogdata.dataList;
        for(i=0;i<dataList.length;i++){
            if(dataList[i].noteId==noteid){
                loadBlog(dataList[i].createdTime);
            }
        }
    }
}

function loadBlog(domOrCreateTime,isFormIndex) {
    hiddenBlog();
    var title;
    var createTime;
    if(isFormIndex){
        title = domOrCreateTime.parentNode.parentNode.childNodes[1].childNodes[1].innerText;
        createTime = domOrCreateTime.parentNode.parentNode.childNodes[1].childNodes[3].innerText;
        console.log("点击查看"+title);
    }else {
        createTime = domOrCreateTime;
    }
    var dataList = getData().dataList;
    var priv;
    var next;
    for(i=0;i<dataList.length;i++){
        if(dataList[i].createdTime == createTime){
            setBlogData(dataList[i]);

            if(dataList[i-1] !=null){
                priv=dataList[i-1].createdTime;
            }else {
                priv="没有上一篇";
            }

            if(dataList[i+1] !=null){
                next=dataList[i+1].createdTime;
            }else {
                next="没有下一篇";
            }
            setPrivAndNext(priv,next);
            if(isFormIndex){
                hiddenIndex();
            }
            showBlog();
            break;
        }
    }
}

function show404() {

}

function hiddenIndex() {
    // window.sessionStorage.scrollTemp= document.body.scrollTop;
    document.getElementById("card_container").style.display="none";
    console.log("hidden一次");
}
// function showIndex() {
//     document.getElementById("card_container").style.display="block";
//     if(window.sessionStorage.scrollTemp == null){
//         document.body.scrollTop=0;
//     }else {
//         document.body.scrollTop=window.sessionStorage.scrollTemp;
//     }
// }

function hiddenBlog() {
    document.getElementById("blog_container").style.display="none";
}

function showBlog() {
    document.getElementById("blog_container").style.display="block";
    document.body.scrollTop=0;
}

function setBlogData(blog) {
    if(blog.isMarkdown){
        document.getElementById("blog_card").innerHTML=marked(blog.content);
    }else {
        document.getElementById("blog_card").innerHTML=blog.content;
    }
}

function setPrivAndNext(priv,next) {
    var privTime = document.getElementById("privTime");
    privTime.innerHTML=priv;
    var nextTime = document.getElementById("nextTime");
    nextTime.innerHTML=next;
    var privTitle = document.getElementById("privTitle");
    privTitle.innerHTML="已到最前";
    var nextTitle = document.getElementById("nextTitle");
    nextTitle.innerHTML="已到最后";

    var dataList = getData().dataList;
    privTitle.parentElement.setAttribute("onclick","");
    nextTitle.parentElement.setAttribute("onclick","");

    for(i=0;i<dataList.length;i++){
        if(dataList[i].createdTime == priv){
            privTitle.innerHTML=dataList[i].title;
            privTitle.parentElement.setAttribute("onclick","gotoPath(\""+dataList[i].noteId+"\")");
            console.log("找到priv")
        }
        if(dataList[i].createdTime == next){
            nextTitle.innerHTML=dataList[i].title;
            nextTitle.parentElement.setAttribute("onclick","gotoPath(\""+dataList[i].noteId+"\")");
            console.log("找到next")
        }
    }
}

function gotoPath(path) {
    if(window.location.pathname == "/"){
        window.sessionStorage.scrollTemp= document.body.scrollTop;
    }
    window.location.href=path;
}