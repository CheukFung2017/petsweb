var myhead=sessionStorage.getItem("profile");
var id=sessionStorage.getItem("userId");
var username=sessionStorage.getItem("userName");


window.onload=function(){
    loadInfo();
     $('#username').html(username);
}

var imgsrc;

$('.touxiang').change(function(){
                var f=this.files[0];
                var formData=new FormData();
                formData.append('smfile',f);
                
            

                $.ajax({
                    url: 'https://sm.ms/api/upload',
                    type: 'POST',
 
                    success: function(data){
                        
                        console.log(data);
                         $("#finalImg").prop("src",data.data.url);     
                         imgsrc=data.data.url;
                    },               
                    error: function(data){
                        console.log(data);
                    },
                    
                    data:formData,
                    cache: false,
                    contentType: false,
                    processData: false
                    
                });
            });
            


function loadInfo(){
    var settings = {
        url: "http://localhost:8080/user/userinfobyid",
          crossDomain:"true",
        xhrFields:{
            withCredentials:"true"
        },
        type: "POST",
        async:false,
        data: {
            'id':id
        }, 
        dataType: "json",
        success: function(res) {
          
            var realname,phone,email,profile;
            
            realname = res['user']['realname'];
            profile = res['user']['profile'];
            phone = res['user']['phone'];
            email=res['user']['email'];	
            console.log("phone: "+phone);
            // $('#username').html(username);
            $('#finalImg').attr('src',profile);
           
            $('#realname').val(realname);
            $('#email').val(email);
            if(phone!=null){
                $('#phone').val(phone.toString());
            }
  			// $('#phone').val(phone;
            // var u= "data:image/png;base64,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";
            $(".myhead").css("background",`url(${profile}) no-repeat`);
		    $(".myhead").css("background-position",`center center`);
		    $(".myhead").css("background-size",`100%`);
            sessionStorage.setItem("profile", profile)
            console.log("in success");
            // if(res.total==0){
            //  $(".js-load-more").hide();
            //  articleDiv.innerHTML = "抱歉，搜索不到您要的内容";
            // }
        },

        error: function(res) {
            console.log("load error");
        }
    };
    $.ajax(settings);
}

function saveInfoAjax(){
    console.log("savetest");
    // var phone2 =parseInt($('#phone').val());
 

	var settings = {
        url: "http://localhost:8080/user/modify",
        type: "POST",
        crossDomain:"true",
        xhrFields:{
        withCredentials:"true"
    	},
        data: {
        	'userName': username,
        	'realname': $('#realname').val(),
        	'email':  $('#email').val(),
        	'phone': parseInt($('#phone').val()),
        	'userId': id,
            'profile': imgsrc,
            // "password":"AAA007aaa"
        },
        dataType:"json",
        success: function (res) {
            console.log(res);
            console.log("src:  "+imgsrc)
            swal('修改成功！','','success');
            setTimeout("location.reload()",2000);
        },
        error: function (res){
        	console.log("save error");
        }
    };
    $.ajax(settings);
}

