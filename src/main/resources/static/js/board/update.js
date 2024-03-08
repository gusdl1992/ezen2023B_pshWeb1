let bno = new URL(location.href).searchParams.get('bno');

// 섬머노트 실행 Run summernote ( $ : jquery 문법 :  )
    // JS : window.onload = function   VS           JQUERY : $(document).ready(function(){} : 문서(HTML) 이 모두 렌더링 되었을떄
    // JS : document.querySelector('#summernote') vs JQUERY : $('#summernote')
    // JS :                 X                       JQUERY : AJAX






// 1. 게시물 개별 조회
onView();
function onView(){
    console.log("onView");
    $.ajax({
        url : "/board/view.do",
        method : "get" ,
        data : { "bno" : bno  }, // 쿼리스트링 방식
        success : (r)=>{
            console.log(r);
            document.querySelector('.bcno').value = r.bcno;
            document.querySelector('.btitle').value = r.btitle;
            document.querySelector('.bcontent').value = r.bcontent;
            document.querySelector('.bfile').innerHTML = r.bfile;
            // 썸머노트 옵션 객체
            let option = {
                lang : 'ko-KR' , // 한글패치
                height : 500  // 에디터 세로크기
            }
            $('#summernote').summernote(option); // 썸머노트 실행
        }
    });
}

// 2. 게시물 수정
function onUpdate(){
    // 1. 폼을 가져온다.
    let boardUpdateForm = document.querySelector('.boardUpdateForm');
    // 2. 폼객체화
    let boardUpdateFormData = new FormData(boardUpdateForm);

        // + 폼 객체에 데이터 추가. [ HTML 입력 폼 외 데이터 삽입 가능 ]
        // boardUpdateFormData.set( 키 , 값 );
        // 폼데이터객체명.set( 속성명(name) , 데이터(value) );
        boardUpdateFormData.set('bno' , bno);

    // . 멀티파트 폼 전송

    $.ajax({
        url : "/board/update.do" ,
        method : 'put' ,
        data : boardUpdateFormData ,
        contentType : false ,
        processData : false ,
        success : (r) =>{
            if(r){alert('수정성공'); location.href="/board/view?bno=" + bno;}
            else{ alert('수정실패') }
        }
    });
}