# 일정 관리 API V2

## API 명세서
- 모든 응답은 application/json 타입 사용

### 일정 생성하기
- URL : /api/schedules
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 201 Created
- Description
  - 제목은 1글자 이상 100글자 이하
  - 내용은 null 제외
  - 로그인 하지 않을 경우 이용 불가
  - 사용자의 정보는 로그인 후 세션으로 처리됨


- 요청 예시
```json
{
    "title":"TEST"
    , "content":"testing"
}  
``` 
- 응답 예시
```json
{
    "id": 1,
    "userId": 1,
    "userName": "tester",
    "title": "TEST",
    "content": "testing",
    "createAt": "2026-01-10T21:19:46.352912",
    "updateAt": "2026-01-10T21:19:46.352912"
}
```
  
### 일정 개별 조회하기
- URL : /api/schedules/:schedule_id
- HTTP Method : GET
- Response HTTP Status : 200 OK


- 응답 예시
```json
{
    "id": 1,
    "userId": 1,
    "userName": "tester",
    "title": "TEST",
    "content": "testing",
    "commentCount": 5,
    "createAt": "2026-01-10T21:19:45.064324",
    "updateAt": "2026-01-10T21:19:45.064324"
}  
``` 

### 일정 전체 조회하기
- URL : /api/schedules
- HTTP Method : GET
- Query Parameter : pageNumber, pageSize
  - 페이징 관련 파라미터 (pageNumber 는 조회 페이지를 (default 0), pageSize는 한 페이지 당 조회 크기 (default 10)) 
- Response HTTP Status : 200 OK
- Description
  - 두 쿼리 파라미터는 필수 값이 아님
  - 수정일 기준으로 내림차순 정렬


- 전체 조회 예시
```
url : /schedules?pageNumber=0?pageSize=10
```
```json
[
    {
        "id": 3,
        "userId": 3,
        "userName": "tester",
        "title": "TEST",
        "content": "testing",
        "commentCount": 0,
        "createAt": "2026-01-10T21:19:46.352912",
        "updateAt": "2026-01-10T21:19:46.352912"
    },
    {
        "id": 2,
        "userId": 2,
        "userName": "tester",
        "title": "TEST",
        "content": "testing",
        "commentCount": 0,
        "createAt": "2026-01-10T21:19:45.736706",
        "updateAt": "2026-01-10T21:19:45.736706"
    },
    {
        "id": 1,
        "userId": 1,
        "userName": "tester",
        "title": "TEST",
        "content": "testing",
        "commentCount": 5,
        "createAt": "2026-01-10T21:19:45.064324",
        "updateAt": "2026-01-10T21:19:45.064324"
    }
]
``` 

### 일정 수정하기
- URL : /api/schedules/:schedule_id
- HTTP Method : PUT
- Content-Type : application/json
- Response HTTP Status : 200 OK
- Description 
  - 제목과 내용만 수정 가능
  - 제목은 1글자 이상 100글자 이하
  - 내용은 null 제외
  - 로그인 하지 않을 경우 사용 불가
  - 로그인 계정이 작성한 일정만 수정 가능

- 요청 예시
```
url : /api/schedules/1
```
```json
{
    "title":"TEST2"
    , "content":"testing2"
}
```

- 성공 응답 예시
```json
{
    "id": 1,
    "userId": 1,
    "userName": "tester",
    "title": "TEST2",
    "content": "testing2",
    "createAt": "2026-01-10T21:19:45.064324",
    "updateAt": "2026-01-10T21:19:45.064324"
}
```

### 일정 삭제하기
- URL : /api/schedules/:schedule_id
- HTTP Method : DELETE
- Response HTTP Status : 204 No Content
- Description
  - 로그인 하지 않을 경우 사용 불가
  - 로그인 계정이 작성한 일정만 삭제 가능
    
----

### 유저 생성하기 (회원가입)
- URL : /api/users
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 201 Created
- Description
  - 이름은 2글자 이상 20글자 이하
  - 이메일은 이메일 형식이여야함
  - 이미 가입된 이메일은 다시 가입할 수 없음
  - 비밀번호는 8자리 부터 20자리 이하
  - 비밀번호는 @$!%*?&^ 중 하나의 특수문자, 영문 소문자와 대문자, 숫자가 한 글자씩 이상 포함되어야함

- 요청 예시
```json
{
    "userName":"tester"
    , "email":"tester@test.com"
    , "password":"1234ASDFasdf!"
}
```

- 응답 예시
```json
{
    "id": 1,
    "userName": "tester",
    "email": "tester@test.com",
    "createAt": "2026-01-10T23:09:56.132236",
    "updateAt": "2026-01-10T23:09:56.132236"
}
```

### 유저 개별 조회하기
- URL : /api/users/:user_id
- HTTP Method : GET
- Response HTTP Status : 200 OK
- Description
  - 로그인 하지 않을 경우 이용 불가
  - 로그인 계정의 정보만 조회 가능

- 응답 예시
```
url : /api/users/1
```
```json
{
    "id": 1,
    "userName": "tester",
    "email": "tester@test.com",
    "createAt": "2026-01-10T21:19:35.473852",
    "updateAt": "2026-01-10T21:19:35.473852"
}
```

### 유저 전체 조회하기
- URL : /api/users
- HTTP Method : GET
- Response HTTP Status : 200 OK

- 응답 예시
```json
[
    {
        "id": 1,
        "userName": "tester",
        "email": "tester@test.com",
        "createAt": "2026-01-10T21:19:35.473852",
        "updateAt": "2026-01-10T21:19:35.473852"
    },
    {
        "id": 2,
        "userName": "tester",
        "email": "tester@test2.com",
        "createAt": "2026-01-10T23:09:56.132236",
        "updateAt": "2026-01-10T23:09:56.132236"
    }
]
```

