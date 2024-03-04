
// 섬머노트 실행 Run summernote
$(document).ready(function() {
    // 썸머노트 옵션 객체
    let option = {
        lang : 'ko-KR' , // 한글패치
        height : 500  // 에디터 세로크기
    }

  $('#summernote').summernote(option);
});

// 1. 글쓰기
function onWrite(){
    console.log("onWrite()");
    // 1. 폼 DOM 가져온다
    let boardWriteForm =  document.querySelector('.boardWriteForm');
    // 2. 폼 바이트 객체 변환
    let boardWriteFormData = new FormData(boardWriteForm);
    // 3. ajax 첨부파일 폼 전송 
    $.ajax({
        url : "/board/write.do" ,
        method : "post",
        data : boardWriteFormData ,
        contentType : false ,
        processData : false ,
        success : (r)=>{
            console.log(r);
            if(r == 0 ){
                alert('글쓰기 실패 : 관리자에게 문의(DB 오류)')
            }else if(r == -1){
            alert('글쓰기 실패 : 관리자에게 문의(첨부파일 오류)')
            }else if(r == -2){
                alert('로그인 세션이 존재하지 않습니다.(잘못된 접근)')
                location.href="/member/login";
            }else if(r >= 1){
               alert('글쓰기 성공');
               location.href="/board/view?bno=" +r;
            }
        }

    });
    
}