# SpringBoot 게시판 프로젝트(CRUD) &nbsp;&nbsp;  
SpringBoot와 JPA, AWS 등 추가 공부를 위한 개인 프로젝트입니다. 
추가적으로 확장해 나아갈 예정입니다.
* Service - ServiceImpl 구조를 사용하여 인터페이스와 구현체를 분리함으로써 구현체는 독립적으로 변경하거나 확장하여도 사용하는 클라이언트에는 영향을 주지 않도록 설계.
* Spring Data JPA 사용하여 데이터 처리 구현</br>
* Spring Security를 사용하여 세션 기반의 인증 구현</br>
* AWS EC2(Linux) 설정 및 Docker 기반 소스 배포</br>
* AWS RDS 데이터베이스(MySQL) 설정 및 EC2 인스턴스와 연동</br>
* AWS S3를 사용하여 파일 업로드, 삭제 기능 구현</br>
* Docker 이미지 사용하여 서버 배포</br>
* JUnit을 사용하여 단위테스트 코드 작성</br>


## Description
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
> Create
>> 회원가입, 게시글 작성, 첨부파일 추가

> Read
>> 게시글 목록(전체, Paging), 단일 게시글, 첨부파일 다운로드 및 미리보기

> Update
>> 게시글 수정, 첨부파일 수정

> Delete
>> 게시글 삭제


