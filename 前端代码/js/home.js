window.onload = function(){
    console.log("testaaaa");
    loadAdopt();
}

function loadAdopt(){
    var settings={
        url:"http://localhost:8080/Adopt/home_adopt",
        crossDomain:"true",
        xhrFields: {
            withCredentials:"true"
        },
        type: "POST",
        data: {
            "pageSize": "4",
            "pageNum": "1"
        },
        dataType:"json",
        success:function(res){
            var adoptDiv = document.getElementById("adoptDiv");
            var content = "";
            var data=res.data;
            var adoptions=data.list;
            var post,photoAddress, title, petType,typeName, petName, sex, sexName,city, updated, replycount, photos;
            for(var i = 0, length_1 = adoptions.length; i < length_1; i++){
                (function (adoption){
                    petName=adoption.petName;
                    console.log(petName);
                    post=adoption.post;
                    title=post.title;
                    console.log(title);
                    updated=post.updated;
                    replycount=post.replycount;
                    var summary=post.summary;
                    if(replycount==null){
                        replycount=0;
                    }
                    petType=post.petType;
                    switch(petType){
                        case 1:
                            typeName="狗狗";
                            break;
                        case 2:
                            typeName="猫猫";
                            break;
                        case 0:
                            typeName="其它";
                            break;
                    }
                    sex=adoption.sex;
                    switch(sex){
                        case 1:
                            sexName="公";
                            break;
                        case 2:
                            sexName="母";
                            break;
                    }
                    city=post.city;
                    var url="./adopt_detail.html?id="+post.postId;
                    photos=post.photos;
                    if(photos!=null)
                    {
                        photoAddress=photos[0].photo_address;
                        console.log(photoAddress);
                    }
                    content +=
                        ` <article class="widget-post">
                        <div class="post-image">
                            <a href="${url}"><img src="${photoAddress}" alt=""></a>
                        </div>
                        <div class="post-body">
                            <h2><a href="${url}">${title}</a></h2>
                            <div class="summary">${summary}</div>
                            <div class="pet_info row"><span class="pet_msg col-md-3">宠物类型：${typeName}</span><span class="pet_msg col-md-3">宠物昵称：${petName}</span> <span class="pet_msg col-md-3">宠物性别: ${sexName}</span><span class="pet_msg col-md-3">所在地区：${city}</span></div>
                            <div class="post-meta">
                                <span><i class="fa fa-clock-o"></i>${updated}</span> <span><a href="post.html"><i class="fa fa-comment-o"></i> ${replycount}</a></span>
                            </div>
                        </div>
                    </article> `
                })(adoptions[i])
            }
            adoptDiv.innerHTML += content;
            adoptDiv.innerHTML += `<article class="widget-post">
            <a href="#" class="readmore">查看更多</a>
        </article> `;
            console.log("adopt success");
           
            
        },
        error:function(res){
            console.log("ajax error");
        }
    };
    $.ajax(settings);
}


//  <article class="widget-post">
//     <div class="post-image">
//         <a href="post.html"><img src="images/90x60-1.jpg" alt=""></a>
//     </div>
//     <div class="post-body">
//         <h2><a href="post.html">我家可爱小金毛等你来领养</a></h2>
//         <div class="pet_info">宠物类型：<span class="pet_msg">狗狗</span>宠物昵称：<span class="pet_msg">Dorry</span> 宠物性别:<span class="pet_msg">公</span>所在地区：<span class="pet_msg">广州</span></div>
//         <div class="post-meta">
//             <span><i class="fa fa-clock-o"></i> 2. august 2015</span> <span><a href="post.html"><i class="fa fa-comment-o"></i> 23</a></span>
//         </div>
//     </div>
// </article> 