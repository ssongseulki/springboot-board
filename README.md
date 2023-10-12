# SpringBoot 게시판 CRUD &nbsp;&nbsp;  <a href="http:13.124.205.54:8080/"><img src="https://img.shields.io/badge/SSONG's BOARD-E4405F?style=flat-square&logoColor=white"/></a> 👈Click!

* 기본 CRUD 게시판을 Server Side Rendering 방식으로 구현한 연습 프로젝트</br>
* Service - ServiceImpl 구조를 사용하여 인터페이스와 구현체를 분리함으로써 구현체는 독립적으로 변경하거나 확장하여도 사용하는 클라이언트에는 영향을 주지 않도록 설계.
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
>> Java Spring Boot, JPA

>FRONT_END
>> Thymeleaf, Jquery, Bootstrap

### 2. ER_Diagram
<img src="https://github.com/ssongseulki/mvc-board/assets/68680087/d79eb2ff-5ec8-4470-aefb-f8490aad06f0" width = "600" height = "300">

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


