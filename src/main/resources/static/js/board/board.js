// 1. 전체 출력용 : 함수 : 매개변수X , 반환X , 언제 실행할껀지 -> 스크립트가 열릴때

doViewList(1) // 첫페이지 실행
function doViewList(page){
    console.log('doViewList')
    $.ajax({
        url : "/board/do",
        method : "get",
        data : {'page' : page },
        success : (r) => {
            console.log(r);
            //=============== 테이블에 레코드 구성 =====================
            // 1. 어디에
            let boardTableBody = document.querySelector("#boardTableBody");
            // 2. 무엇을
            let html = '';
                // 서버가 보내준 데이터를 출력
                // 1. r 이 객체로 변환 되어 forEach 사용시 r.list.forEach 사용
                r.list.forEach( board => {
                    console.log( board );
                    html += `<tr>
                                 <th>${board.bno}</th>
                                 <td>${board.btitle}</td>
                                 <td><img src="/img/${board.mimg}" style="width:20px; border-radius:50%";>${board.mid}</td>
                                 <td>${board.bdate}</td>
                                 <td>${board.bview}</td>
                             </tr>`
                })
            // 3. 출력
            boardTableBody.innerHTML = html;

            // ========== 페이지네이션 구성 ========================
            // 1. 어디에
            let pagination = document.querySelector('.pagination');
            // 2. 무엇을
            let pagehtml = ``;
            // 이전 버튼 ( 만약에 현재페이지가 1페이지 이면 1페이지 고정 )
            pagehtml += `
            <li class="page-item"><a class="page-link" onclick="doViewList(${ page -1 < 1 ? 1 : page - 1 })">이전</a></li>
            `
            //페이지 번호 버튼 (1부터 마지막 페이지 까지 )
            for(let i = r.startBtn; i<= r.endBtn ; i++){
                // + 만약에 i 가 현재페이지와 같으면 active 클래스 삽입 아니면 생략 ( 조건부 랜더링 )
                pagehtml +=
                `<li class="page-item ${ i == page ? 'active' : ''} "><a class="page-link" onclick="doViewList(${ i })">${i}</a></li>
                `
            }

            // 다음 버튼 ( 만약에 현재페이지가 마지막 페이지 이면 현재 페이지 고정 )
            pagehtml += `
            <li class="page-item"><a class="page-link" onclick="doViewList(${ page +1 > r.totalPage ? r.totalPage : page+1 })">다음</a></li>
            `
            // 3. 출력
            pagination.innerHTML = pagehtml;


        } // success end

    }); // ajax end
    return;
}