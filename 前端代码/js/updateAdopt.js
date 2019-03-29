

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
                var post=data.post;
                var petName = data.petName;
                var petType = post.petType;
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
                $(".pet_name").val(petName);
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
                $(".phone").text(phone);
                var city = post.city;
                $(".city").text(city);
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