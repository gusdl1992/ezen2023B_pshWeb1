console.log('JS 실행');

// 회원가입 요청
function signup(){
    console.log("signup()")
    // 1. 입력받은 데이터 가져오기
    let mid = document.querySelector('#mid').value;
    console.log(mid)
    let mpw = document.querySelector('#mpw').value;
    console.log(mpw)
    let mphone = document.querySelector('#mphone').value;
    console.log(mphone)
    // 2. 객체화
    let info ={
        mid : mid ,
        mpw : mpw ,
        mphone : mphone
    };console.log(info)

        // 2. ajax 함수
        $.ajax({
           url : '/member/signUp',
           method : 'post',
           data : info ,
           success : function ( result ){ console.log(result);
                // 4. 결과
                if( result ){
                alert('회원가입 성공');
                window.location.href ='/'
                }
                else{    alert('회원가입 실패');
                   window.location.href ='/'
                }
           }
        }) // ajax end
}

// 로그인 요청
function signIn(){
    console.log('signIn()')
    // 1. 입력받은 데이터 가져오기
    let mid = document.querySelector('#mid').value;
    console.log(mid)
    let mpw = document.querySelector('#mpw').value;
    console.log(mpw)
    // 2. 객체화
    let info ={
        mid : mid ,
        mpw : mpw
    };console.log(info)

        // 2. ajax 함수
        $.ajax({
           url : '/member/signIn',
           method : 'post',
           data : info ,
           success : function ( result ){ console.log(result);
                // 4. 결과
                if( result ){
                alert('로그인 성공');
                window.location.href ='/board/view'
                }
                else{  alert('로그인 실패');
                   window.location.href ='/'
                }
           }
        }) // ajax end


}