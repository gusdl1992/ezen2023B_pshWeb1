
console.log("안녕2")
allview()
function write2(){
    let content = document.querySelector("#content").value
    $.ajax({
            url : "/todo",
            method:"post",
            data : {content:content},
            success : function(result){
                console.log(result)
                if(result == true){
                    //화면 갱신    // 4.   출력
                    alert("등록성공")
                    allview()
                }
            }
        })
}

function allview(){
    $.ajax({
            url: "/allview",
            method: "get",
            success : function result(resultValue){
            let html = ""
            let div =document.querySelector('div')
            for( i= 0 ; i < resultValue.length; i++){
            html += `번호 : ${resultValue[i].no} , 할일: ${resultValue[i].content}, 상태 : ${resultValue[i].state} <button onclick="up(${resultValue[i].no})">상태변경</button><button onclick="del(${resultValue[i].no})">삭제
            <button onclick="upda(${resultValue[i].no})">할일수정</button>
            </button><br/>`;
            }
            div.innerHTML = html;
            }
        })

}

function up(no){
        $.ajax({
                url : "/rework",
                method:"post",
                data : {no:no},
                success : function(result){
                    console.log(result)
                    if(result == true){
                        allview();
                    }
                }
            })
}

function del(no){
    $.ajax({
        url : "/del",
        method:"post",
        data : {no:no},
        success : function(result){
            console.log(result)
            if(result == true){
                allview();
            }
        }
    })
}

function upda(no){
    let content = document.querySelector("#content").value
    info= {no:no,content:content}
    $.ajax({
            url : "/update",
            method:"post",
            data : info,
            success : function(result){
                console.log(result)
                if(result == true){
                    allview();
                }
            }
        })
}