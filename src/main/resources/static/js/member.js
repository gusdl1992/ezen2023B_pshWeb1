console.log( 'member.js' );

// 1. 회원가입
function signup(){
    console.log( "signup() ");
    // 1. HTML 입력값 호출[ document.querySelector() ]
    // 1. 데이터 하나씩 가져오기
//    let id  = document.querySelector('#id').value;      console.log( id );
//    let pw  = document.querySelector('#pw').value;      console.log( pw );
//    let name = document.querySelector('#name').value;   console.log( name );
//    let phone = document.querySelector('#phone').value; console.log( phone );
//    let email = document.querySelector('#email').value; console.log( email );
//    let img = document.querySelector('#img').value;     console.log( img );

    // 폼 가져오기 querySelector -> 하나만 가져오기기 , querySelectorAll  -> 전체 가져오기
    // DTO 로 데이터 전달시 HTML 에서는 name 값 이 DTO 값과 동일 해야 한다.
    let signUpForm = document.querySelectorAll('.signUpForm');
    console.log(signUpForm);
    let signUpFormData = new FormData(signUpForm[0]);
    console.log(signUpFormData);    // new FormData : 문자 데이터가 아닌 바이트 데이터로 변환. ( 첨부파일 필수 )

    // --- 유효성검사
    // 2. 객체화 [ let info = { }  ]
//    let info = {
//        id : id , pw : pw , name : name , phone : phone , email : email , img : img
//    }; console.log( info );
    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신[ JQUERY AJAX ]
    $.ajax({
            url : '/member/signup',
            method : 'POST',
//            data :  info ,
            data : signUpFormData ,
            contentType : false ,
            processData : false ,
            success : function ( result ){
                console.log( result);
                // 4. 결과
                if( result ) {
                    alert('회원가입 성공');
                    location.href = '/member/login';
                }else{
                    alert('회원가입 실패');
                }
            }
        })

}
// 2. 로그인
function login(){
    console.log("login()");
    // 1. HTML 입력값 호출
    let id = document.querySelector('#id').value; console.log(id);
    let pw = document.querySelector('#pw').value; console.log(pw);
    // 2. 객체화
    let info = { id : id , pw : pw }; console.log( info );

    // 3. 서버와 통신
    $.ajax({
       url : '/member/login',   // controller 매핑 주소
       method : 'POST',         // controller POST , GET  매핑
       data :  info ,           // controller 매개변수
       success : function ( result ){ console.log(result);  // controller return
           // 4. 결과
           if(result){
               alert('로그인 성공!')
               // JS 페이지 전환
               location.href="/";   // 로그인 성공시 메인페이지로
           }else{
               alert('로그인 실패')
           } // if 끝
       } // 익명함수 끝
    }) // ajax 끝


} //  메소드 엔드

function onChangeImg(event){
    console.log('체인지 : preimg')
    console.log(event); // 현재 함수를 실행한 input
    console.log(event.files); // 현재 input의 첨부파일들
    console.log(event.files[0]); // 첨부파일들 중에서 첫번째 파일

    // 1. input 에 업로드 된 파일을 바이트로 가져오기
        // new FileReader() : 파일 읽기 관련 메소드 제공
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드 
    fileReader.readAsDataURL(event.files[0]);
    console.log(fileReader);
    console.log(fileReader.result);
    // 3. 파일 onload 정의
    fileReader.onload = e =>{
        console.log(e);                 // ProgressEvent
        console.log(e.target);
        console.log(e.target.result);   // 여기에 읽어온 첨부파일 바이트
        document.querySelector('#preimg').src = e.target.result;
    }
    
}

/*
    함수 정의 방법
        1. function 함수명( 매개변수 ){}
        2. function(매개변수){}
            let 변수명 = function(매개변수){}   JS OK , JAVA X
            let 변수명 = {
                e : function(매개변수){ }
            }
        3. (매개변수) => { }
            let 변수명 = (매개변수) => {}   JS OK , JAVA X
            let 변수명 = {
                e : (매개변수) => { }
            }



*/


/*
$.ajax({
   url : '서버 매핑 주소 ',
   method : '서버 매핑 방법',
   data :  서버 요청 보낼 매개변수  ,
   success : function ( 서버 응답 받은 매개변수 ){ }
})



*/