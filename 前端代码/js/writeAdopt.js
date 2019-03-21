var username=sessionStorage.getItem("userName");
var authorId=sessionStorage.getItem("id");

// var petName = $('#pet_name').val();
// var petType = $("input[name='type']:checked").val();
// var sex = $('input:radio[name="sex"]:checked').val();

// var options=$("#age option:selected");
// var age = options.text();
// var immune = $("input[name='whetherimmune']:checked").val();
// var sterilized = $("input[name='whethersterilized']:checked").val();
// var contactname = $('#contact_name').val();
// var phone = $('#contact_phone').val();
// var city = $('#citySelect').val();


//上传图片处理
var $addPicturehide = $('#addpicturehide');
var $addPicture = $('#addpicture');
var count = 0;
var photos = [];

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
        })

        $addPicturehide.before($holder)

    }

})


function addAdoption(){
    var petName = $('#pet_name').val();
    var petType = $("input[name='type']:checked").val();
    var sex = $('input:radio[name="sex"]:checked').val();
    
    var options=$("#age option:selected");
    var age = options.text();
    var immune = $("input[name='whetherimmune']:checked").val();
    var sterilized = $("input[name='whethersterilized']:checked").val();
    var contactname = $('#contact_name').val();
    var phone = $('#contact_phone').val();
    var city = $('#citySelect').val();
    var title = $('#title').val();
    var description = $('#detail').val();
    var summary = description.substr(0,66);
    summary = summary+"...";
    var myDate = new Date();
    var updated=myDate.toLocaleTimeString(); 
    console.log("username: "+username);
    console.log("sex: "+sex);
    console.log("petType: "+petType);
    console.log("age: "+age);
    console.log("city: "+city);
    console.log("photots: "+photos);
    console.log("title: "+title);
    console.log("detail: "+description);
    console.log("summary: "+summary);

    if(title == null || title == ''){
        swal("系统提示:请输入标题！","","warning");
    }
    else if(description == null || description == ''){
        swal("系统提示:请输入帖子内容！","","warning");
    }
    else{
         var settings = {
            url: "http://localhost:8080/Adopt/add2",
            type: "POST",
            crossDomain:"true",
            contentType:"application/json; charset=utf-8",
            xhrFields:{
            withCredentials:"true"
    	},
        data:JSON.stringify({
            'petName':petName,
            'age':age,
            'sex':sex,
            'sterilized':sterilized,
            'immune':immune,
            'isAdopt':"0",
        	'post':{
                'title':title,
                'summary':summary,
                'updated':updated,
                'description':description,
                'contactname':contactname,
                'phone':phone,
                'city':city,
                'petType':petType,
                'photos':photos
            }
        }),
        dataType:"json",
        success: function (res) {
            console.log(res);
            swal('发布成功','','success');
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


