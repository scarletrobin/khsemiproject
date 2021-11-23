<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="accordion" id="accordion1" >
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              회원정보변경은 어디에서 하나요?
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">MY > 설정> [계정설정] 에서 변경</strong>
              <br><br>
              회원정보 변경은 MY에서 직접 처리가 가능합니다.
              <br>
              ※ 변경가능한 정보 : 성별, 생년월일, 연락처, 이메일 등      
              <br>                                     
              단, 연락처 변경은 본인 명의의 휴대폰번호 인증 시 자동 변경됩니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              상점정보 변경은 어디에서 하나요?
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">MY > 상점명 클릭 > [수정하기] 선택 후 변경가능</strong>
              <br><br>
              상점정보 변경은 MY에서 직접 처리가 가능하며 상점명, 상점주소, 상점소개 등 수정이 가능합니다.
              <br>
              ※ 상점명은 변경 후 30일 동안 수정이 불가능하며 한글, 영어, 숫자만 입력이 가능합니다.(최대 10자)
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              회원탈퇴 후 재가입을 할 수 있나요?
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">탈퇴 후 신규 상점으로 가입가능</strong>
              <br><br>
              회원탈퇴 후에는 동일 상점으로 재가입은 불가하며, 새로운 상점으로 가입이 가능합니다.(탈퇴 상점 복구 불가)
              <br>
              단, 탈퇴 후 재가입은 7일 동안 제한됩니다.  
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                회원탈퇴 시 개인정보는 삭제되나요?
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">탈퇴 시 개인정보는 지체없이 삭제</strong>
                <br><br>
                탈퇴 시 개인정보는 지체없이 삭제되며, 법령에서 일정기간 정보의 보관을 규정하거나 서비스 운영상 반드시 필요한 경우
                <br>
                개인정보처리방침에 공개한 내용과 같이 일정한 기간 동안 관련된 개인정보를 보관 후 파기합니다.
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                여러개의 계정을 만들 수 있나요?
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">1인 1계정 원칙</strong>
                <br><br>
                오렌지마켓은 1인 1계정 사용을 원칙으로 하고 있습니다.
                <br>
                여러 계정의 동일 사용자라 판단되는 경우 경고 및 제재조치가 될 수 있으니 유의해 주세요.
              </div>
            </div>
        </div>
    </div>
    <div class="accordion" id="accordion2" style="display: none;">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              [분쟁해결절차] 사기꾼의 주요 사기패턴 유형이 궁금해요
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">주요사기 패턴을 알려드려요!</strong>
              <br><br>
              #최근 빈번하게 발생하는 사기유형
              <br>
              ★아래의 계좌유형(가상계좌)의 경우 사기 가능성이 높아 입금유도 시 주의가 필요합니다!
              <br><br>                                    
              <p class="text-warning">카카오뱅크 3355 로 시작하는 계좌</p>
              <p class="text-warning">신한은행 562 또는 270으로 시작하는 계좌</p>
              <p class="text-warning">케이뱅크 7001로 시작하는 계좌</p>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              [분쟁해결절차] 상품금액을 미리 입금했는데, 판매자가 연락두절이에요.
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">사기정황 판단 후 이용 제재 조치</strong>
              <br><br>
              판매자에게 입금했으나 연락 두절인 경우, 아래의 요청 자료를 준비 하셔서 고객센터 > 1:1문의 접수를 부탁드립니다.
              <br>
              사기정황 판단 후 다른 계정으로도 문제를 발생 시키지 않도록 조치 하겠습니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              [분쟁해결절차] 상품을 보냈는데, 구매자가 상품금액을 입금하지 않았어요.
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">사기정황 판단 후 이용 제재 조치</strong>
              <br><br>
              <strong>거래 시, 구매자의 입금확인 후 거래를 진행해 주세요.</strong>
              <br>
              구매자에게 상품을 발송했으나 상품금액을 입금하지 않는 경우 고객센터 > 1:1문의 접수를 부탁드립니다.
              <br>
              사기정황 판단 후 다른 계정으로도 문제를 발생 시키지 않도록 조치 하겠습니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                [분쟁해결절차] 판매자 계좌/전화번호가 사기 이력이 있는지 확인할 수 있나요?
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">블랙리스트 여부로 판단가능합니다.</strong>
                <br><br>
                판매자 혹은 구매자가 블랙리스트일경우 불량회원일 확률이 있습니다. 
                <br>
                해당 회원들과 거래할때는 반드시 직거래를 이용하시길 바랍니다.
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                거래사기로 제재된 판매자가 다시 오렌지마켓을 이용 할 수 있나요?
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">거래사기 해결 시 까지 서비스 이용 불가</strong>
                <br><br>
                거래사기로 제재된 상점의 경우 탈퇴 후 재가입을 하더라도 서비스 이용이 제한됩니다.
                <br>
                단, 상대방 상점과 문제가 해결된 경우 이용 가능합니다.
              </div>
            </div>
        </div>
    </div>
    <div class="accordion" id="accordion3" style="display: none;">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              오렌지마켓에서 지켜야 할 매너
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">기본 매너를 지켜주세요!</strong>
              <br><br>
              <strong>기본적으로 지켜야할 매너엔 무엇이있을까요?</strong>
              <br>
              <ul>
                <li>서로 존중해요. 우리 존댓말로 대화해요.</li>
                <li>모두의 시간은 소중합니다. 시간 약속을 꼭 지켜주세요.</li>
                <li>절대로 중간에 연락 끊기는 일이 없도록 해요. (잠수는 절대 안 돼요!)</li>
                <li>따뜻한 감사 인사로 마무리를 지어요.</li>
                <li>어떤 상황에서도 욕설, 비방, 명예훼손 등의 언행은 지양해 주세요.</li>
                <li>늦은 시간 채팅은 부담스러울 수 있어요. 특히 새벽 시간에는 채팅을 자제해 주세요. </li>
                <li>택배 거래는 부득이한 경우에만 요청하시고 가능한 한 직거래로 해주세요.</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              오렌지마켓에서 직거래 잘하는 방법
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">따듯하고 건강하게 거래해요!</strong>
              <br><br>
              <strong>직거래를 잘하기 위해서 지켜야할 점은 무엇이 있을까요?</strong>
              <br>
              <ul>
                <li>공공장소에서 만나 거래해요!</li>
                <li>미성년자라면, 반드시 보호자와 함께 해주세요.</li>
                <li>모두의 시간은 소중해요. 약속 시간을 꼭 지켜주세요!</li>
                <li>만나서 가격을 무리하게 깎지 말아주세요.</li>
                <li>거래 후에는 따뜻한 감사 인사로 마무리해요😊</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              거래 순서는 꼭 쪽지의 선착순으로 해야 하나요?
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">선착순으로 하지 않아도 괜찮아요.</strong>
              <br><br>
              판매자의 판단에 따라 채팅의 선착순이 아니라 가까운 거리에 있는 이웃과 거래할 수도 있고 
              <br>거래 상대방의 매너온도, 거래 후기를 참고하여 구매자를 선택할 수도 있어요.
              <br>
              나와 거래하지 않더라도 판매자의 선택을 존중해 주세요. 
              <br>이는 중고거래의 특성으로 이해해 주시길 바랍니다. :)
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                비매너 평가를 하면 상대방이 알 수 있나요?
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">상대방은 알 수 없습니다!</strong>
                <br><br>
                누가 나에게 비매너 평가 했는지 알 수 없어요. <strong>매너 평가는 익명으로 처리돼요.</strong> 
                <br>(칭찬 매너 평가도 익명으로 처리됩니다.)
                <br>
                비매너 평가는 같은 항목을 2개 이상 받은 경우에만 항목 내용이 본인에게 노출돼요.
                <br>
                이렇게 하는 이유는 누가 남긴 비매너 평가인지 알 수 없게 하기 위함이에요.
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                블랙리스트가 달려있는 회원은 뭔가요?
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">신고횟수가 누적된 회원입니다.</strong>
                <br><br>
                잦은 비매너 행위 및 신고횟수가 일정 수 이상 누적된 회원들입니다.
                <br>
                해당 회원들과도 동일하게 거래할 수 있으나, 거래하기전, 하나의 지표가 될 수 있습니다.
              </div>
            </div>
        </div>
    </div>
    <div class="accordion" id="accordion4" style="display: none;">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              판매금지물품
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">꼭 지켜주세요!</strong>
              <br><br>
              *오렌지마켓은 현행법령상 판매가 허용되지 않는 불법상품 및 유해 상품을 판매하는 것은 제한하고 있어요.
              <br>
              판매 자격을 갖췄더라도 개인 간 거래를 지향하는 당근마켓에서는 해당 물품을 판매할 수 없어요.
              <br>
              *판매금지 물품은 <strong>나눔, 교환, 삽니다 게시글 또한 허용되지 않습니다.</strong>
              <br>
              *현행법을 위반할 경우 처벌 받을 수 있습니다. 이점 유의해 주시길 바랍니다.
              <br><br>
              다함께 공정하고 따뜻한 거래 문화를 만들어요. :)
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              형편없는 물건을 판매해요.
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              오렌지마켓은 <span class="text-info">판매금지 물품</span> 이외의 물건에 대해서는 직접적으로 제재하지 않아요.
              <br><br>
              하지만 사용할 수 없을 정도의 물건은 판매하지 않는 것이 좋아요. 
              <br>
              물건의 상태가 좋지 않다면 구매자로부터 환불 요구를 받을 가능성도 커져요.
              <br><br>
              물건의 상태가 심하게 좋지 않아서 판매할 가치가 없는 경우 <strong>[게시글 상단 오른쪽 점 3개 버튼 클릭 > 신고하기]</strong> 에서 신고해 주세요.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              종량제봉투를 거래할 수 있나요?
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">종량제 봉투는 개인이 판매할 수 없어요.</strong>
              <br><br>
              종량제봉투를 개인이 판매할 경우 [폐기물관리법] 제14조7항의 규정에 따라 300만 원 이하의 벌금에 처할 수 있어요.
              <br>
              국내 폐기물관리법상 종량제봉투는 개인이 판매할 수 없어요. 그래서 종량제봉투는 판매금지 물품에 해당합니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                반려동물을 무료로 분양할 수 있나요?
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">불가능합니다.</strong>
                <br><br>
                무료 분양이더라도 생명이 있는 동물을 거래하는 행위는 금지하고 있어요.
                <br>아직 오렌지마켓이 동물의 평생 가족을 찾아 주는 서비스로는 부족한 점이 많기 때문이에요.
                <br>
                생명이 있는 동물은 거래하지 말아 주세요. :)
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                나라미를 거래할 수 있나요?
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">판매금지 물품에 해당합니다.</strong>
                <br><br>
                나라미(정부가공쌀)는 국내 양곡관리법상 거래할 수 없어요. 
                <br>
                처벌 조항은 다음과 같아요. 참고해 주세요.
                <br>
                지정한 용도 외로 사용, 처분(시중유통, 재판매 등)한 사람은 [양곡관리법] 제32조의 규정에 따라 3년 이하의 징역 또는 사용, 처분한 양곡을 시가로 환산한 가액의 5배 이하의 벌금에 처한다.
              </div>
            </div>
        </div>
    </div>
    <div class="accordion" id="accordion5" style="display: none;">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              전안법이 중고거래에도 적용되나요?
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">개인간 중고거래는 단속 대상이 아닙니다.</strong>
              <br><br>
              <p>2018년 1월 1일부터 적용되는 전안법(전기용품 및 생활용품 안전관리법)에 대해 국가표준기술원에 문의한 내용을 안내해 드립니다.
              </p>
              <p>해당 법률에 따라 판매사 및 제조사는 단속 대상이나 개인 간 중고거래는 단속 대상이 아닙니다.</p>
              <p>해당 법률을 엄격히 적용하는 경우 전기용품과 생활용품의 중고거래 또한 전안법 대상이 될 수 있지만(KS 인증을 받은 상품만 판매 가능)</p>
              <p>실질적으로 개인 간 중고거래에 해당 법률을 적용하는 것은 무리가 있다는 점은 고려하여 KS인증제도를 운영하는 국가표준기술원도 위와 같이 답변한 것 같습니다.</p>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              새로고침이 뭔가요?
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
                새로고침은 현재 보고 있는 게시글의 상태를 최신 상태로 변경하는 것을 의미해요.

                게시글을 보고 있는 도중에 조회수, 관심수, 채팅수, 게시글의 내용이 달라질 수도 있어요.
                
                새로고침 하게 되면 변경된 내용이 보이게 됩니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              법정동과 행정동은 어떻게 다른가요?
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">법정동과 행정동의 차이를 설명해 드릴게요.</strong>
              <br><br>
              1. 행정동은 행정 구역을 나누어 관리하기 위한 주소라고 생각하시면 됩니다. 보통 주민센터(동사무소)에서 사용되는 주소예요.
              <br>
              2. 법정동은 법정 구역으로 전통적인 지역 구분입니다.
              <br><br>
              예를 들면 양재동은 법정동 이름이고, 양재1동, 양재2동은 행정동 이름입니다.
              <br>
              다행히 이 두 명칭이 같은 경우에는 큰 문제가 없지만 다른 경우에는 혼란스러울 수 있으니 이점 참고 부탁 드립니다. :)
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                오프라인 벼룩시장은 안 여나요?
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">아쉽네요 :(</strong>
                <br><br>
                오렌지마켓을 사용하는 여러분을 직접 만날 수 있는 기회인 만큼 오렌지마켓도 오프라인 벼룩시장을 얼른 열고 싶어요.
                <br>
                하지만 현재로서는 오프라인 벼룩시장을 기획하고 있지는 않아요. 안전하고 즐거운 오프라인 벼룩시장을 주최할 방법을 열심히 고민해 볼게요. :)
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                채팅을 보냈는데 상대방이 메시지를 읽지 않아요, 답장이 없어요
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">상대방이 메시지를 읽지 않는다면 여러가지 이유가 있을 수 있어요.</strong>
                <br><br>
                <ul>
                    <li>바빠서 채팅 메시지를 확인하지 못 하는 경우</li>
                    <li>의도적으로 채팅을 안 읽는 경우</li>
                    <li>핸드폰을 잃어버린 경우 </li>
                </ul>
                <br>
                다만 사기와 같은 긴급 상황이 아니라면 고객센터에서 대신 연락해 드리지 않으니 이점 양해 부탁 드려요.
                <br>
                답장이 늦더라도 조금씩 이해해 주시길 바랍니다. :)
                <br>
                만약 오래 기다렸는데도 답장이 오지 않는다면 아쉽지만 다른 사용자와 거래해 주세요.
              </div>
            </div>
        </div>
    </div>
    <div class="accordion" id="accordion6" style="display: none;">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              상품 찜하기란 무엇인가요?
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="">
            <div class="accordion-body">
              <strong class="text-primary">상품 페이지 > 하트 아이콘 선택 후 찜하기 가능</strong>
              <br><br>
              찜하기란 관심 상품을 모아서 확인할 수 있는 기능으로서 찜하기 후 'MY > 찜목록'에서 확인 가능합니다.
              <br>
              찜 해제를 원하시는 경우 한 상품 페이지 > 찜 버튼을 한번 더 누른 후 해제해 주세요.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              고객센터 운영시간은 어떻게 되나요?
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
                <strong class="text-primary">고객센터 운영시간은 평일 9시~18시(점심시간12시~13시), 주말/공휴일은 1:1 상담만 운영</strong>
                <br><br>
                평일은 전화상담과 1:1상담이 모두 운영되며, 주말 및 공휴일은 1:1문의 답변만 제공됩니다.    
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                본인인증을 하지 않을 경우 상품 판매가 불가한가요?
            </button>
          </h2>
          <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong class="text-primary">본인인증 후 상품 판매 등록 가능</strong>
              <br><br>
              본인인증을 하지 않은 상점은 서비스 이용이 제한되며 본인인증 후 상품 판매 등 서비스 이용이 가능합니다.
            </div>
          </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                상품 하자가 발견됐는데, 판매자 연락이 안돼요.
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">상품 하자가 발견된 경우 판매자 제재 조치</strong>
                <br><br>
                구매 전, 판매자에게 안내 받지 못한 상품 하자가 발견되었으나 판매자 연락이 안되는 경우 1:1문의로 접수해 주세요.
                <br>
                확인 후 운영정책에 따라 제재 조치 됩니다.
              </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                단순변심 교환이나 환불은 어떻게 하나요?
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong class="text-primary">교환/반품은 판매자와 협의 후 가능</strong>
                <br><br>
                중고상품은 개인간의 거래이므로 전자상거래법, 소비자보호법 등이 적용되지 않습니다.
                <br>
                물품을 받으신 후 단순변심 또는 안내 받지 못한 하자가 확인되어 교환/반품을 원하실 경우, 판매자와 협의를 통해 진행해 주시기 바랍니다.
                <br>
                단순변심 환불로 판매자와 협의된 경우 배송비는 구매자 부담이 될 수 있습니다.
              </div>
            </div>
        </div>
    </div>

