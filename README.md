<!--Header-->
# 1. instagram_server_api
- ProjectName : **instagram server**
- DevelopPeriod : *2023.03.24 ~ 2023.04.13*
---
# 2. Info
- 목표
  네카라쿠배 4년차 현직 개발자와 함께하는 인스타그램 서버만들기 챌린지
  API 가이드라인을 따라 instagram 서버를 구현한다.
  
  | **시스템 구조도 아키텍쳐** |
  사진첨부

  | **ERD** |
  ![InstaERD](https://user-images.githubusercontent.com/74723818/230729128-6da89542-5256-4356-9e91-98e2b021f0d4.PNG)

  | **DB Schema** |
  사진첨부
  
- API 스펙
  - 글 <생성, 수정, 삭제>
  - 댓글 <생성, 수정, 삭제>
  - 답글 <생성, 수정, 삭제>
  - 피드 조회
  - 회원 가입
  - 로그인
  - 회원 탈퇴
  - 프로필 <조회, 수정>
  - 팔로우, 언팔로우
  - DM 목록 조회, DM 상세 내역 조회, DM 전송
  - 토큰 재발급
---
# 3. Team Building
- **BackEnd**
 - [이지우](https://github.com/CordHouse)
 - [최현준]()
---
# 4. Skill
```text
- Jwt 
- Security
- QueryDsl
- CI / CD
 - Github Actions
 - Elastic BeanStalk
- Pagenation
```
---
# 5. Config
```text
1. Jwt access_token = 120분
2. Jwt refresh_token = 7일
3. Security whiteList = 회원가입, 로그인, 프로필 조회
```
---
# 6. Memoir(회고록)
- 노션 링크 첨부

---
# 7. Convention
| **Git Convention** |
```text
Git Convention
feat : 기능추가
fix : 버그 수정
refactor : 리팩토링, 기능은 그대로 두고 코드를 수정
style : formatting, 세미콜론 추가 / 코드 변경은 없음
chore : 라이브러리 설치, 빌드 작업 업데이트
docs : 주석 추가 삭제, 문서 변경
```
