# jwt-tutorial <img src="https://img.shields.io/badge/JSON%20Web%20Tokens-000000?style=flat&logo=JSON%20Web%20Tokens&logoColor=white"/> <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=Spring%20Boot&logoColor=white"/>

[Spring Boot JWT Tutorial](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard) 소스 코드입니다

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

### dependency
- Spring WEB
- Spring Security
- Spring Data JPA
- H2 Database
- Lombok
- Validation

### API
| 메소드 | URL | 설명 | 파라미터 |
|------|---|---|---|
| GET | /api/hello | hello |  |

