# Rolling 백엔드 서비스

## 프로젝트 개요
Rolling은 온라인 롤링페이퍼 서비스의 백엔드 시스템입니다. 사용자들이 메시지와 감정을 공유할 수 있는 디지털 롤링페이퍼 플랫폼을 제공합니다.

## 기술 스택
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database
- Gradle 8.13
- Lombok

## 주요 기능
- 롤링페이퍼 생성 및 관리
- 메시지 작성 및 조회
- 배경색상 설정 (BEIGE, BLUE, GREEN, PURPLE)
- REST API 제공
- 글로벌 예외 처리

## 개발 환경 설정

### 필수 요구사항
- JDK 17 이상
- Gradle 8.13 이상

### 로컬 개발 환경 설정
1. 저장소 클론
```bash
git clone <repository-url>
cd rolling
```

2. 애플리케이션 빌드
```bash
./gradlew build
```

3. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 데이터베이스 설정
- H2 인메모리 데이터베이스를 사용
- 데이터베이스 파일은 `./data/rolling_db`에 저장됨
- H2 콘솔: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:file:./data/rolling_db
  - 사용자명: sa
  - 비밀번호: (빈 값)

## 데이터 모델

### 주요 엔티티
1. **Recipients** - 롤링페이퍼 수신자 정보
   - 배경색상 옵션: BEIGE, BLUE, GREEN, PURPLE

2. **Message** - 작성된 메시지 정보

3. **User** - 사용자 정보

## API 엔드포인트

현재 프로젝트는 다음과 같은 예외 처리가 구현되어 있습니다:

- **404 Not Found**: 리소스를 찾을 수 없는 경우
- **500 Internal Server Error**: 서버 내부 오류 발생 시

## 프로젝트 구조
```
rolling/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── rolling/
│   │   │           ├── config/      # 설정 클래스
│   │   │           ├── controller/  # API 컨트롤러
│   │   │           ├── model/       # 데이터 모델
│   │   │           ├── repository/  # 데이터 액세스 계층
│   │   │           ├── service/     # 비즈니스 로직
│   │   │           └── exception/   # 예외 처리
│   │   └── resources/
│   │       └── application.properties  # 애플리케이션 설정
│   └── test/        # 테스트 코드
├── data/            # H2 데이터베이스 파일
├── gradle/          # Gradle 래퍼
├── build.gradle     # Gradle 빌드 스크립트
└── settings.gradle  # Gradle 설정
```

## 애플리케이션 설정

### 주요 설정 (application.properties)
```properties
# 서버 설정
spring.application.name=rolling
server.port=8080

# H2 데이터베이스 설정
spring.datasource.url=jdbc:h2:file:./data/rolling_db;DB_CLOSE_ON_EXIT=TRUE;AUTO_SERVER=TRUE
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA 설정
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.naming.physical-strategy=com.rolling.config.jpa.SnakeCasePhysicalNamingStrategy

# 로깅 설정
logging.level.com.rolling=DEBUG
logging.level.org.springframework=INFO
logging.level.org.springframework.beans=DEBUG
logging.level.org.hibernate=DEBUG

# 타임존 설정
spring.jackson.time-zone=Asia/Seoul
```

## 알려진 이슈
- **배경색상 Enum 처리**: Enum 타입 'BEIGE', 'BLUE', 'GREEN', 'PURPLE'과 정수 값 사이의 비교에서 타입 변환 문제 발생 가능
- **해결 방법**: 배경색상 필드 타입을 일관되게 유지하고, Enum <-> Integer 변환 시 적절한 컨버터 사용

## 배포
- 프로덕션 환경에 배포하기 전에 적절한 데이터베이스 설정과 보안 설정을 구성해야 합니다.
- 필요에 따라 `application-prod.properties` 파일을 생성하여 프로덕션 환경 설정을 관리할 수 있습니다.

## 테스트
테스트 실행:
```bash
./gradlew test
```

## 라이센스
이 프로젝트는 MIT 라이센스 하에 배포됩니다. 