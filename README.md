# 🎯 Duke Backend - 통합 백엔드 서비스

> Rolling 롤링페이퍼와 BLOB 소셜 미디어 서비스를 통합 제공하는 백엔드 시스템

## 🌐 서비스 구경하기

### Rolling 프로덕트(프론트서버)
> https://rolling-production.vercel.app

### API 문서(Swagger)
> https://15.165.187.153.nip.io/swagger-ui/index.html
> - Rolling API: `/recipients`, `/messages`, `/reactions`
> - BLOB API: `/blob/users`, `/blob/posts`


## 📝 프로젝트 소개

Duke Backend는 온라인 롤링페이퍼 서비스를 위한 백엔드 API 서버입니다.

사용자들이 온라인에서 손쉽게 롤링페이퍼를 만들고 공유할 수 있는 디지털 플랫폼의 백엔드 시스템입니다. 전통적인 종이 롤링페이퍼의 따뜻함과 정성을 디지털 환경에서도 느낄 수 있도록 설계되었습니다.

### 주요 기능
- 개인화된 롤링페이퍼 생성 및 관리
- 다양한 배경색상 옵션 (BEIGE, BLUE, GREEN, PURPLE)
- 메시지 작성 및 조회 기능
- 반응(이모지) 시스템
- RESTful API 제공으로 프론트엔드와 원활한 연동
- 통일된 응답 처리 시스템 (ServiceResult 패턴)
- 글로벌 예외 처리
- Swagger를 통한 API 문서화

### 내부 서비스 구조
Duke Backend는 내부적으로 BLOB 소셜 미디어 서비스도 포함하고 있으나, 현재는 Rolling 서비스가 주요 서비스로 운영되고 있습니다.

