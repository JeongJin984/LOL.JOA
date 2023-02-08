## 3/29

### 주요 업데이트

- 환경변수 적용
  - SELENIUM_SERVER_IP: 셀레니움 주소(도커 혹은 쿠버네티스 IP & 포트(보통 4444))
  - CRAWLING_TARGET_URL: 크롤링 사이트(https://lol.fandom.com/wiki/LCK/2022_Season/Spring_Season)
- 도커 이미지 배포 완
- Dockerfile 작성

### 문제 및 해결

- 명령어가 인식이 잘 안되는 문제
  - docker run --name loljoa-crawler -e SELENIUM_SERVER_IP=172.23.0.2:4444 -e CRAWLING_TARGET_URL=https://lol.fandom.com/wiki/LCK/2022_Season/Spring_Season --net loljoa_selenium -d loljoa/lck_schedule_crawl
er:0.0.1
  - 위에 꺼 할때 명령어 파라미터 순서가 잘못되어도 에러가 생길 때가 있음
- 도커 네트워크에 하나로 묶어서 셀레니움 이미지와 현재 이미지가 통신이 가능해야함
  - 쿠버네티스에서는 CluserIP 사용해야 할 듯함