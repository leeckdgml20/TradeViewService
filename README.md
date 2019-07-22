# 거래 내역 조회 서비스 (TradeViewService)
거래내역, 고객, 지점정보 데이터를 토대로 요구사항에 맞는 서비스를 제공.

### 버전
빌드 및 실행 환경
JDK 1.8
Maven 3

### 개발 프레임워크
IDE Spring Tool Suite 4

### 소스 내려받기
'''sh
$ git clone https://github.com/leeckdgml20/TradeViewService
'''
### 설치 방법
* Root 이동
'''sh
$ cd TradeViewService
'''
* 컴파일
'''sh
$ mvn compile
'''
* 패키징
'''sh
$ mvn package
'''
### 실행 방법
* WAS 실행
'''sh
$ mvn spring-boot:run</br>
$ java -jar target/TradeViewService-1.0.war
'''
* 단위 테스트
'''sh
$ mvn test
'''
* Client 접속
> http://localhost:8080/main

#### 문제 해결방법
