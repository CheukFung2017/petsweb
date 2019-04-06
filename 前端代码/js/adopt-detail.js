var id=sessionStorage.getItem("userId");
// var username=sessionStorage.getItem("userName");
window.onload = function(){
    loadAdoptDetail();
    testfunction();
    loadComment();
}

var isLogin=false;
var id=sessionStorage.getItem("userId");
var regex = /.*?id=([0-9]*)/ig;
    var detailUrl = window.location.href;
    var postId = regex.exec(detailUrl)[1]; 

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}       
function testfunction() {
    var settings = {
        url: "http://localhost:8080/user/userinfo",
          crossDomain:"true",
          async:false,
    xhrFields:{
        withCredentials:"true"
    },
        type: "POST",
        data: {},
        dataType: "json",
        success: function (data) {
            //用户没登录
            if(data.success==false){
                console.log(isLogin);
            }
            else{
                isLogin=true;
                console.log(isLogin);
            }
            // console.log("userinfo:"+data.success);
            
        }
    };
    $.ajax(settings);
}

function loadAdoptDetail(){
    var regex = /.*?id=([0-9]*)/ig;
    var detailUrl = window.location.href;
    var detailId = regex.exec(detailUrl)[1];   
    if(detailId){
        postId=detailId;
        console.log("postId: "+postId);
        var settings = {
            url: "http://localhost:8080/post/postdetail",
              crossDomain:"true",
            xhrFields:{
                withCredentials:"true"
            },
            type: "POST",
            async:false,
            data:{
                "postId":postId
            },
            dataType:"json",
            success:function(res){
                var data=res.data;
                var post=data.post;
                var petName = data.petName;
                var petType = post.petType;
                var title = post.title;
                $(".title").text(title);
                switch(petType){
                    case 1:
                        $(".pet_type").text("狗狗");
                        break;
                    case 2:
                        $(".pet_type").text("猫猫");
                        break;
                    case 3:
                        $(".pet_type").text("其它");
                        break;
                }
                var sex=data.sex;
                switch(sex){
                    case 1:
                        $(".sex").text("男");
                        break;
                    case 2:
                        $(".sex").text("女");
                        break;
                }
                var age = data.age;
                $(".pet_name").text(petName);
                $(".age").text(age);
                var immune = data.immune;
                switch(immune){
                    case 1:
                        $(".immune").text("已免疫");
                        break;
                    case 2:
                        $(".immune").text("未免疫");
                        break;
                    case 0:
                        $(".immune").text("不详");
                        break;
                }
                var sterilized = data.sterilized;
                switch(sterilized){
                    case 1:
                        $(".sterilized").text("已绝育");
                        break;
                    case 2:
                        $(".sterilized").text("未绝育");
                        break;
                    case 0:
                    $(".sterilized").text("不详");
                        break;
                }
                var contactname = post.contactname;
                $(".contact_name").text(contactname);
                var phone = post.phone;
                // $(".phone").text(phone);
                var city = post.city;
                $(".city").text(city);
                var isAdopted = data.isAdopted;
                switch(isAdopted){
                    case 1:
                     $(".adoptstate").text("等待领养");
                     break;
                    case 0:
                    $(".adoptstate").text("已被领养");
                    break;
                    default:
                    $(".adoptstate").text("等待领养");
                    break;

                }

                var description = post.description; 
                $(".adopt_description").text(description);
                var author = post.author;
                $(".author").text("by "+author);
                var updated = post.updated;
                $(".updated").text(updated);
                var replycount = post.replycount;
                if(replycount==null){
                    replycount=0;
                }
                $(".replycount").text(replycount);

                //轮播图
                var photos = post.photos;
                var len = photos.length;
                var imgDiv = document.getElementById("adopt_img");
                var content = "";
                content += `<ol class="carousel-indicators"><li data-target="#adopt_img" data-slide-to="0" class="active"></li>`;
                for(var i=1;i<len;i++){
                    content+=`<li data-target="#adopt_img" data-slide-to="${i}"></li>`;
                }
                content+=`</ol>`;
                content+=`<div class="carousel-inner"><div class="item active"><a href="${photos[0].photo_address}" target="_blank"><img class="itemimg" src="${photos[0].photo_address}" alt="First slide" style="height:260px"></a> </div>`;
                for(var j=1;j<len;j++){
                    content+=`<div class="item">
                    <a href="${photos[j].photo_address}" target="_blank"><img class="itemimg" src="${photos[j].photo_address}" alt="Second slide" style="height:260px"></a>
                    </div>`
                }
                content+=`</div>
                <a class="left carousel-control" href="#adopt_img" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#adopt_img" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>`;
                imgDiv.innerHTML += content;
            },
            error:function(res){
                console.log("ajax error");
            }


        };
        $.ajax(settings);
    }
}