### 유저 수정하기
- URL : /api/users/:user_id
- HTTP Method : Patch
- Content-Type : application/json
- Response HTTP Status : 200 OK
- Description
  - 로그인 하지 않을 경우 이용 불가
  - 로그인 계정의 정보만 수정 가능
  - 이름은 2글자 이상 20글자 이하
  - 이메일은 이메일 형식이여야함
  - 이미 가입된 이메일로는 이메일을 수정할 수 없음
  - 비밀번호는 8자리 부터 20자리 이하
  - 비밀번호는 @$!%*?&^ 중 하나의 특수문자, 영문 소문자와 대문자, 숫자가 한 글자씩 이상 포함되어야함

- 요청 예시
```json
{
    "userName":"tester4"
    , "password":"9999asdfASDF@"
}
```

- 응답 예시
```json
{
    "id": 1,
    "userName": "tester4",
    "email": "tester2@test.com",
    "createAt": "2026-01-10T21:19:35.473852",
    "updateAt": "2026-01-11T00:30:24.761259"
}
```

### 유저 삭제하기
- URL : /api/users/:user_id
- HTTP Method : DELETE
- Response HTTP Status : 204 No Content
- Description
  - 로그인 하지 않을 경우 사용 불가
  - 본인의 계정만 삭제 가능


### 유저 로그인
- URL : /api/login
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 200 Created
- Description
  - 로그인 이메일은 필수
  - 로그인 비밀번호는 필수
  - bcrypt 인코딩, 디코딩 적용

- 요청 예시
```json
{
    "email":"tester2@test.com"
    , "password":"9999asdfASDF@"
}
```

- 응답 예시
```json
{
    "id": 1,
    "email": "tester2@test.com",
    "userName": "tester2"
}
```

### 유저 로그아웃
- URL : /api/logout
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 204 No Content
- Description
  - 로그인 하지 않은 경우엔 사용 불가

----


### 댓글 생성하기
- URL : /api/schedules/:schedule_id/comments
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 201 Created
- Description
  - 댓글은 1글자 이상, 200자 이하
  - 로그인 하지 않을 경우 이용 불가
  - 사용자의 정보는 로그인 후 세션으로 처리됨


- 요청 예시
```json
{
    "content":"comment testing"
} 
``` 
- 응답 예시
```
url : /api/schedules/1/comments
```
```json
{
    "id": 6,
    "content": "comment testing",
    "scheduleId": 1,
    "userId": 1,
    "createdAt": "2026-01-10T23:32:02.417805",
    "updatedAt": "2026-01-10T23:32:02.417805"
}
```

### 댓글 개별 조회하기
- URL : /api/schedules/:schedule_id/comments/:comment_id
- HTTP Method : GET
- Response HTTP Status : 200 OK

- 응답 예시
```
url : /api/schedules/1/comments/1
```
```json
{
    "id": 1,
    "content": "comment testing",
    "scheduleId": 1,
    "userId": 1,
    "createdAt": "2026-01-10T21:19:48.9426",
    "updatedAt": "2026-01-10T21:19:48.9426"
}
```

### 댓글 전체 조회하기
- URL : /api/schedules/:schedule_id/comments/:comment_id
- HTTP Method : GET
- Response HTTP Status : 200 OK

- 응답 예시
```
url : /api/schedules/1/comments
```
```json
[
    {
        "id": 1,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T21:19:48.9426",
        "updatedAt": "2026-01-10T21:19:48.9426"
    },
    {
        "id": 2,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T21:20:35.086185",
        "updatedAt": "2026-01-10T21:20:35.086185"
    },
    {
        "id": 3,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T21:20:35.733297",
        "updatedAt": "2026-01-10T21:20:35.733297"
    },
    {
        "id": 4,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T21:20:36.324973",
        "updatedAt": "2026-01-10T21:20:36.324973"
    },
    {
        "id": 5,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T21:20:36.933328",
        "updatedAt": "2026-01-10T21:20:36.933328"
    },
    {
        "id": 6,
        "content": "comment testing",
        "scheduleId": 1,
        "userId": 1,
        "createdAt": "2026-01-10T23:32:02.417805",
        "updatedAt": "2026-01-10T23:32:02.417805"
    }
]
```

----

## 예외처리

### URL이 잘못된 경우
```json
{
    "message": "api/usesr 에 따른 자원을 찾지 못했습니다"
}
```

### 입력 파라미터가 잘못된 경우 (유효성 체크)
```json
{
    "message": "제목은 비어 있을 수 없습니다"
}
```

### 세션 관련 예외처리
```json
{
    "message": "로그인 되지 않은 상태의 접근입니다"
}

{
    "message": "정상적인 접근이 아닙니다"
}
```

### 리소스를 찾지 못한 경우
```json
{
    "message": "없는 유저 입니다"
}

{
    "message": "없는 일정 입니다"
}

{
    "message": "해당 일정에 댓글이 없습니다"
}
```

### 중복 데이터
```json
{
    "message": "이미 가입된 이메일입니다, 다른 이메일로 수정해주세요"
}
```

## ERD
> 논리적 관계만 가짐 (ConstraintMode.NO_CONSTRAINT 사용)
<img width="612" height="618" alt="Schedule_Manager_V2" src="https://github.com/user-attachments/assets/99bd3341-2197-47ca-ba15-3b7db046cd77" />