## 🛠️ 기술 스택

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-green?style=flat-square&logo=spring-boot)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-latest-green?style=flat-square&logo=spring)
![MariaDB](https://img.shields.io/badge/MariaDB-latest-blue?style=flat-square&logo=mariadb)
![Gradle](https://img.shields.io/badge/Gradle-8.13-blue?style=flat-square&logo=gradle)
![Lombok](https://img.shields.io/badge/Lombok-latest-red?style=flat-square&logo=lombok)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3.0-green?style=flat-square&logo=swagger)

## 🚀 설치 및 실행 방법

### 필수 요구사항
- JDK 17 이상
- Gradle 8.13 이상
- MariaDB

### 로컬 개발 환경 설정
1. 저장소 클론
```bash
git clone <repository-url>
cd duke-backend
```

2. 환경 변수 설정
```bash
export DB_URL=jdbc:mariadb://localhost:3306/duke_db
export DB_USERNAME=root
export DB_PASSWORD=yourpassword
```

3. 애플리케이션 빌드
```bash
./gradlew build
```

4. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 데이터베이스 설정
- MariaDB 사용
- 환경 변수로 연결 정보 관리 (DB_URL, DB_USERNAME, DB_PASSWORD)
- 초기 데이터는 data.sql을 통해 자동 로드

## 📋 주요 기능 및 API

### 데이터 모델
1. **Recipients** - 롤링페이퍼 수신자 정보
   - 배경색상 옵션: BEIGE, BLUE, GREEN, PURPLE
2. **Message** - 작성된 메시지 정보
3. **Reaction** - 메시지 반응(이모지) 정보

### API 엔드포인트
- `GET/POST /recipients` - 롤링페이퍼 관리
- `GET/POST /recipients/{id}/messages` - 메시지 관리
- `POST /recipients/{id}/reactions` - 반응 추가
- `GET /health` - 서버 상태 확인
- `GET /actuator/prometheus` - 모니터링 메트릭

### API 문서
- Swagger UI: http://localhost:8080/swagger-ui.html
- API 문서: http://localhost:8080/v3/api-docs

### 예외 처리
- **404 Not Found**: 리소스를 찾을 수 없는 경우
- **400 Bad Request**: 잘못된 요청 데이터
- **500 Internal Server Error**: 서버 내부 오류 발생 시

## 🏗️ 프로젝트 구조 및 아키텍처

### 레이어드 아키텍처
```
Controller → Service (Interface) → ServiceImpl → Repository → Entity
    ↓            ↓                      ↓             ↓           ↓
   DTO    ServiceResult         비즈니스 로직    JPA Query    DB Table
```

### 프로젝트 구조
```
duke-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/common/          # 공통 컴포넌트
│   │   │   │   ├── config/          # 공통 설정 (CORS, JPA 네이밍)
│   │   │   │   ├── dto/             # 공통 DTO (ServiceResult, PageResponseDto)
│   │   │   │   ├── exception/       # 공통 예외 처리
│   │   │   │   └── util/            # 공통 유틸리티
│   │   │   ├── com/rolling/         # Rolling 롤링페이퍼 서비스
│   │   │   │   ├── DukeApplication.java     # 메인 애플리케이션
│   │   │   │   ├── config/          # Rolling 전용 설정
│   │   │   │   ├── controller/      # Rolling API
│   │   │   │   ├── service/         # Rolling 비즈니스 로직
│   │   │   │   ├── repository/      # Rolling 데이터 액세스
│   │   │   │   └── model/           # Rolling 데이터 모델
│   │   │   └── com/blob/            # BLOB 소셜 미디어 서비스
│   │   │       ├── config/          # BLOB 전용 설정
│   │   │       ├── controller/      # BLOB API
│   │   │       ├── service/         # BLOB 비즈니스 로직
│   │   │       ├── repository/      # BLOB 데이터 액세스
│   │   │       ├── entity/          # BLOB 엔티티
│   │   │       ├── dto/             # BLOB DTO
│   │   │       ├── security/        # BLOB 보안 (TODO)
│   │   │       └── exception/       # BLOB 예외 처리 (TODO)
│   │   └── resources/
│   │       ├── application.properties      # 기본 설정
│   │       ├── application-prod.properties # 운영 환경 설정
│   │       └── data.sql                    # 초기 데이터
│   └── test/        # 테스트 코드
├── docs/            # 프로젝트 문서
│   ├── CLAUDE.md    # 개발 가이드
│   └── folder-tree.md # 폴더 구조
├── gradle/          # Gradle 래퍼
├── build.gradle     # Gradle 빌드 스크립트
└── settings.gradle  # Gradle 설정
```

### 아키텍처 다이어그램

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    Nginx    │────▶│ Spring Boot │────▶│   MariaDB   │
│  (Reverse   │     │     App     │     │  Database   │
│   Proxy)    │     │             │     │             │
└─────────────┘     └─────────────┘     └─────────────┘
       │                                       │
       │                                       │
       ▼                                       ▼
┌─────────────┐                      ┌─────────────────┐
│   Certbot   │                      │ Persistent Data │
│  (SSL 관리) │                      │     Volume      │
└─────────────┘                      └─────────────────┘
```

## 📊 애플리케이션 설정

### 개발 환경 설정 (application.properties)
```properties
# MariaDB 데이터베이스 설정
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JPA 설정 - 스키마 관리
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MariaDBDialect

# SQL 초기화 스크립트 실행 설정
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
spring.sql.init.data-locations=classpath:data.sql

# 로깅 설정
logging.level.com.rolling=ERROR
logging.level.org.springframework=ERROR

# Swagger OpenAPI 설정
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### 운영 환경 설정 (application-prod.properties)
```properties
# 운영 환경 설정
spring.jpa.hibernate.ddl-auto=validate
spring.sql.init.mode=never

# 로깅 레벨 조정
logging.level.com.rolling=WARN
logging.level.org.springframework=WARN

# 개발 도구 비활성화
spring.devtools.restart.enabled=false
spring.devtools.livereload.enabled=false

# 데이터베이스 커넥션 풀 최적화
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=10

# 성능 최적화 설정
spring.jpa.properties.hibernate.generate_statistics=false
spring.jpa.properties.hibernate.jdbc.batch_size=50
```

## 🚢 배포

### 인프라 구성
Rolling 서비스는 Docker 컨테이너 기반으로 구성되어 있으며, 다음과 같은 컴포넌트로 구성됩니다:

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    Nginx    │────▶│ Spring Boot │────▶│   MariaDB   │
│  (Reverse   │     │     App     │     │  Database   │
│   Proxy)    │     │             │     │             │
└─────────────┘     └─────────────┘     └─────────────┘
       │                                       │
       │                                       │
       ▼                                       ▼
┌─────────────┐                      ┌─────────────────┐
│   Certbot   │                      │ Persistent Data │
│  (SSL 관리) │                      │     Volume      │
└─────────────┘                      └─────────────────┘
```

### 멀티 스테이지 Docker 빌드
애플리케이션은 멀티 스테이지 Docker 빌드를 통해 최적화된 이미지로 빌드됩니다:

```dockerfile
# 빌드 스테이지
FROM openjdk:17-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# 실행 스테이지
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/build/libs/duke-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose 배포
전체 시스템은 Docker Compose를 통해 다음과 같은 서비스로 구성됩니다:

1. **app**: Spring Boot 애플리케이션
   - 자동 빌드 및 배포
   - MariaDB 의존성 관리
   - 애플리케이션 네트워크 연결

2. **nginx**: 웹 서버 및 리버스 프록시
   - HTTP/HTTPS 포트 노출 (80/443)
   - SSL 인증서 관리
   - 자동 설정 리로드
   - 애플리케이션으로 트래픽 라우팅

3. **certbot**: SSL 인증서 자동화
   - 인증서 자동 갱신
   - Let's Encrypt 연동

4. **mariadb**: 데이터베이스
   - 데이터 영속성을 위한 볼륨 마운트
   - 헬스체크 구성
   - 데이터베이스 초기화

### 배포 방법

#### 1. 환경 준비
```bash
# 필요한 디렉토리 구조 생성
mkdir -p nginx/conf nginx/ssl certbot/conf certbot/www
```

#### 2. SSL 인증서 준비
```bash
# 자체 서명 인증서 생성 (개발용)
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout nginx/ssl/key.pem -out nginx/ssl/cert.pem
```

#### 3. Docker Compose 실행
```bash
# 서비스 시작
docker-compose up -d

# 로그 확인
docker-compose logs -f
```

#### 4. 배포 확인
- HTTPS를 통해 서비스 접속 확인
- Swagger UI: https://[도메인]/swagger-ui.html

### 보안 및 성능 최적화
- HTTPS 리다이렉션 설정
- 최신 TLS 프로토콜 사용
- 안전한 암호화 알고리즘 적용
- 데이터베이스 커넥션 풀 최적화
- 주기적인 인증서 갱신 자동화

### 모니터링 및 유지보수
```bash
# 컨테이너 상태 확인
docker-compose ps

# 서비스 재시작
docker-compose restart [서비스명]

# 로그 확인
docker-compose logs -f [서비스명]

# 설정 변경 후 적용
docker-compose up -d --no-deps [서비스명]
```

### 확장성
- 수평적 확장을 위해 필요시 로드 밸런서 추가 가능
- 데이터베이스 복제 구성 가능
- 컨테이너 오케스트레이션 도구(Kubernetes 등)로 마이그레이션 가능

## 🧪 테스트

테스트 실행 방법:
```bash
./gradlew test
```

## 🔧 알려진 이슈 및 해결 방법

- **배경색상 Enum 처리**: Enum 타입 'BEIGE', 'BLUE', 'GREEN', 'PURPLE'과 정수 값 사이의 비교에서 타입 변환 문제 발생 가능
- **해결 방법**: 배경색상 필드 타입을 일관되게 유지하고, Enum <-> Integer 변환 시 적절한 컨버터 사용