console.log('todo.js실행')
// JS 함수 정의 : function함수명(매개변수){실행문;}
// { key : value , key : value  } : 객체 vs


// 1. 할일등록 메소드
function doPost(){}

doGet(); // JS 실행시 최초 한번 실행
// 2. 할일 목록 출력
function doGet(){}
    // - 스프링(자바) 와 통신(주고 받기)
    // JQUERY AJAX
    // $.ajax(JSON 형식의 통신 정보)
    //$.ajax({})
    /*

    HTTP method : post , get ,put , delete 등등
        $.ajax({
                url :   spring controller url  / 통신 대상 식별 ,
                method : 'HTTP method / 통신 방법'  ,
                data : 'HTTP request value / 통신 요청으로 보낼 데이터 ' ,
                success : HTTP response function / 통신 응답 함수
            })


        $.ajax({
                url : 'spring controller mapping 주소'  ,
                method : 'mapping 방법'   ,
                data :   '보낼 데이터'   ,
                success : function result('받을 데이터'){ }
            })
    */
    $.ajax({
        url : '/todo/get.do'  ,
        method : 'get'   ,
        success : function result(resultValue){
        console.log(resultValue);
        // 통신 응답 결과를 HTML 형식으로 출력해주기

        // 1. 어디에
        let tbody = document.querySelector('table tbody')
        // 2. 무엇을
        let html = ``;
        for(let i = 0 ; i < resultValue.length ; i++){
            html += `
                <tr>
                <th>${resultValue[i].id}</th>
                <th>${resultValue[i].content}</th>
                <th>${resultValue[i].deadline}</th>
                <th>${resultValue[i].state}</th>
                </tr>
            `
        } // for end
        // 3. 대입
        tbody.innerHTML = html;
        }
    })

// 3. 할일 수정
function doPut(){}

// 4. 할일 삭제
function doDelete(){}