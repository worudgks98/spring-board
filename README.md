# Spring Boot Board Project

## 프로젝트 소개

Spring Boot와 MySQL을 활용하여 구현한 게시판 프로젝트입니다.
게시글 작성, 조회, 수정, 삭제(CRUD) 기능을 구현하였습니다.

---

## 개발 환경

* Java 17
* Spring Boot 3
* Gradle
* MySQL
* JPA
* Thymeleaf
* IntelliJ IDEA

---

## 주요 기능

### 게시글 작성

* 작성자
* 비밀번호
* 제목
* 내용 입력 가능

### 게시글 목록 조회

* 전체 게시글 조회
* 작성일 표시
* 조회수 표시

### 게시글 상세 조회

* 게시글 상세 내용 확인
* 조회수 증가 기능

### 게시글 수정

* 비밀번호 검증 후 수정 가능

### 게시글 삭제

* 게시글 삭제 기능

---

## 데이터베이스 설정

`application.yml`

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db_codingrecipe
    username: 사용자명
    password: 비밀번호

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 실행 방법

```bash
./gradlew bootRun
```

실행 후 브라우저에서 접속:

```bash
http://localhost:8080/board/
```

---

## 프로젝트 구조

```bash
src
 ├─ controller
 ├─ service
 ├─ repository
 ├─ entity
 ├─ dto
 └─ templates
```

---

## 구현 화면

* 게시글 목록
* 게시글 상세조회
* 게시글 작성
* 게시글 수정

---

## 학습 내용

* Spring Boot MVC 패턴
* JPA Entity 활용
* DTO 변환
* Thymeleaf 템플릿 엔진
* MySQL 연동
* Git & GitHub 사용
