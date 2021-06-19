# jwt-tutorial <img src="https://img.shields.io/badge/JSON%20Web%20Tokens-000000?style=flat&logo=JSON%20Web%20Tokens&logoColor=white"/> <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=Spring%20Boot&logoColor=white"/>

[Spring Boot JWT Tutorial](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard) 소스 코드입니다

### BLOG
[JWT란 ?](https://soyeon207.github.io/%EA%B0%9C%EB%B0%9C/2021/06/20/jwt.html)

### SetUp
```
git clone https://github.com/soyeon207/study-jwt.git
cd study-jwt
```

http://localhost:8080/h2-console 접속 후 아래 쿼리 실행 
```
INSERT INTO USER (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
```

postman 에 API 추가 후 Tests 에 아래 쿼리 추가 
```
var jsonData = JSON.parse(responseBody)
pm.globals.set("jwt_tutorial_token", jsonData.token);
```
Authorization 에 Bearer Token 에 `{{jwt_tutorial_token}}` 추가 

### dependency
- Spring WEB
- Spring Security
- Spring Data JPA
- H2 Database
- Lombok
- Validation
- io.jsonwebtoken

### API
| 메소드 | URL | 설명 | 파라미터 | 권한 |
|------|---|---|---|---|
| GET | /api/hello | hello |  | X |
| POST | /api/authenticate | 로그인 API | username, password | X |
| POST | /api/signup | 회원가입 API | username, password, nickname | X |
| GET | /api/user | 현재 security 에 저장된 user 정보 가져오는 API | | USER, ADMIN |
| GET | /api/user/{nickname} | 해당 nickname 에 해당하는 정보 가져오는 API | | ADMIN |

