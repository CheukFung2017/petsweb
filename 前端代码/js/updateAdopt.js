window.onload=function(){
    loadAdopt();
}
var $addPicturehide = $('#addpicturehide');
var $addPicture = $('#addpicture');
var photos = [];
var count = 0;
var adoptId,postId;

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

function loadAdopt(){
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
                adoptId = data.adoptId;
                var post=data.post;
                var petName = data.petName;
                $("#pet_name").val(petName);
                var petType = post.petType;

                switch(petType){
                    case 1:
                        $("input[type=radio][name=type][value=1]").attr("checked",'checked');
                         break;
                    case 2:
                        $("input[type=radio][name=type][value=2]").attr("checked",'checked');
                        break;
                    case 3:
                        $("input[type=radio][name=type][value=0]").attr("checked",'checked');
                        break;
                }

                var sex=data.sex;
                switch(sex){
                    case 1:
                        $("input[type=radio][name=sex][value=1]").attr("checked",'checked');
                        break;
                    case 2:
                        $("input[type=radio][name=sex][value=2]").attr("checked",'checked');
                        break;
                }
                var age = data.age;
                $("#age").val(age);
                var immune = data.immune;
                switch(immune){
                    case 1:
                        $("input[type=radio][name=whetherimmune][value=1]").attr("checked",'checked');
                        break;
                    case 2:
                        $("input[type=radio][name=whetherimmune][value=2]").attr("checked",'checked');
                        break;
                    case 0:
                        $("input[type=radio][name=whetherimmune][value=0]").attr("checked",'checked');
                        break;
                }
                var sterilized = data.sterilized;
                switch(sterilized){
                    case 1:
                        $("input[type=radio][name=whethersterilized][value=1]").attr("checked",'checked');
                        break;
                    case 2:
                         $("input[type=radio][name=whethersterilized][value=2]").attr("checked",'checked');
                        break;
                    case 0:
                        $("input[type=radio][name=whethersterilized][value=0]").attr("checked",'checked');
                        break;
                }
                var contactname = post.contactname;
                $("#contact_name").val(contactname);
                var phone = post.phone;
                $("#contact_phone").val(phone);
                var city = post.city;
                $("#citySelect").val(city);
                var title = post.title;
                $("#title").val(title);
                var description = post.description;
                $("#detail").val(description);
               

                //轮播图
                var imgs = post.photos;
                var len = imgs.length;
                count = len;
                for(var ph=0;ph<len;ph++)
                {       
                    var $img = $(document.createElement('img'))
                    var imgsrc = imgs[ph].photo_address;
                $img.attr('src', imgsrc)
                $img.attr('class', 'pet-picture')
                var $hover = $(document.createElement('div'))
                $hover.attr('class', 'pet-picture-hover')
                $hover.attr('value',ph);

                var $holder = $(document.createElement('div'))
                $holder.attr('class', 'pet-picture-holder')
                $holder.append($img)
                $holder.append($hover)

                $hover.on('click', function () { 
                    $(this).parent().remove()
                    count--;
                    //移除photos List中指定下标的图片对象
                    photos.splice($hover.val(),1);
                    console.log($hover.val()+"has been remove");
                })

                $addPicturehide.before($holder)

                var p1 ={};
                    p1.photoAddress=imgsrc;
                    photos.push(p1);

                    console.log(photos[ph].photoAddress);
                }
                
                
                
            },
            error:function(res){
                console.log("ajax error");
            }


        };
        $.ajax(settings);
    }
}


if(count<=6)
{
    $addPicture.on('click', function () {
         $addPicturehide.click()
    })
}
else
{
    swal("最多只能上传6张图片", "", "wrong");
     setTimeout(function () { location.reload() }, 1500);
}

$addPicturehide.on('change', function (e) {
    var reader = new FileReader();
    var file = this.files[0];
    var flag = count;
    if (!/image\/\w+/.test(file.type)) {
        alert("请确保文件为图像类型！");
        return false;
    }
     var formData=new FormData();
            formData.append('smfile',file);
            
            $.ajax({
                url: 'https://sm.ms/api/upload',
                type: 'POST',
                success: function(data){
                    console.log(data);
                    count++;
        //             $('#res').html(JSON.stringify(data.data.url));
                    var p ={};
                    p.photoAddress=data.data.url;
                    photos.push(p);
                    console.log("flag: "+flag);
                },
                error: function(data){
                    console.log(data);
                },
                
                data:formData,
                cache: false,
                contentType: false,
                processData: false
                
            });
    reader.readAsDataURL(file);
    reader.onload = function (e) {
        var $img = $(document.createElement('img'))
        $img.attr('src', this.result)
        $img.attr('class', 'pet-picture')
        var $hover = $(document.createElement('div'))
        $hover.attr('class', 'pet-picture-hover')
        $hover.attr('value',flag);
        var $holder = $(document.createElement('div'))
        $holder.attr('class', 'pet-picture-holder')
        $holder.append($img)
        $holder.append($hover)

        $hover.on('click', function () { 
            $(this).parent().remove()
            count--;
            //移除photos List中指定下标的图片对象
            photos.splice($hover.val(),1);
            console.log($hover.val()+"has been remove");
        })

        $addPicturehide.before($holder)

    }
    

})


function modifyAdoption(){
    var petName = $('#pet_name').val();
    var petType = $("input[name='type']:checked").val();
    var sex = $('input:radio[name="sex"]:checked').val();
    
 
    var age = $("#age").val();
    var immune = $("input[name='whetherimmune']:checked").val();
    var sterilized = $("input[name='whethersterilized']:checked").val();
    var contactname = $('#contact_name').val();
    var phone = $('#contact_phone').val();
    var city = $('#citySelect').val();
    var title = $('#title').val();
    var description = $('#detail').val();
    var summary = description.substr(0,50);
    summary = summary+"...";
    // var myDate = new Date();
    var updated = new Date().format("yyyy-MM-dd hh:mm:ss");
    // var updated=myDate.toLocaleTimeString(); 


    if(photos.length==0){
        var p2 ={};
                    p2.photoAddress="https://i.loli.net/2019/03/25/5c9893c3d7bf5.jpeg";
                    photos.push(p2);
    }

    if(title == null || title == ''){
        swal("系统提示:请输入标题！","","warning");
    }
    else if(description == null || description == ''){
        swal("系统提示:请输入帖子内容！","","warning");
    }
    else{
         var settings = {
            url: "http://localhost:8080/Adopt/modify",
            type: "POST",
            crossDomain:"true",
            contentType:"application/json; charset=utf-8",
            xhrFields:{
            withCredentials:"true"
    	},
        data:JSON.stringify({
            'adoptId':adoptId,
            'petName':petName,
            'age':age,
            'sex':sex,
            'sterilized':sterilized,
            'immune':immune,
            'isAdopt':"0",
        	'post':{
                'postId':postId,
                'title':title,
                'summary':summary,
                'updated':updated,
                'description':description,
                'contactname':contactname,
                'phone':phone,
                'city':city,
                'petType':petType,
                'photos':photos,
                'typeId':1
            }
        }),
        dataType:"json",
        success: function (res) {
            console.log(res);
            swal('修改成功','','success');
            console.log(photos);
            // setTimeout("location.reload()",2000);
        },
        error: function (res){
            console.log("save error");
            console.log(res);

        }
    };
    $.ajax(settings);
    }
   
}