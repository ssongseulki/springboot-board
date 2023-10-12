# SpringBoot 게시판 CRUD
## 13.124.205.54:8080/
* 기본 CRUD 게시판을 Serber Side Rendering 방식으로 구현한 연습 프로젝트</br>
* Service - ServiceImpl 구조를 사용하여 인터페이스와 구현체를 분리함으로써 구현체는 독립적으로 변경하거나 확장하여도 사용하는 클라이언트에는 영향을 주지 않도록 설계함. 이는 객체지향의 특징인 다형성과 개방-폐쇄(OCP, 기존 코드는 변경하지 않으면서 기능은 추가될 수 있도록 확장에는 열려있어야 하고 수정은 폐쇄적이어야 하는)를 지향한다.
* Spring Security를 사용한 회원 인증 처리 구현 </br>
* AWS EC2 클라우드 서버 연동 </br>
* AWS RDS 데이터베이스 사용(Mysql) </br>
* AWS S3를 사용하여 첨부파일 관리 </br>

## Discription
### 1. 개발환경
> 공통         
>> 운영체제 : Windows / Linux(ubuntu)         
>> 개발 Tool : IntelliJ, Workbench        
>> 데이터베이스 : MySQL

>BACK-END 
>> Language Framework : Java Spring Boot, JPA

>FRONT_END
>> Thymeleaf, Jquery

### 2. ER_Diagram
<img src="https://github.com/ssongseulki/mvc-board/assets/68680087/d79eb2ff-5ec8-4470-aefb-f8490aad06f0.png" width = "600" height = "300">

### 3. 주요 기능
(댓글, 대댓글 추가 예정)
> Create
>> 회원가입, 게시글 작성, 첨부파일 추가

> Read
>> 게시글 목록(전체, Paging), 단일 게시글, 첨부파일 다운로드 및 미리보기

> Update
>> 게시글 수정, 첨부파일 수정

> Delete
>> 게시글 삭제


