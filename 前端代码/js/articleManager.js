window.onload = function(){
    console.log("articlemanagetest");
    console.log("role" + sessionStorage.role);
    if(sessionStorage.role == 1){
        listUserPostAjax(userPageNum);
        
    }
    else{
        console.log("管理员牛逼");
        }
    currentPage();
    
}


function postdetail(typeId,postId){
    var url;
    switch(typeId){
        case 1:
            url = "./adopt_detail.html?id="+postId;
            break;
    }
    window.location.href=url;
}

function updatedetail(typeId,postId){
    var murl;
    switch(typeId){
        case 1:
            url = "./updateAdopt.html?id="+postId;
            break;
    }
    window.location.href=url;
}


var adminPageNum = 1;
var adminPagetotal;
var userPageNum = 1;
var userPagetotal;
var userId = sessionStorage.getItem("userId");

function backArticlePage() {
    if (adminPageNum > 1 && sessionStorage.role == 0) {
        $("#backpage").removeClass('disabled');
        adminPageNum -= 1;
        listAllArticleAjax(adminPageNum);
    } else if (adminPageNum == 1 && sessionStorage.role == 0) {
        $("#backpage").addClass('disabled');
    } else if (userPageNum > 1 && sessionStorage.role == 1) {
        $("#backpage").removeClass('disabled');
        userPageNum -= 1;
        listUserPostAjax(userPageNum);
    } else if (userPageNum == 1 && sessionStorage.role == 1) {
        $("#backpage").addClass('disabled');
    }
    currentPage();
}

function nextArticlePage() {

    if (adminPageNum < adminPagetotal && sessionStorage.role == 0) {
        adminPageNum += 1;
        listAllArticleAjax(adminPageNum);
    } else if (adminPageNum == adminPagetotal && sessionStorage.role == 0) {
        $("#nextpage").addClass('disabled');
    } else if (userPageNum < userPagetotal && sessionStorage.role == 1) {
        userPageNum += 1;
        listUserPostAjax(userPageNum);
    } else if (userPageNum == userPagetotal && sessionStorage.role == 1) {
        $("#nextpage").addClass('disabled');
    }
    currentPage();

}

function jumpArticlePage() {
    console.log("jump teswt");
    var jumppage = $('.jumppage2').val();
    if (jumppage <= adminPagetotal && sessionStorage.role == 0) {
        console.log("跳到" + jumppage);
        adminPageNum = jumppage;
        listAllArticleAjax(jumppage);
    } else if (jumppage <= userPagetotal && sessionStorage.role == 1) {
        console.log("跳到" + jumppage);
        userPageNum = jumppage;
        listUserPostAjax(jumppage);
    } else if (jumppage > adminPagetotal || jumppage > userPagetotal)
        swal("超过页数！","","warning");
    currentPage();
}

function jumpArticlePage2(pnum) {
    
    if (pnum <= adminPagetotal && sessionStorage.role == 0) {
        console.log("跳到" + pnum);
        adminPageNum = pnum;
        listAllArticleAjax(pnum);
    } else if (pnum <= userPagetotal && sessionStorage.role == 1) {
        console.log("跳到" + pnum);
        userPageNum = pnum;
        listUserPostAjax(pnum);
    } else if (pnum > adminPagetotal || jumppage > userPagetotal)
        swal("超过页数！","","warning");
    currentPage();
    

}

function currentPage(){
    $("#pagenav a").each(function(){  
        console.log("test: " + $(this).text());
        if($(this).text()== userPageNum){  
            $(this).parent().addClass("active");  
        }  
    });
}

//用户界面列出所有文章
function listUserPostAjax(page) {
    console.log("userid" + sessionStorage.userId);
    var settings = {
        url: "http://localhost:8080/post/listUserPost",
        type: "POST",
        async: false,
        crossDomain: "true",
        xhrFields: {
            withCredentials: "true"
        },
        data: {
            "pageNum": page,
            "pageSize": 2
        },
        dataType: "json",
        success: function(res) {
            var data=res.data;
            var posts=data.list;
            var detail = "";
            var articleDiv = document.getElementById("allarticle");
            var pagenav = document.getElementById("pagenav");
            var title, replyCount, updated,  postId, murl;
            
            for(var i = 0, length_1 = posts.length; i < length_1; i++){
                (function (post){
                    postId = post.postId;
                typeId = post.typeId;
                var typename
                switch(typeId){
                    case 1:
                        typename="领养";
                        break;
                    case 2:
                        typename="救助";
                        break;
                    case 3:
                        typename="求偶";
                        break;
                    case 4:
                        typename="心得";
                        break;
                }
                // url = "./article.html?id=" + res['posts'][key]['id'];
                replyCount = post.replycount;
                if(replyCount==null){
                    replyCount=0;
                }
                title = post.title;
                updated = post.updated;
                // murl = "./updateArticle.html?id=" + articleid;
                    detail += `<tr>
                    <td style="display: none;">${postId}</td>
                    <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                    <td class="article-title"><a href="javascript:postdetail(${typeId},${postId})">${title}</a></td>
                    <td>${typename}</td>
                    <td class="hidden-sm">${replyCount}</td>
                    <td>${updated}</td>
                    <td><a href="javascript:updatedetail(${typeId},${postId})">修改</a><a onclick="itemdelete(this)" id="articleid"> 删除</a></td>
                  </tr>`;
                })(posts[i])
            }

            
            articleDiv.innerHTML = detail;
            userPagetotal = data.pages;
            var pageshow = "";
            pageshow +=`<li id="backpage"><a aria-label="Previous" onclick="backArticlePage()"> <span aria-hidden="true">&laquo;</span> </a></li>  `
            for(var j = 1;j<=userPagetotal;j++){
                pageshow += `<li><a href="javascript:jumpArticlePage2(${j})">${j}</a></li>`
            }
            pageshow +=`<li id="nextpage"><a onclick="nextArticlePage()" aria-label="Next"><span aria-hidden="true">&raquo;</span> </a></li>
            <li class="jumppage"><input type="button" onclick="jumpArticlePage()" value="跳到"><input type="text" class="pagearea jumppage2">页</li>
            <li><p id="totalpage"></p></li>`
            pagenav.innerHTML = pageshow;
            console.log("总页数" + userPagetotal);
            console.log("success get");
            console.log("当前页" + userPageNum);
        },

        error: function(res) {
            console.log("error");
        }
    };
    $.ajax(settings);
}