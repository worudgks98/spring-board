# Spring Boot Board Project

## 프로젝트 소개

Spring Boot와 JPA를 활용한 게시판 프로젝트입니다.

회원가입 및 로그인 기능을 구현하고, 로그인한 사용자가 게시글을 작성하고 조회할 수 있는 게시판 시스템을 개발하고 있습니다.

---

## 개발 환경

- Java 17
- Spring Boot 3
- Spring Data JPA
- Thymeleaf
- MySQL
- Gradle
- IntelliJ IDEA

---

## 주요 기능

### 회원 기능

#### 회원가입

- 이메일
- 이름
- 비밀번호 입력
- 회원 정보 DB 저장

#### 로그인

- 이메일과 비밀번호 검증
- 로그인 성공 시 Session 생성

#### 로그아웃

- Session 제거

---

### 게시판 기능

#### 게시글 작성

- 제목
- 작성자
- 내용 저장

#### 게시글 목록 조회

- 전체 게시글 조회
- 작성일 조회
- 조회수 조회

#### 게시글 상세 조회

- 게시글 내용 확인
- 조회수 증가

#### 게시글 수정

- 작성한 게시글 수정

#### 게시글 삭제

- 게시글 삭제

---

## 프로젝트 구조

```text
src
 ├─ controller
 │   ├─ BoardController
 │   └─ MemberController
 │
 ├─ service
 │   ├─ BoardService
 │   └─ MemberService
 │
 ├─ repository
 │   ├─ BoardRepository
 │   └─ MemberRepository
 │
 ├─ entity
 │   ├─ BoardEntity
 │   └─ MemberEntity
 │
 ├─ dto
 │   ├─ BoardDTO
 │   └─ MemberDTO
 │
 └─ templates
     ├─ index.html
     ├─ save.html
     ├─ detail.html
     ├─ update.html
     ├─ list.html
     ├─ memberSave.html
     └─ login.html
