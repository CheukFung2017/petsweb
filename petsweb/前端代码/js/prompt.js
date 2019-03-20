var timer=null;
$(function () {
	 $("#accountname").html(sessionStorage.getItem("userName"));    
     console.log("role"+sessionStorage.role);
    console.log("houtaiid"+sessionStorage.userId);
    var myhead=sessionStorage.getItem("profile");
    $("#headimage").css("background",`url(${myhead}) no-repeat`);
    $("#headimage").css("background-position",`center center`);
    $("#headimage").css("background-size",`100%`);
    if(sessionStorage.role==1){
        console.log(sessionStorage.role);
        $("#myarticle").css("display","block");
        $("#articlemanger").css("display","none");
    }
    else{
        console.log(sessionStorage.role);
        $("#searcharea").css("display","block");
        $("#moreside").css("display","block");
        $("#safeside").css("display","block");
        $("#categoryside").css("display","block");
        $(".otherMenu1").css("display","block");
        $("#myarticle").css("display","block");

    }
});
function showPromptBox(){
// var oDiv1=document.getElementById('accountname');
        var oDiv2=document.getElementById('prompt-box');
       
            clearTimeout(timer);
            oDiv2.style.display='block';
        }

function closePromptBox(){
    // var timer=null;
    var oDiv2=document.getElementById('prompt-box');
    timer=setTimeout(function(){
            oDiv2.style.display='none';
            },500)
}

var myid=sessionStorage.getItem("userId");
// console.log(sessionStorage.getItem("nickname"));
function goArticle(){
	window.location.href=`myarticle.html?id=${myid}`;
}
function goCollection(){
	window.location.href=`mycollection.html?id=${myid}`;
}

// function pHlogout() {
//     var settings = {
//         url: "http://www.bedgasmblog.cn/user/logout",
//         type: "POST",
//         data: {},
//         dataType:"json",
//         success: function (data) {
//             if (data.success == true) {
//                 console.log("退出！");
//                 sessionStorage.clear();
//                 window.location.href = 'index.html';
//             }
//         },
//     };
//     $.ajax(settings);
// }

// var id=sessionStorage.getItem("id");
function goSetting(){
	window.location.href=`personinfo.html?userId=${myid}`;
} 