function loadComment(){
    var regex = /.*?id=([0-9]*)/ig;
    var detailUrl = window.location.href;
    var detailId = regex.exec(detailUrl)[1];
    if (detailId) {
        postId = detailId;
        console.log("postId: " + postId);
        var html = $.ajax({
            async: true,
            crossDomain: "true",
            xhrFields: {
                withCredentials: "true"
            },
            url: "http://localhost:8080/comment/list",
            type: "post",
            data: {
                'postId': postId
            },
            dataType: "json",
            success: function(res) {
                var commentDiv = document.getElementById("commentDiv");
                var detail = "";
                var url = "";
                var commentDate, username, content,commentUserUrl,profile;
               for (var key in res['comments']){
                    username = res['comments'][key]['user']['userName'];
                    commentDate = res['comments'][key]['commentDate'];
                    // commentUserUrl="./personHome.html?id=" + res['comments'][key]['user']['id'];
                    content = res['comments'][key]['content'];
                    profile=res['comments'][key]['user']['profile'];
                    detail +=
                       ` <div class="comment-list-box clearfix">
                            <div class="comment-headimage" style="background:url(${profile}) no-repeat;background-position:center center;background-size:100%"></div>
                            <div class="comment-content">
                                <h2><a href="#" >${username}</a><span class="commentDate"> &nbsp &nbsp<i class="fa fa-clock-o"></i>${commentDate}</span></h2>
                                <p>${content}</p>
                            </div>
                        </div> `;
               }
                
            
                commentDiv.innerHTML += detail;
                console.log("commentsuccess");
            },
            error: function(res) {
                console.log("commenterror");
            }
        })
    } else {
        swal("没有id");
    }
}

function addComment() {
    if (isLogin) {
        var mycomment = $("#mycomment").val();
        var commentDate = new Date().format("yyyy-MM-dd hh:mm:ss");

        if (mycomment.length < 140) {
            var settings = {
                url: "http://localhost:8080/comment/save",
                crossDomain: "true",
                xhrFields: {
                    withCredentials: "true"
                },
                type: "POST",
                data: {
                    'content': mycomment,
                    'commentDate': commentDate,
                    'userId': id,
                    'postId': postId
                },
                dataType: "json",
                success: function(res) {
                    console.log("addcomment success");
                    console.log("userId: "+id);
                    console.log("postId: "+postId);
                    // swal('评论成功！','','success');
                    // setTimeout('location.reload()',1000);
                },

                error: function(res) {
                    console.log("add error");
                }
            };
            $.ajax(settings);
        } else
            swal('评论过长，140字以内！','','success');
    } else {
        swal('登录之后才能评论喔~','','error');
        
        // confirm("登录之后才能评论喔");
    }

}


function applyadopt(){
    var applyname = $("#applyname").val();
    var applysex = $("input[name='applysex']:checked").val();
    var applyage = $("applyage").val();
    var experience = $("input[name='experience']:checked").val();
    var job = $("#job").val();
    var applycity = $("#citySelect").val();
    var applyphone = $("#applyphone").val();
    var content = $("#content").val();

    if(isLogin){
        var settings = {
            url: "http://localhost:8080/Adopt/apply",
            crossDomain: "true",
            xhrFields: {
                withCredentials: "true"
            },
            type: "POST",
            data: {
                'sex':applysex,
                'age':applyage,
                'experience':experience,
                'job':job,
                'city':applycity,
                'phone':applyphone,
                'content': content,
                'userId': id,
                'postId': postId,
                'applyname':applyname
            },
            dataType: "json",
            success: function(res) {
                console.log("apply success");
                console.log("userId: "+id);
                console.log("postId: "+postId);
                $("#applyModal").modal('hide');
                // swal('评论成功！','','success');
                // setTimeout('location.reload()',1000);
            },
    
            error: function(res) {
                console.log("add error");
            }
        };
        $.ajax(settings);
    }
    else{
        swal('登录之后才能评论喔~','','error');
        
        // confirm("登录之后才能评论喔");
    }

  
}